/*
 * Created on Tue May 12 16:12:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakushukka;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * KeikakuShukkaBaseクラス.
 * @author kanri-user
 */
public class KeikakuShukkaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public KeikakuShukkaBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "KEIKAKU_SHUKKA";

	// /** IDアノテーション nengappi. */
	// public static final String nengappi_ID = "assigned";
	// /** IDアノテーション seihincode. */
	// public static final String seihincode_ID = "assigned";
	// /** IDアノテーション tsumino. */
	// public static final String tsumino_ID = "assigned";

	/** COLUMNアノテーション nengappi. */
	public static final String nengappi_COLUMN = "NENGAPPI";

	/** COLUMNアノテーション sysflag. */
	public static final String sysflag_COLUMN = "SYSFLAG";

	/** COLUMNアノテーション konsai. */
	public static final String konsai_COLUMN = "KONSAI";

	/** COLUMNアノテーション tsumino. */
	public static final String tsumino_COLUMN = "TSUMINO";

	/** COLUMNアノテーション juchuno. */
	public static final String juchuno_COLUMN = "JUCHUNO";

	/** COLUMNアノテーション juchuedaban. */
	public static final String juchuedaban_COLUMN = "JUCHUEDABAN";

	/** COLUMNアノテーション todokecode. */
	public static final String todokecode_COLUMN = "TODOKECODE";

	/** COLUMNアノテーション unsotencode. */
	public static final String unsotencode_COLUMN = "UNSOTENCODE";

	/** COLUMNアノテーション shaban. */
	public static final String shaban_COLUMN = "SHABAN";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション shukkariyu. */
	public static final String shukkariyu_COLUMN = "SHUKKARIYU";

	/** COLUMNアノテーション keikakusu. */
	public static final String keikakusu_COLUMN = "KEIKAKUSU";

	/** COLUMNアノテーション hakkosumi. */
	public static final String hakkosumi_COLUMN = "HAKKOSUMI";

	/** COLUMNアノテーション shukkajokyo. */
	public static final String shukkajokyo_COLUMN = "SHUKKAJOKYO";

	/** COLUMNアノテーション jokyo1f. */
	public static final String jokyo1f_COLUMN = "JOKYO1F";

	/** COLUMNアノテーション jokyo2f. */
	public static final String jokyo2f_COLUMN = "JOKYO2F";

	/** COLUMNアノテーション jokyokiken. */
	public static final String jokyokiken_COLUMN = "JOKYOKIKEN";

	/** COLUMNアノテーション shukkahoho. */
	public static final String shukkahoho_COLUMN = "SHUKKAHOHO";

	//
	// インスタンスフィールド
	//

	private String nengappi;

	private String sysflag;

	private String konsai;

	private String tsumino;

	private String juchuno;

	private String juchuedaban;

	private String todokecode;

	private String unsotencode;

	private String shaban;

	private String seihincode;

	private String iocode;

	private String jishaflag;

	private String shukkariyu;

	private java.math.BigDecimal keikakusu;

	private String hakkosumi;

	private String shukkajokyo;

	private String jokyo1f;

	private String jokyo2f;

	private String jokyokiken;

	private String shukkahoho;

	//
	// インスタンスメソッド
	//

	/**
	 * nengappi取得.
	 * @return nengappi
	 */
	public String getNengappi() {
		return this.nengappi;
	}

	/**
	 * nengappi設定.
	 * @param nengappi nengappi
	 */
	public void setNengappi(final String nengappi) {
		this.nengappi = nengappi;
	}

	/**
	 * sysflag取得.
	 * @return sysflag
	 */
	public String getSysflag() {
		return this.sysflag;
	}

	/**
	 * sysflag設定.
	 * @param sysflag sysflag
	 */
	public void setSysflag(final String sysflag) {
		this.sysflag = sysflag;
	}

	/**
	 * konsai取得.
	 * @return konsai
	 */
	public String getKonsai() {
		return this.konsai;
	}

	/**
	 * konsai設定.
	 * @param konsai konsai
	 */
	public void setKonsai(final String konsai) {
		this.konsai = konsai;
	}

	/**
	 * tsumino取得.
	 * @return tsumino
	 */
	public String getTsumino() {
		return this.tsumino;
	}

	/**
	 * tsumino設定.
	 * @param tsumino tsumino
	 */
	public void setTsumino(final String tsumino) {
		this.tsumino = tsumino;
	}

	/**
	 * juchuno取得.
	 * @return juchuno
	 */
	public String getJuchuno() {
		return this.juchuno;
	}

	/**
	 * juchuno設定.
	 * @param juchuno juchuno
	 */
	public void setJuchuno(final String juchuno) {
		this.juchuno = juchuno;
	}

	/**
	 * juchuedaban取得.
	 * @return juchuedaban
	 */
	public String getJuchuedaban() {
		return this.juchuedaban;
	}

	/**
	 * juchuedaban設定.
	 * @param juchuedaban juchuedaban
	 */
	public void setJuchuedaban(final String juchuedaban) {
		this.juchuedaban = juchuedaban;
	}

	/**
	 * todokecode取得.
	 * @return todokecode
	 */
	public String getTodokecode() {
		return this.todokecode;
	}

	/**
	 * todokecode設定.
	 * @param todokecode todokecode
	 */
	public void setTodokecode(final String todokecode) {
		this.todokecode = todokecode;
	}

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
	 * shaban取得.
	 * @return shaban
	 */
	public String getShaban() {
		return this.shaban;
	}

	/**
	 * shaban設定.
	 * @param shaban shaban
	 */
	public void setShaban(final String shaban) {
		this.shaban = shaban;
	}

	/**
	 * seihincode取得.
	 * @return seihincode
	 */
	public String getSeihincode() {
		return this.seihincode;
	}

	/**
	 * seihincode設定.
	 * @param seihincode seihincode
	 */
	public void setSeihincode(final String seihincode) {
		this.seihincode = seihincode;
	}

	/**
	 * iocode取得.
	 * @return iocode
	 */
	public String getIocode() {
		return this.iocode;
	}

	/**
	 * iocode設定.
	 * @param iocode iocode
	 */
	public void setIocode(final String iocode) {
		this.iocode = iocode;
	}

	/**
	 * jishaflag取得.
	 * @return jishaflag
	 */
	public String getJishaflag() {
		return this.jishaflag;
	}

	/**
	 * jishaflag設定.
	 * @param jishaflag jishaflag
	 */
	public void setJishaflag(final String jishaflag) {
		this.jishaflag = jishaflag;
	}

	/**
	 * shukkariyu取得.
	 * @return shukkariyu
	 */
	public String getShukkariyu() {
		return this.shukkariyu;
	}

	/**
	 * shukkariyu設定.
	 * @param shukkariyu shukkariyu
	 */
	public void setShukkariyu(final String shukkariyu) {
		this.shukkariyu = shukkariyu;
	}

	/**
	 * keikakusu取得.
	 * @return keikakusu
	 */
	public java.math.BigDecimal getKeikakusu() {
		return this.keikakusu;
	}

	/**
	 * keikakusu設定.
	 * @param keikakusu keikakusu
	 */
	public void setKeikakusu(final java.math.BigDecimal keikakusu) {
		this.keikakusu = keikakusu;
	}

	/**
	 * hakkosumi取得.
	 * @return hakkosumi
	 */
	public String getHakkosumi() {
		return this.hakkosumi;
	}

	/**
	 * hakkosumi設定.
	 * @param hakkosumi hakkosumi
	 */
	public void setHakkosumi(final String hakkosumi) {
		this.hakkosumi = hakkosumi;
	}

	/**
	 * shukkajokyo取得.
	 * @return shukkajokyo
	 */
	public String getShukkajokyo() {
		return this.shukkajokyo;
	}

	/**
	 * shukkajokyo設定.
	 * @param shukkajokyo shukkajokyo
	 */
	public void setShukkajokyo(final String shukkajokyo) {
		this.shukkajokyo = shukkajokyo;
	}

	/**
	 * jokyo1f取得.
	 * @return jokyo1f
	 */
	public String getJokyo1f() {
		return this.jokyo1f;
	}

	/**
	 * jokyo1f設定.
	 * @param jokyo1f jokyo1f
	 */
	public void setJokyo1f(final String jokyo1f) {
		this.jokyo1f = jokyo1f;
	}

	/**
	 * jokyo2f取得.
	 * @return jokyo2f
	 */
	public String getJokyo2f() {
		return this.jokyo2f;
	}

	/**
	 * jokyo2f設定.
	 * @param jokyo2f jokyo2f
	 */
	public void setJokyo2f(final String jokyo2f) {
		this.jokyo2f = jokyo2f;
	}

	/**
	 * jokyokiken取得.
	 * @return jokyokiken
	 */
	public String getJokyokiken() {
		return this.jokyokiken;
	}

	/**
	 * jokyokiken設定.
	 * @param jokyokiken jokyokiken
	 */
	public void setJokyokiken(final String jokyokiken) {
		this.jokyokiken = jokyokiken;
	}

	/**
	 * shukkahoho取得.
	 * @return shukkahoho
	 */
	public String getShukkahoho() {
		return this.shukkahoho;
	}

	/**
	 * shukkahoho設定.
	 * @param shukkahoho shukkahoho
	 */
	public void setShukkahoho(final String shukkahoho) {
		this.shukkahoho = shukkahoho;
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
