/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.financialclass;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.financialclass.FinancialClassForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 財務分類のAuto Complete用アクション
 * @author t0011036
 */
public class FinancialClassForAutoCompleteAction extends AbstractAction {

	/** 財務分類のAuto Complete用ロジック */
	private FinancialClassForAutoCompleteLogic financialClassAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public FinancialClassForAutoCompleteAction() {
	}

	/**
	 * 検索画面用財務分類検索 財務分類名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchFinancialClass(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchFinancialClass.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 財務分類を財務分類コードまたは財務分類名称で検索 */
		try {
			List<FinancialClassForAutoComplete> result = financialClassAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<FinancialClassForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("財務分類検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<FinancialClassForAutoCompleteBean> getAutoCompleteBean(
			final List<FinancialClassForAutoComplete> result) {
		List<FinancialClassForAutoCompleteBean> list = new ArrayList<FinancialClassForAutoCompleteBean>();

		for (FinancialClassForAutoComplete bean : result) {
			FinancialClassForAutoCompleteBean item = new FinancialClassForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 財務分類データをオートコンプリート用Beanに移送する
	 * @param source財務分類データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private FinancialClassForAutoCompleteBean transferEntityData(
			final FinancialClassForAutoComplete source,
			final FinancialClassForAutoCompleteBean dest) {
		dest.setCode(source.getFinancialClassCd());
		dest.setName(source.getFinancialClassName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * financialClassAutoCompleteLogicを設定します。
	 * @param financialClassAutoCompleteLogic financialClassAutoCompleteLogic
	 */
	public void setFinancialClassAutoCompleteLogic(
			final FinancialClassForAutoCompleteLogic financialClassAutoCompleteLogic) {
		this.financialClassAutoCompleteLogic = financialClassAutoCompleteLogic;
	}
}
