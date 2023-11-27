/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;

/**
 * 工程タブ詳細画面 Formクラス.
 * @author tosco
 */
public final class DirectionProcedureDetailForm extends AbstractDirectionForm {

	private static final long serialVersionUID = 1L;

	/** ステップNO. */
	private String strStepNo;

	/** 投入順序 SEQ */
	private String strSeq;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 工程条件:フリー入力 */
	private String condition;

	/** 備考 */
	private String procedureRemark;

	/** 注釈 */
	private String procedureNotes;

	/** リードタイム（分） */
	private String leadtime;

	/** 開始日付 */
	private String strStartDate;

	/** 開始時間 */
	private String strStartTime;

	/** 終了日付 */
	private String strEndDate;

	/** 終了時間 */
	private String strEndTime;

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

	/** 検索結果 */
	private DirectionDirectionProcedureList detailBean;

	/** 小数点桁数(RECIPE1) */
	private String decimalPointRecipe1;
	/** 端数区分(RECIPE1) */
	private String roundDivisionRecipe1;
	/** 小数点桁数(RECIPE2) */
	private String decimalPointRecipe2;
	/** 端数区分(RECIPE2) */
	private String roundDivisionRecipe2;
	/** 小数点桁数(SONOTA) */
	private String decimalPointSonota;
	/** 端数区分(SONOTA) */
	private String roundDivisionSonota;

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedureDetailForm() {
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
			//Validatorによる判定
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
		setStrStepNo(null);
		setStrSeq(null);
		setOperationCd(null);
		setOperationName(null);
		setCondition(null);
		setProcedureRemark(null);
		setProcedureNotes(null);
		setLeadtime(null);
		setStrStartDate(null);
		setStrStartTime(null);
		setStrEndDate(null);
		setStrEndTime(null);
		setStrConditionTemp(null);
		setStrConditionTime(null);
		setStrStirSpeed1(null);
		setStrStirSpeed2(null);
		setStrWaterWeight(null);
		setStrMainStream(null);
		setDecimalPointRecipe1(null);
		setDecimalPointRecipe2(null);
		setDecimalPointSonota(null);
		setRoundDivisionRecipe1(null);
		setRoundDivisionRecipe2(null);
		setRoundDivisionSonota(null);
		// 検索結果Bean
		setDetailBean(null);
	}

	//getter,setter

	/**
	 * ステップNO.を取得します。
	 * @return strStepNo
	 */
	public String getStrStepNo() {
		return strStepNo;
	}

	/**
	 * ステップNO.を設定します。
	 * @param strStepNo ステップNO.
	 */
	public void setStrStepNo(final String strStepNo) {
		this.strStepNo = strStepNo;
	}

	/**
	 * 投入順序 SEQを取得します。
	 * @return strSeq 投入順序 SEQ
	 */
	public String getStrSeq() {
		return strSeq;
	}

	/**
	 * 投入順序 SEQを設定します。
	 * @param strSeq 投入順序 SEQ
	 */
	public void setStrSeq(final String strSeq) {
		this.strSeq = strSeq;
	}

	/**
	 * 工程コードを取得します。
	 * @return operationCd 工程コード
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * 工程コードを設定します。
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称を取得します。
	 * @return operationName 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 工程条件(自由入力)を取得します。
	 * @return condition 工程条件(自由入力)
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * 工程条件(自由入力)を設定します。
	 * @param condition 工程条件(自由入力)
	 */
	public void setCondition(final String condition) {
		this.condition = condition;
	}

	/**
	 * 備考を取得します。
	 * @return procedureRemark 備考
	 */
	public String getProcedureRemark() {
		return procedureRemark;
	}

	/**
	 * 備考を設定します。
	 * @param procedureRemark 備考
	 */
	public void setProcedureRemark(final String procedureRemark) {
		this.procedureRemark = procedureRemark;
	}

	/**
	 * 注釈を取得します。
	 * @return procedureNotes 注釈
	 */
	public String getProcedureNotes() {
		return procedureNotes;
	}

	/**
	 * 注釈を設定します。
	 * @param procedureNotes 注釈
	 */
	public void setProcedureNotes(final String procedureNotes) {
		this.procedureNotes = procedureNotes;
	}

	/**
	 * リードタイム（分）を取得します。
	 * @return leadtime リードタイム（分）
	 */
	public String getLeadtime() {
		return leadtime;
	}

	/**
	 * リードタイム（分）を設定します。
	 * @param leadtime リードタイム（分）
	 */
	public void setLeadtime(final String leadtime) {
		this.leadtime = leadtime;
	}

	/**
	 * 開始日付を取得します。
	 * @return strStartDate
	 */
	public String getStrStartDate() {
		return strStartDate;
	}

	/**
	 * 開始日付を設定します。
	 * @param strStartDate 開始日付
	 */
	public void setStrStartDate(final String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * 開始時間を取得します。
	 * @return strStartTime
	 */
	public String getStrStartTime() {
		return strStartTime;
	}

	/**
	 * 開始時間を設定します。
	 * @param strStartTime 開始時間
	 */
	public void setStrStartTime(final String strStartTime) {
		this.strStartTime = strStartTime;
	}

	/**
	 * 終了日付を取得します。
	 * @return strEndDate
	 */
	public String getStrEndDate() {
		return strEndDate;
	}

	/**
	 * 終了日付を設定します。
	 * @param strEndDate 終了日付
	 */
	public void setStrEndDate(final String strEndDate) {
		this.strEndDate = strEndDate;
	}

	/**
	 * 終了時間を取得します。
	 * @return strEndTime
	 */
	public String getStrEndTime() {
		return strEndTime;
	}

	/**
	 * 終了時間を設定します。
	 * @param strEndTime 終了時間
	 */
	public void setStrEndTime(final String strEndTime) {
		this.strEndTime = strEndTime;
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

	/**
	 * 検索結果Bean取得
	 * @return DirectionDirectionProcedureList 検索結果Bean
	 */
	public DirectionDirectionProcedureList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果Bean設定
	 * @param detailBean 検索結果Bean
	 */
	public void setDetailBean(final DirectionDirectionProcedureList detailBean) {
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

}
