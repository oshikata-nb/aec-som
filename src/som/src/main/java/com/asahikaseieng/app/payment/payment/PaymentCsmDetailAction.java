/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import com.asahikaseieng.app.checkholiday.CheckHolidayLogic;
import com.asahikaseieng.app.checkholiday.CheckHolidayResult;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.accounts.AccountsListLogic;
import com.asahikaseieng.app.master.bank.BankDetailLogic;
import com.asahikaseieng.app.master.classification.ClassificationDetailAction;
import com.asahikaseieng.app.master.company.CompanyDetailLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.entity.master.bank.Bank;
import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.check.PasswordCheck;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentVender;
import com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail.PaymentHeaderDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail.PaymentHeaderDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetStockReductionCallDto;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 支払入力詳細 Actionクラス.(カスタマイズ)
 * @author tosco
 */
public final class PaymentCsmDetailAction extends AbstractAction {

	/** 更新フラグ-更新モード */
	protected static final int INSERT_FLAG_UPDATE = 0;

	/** 更新フラグ-挿入モード */
	protected static final int INSERT_FLAG_INSERT = 1;

	/** 更新フラグ-参照モード */
	protected static final int INSERT_FLAG_VIEW = 2;

	/** 分類マスタ-データ種別:4(支払) */
	private static final BigDecimal CLASSIFICATION_DATA_TYPE = new BigDecimal(
			"4");

	/** 取引先区分=TS(得意先) */
	private static final String VENDER_DIVISION_TS = "TS";

	/** 取引先区分=SI(仕入れ先) */
	private static final String VENDER_DIVISION_SI = "SI";

	/** 分類マスタ-フラグON */
	private static final int FLG_ON = 1;

	/** 分類マスタ-フラグOFF */
	private static final int FLG_OFF = 0;

	/** 支払トランザクション－支払伝票発行区分 */
	private static final int ISSUED_DIVISION_NO = 9;

	/** 支払トランザクション－ﾃﾞｰﾀ種別 :4(支払) */
	private static final String CREDIT_DATA_TYPE = "4";

	/** 遷移先－売掛元帳 */
	private static final String FORWARD_ARLEDGER = "backArLeder";

	/** 遷移先－買掛元帳 */
	private static final String FORWARD_APLEDGER = "backApLeder";

	/** 承認ステータス－入力中 */
	private static final String APPROVAL_STATUS_INPUT = "1";

	/** 取引先マスタ－振込み区分(1:自社) */
	private static final int COMPANY_PAYING_CHECK_DIVISION_OWN = 1;

	/** 銀行コード（銀行＋支店)の長さ(7byte) */
	private static final int BANK_CD_LENGTH = 7;

	/** 手形 */
	public static final String NOTE = "1";

	/** 振込 */
	public static final String TRANSFER = "3";

	/** 郵便振替 */
	public static final String POSTAL_TRANSFER = "4";

	/** 手数料 */
	public static final String COMMISSION = "6";

	/** 仕入割引 */
	public static final String PURCHASE_DISCOUNT = "7";

	private static final BigDecimal EVERYDAY = new BigDecimal("0");

	private static final BigDecimal VENDER_NODATA = new BigDecimal("1");

	private static final BigDecimal VENDER_NOCAL = new BigDecimal("2");

	private static final BigDecimal CAL_NODATA = new BigDecimal("3");

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/** 満期日計算用 */
	private static final BigDecimal END_DAY = new BigDecimal("30");

	/** 支払月区分(当月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION1 = new BigDecimal("1");

	/** 支払月区分(翌月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION2 = new BigDecimal("2");

	/** 支払月区分(翌々月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION3 = new BigDecimal("3");

	/** 支払月区分(3ヶ月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION4 = new BigDecimal("4");

	/** 支払月区分(4ヶ月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION5 = new BigDecimal("5");

	/** 支払月区分(5ヶ月) */
	private static final BigDecimal CREDIT_MONTH_DIVISION6 = new BigDecimal("6");

	/** 部署マスタ詳細 ロジッククラス */
	private OrganizationDetailLogic organizationDetailLogic;

	/** 科目マスタ一覧取得 ロジッククラス */
	private AccountsListLogic accountsListLogic;

	/** 支払詳細処理ロジッククラス */
	private PaymentCsmDetailLogic paymentCsmDetailLogic;

	/** 銀行検索処理ロジッククラス */
	private BankDetailLogic bankDetailLogic;

	/** 自社マスタ検索処理ロジッククラス */
	private CompanyDetailLogic companyDetailLogic;

	/** 休日チェックロジッククラス */
	private CheckHolidayLogic checkHolidayLogic;

	private PaymentHeaderDetailDao paymentHeaderDetailDao;

	/**
	 * コンストラクタ.
	 */
	public PaymentCsmDetailAction() {
		super();
	}

	/**
	 * 支払入力詳細画面表示処理
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

		return mapping.findForward("success");
	}

	/**
	 * 新規登録－初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initNew(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_PAYMENT,
			Constants.TAB_ID_PAYMENT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 初期化する
		detailForm.clear();

		detailForm.setInsertFlg(INSERT_FLAG_INSERT); // 更新フラグ=1:新規入力

		HttpSession session = request.getSession(false);
		if (session != null) {
			// ログインユーザ情報を取得
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			// ログインユーザ.部署コードを初期表示
			detailForm.setOrganizationCd(loginInfo.getOrganizationCd());

			// 部署マスタから部署名称を検索
			getSelectionName(detailForm);
		}
		// 支払分類（分類マスタから取得）
		detailForm.setCategoryList(getCategoryComboBox());
		// 科目（科目マスタから取得）
		detailForm.setAccountList(PaymentCsmUtil
				.getClassComboBox(accountsListLogic));

		// 新規行を1行表示
		List<PaymentCsmBean> list = new ArrayList<PaymentCsmBean>();
		list.add(getNewLine(1, detailForm.getStrPaymentDate()));
		detailForm.setDetailList(list);

		return mapping.findForward("success");
	}

	/**
	 * 更新入力－初期表示処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initUpdate(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initNew.");
		}
		String forward = "success";

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_PAYMENT,
			Constants.TAB_ID_PAYMENT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 支払番号退避
		String slipNo = detailForm.getSlipNo();
		// 初期化する
		detailForm.clear();
		detailForm.setSlipNo(slipNo);

		detailForm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(detailForm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			forward = "back";
		}
		return mapping.findForward(forward);
	}

	/**
	 * 照会－初期表示処理（売掛元帳からの遷移)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initArLedger(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initArLedger.");
		}
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_PAYMENT,
			Constants.TAB_ID_PAYMENT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, detailForm,
			FORWARD_ARLEDGER);
		return mapping.findForward(forward);
	}

	/**
	 * 照会－初期表示処理（買掛元帳からの遷移)
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward initApLedger(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initApLedger.");
		}
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, detailForm, Constants.MENU_ID_PAYMENT,
			Constants.TAB_ID_PAYMENT_DETAIL);

		if (!detailForm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, detailForm,
			FORWARD_APLEDGER);
		return mapping.findForward(forward);
	}

	/**
	 * 更新入力－行追加処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward addRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("addRow.");
		}

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
		List<PaymentCsmBean> detailList = detailForm.getDetailList();

		// 必須入力チェック(支払分類、支払金額、科目を行追加時の必須項目とする。)
		// int index = 2;
		// for (PaymentCsmBean bean : detailList) {
		// // 支払方法
		// if (StringUtils.isEmpty(bean.getCategoryDivision())) {
		// addError(
		// request,
		// "errors.addrow",
		// String.valueOf(index),
		// getMessageResource(request, "item.payment.categoryDivision"));
		// }
		// 支払金額
		// if (StringUtils.isEmpty(bean.getPaymentAmount())) {
		// addError(request, "errors.addrow", String.valueOf(index),
		// getMessageResource(request, "item.payment.paymentAmount"));
		// }
		// // 科目
		// if (StringUtils.isEmpty(bean.getCreditTitleCd())) {
		// addError(request, "errors.addrow", String.valueOf(index),
		// getMessageResource(request, "item.payment.debitTitleCd"));
		// }
		// index++;
		// }
		ActionMessages messages = getErrors(request);
		if (messages.isEmpty()) {
			// 必須入力チェックＯＫ時
			// 現在の行数
			int count = detailList.size();
			// 新規行追加
			detailList
					.add(getNewLine(count + 1, detailForm.getStrPaymentDate()));
		}
		return mapping.findForward("success");
	}

	/**
	 * 更新入力－行削除処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delRow(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("delRow.");
		}

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
		List<PaymentCsmBean> detailList = detailForm.getDetailList();
		int len = detailList.size();
		for (int i = len - 1; i >= 0; i--) {
			PaymentCsmBean bean = (PaymentCsmBean) detailList.get(i);
			if (bean.isDeleteFlag()) {
				// 削除対象行
				detailList.remove(i);
			}
		}
		if (detailList.size() <= 0) {
			// 明細行が空になった場合は、新規行を1行追加する
			detailList.add(getNewLine(1, detailForm.getStrPaymentDate()));
		} else {
			// 行番号振りなおし
			int index = 1;
			for (PaymentCsmBean bean : detailList) {
				bean.setRowNo(String.valueOf(index));
				index++;
			}
		}
		return mapping.findForward("success");
	}

	/**
	 * 更新処理
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
			getLog().debug("update.");
		}

		// 遷移先
		String forward = "back";

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		if (detailForm.getDetailList().size() == 0) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.payment.detail.list");
			return mapping.findForward("success");
		}

		List<PaymentCsmBean> detailList = detailForm.getDetailList();

		// 部署・取引先のマスタ存在チェック
		boolean checkHeader = checkMasterHeader(detailForm, request);
		// 明細行マスタ存在チェック
		boolean chechMaster = checkMasterDetail(detailList, request);
		// 自社マスタからデータ取得
		boolean checkCompany = setCompanyMaster(detailForm, request);
		// 分類＝相殺時取引先チェック
		boolean checkSetOff = checkSetoffVender(detailForm, request);

		if (checkHeader & chechMaster & checkCompany & checkSetOff) {
			// マスタチェックＯＫ
			// ヘッダ部データを明細部に設定
			List<AltPayment> updateList = getUpdateBeans(detailForm, detailList);

			// 更新時初期値設定
			setUpdateInitValue(updateList, detailForm, request);

			// 削除＆挿入
			paymentCsmDetailLogic.update(updateList);

			// 登録完了メッセージ
			saveMessage(request, "message.complete.update");
		} else {
			forward = "success";
		}
		return mapping.findForward(forward);
	}

	/**
	 * 挿入処理
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
		// 遷移先
		String forward = "back";

		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		if (detailForm.getDetailList().size() == 0) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.payment.detail.list");
			return mapping.findForward("success");
		}

		List<PaymentCsmBean> detailList = detailForm.getDetailList();

		// 部署・取引先のマスタ存在チェック
		boolean checkHeader = checkMasterHeader(detailForm, request);
		// 明細行マスタ存在チェック
		boolean chechMaster = checkMasterDetail(detailList, request);
		// 自社マスタからデータ取得
		boolean checkCompany = setCompanyMaster(detailForm, request);
		// 分類＝相殺時取引先チェック
		boolean checkSetOff = checkSetoffVender(detailForm, request);
		// 仕入割引データ存在チェック
		boolean checkStock = checkPaymentHeader(detailForm, request);
		// 支払データ存在チェック
		boolean checkPayment = checkPayment(detailForm, request);

		if (checkPayment) {
			addError(request, "errors.exist.payment.data");
			return mapping.findForward("success");
		}

		if (checkHeader & chechMaster & checkCompany & checkSetOff) {
			// マスタチェックＯＫ

			// ヘッダ部データを明細部に設定
			List<AltPayment> updateList = getUpdateBeans(detailForm, detailList);

			// 挿入時初期値設定
			setInsertInitValue(updateList, request);

			// 挿入
			String message = paymentCsmDetailLogic.insert(updateList);
			if (StringUtils.isEmpty(message)) {
				// 登録完了メッセージ
				saveMessage(request, "message.complete.insert");
			} else {
				// エラー発生時
				addError(request, "errors.payment.insert");
				forward = "success";
			}
		} else {
			// マスタ存在チェックエラー
			forward = "success";
		}

		return mapping.findForward(forward);
	}

	/**
	 * 支払入力削除処理
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
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
		String slipNo = detailForm.getSlipNo();

		// 削除
		paymentCsmDetailLogic.delete(slipNo);

		// メッセージ
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 科目変更処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeCreditTitle(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("subdebit.");
		}
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
		List<PaymentCsmBean> detailList = detailForm.getDetailList();
		// イベント発生元行のデータを取得
		PaymentCsmBean bean = detailList.get(detailForm.getIndex());
		// 補助科目一覧
		bean.setSubAccounts(PaymentCsmUtil.getAccountsSubComboBox(bean
				.getCreditTitleCd(), accountsListLogic));

		return mapping.findForward("success");
	}

	/**
	 * 支払方法変更イベント処理
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward changeCategory(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("changeCategory.");
		}
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		List<PaymentCsmBean> detailList = detailForm.getDetailList();
		// イベント発生元行のデータを取得
		PaymentCsmBean bean = detailList.get(detailForm.getIndex());

		String category = bean.getCategoryDivision(); // 分類コード
		// String customerCode = detailForm.getCustomerCd(); // 取引先コード

		if (StringUtils.isNotEmpty(category)) {
			/* /なし文字列日付 ---> 日付 */
			Timestamp paymentDate = getDateformat(detailForm
					.getStrPaymentDate());

