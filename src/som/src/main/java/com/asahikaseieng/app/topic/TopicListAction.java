/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.dao.nonentity.topic.TopicList;
import com.asahikaseieng.dao.nonentity.topic.TopicListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 不具合 Actionクラス.
 * @author a1020630
 */
public final class TopicListAction extends AbstractSearchAction {

	private TopicListLogic topicListLogic;

	/**
	 * コンストラクタ.
	 */
	public TopicListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		return null;
	}

	/**
	 * 検索処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		TopicListForm frm = (TopicListForm) form;
		/* クリア */
		frm.setSearchList(new ArrayList<TopicList>());
		TopicListPagerCondition condition = (TopicListPagerCondition) frm.getPager()
			.getPagerCondition();
		/* 明細取得 */
		frm.setSearchList(topicListLogic.getSearchList(condition));
		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * topicListLogicを設定します。
	 * @param topicListLogic topicListLogic
	 */
	public void setTopicListLogic(
			final TopicListLogic topicListLogic) {
		this.topicListLogic = topicListLogic;
	}
}
