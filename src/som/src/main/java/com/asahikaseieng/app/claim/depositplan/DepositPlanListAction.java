/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.claim.depositplan;

import java.math.BigDecimal;
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

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanList;
import com.asahikaseieng.dao.nonentity.claim.depositplan.DepositPlanListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.sort.ClassificationComparator;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入金予定一覧表作成処理 Actionクラス.
 * @author tosco
 */
public final class DepositPlanListAction extends AbstractSearchAction {

	private DepositPlanListLogic depositPlanListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private DepositPlanListExcelDecorator depositPlanListExcelDecorator;

	/* 売上金額 */
	private static final String URKINGAKU = "URKINGAKU";

	/**
	 * コンストラクタ.
	 */
	public DepositPlanListAction() {
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

		DepositPlanListForm frm = (DepositPlanListForm) form;

		/* 初期表示：入金予定日FROM */
		// システム日付
		Calendar sysCal = Calendar.getInstance();
		// 日付を１日プラス
		sysCal.add(Calendar.DATE, 1);
		// 日付を表示用に変換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = sdf.format(sysCal.getTime());
		// 請求予定日FROMを設定
		frm.setSrhCreditDateFrom(strDate);
		// 入金予定日FROMを設定
		frm.setSrhCreditScheduledDateFrom(strDate);

		/* 入金分類コンボの設定 */
		frm.setSrhCreditList(getCategoryComboBox());

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

		DepositPlanListForm frm = (DepositPlanListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<DepositPlanList>());

		/* 検索条件を取得 */
		DepositPlanListPagerCondition condition = (DepositPlanListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 部署コード
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		// 担当者コード
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		// 請求先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		// 銀行マスタコード
		condition.setSrhBankMasterCd(frm.getSrhBankMasterCd());
		// 出力順
		condition.setSrhOutputDivision(frm.getSrhOutputDivision());
		// 入金分類
		condition.setSrhCreditDivision(frm.getSrhCreditDivision());
		// 請求予定日From
		Date dateFrom = null;
		if (!StringUtils.isEmpty(frm.getSrhCreditDateFrom())) {
			dateFrom = this.toCalendar(frm.getSrhCreditDateFrom()).getTime();
		}
		condition.setSrhCreditDateFrom(dateFrom);
		// 請求予定日To
		Date dateTo = null;
		if (!StringUtils.isEmpty(frm.getSrhCreditDateTo())) {
			dateTo = this.toCalendar(frm.getSrhCreditDateTo()).getTime();
		}
		condition.setSrhCreditDateTo(dateTo);
		// 入金予定日From
		Date scheduledDateFrom = null;
		if (!StringUtils.isEmpty(frm.getSrhCreditScheduledDateFrom())) {
			scheduledDateFrom = this.toCalendar(
				frm.getSrhCreditScheduledDateFrom()).getTime();
		}
		condition.setSrhCreditScheduledDateFrom(scheduledDateFrom);
		// 入金予定日To
		Date scheduledDateTo = null;
		if (!StringUtils.isEmpty(frm.getSrhCreditScheduledDateTo())) {
			scheduledDateTo = this
					.toCalendar(frm.getSrhCreditScheduledDateTo()).getTime();
		}
		condition.setSrhCreditScheduledDateTo(scheduledDateTo);

		/* 帳票(Excel)用に検索条件を保持 */
		DepositPlanListConditionForReport reportCondition = new DepositPlanListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		frm.setSearchList(depositPlanListLogic.getSearchList(condition));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			/* 数値文字列に変換 */
			frm.getSearchList().get(i).setStrClaimAmountBalance(
				checker.format(URKINGAKU, frm.getSearchList().get(i)
						.getClaimAmountBalance()));
		}

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

		DepositPlanListForm frm = (DepositPlanListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
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

		DepositPlanListForm frm = (DepositPlanListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = depositPlanListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

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
	@SuppressWarnings("unchecked")
	private List<ComboBoxItems> getCategoryComboBox() throws NoDataException {

		List<ComboBoxItems> categoryList = new ArrayList<ComboBoxItems>();
		// 種別は'入金'
		BigDecimal dataType = new BigDecimal("2");

		// 入金分類一覧取得
		List<ClassificationListForComboboxes> bean = depositPlanListLogic
				.getClassificationList(dataType);

		/* 分類コード順のソート */
		Collections.sort(bean, new ClassificationComparator(
				ClassificationComparator.ASC));

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

	/* -------------------- setter -------------------- */

	/**
	 * depositPlanListLogicを設定します。
	 * 
	 * @param depositPlanListLogic depositPlanListLogic
	 */
	public void setDepositPlanListLogic(
			final DepositPlanListLogic depositPlanListLogic) {
		this.depositPlanListLogic = depositPlanListLogic;
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
	 * depositPlanListExcelDecoratorを設定します。
	 * @param depositPlanListExcelDecorator depositPlanListExcelDecorator
	 */
	public void setDepositPlanListExcelDecorator(
			final DepositPlanListExcelDecorator depositPlanListExcelDecorator) {
		this.depositPlanListExcelDecorator = depositPlanListExcelDecorator;
	}
}
