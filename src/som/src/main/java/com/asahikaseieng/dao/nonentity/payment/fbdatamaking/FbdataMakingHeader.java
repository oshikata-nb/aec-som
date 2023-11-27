/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ＦＢデータ作成 ＦＢヘッダー用Entityクラス.
 * @author tosco
 */
public class FbdataMakingHeader extends FbdataMakingHeaderBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** ファイル作成日時(スラッシュ編集) */
	private String strDlDate;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingHeader() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrDlDate(AecDateUtils.dateFormat(getDlDate(), "yyyy/MM/dd HH:mm"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * ファイル作成日時(スラッシュ編集)を取得します。
	 * @return String ファイル作成日時(スラッシュ編集)
	 */
	public String getStrDlDate() {
		return strDlDate;
	}

	/**
	 * ファイル作成日時(スラッシュ編集)を設定します。
	 * @param strDlDate ファイル作成日時(スラッシュ編集)
	 */
	public void setStrDlDate(final String strDlDate) {
		this.strDlDate = strDlDate;
	}


}

