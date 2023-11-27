/*
 * Created on 2009/03/24
 * AECS佐藤 受注納入先区分コンボボックス追加 2020/01/21
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * BelongDetailクラス.
 * @author t0011036
 */
public class BelongDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BelongDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "BELONG";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	/** COLUMNアノテーション belongKbn */
	public static final String belongKbn_COLUMN = "BELONG_KBN";

	/* AECS佐藤 受注納入先区分追加 2020/01/21 */
	/** COLUMNアノテーション orderDeliveryKbn */
	public static final String orderDeliveryKbn_COLUMN = "ORDER_DELIVERY_KBN";
	
	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String savOrganizationCd;

	private String organizationName;

	private String tantoCd;

	private String tantoNm;

	private java.math.BigDecimal postId;

	private java.math.BigDecimal savPostId;

	private String postName;

	private String belongKbn;

	private String savBelongKbn;
	
	/* AECS佐藤 受注納入先区分追加 2020/01/21 */
	private java.math.BigDecimal orderDeliveryKbn;
	
	private java.math.BigDecimal savOrderDeliveryKbn;

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
	 * orderDeliveryKbnを取得します。
	 * @return orderDeliveryKbn
	 */
	public java.math.BigDecimal getOrderDeliveryKbn() {
		return this.orderDeliveryKbn;
	}

	/**
	 * orderDeliveryKbnを設定します。
	 * @param orderDeliveryKbn orderDeliveryKbn
	 */
	public void setOrderDeliveryKbn(final java.math.BigDecimal orderDeliveryKbn) {
		this.orderDeliveryKbn = orderDeliveryKbn;
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

	/**
	 * savPostIdを取得します。
	 * @return savPostId
	 */
	public java.math.BigDecimal getSavPostId() {
		return savPostId;
	}

	/**
	 * savPostIdを設定します。
	 * @param savPostId savPostId
	 */
	public void setSavPostId(final java.math.BigDecimal savPostId) {
		this.savPostId = savPostId;
	}

	/**
	 * savBelongKbnを取得します。
	 * @return savBelongKbn
	 */
	public String getSavBelongKbn() {
		return savBelongKbn;
	}

	/**
	 * savBelongKbnを設定します。
	 * @param savBelongKbn savBelongKbn
	 */
	public void setSavBelongKbn(final String savBelongKbn) {
		this.savBelongKbn = savBelongKbn;
	}

	/**
	 * savOrganizationCdを取得します。
	 * @return savOrganizationCd
	 */
	public String getSavOrganizationCd() {
		return savOrganizationCd;
	}

	/**
	 * savOrganizationCdを設定します。
	 * @param savOrganizationCd savOrganizationCd
	 */
	public void setSavOrganizationCd(final String savOrganizationCd) {
		this.savOrganizationCd = savOrganizationCd;
	}

	/**
	 * savOrderDeliveryKbnを取得します。
	 * @return savOrderDeliveryKbn
	 */
	public java.math.BigDecimal getSavOrderDeliveryKbn() {
		return savOrderDeliveryKbn;
	}

	/**
	 * savOrderDeliveryKbnを設定します。
	 * @param savOrderDeliveryKbn savOrderDeliveryKbn
	 */
	public void setSavOrderDeliveryKbn(final java.math.BigDecimal savOrderDeliveryKbn) {
		this.savOrderDeliveryKbn = savOrderDeliveryKbn;
	}
}
