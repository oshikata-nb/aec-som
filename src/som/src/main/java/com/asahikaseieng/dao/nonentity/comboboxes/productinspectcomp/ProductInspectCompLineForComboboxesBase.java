/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.productinspectcomp;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製品検査完了入力－生産ラインコンボボックス用データ.
 * @author tosco
 */
public class ProductInspectCompLineForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductInspectCompLineForComboboxesBase() {
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
	 * 生産ライン取得
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 生産ライン名称取得
	*/
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * 生産ライン名称設定
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