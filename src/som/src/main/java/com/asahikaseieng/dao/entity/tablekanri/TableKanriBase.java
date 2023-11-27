/*
 * Created on Wed Feb 04 16:12:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tablekanri;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TableKanriBaseクラス.
 * @author kanri-user
 */
public class TableKanriBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TableKanriBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TABLE_KANRI";


//	/** IDアノテーション codeno. */
//	public static final String codeno_ID = "assigned";
//	/** IDアノテーション setteichi. */
//	public static final String setteichi_ID = "assigned";

	/** COLUMNアノテーション codeno. */
	public static final String codeno_COLUMN = "CODENO";

	/** COLUMNアノテーション codemei. */
	public static final String codemei_COLUMN = "CODEMEI";

	/** COLUMNアノテーション setteichi. */
	public static final String setteichi_COLUMN = "SETTEICHI";

	/** COLUMNアノテーション suchi. */
	public static final String suchi_COLUMN = "SUCHI";

	/** COLUMNアノテーション mojichi. */
	public static final String mojichi_COLUMN = "MOJICHI";

	/** COLUMNアノテーション hiduke. */
	public static final String hiduke_COLUMN = "HIDUKE";

	/** COLUMNアノテーション keta. */
	public static final String keta_COLUMN = "KETA";

	/** COLUMNアノテーション biko. */
	public static final String biko_COLUMN = "BIKO";

	//
	// インスタンスフィールド
	//

	private String codeno;

	private String codemei;

	private String setteichi;

	private java.math.BigDecimal suchi;

	private String mojichi;

	private java.sql.Timestamp hiduke;

	private java.math.BigDecimal keta;

	private String biko;

	//
	// インスタンスメソッド
	//

	/**
	 * codeno取得.
	 * @return codeno
	 */
	public String getCodeno() {
		return this.codeno;
	}

	/**
	 * codeno設定.
	 * @param codeno codeno
	 */
	public void setCodeno(final String codeno) {
		this.codeno = codeno;
	}

	/**
	 * codemei取得.
	 * @return codemei
	 */
	public String getCodemei() {
		return this.codemei;
	}

	/**
	 * codemei設定.
	 * @param codemei codemei
	 */
	public void setCodemei(final String codemei) {
		this.codemei = codemei;
	}

	/**
	 * setteichi取得.
	 * @return setteichi
	 */
	public String getSetteichi() {
		return this.setteichi;
	}

	/**
	 * setteichi設定.
	 * @param setteichi setteichi
	 */
	public void setSetteichi(final String setteichi) {
		this.setteichi = setteichi;
	}

	/**
	 * suchi取得.
	 * @return suchi
	 */
	public java.math.BigDecimal getSuchi() {
		return this.suchi;
	}

	/**
	 * suchi設定.
	 * @param suchi suchi
	 */
	public void setSuchi(final java.math.BigDecimal suchi) {
		this.suchi = suchi;
	}

	/**
	 * mojichi取得.
	 * @return mojichi
	 */
	public String getMojichi() {
		return this.mojichi;
	}

	/**
	 * mojichi設定.
	 * @param mojichi mojichi
	 */
	public void setMojichi(final String mojichi) {
		this.mojichi = mojichi;
	}

	/**
	 * hiduke取得.
	 * @return hiduke
	 */
	public java.sql.Timestamp getHiduke() {
		return this.hiduke;
	}

	/**
	 * hiduke設定.
	 * @param hiduke hiduke
	 */
	public void setHiduke(final java.sql.Timestamp hiduke) {
		this.hiduke = hiduke;
	}

	/**
	 * keta取得.
	 * @return keta
	 */
	public java.math.BigDecimal getKeta() {
		return this.keta;
	}

	/**
	 * keta設定.
	 * @param keta keta
	 */
	public void setKeta(final java.math.BigDecimal keta) {
		this.keta = keta;
	}

	/**
	 * biko取得.
	 * @return biko
	 */
	public String getBiko() {
		return this.biko;
	}

	/**
	 * biko設定.
	 * @param biko biko
	 */
	public void setBiko(final String biko) {
		this.biko = biko;
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
