/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.shipping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.item.shipping.ShippingItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 出荷指図用品目マスタのAuto Complete用アクション
 * @author tosco
 */
public class ShippingItemForAutoCompleteAction extends AbstractAction {

	/** 品目マスタのAuto Complete用ロジック */
	private ShippingItemForAutoCompleteLogic shippingItemForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public ShippingItemForAutoCompleteAction() {
	}
	//品目コード・品目名称で検索-----------------------------------------------
	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * 品目名称・他社コード１・荷姿・運用管理単位・名称１・小数点桁数・端数区分
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
		//品目マスタを品目コードまたは品目名称で検索
		try {
			List<ShippingItemForAutoComplete> result =
				shippingItemForAutoCompleteLogic.getDetailDigitList(paramForm.getCode());
			//Formに設定する
			List<ShippingItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteilDigit");
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
	public ActionForward searchDetailDigitItemOtherCompany(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDetailDigitItemOtherCompany.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		//品目マスタを品目コードまたは品目名称で検索
		try {
			List<ShippingItemForAutoComplete> result =
				shippingItemForAutoCompleteLogic.getDetailDigitOtherCompany1List(paramForm.getCode());
			//Formに設定する
			List<ShippingItemForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("品目マスタ検索結果なし");
		}
		return mapping.findForward("deteilDigitOtherCompany1");
	}


	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ShippingItemForAutoCompleteBean> getAutoCompleteBean(
		final List<ShippingItemForAutoComplete> result) {
		List<ShippingItemForAutoCompleteBean> list = new ArrayList<ShippingItemForAutoCompleteBean>();
		for (ShippingItemForAutoComplete bean : result) {
			ShippingItemForAutoCompleteBean item = new ShippingItemForAutoCompleteBean();
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
	private ShippingItemForAutoCompleteBean transferEntityData(
			final ShippingItemForAutoComplete source, final ShippingItemForAutoCompleteBean dest) {
		dest.setCode(source.getItemCd());
		dest.setName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest.setUnitOfOperationManagement(source.getUnitOfOperationManagement());
		dest.setName01(source.getName01());
		dest.setSmallnumLength(source.getSmallnumLength());
		dest.setRoundDivision(source.getRoundDivision());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 品目マスタのAuto Complete用ロジックを設定します。
	 * @param shippingItemForAutoCompleteLogic 品目マスタのAuto Complete用ロジック
	 */
	public void setShippingItemForAutoCompleteLogic(
			final ShippingItemForAutoCompleteLogic shippingItemForAutoCompleteLogic) {
		this.shippingItemForAutoCompleteLogic = shippingItemForAutoCompleteLogic;
	}

}
