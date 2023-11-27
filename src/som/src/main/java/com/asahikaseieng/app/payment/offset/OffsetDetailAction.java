/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.accounts.AccountsDetailLogic;
import com.asahikaseieng.app.master.offsetgroup.OffsetGroupDetailLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.entity.master.accounts.Accounts;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.nonentity.checkholiday.CheckHolidayDetail;
import com.asahikaseieng.dao.nonentity.checkholiday.CheckHolidayDetailDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.offsetgroupdetail.OffsetGroupDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDeposit;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayable;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetTranData;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 相殺処理詳細 Actionクラス.
 * @author tosco
 */
public final class OffsetDetailAction extends AbstractAction {

	private OffsetDetailLogic offsetDetailLogic;

	private OffsetGroupDetailLogic offsetGroupDetailLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private AccountsDetailLogic accountsDetailLogic;
	
	private CheckHolidayDetailDao checkHolidayDetailDao;
	
	private VenderDao venderDao;

	/** 分類マスタ.データ種別＝グループ間相殺(5) */
	public static final BigDecimal CATEGORY_DATA_TYPE_OFFSET = new BigDecimal(
			"5");

	/** 科目マスタ.売上科目区分＝売掛(10) */
	public static final BigDecimal ACCOUNTS_ARTICLE_URI = new BigDecimal(10);

	/** 科目マスタ.売上科目区分＝未収(11) */
	public static final BigDecimal ACCOUNTS_ARTICLE_MI = new BigDecimal(11);

	/** 科目マスタ.仕入科目区分＝買掛(1) */
	public static final BigDecimal ACCOUNTS_PURCHASE_KAI = new BigDecimal(1);

	/** 科目マスタ.仕入科目区分＝未払(2) */
	public static final BigDecimal ACCOUNTS_PURCHASE_MI = new BigDecimal(2);

	/** 遷移先－売掛元帳 */
	private static final String FORWARD_ARLEDGER = "backArLeder";

	/** 遷移先－買掛元帳 */
	private static final String FORWARD_APLEDGER = "backApLeder";

	/** 取引先区分 得意先 'TS' */
	private static final String VENDER_DIVISION_TS = "TS";

	/** 取引先区分 仕入先 'SI' */
	private static final String VENDER_DIVISION_SI = "SI";

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/**
	 * コンストラクタ.
	 */
	public OffsetDetailAction() {
		super();
	}

	/**
	 * 詳細画面表示処理
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET,
			Constants.TAB_ID_OFFSET_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		String offsetNo = frm.getOffsetNo();

		/* 検索値設定用 */
		List<OffsetPayable> payableList = new ArrayList<OffsetPayable>();
		List<OffsetDeposit> depositList = new ArrayList<OffsetDeposit>();

		BigDecimal totalPayable = BigDecimal.ZERO;
		BigDecimal totalCredit = BigDecimal.ZERO;
		BigDecimal offsetAmount = BigDecimal.ZERO;

		BigDecimal payableFlg = null;
		BigDecimal creditFlg = null;

		try {
			/* 初期検索 */
			List<OffsetTranData> tranList = offsetDetailLogic
					.getDetailData(offsetNo);

			// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),
			// "###,###,###"));
			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, tranList.get(0));
			frm.setOrganizationCd(tranList.get(0).getOrganizationCd());
			frm.setSrhOrganizationName(tranList.get(0).getOrganizationName());

			String strDate = tranList.get(0).getOffsetDate().toString();
			strDate = strDate.replace("-", "/");

			frm.setStrOffsetDate(AecDateUtils.dateFormat(AecDateUtils
					.getTimestampYmdFormat(strDate), "yyyy/MM/dd"));

			/* 分類取得 */
			ClassificationDetail categoryBean = offsetDetailLogic
					.getClassificationEntity(CATEGORY_DATA_TYPE_OFFSET, frm
							.getCategoryDivi());
			if (categoryBean != null) {
				/* 仕入科目区分取得 */
				Accounts payableAccounts = accountsDetailLogic
						.getEntity(categoryBean.getDebitAccountsCd());
				payableFlg = payableAccounts.getPurchaseAccounts();

				/* 売上科目区分取得 */
				Accounts creditAccounts = accountsDetailLogic
						.getEntity(categoryBean.getCreditAccountsCd());
				creditFlg = creditAccounts.getArticleAccounts();

			}

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic checker = CheckDigitUtil
					.getCheckDigitUtils(request);

