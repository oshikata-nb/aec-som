/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.sales;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.autocomplete.item.sales.SalesItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 売上用品目マスタのAuto Complete用アクション
 * @author tosco
 */
public class SalesItemForAutoCompleteAction extends AbstractAction {

	/** 品目マスタのAuto Complete用ロジック */
	private SalesItemForAutoCompleteLogic salesItemForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public SalesItemForAutoCompleteAction() {
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
		SalesItemForAutoCompleteForm paramForm = (SalesItemForAutoCompleteForm) form;
		//品目マスタを品目コードまたは品目名称で検索
		try {
			List<SalesItemForAutoComplete> result =
				salesItemForAutoCompleteLogic.getDetailList(paramForm.getCode()
					, paramForm.getDeliveryCd());
			//Formに設定する			List<SalesItemForAutoCompleteBean> list = getAutoCompleteBean(result);
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
	 * 品目名称・他社コード１・荷姿・運用管理単位
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
			getLog().debug("searchOtherCompany1Item.");
		}
		SalesItemForAutoCompleteForm paramForm = (SalesItemForAutoCompleteForm) form;
		//品目マスタを他社コード１で検索
		try {
			List<SalesItemForAutoComplete> result =
				salesItemForAutoCompleteLogic.getDetailOtherCompany1List(paramForm.getCode()
					, paramForm.getDeliveryCd());
			//Formに設定する			List<SalesItemForAutoCompleteBean> list = getAutoCompleteBean(result);
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
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<SalesItemForAutoCompleteBean> getAutoCompleteBean(
		final List<SalesItemForAutoComplete> result) {
		List<SalesItemForAutoCompleteBean> list = new ArrayList<SalesItemForAutoCompleteBean>();
		for (SalesItemForAutoComplete bean : result) {
			SalesItemForAutoCompleteBean item = new SalesItemForAutoCompleteBean();
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
	private SalesItemForAutoCompleteBean transferEntityData(
			final SalesItemForAutoComplete source, final SalesItemForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest.setUnitOfOperationManagement(source.getUnitOfOperationManagement());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 品目マスタのAuto Complete用ロジックを設定します。
	 * @param salesItemForAutoCompleteLogic 品目マスタのAuto Complete用ロジック
	 */
	public void setSalesItemForAutoCompleteLogic(
			final SalesItemForAutoCompleteLogic salesItemForAutoCompleteLogic) {
		this.salesItemForAutoCompleteLogic = salesItemForAutoCompleteLogic;
	}

}
