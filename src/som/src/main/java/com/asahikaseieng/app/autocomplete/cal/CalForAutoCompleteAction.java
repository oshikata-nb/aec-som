/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.cal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.cal.CalForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * カレンダーのAuto Complete用アクション
 * @author t0011036
 */
public class CalForAutoCompleteAction extends AbstractAction {

	/** カレンダーのAuto Complete用ロジック */
	private CalForAutoCompleteLogic calAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public CalForAutoCompleteAction() {
	}

	/**
	 * 検索画面用カレンダー検索 カレンダー名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchCal(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchCal.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* カレンダーをカレンダーコードまたはカレンダー名称で検索 */
		try {
			List<CalForAutoComplete> result = calAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<CalForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("カレンダー検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<CalForAutoCompleteBean> getAutoCompleteBean(
			final List<CalForAutoComplete> result) {
		List<CalForAutoCompleteBean> list = new ArrayList<CalForAutoCompleteBean>();

		for (CalForAutoComplete bean : result) {
			CalForAutoCompleteBean item = new CalForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * カレンダーデータをオートコンプリート用Beanに移送する
	 * @param sourceカレンダーデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private CalForAutoCompleteBean transferEntityData(
			final CalForAutoComplete source, final CalForAutoCompleteBean dest) {
		dest.setCode(source.getCalCd());
		dest.setName(source.getCalName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * calAutoCompleteLogicを設定します。
	 * @param calAutoCompleteLogic calAutoCompleteLogic
	 */
	public void setCalAutoCompleteLogic(
			final CalForAutoCompleteLogic calAutoCompleteLogic) {
		this.calAutoCompleteLogic = calAutoCompleteLogic;
	}
}
