/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.balance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.請求残高一覧
 * @author t0011036
 */
public class ClaimBalanceListExcelDecoratorImpl implements
		ClaimBalanceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ClaimBalanceListLogic claimBalanceListLogic;

	/**
	 * コンストラクタ
	 */
	public ClaimBalanceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ClaimBalanceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("claimbalancelist");

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
	private void setDetail(final ClaimBalanceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ClaimBalanceListForReport> list = claimBalanceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("claimbalancelist");

		/* リスト部 */
		for (ClaimBalanceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getClaimNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditScheduledDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditDivision());
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
				.getFunTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEraserBalanceAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherCredit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSales());
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
	 * claimBalanceListLogicを設定します。
	 * @param claimBalanceListLogic claimBalanceListLogic
	 */
	public void setClaimBalanceListLogic(
			final ClaimBalanceListLogic claimBalanceListLogic) {
		this.claimBalanceListLogic = claimBalanceListLogic;
	}
}
