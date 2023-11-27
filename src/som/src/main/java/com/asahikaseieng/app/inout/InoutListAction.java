/*
 * Created on 2008/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordList;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordListPagerCondition;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordReportCondition;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受払照会 Actionクラス
 * @author tanaka
 */
public final class InoutListAction extends AbstractSearchAction {

	private InoutListLogic inoutListLogic;

	private InoutListExcelDecorator inoutListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public InoutListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		InoutListForm frm = (InoutListForm) form;

		if (StringUtils.isEmpty(frm.getSrhItemCd())
				&& StringUtils.isEmpty(frm.getSrhLocationCd())) {
			/* エラーメッセージ */
			saveError(request, "errors.empty.item.location");
			return mapping.findForward("success");
		}

		/* クリア */
		frm.setSearchList(new ArrayList<InoutRecordList>());

		/* 検索条件を取得 */
		InoutRecordListPagerCondition condition = (InoutRecordListPagerCondition) frm
				.getPager().getPagerCondition();

		frm.setSrhInoutDateFrom(AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhInoutDateFrom()));
		frm.setSrhInoutDateTo(AecDateUtils.getTimestampYmdFormat(frm
				.getStrSrhInoutDateTo()));

		/* 検索条件をセット */
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhInoutDivision(frm.getSrhInoutDivision());
		condition.setSrhInoutDateFrom(frm.getSrhInoutDateFrom());
		condition.setSrhInoutDateTo(frm.getSrhInoutDateTo());

		/* 明細取得 */
		frm.setSearchList(inoutListLogic.getList(condition));

		/* 帳票出力用検索条件 */
		InoutRecordReportCondition reportCondition = new InoutRecordReportCondition();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		String link = null;

		/* 数値桁数チェック部品呼び出し */
		// CheckDigitUtilsLogic checker = CheckDigitUtil
		// .getCheckDigitUtils(request);
		/* 利用可能数 */
		for (int i = 0; i < frm.getSearchList().size(); i++) {
			link = null;

			/* 数値文字列に変換 */
			// frm.getSearchList().get(i).setStrInoutQty(
			// checker.format(frm.getSearchList().get(i)
			// .getUnitOfOperationManagement(), frm.getSearchList()
			// .get(i).getInoutQty()));
			frm.getSearchList().get(i).setStrInoutQty(
				AecNumberUtils.decimalFormatEx(frm.getSearchList().get(i)
						.getInoutQty()));

			/* 日付文字列に変換 */
			frm.getSearchList().get(i).setStrInoutDate(
				AecDateUtils.dateFormat(frm.getSearchList().get(i)
						.getInoutDate(), "yyyy/MM/dd"));

			if (frm.getSearchList().get(i).getOderNo() != null) {
				/* 受払区分によって、飛ばす画面を分ける */
				if (frm.getSearchList().get(i).getInoutDivision().equals(
					new BigDecimal("1"))
						|| frm.getSearchList().get(i).getInoutDivision()
								.equals(new BigDecimal("2"))) {
					if (frm.getSearchList().get(i).getOderLineNo() != null) {
						if (frm.getSearchList().get(i).getDirectionDivision() != null) {
							if (frm.getSearchList().get(i).getDirectionStatus()
									.compareTo(new BigDecimal("3")) <= 0) {
								link = "/DirectionHeader.do?op=init&directionDivision="
										+ frm.getSearchList().get(i)
												.getDirectionDivision()
												.toString() + "&directionNo=";
							} else {
								link = "/RdirectionHeader.do?op=init&directionDivision="
										+ frm.getSearchList().get(i)
												.getDirectionDivision()
												.toString() + "&directionNo=";
							}
						}
					}
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("3"))) {
					link = "/PurchaseDetail.do?op=init&buySubcontractOrderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("4"))) {
					link = "/OrderDetail.do?op=init&orderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("10"))) {
					link = "/InventoryList.do?op=search&srhLocationCd="
							+ frm.getSearchList().get(i).getLocationCd()
							+ "&srhItemCd="
							+ frm.getSearchList().get(i).getItemCd()
							+ "&srhLink=1" + "&srhOderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("11"))) {
					link = "/InquiryInputList.do?op=search&strSrhCountDate="
							+ frm.getSearchList().get(i).getStrInoutDate()
							+ "&srhLocationCd="
							+ frm.getSearchList().get(i).getLocationCd()
							+ "&srhItemCd="
							+ frm.getSearchList().get(i).getItemCd()
							+ "&srhCountDivision="
							+ frm.getSearchList().get(i).getCountDivision()
							+ "&srhLink=1" + "&srhOderNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("12"))) {
					link = "/SalesDetailStandard.do?op=search&salesNo=";
				} else if (frm.getSearchList().get(i).getInoutDivision()
						.equals(new BigDecimal("13"))) {
					link = "/SalesDetailKeep.do?op=search&salesNo=";
				}
			}

			frm.getSearchList().get(i).setLink(link);
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
	public ActionForward report(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("report.");
		}

		InoutListForm frm = (InoutListForm) form;

		List<InoutRecordListForReport> list = inoutListLogic
				.getListForReport(frm.getReportCondition());

		/* Excel作成 */
		FileDownloadInfo info = inoutListExcelDecorator.createReport(list);

		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutListLogicを設定します。
	 * @param inoutListLogic salesListLogic
	 */
	public void setInoutListLogic(final InoutListLogic inoutListLogic) {
		this.inoutListLogic = inoutListLogic;
	}

	/**
	 * 帳票出力ロジックを設定します。
	 * @param inoutListExcelDecorator 帳票出力ロジック
	 */
	public void setInoutListExcelDecorator(
			final InoutListExcelDecorator inoutListExcelDecorator) {
		this.inoutListExcelDecorator = inoutListExcelDecorator;
	}
}
