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
 * 処方プロシージャデータ格納クラス.
 *
 * @author tosco
 */
public class GrecipeRecipeProcedureListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public GrecipeRecipeProcedureListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_PROCEDURE";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

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

	/** COLUMNアノテーション machineTime */
	public static final String machineTime_COLUMN = "MACHINE_TIME";

	/** COLUMNアノテーション manTime */
	public static final String manTime_COLUMN = "MAN_TIME";

	/** COLUMNアノテーション workTime */
	public static final String workTime_COLUMN = "WORK_TIME";

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

	/** COLUMNアノテーション net */
	public static final String net_COLUMN = "NET";

	/** COLUMNアノテーション weightMin */
	public static final String weightMin_COLUMN = "WEIGHT_MIN";

	/** COLUMNアノテーション weightMax */
	public static final String weightMax_COLUMN = "WEIGHT_MAX";

	/** COLUMNアノテーション filter */
	public static final String filter_COLUMN = "FILTER";

	/** COLUMNアノテーション mesh */
	public static final String mesh_COLUMN = "MESH";

	/** COLUMNアノテーション autoCheckerMin */
	public static final String autoCheckerMin_COLUMN = "AUTO_CHECKER_MIN";

	/** COLUMNアノテーション autoCheckerMax */
	public static final String autoCheckerMax_COLUMN = "AUTO_CHECKER_MAX";

	/** COLUMNアノテーション grossCheckerMin */
	public static final String grossCheckerMin_COLUMN = "GROSS_CHECKER_MIN";

	/** COLUMNアノテーション grossCheckerMax */
	public static final String grossCheckerMax_COLUMN = "GROSS_CHECKER_MAX";

	/** COLUMNアノテーション autoCheckerAve */
	public static final String autoCheckerAve_COLUMN = "AUTO_CHECKER_AVE";

	/** COLUMNアノテーション grossCheckerAve */
	public static final String grossCheckerAve_COLUMN = "GROSS_CHECKER_AVE";

	/** COLUMNアノテーション openingTorqueMin */
	public static final String openingTorqueMin_COLUMN = "OPENING_TORQUE_MIN";

	/** COLUMNアノテーション openingTorqueMax */
	public static final String openingTorqueMax_COLUMN = "OPENING_TORQUE_MAX";

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

	/** COLUMNアノテーション hotAirPresetTemp */
	public static final String hotAirPresetTemp_COLUMN = "HOT_AIR_PRESET_TEMP";

	/** COLUMNアノテーション hotAirPressure */
	public static final String hotAirPressure_COLUMN = "HOT_AIR_PRESSURE";

	/** COLUMNアノテーション firstHeatSeal */
	public static final String firstHeatSeal_COLUMN = "FIRST_HEAT_SEAL";

	/** COLUMNアノテーション secondHeatSeal */
	public static final String secondHeatSeal_COLUMN = "SECOND_HEAT_SEAL";

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

	/** 表示順 */
	private BigDecimal seq;

	/** 工程コード */
	private String operationCd;

	/** 条件 */
	private String condition;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 作業時間（機械） */
	private BigDecimal machineTime;

	/** 作業時間（人） */
	private BigDecimal manTime;

	/** 稼動時間 */
	private BigDecimal workTime;

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

	/** 本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,…8:準備 */
	private BigDecimal mainStream;

	/** 正味質量 */
	private BigDecimal net;

	/** 量目管理幅最小 */
	private BigDecimal weightMin;

	/** 量目管理幅最大 */
	private BigDecimal weightMax;

	/** 濾過用フィルター */
	private BigDecimal filter;

	/** 濾過用メッシュ */
	private BigDecimal mesh;

	/** オートチェッカー最小 */
	private BigDecimal autoCheckerMin;

	/** オートチェッカー最大 */
	private BigDecimal autoCheckerMax;

	/** グロスチェッカー最小 */
	private BigDecimal grossCheckerMin;

	/** グロスチェッカー最大 */
	private BigDecimal grossCheckerMax;

	/** オートチェッカー中心 */
	private BigDecimal autoCheckerAve;

	/** グロスチェッカー中心 */
	private BigDecimal grossCheckerAve;

	/** 開きトルク最小 */
	private BigDecimal openingTorqueMin;

	/** 開きトルク最大 */
	private BigDecimal openingTorqueMax;

	/** 閉めトルク最小 */
	private BigDecimal closingTorqueMin;

	/** 閉めトルク最大 */
	private BigDecimal closingTorqueMax;

	/** トルク圧 */
	private BigDecimal torquePressure;

	/** エアー圧 */
	private BigDecimal airPressure;

	/** 巻締め時間 */
	private BigDecimal vcloseTime;

	/** ホットエアー設定温度 */
	private BigDecimal hotAirPresetTemp;

	/** ホットエアー吹き出し圧力 */
	private BigDecimal hotAirPressure;

	/** 第一ヒートシール設定温度 */
	private BigDecimal firstHeatSeal;

	/** 第二ヒートシール設定温度 */
	private BigDecimal secondHeatSeal;

	/** 登録日時 */
	private String inputorCd;

	/** 登録者ID */
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
	 * 表示順取得
	 * @return BigDecimal 表示順
	*/
	public BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * 表示順設定
	 * @param seq 表示順
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
	 * 条件取得
	 * @return String 条件
	*/
	public String getCondition() {
		return this.condition;
	}

	/**
	 * 条件設定
	 * @param condition 条件
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
	 * 作業時間（機械）取得
	 * @return BigDecimal 作業時間（機械）
	*/
	public BigDecimal getMachineTime() {
		return this.machineTime;
	}

	/**
	 * 作業時間（機械）設定
	 * @param machineTime 作業時間（機械）
	*/
	public void setMachineTime(final BigDecimal machineTime) {
		this.machineTime = machineTime;
	}

	/**
	 * 作業時間（人）取得
	 * @return BigDecimal 作業時間（人）
	*/
	public BigDecimal getManTime() {
		return this.manTime;
	}

	/**
	 * 作業時間（人）設定
	 * @param manTime 作業時間（人）
	*/
	public void setManTime(final BigDecimal manTime) {
		this.manTime = manTime;
	}

	/**
	 * 稼動時間取得
	 * @return BigDecimal 稼動時間
	*/
	public BigDecimal getWorkTime() {
		return this.workTime;
	}

	/**
	 * 稼動時間設定
	 * @param workTime 稼動時間
	*/
	public void setWorkTime(final BigDecimal workTime) {
		this.workTime = workTime;
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
	 * 本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,…8:準備取得
	 * @return BigDecimal 本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,…8:準備
	*/
	public BigDecimal getMainStream() {
		return this.mainStream;
	}

	/**
	 * 本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,…8:準備設定
	 * @param mainStream 本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,…8:準備
	*/
	public void setMainStream(final BigDecimal mainStream) {
		this.mainStream = mainStream;
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
	 * 巻締め時間取得
	 * @return BigDecimal 巻締め時間
	*/
	public BigDecimal getVcloseTime() {
		return this.vcloseTime;
	}

	/**
	 * 巻締め時間設定
	 * @param vcloseTime 巻締め時間
	*/
	public void setVcloseTime(final BigDecimal vcloseTime) {
		this.vcloseTime = vcloseTime;
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
	 * @param updateDate 更新者ID
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
