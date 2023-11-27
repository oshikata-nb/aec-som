/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;

/**
 * 製造実績-検査詳細画面 Formクラス
 * @author tosco
 */
public final class RdirectionInspectionDetailForm extends
		AbstractRdirectionForm {

	private static final long serialVersionUID = 1L;

	/** STEP_NO */
	private String stepNo;

	/** LINE_NO */
	private String lineNo;

	/** 指図ステータス */
	private String directionStatus;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 検査コード */
	private String inspectionCd;

	/** 検査名称 */
	private String inspectionName;

	/** 区分 */
	private String strInspectionDivision;

	/** ラジオボタン */
	private String valueType;

	/** 値1 */
	private String value1;

	/** 値1 */
	private String resultValue1;

	/** 値1(カンマ編集) */
	private String value1Format;

	/** 値1(カンマ編集) */
	private String result1Format;

	/** 条件 */
	private String strInspectionCondition;

	/** 値2 */
	private String value2;

	/** 値1(カンマ編集) */
	private String value2Format;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 基本処方名称 */
	private String recipeName;

	/** 用途 */
	private String recipeUse;

	/** 用途名 */
	private String recipeUseName;

	/** 検索結果 */
	private RdirectionDirectionInspectionList detailBean;

	/**
	 * コンストラクタ.
	 */
	public RdirectionInspectionDetailForm() {
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
		// LINE_NO
		setLineNo(null);
		// 工程コード
		setOperationCd(null);
		// 工程名称
		setOperationName(null);
		// 検査コード
		setInspectionCd(null);
		// 検査名称
		setInspectionName(null);
		// 区分
		setStrInspectionDivision(null);
		// 条件
		setStrInspectionCondition(null);
		// 値1
		setResultValue1(null);
		setValue1(null);
		// 値2
		setValue2(null);
		// 備考
		setRemark(null);
		// 注釈
		setNotes(null);
		// ラジオボタン
		setValueType(null);
		// 基本処方名称
		setRecipeName(null);
		// 用途
		setRecipeUse(null);
		// 用途名称
		setRecipeUseName(null);
		// 検索結果
		setDetailBean(null);
		// 値1(カンマ編集)
		setValue1Format(null);
		// 値2(カンマ編集)
		setValue2Format(null);

		setResult1Format(null);
	}

	/**
	 * STEP_NOを取得します
	 * @return String STEP_NO
	 */
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * STEP_NOを設定します
	 * @param stepNo STEP_NO
	 */
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * lineNoを取得します。
	 * @return lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}

	/**
	 * lineNoを設定します。
	 * @param lineNo lineNo
	 */
	public void setLineNo(final String lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * 指図ステータスを取得します。
	 * @return directionStatus
	 */
	public String getDirectionStatus() {
		return directionStatus;
	}

	/**
	 * 指図ステータスを設定します。
	 * @param directionStatus directionStatus
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 工程コードを取得します。
	 * @return operationCd
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
	 * @return operationName
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
	 * 検査コードを取得します
	 * @return String 検査コード
	 */
	public String getInspectionCd() {
		return this.inspectionCd;
	}

	/**
	 * 検査コードを設定します
	 * @param inspectionCd 検査コード
	 */
	public void setInspectionCd(final String inspectionCd) {
		this.inspectionCd = inspectionCd;
	}

	/**
	 * 検査名称を取得します
	 * @return inspectionName
	 */
	public String getInspectionName() {
		return inspectionName;
	}

	/**
	 * 検査名称を設定します
	 * @param inspectionName 検査名称
	 */
	public void setInspectionName(final String inspectionName) {
		this.inspectionName = inspectionName;
	}

	/**
	 * 区分を取得します
	 * @return String 本流/予備溶解
	 */
	public String getStrInspectionDivision() {
		return this.strInspectionDivision;
	}

	/**
	 * 区分を設定します
	 * @param strInspectionDivision 区分
	 */
	public void setStrInspectionDivision(final String strInspectionDivision) {
		this.strInspectionDivision = strInspectionDivision;
	}

	/**
	 * ラジオボタンを取得します。
	 * @return atai
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * ラジオボタンを設定します。
	 * @param valueType ラジオボタン
	 */
	public void setValueType(final String valueType) {
		this.valueType = valueType;
	}

	/**
	 * 値1 を取得します。
	 * @return resultValue1
	 */
	public String getValue1() {
		return value1;
	}

	/**
	 * 値1 を設定します。
	 * @param resultValue1 値1
	 */
	public void setValue1(final String resultValue1) {
		this.value1 = resultValue1;
	}

	/**
	 * 値1(カンマ編集) を取得します。
	 * @return String (カンマ編集)
	 */
	public String getValue1Format() {
		return value1Format;
	}

	/**
	 * 値1(カンマ編集) を設定します。
	 * @param value1Format 値1(カンマ編集)
	 */
	public void setValue1Format(final String value1Format) {
		this.value1Format = value1Format;
	}

	/**
	 * 条件を取得します
	 * @return String 条件
	 */
	public String getStrInspectionCondition() {
		return this.strInspectionCondition;
	}

	/**
	 * 条件設定
	 * @param strInspectionCondition 条件
	 */
	public void setStrInspectionCondition(final String strInspectionCondition) {
		this.strInspectionCondition = strInspectionCondition;
	}

	/**
	 * 値2を取得します。
	 * @return resultValue2
	 */
	public String getValue2() {
		return value2;
	}

	/**
	 * 値2を設定します。
	 * @param resultValue2 値2
	 */
	public void setValue2(final String resultValue2) {
		this.value2 = resultValue2;
	}

	/**
	 * 値2(カンマ編集)を取得します。
	 * @return value2Format String
	 */
	public String getValue2Format() {
		return value2Format;
	}

	/**
	 * 値2(カンマ編集)を設定します。
	 * @param value2Format 値2(カンマ編集)
	 */
	public void setValue2Format(final String value2Format) {
		this.value2Format = value2Format;
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
	 * 基本処方名称を取得します。
	 * @return recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * 基本処方名称を設定します。
	 * @param recipeName recipeName
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * 基本処方名称を取得します。
	 * @return recipeUseName
	 */
	public String getRecipeUseName() {
		return recipeUseName;
	}

	/**
	 * 基本処方名称を設定します。
	 * @param recipeUseName recipeUseName
	 */
	public void setRecipeUseName(final String recipeUseName) {
		this.recipeUseName = recipeUseName;
	}

	/**
	 * 検索結果Bean取得
	 * @return RdirectionDirectionInspectionList 検索結果Bean
	 */
	public RdirectionDirectionInspectionList getDetailBean() {
		return detailBean;
	}

	/**
	 * 検索結果Bean設定
	 * @param detailBean 検索結果Bean
	 */
	public void setDetailBean(final RdirectionDirectionInspectionList detailBean) {
		this.detailBean = detailBean;
	}

	/**
	 * 用途を取得します。
	 * @return recipeUse
	 */
	public String getRecipeUse() {
		return recipeUse;
	}

	/**
	 * 用途を設定します。
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * resultValue1を取得します。
	 * @return resultValue1
	 */
	public String getResultValue1() {
		return resultValue1;
	}

	/**
	 * resultValue1を設定します。
	 * @param resultValue1 resultValue1
	 */
	public void setResultValue1(final String resultValue1) {
		this.resultValue1 = resultValue1;
	}

	/**
	 * result1Formatを取得します。
	 * @return result1Format
	 */
	public String getResult1Format() {
		return result1Format;
	}

	/**
	 * result1Formatを設定します。
	 * @param result1Format result1Format
	 */
	public void setResult1Format(final String result1Format) {
		this.result1Format = result1Format;
	}
}
