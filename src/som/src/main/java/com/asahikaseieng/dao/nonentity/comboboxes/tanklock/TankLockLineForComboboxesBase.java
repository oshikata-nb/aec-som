/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.tanklock;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 調合タンク底弁インターロック解除/再設定－生産ラインコンボボックス用データ.
 * @author tosco
 */
public class TankLockLineForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TankLockLineForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "LINE";

	/** COLUMNアノテーション carry_cd */
	public static final String carryCd_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション carry_name1 */
	public static final String carryName1_COLUMN = "PRODUCTION_LINE_NAME";

	//
	// インスタンスフィールド
	//

	/** 生産ライン */
	private String productionLine;

	/** 生産ライン名称 */
	private String productionLineName;

	//
	// インスタンスメソッド
	//

	/**
	 * 生産ライン取得	 * @return String 生産ライン	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定	 * @param productionLine 生産ライン	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 生産ライン名称取得	 * @return String 生産ライン名称
	*/
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * 生産ライン名称設定	 * @param productionLineName 生産ライン名称
	*/
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
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
