/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.order;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 出荷指図用ロケーションマスタのオートコンプリート用Form
 * @author tosco
 */
public class OrderLocationForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OrderLocationForAutoCompleteForm() {
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
