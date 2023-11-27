/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * GHSTemp（原処方情報からの取得）
 * @author hongyo
 */
public class GhsTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GhsTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "GHS_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション ghs */
	public static final String ghs_COLUMN = "GHS";

	/** COLUMNアノテーション titleCd */
	public static final String titleCd_COLUMN = "TITLE_CD";

	/** COLUMNアノテーション titleName */
	public static final String titleName_COLUMN = "TITLE_NAME";

	/** COLUMNアノテーション division */
	public static final String division_COLUMN = "DIVISION";

	/** COLUMNアノテーション subTitleName */
	public static final String subTitleName_COLUMN = "SUB_TITLE_NAME";

	/** COLUMNアノテーション value */
	public static final String value_COLUMN = "VALUE";

	/** COLUMNアノテーション nmeflg1 */
	public static final String nmeflg1_COLUMN = "NMEFLG1";

	/** COLUMNアノテーション nmeflg2 */
	public static final String nmeflg2_COLUMN = "NMEFLG2";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** GHS */
	private BigDecimal ghs;

	/** 項目コード */
	private String titleCd;

	/** 項目名称 */
	private String titleName;

	/** 区分 */
	private BigDecimal division;

	/** サブ項目名称 */
	private String subTitleName;

	/** 数値 */
	private BigDecimal value;

	/** 拡張フラグ１ */
	private BigDecimal nmeflg1;

	/** 拡張フラグ２ */
	private BigDecimal nmeflg2;

	/**
	 * divisionを取得します。
	 * @return division
	 */
	public BigDecimal getDivision() {
		return division;
	}

	/**
	 * divisionを設定します。
	 * @param division division
	 */
	public void setDivision(final BigDecimal division) {
		this.division = division;
	}

	/**
	 * ghsを取得します。
	 * @return ghs
	 */
	public BigDecimal getGhs() {
		return ghs;
	}

	/**
	 * ghsを設定します。
	 * @param ghs ghs
	 */
	public void setGhs(final BigDecimal ghs) {
		this.ghs = ghs;
	}

	/**
	 * nmeflg1を取得します。
	 * @return nmeflg1
	 */
	public BigDecimal getNmeflg1() {
		return nmeflg1;
	}

	/**
	 * nmeflg1を設定します。
	 * @param nmeflg1 nmeflg1
	 */
	public void setNmeflg1(final BigDecimal nmeflg1) {
		this.nmeflg1 = nmeflg1;
	}

	/**
	 * nmeflg2を取得します。
	 * @return nmeflg2
	 */
	public BigDecimal getNmeflg2() {
		return nmeflg2;
	}

	/**
	 * nmeflg2を設定します。
	 * @param nmeflg2 nmeflg2
	 */
	public void setNmeflg2(final BigDecimal nmeflg2) {
		this.nmeflg2 = nmeflg2;
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * subTitleNameを取得します。
	 * @return subTitleName
	 */
	public String getSubTitleName() {
		return subTitleName;
	}

	/**
	 * subTitleNameを設定します。
	 * @param subTitleName subTitleName
	 */
	public void setSubTitleName(final String subTitleName) {
		this.subTitleName = subTitleName;
	}

	/**
	 * titleCdを取得します。
	 * @return titleCd
	 */
	public String getTitleCd() {
		return titleCd;
	}

	/**
	 * titleCdを設定します。
	 * @param titleCd titleCd
	 */
	public void setTitleCd(final String titleCd) {
		this.titleCd = titleCd;
	}

	/**
	 * titleNameを取得します。
	 * @return titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * titleNameを設定します。
	 * @param titleName titleName
	 */
	public void setTitleName(final String titleName) {
		this.titleName = titleName;
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(final BigDecimal value) {
		this.value = value;
	}



}

