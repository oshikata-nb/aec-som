/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.gadgetconfig.GadgetConfig;
import com.asahikaseieng.dao.entity.menu.Menu;
import com.asahikaseieng.dao.nonentity.master.controlauthoritylist.ControlAuthorityList;
import com.asahikaseieng.dao.nonentity.master.controlauthoritylist.ControlAuthorityListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.NoLoginRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStackTrace;

/**
 * アプリケーション独自のDispatchAction.Actionの主要な共通機能を実装するクラス.
 * @author jbd
 */
public abstract class AbstractAction extends DispatchAction {

	private ControlAuthorityListDao controlAuthorityListDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/**
	 * コンストラクタ.
	 */
	public AbstractAction() {
		super();
	}

	/* -------------------- helper method -------------------- */

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	protected final Log getLog() {
		return LogFactory.getLog(this.getClass());
	}

	/**
	 * ログインユーザー情報を取得します.
	 * @param request request
	 * @return LoginInfo ログイン情報
	 */
	protected final LoginInfo getLoginInfo(final HttpServletRequest request) {

		LoginInfo bean = (LoginInfo) request.getSession(false).getAttribute(
			Constants.LOGIN_KEY);

		/* 同時に来る複数Action(フレーム分割)の１つで例外が発生した場合の為 */
		if (bean == null) {
			return new LoginInfo();
		}

		return bean;
	}

	/**
	 * 権限を取得します.
	 * @param request request
	 * @return String 権限
	 */
	protected final String getMenuAuth(final HttpServletRequest request) {

		return (String) request.getSession(false).getAttribute(
			Constants.MENU_CONTEXT_AUTH);
	}

	/**
	 * 権限取得
	 * @param request request
	 * @param menuId menuId
	 * @param tabId tabId
	 * @return 権限あり:true なし:false
	 */
	protected final void getControlAuthority(final HttpServletRequest request,
			final ActionForm form, final BigDecimal menuId,
			final BigDecimal tabId) {
		AbstractForm frm = (AbstractForm) form;

		/* ログインユーザー取得 */
		String tantoCd = getLoginInfo(request).getTantoCd();
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		BigDecimal postId = getLoginInfo(request).getRoleId();

		List<ControlAuthorityList> list = new ArrayList<ControlAuthorityList>();

		/* 閲覧権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_VIEW);

		if (0 < list.size()) {
			frm.setViewAuthority(true);
		} else {
			frm.setViewAuthority(false);
		}

		/* 承認権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_APPROVAL);

		if (0 < list.size()) {
			frm.setApprovalAuthority(true);
		} else {
			frm.setApprovalAuthority(false);
		}

		/* 承認依頼権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_APPROVAL_REQUEST);

		if (0 < list.size()) {
			frm.setApprovalRequestAuthority(true);
		} else {
			frm.setApprovalRequestAuthority(false);
		}

		/* 否認権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_NEGATION);

		if (0 < list.size()) {
			frm.setNegationAuthority(true);
		} else {
			frm.setNegationAuthority(false);
		}

		/* 承認取消権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_APPROVAL_CANCEL);

		if (0 < list.size()) {
			frm.setApprovalCancelAuthority(true);
		} else {
			frm.setApprovalCancelAuthority(false);
		}

		/* 登録権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_UPDATE);

		if (0 < list.size()) {
			frm.setUpdateAuthority(true);
		} else {
			frm.setUpdateAuthority(false);
		}

		/* 削除権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_DELETE);

		if (0 < list.size()) {
			frm.setDeleteAuthority(true);
		} else {
			frm.setDeleteAuthority(false);
		}
		
		/* 確定権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_CONFIRM);

		if (0 < list.size()) {
			frm.setConfirmAuthority(true);
		} else {
			frm.setConfirmAuthority(false);
		}

		/* 確定取消権限取得 */
		list = controlAuthorityListDao.getList(tantoCd, organizationCd, postId,
			menuId, tabId, Constants.CTRL_ID_CONFIRM_CANCEL);

		if (0 < list.size()) {
			frm.setConfirmCancelAuthority(true);
		} else {
			frm.setConfirmCancelAuthority(false);
		}
		
	}

