/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.directionmaterialusedforreport.RepDirectionMaterialUsed;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 製品別原材料消費量リストＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class DirectionMaterialUsedExcelDecoratorImpl implements
		DirectionMaterialUsedExcelDecorator {

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
	public DirectionMaterialUsedExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final List<RepDirectionMaterialUsed> list, final String dateFrom,
			final String dateTo, final String tantoName,
			final Timestamp currentDate) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("direction_material_used");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		// 製品別原材料消費量リスト明細取得用ヴューを検索
		// List<RepDirectionMaterialUsed> detailList =
		// repDirectionMaterialUsedDao
		// .getListForReport(dateFrom, dateTo);

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
			final List<RepDirectionMaterialUsed> detailList) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		// 期間をセット
		builder.setExcelDataString((short) 0, (short) 1, dateFrom);
		builder.setExcelDataString((short) 0, (short) 2, dateTo);

		for (RepDirectionMaterialUsed bean : detailList) {
			sCol = TEMP_START_COL;

			// 中間生地・製品
			builder.setExcelDataString(sRow, sCol++, bean.getDivision());
			// 財務分類
			builder.setExcelDataString(sRow, sCol++, bean
					.getFinancialClassCdDisp());
			// 品目コード
			builder.setExcelDataString(sRow, sCol++, bean.getItemCdDisp());
			// 品目名称
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			// 荷姿
			builder.setExcelDataString(sRow, sCol++, bean.getStyleOfPacking());
			// 製造・包装数
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getResultQty());
			// 種別
			builder.setExcelDataString(sRow, sCol++, bean.getMItemDivision());
			// 品目コード
			builder.setExcelDataString(sRow, sCol++, bean.getMItemCd());
			// 品目名称
			builder.setExcelDataString(sRow, sCol++, bean.getMItemName());
			// 原材料荷姿
			builder.setExcelDataString(sRow, sCol++, bean.getMStyleOfPacking());
			// 平均単価
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getUnitprice());
			// 製造出庫量
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMResultQty());
			// 製造出庫金額
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAmount());

			sRow++;
		}
	}
}
