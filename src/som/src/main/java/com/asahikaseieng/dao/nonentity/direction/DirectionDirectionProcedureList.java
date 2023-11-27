/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.direction;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;


/**
 * 製造指図プロシージャデータ格納クラス.
 *
 * @author tosco
 */
public class DirectionDirectionProcedureList extends DirectionDirectionProcedureListBase
		implements PropertyTransferCallbacker {


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

	/** チェックフラグ */
	private boolean checkFlg;

	/** STEP_NO */
	private String strStepNo;

	/** SEQ */
	private String strSeq;

	/** 工程名称 */
	private String operationName;

	/** 配合 */
	private String formulaMark;

	/** 工程 */
	private String inspectionMark;

	/** 最終STEP_NO */
	private BigDecimal lastStepNo;

	/** 開始日付String */
	private String strStartDate;

	/** 終了日付String */
	private String strEndDate;

	/** 開始時間String */
	private String strStartTime;

	/** 終了時間String */
	private String strEndTime;

	/** 温度String */
	private String strConditionTemp;

	/** 時間String */
	private String strConditionTime;

	/** 攪拌速度1String */
	private String strStirSpeed1;

	/** 攪拌速度2String */
	private String strStirSpeed2;

	/** 洗浄水絶対重量String */
	private String strWaterWeight;

	/** 本流/予備溶解String */
	private String strMainStream;

	/**
	 * コンストラクタ.
	 */
	public DirectionDirectionProcedureList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		if (getStepNo() != null) {
			setStrStepNo(getStepNo().toString());
		}
		if (getSeq() != null) {
			setStrSeq(getSeq().toString());
		}
		/* 日付フォーマット変換 */
		setStrStartDate(AecDateUtils.dateFormat(getStartDate(), "yyyy/MM/dd"));
		setStrEndDate(AecDateUtils.dateFormat(getEndDate(), "yyyy/MM/dd"));
		setStrStartTime(AecDateUtils.dateFormat(getStartDate(), "HH:mm"));
		setStrEndTime(AecDateUtils.dateFormat(getEndDate(), "HH:mm"));
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
	 * SEQを取得します。
	 * @return strSeq
	 */
	public String getStrSeq() {
		return strSeq;
	}

	/**
	 * SEQを設定します。
	 * @param strSeq SEQ
	 */
	public void setStrSeq(final String strSeq) {
		this.strSeq = strSeq;
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
	 * 最終STEP_NOを設定します。
	 * @param lastStepNo 最終STEP_NO
	 */
	public void setLastStepNo(final BigDecimal lastStepNo) {
		this.lastStepNo = lastStepNo;
	}

	/**
	 * 終了日付Stringを取得します。
	 * @return strEndDate
	 */
	public String getStrEndDate() {
		return strEndDate;
	}

	/**
	 * 終了日付Stringを設定します。
	 * @param strEndDate 終了日付String
	 */
	public void setStrEndDate(final String strEndDate) {
		this.strEndDate = strEndDate;
	}

	/**
	 * 開始日付Stringを取得します。
	 * @return strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}

	/**
	 * 開始日付Stringを設定します。
	 * @param strStartDate 開始日付String
	 */
	public void setStrStartDate(final String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * 終了時間Stringを取得します。
	 * @return strEndTime
	 */
	public String getStrEndTime() {
		return strEndTime;
	}

	/**
	 * 終了時間Stringを設定します。
	 * @param strEndTime 終了時間String
	 */
	public void setStrEndTime(final String strEndTime) {
		this.strEndTime = strEndTime;
	}

	/**
	 * 開始時間Stringを取得します。
	 * @return strStartTime
	 */
	public String getStrStartTime() {
		return strStartTime;
	}

	/**
	 * 開始時間Stringを設定します。
	 * @param strStartTime 開始時間String
	 */
	public void setStrStartTime(final String strStartTime) {
		this.strStartTime = strStartTime;
	}

	/**
	 * 温度を取得します。
	 * @return strConditionTemp 温度
	 */
	public String getStrConditionTemp() {
		return strConditionTemp;
	}

	/**
	 * 温度を設定します。
	 * @param strConditionTemp 温度
	 */
	public void setStrConditionTemp(final String strConditionTemp) {
		this.strConditionTemp = strConditionTemp;
	}

	/**
	 * 時間を取得します。
	 * @return strConditionTime 時間
	 */
	public String getStrConditionTime() {
		return strConditionTime;
	}

	/**
	 * 時間を設定します。
	 * @param strConditionTime 時間
	 */
	public void setStrConditionTime(final String strConditionTime) {
		this.strConditionTime = strConditionTime;
	}

	/**
	 * 攪拌速度1を取得します。
	 * @return strStirSpeed1 攪拌速度1
	 */
	public String getStrStirSpeed1() {
		return strStirSpeed1;
	}

	/**
	 * 攪拌速度1を設定します。
	 * @param strStirSpeed1 攪拌速度1
	 */
	public void setStrStirSpeed1(final String strStirSpeed1) {
		this.strStirSpeed1 = strStirSpeed1;
	}

	/**
	 * 攪拌速度2を取得します。
	 * @return strStirSpeed2 攪拌速度2
	 */
	public String getStrStirSpeed2() {
		return strStirSpeed2;
	}

	/**
	 * 攪拌速度2を設定します。
	 * @param strStirSpeed2 攪拌速度2
	 */
	public void setStrStirSpeed2(final String strStirSpeed2) {
		this.strStirSpeed2 = strStirSpeed2;
	}

	/**
	 * 洗浄水絶対重量を取得します。
	 * @return strWaterWeight 洗浄水絶対重量
	 */
	public String getStrWaterWeight() {
		return strWaterWeight;
	}

	/**
	 * 洗浄水絶対重量を設定します。
	 * @param strWaterWeight 洗浄水絶対重量
	 */
	public void setStrWaterWeight(final String strWaterWeight) {
		this.strWaterWeight = strWaterWeight;
	}

	/**
	 * 本流/予備溶解を取得します。
	 * @return strMainStream
	 */
	public String getStrMainStream() {
		return strMainStream;
	}

	/**
	 * 本流/予備溶解を設定します。
	 * @param strMainStream 本流/予備溶解
	 */
	public void setStrMainStream(final String strMainStream) {
		this.strMainStream = strMainStream;
	}

}