	/**
	 * 権限取得
	 * @param request request
	 * @param menuId menuId
	 * @param tabId tabId
	 * @param controlId controlId
	 * @return 権限あり:true なし:false
	 */
	// protected final Boolean getControlAuthorityList(
	// final HttpServletRequest request, final BigDecimal menuId,
	// final BigDecimal tabId, final BigDecimal controlId) {
	// /* ログインユーザー取得 */
	// String tantoCd = getLoginInfo(request).getTantoCd();
	// String organizationCd = getLoginInfo(request).getOrganizationCd();
	// BigDecimal postId = getLoginInfo(request).getRoleId();
	//
	// /* 権限取得 */
	// List<ControlAuthorityList> list = controlAuthorityListDao.getList(
	// tantoCd, organizationCd, postId, menuId, tabId, controlId);
	//
	// if (list.size() == 0) {
	// return false;
	// }
	//
	// return true;
	// }
	/* -------------------- helper method message -------------------- */

	/**
	 * MessageResourcesを取得する.
	 * @return MessageResources
	 */
	private static MessageResources getMessageResources() {
		return MessageResources
				.getMessageResources(Constants.APPLICATION_PROPERTIES);
	}

	/**
	 * メッセージを取得する.
	 * @param key String
	 * @param objects Object
	 * @return 文字列
	 */
	protected static final String getMessage(final String key,
			final Object... objects) {
		return getMessageResources().getMessage(key, objects);
	}

	/**
	 * メッセージを取得する.
	 * @param key String
	 * @param objects objects...
	 * @return 文字列
	 */
	protected static final String getMessageWithArgs(final String key,
			final Object... objects) {
		Object[] ret = convertMessageKeyArgs(objects);
		return getMessageResources().getMessage(key, ret);
	}

