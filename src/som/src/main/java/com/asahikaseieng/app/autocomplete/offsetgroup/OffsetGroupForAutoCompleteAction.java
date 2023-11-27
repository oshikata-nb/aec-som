/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.offsetgroup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup.OffsetGroupForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 相殺グループのAuto Complete用アクション
 * @author t0011036
 */
public class OffsetGroupForAutoCompleteAction extends AbstractAction {

	/** 相殺グループのAuto Complete用ロジック */
	private OffsetGroupForAutoCompleteLogic offsetGroupAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public OffsetGroupForAutoCompleteAction() {
	}

	/**
	 * 検索画面用相殺グループ検索 相殺グループ名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchOffsetGroup(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchOffsetGroup.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 相殺グループを相殺グループコードまたは相殺グループ名称で検索 */
		try {
			List<OffsetGroupForAutoComplete> result = offsetGroupAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<OffsetGroupForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("相殺グループ検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<OffsetGroupForAutoCompleteBean> getAutoCompleteBean(
			final List<OffsetGroupForAutoComplete> result) {
		List<OffsetGroupForAutoCompleteBean> list = new ArrayList<OffsetGroupForAutoCompleteBean>();

		for (OffsetGroupForAutoComplete bean : result) {
			OffsetGroupForAutoCompleteBean item = new OffsetGroupForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 相殺グループデータをオートコンプリート用Beanに移送する
	 * @param source相殺グループデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private OffsetGroupForAutoCompleteBean transferEntityData(
			final OffsetGroupForAutoComplete source,
			final OffsetGroupForAutoCompleteBean dest) {
		dest.setCode(source.getOffsetGroupCd());
		dest.setName(source.getOffsetGroupName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * offsetGroupAutoCompleteLogicを設定します。
	 * @param offsetGroupAutoCompleteLogic offsetGroupAutoCompleteLogic
	 */
	public void setOffsetGroupAutoCompleteLogic(
			final OffsetGroupForAutoCompleteLogic offsetGroupAutoCompleteLogic) {
		this.offsetGroupAutoCompleteLogic = offsetGroupAutoCompleteLogic;
	}
}
