/*
 * Created on 2008/07/01
 *
 * $copyright$
 */
package com.asahikaseieng.app.debt.apbalance;

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
import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalanceList;
import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListConditionForReport;
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
 * 買掛残高一覧表作成処理 Actionクラス.
 * @author tosco
 */
public final class ApBalanceListAction extends AbstractSearchAction {

	private ApBalanceListLogic apBalanceListLogic;

	private OrganizationDetailLogic organizationDetailLogic;

	private ApBalanceListExcelDecorator apBalanceListExcelDecorator;

	/* 仕入金額 */
	private static final String SIKINGAKU = "SIKINGAKU";

	/* 金額 */
	private static final String KINGAKU = "KINGAKU";

	/* 消費税 */
	private static final String TAX_AMOUNT = "TAX_AMOUNT";

	/**
	 * コンストラクタ.
	 */
	public ApBalanceListAction() {
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

		ApBalanceListForm frm = (ApBalanceListForm) form;

		/* 初期表示：出力区分 */
		frm.setSrhAccountPayableDivi(true);

		/* 初期表示：部署 */
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginInfo loginInfo = (LoginInfo) session
					.getAttribute(Constants.LOGIN_KEY);

			/* 初期表示：部署コード */
			frm.setSrhOrganizationCd(loginInfo.getOrganizationCd());

			try {
				/* 部署マスタ検索 */
				OrganizationDetail bumonDate = organizationDetailLogic
						.getDetailEntity(frm.getSrhOrganizationCd());
				if (bumonDate != null) {
					/* 初期表示：部署名称 */
					frm.setSrhOrganizationName(bumonDate.getOrganizationName());
				}

				/* 初期表示：対象年月 */
				ApRollback bean = apBalanceListLogic.getSearch(frm
						.getSrhOrganizationCd());
				frm.setSrhTargetMonth(bean.getClosingMonth());

			} catch (NoDataException e) {
				// 取得できない場合でもエラーにしない
			}
		}

		// 初期値:対象区分＝本締め(0)
		frm.setSrhTargetDivision("0");

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

		ApBalanceListForm frm = (ApBalanceListForm) form;

		/* クリア */
		frm.setSearchList(new ArrayList<ApBalanceList>());

		/* 日付エラー */
		if (!checkYearMonth(frm.getSrhTargetMonth())) {
			saveErrorWithArgs(request, "errors.yyyymm", "item.srhTargetMonth");
			return mapping.findForward("success");
		}

		/* 検索条件を取得 */
		ApBalancePagerCondition condition = (ApBalancePagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		// 部署コード
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		// 担当者コード
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		// 担当者名称
		condition.setSrhTantoNm(frm.getSrhTantoNm());
		// 支払先コード
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		// 支払先名称
		condition.setSrhVenderName1(frm.getSrhVenderName1());
		// 対象年月
		condition.setSrhTargetMonth(frm.getSrhTargetMonth());
		// 出力区分（買掛残有無）
		condition.setSrhAccountPayableDivi(frm.isSrhAccountPayableDivi());
		// 出力区分（未払金残有無）
		condition.setSrhArrearageDivi(frm.isSrhArrearageDivi());
		// 対象区分
		condition.setSrhTargetDivision(frm.getSrhTargetDivision());

		/* 帳票(Excel)用に検索条件を保持 */
		ApBalanceListConditionForReport reportCondition = new ApBalanceListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setCondition(reportCondition);

		List<ApBalanceList> list = apBalanceListLogic.getSearchList(condition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		for (int i = 0; i < list.size(); i++) {
			/* 数値文字列に変換 */
			// list.get(i).setStrBalanceForward(
			// checker.format(SIKINGAKU, list.get(i).getBalanceForward())); /*
			// 前月残 */
			list.get(i).setStrBalanceForward(
				checker.format(KINGAKU, list.get(i).getBalanceForward())); /* 前月残 */
			// list.get(i).setStrTotalPayment(
			// checker.format(SIKINGAKU, list.get(i).getTotalPayment())); /*
			// 支払・その他計 */
			list.get(i).setStrTotalPayment(
				checker.format(KINGAKU, list.get(i).getTotalPayment())); /* 支払・その他計 */
			list.get(i).setStrStockingAmount(
				checker.format(SIKINGAKU, list.get(i).getStockingAmount())); /* 今回仕入額 */
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

		ApBalanceListForm frm = (ApBalanceListForm) form;

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

		ApBalanceListForm frm = (ApBalanceListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = apBalanceListExcelDecorator.createReport(frm
				.getCondition());

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

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
	 * ApBalanceListLogicを設定します。
	 * 
	 * @param apBalanceListLogic apBalanceListLogic
	 */
	public void setApBalanceListLogic(
			final ApBalanceListLogic apBalanceListLogic) {
		this.apBalanceListLogic = apBalanceListLogic;
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
	 * apBalanceListExcelDecoratorを設定します。
	 * @param apBalanceListExcelDecorator apBalanceListExcelDecorator
	 */
	public void setApBalanceListExcelDecorator(
			final ApBalanceListExcelDecorator apBalanceListExcelDecorator) {
		this.apBalanceListExcelDecorator = apBalanceListExcelDecorator;
	}
}
