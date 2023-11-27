/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.location.order.OrderLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 出荷指図用ロケーションマスタのAuto Complete用アクション
 * @author tosco
 */
public class OrderLocationForAutoCompleteAction extends AbstractAction {

	/** 出荷指図用ロケーションのAuto Complete用ロジック */
	private OrderLocationForAutoCompleteLogic orderLocationForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public OrderLocationForAutoCompleteAction() {
	}

	// ロケーションコード・ロケーション名称で検索-----------------------------------------------
	/**
	 * 出荷指図画面用ロケーションマスタ検索 ロケーションコード・ロケーション名称
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
		OrderLocationForAutoCompleteForm frm = (OrderLocationForAutoCompleteForm) form;
		// ロケーションマスタロケーションコードまたはロケーション名称で検索
		try {
			List<OrderLocationForAutoComplete> result = orderLocationForAutoCompleteLogic
					.getSearchList(frm.getCode(), frm.getItemCd());
			// Formに設定する

			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合

			log.debug("ロケーションマスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 * 
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(
			final List<OrderLocationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (OrderLocationForAutoComplete bean : result) {
			GeneralParameterBean location = new GeneralParameterBean();
			transferEntityData(bean, location);
			list.add(location);
		}
		return list;
	}

	/**
	 * ロケーションマスタデータをオートコンプリート用Beanに移送する
	 * 
	 * @param source ロケーションマスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final OrderLocationForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getLocationCd());
		dest.setName(source.getLocationName());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 受注用ロケーションのAuto Complete用ロジックを設定します。
	 * 
	 * @param orderLocationForAutoCompleteLogic 受注用ロケーションのAuto Complete用ロジック
	 */
	public void setOrderLocationForAutoCompleteLogic(
			final OrderLocationForAutoCompleteLogic orderLocationForAutoCompleteLogic) {
		this.orderLocationForAutoCompleteLogic = orderLocationForAutoCompleteLogic;
	}

}
