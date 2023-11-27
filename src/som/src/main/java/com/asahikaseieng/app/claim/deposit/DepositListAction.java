/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.deposit;

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
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCredit;
import com.asahikaseieng.dao.nonentity.claim.deposit.DepositCreditListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入金処理－入金入力リスト画面 Actionクラス.
 * @author tosco
 */
public final class DepositListAction extends AbstractSearchAction {

	/** 分類マスタ.データ種別＝入金(2) */
	public static final int CATEGORY_DATA_TYPE_CREDIT = 2;

	/** 部署マスタ詳細 ロジッククラス */
	private OrganizationDetailLogic organizationDetailLogic;

	/** 入金入力 ロジッククラス */
	private DepositListLogic depositListLogic;

	/** 仕訳伝票（入金伝票） */
	private DepositListExcelDecorator depositListExcelDecorator;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/**
	 * コンストラクタ.
	 */
	public DepositListAction() {
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

		DepositListForm depositform = (DepositListForm) form;

		/* 権限取得 */
		getControlAuthority(request, depositform, Constants.MENU_ID_DEPOSIT,
			Constants.TAB_ID_DEPOSIT_DETAIL);

		HttpSession session = request.getSession(false);
		if (session != null) {
			// ログインユーザ情報を取得
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			// ログインユーザ.部署コードを初期表示
			depositform.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			// 部署マスタから部署名称を検索
			OrganizationDetail organizationDatail = organizationDetailLogic
					.getDetailEntity(depositform.getSrhOrganizationCd());
			if (organizationDatail != null) {
				// 検索結果が存在した場合は、部署名称の初期値として設定する。
				depositform.setSrhOrganizationName(organizationDatail
						.getOrganizationName());

			} else {
				// 部署コードが存在しない場合は、名称をクリア
				depositform.setSrhOrganizationName("");
			}
		}
		// 入金分類（分類マスタから取得）
		depositform.setCategoryList(getCategoryComboBox());

		// 初期値:出力区分＝全て(0)
		depositform.setSrhOutputDivision("0");
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

		DepositListForm depositForm = (DepositListForm) form;

		// 前回検索結果をクリア
		depositForm.setSearchList(null);

		/* 検索条件を生成 */
		DepositCreditListPagerCondition condition = (DepositCreditListPagerCondition) depositForm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// データ種別
		condition.setDataType(CATEGORY_DATA_TYPE_CREDIT);
		// 部署コード
		condition.setOrganizationCd(depositForm.getSrhOrganizationCd());
		// 担当者コード
		condition.setTantoCd(depositForm.getSrhTantoCd());
		// 入金日付FROM
		condition.setCreditDateFrom(depositForm.getSrhCreditDateFrom());
		// 入金日付To
		condition.setCreditDateTo(depositForm.getSrhCreditDateTo());
		// 請求先コード
		condition.setVenderCd(depositForm.getSrhVenderCd());
		// 入金番号FROM
		condition.setCreditNoFrom(depositForm.getSrhSlipNoFrom());
		// 入金番号TO
		condition.setCreditNoTo(depositForm.getSrhSlipNoTo());
		// 入金分類
		condition.setCategoryDivision(depositForm.getSrhCategoryDivision());
		// 出力区分
		condition.setApprovalStatus(depositForm.getSrhOutputDivision());
		// 伝票発行
		if (depositForm.getSrhIssuedCheck()) {
			condition.setIssuedDivision("0");
		} else {
			condition.setIssuedDivision(null);
		}

		/* 帳票(Excel)用に検索条件を保持 */
		DepositCreditListConditionForReport reportCondition = new DepositCreditListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		depositForm.setCondition(reportCondition);

		// 入金トランザクションテーブルから検索実行
		List<DepositCredit> result = depositListLogic.getSearchList(condition);
		// 検索結果が存在する場合
		for (DepositCredit bean : result) {
			// bean.setCreditAmount(getFormatNumber(request, bean
			// .getCreditAmount()));
			// bean.setEraserAmount(getFormatNumber(request, bean
			// .getEraserAmount()));
			// bean.setCreditEraserAmount(getFormatNumber(request, bean
			// .getCreditEraserAmount()));
			bean.setCreditAmount(getFormatNumber(request, bean
					.getCreditAmount(), KINGAKU)); /* 入金金額 */
			bean.setEraserAmount(getFormatNumber(request, bean
					.getEraserAmount(), URKINGAKU)); /* 消込額 */
			bean.setCreditEraserAmount(getFormatNumber(request, bean
					.getCreditEraserAmount(), KINGAKU)); /* 入金消込残 */
			if ((bean.getDataTotalDivision() != null)
					&& (bean.getDataTotalDivision().intValue() == 2)) {
				// 消し込みの場合は、入金金額、消し込み金額は空白にする。
				bean.setEraserAmount("");
				bean.setCreditEraserAmount("");
			}
		}

		depositForm.setSearchList(result);

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

		DepositListForm frm = (DepositListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	// 内部ロジック--------------------------------------------------------------------------------------

	/**
	 * 数値フォーマットに変換する
	 * @param request request
	 * @param s 対象文字列
	 * @param unitDivision 区分
	 * @return
	 */
	private String getFormatNumber(final HttpServletRequest request,
			final String s, final String unitDivision) {
		String res = null;

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		if (StringUtils.isNotEmpty(s)) {
			BigDecimal dec = new BigDecimal(s);
			// res = checker.format(URKINGAKU, dec);
			res = checker.format(unitDivision, dec);
		}
		return res;
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

		DepositListForm frm = (DepositListForm) form;

		/* 承認依頼中 */
		BigDecimal status = new BigDecimal("2");
		BigDecimal statusMode = new BigDecimal("1");

		/* ステータス更新 */
		depositListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
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

		DepositListForm frm = (DepositListForm) form;

		/* 承認 */
		BigDecimal status = new BigDecimal("3");
		BigDecimal statusMode = new BigDecimal("2");

		/* ステータス更新 */
		depositListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
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

		DepositListForm frm = (DepositListForm) form;

		/* 否認 */
		BigDecimal status = new BigDecimal("1");
		BigDecimal statusMode = new BigDecimal("3");

		/* ステータス更新 */
		depositListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
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

		DepositListForm frm = (DepositListForm) form;

		/* 承認取消 */
		BigDecimal status = new BigDecimal("1");
		BigDecimal statusMode = new BigDecimal("4");

		/* ステータス更新 */
		depositListLogic.statusUpdate(frm, status, statusMode, getLoginInfo(
			request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("reSearch");
	}

	/**
	 * 印刷処理
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

		DepositListForm frm = (DepositListForm) form;
		ArrayList<String> creditNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();

		for (DepositCredit bean : frm.getSearchList()) {

			if (!bean.getChecked()) {
				continue;
			} else {
				// 処理を行う請求番号リストを作成
				creditNoList.add(bean.getCreditNo());
			}

		}

		if (!creditNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* 仕訳伝票（入金伝票）出力処理 */
			info = depositListExcelDecorator.createReport(creditNoList,
				tantoNm, null, request.getRemoteAddr());
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);

			/* 伝票発行フラグ更新 */
			depositListLogic.updateIssuedDivision(creditNoList, getLoginInfo(
				request).getTantoCd());
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

		DepositListForm frm = (DepositListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = depositListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 入金分類コンボボックス内容取得
	 * @return List<ComboBoxItems> 入金コンボボックス内容リスト
	 */
	@SuppressWarnings("unchecked")
	public List<ComboBoxItems> getCategoryComboBox() {
		// 検索結果を格納用リスト
		List<ComboBoxItems> categoryList = new ArrayList<ComboBoxItems>();
		// 分類マスタ.データ種別＝入金(2)のみ対象
		BigDecimal dataType = new BigDecimal(CATEGORY_DATA_TYPE_CREDIT);

		// コンボボックスの先頭行設定
		ComboBoxItems comboBlank = new ComboBoxItems();
		comboBlank.setValues("");
		comboBlank.setLabales("すべて");

		categoryList.add(comboBlank);

		// 分類マスタから検索結果取得
		List<ClassificationListForComboboxes> bean = depositListLogic
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
	 * 入金入力 ロジッククラスを設定します。
	 * @param depositListLogic 入金入力 ロジッククラス
	 */
	public void setDepositListLogic(final DepositListLogic depositListLogic) {
		this.depositListLogic = depositListLogic;
	}

	/**
	 * 仕訳伝票（入金伝票）検索画面ロジッククラスを設定します。
	 * @param depositListExcelDecorator 仕訳伝票（入金伝票）検索画面ロジッククラス
	 */
	public void setDepositListExcelDecorator(
			final DepositListExcelDecorator depositListExcelDecorator) {
		this.depositListExcelDecorator = depositListExcelDecorator;
	}
}
