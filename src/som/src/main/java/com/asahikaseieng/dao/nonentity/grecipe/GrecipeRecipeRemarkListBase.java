/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 処方その他データ格納クラス.
 *
 * @author tosco
 */
public class GrecipeRecipeRemarkListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GrecipeRecipeRemarkListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_REMARK";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション generalRecipeRemark */
	public static final String generalRecipeRemark_COLUMN = "GENERAL_RECIPE_REMARK";

	/** COLUMNアノテーション masterRecipeRemark */
	public static final String masterRecipeRemark_COLUMN = "MASTER_RECIPE_REMARK";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/** RECIPE_ID|レシピインデックス */
	private BigDecimal recipeId;

	/** 原処方備考 */
	private String generalRecipeRemark;

	/** 基本処方備考 */
	private String masterRecipeRemark;

	/** 登録者ID */
	private String inputorCd;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 更新者ID */
	private String updatorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * RECIPE_ID|レシピインデックス取得
	 * @return BigDecimal RECIPE_ID|レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * RECIPE_ID|レシピインデックス設定
	 * @param recipeId RECIPE_ID|レシピインデックス
	*/
	public void setRecipeId(final BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * 原処方備考取得
	 * @return String 原処方備考
	*/
	public String getGeneralRecipeRemark() {
		return this.generalRecipeRemark;
	}

	/**
	 * 原処方備考設定
	 * @param generalRecipeRemark 原処方備考
	*/
	public void setGeneralRecipeRemark(final String generalRecipeRemark) {
		this.generalRecipeRemark = generalRecipeRemark;
	}

	/**
	 * 基本処方備考取得
	 * @return String 基本処方備考
	*/
	public String getMasterRecipeRemark() {
		return this.masterRecipeRemark;
	}

	/**
	 * 基本処方備考設定
	 * @param masterRecipeRemark 基本処方備考
	*/
	public void setMasterRecipeRemark(final String masterRecipeRemark) {
		this.masterRecipeRemark = masterRecipeRemark;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
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
