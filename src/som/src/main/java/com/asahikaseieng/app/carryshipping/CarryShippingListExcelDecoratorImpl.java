/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.carryshipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListForReport;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportCondition;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportDetail;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportDetailDao;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportHeader;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportHeaderDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.出荷指図一覧
 * @author t1344224
 */
public class CarryShippingListExcelDecoratorImpl implements
		CarryShippingListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW_CARRY = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private CarryShippingListLogic carryShippingListLogic;

	private RepCarryShippingForReportHeaderDao repCarryShippingForReportHeaderDao;

	private RepCarryShippingForReportDetailDao repCarryShippingForReportDetailDao;

	/**
	 * コンストラクタ
	 */
	public CarryShippingListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReportCarryShipping(
			final RepCarryShippingForReportCondition condition)
			throws NoDataException {
		/* テンプレートをセット */
		builder.setWorkbookUrl("slip_carry_shipping");

		// ヘッダデータをセット
		setHeaderCarryShipping(condition);

		// 詳細データをセット
		setDetailCarryShipping(condition);

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setHeaderCarryShipping(
			final RepCarryShippingForReportCondition condition)
			throws NoDataException {
		short rowNum = TEMP_START_ROW_CARRY;
		short colNum = TEMP_START_COL;

		List<RepCarryShippingForReportHeader> list = repCarryShippingForReportHeaderDao
				.getHeaderSerchList(condition);
		/* シートをセット */
		builder.setSheet("header_data");

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		/* リスト部 */
		for (RepCarryShippingForReportHeader bean : list) {
			colNum = TEMP_START_COL;
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getScheduledShippingDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName2());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAbbreviation());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFlanTransShopCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTranceportLeadTime());
			builder.setExcelDataString(rowNum, colNum++, bean.getCompanyCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLabelPublish());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getLabelCd());
			rowNum++;
		}
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setDetailCarryShipping(
			final RepCarryShippingForReportCondition condition) {
		short rowNum = TEMP_START_ROW_CARRY;
		short colNum = TEMP_START_COL;

		List<RepCarryShippingForReportDetail> list = repCarryShippingForReportDetailDao
				.getDetailSerchList(condition);
		/* シートをセット */
		builder.setSheet("detail_data");

		/* リスト部 */
		for (RepCarryShippingForReportDetail bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingInstructDate());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getScheduledShippingDate());

			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());

			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderRowNo());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());

			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());

			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName2());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryAddres());

			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());

			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());

			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			// 2015/1/26 -> 追加
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			// 2015/1/26 <- 追加

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingStatus());

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());

			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());

			builder.setExcelDataString(rowNum, colNum++, bean.getSummery());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliveryComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingResultDate());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getShippingSlipNo());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingSlipRowNo());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipPublishComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipPublishDate());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingOrderComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingOrderDate());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingScheduleComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingScheduleDate());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingTagComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingTagDate());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingRequestComp());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingRequestDate());

			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSuggestedDeliverlimit());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryFare());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getSendingOffNumber());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAllUpWeight());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingInstruction());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingInstructionW());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingResultQuantity());

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingResultQuantityW());

			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());

			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());

			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());

			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getDeliveryExpectedDate());

			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryExpectedTime());

			builder.setExcelDataString(rowNum, colNum++, bean.getCarryBc());

			rowNum++;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final CarryShippingListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("carryshippinglist");

		// ヘッダデータをセット
		setHeader(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setHeader(final CarryShippingListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<CarryShippingListForReport> list = carryShippingListLogic
				.getReportList(condition);
		/* シートをセット */
		builder.setSheet("shippingtemp");

		/* リスト部 */
		for (CarryShippingListForReport bean : list) {
			colNum = TEMP_START_COL;
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingInstructDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDeliveryName());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSendingOffNumber());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingOrderSendCompFlag());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getShippingOrderSendComp());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemUnit());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingResultDate());
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
	 * carryShippingListLogicを設定します。
	 * @param carryShippingListLogic carryShippingListLogic
	 */
	public void setCarryShippingListLogic(
			final CarryShippingListLogic carryShippingListLogic) {
		this.carryShippingListLogic = carryShippingListLogic;
	}

	/**
	 * repCarryShippingForReportHeaderDaoを設定します。
	 * @param repCarryShippingForReportHeaderDao
	 *            repCarryShippingForReportHeaderDao
	 */
	public void setRepCarryShippingForReportHeaderDao(
			final RepCarryShippingForReportHeaderDao repCarryShippingForReportHeaderDao) {
		this.repCarryShippingForReportHeaderDao = repCarryShippingForReportHeaderDao;
	}

	/**
	 * repCarryShippingForReportDetailDaoを設定します。
	 * @param repCarryShippingForReportDetailDao
	 *            repCarryShippingForReportDetailDao
	 */
	public void setRepCarryShippingForReportDetailDao(
			final RepCarryShippingForReportDetailDao repCarryShippingForReportDetailDao) {
		this.repCarryShippingForReportDetailDao = repCarryShippingForReportDetailDao;
	}
}
