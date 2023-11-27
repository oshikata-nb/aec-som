/*
 * Created on 2007/12/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

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
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryList;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryListForReportCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReport;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecTextUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 在庫照会一覧 Actionクラス.
 * @author tanaka
 */
public final class InventoryListAction extends AbstractSearchAction {

	private InventoryListLogic inventoryListLogic;

	private InventoryListExcelDecorator inventoryListExcelDecorator;

	private static final String KG_CD = "1";

	/**
	 * コンストラクタ.
	 */
	public InventoryListAction() {
		super();
	}

	/**
	 * 初期処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		/* 初期検索無し */
		return mapping.findForward("success");
	}

	/**
	 * 検索処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("search.");
		}

		InventoryListForm frm = (InventoryListForm) form;

		/* ロケーション再検索 */
		if (frm.getOp().equals("search") && frm.getIndexVal() != -1) {
			/* ロケーションコード */
			frm.setSrhLocationCd(frm.getSearchList().get(frm.getIndexVal())
					.getLocationCd());

			/* ロケーション名称 */
			frm.setSrhLocationName(frm.getSearchList().get(frm.getIndexVal())
					.getLocationName());
		}

		/* クリア */
		frm.setSearchList(new ArrayList<InventoryList>());

		String link = frm.getSrhLink();

		if (StringUtils.isEmpty(link)) {
			link = "";
		}

		/* 品目検索 */
		InventoryItemQueueDetail beanItemQueue = inventoryListLogic
				.getItemQueueEntity(frm.getSrhItemCd());

		/* リンク先から呼ばれた場合 */
		if (link.equals("1")) {
			/* ロケーション検索 */
			LocationDetail beanLocation = inventoryListLogic
					.getLocationEntity(frm.getSrhLocationCd());

			if (beanLocation != null) {
				frm.setSrhLocationName(beanLocation.getLocationName());
			}

			if (beanItemQueue != null) {
				frm.setSrhItemName(beanItemQueue.getItemName());
			}
		}

		/* 検索条件を取得 */
		InventoryListPagerCondition condition = (InventoryListPagerCondition) frm
				.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setSrhLocationCd(frm.getSrhLocationCd());
		condition.setSrhItemCd(frm.getSrhItemCd());
		condition.setSrhOtherCompanyCd1(frm.getSrhOtherCompanyCd1());
		condition.setSrhLotNo(frm.getSrhLotNo());

		/* 在庫可能FLG取得 */
		LocationDetail bean = inventoryListLogic.getLocationEntity(frm
				.getSrhLocationCd());

		BigDecimal availableFlg = new BigDecimal("1");

		if (bean != null) {
			availableFlg = bean.getAvailableFlg();
		}

		condition.setSrhAvailableFlg(availableFlg);

		/* 帳票検索条件をセット */
		InventoryListForReportCondition reportCondition = new InventoryListForReportCondition();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		if (frm.getOp().equals("search")) {
			/* 在庫数量合計取得 */
			BigDecimal inventoryQty = inventoryListLogic.getTotalQty(condition
					.getSrhLocationCd(), condition.getSrhItemCd(), condition
					.getSrhOtherCompanyCd1(), condition.getSrhLotNo(),
				availableFlg);

			frm.setSrhInventoryQty(inventoryQty);
			// frm.setStrSrhInventoryQty(AecNumberUtils.decimalFormat(frm
			// .getSrhInventoryQty(), "#,##0"));
			if (beanItemQueue == null) {
				frm.setStrSrhInventoryQty(AecNumberUtils.decimalFormat(
					inventoryQty, "#,##0"));
			} else {
				frm.setStrSrhInventoryQty(checker.format(frm.getUnitDivision(),
					frm.getSrhInventoryQty()));
			}
		}

		/* 明細取得 */
		if (frm.getIndexVal() == -1) {
			/* 検索ボタン検索 */
			frm.setSearchList(inventoryListLogic.getList(condition));
		} else {
			/* ロケーション再検索 */
			frm.setSearchList(inventoryListLogic.getRelocationList(condition));
		}

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			if (frm.getSearchList().get(i).getInventoryQty() != null) {
				/* 数値文字列に変換 */
				frm.getSearchList().get(i).setStrPackQty(
					checker.format(frm.getSearchList().get(i)
							.getUnitOfOperationManagement(), frm
							.getSearchList().get(i).getPackQty()));
				frm.getSearchList().get(i).setStrFraction(
					checker.format(frm.getSearchList().get(i)
							.getUnitOfFractionManagement(), frm.getSearchList()
							.get(i).getFraction()));

				/* 総量の単位はKgなのでKg桁数設定をする */
				// frm.getSearchList().get(i).setStrInventoryQty(
				// checker.format(frm.getSearchList().get(i)
				// .getUnitOfOperationManagement(), frm
				// .getSearchList().get(i).getInventoryQty()));
				frm.getSearchList().get(i).setStrInventoryQty(
					checker.format(KG_CD, frm.getSearchList().get(i)
							.getInventoryQty()));
			}
		}

		return mapping.findForward("success");
	}

	/**
	 * 検索処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward relocation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("relocation.");
		}

		InventoryListForm frm = (InventoryListForm) form;

		/* ロケーションコード */
		frm.setSrhLocationCd(frm.getSearchList().get(frm.getIndexVal())
				.getLocationCd());

		/* ロケーション名称 */
		frm.setSrhLocationName(frm.getSearchList().get(frm.getIndexVal())
				.getLocationName());

		/* クリア */
		frm.setSearchList(new ArrayList<InventoryList>());

		/* 検索条件を取得 */
		InventoryListPagerCondition condition = (InventoryListPagerCondition) frm
				.getPager().getPagerCondition();

		condition.setCount(0);
		condition.setOffset(0);
		condition.setOverThreshold(false);

		/* 検索条件をセット */
		condition.setSrhLocationCd(frm.getSrhLocationCd());

		/* 在庫可能FLG取得 */
		LocationDetail bean = inventoryListLogic.getLocationEntity(frm
				.getSrhLocationCd());

		String locationCd = frm.getSrhLocationCd();
		String itemCd = null;
		String otherCompanyCd1 = null;
		String lotNo = null;
		BigDecimal availableFlg = new BigDecimal("1");

		if (bean != null) {
			availableFlg = bean.getAvailableFlg();
		} else if (condition.getSrhLotNo() != null) {
			condition.setSrhAvailableFlg(new BigDecimal("1"));
		}

		condition.setSrhAvailableFlg(availableFlg);

		if (!StringUtils.isEmpty(frm.getSrhItemCd())) {
			itemCd = AecTextUtils.likeFilter(frm.getSrhItemCd());
		}

		if (!StringUtils.isEmpty(frm.getSrhOtherCompanyCd1())) {
			otherCompanyCd1 = AecTextUtils.likeFilter(frm
					.getSrhOtherCompanyCd1());
		}

		if (!StringUtils.isEmpty(frm.getSrhLotNo())) {
			lotNo = AecTextUtils.likeFilter(frm.getSrhLotNo());
		}

		/* 在庫数量合計取得 */
		BigDecimal inventoryQty = inventoryListLogic.getReTotalQty(locationCd,
			itemCd, otherCompanyCd1, lotNo, availableFlg);

		frm.setSrhInventoryQty(inventoryQty);
		// frm.setStrSrhInventoryQty(AecNumberUtils.decimalFormat(frm
		// .getSrhInventoryQty(), "#,##0"));
		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		InventoryItemQueueDetail beanItemQueue = inventoryListLogic
				.getItemQueueEntity(itemCd);

		if (beanItemQueue == null) {
			frm.setStrSrhInventoryQty(AecNumberUtils.decimalFormat(
				inventoryQty, "#,##0"));
		} else {
			frm.setStrSrhInventoryQty(checker.format(frm.getUnitDivision(),
				inventoryQty));
		}

		/* 帳票検索条件をセット */
		InventoryListForReportCondition reportCondition = new InventoryListForReportCondition();
		IgnoreCaseBeanUtils.copyProperties(reportCondition, condition);
		frm.setReportCondition(reportCondition);

		/* 明細取得 */
		frm.setSearchList(inventoryListLogic.getRelocationList(condition));

		for (int i = 0; i < frm.getSearchList().size(); i++) {
			if (frm.getSearchList().get(i).getInventoryQty() != null) {
				/* 数値文字列に変換 */
				frm.getSearchList().get(i).setStrPackQty(
					checker.format(frm.getSearchList().get(i)
							.getUnitOfOperationManagement(), frm
							.getSearchList().get(i).getPackQty()));
				frm.getSearchList().get(i).setStrFraction(
					checker.format(frm.getSearchList().get(i)
							.getUnitOfFractionManagement(), frm.getSearchList()
							.get(i).getFraction()));

				/* 総量の単位はKgなのでKg桁数設定をする */
				// frm.getSearchList().get(i).setStrInventoryQty(
				// checker.format(frm.getSearchList().get(i)
				// .getUnitOfOperationManagement(), frm
				// .getSearchList().get(i).getInventoryQty()));
				frm.getSearchList().get(i).setStrInventoryQty(
					checker.format(KG_CD, frm.getSearchList().get(i)
							.getInventoryQty()));
			}
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

		InventoryListForm frm = (InventoryListForm) form;

		/* 在庫可能FLG取得 */
		LocationDetail bean = inventoryListLogic.getLocationEntity(frm
				.getReportCondition().getSrhLocationCd());

		if (bean != null) {
			frm.getReportCondition().setSrhAvailableFlg(bean.getAvailableFlg());
		} else if (frm.getReportCondition().getSrhLotNo() != null) {
			frm.getReportCondition().setSrhAvailableFlg(new BigDecimal("1"));
		}

		// ロケーションコードのみが検索条件 かつ 在庫可能フラグが0:不可能である場合
		if (isLocationOnly(frm.getReportCondition())) {

			// ロケーションインベントリーのデータを取得
			List<InventoryLocationListForReport> location = inventoryListLogic
					.getLocationListForReport(frm.getReportCondition());

			// 下位ロケのロケーションインベントリーのデータを取得
			List<InventoryLowerLocationListForReport> lowerLocation = inventoryListLogic
					.getLowerLocationListForReport(frm.getReportCondition());

			/* Excel作成 */
			FileDownloadInfo info = inventoryListExcelDecorator
					.createReportLocation(location, lowerLocation);
			/* セッションにセット */
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

			// ロケーションコードと品目のみが検索条件 かつ 在庫可能フラグが0:不可能である場合
		} else if (isLocationAndItem(frm.getReportCondition())) {

			// ロケーションインベントリーのデータを取得
			List<InventoryLocationListForReport> location = inventoryListLogic
					.getLocationListForReport(frm.getReportCondition());

			// 下位ロケのロケーションインベントリーのデータを取得
			List<InventoryLowerLocationListForReport> lowerLocation = inventoryListLogic
					.getLowerLocationListForReport(frm.getReportCondition());

			/* Excel作成 */
			FileDownloadInfo info = inventoryListExcelDecorator
					.createReportLocation(location, lowerLocation);
			/* セッションにセット */
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

		} else if (isAll(frm.getReportCondition())) {

			// ロケーションインベントリーのデータを取得
			List<InventoryLocationListForReport> location = inventoryListLogic
					.getLocationListForReport(frm.getReportCondition());

			// 下位ロケのロケーションインベントリーのデータを取得
			List<InventoryLowerLocationListForReport> lowerLocation = inventoryListLogic
					.getLowerLocationListForReport(frm.getReportCondition());

			// ロット在庫のデータを取得
			List<InventoryLotListForReport> lot = inventoryListLogic
					.getLotListForReport(frm.getReportCondition());

			/* Excel作成 */
			FileDownloadInfo info = inventoryListExcelDecorator
					.createReportAll(location, lowerLocation, lot);
			/* セッションにセット */
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);

		} else {

			List<InventoryLotListForReport> lot = inventoryListLogic
					.getLotListForReport(frm.getReportCondition());
			/* Excel作成 */
			FileDownloadInfo info = inventoryListExcelDecorator
					.createReportLot(lot);
			/* セッションにセット */
			request.getSession(false).setAttribute(Constants.DOWNLOAD_FILE_KEY,
				info);
		}

		/* ダウンロードフラグをONに */
		frm.setExcelDownloadFlg(true);

		return mapping.findForward("success");
	}

	/**
	 * 
	 * 全ての検索条件が無しかチェック
	 * @param condition
	 * @return
	 */
	private boolean isAll(final InventoryListForReportCondition condition) {

		if (condition.getSrhLocationCd() == null
				|| condition.getSrhLocationCd().equals("")) {

			if ((condition.getSrhItemCd() == null || condition.getSrhItemCd()
					.equals(""))
					&& (condition.getSrhOtherCompanyCd1() == null || condition
							.getSrhOtherCompanyCd1().equals(""))) {
				if (condition.getSrhLotNo() == null
						|| condition.getSrhLotNo().equals("")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * ロケーションと品目のみ取得する条件であるかチェック
	 * @param condition
	 * @return
	 */
	private boolean isLocationAndItem(
			final InventoryListForReportCondition condition) {

		if (condition.getSrhLocationCd() != null
				|| !condition.getSrhLocationCd().equals("")) {

			if (condition.getSrhItemCd() == null
					|| condition.getSrhItemCd().equals("")
					|| condition.getSrhOtherCompanyCd1() == null
					|| condition.getSrhOtherCompanyCd1().equals("")) {
				if (condition.getSrhLotNo() == null
						|| condition.getSrhLotNo().equals("")) {
					if (condition.getSrhAvailableFlg() == null
							|| condition.getSrhAvailableFlg().equals(
								BigDecimal.ZERO)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * ロケーションデータのみ取得する条件であるかチェック
	 * @param condition
	 * @return
	 */
	private boolean isLocationOnly(
			final InventoryListForReportCondition condition) {

		if (condition.getSrhLocationCd() != null
				|| !condition.getSrhLocationCd().equals("")) {

			if (condition.getSrhItemCd() == null
					|| condition.getSrhItemCd().equals("")) {

				if (condition.getSrhOtherCompanyCd1() == null
						|| condition.getSrhOtherCompanyCd1().equals("")) {
					if (condition.getSrhLotNo() == null
							|| condition.getSrhLotNo().equals("")) {
						if (condition.getSrhAvailableFlg() == null
								|| condition.getSrhAvailableFlg().equals(
									BigDecimal.ZERO)) {
							return true;
						}
					}
				}

			}
		}
		return false;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryListLogicを設定します。
	 * @param inventoryListLogic inventoryListLogic
	 */
	public void setInventoryListLogic(
			final InventoryListLogic inventoryListLogic) {
		this.inventoryListLogic = inventoryListLogic;
	}

	/**
	 * inventoryListExcelDecoratorを設定します。
	 * @param inventoryListExcelDecorator inventoryListExcelDecorator
	 */
	public void setInventoryListExcelDecorator(
			final InventoryListExcelDecorator inventoryListExcelDecorator) {
		this.inventoryListExcelDecorator = inventoryListExcelDecorator;
	}
}
