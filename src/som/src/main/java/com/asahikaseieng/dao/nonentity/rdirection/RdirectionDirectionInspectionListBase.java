/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 製造指図検査データ格納クラス.
 *
 * @author tosco
 */
public class RdirectionDirectionInspectionListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionInspectionListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "DIRECTION_INSPECTION";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション directionStatus */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

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

	/** COLUMNアノテーション resultValue1 */
	public static final String resultValue1_COLUMN = "RESULT_VALUE1";

	/** COLUMNアノテーション value2 */
	public static final String value2_COLUMN = "VALUE2";

	/** COLUMNアノテーション resultValue2 */
	public static final String resultValue2_COLUMN = "RESULT_VALUE2";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	//
	// インスタンスフィールド
	//

	/** 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** 指図ステータス */
	private String directionStatus;

	/** ステップNO. */
	private BigDecimal stepNo;

	/** 行NO. */
	private BigDecimal lineNo;

	/** 検査順 */
	private BigDecimal seq;

	/** 検査コード */
	private String inspectionCd;

	/** 検査区分|規格,指標,参照 */
	private String division;

	/** 値１タイプ|1:数値,2:文字列 */
	private String valueType;

	/** 値１ */
	private String value1;

	/** 実績値１ */
	private String resultValue1;

	/** 値２ */
	private String value2;

	/** 実績値２ */
	private String resultValue2;

	/** 条件区分|1:以下,2:以上,3:未満,4:超,5:付近 */
	private String condition;

	/** 注釈 */
	private String notes;

	/** 備考|2007/12/10属性追加byTada */
	private String remark;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図取得
	 * @return BigDecimal 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図
	*/
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図設定
	 * @param directionDivision 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図
	*/
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 指図番号取得
	 * @return String 指図番号
	*/
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 指図番号設定
	 * @param directionNo 指図番号
	*/
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 指図ステータスを取得します。
	 * @return directionStatus
	 */
	public String getDirectionStatus() {
		return directionStatus;
	}

	/**
	 * 指図ステータスを設定します。
	 * @param directionStatus directionStatus
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * ステップNO.取得
	 * @return BigDecimal ステップNO.
	*/
	public BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * ステップNO.設定
	 * @param stepNo ステップNO.
	*/
	public void setStepNo(final BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 行NO.取得
	 * @return BigDecimal 行NO.
	*/
	public BigDecimal getLineNo() {
		return this.lineNo;
	}

	/**
	 * 行NO.設定
	 * @param lineNo 行NO.
	*/
	public void setLineNo(final BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * 検査順取得
	 * @return BigDecimal 検査順
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * 検査順設定
	 * @param seq 検査順
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * 検査コード取得
	 * @return String 検査コード
	*/
	public String getInspectionCd() {
		return this.inspectionCd;
	}

	/**
	 * 検査コード設定
	 * @param inspectionCd 検査コード
	*/
	public void setInspectionCd(final String inspectionCd) {
		this.inspectionCd = inspectionCd;
	}

	/**
	 * 検査区分|規格,指標,参照取得
	 * @return String 検査区分|規格,指標,参照
	*/
	public String getDivision() {
		return this.division;
	}

	/**
	 * 検査区分|規格,指標,参照設定
	 * @param division 検査区分|規格,指標,参照
	*/
	public void setDivision(final String division) {
		this.division = division;
	}

	/**
	 * 値１タイプ|1:数値,2:文字列取得
	 * @return String 値１タイプ|1:数値,2:文字列
	*/
	public String getValueType() {
		return this.valueType;
	}

	/**
	 * 値１タイプ|1:数値,2:文字列設定
	 * @param valueType 値１タイプ|1:数値,2:文字列
	*/
	public void setValueType(final String valueType) {
		this.valueType = valueType;
	}

	/**
	 * 値１取得
	 * @return String 値１
	*/
	public String getValue1() {
		return this.value1;
	}

	/**
	 * 値１設定
	 * @param value1 値１
	*/
	public void setValue1(final String value1) {
		this.value1 = value1;
	}

	/**
	 * 実績値１取得
	 * @return String 実績値１
	*/
	public String getResultValue1() {
		return this.resultValue1;
	}

	/**
	 * 実績値１設定
	 * @param resultValue1 実績値１
	*/
	public void setResultValue1(final String resultValue1) {
		this.resultValue1 = resultValue1;
	}

	/**
	 * 値２取得
	 * @return String 値２
	*/
	public String getValue2() {
		return this.value2;
	}

	/**
	 * 値２設定
	 * @param value2 値２
	*/
	public void setValue2(final String value2) {
		this.value2 = value2;
	}

	/**
	 * 実績値２取得
	 * @return String 実績値２
	*/
	public String getResultValue2() {
		return this.resultValue2;
	}

	/**
	 * 実績値２設定
	 * @param resultValue2 実績値２
	*/
	public void setResultValue2(final String resultValue2) {
		this.resultValue2 = resultValue2;
	}

	/**
	 * 条件区分|1:以下,2:以上,3:未満,4:超,5:付近取得
	 * @return String 条件区分|1:以下,2:以上,3:未満,4:超,5:付近
	*/
	public String getCondition() {
		return this.condition;
	}

	/**
	 * 条件区分|1:以下,2:以上,3:未満,4:超,5:付近設定
	 * @param condition 条件区分|1:以下,2:以上,3:未満,4:超,5:付近
	*/
	public void setCondition(final String condition) {
		this.condition = condition;
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
	 * 備考|2007/12/10属性追加byTada取得
	 * @return String 備考|2007/12/10属性追加byTada
	*/
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考|2007/12/10属性追加byTada設定
	 * @param remark 備考|2007/12/10属性追加byTada
	*/
	public void setRemark(final String remark) {
		this.remark = remark;
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
