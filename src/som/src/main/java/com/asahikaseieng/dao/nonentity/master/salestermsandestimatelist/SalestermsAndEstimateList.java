/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * SalestermsListクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateList extends SalestermsAndEstimateListBase 
											implements PropertyTransferCallbacker{

	private static final long serialVersionUID = 1L;
	
	/** チェックボックス */
	private boolean salestermsAndEstimateCheckBox;
	
	/** 入力日 */
	private String strSrhInputDate;
	
	/* 確定・確定取消判別フラグ */
	private String confirmFlg;
	
	/** ログ出力用エラーコード */
	private String errorCd;

	/** ログ出力用エラーメッセージ */
	private String errorMsg;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateList() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 取得した日付をyyyy/MM/ddに変換し、セット
		setStrSrhInputDate(AecDateUtils.dateFormat(getSrhInputDate(),
			"yyyy/MM/dd"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * salestermsAndEstimateCheckBoxを取得します。
	 * @return salestermsAndEstimateCheckBox
	 */
	public boolean isSalestermsAndEstimateCheckBox() {
		return salestermsAndEstimateCheckBox;
	}

	/**
	 * salestermsAndEstimateCheckBoxを設定します。
	 * @param salestermsAndEstimateCheckBox salestermsAndEstimateCheckBox
	 */
	public void setSalestermsAndEstimateCheckBox(
			final boolean salestermsAndEstimateCheckBox) {
		this.salestermsAndEstimateCheckBox = salestermsAndEstimateCheckBox;
	}

	/**
	 * strSrhInputDateを取得します。
	 * @return strSrhInputDate
	 */
	public String getStrSrhInputDate() {
		return strSrhInputDate;
	}

	/**
	 * strSrhInputDateを設定します。
	 * @param strSrhInputDate strSrhInputDate
	 */
	public void setStrSrhInputDate(final String strSrhInputDate) {
		this.strSrhInputDate = strSrhInputDate;
	}

	/**
	 * confirmFlgを取得します。
	 * @return confirmFlg
	 */
	public String getConfirmFlg() {
		return confirmFlg;
	}

	/**
	 * confirmFlgを設定します。
	 * @param confirmFlg confirmFlg
	 */
	public void setConfirmFlg(final String confirmFlg) {
		this.confirmFlg = confirmFlg;
	}

	/**
	 * errorCdを取得します。
	 * @return errorCd
	 */
	public String getErrorCd() {
		return errorCd;
	}

	/**
	 * errorCdを設定します。
	 * @param errorCd errorCd
	 */
	public void setErrorCd(final String errorCd) {
		this.errorCd = errorCd;
	}

	/**
	 * errorMsgを取得します。
	 * @return errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * errorMsgを設定します。
	 * @param errorMsg errorMsg
	 */
	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/* ---------- 仮想プロパティ ---------- */

}
