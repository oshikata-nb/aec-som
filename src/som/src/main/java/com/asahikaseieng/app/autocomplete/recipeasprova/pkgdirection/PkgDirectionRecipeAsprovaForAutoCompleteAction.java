/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeasprova.pkgdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeasprova.pkgdirection.PkgDirectionRecipeAsprovaForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装指図－処方ASPROVAのAuto Complete用アクション
 * @author tosco
 */
public class PkgDirectionRecipeAsprovaForAutoCompleteAction extends AbstractAction {

	/** 包装指図－処方ASPROVAのAuto Complete用ロジック */
	private PkgDirectionRecipeAsprovaForAutoCompleteLogic pkgDirectionRecipeAsprovaForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeAsprovaForAutoCompleteAction() {
	}

	//method------------------------------------------------------------------------
	/**
	 * 処方ASPROVAをレシピインデックスで検索
	 * 設備コード・設備名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchRecipeAsprova(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchRecipeAsprova.");
		}
		PkgDirectionRecipeAsprovaForAutoCompleteForm paramForm
			= (PkgDirectionRecipeAsprovaForAutoCompleteForm) form;

		String recipeid = paramForm.getRecipeId();
		if (StringUtils.isNotEmpty(recipeid)) {
			//処方ASPROVAをレシピインデックスで検索
			try {
				List<PkgDirectionRecipeAsprovaForAutoComplete> result =
					pkgDirectionRecipeAsprovaForAutoCompleteLogic.getSearchList(
					recipeid, paramForm.getCode());
				//Formに設定する
				List<GeneralParameterBean> list = getAutoCompleteBean(result);
				paramForm.setResult(list);
			} catch (NoDataException e) {
				//検索結果が存在しない場合
				log.debug("レシピASPROVA検索結果なし");
			}
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<GeneralParameterBean> getAutoCompleteBean(
		final List<PkgDirectionRecipeAsprovaForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (PkgDirectionRecipeAsprovaForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			item.setCode(bean.getResouceCd());
			item.setName(bean.getResouceName());
			list.add(item);
		}
		return list;
	}

	//------------------------------------------------------------------------------
	/**
	 * 包装指図－処方ASPROVAのAuto Complete用ロジックを設定
	 * @param pkgDirectionRecipeAsprovaForAutoCompleteLogic 包装指図－処方ASPROVAのAuto Complete用ロジック
	 */
	public void setPkgDirectionRecipeAsprovaForAutoCompleteLogic
		(final PkgDirectionRecipeAsprovaForAutoCompleteLogic pkgDirectionRecipeAsprovaForAutoCompleteLogic) {
		this.pkgDirectionRecipeAsprovaForAutoCompleteLogic = pkgDirectionRecipeAsprovaForAutoCompleteLogic;
	}

}
