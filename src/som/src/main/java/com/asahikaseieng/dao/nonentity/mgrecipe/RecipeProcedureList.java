/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方プロシージャデータ格納クラス.
 * 
 * @author tosco
 */
public class RecipeProcedureList extends RecipeProcedureListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション formulaMark */
	public static final String formulaMark_COLUMN = "FORMULA_MARK";

	/** COLUMNアノテーション inspectionMark */
	public static final String inspectionMark_COLUMN = "INSPECTION_MARK";

	/** COLUMNアノテーション lastStepNo */
	public static final String lastStepNo_COLUMN = "LAST_STEP_NO";

	// /** COLUMNアノテーション jyutenUnitDiv */
	// public static final String jyutenUnitDiv_COLUMN = "JYUTEN_UNIT_DIV";
	//
	// /** COLUMNアノテーション packageUnitDiv */
	// public static final String packageUnitDiv_COLUMN = "PACKAGE_UNIT_DIV";
	//
	/** COLUMNアノテーション jyutenUnit */
	public static final String jyutenUnit_COLUMN = "JYUTEN_UNIT";

	/** COLUMNアノテーション packageUnit */
	public static final String packageUnit_COLUMN = "PACKAGE_UNIT";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** チェックフラグ */
	private boolean checkFlg;

	/** STEP_NO */
	private String strStepNo;

	/** 工程名称 */
	private String operationName;

	/** 配合 */
	private String formulaMark;

	/** 工程 */
	private String inspectionMark;

	/** 最終STEP_NO */
	private BigDecimal lastStepNo;

	// /** 端数管理単位(充填) */
	// private String jyutenUnitDiv;
	//
	// /** 運用管理単位(包装) */
	// private String packageUnitDiv;
	//
	// /** 充填単位 */
	// private String jyutenUnit;
	//
	// /** 包装単位 */
	// private String packageUnit;
	//
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

	/** 工程グループコード */
	private String operationGroupCd;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public RecipeProcedureList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		if (getStepNo() != null) {
			setStrStepNo(getStepNo().toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * チェックフラグ取得
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグ設定
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * STEP_NO取得
	 * @return String STEP_NO
	 */
	public String getStrStepNo() {
		return strStepNo;
	}

	/**
	 * STEP_NO設定
	 * @param strStepNo STEP_NO
	 */
	public void setStrStepNo(final String strStepNo) {
		this.strStepNo = strStepNo;
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
	 * 配合取得
	 * @return String 配合
	 */
	public String getFormulaMark() {
		return formulaMark;
	}

	/**
	 * 配合設定
	 * @param formulaMark 配合
	 */
	public void setFormulaMark(final String formulaMark) {
		this.formulaMark = formulaMark;
	}

	/**
	 * 工程取得
	 * @return String 工程
	 */
	public String getInspectionMark() {
		return inspectionMark;
	}

	/**
	 * 工程設定
	 * @param inspectionMark 工程
	 */
	public void setInspectionMark(final String inspectionMark) {
		this.inspectionMark = inspectionMark;
	}

	/**
	 * 最終STEP_NO取得
	 * @return BigDecimal 最終STEP_NO
	 */
	public BigDecimal getLastStepNo() {
		return lastStepNo;
	}

	/**
	 * 最終STEP_NO設定
	 * @param lastStepNo 最終STEP_NO
	 */
	public void setLastStepNo(final BigDecimal lastStepNo) {
		this.lastStepNo = lastStepNo;
	}

	// /**
	// * 端数管理単位(充填)取得
	// * @return String 端数管理単位(充填)
	// */
	// public String getJyutenUnitDiv() {
	// return jyutenUnitDiv;
	// }
	//
	// /**
	// * 端数管理単位(充填)設定
	// * @param jyutenUnitDiv 端数管理単位(充填)
	// */
	// public void setJyutenUnitDiv(final String jyutenUnitDiv) {
	// this.jyutenUnitDiv = jyutenUnitDiv;
	// }
	//
	// /**
	// * 運用管理単位(包装)取得
	// * @return String 運用管理単位(包装)
	// */
	// public String getPackageUnitDiv() {
	// return packageUnitDiv;
	// }
	//
	// /**
	// * 運用管理単位(包装)設定
	// * @param packageUnitDiv 運用管理単位(包装)
	// */
	// public void setPackageUnitDiv(final String packageUnitDiv) {
	// this.packageUnitDiv = packageUnitDiv;
	// }
	//
	// /**
	// * 充填単位取得
	// * @return String 充填単位
	// */
	// public String getJyutenUnit() {
	// return jyutenUnit;
	// }
	//
	// /**
	// * 充填単位設定
	// * @param jyutenUnit 充填単位
	// */
	// public void setJyutenUnit(final String jyutenUnit) {
	// this.jyutenUnit = jyutenUnit;
	// }
	//
	// /**
	// * 包装単位取得
	// * @return String 包装単位
	// */
	// public String getPackageUnit() {
	// return packageUnit;
	// }
	//
	// /**
	// * 包装単位設定
	// * @param packageUnit 包装単位
	// */
	// public void setPackageUnit(final String packageUnit) {
	// this.packageUnit = packageUnit;
	// }
	//
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
	 * 工程グループコードを取得します。
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * 工程グループコードを設定します。
	 * @param operationGroupCd 工程グループコード
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
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
