/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.direction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.direction.DirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 予備溶解タンクNOのAuto Complete用アクション
 * @author tosco
 */
public class DirectionRecipeResouceForAutoCompleteAction extends AbstractAction {

	/** 予備溶解タンクNOのAuto Complete用ロジック */
	private DirectionRecipeResouceForAutoCompleteLogic directionRecipeResouceForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public DirectionRecipeResouceForAutoCompleteAction() {
	}

	//method------------------------------------------------------------------------
	/**
	 * 予備溶解タンクNOを前後設備紐付けマスタから検索
	 * 設備コード・設備名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchDissolutionTank(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchDissolutionTank.");
		}
		DirectionRecipeResouceForAutoCompleteForm paramForm =
			(DirectionRecipeResouceForAutoCompleteForm) form;

		String productionLine = paramForm.getProductionLine();
		if (StringUtils.isNotEmpty(productionLine)) {
			//設備マスタ検索
			try {
				List<DirectionRecipeResouceForAutoComplete> result =
					directionRecipeResouceForAutoCompleteLogic.getSearchList(
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
		final List<DirectionRecipeResouceForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (DirectionRecipeResouceForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			item.setCode(bean.getResouceCd());
			item.setName(bean.getResouceName());
			list.add(item);
		}
		return list;
	}

	//------------------------------------------------------------------------------
	/**
	 * 予備溶解タンクNOのAuto Complete用ロジックを設定します。
	 * @param directionRecipeResouceForAutoCompleteLogic 予備溶解タンクNOのAuto Complete用ロジック
	 */
	public void setDirectionRecipeAsprovaForAutoCompleteLogic(
		final DirectionRecipeResouceForAutoCompleteLogic directionRecipeResouceForAutoCompleteLogic) {
		this.directionRecipeResouceForAutoCompleteLogic = directionRecipeResouceForAutoCompleteLogic;
	}

}
