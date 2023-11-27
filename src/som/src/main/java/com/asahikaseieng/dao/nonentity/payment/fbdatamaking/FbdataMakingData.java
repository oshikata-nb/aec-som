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
 * ＦＢデータ作成 ＦＢデータ用Entityクラス.
 * @author tosco
 */
public class FbdataMakingData extends FbdataMakingDataBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** チェックフラグ */
	private boolean checkFlg;

	/** 振込金額(計算用）(カンマ編集) */
	private String strTransferAmountN;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingData() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setCheckFlg(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setTransferAmountN(AecNumberUtils.convertBigDecimal(getStrTransferAmountN()));
	}


	/* ---------- getter ,setter ---------- */
	/**
	 * チェックフラグを取得します。
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * 振込金額(計算用）(カンマ編集)を取得します。
	 * @return String 振込金額(計算用）(カンマ編集)
	 */
	public String getStrTransferAmountN() {
		return strTransferAmountN;
	}

	/**
	 * 振込金額(計算用）(カンマ編集)を設定します。
	 * @param strTransferAmountN 振込金額(計算用）(カンマ編集)
	 */
	public void setStrTransferAmountN(final String strTransferAmountN) {
		this.strTransferAmountN = strTransferAmountN;
	}

}

