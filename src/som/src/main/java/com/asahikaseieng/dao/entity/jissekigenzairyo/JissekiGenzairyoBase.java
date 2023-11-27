/*
 * Created on Wed Feb 04 16:08:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * JissekiGenzairyoBaseクラス.
 * @author kanri-user
 */
public class JissekiGenzairyoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JissekiGenzairyoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_GENZAIRYO";


//	/** IDアノテーション jikoku. */
//	public static final String jikoku_ID = "assigned";
//	/** IDアノテーション location. */
//	public static final String location_ID = "assigned";
//	/** IDアノテーション nyukalot. */
//	public static final String nyukalot_ID = "assigned";

	/** COLUMNアノテーション genzaicode. */
	public static final String genzaicode_COLUMN = "GENZAICODE";

	/** COLUMNアノテーション zaikokubun. */
	public static final String zaikokubun_COLUMN = "ZAIKOKUBUN";

	/** COLUMNアノテーション location. */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション nyukalot. */
	public static final String nyukalot_COLUMN = "NYUKALOT";

	/** COLUMNアノテーション genryolot. */
	public static final String genryolot_COLUMN = "GENRYOLOT";

	/** COLUMNアノテーション nyukabi. */
	public static final String nyukabi_COLUMN = "NYUKABI";

	/** COLUMNアノテーション nisugatasu. */
	public static final String nisugatasu_COLUMN = "NISUGATASU";

	/** COLUMNアノテーション hasu. */
	public static final String hasu_COLUMN = "HASU";

	/** COLUMNアノテーション jikoku. */
	public static final String jikoku_COLUMN = "JIKOKU";

	/** COLUMNアノテーション nyuusyukko. */
	public static final String nyuusyukko_COLUMN = "NYUUSYUKKO";

	/** COLUMNアノテーション sumi. */
	public static final String sumi_COLUMN = "SUMI";

	//
	// インスタンスフィールド
	//

	private String genzaicode;

	private String zaikokubun;

	private String location;

	private String nyukalot;

	private String genryolot;

	private java.sql.Timestamp nyukabi;

	private String nisugatasu;

	private String hasu;

	private java.sql.Timestamp jikoku;

	private String nyuusyukko;

	private String sumi;

	//
	// インスタンスメソッド
	//

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
	 * zaikokubun取得.
	 * @return zaikokubun
	 */
	public String getZaikokubun() {
		return this.zaikokubun;
	}

	/**
	 * zaikokubun設定.
	 * @param zaikokubun zaikokubun
	 */
	public void setZaikokubun(final String zaikokubun) {
		this.zaikokubun = zaikokubun;
	}

	/**
	 * location取得.
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * location設定.
	 * @param location location
	 */
	public void setLocation(final String location) {
		this.location = location;
	}

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
	 * genryolot取得.
	 * @return genryolot
	 */
	public String getGenryolot() {
		return this.genryolot;
	}

	/**
	 * genryolot設定.
	 * @param genryolot genryolot
	 */
	public void setGenryolot(final String genryolot) {
		this.genryolot = genryolot;
	}

	/**
	 * nyukabi取得.
	 * @return nyukabi
	 */
	public java.sql.Timestamp getNyukabi() {
		return this.nyukabi;
	}

	/**
	 * nyukabi設定.
	 * @param nyukabi nyukabi
	 */
	public void setNyukabi(final java.sql.Timestamp nyukabi) {
		this.nyukabi = nyukabi;
	}

	/**
	 * nisugatasu取得.
	 * @return nisugatasu
	 */
	public String getNisugatasu() {
		return this.nisugatasu;
	}

	/**
	 * nisugatasu設定.
	 * @param nisugatasu nisugatasu
	 */
	public void setNisugatasu(final String nisugatasu) {
		this.nisugatasu = nisugatasu;
	}

	/**
	 * hasu取得.
	 * @return hasu
	 */
	public String getHasu() {
		return this.hasu;
	}

	/**
	 * hasu設定.
	 * @param hasu hasu
	 */
	public void setHasu(final String hasu) {
		this.hasu = hasu;
	}

	/**
	 * jikoku取得.
	 * @return jikoku
	 */
	public java.sql.Timestamp getJikoku() {
		return this.jikoku;
	}

	/**
	 * jikoku設定.
	 * @param jikoku jikoku
	 */
	public void setJikoku(final java.sql.Timestamp jikoku) {
		this.jikoku = jikoku;
	}

	/**
	 * nyuusyukko取得.
	 * @return nyuusyukko
	 */
	public String getNyuusyukko() {
		return this.nyuusyukko;
	}

	/**
	 * nyuusyukko設定.
	 * @param nyuusyukko nyuusyukko
	 */
	public void setNyuusyukko(final String nyuusyukko) {
		this.nyuusyukko = nyuusyukko;
	}

	/**
	 * sumi取得.
	 * @return sumi
	 */
	public String getSumi() {
		return this.sumi;
	}

	/**
	 * sumi設定.
	 * @param sumi sumi
	 */
	public void setSumi(final String sumi) {
		this.sumi = sumi;
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
