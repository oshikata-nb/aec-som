/*
 * Created on Wed Feb 04 16:09:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseizodetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * JissekiSeizoDetailBaseクラス.
 * @author kanri-user
 */
public class JissekiSeizoDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JissekiSeizoDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_SEIZO_DETAIL";


//	/** IDアノテーション seizosashizuno. */
//	public static final String seizosashizuno_ID = "assigned";
//	/** IDアノテーション step. */
//	public static final String step_ID = "assigned";
//	/** IDアノテーション substep. */
//	public static final String substep_ID = "assigned";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション step. */
	public static final String step_COLUMN = "STEP";

	/** COLUMNアノテーション substep. */
	public static final String substep_COLUMN = "SUBSTEP";

	/** COLUMNアノテーション kaishijikoku. */
	public static final String kaishijikoku_COLUMN = "KAISHIJIKOKU";

	/** COLUMNアノテーション shuryojikoku. */
	public static final String shuryojikoku_COLUMN = "SHURYOJIKOKU";

	/** COLUMNアノテーション genzaicode. */
	public static final String genzaicode_COLUMN = "GENZAICODE";

	/** COLUMNアノテーション nyukalot. */
	public static final String nyukalot_COLUMN = "NYUKALOT";

	/** COLUMNアノテーション gentonyuryo. */
	public static final String gentonyuryo_COLUMN = "GENTONYURYO";

	/** COLUMNアノテーション ondo. */
	public static final String ondo_COLUMN = "ONDO";

	/** COLUMNアノテーション kakuhan. */
	public static final String kakuhan_COLUMN = "KAKUHAN";

	/** COLUMNアノテーション ph. */
	public static final String ph_COLUMN = "PH";

	/** COLUMNアノテーション shoricode. */
	public static final String shoricode_COLUMN = "SHORICODE";

	//
	// インスタンスフィールド
	//

	private String seizosashizuno;

	private java.math.BigDecimal step;

	private java.math.BigDecimal substep;

	private java.sql.Timestamp kaishijikoku;

	private java.sql.Timestamp shuryojikoku;

	private String genzaicode;

	private String nyukalot;

	private java.math.BigDecimal gentonyuryo;

	private java.math.BigDecimal ondo;

	private java.math.BigDecimal kakuhan;

	private java.math.BigDecimal ph;

	private String shoricode;

	//
	// インスタンスメソッド
	//

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
	 * kaishijikoku取得.
	 * @return kaishijikoku
	 */
	public java.sql.Timestamp getKaishijikoku() {
		return this.kaishijikoku;
	}

	/**
	 * kaishijikoku設定.
	 * @param kaishijikoku kaishijikoku
	 */
	public void setKaishijikoku(final java.sql.Timestamp kaishijikoku) {
		this.kaishijikoku = kaishijikoku;
	}

	/**
	 * shuryojikoku取得.
	 * @return shuryojikoku
	 */
	public java.sql.Timestamp getShuryojikoku() {
		return this.shuryojikoku;
	}

	/**
	 * shuryojikoku設定.
	 * @param shuryojikoku shuryojikoku
	 */
	public void setShuryojikoku(final java.sql.Timestamp shuryojikoku) {
		this.shuryojikoku = shuryojikoku;
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
	 * gentonyuryo取得.
	 * @return gentonyuryo
	 */
	public java.math.BigDecimal getGentonyuryo() {
		return this.gentonyuryo;
	}

	/**
	 * gentonyuryo設定.
	 * @param gentonyuryo gentonyuryo
	 */
	public void setGentonyuryo(final java.math.BigDecimal gentonyuryo) {
		this.gentonyuryo = gentonyuryo;
	}

	/**
	 * ondo取得.
	 * @return ondo
	 */
	public java.math.BigDecimal getOndo() {
		return this.ondo;
	}

	/**
	 * ondo設定.
	 * @param ondo ondo
	 */
	public void setOndo(final java.math.BigDecimal ondo) {
		this.ondo = ondo;
	}

	/**
	 * kakuhan取得.
	 * @return kakuhan
	 */
	public java.math.BigDecimal getKakuhan() {
		return this.kakuhan;
	}

	/**
	 * kakuhan設定.
	 * @param kakuhan kakuhan
	 */
	public void setKakuhan(final java.math.BigDecimal kakuhan) {
		this.kakuhan = kakuhan;
	}

	/**
	 * ph取得.
	 * @return ph
	 */
	public java.math.BigDecimal getPh() {
		return this.ph;
	}

	/**
	 * ph設定.
	 * @param ph ph
	 */
	public void setPh(final java.math.BigDecimal ph) {
		this.ph = ph;
	}

	/**
	 * shoricode取得.
	 * @return shoricode
	 */
	public String getShoricode() {
		return this.shoricode;
	}

	/**
	 * shoricode設定.
	 * @param shoricode shoricode
	 */
	public void setShoricode(final String shoricode) {
		this.shoricode = shoricode;
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