	/**
	 * キー配列からキーを置換文字列に置き換える.
	 * @param keys プロパティKey配列
	 */
	private static Object[] convertMessageKeyArgs(final Object[] keys) {

		Object[] ret = new Object[keys.length];

		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null && (keys[i] instanceof String)) {
				/* 値を置き換える */
				ret[i] = getMessageResources().getMessage((String) keys[i]);
			} else {
				ret[i] = keys[i];
			}
		}

		return ret;
	}

	/**
	 * 一件だけエラーを登録する.
	 * @param request HttpServletRequest
	 * @param key String
	 * @param objects Object
	 */
	protected final void saveError(final HttpServletRequest request,
			final String key, final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		saveErrors(request, messages);
	}

	/**
	 * 一件だけエラーを登録する.
	 * @param request HttpServletRequest
	 * @param key String
	 * @param objects Object
	 */
	protected final void saveErrorWithArgs(final HttpServletRequest request,
			final String key, final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages
				.add("", new ActionMessage(key, convertMessageKeyArgs(objects)));
		saveErrors(request, messages);
	}

	/**
	 * 一件だけメッセージを登録する.
	 * @param request HttpServletRequest
	 * @param key String
	 */
	protected final void saveMessage(final HttpServletRequest request,
			final String key) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key));
		saveMessages(request, messages);
	}

	/* -------------------- common method -------------------- */

	/**
	 * 初期化処理.
	 * @param map ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception システム例外
	 */
	public ActionForward init(final ActionMapping map, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		getLog().debug("enter \"init\"");

		return map.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public final ActionForward execute(final ActionMapping map,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		// StrutsTestCaseは直接doPostを呼ぶ為
		// ApplicationServlet#serviceが呼ばれないので...
		// 本番ではここまでくれば必ずSessionはできている
		request.getSession();

		CustomMapping mapping = (CustomMapping) map;
		if (!mapping.isAllowNoLogin()
				&& request.getSession(false).getAttribute(Constants.LOGIN_KEY) == null) {
			// TODO タイムアウト調査後削除
			getLog().error("TIMEOUT" + mapping.toString());

			throw new NoLoginRuntimeException();
		}

		if (mapping.isGadget()) {
			return gadgetExecute(map, form, request, response, mapping);
		} else {
			return defaultExecute(map, form, request, response, mapping);
		}
	}

	private ActionForward gadgetExecute(final ActionMapping map,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response, final CustomMapping mapping) {
		try {
			return super.execute(map, form, request, response);
		} catch (Exception e) {
			// スタックトレースを出力
			getLog().error(AecStackTrace.getStackTrace(e));
			return mapping.findForward("global.gadget.error");
		}
	}

	private ActionForward defaultExecute(final ActionMapping map,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response, final CustomMapping mapping)
			throws Exception {
		synchronized (request.getSession(false)) {

			if (form != null) {
				/* プロパティの移送処理 */
				AbstractActionHelper.propertyCheck(form);
			}

			/* メニューから遷移した場合、メニューIDから権限を引く */
			setMenuAuth(request);

			try {

				return super.execute(map, form, request, response);

			} catch (DuplicateRuntimeException de) {

				/* 一意制約エラー */
				if (de.getIndex() == Constants.EXCEPTION_INIT_ROW) {
					/* 行編集無し */
					saveError(request, "errors.duplicate.data");
				} else {
					/* 行編集有り */
					String index = "" + de.getIndex();
					saveError(request, "errors.duplicate.data.row", index);
				}

				return mapping.getInputForward();

			} catch (OptimisticLockRuntimeException ee) {

				/* 排他エラー */
				if (ee.getIndex() == Constants.EXCEPTION_INIT_ROW) {
					/* 行編集無し */
					saveError(request, "errors.optimisticlock.data");
				} else {
					/* 行編集有り */
					String index = "" + ee.getIndex();
					saveError(request, "errors.optimisticlock.data.row", index);
				}

				return mapping.getInputForward();

			} catch (NoDataException nd) {

				AbstractForm frm = (AbstractForm) form;
				if (0 <= frm.getOp().indexOf("print")
						|| 0 <= frm.getOp().indexOf("report")) {
					/* 帳票の対象データ無し */
					saveError(request, "errors.nodata.print");
				} else {
					/* 対象データ無し */
					saveError(request, "errors.nodata");
				}

				return mapping.getInputForward();
			} catch (ZaikoCtrlExceptionEx ze) {
				String mes = ze.getMessage();
				if (mes.indexOf("ORA-") < 0) {
					saveError(request, "errors.detail", mes);
				} else {
					saveError(request, "errors.stockinout.update");
				}
				if (StringUtils.isNotEmpty(ze.getModuleCd())) {
					// エラーログに出力する
					outPutErrorLog(ze.getModuleCd(), null,
						ze.getInsideErrMsg(), ze.getUser());
				}
				return mapping.getInputForward();
			} catch (LogicExceptionEx le) {
				String mes = le.getMessage();
				saveError(request, "errors.detail", mes);
				return mapping.getInputForward();
			}

		}
	}

	private void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > Constants.ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				Constants.ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- local helper method -------------------- */

	/**
	 * リクエストよりメニューIDを取得し、メニュー権限をセッションに格納する.
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void setMenuAuth(final HttpServletRequest request) {

		String menuId = request.getParameter(Constants.MENU_PARAM);
		// TODO TIMEOUT対策
		if (("/Error.do").equals(request.getServletPath())) {
			menuId = "";
		}
		if (StringUtils.isNotEmpty(menuId)) {

			/* "menu" + idの形で飛んで来るので、idだけを取る */
			menuId = StringUtils.substring(menuId, Constants.MENU_ID_PREFIX
					.length());

			/* メニューrootSetから権限を探す */
			SortedSet<Menu> rootSet = (SortedSet<Menu>) request.getSession(
				false).getAttribute(Constants.MENU_KEY);
			Menu menu = Menu.findMenu(new BigDecimal(menuId), rootSet);

			/* メニューの権限をセッションに格納 */
			request.getSession(false).setAttribute(Constants.MENU_CONTEXT_AUTH,
				menu.getAuth());
		}
	}

	/**
	 * ガジェットIDに対応する権限を取得する.
	 * @param request request
	 * @param gadgetId gadgetId
	 * @return auth
	 */
	@SuppressWarnings("unchecked")
	protected final String getGadgetAuth(final HttpServletRequest request,
			final String gadgetId) {

		// 入力チェック
		if (StringUtils.isEmpty(gadgetId)) {
			throw new IllegalArgumentException("gadgetId is empty.");
		}

		/* ガジェットリストから権限を探す */
		List<GadgetConfig> list = (List<GadgetConfig>) request
				.getSession(false).getAttribute(Constants.GADGET_KEY);
		for (GadgetConfig bean : list) {
			if (gadgetId.equals(bean.getGadgetId())) {
				return bean.getAuth();
			}
		}
		throw new IllegalStateException("can't reach here.");
	}

	/* -------------------- setter -------------------- */

	/**
	 * controlAuthorityListDaoを設定します。
	 * @param controlAuthorityListDao controlAuthorityListDao
	 */
	public void setControlAuthorityListDao(
			final ControlAuthorityListDao controlAuthorityListDao) {
		this.controlAuthorityListDao = controlAuthorityListDao;
	}

	/**
	 * errorLogDaoを設定します。
	 * @param errorLogDao errorLogDao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}
}
