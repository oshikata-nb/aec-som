/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 設備グループ詳細 Formクラス.
 * @author t0011036
 */
public final class RecipeResouceGroupDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 設備グループコード */
	private String resouceGroupCd;

	/* 設備グループ名称 */
	private String resouceGroupName;

	/* 設備グループグループコード */
	private String operationGroupCd;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 設備グループグループ名称 */
	private String[] operationGroupValues;

	private String[] operationGroupLabels;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupDetailForm() {
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
		setResouceGroupCd(null);
		setResouceGroupName(null);
		setOperationGroupCd(null);
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
	 * operationGroupLabelsを取得します。
	 * @return operationGroupLabels
	 */
	public String[] getRecipeResouceGroupGroupLabels() {
		return operationGroupLabels;
	}

	/**
	 * operationGroupLabelsを設定します。
	 * @param operationGroupLabels operationGroupLabels
	 */
	public void setRecipeResouceGroupGroupLabels(
			final String[] operationGroupLabels) {
		this.operationGroupLabels = operationGroupLabels;
	}

	/**
	 * operationGroupValuesを取得します。
	 * @return operationGroupValues
	 */
	public String[] getRecipeResouceGroupGroupValues() {
		return operationGroupValues;
	}

	/**
	 * operationGroupValuesを設定します。
	 * @param operationGroupValues operationGroupValues
	 */
	public void setRecipeResouceGroupGroupValues(
			final String[] operationGroupValues) {
		this.operationGroupValues = operationGroupValues;
	}

	/**
	 * operationGroupCdを取得します。
	 * @return operationGroupCd
	 */
	public String getRecipeResouceGroupGroupCd() {
		return operationGroupCd;
	}

	/**
	 * operationGroupCdを設定します。
	 * @param operationGroupCd operationGroupCd
	 */
	public void setRecipeResouceGroupGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
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
	 * resouceGroupCdを取得します。
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return resouceGroupCd;
	}

	/**
	 * resouceGroupCdを設定します。
	 * @param resouceGroupCd resouceGroupCd
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * resouceGroupNameを取得します。
	 * @return resouceGroupName
	 */
	public String getResouceGroupName() {
		return resouceGroupName;
	}

	/**
	 * resouceGroupNameを設定します。
	 * @param resouceGroupName resouceGroupName
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}
}
