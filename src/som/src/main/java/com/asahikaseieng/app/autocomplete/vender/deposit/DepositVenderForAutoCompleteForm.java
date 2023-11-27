/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.deposit;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 取引先(入金用)のオートコンプリート用Form
 * @author tosco
 */
public class DepositVenderForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public DepositVenderForAutoCompleteForm() {
	}

	/** 取引先区分 */
	private String venderDivision;

	/**
	 * 取引先区分を取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * 取引先区分を設定します。
	 * @param venderDivision 用途
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}
}
