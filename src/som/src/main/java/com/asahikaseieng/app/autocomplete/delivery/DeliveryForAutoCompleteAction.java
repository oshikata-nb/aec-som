/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.DeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 納入先マスタのAuto Complete用アクション
 * @author tosco
 */
public class DeliveryForAutoCompleteAction extends AbstractAction {

	/** 納入先マスタのAuto Complete用ロジック */
	private DeliveryForAutoCompleteLogic deliveryForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public DeliveryForAutoCompleteAction() {
	}

	// 納入先コード・納入先名称で検索-----------------------------------------------
	/**
	 * 納入先マスタ検索 納入先名称1
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDelivery(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDelivery.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		// 納入先マスタを納入先コードまたは納入先名称で検索
		try {
			List<DeliveryForAutoComplete> result = deliveryForAutoCompleteLogic
					.getSearchList(frm.getCode());
			// Formに設定する

			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合

			log.debug("納入先マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 納入先マスタ検索 納入先名称2
	 * AECS佐藤 設定した区分の納入先のみ表示 2020/01/23
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDeliveryDivision(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDeliveryDivision.");
		}
		DeliveryForAutoCompleteForm frm = (DeliveryForAutoCompleteForm) form;
		// 納入先マスタを納入先コードまたは納入先名称で検索
		try {
			List<DeliveryForAutoComplete> result = deliveryForAutoCompleteLogic
					.getDeliverySearchList(frm.getCode(),frm.getDeliveryDivision());
			// Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
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
	 * 
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(
			final List<DeliveryForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (DeliveryForAutoComplete bean : result) {
			GeneralParameterBean operation = new GeneralParameterBean();
			transferEntityData(bean, operation);
			list.add(operation);
		}
		return list;
	}

	/**
	 * 納入先マスタデータをオートコンプリート用Beanに移送する
	 * 
	 * @param source 納入先マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final DeliveryForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getDeliveryCd());
		dest.setName(source.getSearchKana());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 納入先マスタのAuto Complete用ロジックを設定します。
	 * 
	 * @param deliveryForAutoCompleteLogic 納入先マスタのAuto Complete用ロジック
	 */
	public void setDeliveryForAutoCompleteLogic(
			final DeliveryForAutoCompleteLogic deliveryForAutoCompleteLogic) {
		this.deliveryForAutoCompleteLogic = deliveryForAutoCompleteLogic;
	}

}
