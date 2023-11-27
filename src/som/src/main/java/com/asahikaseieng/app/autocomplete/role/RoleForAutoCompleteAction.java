/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.role.RoleForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * ロールのAuto Complete用アクション
 * @author t0011036
 */
public class RoleForAutoCompleteAction extends AbstractAction {

	/** ロールのAuto Complete用ロジック */
	private RoleForAutoCompleteLogic roleAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public RoleForAutoCompleteAction() {
	}

	/**
	 * 検索画面用ロール検索 ロール名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchRole(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchRole.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* ロールをロールコードまたはロール名称で検索 */
		try {
			List<RoleForAutoComplete> result = roleAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<RoleForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("ロール検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<RoleForAutoCompleteBean> getAutoCompleteBean(
			final List<RoleForAutoComplete> result) {
		List<RoleForAutoCompleteBean> list = new ArrayList<RoleForAutoCompleteBean>();

		for (RoleForAutoComplete bean : result) {
			RoleForAutoCompleteBean item = new RoleForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * ロールデータをオートコンプリート用Beanに移送する
	 * @param sourceロールデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private RoleForAutoCompleteBean transferEntityData(
			final RoleForAutoComplete source, final RoleForAutoCompleteBean dest) {
		dest.setCode(source.getRoleId().toString());
		dest.setName(source.getRoleName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * roleAutoCompleteLogicを設定します。
	 * @param roleAutoCompleteLogic roleAutoCompleteLogic
	 */
	public void setRoleAutoCompleteLogic(
			final RoleForAutoCompleteLogic roleAutoCompleteLogic) {
		this.roleAutoCompleteLogic = roleAutoCompleteLogic;
	}
}
