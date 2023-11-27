/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arupdate;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売掛更新用Entityクラス.
 * @author tosco
 */
public class ArUpdateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArUpdateBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DEPOSIT_HEADER";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション closingMonth */
	public static final String closingMonth_COLUMN = "CLOSING_MONTH";

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 売掛締め日 */
	private Date creditDate;

	/** 売掛締め日の翌月 */
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
	 * 売掛締め日取得.
	 * @return String 売掛締め日
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 売掛締め日設定.
	 * @param creditDate 売掛締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * 売掛締め日の翌月取得.
	 * @return String 売掛締め日の翌月
	 */
	public String getClosingMonth() {
		return closingMonth;
	}

	/**
	 * 売掛締め日の翌月設定.
	 * @param closingMonth 売掛締め日の翌月
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

