/*
 * Created on 2009/02/23
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
 * 製造指図プロシージャデータ格納クラス.
 *
 * @author tosco
 */
public class DirectionDirectionProcedureListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionDirectionProcedureListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "DIRECTION_PROCEDURE";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

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

	/** COLUMNアノテーション resultConditionTemp. */
	public static final String resultConditionTemp_COLUMN = "RESULT_CONDITION_TEMP";

	/** COLUMNアノテーション resultStirSpeed. */
	public static final String resultStirSpeed_COLUMN = "RESULT_STIR_SPEED";

	/** COLUMNアノテーション resultPh. */
	public static final String resultPh_COLUMN = "RESULT_PH";

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

	/** ステップNO. */
	private BigDecimal stepNo;

	/** 投入順 */
	private BigDecimal seq;

	/** 工程コード */
	private String operationCd;

	/** 工程条件:フリー入力 */
	private String condition;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

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

	/** 実績温度 */
	private java.math.BigDecimal resultConditionTemp;

	/** 実績攪拌速度 */
	private java.math.BigDecimal resultStirSpeed;

	/** 実績ｐＨ */
	private java.math.BigDecimal resultPh;

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
	 * 実績温度取得.
	 * @return resultConditionTemp 実績温度
	 */
	public java.math.BigDecimal getResultConditionTemp() {
		return this.resultConditionTemp;
	}

	/**
	 * 実績温度設定.
	 * @param resultConditionTemp 実績温度
	 */
	public void setResultConditionTemp(final java.math.BigDecimal resultConditionTemp) {
		this.resultConditionTemp = resultConditionTemp;
	}

	/**
	 * 実績攪拌速度取得.
	 * @return resultStirSpeed
	 */
	public java.math.BigDecimal getResultStirSpeed() {
		return this.resultStirSpeed;
	}

	/**
	 * 実績攪拌速度設定.
	 * @param resultStirSpeed 実績攪拌速度
	 */
	public void setResultStirSpeed(final java.math.BigDecimal resultStirSpeed) {
		this.resultStirSpeed = resultStirSpeed;
	}

	/**
	 * 実績ｐＨ取得.
	 * @return resultPh 実績ｐＨ
	 */
	public java.math.BigDecimal getResultPh() {
		return this.resultPh;
	}

	/**
	 * 実績ｐＨ設定.
	 * @param resultPh 実績ｐＨ
	 */
	public void setResultPh(final java.math.BigDecimal resultPh) {
		this.resultPh = resultPh;
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
