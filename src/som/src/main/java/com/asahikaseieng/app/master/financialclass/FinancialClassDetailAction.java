/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.financialclass.FinancialClass;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 財務分類詳細 Actionクラス.
 * @author t0011036
 */
public final class FinancialClassDetailAction extends AbstractAction {

	private FinancialClassDetailLogic financialClassDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassDetailAction() {
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

		FinancialClassDetailForm frm = (FinancialClassDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_FINANCIAL_CLASS,
			Constants.TAB_ID_FINANCIAL_CLASS_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		FinancialClassDetail bean = financialClassDetailLogic
				.getDetailEntity(frm.getFinancialClassCd());

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

		FinancialClassDetailForm frm = (FinancialClassDetailForm) form;

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			financialClassDetailLogic.insert(insertFinancialClass(frm,
				getLoginInfo(request).getTantoCd()));
		} else {
			/* 更新処理を実行 */
			financialClassDetailLogic.update(updateFinancialClass(frm,
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

		FinancialClassDetailForm frm = (FinancialClassDetailForm) form;

		/* 削除処理を実行 */
		financialClassDetailLogic.delete(deleteFinancialClass(frm,
			getLoginInfo(request).getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のFinancialClassを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return FinancialClass
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private FinancialClass updateFinancialClass(
			final FinancialClassDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		FinancialClass newBean = new FinancialClass();

		try {
			newBean = financialClassDetailLogic.getEntity(frm
					.getFinancialClassCd());
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
	 * 追加処理用のFinancialClassを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return FinancialClass
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private FinancialClass insertFinancialClass(
			final FinancialClassDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		FinancialClass newBean = new FinancialClass();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のFinancialClassを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return FinancialClass
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private FinancialClass deleteFinancialClass(
			final FinancialClassDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		FinancialClass newBean = new FinancialClass();

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

		FinancialClassDetailForm frm = (FinancialClassDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_FINANCIAL_CLASS,
			Constants.TAB_ID_FINANCIAL_CLASS_DETAIL);

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
	 * financialClassDetailLogicを設定します。
	 * @param financialClassDetailLogic financialClassDetailLogic
	 */
	public void setFinancialClassDetailLogic(
			final FinancialClassDetailLogic financialClassDetailLogic) {
		this.financialClassDetailLogic = financialClassDetailLogic;
	}
}
