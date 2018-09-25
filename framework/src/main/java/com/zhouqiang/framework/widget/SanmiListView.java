package com.zhouqiang.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.AbsListView;
import android.widget.ListView;

import com.zhouqiang.framework.image.ImageTask;
import com.zhouqiang.framework.image.ImageWorker;

/**
 * 自定义ListView
 * <p>
 * 1.下载获取图片可调用该类{@link #addTask(int position, int index, ImageTask task)}方法;
 * </p>
 * <p>
 * 2.自定义了滑动监听{@link BaseScrollListener},请调用
 * {@link #setBaseScrollListener(BaseScrollListener listener)} , {@link #getBaseScrollListener()}
 * 方法设置或获取监听。 <b>PS(设置原监听 {@link OnScrollListener
 * OnScrollListener}仍然有效)</b>
 * </p>
 * <p>
 * 3.自定义了监听{@link BaseSizeChangedListener},请调用
 * {@link #setBaseSizeChangedListener(BaseSizeChangedListener listener)} ,
 * {@link #getBaseSizeChangedListener()}方法设置或获取监听
 * </p>
 *
 * @author YANG ZT
 */
public class SanmiListView extends ListView {

    private SparseArray<SparseArray<ImageTask>> tasks = new SparseArray<SparseArray<ImageTask>>();
    private ImageWorker imageWorker;// 图片下载器
    private OnScrollListener onScrollListener;// 由于设置了自定义滑动监听,如果再设置滑动监听的话只需记录一下,在自定义监听中调用即可

    public SanmiListView(Context context) {
        this(context, null);
    }

    public SanmiListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SanmiListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (isInEditMode()) {
            return;
        }
        imageWorker = new ImageWorker(context.getApplicationContext());
        setOnScrollListener(new ScrollListener());
    }

    /**
     * add a imageTask
     *
     * @param position the position in the ListView
     * @param index    the index of the task in the position
     * @param task     the task
     */
    public void addTask(int position, int index, ImageTask task) {
        if (!imageWorker.isThreadControlable()) {
            imageWorker.loadImage(task);
        }

        // 需要异步执行的任务，添加进任务队列
        SparseArray<ImageTask> tasksInPosition = tasks.get(position);
        if (imageWorker.loadImage(task)) {
            if (tasksInPosition == null) {
                tasksInPosition = new SparseArray<ImageTask>();
                tasks.put(position, tasksInPosition);
            }
            tasksInPosition.put(index, task);
        } else {
            if (tasksInPosition != null)
                tasksInPosition.remove(index);
        }
    }

    // 执行任务
    private void excuteTasks(int first, int last) {
        for (int i = first; i <= last; i++) {
            SparseArray<ImageTask> tasksInPosition = tasks.get(i);
            if (tasksInPosition != null) {
                int size = tasksInPosition.size();
                for (int index = 0; index < size; index++) {
                    int key = tasksInPosition.keyAt(index);
                    ImageTask task = tasksInPosition.get(key);
                    imageWorker.loadImageByThread(task);
                }
            }
        }

        // 清空不可见条目的任务
        int size = tasks.size();
        for (int index = 0; index < size; index++) {
            int key = tasks.keyAt(index);
            if (key < first || key > last) {
                tasks.remove(key);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (baseSizeChangedListener != null)
            baseSizeChangedListener.onSizeChanged(this, w, h, oldw, oldh);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        if (l instanceof ScrollListener) {
            onScrollListener = null;
            super.setOnScrollListener(l);
        } else {
            onScrollListener = l;
        }
    }

    // 自定义滑动监听
    private class ScrollListener implements OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (onScrollListener != null)
                onScrollListener.onScrollStateChanged(view, scrollState);
            switch (scrollState) {
                case SCROLL_STATE_IDLE:
                    int first = getFirstVisiblePosition();
                    int last = getLastVisiblePosition();
                    excuteTasks(first, last);
                    imageWorker.setThreadControlable(false);
                    if (baseScrollListener != null)
                        baseScrollListener.onStop(SanmiListView.this);
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
                    imageWorker.clearTasks();
                    imageWorker.setThreadControlable(true);
                    if (baseScrollListener != null)
                        baseScrollListener.onStart(SanmiListView.this);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (onScrollListener != null)
                onScrollListener.onScroll(view, firstVisibleItem,
                        visibleItemCount, totalItemCount);
        }

    }

    private BaseScrollListener baseScrollListener;

    /**
     * @return 滑动监听
     */
    public BaseScrollListener getBaseScrollListener() {
        return baseScrollListener;
    }

    /**
     * 设置 滑动监听
     *
     * @param baseScrollListener
     */
    public void setBaseScrollListener(BaseScrollListener baseScrollListener) {
        this.baseScrollListener = baseScrollListener;
    }

    /**
     * 滑动监听
     */
    public interface BaseScrollListener {
        /**
         * 开始滑动
         *
         * @param view
         */
        public void onStart(SanmiListView view);

        /**
         * 停止滑动
         *
         * @param view
         */
        public void onStop(SanmiListView view);
    }

    private BaseSizeChangedListener baseSizeChangedListener;

    /**
     * @return 大小改变监听
     */
    public BaseSizeChangedListener getBaseSizeChangedListener() {
        return baseSizeChangedListener;
    }

    /**
     * 设置大小改变监听
     *
     * @param baseSizeChangedListener
     */
    public void setBaseSizeChangedListener(
            BaseSizeChangedListener baseSizeChangedListener) {
        this.baseSizeChangedListener = baseSizeChangedListener;
    }

    /**
     * 大小改变监听
     */
    public interface BaseSizeChangedListener {
        public void onSizeChanged(SanmiListView view, int w, int h, int oldw,
                                  int oldh);
    }
}
