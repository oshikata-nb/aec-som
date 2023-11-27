/*
 * Created on 2009/07/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepDepositHeaderクラス.
 * @author kanri-user
 */
public class RepDepositHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDepositHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション creditNo */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション homeName */
	public static final String homeName_COLUMN = "HOME_NAME";

	//
	// インスタンスフィールド
	//

	private String creditNo;

	private String organizationCd;

	private String organizationName;

	private java.sql.Timestamp creditDate;

	private String homeName;

	//
	// インスタンスメソッド
	//

	/**
	 * creditNo取得.
	 * @return creditNo
	 */
	public String getCreditNo() {
		return this.creditNo;
	}

	/**
	 * creditNo設定.
	 * @param creditNo creditNo
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
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
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * creditDate取得.
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * homeName取得.
	 * @return homeName
	 */
	public String getHomeName() {
		return this.homeName;
	}

	/**
	 * homeName設定.
	 * @param homeName homeName
	 */
	public void setHomeName(final String homeName) {
		this.homeName = homeName;
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

