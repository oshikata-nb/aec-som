/*
 * Created on Wed Feb 04 16:08:52 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyolabelhakko;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * JissekiGenzairyoLabelhakkoBaseクラス.
 * @author kanri-user
 */
public class JissekiGenzairyoLabelhakkoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JissekiGenzairyoLabelhakkoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_GENZAIRYO_LABELHAKKO";


//	/** IDアノテーション nyukalot. */
//	public static final String nyukalot_ID = "assigned";
//	/** IDアノテーション renban. */
//	public static final String renban_ID = "assigned";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション genzaicode. */
	public static final String genzaicode_COLUMN = "GENZAICODE";

	/** COLUMNアノテーション labelhakkobi. */
	public static final String labelhakkobi_COLUMN = "LABELHAKKOBI";

	/** COLUMNアノテーション nyukasu. */
	public static final String nyukasu_COLUMN = "NYUKASU";

	/** COLUMNアノテーション nyukaryo. */
	public static final String nyukaryo_COLUMN = "NYUKARYO";

	/** COLUMNアノテーション genryolot. */
	public static final String genryolot_COLUMN = "GENRYOLOT";

	/** COLUMNアノテーション nyukalot. */
	public static final String nyukalot_COLUMN = "NYUKALOT";

	/** COLUMNアノテーション renban. */
	public static final String renban_COLUMN = "RENBAN";

	/** COLUMNアノテーション minyukosu. */
	public static final String minyukosu_COLUMN = "MINYUKOSU";

	//
	// インスタンスフィールド
	//

	private String kojocode;

	private String genzaicode;

	private String labelhakkobi;

	private java.math.BigDecimal nyukasu;

	private java.math.BigDecimal nyukaryo;

	private String genryolot;

	private String nyukalot;

	private java.math.BigDecimal renban;

	private java.math.BigDecimal minyukosu;

	//
	// インスタンスメソッド
	//

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
	 * labelhakkobi取得.
	 * @return labelhakkobi
	 */
	public String getLabelhakkobi() {
		return this.labelhakkobi;
	}

	/**
	 * labelhakkobi設定.
	 * @param labelhakkobi labelhakkobi
	 */
	public void setLabelhakkobi(final String labelhakkobi) {
		this.labelhakkobi = labelhakkobi;
	}

	/**
	 * nyukasu取得.
	 * @return nyukasu
	 */
	public java.math.BigDecimal getNyukasu() {
		return this.nyukasu;
	}

	/**
	 * nyukasu設定.
	 * @param nyukasu nyukasu
	 */
	public void setNyukasu(final java.math.BigDecimal nyukasu) {
		this.nyukasu = nyukasu;
	}

	/**
	 * nyukaryo取得.
	 * @return nyukaryo
	 */
	public java.math.BigDecimal getNyukaryo() {
		return this.nyukaryo;
	}

	/**
	 * nyukaryo設定.
	 * @param nyukaryo nyukaryo
	 */
	public void setNyukaryo(final java.math.BigDecimal nyukaryo) {
		this.nyukaryo = nyukaryo;
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
	 * renban取得.
	 * @return renban
	 */
	public java.math.BigDecimal getRenban() {
		return this.renban;
	}

	/**
	 * renban設定.
	 * @param renban renban
	 */
	public void setRenban(final java.math.BigDecimal renban) {
		this.renban = renban;
	}

	/**
	 * minyukosu取得.
	 * @return minyukosu
	 */
	public java.math.BigDecimal getMinyukosu() {
		return this.minyukosu;
	}

	/**
	 * minyukosu設定.
	 * @param minyukosu minyukosu
	 */
	public void setMinyukosu(final java.math.BigDecimal minyukosu) {
		this.minyukosu = minyukosu;
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
