/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 部署詳細 Actionクラス.
 * @author t0011036
 */
public final class OrganizationDetailAction extends AbstractAction {

	private OrganizationDetailLogic organizationDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public OrganizationDetailAction() {
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

		OrganizationDetailForm frm = (OrganizationDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORGANIZATION,
			Constants.TAB_ID_ORGANIZATION_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		OrganizationDetail bean = organizationDetailLogic.getDetailEntity(frm
				.getOrganizationCd());

		bean.setBillDescriptionMatter(bean.getBillDescriptionMatter().subtract(
			new BigDecimal("1")));

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

		OrganizationDetailForm frm = (OrganizationDetailForm) form;

		if (!StringUtils.isEmpty(frm.getParentOrganizationCd())) {
			/* 親部署コードチェック */
			OrganizationDetail beanOrganization = organizationDetailLogic
					.getDetailEntity(frm.getParentOrganizationCd());

			if (beanOrganization == null) {
				/* エラーメッセージ */
				saveError(request,
					"errors.nodata.organization.parent.organization.cd");
				return mapping.findForward("success");
			}

			/* 親部署循環参照チェック */
			if (frm.getOrganizationCd().equals(frm.getParentOrganizationCd())) {
				/* エラーメッセージ */
				saveError(request, "errors.same.parent.organization.cd");
				return mapping.findForward("success");
			}
		}

		/* 部署レベル計算 */
		if (!StringUtils.isEmpty(frm.getParentOrganizationCd())) {
			int organizationLevel = organizationDetailLogic
					.calcOrganizationLevel(frm.getOrganizationCd(), frm
							.getParentOrganizationCd());

			if (organizationLevel == -1) {
				/* エラーメッセージ */
				saveError(request, "errors.loop.parent.organization.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getTantoCd())) {
			/* 担当者コードチェック */
			LoginDetail beanLogin = organizationDetailLogic.getLoginEntity(frm
					.getTantoCd());

			if (beanLogin == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.organization.tanto.cd");
				return mapping.findForward("success");
			}
		}

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			organizationDetailLogic.insert(insertOrganization(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			organizationDetailLogic.update(updateOrganization(frm,
				getLoginInfo(request).getTantoCd()));
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

		OrganizationDetailForm frm = (OrganizationDetailForm) form;

		/* 削除処理を実行 */
		organizationDetailLogic.delete(deleteOrganization(frm, getLoginInfo(
			request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のOrganizationを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Organization
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Organization updateOrganization(final OrganizationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Organization newBean = new Organization();

		try {
			newBean = organizationDetailLogic
					.getEntity(frm.getOrganizationCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setBillDescriptionMatter(newBean.getBillDescriptionMatter()
				.add(new BigDecimal("1")));
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のOrganizationを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Organization
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Organization insertOrganization(final OrganizationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Organization newBean = new Organization();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setBillDescriptionMatter(newBean.getBillDescriptionMatter()
				.add(new BigDecimal("1")));
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のOrganizationを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Organization
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Organization deleteOrganization(final OrganizationDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Organization newBean = new Organization();

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

		OrganizationDetailForm frm = (OrganizationDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ORGANIZATION,
			Constants.TAB_ID_ORGANIZATION_DETAIL);

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
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic organizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}
}
