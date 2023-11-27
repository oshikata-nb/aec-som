/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.depositplan;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.depositplanforreport.DepositPlanListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.入金予定一覧
 * @author t0011036
 */
public class DepositPlanListExcelDecoratorImpl implements
		DepositPlanListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private DepositPlanListLogic depositPlanListLogic;

	/**
	 * コンストラクタ
	 */
	public DepositPlanListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final DepositPlanListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("depositplanlist");

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
	private void setDetail(final DepositPlanListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<DepositPlanListForReport> list = depositPlanListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("depositplanlist");

		/* リスト部 */
		for (DepositPlanListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getClaimNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUseBankMasterCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUseBankMasterName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUseAccountDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getUseAccountNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditScheduledDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getCategoryName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNoteSight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getHolidayFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherCreditAmountForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBalanceForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesReturnedAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesDiscountAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInvoiceTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxDifference());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmountBalance());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBillDivision());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getIssueDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getIssuerCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getIssuerName());
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
	 * depositPlanListLogicを設定します。
	 * @param depositPlanListLogic depositPlanListLogic
	 */
	public void setDepositPlanListLogic(
			final DepositPlanListLogic depositPlanListLogic) {
		this.depositPlanListLogic = depositPlanListLogic;
	}
}
