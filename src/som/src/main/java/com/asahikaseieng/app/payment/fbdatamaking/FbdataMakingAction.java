/*
 * Created on 2009/06/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.fbdatamaking;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.master.bank.BankDetailLogic;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.dao.entity.master.bank.Bank;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingData;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeader;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailer;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ＦＢデータ作成 Actionクラス.
 * @author tosco
 */
public final class FbdataMakingAction extends AbstractAction {

	/** 銀行コード（銀行＋支店)の長さ(7byte) */
	private static final int BANK_CD_LENGTH = 7;

	/** 自社マスタ検索処理ロジッククラス */
	private CompanyDetailLogic companyDetailLogic;

	/** 銀行検索処理ロジッククラス */
	private BankDetailLogic bankDetailLogic;

	/** ＦＢデータ作成 ロジッククラス */
	private FbdataMakingLogic fbdataMakingLogic;

	/**
	 * コンストラクタ.
	 */
	public FbdataMakingAction() {
		super();
	}

	/**
	 * ＦＢデータ作成画面表示処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		FbdataMakingForm frm = (FbdataMakingForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_FBDATA_MAKING,
			Constants.TAB_ID_FBDATA_MAKING_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("mypage");
		}

		// 自社マスタ取得
		Company company = setCompanyMaster(request);

		// 自社の銀行コード
		String ownBankCode = company.getBankMasterCd();
		if (StringUtils.isNotEmpty(ownBankCode)
				&& (ownBankCode.length() == BANK_CD_LENGTH)) {
			Bank bankDetail = getBankDetail(ownBankCode, request);
			if (bankDetail != null) {
				frm.setBankMasterCd(bankDetail.getBankMasterCd());
				frm.setBankMasterName(bankDetail.getBankMasterName());
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * ＦＢデータ作成処理（実行ボタン押下時）
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		FbdataMakingForm frm = (FbdataMakingForm) form;

		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);

		// ＦＢヘッダー存在チェック
		FbdataMakingHeader headerBean = fbdataMakingLogic.getSearchFbHeader(frm
				.getPaymentDate().replace("/", ""));

		int flg = 0;
		if (headerBean != null) {
			flg = 0; // ＦＢデータ検索
		} else {
			flg = 1; // 支払トラン検索

			// 銀行情報取得（ＦＢヘッダー用）
			headerBean = fbdataMakingLogic.getSearchBankInfo(frm
					.getPaymentDate());
		}

		// 振込み情報取得（ＦＢデータ用）
		List<FbdataMakingData> dataBeanList = fbdataMakingLogic
				.getSearchPaymentInfo(frm.getPaymentDate(), frm
						.getBankMasterCd(), check, flg);

		// 合計件数、合計金額情報取得（ＦＢトレーラー用）
		FbdataMakingTrailer trailerBean = fbdataMakingLogic.getSearchTotalInfo(
			frm.getPaymentDate(), frm.getBankMasterCd(), check, flg);

		BigDecimal total = new BigDecimal(dataBeanList.size());

		/* トレーラー情報修正 */
		trailerBean.setTransferTotalNumberN(total);
		trailerBean.setStrTransferTotalNumberN(total.toString());

		// ヘッダー・レコード設定
		setHeader(frm, headerBean);
		// データ・レコード設定
		frm.setFbDataMakingList(dataBeanList);
		// トレーラー・レコード設定
		setTrailer(frm, trailerBean);

		// javascriptでの桁数丸め用に必要となる値取得
		getCheckDigit(frm, check);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward insert(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("insert.");
		}

		FbdataMakingForm frm = (FbdataMakingForm) form;
		String tantoCd = getLoginInfo(request).getTantoCd();

		try {
			// 登録処理(DELETE/INSERT)を実行
			fbdataMakingLogic.insert(frm, tantoCd);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("downLoadRefresh");
	}

