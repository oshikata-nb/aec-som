/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 受注用品目マスタのAuto Complete用アクション
 * @author tosco
 */
public class OrderItemForAutoCompleteAction extends AbstractAction {

	/** 品目マスタのAuto Complete用ロジック */
	private OrderItemForAutoCompleteLogic orderItemForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public OrderItemForAutoCompleteAction() {
	}

	//品目コード・品目名称で検索-----------------------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * 品目名称・他社コード１・荷姿・運用管理単位	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItem(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchItem.");
		}
		OrderItemForAutoCompleteForm paramForm = (OrderItemForAutoCompleteForm) form;
		//品目マスタを品目コードまたは品目名称で検索
		try {
			List<OrderItemForAutoComplete> result =
				orderItemForAutoCompleteLogic.getDetailList(
					paramForm.getCode(), paramForm.getDeliveryCd()
					, paramForm.getOrderDivision(), paramForm.getBalanceCd());

			//Formに設定する			List<OrderItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//他社コード１で検索-----------------------------------------------
	/**
	 * 検索画面用品目マスタ検索(他社コード１で検索)
	 * 品目名称・他社コード１・荷姿・運用管理単位	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItemOtherCompany(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchOtherCompany1Item.");
		}
		OrderItemForAutoCompleteForm paramForm = (OrderItemForAutoCompleteForm) form;
		//品目マスタを他社コード１で検索
		try {
			List<OrderItemForAutoComplete> result =
				orderItemForAutoCompleteLogic.getDetailOtherCompany1List(
					paramForm.getCode(), paramForm.getDeliveryCd()
					, paramForm.getOrderDivision(), paramForm.getBalanceCd());


			//Formに設定する			List<OrderItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("successOtherCompany1");
	}


	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト	 */
	private List<OrderItemForAutoCompleteBean> getAutoCompleteBean(
		final List<OrderItemForAutoComplete> result) {

		List<OrderItemForAutoCompleteBean> list = new ArrayList<OrderItemForAutoCompleteBean>();

		for (OrderItemForAutoComplete bean : result) {
			OrderItemForAutoCompleteBean item = new OrderItemForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 品目マスタデータをオートコンプリート用Beanに移送する	 * @param source 品目マスタキューデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private OrderItemForAutoCompleteBean transferEntityData(
			final OrderItemForAutoComplete source, final OrderItemForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest.setUnitOfOperationManagement(source.getUnitOfOperationManagement());
		// 帳合コード
		dest.setBalanceCd(source.getBalanceCd());

		return dest;
	}


	//------------------------------------------------------------------------------
	/**
	 * 品目マスタのAuto Complete用ロジックを設定します。
	 * @param orderItemForAutoCompleteLogic 品目マスタのAuto Complete用ロジック
	 */
	public void setOrderItemForAutoCompleteLogic(
			final OrderItemForAutoCompleteLogic orderItemForAutoCompleteLogic) {
		this.orderItemForAutoCompleteLogic = orderItemForAutoCompleteLogic;
	}

}
