/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 包装指図－製造指図フォーミュラデータ格納クラス.
 *
 * @author tosco
 */
public class PkgDirectionDirectionFormulaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionFormulaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "DIRECTION_FORMULA";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

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

	/** COLUMNアノテーション tonyu */
	public static final String tonyu_COLUMN = "TONYU";

	/** COLUMNアノテーション dataread */
	public static final String dataread_COLUMN = "DATAREAD";

	/** COLUMNアノテーション tonyusokudo */
	public static final String tonyusokudo_COLUMN = "TONYUSOKUDO";

	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション stockpdQty */
	public static final String stockpdQty_COLUMN = "STOCKPD_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション sampleQty */
	public static final String sampleQty_COLUMN = "SAMPLE_QTY";

	/** COLUMNアノテーション lossQty */
	public static final String lossQty_COLUMN = "LOSS_QTY";

	/** COLUMNアノテーション defectQty */
	public static final String defectQty_COLUMN = "DEFECT_QTY";

	/** COLUMNアノテーション adjustQty */
	public static final String adjustQty_COLUMN = "ADJUST_QTY";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション stepCondition */
	public static final String stepCondition_COLUMN = "STEP_CONDITION";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション nextLocationCd */
	public static final String nextLocationCd_COLUMN = "NEXT_LOCATION_CD";

	/** COLUMNアノテーション nextAfterLocationCd */
	public static final String nextAfterLocationCd_COLUMN = "NEXT_AFTER_LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション manufacturerLotNo */
	public static final String manufacturerLotNo_COLUMN = "MANUFACTURER_LOT_NO";

	/** COLUMNアノテーション fillQty */
	public static final String fillQty_COLUMN = "FILL_QTY";

	/** COLUMNアノテーション fillResultQty */
	public static final String fillResultQty_COLUMN = "FILL_RESULT_QTY";

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

	/** 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** ステップNO. */
	private BigDecimal stepNo;

	/** 行NO. */
	private BigDecimal lineNo;

	/** 投入順 */
	private BigDecimal seq;

	/** 品目タイプ|-1:原材料,1:中間品,2:回収品,3:製品・半製品,4:副生品,5:廃棄物 */
	private BigDecimal lineType;

	/** 品目コード */
	private String itemCd;

	/** 投入方法 */
	private BigDecimal tonyu;

	/** データ読取 */
	private BigDecimal dataread;

	/** 投入速度 */
	private BigDecimal tonyusokudo;

	/** 数量 ※2007/12/10属性変更BYTADA */
	private BigDecimal qty;

	/** 在庫引落量 */
	private BigDecimal stockpdQty;

	/** 実績数量 */
	private BigDecimal resultQty;

	/** サンプル */
	private BigDecimal sampleQty;

	/** ロス数量 */
	private BigDecimal lossQty;

	/** 不良 */
	private BigDecimal defectQty;

	/** 調整数量 */
	private BigDecimal adjustQty;

	/** 原価 ※2007/12/10属性変更BYTADA */
	private BigDecimal cost;

	/** 条件 */
	private String stepCondition;

	/** 注釈 */
	private String notes;

	/** ロケーションコード（第1タンクNo　兼　実績タンクNo） */
	private String locationCd;

	/** 第2タンクNo */
	private String nextLocationCd;

	/** 第3タンクNo */
	private String nextAfterLocationCd;

	/** 入荷ロット番号 */
	private String lotNo;

	/** メーカーロット番号 */
	private String manufacturerLotNo;

	/** 充填予定数 */
	private BigDecimal fillQty;

	/** 充填実績数 */
	private BigDecimal fillResultQty;

	/** 備考 */
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
	 * 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図取得
	 * @return BigDecimal 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図
	*/
	public BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図設定
	 * @param directionDivision 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図
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
	 * 投入順取得
	 * @return BigDecimal 投入順
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * 投入順設定
	 * @param seq 投入順
	*/
	public void setSeq(final BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * 品目タイプ|-1:原材料,1:中間品,2:回収品,3:製品・半製品,4:副生品,5:廃棄物取得
	 * @return BigDecimal 品目タイプ|-1:原材料,1:中間品,2:回収品,3:製品・半製品,4:副生品,5:廃棄物
	*/
	public BigDecimal getLineType() {
		return this.lineType;
	}

	/**
	 * 品目タイプ|-1:原材料,1:中間品,2:回収品,3:製品・半製品,4:副生品,5:廃棄物設定
	 * @param lineType 品目タイプ|-1:原材料,1:中間品,2:回収品,3:製品・半製品,4:副生品,5:廃棄物
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
	 * 投入方法取得
	 * @return BigDecimal 投入方法
	*/
	public BigDecimal getTonyu() {
		return this.tonyu;
	}

	/**
	 * 投入方法設定
	 * @param tonyu 投入方法
	*/
	public void setTonyu(final BigDecimal tonyu) {
		this.tonyu = tonyu;
	}

	/**
	 * データ読取取得
	 * @return BigDecimal データ読取
	*/
	public BigDecimal getDataread() {
		return this.dataread;
	}

	/**
	 * データ読取設定
	 * @param dataread データ読取
	*/
	public void setDataread(final BigDecimal dataread) {
		this.dataread = dataread;
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
	 * 在庫引落量取得
	 * @return BigDecimal 在庫引落量
	*/
	public BigDecimal getStockpdQty() {
		return this.stockpdQty;
	}

	/**
	 * 在庫引落量設定
	 * @param stockpdQty 在庫引落量
	*/
	public void setStockpdQty(final BigDecimal stockpdQty) {
		this.stockpdQty = stockpdQty;
	}

	/**
	 * 実績数量取得
	 * @return BigDecimal 実績数量
	*/
	public BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * 実績数量設定
	 * @param resultQty 実績数量
	*/
	public void setResultQty(final BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * サンプル取得
	 * @return BigDecimal サンプル
	*/
	public BigDecimal getSampleQty() {
		return this.sampleQty;
	}

	/**
	 * サンプル設定
	 * @param sampleQty サンプル
	*/
	public void setSampleQty(final BigDecimal sampleQty) {
		this.sampleQty = sampleQty;
	}

	/**
	 * ロス数量取得
	 * @return BigDecimal ロス数量
	*/
	public BigDecimal getLossQty() {
		return this.lossQty;
	}

	/**
	 * ロス数量設定
	 * @param lossQty ロス数量
	*/
	public void setLossQty(final BigDecimal lossQty) {
		this.lossQty = lossQty;
	}

	/**
	 * 不良取得
	 * @return BigDecimal 不良
	*/
	public BigDecimal getDefectQty() {
		return this.defectQty;
	}

	/**
	 * 不良設定
	 * @param defectQty 不良
	*/
	public void setDefectQty(final BigDecimal defectQty) {
		this.defectQty = defectQty;
	}

	/**
	 * 調整数量取得
	 * @return BigDecimal 調整数量
	*/
	public BigDecimal getAdjustQty() {
		return this.adjustQty;
	}

	/**
	 * 調整数量設定
	 * @param adjustQty 調整数量
	*/
	public void setAdjustQty(final BigDecimal adjustQty) {
		this.adjustQty = adjustQty;
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
	 * 条件取得
	 * @return String 条件
	*/
	public String getStepCondition() {
		return this.stepCondition;
	}

	/**
	 * 条件設定
	 * @param stepCondition 条件
	*/
	public void setStepCondition(final String stepCondition) {
		this.stepCondition = stepCondition;
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
	 * ロケーションコード（第1タンクNo　兼　実績タンクNo）取得
	 * @return String ロケーションコード（第1タンクNo　兼　実績タンクNo）
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * ロケーションコード（第1タンクNo　兼　実績タンクNo）設定
	 * @param locationCd ロケーションコード（第1タンクNo　兼　実績タンクNo）
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 第2タンクNo取得
	 * @return String 第2タンクNo
	*/
	public String getNextLocationCd() {
		return this.nextLocationCd;
	}

	/**
	 * 第2タンクNo設定
	 * @param nextLocationCd 第2タンクNo
	*/
	public void setNextLocationCd(final String nextLocationCd) {
		this.nextLocationCd = nextLocationCd;
	}

	/**
	 * 第3タンクNo取得
	 * @return String 第3タンクNo
	*/
	public String getNextAfterLocationCd() {
		return this.nextAfterLocationCd;
	}

	/**
	 * 第3タンクNo設定
	 * @param nextAfterLocationCd 第3タンクNo
	*/
	public void setNextAfterLocationCd(final String nextAfterLocationCd) {
		this.nextAfterLocationCd = nextAfterLocationCd;
	}

	/**
	 * 入荷ロット番号取得
	 * @return String 入荷ロット番号
	*/
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * 入荷ロット番号設定
	 * @param lotNo 入荷ロット番号
	*/
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * メーカーロット番号取得
	 * @return String メーカーロット番号
	*/
	public String getManufacturerLotNo() {
		return this.manufacturerLotNo;
	}

	/**
	 * メーカーロット番号設定
	 * @param manufacturerLotNo メーカーロット番号
	*/
	public void setManufacturerLotNo(final String manufacturerLotNo) {
		this.manufacturerLotNo = manufacturerLotNo;
	}

	/**
	 * 充填予定数取得
	 * @return BigDecimal 充填予定数
	*/
	public BigDecimal getFillQty() {
		return this.fillQty;
	}

	/**
	 * 充填予定数設定
	 * @param fillQty 充填予定数
	*/
	public void setFillQty(final BigDecimal fillQty) {
		this.fillQty = fillQty;
	}

	/**
	 * 充填実績数取得
	 * @return BigDecimal 充填実績数
	*/
	public BigDecimal getFillResultQty() {
		return this.fillResultQty;
	}

	/**
	 * 充填実績数設定
	 * @param fillResultQty 充填実績数
	*/
	public void setFillResultQty(final BigDecimal fillResultQty) {
		this.fillResultQty = fillResultQty;
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
