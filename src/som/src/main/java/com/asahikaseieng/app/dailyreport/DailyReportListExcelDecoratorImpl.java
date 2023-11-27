/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.dailyreport;

import java.util.List;

import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportDetailListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportHeaderListForReport;
import com.asahikaseieng.dao.nonentity.dailyeportforreport.DailyReportListConditionForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.製造指図一覧
 * @author tosco
 */
public class DailyReportListExcelDecoratorImpl implements
		DailyReportListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private DailyReportListLogic dailyReportListLogic;

	/**
	 * コンストラクタ
	 */
	public DailyReportListExcelDecoratorImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final DailyReportListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("dairyreportlist");

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
			final DailyReportListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("header");

		List<DailyReportHeaderListForReport> list = dailyReportListLogic.getHeaderReportList(condition);
		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (DailyReportHeaderListForReport bean : list) {
			sCol = TEMP_START_COL;
			builder.setExcelDataString(sRow, sCol++, bean.getProductionLine());
			builder.setExcelDataString(sRow, sCol++, bean.getProductionLineName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getProductionDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTantoDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoNm());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getInsideTotal());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getOutsideTotal());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getEmployTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getIndirectTime());
			
			builder.setExcelDataString(sRow, sCol++, bean.getInsideTotalHhmm());
			builder.setExcelDataString(sRow, sCol++, bean.getOutsideTotalHhmm());
			builder.setExcelDataString(sRow, sCol++, bean.getEmployTimeHhmm());
			builder.setExcelDataString(sRow, sCol++, bean.getIndirectTimeHhmm());
			
			
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
	 * 帳票Excelデータをセット
	 * @param condition condition
	 */
	private void setDetailReport(
			final DailyReportListConditionForReport condition) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;
		/* シートをセット */
		builder.setSheet("detail");

		List<DailyReportDetailListForReport> list = dailyReportListLogic
				.getDetailReportList(condition);

		// 検索結果の無い場合処理をしない
		if (list == null) {
			return;
		}

		for (DailyReportDetailListForReport bean : list) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getProductionLine());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductionLineName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean
					.getProductionDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTantoDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoCd());
			builder.setExcelDataString(sRow, sCol++, bean.getTantoNm());
			builder.setExcelDataString(sRow, sCol++, bean.getDirectionNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getJobTime());
			
			builder.setExcelDataString(sRow, sCol++, bean.getJobTimeHhmm());
			
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
	 * dailyReportListLogicを設定します。
	 * @param dailyReportListLogic dailyReportListLogic
	 */
	public void setDailyReportListLogic(
			final DailyReportListLogic dailyReportListLogic) {
		this.dailyReportListLogic = dailyReportListLogic;
	}
}
