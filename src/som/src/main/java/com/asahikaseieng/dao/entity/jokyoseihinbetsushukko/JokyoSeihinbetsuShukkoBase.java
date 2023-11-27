/*
 * Created on Wed Feb 04 16:09:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jokyoseihinbetsushukko;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * JokyoSeihinbetsuShukkoBaseクラス.
 * @author kanri-user
 */
public class JokyoSeihinbetsuShukkoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JokyoSeihinbetsuShukkoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JOKYO_SEIHINBETSU_SHUKKO";


//	/** IDアノテーション seishukkosabc. */
//	public static final String seishukkosabc_ID = "assigned";

	/** COLUMNアノテーション nengappi. */
	public static final String nengappi_COLUMN = "NENGAPPI";

	/** COLUMNアノテーション tsumino. */
	public static final String tsumino_COLUMN = "TSUMINO";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション shukkariyu. */
	public static final String shukkariyu_COLUMN = "SHUKKARIYU";

	/** COLUMNアノテーション hitsuyosu. */
	public static final String hitsuyosu_COLUMN = "HITSUYOSU";

	/** COLUMNアノテーション sumisu. */
	public static final String sumisu_COLUMN = "SUMISU";

	/** COLUMNアノテーション shukkajokyo. */
	public static final String shukkajokyo_COLUMN = "SHUKKAJOKYO";

	/** COLUMNアノテーション forklanno. */
	public static final String forklanno_COLUMN = "FORKLANNO";

	/** COLUMNアノテーション location. */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション hososashizuno. */
	public static final String hososashizuno_COLUMN = "HOSOSASHIZUNO";

	/** COLUMNアノテーション nyukabc. */
	public static final String nyukabc_COLUMN = "NYUKABC";

	/** COLUMNアノテーション seishukkosabc. */
	public static final String seishukkosabc_COLUMN = "SEISHUKKOSABC";

	/** COLUMNアノテーション juchuno. */
	public static final String juchuno_COLUMN = "JUCHUNO";

	/** COLUMNアノテーション juchuedaban. */
	public static final String juchuedaban_COLUMN = "JUCHUEDABAN";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp nengappi;

	private String tsumino;

	private String seihincode;

	private String iocode;

	private String jishaflag;

	private String shukkariyu;

	private String hitsuyosu;

	private String sumisu;

	private String shukkajokyo;

	private String forklanno;

	private String location;

	private String hososashizuno;

	private String nyukabc;

	private String seishukkosabc;

	private String juchuno;

	private String juchuedaban;

	//
	// インスタンスメソッド
	//

	/**
	 * nengappi取得.
	 * @return nengappi
	 */
	public java.sql.Timestamp getNengappi() {
		return this.nengappi;
	}

	/**
	 * nengappi設定.
	 * @param nengappi nengappi
	 */
	public void setNengappi(final java.sql.Timestamp nengappi) {
		this.nengappi = nengappi;
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
	 * hitsuyosu取得.
	 * @return hitsuyosu
	 */
	public String getHitsuyosu() {
		return this.hitsuyosu;
	}

	/**
	 * hitsuyosu設定.
	 * @param hitsuyosu hitsuyosu
	 */
	public void setHitsuyosu(final String hitsuyosu) {
		this.hitsuyosu = hitsuyosu;
	}

	/**
	 * sumisu取得.
	 * @return sumisu
	 */
	public String getSumisu() {
		return this.sumisu;
	}

	/**
	 * sumisu設定.
	 * @param sumisu sumisu
	 */
	public void setSumisu(final String sumisu) {
		this.sumisu = sumisu;
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
	 * forklanno取得.
	 * @return forklanno
	 */
	public String getForklanno() {
		return this.forklanno;
	}

	/**
	 * forklanno設定.
	 * @param forklanno forklanno
	 */
	public void setForklanno(final String forklanno) {
		this.forklanno = forklanno;
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
	 * hososashizuno取得.
	 * @return hososashizuno
	 */
	public String getHososashizuno() {
		return this.hososashizuno;
	}

	/**
	 * hososashizuno設定.
	 * @param hososashizuno hososashizuno
	 */
	public void setHososashizuno(final String hososashizuno) {
		this.hososashizuno = hososashizuno;
	}

	/**
	 * nyukabc取得.
	 * @return nyukabc
	 */
	public String getNyukabc() {
		return this.nyukabc;
	}

	/**
	 * nyukabc設定.
	 * @param nyukabc nyukabc
	 */
	public void setNyukabc(final String nyukabc) {
		this.nyukabc = nyukabc;
	}

	/**
	 * seishukkosabc取得.
	 * @return seishukkosabc
	 */
	public String getSeishukkosabc() {
		return this.seishukkosabc;
	}

	/**
	 * seishukkosabc設定.
	 * @param seishukkosabc seishukkosabc
	 */
	public void setSeishukkosabc(final String seishukkosabc) {
		this.seishukkosabc = seishukkosabc;
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
