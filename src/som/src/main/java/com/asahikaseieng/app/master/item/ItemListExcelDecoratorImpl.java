/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReport;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReportDao;
import com.asahikaseieng.poi.ReportExcelBuilder;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ExcelDecorator実装クラス.品目マスタ一覧
 * @author t0011036
 */
public class ItemListExcelDecoratorImpl implements ItemListExcelDecorator {

	/** テンポラリシート開始行位置 */
	protected static final short TEMP_START_ROW = 1;

	/** テンポラリシート開始列位置 */
	protected static final short TEMP_START_COL = 0;

	/** ビルダー */
	private ReportExcelBuilder builder = new ReportExcelBuilder();

	private ItemListLogic itemListLogic;

	private ItemQueueListForReportDao itemQueueListForReportDao;

	/**
	 * コンストラクタ
	 */
	public ItemListExcelDecoratorImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public FileDownloadInfo createReport(
			final ItemQueueListConditionForReport condition) {
		/* テンプレートをセット */
		builder.setWorkbookUrl("itemqueuelist");

		/* 基本をセット */
		setItem(condition);

		/* 在庫単価共通をセット */
		setCommon(condition);

		/* 在庫単価販売品をセット */
		setArticle(condition);

		/* 在庫単価製造品をセット */
		setProduct(condition);

		/* 在庫単価購入品をセット */
		setPurchase(condition);

		/* 成分をセット */
		setComponent(condition);

		/* 関連をセット */
		setRecipe(condition);

		/* 技術をセット */
		setLabel(condition);

		/* その他をセット */
		setRemark(condition);

		/* 更新をセット */
		setHistory(condition);

		/* ダウンロードファイル情報を返す */
		return new FileDownloadInfo(builder.getDefaultFileName(), builder
				.create());
	}

