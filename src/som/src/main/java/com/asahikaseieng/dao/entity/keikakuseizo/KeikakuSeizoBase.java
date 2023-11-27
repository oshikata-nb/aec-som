/*
 * Created on Wed Feb 04 16:10:11 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuseizo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * KeikakuSeizoBaseクラス.
 * @author kanri-user
 */
public class KeikakuSeizoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public KeikakuSeizoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "KEIKAKU_SEIZO";


//	/** IDアノテーション seizosashizuno. */
//	public static final String seizosashizuno_ID = "assigned";

	/** COLUMNアノテーション seizobi. */
	public static final String seizobi_COLUMN = "SEIZOBI";

	/** COLUMNアノテーション kojocode. */
	public static final String kojocode_COLUMN = "KOJOCODE";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション chogotankno. */
	public static final String chogotankno_COLUMN = "CHOGOTANKNO";

	/** COLUMNアノテーション holdtankno. */
	public static final String holdtankno_COLUMN = "HOLDTANKNO";

	/** COLUMNアノテーション junban. */
	public static final String junban_COLUMN = "JUNBAN";

	/** COLUMNアノテーション daiseicode. */
	public static final String daiseicode_COLUMN = "DAISEICODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション keikakuryo. */
	public static final String keikakuryo_COLUMN = "KEIKAKURYO";

	/** COLUMNアノテーション seizojokyo. */
	public static final String seizojokyo_COLUMN = "SEIZOJOKYO";

	/** COLUMNアノテーション henkofuka. */
	public static final String henkofuka_COLUMN = "HENKOFUKA";

	//
	// インスタンスフィールド
	//

	private String seizobi;

	private String kojocode;

	private String seizosashizuno;

	private String chogotankno;

	private String holdtankno;

	private java.math.BigDecimal junban;

	private String daiseicode;

	private String iocode;

	private String jishaflag;

	private java.math.BigDecimal keikakuryo;

	private String seizojokyo;

	private String henkofuka;

	//
	// インスタンスメソッド
	//

	/**
	 * seizobi取得.
	 * @return seizobi
	 */
	public String getSeizobi() {
		return this.seizobi;
	}

	/**
	 * seizobi設定.
	 * @param seizobi seizobi
	 */
	public void setSeizobi(final String seizobi) {
		this.seizobi = seizobi;
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
	 * seizosashizuno取得.
	 * @return seizosashizuno
	 */
	public String getSeizosashizuno() {
		return this.seizosashizuno;
	}

	/**
	 * seizosashizuno設定.
	 * @param seizosashizuno seizosashizuno
	 */
	public void setSeizosashizuno(final String seizosashizuno) {
		this.seizosashizuno = seizosashizuno;
	}

	/**
	 * chogotankno取得.
	 * @return chogotankno
	 */
	public String getChogotankno() {
		return this.chogotankno;
	}

	/**
	 * chogotankno設定.
	 * @param chogotankno chogotankno
	 */
	public void setChogotankno(final String chogotankno) {
		this.chogotankno = chogotankno;
	}

	/**
	 * holdtankno取得.
	 * @return holdtankno
	 */
	public String getHoldtankno() {
		return this.holdtankno;
	}

	/**
	 * holdtankno設定.
	 * @param holdtankno holdtankno
	 */
	public void setHoldtankno(final String holdtankno) {
		this.holdtankno = holdtankno;
	}

	/**
	 * junban取得.
	 * @return junban
	 */
	public java.math.BigDecimal getJunban() {
		return this.junban;
	}

	/**
	 * junban設定.
	 * @param junban junban
	 */
	public void setJunban(final java.math.BigDecimal junban) {
		this.junban = junban;
	}

	/**
	 * daiseicode取得.
	 * @return daiseicode
	 */
	public String getDaiseicode() {
		return this.daiseicode;
	}

	/**
	 * daiseicode設定.
	 * @param daiseicode daiseicode
	 */
	public void setDaiseicode(final String daiseicode) {
		this.daiseicode = daiseicode;
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
	 * keikakuryo取得.
	 * @return keikakuryo
	 */
	public java.math.BigDecimal getKeikakuryo() {
		return this.keikakuryo;
	}

	/**
	 * keikakuryo設定.
	 * @param keikakuryo keikakuryo
	 */
	public void setKeikakuryo(final java.math.BigDecimal keikakuryo) {
		this.keikakuryo = keikakuryo;
	}

	/**
	 * seizojokyo取得.
	 * @return seizojokyo
	 */
	public String getSeizojokyo() {
		return this.seizojokyo;
	}

	/**
	 * seizojokyo設定.
	 * @param seizojokyo seizojokyo
	 */
	public void setSeizojokyo(final String seizojokyo) {
		this.seizojokyo = seizojokyo;
	}

	/**
	 * henkofuka取得.
	 * @return henkofuka
	 */
	public String getHenkofuka() {
		return this.henkofuka;
	}

	/**
	 * henkofuka設定.
	 * @param henkofuka henkofuka
	 */
	public void setHenkofuka(final String henkofuka) {
		this.henkofuka = henkofuka;
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
