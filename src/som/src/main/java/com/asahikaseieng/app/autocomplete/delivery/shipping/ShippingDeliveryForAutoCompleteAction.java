/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.shipping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping.ShippingDeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 納入先マスタのAuto Complete用アクション
 * @author tosco
 */
public class ShippingDeliveryForAutoCompleteAction extends AbstractAction {

	/** 納入先マスタのAuto Complete用ロジック */
	private ShippingDeliveryForAutoCompleteLogic shippingOrderDeliveryForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ShippingDeliveryForAutoCompleteAction() {
	}

	// 納入先コード・納入先名称で検索-----------------------------------------------
	/**
	 * 検索画面用納入先マスタ検索 納入先名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchShippingDelivery(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchShippingDelivery.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 納入先マスタを納入先コードまたは納入先名称で検索
		try {
			List<ShippingDeliveryForAutoComplete> result = shippingOrderDeliveryForAutoCompleteLogic
					.getSearchList(paramForm.getCode());
			// Formに設定する

			List<ShippingDeliveryForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("納入先マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ShippingDeliveryForAutoCompleteBean> getAutoCompleteBean(
			final List<ShippingDeliveryForAutoComplete> result) {
		List<ShippingDeliveryForAutoCompleteBean> list = new ArrayList<ShippingDeliveryForAutoCompleteBean>();
		for (ShippingDeliveryForAutoComplete bean : result) {
			ShippingDeliveryForAutoCompleteBean item = new ShippingDeliveryForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 納入先マスタデータをオートコンプリート用Beanに移送する
	 * @param source 納入先マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final ShippingDeliveryForAutoComplete source,
			final ShippingDeliveryForAutoCompleteBean dest) {
		dest.setCode(source.getDeliveryCd());
		dest.setName(source.getSearchKana());
		dest.setCarryCd(source.getCarryCd());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 納入先マスタのAuto Complete用ロジックを設定します。
	 * @param shippingOrderDeliveryForAutoCompleteLogic 納入先マスタのAuto
	 *            Complete用ロジック
	 */
	public void setShippingOrderDeliveryForAutoCompleteLogic(
			final ShippingDeliveryForAutoCompleteLogic shippingOrderDeliveryForAutoCompleteLogic) {
		this.shippingOrderDeliveryForAutoCompleteLogic = shippingOrderDeliveryForAutoCompleteLogic;
	}

}
