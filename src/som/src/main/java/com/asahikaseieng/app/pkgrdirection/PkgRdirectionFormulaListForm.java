/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;

/**
 * 包装実績－配合一覧画面 Formクラス
 * @author tosco
 */
public class PkgRdirectionFormulaListForm extends AbstractPkgRdirectionForm {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> seqCombo;

	/** 工程順序 */
	private String procStepNo;

	/** TEMP 工程順序 */
	private String tempProcStepNo;

	/** 単位 */
	private String unitName;

	/** 単位区分 */
	private String headUnitDiv;

	/** 検索結果リスト */
	private List<PkgRdirectionDirectionFormulaList> searchFormList;

	/** 配合検索行番号 */
	private String line;

	/** 行削除リスト */
	private List<PkgRdirectionDirectionFormulaList> delFormList;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionFormulaListForm() {
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
		ActionErrors errors = new ActionErrors();
		if ("search".equals(getOp())) {
			// 一覧の初期化
			setSearchFormList(new ArrayList<PkgRdirectionDirectionFormulaList>());
			setDelFormList(new ArrayList<PkgRdirectionDirectionFormulaList>());
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	@Override
	protected void clear() {
		super.clear();
		// 工程順序コンボボックス
		setSeqCombo(null);
		// 工程順序
		setProcStepNo(null);
		// TEMP 工程順序
		setTempProcStepNo(null);
		// 単位
		setUnitName(null);
		// 検索結果リスト
		setSearchFormList(null);
		// 配合検索行番号
		setLine(null);
		// 行削除リスト
		setDelFormList(null);
	}

	/**
	 * 工程順序コンボボックスを取得します。
	 * @return 工程順序コンボボックス
	 */
	public List<ComboBoxItems> getSeqCombo() {
		return seqCombo;
	}

	/**
	 * 工程順序コンボボックスを設定します。
	 * @param seqCombo 工程順序コンボボックス
	 */
	public void setSeqCombo(final List<ComboBoxItems> seqCombo) {
		this.seqCombo = seqCombo;
	}

	/**
	 * 工程順序を取得します。
	 * @return procStepNo
	 */
	public String getProcStepNo() {
		return procStepNo;
	}

	/**
	 * 工程順序を設定します。
	 * @param procStepNo 工程順序
	 */
	public void setProcStepNo(final String procStepNo) {
		this.procStepNo = procStepNo;
	}

	/**
	 * 単位を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定します。
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 単位区分を取得します。
	 * @return headUnitDiv
	 */
	public String getHeadUnitDiv() {
		return headUnitDiv;
	}

	/**
	 * 単位区分を設定します。
	 * @param headUnitDiv 単位区分
	 */
	public void setHeadUnitDiv(final String headUnitDiv) {
		this.headUnitDiv = headUnitDiv;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return 検索結果リスト
	 */
	public List<PkgRdirectionDirectionFormulaList> getSearchFormList() {
		return searchFormList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFormList 検索結果リスト
	 */
	public void setSearchFormList(final List<PkgRdirectionDirectionFormulaList> searchFormList) {
		this.searchFormList = searchFormList;
	}

	/**
	 * 工程検索行番号取得
	 * @return String 工程検索行番号
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 工程検索行番号設定
	 * @param line 工程検索行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * 行削除リストを取得します。
	 * @return delFormList
	 */
	public List<PkgRdirectionDirectionFormulaList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(final List<PkgRdirectionDirectionFormulaList> delFormList) {
		this.delFormList = delFormList;
	}

	/**
	 * TEMP 工程順序を取得します。
	 * @return tempProcStepNo
	 */
	public String getTempProcStepNo() {
		return tempProcStepNo;
	}

	/**
	 * TEMP 工程順序を設定します。
	 * @param tempProcStepNo TEMP 工程順序
	 */
	public void setTempProcStepNo(final String tempProcStepNo) {
		this.tempProcStepNo = tempProcStepNo;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFormList() != null) {
			for (PkgRdirectionDirectionFormulaList bean : getSearchFormList()) {
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
		if (searchFormList != null) {
			count = searchFormList.size();
		}
		return count;
	}
}
