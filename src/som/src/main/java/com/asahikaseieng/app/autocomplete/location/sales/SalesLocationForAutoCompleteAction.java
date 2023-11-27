/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.location.sales.SalesLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 売上用ロケーションマスタのAuto Complete用アクション
 * @author tosco
 */
public class SalesLocationForAutoCompleteAction extends AbstractAction {

	/** 売上用ロケーションのAuto Complete用ロジック */
	private SalesLocationForAutoCompleteLogic salesLocationForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public SalesLocationForAutoCompleteAction() {
	}
	//ロケーションコード・ロケーション名称で検索-----------------------------------------------
	/**
	 * 売上画面用ロケーションマスタ検索
	 * ロケーションコード・ロケーション名称
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
		SalesLocationForAutoCompleteForm frm = (SalesLocationForAutoCompleteForm) form;
		//ロケーションマスタロケーションコードまたはロケーション名称で検索
		try {
			List<SalesLocationForAutoComplete> result =
				salesLocationForAutoCompleteLogic.getSearchList(
						frm.getCode(), frm.getItemCd());
			//Formに設定する			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合			log.debug("ロケーションマスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<SalesLocationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (SalesLocationForAutoComplete bean : result) {
			GeneralParameterBean location = new GeneralParameterBean();
			transferEntityData(bean, location);
			list.add(location);
		}
		return list;
	}

	/**
	 * ロケーションマスタデータをオートコンプリート用Beanに移送する	 * @param source ロケーションマスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final SalesLocationForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getLocationCd());
		dest.setName(source.getLocationName());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 売上用ロケーションのAuto Complete用ロジックを設定します。	 * @param salesLocationForAutoCompleteLogic 売上用ロケーションのAuto Complete用ロジック
	 */
	public void setSalesLocationForAutoCompleteLogic(
			final SalesLocationForAutoCompleteLogic salesLocationForAutoCompleteLogic) {
		this.salesLocationForAutoCompleteLogic = salesLocationForAutoCompleteLogic;
	}

}
