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
 * アレルギー情報Temp（原処方情報からの取得）
 * @author hongyo
 */
public class AllergyInfoTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AllergyInfoTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "ALLERGY_INFO_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション allergyCondition */
	public static final String allergyCondition_COLUMN = "ALLERGY_CONDITION";

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション indicateDivision */
	public static final String indicateDivision_COLUMN = "INDICATE_DIVISION";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** アレルギー有無 */
	private BigDecimal allergyCondition;

	/** 成分コード */
	private String componentCd;

	/** 表示区分 */
	private BigDecimal indicateDivision;

	//
	// インスタンスメソッド
	//

	/**
	 * allergyConditionを取得します。
	 * @return allergyCondition
	 */
	public BigDecimal getAllergyCondition() {
		return allergyCondition;
	}

	/**
	 * allergyConditionを設定します。
	 * @param allergyCondition allergyCondition
	 */
	public void setAllergyCondition(final BigDecimal allergyCondition) {
		this.allergyCondition = allergyCondition;
	}

	/**
	 * componentCdを取得します。
	 * @return componentCd
	 */
	public String getComponentCd() {
		return componentCd;
	}

	/**
	 * componentCdを設定します。
	 * @param componentCd componentCd
	 */
	public void setComponentCd(final String componentCd) {
		this.componentCd = componentCd;
	}

	/**
	 * indicateDivisionを取得します。
	 * @return indicateDivision
	 */
	public BigDecimal getIndicateDivision() {
		return indicateDivision;
	}

	/**
	 * indicateDivisionを設定します。
	 * @param indicateDivision indicateDivision
	 */
	public void setIndicateDivision(final BigDecimal indicateDivision) {
		this.indicateDivision = indicateDivision;
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
}
