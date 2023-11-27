/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.品目分類マスタ一覧
 * @author t0011036
 */
public class ItemCategoryListExcelDecoratorImpl implements
		ItemCategoryListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ItemCategoryListLogic itemCategoryListLogic;

	/**
	 * コンストラクタ
	 */
	public ItemCategoryListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ItemCategoryListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("itemcategorylist");

		/* 明細をセット */
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param list List<ItemCategoryListForReport>
	 */
	private void setDetail(final ItemCategoryListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemCategoryListForReport> list = itemCategoryListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("itemcategorylist");

		/* リスト部 */
		for (ItemCategoryListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getItemCategory());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getItemCategoryName());
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
	 * itemCategoryListLogicを設定します。
	 * @param itemCategoryListLogic itemCategoryListLogic
	 */
	public void setItemCategoryListLogic(
			final ItemCategoryListLogic itemCategoryListLogic) {
		this.itemCategoryListLogic = itemCategoryListLogic;
	}
}
