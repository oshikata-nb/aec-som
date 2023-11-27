/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepArledgerDetailクラス.
 * @author kanri-user
 */
public class RepArledgerDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepArledgerDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション simeKbn */
	public static final String simeKbn_COLUMN = "SIME_KBN";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション orderFlg */
	public static final String orderFlg_COLUMN = "ORDER_FLG";

	/** COLUMNアノテーション venderCdDisp */
	public static final String venderCdDisp_COLUMN = "VENDER_CD_DISP";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

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

	/** COLUMNアノテーション sales */
	public static final String sales_COLUMN = "SALES";

	/** COLUMNアノテーション credit */
	public static final String credit_COLUMN = "CREDIT";

	/** COLUMNアノテーション balance */
	public static final String balance_COLUMN = "BALANCE";

	//
	// インスタンスフィールド
	//

	private String simeKbn;

	private String organizationCd;

	private String depositNo;

	private String venderCd;

	private String orderFlg;

	private String venderCdDisp;

	private String venderName;

	private String tranDate;

	private String tranDivi;

	private String categoryName;

	private String slipNo;

	private String rowNo;

	private String itemName;

	private String sales;

	private String credit;

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
	 * depositNo取得.
	 * @return depositNo
	 */
	public String getDepositNo() {
		return this.depositNo;
	}

	/**
	 * depositNo設定.
	 * @param depositNo depositNo
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
	 * venderCdDisp取得.
	 * @return venderCdDisp
	 */
	public String getVenderCdDisp() {
		return this.venderCdDisp;
	}

	/**
	 * venderCdDisp設定.
	 * @param venderCdDisp venderCdDisp
	 */
	public void setVenderCdDisp(final String venderCdDisp) {
		this.venderCdDisp = venderCdDisp;
	}

	/**
	 * venderName取得.
	 * @return venderName
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * venderName設定.
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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
	 * sales取得.
	 * @return sales
	 */
	public String getSales() {
		return this.sales;
	}

	/**
	 * sales設定.
	 * @param sales sales
	 */
	public void setSales(final String sales) {
		this.sales = sales;
	}

	/**
	 * credit取得.
	 * @return credit
	 */
	public String getCredit() {
		return this.credit;
	}

	/**
	 * credit設定.
	 * @param credit credit
	 */
	public void setCredit(final String credit) {
		this.credit = credit;
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

