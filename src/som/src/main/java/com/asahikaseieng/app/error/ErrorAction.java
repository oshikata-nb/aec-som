/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.NoLoginRuntimeException;
import com.asahikaseieng.mail.MailSpooler;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * エラー Actionクラス.
 * @author jbd
 */
public class ErrorAction extends AbstractAction {

	private static final int NAME_LENGTH = 15;

	private static Log log = LogFactory.getLog(ErrorAction.class);

	/**
	 * デフォルトコンストラクタ.
	 */
	public ErrorAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward unspecified(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		final Object errorMessage = request
				.getAttribute("javax.servlet.error.message");
		final Object errorType = request
				.getAttribute("javax.servlet.error.exception_type");
		final Throwable throwable = (Throwable) request
				.getAttribute("javax.servlet.error.exception");
		final Object requestURI = request
				.getAttribute("javax.servlet.error.request_uri");
		final Object statusCode = request
				.getAttribute("javax.servlet.error.status_code");

		// スタックトレースを文字列化
		final StringWriter sw = new StringWriter();
		if (throwable != null) {
			PrintWriter pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
		}

		// ブラウザの標準挙動では、用意した画面が表示されないのでリセットする.
		response.reset();

		// エラーハンドリング
		if (statusCode != null
				&& ((Integer) statusCode).intValue() == HttpServletResponse.SC_NOT_FOUND) {
			return mapping.findForward("404");
		}

		// NoLoginエラー
		if (throwable instanceof NoLoginRuntimeException) {
			return mapping.findForward("timeout");
		}

		// それ以外のエラー処理

		// ログ出力
		log.error("requestURI:  " + requestURI);
		log.error("statusCode:  " + statusCode);
		log.error("errorMessage:" + errorMessage);

		final LoginInfo bean = getLoginInfo(request);
		if (null != bean) {
			log.error("user:        " + bean.getTantoCd());
		}

		log.error("stack trace: " + SystemUtils.LINE_SEPARATOR + sw.toString());

		// 例外メールを送信
		sendExceptionMail(request, errorMessage, errorType, statusCode, sw
				.toString());

		// セッションクリア
		request.getSession(false).invalidate();

		return mapping.findForward("500");
	}

	/**
	 * 例外メール送信処理.
	 * @param request HttpServletRequest
	 * @param message Object
	 * @param errorType Object
	 * @param statusCode Object
	 * @param stackTrace String
	 */
	private void sendExceptionMail(final HttpServletRequest request,
			final Object message, final Object errorType,
			final Object statusCode, final String stackTrace) {

		// メール送信内容作成
		StringBuffer sb = new StringBuffer();
		sb.append("-------- RemoteHost Information -------\n");
		sb.append("host name       : ");
		sb.append(request.getRemoteHost());
		sb.append("\n");
		sb.append("ip address      : ");
		sb.append(request.getRemoteAddr());
		sb.append("\n");

		Enumeration col = request.getHeaderNames();

		while (col.hasMoreElements()) {
			String name = (String) col.nextElement();
			StringBuffer space = new StringBuffer(" ");
			for (int i = 0; i < NAME_LENGTH - name.length(); i++) {
				space.append(" ");
			}

			sb.append(name);
			sb.append(space.toString());
			sb.append(": ");
			sb.append(request.getHeader(name));
			sb.append("\n");
		}

		String url = "";
		if (request.getRequestURL() != null) {
			url = request.getRequestURL().toString();
		}
		String query = StringUtils.defaultString(request.getQueryString());

		sb.append("\n\n");
		sb.append("--------Error Information-------\n");
		sb.append("statusCode      : ").append(statusCode).append("\n");
		sb.append("message         : ").append(message).append("\n");
		sb.append("errorType       : ").append(errorType).append("\n");
		sb.append("requestURL      : ").append(
			StringUtils.join(new String[] {url, query}, "?")).append("\n");
		sb.append("throwable       : \n").append(
			StringUtils.defaultString(stackTrace));

		// メールの送信先(cc, bcc)を処理
		ResourceBundle sys = Constants.RB_SYSTEM_PROPERTIES;

		String[] cc = StringUtils
				.split(sys.getString("mail.exception.cc"), ",");
		String[] bcc = StringUtils.split(sys.getString("mail.exception.bcc"),
			",");

		// spoolerに登録し、メール送信予約
		MailSpooler.getInstance().addMessage(
			sys.getString("mail.exception.from"),
			sys.getString("mail.exception.from.name"),
			sys.getString("mail.exception.to"), cc, bcc,
			sys.getString("mail.exception.subject"), sb.toString());
	}
}
