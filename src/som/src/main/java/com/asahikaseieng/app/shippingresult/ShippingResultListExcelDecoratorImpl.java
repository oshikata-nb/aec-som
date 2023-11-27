/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingDetailResultListForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.出荷指図一覧
 * @author t1344224
 */
public class ShippingResultListExcelDecoratorImpl implements
		ShippingResultListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ShippingResultListLogic shippingResultListLogic;

	/**
	 * コンストラクタ
	 */
	public ShippingResultListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ShippingResultListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("shippingresultlist");

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
	private void setHeader(final ShippingResultListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ShippingResultListForReport> list = shippingResultListLogic
				.getReportHeaderList(condition);
		/* シートをセット */
		builder.setSheet("shipping");

		/* リスト部 */
		for (ShippingResultListForReport bean : list) {
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
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderShortedName());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSearchKana());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingStatus());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getShippingStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSummery());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliveryComp());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingInstructionSum());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShippingResultQuantitySum());

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
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingFareComp()); // 運賃表発行済区分(0:未発行 1:発行済)
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingFareDate()); // 運賃表発行日
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingDelivelyComp()); // 納品伝票発行済区分(0:未発行 1:発行済)
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingDelivelyDate()); // 納品伝票発行日
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingNewTagComp()); // 新荷札発行済区分(0:未発行 1:発行済)
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingNewTagDate()); // 新荷札発行日
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSlipShippingPostalComp()); // 新郵政発行済区分(0:未発行 1:発行済)
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getSlipShippingPostalDate()); // 新郵政発行日
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
	 * 明細ーデータをセット
	 * @param condition 検索条件
	 */
	private void setDetail(final ShippingResultListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ShippingDetailResultListForReport> detailList = shippingResultListLogic
				.getReportDetailList(condition);
		/* シートをセット */
		builder.setSheet("shipping_detail");

		/* リスト部 */
		for (ShippingDetailResultListForReport bean : detailList) {
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
	 * shippingResultListLogicを設定します。
	 * @param shippingResultListLogic shippingResultListLogic
	 */
	public void setShippingResultListLogic(
			final ShippingResultListLogic shippingResultListLogic) {
		this.shippingResultListLogic = shippingResultListLogic;
	}
}
