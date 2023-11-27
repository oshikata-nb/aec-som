/*
 * Created on 2006/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.struts.servlet.S2ActionServlet;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.NoLoginRuntimeException;
import com.asahikaseieng.mail.MailSpooler;

/**
 * アプリケーション独自のS2ActionServlet.
 * @author jbd
 */
public class ApplicationServlet extends S2ActionServlet {

	private static final long serialVersionUID = 2272830525535892620L;

	private Thread mailThread;

	/**
	 * Logオブジェクトを返す.
	 * 
	 * @return Logオブジェクト
	 */
	protected final Log getLog() {
		return LogFactory.getLog(this.getClass());
	}

	/**
	 * コンストラクタ.
	 */
	public ApplicationServlet() {
	}

	/**
	 * {@inheritDoc}
	 */
	public final void init() throws ServletException {

		// mail spooler start
		mailThread = new Thread(MailSpooler.getInstance());
		mailThread.start();

		// アプリケーション独自の初期化処理を書く
		super.init();
	}

	/**
	 * {@inheritDoc}
	 */
	protected final void service(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		// 侵入チェック
		String path = ((CustomS2RequestProcessor) getRequestProcessor(getModuleConfig(request)))
				.useProcessPath(request, response);
		CustomMapping mapping = (CustomMapping) getModuleConfig(request)
				.findActionConfig(path);

		if (mapping != null
				&& !mapping.isAllowNoLogin()
				&& request.getSession(false).getAttribute(Constants.LOGIN_KEY) == null) {
			// TODO タイムアウト調査後削除
			getLog().error("TIMEOUT" + mapping.toString());

			throw new NoLoginRuntimeException();
		}

		// 上を通過したので明示的にセッション作成
		request.getSession();

		if (getLog().isDebugEnabled()) {
			logging(request);
		}

		// キャッシュをクリア
		noCache(response);

		// 実処理を開始
		super.service(request, response);
	}

	/**
	 * レスポンスをNoCacheに設定する<br>
	 * （HTTPレスポンスのヘッダーにno-cacheの設定をする。）.
	 * @param response レスポンス
	 */
	private void noCache(final HttpServletResponse response) {

		if (getLog().isDebugEnabled()) {
			getLog().debug("no-cache");
		}

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 1);
		response.addHeader("Cache-Control", "no-store");

	}

	/**
	 * ログ出力.<br>
	 * リクエストのパラメータとセッションの状況をログ出力する.
	 * @param request
	 */
	private void logging(final HttpServletRequest request) {

		getLog().debug("**************************************************");
		getLog().debug("url     : " + request.getRequestURI());
		getLog().debug("host    : " + request.getRemoteAddr());
		getLog().debug("referer : " + request.getHeader("referer"));

		Map map = createParameters(request);
		for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
			Map.Entry entry = (Map.Entry) i.next();
			getLog().debug(
				"param   : (" + entry.getKey() + "," + entry.getValue() + ")");
		}

		if (request.getSession(false) != null) {
			getLog()
					.debug("- session ----------------------------------------");
			Enumeration e = request.getSession(false).getAttributeNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				getLog().debug("key     = " + key);
				getLog().debug(
					"  value = " + request.getSession(false).getAttribute(key));
			}
		}

	}

	/**
	 * パラメータMap作成.
	 * @param request ＨＴＴＰリクエスト情報
	 * @return パラメータMap
	 */
	private Map createParameters(final HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();

		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = request.getParameter(key);
			result.put(key, value);
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void destroy() {
		// アプリケーション独自の終了処理を書く

		// mail spooler stop
		try {
			MailSpooler.getInstance().setStopped(true);
			mailThread.interrupt();
			mailThread.join(); // 待つ
		} catch (InterruptedException e) {
			log.fatal("abnormal destroy[" + e.getMessage() + "]");
		} finally {
			super.destroy();
		}
	}
}
