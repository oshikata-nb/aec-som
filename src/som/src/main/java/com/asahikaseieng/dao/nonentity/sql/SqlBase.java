/*
 * Created on 2008/06/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sql;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LabelListクラス.
 * @author a1020630
 */
public class SqlBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SqlBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション listString */
	public static final String listString_COLUMN = "LIST_STRING";

	/** COLUMNアノテーション linkString */
	public static final String linkString01_COLUMN = "LINK_STRING01";

	/** COLUMNアノテーション linkString */
	public static final String linkString02_COLUMN = "LINK_STRING02";

	//
	// インスタンスフィールド
	//

	private String listString;

	private String linkString01;

	private String linkString02;

	//
	// インスタンスメソッド
	//

	/**
	 * listString取得.
	 * @return listString
	 */
	public String getListString() {
		return this.listString;
	}

	/**
	 * listString設定.
	 * @param listString listString
	 */
	public void setListString(final String listString) {
		this.listString = listString;
	}

	/**
	 * linkString01取得.
	 * @return linkString01
	 */
	public String getLinkString01() {
		return this.linkString01;
	}

	/**
	 * linkString01設定.
	 * @param linkString01 linkString01
	 */
	public void setLinkString01(final String linkString01) {
		this.linkString01 = linkString01;
	}

	/**
	 * linkString02取得.
	 * @return linkString02
	 */
	public String getLinkString02() {
		return this.linkString02;
	}

	/**
	 * linkString02設定.
	 * @param linkString02 linkString02
	 */
	public void setLinkString02(final String linkString02) {
		this.linkString02 = linkString02;
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