			for (OffsetTranData tran : tranList) {
				/* 変更・参照判定 */
				if (checkUpdateDivi(tran)) {
					// 参照フラグを立てる
					frm.setReferFlg(1);
				}

				if (tran.getVenderDivision().equals(VENDER_DIVISION_SI)) {
					/* 買掛リスト作成 */
					OffsetPayable payableBean = getPayableBean(tran, payableFlg);
					if (payableBean != null) {
						payableBean.setStrPayableAmount(checker.format(
							SIKINGAKU, payableBean.getPayableAmount())); /* 買掛残 */
						payableBean.setStrTotalPayableAmount(checker.format(
							SIKINGAKU, payableBean.getTotalPayableAmount())); /* 買掛残合計 */

						// 買掛側の相殺金額を設定（数値）
						payableBean.setOffsetAmount(tran
								.getPayableOffsetAmount());

						// 買掛側の相殺金額を設定（文字列）
						payableBean.setStrPayableOffset(checker.format(
							SIKINGAKU, tran.getPayableOffsetAmount()));

						/* 買掛一覧用リストに設定 */
						payableList.add(payableBean);
						// 買掛残を加算
						totalPayable = totalPayable.add(payableBean
								.getPayableAmount());
						offsetAmount = tran.getOffsetAmount();
					}

				}

				if (tran.getVenderDivision().equals(VENDER_DIVISION_TS)) {
					/* 売掛リスト作成 */
					OffsetDeposit depositBean = getDepositBean(tran, creditFlg);
					if (depositBean != null) {
						depositBean.setStrCreditAmount(checker.format(
							URKINGAKU, depositBean.getCreditAmount())); /* 売掛残 */
						depositBean.setStrTotalCreditAmount(checker.format(
							URKINGAKU, depositBean.getTotalCreditAmount())); /* 売掛残合計 */

						// 売掛側の相殺金額を設定（数値）
						depositBean.setOffsetAmount(tran
								.getDepositOffsetAmount());

						// 売掛側の相殺金額を設定（文字列）
						depositBean.setStrDepositOffset(checker.format(
							URKINGAKU, tran.getDepositOffsetAmount())); /* 相殺金額 */

						/* 売掛一覧用リストに設定 */
						depositList.add(depositBean);
						// 売掛残を加算
						totalCredit = totalCredit.add(depositBean
								.getCreditAmount());
						// 相殺金額計
						offsetAmount = tran.getOffsetAmount();
					}
				}
			}
			/* 買掛リスト設定 */
			frm.setPayableList(payableList);
			/* 買掛残合計設定 */
			frm.setStrTotalPayableAmount(checker
					.format(SIKINGAKU, totalPayable));
			/* 買掛側相殺金額設定 */
			frm.setStrTotalPayableOffset(checker
					.format(SIKINGAKU, offsetAmount));
			frm.setHiddenPayableOffset(offsetAmount.toString());
			/* 売掛リスト設定 */
			frm.setDepositList(depositList);
			/* 売掛残合計設定 */
			frm.setStrTotalCreditAmount(checker.format(URKINGAKU, totalCredit));
			/* 売掛側相殺金額設定 */
			frm.setStrTotalDepositOffset(checker
					.format(URKINGAKU, offsetAmount));
			frm.setHiddenDepositOffset(offsetAmount.toString());
			/* 初期設定：残高 */
			frm.setBalanceAmount("0");

		} catch (NoDataException e) {
			/* エラーメッセージ */
			saveError(request, "errors.nodata.deleted");
			return mapping.findForward("back");
		}

		return mapping.findForward("success");
	}

	/**
	 * 買掛ヘッダー検索
	 * @param tran 相殺トランザクションデータ
	 * @param payableFlg
	 * @return 買掛データ
	 * @throws NoDataException
	 */
	private OffsetPayable getPayableBean(final OffsetTranData tran,
			final BigDecimal payableFlg) throws NoDataException {
		Date payableDate = null;
		/* 買掛更新フラグ判定 */
		if (tran.getPayableUpdateDivi().compareTo(BigDecimal.ZERO) != 0) {
			// 引数設定
			payableDate = tran.getPayableUpdateDate();
		}
		/* 買掛ヘッダー検索 */
		OffsetPayable payableBean = offsetDetailLogic.getPayable(tran
				.getOrganizationCd(), tran.getVenderCd(), payableDate,
			payableFlg);

		return payableBean;
	}

	/**
	 * 売掛ヘッダー検索
	 * @param tran 相殺トランザクションデータ
	 * @return 売掛ヘッダー
	 * @throws NoDataException
	 */
	private OffsetDeposit getDepositBean(final OffsetTranData tran,
			final BigDecimal creditFlg) throws NoDataException {
		Date depositDate = null;
		/* 売掛更新フラグ判定 */
		if (tran.getDepositUpdateDivi().compareTo(BigDecimal.ZERO) != 0) {
			// 引数設定
			depositDate = tran.getDeliveryUpdateDate();
		}
		/* 売掛ヘッダー検索 */
		OffsetDeposit depositBean = offsetDetailLogic.getDeposit(tran
				.getOrganizationCd(), tran.getVenderCd(), depositDate,
			creditFlg);

		return depositBean;
	}

	/**
	 * 更新フラグチェック
	 * @param tranData 相殺トランザクション
	 * @return true:参照のみ、false:変更可
	 */
	private boolean checkUpdateDivi(final OffsetTranData tranData) {
		boolean res = false;
		if (isDone(tranData.getDepositUpdateDivi())
				|| isDone(tranData.getClaimUpdateDivi())
				|| isDone(tranData.getPayableUpdateDivi())
				|| isDone(tranData.getPaymentUpdateDivi())
				|| isDone(tranData.getEraserCompleteDivi())
				|| tranData.getTransmissionDate() != null) {
			/* 売り掛け更新、請求更新、買掛更新、支払い更新フラグ、消込完了フラグ、データ転送日時 */
			/* 何れかが立っていた場合は、更新付加->照会モード */
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
	 * 新規ボタン押下処理
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET,
			Constants.TAB_ID_OFFSET_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setOrganizationCd(loginInfo.getOrganizationCd());

			/* 部署マスタ検索 */
			OrganizationDetail bumonDate = organizationDetailLogic
					.getDetailEntity(frm.getOrganizationCd());
			if (bumonDate != null) {
				/* 初期表示：部署名称 */
				frm.setSrhOrganizationName(bumonDate.getOrganizationName());
			} else {
				frm.setSrhOrganizationName("");
			}
		}
		/* 初期表示：分類コンボボックス */
		frm.setCategoryList(getClassificationList());
		/* 初期設定：insertFlg */
		frm.setInsertFlg(1);
		/* 初期設定：searchFlg */
		frm.setSearchFlg(0);
		/* 初期設定：referFlg */
		frm.setReferFlg(0);
		/* 初期設定：相殺グループコンボ */
		frm.setOffsetGroupCdList(getOffsetGroupComboBox());
		/* 初期設定：残高 */
		frm.setBalanceAmount("0");
		/* 初期設定：承認ステータス */
		frm.setApprovalStatus(new BigDecimal("0"));

		return mapping.findForward("success");
	}

	/**
	 * 検索処理
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
			getLog().debug("update.");
		}

		OffsetDetailForm frm = (OffsetDetailForm) form;

		String offsetGroupCd = frm.getOffsetGroupCd(); // 相殺グループ
		String categoryDivi = frm.getCassification(); // 分類
		// 科目チェック用
		BigDecimal payableAccounts = null;
		BigDecimal creditAccounts = null;
		// データ存在チェック用
		boolean payableFlg = false;
		boolean creditFlg = false;
		// 締め年月チェック用
		String payableDate = null;
		String creditDate = null;

		/* 相殺グループ取得 */
		List<OffsetGroupDetail> offsetGroupList = offsetGroupDetailLogic
				.getEntity(offsetGroupCd);
		if (!offsetGroupList.isEmpty()) {
			OffsetGroupDetail offsetGroupData = offsetGroupList.get(0);
			if (offsetGroupData != null) {
				frm.setOffsetGroupName(offsetGroupData.getOffsetGroupName());
			}
		}

		/* 分類取得 */
		ClassificationDetail categoryBean = offsetDetailLogic
				.getClassificationEntity(CATEGORY_DATA_TYPE_OFFSET,
					categoryDivi);
		if (categoryBean != null) {
			/* 分類名称取得 */
			frm.setCategoryDivi(categoryBean.getCategoryDivision());
			frm.setCategoryName(categoryBean.getCategoryName());

			/* 仕入科目区分取得 */
			Accounts accountsPayable = accountsDetailLogic
					.getEntity(categoryBean.getDebitAccountsCd());
			payableAccounts = accountsPayable.getPurchaseAccounts();

			/* 売上科目区分取得 */
			Accounts accountsCredit = accountsDetailLogic
					.getEntity(categoryBean.getCreditAccountsCd());
			creditAccounts = accountsCredit.getArticleAccounts();

			// 項目タイトル
			frm.setTitlePayable(accountsPayable.getAccountsName());
			frm.setTitleCredit(accountsCredit.getAccountsName());
		}

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 検索 */
		if ((payableAccounts != null)
				&& ((payableAccounts.compareTo(ACCOUNTS_PURCHASE_KAI) == 0) || (payableAccounts
						.compareTo(ACCOUNTS_PURCHASE_MI) == 0))) {

			List<OffsetPayable> payableList = offsetDetailLogic.getPayableNew(
				frm.getOrganizationCd(), offsetGroupCd, payableAccounts);

			if (!payableList.isEmpty()) {
				payableFlg = true;
				// 買掛残合計
				frm.setStrTotalPayableAmount(payableList.get(
					payableList.size() - 1).getStrTotalPayableAmount());
				// 買掛リスト
				frm.setPayableList(payableList);
				// 買掛側相殺金額
				frm.setStrTotalPayableOffset("0");
				// 締め年月チェック用編集
				String temDate = payableList.get(0).getPayableDate().toString();
				payableDate = temDate.substring(0, temDate.lastIndexOf("-"));
				for (OffsetPayable payable : payableList) {
					// 相殺金額をカンマ編集
					// payable.setStrPayableOffset(getCurrency(payable
					// .getStrPayableOffset()));
					payable.setStrPayableOffset(checker.format(SIKINGAKU,
						AecNumberUtils.convertBigDecimal(payable
								.getStrPayableOffset())));
					payable.setStrPayableAmount(checker.format(SIKINGAKU,
						payable.getPayableAmount())); /* 買掛残 */
				}
			}
		}

		/* 検索 */
		if ((creditAccounts != null)
				&& ((creditAccounts.compareTo(ACCOUNTS_ARTICLE_URI) == 0) || (creditAccounts
						.compareTo(ACCOUNTS_ARTICLE_MI) == 0))) {

			List<OffsetDeposit> depositList = offsetDetailLogic.getDepositNew(
				frm.getOrganizationCd(), offsetGroupCd, creditAccounts);

			if (!depositList.isEmpty()) {
				creditFlg = true;
				// 売掛残合計
				frm.setStrTotalCreditAmount(depositList.get(
					depositList.size() - 1).getStrTotalCreditAmount());
				// 売掛リスト
				frm.setDepositList(depositList);
				// 売掛側相殺金額
				frm.setStrTotalDepositOffset("0");
				// 締め年月チェック用編集
				String temDate = depositList.get(0).getCreditDate().toString();
				creditDate = temDate.substring(0, temDate.lastIndexOf("-"));
				for (OffsetDeposit deposit : depositList) {
					// 相殺金額をカンマ編集
					// deposit.setStrDepositOffset(getCurrency(deposit
					// .getStrDepositOffset()));
					deposit.setStrDepositOffset(checker.format(URKINGAKU,
						AecNumberUtils.convertBigDecimal(deposit
								.getStrDepositOffset())));
					deposit.setStrCreditAmount(checker.format(URKINGAKU,
						deposit.getCreditAmount())); /* 売掛残 */
				}
			}
		}

		// 締め年月チェック
		if ((payableDate != null) && (creditDate != null)
				&& (!payableDate.equals(creditDate))) {
			// 買掛リストクリア
			frm.setPayableList(null);
			// 売掛リストクリア
			frm.setDepositList(null);
			// 摘要名クリア
			// frm.setSrhSummary(null);
			// frm.setSrhSummaryCd(null);
			/* エラーメッセージ */
			saveError(request, "errors.offset.date");
			return mapping.findForward("success");
		}
		if (payableFlg && creditFlg) {
			// 登録ボタン表示フラグ
			// (両方のデータがそろった場合のみ表示する)
			frm.setButtonFlg(1);
		} else if ((!payableFlg) && (!creditFlg)) {
			// error：該当データがありません。
			throw new NoDataException();
		}

		// 残高
		frm.setBalanceAmount("0");
		// 検索実行フラグ
		frm.setSearchFlg(1);

		return mapping.findForward("success");

	}

	/**
	 * クリア処理．新規ボタン押下時の状態にする
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* クリア */
		frm.setPayableList(null);
		frm.setDepositList(null);
		frm.setStrTotalCreditAmount(null);
		frm.setStrTotalDepositOffset(null);
		frm.setStrTotalPayableAmount(null);
		frm.setStrTotalPayableOffset(null);
		frm.setSrhSummary(null);
		frm.setSrhSummaryCd(null);

		/* 初期設定：insertFlg */
		frm.setInsertFlg(1);
		/* 初期設定：searchFlg */
		frm.setSearchFlg(0);
		/* 初期設定：登録ボタン表示フラグ */
		frm.setButtonFlg(0);
		/* 初期設定：残高 */
		frm.setBalanceAmount("0");

		return mapping.findForward("success");
	}

	/**
	 * 変更処理
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		List<OffsetPayable> payableList = frm.getPayableList();
		List<OffsetDeposit> depositList = frm.getDepositList();

		/* 相殺金額チェック */ //2021/12/27 相殺日付の日付チェックを追加
		if ((checkPayableOffset(request, payableList))
				&& (checkDepositOffset(request, depositList))) {
			return mapping.getInputForward();
		}
		//2021/12/27 相殺日付の日付チェックを追加
		if(checkOffsetDate(request, frm)){
			return mapping.getInputForward();
		}

		/* 更新対象データを作成する */
		OffsetDetail newHedBean = new OffsetDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newHedBean, frm);

		// 部署コード
		// newHedBean.setOrganizationCd(frm.getOrganizationCd());

		// 相殺金額
		BigDecimal offsetAmount = BigDecimal.ZERO;
		if ((frm.getHiddenDepositOffset() != null)
				&& (!frm.getHiddenDepositOffset().equals(""))
				&& (!frm.getHiddenDepositOffset().equals("0"))) {
			// 相殺金額設定
			offsetAmount = new BigDecimal(frm.getHiddenDepositOffset());
		} else {
			// 相殺金額が入力されていません。
			saveError(request, "errors.offset.amount");
			return mapping.getInputForward();
		}
		newHedBean.setOffsetAmount(offsetAmount);

		// 承認ステータス
		// newHedBean.setApprovalStatus(BigDecimal.ONE);
		// 相殺日時
		Timestamp sysDate = AecDateUtils.getCurrentTimestamp();
		// String strDate = AecDateUtils.dateFormat(sysDate, "yyyy/MM/dd");
		// Date offsetDate = getSqlDateFormat(strDate);
		// newHedBean.setOffsetDate(offsetDate);
		Date offsetDate = getSqlDateFormat(frm.getStrOffsetDate());
		newHedBean.setOffsetDate(offsetDate);

		// 基本情報入力
		newHedBean.setUpdateDate(sysDate);
		String tantoCd = getLoginInfo(request).getTantoCd();
		newHedBean.setUpdatorCd(tantoCd);

		try {
			/* 更新処理を実行 */
			offsetDetailLogic.update(newHedBean, payableList, depositList);

		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();

		}

		/* メッセージ */
		saveMessage(request, "message.complete.update");

		return mapping.findForward("success");

	}

	/**
	 * 登録処理
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		List<OffsetPayable> payableList = frm.getPayableList();
		List<OffsetDeposit> depositList = frm.getDepositList();
		
		

		/* 相殺金額チェック */
		if ((checkPayableOffset(request, payableList))
				|| (checkDepositOffset(request, depositList))) {
			return mapping.getInputForward();
		}
		
		//2021/12/27 相殺日付の日付チェックを追加
		if(checkOffsetDate(request, frm)){
			return mapping.getInputForward();
		}

		/* 更新対象データを作成する */
		OffsetDetail newHedBean = new OffsetDetail();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newHedBean, frm);

		// 部署コード
		// newHedBean.setOrganizationCd(frm.getOrganizationCd());

		// 相殺金額
		BigDecimal offsetAmount = BigDecimal.ZERO;
		if ((frm.getHiddenDepositOffset() != null)
				&& (!frm.getHiddenDepositOffset().equals(""))
				&& (!frm.getHiddenDepositOffset().equals("0"))) {
			// 相殺金額設定
			offsetAmount = new BigDecimal(frm.getHiddenDepositOffset());
		} else {
			// 相殺金額が入力されていません。
			saveError(request, "errors.offset.amount");
			return mapping.getInputForward();
		}
		newHedBean.setOffsetAmount(offsetAmount);
		// 承認ステータス
		BigDecimal approvalStatus = new BigDecimal("1");
		newHedBean.setApprovalStatus(approvalStatus);
		// 相殺日時
		Timestamp sysDate = AecDateUtils.getCurrentTimestamp();
		// String strDate = AecDateUtils.dateFormat(sysDate, "yyyy/MM/dd");
		// Date offsetDate = getSqlDateFormat(strDate);
		// newHedBean.setOffsetDate(offsetDate);
		Date offsetDate = getSqlDateFormat(frm.getStrOffsetDate());
		newHedBean.setOffsetDate(offsetDate);

		String tantoCd = getLoginInfo(request).getTantoCd();

		// 相殺番号取得(FUNCTION呼出)
		String offsetNo = offsetDetailLogic.callFunction(tantoCd);

		// 基本情報入力
		frm.setInputDate(sysDate); /* 登録日時 */
		frm.setInputorCd(tantoCd); /* 登録者ID */

		newHedBean.setOffsetNo(offsetNo);
		newHedBean.setUpdateDate(sysDate);
		newHedBean.setInputDate(sysDate);
		newHedBean.setUpdatorCd(tantoCd);
		newHedBean.setInputorCd(tantoCd);

		/* 更新処理を実行 */
		offsetDetailLogic.insert(frm.getCassification(), newHedBean,
			payableList, depositList);

		frm.setOffsetNo(offsetNo);
		frm.setApprovalStatus(approvalStatus);
		frm.setInsertFlg(0);

		/* メッセージ */
		saveMessage(request, "message.complete.insert");

		return mapping.findForward("success");
	}

	/**
	 * 削除処理
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 削除対象データを作成する:プライマリキー */
		OffsetDetail bean = offsetDetailLogic
				.getOffsetHeader(frm.getOffsetNo());

		List<OffsetPayable> payableList = frm.getPayableList();
		List<OffsetDeposit> depositList = frm.getDepositList();

		try {
			/* 削除処理を実行 */
			offsetDetailLogic.delete(bean, payableList, depositList);
		} catch (NoDataException e) {
			saveError(request, "errors.nodata.deleted");
			return mapping.getInputForward();
		}

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");

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
		OffsetDetailForm frm = (OffsetDetailForm) form;
		String back = frm.getBackForward();
		if (StringUtils.isNotEmpty(back)) {
			forward = back;
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
		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET,
			Constants.TAB_ID_OFFSET_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, frm, FORWARD_ARLEDGER);
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
		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_OFFSET,
			Constants.TAB_ID_OFFSET_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		// 参照用データ作成
		String forward = showReferenceAction(request, frm, FORWARD_APLEDGER);
		return mapping.findForward(forward);
	}

	/**
	 * 売掛側、相殺金額チェック
	 * @param request
	 * @param depositList 売掛リスト
	 * @return true:エラー、false：問題なし
	 */
	private boolean checkDepositOffset(final HttpServletRequest request,
			final List<OffsetDeposit> depositList) {
		for (OffsetDeposit bean : depositList) {
			BigDecimal offset = AecNumberUtils.convertBigDecimal(bean
					.getStrDepositOffset());
			BigDecimal deposit = AecNumberUtils.convertBigDecimal(bean
					.getStrCreditAmount());
			// 相殺金額 >= 売掛残 の場合エラー
			if (deposit.compareTo(offset) < 0) {
				// 売掛取引一覧の 相殺金額計 が 合計 以下となるような値を入力してください。
				saveError(request, "errors.offset.deposit");
				return true;
			}
		}
		return false;
	}

	/**
	 * 買掛側、相殺金額チェック
	 * @param request
	 * @param payableList 買掛リスト
	 * @return true:エラー、false：問題なし
	 */
	private boolean checkPayableOffset(final HttpServletRequest request,
			final List<OffsetPayable> payableList) {
		for (OffsetPayable bean : payableList) {
			BigDecimal offset = AecNumberUtils.convertBigDecimal(bean
					.getStrPayableOffset());
			BigDecimal payable = AecNumberUtils.convertBigDecimal(bean
					.getStrPayableAmount());
			// 相殺金額 >= 買掛残 の場合エラー
			if (payable.compareTo(offset) < 0) {
				// 買掛取引一覧の 相殺金額計 が 合計 以下となるような値を入力してください。
				saveError(request, "errors.offset.payable");
				return true;
			}
		}
		return false;
	}

	/**
	 * 相殺日のカレンダーマスタチェック　2021年12月27日追加
	 * @param request
	 * @param payableList 買掛リスト
	 * @return true:エラー、false：問題なし
	 */
	private boolean checkOffsetDate(final HttpServletRequest request,final OffsetDetailForm frm) {

		
		// 2021/12/27 相殺画面で、相殺日が休日でも登録できる不具合を修正。カレンダーは1200：銀行用を採用
		CheckHolidayDetail bean = checkHolidayDetailDao.getCalHoliday("1200", AecDateUtils.getTimestampYmdFormat(frm.getStrOffsetDate()));
		if(bean == null){
			// カレンダーがない場合
			saveError(request, "errors.offset.nodata.calendar.cd");
			return true;
		}else if(bean.getCalHoliday().equals(BigDecimal.ONE)){
			// 休日の場合、エラーとする
			saveError(request, "errors.offset.offset.day.holday");
			return true;
		}
		
		return false;
	}

	/**
	 * 参照用データ作成処理
	 * @param request HttpServletRequest
	 * @param frm 詳細画面Form
	 * @param forward 戻り先
	 * @return 遷移先
	 * @throws IllegalAccessException アクセス権が無い場合発生
	 * @throws InvocationTargetException 対象メソッドが無い場合発生
	 */
	private String showReferenceAction(final HttpServletRequest request,
			final OffsetDetailForm frm, final String forward)
			throws IllegalAccessException, InvocationTargetException {
		String res = "success";
		/* 参照フラグを立てる */
		frm.setReferFlg(1);

		// 戻り先を設定
		frm.setBackForward(forward);

		/* 検索値設定用 */
		List<OffsetPayable> payableList = new ArrayList<OffsetPayable>();
		List<OffsetDeposit> depositList = new ArrayList<OffsetDeposit>();

		// 初期化
		BigDecimal totalPayable = BigDecimal.ZERO;
		BigDecimal totalCredit = BigDecimal.ZERO;
		BigDecimal offsetAmount = BigDecimal.ZERO;

		BigDecimal payableFlg = null;
		BigDecimal creditFlg = null;

		try {
			/* 初期検索 */
			List<OffsetTranData> tranList = offsetDetailLogic.getDetailData(frm
					.getOffsetNo());

			/* 分類取得 */
			ClassificationDetail categoryBean = offsetDetailLogic
					.getClassificationEntity(CATEGORY_DATA_TYPE_OFFSET,
						tranList.get(0).getCategoryDivi());
			if (categoryBean != null) {
				/* 仕入科目区分取得 */
				Accounts payableAccounts = accountsDetailLogic
						.getEntity(categoryBean.getDebitAccountsCd());
				payableFlg = payableAccounts.getPurchaseAccounts();

				/* 売上科目区分取得 */
				Accounts creditAccounts = accountsDetailLogic
						.getEntity(categoryBean.getCreditAccountsCd());
				creditFlg = creditAccounts.getArticleAccounts();

			}

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, tranList.get(0));

			/* 数値桁数チェック部品呼び出し */
			CheckDigitUtilsLogic checker = CheckDigitUtil
					.getCheckDigitUtils(request);

			for (OffsetTranData tran : tranList) {
				/* 買掛リスト作成 */
				OffsetPayable payableBean = getPayableBean(tran, payableFlg);
				if (payableBean != null) {
					payableBean.setStrPayableAmount(checker.format(SIKINGAKU,
						payableBean.getPayableAmount())); /* 買掛残 */
					payableBean.setStrTotalPayableAmount(checker.format(
						SIKINGAKU, payableBean.getTotalPayableAmount())); /* 買掛残合計 */

					// 買掛側の相殺金額を設定（数値）
					payableBean.setOffsetAmount(tran.getPayableOffsetAmount());
					// 買掛側の相殺金額を設定（文字列）
					payableBean.setStrPayableOffset(checker.format(SIKINGAKU,
						tran.getPayableOffsetAmount()));
					/* 買掛一覧用リストに設定 */
					payableList.add(payableBean);
					// 買掛残を加算
					totalPayable = totalPayable.add(payableBean
							.getPayableAmount());
					offsetAmount = tran.getOffsetAmount();
				}

				/* 売掛リスト作成 */
				OffsetDeposit depositBean = getDepositBean(tran, creditFlg);
				if (depositBean != null) {
					depositBean.setStrCreditAmount(checker.format(URKINGAKU,
						depositBean.getCreditAmount())); /* 売掛残 */
					depositBean.setStrTotalCreditAmount(checker.format(
						URKINGAKU, depositBean.getTotalCreditAmount())); /* 売掛残合計 */

					// 売掛側の相殺金額を設定（数値）
					depositBean.setOffsetAmount(tran.getDepositOffsetAmount());
					// 売掛側の相殺金額を設定（文字列）
					depositBean.setStrDepositOffset(checker.format(URKINGAKU,
						tran.getDepositOffsetAmount()));
					/* 売掛一覧用リストに設定 */
					depositList.add(depositBean);
					// 売掛残を加算
					totalCredit = totalCredit
							.add(depositBean.getCreditAmount());
					// 相殺金額計
					offsetAmount = tran.getOffsetAmount();
				}
			}

			/* 買掛リスト設定 */
			frm.setPayableList(payableList);
			/* 買掛残合計設定 */
			frm.setStrTotalPayableAmount(checker
					.format(SIKINGAKU, totalPayable));
			/* 買掛側相殺金額設定 */
			frm.setStrTotalPayableOffset(checker
					.format(SIKINGAKU, offsetAmount));
			frm.setHiddenPayableOffset(offsetAmount.toString());
			/* 売掛リスト設定 */
			frm.setDepositList(depositList);
			/* 売掛残合計設定 */
			frm.setStrTotalCreditAmount(checker.format(URKINGAKU, totalCredit));
			/* 売掛側相殺金額設定 */
			frm.setStrTotalDepositOffset(checker
					.format(URKINGAKU, offsetAmount));
			frm.setHiddenDepositOffset(offsetAmount.toString());
			/* 初期設定：残高 */
			frm.setBalanceAmount("0");

		} catch (NoDataException e) {
			// データが検索できなかった場合、エラーメッセージを表示して、一覧へ戻る
			addError(request, "errors.nodata.deleted");
			res = forward;
		}

		return res;
	}

	/**
	 * 相殺グループコード＋相殺グループ名称のコンボボックス取得
	 * 
	 * @return List<ComboBoxItems> 相殺グループのコンボボックス
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private List<ComboBoxItems> getOffsetGroupComboBox() throws NoDataException {

		List<ComboBoxItems> accountsList = new ArrayList<ComboBoxItems>();

		// 相殺グループ一覧取得
		List<OffsetGroupDetail> list = offsetGroupDetailLogic.getEntity("");

		if (list.isEmpty()) {
			ComboBoxItems combo = new ComboBoxItems();
			// 相殺グループコード設定(Value値)
			combo.setValues("");
			// 相殺グループ名称設定(ラベル)
			combo.setLabales("");
			accountsList.add(combo);

		} else {
			// 同じコードは１度だけ
			String tmpOffsetGroupCd = "";
			// 相殺グループコード、相殺グループ名称を取得して配列に設定
			for (OffsetGroupDetail bean : list) {
				if (tmpOffsetGroupCd.equals(bean.getOffsetGroupCd())) {
					continue;
				} else {
					ComboBoxItems combo = new ComboBoxItems();
					// 相殺グループコード設定(Value値)
					combo.setValues(bean.getOffsetGroupCd());
					// 相殺グループ名称設定(ラベル)
					combo.setLabales(bean.getOffsetGroupName());
					accountsList.add(combo);
					tmpOffsetGroupCd = bean.getOffsetGroupCd();
				}
			}

		}

		return accountsList;
	}

	/**
	 * 日にちを正しい年月日に補正した値を取得
	 * 
	 * @param strDate 画面の年月日
	 * @return Date フォーマットした年月日
	 */
	private Date getSqlDateFormat(final String strDate) {

		// スラッシュ分割
		String[] ymd = strDate.split("/");
		int year = Integer.parseInt(ymd[0]);
		int month = Integer.parseInt(ymd[1]);
		int day = Integer.parseInt(ymd[2]);

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		// 月が変わってしまう場合
		if (month - 1 != cal.get(Calendar.MONTH)) {
			cal.set(year, month - 1, 1);
			// 画面締め年月の月末日取得
			int endDay = cal.getActualMaximum(Calendar.DATE);
			cal.set(year, month - 1, endDay);
		}

		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 分類コンボボックスのリスト取得
	 * @return
	 * @throws NoDataException
	 */
	@SuppressWarnings("unchecked")
	private List<ComboBoxItems> getClassificationList() throws NoDataException {
		// 検索結果を格納用リスト
		List<ComboBoxItems> classList = new ArrayList<ComboBoxItems>();
		// 分類マスタ.データ種別＝グループ間相殺(5)のみ対象
		BigDecimal dataType = CATEGORY_DATA_TYPE_OFFSET;

		// 分類マスタ検索
		List<ClassificationListForComboboxes> result = offsetDetailLogic
				.getClassificationList(dataType);

		/* 分類コード順のソート */
		Collections.sort(result, new ClassificationComparator(
				ClassificationComparator.ASC));

		for (ClassificationListForComboboxes bean : result) {
			String categoryDivi = bean.getCategoryDivision();
			ComboBoxItems combo = new ComboBoxItems();
			// 科目コード設定(Value値)
			combo.setValues(categoryDivi);
			// 科目名称をラベルとして設定
			combo.setLabales(bean.getCategoryName());
			classList.add(combo);
		}
		return classList;
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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");

		/* ステータス更新 */
		offsetDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		frm.setApprovalStatus(new BigDecimal("2")); /* 承認依頼 */

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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");

		/* ステータス更新 */
		offsetDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		frm.setApprovalStatus(new BigDecimal("3")); /* 承認 */

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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		offsetDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		frm.setApprovalStatus(new BigDecimal("1")); /* 入力中 */

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

		OffsetDetailForm frm = (OffsetDetailForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");

		/* ステータス更新 */
		offsetDetailLogic.statusUpdate(frm, status, getLoginInfo(request)
				.getTantoCd());

		frm.setApprovalStatus(new BigDecimal("1")); /* 入力中 */

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * OffsetDetailLogicを設定します。
	 * @param offsetDetailLogic offsetDetailLogic
	 */
	public void setOffsetDetailLogic(final OffsetDetailLogic offsetDetailLogic) {
		this.offsetDetailLogic = offsetDetailLogic;
	}

	/**
	 * OffsetGroupDetailLogicを設定します。
	 * @param offsetGroupDetailLogic OffsetGroupDetailLogic
	 */
	public void setOffsetGroupDetailLogic(
			final OffsetGroupDetailLogic offsetGroupDetailLogic) {
		this.offsetGroupDetailLogic = offsetGroupDetailLogic;
	}

	/**
	 * organizationDetailLogicを設定します。
	 * @param organizationDetailLogic organizationDetailLogic
	 */
	public void setOrganizationDetailLogic(
			final OrganizationDetailLogic organizationDetailLogic) {
		this.organizationDetailLogic = organizationDetailLogic;
	}

	/**
	 * accountsDetailLogicを設定します。
	 * @param accountsDetailLogic accountsDetailLogic
	 */
	public void setAccountsDetailLogic(
			final AccountsDetailLogic accountsDetailLogic) {
		this.accountsDetailLogic = accountsDetailLogic;
	}

	/**
	 * checkHolidayDetailDaoを設定します。
	 * @param checkHolidayDetailDao checkHolidayDetailDao
	 */
	public void setCheckHolidayDetailDao(CheckHolidayDetailDao checkHolidayDetailDao) {
		this.checkHolidayDetailDao = checkHolidayDetailDao;
	}

	/**
	 * venderDaoを設定します。
	 * @param venderDao venderDao
	 */
	public void setVenderDao(VenderDao venderDao) {
		this.venderDao = venderDao;
	}
}
