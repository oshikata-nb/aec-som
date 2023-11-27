/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.棚卸入力一覧
 * @author t0011036
 */
public class InquiryInputListExcelDecoratorImpl implements
		InquiryInputListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/**
	 * コンストラクタ
	 */
	public InquiryInputListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<InquiryInputListForReport> list, final String tantoNm,
			final Timestamp currentDate) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("inquiryinputlist");

		/* 明細をセット */
		setDetail(list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setDetail(final List<InquiryInputListForReport> list) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet("inquiryinputlist");

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (InquiryInputListForReport bean : list) {
			sCol = TEMP_START_COL;

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCountDate());
			builder.setExcelDataString(sRow, sCol++, bean.getCountDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getName01());

			builder.setExcelDataString(sRow, sCol++, bean.getCountLocation());
			builder.setExcelDataString(sRow, sCol++, bean.getLocationName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getLotNo());
			builder.setExcelDataString(sRow, sCol++, bean.getAliasLotNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCountQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFractionQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInputQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getInputfraction());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCountCost());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getCountUpdateDate());
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
