/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;

/**
 * 原処方 検査一覧タブ Formクラス
 * @author tosco
 */
public final class GrecipeInspectionListForm extends AbstractGrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> procedureStepNoCombo;

	/** 選択STEP_NO */
	private String selectProcedureStepNo;

	/** 検索用STEP_NO */
	private String selectKeyStepNo;

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 検索結果リスト */
	private List<GrecipeRecipeInspectionList> searchInspectionList;

	/** 検索結果リスト保存用 */
	private List<GrecipeRecipeInspectionList> saveSearchInspectionList;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public GrecipeInspectionListForm() {
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
		} else if ("addlist".equals(getOp())) {
			clearCheck();
		} else if ("dellist".equals(getOp())) {
			clearCheck();
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
		if ("insert".equals(getOp())) {
			// Validatorによる判定
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理
	 */
	@Override
	protected void clear() {
		super.clear();
		if (!"reSearch".equals(getOp())) {
			this.setSearchInspectionList(null);
			this.setSaveSearchInspectionList(null);
			this.setOperationCd(null);
			this.setOperationName(null);
			this.setProcedureStepNoCombo(null);
			this.setSelectProcedureStepNo(null);
			this.setSelectKeyStepNo(null);
		}
	}

	/**
	 * チェックボックス用クリア処理
	 */
	private void clearCheck() {
		if (getSearchInspectionList() != null) {
			for (GrecipeRecipeInspectionList bean : getSearchInspectionList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	// getter,setter

	/**
	 * 工程順序コンボボックスを取得する
	 * @return 工程順序コンボボックス
	 */
	public List<ComboBoxItems> getProcedureStepNoCombo() {
		return procedureStepNoCombo;
	}

	/**
	 * 工程順序コンボボックスを設定する
	 * @param procedureStepNoCombo 工程順序コンボボックス
	 */
	public void setProcedureStepNoCombo(
			final List<ComboBoxItems> procedureStepNoCombo) {
		this.procedureStepNoCombo = procedureStepNoCombo;
	}

	/**
	 * 選択STEP_NOを取得する
	 * @return 選択STEP_NO
	 */
	public String getSelectProcedureStepNo() {
		return selectProcedureStepNo;
	}

	/**
	 * 選択STEP_NOを設定する
	 * @param selectProcedureStepNo 選択STEP_NO
	 */
	public void setSelectProcedureStepNo(final String selectProcedureStepNo) {
		this.selectProcedureStepNo = selectProcedureStepNo;
	}

	/**
	 * 検索用STEP_NOを取得する
	 * @return 検索用STEP_NO
	 */
	public String getSelectKeyStepNo() {
		return selectKeyStepNo;
	}

	/**
	 * 検索用STEP_NOを設定する
	 * @param selectKeyStepNo 検索用STEP_NO
	 */
	public void setSelectKeyStepNo(final String selectKeyStepNo) {
		this.selectKeyStepNo = selectKeyStepNo;
	}

	/**
	 * 工程コードを取得する
	 * @return 工程コード
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * 工程コードを設定する
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * 工程名称を取得する
	 * @return 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定する
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
	}

	/**
	 * 検索結果リストを取得する
	 * @return List<GrecipeRecipeInspectionList> 検索結果リスト
	 */
	public List<GrecipeRecipeInspectionList> getSearchInspectionList() {
		return searchInspectionList;
	}

	/**
	 * 検索結果リストを設定する
	 * @param searchInspectionList 検索結果リスト
	 */
	public void setSearchInspectionList(
			final List<GrecipeRecipeInspectionList> searchInspectionList) {
		this.searchInspectionList = searchInspectionList;
	}

	/**
	 * 検索結果リスト保存用を取得する
	 * @return List<GrecipeRecipeInspectionList> 検索結果リスト保存用
	 */
	public List<GrecipeRecipeInspectionList> getSaveSearchInspectionList() {
		return saveSearchInspectionList;
	}

	/**
	 * 検索結果リスト保存用を設定する
	 * @param saveSearchInspectionList 検索結果リスト保存用
	 */
	public void setSaveSearchInspectionList(
			final List<GrecipeRecipeInspectionList> saveSearchInspectionList) {
		this.saveSearchInspectionList = saveSearchInspectionList;
	}

	/**
	 * 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getCount() {
		int count = 0;
		if (searchInspectionList != null) {
			count = searchInspectionList.size();
		}
		return count;
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
