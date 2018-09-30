package com.project.my.studystarteacher.newteacher.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/Photo_LJ/";
	public static final String CACHE = "cache";
	public static final String ICON = "icon";
	public static final String Images = "Images";
	public static final String ROOT = "Starpupil";
	public static final String VIDEO = "video";

	public static final String MP3 = "audio";
	public static final int ID = 0;
	/**
	 * ��ȡͼƬ�Ļ����·��
	 * @return
	 */

	public static File getIconDir(Context cnt){
		return getDir(ICON, cnt);
		
	}
	public static File saveBitmap(Bitmap bm, String picName) {
		File f = null;
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}
	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	public static File getImagesDir(Context cnt) {
		// TODO Auto-generated method stub
		return getDir(Images, cnt);
	}
	/**
	 * ��ȡ����·��
	 * @return
	 */
	public static File getCacheDir(Context cnt) {
		return getDir(CACHE, cnt);
	}
	public static File getDir(String cache,Context cnt) {
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(cache);// /mnt/sdcard/Starpupil/cache
			
		}else{
			File filesDir = cnt.getCacheDir();    //  cache  getFileDir file
			path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
			path.append(File.separator);///data/data/com.itheima.googleplay/cache/
			path.append(cache);///data/data/com.itheima.googleplay/cache/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// �����ļ���
		}
		return file;

	}
	public static String getDirPath(Context cnt){
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/GooglePlay

		}else{
			File filesDir = cnt.getCacheDir();    //  cache  getFileDir file
			path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// �����ļ���
		}

		return file.getAbsolutePath();
	}
	private static boolean isSDAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	public static File getVideoDir(Context cnt){
		return getDir(VIDEO, cnt);

	}
	public static String getUriPath(Context context, Uri uri) {
		if(uri == null) {
			return null;
		}
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
			if("com.android.externalstorage.documents".equals(uri.getAuthority())) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				if("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			} else if("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if("com.android.providers.media.documents".equals(uri.getAuthority())) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				Uri contentUri = null;
				if("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				final String selection = "_id=?";
				final String[] selectionArgs = new String[] {split[1]};
				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		} else if("content".equalsIgnoreCase(uri.getScheme())) {
			if("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
				return uri.getLastPathSegment();
			}
			return getDataColumn(context, uri, null, null);
		} else if("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}
		return null;
	}
	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = {column};
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if(cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(column_index);
			}
		} finally {
			if(cursor != null) cursor.close();
		}
		return null;
	}
		public static List<String> getEmojiFile(Context context) {
			try {
				List<String> list = new ArrayList<String>();
				InputStream in = context.getResources().getAssets().open("emoji");
				BufferedReader br = new BufferedReader(new InputStreamReader(in,
						"UTF-8"));
				String str = null;
				while ((str = br.readLine()) != null) {
					list.add(str);

				}

				return list;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}


	public static File getRecorDir(Context cnt) {
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(ID);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(MP3);// /mnt/sdcard/Starpupil/Images

		} else {
			File filesDir = cnt.getCacheDir();    //  cache  getFileDir file
			path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
			path.append(File.separator);///data/data/com.itheima.googleplay/cache/
			path.append(ID);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(MP3);///data/data/com.itheima.googleplay/cache/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// �����ļ���
		}
		return file;
	}

	// 获取当前目录下所有的mp4文件
	public static ArrayList<String> GetVideoFileName(String fileAbsolutePath) {
		ArrayList<String> lists = new ArrayList<String>();
//			Vector vecFile = new Vector();
		File file = new File(fileAbsolutePath);
		File[] subFile = file.listFiles();

		for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
			// 判断是否为文件夹
			if (!subFile[iFileLength].isDirectory()) {
				String filename = subFile[iFileLength].getName();
				System.out.println(filename);
				// 判断是否为MP4结尾
				if (filename.trim().toLowerCase().endsWith(".amr")) {
					lists.add(filename);
					System.out.println("amr" + filename);
				}
			}
		}
//			Collections.reverse(lists);
		return lists;
	}
}
