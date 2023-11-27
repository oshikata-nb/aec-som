/*
 * Created on 2009/02/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 製造指図一覧－指図書発行-計装インターフェイス用データ格納クラス.
 *
 * @author tosco
 */
public class DirectionProcedureFormulaListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedureFormulaListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
//	public static final String TABLE = "DIRECTION_PROCEDURE";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション seq */
	public static final String procedureSeq_COLUMN = "PROCEDURE_SEQ";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark */
	public static final String procedureRemark_COLUMN = "PROCEDURE_REMARK";

	/** COLUMNアノテーション notes */
	public static final String procedureNotes_COLUMN = "PROCEDURE_NOTES";

	/** COLUMNアノテーション leadtime */
	public static final String leadtime_COLUMN = "LEADTIME";

	/** COLUMNアノテーション startDate */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション resultSdate */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション conditionTemp */
	public static final String conditionTemp_COLUMN = "CONDITION_TEMP";

	/** COLUMNアノテーション conditionTime */
	public static final String conditionTime_COLUMN = "CONDITION_TIME";

	/** COLUMNアノテーション stirSpeed1 */
	public static final String stirSpeed1_COLUMN = "STIR_SPEED1";

	/** COLUMNアノテーション stirSpeed2 */
	public static final String stirSpeed2_COLUMN = "STIR_SPEED2";

	/** COLUMNアノテーション waterWeight */
	public static final String waterWeight_COLUMN = "WATER_WEIGHT";

	/** COLUMNアノテーション mainStream */
	public static final String mainStream_COLUMN = "MAIN_STREAM";

	/** COLUMNアノテーション ph */
	public static final String ph_COLUMN = "PH";

	/** COLUMNアノテーション fillingQty */
	public static final String fillingQty_COLUMN = "FILLING_QTY";

	/** COLUMNアノテーション fillingUnit */
	public static final String fillingUnit_COLUMN = "FILLING_UNIT";

	/** COLUMNアノテーション net */
	public static final String net_COLUMN = "NET";

	/** COLUMNアノテーション weightMin */
	public static final String weightMin_COLUMN = "WEIGHT_MIN";

	/** COLUMNアノテーション weightMax */
	public static final String weightMax_COLUMN = "WEIGHT_MAX";

	/** COLUMNアノテーション filter */
	public static final String filter_COLUMN = "FILTER";

	/** COLUMNアノテーション autoCheckerMin */
	public static final String autoCheckerMin_COLUMN = "AUTO_CHECKER_MIN";

	/** COLUMNアノテーション autoCheckerMax */
	public static final String autoCheckerMax_COLUMN = "AUTO_CHECKER_MAX";

	/** COLUMNアノテーション grossCheckerMin */
	public static final String grossCheckerMin_COLUMN = "GROSS_CHECKER_MIN";

	/** COLUMNアノテーション grossCheckerMax */
	public static final String grossCheckerMax_COLUMN = "GROSS_CHECKER_MAX";

	/** COLUMNアノテーション openingTorqueMin */
	public static final String openingTorqueMin_COLUMN = "OPENING_TORQUE_MIN";

	/** COLUMNアノテーション openingTorqueMax */
	public static final String openingTorqueMax_COLUMN = "OPENING_TORQUE_MAX";

	/** COLUMNアノテーション hotAirPresetTemp */
	public static final String hotAirPresetTemp_COLUMN = "HOT_AIR_PRESET_TEMP";

	/** COLUMNアノテーション hotAirPressure */
	public static final String hotAirPressure_COLUMN = "HOT_AIR_PRESSURE";

	/** COLUMNアノテーション mesh */
	public static final String mesh_COLUMN = "MESH";

	/** COLUMNアノテーション autoCheckerAve */
	public static final String autoCheckerAve_COLUMN = "AUTO_CHECKER_AVE";

	/** COLUMNアノテーション grossCheckerAve */
	public static final String grossCheckerAve_COLUMN = "GROSS_CHECKER_AVE";

	/** COLUMNアノテーション closingTorqueMin */
	public static final String closingTorqueMin_COLUMN = "CLOSING_TORQUE_MIN";

	/** COLUMNアノテーション closingTorqueMax */
	public static final String closingTorqueMax_COLUMN = "CLOSING_TORQUE_MAX";

	/** COLUMNアノテーション torquePressure */
	public static final String torquePressure_COLUMN = "TORQUE_PRESSURE";

	/** COLUMNアノテーション airPressure */
	public static final String airPressure_COLUMN = "AIR_PRESSURE";

	/** COLUMNアノテーション vcloseTime */
	public static final String vcloseTime_COLUMN = "VCLOSE_TIME";

	/** COLUMNアノテーション firstHeatSeal */
	public static final String firstHeatSeal_COLUMN = "FIRST_HEAT_SEAL";

	/** COLUMNアノテーション secondHeatSeal */
	public static final String secondHeatSeal_COLUMN = "SECOND_HEAT_SEAL";

	/** COLUMNアノテーション inputDate */
	public static final String procedureInputDate_COLUMN = "PROCEDURE_INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String procedureInputorCd_COLUMN = "PROCEDURE_INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String procedureUpdateDate_COLUMN = "PROCEDURE_UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String procedureUpdatorCd_COLUMN = "PROCEDURE_UPDATOR_CD";

