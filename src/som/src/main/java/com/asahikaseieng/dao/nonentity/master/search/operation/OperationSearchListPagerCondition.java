/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.operation;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 工程マスタ検索(ポップアップ)検索条件
 * @author tosco
 */
public class OperationSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OperationSearchListPagerCondition() {
	}

	/** 工程コード */
	private String operationCd;

	/** 工程名称 */
	private String operationName;

	/** 用途 */
	private String recipeUse;

	/**
	 * 工程コードを取得します。
	 * @return String 工程コード
	 */
	public String getOperationCd() {
		return operationCd;
	}

	/**
	 * 工程コードを設定します。
	 * @param operationCd 工程コード
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = AecTextUtils.likeFilter(operationCd);
	}

	/**
	 * 工程名称を取得します。
	 * @return String 工程名称
	 */
	public String getOperationName() {
		return operationName;
	}

	/**
	 * 工程名称を設定します。
	 * @param operationName 工程名称
	 */
	public void setOperationName(final String operationName) {
		this.operationName = AecTextUtils.likeFilter(operationName);
	}

	/**
	 * 用途を取得します。
	 * @return String 用途
	 */
	public String getRecipeUse() {
		return recipeUse;
	}

	/**
	 * 用途を設定します。
	 * @param recipeUse 用途
	 */
	public void setRecipeUse(final String recipeUse) {
		this.recipeUse = recipeUse;
	}
}
