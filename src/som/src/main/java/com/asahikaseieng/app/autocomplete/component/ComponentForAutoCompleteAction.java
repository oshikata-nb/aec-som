/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.component;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.component.ComponentForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 成分のAuto Complete用アクション
 * @author t0011036
 */
public class ComponentForAutoCompleteAction extends AbstractAction {

	/** 成分のAuto Complete用ロジック */
	private ComponentForAutoCompleteLogic componentAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ComponentForAutoCompleteAction() {
	}

	/**
	 * 検索画面用成分検索 成分名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchComponent(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchComponent.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 成分を成分コードまたは成分名称で検索 */
		try {
			List<ComponentForAutoComplete> result = componentAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<ComponentForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("成分検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ComponentForAutoCompleteBean> getAutoCompleteBean(
			final List<ComponentForAutoComplete> result) {
		List<ComponentForAutoCompleteBean> list = new ArrayList<ComponentForAutoCompleteBean>();

		for (ComponentForAutoComplete bean : result) {
			ComponentForAutoCompleteBean item = new ComponentForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 成分データをオートコンプリート用Beanに移送する
	 * @param source成分データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private ComponentForAutoCompleteBean transferEntityData(
			final ComponentForAutoComplete source,
			final ComponentForAutoCompleteBean dest) {
		dest.setCode(source.getComponentCd());
		dest.setName(source.getComponentName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * componentAutoCompleteLogicを設定します。
	 * @param componentAutoCompleteLogic componentAutoCompleteLogic
	 */
	public void setComponentAutoCompleteLogic(
			final ComponentForAutoCompleteLogic componentAutoCompleteLogic) {
		this.componentAutoCompleteLogic = componentAutoCompleteLogic;
	}
}
