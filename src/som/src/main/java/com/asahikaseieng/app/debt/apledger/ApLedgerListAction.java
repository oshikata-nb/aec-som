/*
 * Created on 2008/07/09
 *
 * $copyright$
 */
package com.asahikaseieng.app.debt.apledger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.master.organization.OrganizationDetailLogic;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerList;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerPagerCondition;
import com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApLedgerConditionForReport;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 買掛元帳 Actionクラス.
 * @author tosco
 */
public final class ApLedgerListAction extends AbstractSearchAction {

	private ApLedgerListLogic apLedgerListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private ApLedgerListExcelDecorator apLedgerListExcelDecorator;

	/* 対象区分 本締め */
	private static final BigDecimal NORMAL = new BigDecimal("0");

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ApLedgerListAction() {
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

		ApLedgerListForm frm = (ApLedgerListForm) form;

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			/* 初期表示：通常処理分 */
			frm.setSrhNormalFlg(true);

			try {
				/* 部署マスタ検索 */
				OrganizationDetail bumonDate = organizationDetailLogic
						.getDetailEntity(frm.getSrhOrganizationCd());
				if (bumonDate != null) {
					/* 初期表示：部署名称 */
					frm.setSrhOrganizationName(bumonDate.getOrganizationName());
				}

				/* 初期表示：対象年月 */
				ApRollback bean = apLedgerListLogic.getSearch(frm
						.getSrhOrganizationCd());
				frm.setSrhTargetMonth(bean.getClosingMonth());

			} catch (NoDataException e) {
				// 取得できない場合でもエラーにしない
			}
		}
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

		ApLedgerListForm frm = (ApLedgerListForm) form;

		/* 日付エラー */
		if (!checkYearMonth(frm.getSrhTargetMonth())) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* クリア */
		frm.setSearchList(new ArrayList<ApLedgerList>());

		/* 検索条件を取得 */
		ApLedgerPagerCondition condition = (ApLedgerPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhVenderName1(frm.getSrhVenderName1());
		condition.setSrhTargetMonth(frm.getSrhTargetMonth());

		// 対象区分
		if (frm.getSrhNormalTemp().equals(NORMAL)) {
			frm.setSrhNormalFlg(true);
			frm.setSrhTempClosingFlg(false);
			condition.setSrhNormalFlg(true);
			condition.setSrhTempClosingFlg(false);
		} else {
			frm.setSrhNormalFlg(false);
			frm.setSrhTempClosingFlg(true);
			condition.setSrhNormalFlg(false);
			condition.setSrhTempClosingFlg(true);
		}

		List<ApLedgerList> list = apLedgerListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			// list.get(i).setStrBalanceForward(
			// checker.format(SIKINGAKU, list.get(i).getBalanceForward())); /*
			// 前月残 */
			// list.get(i).setStrOtherPayment(
			// checker.format(SIKINGAKU, list.get(i).getOtherPayment())); /*
			// 支払・その他計 */
			list.get(i).setStrBalanceForward(
				checker.format(KINGAKU, list.get(i).getBalanceForward())); /* 前月残 */
			list.get(i).setStrOtherPayment(
				checker.format(KINGAKU, list.get(i).getOtherPayment())); /* 支払・その他計 */
			list.get(i).setStrStockingAmount(
				checker.format(SIKINGAKU, list.get(i).getStockingAmount())); /* 当月仕入高 */
			// list.get(i).setStrOtherStocking(
			// checker.format(SIKINGAKU, list.get(i).getOtherStocking())); /*
			// その他計 */
			list.get(i).setStrOtherStocking(
				checker.format(KINGAKU, list.get(i).getOtherStocking())); /* その他計 */
			list.get(i).setStrTaxAmount(
				checker.format(TAX_AMOUNT, list.get(i).getTaxAmount())); /* 消費税 */
			list.get(i).setStrAccountPayableBreakdouwn(
				checker.format(SIKINGAKU, list.get(i)
						.getAccountPayableBreakdouwn())); /* 買掛残高 */
			list.get(i)
					.setStrArrearageBreakdouwn(
						checker.format(SIKINGAKU, list.get(i)
								.getArrearageBreakdouwn())); /* 未払金残高 */
			// list.get(i).setStrPayableAmount(
			// checker.format(SIKINGAKU, list.get(i).getPayableAmount())); /*
			// 当月残 */
			list.get(i).setStrPayableAmount(
				checker.format(KINGAKU, list.get(i).getPayableAmount())); /* 当月残 */
		}

		frm.setSearchList(list);

		/* 帳票用に検索条件を保持 */
		RepApLedgerConditionForReport reportCondition = new RepApLedgerConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

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

		ApLedgerListForm frm = (ApLedgerListForm) form;

		/* クリア */
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * 印刷処理
	 * 
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

		ApLedgerListForm listForm = (ApLedgerListForm) form;

		// ログインユーザー取得
		String loginUserId = getLoginInfo(request).getTantoCd();

		FileDownloadInfo info = null;

		String targetMonth = listForm.getSrhTargetMonth();
		String targetKbn = null;

		if (listForm.isSrhNormalFlg() && !listForm.isSrhTempClosingFlg()) {
			targetKbn = "0"; // 通常
		} else if (!listForm.isSrhNormalFlg() && listForm.isSrhTempClosingFlg()) {
			targetKbn = "1"; // 仮締め
		} else if (listForm.isSrhNormalFlg() && listForm.isSrhTempClosingFlg()) {
			targetKbn = "2"; // 両方
		}

		/* 買掛元帳出力処理 */
		info = apLedgerListExcelDecorator.createReport(listForm
				.getReportCondition(), targetKbn, targetMonth, loginUserId,
			null, request);

		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		// ExcelダウンロードフラグＯＮ
		listForm.setExcelDownloadFlg(true);

		return mapping.findForward("success");

	}

	/**
	 * 年月チェック
	 * @param strDate 対象年月
	 * @return チェック結果（問題なし：true、問題あり：false）
	 */
	private boolean checkYearMonth(final String strDate) {
		String targetMonth = strDate;
		if (targetMonth.length() == 7) {
			// 日付の追加
			targetMonth = targetMonth.concat("/01");
			// 日付フォーマット（不正な日付の場合nullが返る）
			if (AecDateUtils.getTimestampYmdFormat(targetMonth) == null) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	/* -------------------- setter -------------------- */

	/**
	 * ApLedgerListLogicを設定します。
	 * @param apLedgerListLogic apLedgerListLogic
	 */
	public void setApLedgerListLogic(final ApLedgerListLogic apLedgerListLogic) {
		this.apLedgerListLogic = apLedgerListLogic;
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
	 * 買掛元帳検索画面ロジッククラスを設定します。
	 * @param apLedgerListExcelDecorator 買掛元帳検索画面ロジッククラス
	 */
	public void setApLedgerListExcelDecorator(
			final ApLedgerListExcelDecorator apLedgerListExcelDecorator) {
		this.apLedgerListExcelDecorator = apLedgerListExcelDecorator;
	}

}
