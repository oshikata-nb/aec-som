/*
 * Created on 2009/08/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepApledgerDetailクラス.
 * @author kanri-user
 */
public class RepApledgerDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepApledgerDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション simeKbn */
	public static final String simeKbn_COLUMN = "SIME_KBN";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション supplierCd */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション orderFlg */
	public static final String orderFlg_COLUMN = "ORDER_FLG";

	/** COLUMNアノテーション supplierCdDisp */
	public static final String supplierCdDisp_COLUMN = "SUPPLIER_CD_DISP";

	/** COLUMNアノテーション supplierName */
	public static final String supplierName_COLUMN = "SUPPLIER_NAME";

	/** COLUMNアノテーション tranDate */
	public static final String tranDate_COLUMN = "TRAN_DATE";

	/** COLUMNアノテーション tranDivi */
	public static final String tranDivi_COLUMN = "TRAN_DIVI";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション stocking */
	public static final String stocking_COLUMN = "STOCKING";

	/** COLUMNアノテーション payment */
	public static final String payment_COLUMN = "PAYMENT";

	/** COLUMNアノテーション balance */
	public static final String balance_COLUMN = "BALANCE";

	//
	// インスタンスフィールド
	//

	private String simeKbn;

	private String organizationCd;

	private String payableNo;

	private String supplierCd;

	private String orderFlg;

	private String supplierCdDisp;

	private String supplierName;

	private String tranDate;

	private String tranDivi;

	private String categoryName;

	private String slipNo;

	private String rowNo;

	private String itemName;

	private String stocking;

	private String payment;

	private String balance;

	//
	// インスタンスメソッド
	//

	/**
	 * simeKbn取得.
	 * @return simeKbn
	 */
	public String getSimeKbn() {
		return this.simeKbn;
	}

	/**
	 * simeKbn設定.
	 * @param simeKbn simeKbn
	 */
	public void setSimeKbn(final String simeKbn) {
		this.simeKbn = simeKbn;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * payableNo取得.
	 * @return payableNo
	 */
	public String getPayableNo() {
		return this.payableNo;
	}

	/**
	 * payableNo設定.
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * supplierCd取得.
	 * @return supplierCd
	 */
	public String getSupplierCd() {
		return this.supplierCd;
	}

	/**
	 * supplierCd設定.
	 * @param supplierCd supplierCd
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * orderFlg取得.
	 * @return orderFlg
	 */
	public String getOrderFlg() {
		return this.orderFlg;
	}

	/**
	 * orderFlg設定.
	 * @param orderFlg orderFlg
	 */
	public void setOrderFlg(final String orderFlg) {
		this.orderFlg = orderFlg;
	}

	/**
	 * supplierCdDisp取得.
	 * @return supplierCdDisp
	 */
	public String getSupplierCdDisp() {
		return this.supplierCdDisp;
	}

	/**
	 * supplierCdDisp設定.
	 * @param supplierCdDisp supplierCdDisp
	 */
	public void setSupplierCdDisp(final String supplierCdDisp) {
		this.supplierCdDisp = supplierCdDisp;
	}

	/**
	 * supplierName取得.
	 * @return supplierName
	 */
	public String getSupplierName() {
		return this.supplierName;
	}

	/**
	 * supplierName設定.
	 * @param supplierName supplierName
	 */
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * tranDate取得.
	 * @return tranDate
	 */
	public String getTranDate() {
		return this.tranDate;
	}

	/**
	 * tranDate設定.
	 * @param tranDate tranDate
	 */
	public void setTranDate(final String tranDate) {
		this.tranDate = tranDate;
	}

	/**
	 * tranDivi取得.
	 * @return tranDivi
	 */
	public String getTranDivi() {
		return this.tranDivi;
	}

	/**
	 * tranDivi設定.
	 * @param tranDivi tranDivi
	 */
	public void setTranDivi(final String tranDivi) {
		this.tranDivi = tranDivi;
	}

	/**
	 * categoryName取得.
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public String getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final String rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * stocking取得.
	 * @return stocking
	 */
	public String getStocking() {
		return this.stocking;
	}

	/**
	 * stocking設定.
	 * @param stocking stocking
	 */
	public void setStocking(final String stocking) {
		this.stocking = stocking;
	}

	/**
	 * payment取得.
	 * @return payment
	 */
	public String getPayment() {
		return this.payment;
	}

	/**
	 * payment設定.
	 * @param payment payment
	 */
	public void setPayment(final String payment) {
		this.payment = payment;
	}

	/**
	 * balance取得.
	 * @return balance
	 */
	public String getBalance() {
		return this.balance;
	}

	/**
	 * balance設定.
	 * @param balance balance
	 */
	public void setBalance(final String balance) {
		this.balance = balance;
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

