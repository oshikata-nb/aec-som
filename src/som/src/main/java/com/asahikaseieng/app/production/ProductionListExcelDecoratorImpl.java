/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.production;

import java.util.List;

import com.asahikaseieng.dao.nonentity.productionforreport.ProductionDetailListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionHeaderListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionListConditionForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.製造指図一覧
 * @author tosco
 */
public class ProductionListExcelDecoratorImpl implements
		ProductionListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ProductionListLogic productionListLogic;

	/**
	 * コンストラクタ
	 */
	public ProductionListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ProductionListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("productionlist");

		/* ヘッダをセット */
		setHeaderReport(condition);

		/* 詳細をセット */
		setDetailReport(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setHeaderReport(
			final ProductionListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("header");

		List<ProductionHeaderListForReport> list = productionListLogic
				.getHeaderList(condition);
		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (ProductionHeaderListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getShipperDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getTypeDivision());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductionLineName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());

			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOrderLet());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderExpectQty());
			builder.setExcelDataString(sRow, sCol++, bean.getUnitName());

			sRow++;
		}
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setDetailReport(
			final ProductionListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("detail");

		List<ProductionDetailListForReport> list = productionListLogic
				.getDetailList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (ProductionDetailListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getOrderCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getOrderLet());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderExpectQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOrderAcceptQty());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderComment());
			builder.setExcelDataString(sRow, sCol++, bean.getOrderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOrderRowNo());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}

	/**
	 * productionListLogicを設定します。
	 * @param productionListLogic productionListLogic
	 */
	public void setProductionListLogic(
			final ProductionListLogic productionListLogic) {
		this.productionListLogic = productionListLogic;
	}
}
