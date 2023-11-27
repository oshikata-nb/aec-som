/*
 * Created on Wed Feb 04 16:11:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastergenzairyo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MasterGenzairyoBaseクラス.
 * @author kanri-user
 */
public class MasterGenzairyoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MasterGenzairyoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MASTER_GENZAIRYO";


//	/** IDアノテーション genzaicode. */
//	public static final String genzaicode_ID = "assigned";

	/** COLUMNアノテーション daigencode. */
	public static final String daigencode_COLUMN = "DAIGENCODE";

	/** COLUMNアノテーション daigenmei. */
	public static final String daigenmei_COLUMN = "DAIGENMEI";

	/** COLUMNアノテーション genzaicode. */
	public static final String genzaicode_COLUMN = "GENZAICODE";

	/** COLUMNアノテーション genzaimei. */
	public static final String genzaimei_COLUMN = "GENZAIMEI";

	/** COLUMNアノテーション genzaikana. */
	public static final String genzaikana_COLUMN = "GENZAIKANA";

	/** COLUMNアノテーション nisugata. */
	public static final String nisugata_COLUMN = "NISUGATA";

	/** COLUMNアノテーション kgkanzanchi. */
	public static final String kgkanzanchi_COLUMN = "KGKANZANCHI";

	/** COLUMNアノテーション jundo. */
	public static final String jundo_COLUMN = "JUNDO";

	/** COLUMNアノテーション shiirecode. */
	public static final String shiirecode_COLUMN = "SHIIRECODE";

	/** COLUMNアノテーション yushomusho. */
	public static final String yushomusho_COLUMN = "YUSHOMUSHO";

	/** COLUMNアノテーション genzaizaikoshu. */
	public static final String genzaizaikoshu_COLUMN = "GENZAIZAIKOSHU";

	/** COLUMNアノテーション genzaishubetsu. */
	public static final String genzaishubetsu_COLUMN = "GENZAISHUBETSU";

	/** COLUMNアノテーション genzaikubun. */
	public static final String genzaikubun_COLUMN = "GENZAIKUBUN";

	/** COLUMNアノテーション tashagencode. */
	public static final String tashagencode_COLUMN = "TASHAGENCODE";

	/** COLUMNアノテーション hacchutencode. */
	public static final String hacchutencode_COLUMN = "HACCHUTENCODE";

	/** COLUMNアノテーション genunit. */
	public static final String genunit_COLUMN = "GENUNIT";

	/** COLUMNアノテーション makermei. */
	public static final String makermei_COLUMN = "MAKERMEI";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション gaichuflag. */
	public static final String gaichuflag_COLUMN = "GAICHUFLAG";

	//
	// インスタンスフィールド
	//

	private String daigencode;

	private String daigenmei;

	private String genzaicode;

	private String genzaimei;

	private String genzaikana;

	private String nisugata;

	private java.math.BigDecimal kgkanzanchi;

	private java.math.BigDecimal jundo;

	private String shiirecode;

	private String yushomusho;

	private String genzaizaikoshu;

	private String genzaishubetsu;

	private String genzaikubun;

	private String tashagencode;

	private String hacchutencode;

	private String genunit;

	private String makermei;

	private String kojocode;

	private String gaichuflag;

	//
	// インスタンスメソッド
	//

	/**
	 * daigencode取得.
	 * @return daigencode
	 */
	public String getDaigencode() {
		return this.daigencode;
	}

	/**
	 * daigencode設定.
	 * @param daigencode daigencode
	 */
	public void setDaigencode(final String daigencode) {
		this.daigencode = daigencode;
	}

	/**
	 * daigenmei取得.
	 * @return daigenmei
	 */
	public String getDaigenmei() {
		return this.daigenmei;
	}

	/**
	 * daigenmei設定.
	 * @param daigenmei daigenmei
	 */
	public void setDaigenmei(final String daigenmei) {
		this.daigenmei = daigenmei;
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
	 * genzaimei取得.
	 * @return genzaimei
	 */
	public String getGenzaimei() {
		return this.genzaimei;
	}

	/**
	 * genzaimei設定.
	 * @param genzaimei genzaimei
	 */
	public void setGenzaimei(final String genzaimei) {
		this.genzaimei = genzaimei;
	}

	/**
	 * genzaikana取得.
	 * @return genzaikana
	 */
	public String getGenzaikana() {
		return this.genzaikana;
	}

	/**
	 * genzaikana設定.
	 * @param genzaikana genzaikana
	 */
	public void setGenzaikana(final String genzaikana) {
		this.genzaikana = genzaikana;
	}

	/**
	 * nisugata取得.
	 * @return nisugata
	 */
	public String getNisugata() {
		return this.nisugata;
	}

	/**
	 * nisugata設定.
	 * @param nisugata nisugata
	 */
	public void setNisugata(final String nisugata) {
		this.nisugata = nisugata;
	}

	/**
	 * kgkanzanchi取得.
	 * @return kgkanzanchi
	 */
	public java.math.BigDecimal getKgkanzanchi() {
		return this.kgkanzanchi;
	}

	/**
	 * kgkanzanchi設定.
	 * @param kgkanzanchi kgkanzanchi
	 */
	public void setKgkanzanchi(final java.math.BigDecimal kgkanzanchi) {
		this.kgkanzanchi = kgkanzanchi;
	}

	/**
	 * jundo取得.
	 * @return jundo
	 */
	public java.math.BigDecimal getJundo() {
		return this.jundo;
	}

	/**
	 * jundo設定.
	 * @param jundo jundo
	 */
	public void setJundo(final java.math.BigDecimal jundo) {
		this.jundo = jundo;
	}

	/**
	 * shiirecode取得.
	 * @return shiirecode
	 */
	public String getShiirecode() {
		return this.shiirecode;
	}

	/**
	 * shiirecode設定.
	 * @param shiirecode shiirecode
	 */
	public void setShiirecode(final String shiirecode) {
		this.shiirecode = shiirecode;
	}

	/**
	 * yushomusho取得.
	 * @return yushomusho
	 */
	public String getYushomusho() {
		return this.yushomusho;
	}

	/**
	 * yushomusho設定.
	 * @param yushomusho yushomusho
	 */
	public void setYushomusho(final String yushomusho) {
		this.yushomusho = yushomusho;
	}

	/**
	 * genzaizaikoshu取得.
	 * @return genzaizaikoshu
	 */
	public String getGenzaizaikoshu() {
		return this.genzaizaikoshu;
	}

	/**
	 * genzaizaikoshu設定.
	 * @param genzaizaikoshu genzaizaikoshu
	 */
	public void setGenzaizaikoshu(final String genzaizaikoshu) {
		this.genzaizaikoshu = genzaizaikoshu;
	}

	/**
	 * genzaishubetsu取得.
	 * @return genzaishubetsu
	 */
	public String getGenzaishubetsu() {
		return this.genzaishubetsu;
	}

	/**
	 * genzaishubetsu設定.
	 * @param genzaishubetsu genzaishubetsu
	 */
	public void setGenzaishubetsu(final String genzaishubetsu) {
		this.genzaishubetsu = genzaishubetsu;
	}

	/**
	 * genzaikubun取得.
	 * @return genzaikubun
	 */
	public String getGenzaikubun() {
		return this.genzaikubun;
	}

	/**
	 * genzaikubun設定.
	 * @param genzaikubun genzaikubun
	 */
	public void setGenzaikubun(final String genzaikubun) {
		this.genzaikubun = genzaikubun;
	}

	/**
	 * tashagencode取得.
	 * @return tashagencode
	 */
	public String getTashagencode() {
		return this.tashagencode;
	}

	/**
	 * tashagencode設定.
	 * @param tashagencode tashagencode
	 */
	public void setTashagencode(final String tashagencode) {
		this.tashagencode = tashagencode;
	}

	/**
	 * hacchutencode取得.
	 * @return hacchutencode
	 */
	public String getHacchutencode() {
		return this.hacchutencode;
	}

	/**
	 * hacchutencode設定.
	 * @param hacchutencode hacchutencode
	 */
	public void setHacchutencode(final String hacchutencode) {
		this.hacchutencode = hacchutencode;
	}

	/**
	 * genunit取得.
	 * @return genunit
	 */
	public String getGenunit() {
		return this.genunit;
	}

	/**
	 * genunit設定.
	 * @param genunit genunit
	 */
	public void setGenunit(final String genunit) {
		this.genunit = genunit;
	}

	/**
	 * makermei取得.
	 * @return makermei
	 */
	public String getMakermei() {
		return this.makermei;
	}

	/**
	 * makermei設定.
	 * @param makermei makermei
	 */
	public void setMakermei(final String makermei) {
		this.makermei = makermei;
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
	 * gaichuflag取得.
	 * @return gaichuflag
	 */
	public String getGaichuflag() {
		return this.gaichuflag;
	}

	/**
	 * gaichuflag設定.
	 * @param gaichuflag gaichuflag
	 */
	public void setGaichuflag(final String gaichuflag) {
		this.gaichuflag = gaichuflag;
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
