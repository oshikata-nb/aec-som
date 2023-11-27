/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecorator実装クラス.設備グループマスタ一覧
 * @author t0011036
 */
public class RecipeResouceGroupListExcelDecoratorImpl implements
		RecipeResouceGroupListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RecipeResouceGroupListLogic recipeResouceGroupListLogic;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceGroupListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RecipeResouceGroupListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("reciperesoucegrouplist");

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
	private void setDetail(
			final RecipeResouceGroupListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<RecipeResouceGroupListForReport> list = recipeResouceGroupListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("reciperesoucegrouplist");

		/* リスト部 */
		for (RecipeResouceGroupListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean
					.getResouceGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getResouceGroupName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationGroupCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationGroupName());
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
	 * recipeResouceGroupListLogicを設定します。
	 * @param recipeResouceGroupListLogic recipeResouceGroupListLogic
	 */
	public void setRecipeResouceGroupListLogic(
			final RecipeResouceGroupListLogic recipeResouceGroupListLogic) {
		this.recipeResouceGroupListLogic = recipeResouceGroupListLogic;
	}
}
