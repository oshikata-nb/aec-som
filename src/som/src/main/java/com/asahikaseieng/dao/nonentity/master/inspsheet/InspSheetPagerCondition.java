/*
 * Created on 2008/11/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.inspsheet;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * Conditionクラス.検査成績書マスタ
 * @author tosco
 */
public class InspSheetPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InspSheetPagerCondition() {
	}

	//フィールド-----------------------------------------------------------------------------

	/** 取引先コード */
	private String venderCode;

	/** 納入先コード */
	private String deliveryCode;

	/** 品目コード */
	private String itemCode;

	/** 取引先名称 */
	private String venderName;

	/** 納入先名称 */
	private String deliveryName;

	/** 品目名称 */
	private String itemName;

	//メソッド-----------------------------------------------------------------------------

	/**
	 * 納入先名称を取得します。
	 * @return 納入先名称
	 */
	public String getDeliveryName() {
		return deliveryName;
	}
	/**
	 * 納入先名称を設定します。
	 * @param deliveryName 納入先名称
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}
	/**
	 * 品目名称を取得します。
	 * @return 品目名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 取引先名称を取得します。
	 * @return 取引先名称
	 */
	public String getVenderName() {
		return venderName;
	}
	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}
	/**
	 * 取引先コード取得.
	 * @return 取引先コード
	 */
	public String getVenderCode() {
		return this.venderCode;
	}
	/**
	 * 取引先コード設定.
	 * @param venderCode 取引先コード
	 */
	public void setVenderCode(final String venderCode) {
		this.venderCode = venderCode;
	}

	/**
	 * 納入先コード取得.
	 * @return 納入先コード
	 */
	public String getDeliveryCode() {
		return this.deliveryCode;
	}
	/**
	 * 納入先コード設定.
	 * @param deliveryCode 納入先コード
	 */
	public void setDeliveryCode(final String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	/**
	 * 品目コード取得.
	 * @return 品目コード
	 */
	public String getItemCode() {
		return this.itemCode;
	}
	/**
	 * 品目コード設定.
	 * @param itemCode 品目コード
	 */
	public void setItemCode(final String itemCode) {
		this.itemCode = itemCode;
	}

}
