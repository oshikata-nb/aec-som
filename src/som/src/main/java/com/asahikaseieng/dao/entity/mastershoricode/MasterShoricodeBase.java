/*
 * Created on Wed Feb 04 16:12:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastershoricode;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterShoricodeBaseクラス.
 * @author kanri-user
 */
public class MasterShoricodeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterShoricodeBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_SHORICODE";


//	/** IDアノテーション shioricode. */
//	public static final String shioricode_ID = "assigned";

	/** COLUMNアノテーション shioricode. */
	public static final String shioricode_COLUMN = "SHIORICODE";

	/** COLUMNアノテーション shorimei. */
	public static final String shorimei_COLUMN = "SHORIMEI";

	/** COLUMNアノテーション saiyono. */
	public static final String saiyono_COLUMN = "SAIYONO";

	//
	// インスタンスフィールド
	//

	private String shioricode;

	private String shorimei;

	private String saiyono;

	//
	// インスタンスメソッド
	//

	/**
	 * shioricode取得.
	 * @return shioricode
	 */
	public String getShioricode() {
		return this.shioricode;
	}

	/**
	 * shioricode設定.
	 * @param shioricode shioricode
	 */
	public void setShioricode(final String shioricode) {
		this.shioricode = shioricode;
	}

	/**
	 * shorimei取得.
	 * @return shorimei
	 */
	public String getShorimei() {
		return this.shorimei;
	}

	/**
	 * shorimei設定.
	 * @param shorimei shorimei
	 */
	public void setShorimei(final String shorimei) {
		this.shorimei = shorimei;
	}

	/**
	 * saiyono取得.
	 * @return saiyono
	 */
	public String getSaiyono() {
		return this.saiyono;
	}

	/**
	 * saiyono設定.
	 * @param saiyono saiyono
	 */
	public void setSaiyono(final String saiyono) {
		this.saiyono = saiyono;
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
