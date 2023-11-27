/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.pkgdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装指図－品目マスタのAuto Complete用アクション
 * @author tosco
 */
public class PkgDirectionItemForAutoCompleteAction extends AbstractAction {

	/** 包装指図－品目マスタのAuto Complete用ロジック */
	private PkgDirectionItemForAutoCompleteLogic pkgDirectionItemForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionItemForAutoCompleteAction() {
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
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getSearchList(paramForm.getCode(), null);
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	/**
	 * 検索画面用品目マスタ検索 品目名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItemRepack(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchItem.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを品目コードまたは品目名称で検索
		try {
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getSearchList(paramForm.getCode(), "yes");
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
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
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getDetailList(paramForm.getCode(), null);
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteil");
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
	public ActionForward searchDetailItemRepack(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailItem.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// 品目マスタを品目コードまたは品目名称で検索
		try {
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getDetailList(paramForm.getCode(), "yes");
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteil");
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
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getSearchListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
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
			List<PkgDirectionItemForAutoComplete> result = pkgDirectionItemForAutoCompleteLogic
					.getDetailListOtherCompany(paramForm.getCode());
			// Formに設定する
			List<PkgDirectionItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteilOtherCompany1");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<PkgDirectionItemForAutoCompleteBean> getAutoCompleteBean(
			final List<PkgDirectionItemForAutoComplete> result) {
		List<PkgDirectionItemForAutoCompleteBean> list = new ArrayList<PkgDirectionItemForAutoCompleteBean>();
		for (PkgDirectionItemForAutoComplete bean : result) {
			PkgDirectionItemForAutoCompleteBean item = new PkgDirectionItemForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 品目マスタデータをオートコンプリート用Beanに移送する
	 * @param source 品目マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private PkgDirectionItemForAutoCompleteBean transferEntityData(
			final PkgDirectionItemForAutoComplete source,
			final PkgDirectionItemForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest
				.setUnitOfOperationManagement(source
						.getUnitOfOperationManagement());
		dest.setName01(source.getName01());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 包装指図－品目マスタのAuto Complete用ロジックを設定します。
	 * @param pkgDirectionItemForAutoCompleteLogic 包装指図－品目マスタのAuto Complete用ロジック
	 */
	public void setPkgDirectionItemForAutoCompleteLogic(
			final PkgDirectionItemForAutoCompleteLogic pkgDirectionItemForAutoCompleteLogic) {
		this.pkgDirectionItemForAutoCompleteLogic = pkgDirectionItemForAutoCompleteLogic;
	}
}
