/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.master.loginallrolelist.LoginAllRoleList;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.loginrolelist.LoginRoleList;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;
import com.asahikaseieng.utils.crypt.MD5Crypt;

/**
 * 担当者マスタ詳細 Actionクラス.
 * @author t0011036
 */
public final class LoginDetailAction extends AbstractAction {

	/* ロールID桁数 */
	private static final Integer ROLE_ID_LENGTH = 10;

	private LoginDetailLogic loginDetailLogic;

	/**
	 * コンストラクタ.
	 */
	public LoginDetailAction() {
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

		LoginDetailForm frm = (LoginDetailForm) form;

		if (getLoginInfo(request).getAdminFlg().equals("2")) {
			frm.setDeleteAuthority(true);
			frm.setUpdateAuthority(true);
		} else {
			/* 権限取得 */
			getControlAuthority(request, frm, Constants.MENU_ID_LOGIN,
				Constants.TAB_ID_LOGIN_DETAIL);

			if (!frm.getViewAuthority()) {
				/* エラーメッセージ */
				saveError(request, "errors.not.view.authority");
				return mapping.findForward("back");
			}
		}

		/* 担当者初期検索 */
		LoginDetail bean = loginDetailLogic.getDetailEntity(frm.getTantoCd());

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, bean);

		/* ロールセット取得 */
		setRoleCombobox(frm);

		/* 現行パスワード非表示 */
		frm.setPassword(null);

		/* 前回パスワード設定 */
		frm.setLastPassword(bean.getPassword());

