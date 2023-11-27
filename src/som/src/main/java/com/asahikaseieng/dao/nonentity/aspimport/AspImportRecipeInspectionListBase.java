/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.aspimport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 処方検査基本データ格納クラス.
 *
 * @author tosco
 */
public class AspImportRecipeInspectionListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AspImportRecipeInspectionListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_INSPECTION";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション inspectionCd */
	public static final String inspectionCd_COLUMN = "INSPECTION_CD";

	/** COLUMNアノテーション division */
	public static final String division_COLUMN = "DIVISION";

	/** COLUMNアノテーション valueType */
	public static final String valueType_COLUMN = "VALUE_TYPE";

	/** COLUMNアノテーション value1 */
	public static final String value1_COLUMN = "VALUE1";

	/** COLUMNアノテーション value2 */
	public static final String value2_COLUMN = "VALUE2";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

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

	/** STEP_NO */
	private BigDecimal stepNo;

	/** LINE_NO */
	private BigDecimal lineNo;

	/** SEQ|検査順 */
	private BigDecimal seq;

	/** INSPECTION_CD|検査コード */
	private String inspectionCd;

	/** DIVISION|区分 */
	private String division;

	/** 値1入力種類|1:数値,2:文字列 */
	private String valueType;

	/** VALUE1|値１ */
	private String value1;

	/** VALUE2|値２ */
	private String value2;

	/** CONDITION|条件 */
	private String condition;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

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
	 * STEP_NO取得
	 * @return BigDecimal STEP_NO
	*/
	public BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NO設定
	 * @param stepNo STEP_NO
	*/
	public void setStepNo(final BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * LINE_NO取得
	 * @return BigDecimal LINE_NO
	*/
	public BigDecimal getLineNo() {
		return this.lineNo;
	}

	/**
	 * LINE_NO設定
	 * @param lineNo LINE_NO
	*/
	public void setLineNo(final BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * SEQ|検査順取得
	 * @return BigDecimal SEQ|検査順
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * SEQ|検査順設定
	 * @param seq SEQ|検査順
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * INSPECTION_CD|検査コード取得
	 * @return String INSPECTION_CD|検査コード
	*/
	public String getInspectionCd() {
		return this.inspectionCd;
	}

	/**
	 * INSPECTION_CD|検査コード設定
	 * @param inspectionCd INSPECTION_CD|検査コード
	*/
	public void setInspectionCd(final String inspectionCd) {
		this.inspectionCd = inspectionCd;
	}

	/**
	 * DIVISION|区分取得
	 * @return String DIVISION|区分
	*/
	public String getDivision() {
		return this.division;
	}

	/**
	 * DIVISION|区分設定
	 * @param division DIVISION|区分
	*/
	public void setDivision(final String division) {
		this.division = division;
	}

	/**
	 * 値1入力種類|1:数値,2:文字列取得
	 * @return String 値1入力種類|1:数値,2:文字列
	*/
	public String getValueType() {
		return this.valueType;
	}

	/**
	 * 値1入力種類|1:数値,2:文字列設定
	 * @param valueType 値1入力種類|1:数値,2:文字列
	*/
	public void setValueType(final String valueType) {
		this.valueType = valueType;
	}

	/**
	 * VALUE1|値１取得
	 * @return String VALUE1|値１
	*/
	public String getValue1() {
		return this.value1;
	}

	/**
	 * VALUE1|値１設定
	 * @param value1 VALUE1|値１
	*/
	public void setValue1(final String value1) {
		this.value1 = value1;
	}

	/**
	 * VALUE2|値２取得
	 * @return String VALUE2|値２
	*/
	public String getValue2() {
		return this.value2;
	}

	/**
	 * VALUE2|値２設定
	 * @param value2 VALUE2|値２
	*/
	public void setValue2(final String value2) {
		this.value2 = value2;
	}

	/**
	 * CONDITION|条件取得
	 * @return String CONDITION|条件
	*/
	public String getCondition() {
		return this.condition;
	}

	/**
	 * CONDITION|条件設定
	 * @param condition CONDITION|条件
	*/
	public void setCondition(final String condition) {
		this.condition = condition;
	}

	/**
	 * 備考取得
	 * @return String 備考
	*/
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定
	 * @param remark 備考
	*/
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	*/
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 注釈設定
	 * @param notes 注釈
	*/
	public void setNotes(final String notes) {
		this.notes = notes;
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
