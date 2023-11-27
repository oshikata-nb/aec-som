/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bumon;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.bumon.BumonForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 会計部門マスタのAuto Complete用アクション
 * @author tosco
 */
public class BumonForAutoCompleteAction extends AbstractAction {

	/** 会計部門マスタのAuto Complete用ロジック */
	private BumonForAutoCompleteLogic bumonForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public BumonForAutoCompleteAction() {
	}

	//会計部門コード・会計部門名称で検索-----------------------------------------------
	/**
	 * 会計部門マスタ検索
	 * 会計部門名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchBumon(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchBumon.");
		}
		GeneralParameterForm frm = (GeneralParameterForm) form;
		//会計部門マスタを会計部門コードまたは会計部門名称で検索
		try {
			List<BumonForAutoComplete> result =
				bumonForAutoCompleteLogic.getSearchList(frm.getCode());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("会計部門マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<BumonForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (BumonForAutoComplete bean : result) {
			GeneralParameterBean bumon = new GeneralParameterBean();
			transferEntityData(bean, bumon);
			list.add(bumon);
		}
		return list;
	}

	/**
	 * 会計部門マスタデータをオートコンプリート用Beanに移送する
	 * @param source 会計部門マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final BumonForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getSectionCd());
		dest.setName(source.getSectionName());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 会計部門マスタのAuto Complete用ロジックを設定します。
	 * @param bumonForAutoCompleteLogic 会計部門マスタのAuto Complete用ロジック
	 */
	public void setBumonForAutoCompleteLogic(
			final BumonForAutoCompleteLogic bumonForAutoCompleteLogic) {
		this.bumonForAutoCompleteLogic = bumonForAutoCompleteLogic;
	}

}
