/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bank;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 銀行マスタのオートコンプリート用Form
 * @author tosco
 */
public class BankForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BankForAutoCompleteForm() {
	}

	/** 銀行コード */
	private String srhBankCd;

	/**
	 * srhBankCdを取得します。
	 * @return srhBankCd
	 */
	public String getSrhBankCd() {
		return srhBankCd;
	}

	/**
	 * srhBankCdを設定します。
	 * @param srhBankCd srhBankCd
	 */
	public void setSrhBankCd(final String srhBankCd) {
		this.srhBankCd = srhBankCd;
	}
}
