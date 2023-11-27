/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingDetailListForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingforreport.ShippingListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.出荷指図一覧
 * @author t1344224
 */
public class ShippingListExcelDecoratorImpl implements
		ShippingListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ShippingListLogic shippingListLogic;

	/**
	 * コンストラクタ
	 */
	public ShippingListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ShippingListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("shippinglist");

		// ヘッダデータをセット
		setHeader(condition);

		// 詳細データをセット
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setHeader(final ShippingListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ShippingListForReport> list = shippingListLogic
				.getHeaderList(condition);
		/* シートをセット */
		builder.setSheet("shipping");

		/* リスト部 */
		for (ShippingListForReport bean : list) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingInstructDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getScheduledShippingDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getShippingStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getLocationCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getLocationName());
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
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSuggestedDeliverlimit());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCarryFare());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSendingOffNumber());
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
	 * ヘッダーデータをセット
	 * @param condition 検索条件
	 */
	private void setDetail(final ShippingListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ShippingDetailListForReport> detailList = shippingListLogic
				.getDetailList(condition);
		/* シートをセット */
		builder.setSheet("shipping_detail");

		/* リスト部 */
		for (ShippingDetailListForReport bean : detailList) {
			colNum = TEMP_START_COL;
			builder.setExcelDataString(rowNum, colNum++, bean.getShippingNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingRowNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getRowNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getLotNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingInstruction());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getShippingResultDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingResultQuantity());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getSummery());
			builder.setExcelDataString(rowNum, colNum++, bean.getLocationCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getLocationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliveryComp());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductOutOrderBc());
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
	 * shippingListLogicを設定します。
	 * @param shippingListLogic shippingListLogic
	 */
	public void setShippingListLogic(final ShippingListLogic shippingListLogic) {
		this.shippingListLogic = shippingListLogic;
	}
}
