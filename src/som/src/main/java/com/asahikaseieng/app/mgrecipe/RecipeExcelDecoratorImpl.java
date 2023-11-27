/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeAsprovaForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeAsprovaForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeDetailForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeDetailForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeFormulaForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeFormulaForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeHeaderForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeHeaderForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeInspectionForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeInspectionForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeProcedureForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeProcedureForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeRemarkForReport;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeRemarkForReportDao;
import com.asahikaseieng.dao.nonentity.recipeforreport.RecipeReportConditionForReport;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 処方ＥＸＣＥＬダウンロード用ファイル作成
 * @author ｔ１３４４２２４
 */
public class RecipeExcelDecoratorImpl implements RecipeExcelDecorator {

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private RecipeHeaderForReportDao recipeHeaderForReportDao;

	private RecipeProcedureForReportDao recipeProcedureForReportDao;

	private RecipeFormulaForReportDao recipeFormulaForReportDao;

	private RecipeInspectionForReportDao recipeInspectionForReportDao;

	private RecipeAsprovaForReportDao recipeAsprovaForReportDao;

	private RecipeRemarkForReportDao recipeRemarkForReportDao;

	private RecipeDetailForReportDao recipeDetailForReportDao;

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** 処方ヘッダ用データシート名 */
	protected static final String HEADER_DATA_SHEET_NAME = "recipe_header";

	/** 処方プロシージャ用データシート名 */
	protected static final String PROCEDURE_DATA_SHEET_NAME = "recipe_procedure";

	/** 処方フォーミュラ用データシート名 */
	protected static final String FORMULA_DATA_SHEET_NAME = "recipe_formula";

	/** 処方インスペクション用データシート名 */
	protected static final String INSPECTION_DATA_SHEET_NAME = "recipe_inspection";

	/** 処方アスプローバ用データシート名 */
	protected static final String ASPROVA_DATA_SHEET_NAME = "recipe_asprova";

	/** 処方備考用データシート名 */
	protected static final String REMARK_DATA_SHEET_NAME = "recipe_remark";

	/** 処方詳細用データシート名 */
	protected static final String DETAIL_DATA_SHEET_NAME = "recipe_detail";

