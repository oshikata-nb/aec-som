/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepOffsetHeaderクラス.
 * @author kanri-user
 */
public class RepOffsetHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepOffsetHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション offsetNo */
	public static final String offsetNo_COLUMN = "OFFSET_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション offsetDate */
	public static final String offsetDate_COLUMN = "OFFSET_DATE";

	/** COLUMNアノテーション homeName */
	public static final String homeName_COLUMN = "HOME_NAME";

	//
	// インスタンスフィールド
	//

	private String offsetNo;

	private String organizationCd;

	private String organizationName;

	private java.sql.Timestamp offsetDate;

	private String homeName;

	//
	// インスタンスメソッド
	//

	/**
	 * offsetNo取得.
	 * @return offsetNo
	 */
	public String getOffsetNo() {
		return this.offsetNo;
	}

	/**
	 * offsetNo設定.
	 * @param offsetNo offsetNo
	 */
	public void setOffsetNo(final String offsetNo) {
		this.offsetNo = offsetNo;
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
	 * offsetDate取得.
	 * @return offsetDate
	 */
	public java.sql.Timestamp getOffsetDate() {
		return this.offsetDate;
	}

	/**
	 * offsetDate設定.
	 * @param offsetDate offsetDate
	 */
	public void setOffsetDate(final java.sql.Timestamp offsetDate) {
		this.offsetDate = offsetDate;
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

