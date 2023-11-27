/*
 * Created on Tue May 12 16:22:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.loggingsokonyushukko;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LoggingSokoNyushukkoBaseクラス.
 * @author kanri-user
 */
public class LoggingSokoNyushukkoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoggingSokoNyushukkoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOGGING_SOKO_NYUSHUKKO";


//	/** IDアノテーション barcode. */
//	public static final String barcode_ID = "assigned";
//	/** IDアノテーション irideflag. */
//	public static final String irideflag_ID = "assigned";
//	/** IDアノテーション location. */
//	public static final String location_ID = "assigned";
//	/** IDアノテーション nichiji. */
//	public static final String nichiji_ID = "assigned";

	/** COLUMNアノテーション nichiji. */
	public static final String nichiji_COLUMN = "NICHIJI";

	/** COLUMNアノテーション irideflag. */
	public static final String irideflag_COLUMN = "IRIDEFLAG";

	/** COLUMNアノテーション monoflag. */
	public static final String monoflag_COLUMN = "MONOFLAG";

	/** COLUMNアノテーション barcode. */
	public static final String barcode_COLUMN = "BARCODE";

	/** COLUMNアノテーション location. */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション suryo. */
	public static final String suryo_COLUMN = "SURYO";

	/** COLUMNアノテーション tantocode. */
	public static final String tantocode_COLUMN = "TANTOCODE";

	/** COLUMNアノテーション mcno. */
	public static final String mcno_COLUMN = "MCNO";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp nichiji;

	private String irideflag;

	private String monoflag;

	private String barcode;

	private String location;

	private java.math.BigDecimal suryo;

	private String tantocode;

	private String mcno;

	//
	// インスタンスメソッド
	//

	/**
	 * nichiji取得.
	 * @return nichiji
	 */
	public java.sql.Timestamp getNichiji() {
		return this.nichiji;
	}

	/**
	 * nichiji設定.
	 * @param nichiji nichiji
	 */
	public void setNichiji(final java.sql.Timestamp nichiji) {
		this.nichiji = nichiji;
	}

	/**
	 * irideflag取得.
	 * @return irideflag
	 */
	public String getIrideflag() {
		return this.irideflag;
	}

	/**
	 * irideflag設定.
	 * @param irideflag irideflag
	 */
	public void setIrideflag(final String irideflag) {
		this.irideflag = irideflag;
	}

	/**
	 * monoflag取得.
	 * @return monoflag
	 */
	public String getMonoflag() {
		return this.monoflag;
	}

	/**
	 * monoflag設定.
	 * @param monoflag monoflag
	 */
	public void setMonoflag(final String monoflag) {
		this.monoflag = monoflag;
	}

	/**
	 * barcode取得.
	 * @return barcode
	 */
	public String getBarcode() {
		return this.barcode;
	}

	/**
	 * barcode設定.
	 * @param barcode barcode
	 */
	public void setBarcode(final String barcode) {
		this.barcode = barcode;
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
	 * suryo取得.
	 * @return suryo
	 */
	public java.math.BigDecimal getSuryo() {
		return this.suryo;
	}

	/**
	 * suryo設定.
	 * @param suryo suryo
	 */
	public void setSuryo(final java.math.BigDecimal suryo) {
		this.suryo = suryo;
	}

	/**
	 * tantocode取得.
	 * @return tantocode
	 */
	public String getTantocode() {
		return this.tantocode;
	}

	/**
	 * tantocode設定.
	 * @param tantocode tantocode
	 */
	public void setTantocode(final String tantocode) {
		this.tantocode = tantocode;
	}

	/**
	 * mcno取得.
	 * @return mcno
	 */
	public String getMcno() {
		return this.mcno;
	}

	/**
	 * mcno設定.
	 * @param mcno mcno
	 */
	public void setMcno(final String mcno) {
		this.mcno = mcno;
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
