/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.dao.entity.topic.Topic;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 組織マスタ詳細 Actionクラス.
 * @author tosco
 */
public final class TopicDetailAction extends AbstractAction {

	private TopicDetailLogic topicDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public TopicDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		TopicDetailForm frm = (TopicDetailForm) form;

		try {
			/* 初期検索 */
			Topic bean = topicDetailLogic.getEntity(
				AecNumberUtils.convertBigDecimal(frm.getTopicId()));
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, bean);

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc} 新規ボタン処理:何もしない？
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		TopicDetailForm frm = (TopicDetailForm) form;

		frm.setInsertFlg(1);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理.update.ロケーションマスタ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("update.");
		}
		TopicDetailForm frm = (TopicDetailForm) form;

		/* 更新対象データを作成する:プライマリキー */
		Topic newBean = topicDetailLogic.getEntity(
			AecNumberUtils.convertBigDecimal(frm.getTopicId()));
		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		try {
			/* 更新処理を実行 */
			topicDetailLogic.update(newBean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("back");

	}

	/**
	 * 追加処理.insert.ロケーションマスタ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}
		TopicDetailForm frm = (TopicDetailForm) form;

		/* 更新対象データを作成する */
		Topic newBean = new Topic();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);
		/** createUpdateBean.基本情報登録 */
		String tantoCd = getLoginInfo(request).getTantoCd();
		newBean.setUpdatorCd(tantoCd);
		newBean.setInputorCd(tantoCd);
		newBean.setInputDate(AecDateUtils.getCurrentTimestamp());
		newBean.setUpdateDate(AecDateUtils.getCurrentTimestamp());


		/* 更新処理を実行 */
		topicDetailLogic.insert(newBean);

		/* メッセージ */
		saveMessage(request, "message.complete.insert");
		frm.setInsertFlg(0);

		return mapping.findForward("back");

	}

	/**
	 * ロケーションマスタ：delete
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delete.");
		}

		TopicDetailForm frm = (TopicDetailForm) form;

		/* 更新対象データを作成する:プライマリキー */
		Topic bean = topicDetailLogic.getEntity(
			AecNumberUtils.convertBigDecimal(frm.getTopicId()));

		try {
			/* 更新処理を実行 */
			topicDetailLogic.delete(bean);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");

	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDetailLogicを設定します。
	 * @param topicDetailLogic TopicLogic
	 */
	public void setTopicLogic(
			final TopicDetailLogic topicDetailLogic) {
		this.topicDetailLogic = topicDetailLogic;
	}

}
