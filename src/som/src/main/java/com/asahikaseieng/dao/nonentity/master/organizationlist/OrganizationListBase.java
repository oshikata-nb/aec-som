/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrganizationListクラス.
 * @author t0011036
 */
public class OrganizationListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrganizationListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション shortOrganizationName */
	public static final String shortOrganizationName_COLUMN = "SHORT_ORGANIZATION_NAME";

	/** COLUMNアノテーション parentOrganizationCd */
	public static final String parentOrganizationCd_COLUMN = "PARENT_ORGANIZATION_CD";

	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String organizationName;

	private String shortOrganizationName;

	private String parentOrganizationCd;

	//
	// インスタンスメソッド
	//

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
	 * shortOrganizationName取得.
	 * @return shortOrganizationName
	 */
	public String getShortOrganizationName() {
		return this.shortOrganizationName;
	}

	/**
	 * shortOrganizationName設定.
	 * @param shortOrganizationName shortOrganizationName
	 */
	public void setShortOrganizationName(final String shortOrganizationName) {
		this.shortOrganizationName = shortOrganizationName;
	}

	/**
	 * parentOrganizationCd取得.
	 * @return parentOrganizationCd
	 */
	public String getParentOrganizationCd() {
		return this.parentOrganizationCd;
	}

	/**
	 * parentOrganizationCd設定.
	 * @param parentOrganizationCd parentOrganizationCd
	 */
	public void setParentOrganizationCd(final String parentOrganizationCd) {
		this.parentOrganizationCd = parentOrganizationCd;
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