//Formula>>>>>
	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq */
	public static final String formulaSeq_COLUMN = "FORMULA_SEQ";

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
	public static final String formulaNotes_COLUMN = "FORMULA_NOTES";

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
	public static final String formulaRemark_COLUMN = "FORMULA_REMARK";

	/** COLUMNアノテーション inputDate */
	public static final String formulaInputDate_COLUMN = "FORMULA_INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String formulaInputorCd_COLUMN = "FORMULA_INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String formulaUpdateDate_COLUMN = "FORMULA_UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String formulaUpdatorCd_COLUMN = "FORMULA_UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 指図区分|0:バッチ指図,1:充填・包装指図,3:詰替・貼替指図 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** ステップNO. */
	private BigDecimal stepNo;

	/** 投入順 */
	private BigDecimal procedureSeq;

	/** 工程コード */
	private String operationCd;

	/** 工程条件:フリー入力 */
	private String condition;

	/** 備考 */
	private String procedureRemark;

	/** 注釈 */
	private String procedureNotes;

	/** リードタイム（分） */
	private BigDecimal leadtime;

	/** 開始日時|2007/12/10属性名、カラム名変更byTada */
	private Timestamp startDate;

	/** 終了日時|2007/12/10属性名、カラム名変更byTada */
	private Timestamp endDate;

	/** 開始実績日時 */
	private Timestamp resultSdate;

	/** 終了実績日時 */
	private Timestamp resultEdate;

	/** 温度 */
	private BigDecimal conditionTemp;

	/** 時間 */
	private BigDecimal conditionTime;

	/** 攪拌速度1 */
	private BigDecimal stirSpeed1;

	/** 攪拌速度2 */
	private BigDecimal stirSpeed2;

	/** 洗浄水絶対重量 */
	private BigDecimal waterWeight;

	/** 本流/予備溶解 */
	private BigDecimal mainStream;

	/** ｐＨ */
	private BigDecimal ph;

	/** 充填予定数量 */
	private BigDecimal fillingQty;

	/** 充填単位 */
	private String fillingUnit;

	/** 正味質量 */
	private BigDecimal net;

	/** 量目管理幅最小 */
	private BigDecimal weightMin;

	/** 量目管理幅最大 */
	private BigDecimal weightMax;

	/** 濾過用フィルター */
	private BigDecimal filter;

	/** オートチェッカー最小 */
	private BigDecimal autoCheckerMin;

	/** オートチェッカー最大 */
	private BigDecimal autoCheckerMax;

	/** グロスチェッカー最小 */
	private BigDecimal grossCheckerMin;

	/** グロスチェッカー最大 */
	private BigDecimal grossCheckerMax;

	/** 開きトルク最小 */
	private BigDecimal openingTorqueMin;

	/** 開きトルク最大 */
	private BigDecimal openingTorqueMax;

	/** ホットエアー設定温度 */
	private BigDecimal hotAirPresetTemp;

	/** ホットエアー吹き出し圧力 */
	private BigDecimal hotAirPressure;

	/** 濾過用メッシュ */
	private BigDecimal mesh;

	/** オートチェッカー中心 */
	private BigDecimal autoCheckerAve;

	/** グロスチェッカー中心 */
	private BigDecimal grossCheckerAve;

	/** 閉めトルク最小 */
	private BigDecimal closingTorqueMin;

	/** 閉めトルク最大 */
	private BigDecimal closingTorqueMax;

	/** トルク圧 */
	private BigDecimal torquePressure;

	/** エアー圧 */
	private BigDecimal airPressure;

	/** 巻閉め時間 */
	private BigDecimal vcloseTime;

	/** 第一ヒートシール設定温度 */
	private BigDecimal firstHeatSeal;

	/** 第二ヒートシール設定温度 */
	private BigDecimal secondHeatSeal;

	/** 登録日時 */
	private Timestamp procedureInputDate;

	/** 登録者ID */
	private String procedureInputorCd;

	/** 更新日時 */
	private Timestamp procedureUpdateDate;

	/** 更新者ID */
	private String procedureUpdatorCd;

