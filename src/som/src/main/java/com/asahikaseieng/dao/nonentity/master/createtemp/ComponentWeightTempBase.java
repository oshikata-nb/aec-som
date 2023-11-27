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
 * 成分重量Temp（原処方情報からの取得）
 * @author hongyo
 */
public class ComponentWeightTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentWeightTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "COMPONENT_WEIGHT_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション componentName */
	public static final String componentName_COLUMN = "COMPONENT_NAME";

	/** COLUMNアノテーション value */
	public static final String value_COLUMN = "VALUE";

	/** COLUMNアノテーション indicateValue */
	public static final String indicateValue_COLUMN = "INDICATE_VALUE";

	/** COLUMNアノテーション indicateDivision */
	public static final String indicateDivision_COLUMN = "INDICATE_DIVISION";

	//
	// インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** 成分コード */
	private String componentCd;

	/** 成分名称 */
	private String componentName;

	/** 重量 */
	private BigDecimal value;

	/** 表示数値 */
	private BigDecimal indicateValue;

	/** 表示区分 */
	private BigDecimal indicateDivision;

	//
	// インスタンスメソッド
	//

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
	 * componentNameを取得します。
	 * @return componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * componentNameを設定します。
	 * @param componentName componentName
	 */
	public void setComponentName(final String componentName) {
		this.componentName = componentName;
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
	 * indicateValueを取得します。
	 * @return indicateValue
	 */
	public BigDecimal getIndicateValue() {
		return indicateValue;
	}

	/**
	 * indicateValueを設定します。
	 * @param indicateValue indicateValue
	 */
	public void setIndicateValue(final BigDecimal indicateValue) {
		this.indicateValue = indicateValue;
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