			/* 日付 ---> 文字列日付 */
			String strPaymentDate = AecDateUtils.dateFormat(paymentDate,
				"yyyy/MM/dd");

			// 手形必須区分
			bean.setNoteDiv(0);
			ClassificationDetail classBean = null;
			// 分類マスタを分類コードで検索
			classBean = paymentCsmDetailLogic.getClassificationEntity(
				CLASSIFICATION_DATA_TYPE, category);

			// 科目・補助科目初期表示値設定
			bean.setCreditTitleCd(classBean.getCreditAccountsCd()); // 貸方科目

			// 銀行に関する項目をクリア
			bean.setBankDivision(FLG_OFF);
			bean.setBankCd(null); // 銀行本店＋支店
			bean.setAccountDivision(null); // 預金種別
			bean.setAccountNo(null); // 口座番号
			bean.setFeeFlag(FLG_OFF); // 振込手数料計算フラグをOFF

			// 手形必須区分
			if (classBean.getNoteDivision().intValue() == FLG_ON) {
				bean.setNoteDiv(FLG_ON);

				// 2011/5/23 手形の場合は「約束手形」をデフォルト表示
				bean.setNoteDivision(new BigDecimal(1));
				bean.setNoteDivisionString("1");

				for (AltPayment pay : detailForm.getHeaderList()) {
					if (StringUtils.isNotEmpty(strPaymentDate)) {
						/* 支払日と支払予定日が同じか */
						if (strPaymentDate.equals(pay
								.getStrCreditScheduledDate())) {
							BigDecimal quotient = detailForm.getNoteSight()
									.divide(END_DAY, BigDecimal.ROUND_DOWN); /* 商 */
							BigDecimal rest = detailForm.getNoteSight()
									.remainder(END_DAY); /* 余り */
							Timestamp noteDate = null; /* 満期日 */
							int y = AecNumberUtils.convertBigDecimal(
								AecDateUtils.dateFormat(pay.getPayableDate(),
									"yyyy")).intValue(); /* 支払締日(年) */
							int m = AecNumberUtils.convertBigDecimal(
								AecDateUtils.dateFormat(pay.getPayableDate(),
									"MM")).intValue(); /* 支払締日(月) */
							int d = AecNumberUtils.convertBigDecimal(
								AecDateUtils.dateFormat(pay.getPayableDate(),
									"dd")).intValue(); /* 支払締日(日) */
							int a = 0;

							/* 支払月判定 */
							if (detailForm.getCreditMonthDivision().equals(
								CREDIT_MONTH_DIVISION1)) {
								a = CREDIT_MONTH_DIVISION1.subtract(
									BigDecimal.ONE).intValue(); /* 当月 */
							} else if (detailForm.getCreditMonthDivision()
									.equals(CREDIT_MONTH_DIVISION2)) {
								a = CREDIT_MONTH_DIVISION2.subtract(
									BigDecimal.ONE).intValue(); /* 翌月 */
							} else if (detailForm.getCreditMonthDivision()
									.equals(CREDIT_MONTH_DIVISION3)) {
								a = CREDIT_MONTH_DIVISION3.subtract(
									BigDecimal.ONE).intValue(); /* 翌々月 */
							} else if (detailForm.getCreditMonthDivision()
									.equals(CREDIT_MONTH_DIVISION4)) {
								a = CREDIT_MONTH_DIVISION4.subtract(
									BigDecimal.ONE).intValue(); /* 3ヶ月 */
							} else if (detailForm.getCreditMonthDivision()
									.equals(CREDIT_MONTH_DIVISION5)) {
								a = CREDIT_MONTH_DIVISION5.subtract(
									BigDecimal.ONE).intValue(); /* 4ヶ月 */
							} else if (detailForm.getCreditMonthDivision()
									.equals(CREDIT_MONTH_DIVISION6)) {
								a = CREDIT_MONTH_DIVISION6.subtract(
									BigDecimal.ONE).intValue(); /* 5ヶ月 */
							}

							bean.setDrawalDateString(strPaymentDate); /* 振出日 */

							Calendar cal = Calendar.getInstance();

							if (detailForm.getNoteSight().equals(
								BigDecimal.ZERO)) {
								cal.set(y, m - 1 + a, d);

								/* 日が変わった場合月末にする */
								if (d != cal.get(Calendar.DATE)) {
									cal.set(y, m - 1 + a, 1);
									cal.set(y, m - 1 + a, cal
											.getActualMaximum(Calendar.DATE));
								}
							} else if (rest.equals(BigDecimal.ZERO)) {
								cal.set(y, m - 1 + a + quotient.intValue(), 1);
								cal.set(y, m - 1 + a + quotient.intValue(), cal
										.getActualMaximum(Calendar.DATE));
							} else if (!rest.equals(BigDecimal.ZERO)) {
								cal.set(y, m - 1 + a + quotient.intValue() + 1,
									rest.intValue());

								/* 日が変わった場合月末にする */
								if (rest.intValue() != cal.get(Calendar.DATE)) {
									cal.set(y, m - 1 + a + quotient.intValue()
											+ 1, 1);
									cal.set(y, m - 1 + a + quotient.intValue()
											+ 1, cal
											.getActualMaximum(Calendar.DATE));
								}
							}

							noteDate = AecDateUtils
									.getTimestampYmdFormat(getDateString(new Date(
											cal.getTimeInMillis())));
							bean.setNoteDateString(AecDateUtils.dateFormat(
								noteDate, "yyyy/MM/dd")); /* 満期日 */
							break;
						}
					}
				}
			} else {
				// 2011/5/23 手形の以外場合は空欄をデフォルト表示
				bean.setNoteDivision(new BigDecimal(0));
				bean.setNoteDivisionString("0");

				bean.setNoteDiv(FLG_OFF);
				bean.setDrawalDateString(null); /* 振出日 */
				bean.setNoteDateString(null); /* 満期日 */
			}
		} else {
			// 2011/5/23 手形の以外場合は空欄をデフォルト表示
			bean.setNoteDivision(new BigDecimal(0));
			bean.setNoteDivisionString("0");

			// 支払分類がブランクなのでクリア
			bean.setDebitTitleCd(""); // 科目
			bean.setDebitSubTitleCd(""); // 補助科目
			bean.setNoteDiv(FLG_OFF);
			// 銀行コード
			bean.setBankCd("");
			// 預金種別
			bean.setAccountDivision("");
			// 口座番号
			bean.setAccountNo("");
			bean.setBankDivision(0);
			bean.setDrawalDateString(null); /* 振出日 */
			bean.setNoteDateString(null); /* 満期日 */
		}
		return mapping.findForward("success");
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
		String forward = "back";
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
		String back = detailForm.getBackForward();
		if (StringUtils.isNotEmpty(back)) {
			forward = back;
		}

		return mapping.findForward(forward);
	}

	/**
	 * 支払先リスト作成処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	// public ActionForward autoSupplierList(final ActionMapping mapping,
	// final ActionForm form, final HttpServletRequest request,
	// final HttpServletResponse response) throws Exception {
	//
	// if (getLog().isDebugEnabled()) {
	// getLog().debug("autoSupplierList.");
	// }
	// PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;
	//
	// // 支払先を検索
	// List<AltPaymentVender> venderList = paymentCsmDetailLogic
	// .getVenderList(VENDER_DIVISION_SI, AecTextUtils
	// .likeFilter(detailForm.getCustomerCd()));
	//
	// detailForm.setAutoList(venderList);
	//
	// return mapping.findForward("autoComplete");
	// }
	/**
	 * 振込手数料計算処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward calcFee(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("calcFee.");
		}
		PaymentCsmDetailForm detailForm = (PaymentCsmDetailForm) form;

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		// 自社マスタ取得
		Company company = setCompanyMaster(request);
		// 自社の銀行コード
		String ownBankCode = company.getBankMasterCd();
		if (StringUtils.isNotEmpty(ownBankCode)
				&& (ownBankCode.length() == BANK_CD_LENGTH)) {
			try {
				Bank bankDetail = getBankDetail(ownBankCode);
				// 存在する場合、振込手数料、データ種別、振込手数料分類コードを取得
				// BigDecimal dataType = bankDetail.getDataType();
				String categoryDivision = bankDetail.getCategoryDivision();
				BigDecimal fee = bankDetail.getFee();

				// 手数料行として、新規に一行追加
				// 現在の行数
				int count = detailForm.getDetailCount();
				int index = detailForm.getIndex();
				// 新規行追加
				PaymentCsmBean bean = getNewLine(count + 1, detailForm
						.getStrPaymentDate());
				bean.setPaymentAmount(fee.toString()); // 振込手数料を支払い金額に設定
				bean.setCategoryDivision(categoryDivision); // 支払分類
				// イベント発生行を追加した行に設定
				detailForm.setIndex(index + 1);
				List<PaymentCsmBean> detailList = detailForm.getDetailList();
				detailList.add(bean); // 行追加

				// イベント発生行を取得
				PaymentCsmBean calcBean = detailList.get(index);
				String amount = replace(calcBean.getPaymentAmount(), ",", ""); // ,を除く
				// 支払金額から振込手数料を減額する。
				BigDecimal resultAmount = new BigDecimal(amount).subtract(fee);
				// calcBean.setPaymentAmount(checker.format(SIKINGAKU,
				// resultAmount));
				calcBean
						.setPaymentAmount(checker.format(KINGAKU, resultAmount));

				// 新規行追加、分類変更処理を呼ぶ
				changeCategory(mapping, form, request, response);
			} catch (NoDataException e) {
				// 銀行コードが存在しない場合は、何もしない
				if (getLog().isDebugEnabled()) {
					getLog().debug("銀行コードが存在しない.");
				}
			}
		}

		return mapping.findForward("success");
	}

	// 内部メソッド-------------------------------------------------------------

	/**
	 * 部署マスタから部署名称を取得し、DepositDetailFormに設定する。
	 * @param detailForm 詳細画面FORM
	 */
	private void getSelectionName(final PaymentCsmDetailForm detailForm) {
		try {
			// 部署マスタから部署名称を検索
			Organization organizationDetail = organizationDetailLogic
					.getEntity(detailForm.getOrganizationCd());
			if (organizationDetail != null) {
				// 検索結果が存在した場合は、部署名称の初期値として設定する。
				detailForm.setOrganizationName(organizationDetail
						.getOrganizationName());
			}
		} catch (NoDataException e) {
			// 部署コードが存在しない場合は、名称をクリア
			detailForm.setOrganizationName("");
		}
	}

	/**
	 * 新規明細行を取得する
	 * @param row 行番号
	 * @param paymentDate 支払日付
	 * @return CreditBean
	 */
	private PaymentCsmBean getNewLine(final int row, final String strPaymentDate) {
		PaymentCsmBean data = new PaymentCsmBean();
		data.setRowNo(String.valueOf(row)); // 行番号
		data.setSubAccounts(PaymentCsmUtil.getAccountsSubComboBox(null,
			accountsListLogic));

		if (StringUtils.isEmpty(data.getSummary())) {
			if (!StringUtils.isEmpty(strPaymentDate)) {
				Timestamp paymentDate = AecDateUtils
						.getTimestampYmdFormat(strPaymentDate);

				if (paymentDate != null) {
					data.setSummary(AecDateUtils.dateFormat(paymentDate, "yy")
							+ "年" + AecDateUtils.dateFormat(paymentDate, "MM")
							+ "月支払");
				}
			}
		}

		return data;
	}

	/**
	 * 取引先コードから銀行コード。預金種別・口座番号を取得する。
	 * @param detailForm 詳細画面Form
	 * @param bean 明細データ
	 * @param request HttpServletRequest
	 * @param mapping ActionMapping
	 * @param response HttpServletResponse
	 */
	// private void getCustomerBank(final PaymentCsmDetailForm detailForm,
	// final PaymentCsmBean bean, final HttpServletRequest request,
	// final ActionMapping mapping, final HttpServletResponse response)
	// throws Exception {
	// String customerCode = detailForm.getCustomerCd(); // 取引先コード
	// try {
	// Vender vender = paymentCsmDetailLogic.getVenderEntity(customerCode,
	// VENDER_DIVISION_SI);
	// // 銀行コード
	// String bankCode = vender.getBankCd();
	// bean.setBankCd(bankCode);
	// if (StringUtils.isNotEmpty(bankCode)
	// && (bankCode.length() == BANK_CD_LENGTH)) {
	// try {
	// Bank bankDetail = getBankDetail(bankCode);
	// // 存在する場合、銀行名を更新
	// bean.setBankName(bankDetail.getBankName() + " "
	// + bankDetail.getBranchName());
	// } catch (NoDataException e) {
	// // 銀行コードが存在しない場合は、マスタチェックエラー
	// bean.setBankName("");
	// }
	// } else {
	// // 銀行コードが無いか不正の場合は銀行名はブランク
	// bean.setBankName("");
	// }
	// // 預金種別
	// bean.setAccountDivision(vender.getAccountDivision().toString());
	// // 口座番号
	// bean.setAccountNo(vender.getAccountNo());
	//
	// // 振込区分=1(自社)の場合、振込手数料計算フラグを立てる->支払金額を入力したときに手数料計算を行う。
	// if (vender.getPayingCheckDivision().intValue() ==
	// COMPANY_PAYING_CHECK_DIVISION_OWN) {
	// // 振込区分が自社の場合
	// bean.setFeeFlag(FLG_ON); // 振込手数料計算フラグをON
	// if ((detailForm.getDetailCount() == 1)
	// && StringUtils.isNotEmpty(bean.getPaymentAmount())) {
	// // 既存データが1行のみで、支払金額が入力されている場合
	// // 要は既存データの支払分類を銀行振込等に変えた場合)
	// calcFee(mapping, detailForm, request, response);
	// }
	// }
	// } catch (NoDataException e) {
	// if (getLog().isDebugEnabled()) {
	// getLog().debug("取引先マスタデータ無：" + customerCode);
	// }
	// }
	// }
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
			addError(request, "errors.nodata.master", getMessageResource(
				request, "item.deposit.company"));
		}
		return res;
	}

	/**
	 * 7桁の銀行コード＋支店コードから銀行マスタを検索する。
	 * @param bankBranchCode 7桁の銀行コード＋支店コード
	 * @return 銀行詳細情報
	 * @throws NoDataException データが存在しない場合発生
	 */
	private Bank getBankDetail(final String bankBranchCode)
			throws NoDataException {
		Bank bankDetail = bankDetailLogic.getEntity(bankBranchCode);
		return bankDetail;
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
	 * 部署マスタ・取引先マスタの存在チェックを行う。
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean checkMasterHeader(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		try {
			// 部署マスタから部署名称を検索
			Organization organizationDetail = organizationDetailLogic
					.getEntity(detailForm.getOrganizationCd());
			if (organizationDetail != null) {
				// 検索結果が存在した場合は、部署名称を設定する。
				detailForm.setOrganizationName(organizationDetail
						.getOrganizationName());
				// // 会計部門を設定
				// detailForm.setAccountSectionCode(organizationDetail
				// .getAccountSectionCd());
			}
		} catch (NoDataException e) {
			// 部署コードが存在しない場合は、マスタチェックエラー
			detailForm.setOrganizationName("");
			addError(request, "errors.nodata.master", getMessageResource(
				request, "item.payment.organizationCd"));
			res = false;
		}
		// 取引先マスタから検索
		boolean checkVender = getVenderName(detailForm, request);
		if (!checkVender) {
			res = false;
		}

		/* 名称マスタから全社共通部門コードを取得 */
		NamesDetail bean = paymentCsmDetailLogic.getNamesEntity();

		if (bean == null) {
			detailForm.setCreditSectionCd(null);
		} else {
			detailForm.setCreditSectionCd(bean.getName01());
		}

		return res;
	}

	/**
	 * 取引先マスタから検索
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean getVenderName(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		// 支払先存在チェック
		if (StringUtils.isNotEmpty(detailForm.getCustomerCd())) {
			try {
				// 取引先を検索
				Vender vender = paymentCsmDetailLogic.getVenderEntity(
					detailForm.getCustomerCd(), VENDER_DIVISION_SI);
				// 振込区分をFormに設定
				detailForm.setPayingCheckDivision(vender
						.getPayingCheckDivision().intValue());
				detailForm.setBankCd(vender.getOtherBankCd());
				detailForm.setAccountDivision(vender.getOtherAccountDivision()
						.toString());
				detailForm.setAccountNo(vender.getOtherAccountNo());
				detailForm.setDebitSectionCd(vender.getSectionCd());
				detailForm.setDebitTitleCd(vender.getAccountsCd());
			} catch (NoDataException e) {
				addError(request, "errors.payment.vender");
				return false;
			}
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		try {
			// 取引先マスタから検索
			// VenderDetail venderDetail =
			// venderDetailLogic.getEntity(detailForm.getCustomerCd(),
			// VENDER_DIVISION_SI);
			// if (venderDetail != null) {
			// //検索結果が存在した場合は、請求先名称を設定する。
			// detailForm.setCustomerName(venderDetail.getVenderName());
			// res = true;
			// }
			// 取引先マスタ,PAYMENT_HEADERから検索
			AltPaymentVender vender = paymentCsmDetailLogic.getVender(
				VENDER_DIVISION_SI, detailForm.getCustomerCd());
			if (vender != null) {
				// 検索結果が存在した場合は、請求先名称,繰越残を設定する。
				detailForm.setCustomerName(vender.getVenderShortedName());
				detailForm.setPayableAmount(checker.format(SIKINGAKU, vender
						.getPayableAmoun()));
				res = true;
			}
		} catch (NoDataException e) {
			// 取引先コードが存在しない場合は、マスタチェックエラー
			detailForm.setCustomerName("");
			addError(request, "errors.nodata.master", getMessageResource(
				request, "item.payment.supplierCd"));
			res = false;
		}
		return res;
	}

	/**
	 * 明細行のマスタ存在チェックを行う。
	 * @param detailList 明細行データ配列
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean checkMasterDetail(final List<PaymentCsmBean> detailList,
			final HttpServletRequest request) {
		boolean res = true;
		for (PaymentCsmBean bean : detailList) {
			// 支払分類存在チェック
			// 分類マスタを分類コードで検索
			ClassificationDetail classDetail = paymentCsmDetailLogic
					.getClassificationEntity(CLASSIFICATION_DATA_TYPE, bean
							.getCategoryDivision());
			// 分類マスタから各値を設定
			bean.setDataTotalDivision(classDetail.getDataTotalDivision()); // ﾃﾞｰﾀ集計区分
			bean.setDepositTargetDivision(classDetail.getArDivision()); // 売掛対象区分
			bean.setClaimTargetDivision(classDetail.getClaimDivision()); // 請求対象区分
			bean.setPayableTargetDivision(classDetail.getCreditDivision()); // 買掛対象区分
			bean.setPaymentTargetDivision(classDetail.getPaymentDivision()); // 支払対象区分
			bean.setBankDivision(classDetail.getBankDivision().intValue()); // 銀行必須区分
			bean.setNoteDiv(classDetail.getNoteDivision().intValue()); // 手形必須区分
			// 読取時表示分類コード＋分類名称
			bean.setViewCategoryDivision(classDetail.getCategoryDivision()
					+ ":" + classDetail.getCategoryName());
			// データ集計区分
			bean.setClassificationDataTotalDivision(classDetail
					.getDataTotalDivision().intValue());
		}
		return res;
	}

	/**
	 * 自社マスタ検索して、支払伝票発行区分を設定する。
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックＮＧ]
	 */
	private boolean setCompanyMaster(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;

		HttpSession session = request.getSession(false);
		PasswordCheck passBean = (PasswordCheck) session
				.getAttribute(Constants.COMPANY_INFO_KEY);

		try {
			Company company = companyDetailLogic.getEntity(passBean
					.getCompanyCd());
			BigDecimal div = company.getPaymentIssuedDivision();
			int issuDiv = 0;
			if (div != null) {
				issuDiv = div.intValue();
				if (issuDiv == 1) {
					issuDiv = ISSUED_DIVISION_NO; // 1->9
				}
			}
			// 伝票発行区分
			detailForm.setCreditIssuedDivision(new BigDecimal(issuDiv));
		} catch (NoDataException e) {
			addError(request, "errors.nodata.master", getMessageResource(
				request, "item.payment.company"));
			res = false;
		}
		return res;
	}

	/**
	 * 更新用データ取得
	 * @param detailForm 詳細画面Form
	 * @param detailList 明細行データ配列
	 * @return 更新用データ配列
	 */
	private List<AltPayment> getUpdateBeans(
			final PaymentCsmDetailForm detailForm,
			final List<PaymentCsmBean> detailList) {
		List<AltPayment> list = new ArrayList<AltPayment>();

		// ヘッダ部データを明細部に設定
		// 部署コード
		String organizationCode = detailForm.getOrganizationCd();
		// 借方部門コード
		String debitSectionCd = detailForm.getDebitSectionCd();
		// 借方科目コード
		String debitTitleCd = detailForm.getDebitTitleCd();
		// 貸方部門コード
		String creditSectionCd = detailForm.getCreditSectionCd();
		// 支払日付
		String creditDateString = detailForm.getStrPaymentDate();
		Timestamp creditDate = getDate(creditDateString);
		// 請求先コード
		String customerCode = detailForm.getCustomerCd();
		// 支払伝票発行区分
		BigDecimal issuedDivision = detailForm.getCreditIssuedDivision();
		// 承認ステータス
		String approvalStatus = detailForm.getApprovalStatus();
		// 仕入割引額
		BigDecimal stockReduction = AecNumberUtils.convertBigDecimal(detailForm
				.getStrPurchaseDiscountAmount());
		// 振込手数料
		BigDecimal commission = AecNumberUtils.convertBigDecimal(detailForm
				.getStrCommission());
		// 銀行マスタコード
		String bankCd = detailForm.getBankCd();
		// 預金種別
		String accountDivision = detailForm.getAccountDivision();
		// 口座番号
		String accountNo = detailForm.getAccountNo();

		for (PaymentCsmBean bean : detailList) {
			bean.setOrganizationCd(organizationCode); // 部署コード
			bean.setDebitSectionCd(debitSectionCd); // 借方部門コード
			bean.setDebitTitleCd(debitTitleCd); // 借方科目コード
			bean.setCreditSectionCd(creditSectionCd); // 貸方部門コード
			bean.setPaymentDate(creditDate); // 支払日付
			bean.setSupplierCd(customerCode); // 支払先コード
			bean.setIssuedDivision(issuedDivision); // 支払伝票発行区分
			bean.setApprovalStatus(approvalStatus); /* 承認ステータス */
			bean.setStockReduction(stockReduction); /* 仕入割引額 */
			bean.setTransferFree(commission); /* 振込手数料 */

			/* 支払区分に銀行区分がある場合 */
			if (bean.getBankDivision() == 1) {
				bean.setBankCd(bankCd); /* 銀行マスタコード */
				bean.setAccountDivision(accountDivision); /* 預金種別 */
				bean.setAccountNo(accountNo); /* 口座番号 */
			} else {
				bean.setBankCd(null); /* 銀行マスタコード */
				bean.setAccountDivision(null); /* 預金種別 */
				bean.setAccountNo(null); /* 口座番号 */
			}

			// 手形番号が設定されている場合
			// if (StringUtils.isNotEmpty(bean.getNoteNo())) {
			if (bean.getNoteDiv() == 1) {
				bean.setNoteDivision(new BigDecimal(bean
						.getNoteDivisionString()));
				bean.setNoteDate(AecDateUtils.getTimestampYmdFormat(bean
						.getNoteDateString(), false));
				bean.setDrawalDate(AecDateUtils.getTimestampYmdFormat(bean
						.getDrawalDateString(), false));
			} else {
				bean.setNoteNo(null);
				bean.setNoteDivision(null);
				bean.setNoteDate(null);
				bean.setDrawalDate(null);
			}

			// 更新対象データを作成
			AltPayment updateBean = new AltPayment();
			// 内容を更新用Beanにコピー
			try {
				IgnoreCaseBeanUtils.copyProperties(updateBean, bean);
			} catch (IllegalAccessException iae) {
				if (getLog().isDebugEnabled()) {
					getLog().debug("更新対象データに値を移行時エラー発生", iae);
				}
			} catch (InvocationTargetException ite) {
				if (getLog().isDebugEnabled()) {
					getLog().debug("更新対象データに値を移行時エラー発生", ite);
				}
			}
			list.add(updateBean);
		}

		return list;
	}

	/**
	 * 日付文字列(YYYY/MM/DD)からTimestampオブジェクトを生成
	 * @param dateString 日付文字列(YYYY/MM/DD)
	 * @return Timestampオブジェクト
	 */
	private Timestamp getDate(final String dateString) {
		return AecDateUtils.getTimestampYmdFormat(dateString, false);
	}

	/**
	 * 挿入時初期データ設定
	 * @param list 更新用明細データ
	 */
	private void setInsertInitValue(final List<AltPayment> list,
			final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// ログインユーザ情報を取得
		LoginInfo loginInfo = (LoginInfo) session
				.getAttribute(Constants.LOGIN_KEY);
		String userId = loginInfo.getTantoCd();
		BigDecimal zero = new BigDecimal(0);

		// 初期値を設定
		for (AltPayment bean : list) {
			bean.setDataType(CREDIT_DATA_TYPE); // ﾃﾞｰﾀ種別 4:支払
			bean.setPaymentAmount(replace(bean.getPaymentAmount(), ",", "")); // 支払金額
			bean.setDebitSectionCd(bean.getDebitSectionCd()); // 借方部門コード
			bean.setDebitTitleCd(bean.getDebitTitleCd()); // 借方科目コード
			bean.setCreditSectionCd(bean.getCreditSectionCd()); // 貸方部門コード
			bean.setDepositUpdateDivision(zero); // 売掛更新フラグ
			bean.setClaimUpdateDivision(zero); // 請求更新フラグ
			bean.setPayableUpdateDivision(zero); // 買掛更新フラグ
			bean.setPaymentUpdateDivision(zero); // 支払更新フラ
			bean.setEraserCompleteDivision(zero); // 消込完了フラグ
			bean.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータ
			bean.setInputDate(new Date()); // 登録日時
			bean.setInputorCd(userId); // 登録者
			bean.setUpdateDate(new Date()); // 更新日時
			bean.setUpdatorCd(userId); // 更新者
		}
	}

	/**
	 * 文字列を置換する。
	 * @param s 対象文字列
	 * @param regex 置換パターン(正規表現)
	 * @param replacement 置換文字列
	 * @return 文字列
	 */
	private String replace(final String s, final String regex,
			final String replacement) {
		String res = null;
		if (StringUtils.isNotEmpty(s)) {
			res = s.replaceAll(regex, replacement);
		}
		return res;
	}

	/**
	 * 既存データ検索
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @throws NoDataException データが存在しない場合
	 * @throws IllegalAccessException プロパティへのアクセス権が無い場合
	 * @throws InvocationTargetException プロパティが存在しない場合
	 */
	private void getExistData(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) throws NoDataException,
			IllegalAccessException, InvocationTargetException {
		// 支払トランザクションテーブルから検索
		List<AltPayment> list = paymentCsmDetailLogic.findBySlipNo(detailForm
				.getSlipNo());
		List<PaymentCsmBean> detailList = new ArrayList<PaymentCsmBean>();

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (AltPayment payment : list) {
			PaymentCsmBean bean = new PaymentCsmBean();
			// 表示用データに移送
			IgnoreCaseBeanUtils.copyProperties(bean, payment);
			// 支払金額カンマ編集
			// bean.setPaymentAmount(checker.format(SIKINGAKU, AecNumberUtils
			// .convertBigDecimal(bean.getPaymentAmount())));
			bean.setPaymentAmount(checker.format(KINGAKU, AecNumberUtils
					.convertBigDecimal(bean.getPaymentAmount())));

			// 補助科目一覧
			bean.setSubAccounts(PaymentCsmUtil.getAccountsSubComboBox(bean
					.getCreditTitleCd(), accountsListLogic));

			if (bean.getNoteDivision() != null) {
				bean.setNoteDivisionString(bean.getNoteDivision().toString());
			}
			if (bean.getNoteDate() != null) {
				bean.setNoteDateString(AecDateUtils.dateFormat(bean
						.getNoteDate(), "yyyy/MM/dd"));
			}
			if (bean.getDrawalDate() != null) {
				bean.setDrawalDateString(AecDateUtils.dateFormat(bean
						.getDrawalDate(), "yyyy/MM/dd"));
			}

			// 更新可能チェック
			if (isReadonly(bean)) {
				// 更新不可なので照会にする。
				detailForm.setInsertFlg(INSERT_FLAG_VIEW); // 更新フラグ=2:照会
			}

			detailList.add(bean);
		}
		// 分類・銀行検索
		checkMasterDetail(detailList, request);
		detailForm.setDetailList(detailList);

		AltPayment payment = list.get(0);
		detailForm.setOrganizationCd(payment.getOrganizationCd()); // 部署コード
		detailForm.setStrPaymentDate(getDateString(payment.getPaymentDate())); // 支払日時
		detailForm.setCustomerCd(payment.getSupplierCd()); // 仕入先コード
		detailForm.setApprovalStatus(payment.getApprovalStatus()); /* 承認ステータス */

		// 部署マスタから部署名称を検索
		getSelectionName(detailForm);

		// 取引先マスタから検索
		getVenderName(detailForm, request);

		// 取引先データから、振込手数料計算フラグを設定する。
		setVenderData(detailForm);

		/* 支払予定セット */
		setScheduled(detailForm, request);

		BigDecimal paymentAmount = AecNumberUtils.convertBigDecimal(detailForm
				.getStrPaymentAmount()); /* 支払合計 */
		BigDecimal stockReduction = BigDecimal.ZERO; /* 仕入割引 */
		BigDecimal commission = AecNumberUtils.convertBigDecimal(detailForm
				.getStrCommission()); /* 手数料 */

		for (AltPayment pay : list) {
			/* 仕入割引 */
			if (paymentAmount.equals(BigDecimal.ZERO)) {
				/* 支払合計がゼロの場合は仕入割引をゼロにする */
				detailForm.setStrPurchaseDiscountAmount("0");
			} else {
				detailForm.setStrPurchaseDiscountAmount(checker.format(
					SIKINGAKU, pay.getPurchaseDiscountAmount()));
				stockReduction = pay.getPurchaseDiscountAmount();
			}
		}

		/* 今回支払予定額 = 支払合計 - 仕入割引 - 手数料 */
		detailForm.setStrPaymentScheduledAmount(checker.format(KINGAKU,
			paymentAmount.subtract(stockReduction).subtract(commission)));

		// 支払分類（分類マスタから取得）
		detailForm.setCategoryList(getCategoryComboBox());
		// 科目（科目マスタから取得）
		detailForm.setAccountList(PaymentCsmUtil
				.getClassComboBox(accountsListLogic));

		// readonly時のデータ取得
		if (INSERT_FLAG_VIEW == detailForm.getInsertFlg()) {
			// 読取専用時
			for (PaymentCsmBean bean : detailList) {
				initReadOnly(bean, detailForm);
			}
		}
	}

	/**
	 * 取引先データから、振込手数料計算フラグを設定する。
	 * @param detailForm 明細画面Form
	 */
	private void setVenderData(final PaymentCsmDetailForm detailForm) {
		List<PaymentCsmBean> detailList = detailForm.getDetailList();

		if (INSERT_FLAG_VIEW != detailForm.getInsertFlg()) {
			// 入力可能時
			// 銀行コード取得
			String bankBranchCode = detailForm.getBankCd();
			if (StringUtils.isNotEmpty(bankBranchCode)) {
				if (bankBranchCode.length() != 7) {
					// 銀行コード＋支店コードは必ず７ケタ
					detailForm.setBankName("");
				} else {
					try {
						Bank bankDetail = getBankDetail(bankBranchCode);
						// 存在する場合、銀行名を更新
						detailForm.setBankName(bankDetail.getBankName() + " "
								+ bankDetail.getBranchName());
					} catch (NoDataException e) {
						// 銀行コードが存在しない場合は、マスタチェックエラー
						detailForm.setBankName("");
					}
				}
			}
			for (PaymentCsmBean bean : detailList) {
				// 振込区分=1(自社)の場合、振込手数料計算フラグを立てる
				if (bean.getBankDivision() == FLG_ON) {
					if (detailForm.getPayingCheckDivision() == COMPANY_PAYING_CHECK_DIVISION_OWN) {
						bean.setFeeFlag(FLG_ON); // 振込手数料計算フラグをON
					}
					// 入力可能時、銀行関連は取引先マスタの値を使用する。
					bean.setAccountDivision(detailForm.getAccountDivision());
					bean.setAccountNo(detailForm.getAccountNo());
					bean.setBankCd(detailForm.getBankCd());
					bean.setBankName(detailForm.getBankName());
				}
			}
		}

	}

	/**
	 * 数値文字列をカンマ編集する
	 * @param amount 数値文字列
	 * @return カンマ編集文字列
	 */
	// private String getCurrency(final String amount) {
	// String res = null;
	// if (StringUtils.isNotEmpty(amount)) {
	// BigDecimal dec = new BigDecimal(amount);
	// NumberFormat nf = NumberFormat.getNumberInstance();
	// res = nf.format(dec);
	// }
	// return res;
	// }
	/**
	 * 参照のみかチェックする。
	 * @param bean 明細Bean
	 * @return [true:更新不可][false:更新可能]
	 */
	private boolean isReadonly(final PaymentCsmBean bean) {
		boolean res = false;
		if (isDone(bean.getDepositUpdateDivision())
				|| isDone(bean.getClaimUpdateDivision())
				|| isDone(bean.getPayableUpdateDivision())
				|| isDone(bean.getEraserCompleteDivision())
				|| isDone(bean.getPaymentUpdateDivision())) {
			// 売り掛け更新、請求更新、買掛更新、支払い更新、
			// 消込完了フラグの何れかが立っていた場合は、更新付加->照会モード
			res = true;
		}
		return res;
	}

	/**
	 * 処理済みかチェックする。
	 * @param dec フラグ
	 * @return [true:処理済][false:未処理]
	 */
	private boolean isDone(final BigDecimal dec) {
		boolean res = false;
		if (dec != null) {
			if (dec.intValue() != 0) {
				// 0:未処理以外は処理済とする
				res = true;
			}
		}
		return res;
	}

	/**
	 * Dateオブジェクトを日付文字列(YYYY/MM/DD)に変換する
	 * @param d Dateオブジェクト
	 * @return 日付文字列(YYYY/MM/DD)
	 */
	private String getDateString(final Date d) {
		String res = null;
		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			res = sdf.format(d);
		}
		return res;
	}

	/**
	 * 読取専用時のコンボボックスの内容を設定する。
	 * @param bean 明細データ
	 * @param detailForm 詳細画面Form
	 */
	private void initReadOnly(final PaymentCsmBean bean,
			final PaymentCsmDetailForm detailForm) {
		// //預金種別コンボ内容
		// bean.setViewAccountDivision(getViewAccountDivision(bean.getAccountDivision()));
		// 科目
		bean.setViewCreditTitleCd(detailForm.getDebitLabel(bean
				.getCreditTitleCd()));
		// 補助科目getSubAccountLabel
		bean.setViewCreditSubTitleCd(bean.getSubAccountLabel(bean
				.getCreditSubTitleCd()));
	}

	/**
	 * 更新時初期データ設定
	 * @param list 更新用明細データ
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 */
	private void setUpdateInitValue(final List<AltPayment> list,
			final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// ログインユーザ情報を取得
		LoginInfo loginInfo = (LoginInfo) session
				.getAttribute(Constants.LOGIN_KEY);
		String userId = loginInfo.getTantoCd();
		// 支払番号
		String slipNo = detailForm.getSlipNo();
		// 0フラグ
		BigDecimal zero = new BigDecimal(0);
		// 初期値を設定
		for (AltPayment payment : list) {
			payment.setUpdateDate(new Date()); // 更新日時
			payment.setUpdatorCd(userId); // 更新者
			payment.setPaymentAmount(replace(payment.getPaymentAmount(), ",",
				"")); // 支払金額
			// 日付をTimestampからDateへ変換
			payment.setPaymentDate(convertTimestamp(payment.getPaymentDate()));
			payment.setDebitSectionCd(detailForm.getDebitSectionCd()); /* 借方部門コード */
			payment.setDebitTitleCd(detailForm.getDebitTitleCd()); /* 借方科目コード */
			payment.setCreditSectionCd(detailForm.getCreditSectionCd()); /* 貸方部門コード */
			payment.setIssueDate(convertTimestamp(payment.getIssueDate()));
			payment.setDeliveryUpdateDate(convertTimestamp(payment
					.getDeliveryUpdateDate()));
			payment.setInvoiceUpdateDate(convertTimestamp(payment
					.getInvoiceUpdateDate()));
			payment.setPayableUpdateDate(convertTimestamp(payment
					.getPayableUpdateDate()));
			payment.setPaymentUpdateDate(convertTimestamp(payment
					.getPaymentUpdateDate()));
			payment
					.setApprovalDate(convertTimestamp(payment.getApprovalDate()));
			payment.setInputDate(convertTimestamp(payment.getInputDate()));

			if (StringUtils.isEmpty(payment.getSlipNo())) {
				// 新規挿入行
				payment.setSlipNo(slipNo); // 支払番号
				payment.setDataType(CREDIT_DATA_TYPE); // ﾃﾞｰﾀ種別 4:支払
				payment.setDepositUpdateDivision(zero); // 売掛更新フラグ
				payment.setClaimUpdateDivision(zero); // 請求更新フラグ
				payment.setPayableUpdateDivision(zero); // 買掛更新フラグ
				payment.setPaymentUpdateDivision(zero); // 支払更新フラ
				payment.setEraserCompleteDivision(zero); // 消込完了フラグ
				payment.setApprovalStatus(APPROVAL_STATUS_INPUT); // 承認ステータ
				payment.setInputDate(new Date()); // 登録日時
				payment.setInputorCd(userId); // 登録者
			}
		}
	}

	/**
	 * Timestamp型からDate型へ変換する
	 * @param t Timestamp型
	 * @return Date型
	 */
	private Date convertTimestamp(final Date t) {
		Date res = null;
		if (t != null) {
			if (t instanceof Timestamp) {
				long time = t.getTime();
				res = new Date(time);
			} else {
				res = t;
			}
		}
		return res;
	}

	/**
	 * 参照用データ作成処理
	 * @param request HttpServletRequest
	 * @param detailForm 詳細画面Form
	 * @param forward 戻り先
	 * @return 遷移先
	 * @throws IllegalAccessException アクセス権が無い場合発生
	 * @throws InvocationTargetException 対象メソッドが無い場合発生
	 */
	private String showReferenceAction(final HttpServletRequest request,
			final PaymentCsmDetailForm detailForm, final String forward)
			throws IllegalAccessException, InvocationTargetException {
		String res = "success";
		String slipNo = detailForm.getSlipNo();
		// 初期化する
		detailForm.clear();
		detailForm.setSlipNo(slipNo);

		detailForm.setInsertFlg(INSERT_FLAG_VIEW); // 更新フラグ=2:照会

		try {
			// 初期表示データ取得
			getExistData(detailForm, request);
			// 戻り先を売掛台帳に設定
			detailForm.setBackForward(forward);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata.deleted");
			res = forward;
		}
		return res;
	}

	/**
	 * 分類.データ集計区分＝相殺の場合、支払先に請求先が存在するかチェックを行う
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックNG]
	 */
	private boolean checkSetoffVender(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		boolean res = true;
		List<PaymentCsmBean> list = detailForm.getDetailList();
		boolean isSetoff = false;
		for (PaymentCsmBean bean : list) {
			if (bean.getClassificationDataTotalDivision() == ClassificationDetailAction.DATA_TOTAL_DIVISION_OFFSET) {
				// 分類.データ集計区分＝相殺の場合、支払先に請求先が存在するかチェックを行う。
				isSetoff = true;
				break;
			}
		}
		if (isSetoff) {
			// 取引先マスタから仕入先の請求先が存在するかチェックを行う。
			try {
				Vender vender = paymentCsmDetailLogic.getVenderEntity(
					detailForm.getCustomerCd(), VENDER_DIVISION_TS);
				if (StringUtils.isEmpty(vender.getPaymentInvoiceCd())
						|| vender.getPaymentInvoiceCd().equals(
							detailForm.getCustomerCd())) {
					res = true;
				} else {
					// 支払先ではないので、エラー
					throw new NoDataException();
				}
			} catch (NoDataException e) {
				// 仕入先の取引先が存在しないので、
				// 同一コードの支払先が存在しないので総裁の指定は出来ません」エラー
				addError(request, "errors.payment.setoff");
				res = false;
			}
		}
		return res;
	}

	/**
	 * 仕入割引データ存在チェック
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックNG]
	 */
	private boolean checkPaymentHeader(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		Timestamp paymentDate = AecDateUtils.getTimestampYmdFormat(detailForm
				.getStrPaymentDate());

		/* 仕入割引額の登録 */
		List<PaymentHeaderDetail> listHeader = paymentHeaderDetailDao
				.getEntity(detailForm.getOrganizationCd(), detailForm
						.getCustomerCd(), paymentDate);

		if (listHeader == null) {
			return false;
		}

		if (listHeader.size() == 0) {
			return false;
		}

		return true;
	}

	/**
	 * 支払データ存在チェック
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:チェックＯＫ][false:チェックNG]
	 */
	private boolean checkPayment(final PaymentCsmDetailForm detailForm,
			final HttpServletRequest request) {
		Timestamp paymentDate = AecDateUtils.getTimestampYmdFormat(detailForm
				.getStrPaymentDate());

		/* 支払登録チェック */
		List<AltPayment> list = paymentCsmDetailLogic.checkEntity(paymentDate,
			detailForm.getCustomerCd(), detailForm.getOrganizationCd());

		if (list == null) {
			return false;
		}

		if (list.size() == 0) {
			return false;
		}

		return true;
	}

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

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");

		/* ステータス更新 */
		paymentCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// 支払番号退避
		String slipNo = frm.getSlipNo();

		// 初期化する
		frm.clear();
		frm.setSlipNo(slipNo);

		frm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(frm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			return mapping.findForward("back");
		}

		frm.setApprovalStatus("2");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
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

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");

		/* ステータス更新 */
		paymentCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// 支払番号退避
		String slipNo = frm.getSlipNo();

		// 初期化する
		frm.clear();
		frm.setSlipNo(slipNo);

		frm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(frm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			return mapping.findForward("back");
		}

		frm.setApprovalStatus("3");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
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

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		paymentCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// 支払番号退避
		String slipNo = frm.getSlipNo();

		// 初期化する
		frm.clear();
		frm.setSlipNo(slipNo);

		frm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(frm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			return mapping.findForward("back");
		}

		frm.setApprovalStatus("1");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
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

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		paymentCsmDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		// 支払番号退避
		String slipNo = frm.getSlipNo();

		// 初期化する
		frm.clear();
		frm.setSlipNo(slipNo);

		frm.setInsertFlg(INSERT_FLAG_UPDATE); // 更新フラグ=1:更新入力

		try {
			getExistData(frm, request);
		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata");
			return mapping.findForward("back");
		}

		frm.setApprovalStatus("1");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 休日チェック
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward checkHoliday(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("checkHoliday.");
		}

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 休日チェック */
		if (checkHoliday(frm, request)) {
			frm.setHolidayMsg("入力された支払日付は休日です。");
		} else {
			frm.setHolidayMsg(null);
		}

		return mapping.findForward("success");
	}

	/**
	 * 休日チェック
	 * @param detailForm 詳細画面Form
	 * @param request HttpServletRequest
	 * @return [true:休日][false:平日]
	 */
	private Boolean checkHoliday(final PaymentCsmDetailForm frm,
			final HttpServletRequest request) {
		/* 日付文字列をyyyy/MM/ddに変換 */
		String s = getStringDateString(frm.getStrPaymentDate());

		Timestamp paymentDate = AecDateUtils.getTimestampYmdFormat(s);

		/* 支払日付未入力 */
		if (paymentDate == null) {
			return false;
		}

		/* 取引先未入力 */
		if (StringUtils.isEmpty(frm.getCustomerCd())) {
			return false;
		}

		/* 取引先カレンダー検索 */
		CheckHolidayResult result = checkHolidayLogic.getVenderHoliday(
			VENDER_DIVISION_SI, frm.getCustomerCd(), paymentDate);

		/* 取引先未登録 */
		if (result.getCode().equals(VENDER_NODATA)) {
			addError(request, "errors.claim.vender");
			return false;
		}

		/* 取引先カレンダー未登録 */
		if (result.getCode().equals(VENDER_NOCAL)) {
			addError(request, "errors.nodata.claim.vender.cal");
			return false;
		}

		/* カレンダー未登録 */
		if (result.getCode().equals(CAL_NODATA)) {
			addError(request, "errors.nodata.claim.cal");
			return false;
		}

		/* 平日 */
		if (result.getCalHoliday().equals(EVERYDAY)) {
			return false;
		}

		return true;
	}

	/**
	 * Stringオブジェクトを日付文字列に変換する
	 * @param d 日付文字
	 * @return 日付文字列(yyyy/MM/dd)
	 */
	private String getStringDateString(final String d) {
		String res = null;

		if (!StringUtils.isEmpty(d)) {
			res = d.replaceAll("/", "");

			if (res.length() == 8) {
				res = res.substring(0, 4) + "/" + res.substring(4, 6) + "/"
						+ res.substring(6, 8);
			} else if (res.length() == 6) {
				res = res.substring(0, 2) + "/" + res.substring(2, 4) + "/"
						+ res.substring(4, 6);
			}
		}

		return res;
	}

	/**
	 * 自動仕訳セット
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward setJournal(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("setJournal.");
		}

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;
		NumberFormat nf = NumberFormat.getNumberInstance();
		int row = -1; /* 処理行位置 */

		/* リストクリア */
		frm.setDetailList(new ArrayList<PaymentCsmBean>());

		BigDecimal paymentScheduledAmount = AecNumberUtils
				.convertBigDecimal(frm.getStrPaymentScheduledAmount()); /* 今回支払予定額 */
		String categoryDivision = null; /* 分類コード */

		/* /なし文字列日付 ---> 日付 */
		Timestamp paymentDate = getDateformat(frm.getStrPaymentDate());

		/* 日付 ---> 文字列日付 */
		String strPaymentDate = AecDateUtils.dateFormat(paymentDate,
			"yyyy/MM/dd");

		/* 今回支払予定額チェック */
		if (!paymentScheduledAmount.equals(BigDecimal.ZERO)) {
			/* 今回支払予定額行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				for (int j = 0; j < frm.getHeaderList().size(); j++) {
					if (frm.getHeaderList().get(j).getStrCreditScheduledDate()
							.equals(strPaymentDate)) {
						/* 仕入割引の場合は振込とみなす */
						if (frm.getHeaderList().get(j).getCreditDivision()
								.equals(PURCHASE_DISCOUNT)) {
							/* 仕入割引の場合 */
							categoryDivision = TRANSFER;
						} else {
							categoryDivision = frm.getHeaderList().get(j)
									.getCreditDivision();
						}

						if (frm.getDetailList().get(i).getCategoryDivision()
								.equals(categoryDivision)) {
							row = i;
							break;
						}
					}
				}

				if (row != -1) {
					break;
				}
			}

			/* 今回支払予定額行が見つからない場合は空白行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				if (StringUtils.isEmpty(frm.getDetailList().get(i)
						.getCategoryDivision())) {
					row = i;
					break;
				}
			}

			/* 空白行が見つからない場合は行追加 */
			if (row == -1) {
				/* 行追加 */
				addRow(mapping, form, request, response);

				row = frm.getDetailList().size() - 1;
				frm.getDetailList().get(row).setCategoryDivision(null);

				for (int j = 0; j < frm.getHeaderList().size(); j++) {
					if (frm.getHeaderList().get(j).getStrCreditScheduledDate()
							.equals(strPaymentDate)) {
						/* 仕入割引の場合は振込とみなす */
						if (frm.getHeaderList().get(j).getCreditDivision()
								.equals(PURCHASE_DISCOUNT)) {
							/* 仕入割引の場合 */
							categoryDivision = TRANSFER;
						} else {
							categoryDivision = frm.getHeaderList().get(j)
									.getCreditDivision();
						}

						/* 支払区分 */
						frm.getDetailList().get(row).setCategoryDivision(
							categoryDivision);
						break;
					}
				}
			}

			/* 支払区分 */
			// if (0 < frm.getHeaderList().size()) {
			// frm.getDetailList().get(row).setCategoryDivision(
			// frm.getHeaderList().get(0).getCategoryDivision());
			// } else {
			// frm.getDetailList().get(row).setCategoryDivision(null);
			// }
			frm.getDetailList().get(row).setPaymentAmount(
				nf.format(paymentScheduledAmount)); /* 支払金額 */
			frm.setIndex(row); /* 勘定科目処理行 */

			/* 支払方法変更イベント処理 */
			changeCategory(mapping, form, request, response);
		}

		BigDecimal purchaseDiscountAmount = AecNumberUtils
				.convertBigDecimal(frm.getStrPurchaseDiscountAmount()); /* 仕入割引 */
		row = -1;

		/* 仕入割引チェック */
		if (!purchaseDiscountAmount.equals(BigDecimal.ZERO)) {
			/* 仕入割引行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				if (!StringUtils.isEmpty(frm.getDetailList().get(i)
						.getCategoryDivision())) {
					for (int j = 0; j < frm.getHeaderList().size(); j++) {
						if (frm.getHeaderList().get(j)
								.getStrCreditScheduledDate().equals(
									strPaymentDate)) {
							if (frm.getDetailList().get(i)
									.getCategoryDivision().equals(
										PURCHASE_DISCOUNT)) {
								row = i;
								break;
							}
						}
					}
				}

				if (row != -1) {
					break;
				}
			}

			/* 仕入割引行が見つからない場合は空白行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				if (StringUtils.isEmpty(frm.getDetailList().get(i)
						.getCategoryDivision())) {
					row = i;
					break;
				}
			}

			/* 空白行が見つからない場合は行追加 */
			if (row == -1) {
				/* 行追加 */
				addRow(mapping, form, request, response);

				row = frm.getDetailList().size() - 1;
			}

			frm.getDetailList().get(row).setCategoryDivision(PURCHASE_DISCOUNT); /* 支払区分(仕入割引) */
			frm.getDetailList().get(row).setPaymentAmount(
				nf.format(purchaseDiscountAmount)); /* 支払金額 */
			frm.setIndex(row); /* 勘定科目処理行 */

			/* 支払方法変更イベント処理 */
			changeCategory(mapping, form, request, response);
		}

		BigDecimal commission = AecNumberUtils.convertBigDecimal(frm
				.getStrCommission()); /* 手数料 */
		row = -1;

		/* 手数料チェック */
		if (!commission.equals(BigDecimal.ZERO)) {
			/* 手数料行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				if (!StringUtils.isEmpty(frm.getDetailList().get(i)
						.getCategoryDivision())) {
					for (int j = 0; j < frm.getHeaderList().size(); j++) {
						if (frm.getHeaderList().get(j)
								.getStrCreditScheduledDate().equals(
									strPaymentDate)) {
							if (frm.getDetailList().get(i)
									.getCategoryDivision().equals(COMMISSION)) {
								row = i;
								break;
							}
						}
					}
				}

				if (row != -1) {
					break;
				}
			}

			/* 手数料行が見つからない場合は空白行を検索 */
			for (int i = 0; i < frm.getDetailList().size(); i++) {
				if (StringUtils.isEmpty(frm.getDetailList().get(i)
						.getCategoryDivision())) {
					row = i;
					break;
				}
			}

			/* 空白行が見つからない場合は行追加 */
			if (row == -1) {
				/* 行追加 */
				addRow(mapping, form, request, response);

				row = frm.getDetailList().size() - 1;
			}

			frm.getDetailList().get(row).setCategoryDivision(COMMISSION); /* 支払区分(手数料) */
			frm.getDetailList().get(row)
					.setPaymentAmount(nf.format(commission)); /* 支払金額 */
			frm.setIndex(row); /* 勘定科目処理行 */

			/* 支払方法変更イベント処理 */
			changeCategory(mapping, form, request, response);
		}

		return mapping.findForward("success");
	}

	/**
	 * 支払予定セット
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward setScheduled(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("setScheduled.");
		}

		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		/* 支払日付が入力されている場合 */
		if (StringUtils.isNotEmpty(frm.getStrPaymentDate())) {
			/* /なし文字列日付 ---> 日付 */
			Timestamp paymentDate = getDateformat(frm.getStrPaymentDate());

			/* 支払日付が正しくない場合 */
			if (paymentDate == null) {
				/* クリア */
				frm.setHeaderList(new ArrayList<AltPayment>());
				frm.setDetailList(new ArrayList<PaymentCsmBean>());
				frm.setStrBalanceForward("0");
				frm.setStrPaidAmount("0");
				frm.setStrOffsetAmount("0");
				frm.setStrPaymentAmount("0");
				frm.setStrPurchaseDiscountAmount("0");
				frm.setStrCommission("0");
				frm.setStrPaymentScheduledAmount("0");

				saveErrorWithArgs(request, "errors.date",
					"item.payment.srhPaymentDate");
				return mapping.findForward("success");
			}
		}

		/* 支払日付と請求先が入力されていないと検索しない */
		if (StringUtils.isNotEmpty(frm.getCustomerCd())
				&& StringUtils.isNotEmpty(frm.getStrPaymentDate())) {
			/* 支払予定セット */
			setScheduled(form, request);

			/* 自動仕訳セット */
			setJournal(mapping, form, request, response);
		}

		/* 休日チェック */
		checkHoliday(mapping, form, request, response);

		return mapping.findForward("success");
	}

	/**
	 * 支払予定セット
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 */
	public void setScheduled(final ActionForm form,
			final HttpServletRequest request) {
		PaymentCsmDetailForm frm = (PaymentCsmDetailForm) form;

		frm.setHeaderList(new ArrayList<AltPayment>());

		String strPaidAmount = "0"; /* 支払済金額 */
		BigDecimal paidAmount = BigDecimal.ZERO; /* 支払済金額 */

		/* /なし文字列日付 ---> 日付 */
		Timestamp paymentDate = getDateformat(frm.getStrPaymentDate());

		/* 日付 ---> 文字列日付 */
		String strPaymentDate = AecDateUtils.dateFormat(paymentDate,
			"yyyy/MM/dd");

		/* 支払済金額取得 */
		AltPayment bean = paymentCsmDetailLogic.getPaidEntity(frm
				.getCustomerCd(), strPaymentDate);

		if (bean != null) {
			strPaidAmount = bean.getPaymentAmount();
		}

		/* 文字数値--->数値 */
		paidAmount = AecNumberUtils.convertBigDecimal(strPaidAmount);

		BigDecimal balanceForward = BigDecimal.ZERO; // 支払残高
		BigDecimal totalPaymentAmount = BigDecimal.ZERO;

		/* 支払額合計取得 */
		AltPayment beanTotal = paymentCsmDetailLogic.getTotalPaymentAmount(frm
				.getCustomerCd());

		if (beanTotal != null) {
			totalPaymentAmount = beanTotal.getTotalPaymentAmount();
		}

		/* 支払予定取得 */
		List<AltPayment> list = paymentCsmDetailLogic.getPaymentHeaderList(frm
				.getCustomerCd(), totalPaymentAmount);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		BigDecimal commission = BigDecimal.ZERO;
		BigDecimal stockReduction = BigDecimal.ZERO;
		BigDecimal offsetAmount = BigDecimal.ZERO;
		BigDecimal paymentAmount = BigDecimal.ZERO;
		Timestamp firstPaymentDate = null; // 支払予定日
		boolean flg = false;

		for (AltPayment pay : list) {
			if (0 < pay.getTotalPayableAmount().compareTo(totalPaymentAmount)) {
				firstPaymentDate = pay.getCreditScheduledDate(); // 最初に見つかった支払予定日

				/* 支払日付と支払予定日が同じ最初のレコードを検索 */
				for (AltPayment pay2 : list) {
					if (0 < pay2.getTotalPayableAmount().compareTo(
						totalPaymentAmount)) {
						/* 支払日付と支払予定日が同じレコードを検索 */
						if (firstPaymentDate.equals(pay2
								.getCreditScheduledDate())) {
							if (!flg) {
								/* 最初の計算 */
								pay.setPayableAmount(pay2
										.getTotalPayableAmount().subtract(
											totalPaymentAmount));
							} else {
								/* 2回目以降の計算 */
								pay.setPayableAmount(pay.getPayableAmount()
										.subtract(pay2.getPayableAmount()));
							}

							flg = true;
						}
					}
				}

				/* 支払日付と支払予定日が同じ最初のレコード処理が済んだら処理を抜ける */
				if (flg) {
					break;
				}
			}
		}

		for (AltPayment pay : list) {
			pay.setStrAccountPayableSum(checker.format(SIKINGAKU, pay
					.getAccountPayableSum())); // 今回買掛金合計
			pay.setStrPaymentAmountSum(checker.format(KINGAKU, pay
					.getPaymentAmountSum())); // 支払残高合計
			pay.setStrPayableAmount(checker.format(KINGAKU, pay
					.getPayableAmount())); // 支払残高

			if (0 <= strPaymentDate.compareTo(pay.getStrCreditScheduledDate())) {
				/* 支払日と支払予定日が同じ場合は振込手数料を取得 */
				if (0 == strPaymentDate.compareTo(pay
						.getStrCreditScheduledDate())) {
					commission = pay.getCommission();
					// paymentAmount =
					// paymentAmount.add(pay.getPayableAmount()); // 支払予定額
				}

				balanceForward = balanceForward.add(pay.getPayableAmount()); // 支払残高
			}
		}

		// 相殺検索
		AltPayment beanOffset = paymentCsmDetailLogic.getOffsetAmount(
			strPaymentDate, frm.getCustomerCd());

		if (beanOffset != null) {
			offsetAmount = beanOffset.getPayableOffsetAmount();
		}

		frm.setHeaderList(list);

		/* 支払合計 = 支払予定額 + 支払残高 - 支払済 - 相殺額 */
		paymentAmount = paymentAmount.add(balanceForward).subtract(paidAmount)
				.subtract(offsetAmount);

		/* 支払合計がゼロなら手数料もゼロにする */
		if (paymentAmount.equals(BigDecimal.ZERO)) {
			commission = BigDecimal.ZERO;
		}

		ProGetStockReductionCallDto dto = new ProGetStockReductionCallDto();

		/* 手形サイト情報取得 */
		dto = paymentCsmDetailLogic.getStockReduction(frm.getCustomerCd(),
			paymentAmount);

		if (dto != null) {
			stockReduction = dto.getPNumStockReduction(); /* 仕入割引額 */
			frm.setCreditMonthDivision(dto.getPNumCreditMonthDivision()); /* 支払月区分 */
			frm.setNoteSight(AecNumberUtils.convertNullToZero(dto
					.getPNumNoteSight())); /* 手形サイト */
		} else {
			frm.setCreditMonthDivision(BigDecimal.ZERO); /* 支払月区分 */
			frm.setNoteSight(AecNumberUtils.convertNullToZero(BigDecimal.ZERO)); /* 手形サイト */
		}

		frm.setStrBalanceForward(checker.format(SIKINGAKU, balanceForward)); /* 支払残高 */
		frm.setStrPaidAmount(checker.format(SIKINGAKU, paidAmount)); /* 支払済金額 */
		frm.setStrOffsetAmount(checker.format(SIKINGAKU, offsetAmount)); /* 相殺額 */
		frm.setStrPaymentAmount(checker.format(KINGAKU, paymentAmount)); /* 支払合計 */
		frm.setStrPurchaseDiscountAmount(checker.format(SIKINGAKU,
			stockReduction)); /* 仕入割引 */
		frm.setStrCommission(checker.format(SIKINGAKU, commission)); /* 手数料 */

		/* 支払合計がゼロの場合は今回支払予定額もゼロにする */
		if (paymentAmount.equals(new BigDecimal("0"))) {
			frm.setStrPaymentScheduledAmount(checker.format(KINGAKU,
				BigDecimal.ZERO));
		} else {
			frm.setStrPaymentScheduledAmount(checker.format(KINGAKU,
				paymentAmount.subtract(stockReduction).subtract(commission)));
		}
	}

	/**
	 * /なし日付を日付フォーマットに変換
	 * @param moto 変換前日付
	 * @return 変換後日付
	 */
	private Timestamp getDateformat(final String moto) {
		String saki = moto;
		boolean isYy = false;

		/* 支払日付に / がない場合 */
		if (moto.indexOf("/") == -1) {
			/* 日付文字列に変換 */
			if (moto.length() == 8) {
				saki = moto.substring(0, 4) + "/" + moto.subSequence(4, 6)
						+ "/" + moto.subSequence(6, 8);
			} else if (moto.length() == 6) {
				saki = moto.substring(0, 2) + "/" + moto.subSequence(2, 4)
						+ "/" + moto.subSequence(4, 6);
				isYy = true;
			}
		}

		return AecDateUtils.getTimestampYmdFormat(saki, isYy);
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
		BigDecimal dataType = new BigDecimal(CREDIT_DATA_TYPE);

		// コンボボックスの先頭行設定
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales(" ");

		categoryList.add(comboBlank);

		// 分類マスタから検索結果取得
		List<ClassificationListForComboboxes> bean = paymentCsmDetailLogic
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
	 * 科目マスタ一覧取得 ロジッククラスを設定します。
	 * @param accountsListLogic 科目マスタ一覧取得 ロジッククラス
	 */
	public void setAccountsListLogic(final AccountsListLogic accountsListLogic) {
		this.accountsListLogic = accountsListLogic;
	}

	/**
	 * 支払支払詳細処理ロジッククラスを設定します。
	 * @param paymentCsmDetailLogic 支払支払詳細処理ロジッククラス
	 */
	public void setPaymentDetailLogic(
			final PaymentCsmDetailLogic paymentCsmDetailLogic) {
		this.paymentCsmDetailLogic = paymentCsmDetailLogic;
	}

	/**
	 * 銀行検索処理ロジッククラスを設定します。
	 * @param bankDetailLogic 銀行検索処理ロジッククラス
	 */
	public void setBankDetailLogic(final BankDetailLogic bankDetailLogic) {
		this.bankDetailLogic = bankDetailLogic;
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
	 * checkHolidayLogicを設定します。
	 * @param checkHolidayLogic checkHolidayLogic
	 */
	public void setCheckHolidayLogic(final CheckHolidayLogic checkHolidayLogic) {
		this.checkHolidayLogic = checkHolidayLogic;
	}

	/**
	 * paymentCsmDetailLogicを設定します。
	 * @param paymentCsmDetailLogic paymentCsmDetailLogic
	 */
	public void setPaymentCsmDetailLogic(
			final PaymentCsmDetailLogic paymentCsmDetailLogic) {
		this.paymentCsmDetailLogic = paymentCsmDetailLogic;
	}

	/**
	 * paymentHeaderDetailDaoを設定します。
	 * @param paymentHeaderDetailDao paymentHeaderDetailDao
	 */
	public void setPaymentHeaderDetailDao(
			final PaymentHeaderDetailDao paymentHeaderDetailDao) {
		this.paymentHeaderDetailDao = paymentHeaderDetailDao;
	}
}
