/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.payment.PaymentVenderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 支払入力－取引先マスタのAuto Complete用アクション
 * @author tosco
 */
public class PaymentVenderForAutoCompleteAction extends AbstractAction {

	/** 振込 */
	public static final String TRANSFER = "3";

	/** 郵便振替 */
	public static final String POSTAL_TRANSFER = "4";

	/** 支払入力－取引先マスタのAuto Complete用ロジック */
	private PaymentVenderForAutoCompleteLogic paymentVenderForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public PaymentVenderForAutoCompleteAction() {
	}

	/**
	 * 支払先を検索
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchVender(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchVender.");
		}
		PaymentVenderForAutoCompleteForm frm = (PaymentVenderForAutoCompleteForm) form;
		// 取引先マスタを取引先コードまたは取引先略称で検索
		try {
			List<PaymentVenderForAutoComplete> result = paymentVenderForAutoCompleteLogic
					.getSearchList(frm.getOrganizationCd(), frm.getCode());

			// Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("取引先マスタ検索結果なし");
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
			final List<PaymentVenderForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (PaymentVenderForAutoComplete bean : result) {
			PaymentVenderForAutoCompleteBean vender = new PaymentVenderForAutoCompleteBean();
			transferEntityData(bean, vender);
			list.add(vender);
		}
		return list;
	}

	/**
	 * 取引先マスタデータをオートコンプリート用Beanに移送する
	 * @param source 取引先マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final PaymentVenderForAutoComplete source,
			final PaymentVenderForAutoCompleteBean dest) {
		dest.setCode(source.getVenderCd());
		dest.setName(source.getVenderShortedName());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 取引先マスタのAuto Complete用ロジックを設定します。
	 * @param paymentVenderForAutoCompleteLogic 支払入力－取引先マスタのAuto Complete用ロジック
	 */
	public void setVenderForAutoCompleteLogic(
			final PaymentVenderForAutoCompleteLogic paymentVenderForAutoCompleteLogic) {
		this.paymentVenderForAutoCompleteLogic = paymentVenderForAutoCompleteLogic;
	}
}
