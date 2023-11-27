/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.belongrole.BelongRole;
import com.asahikaseieng.dao.nonentity.master.belongroledetail.BelongRoleDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.dao.nonentity.master.roledetail.RoleDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 所属・ロール組合せ詳細 Actionクラス.
 * @author t0011036
 */
public final class BelongRoleDetailAction extends AbstractAction {

	private BelongRoleDetailLogic belongRoleDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleDetailAction() {
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

		BelongRoleDetailForm frm = (BelongRoleDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BELONG_ROLE,
			Constants.TAB_ID_BELONG_ROLE_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		BelongRoleDetail bean = belongRoleDetailLogic.getDetailEntity(frm
				.getOrganizationCd(), frm.getPostId());

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

		BelongRoleDetailForm frm = (BelongRoleDetailForm) form;

		if (!StringUtils.isEmpty(frm.getOrganizationCd())) {
			/* 部署コードチェック */
			OrganizationDetail beanOrganization = belongRoleDetailLogic
					.getOrganizationEntity(frm.getOrganizationCd());

			if (beanOrganization == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.role.organization.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getPostId())) {
			/* 役職コードチェック */
			PostDetail beanPost = belongRoleDetailLogic.getPostEntity(frm
					.getPostId());

			if (beanPost == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.role.post.id");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getRoleId())) {
			/* ロールIDチェック */
			RoleDetail beanRole = belongRoleDetailLogic.getRoleEntity(frm
					.getRoleId());

			if (beanRole == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.belong.role.role.id");
				return mapping.findForward("success");
			}
		}

		BelongRole bean = belongRoleDetailLogic.getEntity(frm
				.getOrganizationCd(), AecNumberUtils.convertBigDecimal(frm
				.getPostId()));

		if (bean != null) {
			/* 更新処理を実行 */
			belongRoleDetailLogic.update(updateBelongRole(frm, getLoginInfo(
				request).getTantoCd()));
		} else {
			/* 追加処理を実行 */
			belongRoleDetailLogic.insert(insertBelongRole(frm, getLoginInfo(
				request).getTantoCd()));
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

		BelongRoleDetailForm frm = (BelongRoleDetailForm) form;

		/* 削除処理を実行 */
		belongRoleDetailLogic.delete(deleteBelongRole(frm,
			getLoginInfo(request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 追加処理用のBelongRoleを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return BelongRole
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongRole insertBelongRole(final BelongRoleDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongRole newBean = new BelongRole();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 更新処理用のBelongRoleを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return BelongRole
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongRole updateBelongRole(final BelongRoleDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongRole newBean = new BelongRole();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		return newBean;
	}

	/**
	 * 削除処理用のBelongRoleを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return BelongRole
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private BelongRole deleteBelongRole(final BelongRoleDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		BelongRole newBean = new BelongRole();

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

		BelongRoleDetailForm frm = (BelongRoleDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_BELONG_ROLE,
			Constants.TAB_ID_BELONG_ROLE_DETAIL);

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
	 * belongRoleDetailLogicを設定します。
	 * @param belongRoleDetailLogic belongRoleDetailLogic
	 */
	public void setBelongRoleDetailLogic(
			final BelongRoleDetailLogic belongRoleDetailLogic) {
		this.belongRoleDetailLogic = belongRoleDetailLogic;
	}
}
