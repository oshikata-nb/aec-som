/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.VenderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 取引先マスタのAuto Complete用アクション
 * @author tosco
 */
public class VenderForAutoCompleteAction extends AbstractAction {

	/** 取引先マスタのAuto Complete用ロジック */
	private VenderForAutoCompleteLogic venderForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public VenderForAutoCompleteAction() {
	}

	// 取引先コード・取引先略称で検索-----------------------------------------------
	/**
	 * 検索画面用取引先マスタ検索 取引先略称
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
		VenderForAutoCompleteForm frm = (VenderForAutoCompleteForm) form;
		// 取引先マスタを取引先コードまたは取引先略称で検索
		try {
			List<VenderForAutoComplete> result = venderForAutoCompleteLogic
					.getSearchList(frm.getCode(), frm.getVenderDivision());
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
			final List<VenderForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (VenderForAutoComplete bean : result) {
			GeneralParameterBean vender = new GeneralParameterBean();
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
			final VenderForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getVenderCd());
		dest.setName(source.getVenderShortedName());
		return dest;
	}

	// ------------------------------------------------------------------------------
	/**
	 * 取引先マスタのAuto Complete用ロジックを設定します。
	 * @param venderForAutoCompleteLogic 取引先マスタのAuto Complete用ロジック
	 */
	public void setVenderForAutoCompleteLogic(
			final VenderForAutoCompleteLogic venderForAutoCompleteLogic) {
		this.venderForAutoCompleteLogic = venderForAutoCompleteLogic;
	}
}
