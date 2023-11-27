/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.仕入先別単価マスタ一覧
 * @author t0011036
 */
public class UnitpriceListExcelDecoratorImpl implements
		UnitpriceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private UnitpriceListLogic unitpriceListLogic;

	/**
	 * コンストラクタ
	 */
	public UnitpriceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final UnitpriceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("unitpricelist");

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
	private void setDetail(final UnitpriceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<UnitpriceListForReport> list = unitpriceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("unitpricelist");

		/* リスト部 */
		for (UnitpriceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getVenderDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getMaterialMakerName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataString(rowNum, colNum++, bean.getOutsideCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getOutsideName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getValidDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getEndDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getConsecutiveNo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityFrom());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getQuantityTo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getUnitprice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExpenceRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMinBuyQuantity());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataString(rowNum, colNum++, bean.getVender01Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVender02Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVender03Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVender04Cd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVender05Cd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUnitpriceDivision());

			rowNum++;
		}
	}

	/**
	 * unitpriceListLogicを設定します。
	 * @param unitpriceListLogic unitpriceListLogic
	 */
	public void setUnitpriceListLogic(
			final UnitpriceListLogic unitpriceListLogic) {
		this.unitpriceListLogic = unitpriceListLogic;
	}
}
