/*
 * Created on 2020/01/23
 * AECS佐藤 設定した区分の納入先のみ表示 2020/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 納入先マスタのオートコンプリート用Form
 * @author tosco
 */
public class DeliveryForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public DeliveryForAutoCompleteForm() {
	}

	/** 納入先区分 */
	private String deliveryDivision;


	/**
	 * deliveryDivisionを取得します。
	 * @return deliveryDivision
	 */
	public String getDeliveryDivision() {
		return deliveryDivision;
	}

	/**
	 * deliveryDivisionを設定します。
	 * @param deliveryDivision deliveryDivision
	 */
	public void setDeliveryDivision(final String deliveryDivision) {
		this.deliveryDivision = deliveryDivision;
	}
}
