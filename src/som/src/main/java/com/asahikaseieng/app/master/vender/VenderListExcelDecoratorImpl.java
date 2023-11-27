/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.取引先マスタ一覧
 * @author t0011036
 */
public class VenderListExcelDecoratorImpl implements VenderListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private VenderListLogic venderListLogic;

	/**
	 * コンストラクタ
	 */
	public VenderListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final VenderListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("venderlist");

		/* 明細をセット */
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param condition 検索条件
	 */
	private void setDetail(final VenderListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<VenderListForReport> list = venderListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("venderlist");

		/* リスト部 */
		for (VenderListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderFaxNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderFaxOutput());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderMailAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderMailAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderMailAddress3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getOrderMailOutput());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesFaxNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSalesFaxOutput());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesMailAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesMailAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getSalesMailAddress3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSalesMailOutput());
			builder.setExcelDataString(rowNum, colNum++, bean.getMailOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getMailOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRepresentRole());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRepresentPerson());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClosingDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditMonthDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditScheduledDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditDivision1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBoundAmount1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditDivision2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBoundAmount2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditDivision3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBoundAmount3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditDivision4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBoundAmount4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditDivision5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayingCheckDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayingCheckConditionAmount());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getPaymentInvoiceCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getPaymentInvoiceName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditLimitPrice());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataString(rowNum, colNum++, bean.getAreaCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getAreaName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesBasic());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCalcDivision());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getTaxRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesPurchaseRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesPurchaseRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitpriceRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitpriceRoundupUnit());
			builder.setExcelDataString(rowNum, colNum++, bean.getBankCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountStockhold());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerTantoName1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTantoComment1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerTantoName2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTantoComment2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerTantoName3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTantoComment3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerImpression1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerImpression2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerImpression3());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getAccountsPayableUpdate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getClosingUpdate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLedgerPublish());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillPublish());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipPublish());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExpenceRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCurrencyDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNewTaxRatio());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getNewTaxApplyDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseOrderDataDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getFaxOutput());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliverDay1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliverDay2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPreInfoSupplyDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountsCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountsName());
			builder.setExcelDataString(rowNum, colNum++, bean.getCityCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSubcontractLaw());
			builder.setExcelDataString(rowNum, colNum++, bean.getCalendarCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCalName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getOtherBankCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherBankMasterName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherAccountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherAccountNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherAccountStockhold());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTransferCommissionLoad());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAdvanceDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditMonthDivision1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditMonthDivision2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditMonthDivision3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditScheduledDate1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditScheduledDate2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditScheduledDate3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCategoryDivision1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCategoryDivision2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCategoryDivision3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseDiscountDays1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseDiscountDays2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseDiscountDays3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getHolidayFlg());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSalesCategoryDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillOutsideCdPublish());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountingCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getZeroTargetPublish());
			builder.setExcelDataString(rowNum, colNum++, bean.getSlipFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getTsInvoicePtn());
			builder.setExcelDataString(rowNum, colNum++, bean.getSiInvoicePtn());
			builder.setExcelDataString(rowNum, colNum++, bean.getInvoiceNo());

			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());

			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());

			rowNum++;
		}
	}

	/**
	 * venderListLogicを設定します。
	 * @param venderListLogic venderListLogic
	 */
	public void setVenderListLogic(final VenderListLogic venderListLogic) {
		this.venderListLogic = venderListLogic;
	}
}
