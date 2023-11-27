/*
 * Created on 2008/10/03
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 支払処理 Actionクラス.(カスタマイズ)
 * @author tosco
 */
public final class PaymentCsmListAction extends AbstractSearchAction {

	/** データ種別＝支払(4) */
	public static final int DATA_TYPE_PAYMENT = 4;

	/** 部署マスタ詳細 ロジッククラス */
	private OrganizationDetailLogic organizationDetailLogic;

	/** 支払入力 ロジッククラス */
	private PaymentCsmListLogic paymentCsmListLogic;

	/** 仕訳伝票（支払伝票） */
	private PaymentCsmListExcelDecorator paymentCsmListExcelDecorator;

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/**
	 * コンストラクタ.
	 */
	public PaymentCsmListAction() {
		super();
	}

	/**
	 * メニューから遷移時の初期表示処理（検索結果なし） {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PaymentCsmListForm paymentForm = (PaymentCsmListForm) form;

		/* 権限取得 */
		getControlAuthority(request, paymentForm, Constants.MENU_ID_PAYMENT,
			Constants.TAB_ID_PAYMENT_DETAIL);

		HttpSession session = request.getSession(false);
		if (session != null) {
			// ログインユーザ情報を取得
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			// ログインユーザ.部署コードを初期表示
			paymentForm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			try {
				// 部署マスタから部署名称を検索
				Organization organizationDetail = organizationDetailLogic
						.getEntity(paymentForm.getSrhOrganizationCd());
				if (organizationDetail != null) {
					// 検索結果が存在した場合は、部署名称の初期値として設定する。
					paymentForm.setSrhOrganizationName(organizationDetail
							.getOrganizationName());
				}
			} catch (NoDataException e) {
				// 部署コードが存在しない場合は、名称をクリア
				paymentForm.setSrhOrganizationName("");
			}
		}
		// 入金分類（分類マスタから取得）
		paymentForm.setCategoryList(getCategoryComboBox());

		// 初期値:出力区分＝全て(0)
		paymentForm.setSrhOutputDivision("0");
		return mapping.findForward("success");
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		PaymentCsmListForm paymentForm = (PaymentCsmListForm) form;

		// 前回検索結果をクリア
		paymentForm.setSearchList(null);

		/* 検索条件を生成 */
		AltPaymentPagerCondition condition = (AltPaymentPagerCondition) paymentForm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// データ種別
		condition.setDataType(DATA_TYPE_PAYMENT);
		// 部署コード
		condition.setOrganizationCd(getSQLPattern(paymentForm
				.getSrhOrganizationCd()));
		// 担当者コード
		condition.setTantoCd(getSQLPattern(paymentForm.getSrhTantoCd()));
		// 担当者名称
		condition.setTantoNm(getSQLPattern(paymentForm.getSrhTantoNm()));
		// 入金日付FROM
		condition.setPaymentDateFrom(paymentForm.getSrhPaymentDateFrom());
		// 入金日付To
		condition.setPaymentDateTo(paymentForm.getSrhPaymentDateTo());
		// 支払先
		condition.setSupplierCd(getSQLPattern(paymentForm.getSrhCustomerCd()));
		// 支払先
		condition
				.setSupplierNm(getSQLPattern(paymentForm.getSrhCustomerName()));
		// 入金番号FROM
		condition.setSlipNoFrom(paymentForm.getSrhSlipNoFrom());
		// 入金番号TO
		condition.setSlipNoTo(paymentForm.getSrhSlipNoTo());
		// 入金分類
		condition.setCategoryDivision(paymentForm.getSrhPaymentDivision());
		// 出力区分
		condition.setApprovalStatus(paymentForm.getSrhOutputDivision());
		// 伝票発行
		if (paymentForm.getSrhIssuedCheck()) {
			condition.setIssuedDivision("0");
		} else {
			condition.setIssuedDivision(null);
		}

		/* 帳票(Excel)用に検索条件を保持 */
		PaymentListConditionForReport reportCondition = new PaymentListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		paymentForm.setCondition(reportCondition);

		// 入金トランザクションテーブルから検索実行
		List<AltPayment> result = paymentCsmListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		// 検索結果が存在する場合
		for (AltPayment bean : result) {
			// 金額文字列にフォーマット
			// bean.setPaymentAmount(checker.format(SIKINGAKU, AecNumberUtils
			// .convertBigDecimal(bean.getPaymentAmount()))); // 支払金額
			bean.setPaymentAmount(checker.format(KINGAKU, AecNumberUtils
					.convertBigDecimal(bean.getPaymentAmount()))); // 支払金額
		}
		// 検索結果を設定
		paymentForm.setSearchList(result);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	// 内部ロジック--------------------------------------------------------------------------------------
	/**
	 * 対象文字列がnullで無い場合、前方一致用の文字を追加する。
	 * @param s 対象文字列
	 * @return
	 */
	private String getSQLPattern(final String s) {
		String res = null;
		if (StringUtils.isNotEmpty(s)) {
			// 検索データが設定されている場合は前方一致にする。
			res = s + "%";
		}
		return res;
	}

	/**
	 * 数値フォーマットに変換する
	 * @param s 対象文字列
	 * @return
	 */
	// private String getFormatNumber(final String s) {
	// String res = null;
	// if (StringUtils.isNotEmpty(s)) {
	// BigDecimal dec = new BigDecimal(s);
	// DecimalFormat df = new DecimalFormat("###,###.####");
	// res = df.format(dec);
	// }
	// return res;
	// }
	/**
	 * 承認依頼.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRequest.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");
		BigDecimal statusMode = new BigDecimal("1");

		/* ステータス更新 */
		paymentCsmListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
			request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 承認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");
		BigDecimal statusMode = new BigDecimal("2");

