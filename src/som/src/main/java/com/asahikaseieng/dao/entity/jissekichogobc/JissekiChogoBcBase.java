/*
 * Created on Wed Feb 04 16:08:26 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekichogobc;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * JissekiChogoBcBaseクラス.
 * @author kanri-user
 */
public class JissekiChogoBcBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JissekiChogoBcBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_CHOGO_BC";


//	/** IDアノテーション location. */
//	public static final String location_ID = "assigned";
//	/** IDアノテーション modeFlg. */
//	public static final String modeFlg_ID = "assigned";
//	/** IDアノテーション nyukalot. */
//	public static final String nyukalot_ID = "assigned";
//	/** IDアノテーション step. */
//	public static final String step_ID = "assigned";
//	/** IDアノテーション substep. */
//	public static final String substep_ID = "assigned";
//	/** IDアノテーション tonyuno. */
//	public static final String tonyuno_ID = "assigned";

	/** COLUMNアノテーション location. */
	public static final String location_COLUMN = "LOCATION";

	/** COLUMNアノテーション nyukalot. */
	public static final String nyukalot_COLUMN = "NYUKALOT";

	/** COLUMNアノテーション step. */
	public static final String step_COLUMN = "STEP";

	/** COLUMNアノテーション substep. */
	public static final String substep_COLUMN = "SUBSTEP";

	/** COLUMNアノテーション tonyuno. */
	public static final String tonyuno_COLUMN = "TONYUNO";

	/** COLUMNアノテーション juryo. */
	public static final String juryo_COLUMN = "JURYO";

	/** COLUMNアノテーション modeFlg. */
	public static final String modeFlg_COLUMN = "MODE_FLG";

	//
	// インスタンスフィールド
	//

	private String location;

	private String nyukalot;

	private java.math.BigDecimal step;

	private java.math.BigDecimal substep;

	private java.math.BigDecimal tonyuno;

	private java.math.BigDecimal juryo;

	private String modeFlg;

	//
	// インスタンスメソッド
	//

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
	 * step取得.
	 * @return step
	 */
	public java.math.BigDecimal getStep() {
		return this.step;
	}

	/**
	 * step設定.
	 * @param step step
	 */
	public void setStep(final java.math.BigDecimal step) {
		this.step = step;
	}

	/**
	 * substep取得.
	 * @return substep
	 */
	public java.math.BigDecimal getSubstep() {
		return this.substep;
	}

	/**
	 * substep設定.
	 * @param substep substep
	 */
	public void setSubstep(final java.math.BigDecimal substep) {
		this.substep = substep;
	}

	/**
	 * tonyuno取得.
	 * @return tonyuno
	 */
	public java.math.BigDecimal getTonyuno() {
		return this.tonyuno;
	}

	/**
	 * tonyuno設定.
	 * @param tonyuno tonyuno
	 */
	public void setTonyuno(final java.math.BigDecimal tonyuno) {
		this.tonyuno = tonyuno;
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
	 * modeFlg取得.
	 * @return modeFlg
	 */
	public String getModeFlg() {
		return this.modeFlg;
	}

	/**
	 * modeFlg設定.
	 * @param modeFlg modeFlg
	 */
	public void setModeFlg(final String modeFlg) {
		this.modeFlg = modeFlg;
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