//Formula
	/** 行NO. */
	private BigDecimal lineNo;

	/** 投入順 */
	private BigDecimal formulaSeq;

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
	private String formulaNotes;

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
	private String formulaRemark;

	/** 登録日時 */
	private Timestamp formulaInputDate;

	/** 登録者ID */
	private String formulaInputorCd;

	/** 更新日時 */
	private Timestamp formulaUpdateDate;

	/** 更新者ID */
	private String formulaUpdatorCd;


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
	 * 投入順取得
	 * @return BigDecimal 投入順
	*/
	public BigDecimal getProcedureSeq() {
		return this.procedureSeq;
	}

	/**
	 * 投入順設定
	 * @param procedureSeq 投入順
	*/
	public void setProcedureSeq(final BigDecimal procedureSeq) {
		this.procedureSeq = procedureSeq;
	}

	/**
	 * 工程コード取得
	 * @return String 工程コード
	*/
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * 工程コード設定
	 * @param operationCd 工程コード
	*/
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程条件:フリー入力取得
	 * @return String 工程条件:フリー入力
	*/
	public String getCondition() {
		return this.condition;
	}

	/**
	 * 工程条件:フリー入力設定
	 * @param condition 工程条件:フリー入力
	*/
	public void setCondition(final String condition) {
		this.condition = condition;
	}

	/**
	 * 備考取得
	 * @return String 備考
	*/
	public String getProcedureRemark() {
		return this.procedureRemark;
	}

	/**
	 * 備考設定
	 * @param procedureRemark 備考
	*/
	public void setProcedureRemark(final String procedureRemark) {
		this.procedureRemark = procedureRemark;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	*/
	public String getProcedureNotes() {
		return this.procedureNotes;
	}

	/**
	 * 注釈設定
	 * @param procedureNotes 注釈
	*/
	public void setProcedureNotes(final String procedureNotes) {
		this.procedureNotes = procedureNotes;
	}

	/**
	 * リードタイム（分）取得
	 * @return BigDecimal リードタイム（分）
	*/
	public BigDecimal getLeadtime() {
		return this.leadtime;
	}

	/**
	 * リードタイム（分）設定
	 * @param leadtime リードタイム（分）
	*/
	public void setLeadtime(final BigDecimal leadtime) {
		this.leadtime = leadtime;
	}

	/**
	 * 開始日時|2007/12/10属性名、カラム名変更byTada取得
	 * @return Timestamp 開始日時|2007/12/10属性名、カラム名変更byTada
	*/
	public Timestamp getStartDate() {
		return this.startDate;
	}

	/**
	 * 開始日時|2007/12/10属性名、カラム名変更byTada設定
	 * @param startDate 開始日時|2007/12/10属性名、カラム名変更byTada
	*/
	public void setStartDate(final Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * 終了日時|2007/12/10属性名、カラム名変更byTada取得
	 * @return Timestamp 終了日時|2007/12/10属性名、カラム名変更byTada
	*/
	public Timestamp getEndDate() {
		return this.endDate;
	}

	/**
	 * 終了日時|2007/12/10属性名、カラム名変更byTada設定
	 * @param endDate 終了日時|2007/12/10属性名、カラム名変更byTada
	*/
	public void setEndDate(final Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * 開始実績日時取得
	 * @return Timestamp 開始実績日時
	*/
	public Timestamp getResultSdate() {
		return this.resultSdate;
	}

	/**
	 * 開始実績日時設定
	 * @param resultSdate 開始実績日時
	*/
	public void setResultSdate(final Timestamp resultSdate) {
		this.resultSdate = resultSdate;
	}

	/**
	 * 終了実績日時取得
	 * @return Timestamp 終了実績日時
	*/
	public Timestamp getResultEdate() {
		return this.resultEdate;
	}

	/**
	 * 終了実績日時設定
	 * @param resultEdate 終了実績日時
	*/
	public void setResultEdate(final Timestamp resultEdate) {
		this.resultEdate = resultEdate;
	}

	/**
	 * 温度取得
	 * @return BigDecimal 温度
	*/
	public BigDecimal getConditionTemp() {
		return this.conditionTemp;
	}

	/**
	 * 温度設定
	 * @param conditionTemp 温度
	*/
	public void setConditionTemp(final BigDecimal conditionTemp) {
		this.conditionTemp = conditionTemp;
	}

	/**
	 * 時間取得
	 * @return BigDecimal 時間
	*/
	public BigDecimal getConditionTime() {
		return this.conditionTime;
	}

	/**
	 * 時間設定
	 * @param conditionTime 時間
	*/
	public void setConditionTime(final BigDecimal conditionTime) {
		this.conditionTime = conditionTime;
	}

	/**
	 * 攪拌速度1取得
	 * @return BigDecimal 攪拌速度1
	*/
	public BigDecimal getStirSpeed1() {
		return this.stirSpeed1;
	}

	/**
	 * 攪拌速度1設定
	 * @param stirSpeed1 攪拌速度1
	*/
	public void setStirSpeed1(final BigDecimal stirSpeed1) {
		this.stirSpeed1 = stirSpeed1;
	}

	/**
	 * 攪拌速度2取得
	 * @return BigDecimal 攪拌速度2
	*/
	public BigDecimal getStirSpeed2() {
		return this.stirSpeed2;
	}

	/**
	 * 攪拌速度2設定
	 * @param stirSpeed2 攪拌速度2
	*/
	public void setStirSpeed2(final BigDecimal stirSpeed2) {
		this.stirSpeed2 = stirSpeed2;
	}

	/**
	 * 洗浄水絶対重量取得
	 * @return BigDecimal 洗浄水絶対重量
	*/
	public BigDecimal getWaterWeight() {
		return this.waterWeight;
	}

	/**
	 * 洗浄水絶対重量設定
	 * @param waterWeight 洗浄水絶対重量
	*/
	public void setWaterWeight(final BigDecimal waterWeight) {
		this.waterWeight = waterWeight;
	}

	/**
	 * 本流/予備溶解取得
	 * @return BigDecimal 本流/予備溶解
	*/
	public BigDecimal getMainStream() {
		return this.mainStream;
	}

	/**
	 * 本流/予備溶解設定
	 * @param mainStream 本流/予備溶解
	*/
	public void setMainStream(final BigDecimal mainStream) {
		this.mainStream = mainStream;
	}

	/**
	 * ｐＨ取得
	 * @return BigDecimal ｐＨ
	*/
	public BigDecimal getPh() {
		return this.ph;
	}

	/**
	 * ｐＨ設定
	 * @param ph ｐＨ
	*/
	public void setPh(final BigDecimal ph) {
		this.ph = ph;
	}

	/**
	 * 充填予定数量取得
	 * @return BigDecimal 充填予定数量
	*/
	public BigDecimal getFillingQty() {
		return this.fillingQty;
	}

	/**
	 * 充填予定数量設定
	 * @param fillingQty 充填予定数量
	*/
	public void setFillingQty(final BigDecimal fillingQty) {
		this.fillingQty = fillingQty;
	}

	/**
	 * 充填単位取得
	 * @return String 充填単位
	*/
	public String getFillingUnit() {
		return this.fillingUnit;
	}

	/**
	 * 充填単位設定
	 * @param fillingUnit 充填単位
	*/
	public void setFillingUnit(final String fillingUnit) {
		this.fillingUnit = fillingUnit;
	}

	/**
	 * 正味質量取得
	 * @return BigDecimal 正味質量
	*/
	public BigDecimal getNet() {
		return this.net;
	}

	/**
	 * 正味質量設定
	 * @param net 正味質量
	*/
	public void setNet(final BigDecimal net) {
		this.net = net;
	}

	/**
	 * 量目管理幅最小取得
	 * @return BigDecimal 量目管理幅最小
	*/
	public BigDecimal getWeightMin() {
		return this.weightMin;
	}

	/**
	 * 量目管理幅最小設定
	 * @param weightMin 量目管理幅最小
	*/
	public void setWeightMin(final BigDecimal weightMin) {
		this.weightMin = weightMin;
	}

	/**
	 * 量目管理幅最大取得
	 * @return BigDecimal 量目管理幅最大
	*/
	public BigDecimal getWeightMax() {
		return this.weightMax;
	}

	/**
	 * 量目管理幅最大設定
	 * @param weightMax 量目管理幅最大
	*/
	public void setWeightMax(final BigDecimal weightMax) {
		this.weightMax = weightMax;
	}

	/**
	 * 濾過用フィルター取得
	 * @return BigDecimal 濾過用フィルター
	*/
	public BigDecimal getFilter() {
		return this.filter;
	}

	/**
	 * 濾過用フィルター設定
	 * @param filter 濾過用フィルター
	*/
	public void setFilter(final BigDecimal filter) {
		this.filter = filter;
	}

	/**
	 * オートチェッカー最小取得
	 * @return BigDecimal オートチェッカー最小
	*/
	public BigDecimal getAutoCheckerMin() {
		return this.autoCheckerMin;
	}

	/**
	 * オートチェッカー最小設定
	 * @param autoCheckerMin オートチェッカー最小
	*/
	public void setAutoCheckerMin(final BigDecimal autoCheckerMin) {
		this.autoCheckerMin = autoCheckerMin;
	}

	/**
	 * オートチェッカー最大取得
	 * @return BigDecimal オートチェッカー最大
	*/
	public BigDecimal getAutoCheckerMax() {
		return this.autoCheckerMax;
	}

	/**
	 * オートチェッカー最大設定
	 * @param autoCheckerMax オートチェッカー最大
	*/
	public void setAutoCheckerMax(final BigDecimal autoCheckerMax) {
		this.autoCheckerMax = autoCheckerMax;
	}

	/**
	 * グロスチェッカー最小取得
	 * @return BigDecimal グロスチェッカー最小
	*/
	public BigDecimal getGrossCheckerMin() {
		return this.grossCheckerMin;
	}

	/**
	 * グロスチェッカー最小設定
	 * @param grossCheckerMin グロスチェッカー最小
	*/
	public void setGrossCheckerMin(final BigDecimal grossCheckerMin) {
		this.grossCheckerMin = grossCheckerMin;
	}

	/**
	 * グロスチェッカー最大取得
	 * @return BigDecimal グロスチェッカー最大
	*/
	public BigDecimal getGrossCheckerMax() {
		return this.grossCheckerMax;
	}

	/**
	 * グロスチェッカー最大設定
	 * @param grossCheckerMax グロスチェッカー最大
	*/
	public void setGrossCheckerMax(final BigDecimal grossCheckerMax) {
		this.grossCheckerMax = grossCheckerMax;
	}

	/**
	 * 開きトルク最小取得
	 * @return BigDecimal 開きトルク最小
	*/
	public BigDecimal getOpeningTorqueMin() {
		return this.openingTorqueMin;
	}

	/**
	 * 開きトルク最小設定
	 * @param openingTorqueMin 開きトルク最小
	*/
	public void setOpeningTorqueMin(final BigDecimal openingTorqueMin) {
		this.openingTorqueMin = openingTorqueMin;
	}

	/**
	 * 開きトルク最大取得
	 * @return BigDecimal 開きトルク最大
	*/
	public BigDecimal getOpeningTorqueMax() {
		return this.openingTorqueMax;
	}

	/**
	 * 開きトルク最大設定
	 * @param openingTorqueMax 開きトルク最大
	*/
	public void setOpeningTorqueMax(final BigDecimal openingTorqueMax) {
		this.openingTorqueMax = openingTorqueMax;
	}

	/**
	 * ホットエアー設定温度取得
	 * @return BigDecimal ホットエアー設定温度
	*/
	public BigDecimal getHotAirPresetTemp() {
		return this.hotAirPresetTemp;
	}

	/**
	 * ホットエアー設定温度設定
	 * @param hotAirPresetTemp ホットエアー設定温度
	*/
	public void setHotAirPresetTemp(final BigDecimal hotAirPresetTemp) {
		this.hotAirPresetTemp = hotAirPresetTemp;
	}

	/**
	 * ホットエアー吹き出し圧力取得
	 * @return BigDecimal ホットエアー吹き出し圧力
	*/
	public BigDecimal getHotAirPressure() {
		return this.hotAirPressure;
	}

	/**
	 * ホットエアー吹き出し圧力設定
	 * @param hotAirPressure ホットエアー吹き出し圧力
	*/
	public void setHotAirPressure(final BigDecimal hotAirPressure) {
		this.hotAirPressure = hotAirPressure;
	}

	/**
	 * 濾過用メッシュ取得
	 * @return BigDecimal 濾過用メッシュ
	*/
	public BigDecimal getMesh() {
		return this.mesh;
	}

	/**
	 * 濾過用メッシュ設定
	 * @param mesh 濾過用メッシュ
	*/
	public void setMesh(final BigDecimal mesh) {
		this.mesh = mesh;
	}

	/**
	 * オートチェッカー中心取得
	 * @return BigDecimal オートチェッカー中心
	*/
	public BigDecimal getAutoCheckerAve() {
		return this.autoCheckerAve;
	}

	/**
	 * オートチェッカー中心設定
	 * @param autoCheckerAve オートチェッカー中心
	*/
	public void setAutoCheckerAve(final BigDecimal autoCheckerAve) {
		this.autoCheckerAve = autoCheckerAve;
	}

	/**
	 * グロスチェッカー中心取得
	 * @return BigDecimal グロスチェッカー中心
	*/
	public BigDecimal getGrossCheckerAve() {
		return this.grossCheckerAve;
	}

	/**
	 * グロスチェッカー中心設定
	 * @param grossCheckerAve グロスチェッカー中心
	*/
	public void setGrossCheckerAve(final BigDecimal grossCheckerAve) {
		this.grossCheckerAve = grossCheckerAve;
	}

	/**
	 * 閉めトルク最小取得
	 * @return BigDecimal 閉めトルク最小
	*/
	public BigDecimal getClosingTorqueMin() {
		return this.closingTorqueMin;
	}

	/**
	 * 閉めトルク最小設定
	 * @param closingTorqueMin 閉めトルク最小
	*/
	public void setClosingTorqueMin(final BigDecimal closingTorqueMin) {
		this.closingTorqueMin = closingTorqueMin;
	}

	/**
	 * 閉めトルク最大取得
	 * @return BigDecimal 閉めトルク最大
	*/
	public BigDecimal getClosingTorqueMax() {
		return this.closingTorqueMax;
	}

	/**
	 * 閉めトルク最大設定
	 * @param closingTorqueMax 閉めトルク最大
	*/
	public void setClosingTorqueMax(final BigDecimal closingTorqueMax) {
		this.closingTorqueMax = closingTorqueMax;
	}

	/**
	 * トルク圧取得
	 * @return BigDecimal トルク圧
	*/
	public BigDecimal getTorquePressure() {
		return this.torquePressure;
	}

	/**
	 * トルク圧設定
	 * @param torquePressure トルク圧
	*/
	public void setTorquePressure(final BigDecimal torquePressure) {
		this.torquePressure = torquePressure;
	}

	/**
	 * エアー圧取得
	 * @return BigDecimal エアー圧
	*/
	public BigDecimal getAirPressure() {
		return this.airPressure;
	}

	/**
	 * エアー圧設定
	 * @param airPressure エアー圧
	*/
	public void setAirPressure(final BigDecimal airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * 巻閉め時間取得
	 * @return BigDecimal 巻閉め時間
	*/
	public BigDecimal getVcloseTime() {
		return this.vcloseTime;
	}

	/**
	 * 巻閉め時間設定
	 * @param vcloseTime 巻閉め時間
	*/
	public void setVcloseTime(final BigDecimal vcloseTime) {
		this.vcloseTime = vcloseTime;
	}

	/**
	 * 第一ヒートシール設定温度取得
	 * @return BigDecimal 第一ヒートシール設定温度
	*/
	public BigDecimal getFirstHeatSeal() {
		return this.firstHeatSeal;
	}

	/**
	 * 第一ヒートシール設定温度設定
	 * @param firstHeatSeal 第一ヒートシール設定温度
	*/
	public void setFirstHeatSeal(final BigDecimal firstHeatSeal) {
		this.firstHeatSeal = firstHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度取得
	 * @return BigDecimal 第二ヒートシール設定温度
	*/
	public BigDecimal getSecondHeatSeal() {
		return this.secondHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度設定
	 * @param secondHeatSeal 第二ヒートシール設定温度
	*/
	public void setSecondHeatSeal(final BigDecimal secondHeatSeal) {
		this.secondHeatSeal = secondHeatSeal;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getProcedureInputDate() {
		return this.procedureInputDate;
	}

	/**
	 * 登録日時設定
	 * @param procedureInputDate 登録日時
	*/
	public void setProcedureInputDate(final Timestamp procedureInputDate) {
		this.procedureInputDate = procedureInputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String getProcedureInputorCd() {
		return this.procedureInputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param procedureInputorCd 登録者ID
	*/
	public void setProcedureInputorCd(final String procedureInputorCd) {
		this.procedureInputorCd = procedureInputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getProcedureUpdateDate() {
		return this.procedureUpdateDate;
	}

	/**
	 * 更新日時設定
	 * @param procedureUpdateDate 更新日時
	*/
	public void setProcedureUpdateDate(final Timestamp procedureUpdateDate) {
		this.procedureUpdateDate = procedureUpdateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getProcedureUpdatorCd() {
		return this.procedureUpdatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param procedureUpdatorCd 更新者ID
	*/
	public void setProcedureUpdatorCd(final String procedureUpdatorCd) {
		this.procedureUpdatorCd = procedureUpdatorCd;
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
//Formula

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
	public BigDecimal getFormulaSeq() {
		return this.formulaSeq;
	}

	/**
	 * 投入順設定
	 * @param formulaSeq 投入順
	*/
	public void setFormulaSeq(final BigDecimal formulaSeq) {
		this.formulaSeq = formulaSeq;
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
	 * 数量 ※2007/12/10属性変更BYTADA取得
	 * @return BigDecimal 数量 ※2007/12/10属性変更BYTADA
	*/
	public BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * 数量 ※2007/12/10属性変更BYTADA設定
	 * @param qty 数量 ※2007/12/10属性変更BYTADA
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
	 * 原価 ※2007/12/10属性変更BYTADA取得
	 * @return BigDecimal 原価 ※2007/12/10属性変更BYTADA
	*/
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * 原価 ※2007/12/10属性変更BYTADA設定
	 * @param cost 原価 ※2007/12/10属性変更BYTADA
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
	public String getFormulaNotes() {
		return this.formulaNotes;
	}

	/**
	 * 注釈設定
	 * @param formulaNotes 注釈
	*/
	public void setFormulaNotes(final String formulaNotes) {
		this.formulaNotes = formulaNotes;
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
	public String getFormulaRemark() {
		return this.formulaRemark;
	}

	/**
	 * 備考設定
	 * @param formulaRemark 備考
	*/
	public void setFormulaRemark(final String formulaRemark) {
		this.formulaRemark = formulaRemark;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getFormulaInputDate() {
		return this.formulaInputDate;
	}

	/**
	 * 登録日時設定
	 * @param formulaInputDate 登録日時
	*/
	public void setFormulaInputDate(final Timestamp formulaInputDate) {
		this.formulaInputDate = formulaInputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	*/
	public String geFormulatInputorCd() {
		return this.formulaInputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param formulaInputorCd 登録者ID
	*/
	public void setFormulaInputorCd(final String formulaInputorCd) {
		this.formulaInputorCd = formulaInputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getFormulaUpdateDate() {
		return this.formulaUpdateDate;
	}

	/**
	 * 更新日時設定
	 * @param formulaUpdateDate 更新日時
	*/
	public void setFormulaUpdateDate(final Timestamp formulaUpdateDate) {
		this.formulaUpdateDate = formulaUpdateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	*/
	public String getFormulaUpdatorCd() {
		return this.formulaUpdatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param formulaUpdatorCd 更新者ID
	*/
	public void setFormulaUpdatorCd(final String formulaUpdatorCd) {
		this.formulaUpdatorCd = formulaUpdatorCd;
	}
}