	/**
	 * 基本をセット
	 * @param condition 検索条件
	 */
	private void setItem(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		// List<ItemQueueListForReport> list = new
		// ArrayList<ItemQueueListForReport>();
		//
		// /* 有効品目があるかチェック */
		// ItemDetail beanItem = itemListLogic.getItemEntity(condition
		// .getSrhItemCd());
		//
		// /* 明細取得 */
		// if (beanItem == null) {
		// list = itemListLogic.getInactivateListForReport(condition);
		// } else {
		// list = itemListLogic.getActivateListForReport(condition);
		// }

		List<ItemQueueListForReport> list = itemListLogic
				.getListForReport(condition);

		/* シートをセット */
		builder.setSheet("itemlist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getItemType());
			// 2021/12/14 帳票Excelに有効日付が表示されない不具合を修正
//			builder.setExcelDataString(rowNum, colNum++, bean.getStrActiveDate());
			builder.setExcelDataString(rowNum, colNum++,AecDateUtils.dateFormat(bean.getActiveDate(), "yyyy/MM/dd"));
			
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean.getActivate());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemSubName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProductDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getArticleDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseDivision());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getParentItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getParentItemName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getItemCategory());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getItemCategoryName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPhantomDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSpotDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getStockDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getBulkDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDefaultLocation());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDefaultLocationName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCostDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLotDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getNewFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getResearchItem());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getShipperDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTypeDivision());
			//190820 軽減税率
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getSalesTaxCategory());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPurchaseTaxCategory());
			//190820 end
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getItemDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOtherCompanyCd1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderLocation());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOrderLocationName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getStyleOfPacking());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getNumberOfInsertions());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getAllUpWeight());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUnitOfOperationManagement());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOperationManagementName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUnitOfStockControl());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUnitOfStockControlName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getKgOfFractionManagement());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getUnitOfFractionManagement());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFractionManagementName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getKgConversionCoefficient());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getWaterDivision());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDevelopmentRequestNo());

			rowNum++;
		}
	}

	/**
	 * 在庫単価共通をセット
	 * @param condition 検索条件
	 */
	private void setCommon(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getCommonAttributeQueueListForReport(condition);

		/* シートをセット */
		builder.setSheet("commonlist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExpireMonths());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getContractMonths());
			builder.setExcelDataString(rowNum, colNum++, bean.getOrderInfo());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemarks());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getApplicationLaw());
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
	 * 在庫単価販売品をセット
	 * @param condition 検索条件
	 */
	private void setArticle(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getArticleAttributeQueueListForReport(condition);

		/* シートをセット */
		builder.setSheet("articlelist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getOriginalItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOriginalItemVersion());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getCustomerItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getAbcDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDefaultLocation());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxDivision());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getTaxRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPriceCalcDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSellingPrice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliveryTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCertificationAttach());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeliveryLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSafetyLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExpireMonths());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getContractMonths());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPaletteProducts());
			builder.setExcelDataString(rowNum, colNum++, bean.getJanCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItfCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountsCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountsName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFinancialClassCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getFinancialClassName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getKeepDivisionl());
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
	 * 在庫単価製造品をセット
	 * @param condition 検索条件
	 */
	private void setProduct(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getProductAttributeQueueListForReport(condition);

		/* シートをセット */
		builder.setSheet("productlist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDailyProduction());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getServerDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProductionCycle());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getEnphasisDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCockDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getPlanFlg());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSerialnoFlg());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getInspectionCategory());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProductionLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSafetyLeadTime());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionCategory());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderPattern());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLine());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLineName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectionDays());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPlanDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderPoint());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCost());

			rowNum++;
		}
	}

	/**
	 * 在庫単価購入品をセット
	 * @param condition 検索条件
	 */
	private void setPurchase(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getPurchaseAttributeQueueListForReport(condition);

		/* シートをセット */
		builder.setSheet("purchaselist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getStatusName());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getParentItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getVenderName1());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSupplierItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getDeliveryCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getDeliveryName1());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getSafetyLeadTime());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPriceCalcDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchasePrice());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getTaxDivision());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getTaxRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPurchaseTrigger());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExtraRatio());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getMultiSupplierDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getServerDivision());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCertificationAttach());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCockDivision());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getInspectionCategory());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getInspectionType());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getExpireMonths());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getContractMonths());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getMaterialMakerName());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getSuppliedGoodsDivision());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getAccountsCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAccountsName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLeaseDrumFlag());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOrderPoint());
			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getOrderQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getLorryDivision());
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
	 * 成分をセット
	 * @param condition 検索条件
	 */
	private void setComponent(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getComponentInformationQueueListForReport(condition);

		/* シートをセット */
		builder.setSheet("componentlist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getIndicateOrder());
			builder.setExcelDataString(rowNum, colNum++, bean.getComponentCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getComponentName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getCalcValue());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getIndicateValue());
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
	 * 関連をセット
	 * @param condition 検索条件
	 */
	private void setRecipe(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getRecipeHeaderListForReport(condition);

		/* シートをセット */
		builder.setSheet("recipelist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder
					.setExcelDataBigDecimal(rowNum, colNum++, bean
							.getRecipeId());
			builder.setExcelDataString(rowNum, colNum++, bean.getRecipeCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipeVersion());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipeType());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getRecipeDescription());
			builder.setExcelDataString(rowNum, colNum++, bean.getRecipeMemo());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipeStatus());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipeUse());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getRecipePriority());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getOriginalRecipeId());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLine());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getProductionLineName());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMinQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMaxQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getStdQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getUnitQty());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getCost());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getProcessLoss());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getStartDate());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getEndDate());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getDeleteFlag());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getPrintFlag());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getPrintDate());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getPrintTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getPrintTantoName());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean
					.getApprovalStatus());
			builder.setExcelDataString(rowNum, colNum++, bean.getAppTantoCd());
			builder
					.setExcelDataString(rowNum, colNum++, bean
							.getAppTantoName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean.getAppDate());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getLastAppTantoCd());
			builder.setExcelDataString(rowNum, colNum++, bean
					.getLastAppTantoName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getLastAppDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getRecipeName());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getSectionName());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());

			rowNum++;
		}
	}

	/**
	 * 技術をセット
	 * @param condition 検索条件
	 */
	private void setLabel(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getLabelListForReport(condition);

		/* シートをセット */
		builder.setSheet("labellist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getLabelCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getLabelName());
			builder.setExcelDataString(rowNum, colNum++, bean.getLabelPath());
			builder.setExcelDataString(rowNum, colNum++, bean.getCommonCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getInputorName());
			builder
					.setExcelDataTimestamp(rowNum, colNum++, bean
							.getInputDate());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getUpdatorName());
			builder.setExcelDataTimestamp(rowNum, colNum++, bean
					.getUpdateDate());

			rowNum++;
		}
	}

	/**
	 * その他をセット
	 * @param condition 検索条件
	 */
	private void setRemark(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getItemRemarkListForReport(condition);

		/* シートをセット */
		builder.setSheet("remarklist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getVersion());
			builder.setExcelDataString(rowNum, colNum++, bean.getRemark());
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
	 * 更新をセット
	 * @param condition 検索条件
	 */
	private void setHistory(final ItemQueueListConditionForReport condition) {
		short rowNum = TEMP_START_ROW;
		short colNum = TEMP_START_COL;

		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getChangeHistoryListForReport(condition);

		/* シートをセット */
		builder.setSheet("historylist");

		/* リスト部 */
		for (ItemQueueListForReport bean : list) {
			colNum = TEMP_START_COL;

			builder.setExcelDataBigDecimal(rowNum, colNum++, bean.getMenuId());
			builder.setExcelDataString(rowNum, colNum++, bean.getItemCd());
			builder.setExcelDataString(rowNum, colNum++, bean.getReason());
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

	/* -------------------- setter -------------------- */

	/**
	 * itemListLogicを設定します。
	 * @param itemListLogic itemListLogic
	 */
	public void setItemListLogic(final ItemListLogic itemListLogic) {
		this.itemListLogic = itemListLogic;
	}

	/**
	 * itemQueueListForReportDaoを設定します。
	 * @param itemQueueListForReportDao itemQueueListForReportDao
	 */
	public void setItemQueueListForReportDao(
			final ItemQueueListForReportDao itemQueueListForReportDao) {
		this.itemQueueListForReportDao = itemQueueListForReportDao;
	}
}
