/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.納入先マスタ一覧
 * @author t0011036
 */
public class DeliveryListExcelDecoratorImpl implements
		DeliveryListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private DeliveryListLogic deliveryListLogic;

	/**
	 * コンストラクタ
	 */
	public DeliveryListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final DeliveryListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("deliverylist");

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
	private void setDetail(final DeliveryListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<DeliveryListForReport> list = deliveryListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("deliverylist");

		/* リスト部 */
		for (DeliveryListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName2());
			builder.setExcelDataString(rowNum, colNum++, bean.getSearchKana());
			builder.setExcelDataString(rowNum, colNum++, bean.getZipcodeNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress1());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress2());
			builder.setExcelDataString(rowNum, colNum++, bean.getAddress3());
			builder.setExcelDataString(rowNum, colNum++, bean.getTelNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getFaxNo());
			builder.setExcelDataString(rowNum, colNum++, bean.getCompanyCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCityCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryFareCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCarryName1());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getLeadTime());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getDeliveryTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getFareClaimExistence());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryCondition());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getShop1Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getShop2Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getShop3Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getShop4Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getShop5Cd());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());

			rowNum++;
		}
	}

	/**
	 * deliveryListLogicを設定します。
	 * @param deliveryListLogic deliveryListLogic
	 */
	public void setDeliveryListLogic(final DeliveryListLogic deliveryListLogic) {
		this.deliveryListLogic = deliveryListLogic;
	}
}
