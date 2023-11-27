/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.支払残高一覧
 * @author t0011036
 */
public class PaymentBalanceListExcelDecoratorImpl implements
		PaymentBalanceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private PaymentBalanceListLogic paymentBalanceListLogic;

	/**
	 * コンストラクタ
	 */
	public PaymentBalanceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PaymentBalanceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("paymentbalancelist");

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
	private void setDetail(final PaymentBalanceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<PaymentBalanceListForReport> list = paymentBalanceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("paymentbalancelist");

		/* リスト部 */
		for (PaymentBalanceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getPaymentNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSupplierCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getPayableDate());
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
					.getStockingAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockingReturnedAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockingDiscountAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherStockingAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCreditAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOtherTotal());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockReduction());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTransferFee());
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
	 * paymentBalanceListLogicを設定します。
	 * @param paymentBalanceListLogic paymentBalanceListLogic
	 */
	public void setPaymentBalanceListLogic(
			final PaymentBalanceListLogic paymentBalanceListLogic) {
		this.paymentBalanceListLogic = paymentBalanceListLogic;
	}
}
