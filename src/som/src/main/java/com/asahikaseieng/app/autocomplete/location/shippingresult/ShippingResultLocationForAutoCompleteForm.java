/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.shippingresult;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 出荷実績用ロケーションマスタのオートコンプリート用Form
 * @author tosco
 */
public class ShippingResultLocationForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ShippingResultLocationForAutoCompleteForm() {
	}

	/** 品目コード */
	private String itemCd;

	/**
	 * 品目コードを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}
}
