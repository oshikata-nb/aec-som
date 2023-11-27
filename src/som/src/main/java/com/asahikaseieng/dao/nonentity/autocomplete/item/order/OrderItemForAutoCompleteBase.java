/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.order;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderItemListForAutoCompleteクラス.
 * @author tosco
 */
public class OrderItemForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderItemForAutoCompleteBase() {
	}

	// 定数


	//
	// インスタンスフィールド	//

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 荷姿 */
	private String styleOfPacking;

	/** 運用管理単位 */
	private String unitOfOperationManagement;

	/** 帳合コード */
	private String balanceCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	*/
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	*/
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 他社コード1取得
	 * @return String 他社コード1
	*/
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * 他社コード1設定
	 * @param otherCompanyCd1 他社コード1
	*/
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	*/
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	*/
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 運用管理単位取得
	 * @return String 運用管理単位
	*/
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * 運用管理単位設定
	 * @param unitOfOperationManagement 運用管理単位
	*/
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
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

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
