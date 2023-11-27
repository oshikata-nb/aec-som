/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 包装指図－製品入出庫実績データ格納クラス.
 * 
 * @author kanri-user
 */
public class PkgDirectionJissekiSeihinListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionJissekiSeihinListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_SEIHIN";

	/** COLUMNアノテーション seihincode. */
	public static final String seihincode_COLUMN = "SEIHINCODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション seihinlot. */
	public static final String seihinlot_COLUMN = "SEIHINLOT";

	/** COLUMNアノテーション hososashizuno. */
	public static final String hososashizuno_COLUMN = "HOSOSASHIZUNO";

	/** COLUMNアノテーション zaikokubun. */
	public static final String zaikokubun_COLUMN = "ZAIKOKUBUN";

	/** COLUMNアノテーション seihinkubun. */
	public static final String seihinkubun_COLUMN = "SEIHINKUBUN";

	/** COLUMNアノテーション location. */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション suuryou. */
	public static final String suuryou_COLUMN = "SUURYOU";

	/** COLUMNアノテーション nyukano. */
	public static final String nyukano_COLUMN = "NYUKANO";

	/** COLUMNアノテーション nyukabc. */
	public static final String nyukabc_COLUMN = "NYUKABC";

	/** COLUMNアノテーション juchuno. */
	public static final String juchuno_COLUMN = "JUCHUNO";

	/** COLUMNアノテーション juchuedaban. */
	public static final String juchuedaban_COLUMN = "JUCHUEDABAN";

	/** COLUMNアノテーション torihikicode. */
	public static final String torihikicode_COLUMN = "TORIHIKICODE";

	/** COLUMNアノテーション hacchuno. */
	public static final String hacchuno_COLUMN = "HACCHUNO";

	/** COLUMNアノテーション jikoku. */
	public static final String jikoku_COLUMN = "JIKOKU";

	/** COLUMNアノテーション nyuusyukko. */
	public static final String nyuusyukko_COLUMN = "NYUUSYUKKO";

	/** COLUMNアノテーション sumi. */
	public static final String sumi_COLUMN = "SUMI";

	//
	// インスタンスフィールド
	//

	private String seihincode;

	private String iocode;

	private String jishaflag;

	private String seihinlot;

	private String hososashizuno;

	private String zaikokubun;

	private String seihinkubun;

	private String location;

	private java.math.BigDecimal suuryou;

	private String nyukano;

	private String nyukabc;

	private String juchuno;

	private String juchuedaban;

	private String torihikicode;

	private String hacchuno;

	private java.sql.Timestamp jikoku;

	private String nyuusyukko;

	private String sumi;

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
	 * seihinlot取得.
	 * @return seihinlot
	 */
	public String getSeihinlot() {
		return this.seihinlot;
	}

	/**
	 * seihinlot設定.
	 * @param seihinlot seihinlot
	 */
	public void setSeihinlot(final String seihinlot) {
		this.seihinlot = seihinlot;
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
	 * seihinkubun取得.
	 * @return seihinkubun
	 */
	public String getSeihinkubun() {
		return this.seihinkubun;
	}

	/**
	 * seihinkubun設定.
	 * @param seihinkubun seihinkubun
	 */
	public void setSeihinkubun(final String seihinkubun) {
		this.seihinkubun = seihinkubun;
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
	 * suuryou取得.
	 * @return suuryou
	 */
	public java.math.BigDecimal getSuuryou() {
		return this.suuryou;
	}

	/**
	 * suuryou設定.
	 * @param suuryou suuryou
	 */
	public void setSuuryou(final java.math.BigDecimal suuryou) {
		this.suuryou = suuryou;
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
