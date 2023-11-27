/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue Apr 21 14:40:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tdataseihinnyuka;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * TDataSeihinNyukaBaseクラス.
 * @author a7710658
 */
public class TDataSeihinNyukaBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TDataSeihinNyukaBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "T_DATA_SEIHIN_NYUKA";

	// /** IDアノテーション nyukabc. */
	// public static final String nyukabc_ID = "assigned";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション nyukoriyu. */
	public static final String nyukoriyu_COLUMN = "NYUKORIYU";

	/** COLUMNアノテーション nyukasu. */
	public static final String nyukasu_COLUMN = "NYUKASU";

	/** COLUMNアノテーション nyukano. */
	public static final String nyukano_COLUMN = "NYUKANO";

	/** COLUMNアノテーション nyukabc. */
	public static final String nyukabc_COLUMN = "NYUKABC";

	/** COLUMNアノテーション tantocode. */
	public static final String tantocode_COLUMN = "TANTOCODE";

	/** COLUMNアノテーション juchuno. */
	public static final String juchuno_COLUMN = "JUCHUNO";

	/** COLUMNアノテーション juchuedaban. */
	public static final String juchuedaban_COLUMN = "JUCHUEDABAN";

	/** COLUMNアノテーション henpinriyu. */
	public static final String henpinriyu_COLUMN = "HENPINRIYU";

	/** COLUMNアノテーション torihikicode. */
	public static final String torihikicode_COLUMN = "TORIHIKICODE";

	/** COLUMNアノテーション lotno. */
	public static final String lotno_COLUMN = "LOTNO";

	/** COLUMNアノテーション hacchuno. */
	public static final String hacchuno_COLUMN = "HACCHUNO";

	/** COLUMNアノテーション hososashizuno. */
	public static final String hososashizuno_COLUMN = "HOSOSASHIZUNO";

	/** COLUMNアノテーション kenpinsumi. */
	public static final String kenpinsumi_COLUMN = "KENPINSUMI";

	/** COLUMNアノテーション nyukojokyo. */
	public static final String nyukojokyo_COLUMN = "NYUKOJOKYO";

	/** COLUMNアノテーション zansu. */
	public static final String zansu_COLUMN = "ZANSU";

	/** COLUMNアノテーション nonyuyoteibi. */
	public static final String nonyuyoteibi_COLUMN = "NONYUYOTEIBI";

	//
	// インスタンスフィールド
	//

	private String seihincode;

	private String iocode;

	private String jishaflag;

	private String nyukoriyu;

	private java.math.BigDecimal nyukasu;

	private String nyukano;

	private String nyukabc;

	private String tantocode;

	private String juchuno;

	private String juchuedaban;

	private String henpinriyu;

	private String torihikicode;

	private String lotno;

	private String hacchuno;

	private String hososashizuno;

	private String kenpinsumi;

	private String nyukojokyo;

	private java.math.BigDecimal zansu;

	private String nonyuyoteibi;

	//
	// インスタンスメソッド
	//

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
	 * nyukoriyu取得.
	 * @return nyukoriyu
	 */
	public String getNyukoriyu() {
		return this.nyukoriyu;
	}

	/**
	 * nyukoriyu設定.
	 * @param nyukoriyu nyukoriyu
	 */
	public void setNyukoriyu(final String nyukoriyu) {
		this.nyukoriyu = nyukoriyu;
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
	 * nyukano取得.
	 * @return nyukano
	 */
	public String getNyukano() {
		return this.nyukano;
	}

	/**
	 * nyukano設定.
	 * @param nyukano nyukano
	 */
	public void setNyukano(final String nyukano) {
		this.nyukano = nyukano;
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
	 * henpinriyu取得.
	 * @return henpinriyu
	 */
	public String getHenpinriyu() {
		return this.henpinriyu;
	}

	/**
	 * henpinriyu設定.
	 * @param henpinriyu henpinriyu
	 */
	public void setHenpinriyu(final String henpinriyu) {
		this.henpinriyu = henpinriyu;
	}

	/**
	 * torihikicode取得.
	 * @return torihikicode
	 */
	public String getTorihikicode() {
		return this.torihikicode;
	}

	/**
	 * torihikicode設定.
	 * @param torihikicode torihikicode
	 */
	public void setTorihikicode(final String torihikicode) {
		this.torihikicode = torihikicode;
	}

	/**
	 * lotno取得.
	 * @return lotno
	 */
	public String getLotno() {
		return this.lotno;
	}

	/**
	 * lotno設定.
	 * @param lotno lotno
	 */
	public void setLotno(final String lotno) {
		this.lotno = lotno;
	}

	/**
	 * hacchuno取得.
	 * @return hacchuno
	 */
	public String getHacchuno() {
		return this.hacchuno;
	}

	/**
	 * hacchuno設定.
	 * @param hacchuno hacchuno
	 */
	public void setHacchuno(final String hacchuno) {
		this.hacchuno = hacchuno;
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
	 * kenpinsumi取得.
	 * @return kenpinsumi
	 */
	public String getKenpinsumi() {
		return this.kenpinsumi;
	}

	/**
	 * kenpinsumi設定.
	 * @param kenpinsumi kenpinsumi
	 */
	public void setKenpinsumi(final String kenpinsumi) {
		this.kenpinsumi = kenpinsumi;
	}

	/**
	 * nyukojokyo取得.
	 * @return nyukojokyo
	 */
	public String getNyukojokyo() {
		return this.nyukojokyo;
	}

	/**
	 * nyukojokyo設定.
	 * @param nyukojokyo nyukojokyo
	 */
	public void setNyukojokyo(final String nyukojokyo) {
		this.nyukojokyo = nyukojokyo;
	}

	/**
	 * zansu取得.
	 * @return zansu
	 */
	public java.math.BigDecimal getZansu() {
		return this.zansu;
	}

	/**
	 * zansu設定.
	 * @param zansu zansu
	 */
	public void setZansu(final java.math.BigDecimal zansu) {
		this.zansu = zansu;
	}

	/**
	 * nonyuyoteibi取得.
	 * @return nonyuyoteibi
	 */
	public String getNonyuyoteibi() {
		return this.nonyuyoteibi;
	}

	/**
	 * nonyuyoteibi設定.
	 * @param nonyuyoteibi nonyuyoteibi
	 */
	public void setNonyuyoteibi(final String nonyuyoteibi) {
		this.nonyuyoteibi = nonyuyoteibi;
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
