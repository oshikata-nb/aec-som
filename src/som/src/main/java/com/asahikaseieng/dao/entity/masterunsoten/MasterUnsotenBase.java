/*
 * Created on Wed Feb 04 16:12:47 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterunsoten;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterUnsotenBaseクラス.
 * @author kanri-user
 */
public class MasterUnsotenBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterUnsotenBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_UNSOTEN";


//	/** IDアノテーション unsotencode. */
//	public static final String unsotencode_ID = "assigned";

	/** COLUMNアノテーション unsotencode. */
	public static final String unsotencode_COLUMN = "UNSOTENCODE";

	/** COLUMNアノテーション unsotenmei. */
	public static final String unsotenmei_COLUMN = "UNSOTENMEI";

	/** COLUMNアノテーション unsotenkigo. */
	public static final String unsotenkigo_COLUMN = "UNSOTENKIGO";

	//
	// インスタンスフィールド
	//

	private String unsotencode;

	private String unsotenmei;

	private String unsotenkigo;

	//
	// インスタンスメソッド
	//

	/**
	 * unsotencode取得.
	 * @return unsotencode
	 */
	public String getUnsotencode() {
		return this.unsotencode;
	}

	/**
	 * unsotencode設定.
	 * @param unsotencode unsotencode
	 */
	public void setUnsotencode(final String unsotencode) {
		this.unsotencode = unsotencode;
	}

	/**
	 * unsotenmei取得.
	 * @return unsotenmei
	 */
	public String getUnsotenmei() {
		return this.unsotenmei;
	}

	/**
	 * unsotenmei設定.
	 * @param unsotenmei unsotenmei
	 */
	public void setUnsotenmei(final String unsotenmei) {
		this.unsotenmei = unsotenmei;
	}

	/**
	 * unsotenkigo取得.
	 * @return unsotenkigo
	 */
	public String getUnsotenkigo() {
		return this.unsotenkigo;
	}

	/**
	 * unsotenkigo設定.
	 * @param unsotenkigo unsotenkigo
	 */
	public void setUnsotenkigo(final String unsotenkigo) {
		this.unsotenkigo = unsotenkigo;
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
