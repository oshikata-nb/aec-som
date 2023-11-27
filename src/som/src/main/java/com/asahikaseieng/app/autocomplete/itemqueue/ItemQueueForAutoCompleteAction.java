/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemqueue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.itemqueue.ItemQueueForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 品目マスタキューのAuto Complete用アクション
 * @author tosco
 */
public class ItemQueueForAutoCompleteAction extends AbstractAction {

	/** 品目マスタキューのAuto Complete用ロジック */
	private ItemQueueForAutoCompleteLogic itemQueueForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ItemQueueForAutoCompleteAction() {
	}

	// 品目コード・品目名称で検索-----------------------------------------------
	/**
	 * 検索画面用品目マスタキュー検索 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItemQueue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchItemQueue.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを品目コードまたは品目名称で検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getSearchList(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("success");
	}

	/**
	 * 詳細画面用品目マスタキュー検索 品目名称・他社コード１・荷姿・運用管理単位・名称１
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailItemQueue(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailItemQueue.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを品目コードまたは品目名称で検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getDetailList(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("deteil");
	}

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き 品目名称・他社コード１・荷姿・運用管理単位・名称１・小数点桁数・端数区分
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItemQueue(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitItemQueue.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを品目コードまたは品目名称で検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getDetailDigitList(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("deteilDigit");
	}

	// 他社コード１で検索-----------------------------------------------
	/**
	 * 検索画面用品目マスタキュー検索(他社コード１で検索) 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItemQueueOtherCompany(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchItemQueueOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを他社コードで検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getSearchListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("successOtherCompany1");
	}

	/**
	 * 詳細画面用品目マスタキュー検索(他社コード１で検索) 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailItemQueueOtherCompany(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailItemQueueOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを他社コードで検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getDetailListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("deteilOtherCompany1");
	}

	/**
	 * 詳細画面用小数点桁数・端数区分付き品目マスタキュー検索(他社コード１で検索) 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItemQueueOtherCompany(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitItemQueueOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタキューを他社コードで検索
		try {
			List<ItemQueueForAutoComplete> result = itemQueueForAutoCompleteLogic
					.getDetailDigitListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<ItemQueueForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタキュー検索結果なし");
		}
		return mapping.findForward("deteilDigitOtherCompany1");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ItemQueueForAutoCompleteBean> getAutoCompleteBean(
			final List<ItemQueueForAutoComplete> result) {
		List<ItemQueueForAutoCompleteBean> list = new ArrayList<ItemQueueForAutoCompleteBean>();
		for (ItemQueueForAutoComplete bean : result) {
			ItemQueueForAutoCompleteBean item = new ItemQueueForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 品目マスタキューデータをオートコンプリート用Beanに移送する
	 * @param source 品目マスタキューデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private ItemQueueForAutoCompleteBean transferEntityData(
			final ItemQueueForAutoComplete source,
			final ItemQueueForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest
				.setUnitOfOperationManagement(source
						.getUnitOfOperationManagement());
		dest.setName01(source.getName01());
		dest.setSmallnumLength(source.getSmallnumLength());
		dest.setRoundDivision(source.getRoundDivision());
		dest.setLotDivision(source.getLotDivision());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 品目マスタキューのAuto Complete用ロジックを設定します。
	 * @param itemQueueForAutoCompleteLogic 品目マスタキューのAuto Complete用ロジック
	 */
	public void setItemQueueForAutoCompleteLogic(
			final ItemQueueForAutoCompleteLogic itemQueueForAutoCompleteLogic) {
		this.itemQueueForAutoCompleteLogic = itemQueueForAutoCompleteLogic;
	}
}
