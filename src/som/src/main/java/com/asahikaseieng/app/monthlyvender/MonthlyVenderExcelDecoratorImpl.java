/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.monthlyvender;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.monthlyvenderforreport.RepMonthlyVender;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 得意先別管理月報ＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class MonthlyVenderExcelDecoratorImpl implements
		MonthlyVenderExcelDecorator {

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
	public MonthlyVenderExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final List<RepMonthlyVender> list,
			final String dateFrom, final String dateTo, final String tantoName,
			final Timestamp currentDate) {

		/* テンプレートをセット */
		builder.setWorkbookUrl("monthly_vender");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* 明細をセット */
		setDetail(dateFrom, dateTo, list);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final String dateFrom, final String dateTo,
			final List<RepMonthlyVender> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		// 期間をセット
		builder.setExcelDataString((short) 0, (short) 1, dateFrom);
		builder.setExcelDataString((short) 0, (short) 2, dateTo);

		for (RepMonthlyVender bean : detailList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataString(sRow, sCol++, "1");
			builder.setExcelDataString(sRow, sCol++, bean.getVenderCd());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName1());
			builder.setExcelDataString(sRow, sCol++, bean.getVenderName2());
			builder.setExcelDataString(sRow, sCol++, bean.getOrganizationCd());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getZenzan());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getCreditNyukin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditNyukinSonota());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getCreditNyukinAll());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesUriage());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageHenpin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageTax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageAll());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageMi());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageHenpinMi());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageTaxMi());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSalesUriageAllMi());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSalesAll());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTouzan());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMaeukezan());

			sRow++;
		}
	}

}
