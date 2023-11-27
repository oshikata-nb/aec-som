/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.カレンダーマスタ一覧
 * @author t0011036
 */
public class CalListExcelDecoratorImpl implements CalListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private CalListLogic calListLogic;

	/**
	 * コンストラクタ
	 */
	public CalListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final CalListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("callist");

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
	private void setDetail(final CalListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<CalListForReport> list = calListLogic.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("callist");

		/* リスト部 */
		for (CalListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getCalCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getCalName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCalYear());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getCalDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCalWeek());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCalHoliday());
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
	 * calListLogicを設定します。
	 * @param calListLogic calListLogic
	 */
	public void setCalListLogic(final CalListLogic calListLogic) {
		this.calListLogic = calListLogic;
	}
}
