/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.purchaseorder;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder.PurchaseOrderDeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 納入先マスタのAuto Complete用アクション
 * @author tosco
 */
public class PurchaseOrderDeliveryForAutoCompleteAction extends AbstractAction {

	/** 納入先マスタのAuto Complete用ロジック */
	private PurchaseOrderDeliveryForAutoCompleteLogic purchaseOrderDeliveryForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public PurchaseOrderDeliveryForAutoCompleteAction() {
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
	public ActionForward searchPurchaseOrderDelivery(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchPurchaseOrderDelivery.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 納入先マスタを納入先コードまたは納入先名称で検索
		try {
			List<PurchaseOrderDeliveryForAutoComplete> result = purchaseOrderDeliveryForAutoCompleteLogic
					.getSearchList(paramForm.getCode());
			// Formに設定する

			List<GeneralParameterBean> list = getAutoCompleteBean(result);
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
	private List<GeneralParameterBean> getAutoCompleteBean(
			final List<PurchaseOrderDeliveryForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (PurchaseOrderDeliveryForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
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
			final PurchaseOrderDeliveryForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getDeliveryCd());
		dest.setName(source.getSearchKana());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 納入先マスタのAuto Complete用ロジックを設定します。
	 * @param purchaseOrderDeliveryForAutoCompleteLogic 納入先マスタのAuto
	 *            Complete用ロジック
	 */
	public void setPurchaseOrderDeliveryForAutoCompleteLogic(
			final PurchaseOrderDeliveryForAutoCompleteLogic purchaseOrderDeliveryForAutoCompleteLogic) {
		this.purchaseOrderDeliveryForAutoCompleteLogic = purchaseOrderDeliveryForAutoCompleteLogic;
	}

}
