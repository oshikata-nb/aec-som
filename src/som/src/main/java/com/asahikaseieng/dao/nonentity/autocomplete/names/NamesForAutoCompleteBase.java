/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.names;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 各種名称マスタオートコンプリートBeanクラス.
 * @author tosco
 */
public class NamesForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NamesForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	//
	// インスタンスフィールド
	//

	/** 名称コード */
	private String nameCd;

	/** 名称１ */
	private String name01;

	//
	// インスタンスメソッド
	//

	/**
	 * 名称コード取得.
	 * @return String 名称コード
	 */
	public String getNameCd() {
		return nameCd;
	}

	/**
	 * 名称コード設定.
	 * @param nameCd 名称コード
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * 名称１取得.
	 * @return String 名称１
	 */
	public String getName01() {
		return name01;
	}

	/**
	 * 名称１設定.
	 * @param name01 名称１
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
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

