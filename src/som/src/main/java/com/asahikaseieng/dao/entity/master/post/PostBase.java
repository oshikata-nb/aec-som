/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Fri Mar 20 14:59:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.post;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PostBaseクラス.
 * @author t0011036
 */
public class PostBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PostBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "POST";


//	/** IDアノテーション postId. */
//	public static final String postId_ID = "assigned";

	/** COLUMNアノテーション postId. */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName. */
	public static final String postName_COLUMN = "POST_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal postId;

	private String postName;

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
