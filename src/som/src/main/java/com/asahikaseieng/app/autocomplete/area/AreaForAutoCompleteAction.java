/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.area;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.area.AreaForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 地区のAuto Complete用アクション
 * @author t0011036
 */
public class AreaForAutoCompleteAction extends AbstractAction {

	/** 地区のAuto Complete用ロジック */
	private AreaForAutoCompleteLogic areaAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public AreaForAutoCompleteAction() {
	}

	/**
	 * 検索画面用地区検索 地区名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchArea(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchArea.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 地区を地区コードまたは地区名称で検索 */
		try {
			List<AreaForAutoComplete> result = areaAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<AreaForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("地区検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<AreaForAutoCompleteBean> getAutoCompleteBean(
			final List<AreaForAutoComplete> result) {
		List<AreaForAutoCompleteBean> list = new ArrayList<AreaForAutoCompleteBean>();

		for (AreaForAutoComplete bean : result) {
			AreaForAutoCompleteBean item = new AreaForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 地区データをオートコンプリート用Beanに移送する
	 * @param source地区データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private AreaForAutoCompleteBean transferEntityData(
			final AreaForAutoComplete source, final AreaForAutoCompleteBean dest) {
		dest.setCode(source.getAreaCd());
		dest.setName(source.getAreaName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * areaAutoCompleteLogicを設定します。
	 * @param areaAutoCompleteLogic areaAutoCompleteLogic
	 */
	public void setAreaAutoCompleteLogic(
			final AreaForAutoCompleteLogic areaAutoCompleteLogic) {
		this.areaAutoCompleteLogic = areaAutoCompleteLogic;
	}
}
