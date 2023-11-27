/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.reason;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 理由マスタオートコンプリートBeanクラス.
 * @author tosco
 */
public class ReasonForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ReasonForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "REASON";

	/** COLUMNアノテーション ryCd */
	public static final String ryCd_COLUMN = "RY_CD";

	/** COLUMNアノテーション ryDescription */
	public static final String ryDescription_COLUMN = "RY_DESCRIPTION";

	//
	// インスタンスフィールド
	//

	/** 理由コード */
	private String ryCd;

	/** 理由内容 */
	private String ryDescription;

	//
	// インスタンスメソッド
	//

	/**
	 * 理由コード取得.
	 * @return String 理由コード
	 */
	public String getRyCd() {
		return ryCd;
	}

	/**
	 * 理由コード設定.
	 * @param ryCd 理由コード
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * 理由内容取得.
	 * @return String 理由内容
	 */
	public String getRyDescription() {
		return ryDescription;
	}

	/**
	 * 理由内容設定.
	 * @param ryDescription 理由内容
	 */
	public void setRyDescription(final String ryDescription) {
		this.ryDescription = ryDescription;
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

