/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.location.LocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * ロケーションのAuto Complete用アクション
 * @author t0011036
 */
public class LocationForAutoCompleteAction extends AbstractAction {

	/** ロケーションのAuto Complete用ロジック */
	private LocationForAutoCompleteLogic locationForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public LocationForAutoCompleteAction() {
	}

	/**
	 * 検索画面用ロケーション検索 ロケーション名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchLocation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchLocation.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* ロケーションをロケーションコードまたはロケーション名称で検索 */
		try {
			List<LocationForAutoComplete> result = locationForAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("ロケーション検索結果なし");
		}

		return mapping.findForward("success");
	}

	/**
	 * 利用可能ロケーション検索 ロケーション名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchAvailableLocation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchAvailableLocation.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* ロケーションをロケーションコードまたはロケーション名称で検索 */
		try {
			List<LocationForAutoComplete> result = locationForAutoCompleteLogic
					.getSearchAvailableList(paramForm.getCode());

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("利用可能ロケーション検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(
			final List<LocationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();

		for (LocationForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * ロケーションデータをオートコンプリート用Beanに移送する
	 * @param sourceロケーションデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final LocationForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getLocationCd());
		dest.setName(source.getLocationName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * locationForAutoCompleteLogicを設定します。
	 * @param locationForAutoCompleteLogic locationForAutoCompleteLogic
	 */
	public void setLocationForAutoCompleteLogic(
			final LocationForAutoCompleteLogic locationForAutoCompleteLogic) {
		this.locationForAutoCompleteLogic = locationForAutoCompleteLogic;
	}
}
