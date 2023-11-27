/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterBean;
import com.asahikaseieng.dao.nonentity.autocomplete.operation.OperationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 工程マスタのAuto Complete用アクション
 * @author tosco
 */
public class OperationForAutoCompleteAction extends AbstractAction {

	/** 工程マスタのAuto Complete用ロジック */
	private OperationForAutoCompleteLogic operationForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public OperationForAutoCompleteAction() {
	}

	// 工程コード・工程名称で検索-----------------------------------------------
	/**
	 * 検索画面用工程マスタ検索 工程名称・他社コード１・荷姿・運用管理単位
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchOperation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchOperation.");
		}

		OperationForAutoCompleteForm frm = (OperationForAutoCompleteForm) form;

		/* 工程を工程コードまたは工程名称で検索 */
		try {
			BigDecimal recipeUse = null;

			if (!StringUtils.isEmpty(frm.getRecipeUse())) {
				recipeUse = new BigDecimal(frm.getRecipeUse());
			}

			List<OperationForAutoComplete> result = operationForAutoCompleteLogic
					.getSearchList(frm.getCode(), recipeUse);

			/* Formに設定する */
			List<GeneralParameterBean> list = getAutoCompleteBean(result);

			frm.setResult(list);
		} catch (NoDataException e) {
			// 検索結果が存在しない場合
			log.debug("工程マスタ検索結果なし");
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
			final List<OperationForAutoComplete> result) {
		List<GeneralParameterBean> list = new ArrayList<GeneralParameterBean>();

		for (OperationForAutoComplete bean : result) {
			GeneralParameterBean operation = new GeneralParameterBean();
			transferEntityData(bean, operation);
			list.add(operation);
		}

		return list;
	}

	/**
	 * 工程マスタデータをオートコンプリート用Beanに移送する
	 * @param source 工程マスタデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private GeneralParameterBean transferEntityData(
			final OperationForAutoComplete source,
			final GeneralParameterBean dest) {
		dest.setCode(source.getOperationCd());
		dest.setName(source.getOperationName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * 工程マスタのAuto Complete用ロジックを設定します。
	 * @param operationForAutoCompleteLogic 工程マスタのAuto Complete用ロジック
	 */
	public void setOperationForAutoCompleteLogic(
			final OperationForAutoCompleteLogic operationForAutoCompleteLogic) {
		this.operationForAutoCompleteLogic = operationForAutoCompleteLogic;
	}
}
