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
 * 第57条有機溶剤Temp（原処方情報からの取得）
 * @author hongyo
 */
public class SolventTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SolventTempBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "SOLVENT_TEMP";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション componentName */
	public static final String componentName_COLUMN = "COMPONENT_NAME";

	/** COLUMNアノテーション value */
	public static final String value_COLUMN = "VALUE";

	/** COLUMNアノテーション threshold */
	public static final String threshold_COLUMN = "THRESHOLD";

	//
	//	 インスタンスフィールド
	//

	/** ＳＥＱ */
	private BigDecimal seq;

	/** 成分コード */
	private String componentCd;

	/** 成分名称 */
	private String componentName;

	/** 数値 */
	private BigDecimal value;

	/** 閾値 */
	private BigDecimal threshold;

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
	 * thresholdを取得します。
	 * @return threshold
	 */
	public BigDecimal getThreshold() {
		return threshold;
	}

	/**
	 * thresholdを設定します。
	 * @param threshold threshold
	 */
	public void setThreshold(final BigDecimal threshold) {
		this.threshold = threshold;
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
