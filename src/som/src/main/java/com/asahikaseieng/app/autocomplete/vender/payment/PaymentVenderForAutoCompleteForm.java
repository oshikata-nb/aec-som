/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.payment;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 支払入力－取引先マスタのオートコンプリート用Form
 * @author tosco
 */
public class PaymentVenderForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public PaymentVenderForAutoCompleteForm() {
	}

	/** 部署コード */
	private String organizationCd;

	/**
	 * 部署コード取得
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定
	 * @param organizationCd 部署コード途
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}
}
