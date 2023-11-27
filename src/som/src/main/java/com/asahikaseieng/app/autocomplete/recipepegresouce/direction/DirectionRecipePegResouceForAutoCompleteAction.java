/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipepegresouce.direction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.recipepegresouce.direction.DirectionRecipePegResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * ホールドタンクNOのAuto Complete用アクション
 * @author tosco
 */
public class DirectionRecipePegResouceForAutoCompleteAction extends AbstractAction {

	/** 前後設備紐付けマスタのAuto Complete用ロジック */
	private DirectionRecipePegResouceForAutoCompleteLogic directionRecipePegResouceForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public DirectionRecipePegResouceForAutoCompleteAction() {
	}

	//method------------------------------------------------------------------------
	/**
	 * ホールドタンクNOを前後設備紐付けマスタから検索
	 * 設備コード・設備名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchHoldTank(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchHoldTank.");
		}
		DirectionRecipePegResouceForAutoCompleteForm paramForm =
			(DirectionRecipePegResouceForAutoCompleteForm) form;

		String compoundTankNo = paramForm.getCompoundTankNo();
		if (StringUtils.isNotEmpty(compoundTankNo)) {
			//前後設備紐付けマスタ検索
			try {
				List<DirectionRecipePegResouceForAutoComplete> result =
					directionRecipePegResouceForAutoCompleteLogic.getSearchList(
					compoundTankNo, paramForm.getCode());
				List<GeneralParameterBean> list = getAutoCompleteBean(result);
				paramForm.setResult(list);
			} catch (NoDataException e) {
				//検索結果が存在しない場合
				log.debug("前後設備紐付けマスタ検索結果なし");
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
		final List<DirectionRecipePegResouceForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();
		for (DirectionRecipePegResouceForAutoComplete bean : result) {
			GeneralParameterBean item = new GeneralParameterBean();
			item.setCode(bean.getResouceCd());
			item.setName(bean.getResouceName());
			list.add(item);
		}
		return list;
	}

	//------------------------------------------------------------------------------
	/**
	 * 前後設備紐付けマスタのAuto Complete用ロジックを設定します。
	 * @param directionRecipePegResouceForAutoCompleteLogic 前後設備紐付けマスタのAuto Complete用ロジック
	 */
	public void setDirectionRecipeAsprovaForAutoCompleteLogic(
		final DirectionRecipePegResouceForAutoCompleteLogic directionRecipePegResouceForAutoCompleteLogic) {
		this.directionRecipePegResouceForAutoCompleteLogic = directionRecipePegResouceForAutoCompleteLogic;
	}

}
