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
 * 処方フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class AspImportRecipeFormulaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AspImportRecipeFormulaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_FORMULA";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション lineType */
	public static final String lineType_COLUMN = "LINE_TYPE";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション costUnit */
	public static final String costUnit_COLUMN = "COST_UNIT";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション tonyu */
	public static final String tonyu_COLUMN = "TONYU";

	/** COLUMNアノテーション tonyusokudo */
	public static final String tonyusokudo_COLUMN = "TONYUSOKUDO";

	/** COLUMNアノテーション dataread */
	public static final String dataread_COLUMN = "DATAREAD";

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

	/** レシピインデックス */
	private BigDecimal recipeId;

	/** STEP_NO */
	private BigDecimal stepNo;

	/** LINE_NO */
	private BigDecimal lineNo;

	/** サブステップ№ */
	private BigDecimal seq;

	/** -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物 */
	private BigDecimal lineType;

	/** 品目コード */
	private String itemCd;

	/** 数量 */
	private BigDecimal qty;

	/** 原価 */
	private BigDecimal cost;

	/** 原価単位 */
	private String costUnit;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 投入方法|0:自動,1:手動 */
	private BigDecimal tonyu;

	/** 投入速度 */
	private BigDecimal tonyusokudo;

	/** データ読み取り|0:自動,1:手動 */
	private BigDecimal dataread;

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
	 * レシピインデックス取得
	 * @return BigDecimal レシピインデックス
	*/
	public BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * レシピインデックス設定
	 * @param recipeId レシピインデックス
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
	 * サブステップ№取得
	 * @return BigDecimal サブステップ№
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * サブステップ№設定
	 * @param seq サブステップ№
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物取得
	 * @return BigDecimal -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物
	*/
	public BigDecimal getLineType() {
		return this.lineType;
	}

	/**
	 * -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物設定
	 * @param lineType -1:原材料,1:中間品,2:回収品,3:製品,半製品,4:副生品,5:廃棄物
	*/
	public void setLineType(final BigDecimal lineType) {
		this.lineType = lineType;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 数量取得
	 * @return BigDecimal 数量
	*/
	public BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * 数量設定
	 * @param qty 数量
	*/
	public void setQty(final BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 原価取得
	 * @return BigDecimal 原価
	*/
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * 原価設定
	 * @param cost 原価
	*/
	public void setCost(final BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * 原価単位取得
	 * @return String 原価単位
	*/
	public String getCostUnit() {
		return this.costUnit;
	}

	/**
	 * 原価単位設定
	 * @param costUnit 原価単位
	*/
	public void setCostUnit(final String costUnit) {
		this.costUnit = costUnit;
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
	 * 投入方法|0:自動,1:手動取得
	 * @return BigDecimal 投入方法|0:自動,1:手動
	*/
	public BigDecimal getTonyu() {
		return this.tonyu;
	}

	/**
	 * 投入方法|0:自動,1:手動設定
	 * @param tonyu 投入方法|0:自動,1:手動
	*/
	public void setTonyu(final BigDecimal tonyu) {
		this.tonyu = tonyu;
	}

	/**
	 * 投入速度取得
	 * @return BigDecimal 投入速度
	*/
	public BigDecimal getTonyusokudo() {
		return this.tonyusokudo;
	}

	/**
	 * 投入速度設定
	 * @param tonyusokudo 投入速度
	*/
	public void setTonyusokudo(final BigDecimal tonyusokudo) {
		this.tonyusokudo = tonyusokudo;
	}

	/**
	 * データ読み取り|0:自動,1:手動取得
	 * @return BigDecimal データ読み取り|0:自動,1:手動
	*/
	public BigDecimal getDataread() {
		return this.dataread;
	}

	/**
	 * データ読み取り|0:自動,1:手動設定
	 * @param dataread データ読み取り|0:自動,1:手動
	*/
	public void setDataread(final BigDecimal dataread) {
		this.dataread = dataread;
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
