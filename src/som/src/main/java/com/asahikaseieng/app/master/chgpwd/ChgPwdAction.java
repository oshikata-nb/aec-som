/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.chgpwd;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;
import com.asahikaseieng.utils.crypt.MD5Crypt;

/**
 * 担当者パスワード変更 Actionクラス.
 * @author t0011036
 */
public final class ChgPwdAction extends AbstractAction {

	private ChgPwdLogic chgPwdLogic;

	/**
	 * コンストラクタ.
	 */
	public ChgPwdAction() {
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

		ChgPwdForm frm = (ChgPwdForm) form;

		/* 担当者初期検索 */
		LoginDetail bean = chgPwdLogic.getDetailEntity(frm.getTantoCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* 現行パスワード非表示 */
		frm.setPassword(null);

		/* 前回パスワード設定 */
		frm.setLastPassword(bean.getPassword());

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

		ChgPwdForm frm = (ChgPwdForm) form;

		//20220517 S.Fujimaki パスワードを２度入力させる。 add
		if(!( frm.getPassword().equals(frm.getPasswordConfirm()) )){
			//入力された二つのパスワードの不一致はエラー
			saveError(request, "errors.password.not.match");
			return mapping.findForward("success");
		}
		//20220517 S.Fujimaki パスワードを２度入力させる。 add end

		if (!StringUtils.isEmpty(frm.getPassword())) {
			frm.setUpdatePass(AecDateUtils.getCurrentTimestamp());

			/* パスワードの暗号化 */
			frm.setPassword(MD5Crypt.getMD5String(frm.getPassword()));
			frm.setLastPassword(frm.getPassword());
		} else {
			/* パスワード変更無し */
			frm.setPassword(frm.getLastPassword());
		}

		/* 担当者登録 */
		/* 更新処理を実行 */
		chgPwdLogic.updateLogin(updateLogin(frm, getLoginInfo(request)
				.getTantoCd()));

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 更新処理用のLoginを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private Login updateLogin(final ChgPwdForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		Login newBean = new Login();

		try {
			newBean = chgPwdLogic.getEntity(frm.getTantoCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		frm.setPassword(null);

		/* コピーしきれなかった分は手で */
		newBean.setUpdatorCd(tantoCd);

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

	/* -------------------- setter -------------------- */

	/**
	 * chgPwdLogicを設定します。
	 * @param chgPwdLogic chgPwdLogic
	 */
	public void setChgPwdLogic(final ChgPwdLogic chgPwdLogic) {
		this.chgPwdLogic = chgPwdLogic;
	}
}