		/* ログインユーザの管理者フラグ取得 */
		HttpSession session = request.getSession(false);

		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);
			frm.setLoginAdminFlg(loginInfo.getAdminFlg());
		}

		return mapping.findForward("success");
	}

	/**
	 * ロールリスト取得
	 * @param frm 画面データ
	 */
	public void setRoleCombobox(final LoginDetailForm frm) {
		/* 担当者ロール一覧を取得 */
		List<LoginRoleList> list = loginDetailLogic.getLoginRoleList(frm
				.getTantoCd());

		/* 複数ロール格納 */
		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoleId() != null) {
				String roleId = list.get(i).getRoleId().toString();
				map.put(roleId, roleId);
			}
		}

		/* 全ロール一覧を取得 */
		List<LoginAllRoleList> listRole = loginDetailLogic
				.getLoginAllRoleList();

		List<ComboBoxItems> menuListMoto = new ArrayList<ComboBoxItems>();
		List<ComboBoxItems> menuList = new ArrayList<ComboBoxItems>();

		for (int i = 0; i < listRole.size(); i++) {
			LoginAllRoleList role = listRole.get(i);
			String roleId = role.getRoleId().toString();
			String roleName = role.getRoleName();

			ComboBoxItems combo = new ComboBoxItems();

			/* ロールID(ゼロサプレス)＋ロール名称設定(Value値) */
			StringBuffer zero = new StringBuffer();

			for (int j = 0; j < ROLE_ID_LENGTH - roleId.length(); j++) {
				zero.append("0");
			}

			combo.setValues(zero + roleId + ":" + roleName);

			/* ロールID＋ロール名称設定(ラベル) */
			combo.setLabales(roleId + ":" + roleName);

			if (map.get(roleId) != null) {
				/* 選択先コンボ */
				menuList.add(combo);
			} else {
				/* 選択元コンボ */
				menuListMoto.add(combo);
			}
		}

		frm.setRoleMoto(menuListMoto);
		frm.setRole(menuList);
	}

	/**
	 * ロールリスト取得(新規用)
	 * @param frm 画面データ
	 */
	public void setRoleComboboxNew(final LoginDetailForm frm) {
		/* 全ロール一覧を取得 */
		List<LoginAllRoleList> listRole = loginDetailLogic
				.getLoginAllRoleList();

		List<ComboBoxItems> menuListMoto = new ArrayList<ComboBoxItems>();
		List<ComboBoxItems> menuList = new ArrayList<ComboBoxItems>();

		/* ロールID、ロール名称を取得して配列に設定 */
		for (int i = 0; i < listRole.size(); i++) {
			LoginAllRoleList role = listRole.get(i);
			String roleId = role.getRoleId().toString();
			String roleName = role.getRoleName();

			ComboBoxItems combo = new ComboBoxItems();

			/* ロールID(ゼロサプレス)＋ロール名称設定(Value値) */
			StringBuffer zero = new StringBuffer();

			for (int j = 0; j < ROLE_ID_LENGTH - roleId.length(); j++) {
				zero.append("0");
			}

			combo.setValues(zero + roleId + ":" + roleName);

			/* ロールID＋ロール名称設定(ラベル) */
			combo.setLabales(roleId + ":" + roleName);

			/* 選択元コンボ */
			menuListMoto.add(combo);
		}

		frm.setRoleMoto(menuListMoto);
		frm.setRole(menuList);
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

		LoginDetailForm frm = (LoginDetailForm) form;

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
		loginDetailLogic.regist(frm, getLoginInfo(request).getTantoCd());

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

		LoginDetailForm frm = (LoginDetailForm) form;

		/* 削除処理を実行 */
		// loginDetailLogic.deleteLogin(deleteLogin(frm, getLoginInfo(request)
		// .getTantoCd()));
		//
		// loginDetailLogic.deleteTantoRole(deleteTantoRole(frm, getLoginInfo(
		// request).getTantoCd()));
		loginDetailLogic.delete(frm, getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	// /**
	// * 削除処理用のLoginを作成する.
	// * @param bean 画面データ
	// * @param tantoCd 担当者コード
	// * @return Login
	// * @throws InvocationTargetException InvocationTargetException
	// * @throws IllegalAccessException IllegalAccessException
	// */
	// private Login deleteLogin(final LoginDetailForm frm, final String
	// tantoCd)
	// throws IllegalAccessException, InvocationTargetException {
	// Login newBean = new Login();
	//
	// /* 値を更新用Beanにコピる */
	// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
	//
	// return newBean;
	// }
	//
	// /**
	// * 削除処理用のTantoRoleAllDeleteを作成する.
	// * @param bean 画面データ
	// * @param tantoCd 担当者コード
	// * @return TantoRole
	// * @throws InvocationTargetException InvocationTargetException
	// * @throws IllegalAccessException IllegalAccessException
	// */
	// private TantoRoleAllDelete deleteTantoRole(final LoginDetailForm frm,
	// final String tantoCd) throws IllegalAccessException,
	// InvocationTargetException {
	// TantoRoleAllDelete newBean = new TantoRoleAllDelete();
	//
	// /* 値を更新用Beanにコピる */
	// IgnoreCaseBeanUtils.copyProperties(newBean, frm);
	//
	// return newBean;
	// }

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

		LoginDetailForm frm = (LoginDetailForm) form;

		if (getLoginInfo(request).getAdminFlg().equals("2")) {
			frm.setDeleteAuthority(true);
			frm.setUpdateAuthority(true);
		} else {
			/* 権限取得 */
			getControlAuthority(request, frm, Constants.MENU_ID_LOGIN,
				Constants.TAB_ID_LOGIN_DETAIL);

			if (!frm.getViewAuthority()) {
				/* エラーメッセージ */
				saveError(request, "errors.not.view.authority");
				return mapping.findForward("back");
			}
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		/* ロールセット取得(新規用) */
		setRoleComboboxNew(frm);

		/* ログインユーザの管理者フラグ取得 */
		HttpSession session = request.getSession(false);

		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);
			frm.setLoginAdminFlg(loginInfo.getAdminFlg());
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * loginDetailLogicを設定します。
	 * @param loginDetailLogic loginDetailLogic
	 */
	public void setLoginDetailLogic(final LoginDetailLogic loginDetailLogic) {
		this.loginDetailLogic = loginDetailLogic;
	}
}
