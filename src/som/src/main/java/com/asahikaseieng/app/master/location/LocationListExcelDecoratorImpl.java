/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.ロケーションマスタ一覧
 * @author t0011036
 */
public class LocationListExcelDecoratorImpl implements
		LocationListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private LocationListLogic locationListLogic;

	/**
	 * コンストラクタ
	 */
	public LocationListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final LocationListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("locationlist");

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
	private void setDetail(final LocationListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<LocationListForReport> list = locationListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("locationlist");

		/* リスト部 */
		for (LocationListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getLocationCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getLocationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUpperLocationCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUpperLocationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLocationLevel());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPossibleWeight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCurrentWeight());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMrpTargetFlg());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getTantoNm());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAvailableFlg());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCountDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getName01());

			rowNum++;
		}
	}

	/**
	 * locationListLogicを設定します。
	 * @param locationListLogic locationListLogic
	 */
	public void setLocationListLogic(final LocationListLogic locationListLogic) {
		this.locationListLogic = locationListLogic;
	}
}
