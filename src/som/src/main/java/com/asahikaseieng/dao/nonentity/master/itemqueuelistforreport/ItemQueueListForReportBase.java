/*
 * Created on 2009/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ItemQueueListForReportクラス.
 * @author t0011036
 */
public class ItemQueueListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション activeDate */
	public static final String activeDate_COLUMN = "ACTIVE_DATE";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション activate */
	public static final String activate_COLUMN = "ACTIVATE";

	/** COLUMNアノテーション itemType */
	public static final String itemType_COLUMN = "ITEM_TYPE";

	/** COLUMNアノテーション itemSubName */
	public static final String itemSubName_COLUMN = "ITEM_SUB_NAME";

	/** COLUMNアノテーション productDivision */
	public static final String productDivision_COLUMN = "PRODUCT_DIVISION";

	/** COLUMNアノテーション articleDivision */
	public static final String articleDivision_COLUMN = "ARTICLE_DIVISION";

	/** COLUMNアノテーション purchaseDivision */
	public static final String purchaseDivision_COLUMN = "PURCHASE_DIVISION";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション parentItemName */
	public static final String parentItemName_COLUMN = "PARENT_ITEM_NAME";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション itemCategoryName */
	public static final String itemCategoryName_COLUMN = "ITEM_CATEGORY_NAME";

	/** COLUMNアノテーション phantomDivision */
	public static final String phantomDivision_COLUMN = "PHANTOM_DIVISION";

	/** COLUMNアノテーション spotDivision */
	public static final String spotDivision_COLUMN = "SPOT_DIVISION";

	/** COLUMNアノテーション stockDivision */
	public static final String stockDivision_COLUMN = "STOCK_DIVISION";

	/** COLUMNアノテーション bulkDivision */
	public static final String bulkDivision_COLUMN = "BULK_DIVISION";

	/** COLUMNアノテーション defaultLocation */
	public static final String defaultLocation_COLUMN = "DEFAULT_LOCATION";

	/** COLUMNアノテーション defaultLocationName */
	public static final String defaultLocationName_COLUMN = "DEFAULT_LOCATION_NAME";

	/** COLUMNアノテーション costDivision */
	public static final String costDivision_COLUMN = "COST_DIVISION";

	/** COLUMNアノテーション lotDivision */
	public static final String lotDivision_COLUMN = "LOT_DIVISION";

	/** COLUMNアノテーション newFlg */
	public static final String newFlg_COLUMN = "NEW_FLG";

	/** COLUMNアノテーション researchItem */
	public static final String researchItem_COLUMN = "RESEARCH_ITEM";

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";
	
	/** COLUMNアノテーション salesTaxCategory */
	public static final String salesTaxCategory_COLUMN = "SALES_TAX_CATEGORY";
	
	/** COLUMNアノテーション purchaseTaxCategory */
	public static final String purchaseTaxCategory_COLUMN = "PURCHASE_TAX_CATEGORY";
	
	/** COLUMNアノテーション itemDivision */
	public static final String itemDivision_COLUMN = "ITEM_DIVISION";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション orderLocation */
	public static final String orderLocation_COLUMN = "ORDER_LOCATION";

	/** COLUMNアノテーション orderLocationName */
	public static final String orderLocationName_COLUMN = "ORDER_LOCATION_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション numberOfInsertions */
	public static final String numberOfInsertions_COLUMN = "NUMBER_OF_INSERTIONS";

	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション operationManagementName */
	public static final String operationManagementName_COLUMN = "OPERATION_MANAGEMENT_NAME";

	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";

	/** COLUMNアノテーション unitOfStockControlName */
	public static final String unitOfStockControlName_COLUMN = "UNIT_OF_STOCK_CONTROL_NAME";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション fractionManagementName */
	public static final String fractionManagementName_COLUMN = "FRACTION_MANAGEMENT_NAME";

	/** COLUMNアノテーション kgConversionCoefficient */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	/** COLUMNアノテーション waterDivision */
	public static final String waterDivision_COLUMN = "WATER_DIVISION";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	/** COLUMNアノテーション developmentRequestNo */
	public static final String developmentRequestNo_COLUMN = "DEVELOPMENT_REQUEST_NO";

	/** COLUMNアノテーション expireMonths */
	public static final String expireMonths_COLUMN = "EXPIRE_MONTHS";

	/** COLUMNアノテーション contractMonths */
	public static final String contractMonths_COLUMN = "CONTRACT_MONTHS";

	/** COLUMNアノテーション orderInfo */
	public static final String orderInfo_COLUMN = "ORDER_INFO";

	/** COLUMNアノテーション remarks */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション applicationLaw */
	public static final String applicationLaw_COLUMN = "APPLICATION_LAW";

	/** COLUMNアノテーション originalItemCd */
	public static final String originalItemCd_COLUMN = "ORIGINAL_ITEM_CD";

	/** COLUMNアノテーション originalItemVersion */
	public static final String originalItemVersion_COLUMN = "ORIGINAL_ITEM_VERSION";

	/** COLUMNアノテーション customerItemCd */
	public static final String customerItemCd_COLUMN = "CUSTOMER_ITEM_CD";

	/** COLUMNアノテーション abcDivision */
	public static final String abcDivision_COLUMN = "ABC_DIVISION";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション priceCalcDivision */
	public static final String priceCalcDivision_COLUMN = "PRICE_CALC_DIVISION";

	/** COLUMNアノテーション sellingPrice */
	public static final String sellingPrice_COLUMN = "SELLING_PRICE";

	/** COLUMNアノテーション deliveryTime */
	public static final String deliveryTime_COLUMN = "DELIVERY_TIME";

	/** COLUMNアノテーション certificationAttach */
	public static final String certificationAttach_COLUMN = "CERTIFICATION_ATTACH";

	/** COLUMNアノテーション deliveryLeadTime */
	public static final String deliveryLeadTime_COLUMN = "DELIVERY_LEAD_TIME";

	/** COLUMNアノテーション safetyLeadTime */
	public static final String safetyLeadTime_COLUMN = "SAFETY_LEAD_TIME";

	/** COLUMNアノテーション paletteProducts */
	public static final String paletteProducts_COLUMN = "PALETTE_PRODUCTS";

	/** COLUMNアノテーション janCd */
	public static final String janCd_COLUMN = "JAN_CD";

	/** COLUMNアノテーション itfCd */
	public static final String itfCd_COLUMN = "ITF_CD";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsName */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	/** COLUMNアノテーション financialClassCd */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション financialClassName */
	public static final String financialClassName_COLUMN = "FINANCIAL_CLASS_NAME";

	/** COLUMNアノテーション keepDivision */
	public static final String keepDivision_COLUMN = "KEEP_DIVISION";

	/** COLUMNアノテーション dailyProduction */
	public static final String dailyProduction_COLUMN = "DAILY_PRODUCTION";

	/** COLUMNアノテーション serverDivision */
	public static final String serverDivision_COLUMN = "SERVER_DIVISION";

	/** COLUMNアノテーション productionCycle */
	public static final String productionCycle_COLUMN = "PRODUCTION_CYCLE";

	/** COLUMNアノテーション enphasisDivision */
	public static final String enphasisDivision_COLUMN = "ENPHASIS_DIVISION";

	/** COLUMNアノテーション cockDivision */
	public static final String cockDivision_COLUMN = "COCK_DIVISION";

	/** COLUMNアノテーション planFlg */
	public static final String planFlg_COLUMN = "PLAN_FLG";

	/** COLUMNアノテーション serialnoFlg */
	public static final String serialnoFlg_COLUMN = "SERIALNO_FLG";

	/** COLUMNアノテーション inspectionCategory */
	public static final String inspectionCategory_COLUMN = "INSPECTION_CATEGORY";

	/** COLUMNアノテーション productionLeadTime */
	public static final String productionLeadTime_COLUMN = "PRODUCTION_LEAD_TIME";

	/** COLUMNアノテーション productionCategory */
	public static final String productionCategory_COLUMN = "PRODUCTION_CATEGORY";

	/** COLUMNアノテーション orderPattern */
	public static final String orderPattern_COLUMN = "ORDER_PATTERN";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション inspectionDays */
	public static final String inspectionDays_COLUMN = "INSPECTION_DAYS";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション planDivision */
	public static final String planDivision_COLUMN = "PLAN_DIVISION";

	/** COLUMNアノテーション orderPoint */
	public static final String orderPoint_COLUMN = "ORDER_POINT";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション supplierItemCd */
	public static final String supplierItemCd_COLUMN = "SUPPLIER_ITEM_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション purchaseLeadTime */
	public static final String purchaseLeadTime_COLUMN = "PURCHASE_LEAD_TIME";

	/** COLUMNアノテーション purchasePrice */
	public static final String purchasePrice_COLUMN = "PURCHASE_PRICE";

	/** COLUMNアノテーション purchaseTrigger */
	public static final String purchaseTrigger_COLUMN = "PURCHASE_TRIGGER";

	/** COLUMNアノテーション extraRatio */
	public static final String extraRatio_COLUMN = "EXTRA_RATIO";

	/** COLUMNアノテーション multiSupplierDivision */
	public static final String multiSupplierDivision_COLUMN = "MULTI_SUPPLIER_DIVISION";

	/** COLUMNアノテーション inspectionType */
	public static final String inspectionType_COLUMN = "INSPECTION_TYPE";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	/** COLUMNアノテーション suppliedGoodsDivision */
	public static final String suppliedGoodsDivision_COLUMN = "SUPPLIED_GOODS_DIVISION";

	/** COLUMNアノテーション leaseDrumFlag */
	public static final String leaseDrumFlag_COLUMN = "LEASE_DRUM_FLAG";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション lorryDivision */
	public static final String lorryDivision_COLUMN = "LORRY_DIVISION";

	/** COLUMNアノテーション indicateOrder */
	public static final String indicateOrder_COLUMN = "INDICATE_ORDER";

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション calcValue */
	public static final String calcValue_COLUMN = "CALC_VALUE";

	/** COLUMNアノテーション indicateValue */
	public static final String indicateValue_COLUMN = "INDICATE_VALUE";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション recipeType */
	public static final String recipeType_COLUMN = "RECIPE_TYPE";

	/** COLUMNアノテーション recipeDescription */
	public static final String recipeDescription_COLUMN = "RECIPE_DESCRIPTION";

	/** COLUMNアノテーション recipeMemo */
	public static final String recipeMemo_COLUMN = "RECIPE_MEMO";

	/** COLUMNアノテーション recipeStatus */
	public static final String recipeStatus_COLUMN = "RECIPE_STATUS";

	/** COLUMNアノテーション recipeUse */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	/** COLUMNアノテーション recipePriority */
	public static final String recipePriority_COLUMN = "RECIPE_PRIORITY";

	/** COLUMNアノテーション originalRecipeId */
	public static final String originalRecipeId_COLUMN = "ORIGINAL_RECIPE_ID";

	/** COLUMNアノテーション minQty */
	public static final String minQty_COLUMN = "MIN_QTY";

	/** COLUMNアノテーション maxQty */
	public static final String maxQty_COLUMN = "MAX_QTY";

	/** COLUMNアノテーション stdQty */
	public static final String stdQty_COLUMN = "STD_QTY";

	/** COLUMNアノテーション unitQty */
	public static final String unitQty_COLUMN = "UNIT_QTY";

	/** COLUMNアノテーション processLoss */
	public static final String processLoss_COLUMN = "PROCESS_LOSS";

	/** COLUMNアノテーション startDate */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション deleteFlag */
	public static final String deleteFlag_COLUMN = "DELETE_FLAG";

	/** COLUMNアノテーション printFlag */
	public static final String printFlag_COLUMN = "PRINT_FLAG";

	/** COLUMNアノテーション printDate */
	public static final String printDate_COLUMN = "PRINT_DATE";

	/** COLUMNアノテーション printTantoCd */
	public static final String printTantoCd_COLUMN = "PRINT_TANTO_CD";

	/** COLUMNアノテーション printTantoName */
	public static final String printTantoName_COLUMN = "PRINT_TANTO_NAME";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvalTantoCd */
	public static final String approvalTantoCd_COLUMN = "APP_TANTO_CD";

	/** COLUMNアノテーション approvalTantoName */
	public static final String approvalTantoName_COLUMN = "APP_TANTO_NAME";

	/** COLUMNアノテーション approvalDate */
	public static final String approvalDate_COLUMN = "APP_DATE";

	/** COLUMNアノテーション lastApprovalTantoCd */
	public static final String lastApprovalTantoCd_COLUMN = "LAST_APP_TANTO_CD";

	/** COLUMNアノテーション lastApprovalTantoName */
	public static final String lastApprovalTantoName_COLUMN = "LAST_APP_TANTO_NAME";

	/** COLUMNアノテーション lastApprovalDate */
	public static final String lastApprovalDate_COLUMN = "LAST_APP_DATE";

	/** COLUMNアノテーション recipeName */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	/** COLUMNアノテーション labelCd */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelName */
	public static final String labelName_COLUMN = "LABEL_NAME";

	/** COLUMNアノテーション labelPath */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	/** COLUMNアノテーション commonCd */
	public static final String commonCd_COLUMN = "COMMON_CD";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション reason */
	public static final String reason_COLUMN = "REASON";

	//
	// インスタンスフィールド
	//

	/* 基本 */
	private String itemCd;

	private java.math.BigDecimal version;

	private String itemName;

	private java.math.BigDecimal status;

	private java.sql.Timestamp activeDate;

	private String statusName;

	private String activate;

	private java.math.BigDecimal itemType;

	private String itemSubName;

	private java.math.BigDecimal productDivision;

	private java.math.BigDecimal articleDivision;

	private java.math.BigDecimal purchaseDivision;

	private String parentItemCd;

	private String parentItemName;

	private String itemCategory;

	private String itemCategoryName;

	private java.math.BigDecimal phantomDivision;

	private java.math.BigDecimal spotDivision;

	private java.math.BigDecimal stockDivision;

	private java.math.BigDecimal bulkDivision;

	private String defaultLocation;

	private String defaultLocationName;

	private java.math.BigDecimal costDivision;

	private java.math.BigDecimal lotDivision;

	private java.math.BigDecimal newFlg;

	private java.math.BigDecimal researchItem;

	private java.math.BigDecimal shipperDivision;

	private java.math.BigDecimal typeDivision;
	
	private java.math.BigDecimal salesTaxCategory;
	
	private java.math.BigDecimal purchaseTaxCategory;
	
	private java.math.BigDecimal itemDivision;

	private String otherCompanyCd1;

	private String orderLocation;

	private String orderLocationName;

	private String styleOfPacking;

	private java.math.BigDecimal numberOfInsertions;

	private java.math.BigDecimal allUpWeight;

	private String unitOfOperationManagement;

	private String operationManagementName;

	private String unitOfStockControl;

	private String unitOfStockControlName;

	private java.math.BigDecimal kgOfFractionManagement;

	private String unitOfFractionManagement;

	private String fractionManagementName;

	private java.math.BigDecimal kgConversionCoefficient;

	private java.math.BigDecimal waterDivision;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	private String developmentRequestNo;

	/* 在庫単価共通 */
	private java.math.BigDecimal expireMonths;

	private java.math.BigDecimal contractMonths;

	private String orderInfo;

	private String remarks;

	private String applicationLaw;

	/* 在庫単価 販売品 */
	private String originalItemCd;

	private java.math.BigDecimal originalItemVersion;

	private String customerItemCd;

	private String abcDivision;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal priceCalcDivision;

	private java.math.BigDecimal sellingPrice;

	private java.math.BigDecimal deliveryTime;

	private java.math.BigDecimal certificationAttach;

	private java.math.BigDecimal deliveryLeadTime;

	private java.math.BigDecimal safetyLeadTime;

	private java.math.BigDecimal paletteProducts;

	private String janCd;

	private String itfCd;

	private String sectionCd;

	private String sectionName;

	private String accountsCd;

	private String accountsName;

	private String financialClassCd;

	private String financialClassName;

	private java.math.BigDecimal keepDivisionl;

	/* 在庫単価 製造品 */
	private java.math.BigDecimal dailyProduction;

	private java.math.BigDecimal serverDivision;

	private java.math.BigDecimal productionCycle;

	private java.math.BigDecimal enphasisDivision;

	private java.math.BigDecimal cockDivision;

	private java.math.BigDecimal planFlg;

	private java.math.BigDecimal serialnoFlg;

	private String inspectionCategory;

	private java.math.BigDecimal productionLeadTime;

	private String productionCategory;

	private java.math.BigDecimal orderPattern;

	private String productionLine;

	private String productionLineName;

	private java.math.BigDecimal inspectionDays;

	private String organizationCd;

	private String organizationName;

	private java.math.BigDecimal planDivision;

	private java.math.BigDecimal orderPoint;

	private java.math.BigDecimal cost;

	/* 在庫単価 購入品 */
	private String venderCd;

	private String venderName1;

	private String supplierItemCd;

	private String deliveryCd;

	private String deliveryName1;

	private java.math.BigDecimal purchaseLeadTime;

	private java.math.BigDecimal purchasePrice;

	private java.math.BigDecimal purchaseTrigger;

	private java.math.BigDecimal extraRatio;

	private java.math.BigDecimal multiSupplierDivision;

	private java.math.BigDecimal inspectionType;

	private String materialMakerName;

	private String suppliedGoodsDivision;

	private java.math.BigDecimal leaseDrumFlag;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal lorryDivision;

	/* 成分 */
	private java.math.BigDecimal indicateOrder;

	private String componentCd;

	private String componentName;

	private java.math.BigDecimal calcValue;

	private String indicateValue;

	/* 関連 */
	private java.math.BigDecimal recipeId;

	private String recipeCd;

	private java.math.BigDecimal recipeVersion;

	private java.math.BigDecimal recipeType;

	private String recipeDescription;

	private String recipeMemo;

	private java.math.BigDecimal recipeStatus;

	private java.math.BigDecimal recipeUse;

	private java.math.BigDecimal recipePriority;

	private java.math.BigDecimal originalRecipeId;

	private java.math.BigDecimal minQty;

	private java.math.BigDecimal maxQty;

	private java.math.BigDecimal stdQty;

	private java.math.BigDecimal unitQty;

	private java.math.BigDecimal processLoss;

	private java.sql.Timestamp startDate;

	private java.sql.Timestamp endDate;

	private java.math.BigDecimal deleteFlag;

	private java.math.BigDecimal printFlag;

	private java.sql.Timestamp printDate;

	private String printTantoCd;

	private String printTantoName;

	private java.math.BigDecimal approvalStatus;

	private String appTantoCd;

	private String appTantoName;

	private java.sql.Timestamp appDate;

	private String lastAppTantoCd;

	private String lastAppTantoName;

	private java.sql.Timestamp lastAppDate;

	private String recipeName;

	/* 技術 */
	private String labelCd;

	private String labelName;

	private String labelPath;

	private String commonCd;

	/* その他 */
	private String remark;

	/* 更新 */
	private java.math.BigDecimal menuId;

	private String reason;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * activeDate取得.
	 * @return activeDate
	 */
	public java.sql.Timestamp getActiveDate() {
		return this.activeDate;
	}

	/**
	 * activeDate設定.
	 * @param activeDate activeDate
	 */
	public void setActiveDate(final java.sql.Timestamp activeDate) {
		this.activeDate = activeDate;
	}

	/**
	 * statusName取得.
	 * @return statusName
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * statusName設定.
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * activate取得.
	 * @return activate
	 */
	public String getActivate() {
		return this.activate;
	}

	/**
	 * activate設定.
	 * @param activate activate
	 */
	public void setActivate(final String activate) {
		this.activate = activate;
	}

	/**
	 * itemType取得.
	 * @return itemType
	 */
	public java.math.BigDecimal getItemType() {
		return this.itemType;
	}

	/**
	 * itemType設定.
	 * @param itemType itemType
	 */
	public void setItemType(final java.math.BigDecimal itemType) {
		this.itemType = itemType;
	}

	/**
	 * itemSubName取得.
	 * @return itemSubName
	 */
	public String getItemSubName() {
		return this.itemSubName;
	}

	/**
	 * itemSubName設定.
	 * @param itemSubName itemSubName
	 */
	public void setItemSubName(final String itemSubName) {
		this.itemSubName = itemSubName;
	}

	/**
	 * productDivision取得.
	 * @return productDivision
	 */
	public java.math.BigDecimal getProductDivision() {
		return this.productDivision;
	}

	/**
	 * productDivision設定.
	 * @param productDivision productDivision
	 */
	public void setProductDivision(final java.math.BigDecimal productDivision) {
		this.productDivision = productDivision;
	}

	/**
	 * articleDivision取得.
	 * @return articleDivision
	 */
	public java.math.BigDecimal getArticleDivision() {
		return this.articleDivision;
	}

	/**
	 * articleDivision設定.
	 * @param articleDivision articleDivision
	 */
	public void setArticleDivision(final java.math.BigDecimal articleDivision) {
		this.articleDivision = articleDivision;
	}

	/**
	 * purchaseDivision取得.
	 * @return purchaseDivision
	 */
	public java.math.BigDecimal getPurchaseDivision() {
		return this.purchaseDivision;
	}

	/**
	 * purchaseDivision設定.
	 * @param purchaseDivision purchaseDivision
	 */
	public void setPurchaseDivision(final java.math.BigDecimal purchaseDivision) {
		this.purchaseDivision = purchaseDivision;
	}

	/**
	 * parentItemCd取得.
	 * @return parentItemCd
	 */
	public String getParentItemCd() {
		return this.parentItemCd;
	}

	/**
	 * parentItemCd設定.
	 * @param parentItemCd parentItemCd
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * parentItemName取得.
	 * @return parentItemName
	 */
	public String getParentItemName() {
		return this.parentItemName;
	}

	/**
	 * parentItemName設定.
	 * @param parentItemName parentItemName
	 */
	public void setParentItemName(final String parentItemName) {
		this.parentItemName = parentItemName;
	}

	/**
	 * itemCategory取得.
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * itemCategory設定.
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * itemCategoryName取得.
	 * @return itemCategoryName
	 */
	public String getItemCategoryName() {
		return this.itemCategoryName;
	}

	/**
	 * itemCategoryName設定.
	 * @param itemCategoryName itemCategoryName
	 */
	public void setItemCategoryName(final String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	/**
	 * phantomDivision取得.
	 * @return phantomDivision
	 */
	public java.math.BigDecimal getPhantomDivision() {
		return this.phantomDivision;
	}

	/**
	 * phantomDivision設定.
	 * @param phantomDivision phantomDivision
	 */
	public void setPhantomDivision(final java.math.BigDecimal phantomDivision) {
		this.phantomDivision = phantomDivision;
	}

	/**
	 * spotDivision取得.
	 * @return spotDivision
	 */
	public java.math.BigDecimal getSpotDivision() {
		return this.spotDivision;
	}

	/**
	 * spotDivision設定.
	 * @param spotDivision spotDivision
	 */
	public void setSpotDivision(final java.math.BigDecimal spotDivision) {
		this.spotDivision = spotDivision;
	}

	/**
	 * stockDivision取得.
	 * @return stockDivision
	 */
	public java.math.BigDecimal getStockDivision() {
		return this.stockDivision;
	}

	/**
	 * stockDivision設定.
	 * @param stockDivision stockDivision
	 */
	public void setStockDivision(final java.math.BigDecimal stockDivision) {
		this.stockDivision = stockDivision;
	}

	/**
	 * bulkDivision取得.
	 * @return bulkDivision
	 */
	public java.math.BigDecimal getBulkDivision() {
		return this.bulkDivision;
	}

	/**
	 * bulkDivision設定.
	 * @param bulkDivision bulkDivision
	 */
	public void setBulkDivision(final java.math.BigDecimal bulkDivision) {
		this.bulkDivision = bulkDivision;
	}

	/**
	 * defaultLocation取得.
	 * @return defaultLocation
	 */
	public String getDefaultLocation() {
		return this.defaultLocation;
	}

	/**
	 * defaultLocation設定.
	 * @param defaultLocation defaultLocation
	 */
	public void setDefaultLocation(final String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}

	/**
	 * defaultLocationName取得.
	 * @return defaultLocationName
	 */
	public String getDefaultLocationName() {
		return this.defaultLocationName;
	}

	/**
	 * defaultLocationName設定.
	 * @param defaultLocationName defaultLocationName
	 */
	public void setDefaultLocationName(final String defaultLocationName) {
		this.defaultLocationName = defaultLocationName;
	}

	/**
	 * costDivision取得.
	 * @return costDivision
	 */
	public java.math.BigDecimal getCostDivision() {
		return this.costDivision;
	}

	/**
	 * costDivision設定.
	 * @param costDivision costDivision
	 */
	public void setCostDivision(final java.math.BigDecimal costDivision) {
		this.costDivision = costDivision;
	}

	/**
	 * lotDivision取得.
	 * @return lotDivision
	 */
	public java.math.BigDecimal getLotDivision() {
		return this.lotDivision;
	}

	/**
	 * lotDivision設定.
	 * @param lotDivision lotDivision
	 */
	public void setLotDivision(final java.math.BigDecimal lotDivision) {
		this.lotDivision = lotDivision;
	}

	/**
	 * newFlg取得.
	 * @return newFlg
	 */
	public java.math.BigDecimal getNewFlg() {
		return this.newFlg;
	}

	/**
	 * newFlg設定.
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final java.math.BigDecimal newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * researchItem取得.
	 * @return researchItem
	 */
	public java.math.BigDecimal getResearchItem() {
		return this.researchItem;
	}

	/**
	 * researchItem設定.
	 * @param researchItem researchItem
	 */
	public void setResearchItem(final java.math.BigDecimal researchItem) {
		this.researchItem = researchItem;
	}

	/**
	 * shipperDivision取得.
	 * @return shipperDivision
	 */
	public java.math.BigDecimal getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * shipperDivision設定.
	 * @param shipperDivision shipperDivision
	 */
	public void setShipperDivision(final java.math.BigDecimal shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public java.math.BigDecimal getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final java.math.BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}
	
	
	/**
	 * salesTaxCategory取得.
	 * @return salesTaxCategory
	 */
	public java.math.BigDecimal getSalesTaxCategory() {
		return this.salesTaxCategory;
	}

	/**
	 * salesTaxCategory設定.
	 * @param salesTaxCategory salesTaxCategory
	 */
	public void setSalesTaxCategory(final java.math.BigDecimal salesTaxCategory) {
		this.salesTaxCategory = salesTaxCategory;
	}
	
	/**
	 * purchaseTaxCategory取得.
	 * @return purchaseTaxCategory
	 */
	public java.math.BigDecimal getPurchaseTaxCategory() {
		return this.purchaseTaxCategory;
	}

	/**
	 * purchaseTaxCategory設定.
	 * @param purchaseTaxCategory purchaseTaxCategory
	 */
	public void setPurchaseTaxCategory(final java.math.BigDecimal purchaseTaxCategory) {
		this.purchaseTaxCategory = purchaseTaxCategory;
	}


	/**
	 * itemDivisionを取得します。
	 * @return itemDivision
	 */
	public java.math.BigDecimal getItemDivision() {
		return itemDivision;
	}

	/**
	 * itemDivisionを設定します。
	 * @param itemDivision itemDivision
	 */
	public void setItemDivision(java.math.BigDecimal itemDivision) {
		this.itemDivision = itemDivision;
	}

	/**
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * orderLocation取得.
	 * @return orderLocation
	 */
	public String getOrderLocation() {
		return this.orderLocation;
	}

	/**
	 * orderLocation設定.
	 * @param orderLocation orderLocation
	 */
	public void setOrderLocation(final String orderLocation) {
		this.orderLocation = orderLocation;
	}

	/**
	 * orderLocationName取得.
	 * @return orderLocationName
	 */
	public String getOrderLocationName() {
		return this.orderLocationName;
	}

	/**
	 * orderLocationName設定.
	 * @param orderLocationName orderLocationName
	 */
	public void setOrderLocationName(final String orderLocationName) {
		this.orderLocationName = orderLocationName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * numberOfInsertions取得.
	 * @return numberOfInsertions
	 */
	public java.math.BigDecimal getNumberOfInsertions() {
		return this.numberOfInsertions;
	}

	/**
	 * numberOfInsertions設定.
	 * @param numberOfInsertions numberOfInsertions
	 */
	public void setNumberOfInsertions(
			final java.math.BigDecimal numberOfInsertions) {
		this.numberOfInsertions = numberOfInsertions;
	}

	/**
	 * allUpWeight取得.
	 * @return allUpWeight
	 */
	public java.math.BigDecimal getAllUpWeight() {
		return this.allUpWeight;
	}

	/**
	 * allUpWeight設定.
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(final java.math.BigDecimal allUpWeight) {
		this.allUpWeight = allUpWeight;
	}

	/**
	 * unitOfOperationManagement取得.
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagement設定.
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * operationManagementName取得.
	 * @return operationManagementName
	 */
	public String getOperationManagementName() {
		return this.operationManagementName;
	}

	/**
	 * operationManagementName設定.
	 * @param operationManagementName operationManagementName
	 */
	public void setOperationManagementName(final String operationManagementName) {
		this.operationManagementName = operationManagementName;
	}

	/**
	 * unitOfStockControl取得.
	 * @return unitOfStockControl
	 */
	public String getUnitOfStockControl() {
		return this.unitOfStockControl;
	}

	/**
	 * unitOfStockControl設定.
	 * @param unitOfStockControl unitOfStockControl
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}

	/**
	 * unitOfStockControlName取得.
	 * @return unitOfStockControlName
	 */
	public String getUnitOfStockControlName() {
		return this.unitOfStockControlName;
	}

	/**
	 * unitOfStockControlName設定.
	 * @param unitOfStockControlName unitOfStockControlName
	 */
	public void setUnitOfStockControlName(final String unitOfStockControlName) {
		this.unitOfStockControlName = unitOfStockControlName;
	}

	/**
	 * kgOfFractionManagement取得.
	 * @return kgOfFractionManagement
	 */
	public java.math.BigDecimal getKgOfFractionManagement() {
		return this.kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagement設定.
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(
			final java.math.BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * unitOfFractionManagement取得.
	 * @return unitOfFractionManagement
	 */
	public String getUnitOfFractionManagement() {
		return this.unitOfFractionManagement;
	}

	/**
	 * unitOfFractionManagement設定.
	 * @param unitOfFractionManagement unitOfFractionManagement
	 */
	public void setUnitOfFractionManagement(
			final String unitOfFractionManagement) {
		this.unitOfFractionManagement = unitOfFractionManagement;
	}

	/**
	 * fractionManagementName取得.
	 * @return fractionManagementName
	 */
	public String getFractionManagementName() {
		return this.fractionManagementName;
	}

	/**
	 * fractionManagementName設定.
	 * @param fractionManagementName fractionManagementName
	 */
	public void setFractionManagementName(final String fractionManagementName) {
		this.fractionManagementName = fractionManagementName;
	}

	/**
	 * kgConversionCoefficient取得.
	 * @return kgConversionCoefficient
	 */
	public java.math.BigDecimal getKgConversionCoefficient() {
		return this.kgConversionCoefficient;
	}

	/**
	 * kgConversionCoefficient設定.
	 * @param kgConversionCoefficient kgConversionCoefficient
	 */
	public void setKgConversionCoefficient(
			final java.math.BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
	}

	/**
	 * waterDivision取得.
	 * @return waterDivision
	 */
	public java.math.BigDecimal getWaterDivision() {
		return this.waterDivision;
	}

	/**
	 * waterDivision設定.
	 * @param waterDivision waterDivision
	 */
	public void setWaterDivision(final java.math.BigDecimal waterDivision) {
		this.waterDivision = waterDivision;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * developmentRequestNo取得.
	 * @return developmentRequestNo
	 */
	public String getDevelopmentRequestNo() {
		return this.developmentRequestNo;
	}

	/**
	 * developmentRequestNo設定.
	 * @param developmentRequestNo developmentRequestNo
	 */
	public void setDevelopmentRequestNo(final String developmentRequestNo) {
		this.developmentRequestNo = developmentRequestNo;
	}

	/**
	 * applicationLawを取得します。
	 * @return applicationLaw
	 */
	public String getApplicationLaw() {
		return applicationLaw;
	}

	/**
	 * applicationLawを設定します。
	 * @param applicationLaw applicationLaw
	 */
	public void setApplicationLaw(final String applicationLaw) {
		this.applicationLaw = applicationLaw;
	}

	/**
	 * contractMonthsを取得します。
	 * @return contractMonths
	 */
	public java.math.BigDecimal getContractMonths() {
		return contractMonths;
	}

	/**
	 * contractMonthsを設定します。
	 * @param contractMonths contractMonths
	 */
	public void setContractMonths(final java.math.BigDecimal contractMonths) {
		this.contractMonths = contractMonths;
	}

	/**
	 * expireMonthsを取得します。
	 * @return expireMonths
	 */
	public java.math.BigDecimal getExpireMonths() {
		return expireMonths;
	}

	/**
	 * expireMonthsを設定します。
	 * @param expireMonths expireMonths
	 */
	public void setExpireMonths(final java.math.BigDecimal expireMonths) {
		this.expireMonths = expireMonths;
	}

	/**
	 * orderInfoを取得します。
	 * @return orderInfo
	 */
	public String getOrderInfo() {
		return orderInfo;
	}

	/**
	 * orderInfoを設定します。
	 * @param orderInfo orderInfo
	 */
	public void setOrderInfo(final String orderInfo) {
		this.orderInfo = orderInfo;
	}

	/**
	 * remarksを取得します。
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * remarksを設定します。
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * abcDivisionを取得します。
	 * @return abcDivision
	 */
	public String getAbcDivision() {
		return abcDivision;
	}

	/**
	 * abcDivisionを設定します。
	 * @param abcDivision abcDivision
	 */
	public void setAbcDivision(final String abcDivision) {
		this.abcDivision = abcDivision;
	}

	/**
	 * accountsCdを取得します。
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return accountsCd;
	}

	/**
	 * accountsCdを設定します。
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsNameを取得します。
	 * @return accountsName
	 */
	public String getAccountsName() {
		return accountsName;
	}

	/**
	 * accountsNameを設定します。
	 * @param accountsName accountsName
	 */
	public void setAccountsName(final String accountsName) {
		this.accountsName = accountsName;
	}

	/**
	 * certificationAttachを取得します。
	 * @return certificationAttach
	 */
	public java.math.BigDecimal getCertificationAttach() {
		return certificationAttach;
	}

	/**
	 * certificationAttachを設定します。
	 * @param certificationAttach certificationAttach
	 */
	public void setCertificationAttach(
			final java.math.BigDecimal certificationAttach) {
		this.certificationAttach = certificationAttach;
	}

	/**
	 * customerItemCdを取得します。
	 * @return customerItemCd
	 */
	public String getCustomerItemCd() {
		return customerItemCd;
	}

	/**
	 * customerItemCdを設定します。
	 * @param customerItemCd customerItemCd
	 */
	public void setCustomerItemCd(final String customerItemCd) {
		this.customerItemCd = customerItemCd;
	}

	/**
	 * deliveryLeadTimeを取得します。
	 * @return deliveryLeadTime
	 */
	public java.math.BigDecimal getDeliveryLeadTime() {
		return deliveryLeadTime;
	}

	/**
	 * deliveryLeadTimeを設定します。
	 * @param deliveryLeadTime deliveryLeadTime
	 */
	public void setDeliveryLeadTime(final java.math.BigDecimal deliveryLeadTime) {
		this.deliveryLeadTime = deliveryLeadTime;
	}

	/**
	 * deliveryTimeを取得します。
	 * @return deliveryTime
	 */
	public java.math.BigDecimal getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * deliveryTimeを設定します。
	 * @param deliveryTime deliveryTime
	 */
	public void setDeliveryTime(final java.math.BigDecimal deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * financialClassCdを取得します。
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return financialClassCd;
	}

	/**
	 * financialClassCdを設定します。
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * financialClassNameを取得します。
	 * @return financialClassName
	 */
	public String getFinancialClassName() {
		return financialClassName;
	}

	/**
	 * financialClassNameを設定します。
	 * @param financialClassName financialClassName
	 */
	public void setFinancialClassName(final String financialClassName) {
		this.financialClassName = financialClassName;
	}

	/**
	 * itfCdを取得します。
	 * @return itfCd
	 */
	public String getItfCd() {
		return itfCd;
	}

	/**
	 * itfCdを設定します。
	 * @param itfCd itfCd
	 */
	public void setItfCd(final String itfCd) {
		this.itfCd = itfCd;
	}

	/**
	 * janCdを取得します。
	 * @return janCd
	 */
	public String getJanCd() {
		return janCd;
	}

	/**
	 * janCdを設定します。
	 * @param janCd janCd
	 */
	public void setJanCd(final String janCd) {
		this.janCd = janCd;
	}

	/**
	 * keepDivisionlを取得します。
	 * @return keepDivisionl
	 */
	public java.math.BigDecimal getKeepDivisionl() {
		return keepDivisionl;
	}

	/**
	 * keepDivisionlを設定します。
	 * @param keepDivisionl keepDivisionl
	 */
	public void setKeepDivisionl(final java.math.BigDecimal keepDivisionl) {
		this.keepDivisionl = keepDivisionl;
	}

	/**
	 * originalItemCdを取得します。
	 * @return originalItemCd
	 */
	public String getOriginalItemCd() {
		return originalItemCd;
	}

	/**
	 * originalItemCdを設定します。
	 * @param originalItemCd originalItemCd
	 */
	public void setOriginalItemCd(final String originalItemCd) {
		this.originalItemCd = originalItemCd;
	}

	/**
	 * originalItemVersionを取得します。
	 * @return originalItemVersion
	 */
	public java.math.BigDecimal getOriginalItemVersion() {
		return originalItemVersion;
	}

	/**
	 * originalItemVersionを設定します。
	 * @param originalItemVersion originalItemVersion
	 */
	public void setOriginalItemVersion(
			final java.math.BigDecimal originalItemVersion) {
		this.originalItemVersion = originalItemVersion;
	}

	/**
	 * paletteProductsを取得します。
	 * @return paletteProducts
	 */
	public java.math.BigDecimal getPaletteProducts() {
		return paletteProducts;
	}

	/**
	 * paletteProductsを設定します。
	 * @param paletteProducts paletteProducts
	 */
	public void setPaletteProducts(final java.math.BigDecimal paletteProducts) {
		this.paletteProducts = paletteProducts;
	}

	/**
	 * priceCalcDivisionを取得します。
	 * @return priceCalcDivision
	 */
	public java.math.BigDecimal getPriceCalcDivision() {
		return priceCalcDivision;
	}

	/**
	 * priceCalcDivisionを設定します。
	 * @param priceCalcDivision priceCalcDivision
	 */
	public void setPriceCalcDivision(
			final java.math.BigDecimal priceCalcDivision) {
		this.priceCalcDivision = priceCalcDivision;
	}

	/**
	 * safetyLeadTimeを取得します。
	 * @return safetyLeadTime
	 */
	public java.math.BigDecimal getSafetyLeadTime() {
		return safetyLeadTime;
	}

	/**
	 * safetyLeadTimeを設定します。
	 * @param safetyLeadTime safetyLeadTime
	 */
	public void setSafetyLeadTime(final java.math.BigDecimal safetyLeadTime) {
		this.safetyLeadTime = safetyLeadTime;
	}

	/**
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionNameを取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * sectionNameを設定します。
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * sellingPriceを取得します。
	 * @return sellingPrice
	 */
	public java.math.BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	/**
	 * sellingPriceを設定します。
	 * @param sellingPrice sellingPrice
	 */
	public void setSellingPrice(final java.math.BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/**
	 * taxDivisionを取得します。
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return taxDivision;
	}

	/**
	 * taxDivisionを設定します。
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * taxRatioを取得します。
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return taxRatio;
	}

	/**
	 * taxRatioを設定します。
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * cockDivisionを取得します。
	 * @return cockDivision
	 */
	public java.math.BigDecimal getCockDivision() {
		return cockDivision;
	}

	/**
	 * cockDivisionを設定します。
	 * @param cockDivision cockDivision
	 */
	public void setCockDivision(final java.math.BigDecimal cockDivision) {
		this.cockDivision = cockDivision;
	}

	/**
	 * costを取得します。
	 * @return cost
	 */
	public java.math.BigDecimal getCost() {
		return cost;
	}

	/**
	 * costを設定します。
	 * @param cost cost
	 */
	public void setCost(final java.math.BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * dailyProductionを取得します。
	 * @return dailyProduction
	 */
	public java.math.BigDecimal getDailyProduction() {
		return dailyProduction;
	}

	/**
	 * dailyProductionを設定します。
	 * @param dailyProduction dailyProduction
	 */
	public void setDailyProduction(final java.math.BigDecimal dailyProduction) {
		this.dailyProduction = dailyProduction;
	}

	/**
	 * enphasisDivisionを取得します。
	 * @return enphasisDivision
	 */
	public java.math.BigDecimal getEnphasisDivision() {
		return enphasisDivision;
	}

	/**
	 * enphasisDivisionを設定します。
	 * @param enphasisDivision enphasisDivision
	 */
	public void setEnphasisDivision(final java.math.BigDecimal enphasisDivision) {
		this.enphasisDivision = enphasisDivision;
	}

	/**
	 * inspectionCategoryを取得します。
	 * @return inspectionCategory
	 */
	public String getInspectionCategory() {
		return inspectionCategory;
	}

	/**
	 * inspectionCategoryを設定します。
	 * @param inspectionCategory inspectionCategory
	 */
	public void setInspectionCategory(final String inspectionCategory) {
		this.inspectionCategory = inspectionCategory;
	}

	/**
	 * inspectionDaysを取得します。
	 * @return inspectionDays
	 */
	public java.math.BigDecimal getInspectionDays() {
		return inspectionDays;
	}

	/**
	 * inspectionDaysを設定します。
	 * @param inspectionDays inspectionDays
	 */
	public void setInspectionDays(final java.math.BigDecimal inspectionDays) {
		this.inspectionDays = inspectionDays;
	}

	/**
	 * orderPatternを取得します。
	 * @return orderPattern
	 */
	public java.math.BigDecimal getOrderPattern() {
		return orderPattern;
	}

	/**
	 * orderPatternを設定します。
	 * @param orderPattern orderPattern
	 */
	public void setOrderPattern(final java.math.BigDecimal orderPattern) {
		this.orderPattern = orderPattern;
	}

	/**
	 * orderPointを取得します。
	 * @return orderPoint
	 */
	public java.math.BigDecimal getOrderPoint() {
		return orderPoint;
	}

	/**
	 * orderPointを設定します。
	 * @param orderPoint orderPoint
	 */
	public void setOrderPoint(final java.math.BigDecimal orderPoint) {
		this.orderPoint = orderPoint;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * planDivisionを取得します。
	 * @return planDivision
	 */
	public java.math.BigDecimal getPlanDivision() {
		return planDivision;
	}

	/**
	 * planDivisionを設定します。
	 * @param planDivision planDivision
	 */
	public void setPlanDivision(final java.math.BigDecimal planDivision) {
		this.planDivision = planDivision;
	}

	/**
	 * planFlgを取得します。
	 * @return planFlg
	 */
	public java.math.BigDecimal getPlanFlg() {
		return planFlg;
	}

	/**
	 * planFlgを設定します。
	 * @param planFlg planFlg
	 */
	public void setPlanFlg(final java.math.BigDecimal planFlg) {
		this.planFlg = planFlg;
	}

	/**
	 * productionCategoryを取得します。
	 * @return productionCategory
	 */
	public String getProductionCategory() {
		return productionCategory;
	}

	/**
	 * productionCategoryを設定します。
	 * @param productionCategory productionCategory
	 */
	public void setProductionCategory(final String productionCategory) {
		this.productionCategory = productionCategory;
	}

	/**
	 * productionCycleを取得します。
	 * @return productionCycle
	 */
	public java.math.BigDecimal getProductionCycle() {
		return productionCycle;
	}

	/**
	 * productionCycleを設定します。
	 * @param productionCycle productionCycle
	 */
	public void setProductionCycle(final java.math.BigDecimal productionCycle) {
		this.productionCycle = productionCycle;
	}

	/**
	 * productionLeadTimeを取得します。
	 * @return productionLeadTime
	 */
	public java.math.BigDecimal getProductionLeadTime() {
		return productionLeadTime;
	}

	/**
	 * productionLeadTimeを設定します。
	 * @param productionLeadTime productionLeadTime
	 */
	public void setProductionLeadTime(
			final java.math.BigDecimal productionLeadTime) {
		this.productionLeadTime = productionLeadTime;
	}

	/**
	 * productionLineを取得します。
	 * @return productionLine
	 */
	public String getProductionLine() {
		return productionLine;
	}

	/**
	 * productionLineを設定します。
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * productionLineNameを取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * productionLineNameを設定します。
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * serialnoFlgを取得します。
	 * @return serialnoFlg
	 */
	public java.math.BigDecimal getSerialnoFlg() {
		return serialnoFlg;
	}

	/**
	 * serialnoFlgを設定します。
	 * @param serialnoFlg serialnoFlg
	 */
	public void setSerialnoFlg(final java.math.BigDecimal serialnoFlg) {
		this.serialnoFlg = serialnoFlg;
	}

	/**
	 * serverDivisionを取得します。
	 * @return serverDivision
	 */
	public java.math.BigDecimal getServerDivision() {
		return serverDivision;
	}

	/**
	 * serverDivisionを設定します。
	 * @param serverDivision serverDivision
	 */
	public void setServerDivision(final java.math.BigDecimal serverDivision) {
		this.serverDivision = serverDivision;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * extraRatioを取得します。
	 * @return extraRatio
	 */
	public java.math.BigDecimal getExtraRatio() {
		return extraRatio;
	}

	/**
	 * extraRatioを設定します。
	 * @param extraRatio extraRatio
	 */
	public void setExtraRatio(final java.math.BigDecimal extraRatio) {
		this.extraRatio = extraRatio;
	}

	/**
	 * inspectionTypeを取得します。
	 * @return inspectionType
	 */
	public java.math.BigDecimal getInspectionType() {
		return inspectionType;
	}

	/**
	 * inspectionTypeを設定します。
	 * @param inspectionType inspectionType
	 */
	public void setInspectionType(final java.math.BigDecimal inspectionType) {
		this.inspectionType = inspectionType;
	}

	/**
	 * leaseDrumFlagを取得します。
	 * @return leaseDrumFlag
	 */
	public java.math.BigDecimal getLeaseDrumFlag() {
		return leaseDrumFlag;
	}

	/**
	 * leaseDrumFlagを設定します。
	 * @param leaseDrumFlag leaseDrumFlag
	 */
	public void setLeaseDrumFlag(final java.math.BigDecimal leaseDrumFlag) {
		this.leaseDrumFlag = leaseDrumFlag;
	}

	/**
	 * lorryDivisionを取得します。
	 * @return lorryDivision
	 */
	public java.math.BigDecimal getLorryDivision() {
		return lorryDivision;
	}

	/**
	 * lorryDivisionを設定します。
	 * @param lorryDivision lorryDivision
	 */
	public void setLorryDivision(final java.math.BigDecimal lorryDivision) {
		this.lorryDivision = lorryDivision;
	}

	/**
	 * materialMakerNameを取得します。
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return materialMakerName;
	}

	/**
	 * materialMakerNameを設定します。
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
	}

	/**
	 * multiSupplierDivisionを取得します。
	 * @return multiSupplierDivision
	 */
	public java.math.BigDecimal getMultiSupplierDivision() {
		return multiSupplierDivision;
	}

	/**
	 * multiSupplierDivisionを設定します。
	 * @param multiSupplierDivision multiSupplierDivision
	 */
	public void setMultiSupplierDivision(
			final java.math.BigDecimal multiSupplierDivision) {
		this.multiSupplierDivision = multiSupplierDivision;
	}

	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return orderQty;
	}

	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * purchaseLeadTimeを取得します。
	 * @return purchaseLeadTime
	 */
	public java.math.BigDecimal getPurchaseLeadTime() {
		return purchaseLeadTime;
	}

	/**
	 * purchaseLeadTimeを設定します。
	 * @param purchaseLeadTime purchaseLeadTime
	 */
	public void setPurchaseLeadTime(final java.math.BigDecimal purchaseLeadTime) {
		this.purchaseLeadTime = purchaseLeadTime;
	}

	/**
	 * purchasePriceを取得します。
	 * @return purchasePrice
	 */
	public java.math.BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * purchasePriceを設定します。
	 * @param purchasePrice purchasePrice
	 */
	public void setPurchasePrice(final java.math.BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * purchaseTriggerを取得します。
	 * @return purchaseTrigger
	 */
	public java.math.BigDecimal getPurchaseTrigger() {
		return purchaseTrigger;
	}

	/**
	 * purchaseTriggerを設定します。
	 * @param purchaseTrigger purchaseTrigger
	 */
	public void setPurchaseTrigger(final java.math.BigDecimal purchaseTrigger) {
		this.purchaseTrigger = purchaseTrigger;
	}

	/**
	 * suppliedGoodsDivisionを取得します。
	 * @return suppliedGoodsDivision
	 */
	public String getSuppliedGoodsDivision() {
		return suppliedGoodsDivision;
	}

	/**
	 * suppliedGoodsDivisionを設定します。
	 * @param suppliedGoodsDivision suppliedGoodsDivision
	 */
	public void setSuppliedGoodsDivision(final String suppliedGoodsDivision) {
		this.suppliedGoodsDivision = suppliedGoodsDivision;
	}

	/**
	 * supplierItemCdを取得します。
	 * @return supplierItemCd
	 */
	public String getSupplierItemCd() {
		return supplierItemCd;
	}

	/**
	 * supplierItemCdを設定します。
	 * @param supplierItemCd supplierItemCd
	 */
	public void setSupplierItemCd(final String supplierItemCd) {
		this.supplierItemCd = supplierItemCd;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * calcValueを取得します。
	 * @return calcValue
	 */
	public java.math.BigDecimal getCalcValue() {
		return calcValue;
	}

	/**
	 * calcValueを設定します。
	 * @param calcValue calcValue
	 */
	public void setCalcValue(final java.math.BigDecimal calcValue) {
		this.calcValue = calcValue;
	}

	/**
	 * componentCdを取得します。
	 * @return componentCd
	 */
	public String getComponentCd() {
		return componentCd;
	}

	/**
	 * componentCdを設定します。
	 * @param componentCd componentCd
	 */
	public void setComponentCd(final String componentCd) {
		this.componentCd = componentCd;
	}

	/**
	 * componentNameを取得します。
	 * @return componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * componentNameを設定します。
	 * @param componentName componentName
	 */
	public void setComponentName(final String componentName) {
		this.componentName = componentName;
	}

	/**
	 * indicateOrderを取得します。
	 * @return indicateOrder
	 */
	public java.math.BigDecimal getIndicateOrder() {
		return indicateOrder;
	}

	/**
	 * indicateOrderを設定します。
	 * @param indicateOrder indicateOrder
	 */
	public void setIndicateOrder(final java.math.BigDecimal indicateOrder) {
		this.indicateOrder = indicateOrder;
	}

	/**
	 * indicateValueを取得します。
	 * @return indicateValue
	 */
	public String getIndicateValue() {
		return indicateValue;
	}

	/**
	 * indicateValueを設定します。
	 * @param indicateValue indicateValue
	 */
	public void setIndicateValue(final String indicateValue) {
		this.indicateValue = indicateValue;
	}

	/**
	 * appDateを取得します。
	 * @return appDate
	 */
	public java.sql.Timestamp getAppDate() {
		return appDate;
	}

	/**
	 * appDateを設定します。
	 * @param appDate appDate
	 */
	public void setAppDate(final java.sql.Timestamp appDate) {
		this.appDate = appDate;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * appTantoCdを取得します。
	 * @return appTantoCd
	 */
	public String getAppTantoCd() {
		return appTantoCd;
	}

	/**
	 * appTantoCdを設定します。
	 * @param appTantoCd appTantoCd
	 */
	public void setAppTantoCd(final String appTantoCd) {
		this.appTantoCd = appTantoCd;
	}

	/**
	 * appTantoNameを取得します。
	 * @return appTantoName
	 */
	public String getAppTantoName() {
		return appTantoName;
	}

	/**
	 * appTantoNameを設定します。
	 * @param appTantoName appTantoName
	 */
	public void setAppTantoName(final String appTantoName) {
		this.appTantoName = appTantoName;
	}

	/**
	 * deleteFlagを取得します。
	 * @return deleteFlag
	 */
	public java.math.BigDecimal getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * deleteFlagを設定します。
	 * @param deleteFlag deleteFlag
	 */
	public void setDeleteFlag(final java.math.BigDecimal deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * endDateを取得します。
	 * @return endDate
	 */
	public java.sql.Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * endDateを設定します。
	 * @param endDate endDate
	 */
	public void setEndDate(final java.sql.Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * lastAppDateを取得します。
	 * @return lastAppDate
	 */
	public java.sql.Timestamp getLastAppDate() {
		return lastAppDate;
	}

	/**
	 * lastAppDateを設定します。
	 * @param lastAppDate lastAppDate
	 */
	public void setLastAppDate(final java.sql.Timestamp lastAppDate) {
		this.lastAppDate = lastAppDate;
	}

	/**
	 * lastAppTantoCdを取得します。
	 * @return lastAppTantoCd
	 */
	public String getLastAppTantoCd() {
		return lastAppTantoCd;
	}

	/**
	 * lastAppTantoCdを設定します。
	 * @param lastAppTantoCd lastAppTantoCd
	 */
	public void setLastAppTantoCd(final String lastAppTantoCd) {
		this.lastAppTantoCd = lastAppTantoCd;
	}

	/**
	 * lastAppTantoNameを取得します。
	 * @return lastAppTantoName
	 */
	public String getLastAppTantoName() {
		return lastAppTantoName;
	}

	/**
	 * lastAppTantoNameを設定します。
	 * @param lastAppTantoName lastAppTantoName
	 */
	public void setLastAppTantoName(final String lastAppTantoName) {
		this.lastAppTantoName = lastAppTantoName;
	}

	/**
	 * maxQtyを取得します。
	 * @return maxQty
	 */
	public java.math.BigDecimal getMaxQty() {
		return maxQty;
	}

	/**
	 * maxQtyを設定します。
	 * @param maxQty maxQty
	 */
	public void setMaxQty(final java.math.BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	/**
	 * minQtyを取得します。
	 * @return minQty
	 */
	public java.math.BigDecimal getMinQty() {
		return minQty;
	}

	/**
	 * minQtyを設定します。
	 * @param minQty minQty
	 */
	public void setMinQty(final java.math.BigDecimal minQty) {
		this.minQty = minQty;
	}

	/**
	 * originalRecipeIdを取得します。
	 * @return originalRecipeId
	 */
	public java.math.BigDecimal getOriginalRecipeId() {
		return originalRecipeId;
	}

	/**
	 * originalRecipeIdを設定します。
	 * @param originalRecipeId originalRecipeId
	 */
	public void setOriginalRecipeId(final java.math.BigDecimal originalRecipeId) {
		this.originalRecipeId = originalRecipeId;
	}

	/**
	 * printDateを取得します。
	 * @return printDate
	 */
	public java.sql.Timestamp getPrintDate() {
		return printDate;
	}

	/**
	 * printDateを設定します。
	 * @param printDate printDate
	 */
	public void setPrintDate(final java.sql.Timestamp printDate) {
		this.printDate = printDate;
	}

	/**
	 * printFlagを取得します。
	 * @return printFlag
	 */
	public java.math.BigDecimal getPrintFlag() {
		return printFlag;
	}

	/**
	 * printFlagを設定します。
	 * @param printFlag printFlag
	 */
	public void setPrintFlag(final java.math.BigDecimal printFlag) {
		this.printFlag = printFlag;
	}

	/**
	 * printTantoCdを取得します。
	 * @return printTantoCd
	 */
	public String getPrintTantoCd() {
		return printTantoCd;
	}

	/**
	 * printTantoCdを設定します。
	 * @param printTantoCd printTantoCd
	 */
	public void setPrintTantoCd(final String printTantoCd) {
		this.printTantoCd = printTantoCd;
	}

	/**
	 * printTantoNameを取得します。
	 * @return printTantoName
	 */
	public String getPrintTantoName() {
		return printTantoName;
	}

	/**
	 * printTantoNameを設定します。
	 * @param printTantoName printTantoName
	 */
	public void setPrintTantoName(final String printTantoName) {
		this.printTantoName = printTantoName;
	}

	/**
	 * processLossを取得します。
	 * @return processLoss
	 */
	public java.math.BigDecimal getProcessLoss() {
		return processLoss;
	}

	/**
	 * processLossを設定します。
	 * @param processLoss processLoss
	 */
	public void setProcessLoss(final java.math.BigDecimal processLoss) {
		this.processLoss = processLoss;
	}

	/**
	 * recipeCdを取得します。
	 * @return recipeCd
	 */
	public String getRecipeCd() {
		return recipeCd;
	}

	/**
	 * recipeCdを設定します。
	 * @param recipeCd recipeCd
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * recipeDescriptionを取得します。
	 * @return recipeDescription
	 */
	public String getRecipeDescription() {
		return recipeDescription;
	}

	/**
	 * recipeDescriptionを設定します。
	 * @param recipeDescription recipeDescription
	 */
	public void setRecipeDescription(final String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	/**
	 * recipeIdを取得します。
	 * @return recipeId
	 */
	public java.math.BigDecimal getRecipeId() {
		return recipeId;
	}

	/**
	 * recipeIdを設定します。
	 * @param recipeId recipeId
	 */
	public void setRecipeId(final java.math.BigDecimal recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * recipeMemoを取得します。
	 * @return recipeMemo
	 */
	public String getRecipeMemo() {
		return recipeMemo;
	}

	/**
	 * recipeMemoを設定します。
	 * @param recipeMemo recipeMemo
	 */
	public void setRecipeMemo(final String recipeMemo) {
		this.recipeMemo = recipeMemo;
	}

	/**
	 * recipeNameを取得します。
	 * @return recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * recipeNameを設定します。
	 * @param recipeName recipeName
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * recipePriorityを取得します。
	 * @return recipePriority
	 */
	public java.math.BigDecimal getRecipePriority() {
		return recipePriority;
	}

	/**
	 * recipePriorityを設定します。
	 * @param recipePriority recipePriority
	 */
	public void setRecipePriority(final java.math.BigDecimal recipePriority) {
		this.recipePriority = recipePriority;
	}

	/**
	 * recipeStatusを取得します。
	 * @return recipeStatus
	 */
	public java.math.BigDecimal getRecipeStatus() {
		return recipeStatus;
	}

	/**
	 * recipeStatusを設定します。
	 * @param recipeStatus recipeStatus
	 */
	public void setRecipeStatus(final java.math.BigDecimal recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * recipeTypeを取得します。
	 * @return recipeType
	 */
	public java.math.BigDecimal getRecipeType() {
		return recipeType;
	}

	/**
	 * recipeTypeを設定します。
	 * @param recipeType recipeType
	 */
	public void setRecipeType(final java.math.BigDecimal recipeType) {
		this.recipeType = recipeType;
	}

	/**
	 * recipeUseを取得します。
	 * @return recipeUse
	 */
	public java.math.BigDecimal getRecipeUse() {
		return recipeUse;
	}

	/**
	 * recipeUseを設定します。
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final java.math.BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
	}

	/**
	 * recipeVersionを取得します。
	 * @return recipeVersion
	 */
	public java.math.BigDecimal getRecipeVersion() {
		return recipeVersion;
	}

	/**
	 * recipeVersionを設定します。
	 * @param recipeVersion recipeVersion
	 */
	public void setRecipeVersion(final java.math.BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
	}

	/**
	 * startDateを取得します。
	 * @return startDate
	 */
	public java.sql.Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * startDateを設定します。
	 * @param startDate startDate
	 */
	public void setStartDate(final java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * stdQtyを取得します。
	 * @return stdQty
	 */
	public java.math.BigDecimal getStdQty() {
		return stdQty;
	}

	/**
	 * stdQtyを設定します。
	 * @param stdQty stdQty
	 */
	public void setStdQty(final java.math.BigDecimal stdQty) {
		this.stdQty = stdQty;
	}

	/**
	 * unitQtyを取得します。
	 * @return unitQty
	 */
	public java.math.BigDecimal getUnitQty() {
		return unitQty;
	}

	/**
	 * unitQtyを設定します。
	 * @param unitQty unitQty
	 */
	public void setUnitQty(final java.math.BigDecimal unitQty) {
		this.unitQty = unitQty;
	}

	/**
	 * commonCdを取得します。
	 * @return commonCd
	 */
	public String getCommonCd() {
		return commonCd;
	}

	/**
	 * commonCdを設定します。
	 * @param commonCd commonCd
	 */
	public void setCommonCd(final String commonCd) {
		this.commonCd = commonCd;
	}

	/**
	 * labelCdを取得します。
	 * @return labelCd
	 */
	public String getLabelCd() {
		return labelCd;
	}

	/**
	 * labelCdを設定します。
	 * @param labelCd labelCd
	 */
	public void setLabelCd(final String labelCd) {
		this.labelCd = labelCd;
	}

	/**
	 * labelNameを取得します。
	 * @return labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * labelNameを設定します。
	 * @param labelName labelName
	 */
	public void setLabelName(final String labelName) {
		this.labelName = labelName;
	}

	/**
	 * labelPathを取得します。
	 * @return labelPath
	 */
	public String getLabelPath() {
		return labelPath;
	}

	/**
	 * labelPathを設定します。
	 * @param labelPath labelPath
	 */
	public void setLabelPath(final String labelPath) {
		this.labelPath = labelPath;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * menuIdを取得します。
	 * @return menuId
	 */
	public java.math.BigDecimal getMenuId() {
		return menuId;
	}

	/**
	 * menuIdを設定します。
	 * @param menuId menuId
	 */
	public void setMenuId(final java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * reasonを取得します。
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * reasonを設定します。
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
