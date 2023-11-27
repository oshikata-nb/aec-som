/*
 * Created on Wed Feb 04 16:12:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterkojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterKojoBaseクラス.
 * @author kanri-user
 */
public class MasterKojoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterKojoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_KOJO";


//	/** IDアノテーション kojocode. */
//	public static final String kojocode_ID = "assigned";

	/** COLUMNアノテーション tateyacode. */
	public static final String tateyacode_COLUMN = "TATEYACODE";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション areaname. */
	public static final String areaname_COLUMN = "AREANAME";

	/** COLUMNアノテーション fullname. */
	public static final String fullname_COLUMN = "FULLNAME";

	//
	// インスタンスフィールド
	//

	private String tateyacode;

	private String kojocode;

	private String areaname;

	private String fullname;

	//
	// インスタンスメソッド
	//

	/**
	 * tateyacode取得.
	 * @return tateyacode
	 */
	public String getTateyacode() {
		return this.tateyacode;
	}

	/**
	 * tateyacode設定.
	 * @param tateyacode tateyacode
	 */
	public void setTateyacode(final String tateyacode) {
		this.tateyacode = tateyacode;
	}

	/**
	 * kojocode取得.
	 * @return kojocode
	 */
	public String getKojocode() {
		return this.kojocode;
	}

	/**
	 * kojocode設定.
	 * @param kojocode kojocode
	 */
	public void setKojocode(final String kojocode) {
		this.kojocode = kojocode;
	}

	/**
	 * areaname取得.
	 * @return areaname
	 */
	public String getAreaname() {
		return this.areaname;
	}

	/**
	 * areaname設定.
	 * @param areaname areaname
	 */
	public void setAreaname(final String areaname) {
		this.areaname = areaname;
	}

	/**
	 * fullname取得.
	 * @return fullname
	 */
	public String getFullname() {
		return this.fullname;
	}

	/**
	 * fullname設定.
	 * @param fullname fullname
	 */
	public void setFullname(final String fullname) {
		this.fullname = fullname;
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
