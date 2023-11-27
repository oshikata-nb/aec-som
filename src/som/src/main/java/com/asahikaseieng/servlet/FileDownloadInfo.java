/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.servlet;

import java.io.Serializable;
import java.util.ResourceBundle;

import com.asahikaseieng.Constants;

/**
 * ダウンロード用情報保持クラス.
 * @author jbd
 */
public final class FileDownloadInfo implements Serializable {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;

	/** デフォルトContentType */
	public static final String CONTENTTYPE_DEFAULT = "application/octet-stream";

	/** Disposition attachment */
	public static final String DISPOSITION_ATTACHMENT = "attachment";

	/** Disposition inline */
	public static final String DISPOSITION_INLINE = "inline";

	private String fileName;

	private String path;

	private byte[] bytes;

	private String contentType = CONTENTTYPE_DEFAULT;

	private String disposition = DISPOSITION_ATTACHMENT;

	private boolean deleteOnExist = true;

	// 拡張子contentType対応テーブル
	private static final String[][] contentTypeTable = {
			{"jpg", "image/pjpeg"}, {"gif", "image/gif"},
			{"txt", "text/plain"}, {"xls", "application/vnd.ms-excel"}};

	private static final int CONTENT_TYPE = 1;

	private static final int EXTENTION = 0;

	/**
	 * pdf用のFileDownloadInfoを作成するfactoryメソッド.
	 * @param path path
	 * @param key key
	 * @return FileDownloadInfo
	 */
	public static FileDownloadInfo createPDFDownloadInfo(final String path,
			final String key) {

		FileDownloadInfo info = new FileDownloadInfo(rb.getString(key)
				+ rb.getString("pdf.extension"), path);

		info.setContenType(rb.getString("contenttype.pdf"));
		info.setDisposition(rb.getString("disposition"));
		info.setDeleteOnExist(true);

		return info;
	}

	/**
	 * FileDownloadInfoを作成するfactoryメソッド.
	 * @param path path
	 * @param key key
	 * @return FileDownloadInfo
	 */
	public static FileDownloadInfo createDownloadInfo(final String path,
			final String key) {

		// 拡張子からcontentTypeを獲得
		String contentType = getContentType(path);

		FileDownloadInfo info = new FileDownloadInfo(key, path);

		info.setContenType(contentType);
		info.setDisposition(rb.getString("disposition"));
		info.setDeleteOnExist(false);

		return info;
	}

	// ファイル名から拡張子を取り出す
	private static String getExtention(final String fileName) {
		int idx = fileName.lastIndexOf('.');
		if (idx != -1) {
			return fileName.substring(idx + 1, fileName.length());
		}
		return "";
	}

	// 拡張子からcontentTypeを取り出す
	private static String getContentType(final String fileName) {
		String extention = getExtention(fileName);
		for (int j = 0; j < contentTypeTable.length; j++) {
			if (contentTypeTable[j][EXTENTION].equalsIgnoreCase(extention)) {
				return contentTypeTable[j][CONTENT_TYPE];
			}
		}
		return "application/octet-stream";
	}

	/**
	 * コンストラクタ.
	 * @param string ファイル名
	 * @param path ファイルパス
	 */
	public FileDownloadInfo(final String string, final String path) {
		this.fileName = string;
		this.path = path;
	}

	/**
	 * コンストラクタ.
	 * @param string ファイル名
	 * @param bytes バイナリデータ
	 */
	public FileDownloadInfo(final String string, final byte[] bytes) {
		fileName = string;
		this.bytes = bytes;
	}

	/**
	 * ContentTypeを取得する
	 * @return contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * バイナリデータを取得する
	 * @return バイナリデータ
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * ファイル名を取得する
	 * @return ファイル名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ContentTypeを設定する
	 * @param string contentType
	 */
	public void setContenType(final String string) {
		contentType = string;
	}

	/**
	 * Dispositionを取得する
	 * @return Disposition
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * Dispositionを設定する
	 * @param string Disposition
	 */
	public void setDisposition(final String string) {
		disposition = string;
	}

	/**
	 * pathを取得します。
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * deleteOnExistを取得します。
	 * @return deleteOnExist
	 */
	public boolean isDeleteOnExist() {
		return deleteOnExist;
	}

	/**
	 * deleteOnExistを設定します。
	 * @param deleteOnExist deleteOnExist
	 */
	public void setDeleteOnExist(final boolean deleteOnExist) {
		this.deleteOnExist = deleteOnExist;
	}
}
