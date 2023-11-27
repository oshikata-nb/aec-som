/*
 * Created on Wed Feb 04 16:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseizohead;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * JissekiSeizoHeadBaseクラス.
 * @author kanri-user
 */
public class JissekiSeizoHeadBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public JissekiSeizoHeadBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "JISSEKI_SEIZO_HEAD";

	// /** IDアノテーション seizosashizuno. */
	// public static final String seizosashizuno_ID = "assigned";

	/** COLUMNアノテーション seizosashizuno. */
	public static final String seizosashizuno_COLUMN = "SEIZOSASHIZUNO";

	/** COLUMNアノテーション seizotantocode. */
	public static final String seizotantocode_COLUMN = "SEIZOTANTOCODE";

	/** COLUMNアノテーション seizobi. */
	public static final String seizobi_COLUMN = "SEIZOBI";

	/** COLUMNアノテーション senjotantocode. */
	public static final String senjotantocode_COLUMN = "SENJOTANTOCODE";

	/** COLUMNアノテーション daiseicode. */
	public static final String daiseicode_COLUMN = "DAISEICODE";

	/** COLUMNアノテーション iocode. */
	public static final String iocode_COLUMN = "IOCODE";

	/** COLUMNアノテーション jishaflag. */
	public static final String jishaflag_COLUMN = "JISHAFLAG";

	/** COLUMNアノテーション chogotankno. */
	public static final String chogotankno_COLUMN = "CHOGOTANKNO";

	/** COLUMNアノテーション holdtankno. */
	public static final String holdtankno_COLUMN = "HOLDTANKNO";

	/** COLUMNアノテーション sikomiryo. */
	public static final String sikomiryo_COLUMN = "SIKOMIRYO";

	/** COLUMNアノテーション chogotankcondi. */
	public static final String chogotankcondi_COLUMN = "CHOGOTANKCONDI";

	/** COLUMNアノテーション yobiyokaicondi. */
	public static final String yobiyokaicondi_COLUMN = "YOBIYOKAICONDI";

	/** COLUMNアノテーション holdtankcondi. */
	public static final String holdtankcondi_COLUMN = "HOLDTANKCONDI";

	/** COLUMNアノテーション mekkintantocode. */
	public static final String mekkintantocode_COLUMN = "MEKKINTANTOCODE";

	/** COLUMNアノテーション mekkinkaishijikan. */
	public static final String mekkinkaishijikan_COLUMN = "MEKKINKAISHIJIKAN";

	/** COLUMNアノテーション mekkinshuryojikan. */
	public static final String mekkinshuryojikan_COLUMN = "MEKKINSHURYOJIKAN";

	/** COLUMNアノテーション mekkinmaxondo. */
	public static final String mekkinmaxondo_COLUMN = "MEKKINMAXONDO";

	/** COLUMNアノテーション mekkinminondo. */
	public static final String mekkinminondo_COLUMN = "MEKKINMINONDO";

	/** COLUMNアノテーション mekkinhaisui. */
	public static final String mekkinhaisui_COLUMN = "MEKKINHAISUI";

	/** COLUMNアノテーション sumi. */
	public static final String sumi_COLUMN = "SUMI";

	//
	// インスタンスフィールド
	//

	private String seizosashizuno;

	private String seizotantocode;

	private String seizobi;

	private String senjotantocode;

	private String daiseicode;

	private String iocode;

	private String jishaflag;

	private String chogotankno;

	private String holdtankno;

	private java.math.BigDecimal sikomiryo;

	private String chogotankcondi;

	private String yobiyokaicondi;

	private String holdtankcondi;

	private String mekkintantocode;

	private String mekkinkaishijikan;

	private String mekkinshuryojikan;

	private java.math.BigDecimal mekkinmaxondo;

	private java.math.BigDecimal mekkinminondo;

	private String mekkinhaisui;

	private String sumi;

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
	 * seizotantocode取得.
	 * @return seizotantocode
	 */
	public String getSeizotantocode() {
		return this.seizotantocode;
	}

	/**
	 * seizotantocode設定.
	 * @param seizotantocode seizotantocode
	 */
	public void setSeizotantocode(final String seizotantocode) {
		this.seizotantocode = seizotantocode;
	}

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
	 * senjotantocode取得.
	 * @return senjotantocode
	 */
	public String getSenjotantocode() {
		return this.senjotantocode;
	}

	/**
	 * senjotantocode設定.
	 * @param senjotantocode senjotantocode
	 */
	public void setSenjotantocode(final String senjotantocode) {
		this.senjotantocode = senjotantocode;
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
	 * sikomiryo取得.
	 * @return sikomiryo
	 */
	public java.math.BigDecimal getSikomiryo() {
		return this.sikomiryo;
	}

	/**
	 * sikomiryo設定.
	 * @param sikomiryo sikomiryo
	 */
	public void setSikomiryo(final java.math.BigDecimal sikomiryo) {
		this.sikomiryo = sikomiryo;
	}

	/**
	 * chogotankcondi取得.
	 * @return chogotankcondi
	 */
	public String getChogotankcondi() {
		return this.chogotankcondi;
	}

	/**
	 * chogotankcondi設定.
	 * @param chogotankcondi chogotankcondi
	 */
	public void setChogotankcondi(final String chogotankcondi) {
		this.chogotankcondi = chogotankcondi;
	}

	/**
	 * yobiyokaicondi取得.
	 * @return yobiyokaicondi
	 */
	public String getYobiyokaicondi() {
		return this.yobiyokaicondi;
	}

	/**
	 * yobiyokaicondi設定.
	 * @param yobiyokaicondi yobiyokaicondi
	 */
	public void setYobiyokaicondi(final String yobiyokaicondi) {
		this.yobiyokaicondi = yobiyokaicondi;
	}

	/**
	 * holdtankcondi取得.
	 * @return holdtankcondi
	 */
	public String getHoldtankcondi() {
		return this.holdtankcondi;
	}

	/**
	 * holdtankcondi設定.
	 * @param holdtankcondi holdtankcondi
	 */
	public void setHoldtankcondi(final String holdtankcondi) {
		this.holdtankcondi = holdtankcondi;
	}

	/**
	 * mekkintantocode取得.
	 * @return mekkintantocode
	 */
	public String getMekkintantocode() {
		return this.mekkintantocode;
	}

	/**
	 * mekkintantocode設定.
	 * @param mekkintantocode mekkintantocode
	 */
	public void setMekkintantocode(final String mekkintantocode) {
		this.mekkintantocode = mekkintantocode;
	}

	/**
	 * mekkinkaishijikan取得.
	 * @return mekkinkaishijikan
	 */
	public String getMekkinkaishijikan() {
		return this.mekkinkaishijikan;
	}

	/**
	 * mekkinkaishijikan設定.
	 * @param mekkinkaishijikan mekkinkaishijikan
	 */
	public void setMekkinkaishijikan(final String mekkinkaishijikan) {
		this.mekkinkaishijikan = mekkinkaishijikan;
	}

	/**
	 * mekkinshuryojikan取得.
	 * @return mekkinshuryojikan
	 */
	public String getMekkinshuryojikan() {
		return this.mekkinshuryojikan;
	}

	/**
	 * mekkinshuryojikan設定.
	 * @param mekkinshuryojikan mekkinshuryojikan
	 */
	public void setMekkinshuryojikan(final String mekkinshuryojikan) {
		this.mekkinshuryojikan = mekkinshuryojikan;
	}

	/**
	 * mekkinmaxondo取得.
	 * @return mekkinmaxondo
	 */
	public java.math.BigDecimal getMekkinmaxondo() {
		return this.mekkinmaxondo;
	}

	/**
	 * mekkinmaxondo設定.
	 * @param mekkinmaxondo mekkinmaxondo
	 */
	public void setMekkinmaxondo(final java.math.BigDecimal mekkinmaxondo) {
		this.mekkinmaxondo = mekkinmaxondo;
	}

	/**
	 * mekkinminondo取得.
	 * @return mekkinminondo
	 */
	public java.math.BigDecimal getMekkinminondo() {
		return this.mekkinminondo;
	}

	/**
	 * mekkinminondo設定.
	 * @param mekkinminondo mekkinminondo
	 */
	public void setMekkinminondo(final java.math.BigDecimal mekkinminondo) {
		this.mekkinminondo = mekkinminondo;
	}

	/**
	 * mekkinhaisui取得.
	 * @return mekkinhaisui
	 */
	public String getMekkinhaisui() {
		return this.mekkinhaisui;
	}

	/**
	 * mekkinhaisui設定.
	 * @param mekkinhaisui mekkinhaisui
	 */
	public void setMekkinhaisui(final String mekkinhaisui) {
		this.mekkinhaisui = mekkinhaisui;
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
