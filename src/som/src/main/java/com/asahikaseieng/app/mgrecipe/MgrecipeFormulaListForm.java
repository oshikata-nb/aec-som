/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;

/**
 * 配合一覧タブ Formクラス.
 * @author tosco
 */
public final class MgrecipeFormulaListForm extends AbstractMgrecipeForm {

	private static final long serialVersionUID = 1L;

	/** 工程順序コンボボックス */
	private List<ComboBoxItems> seqCombo;

	/** 工程順序 */
	private String procStepNo;

	/** TEMP 工程順序 */
	private String tempProcStepNo;

	/** 配合量計 */
	private String totalQty;

	/** 単位 */
	private String unitName;

	/** 単位区分 */
	private String headUnitDiv;

	/** 検索結果リスト */
	private List<RecipeFormulaList> searchFormList;

	/** 配合検索行番号 */
	private String line;

	/** 行削除リスト */
	private List<RecipeFormulaList> delFormList;

	/** 表示時処方ヘッダー情報 */
	private RecipeHeaderList headerBean;

	/** リンク */
	private String srhLink;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeFormulaListForm() {
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
		if ("search".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setSearchFormList(new ArrayList<RecipeFormulaList>());
			setDelFormList(new ArrayList<RecipeFormulaList>());
		}
		if ("regist".equals(getOp())) {
			errors = new ActionErrors();
			// 品目コード、配合量チェック
			validateSearchListUPD(errors, request);
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
		// 配合量計
		setTotalQty(null);
		// 単位
		setUnitName(null);
		// 検索結果リスト
		setSearchFormList(null);
		// 配合検索行番号
		setLine(null);
		// 行削除リスト
		setDelFormList(null);
	}

	//
	// インスタンスメソッド
	//

	// getter,setter
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
	 * 配合量計を取得します。
	 * @return 配合量計
	 */
	public String getTotalQty() {
		return totalQty;
	}

	/**
	 * 配合量計を設定します。
	 * @param totalQty 配合量計
	 */
	public void setTotalQty(final String totalQty) {
		this.totalQty = totalQty;
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
	public List<RecipeFormulaList> getSearchFormList() {
		return searchFormList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchFormList 検索結果リスト
	 */
	public void setSearchFormList(final List<RecipeFormulaList> searchFormList) {
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
	public List<RecipeFormulaList> getDelFormList() {
		return delFormList;
	}

	/**
	 * 行削除リストを設定します。
	 * @param delFormList 行削除リスト
	 */
	public void setDelFormList(final List<RecipeFormulaList> delFormList) {
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
	 * 表示時処方ヘッダー情報を取得します。
	 * @return headerBean 表示時処方ヘッダー情報
	 */
	public RecipeHeaderList getHeaderBean() {
		return headerBean;
	}

	/**
	 * 表示時処方ヘッダー情報を設定します。
	 * @param headerBean 表示時処方ヘッダー情報
	 */
	public void setHeaderBean(final RecipeHeaderList headerBean) {
		this.headerBean = headerBean;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getSearchFormList() != null) {
			for (RecipeFormulaList bean : getSearchFormList()) {
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

	/**
	 * 登録時に一覧の入力項目(品目コード、配合量)の入力チェックを行う。
	 * @param errors エラー内容
	 * @param request リクエスト
	 */
	private void validateSearchListUPD(final ActionErrors errors,
			final HttpServletRequest request) {
		List<RecipeFormulaList> list = this.getSearchFormList();
		int index = 1;

		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 検索されていない状態での行削除処理はエラー
		if (list == null) {
			errors.add("", new ActionMessage("errors.mgrecipe.no.search"));
		} else {

			for (RecipeFormulaList bean : list) {

				// 品目コード
				// 必須チェック
				if (StringUtils.isEmpty(bean.getItemCd())) {
					errors.add("", new ActionMessage("errors.required.row", rb
							.getString("item.mgrecipe.product"), Integer
							.toString(index)));
				}

				// 配合量(数値桁数チェック)
				if (StringUtils.isEmpty(bean.getStrQty())) {
					errors.add("", new ActionMessage("errors.required.row", rb
							.getString("item.mgrecipe.formula.qty"), Integer
							.toString(index)));
				} else {
					CheckDigitUtilsLogic check = CheckDigitUtil
							.getCheckDigitUtils(request);
					ActionMessage message = check.checkDigitMessage("HAIGO",
						null, null, bean.getStrQty(), rb
								.getString("item.mgrecipe.formula.qty"));
					if (message != null) {
						errors.add("", message);
					}
				}
				index++;
			}

			List<RecipeFormulaList> delList = this.getDelFormList();
			// 更新の必要なし
			if (list.isEmpty() && (delList == null || delList.isEmpty())) {
				errors.add("", new ActionMessage("errors.nodata"));
			}
		}
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
