/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.company.CompanyForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 自社のAuto Complete用アクション
 * @author t0011036
 */
public class CompanyForAutoCompleteAction extends AbstractAction {

	/** 自社のAuto Complete用ロジック */
	private CompanyForAutoCompleteLogic companyAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public CompanyForAutoCompleteAction() {
	}

	/**
	 * 検索画面用自社検索 自社名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchCompany(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchCompany.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 自社を自社コードまたは自社名称で検索 */
		try {
			List<CompanyForAutoComplete> result = companyAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<CompanyForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("自社検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<CompanyForAutoCompleteBean> getAutoCompleteBean(
			final List<CompanyForAutoComplete> result) {
		List<CompanyForAutoCompleteBean> list = new ArrayList<CompanyForAutoCompleteBean>();

		for (CompanyForAutoComplete bean : result) {
			CompanyForAutoCompleteBean item = new CompanyForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 自社データをオートコンプリート用Beanに移送する
	 * @param source自社データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private CompanyForAutoCompleteBean transferEntityData(
			final CompanyForAutoComplete source,
			final CompanyForAutoCompleteBean dest) {
		dest.setCode(source.getCompanyCd());
		dest.setName(source.getHomeName1());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * companyAutoCompleteLogicを設定します。
	 * @param companyAutoCompleteLogic companyAutoCompleteLogic
	 */
	public void setCompanyAutoCompleteLogic(
			final CompanyForAutoCompleteLogic companyAutoCompleteLogic) {
		this.companyAutoCompleteLogic = companyAutoCompleteLogic;
	}
}