	/**
	 * 削除／再作成処理
	 * 
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
			getLog().debug("delete.");
		}

		FbdataMakingForm frm = (FbdataMakingForm) form;

		// 削除処理を実行
		fbdataMakingLogic.delete(frm);

		/* メッセージ */
		saveMessage(request, "message.fbdatamaking.complete.delete");

		return mapping.findForward("reSearch");
	}

	/**
	 * ＦＢデータダウンロード処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward downLoadFbdata(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		List<String> fbdataList = new ArrayList<String>();

		if (getLog().isDebugEnabled()) {
			getLog().debug("downLoadFbdata.");
		}
		FbdataMakingForm frm = (FbdataMakingForm) form;

		// 支払日付を取得
		String paymentDate = frm.getPaymentDate().replace("/", "");

		// FB出力データを取得
		fbdataList = fbdataMakingLogic.getFbdataList(paymentDate);

		// FBデータファイル名の設定
		ResourceBundle rb = Constants.RB_SYSTEM_PROPERTIES;
		String fileName = rb.getString("fbdata.file.name");
		if (StringUtils.isEmpty(fileName)) {
			String strTimestamp = AecDateUtils.dateFormat(AecDateUtils
					.getCurrentTimestamp(), "yyyyMMddHHmmss");
			fileName = "fb" + strTimestamp + ".txt";
		}
		String fbFileName = new String(fileName.getBytes("Windows-31J"),
				"ISO-8859-1");

		// FBデータ出力
		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=\""
				+ fbFileName + "\"");
		response.setHeader("Cache-control", "private");
		response.flushBuffer();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(response
				.getOutputStream(), "Windows-31J"));
		for (String fbddata : fbdataList) {
			out.println(fbddata);
		}
		out.flush();
		out.close();

		return null;
	}

	/**
	 * クリア処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward clear(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		FbdataMakingForm frm = (FbdataMakingForm) form;
		frm.clear();
		return this.init(mapping, form, request, response);
	}

	/**
	 * 再描画処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward downLoadRefresh(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		FbdataMakingForm frm = (FbdataMakingForm) form;
		String svBankMasterCd = frm.getBankMasterCd();
		String svBankMasterName = frm.getBankMasterName();
		String svPaymentDate = frm.getPaymentDate();
		frm.clear();
		frm.setBankMasterCd(svBankMasterCd);
		frm.setBankMasterName(svBankMasterName);
		frm.setPaymentDate(svPaymentDate);

		/* ダウンロードフラグをON */
		frm.setFbDownloadFlag("1");

		return this.search(mapping, form, request, response);
	}

	/**
	 * ヘッダー・レコード設定
	 * 
	 * @param frm ＦＢデータ作成画面Form
	 * @param bean ＦＢヘッダーBean
	 */
	private void setHeader(final FbdataMakingForm frm,
			final FbdataMakingHeader bean) {
		frm.setStrDlDate(bean.getStrDlDate()); // ファイル作成日時
		frm.setTransferClientCd(bean.getTransferClientCd()); // 依頼人コード
		frm.setTransferClientName(bean.getTransferClientName()); // 依頼人名
		frm.setTrasferDate(bean.getTrasferDate()); // 振込指定日
		frm.setBankCd(bean.getBankMasterCd()); // 銀行コード
		frm.setBankKanaName(bean.getBankKanaName()); // 銀行名
		frm.setBranchCd(bean.getBranchCd()); // 支店コード
		frm.setBranchKanaName(bean.getBranchKanaName()); // 支店名
		frm.setAccountDivision(bean.getAccountDivision()); // 預金種目
		frm.setAccountNo(bean.getAccountNo()); // 口座番号
		frm.setFbHeader(bean);
	}

	/**
	 * トレーラ・レコード設定
	 * 
	 * @param frm ＦＢデータ作成画面Form
	 * @param bean ＦＢトレーラーBean
	 */
	private void setTrailer(final FbdataMakingForm frm,
			final FbdataMakingTrailer bean) {
		frm.setTransferTotalNumber(bean.getStrTransferTotalNumberN()); // 合計件数
		frm.setTransferTotalAmount(bean.getStrTransferTotalAmountN()); // 合計金額
		frm.setFbTrailer(bean);
	}

	/**
	 * 自社マスタ検索して、自社マスタデータを取得する。
	 * @param request HttpServletRequest
	 * @return 自社マスタデータ(検索結果が存在しない場合はnull)
	 */
	private Company setCompanyMaster(final HttpServletRequest request) {
		Company res = null;

		HttpSession session = request.getSession(false);
		PasswordCheck passBean = (PasswordCheck) session
				.getAttribute(Constants.COMPANY_INFO_KEY);

		try {
			res = companyDetailLogic.getEntity(passBean.getCompanyCd());
		} catch (NoDataException e) {
			addError(request, "errors.fbdatamaking.no.master",
				getMessageResource(request, "item.fbdatamaking.company"));
		}
		return res;
	}

	/**
	 * 銀行マスタコードから銀行マスタを検索する。
	 * @param bankMasterCode 銀行マスタコード(7桁の銀行コード＋支店コード)
	 * @param request HttpServletRequest
	 * @return 銀行詳細情報
	 */
	private Bank getBankDetail(final String bankMasterCode,
			final HttpServletRequest request) {
		Bank res = null;
		try {
			res = bankDetailLogic.getEntity(bankMasterCode);
		} catch (NoDataException e) {
			addError(request, "errors.fbdatamaking.no.master",
				getMessageResource(request, "item.fbdatamaking.bank"));
		}
		return res;
	}

	/**
	 * 数値桁数チェック部品からjavascriptでの桁数丸め用に必要となる値を取得
	 * @param frm ＦＢデータ作成画面Form
	 * @param check 数値項目用表示ロジッククラス
	 */
	private void getCheckDigit(final FbdataMakingForm frm,
			final CheckDigitUtilsLogic check) {
		NumberChkDisitDetail detail = check.getCheckDigit(
			FbdataMakingLogicImpl.UNIT_DIVISION_SIKINGAKU,
			FbdataMakingLogicImpl.VENDER_DIV_SI, null);
		if (detail.getSmallnumLength() != null) {
			frm.setDecimalPoint(detail.getSmallnumLength().toString()); // 小数点桁数
		}
		if (detail.getRoundDivision() != null) {
			frm.setRound(detail.getRoundDivision().toString()); // 端数区分
		}
	}

	/**
	 * エラーメッセージを追加する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @param objects 置換パラメータ
	 */
	private void addError(final HttpServletRequest request, final String key,
			final Object... objects) {
		ActionMessages messages = new ActionMessages();
		messages.add("", new ActionMessage(key, objects));
		addErrors(request, messages);
	}

	/**
	 * メッセージプロパティファイルから指定したkeyに対応する文字列を取得する。
	 * @param request HttpServletRequest
	 * @param key メッセージキー
	 * @return メッセージキーに対応するメッセージ文字列
	 */
	private String getMessageResource(final HttpServletRequest request,
			final String key) {
		MessageResources resource = getResources(request);
		return resource.getMessage(key);
	}

	/**
	 * 自社マスタ検索処理ロジッククラスを設定します。
	 * @param companyDetailLogic 自社マスタ検索処理ロジッククラス
	 */
	public void setCompanyDetailLogic(
			final CompanyDetailLogic companyDetailLogic) {
		this.companyDetailLogic = companyDetailLogic;
	}

	/**
	 * 銀行検索処理ロジッククラスを設定します。
	 * @param bankDetailLogic 銀行検索処理ロジッククラス
	 */
	public void setBankDetailLogic(final BankDetailLogic bankDetailLogic) {
		this.bankDetailLogic = bankDetailLogic;
	}

	/**
	 * ＦＢデータ作成 ロジッククラス を設定します。
	 * @param fbdataMakingLogic ＦＢデータ作成 ロジッククラス
	 */
	public void setFbdataMakingLogic(final FbdataMakingLogic fbdataMakingLogic) {
		this.fbdataMakingLogic = fbdataMakingLogic;
	}

}
