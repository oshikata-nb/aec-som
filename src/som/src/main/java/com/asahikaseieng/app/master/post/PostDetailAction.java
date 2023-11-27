/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.post.Post;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 役職詳細 Actionクラス.
 * @author t0011036
 */
public final class PostDetailAction extends AbstractAction {

	private PostDetailLogic postDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public PostDetailAction() {
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

		PostDetailForm frm = (PostDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_POST,
			Constants.TAB_ID_POST_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		PostDetail bean = postDetailLogic.getDetailEntity(frm.getPostId());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
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
			getLog().debug("update");
		}

		PostDetailForm frm = (PostDetailForm) form;

		Post bean = postDetailLogic.getEntity(new BigDecimal(frm.getPostId()));

		if (bean == null) {
			/* 追加処理を実行 */
			postDetailLogic.insert(insertPost(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			if (frm.getNewFlg().equals("true")) {
				/* メッセージ */
				saveError(request, "errors.duplicate.data");

				return mapping.findForward("success");
			}

			/* 更新処理を実行 */
			postDetailLogic.update(updatePost(frm, getLoginInfo(request)
					.getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
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
			getLog().debug("delete");
		}

		PostDetailForm frm = (PostDetailForm) form;

		/* 削除処理を実行 */
		postDetailLogic.delete(deletePost(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のPostを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Post
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Post updatePost(final PostDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Post newBean = postDetailLogic
				.getEntity(new BigDecimal(frm.getPostId()));

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 追加処理用のPostを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Post
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Post insertPost(final PostDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Post newBean = new Post();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 削除処理用のPostを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Post
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Post deletePost(final PostDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Post newBean = new Post();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
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

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		PostDetailForm frm = (PostDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_POST,
			Constants.TAB_ID_POST_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * postDetailLogicを設定します。
	 * @param postDetailLogic postDetailLogic
	 */
	public void setPostDetailLogic(final PostDetailLogic postDetailLogic) {
		this.postDetailLogic = postDetailLogic;
	}
}
