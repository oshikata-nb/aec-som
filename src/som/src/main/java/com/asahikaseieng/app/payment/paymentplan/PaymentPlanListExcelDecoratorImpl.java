/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentplan;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListForReport;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.RepPaymentPlanInform;
import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.RepPaymentPlanInformDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.支払予定一覧
 * @author t0011036
 */
public class PaymentPlanListExcelDecoratorImpl implements
		PaymentPlanListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_INFORM_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_INFORM_START_COL = 0;

	/** お支払通知書ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** お支払通知書詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private PaymentPlanListLogic paymentPlanListLogic;

	private RepPaymentPlanInformDao repPaymentPlanInformDao;

	/**
	 * コンストラクタ
	 */
	public PaymentPlanListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final PaymentPlanListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("paymentplanlist");

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
	private void setDetail(final PaymentPlanListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<PaymentPlanListForReport> list = paymentPlanListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("paymentplanlist");

		/* リスト部 */
		for (PaymentPlanListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getPaymentNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrganizationName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSupplierCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderShortedName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getPayableDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getCreditScheduledDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCreditDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getCategoryName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getBankMasterCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getBankMasterName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUseAccountDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getUseAccountNo());
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
					.getOffsetAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableAmount());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPayableAmountBalance());
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
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportPaymentInform(
			final ArrayList<String> paymentNo, final String tantoName,
			final String loginUserOrganizationName,
			final Timestamp currentDate, final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("payment_plan_inform");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// お支払通知書ヘッダを取得
		List<RepPaymentPlanInform> headerList = repPaymentPlanInformDao
				.getListForReport(paymentNo);

		// お支払通知書詳細を取得
		List<RepPaymentPlanInform> detailList = repPaymentPlanInformDao
				.getListForReport(paymentNo);

		/* ヘッダ情報をセット */
		setHeaderPaymentInform(headerList, tantoName,
			loginUserOrganizationName, currentDate);
		/* 明細をセット */
		setDetailPaymentInform(detailList);
		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * お支払通知書ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeaderPaymentInform(
			final List<RepPaymentPlanInform> headerList,
			final String tantoName, final String loginUserOrganizationName,
			final Timestamp currentDate) {

		short sRow = TEMP_INFORM_START_ROW;
		short sCol = TEMP_INFORM_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		for (RepPaymentPlanInform bean : headerList) {
			sCol = TEMP_INFORM_START_COL;

			// 支払番号
			builder.setExcelDataString(sRow, sCol++, bean.getPaymentNo());
			// 担当部署
			builder.setExcelDataString(sRow, sCol++, loginUserOrganizationName);
			// 担当者
			builder.setExcelDataString(sRow, sCol++, tantoName);
			// 出力日
			builder.setExcelDataTimestamp(sRow, sCol++, currentDate);

			sRow++;
		}

	}

	/**
	 * お支払通知書詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetailPaymentInform(
			final List<RepPaymentPlanInform> detailList) {

		short sRow = TEMP_INFORM_START_ROW;
		short sCol = TEMP_INFORM_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RepPaymentPlanInform bean : detailList) {
			sCol = TEMP_INFORM_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getPaymentNo());
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSupplierCd());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPayableDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getCreditScheduledDate());
			builder.setExcelDataString(sRow, sCol++, bean.getCreditDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNoteSight());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getHolidayFlg());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClaimAmountForward());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditAmountForward());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOtherCreditAmountForward());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getBalanceForward());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingReturnedAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockingDiscountAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOtherStockingAmount());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getOffsetAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTaxAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getPayableAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getStockReduction());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTransferFee());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getBuyMonth());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherBankCd());
			builder.setExcelDataString(sRow, sCol++, bean.getBankMasterName());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherAccountNo());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2());
			builder.setExcelDataString(sRow, sCol++, bean
					.getAccountDivisionName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getClosingDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTransferCommissionLoad());
			builder.setExcelDataString(sRow, sCol++, bean.getNextdayFlg());

			sRow++;

		}

	}

	/**
	 * paymentPlanListLogicを設定します。
	 * @param paymentPlanListLogic paymentPlanListLogic
	 */
	public void setPaymentPlanListLogic(
			final PaymentPlanListLogic paymentPlanListLogic) {
		this.paymentPlanListLogic = paymentPlanListLogic;
	}

	/**
	 * お支払通知書検索用Dao
	 * @param repPaymentPlanInformDao お支払通知書検索用Dao
	 */
	public void setRepPaymentPlanInformDao(
			final RepPaymentPlanInformDao repPaymentPlanInformDao) {
		this.repPaymentPlanInformDao = repPaymentPlanInformDao;
	}
}
