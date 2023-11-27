/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.設備マスタ一覧
 * @author t0011036
 */
public class RecipeResouceListExcelDecoratorImpl implements
		RecipeResouceListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RecipeResouceListLogic recipeResouceListLogic;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RecipeResouceListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("reciperesoucelist");

		/* 明細をセット */
		setDetail(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 明細をセット
	 * @param list List<RecipeResouceListForReport>
	 */
	private void setDetail(final RecipeResouceListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<RecipeResouceListForReport> list = recipeResouceListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("reciperesoucelist");

		/* リスト部 */
		for (RecipeResouceListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getResouceCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getResouceName());
			builder.setExcelDataString(rowNum, colNum++, bean.getShortName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCostMachine());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCost());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getResouceGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getResouceGroupName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLine());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLineName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderPublishFlg());
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
	 * recipeResouceListLogicを設定します。
	 * @param recipeResouceListLogic recipeResouceListLogic
	 */
	public void setRecipeResouceListLogic(
			final RecipeResouceListLogic recipeResouceListLogic) {
		this.recipeResouceListLogic = recipeResouceListLogic;
	}
}
