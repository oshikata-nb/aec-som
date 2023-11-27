/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.item.ItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 品目マスタのAuto Complete用アクション
 * @author t0011036
 */
public class ItemForAutoCompleteAction extends AbstractAction {

	/** 品目マスタのAuto Complete用ロジック */
	private ItemForAutoCompleteLogic itemAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ItemForAutoCompleteAction() {
	}

	// 品目コード・品目名称で検索-----------------------------------------------
	/**
	 * 検索画面用品目マスタ検索 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
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
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを品目コードまたは品目名称で検索
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getSearchList(paramForm.getCode());
			// Formに設定する
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	/**
	 * 詳細画面用品目マスタ検索 品目名称・他社コード１・荷姿・運用管理単位・名称１
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailItem(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailItem.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを品目コードまたは品目名称で検索
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getDetailList(paramForm.getCode());
			// Formに設定する
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteil");
	}

	/**
	 * 品目マスタ詳細画面用 小数点桁数・端数区分付き 品目名称・他社コード１・荷姿・運用管理単位・ 名称１・小数点桁数・端数区分
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItem(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitItem.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを品目コードまたは品目名称で検索
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getDetailDigitList(paramForm.getCode());
			// Formに設定する
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteilDigit");
	}

	/**
	 * 品目マスタ詳細用標準仕入単価取得用
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItemPrice(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitItemPrice.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		/* 品目マスタを品目コードまたは品目名称で検索 */
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getDetailDigitPriceList(paramForm.getCode());
			/* Formに設定する */
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("品目マスタ検索結果なし");
		}

		return mapping.findForward("deteilDigitItemPrice");
	}

	// 他社コード１で検索-----------------------------------------------
	/**
	 * 検索画面用品目マスタ検索(他社コード１で検索) 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
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
			getLog().debug("searchItemOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを他社コードで検索
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getSearchListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("successOtherCompany1");
	}

	/**
	 * 詳細画面用品目マスタ検索(他社コード１で検索) 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailItemOtherCompany(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailItemOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを他社コードで検索
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getDetailListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteilOtherCompany1");
	}

	/**
	 * 品目マスタ詳細用標準仕入単価取得用（他社コード１で検索）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItemOtherCompanyPrice(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitOtherCompanyPrice.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		/* 他社コード１で検索 */
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getOtherCompanyCdDetailDigitPriceList(paramForm.getCode());
			/* Formに設定する */
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("品目マスタ検索結果なし");
		}

		return mapping.findForward("deteilDigitOtherCompanyPrice");
	}

	/**
	 * 品目マスタ詳細用取得用-小数点桁数・端数区分付き（他社コード１で検索）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDetailDigitItemOtherCompany(
			final ActionMapping mapping, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitOtherCompany.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		/* 他社コード１で検索 */
		try {
			List<ItemForAutoComplete> result = itemAutoCompleteLogic
					.getOtherCompanyCdDetailDigitList(paramForm.getCode());
			/* Formに設定する */
			List<ItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("品目マスタ検索結果なし");
		}

		return mapping.findForward("deteilDigitOtherCompany");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ItemForAutoCompleteBean> getAutoCompleteBean(
			final List<ItemForAutoComplete> result) {
		List<ItemForAutoCompleteBean> list = new ArrayList<ItemForAutoCompleteBean>();
		for (ItemForAutoComplete bean : result) {
			ItemForAutoCompleteBean item = new ItemForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 品目マスタデータをオートコンプリート用Beanに移送する
	 * @param source 品目マスタキューデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private ItemForAutoCompleteBean transferEntityData(
			final ItemForAutoComplete source, final ItemForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest
				.setUnitOfOperationManagement(source
						.getUnitOfOperationManagement());
		dest.setUnitOfFractionManagement(source.getUnitOfFractionManagement());
		dest.setKgOfFractionManagement(source.getKgOfFractionManagement());
		dest.setKgConversionCoefficient(source.getKgConversionCoefficient());
		dest.setName01(source.getName01());
		dest.setSmallnumLength(source.getSmallnumLength());
		dest.setRoundDivision(source.getRoundDivision());
		dest.setSellingPrice(source.getSellingPrice());
		dest.setLotDivision(source.getLotDivision());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * itemAutoCompleteLogicを設定します。
	 * @param itemAutoCompleteLogic itemAutoCompleteLogic
	 */
	public void setItemAutoCompleteLogic(
			final ItemForAutoCompleteLogic itemAutoCompleteLogic) {
		this.itemAutoCompleteLogic = itemAutoCompleteLogic;
	}
}
