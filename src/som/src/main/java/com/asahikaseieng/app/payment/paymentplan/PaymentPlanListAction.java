/*
 * Created on 2008/07/09
 *
 * $copyright$
 */
package com.asahikaseieng.app.payment.paymentplan;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanList;
import com.asahikaseieng.dao.nonentity.payment.paymentplan.PaymentPlanPagerCondition;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 支払予定一覧表 Actionクラス.
 * @author tosco
 */
public final class PaymentPlanListAction extends AbstractSearchAction {

	private PaymentPlanListLogic paymentPlanListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private PaymentPlanListExcelDecorator paymentPlanListExcelDecorator;

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/**
	 * コンストラクタ.
	 */
	public PaymentPlanListAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		PaymentPlanListForm frm = (PaymentPlanListForm) form;

		/* 初期表示：支払予定日FROM */
		// システム日付
		Calendar sysCal = Calendar.getInstance();
		// 日付を１日プラス
		sysCal.add(Calendar.DATE, 1);
		// 日付を表示用に変換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(sysCal.getTime());
		// 支払予定日FROMを設定
		frm.setSrhPaymentDateFrom(strDate);

		/* 支払分類コンボの設定 */
		frm.setSrhPaymentList(getCategoryComboBox());

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			/* 部署マスタ検索 */
			OrganizationDetail bumonDate = organizationDetailLogic
					.getDetailEntity(frm.getSrhOrganizationCd());
			if (bumonDate != null) {
				/* 初期表示：部署名称 */
				frm.setSrhOrganizationName(bumonDate.getOrganizationName());
			} else {
				frm.setSrhOrganizationName("");
			}
		}

		/* 初期検索無し */
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

		PaymentPlanListForm frm = (PaymentPlanListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<PaymentPlanList>());

		/* 検索条件を取得 */
		PaymentPlanPagerCondition condition = (PaymentPlanPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 部署コード
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		// 担当者コード
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		// 担当者名称
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		// 請求先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		// 請求先名称
		condition.setSrhVenderName(frm.getSrhVenderName());
		// 銀行コード
		condition.setSrhBankCd(frm.getSrhBankCd());
		// 銀行名称
		condition.setSrhBankName(frm.getSrhBankName());
		// 出力順
		condition.setSrhOutputDivision(frm.getSrhOutputDivision());
		// 支払分類
		condition.setSrhPaymentDivision(frm.getSrhPaymentDivision());
		// 支払予定日From
		Date dateFrom = null;
		if (!StringUtils.isEmpty(frm.getSrhPaymentDateFrom())) {
			dateFrom = this.toCalendar(frm.getSrhPaymentDateFrom()).getTime();
		}
		condition.setSrhPaymentDateFrom(dateFrom);
		// 支払予定日To
		Date dateTo = null;
		if (!StringUtils.isEmpty(frm.getSrhPaymentDateTo())) {
			dateTo = this.toCalendar(frm.getSrhPaymentDateTo()).getTime();
		}
		condition.setSrhPaymentDateTo(dateTo);

		/* 帳票(Excel)用に検索条件を保持 */
		PaymentPlanListConditionForReport reportCondition = new PaymentPlanListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<PaymentPlanList> list = paymentPlanListLogic
				.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			// list.get(i).setStrPayableAmountBalance(
			// checker
			// .format(SIKINGAKU, list.get(i)
			// .getPayableAmountBalance())); /* 支払額 */
			list.get(i).setStrPayableAmountBalance(
				checker.format(KINGAKU, list.get(i).getPayableAmountBalance())); /* 支払額 */
		}

		frm.setSearchList(list);

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

		PaymentPlanListForm frm = (PaymentPlanListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * String型の日付(yyyy/mm/dd)をCalendar型に変換する
	 * @param strDate String型の日付(yyyy/mm/dd)
	 * @return Date
	 */
	private Calendar toCalendar(final String strDate) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);

		int yyyy = Integer.parseInt(strDate.substring(0, 4));
		int mm = Integer.parseInt(strDate.substring(5, 7));
		int dd = Integer.parseInt(strDate.substring(8, 10));
		cal.clear();
		cal.set(yyyy, mm - 1, dd);

		return cal;
	}

	/**
	 * 入金分類コード＋分類名称のコンボボックス取得
	 * 
	 * @return List<ComboBoxItems> 入金分類のコンボボックス
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	private List<ComboBoxItems> getCategoryComboBox() throws NoDataException {

		List<ComboBoxItems> categoryList = new ArrayList<ComboBoxItems>();
		// 種別は'入金'
		BigDecimal dataType = new BigDecimal("4");

		// 入金分類一覧取得
		List<ClassificationListForComboboxes> bean = paymentPlanListLogic
				.getClassificationList(dataType);

		// １行目はすべて
		ComboBoxItems comboEmp = new ComboBoxItems();
		comboEmp.setValues("");
		comboEmp.setLabales("すべて");
		categoryList.add(comboEmp);
		// 分類コード、分類名称を取得して配列に設定
		for (ClassificationListForComboboxes list : bean) {
			ComboBoxItems combo = new ComboBoxItems();
			// 分類コード設定(Value値)
			combo.setValues(list.getCategoryDivision());
			// 科目名称設定(ラベル)
			combo.setLabales(list.getCategoryName());
			categoryList.add(combo);
		}

		return categoryList;
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
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		PaymentPlanListForm frm = (PaymentPlanListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = paymentPlanListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

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

		PaymentPlanListForm frm = (PaymentPlanListForm) form;
		ArrayList<String> creditNoList = new ArrayList<String>();

		// ログインユーザー取得
		String tantoNm = getLoginInfo(request).getTantoNm();
		// ログインユーザー部署取得
		String loginUserOrganizationNm = getLoginInfo(request)
				.getOrganizationName();

		for (PaymentPlanList bean : frm.getSearchList()) {

			if (!bean.getChecked()) {
				continue;
			} else {
				// 処理を行う支払番号リストを作成
				creditNoList.add(bean.getPaymentNo());
			}
		}

		if (!creditNoList.isEmpty()) {

			FileDownloadInfo info = null;

			/* お支払通知書出力処理 */
			info = paymentPlanListExcelDecorator.createReportPaymentInform(
				creditNoList, tantoNm, loginUserOrganizationNm, AecDateUtils
						.getCurrentTimestamp(), request.getRemoteAddr());

			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
			// ExcelダウンロードフラグＯＮ
			frm.setExcelDownloadFlg(true);
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentPlanListLogicを設定します。
	 * 
	 * @param paymentPlanListLogic paymentPlanListLogic
	 */
	public void setPaymentPlanListLogic(
			final PaymentPlanListLogic paymentPlanListLogic) {
		this.paymentPlanListLogic = paymentPlanListLogic;
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
	 * paymentPlanListExcelDecoratorを設定します。
	 * @param paymentPlanListExcelDecorator paymentPlanListExcelDecorator
	 */
	public void setPaymentPlanListExcelDecorator(
			final PaymentPlanListExcelDecorator paymentPlanListExcelDecorator) {
		this.paymentPlanListExcelDecorator = paymentPlanListExcelDecorator;
	}
}
