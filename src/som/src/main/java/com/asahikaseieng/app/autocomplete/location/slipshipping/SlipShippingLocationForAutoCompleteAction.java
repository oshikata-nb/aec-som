/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.slipshipping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping.SlipShippingLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 出荷関連帳票出力画面用ロケーションマスタのAuto Complete用アクション
 * @author tosco
 */
public class SlipShippingLocationForAutoCompleteAction extends AbstractAction {

	/** 出荷関連帳票出力画面ロケーションのAuto Complete用ロジック */
	private SlipShippingLocationForAutoCompleteLogic slipShippingLocationForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public SlipShippingLocationForAutoCompleteAction() {
	}

	/**
	 * 出荷関連帳票出力画面用 上位ロケーションマスタ検索 ロケーションコード・ロケーション名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchLocation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchLocation.");
		}
		GeneralParameterForm paramForm = (GeneralParameterForm) form;
		// ロケーションマスタロケーションコードまたはロケーション名称で検索
		try {
			List<SlipShippingLocationForAutoComplete> result = slipShippingLocationForAutoCompleteLogic
					.getSearchList(paramForm.getCode());
			// Formに設定する

			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合

			log.debug("ロケーションマスタ検索結果なし");
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
			final List<SlipShippingLocationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (SlipShippingLocationForAutoComplete bean : result) {
			GeneralParameterBean location = new GeneralParameterBean();
			transferEntityData(bean, location);
			list.add(location);
		}
		return list;
	}

	/**
	 * ロケーションマスタデータをオートコンプリート用Beanに移送する
	 * 
	 * @param source ロケーションマスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final SlipShippingLocationForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getLocationCd());
		dest.setName(source.getLocationName());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 出荷関連帳票出力画面ロケーションのAuto Complete用ロジックを設定します。
	 * @param slipShippingLocationForAutoCompleteLogic 仕入用ロケーションのAuto
	 *            Complete用ロジック
	 */
	public void setSlipShippingLocationForAutoCompleteLogic(
			final SlipShippingLocationForAutoCompleteLogic slipShippingLocationForAutoCompleteLogic) {
		this.slipShippingLocationForAutoCompleteLogic = slipShippingLocationForAutoCompleteLogic;
	}

}
