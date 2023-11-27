/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.post;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.post.PostForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 役職のAuto Complete用アクション
 * @author t0011036
 */
public class PostForAutoCompleteAction extends AbstractAction {

	/** 役職のAuto Complete用ロジック */
	private PostForAutoCompleteLogic postAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public PostForAutoCompleteAction() {
	}

	/**
	 * 検索画面用役職検索 役職名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchPost(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchPost.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 役職を役職コードまたは役職名称で検索 */
		try {
			List<PostForAutoComplete> result = postAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<PostForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("役職検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<PostForAutoCompleteBean> getAutoCompleteBean(
			final List<PostForAutoComplete> result) {
		List<PostForAutoCompleteBean> list = new ArrayList<PostForAutoCompleteBean>();

		for (PostForAutoComplete bean : result) {
			PostForAutoCompleteBean item = new PostForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 役職データをオートコンプリート用Beanに移送する
	 * @param source役職データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private PostForAutoCompleteBean transferEntityData(
			final PostForAutoComplete source, final PostForAutoCompleteBean dest) {
		dest.setCode(source.getPostId().toString());
		dest.setName(source.getPostName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * postAutoCompleteLogicを設定します。
	 * @param postAutoCompleteLogic postAutoCompleteLogic
	 */
	public void setPostAutoCompleteLogic(
			final PostForAutoCompleteLogic postAutoCompleteLogic) {
		this.postAutoCompleteLogic = postAutoCompleteLogic;
	}
}
