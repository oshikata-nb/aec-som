/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.reason.Reason;
import com.asahikaseieng.dao.nonentity.master.reasondefaultdetail.ReasonDefaultDetail;
import com.asahikaseieng.dao.nonentity.master.reasondetail.ReasonDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 理由詳細 Actionクラス.
 * @author t0011036
 */
public final class ReasonDetailAction extends AbstractAction {

	private ReasonDetailLogic reasonDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public ReasonDetailAction() {
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

		ReasonDetailForm frm = (ReasonDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_REASON,
			Constants.TAB_ID_REASON_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期検索 */
		ReasonDetail bean = reasonDetailLogic.getDetailEntity(frm.getRyCd());

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

		ReasonDetailForm frm = (ReasonDetailForm) form;

		if (frm.getDefaultFlg()) {
			/* デフォルト理由チェック */
			ReasonDefaultDetail bean = reasonDetailLogic.chkDefault();

			if (bean != null) {
				if (!bean.getRyCd().equals(frm.getRyCd())) {
					/* エラーメッセージ */
					saveError(request, "errors.reason.deplicate.default.flg");
					return mapping.findForward("success");
				}
			}
		}

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			reasonDetailLogic.insert(insertReason(frm, getLoginInfo(request)
					.getTantoCd()));
		} else {
			/* 更新処理を実行 */
			reasonDetailLogic.update(updateReason(frm, getLoginInfo(request)
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

		ReasonDetailForm frm = (ReasonDetailForm) form;

		/* 削除処理を実行 */
		reasonDetailLogic.delete(deleteReason(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のReasonを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Reason
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Reason updateReason(final ReasonDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Reason newBean = new Reason();

		try {
			newBean = reasonDetailLogic.getEntity(frm.getRyCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		if (frm.getDefaultFlg() == Boolean.TRUE) {
			newBean.setDefaultFlg(new BigDecimal("1"));
		} else {
			newBean.setDefaultFlg(new BigDecimal("0"));
		}

		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のReasonを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Reason
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Reason insertReason(final ReasonDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Reason newBean = new Reason();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		if (frm.getDefaultFlg() == Boolean.TRUE) {
			newBean.setDefaultFlg(new BigDecimal("1"));
		} else {
			newBean.setDefaultFlg(new BigDecimal("0"));
		}

		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 削除処理用のReasonを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return Reason
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Reason deleteReason(final ReasonDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Reason newBean = new Reason();

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

		ReasonDetailForm frm = (ReasonDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_REASON,
			Constants.TAB_ID_REASON_DETAIL);

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
	 * reasonDetailLogicを設定します。
	 * @param reasonDetailLogic reasonDetailLogic
	 */
	public void setReasonDetailLogic(final ReasonDetailLogic reasonDetailLogic) {
		this.reasonDetailLogic = reasonDetailLogic;
	}
}
