/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;

/**
 * 工程タブ詳細画面 Formクラス.
 * @author tosco
 */
public final class MgrecipeProcedureDetailForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** STEP_NO */
	private String stepNo;

	/** 表示順 */
	private String seq;

	/** 配合量 */
	private String sumQty;

	/** 荷姿 */
	private String styleOfPacking;

	/** 工程コード */
	private String operationCd;

	/** 条件 */
	private String condition;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 作業時間（機械） */
	private String strMachineTime;

	/** 作業時間（人） */
	private String strManTime;

	/** 稼動時間 */
	private String strWorkTime;

	/** 温度 */
	private String strConditionTemp;

	/** 時間 */
	private String strConditionTime;

	/** 攪拌速度1 */
	private String strStirSpeed1;

	/** 攪拌速度2 */
	private String strStirSpeed2;

	/** 洗浄水絶対重量 */
	private String strWaterWeight;

	/** 本流/予備溶解 */
	private String strMainStream;

	/** 正味質量 */
	private String strNet;

	/** 量目管理幅最小 */
	private String strWeightMin;

	/** 量目管理幅最大 */
	private String strWeightMax;

	/** 濾過用フィルター */
	private String strFilter;

	/** 濾過用メッシュ */
	private String strMesh;

	/** オートチェッカー最小 */
	private String strAutoCheckerMin;

	/** オートチェッカー最大 */
	private String strAutoCheckerMax;

	/** グロスチェッカー最小 */
	private String strGrossCheckerMin;

	/** グロスチェッカー最大 */
	private String strGrossCheckerMax;

	/** オートチェッカー中心 */
	private String strAutoCheckerAve;

	/** グロスチェッカー中心 */
	private String strGrossCheckerAve;

	/** 開きトルク最小 */
	private String strOpeningTorqueMin;

	/** 開きトルク最大 */
	private String strOpeningTorqueMax;

	/** 閉めトルク最小 */
	private String strClosingTorqueMin;

	/** 閉めトルク最大 */
	private String strClosingTorqueMax;

	/** トルク圧 */
	private String strTorquePressure;

	/** エアー圧 */
	private String strAirPressure;

	/** 巻締め時間 */
	private String strVcloseTime;

	/** ホットエアー設定温度 */
	private String strHotAirPresetTemp;

	/** ホットエアー吹き出し圧力 */
	private String strHotAirPressure;

	/** 第一ヒートシール設定温度 */
	private String strFirstHeatSeal;

	/** 第二ヒートシール設定温度 */
	private String strSecondHeatSeal;

	/** 工程名称 */
	private String operationName;

	/** 検索結果 */
	private RecipeProcedureList detailBean;

	/** 小数点桁数(RECIPE1) */
	private String decimalPointRecipe1;

	/** 端数区分(RECIPE1) */
	private String roundDivisionRecipe1;

	/** 小数点桁数(RECIPE2) */
	private String decimalPointRecipe2;

	/** 端数区分(RECIPE2) */
	private String roundDivisionRecipe2;

	/** 小数点桁数(RECIPE5) */
	private String decimalPointRecipe5;

	/** 端数区分(RECIPE5) */
	private String roundDivisionRecipe5;

	/** 小数点桁数(SONOTA) */
	private String decimalPointSonota;

	/** 端数区分(SONOTA) */
	private String roundDivisionSonota;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeProcedureDetailForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;
		if ("update".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// STEP_NO
		setStepNo(null);
		// 表示順
		setSeq(null);
		// 配合量計
		setSumQty(null);
		// 荷姿
		setStyleOfPacking(null);
		// 工程コード
		setOperationCd(null);
		// 条件
		setCondition(null);
		// 備考
		setRemark(null);
		// 注釈
		setNotes(null);
		// 作業時間（機械）
		setStrMachineTime(null);
		// 作業時間（人）
		setStrManTime(null);
		// 稼動時間
		setStrWorkTime(null);
		// 温度
		setStrConditionTemp(null);
		// 時間
		setStrConditionTime(null);
		// 攪拌速度1
		setStrStirSpeed1(null);
		// 攪拌速度2
		setStrStirSpeed2(null);
		// 洗浄水絶対重量
		setStrWaterWeight(null);
		// 本流/予備溶解
		setStrMainStream(null);
		// 正味質量
		setStrNet(null);
		// 量目管理幅最小
		setStrWeightMin(null);
		// 量目管理幅最大
		setStrWeightMax(null);
		// 濾過用フィルター
		setStrFilter(null);
		// 濾過用メッシュ
		setStrMesh(null);
		// オートチェッカー最小
		setStrAutoCheckerMin(null);
		// オートチェッカー最大
		setStrAutoCheckerMax(null);
		// グロスチェッカー最小
		setStrGrossCheckerMin(null);
		// グロスチェッカー最大
		setStrGrossCheckerMax(null);
		// オートチェッカー中心
		setStrAutoCheckerAve(null);
		// グロスチェッカー中心
		setStrGrossCheckerAve(null);
		// 開きトルク最小
		setStrOpeningTorqueMin(null);
		// 開きトルク最大
		setStrOpeningTorqueMax(null);
		// 閉めトルク最小
		setStrClosingTorqueMin(null);
		// 閉めトルク最大
		setStrClosingTorqueMax(null);
		// トルク圧
		setStrTorquePressure(null);
		// エアー圧
		setStrAirPressure(null);
		// 巻締め時間
		setStrVcloseTime(null);
		// ホットエアー設定温度
		setStrHotAirPresetTemp(null);
		// ホットエアー吹き出し圧力
		setStrHotAirPressure(null);
		// 第一ヒートシール設定温度
		setStrFirstHeatSeal(null);
		// 第二ヒートシール設定温度
		setStrSecondHeatSeal(null);
		// 工程名称
		setOperationName(null);
		// 検索結果Bean
		setDetailBean(null);
	}

	// getter,setter
	/**
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NO設定
	 * @param stepNo STEP_NO
	 */
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 表示順取得
	 * @return String 表示順
	 */
	public String getSeq() {
		return this.seq;
	}

	/**
	 * 表示順設定
	 * @param seq 表示順
	 */
	public void setSeq(final String seq) {
		this.seq = seq;
	}

	/**
	 * 配合量を取得します。
	 * @return 配合量
	 */
	public String getSumQty() {
		return sumQty;
	}

	/**
	 * 配合量を設定します。
	 * @param sumQty 配合量
	 */
	public void setSumQty(final String sumQty) {
		this.sumQty = sumQty;
	}

	/**
	 * 荷姿を取得します。
	 * @return 荷姿
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
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
	 * @return String 作業時間（機械）
	 */
	public String getStrMachineTime() {
		return this.strMachineTime;
	}

	/**
	 * 作業時間（機械）設定
	 * @param strMachineTime 作業時間（機械）
	 */
	public void setStrMachineTime(final String strMachineTime) {
		this.strMachineTime = strMachineTime;
	}

	/**
	 * 作業時間（人）取得
	 * @return String 作業時間（人）
	 */
	public String getStrManTime() {
		return this.strManTime;
	}

	/**
	 * 作業時間（人）設定
	 * @param strManTime 作業時間（人）
	 */
	public void setStrManTime(final String strManTime) {
		this.strManTime = strManTime;
	}

	/**
	 * 稼動時間取得
	 * @return String 稼動時間
	 */
	public String getStrWorkTime() {
		return this.strWorkTime;
	}

	/**
	 * 稼動時間設定
	 * @param strWorkTime 稼動時間
	 */
	public void setStrWorkTime(final String strWorkTime) {
		this.strWorkTime = strWorkTime;
	}

	/**
	 * 温度取得
	 * @return String 温度
	 */
	public String getStrConditionTemp() {
		return this.strConditionTemp;
	}

	/**
	 * 温度設定
	 * @param strConditionTemp 温度
	 */
	public void setStrConditionTemp(final String strConditionTemp) {
		this.strConditionTemp = strConditionTemp;
	}

	/**
	 * 時間取得
	 * @return String 時間
	 */
	public String getStrConditionTime() {
		return this.strConditionTime;
	}

	/**
	 * 時間設定
	 * @param strConditionTime 時間
	 */
	public void setStrConditionTime(final String strConditionTime) {
		this.strConditionTime = strConditionTime;
	}

	/**
	 * 攪拌速度1取得
	 * @return String 攪拌速度1
	 */
	public String getStrStirSpeed1() {
		return this.strStirSpeed1;
	}

	/**
	 * 攪拌速度1設定
	 * @param strStirSpeed1 攪拌速度1
	 */
	public void setStrStirSpeed1(final String strStirSpeed1) {
		this.strStirSpeed1 = strStirSpeed1;
	}

	/**
	 * 攪拌速度2取得
	 * @return String 攪拌速度2
	 */
	public String getStrStirSpeed2() {
		return this.strStirSpeed2;
	}

	/**
	 * 攪拌速度2設定
	 * @param strStirSpeed2 攪拌速度2
	 */
	public void setStrStirSpeed2(final String strStirSpeed2) {
		this.strStirSpeed2 = strStirSpeed2;
	}

	/**
	 * 洗浄水絶対重量取得
	 * @return String 洗浄水絶対重量
	 */
	public String getStrWaterWeight() {
		return this.strWaterWeight;
	}

	/**
	 * 洗浄水絶対重量設定
	 * @param strWaterWeight 洗浄水絶対重量
	 */
	public void setStrWaterWeight(final String strWaterWeight) {
		this.strWaterWeight = strWaterWeight;
	}

	/**
	 * 本流/予備溶解取得
	 * @return String 本流/予備溶解
	 */
	public String getStrMainStream() {
		return this.strMainStream;
	}

	/**
	 * 本流/予備溶解設定
	 * @param strMainStream 本流/予備溶解
	 */
	public void setStrMainStream(final String strMainStream) {
		this.strMainStream = strMainStream;
	}

	/**
	 * 正味質量取得
	 * @return String 正味質量
	 */
	public String getStrNet() {
		return this.strNet;
	}

	/**
	 * 正味質量設定
	 * @param strNet 正味質量
	 */
	public void setStrNet(final String strNet) {
		this.strNet = strNet;
	}

	/**
	 * 量目管理幅最小取得
	 * @return String 量目管理幅最小
	 */
	public String getStrWeightMin() {
		return this.strWeightMin;
	}

	/**
	 * 量目管理幅最小設定
	 * @param strWeightMin 量目管理幅最小
	 */
	public void setStrWeightMin(final String strWeightMin) {
		this.strWeightMin = strWeightMin;
	}

	/**
	 * 量目管理幅最大取得
	 * @return String 量目管理幅最大
	 */
	public String getStrWeightMax() {
		return this.strWeightMax;
	}

	/**
	 * 量目管理幅最大設定
	 * @param strWeightMax 量目管理幅最大
	 */
	public void setStrWeightMax(final String strWeightMax) {
		this.strWeightMax = strWeightMax;
	}

	/**
	 * 濾過用フィルター取得
	 * @return String 濾過用フィルター
	 */
	public String getStrFilter() {
		return this.strFilter;
	}

	/**
	 * 濾過用フィルター設定
	 * @param strFilter 濾過用フィルター
	 */
	public void setStrFilter(final String strFilter) {
		this.strFilter = strFilter;
	}

	/**
	 * 濾過用メッシュ取得
	 * @return String 濾過用メッシュ
	 */
	public String getStrMesh() {
		return this.strMesh;
	}

	/**
	 * 濾過用メッシュ設定
	 * @param strMesh 濾過用メッシュ
	 */
	public void setStrMesh(final String strMesh) {
		this.strMesh = strMesh;
	}

	/**
	 * オートチェッカー最小取得
	 * @return String オートチェッカー最小
	 */
	public String getStrAutoCheckerMin() {
		return this.strAutoCheckerMin;
	}

	/**
	 * オートチェッカー最小設定
	 * @param strAutoCheckerMin オートチェッカー最小
	 */
	public void setStrAutoCheckerMin(final String strAutoCheckerMin) {
		this.strAutoCheckerMin = strAutoCheckerMin;
	}

	/**
	 * オートチェッカー最大取得
	 * @return String オートチェッカー最大
	 */
	public String getStrAutoCheckerMax() {
		return this.strAutoCheckerMax;
	}

	/**
	 * オートチェッカー最大設定
	 * @param strAutoCheckerMax オートチェッカー最大
	 */
	public void setStrAutoCheckerMax(final String strAutoCheckerMax) {
		this.strAutoCheckerMax = strAutoCheckerMax;
	}

	/**
	 * グロスチェッカー最小取得
	 * @return String グロスチェッカー最小
	 */
	public String getStrGrossCheckerMin() {
		return this.strGrossCheckerMin;
	}

	/**
	 * グロスチェッカー最小設定
	 * @param strGrossCheckerMin グロスチェッカー最小
	 */
	public void setStrGrossCheckerMin(final String strGrossCheckerMin) {
		this.strGrossCheckerMin = strGrossCheckerMin;
	}

	/**
	 * グロスチェッカー最大取得
	 * @return String グロスチェッカー最大
	 */
	public String getStrGrossCheckerMax() {
		return this.strGrossCheckerMax;
	}

	/**
	 * グロスチェッカー最大設定
	 * @param strGrossCheckerMax グロスチェッカー最大
	 */
	public void setStrGrossCheckerMax(final String strGrossCheckerMax) {
		this.strGrossCheckerMax = strGrossCheckerMax;
	}

	/**
	 * オートチェッカー中心取得
	 * @return String オートチェッカー中心
	 */
	public String getStrAutoCheckerAve() {
		return this.strAutoCheckerAve;
	}

	/**
	 * オートチェッカー中心設定
	 * @param strAutoCheckerAve オートチェッカー中心
	 */
	public void setStrAutoCheckerAve(final String strAutoCheckerAve) {
		this.strAutoCheckerAve = strAutoCheckerAve;
	}

	/**
	 * グロスチェッカー中心取得
	 * @return String グロスチェッカー中心
	 */
	public String getStrGrossCheckerAve() {
		return this.strGrossCheckerAve;
	}

	/**
	 * グロスチェッカー中心設定
	 * @param strGrossCheckerAve グロスチェッカー中心
	 */
	public void setStrGrossCheckerAve(final String strGrossCheckerAve) {
		this.strGrossCheckerAve = strGrossCheckerAve;
	}

	/**
	 * 開きトルク最小取得
	 * @return String 開きトルク最小
	 */
	public String getStrOpeningTorqueMin() {
		return this.strOpeningTorqueMin;
	}

	/**
	 * 開きトルク最小設定
	 * @param strOpeningTorqueMin 開きトルク最小
	 */
	public void setStrOpeningTorqueMin(final String strOpeningTorqueMin) {
		this.strOpeningTorqueMin = strOpeningTorqueMin;
	}

	/**
	 * 開きトルク最大取得
	 * @return String 開きトルク最大
	 */
	public String getStrOpeningTorqueMax() {
		return this.strOpeningTorqueMax;
	}

	/**
	 * 開きトルク最大設定
	 * @param strOpeningTorqueMax 開きトルク最大
	 */
	public void setStrOpeningTorqueMax(final String strOpeningTorqueMax) {
		this.strOpeningTorqueMax = strOpeningTorqueMax;
	}

	/**
	 * 閉めトルク最小取得
	 * @return String 閉めトルク最小
	 */
	public String getStrClosingTorqueMin() {
		return this.strClosingTorqueMin;
	}

	/**
	 * 閉めトルク最小設定
	 * @param strClosingTorqueMin 閉めトルク最小
	 */
	public void setStrClosingTorqueMin(final String strClosingTorqueMin) {
		this.strClosingTorqueMin = strClosingTorqueMin;
	}

	/**
	 * 閉めトルク最大取得
	 * @return String 閉めトルク最大
	 */
	public String getStrClosingTorqueMax() {
		return this.strClosingTorqueMax;
	}

	/**
	 * 閉めトルク最大設定
	 * @param strClosingTorqueMax 閉めトルク最大
	 */
	public void setStrClosingTorqueMax(final String strClosingTorqueMax) {
		this.strClosingTorqueMax = strClosingTorqueMax;
	}

	/**
	 * トルク圧取得
	 * @return String トルク圧
	 */
	public String getStrTorquePressure() {
		return this.strTorquePressure;
	}

	/**
	 * トルク圧設定
	 * @param strTorquePressure トルク圧
	 */
	public void setStrTorquePressure(final String strTorquePressure) {
		this.strTorquePressure = strTorquePressure;
	}

	/**
	 * エアー圧取得
	 * @return String エアー圧
	 */
	public String getStrAirPressure() {
		return this.strAirPressure;
	}

	/**
	 * エアー圧設定
	 * @param strAirPressure エアー圧
	 */
	public void setStrAirPressure(final String strAirPressure) {
		this.strAirPressure = strAirPressure;
	}

	/**
	 * 巻締め時間取得
	 * @return String 巻締め時間
	 */
	public String getStrVcloseTime() {
		return this.strVcloseTime;
	}

	/**
	 * 巻締め時間設定
	 * @param strVcloseTime 巻締め時間
	 */
	public void setStrVcloseTime(final String strVcloseTime) {
		this.strVcloseTime = strVcloseTime;
	}

	/**
	 * ホットエアー設定温度取得
	 * @return String ホットエアー設定温度
	 */
	public String getStrHotAirPresetTemp() {
		return this.strHotAirPresetTemp;
	}

	/**
	 * ホットエアー設定温度設定
	 * @param strHotAirPresetTemp ホットエアー設定温度
	 */
	public void setStrHotAirPresetTemp(final String strHotAirPresetTemp) {
		this.strHotAirPresetTemp = strHotAirPresetTemp;
	}

	/**
	 * ホットエアー吹き出し圧力取得
	 * @return String ホットエアー吹き出し圧力
	 */
	public String getStrHotAirPressure() {
		return this.strHotAirPressure;
	}

	/**
	 * ホットエアー吹き出し圧力設定
	 * @param strHotAirPressure ホットエアー吹き出し圧力
	 */
	public void setStrHotAirPressure(final String strHotAirPressure) {
		this.strHotAirPressure = strHotAirPressure;
	}

	/**
	 * 第一ヒートシール設定温度取得
	 * @return String 第一ヒートシール設定温度
	 */
	public String getStrFirstHeatSeal() {
		return this.strFirstHeatSeal;
	}

	/**
	 * 第一ヒートシール設定温度設定
	 * @param strFirstHeatSeal 第一ヒートシール設定温度
	 */
	public void setStrFirstHeatSeal(final String strFirstHeatSeal) {
		this.strFirstHeatSeal = strFirstHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度取得
	 * @return String 第二ヒートシール設定温度
	 */
	public String getStrSecondHeatSeal() {
		return this.strSecondHeatSeal;
	}

	/**
	 * 第二ヒートシール設定温度設定
	 * @param strSecondHeatSeal 第二ヒートシール設定温度
	 */
	public void setStrSecondHeatSeal(final String strSecondHeatSeal) {
		this.strSecondHeatSeal = strSecondHeatSeal;
	}

	/**
	 * 工程名称取得
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称設定
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 検索結果Bean取得
	 * @return RecipeProcedureList 検索結果Bean
	 */
	public RecipeProcedureList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果Bean設定
	 * @param detailBean 検索結果Bean
	 */
	public void setDetailBean(final RecipeProcedureList detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * 小数点桁数(RECIPE1)を取得します。
	 * @return decimalPointRecipe1 小数点桁数(RECIPE1)
	 */
	public String getDecimalPointRecipe1() {
		return decimalPointRecipe1;
	}

	/**
	 * 小数点桁数(RECIPE1)を設定します。
	 * @param decimalPointRecipe1 小数点桁数(RECIPE1)
	 */
	public void setDecimalPointRecipe1(final String decimalPointRecipe1) {
		this.decimalPointRecipe1 = decimalPointRecipe1;
	}

	/**
	 * 端数区分(RECIPE1)を取得します。
	 * @return roundDivisionRecipe1 端数区分(RECIPE1)
	 */
	public String getRoundDivisionRecipe1() {
		return roundDivisionRecipe1;
	}

	/**
	 * 端数区分(RECIPE1)を設定します。
	 * @param roundDivisionRecipe1 端数区分(RECIPE1)
	 */
	public void setRoundDivisionRecipe1(final String roundDivisionRecipe1) {
		this.roundDivisionRecipe1 = roundDivisionRecipe1;
	}

	/**
	 * 小数点桁数(RECIPE2)を取得します。
	 * @return decimalPointRecipe2 小数点桁数(RECIPE2)
	 */
	public String getDecimalPointRecipe2() {
		return decimalPointRecipe2;
	}

	/**
	 * 小数点桁数(RECIPE2)を設定します。
	 * @param decimalPointRecipe2 小数点桁数(RECIPE2)
	 */
	public void setDecimalPointRecipe2(final String decimalPointRecipe2) {
		this.decimalPointRecipe2 = decimalPointRecipe2;
	}

	/**
	 * 端数区分(RECIPE2)を取得します。
	 * @return roundDivisionRecipe2 端数区分(RECIPE2)
	 */
	public String getRoundDivisionRecipe2() {
		return roundDivisionRecipe2;
	}

	/**
	 * 端数区分(RECIPE2)を設定します。
	 * @param roundDivisionRecipe2 端数区分(RECIPE2)
	 */
	public void setRoundDivisionRecipe2(final String roundDivisionRecipe2) {
		this.roundDivisionRecipe2 = roundDivisionRecipe2;
	}

	/**
	 * 小数点桁数(RECIPE5)を取得します。
	 * @return decimalPointRecipe5 小数点桁数(RECIPE5)
	 */
	public String getDecimalPointRecipe5() {
		return decimalPointRecipe5;
	}

	/**
	 * 小数点桁数(RECIPE5)を設定します。
	 * @param decimalPointRecipe5 小数点桁数(RECIPE5)
	 */
	public void setDecimalPointRecipe5(final String decimalPointRecipe5) {
		this.decimalPointRecipe5 = decimalPointRecipe5;
	}

	/**
	 * 端数区分(RECIPE5)を取得します。
	 * @return roundDivisionRecipe5 端数区分(RECIPE5)
	 */
	public String getRoundDivisionRecipe5() {
		return roundDivisionRecipe5;
	}

	/**
	 * 端数区分(RECIPE5)を設定します。
	 * @param roundDivisionRecipe5 端数区分(RECIPE5)
	 */
	public void setRoundDivisionRecipe5(final String roundDivisionRecipe5) {
		this.roundDivisionRecipe5 = roundDivisionRecipe5;
	}

	/**
	 * 小数点桁数(SONOTA)を取得します。
	 * @return decimalPointSonota 小数点桁数(SONOTA)
	 */
	public String getDecimalPointSonota() {
		return decimalPointSonota;
	}

	/**
	 * 小数点桁数(SONOTA)を設定します。
	 * @param decimalPointSonota 小数点桁数(SONOTA)
	 */
	public void setDecimalPointSonota(final String decimalPointSonota) {
		this.decimalPointSonota = decimalPointSonota;
	}

	/**
	 * 端数区分(SONOTA)を取得します。
	 * @return roundDivisionSonota 端数区分(SONOTA)
	 */
	public String getRoundDivisionSonota() {
		return roundDivisionSonota;
	}

	/**
	 * 端数区分(SONOTA)を設定します。
	 * @param roundDivisionSonota 端数区分(SONOTA)
	 */
	public void setRoundDivisionSonota(final String roundDivisionSonota) {
		this.roundDivisionSonota = roundDivisionSonota;
	}

	/**
	 * srhLinkを取得します。
	 * @return srhLink
	 */
	public String getSrhLink() {
		return srhLink;
	}

	/**
	 * srhLinkを設定します。
	 * @param srhLink srhLink
	 */
	public void setSrhLink(final String srhLink) {
		this.srhLink = srhLink;
	}
}
