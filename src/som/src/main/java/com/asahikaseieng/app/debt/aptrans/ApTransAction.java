/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.aptrans;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 会計送信 Actionクラス.
 * @author t0011036
 */
public final class ApTransAction extends AbstractAction {

	private ApTransLogic apTransLogic;

	/**
	 * コンストラクタ.
	 */
	public ApTransAction() {
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

		ApTransForm frm = (ApTransForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_AR_TRANS,
			Constants.TAB_ID_AR_TRANS_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		HttpSession session = request.getSession(false);

		if (session != null) {
			/* セッションからログイン情報取得 */
			LoginInfo loginInfo = (LoginInfo) (session
					.getAttribute(Constants.LOGIN_KEY));

			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd()); /* 部署コード */
			frm.setSrhOrganizationName(loginInfo.getOrganizationName()); /* 部署名称 */
		}

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}

		ApTransForm frm = (ApTransForm) form;

		String organizationCd = frm.getSrhOrganizationCd();
		String inputDate = frm.getSrhStrInputDate().replace("/", "");

		try {
			/* 実行 */
			apTransLogic.insert(organizationCd, inputDate,
				getLoginInfo(request).getTantoCd());
		} catch (LogicExceptionEx e) {
			/* エラーメッセージ */
			saveError(request, "errors.duplicate.data");
			return mapping.findForward("success");
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward create(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("create.");
		}

		ApTransForm frm = (ApTransForm) form;

		try {
			/* CSVデータ検索 */
			frm.setSearchList(apTransLogic.getList(AecTextUtils.likeFilter(frm
					.getSrhStrInputDate().replace("/", ""))));
		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata");
			return mapping.findForward("success");
		}

		/* ダウンロードフラグをONに */
		frm.setCsvDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward downLoadCsvData(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("downLoadCsvdata.");
		}

		ApTransForm frm = (ApTransForm) form;

		/* CSVデータ作成 */
		List<String> csvDataList = apTransLogic.getData(frm.getSearchList());

		/* CSVデータファイル名の設定 */
		// String strTimestamp = AecDateUtils.dateFormat(AecDateUtils
		// .getCurrentTimestamp(), "yyyyMMddHHmmss");
		// String fileName = "Csv" + strTimestamp + ".txt";
		ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;
		String fileName = rb.getString("csv.ap.trans.filename");
		String csvFileName = new String(fileName.getBytes("Windows-31J"),
				"ISO-8859-1");

		/* CSVデータ出力 */
		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ csvFileName + "\"");
		response.setHeader("Cache-control", "private");
		response.flushBuffer();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(response
				.getOutputStream(), "Windows-31J"));

		for (String csvData : csvDataList) {
			out.println(csvData);
		}

		out.flush();
		out.close();

		return null;
	}

	/* -------------------- setter -------------------- */

	/**
	 * apTransLogicを設定します。
	 * @param apTransLogic apTransLogic
	 */
	public void setApTransLogic(final ApTransLogic apTransLogic) {
		this.apTransLogic = apTransLogic;
	}
}
