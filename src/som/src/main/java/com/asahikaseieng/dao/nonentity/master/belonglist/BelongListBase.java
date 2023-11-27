/*
 * Created on 2009/06/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BelongListクラス.
 * @author t0011036
 */
public class BelongListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BelongListBase() {
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

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション shortTantoNm */
	public static final String shortTantoNm_COLUMN = "SHORT_TANTO_NM";

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	/** COLUMNアノテーション shortPostName */
	public static final String shortPostName_COLUMN = "SHORT_POST_NAME";

	/** COLUMNアノテーション belongKbn */
	public static final String belongKbn_COLUMN = "BELONG_KBN";

	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String organizationName;

	private String shortOrganizationName;

	private String tantoCd;

	private String tantoNm;

	private String shortTantoNm;

	private java.math.BigDecimal postId;

	private String postName;

	private String shortPostName;

	private String belongKbn;

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
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNm取得.
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return this.tantoNm;
	}

	/**
	 * tantoNm設定.
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * shortTantoNm取得.
	 * @return shortTantoNm
	 */
	public String getShortTantoNm() {
		return this.shortTantoNm;
	}

	/**
	 * shortTantoNm設定.
	 * @param shortTantoNm shortTantoNm
	 */
	public void setShortTantoNm(final String shortTantoNm) {
		this.shortTantoNm = shortTantoNm;
	}

	/**
	 * postId取得.
	 * @return postId
	 */
	public java.math.BigDecimal getPostId() {
		return this.postId;
	}

	/**
	 * postId設定.
	 * @param postId postId
	 */
	public void setPostId(final java.math.BigDecimal postId) {
		this.postId = postId;
	}

	/**
	 * postName取得.
	 * @return postName
	 */
	public String getPostName() {
		return this.postName;
	}

	/**
	 * postName設定.
	 * @param postName postName
	 */
	public void setPostName(final String postName) {
		this.postName = postName;
	}

	/**
	 * shortPostName取得.
	 * @return shortPostName
	 */
	public String getShortPostName() {
		return this.shortPostName;
	}

	/**
	 * shortPostName設定.
	 * @param shortPostName shortPostName
	 */
	public void setShortPostName(final String shortPostName) {
		this.shortPostName = shortPostName;
	}

	/**
	 * belongKbn取得.
	 * @return belongKbn
	 */
	public String getBelongKbn() {
		return this.belongKbn;
	}

	/**
	 * belongKbn設定.
	 * @param belongKbn belongKbn
	 */
	public void setBelongKbn(final String belongKbn) {
		this.belongKbn = belongKbn;
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

