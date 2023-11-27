/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.fbdatamaking;

import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ＦＢデータ作成 ＦＢトレーラー用Entityクラス.
 * @author tosco
 */
public class FbdataMakingTrailer extends FbdataMakingTrailerBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** 合計件数(数値）(カンマ編集) */
	private String strTransferTotalNumberN;

	/** 合計金額(数値）(カンマ編集) */
	private String strTransferTotalAmountN;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingTrailer() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrTransferTotalNumberN(
			AecNumberUtils.decimalFormat(getTransferTotalNumberN(), "#,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 合計件数(数値）(カンマ編集)を取得します。
	 * @return String 合計件数(数値）(カンマ編集)
	 */
	public String getStrTransferTotalNumberN() {
		return strTransferTotalNumberN;
	}

	/**
	 * 合計件数(数値）(カンマ編集)を設定します。
	 * @param strTransferTotalNumberN 合計件数(数値）(カンマ編集)
	 */
	public void setStrTransferTotalNumberN(final String strTransferTotalNumberN) {
		this.strTransferTotalNumberN = strTransferTotalNumberN;
	}

	/**
	 * 合計金額(数値）(カンマ編集)を取得します。
	 * @return String 合計金額(数値）(カンマ編集)
	 */
	public String getStrTransferTotalAmountN() {
		return strTransferTotalAmountN;
	}

	/**
	 * 合計金額(数値）(カンマ編集)を設定します。
	 * @param strTransferTotalAmountN 合計金額(数値）(カンマ編集)
	 */
	public void setStrTransferTotalAmountN(final String strTransferTotalAmountN) {
		this.strTransferTotalAmountN = strTransferTotalAmountN;
	}

}

