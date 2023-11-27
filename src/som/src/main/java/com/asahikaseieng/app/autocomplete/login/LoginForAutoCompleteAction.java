/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * ログインマスタのAuto Complete用アクション
 * @author tosco
 */
public class LoginForAutoCompleteAction extends AbstractAction {

	/** ログインマスタのAuto Complete用ロジック */
	private LoginForAutoCompleteLogic loginForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public LoginForAutoCompleteAction() {
	}
	//ログインコード・ログイン名称で検索-----------------------------------------------
	/**
	 * 検索画面用ログインマスタ検索
	 * ログイン名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchLogin(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchLogin.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		//ログインマスタをログインコードまたはログイン名称で検索
		try {
			List<LoginForAutoComplete> result =
				loginForAutoCompleteLogic.getSearchList(frm.getCode());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("ログインマスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<LoginForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (LoginForAutoComplete bean : result) {
			GeneralParameterBean login = new GeneralParameterBean();
			transferEntityData(bean, login);
			list.add(login);
		}
		return list;
	}

	/**
	 * ログインマスタデータをオートコンプリート用Beanに移送する
	 * @param source ログインマスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final LoginForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getTantoCd());
		dest.setName(source.getTantoNm());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * ログインマスタのAuto Complete用ロジックを設定します。
	 * @param loginForAutoCompleteLogic ログインマスタのAuto Complete用ロジック
	 */
	public void setLoginForAutoCompleteLogic(
			final LoginForAutoCompleteLogic loginForAutoCompleteLogic) {
		this.loginForAutoCompleteLogic = loginForAutoCompleteLogic;
	}

}
