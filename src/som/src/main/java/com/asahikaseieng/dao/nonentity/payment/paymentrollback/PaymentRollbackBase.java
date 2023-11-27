/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentrollback;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 支払更新ロールバック用Entityクラス.
 * @author tosco
 */
public class PaymentRollbackBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PaymentRollbackBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PAYABLE_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション supplierCd */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション strPayableDate */
	public static final String strPayableDate_COLUMN = "STR_PAYABLE_DATE";

	//
	// インスタンスフィールド	//

	/** 部署コード */
	private String organizationCd;

	/** 支払先コード */
	private String supplierCd;

	/** 支払締め日 */
	private Date payableDate;

	/** 支払締め日(文字列) */
	private String strPayableDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 部署コード取得.
	 * @return String 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コード設定.
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 支払先コード取得.
	 * @return String 支払先コード
	 */
	public String getSupplierCd() {
		return supplierCd;
	}

	/**
	 * 支払先コード設定.
	 * @param supplierCd 支払先コード
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * 支払締め日取得.
	 * @return Date 支払締め日
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 支払締め日設定.
	 * @param payableDate 支払締め日
	 */
	public void setPayableDate(final Date payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * 支払締め日(文字列)取得.
	 * @return String 支払締め日(文字列)
	 */
	public String getStrPayableDate() {
		return strPayableDate;
	}

	/**
	 * 支払締め日(文字列)設定.
	 * @param strPayableDate 支払締め日(文字列)
	 */
	public void setStrPayableDate(final String strPayableDate) {
		this.strPayableDate = strPayableDate;
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

