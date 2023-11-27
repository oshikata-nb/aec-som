/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.買掛残高一覧
 * @author t0011036
 */
public class ApBalanceListExcelDecoratorImpl implements
		ApBalanceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ApBalanceListLogic apBalanceListLogic;

	/**
	 * コンストラクタ
	 */
	public ApBalanceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ApBalanceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("apbalancelist");

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
	private void setDetail(final ApBalanceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ApBalanceListForReport> list = apBalanceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("apbalancelist");

		/* リスト部 */
		for (ApBalanceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getPayableNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSupplierCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getPayableDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBalanceForward());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockingAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaymentAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherPaymentAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTotalPayment());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockingReturnedAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockingDiscountAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherStockingAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherStocking());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAccountPayableBreakdown());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getArrearageBreakdown());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExceptBreakdown());
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
	 * apBalanceListLogicを設定します。
	 * @param apBalanceListLogic apBalanceListLogic
	 */
	public void setApBalanceListLogic(
			final ApBalanceListLogic apBalanceListLogic) {
		this.apBalanceListLogic = apBalanceListLogic;
	}
}
