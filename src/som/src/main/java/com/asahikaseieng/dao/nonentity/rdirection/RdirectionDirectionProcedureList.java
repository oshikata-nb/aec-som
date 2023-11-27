/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 製造指図プロシージャデータ格納クラス.
 * 
 * @author tosco
 */
public class RdirectionDirectionProcedureList extends
		RdirectionDirectionProcedureListBase implements
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

	/** 温度String */
	private String strConditionTemp;

	/** 時間String */
	private String strConditionTime;

	private String strResultConditionTemp;

	/** 攪拌速度1String */
	private String strStirSpeed1;

	private String strResultStirSpeed;

	/** 攪拌速度2String */
	private String strStirSpeed2;

	/** 洗浄水絶対重量String */
	private String strWaterWeight;

	/** ph String */
	private String strPh;

	private String strResultPh;

	/** 本流/予備溶解String */
	private String strMainStream;

	/** 工程開始実績日時String */
	private String strResultSdate;

	/** 工程終了実績日時String */
	private String strResultEdate;

	/** 工程開始実績日String */
	private String strResultSDay;

	/** 工程開始実績時String */
	private String strResultSTime;

	/** 工程終了実績日String */
	private String strResultEDay;

	/** 工程終了実績時String */
	private String strResultETime;

	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionProcedureList() {
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
		if (this.getResultSdate() != null) {
			this.setStrResultSdate(AecDateUtils.dateFormat(this
					.getResultSdate(), "yyyy/MM/dd HH:mm"));
			this.setStrResultSDay(AecDateUtils.dateFormat(
				this.getResultSdate(), "yyyy/MM/dd"));
			this.setStrResultSTime(AecDateUtils.dateFormat(this
					.getResultSdate(), "HH:mm"));
		}
		if (this.getResultEdate() != null) {
			this.setStrResultEdate(AecDateUtils.dateFormat(this
					.getResultEdate(), "yyyy/MM/dd HH:mm"));
			this.setStrResultEDay(AecDateUtils.dateFormat(
				this.getResultEdate(), "yyyy/MM/dd"));
			this.setStrResultETime(AecDateUtils.dateFormat(this
					.getResultEdate(), "HH:mm"));
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
	 * pHを取得します。
	 * @return strPh
	 */
	public String getStrPh() {
		return strPh;
	}

	/**
	 * pHを設定します。
	 * @param strPh pH
	 */
	public void setStrPh(final String strPh) {
		this.strPh = strPh;
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

	/**
	 * 工程開始実績日時取得
	 * @return String 工程開始実績日時
	 */
	public String getStrResultSdate() {
		return this.strResultSdate;
	}

	/**
	 * 工程開始実績日時設定
	 * @param strResultSdate 工程実績予定日時
	 */
	public void setStrResultSdate(final String strResultSdate) {
		this.strResultSdate = strResultSdate;
	}

	/**
	 * 工程終了実績日時取得
	 * @return String 工程終了実績日時
	 */
	public String getStrResultEdate() {
		return this.strResultEdate;
	}

	/**
	 * 工程終了実績日時設定
	 * @param strResultEdate 工程終了実績日時
	 */
	public void setStrResultEdate(final String strResultEdate) {
		this.strResultEdate = strResultEdate;
	}

	/**
	 * 工程終了実績日を取得します。
	 * @return strResultEDay
	 */
	public String getStrResultEDay() {
		return strResultEDay;
	}

	/**
	 * 工程終了実績日を設定します。
	 * @param strResultEDay 工程終了実績日
	 */
	public void setStrResultEDay(final String strResultEDay) {
		this.strResultEDay = strResultEDay;
	}

	/**
	 * 工程終了実績時を取得します。
	 * @return strResultETime
	 */
	public String getStrResultETime() {
		return strResultETime;
	}

	/**
	 * 工程終了実績時を設定します。
	 * @param strResultETime 工程終了実績時
	 */
	public void setStrResultETime(final String strResultETime) {
		this.strResultETime = strResultETime;
	}

	/**
	 * 工程開始実績日を取得します。
	 * @return strResultSDay
	 */
	public String getStrResultSDay() {
		return strResultSDay;
	}

	/**
	 * 工程開始実績日を設定します。
	 * @param strResultSDay 工程開始実績日
	 */
	public void setStrResultSDay(final String strResultSDay) {
		this.strResultSDay = strResultSDay;
	}

	/**
	 * 工程開始実績時を取得します。
	 * @return strResultSTime
	 */
	public String getStrResultSTime() {
		return strResultSTime;
	}

	/**
	 * 工程開始実績時を設定します。
	 * @param strResultSTime 工程開始実績時
	 */
	public void setStrResultSTime(final String strResultSTime) {
		this.strResultSTime = strResultSTime;
	}

	/**
	 * strResultConditionTempを取得します。
	 * @return strResultConditionTemp
	 */
	public String getStrResultConditionTemp() {
		return strResultConditionTemp;
	}

	/**
	 * strResultConditionTempを設定します。
	 * @param strResultConditionTemp strResultConditionTemp
	 */
	public void setStrResultConditionTemp(final String strResultConditionTemp) {
		this.strResultConditionTemp = strResultConditionTemp;
	}

	/**
	 * strResultPhを取得します。
	 * @return strResultPh
	 */
	public String getStrResultPh() {
		return strResultPh;
	}

	/**
	 * strResultPhを設定します。
	 * @param strResultPh strResultPh
	 */
	public void setStrResultPh(final String strResultPh) {
		this.strResultPh = strResultPh;
	}

	/**
	 * strResultSpeedを取得します。
	 * @return strResultStirSpeed
	 */
	public String getStrResultStirSpeed() {
		return strResultStirSpeed;
	}

	/**
	 * strResultSpeedを設定します。
	 * @param strResultStirSpeed strResultStirSpeed
	 */
	public void setStrResultStirSpeed(final String strResultStirSpeed) {
		this.strResultStirSpeed = strResultStirSpeed;
	}
}
