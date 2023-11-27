/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import org.apache.struts.upload.FormFile;

import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 帳票・ラベルマスタデータ格納クラス.
 *
 * @author tosco
 */
public class MgrecipeLabelList extends MgrecipeLabelListBase implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** ファイル名の年月日時分秒部分の桁数 */
	private static final int START_FILE_NAME = 14;

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** アップロード ファイル名称 */
	private FormFile uploadFile;
	/** 表示用ダウンロードファイル名称 */
	private String dispLabelPath;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeLabelList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// ファイル名を表示用に編集
		String lblPath = getLabelPath();
		if ((lblPath != null) && (!lblPath.equals(""))) {
			// 年月日時分秒を取除く
			lblPath = lblPath.substring(START_FILE_NAME);
			setDispLabelPath(lblPath);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 表示用ダウンロードファイル名称を取得します。
	 * @return dispLabelPath
	 */
	public String getDispLabelPath() {
		return dispLabelPath;
	}

	/**
	 * 表示用ダウンロードファイル名称を設定します。
	 * @param dispLabelPath 表示用ダウンロードファイル名称
	 */
	public void setDispLabelPath(final String dispLabelPath) {
		this.dispLabelPath = dispLabelPath;
	}

	/**
	 * アップロード ファイル名称を取得します。
	 * @return uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * アップロード ファイル名称を設定します。
	 * @param uploadFile アップロード ファイル名称
	 */
	public void setUploadFile(final FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}
