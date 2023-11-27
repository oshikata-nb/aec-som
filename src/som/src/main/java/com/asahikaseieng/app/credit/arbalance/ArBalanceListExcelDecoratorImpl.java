/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.地区マスタ一覧
 * @author t0011036
 */
public class ArBalanceListExcelDecoratorImpl implements
		ArBalanceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ArBalanceListLogic arBalanceListLogic;

	/**
	 * コンストラクタ
	 */
	public ArBalanceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ArBalanceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("arbalancelist");

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
	private void setDetail(final ArBalanceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ArBalanceListForReport> list = arBalanceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("arbalancelist");

		/* リスト部 */
		for (ArBalanceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getDepositNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBalanceForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDepositAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherDepositAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getReturnedAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDiscountAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSalesAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditSalesBreakdown());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccruedDebitBreakdown());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExceptBreakdown());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getClaimBalance());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherDeposit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherSales());
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
	 * arBalanceListLogicを設定します。
	 * @param arBalanceListLogic arBalanceListLogic
	 */
	public void setArBalanceListLogic(
			final ArBalanceListLogic arBalanceListLogic) {
		this.arBalanceListLogic = arBalanceListLogic;
	}
}
