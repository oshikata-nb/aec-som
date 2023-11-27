/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apupdate;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 買掛更新用Entityクラス.
 * @author tosco
 */
public class ApUpdateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApUpdateBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PAYABLE_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	/** COLUMNアノテーション closingMonth */
	public static final String closingMonth_COLUMN = "CLOSING_MONTH";

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 買掛締め日 */
	private Date payableDate;

	/** 買掛締め日の翌月 */
	private String closingMonth;

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
	 * 買掛締め日取得.
	 * @return String 買掛締め日
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 買掛締め日設定.
	 * @param payableDate 買掛締め日
	 */
	public void setPayableDate(final Date payableDate) {
		this.payableDate = payableDate;
	}

	/**
	 * 買掛締め日の翌月取得.
	 * @return String 買掛締め日の翌月
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * 買掛締め日の翌月設定.
	 * @param closingMonth 買掛締め日の翌月
	 */
	public void setClosingMonth(final String closingMonth) {
		this.closingMonth = closingMonth;
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