		/* ステータス更新 */
		paymentCsmListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
			request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 否認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward negation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("negation.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");
		BigDecimal statusMode = new BigDecimal("3");

		/* ステータス更新 */
		paymentCsmListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
			request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 承認取消.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalCancel.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");
		BigDecimal statusMode = new BigDecimal("4");

		/* ステータス更新 */
		paymentCsmListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
			request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 印刷処理（仕訳伝票（支払伝票））
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;
		ArrayList<String> creditNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (AltPayment bean : frm.getSearchList()) {

			if (!bean.getChecked()) {
				continue;
			} else {
				// 処理を行う伝票番号リストを作成
				creditNoList.add(bean.getSlipNo());
			}

		}

		if (!creditNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* 仕訳伝票（支払伝票）出力処理 */
			info = paymentCsmListExcelDecorator.createReport(creditNoList,
				tantoNm, null, request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);

			/* 伝票発行フラグ更新 */
			paymentCsmListLogic.updateIssuedDivision(creditNoList,
				getLoginInfo(request).getTantoCd());

		}

		return mapping.findForward("success");
	}

	/**
	 * 印刷処理（手形振出指示書）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reportTegata(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;
		ArrayList<String> creditNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (AltPayment bean : frm.getSearchList()) {

			if (!bean.getChecked()) {
				continue;
			} else if (!bean.getCategoryDivision().equals("1")) {
				continue;
			} else {
				// 処理を行う伝票番号リストを作成
				creditNoList.add(bean.getSlipNo());
			}

		}

		if (!creditNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* 手形振出指示書出力処理 */
			info = paymentCsmListExcelDecorator.createReportTegata(
				creditNoList, tantoNm, AecDateUtils.getCurrentTimestamp(),
				request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);
		}

		return mapping.findForward("success");
	}

	/**
	 * 印刷処理（お支払通知書）
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reportPaymentInform(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;
		ArrayList<String> creditNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ログインユーザー部署取得
		String loginUserOrganizationNm = getLoginInfo(request)
				.getOrganizationName();

		for (AltPayment bean : frm.getSearchList()) {

			if (!bean.getChecked()) {
				continue;
			} else {
				// 処理を行う伝票番号リストを作成
				creditNoList.add(bean.getSlipNo());
			}
		}

		if (!creditNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* お支払通知書出力処理 */
			// info =
			// paymentCsmListExcelDecorator.createReportPaymentInform(creditNoList,
			// tantoNm, null);
			info = paymentCsmListExcelDecorator.createReportPaymentInform(
				creditNoList, tantoNm, loginUserOrganizationNm, AecDateUtils
						.getCurrentTimestamp(), request.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);
		}

		return mapping.findForward("success");
	}

	/**
	 * EXCEL作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward reportList(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("reportList.");
		}

		PaymentCsmListForm frm = (PaymentCsmListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = paymentCsmListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 支払分類コンボボックス内容取得
	 * @return List<ComboBoxItems> 支払コンボボックス内容リスト
	 */
	@SuppressWarnings("unchecked")
	public List<ComboBoxItems> getCategoryComboBox() {
		// 検索結果を格納用リスト
		List<ComboBoxItems> categoryList = new ArrayList<ComboBoxItems>();
		// 分類マスタ.データ種別＝支払(4)のみ対象
		BigDecimal dataType = new BigDecimal(DATA_TYPE_PAYMENT);

		// コンボボックスの先頭行設定
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("すべて");

		categoryList.add(comboBlank);

		// 分類マスタから検索結果取得
		List<ClassificationListForComboboxes> bean = paymentCsmListLogic
				.getClassificationList(dataType);

		/* 分類コード順のソート */
		Collections.sort(bean, new ClassificationComparator(
				ClassificationComparator.ASC));

		// 分類コード、分類名称を設定
		for (ClassificationListForComboboxes list : bean) {
			ComboBoxItems combo = new ComboBoxItems();
			// 分類コード設定(Value値)
			combo.setValues(list.getCategoryDivision());
			// 分類名称をラベルとして設定
			combo.setLabales(list.getCategoryName());
			categoryList.add(combo);
		}

		return categoryList;
	}

	/* -------------------- setter -------------------- */
	/**
	 * 部署マスタ詳細 ロジッククラスを設定する。
	 * @param organizationDetailLogic 部署マスタ詳細 ロジッククラス
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * 仕訳伝票（支払伝票）検索画面ロジッククラスを設定します。
	 * @param paymentCsmListExcelDecorator 仕訳伝票（支払伝票）検索画面ロジッククラス
	 */
	public void setPaymentCsmListExcelDecorator(
			final PaymentCsmListExcelDecorator paymentCsmListExcelDecorator) {
		this.paymentCsmListExcelDecorator = paymentCsmListExcelDecorator;
	}

	/**
	 * paymentCsmListLogicを設定します。
	 * @param paymentCsmListLogic paymentCsmListLogic
	 */
	public void setPaymentCsmListLogic(
			final PaymentCsmListLogic paymentCsmListLogic) {
		this.paymentCsmListLogic = paymentCsmListLogic;
	}
}
