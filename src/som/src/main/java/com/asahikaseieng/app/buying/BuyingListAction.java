/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 *
 */
package com.asahikaseieng.app.buying;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.buying.BuyingList;
import com.asahikaseieng.dao.nonentity.buying.BuyingPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListConditionForReport;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 仕入一覧 Actionクラス.
 * @author tosco
 * 
 */
public final class BuyingListAction extends AbstractSearchAction {
	/** 仕入一覧 ロジッククラス */
	private BuyingListLogic buyingListLogic;

	/** 仕入伝票出力ロジッククラス */
	private BuyingListExcelDecorator buyingListExcelDecorator;

	/** 帳票Excel出力クラス */
	private BuyingReportListExcelDecorator buyingReportListExcelDecorator;

	/**
	 * コンストラクタ.
	 */
	public BuyingListAction() {
		super();
	}

	/**
	 * 初期処理(メニューから遷移時)
	 * 
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

		// formを仕入一覧 Formクラスにキャスト
		BuyingListForm frm = (BuyingListForm) form;

		// 仕入区分コンボボックスの作成
		frm.setStockinDivisionCombo(buyingListLogic
				.createBuyingStockingDivisionCombobox(true));

		// 初期表示時は検索を行わない
		return mapping.findForward("success");
	}

	/**
	 * 帳票処理(検索画面の帳票(Excel)ボタン押下時)
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

		BuyingListForm frm = (BuyingListForm) form;

		/* Excel作成 */
		FileDownloadInfo info = buyingReportListExcelDecorator.createReport(frm
				.getReportCondition());
		/* セッションにセット */
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);

		/* ダウンロードフラグをONに */
		frm.setExcelReportDownloadFlg(true);
		return mapping.findForward("success");

	}

	/**
	 * 検索処理(検索ボタン押下時)
	 * 
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

		// formを仕入一覧 Formクラスにキャスト
		BuyingListForm frm = (BuyingListForm) form;

		/* 検索条件をセット */
		BuyingPagerCondition condition = searchSet(frm);

		/* 帳票(Excel)用に検索条件を保持 */
		BuyingListConditionForReport reportCondition = new BuyingListConditionForReport();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		return mapping.findForward("success");
	}

	/**
	 * フォームに表示されている項目のクリア処理.
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

		if (getLog().isDebugEnabled()) {
			getLog().debug("clear.");
		}

		// formを仕入一覧 Formクラスにキャスト
		BuyingListForm frm = (BuyingListForm) form;

		// フォームのclearメソッドを使用し、フォームをクリアする
		frm.clear();

		return mapping.findForward("success");
	}

	/**
	 * 仕入伝票出力処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward buyingReport(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("order.");
		}
		// 担当者コードを取得
		String tantoNm = getLoginInfo(request).getTantoNm();

		// 担当者コードを取得
		String tantoCd = getLoginInfo(request).getTantoCd();

		// formを仕入一覧 Formクラスにキャスト
		BuyingListForm frm = (BuyingListForm) form;

		ArrayList<String> buyingList = new ArrayList<String>();

		// チェックした仕入伝票番号リスト作成
		for (BuyingList bean : frm.getSearchList()) {
			if (!bean.getSlipBuyingCheckBox()) {
				continue;
			} else {
				// 処理を行う仕入番号リストを作成
				buyingList.add(bean.getSlipNo());
			}
		}

		// 伝票発行フラグ更新処理
		buyingListLogic.updateBuying(tantoCd, buyingList);

		// 仕入伝票発行
		FileDownloadInfo info = null;
		info = buyingListExcelDecorator.createReport(buyingList, tantoNm,
			AecDateUtils.getCurrentTimestamp(), frm.getSrhStockingDateFrom(),
			frm.getSrhStockingDateTo(), request);
		request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
			info);
		frm.setExcelDownloadFlg(true);

		/* 検索条件をセット */
		searchSet(frm);

		return mapping.findForward("success");
	}

	/**
	 * 検索条件をセット
	 * @param frm BuyingListForm
	 * @throws NoDataException NoDataException
	 */
	private BuyingPagerCondition searchSet(final BuyingListForm frm)
			throws NoDataException {

		// 一覧検索結果をクリア
		frm.setSearchList(new ArrayList<BuyingList>());

		// 検索条件の取得
		BuyingPagerCondition condition = (BuyingPagerCondition) frm.getPager()
				.getPagerCondition();

		// 検索条件をセット
		condition.setSrhChargeOrganizationCd(frm.getSrhChargeOrganizationCd());
		condition.setSrhOrganizationCd(frm.getSrhOrganizationCd());
		condition.setSrhTantoCd(frm.getSrhTantoCd());
		condition.setSrhVenderCd(frm.getSrhVenderCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhCategoryDivision(frm.getSrhCategoryDivision());
		condition.setSrhStockingDateFrom(frm.getSrhStockingDateFrom());
		condition.setSrhStockingDateTo(frm.getSrhStockingDateTo());
		condition.setSrhSlipNo(frm.getSrhSlipNo());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhCancelCheck(frm.isSrhCancelCheck());
		condition.setSrhStockingStatus(frm.getSrhStockingStatus());
		condition.setSrhMonthlyCheck(frm.isSrhMonthlyCheck());
		condition.setSrhSlipIssueDivision(frm.getSrhSlipIssueDivision());

		// セットした検索条件を使用し、仕入一覧を検索
		frm.setSearchList(buyingListLogic.getSearchList(condition));

		return condition;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 仕入一覧 ロジッククラスを設定します。
	 * @param buyingListLogic 仕入一覧 ロジッククラス
	 */
	public void setBuyingListLogic(final BuyingListLogic buyingListLogic) {
		this.buyingListLogic = buyingListLogic;
	}

	/**
	 * 仕入伝票出力ロジッククラスを設定します。
	 * @param buyingListExcelDecorator 仕入伝票出力ロジッククラス
	 */
	public void setBuyingListExcelDecorator(
			final BuyingListExcelDecorator buyingListExcelDecorator) {
		this.buyingListExcelDecorator = buyingListExcelDecorator;
	}

	/**
	 * buyingReportListExcelDecoratorを設定します。
	 * @param buyingReportListExcelDecorator buyingReportListExcelDecorator
	 */
	public void setBuyingReportListExcelDecorator(
			final BuyingReportListExcelDecorator buyingReportListExcelDecorator) {
		this.buyingReportListExcelDecorator = buyingReportListExcelDecorator;
	}
}
