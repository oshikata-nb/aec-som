/*
 * Created on Wed Feb 04 09:41:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.belong;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BelongBaseクラス.
 * @author kanri-user
 */
public class BelongBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BelongBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "BELONG";



	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション postId. */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション belongKbn. */
	public static final String belongKbn_COLUMN = "BELONG_KBN";
	
	/** COLUMNアノテーション OrderDeliveryKbn. */
	public static final String OrderDeliveryKbn_COLUMN = "ORDER_DELIVERY_KBN";

	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String tantoCd;

	private java.math.BigDecimal postId;

	private String belongKbn;
	
	private String orderDeliveryKbn;

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
	 * orderDeliveryKbn取得.
	 * @return orderDeliveryKbn
	 */
	public String getOrderDeliveryKbn() {
		return this.orderDeliveryKbn;
	}

	/**
	 * orderDeliveryKbn設定.
	 * @param orderDeliveryKbn orderDeliveryKbn
	 */
	public void setOrderDeliveryKbn(final String orderDeliveryKbn) {
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
}
