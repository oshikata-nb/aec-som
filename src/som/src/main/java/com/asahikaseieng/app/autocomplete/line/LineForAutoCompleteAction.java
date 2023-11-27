/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.line;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.line.LineForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 生産ラインのAuto Complete用アクション
 * @author t0011036
 */
public class LineForAutoCompleteAction extends AbstractAction {

	/** 生産ラインのAuto Complete用ロジック */
	private LineForAutoCompleteLogic lineAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public LineForAutoCompleteAction() {
	}

	/**
	 * 検索画面用生産ライン検索 生産ライン名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchLine(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchLine.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 生産ラインを生産ラインコードまたは生産ライン名称で検索 */
		try {
			List<LineForAutoComplete> result = lineAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<LineForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("生産ライン検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<LineForAutoCompleteBean> getAutoCompleteBean(
			final List<LineForAutoComplete> result) {
		List<LineForAutoCompleteBean> list = new ArrayList<LineForAutoCompleteBean>();

		for (LineForAutoComplete bean : result) {
			LineForAutoCompleteBean item = new LineForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 生産ラインデータをオートコンプリート用Beanに移送する
	 * @param source生産ラインデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private LineForAutoCompleteBean transferEntityData(
			final LineForAutoComplete source, final LineForAutoCompleteBean dest) {
		dest.setCode(source.getProductionLine());
		dest.setName(source.getProductionLineName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * lineAutoCompleteLogicを設定します。
	 * @param lineAutoCompleteLogic lineAutoCompleteLogic
	 */
	public void setLineAutoCompleteLogic(
			final LineForAutoCompleteLogic lineAutoCompleteLogic) {
		this.lineAutoCompleteLogic = lineAutoCompleteLogic;
	}
}
