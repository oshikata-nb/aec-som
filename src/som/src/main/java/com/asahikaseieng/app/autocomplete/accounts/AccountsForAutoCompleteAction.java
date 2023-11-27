/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accounts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.accounts.AccountsForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 科目マスタのAuto Complete用アクション
 * @author tosco
 */
public class AccountsForAutoCompleteAction extends AbstractAction {

	/** 科目マスタのAuto Complete用ロジック */
	private AccountsForAutoCompleteLogic accountsForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public AccountsForAutoCompleteAction() {
	}

	//科目コード・科目名称で検索-----------------------------------------------
	/**
	 * 科目マスタ検索
	 * 科目名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchAccounts(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchAccounts.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		//科目マスタを科目コードまたは科目名称で検索
		try {
			List<AccountsForAutoComplete> result =
				accountsForAutoCompleteLogic.getSearchList(frm.getCode());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("科目マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<AccountsForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (AccountsForAutoComplete bean : result) {
			GeneralParameterBean accounts = new GeneralParameterBean();
			transferEntityData(bean, accounts);
			list.add(accounts);
		}
		return list;
	}

	/**
	 * 科目マスタデータをオートコンプリート用Beanに移送する
	 * @param source 科目マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final AccountsForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getAccountsCd());
		dest.setName(source.getAccountsName());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 科目マスタのAuto Complete用ロジックを設定します。
	 * @param accountsForAutoCompleteLogic 科目マスタのAuto Complete用ロジック
	 */
	public void setAccountsForAutoCompleteLogic(
			final AccountsForAutoCompleteLogic accountsForAutoCompleteLogic) {
		this.accountsForAutoCompleteLogic = accountsForAutoCompleteLogic;
	}

}
