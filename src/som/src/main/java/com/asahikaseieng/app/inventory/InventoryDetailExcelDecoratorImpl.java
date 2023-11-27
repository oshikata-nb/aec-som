/*
 * Created on 2009/06/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製品ラベルＥＸＣＥＬダウンロード用ファイル作成
 * @author t2712372
 */
public class InventoryDetailExcelDecoratorImpl implements
		InventoryDetailExcelDecorator {

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
	public InventoryDetailExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(final InventoryDetailForm frm,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress) {

		/* パラメータチェック */
		if (StringUtils.isEmpty(tantoName)) {
			throw new IllegalArgumentException("Paramater is Empty.");
		}

		/* テンプレートをセット */
		builder.setWorkbookUrl("inventory_label");

		/* プリンタ設定シートをコピー */
		builder.setPrtSettingSheet();

		/* ラベル情報をセット */
		setSeihinLabel(frm, tantoName, currentDate);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 製品ラベル情報をセット
	 * @param tantoName 担当者名
	 */
	private void setSeihinLabel(final InventoryDetailForm frm,
			final String tantoName, final Timestamp currentDate) {

		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		Timestamp outputDate = currentDate;
		if (outputDate == null) {
			outputDate = AecDateUtils.getCurrentTimestamp();
		}

		// ラベル枚数
		builder.setCellValue(sRow, sCol++, frm.getStrLabelCount());
		// ラベル種別
		if (frm.getLotNo().substring(0, 1).equals("N")) {
			builder.setCellValue(sRow, sCol++, "原材料");
		} else {
			builder.setCellValue(sRow, sCol++, "製品");
		}
		// 日付
		if (frm.getLastInDate() == null) {
			sCol++;
		} else {
			builder.setCellValue(sRow, sCol++, frm.getLastInDate());
		}
		// 品目コード
		builder.setCellValue(sRow, sCol++, frm.getItemCd());
		// 品目名
		builder.setCellValue(sRow, sCol++, frm.getItemName());
		// 荷姿
		builder.setCellValue(sRow, sCol++, frm.getStyleOfPacking());
		// 入庫ロケーション
		builder.setCellValue(sRow, sCol++, frm.getLocationCd());
		// 入荷ロット番号/包装指図番号
		builder.setCellValue(sRow, sCol++, frm.getLotNo());
		// 原料ロット番号/製品ロット番号
		builder.setCellValue(sRow, sCol++, frm.getAliasLotNo());
		// 荷姿数
		builder.setCellValue(sRow, sCol++, frm.getStrPackQty());
		// 端数
		builder.setCellValue(sRow, sCol++, frm.getStrFraction());
		// 総量
		builder.setCellValue(sRow, sCol++, frm.getStrInventoryQty());
		// 単価
		builder.setCellValue(sRow, sCol++, frm.getStrInventoryCost());
		// 理由コード
		builder.setCellValue(sRow, sCol++, frm.getRyCd());
		// 理由
		builder.setCellValue(sRow, sCol++, frm.getRyDescription());
		// 摘要
		builder.setCellValue(sRow, sCol++, frm.getRemark());
		// 他社コード
		builder.setCellValue(sRow, sCol++, frm.getOtherCompanyCd1());

	}

}
