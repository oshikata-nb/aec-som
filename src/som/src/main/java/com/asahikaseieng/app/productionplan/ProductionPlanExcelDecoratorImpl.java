/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.productionplan;

import java.util.List;

import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 月間生産計画表ＥＸＣＥＬダウンロード用ファイル作成
 * @author a1020630
 */
public class ProductionPlanExcelDecoratorImpl implements
		ProductionPlanExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 10;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 月間生産計画表ヘッダ用データシート名 */
	// protected static final String HEADER_DATA_SHEET_NAME = "header_data";
	/** 月間生産計画表細用データシート名(全荷主) */
	protected static final String DETAIL_DATA_SHEET_NAME_ALL = "detail_data_all";

	/** 月間生産計画表細用データシート名(花王) */
	protected static final String DETAIL_DATA_SHEET_NAME_KAO = "detail_data_kao";

	/** 月間生産計画表細用カレンダーシート名 */
	protected static final String CALENDAR_SHEET_NAME = "calendar";

	/**
	 * コンストラクタ
	 */
	public ProductionPlanExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final String tantoCd,
			final List<RepProductionPlanForReport> listAll,
			final List<RepProductionPlanForReport> listKao,
			final List<RepProductionPlanCalendar> calendarList,
			final String ipAddress) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("productionplan");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* シート名を指定 */
		builder.setSheet("print");

		/* 全荷主のデータをデータシートに貼り付け */
		setProductionPlan(tantoCd, listAll, DETAIL_DATA_SHEET_NAME_ALL);

		/* 荷主花王のデータをデータシートに貼り付け */
		setProductionPlan(tantoCd, listKao, DETAIL_DATA_SHEET_NAME_KAO);

		/* カレンダーをセット */
		setCalendar(calendarList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * カレンダーデータをセット
	 * @param tantoNm 担当者名
	 * @param list リスト
	 */
	private void setCalendar(final List<RepProductionPlanCalendar> calendarList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(CALENDAR_SHEET_NAME);

		for (RepProductionPlanCalendar bean : calendarList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataTimestamp(sRow, sCol++, bean.getCalDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCalHoliday());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCalWeek());

			sRow++;

		}
	}

	/**
	 * ヘッダをセット
	 * @param tantoNm 担当者名
	 * @param list リスト
	 */
	private void setProductionPlan(final String tantoCd,
			final List<RepProductionPlanForReport> list, final String sheetName) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(sheetName);

		for (RepProductionPlanForReport bean : list) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getPKey());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataString(sRow, sCol++, bean.getOtherCompanyCd1());
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getShipperDivision());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getTypeDivision());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getKgOfFractionManagement());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getNumberOfInsertions());
			builder.setExcelDataString(sRow, sCol++, bean.getProductionLine());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductionLineName());
			builder.setExcelDataString(sRow, sCol++, bean.getPackageLine());
			builder.setExcelDataString(sRow, sCol++, bean.getHizuke());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan01());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan02());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan03());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan04());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan05());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan06());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan07());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan08());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan09());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan10());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan11());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan12());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan13());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan14());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan15());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan16());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan17());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan18());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan19());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan20());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan21());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan22());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan23());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan24());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan25());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan26());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan27());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan28());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan29());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan30());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPlan31());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt01());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt02());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt03());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt04());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt05());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt06());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt07());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt08());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt09());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt10());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt11());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt12());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt13());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt14());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt15());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt16());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt17());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt18());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt19());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt20());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt21());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt22());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt23());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt24());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt25());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt26());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt27());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt28());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt29());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt30());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getReslt31());
			builder.setExcelDataString(sRow, sCol++, tantoCd);
			builder.setExcelDataTimestamp(sRow, sCol++, AecDateUtils
					.getCurrentTimestamp());

			sRow++;

		}
	}

	/* -------------------- setter -------------------- */

}
