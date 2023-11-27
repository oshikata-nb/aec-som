/*
 * Created on Wed Feb 04 16:08:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.codenyukalot;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CodeNyukalotBaseクラス.
 * @author kanri-user
 */
public class CodeNyukalotBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CodeNyukalotBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CODE_NYUKALOT";


//	/** IDアノテーション nyukalot. */
//	public static final String nyukalot_ID = "assigned";

	/** COLUMNアノテーション nyukalot. */
	public static final String nyukalot_COLUMN = "NYUKALOT";

	/** COLUMNアノテーション genzaicode. */
	public static final String genzaicode_COLUMN = "GENZAICODE";

	//
	// インスタンスフィールド
	//

	private String nyukalot;

	private String genzaicode;

	//
	// インスタンスメソッド
	//

	/**
	 * nyukalot取得.
	 * @return nyukalot
	 */
	public String getNyukalot() {
		return this.nyukalot;
	}

	/**
	 * nyukalot設定.
	 * @param nyukalot nyukalot
	 */
	public void setNyukalot(final String nyukalot) {
		this.nyukalot = nyukalot;
	}

	/**
	 * genzaicode取得.
	 * @return genzaicode
	 */
	public String getGenzaicode() {
		return this.genzaicode;
	}

	/**
	 * genzaicode設定.
	 * @param genzaicode genzaicode
	 */
	public void setGenzaicode(final String genzaicode) {
		this.genzaicode = genzaicode;
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
