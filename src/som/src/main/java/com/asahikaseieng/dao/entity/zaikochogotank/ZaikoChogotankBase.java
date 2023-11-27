/*
 * Created on Wed Feb 04 16:11:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.zaikochogotank;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ZaikoChogotankBaseクラス.
 * @author kanri-user
 */
public class ZaikoChogotankBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ZaikoChogotankBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ZAIKO_CHOGOTANK";


//	/** IDアノテーション chogotankno. */
//	public static final String chogotankno_ID = "assigned";

	/** COLUMNアノテーション daiseicode. */
	public static final String daiseicode_COLUMN = "DAISEICODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション chogotankno. */
	public static final String chogotankno_COLUMN = "CHOGOTANKNO";

	/** COLUMNアノテーション juryo. */
	public static final String juryo_COLUMN = "JURYO";

	/** COLUMNアノテーション stepno. */
	public static final String stepno_COLUMN = "STEPNO";

	/** COLUMNアノテーション tsuikastep. */
	public static final String tsuikastep_COLUMN = "TSUIKASTEP";

	//
	// インスタンスフィールド
	//

	private String daiseicode;

	private String iocode;

	private String jishaflag;

	private String seizosashizuno;

	private String chogotankno;

	private java.math.BigDecimal juryo;

	private java.math.BigDecimal stepno;

	private java.math.BigDecimal tsuikastep;

	//
	// インスタンスメソッド
	//

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
	 * juryo取得.
	 * @return juryo
	 */
	public java.math.BigDecimal getJuryo() {
		return this.juryo;
	}

	/**
	 * juryo設定.
	 * @param juryo juryo
	 */
	public void setJuryo(final java.math.BigDecimal juryo) {
		this.juryo = juryo;
	}

	/**
	 * stepno取得.
	 * @return stepno
	 */
	public java.math.BigDecimal getStepno() {
		return this.stepno;
	}

	/**
	 * stepno設定.
	 * @param stepno stepno
	 */
	public void setStepno(final java.math.BigDecimal stepno) {
		this.stepno = stepno;
	}

	/**
	 * tsuikastep取得.
	 * @return tsuikastep
	 */
	public java.math.BigDecimal getTsuikastep() {
		return this.tsuikastep;
	}

	/**
	 * tsuikastep設定.
	 * @param tsuikastep tsuikastep
	 */
	public void setTsuikastep(final java.math.BigDecimal tsuikastep) {
		this.tsuikastep = tsuikastep;
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
