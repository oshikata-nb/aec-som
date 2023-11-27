/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accountssub;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.accountssub.AccountsSubForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 補助科目のAuto Complete用アクション
 * @author t0011036
 */
public class AccountsSubForAutoCompleteAction extends AbstractAction {

	/** 補助科目のAuto Complete用ロジック */
	private AccountsSubForAutoCompleteLogic accountsSubAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public AccountsSubForAutoCompleteAction() {
	}

	/**
	 * 検索画面用補助科目検索 補助科目名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchAccountsSub(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchAccountsSub.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 補助科目を補助科目コードまたは補助科目名称で検索 */
		try {
			List<AccountsSubForAutoComplete> result = accountsSubAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<AccountsSubForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("補助科目検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<AccountsSubForAutoCompleteBean> getAutoCompleteBean(
			final List<AccountsSubForAutoComplete> result) {
		List<AccountsSubForAutoCompleteBean> list = new ArrayList<AccountsSubForAutoCompleteBean>();

		for (AccountsSubForAutoComplete bean : result) {
			AccountsSubForAutoCompleteBean item = new AccountsSubForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 補助科目データをオートコンプリート用Beanに移送する
	 * @param source補助科目データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private AccountsSubForAutoCompleteBean transferEntityData(
			final AccountsSubForAutoComplete source,
			final AccountsSubForAutoCompleteBean dest) {
		dest.setCode(source.getAccountsSubCd());
		dest.setName(source.getAccountsSubName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * accountsSubAutoCompleteLogicを設定します。
	 * @param accountsSubAutoCompleteLogic accountsSubAutoCompleteLogic
	 */
	public void setAccountsSubAutoCompleteLogic(
			final AccountsSubForAutoCompleteLogic accountsSubAutoCompleteLogic) {
		this.accountsSubAutoCompleteLogic = accountsSubAutoCompleteLogic;
	}
}
