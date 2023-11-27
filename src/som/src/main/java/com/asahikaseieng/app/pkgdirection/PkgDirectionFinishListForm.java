/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;

/**
 * 包装指図－仕上一覧画面 Formクラス
 * @author tosco
 */
public class PkgDirectionFinishListForm extends AbstractPkgDirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 検索結果リスト */
	private List<PkgDirectionDirectionFormulaList> searchFinishList;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> stepNoCombo;

	/** 区分コンボボックス */
	private List<ComboBoxItems> lineTypeCombo;

	/** 小数点桁数 */
	private String decimalPointHaigo;

	/** 端数区分 */
	private String roundDivisionHaigo;

	/** 工程検索行番号 */
	private String line;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionFinishListForm() {
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
		// 検索結果リスト
		setSearchFinishList(null);
		// 工程順序コンボボックス
		setStepNoCombo(null);
		// 区分コンボボックス
		setLineTypeCombo(null);
		// 小数点桁数
		setDecimalPointHaigo(null);
		// 端数区分
		setRoundDivisionHaigo(null);
		// 工程検索行番号
		setLine(null);
	}

	//getter,setter

	/**
	 * 検索結果リストを取得します。
	 * @return searchFinishList
	 */
	public List<PkgDirectionDirectionFormulaList> getSearchFinishList() {
		return searchFinishList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFinishList 検索結果リスト
	 */
	public void setSearchFinishList(final List<PkgDirectionDirectionFormulaList> searchFinishList) {
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
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFinishList() != null) {
			for (PkgDirectionDirectionFormulaList bean : getSearchFinishList()) {
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
	 * 小数点桁数を取得します。
	 * @return decimalPointHaigo 小数点桁数
	 */
	public String getDecimalPointHaigo() {
		return decimalPointHaigo;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPointHaigo 小数点桁数
	 */
	public void setDecimalPointHaigo(final String decimalPointHaigo) {
		this.decimalPointHaigo = decimalPointHaigo;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivisionHaigo 端数区分
	 */
	public String getRoundDivisionHaigo() {
		return roundDivisionHaigo;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivisionHaigo 端数区分
	 */
	public void setRoundDivisionHaigo(final String roundDivisionHaigo) {
		this.roundDivisionHaigo = roundDivisionHaigo;
	}

}
