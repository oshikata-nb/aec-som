/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.operation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchList;
import com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 工程マスタ検索(ポップアップ) Formクラス.
 * @author tosco
 */
public final class OperationSearchForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 工程コード */
	private String srhOperationCd;

	/** 工程名称 */
	private String srhOperationName;

	/** 用途 */
	private String srhRecipeUse;

	/** 用途名称 */
	private String recipeUseName;

	/** 検索結果リスト */
	private List<OperationSearchList> searchList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.common"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.common"));
	}

	/**
	 * コンストラクタ.
	 */
	public OperationSearchForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return OperationSearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			setSearchList(new ArrayList<OperationSearchList>());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {

		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * 工程コードを取得します。
	 * @return String 工程コード
	 */
	public String getSrhOperationCd() {
		return srhOperationCd;
	}

	/**
	 * 工程コードを設定します。
	 * @param srhOperationCd 工程コード
	 */
	public void setSrhOperationCd(final String srhOperationCd) {
		this.srhOperationCd = srhOperationCd;
	}

	/**
	 * 工程名称を取得します。
	 * @return String 工程名称
	 */
	public String getSrhOperationName() {
		return srhOperationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param srhOperationName 工程名称
	 */
	public void setSrhOperationName(final String srhOperationName) {
		this.srhOperationName = srhOperationName;
	}

	/**
	 * 用途を取得します。
	 * @return String 用途
	 */
	public String getSrhRecipeUse() {
		return srhRecipeUse;
	}

	/**
	 * 用途を設定します。
	 * @param srhRecipeUse 用途
	 */
	public void setSrhRecipeUse(final String srhRecipeUse) {
		this.srhRecipeUse = srhRecipeUse;
	}

	/**
	 * 用途名称を取得します。
	 * @return recipeUseName
	 */
	public String getRecipeUseName() {
		return recipeUseName;
	}

	/**
	 * 用途名称を設定します。
	 * @param recipeUseName 用途名称
	 */
	public void setRecipeUseName(final String recipeUseName) {
		this.recipeUseName = recipeUseName;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<OperationSearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(final List<OperationSearchList> searchList) {
		this.searchList = searchList;
	}

}
