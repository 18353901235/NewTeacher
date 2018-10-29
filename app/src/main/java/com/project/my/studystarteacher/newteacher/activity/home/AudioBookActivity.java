package com.project.my.studystarteacher.newteacher.activity.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.project.my.studystarteacher.newteacher.R;
import com.project.my.studystarteacher.newteacher.adapter.AudioBookAdapter;
import com.project.my.studystarteacher.newteacher.adapter.BookTypeAdapter;
import com.project.my.studystarteacher.newteacher.base.BaseActivity;
import com.project.my.studystarteacher.newteacher.bean.AudioBook;
import com.project.my.studystarteacher.newteacher.bean.BookType;
import com.project.my.studystarteacher.newteacher.bean.ZhuBoBean;
import com.project.my.studystarteacher.newteacher.net.DemoNetTaskExecuteListener;
import com.project.my.studystarteacher.newteacher.net.MiceNetWorker;
import com.project.my.studystarteacher.newteacher.utils.PopWindowUtils;
import com.zhouqiang.framework.bean.BaseBean;
import com.zhouqiang.framework.net.SanmiNetTask;
import com.zhouqiang.framework.net.SanmiNetWorker;
import com.zhouqiang.framework.util.JsonUtil;
import com.zhouqiang.framework.util.ToastUtil;
import com.zhouqiang.framework.util.WindowSize;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_audio_book)
public class AudioBookActivity extends BaseActivity {
    @ViewInject(R.id.list)
    private PullToRefreshGridView list;
    ArrayList<AudioBook> dataList = new ArrayList<>();
    int index = 1;
    AudioBookAdapter homeClassAdapter3;
    View inflate;
    GridView gr1, gr2, gr3;
    TextView reset, ok;

    @Override
    protected void init() {
        getCommonTitle().setText("有声绘本");
        getRight().setBackgroundResource(R.mipmap.musicbk_ic_search);
        getRight2().setBackgroundResource(R.mipmap.musicbk_ic_screen);
        homeClassAdapter3 = new AudioBookAdapter(mContext, R.layout.item_audio_list, dataList);
        list.setAdapter(homeClassAdapter3);
        inflate = View.inflate(mContext, R.layout.pop_fifter_audio, null);
        gr1 = inflate.findViewById(R.id.gr1);
        gr2 = inflate.findViewById(R.id.gr2);
        gr3 = inflate.findViewById(R.id.gr3);
        reset = inflate.findViewById(R.id.reset);
        ok = inflate.findViewById(R.id.ok);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mp3Book.size() < 1) {
                    ToastUtil.showLongToast(mContext, "播放器初始化中...");
                    return;
                }
                ToActivity(mContext, AudioPayerActivity.class, mp3Book, position);
            }
        });
        getRight2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopWindowUtils.show(mContext, v, inflate, -1, WindowSize.getWidth(mContext) / 4 * 3);
            }
        });
        getData();
        getFData();
        getType();
        list.setMode(PullToRefreshBase.Mode.BOTH);
        list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                index = 1;
                getFData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                index++;
                getFData();
            }
        });
    }

    ArrayList<ZhuBoBean> mp3Book = new ArrayList();

    public void getFData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<AudioBook> bookList = JsonUtil.fromList((String) baseBean.getData(), "bookList", AudioBook.class);
                if (index == 1) {
                    dataList.clear();
                }
                dataList.addAll(bookList);
                homeClassAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onPostExecute(SanmiNetWorker netWorker, SanmiNetTask netTask) {
                super.onPostExecute(netWorker, netTask);
                list.onRefreshComplete();
            }
        });
        Worker.getVoicedBookList(agev, Categoryv, countryv, index + "", "");
    }

    public void getType() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, final BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                //AgeGroup   BookCategory  DifCountry
                setPopData(baseBean, false);
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setPopData(baseBean, false);


                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index = 1;
                        getFData();
                        getData();
                    }
                });

            }
        });
        Worker.getSearchConditionsList();
    }

    String agev = "";
    String Categoryv = "";
    String countryv = "";

    private void setPopData(BaseBean baseBean, boolean isOk) {
        agev = "";
        Categoryv = "";
        countryv = "";
        final ArrayList<BookType> ageGroup = JsonUtil.fromList((String) baseBean.getData(), "AgeGroup", BookType.class);
        final BookTypeAdapter adapter1 = new BookTypeAdapter(mContext, R.layout.item_audio_fifter, ageGroup);
        gr1.setAdapter(adapter1);
        gr1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookType type = (BookType) parent.getItemAtPosition(position);
                for (int i = 0; i < ageGroup.size(); i++) {
                    ageGroup.get(i).setCheck(false);
                }
                ageGroup.get(position).setCheck(true);
                agev = type.getDictionaryname();
                adapter1.notifyDataSetChanged();
            }
        });
        final ArrayList<BookType> BookCategory = JsonUtil.fromList((String) baseBean.getData(), "BookCategory", BookType.class);
        final BookTypeAdapter adapter2 = new BookTypeAdapter(mContext, R.layout.item_audio_fifter, BookCategory);
        gr2.setAdapter(adapter2);
        gr2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookType type = (BookType) parent.getItemAtPosition(position);
                Categoryv = type.getDictionaryname();
                for (int i = 0; i < BookCategory.size(); i++) {
                    BookCategory.get(i).setCheck(false);
                }
                BookCategory.get(position).setCheck(true);
                adapter2.notifyDataSetChanged();
            }
        });
        final ArrayList<BookType> DifCountry = JsonUtil.fromList((String) baseBean.getData(), "DifCountry", BookType.class);
        final BookTypeAdapter adapter3 = new BookTypeAdapter(mContext, R.layout.item_audio_fifter, DifCountry);
        gr3.setAdapter(adapter3);
        gr3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BookType type = (BookType) parent.getItemAtPosition(position);
                countryv = type.getDictionaryname();
                for (int i = 0; i < DifCountry.size(); i++) {
                    DifCountry.get(i).setCheck(false);
                }
                DifCountry.get(position).setCheck(true);
                adapter3.notifyDataSetChanged();
            }
        });
    }

    public void getData() {
        MiceNetWorker Worker = new MiceNetWorker(mContext);
        Worker.setOnTaskExecuteListener(new DemoNetTaskExecuteListener(mContext) {
            @Override
            public void onSuccess(SanmiNetWorker netWorker, SanmiNetTask netTask, BaseBean baseBean) {
                super.onSuccess(netWorker, netTask, baseBean);
                ArrayList<AudioBook> bookList = JsonUtil.fromList((String) baseBean.getData(), "bookList", AudioBook.class);
                for (AudioBook book : bookList) {
                    ZhuBoBean zhuBoBean = new ZhuBoBean();
                    zhuBoBean.setId(book.getId());
                    zhuBoBean.setAuthor(book.getAuthor());
                    zhuBoBean.setBookdesc(book.getBookdesc());
                    zhuBoBean.setUrl(book.getPlayurl());
                    zhuBoBean.setBookname(book.getBookname());
                    zhuBoBean.setPlay_Count(book.getBookconcernamount());
                    zhuBoBean.setHeadImg(book.getBooklogourl());
                    mp3Book.add(zhuBoBean);
                }
            }
        });
        Worker.getVoicedBookList(agev, Categoryv, countryv,  "", "");
    }

}
