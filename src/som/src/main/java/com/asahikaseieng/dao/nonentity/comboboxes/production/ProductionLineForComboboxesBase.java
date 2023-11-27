/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.production;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 生産ライン_表示用データ格納クラス.
 *
 * @author tosco
 */
public class ProductionLineForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductionLineForComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "LINE";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updaterCd */
	public static final String updaterCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 生産ライン */
	private String productionLine;

	/** 生産ライン名称 */
	private String productionLineName;

	/** 登録日付 */
	private Timestamp inputDate;

	/** 登録者 */
	private String inputorCd;

	/** 更新日付 */
	private Timestamp updateDate;

	/** 更新者 */
	private String updaterCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 生産ライン取得
	 * @return String 生産ライン
	*/
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 生産ライン設定
	 * @param productionLine 生産ライン
	*/
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 生産ライン名称取得
	 * @return String 生産ライン名称
	*/
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * 生産ライン名称設定
	 * @param productionLineName 生産ライン名称
	*/
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 登録日付取得
	 * @return Timestamp 登録日付
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日付設定
	 * @param inputDate 登録日付
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得
	 * @return String 登録者
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者設定
	 * @param inputorCd 登録者
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日付取得
	 * @return Timestamp 更新日付
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日付設定
	 * @param updateDate 更新日付
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得
	 * @return String 更新者
	*/
	public String getUpdaterCd() {
		return this.updaterCd;
	}

	/**
	 * 更新者設定
	 * @param updaterCd 更新者
	*/
	public void setUpdaterCd(final String updaterCd) {
		this.updaterCd = updaterCd;
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
