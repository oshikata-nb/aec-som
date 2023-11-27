/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PostListクラス.
 * @author t0011036
 */
public class PostListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PostListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	/** COLUMNアノテーション shortPostName */
	public static final String shortPostName_COLUMN = "SHORT_POST_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal postId;

	private String postName;

	private String shortPostName;

	//
	// インスタンスメソッド
	//

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

