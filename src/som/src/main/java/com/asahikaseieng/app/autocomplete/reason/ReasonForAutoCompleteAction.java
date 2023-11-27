/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reason;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.reason.ReasonForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 理由マスタのAuto Complete用アクション
 * @author tosco
 */
public class ReasonForAutoCompleteAction extends AbstractAction {

	/** 理由マスタのAuto Complete用ロジック */
	private ReasonForAutoCompleteLogic reasonForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ReasonForAutoCompleteAction() {
	}

	//理由コード・理由内容で検索-----------------------------------------------
	/**
	 * 理由マスタ検索
	 * 理由内容
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchReason(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchReason.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		//理由マスタを理由コードまたは理由内容で検索
		try {
			List<ReasonForAutoComplete> result =
				reasonForAutoCompleteLogic.getSearchList(frm.getCode());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("理由マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<ReasonForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (ReasonForAutoComplete bean : result) {
			GeneralParameterBean accounts = new GeneralParameterBean();
			transferEntityData(bean, accounts);
			list.add(accounts);
		}
		return list;
	}

	/**
	 * 理由マスタデータをオートコンプリート用Beanに移送する
	 * @param source 理由マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final ReasonForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getRyCd());
		dest.setName(source.getRyDescription());
		return dest;
	}

	//------------------------------------------------------------------------------
	/**
	 * 理由マスタのAuto Complete用ロジックを設定します。
	 * @param reasonForAutoCompleteLogic 理由マスタのAuto Complete用ロジック
	 */
	public void setAccountsForAutoCompleteLogic(
			final ReasonForAutoCompleteLogic reasonForAutoCompleteLogic) {
		this.reasonForAutoCompleteLogic = reasonForAutoCompleteLogic;
	}

}
