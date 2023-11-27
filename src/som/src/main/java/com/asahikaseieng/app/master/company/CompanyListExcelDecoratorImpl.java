/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.自社マスタ一覧
 * @author t0011036
 */
public class CompanyListExcelDecoratorImpl implements CompanyListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private CompanyListLogic companyListLogic;

	/**
	 * コンストラクタ
	 */
	public CompanyListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final CompanyListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("companylist");

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
	private void setDetail(final CompanyListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<CompanyListForReport> list = companyListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("companylist");

		/* リスト部 */
		for (CompanyListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getHomeName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getHomeName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRepresentRole());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRepresentPerson());
			builder.setExcelDataString(rowNum, colNum++, bean.getInvoiceNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSettlement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClosingDay());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getManagementStartDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getReportCycle());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCalcDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxRoundupUnit());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getTaxRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockDiscountRate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseDiscountRate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProductAutoCharge());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProvideAutoCharge());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitpriceRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitpriceRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBlendQtyRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBlendQtyRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMixRateRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMixRateRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAdjRoundup());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAdjRoundupUnit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityDecimalpoint1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityDecimalpoint2());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName3());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName4());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName5());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName6());
			builder.setExcelDataString(rowNum, colNum++, bean.getCostName7());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getWorkReport());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProductionPlanAutoCreate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAmountDecimalpoint());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProofManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getManufacturingSubcontractCost());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentUpdate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBuySubconstractSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getManufactureSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getWorkResultSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBadSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMonthlySumSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProofSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProcessManagementSaveperiod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDummySaveperiod1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDummySaveperiod2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDummySaveperiod3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDummySaveperiod4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getWorkerManagement());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getWorkOpen());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getViewProductPlan());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName0());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName4());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName5());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName6());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName7());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName8());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderDivisionName9());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency1());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency2());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency3());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency4());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency5());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank6());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency6());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank7());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency7());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank8());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency8());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getInspectRank9());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectFrequency9());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNonInspectMonth());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getReceiveInspreceiptLocation());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPickupInspectSheet());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getChangespecManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTurnMadeManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCostUpdateManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTraceManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProcessManagementUse());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDisbursementManagement());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMonthlyDivideMethod());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getFirstWeekday());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNewTaxRatio());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getNewTaxApllyDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentUpdate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInventoryUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockUpdateDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMonthlyStockClosingDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJseflg1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJseflg2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJseflg3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJseflg4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJseflg5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsekbn1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsekbn2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsekbn3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsekbn4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsekbn5());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsedate1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsedate2());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsedate3());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsedate4());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsedate5());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsetime1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsetime2());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsetime3());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsetime4());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getJsetime5());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsenum1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsenum2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsenum3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsenum4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getJsenum5());
			builder.setExcelDataString(rowNum, colNum++, bean.getJsevalue1());
			builder.setExcelDataString(rowNum, colNum++, bean.getJsevalue2());
			builder.setExcelDataString(rowNum, colNum++, bean.getJsevalue3());
			builder.setExcelDataString(rowNum, colNum++, bean.getJsevalue4());
			builder.setExcelDataString(rowNum, colNum++, bean.getJsevalue5());
			builder.setExcelDataString(rowNum, colNum++, bean.getCompanyCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCentralBranchCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCalCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillOutsideCdPublish());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountingCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditIssuedDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentIssuedDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterCd1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditBankName1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountAbbreviation1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountStockhold1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterCd2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditBankName2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountAbbreviation2());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountStockhold2());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterCd3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditBankName3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountAbbreviation3());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision3());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountStockhold3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterCd4());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditBankName4());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountAbbreviation4());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision4());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo4());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getAccountStockhold4());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getBankMasterCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getPaymentBankName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTransferClientCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getTransferClientName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPasswordValidTerm());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPasswordKetaLowerLimit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPasswordKetaUpperLimit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPrime());
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
	 * companyListLogicを設定します。
	 * @param companyListLogic companyListLogic
	 */
	public void setCompanyListLogic(final CompanyListLogic companyListLogic) {
		this.companyListLogic = companyListLogic;
	}
}
