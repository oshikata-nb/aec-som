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
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;

/**
 * 仕上げ一覧タブ Formクラス.
 * @author tosco
 */
public final class GrecipeFinishListForm extends AbstractGrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 検索結果リスト */
	private List<GrecipeRecipeFormulaList> searchFinishList;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> stepNoCombo;

	/** 区分コンボボックス */
	private List<ComboBoxItems> lineTypeCombo;

	/** 工程検索行番号 */
	private String line;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public GrecipeFinishListForm() {
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
		if ("regist".equals(getOp())) {
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
		// 検索結果リスト
		setSearchFinishList(null);
		// 工程順序コンボボックス
		setStepNoCombo(null);
		// 区分コンボボックス
		setLineTypeCombo(null);
		// 工程検索行番号
		setLine(null);

		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRoundDivision(null);
	}

	// getter,setter

	/**
	 * 検索結果リストを取得します。
	 * @return searchFinishList
	 */
	public List<GrecipeRecipeFormulaList> getSearchFinishList() {
		return searchFinishList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFinishList 検索結果リスト
	 */
	public void setSearchFinishList(
			final List<GrecipeRecipeFormulaList> searchFinishList) {
		this.searchFinishList = searchFinishList;
	}

	/**
	 * 工程順序コンボボックスを取得します。
	 * @return stepNoCombo
	 */
	public List<ComboBoxItems> getStepNoCombo() {
		return stepNoCombo;
	}

	/**
	 * 工程順序コンボボックスを設定します。
	 * @param stepNoCombo 工程順序コンボボックス
	 */
	public void setStepNoCombo(final List<ComboBoxItems> stepNoCombo) {
		this.stepNoCombo = stepNoCombo;
	}

	/**
	 * 工程検索行番号を取得します。
	 * @return line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 工程検索行番号を設定します。
	 * @param line 工程検索行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * 区分コンボボックスを取得します。
	 * @return lineTypeCombo
	 */
	public List<ComboBoxItems> getLineTypeCombo() {
		return lineTypeCombo;
	}

	/**
	 * 区分コンボボックスを設定します。
	 * @param lineTypeCombo 区分コンボボックス
	 */
	public void setLineTypeCombo(final List<ComboBoxItems> lineTypeCombo) {
		this.lineTypeCombo = lineTypeCombo;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return decimalPoint
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFinishList() != null) {
			for (GrecipeRecipeFormulaList bean : getSearchFinishList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * 検索結果の件数を取得する
	 * @return 件数
	 */
	public int getCount() {
		int count = 0;
		if (searchFinishList != null) {
			count = searchFinishList.size();
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
