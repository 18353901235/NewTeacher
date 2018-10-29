package com.project.my.studystarteacher.newteacher.album;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ImgBeanHolder {
	Bitmap bitmap;
	ImageView imageView;
	String path;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