	/**
	 * コンストラクタ
	 */
	public RecipeExcelDecoratorImpl() {
		super();

	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final RecipeReportConditionForReport condition) {

		/* テンプレートをセット */
		if (condition.getRecipeType().equals("3")) { // 基本処方テンプレート
			builder.setWorkbookUrl("mrecipelist");
		} else { // 元処方テンプレート
			builder.setWorkbookUrl("grecipelist");
		}

		// 処方ヘッダを取得
		List<RecipeHeaderForReport> headerList = recipeHeaderForReportDao
				.getHeadList(condition);

		/* ヘッダ情報をセット */
		setHeader(headerList);

		// 処方プロシージャ
		List<RecipeProcedureForReport> procedureList = recipeProcedureForReportDao
				.getProcedureList(condition);

		/* プロシージャ情報をセット */
		setProcedure(procedureList);

		// 処方フォーミュラ
		List<RecipeFormulaForReport> formulaList = recipeFormulaForReportDao
				.getFormulaList(condition);

		/* フォーミュラ情報をセット */
		setFormula(formulaList);

		// 処方インスペクション
		List<RecipeInspectionForReport> inspectionList = recipeInspectionForReportDao
				.getInspectionList(condition);

		/* インスペクション情報をセット */
		setInspection(inspectionList);

		// 処方アスプローバ
		List<RecipeAsprovaForReport> asprovaList = recipeAsprovaForReportDao
				.getAsprovaList(condition);

		/* アスプローバ情報をセット */
		setAsprova(asprovaList);

		// 処方備考
		List<RecipeRemarkForReport> remarkList = recipeRemarkForReportDao
				.getRemarkList(condition);

		/* 備考情報をセット */
		setRemark(remarkList);

		// 処方詳細
		List<RecipeDetailForReport> detailList = recipeDetailForReportDao
				.getDetailList(condition);

		/* 詳細情報をセット */
		setDetail(detailList);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 処方ヘッダをセット
	 * @param tantoName 担当者名
	 */
	private void setHeader(final List<RecipeHeaderForReport> headerList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(HEADER_DATA_SHEET_NAME);

		for (RecipeHeaderForReport bean : headerList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean.getRecipeCd());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRecipeVersion());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeType());
			builder.setExcelDataString(sRow, sCol++, bean
					.getRecipeDescription());
			builder.setExcelDataString(sRow, sCol++, bean.getRecipeMemo());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getRecipeStatus());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeUse());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRecipePriority());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOriginalRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean.getProductionLine());
			builder.setExcelDataString(sRow, sCol++, bean
					.getProductionLineName());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMinQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMaxQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStdQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getUnitQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCost());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getProcessLoss());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getStartDate());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getEndDate());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDeleteFlag());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getPrintFlag());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getPrintDate());
			builder.setExcelDataString(sRow, sCol++, bean.getPrintTantoCd());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getApprovalStatus());
			builder.setExcelDataString(sRow, sCol++, bean.getAppTantoCd());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getAppDate());
			builder.setExcelDataString(sRow, sCol++, bean.getLastAppTantoCd());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getLastAppDate());
			builder.setExcelDataString(sRow, sCol++, bean.getRecipeName());
			builder.setExcelDataString(sRow, sCol++, bean.getSectionCd());
			builder.setExcelDataString(sRow, sCol++, bean.getSectionName());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());

			sRow++;
		}
	}

	/**
	 * 処方プロシージャをセット
	 * @param tantoName 担当者名
	 */
	private void setProcedure(final List<RecipeProcedureForReport> procedureList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(PROCEDURE_DATA_SHEET_NAME);

		for (RecipeProcedureForReport bean : procedureList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataString(sRow, sCol++, bean.getOperationCd());
			builder.setExcelDataString(sRow, sCol++, bean.getOperationName());
			builder.setExcelDataString(sRow, sCol++, bean.getCondition());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMachineTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getManTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWorkTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getConditionTemp());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getConditionTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStirSpeed1());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStirSpeed2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWaterWeight());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMainStream());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getNet());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWeightMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getWeightMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getFilter());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMesh());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getAutoCheckerAve());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getGrossCheckerAve());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOpeningTorqueMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getOpeningTorqueMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClosingTorqueMin());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getClosingTorqueMax());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getTorquePressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAirPressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getVcloseTime());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHotAirPresetTemp());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getHotAirPressure());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getFirstHeatSeal());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getSecondHeatSeal());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdateName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			sRow++;
		}
	}

	/**
	 * 処方フォーミュラをセット
	 * @param tantoName 担当者名
	 */
	private void setFormula(final List<RecipeFormulaForReport> formulaList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(FORMULA_DATA_SHEET_NAME);

		for (RecipeFormulaForReport bean : formulaList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineType());
			builder.setExcelDataString(sRow, sCol++, bean.getItemCd());
			builder.setExcelDataString(sRow, sCol++, bean.getItemName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getQty());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getCost());
			builder.setExcelDataString(sRow, sCol++, bean.getCostUnit());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTonyu());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getTonyusokudo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getDataread());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdateName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			sRow++;
		}
	}

	/**
	 * 処方インスペクションをセット
	 * @param tantoName 担当者名
	 */
	private void setInspection(
			final List<RecipeInspectionForReport> inspectionList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(INSPECTION_DATA_SHEET_NAME);

		for (RecipeInspectionForReport bean : inspectionList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getStepNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getLineNo());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getSeq());
			builder.setExcelDataString(sRow, sCol++, bean.getInspectionCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInspectionName());
			builder.setExcelDataString(sRow, sCol++, bean.getDivision());
			builder.setExcelDataString(sRow, sCol++, bean.getValueType());
			builder.setExcelDataString(sRow, sCol++, bean.getValue1());
			builder.setExcelDataString(sRow, sCol++, bean.getValue2());
			builder.setExcelDataString(sRow, sCol++, bean.getCondition());
			builder.setExcelDataString(sRow, sCol++, bean.getRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getNotes());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdateName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			sRow++;
		}
	}

	/**
	 * 処方アスプローバをセット
	 * @param tantoName 担当者名
	 */
	private void setAsprova(final List<RecipeAsprovaForReport> asprovaList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(ASPROVA_DATA_SHEET_NAME);

		for (RecipeAsprovaForReport bean : asprovaList) {
			sCol = TEMP_START_COL;
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean.getResouceGroupCd());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getResouceGroupName());
			builder
					.setExcelDataString(sRow, sCol++, bean
							.getOperationGroupCd());
			builder.setExcelDataString(sRow, sCol++, bean
					.getOperationGroupName());
			builder.setExcelDataString(sRow, sCol++, bean.getResouceCd());
			builder.setExcelDataString(sRow, sCol++, bean.getResouceName());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getYouinsu());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getMaejikan());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getAtojikan());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getProcessWorkTime1());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getProcessWorkTime2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMachineWorkTime1());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getMachineWorkTime2());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getManWorkTime1());
			builder
					.setExcelDataBigDecimal(sRow, sCol++, bean
							.getManWorkTime2());
			builder.setExcelDataBigDecimal(sRow, sCol++, bean
					.getRecipePriority());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdateName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());
			sRow++;
		}
	}

	/**
	 * 処方備考をセット
	 * @param tantoName 担当者名
	 */
	private void setRemark(final List<RecipeRemarkForReport> remarkList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(REMARK_DATA_SHEET_NAME);

		for (RecipeRemarkForReport bean : remarkList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean
					.getGeneralRecipeRemark());
			builder.setExcelDataString(sRow, sCol++, bean
					.getMasterRecipeRemark());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getInputorName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getInputDate());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdatorCd());
			builder.setExcelDataString(sRow, sCol++, bean.getUpdateName());
			builder.setExcelDataTimestamp(sRow, sCol++, bean.getUpdateDate());

			sRow++;
		}
	}

	/**
	 * 処方詳細をセット
	 * @param tantoName 担当者名
	 */
	private void setDetail(final List<RecipeDetailForReport> detailList) {
		short sRow = TEMP_START_ROW;
		short sCol = TEMP_START_COL;

		/* シートをセット */
		builder.setSheet(DETAIL_DATA_SHEET_NAME);

		for (RecipeDetailForReport bean : detailList) {
			sCol = TEMP_START_COL;

			builder.setExcelDataBigDecimal(sRow, sCol++, bean.getRecipeId());
			builder.setExcelDataString(sRow, sCol++, bean.getGrecipeDetail1());
			builder.setExcelDataString(sRow, sCol++, bean.getGrecipeDetail2());
			builder.setExcelDataString(sRow, sCol++, bean.getGrecipeDetail3());
			builder.setExcelDataString(sRow, sCol++, bean.getGrecipeDetail4());
			builder.setExcelDataString(sRow, sCol++, bean.getMrecipeDetail1());
			builder.setExcelDataString(sRow, sCol++, bean.getMrecipeDetail2());
			builder.setExcelDataString(sRow, sCol++, bean.getMrecipeDetail3());
			builder.setExcelDataString(sRow, sCol++, bean.getMrecipeDetail4());

			sRow++;
		}
	}

	/**
	 * recipeHeaderForReportDaoを設定します。
	 * @param recipeHeaderForReportDao recipeHeaderForReportDao
	 */
	public void setRecipeHeaderForReportDao(
			final RecipeHeaderForReportDao recipeHeaderForReportDao) {
		this.recipeHeaderForReportDao = recipeHeaderForReportDao;
	}

	/**
	 * recipeProcedureForReportDaoを設定します。
	 * @param recipeProcedureForReportDao recipeProcedureForReportDao
	 */
	public void setRecipeProcedureForReportDao(
			final RecipeProcedureForReportDao recipeProcedureForReportDao) {
		this.recipeProcedureForReportDao = recipeProcedureForReportDao;
	}

	/**
	 * recipeFormulaForReportDaoを設定します。
	 * @param recipeFormulaForReportDao recipeFormulaForReportDao
	 */
	public void setRecipeFormulaForReportDao(
			final RecipeFormulaForReportDao recipeFormulaForReportDao) {
		this.recipeFormulaForReportDao = recipeFormulaForReportDao;
	}

	/**
	 * recipeInspectionForReportDaoを設定します。
	 * @param recipeInspectionForReportDao recipeInspectionForReportDao
	 */
	public void setRecipeInspectionForReportDao(
			final RecipeInspectionForReportDao recipeInspectionForReportDao) {
		this.recipeInspectionForReportDao = recipeInspectionForReportDao;
	}

	/**
	 * recipeAsprovaForReportDaoを設定します。
	 * @param recipeAsprovaForReportDao recipeAsprovaForReportDao
	 */
	public void setRecipeAsprovaForReportDao(
			final RecipeAsprovaForReportDao recipeAsprovaForReportDao) {
		this.recipeAsprovaForReportDao = recipeAsprovaForReportDao;
	}

	/**
	 * recipeRemarkForReportDaoを設定します。
	 * @param recipeRemarkForReportDao recipeRemarkForReportDao
	 */
	public void setRecipeRemarkForReportDao(
			final RecipeRemarkForReportDao recipeRemarkForReportDao) {
		this.recipeRemarkForReportDao = recipeRemarkForReportDao;
	}

	/**
	 * recipeDetailForReportDaoを設定します。
	 * @param recipeDetailForReportDao recipeDetailForReportDao
	 */
	public void setRecipeDetailForReportDao(
			final RecipeDetailForReportDao recipeDetailForReportDao) {
		this.recipeDetailForReportDao = recipeDetailForReportDao;
	}

}
