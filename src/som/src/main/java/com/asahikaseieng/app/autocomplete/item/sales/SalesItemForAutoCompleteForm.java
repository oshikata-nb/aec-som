/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.sales;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 売上用品目ンマスタのオートコンプリート用Form
 * @author tosco
 */
public class SalesItemForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public SalesItemForAutoCompleteForm() {
	}

	/** 納入先コード */
	private String deliveryCd;

	/**
	 * 納入先コードを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * 納入先コードを設定します。
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}
}
