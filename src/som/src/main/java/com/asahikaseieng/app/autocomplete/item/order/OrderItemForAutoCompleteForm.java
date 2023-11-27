/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.order;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;

/**
 * 受注用品目マスタのオートコンプリート用Form
 * @author tosco
 */
public class OrderItemForAutoCompleteForm extends GeneralParameterForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OrderItemForAutoCompleteForm() {
	}

	/** 納入先コード */
	private String deliveryCd;

	/** 受注区分　*/
	private String orderDivision;

	/** 帳合コード */
	private String balanceCd;

	/**
	 * 受注区分を取得します。
	 * @return orderDivision
	 */
	public String getOrderDivision() {
		return orderDivision;
	}

	/**
	 * 受注区分を設定します。
	 * @param orderDivision 受注区分
	 */
	public void setOrderDivision(final String orderDivision) {
		this.orderDivision = orderDivision;
	}

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

	/**
	 * 帳合コードを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * 帳合コードを設定します。
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}
}
