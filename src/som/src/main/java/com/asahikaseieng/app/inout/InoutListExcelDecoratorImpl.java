/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.受払照会一覧
 * @author tosco
 */
public class InoutListExcelDecoratorImpl implements InoutListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public InoutListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<InoutRecordListForReport> list) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("inoutrecordlist");

		/* シートをセット */
		builder.setSheet("inoutrecordlist");

		/* 明細をセット */
		setReport(list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param list List<InoutRecordListForReport>
	 */
	private void setReport(final List<InoutRecordListForReport> list) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		for (InoutRecordListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getInoutNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInoutDivision());
			builder.setExcelDataString(sRow, sCol++, bean
					.getInoutDivisionName());
			builder.setExcelDataString(sRow, sCol++, bean.getOderNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOderLineNo());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutPrice());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInoutCost());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInoutDate());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getInoutSourceNo());
			builder.setExcelDataString(sRow, sCol++, bean.getSectionCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSectionName());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountsCd());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountsName());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountsSubCd());
			builder.setExcelDataString(sRow, sCol++, bean.getAccountsSubName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCategory());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getParentItemName());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getParentAccountsCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getParentAccountsName());
			builder.setExcelDataString(sRow, sCol++, bean
					.getParentAccountSubCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getParentAccountsSubName());
			builder.setExcelDataString(sRow, sCol++, bean
					.getParentItemCategory());
			builder.setExcelDataString(sRow, sCol++, bean.getRyCd());
			builder.setExcelDataString(sRow, sCol++, bean.getRyDescription());
			builder.setExcelDataString(sRow, sCol++, bean.getReason());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getInoutUpdateDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getInventoryUpdateDate());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getFuncDivision());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());

			sRow++;
		}
	}
}
