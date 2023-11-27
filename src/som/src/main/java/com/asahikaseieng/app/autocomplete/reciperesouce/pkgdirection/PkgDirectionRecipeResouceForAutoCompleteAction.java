/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.pkgdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.pkgdirection.PkgDirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装指図－包装ラインNOのAuto Complete用アクション
 * @author tosco
 */
public class PkgDirectionRecipeResouceForAutoCompleteAction extends AbstractAction {

	/** 包装指図－包装ラインのAuto Complete用ロジック */
	private PkgDirectionRecipeResouceForAutoCompleteLogic pkgDirectionRecipeResouceForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeResouceForAutoCompleteAction() {
	}

	//method------------------------------------------------------------------------
	/**
	 * 包装ラインを設備マスタから検索
	 * 設備コード・設備名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchPackageLine(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchPackageLine.");
		}
		PkgDirectionRecipeResouceForAutoCompleteForm paramForm =
			(PkgDirectionRecipeResouceForAutoCompleteForm) form;

		String productionLine = paramForm.getProductionLine();
		if (StringUtils.isNotEmpty(productionLine)) {
			//設備マスタ検索
			try {
				List<PkgDirectionRecipeResouceForAutoComplete> result =
					pkgDirectionRecipeResouceForAutoCompleteLogic.getSearchList(
						productionLine, paramForm.getCode());
				List<GeneralParameterBean> list = getAutoCompleteBean(result);
				paramForm.setResult(list);
			} catch (NoDataException e) {
				//検索結果が存在しない場合
				log.debug("設備マスタ検索結果なし");
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
		final List<PkgDirectionRecipeResouceForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (PkgDirectionRecipeResouceForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			item.setCode(bean.getResouceCd());
			item.setName(bean.getResouceName());
			list.add(item);
		}
		return list;
	}

	//------------------------------------------------------------------------------
	/**
	 * 包装指図－包装ラインのAuto Complete用ロジックを設定します。
	 * @param pkgDirectionRecipeResouceForAutoCompleteLogic 包装指図－包装ラインのAuto Complete用ロジック
	 */
	public void setPkgDirectionRecipeAsprovaForAutoCompleteLogic(
		final PkgDirectionRecipeResouceForAutoCompleteLogic pkgDirectionRecipeResouceForAutoCompleteLogic) {
		this.pkgDirectionRecipeResouceForAutoCompleteLogic = pkgDirectionRecipeResouceForAutoCompleteLogic;
	}

}
