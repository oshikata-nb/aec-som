/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutmaterial.InoutMaterial;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 原材料別入出庫一覧ＥＸＣＥＬダウンロード用ファイル作成
 * @author t1344224
 */
public class InoutMaterialExcelDecoratorImpl implements
		InoutMaterialExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "header_data";

	/** 詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "detail_data";

	/**
	 * コンストラクタ
	 */
	public InoutMaterialExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String dateFrom,
			final String dateTo, final List<InoutMaterial> list) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("inout_material_report");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* 明細をセット */
		setDetail(dateFrom, dateTo, list);

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final String dateFrom, final String dateTo,
			final List<InoutMaterial> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		// 期間をセット
		builder.setExcelDataString((short) 0, (short) 1, dateFrom);
		builder.setExcelDataString((short) 0, (short) 2, dateTo);

		for (InoutMaterial bean : detailList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStockDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getTypeDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getZenzanQty());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getZenzanAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTougetuUkeireQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTougetuUkeireAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getHenpinQty());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getHenpinAmount());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRy401ZisyaQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy401KaoQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy402Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy403Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy404Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy405Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy406Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy407Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy408Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy409Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy410Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy999Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy302Qty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRy302Amount());

			sRow++;
		}
	}
}
