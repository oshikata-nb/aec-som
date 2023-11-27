package com.asahikaseieng.filenamefilter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ファイルフィルタークラス
 */
public final class ListupFiles implements FilenameFilter {
	/**
	 * コンストラクタ.
	 */
	public ListupFiles() {
		super();
	}

	/**
	 * 指定ディレクトリ内でファイル一覧を取得する。
	 * @param dirName ファイルリストを取得するフォルダ
	 * @return ファイルリスト
	 */
	public static String[] getFileList(final String dirName) {

		// Fileオブジェクトを生成
		File file = new File(dirName);

		// ファイル一覧を取得
		String[] list = file.list(new ListupFiles());

		return list;
	}

	/**
	 * フィルタリングを行う。 .javaファイルであればtrueを返却する。
	 * @param dir dir
	 * @param name name
	 * @return boolean
	 */
	public boolean accept(final File dir, final String name) {

		if (name.startsWith("chartmap") && name.endsWith(".jpg")) {
			return true;
		}

		return false;
	}

}
