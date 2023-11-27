/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Workbookをキャッシュする.
 * @author jbd
 */
public final class WorkbookCache {

	private static WorkbookCache instance;

	/**
	 * Instance.
	 * @return instance
	 */
	public static synchronized WorkbookCache getInstance() {
		if (instance == null) {
			instance = new WorkbookCache();
		}

		return instance;
	}

	/**
	 * ファイル情報内部クラス.
	 */
	private class FileInfoBase {

		private long lastModified;

		/**
		 * コンストラクタ
		 * 
		 */
		public FileInfoBase() {
			super();
		}

		public final long getLastModified() {
			return lastModified;
		}

		public final void setLastModified(final long last) {
			lastModified = last;
		}
	}

	/**
	 * Workbook情報内部クラス.
	 */
	private class WorkbookInfo extends FileInfoBase {

		private POIFSFileSystem poifs;

		/**
		 * コンストラクタ
		 * 
		 */
		public WorkbookInfo() {
			super();
		}

		public final POIFSFileSystem getPoifs() {
			return poifs;
		}

		public final void setPoifs(final POIFSFileSystem book) {
			poifs = book;
		}
	}

	/* キャッシュテーブル. */
	private Hashtable table = new Hashtable();

	/**
	 * コンストラクタ.
	 */
	private WorkbookCache() {
		super();
		// 初期化
		instance = null;
	}

	/**
	 * Cacheクリア.
	 */
	public void clear() {
		synchronized (table) {
			table.clear();
		}
	}

	/**
	 * POIFSFileSystem情報を取得する.
	 * @param path ファイルパス
	 * @return POIFSFileSystem情報
	 * @throws IOException I/O例外
	 */
	private WorkbookInfo getWorkbookInfo(final String path) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));

		WorkbookInfo wbi = new WorkbookInfo();
		wbi.setLastModified((new File(path)).lastModified());
		wbi.setPoifs(fs);

		return wbi;
	}

	/**
	 * POIFSFileSystemを取得する.
	 * @param url URL
	 * @return POIFSFileSystem
	 * @throws IOException I/O例外
	 */
	@SuppressWarnings("unchecked")
	public HSSFWorkbook getWorkbook(final URL url) throws IOException {
		WorkbookInfo wbi = null;

		// 存在チェック
		if (url == null || url.getPath() == null) {
			throw new IOException("file not exist.");
		}

		File file = new File(url.getPath());

		// 拡張子チェック(xlsのみ有効)
		if (!(file.getName().substring(file.getName().lastIndexOf("."))
				.equals(".xls"))) {
			throw new IOException("not excel-format:" + file.getName());
		}

		// キャッシュ検索
		synchronized (table) {
			wbi = (WorkbookInfo) table.get(file.getName());
		}

		// キャッシュにない、もしくは新しく更新されている場合、
		// ファイルを取得しなおす
		if (wbi == null
				|| (wbi != null && file.lastModified() > wbi.getLastModified())) {

			wbi = getWorkbookInfo(file.getPath());

			synchronized (table) {
				table.put(file.getName(), wbi);
			}
		}

		return new HSSFWorkbook(wbi.getPoifs());
	}
}
