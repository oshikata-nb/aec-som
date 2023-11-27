/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.accounts.Accounts;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 勘定科目詳細 Actionクラス.
 * @author t0011036
 */
public final class AccountsDetailAction extends AbstractAction {

	private AccountsDetailLogic accountsDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public AccountsDetailAction() {
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

		AccountsDetailForm frm = (AccountsDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ACCOUNTS,
			Constants.TAB_ID_ACCOUNTS_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		AccountsDetail bean = accountsDetailLogic.getDetailEntity(frm
				.getAccountsCd());

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

		AccountsDetailForm frm = (AccountsDetailForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			accountsDetailLogic.insert(insertAccounts(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			accountsDetailLogic.update(updateAccounts(frm,
				getLoginInfo(request).getTantoCd()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のAccountsを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Accounts
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Accounts updateAccounts(final AccountsDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Accounts newBean = new Accounts();

		try {
			newBean = accountsDetailLogic.getEntity(frm.getAccountsCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のAccounts.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Accounts
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Accounts insertAccounts(final AccountsDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Accounts newBean = new Accounts();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
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

		AccountsDetailForm frm = (AccountsDetailForm) form;

		/* 削除処理を実行 */
		accountsDetailLogic.delete(deleteAccounts(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理用のAccountsを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Accounts
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Accounts deleteAccounts(final AccountsDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		Accounts newBean = new Accounts();

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

		AccountsDetailForm frm = (AccountsDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ACCOUNTS,
			Constants.TAB_ID_ACCOUNTS_DETAIL);

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
	 * accountsDetailLogicを設定します。
	 * @param accountsDetailLogic accountsDetailLogic
	 */
	public void setAccountsDetailLogic(
			final AccountsDetailLogic accountsDetailLogic) {
		this.accountsDetailLogic = accountsDetailLogic;
	}
}
