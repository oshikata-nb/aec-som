/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 工程詳細 Formクラス.
 * @author t0011036
 */
public final class OperationDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 工程コード */
	private String operationCd;

	/* 工程名称 */
	private String operationName;

	/* 用途 */
	private BigDecimal recipeUse;

	/* 工程グループコード */
	private String operationGroupCd;

	/* 注釈 */
	private String memo;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 工程グループ名称 */
	private String[] operationGroupValues;

	private String[] operationGroupLabels;

	/**
	 * コンストラクタ.
	 */
	public OperationDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setOperationCd(null);
		setOperationName(null);
		setRecipeUse(null);
		setOperationGroupCd(null);
		setMemo(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setOperationGroupValues(null);
		setOperationGroupLabels(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * memoを取得します。
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * memoを設定します。
	 * @param memo memo
	 */
	public void setMemo(final String memo) {
		this.memo = memo;
	}

	/**
	 * operationCdを取得します。
	 * @return operationCd
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * operationCdを設定します。
	 * @param operationCd operationCd
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * operationNameを取得します。
	 * @return operationName
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * operationNameを設定します。
	 * @param operationName operationName
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * recipeUseを取得します。
	 * @return recipeUse
	 */
	public BigDecimal getRecipeUse() {
		return recipeUse;
	}

	/**
	 * recipeUseを設定します。
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * operationGroupLabelsを取得します。
	 * @return operationGroupLabels
	 */
	public String[] getOperationGroupLabels() {
		return operationGroupLabels;
	}

	/**
	 * operationGroupLabelsを設定します。
	 * @param operationGroupLabels operationGroupLabels
	 */
	public void setOperationGroupLabels(final String[] operationGroupLabels) {
		this.operationGroupLabels = operationGroupLabels;
	}

	/**
	 * operationGroupValuesを取得します。
	 * @return operationGroupValues
	 */
	public String[] getOperationGroupValues() {
		return operationGroupValues;
	}

	/**
	 * operationGroupValuesを設定します。
	 * @param operationGroupValues operationGroupValues
	 */
	public void setOperationGroupValues(final String[] operationGroupValues) {
		this.operationGroupValues = operationGroupValues;
	}

	/**
	 * operationGroupCdを取得します。
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return operationGroupCd;
	}

	/**
	 * operationGroupCdを設定します。
	 * @param operationGroupCd operationGroupCd
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}
}
