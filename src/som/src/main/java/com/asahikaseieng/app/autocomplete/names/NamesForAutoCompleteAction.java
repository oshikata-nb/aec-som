/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.names;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.names.NamesForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 各種名称マスタのAuto Complete用アクション
 * @author tosco
 */
public class NamesForAutoCompleteAction extends AbstractAction {

	/** 各種名称マスタのAuto Complete用ロジック */
	private NamesForAutoCompleteLogic namesForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public NamesForAutoCompleteAction() {
	}

	//名称コードまたは、名称１で検索--------------------------------
	/**
	 * 各種名称マスタ検索
	 * 名称コード・名称１
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchNames(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchNames.");
		}

		NamesForAutoCompleteForm frm = (NamesForAutoCompleteForm) form;

		//各種名称マスタを名称コードまたは名称１で検索
		try {
			List<NamesForAutoComplete> result =
				namesForAutoCompleteLogic.getSearchList(frm.getCode(), frm.getNameDivision());
			//Formに設定する
			List<GeneralParameterBean> list = getAutoCompleteBean(result);
			frm.setResult(list);
		} catch (NoDataException e) {
			//検索結果が存在しない場合
			log.debug("各種名称マスタ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(final List<NamesForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (NamesForAutoComplete bean : result) {
			GeneralParameterBean names = new GeneralParameterBean();
			transferEntityData(bean, names);
			list.add(names);
		}
		return list;
	}

	/**
	 * 各種名称マスタデータをオートコンプリート用Beanに移送する
	 * @param source 各種名称マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final NamesForAutoComplete source, final GeneralParameterBean dest) {
		dest.setCode(source.getNameCd());
		dest.setName(source.getName01());
		return dest;
	}

	//------------------------------------------------------------------------------
	/**
	 * 各種名称マスタのAuto Complete用ロジックを設定します。
	 * @param namesForAutoCompleteLogic 各種名称マスタのAuto Complete用ロジック
	 */
	public void setNamesForAutoCompleteLogic(
			final NamesForAutoCompleteLogic namesForAutoCompleteLogic) {
		this.namesForAutoCompleteLogic = namesForAutoCompleteLogic;
	}

}
