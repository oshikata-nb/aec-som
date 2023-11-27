/*
 * Created on 2008/10/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.cost.costproduct;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 
 * 原価積上処理Form
 * @author tosco
 */
public class CostProductForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.原価積上処理Form
	 */
	public CostProductForm() {
	}

	//
	// インスタンスフィールド
	//

	/** 処方有効日：文字列 */
	private String strRecipeDate;

	/** 処方有効日：日付型 */
	private Date recipeDate;

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
	private void clear() {
		// 処方有効日：文字列
		setStrRecipeDate(null);
		// 処方有効日：日付型
		setRecipeDate(null);

	}


	//	
	// インスタンスメソッド
	//		

	/**
	 * startDateを取得します。
	 * @return strRecipeDate
	 */
	public String getStrRecipeDate() {
		return strRecipeDate;
	}

	/**
	 * startDateを設定します。
	 * @param strRecipeDate strRecipeDate
	 */
	public void setStrRecipeDate(final String strRecipeDate) {
		this.strRecipeDate = strRecipeDate;
	}

	/**
	 * startDateを取得します。
	 * @return recipeDate
	 */
	public Date getRecipeDate() {
		return recipeDate;
	}

	/**
	 * startDateを設定します。
	 * @param recipeDate recipeDate
	 */
	public void setRecipeDate(final Date recipeDate) {
		this.recipeDate = recipeDate;
	}

}
