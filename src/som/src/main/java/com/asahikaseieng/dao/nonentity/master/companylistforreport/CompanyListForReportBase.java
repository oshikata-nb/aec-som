/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CompanyListForReportクラス.
 * @author t0011036
 */
public class CompanyListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CompanyListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション homeName1 */
	public static final String homeName1_COLUMN = "HOME_NAME1";

	/** COLUMNアノテーション homeName2 */
	public static final String homeName2_COLUMN = "HOME_NAME2";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション representRole */
	public static final String representRole_COLUMN = "REPRESENT_ROLE";

	/** COLUMNアノテーション representPerson */
	public static final String representPerson_COLUMN = "REPRESENT_PERSON";

	/** COLUMNアノテーション invoiceNo */
	public static final String invoiceNo_COLUMN = "INVOICE_NO";

	/** COLUMNアノテーション settlement */
	public static final String settlement_COLUMN = "SETTLEMENT";

	/** COLUMNアノテーション closingDay */
	public static final String closingDay_COLUMN = "CLOSING_DAY";

	/** COLUMNアノテーション managementStartDate */
	public static final String managementStartDate_COLUMN = "MANAGEMENT_START_DATE";

	/** COLUMNアノテーション reportCycle */
	public static final String reportCycle_COLUMN = "REPORT_CYCLE";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション taxRoundup */
	public static final String taxRoundup_COLUMN = "TAX_ROUNDUP";

	/** COLUMNアノテーション taxRoundupUnit */
	public static final String taxRoundupUnit_COLUMN = "TAX_ROUNDUP_UNIT";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション stockDiscountRate */
	public static final String stockDiscountRate_COLUMN = "STOCK_DISCOUNT_RATE";

	/** COLUMNアノテーション purchaseDiscountRate */
	public static final String purchaseDiscountRate_COLUMN = "PURCHASE_DISCOUNT_RATE";

	/** COLUMNアノテーション roundup */
	public static final String roundup_COLUMN = "ROUNDUP";

	/** COLUMNアノテーション roundupUnit */
	public static final String roundupUnit_COLUMN = "ROUNDUP_UNIT";

	/** COLUMNアノテーション productAutoCharge */
	public static final String productAutoCharge_COLUMN = "PRODUCT_AUTO_CHARGE";

	/** COLUMNアノテーション provideAutoCharge */
	public static final String provideAutoCharge_COLUMN = "PROVIDE_AUTO_CHARGE";

	/** COLUMNアノテーション salesRoundup */
	public static final String salesRoundup_COLUMN = "SALES_ROUNDUP";

	/** COLUMNアノテーション salesRoundupUnit */
	public static final String salesRoundupUnit_COLUMN = "SALES_ROUNDUP_UNIT";

	/** COLUMNアノテーション purchaseRoundup */
	public static final String purchaseRoundup_COLUMN = "PURCHASE_ROUNDUP";

	/** COLUMNアノテーション purchaseRoundupUnit */
	public static final String purchaseRoundupUnit_COLUMN = "PURCHASE_ROUNDUP_UNIT";

	/** COLUMNアノテーション unitpriceRoundup */
	public static final String unitpriceRoundup_COLUMN = "UNITPRICE_ROUNDUP";

	/** COLUMNアノテーション unitpriceRoundupUnit */
	public static final String unitpriceRoundupUnit_COLUMN = "UNITPRICE_ROUNDUP_UNIT";

	/** COLUMNアノテーション blendQtyRoundup */
	public static final String blendQtyRoundup_COLUMN = "BLEND_QTY_ROUNDUP";

	/** COLUMNアノテーション blendQtyRoundupUnit */
	public static final String blendQtyRoundupUnit_COLUMN = "BLEND_QTY_ROUNDUP_UNIT";

	/** COLUMNアノテーション mixRateRoundup */
	public static final String mixRateRoundup_COLUMN = "MIX_RATE_ROUNDUP";

	/** COLUMNアノテーション mixRateRoundupUnit */
	public static final String mixRateRoundupUnit_COLUMN = "MIX_RATE_ROUNDUP_UNIT";

	/** COLUMNアノテーション adjRoundup */
	public static final String adjRoundup_COLUMN = "ADJ_ROUNDUP";

	/** COLUMNアノテーション adjRoundupUnit */
	public static final String adjRoundupUnit_COLUMN = "ADJ_ROUNDUP_UNIT";

	/** COLUMNアノテーション quantityDecimalpoint1 */
	public static final String quantityDecimalpoint1_COLUMN = "QUANTITY_DECIMALPOINT1";

	/** COLUMNアノテーション quantityDecimalpoint2 */
	public static final String quantityDecimalpoint2_COLUMN = "QUANTITY_DECIMALPOINT2";

	/** COLUMNアノテーション costName1 */
	public static final String costName1_COLUMN = "COST_NAME1";

	/** COLUMNアノテーション costName2 */
	public static final String costName2_COLUMN = "COST_NAME2";

	/** COLUMNアノテーション costName3 */
	public static final String costName3_COLUMN = "COST_NAME3";

	/** COLUMNアノテーション costName4 */
	public static final String costName4_COLUMN = "COST_NAME4";

	/** COLUMNアノテーション costName5 */
	public static final String costName5_COLUMN = "COST_NAME5";

	/** COLUMNアノテーション costName6 */
	public static final String costName6_COLUMN = "COST_NAME6";

	/** COLUMNアノテーション costName7 */
	public static final String costName7_COLUMN = "COST_NAME7";

	/** COLUMNアノテーション workReport */
	public static final String workReport_COLUMN = "WORK_REPORT";

	/** COLUMNアノテーション productionPlanAutoCreate */
	public static final String productionPlanAutoCreate_COLUMN = "PRODUCTION_PLAN_AUTO_CREATE";

	/** COLUMNアノテーション amountDecimalpoint */
	public static final String amountDecimalpoint_COLUMN = "AMOUNT_DECIMALPOINT";

	/** COLUMNアノテーション proofManagement */
	public static final String proofManagement_COLUMN = "PROOF_MANAGEMENT";

	/** COLUMNアノテーション manufacturingSubcontractCost */
	public static final String manufacturingSubcontractCost_COLUMN = "MANUFACTURING_SUBCONTRACT_COST";

	/** COLUMNアノテーション paymentUpdate */
	public static final String paymentUpdate_COLUMN = "PAYMENT_UPDATE";

	/** COLUMNアノテーション orderSaveperiod */
	public static final String orderSaveperiod_COLUMN = "ORDER_SAVEPERIOD";

	/** COLUMNアノテーション buySubconstractSaveperiod */
	public static final String buySubconstractSaveperiod_COLUMN = "BUY_SUBCONSTRACT_SAVEPERIOD";

	/** COLUMNアノテーション manufactureSaveperiod */
	public static final String manufactureSaveperiod_COLUMN = "MANUFACTURE_SAVEPERIOD";

	/** COLUMNアノテーション workResultSaveperiod */
	public static final String workResultSaveperiod_COLUMN = "WORK_RESULT_SAVEPERIOD";

	/** COLUMNアノテーション salesSaveperiod */
	public static final String salesSaveperiod_COLUMN = "SALES_SAVEPERIOD";

	/** COLUMNアノテーション stockSaveperiod */
	public static final String stockSaveperiod_COLUMN = "STOCK_SAVEPERIOD";

	/** COLUMNアノテーション paymentSaveperiod */
	public static final String paymentSaveperiod_COLUMN = "PAYMENT_SAVEPERIOD";

	/** COLUMNアノテーション badSaveperiod */
	public static final String badSaveperiod_COLUMN = "BAD_SAVEPERIOD";

	/** COLUMNアノテーション monthlySumSaveperiod */
	public static final String monthlySumSaveperiod_COLUMN = "MONTHLY_SUM_SAVEPERIOD";

	/** COLUMNアノテーション proofSaveperiod */
	public static final String proofSaveperiod_COLUMN = "PROOF_SAVEPERIOD";

	/** COLUMNアノテーション processManagementSaveperiod */
	public static final String processManagementSaveperiod_COLUMN = "PROCESS_MANAGEMENT_SAVEPERIOD";

	/** COLUMNアノテーション dummySaveperiod1 */
	public static final String dummySaveperiod1_COLUMN = "DUMMY_SAVEPERIOD1";

	/** COLUMNアノテーション dummySaveperiod2 */
	public static final String dummySaveperiod2_COLUMN = "DUMMY_SAVEPERIOD2";

	/** COLUMNアノテーション dummySaveperiod3 */
	public static final String dummySaveperiod3_COLUMN = "DUMMY_SAVEPERIOD3";

	/** COLUMNアノテーション dummySaveperiod4 */
	public static final String dummySaveperiod4_COLUMN = "DUMMY_SAVEPERIOD4";

	/** COLUMNアノテーション workerManagement */
	public static final String workerManagement_COLUMN = "WORKER_MANAGEMENT";

	/** COLUMNアノテーション workOpen */
	public static final String workOpen_COLUMN = "WORK_OPEN";

	/** COLUMNアノテーション viewProductPlan */
	public static final String viewProductPlan_COLUMN = "VIEW_PRODUCT_PLAN";

	/** COLUMNアノテーション orderDivisionName0 */
	public static final String orderDivisionName0_COLUMN = "ORDER_DIVISION_NAME0";

	/** COLUMNアノテーション orderDivisionName1 */
	public static final String orderDivisionName1_COLUMN = "ORDER_DIVISION_NAME1";

	/** COLUMNアノテーション orderDivisionName2 */
	public static final String orderDivisionName2_COLUMN = "ORDER_DIVISION_NAME2";

	/** COLUMNアノテーション orderDivisionName3 */
	public static final String orderDivisionName3_COLUMN = "ORDER_DIVISION_NAME3";

	/** COLUMNアノテーション orderDivisionName4 */
	public static final String orderDivisionName4_COLUMN = "ORDER_DIVISION_NAME4";

	/** COLUMNアノテーション orderDivisionName5 */
	public static final String orderDivisionName5_COLUMN = "ORDER_DIVISION_NAME5";

	/** COLUMNアノテーション orderDivisionName6 */
	public static final String orderDivisionName6_COLUMN = "ORDER_DIVISION_NAME6";

	/** COLUMNアノテーション orderDivisionName7 */
	public static final String orderDivisionName7_COLUMN = "ORDER_DIVISION_NAME7";

	/** COLUMNアノテーション orderDivisionName8 */
	public static final String orderDivisionName8_COLUMN = "ORDER_DIVISION_NAME8";

	/** COLUMNアノテーション orderDivisionName9 */
	public static final String orderDivisionName9_COLUMN = "ORDER_DIVISION_NAME9";

	/** COLUMNアノテーション inspectRank1 */
	public static final String inspectRank1_COLUMN = "INSPECT_RANK1";

	/** COLUMNアノテーション inspectFrequency1 */
	public static final String inspectFrequency1_COLUMN = "INSPECT_FREQUENCY1";

	/** COLUMNアノテーション inspectRank2 */
	public static final String inspectRank2_COLUMN = "INSPECT_RANK2";

	/** COLUMNアノテーション inspectFrequency2 */
	public static final String inspectFrequency2_COLUMN = "INSPECT_FREQUENCY2";

	/** COLUMNアノテーション inspectRank3 */
	public static final String inspectRank3_COLUMN = "INSPECT_RANK3";

	/** COLUMNアノテーション inspectFrequency3 */
	public static final String inspectFrequency3_COLUMN = "INSPECT_FREQUENCY3";

	/** COLUMNアノテーション inspectRank4 */
	public static final String inspectRank4_COLUMN = "INSPECT_RANK4";

	/** COLUMNアノテーション inspectFrequency4 */
	public static final String inspectFrequency4_COLUMN = "INSPECT_FREQUENCY4";

	/** COLUMNアノテーション inspectRank5 */
	public static final String inspectRank5_COLUMN = "INSPECT_RANK5";

	/** COLUMNアノテーション inspectFrequency5 */
	public static final String inspectFrequency5_COLUMN = "INSPECT_FREQUENCY5";

	/** COLUMNアノテーション inspectRank6 */
	public static final String inspectRank6_COLUMN = "INSPECT_RANK6";

	/** COLUMNアノテーション inspectFrequency6 */
	public static final String inspectFrequency6_COLUMN = "INSPECT_FREQUENCY6";

	/** COLUMNアノテーション inspectRank7 */
	public static final String inspectRank7_COLUMN = "INSPECT_RANK7";

	/** COLUMNアノテーション inspectFrequency7 */
	public static final String inspectFrequency7_COLUMN = "INSPECT_FREQUENCY7";

	/** COLUMNアノテーション inspectRank8 */
	public static final String inspectRank8_COLUMN = "INSPECT_RANK8";

	/** COLUMNアノテーション inspectFrequency8 */
	public static final String inspectFrequency8_COLUMN = "INSPECT_FREQUENCY8";

	/** COLUMNアノテーション inspectRank9 */
	public static final String inspectRank9_COLUMN = "INSPECT_RANK9";

	/** COLUMNアノテーション inspectFrequency9 */
	public static final String inspectFrequency9_COLUMN = "INSPECT_FREQUENCY9";

	/** COLUMNアノテーション nonInspectMonth */
	public static final String nonInspectMonth_COLUMN = "NON_INSPECT_MONTH";

	/** COLUMNアノテーション receiveInspreceiptLocation */
	public static final String receiveInspreceiptLocation_COLUMN = "RECEIVE_INSPRECEIPT_LOCATION";

	/** COLUMNアノテーション pickupInspectSheet */
	public static final String pickupInspectSheet_COLUMN = "PICKUP_INSPECT_SHEET";

	/** COLUMNアノテーション changespecManagement */
	public static final String changespecManagement_COLUMN = "CHANGESPEC_MANAGEMENT";

	/** COLUMNアノテーション turnMadeManagement */
	public static final String turnMadeManagement_COLUMN = "TURN_MADE_MANAGEMENT";

	/** COLUMNアノテーション costUpdateManagement */
	public static final String costUpdateManagement_COLUMN = "COST_UPDATE_MANAGEMENT";

	/** COLUMNアノテーション traceManagement */
	public static final String traceManagement_COLUMN = "TRACE_MANAGEMENT";

	/** COLUMNアノテーション processManagementUse */
	public static final String processManagementUse_COLUMN = "PROCESS_MANAGEMENT_USE";

	/** COLUMNアノテーション disbursementManagement */
	public static final String disbursementManagement_COLUMN = "DISBURSEMENT_MANAGEMENT";

	/** COLUMNアノテーション monthlyDivideMethod */
	public static final String monthlyDivideMethod_COLUMN = "MONTHLY_DIVIDE_METHOD";

	/** COLUMNアノテーション firstWeekday */
	public static final String firstWeekday_COLUMN = "FIRST_WEEKDAY";

	/** COLUMNアノテーション newTaxRatio */
	public static final String newTaxRatio_COLUMN = "NEW_TAX_RATIO";

	/** COLUMNアノテーション newTaxApllyDate */
	public static final String newTaxApllyDate_COLUMN = "NEW_TAX_APLLY_DATE";

	/** COLUMNアノテーション creditUpdateDate */
	public static final String creditUpdateDate_COLUMN = "CREDIT_UPDATE_DATE";

	/** COLUMNアノテーション payableUpdateDate */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** COLUMNアノテーション inventoryUpdateDate */
	public static final String inventoryUpdateDate_COLUMN = "INVENTORY_UPDATE_DATE";

	/** COLUMNアノテーション stockUpdateDate */
	public static final String stockUpdateDate_COLUMN = "STOCK_UPDATE_DATE";

	/** COLUMNアノテーション monthlyStockClosingDate */
	public static final String monthlyStockClosingDate_COLUMN = "MONTHLY_STOCK_CLOSING_DATE";

	/** COLUMNアノテーション jseflg1 */
	public static final String jseflg1_COLUMN = "JSEFLG1";

	/** COLUMNアノテーション jseflg2 */
	public static final String jseflg2_COLUMN = "JSEFLG2";

	/** COLUMNアノテーション jseflg3 */
	public static final String jseflg3_COLUMN = "JSEFLG3";

	/** COLUMNアノテーション jseflg4 */
	public static final String jseflg4_COLUMN = "JSEFLG4";

	/** COLUMNアノテーション jseflg5 */
	public static final String jseflg5_COLUMN = "JSEFLG5";

	/** COLUMNアノテーション jsekbn1 */
	public static final String jsekbn1_COLUMN = "JSEKBN1";

	/** COLUMNアノテーション jsekbn2 */
	public static final String jsekbn2_COLUMN = "JSEKBN2";

	/** COLUMNアノテーション jsekbn3 */
	public static final String jsekbn3_COLUMN = "JSEKBN3";

	/** COLUMNアノテーション jsekbn4 */
	public static final String jsekbn4_COLUMN = "JSEKBN4";

	/** COLUMNアノテーション jsekbn5 */
	public static final String jsekbn5_COLUMN = "JSEKBN5";

	/** COLUMNアノテーション jsedate1 */
	public static final String jsedate1_COLUMN = "JSEDATE1";

	/** COLUMNアノテーション jsedate2 */
	public static final String jsedate2_COLUMN = "JSEDATE2";

	/** COLUMNアノテーション jsedate3 */
	public static final String jsedate3_COLUMN = "JSEDATE3";

	/** COLUMNアノテーション jsedate4 */
	public static final String jsedate4_COLUMN = "JSEDATE4";

	/** COLUMNアノテーション jsedate5 */
	public static final String jsedate5_COLUMN = "JSEDATE5";

	/** COLUMNアノテーション jsetime1 */
	public static final String jsetime1_COLUMN = "JSETIME1";

	/** COLUMNアノテーション jsetime2 */
	public static final String jsetime2_COLUMN = "JSETIME2";

	/** COLUMNアノテーション jsetime3 */
	public static final String jsetime3_COLUMN = "JSETIME3";

	/** COLUMNアノテーション jsetime4 */
	public static final String jsetime4_COLUMN = "JSETIME4";

	/** COLUMNアノテーション jsetime5 */
	public static final String jsetime5_COLUMN = "JSETIME5";

	/** COLUMNアノテーション jsenum1 */
	public static final String jsenum1_COLUMN = "JSENUM1";

	/** COLUMNアノテーション jsenum2 */
	public static final String jsenum2_COLUMN = "JSENUM2";

	/** COLUMNアノテーション jsenum3 */
	public static final String jsenum3_COLUMN = "JSENUM3";

	/** COLUMNアノテーション jsenum4 */
	public static final String jsenum4_COLUMN = "JSENUM4";

	/** COLUMNアノテーション jsenum5 */
	public static final String jsenum5_COLUMN = "JSENUM5";

	/** COLUMNアノテーション jsevalue1 */
	public static final String jsevalue1_COLUMN = "JSEVALUE1";

	/** COLUMNアノテーション jsevalue2 */
	public static final String jsevalue2_COLUMN = "JSEVALUE2";

	/** COLUMNアノテーション jsevalue3 */
	public static final String jsevalue3_COLUMN = "JSEVALUE3";

	/** COLUMNアノテーション jsevalue4 */
	public static final String jsevalue4_COLUMN = "JSEVALUE4";

	/** COLUMNアノテーション jsevalue5 */
	public static final String jsevalue5_COLUMN = "JSEVALUE5";

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション centralBranchCd */
	public static final String centralBranchCd_COLUMN = "CENTRAL_BRANCH_CD";

	/** COLUMNアノテーション calCd */
	public static final String calCd_COLUMN = "CAL_CD";

	/** COLUMNアノテーション billOutsideCdPublish */
	public static final String billOutsideCdPublish_COLUMN = "BILL_OUTSIDE_CD_PUBLISH";

	/** COLUMNアノテーション accountingCd */
	public static final String accountingCd_COLUMN = "ACCOUNTING_CD";

	/** COLUMNアノテーション creditIssuedDivision */
	public static final String creditIssuedDivision_COLUMN = "CREDIT_ISSUED_DIVISION";

	/** COLUMNアノテーション paymentIssuedDivision */
	public static final String paymentIssuedDivision_COLUMN = "PAYMENT_ISSUED_DIVISION";

	/** COLUMNアノテーション bankMasterCd1 */
	public static final String bankMasterCd1_COLUMN = "BANK_MASTER_CD1";

	/** COLUMNアノテーション accountAbbreviation1 */
	public static final String accountAbbreviation1_COLUMN = "ACCOUNT_ABBREVIATION1";

	/** COLUMNアノテーション accountDivision1 */
	public static final String accountDivision1_COLUMN = "ACCOUNT_DIVISION1";

	/** COLUMNアノテーション accountNo1 */
	public static final String accountNo1_COLUMN = "ACCOUNT_NO1";

	/** COLUMNアノテーション accountStockhold1 */
	public static final String accountStockhold1_COLUMN = "ACCOUNT_STOCKHOLD1";

	/** COLUMNアノテーション bankMasterCd2 */
	public static final String bankMasterCd2_COLUMN = "BANK_MASTER_CD2";

	/** COLUMNアノテーション accountAbbreviation2 */
	public static final String accountAbbreviation2_COLUMN = "ACCOUNT_ABBREVIATION2";

	/** COLUMNアノテーション accountDivision2 */
	public static final String accountDivision2_COLUMN = "ACCOUNT_DIVISION2";

	/** COLUMNアノテーション accountNo2 */
	public static final String accountNo2_COLUMN = "ACCOUNT_NO2";

	/** COLUMNアノテーション accountStockhold2 */
	public static final String accountStockhold2_COLUMN = "ACCOUNT_STOCKHOLD2";

	/** COLUMNアノテーション bankMasterCd3 */
	public static final String bankMasterCd3_COLUMN = "BANK_MASTER_CD3";

	/** COLUMNアノテーション accountAbbreviation3 */
	public static final String accountAbbreviation3_COLUMN = "ACCOUNT_ABBREVIATION3";

	/** COLUMNアノテーション accountDivision3 */
	public static final String accountDivision3_COLUMN = "ACCOUNT_DIVISION3";

	/** COLUMNアノテーション accountNo3 */
	public static final String accountNo3_COLUMN = "ACCOUNT_NO3";

	/** COLUMNアノテーション accountStockhold3 */
	public static final String accountStockhold3_COLUMN = "ACCOUNT_STOCKHOLD3";

	/** COLUMNアノテーション bankMasterCd4 */
	public static final String bankMasterCd4_COLUMN = "BANK_MASTER_CD4";

	/** COLUMNアノテーション accountAbbreviation4 */
	public static final String accountAbbreviation4_COLUMN = "ACCOUNT_ABBREVIATION4";

	/** COLUMNアノテーション accountDivision4 */
	public static final String accountDivision4_COLUMN = "ACCOUNT_DIVISION4";

	/** COLUMNアノテーション accountNo4 */
	public static final String accountNo4_COLUMN = "ACCOUNT_NO4";

	/** COLUMNアノテーション accountStockhold4 */
	public static final String accountStockhold4_COLUMN = "ACCOUNT_STOCKHOLD4";

	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション transferClientCd */
	public static final String transferClientCd_COLUMN = "TRANSFER_CLIENT_CD";

	/** COLUMNアノテーション transferClientName */
	public static final String transferClientName_COLUMN = "TRANSFER_CLIENT_NAME";

	/** COLUMNアノテーション passwordValidTerm */
	public static final String passwordValidTerm_COLUMN = "PASSWORD_VALID_TERM";

	/** COLUMNアノテーション passwordKetaLowerLimit */
	public static final String passwordKetaLowerLimit_COLUMN = "PASSWORD_KETA_LOWER_LIMIT";

	/** COLUMNアノテーション passwordKetaUpperLimit */
	public static final String passwordKetaUpperLimit_COLUMN = "PASSWORD_KETA_UPPER_LIMIT";

	/** COLUMNアノテーション prime */
	public static final String prime_COLUMN = "PRIME";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション creditBankName1 */
	public static final String creditBankName1_COLUMN = "CREDIT_BANK_NAME1";

	/** COLUMNアノテーション creditBankName2 */
	public static final String creditBankName2_COLUMN = "CREDIT_BANK_NAME2";

	/** COLUMNアノテーション creditBankName3 */
	public static final String creditBankName3_COLUMN = "CREDIT_BANK_NAME3";

	/** COLUMNアノテーション creditBankName4 */
	public static final String creditBankName4_COLUMN = "CREDIT_BANK_NAME4";

	/** COLUMNアノテーション paymentBankName */
	public static final String paymentBankName_COLUMN = "PAYMENT_BANK_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String homeName1;

	private String homeName2;

	private String zipcodeNo;

	private String address1;

	private String address2;

	private String address3;

	private String telNo;

	private String faxNo;

	private String representRole;

	private String representPerson;

	private String invoiceNo;

	private java.math.BigDecimal settlement;

	private java.math.BigDecimal closingDay;

	private java.sql.Timestamp managementStartDate;

	private java.math.BigDecimal reportCycle;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal calcDivision;

	private java.math.BigDecimal taxRoundup;

	private java.math.BigDecimal taxRoundupUnit;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal stockDiscountRate;

	private java.math.BigDecimal purchaseDiscountRate;

	private java.math.BigDecimal roundup;

	private java.math.BigDecimal roundupUnit;

	private java.math.BigDecimal productAutoCharge;

	private java.math.BigDecimal provideAutoCharge;

	private java.math.BigDecimal salesRoundup;

	private java.math.BigDecimal salesRoundupUnit;

	private java.math.BigDecimal purchaseRoundup;

	private java.math.BigDecimal purchaseRoundupUnit;

	private java.math.BigDecimal unitpriceRoundup;

	private java.math.BigDecimal unitpriceRoundupUnit;

	private java.math.BigDecimal blendQtyRoundup;

	private java.math.BigDecimal blendQtyRoundupUnit;

	private java.math.BigDecimal mixRateRoundup;

	private java.math.BigDecimal mixRateRoundupUnit;

	private java.math.BigDecimal adjRoundup;

	private java.math.BigDecimal adjRoundupUnit;

	private java.math.BigDecimal quantityDecimalpoint1;

	private java.math.BigDecimal quantityDecimalpoint2;

	private String costName1;

	private String costName2;

	private String costName3;

	private String costName4;

	private String costName5;

	private String costName6;

	private String costName7;

	private java.math.BigDecimal workReport;

	private java.math.BigDecimal productionPlanAutoCreate;

	private java.math.BigDecimal amountDecimalpoint;

	private java.math.BigDecimal proofManagement;

	private java.math.BigDecimal manufacturingSubcontractCost;

	private java.math.BigDecimal paymentUpdate;

	private java.math.BigDecimal orderSaveperiod;

	private java.math.BigDecimal buySubconstractSaveperiod;

	private java.math.BigDecimal manufactureSaveperiod;

	private java.math.BigDecimal workResultSaveperiod;

	private java.math.BigDecimal salesSaveperiod;

	private java.math.BigDecimal stockSaveperiod;

	private java.math.BigDecimal paymentSaveperiod;

	private java.math.BigDecimal badSaveperiod;

	private java.math.BigDecimal monthlySumSaveperiod;

	private java.math.BigDecimal proofSaveperiod;

	private java.math.BigDecimal processManagementSaveperiod;

	private java.math.BigDecimal dummySaveperiod1;

	private java.math.BigDecimal dummySaveperiod2;

	private java.math.BigDecimal dummySaveperiod3;

	private java.math.BigDecimal dummySaveperiod4;

	private java.math.BigDecimal workerManagement;

	private java.math.BigDecimal workOpen;

	private java.math.BigDecimal viewProductPlan;

	private String orderDivisionName0;

	private String orderDivisionName1;

	private String orderDivisionName2;

	private String orderDivisionName3;

	private String orderDivisionName4;

	private String orderDivisionName5;

	private String orderDivisionName6;

	private String orderDivisionName7;

	private String orderDivisionName8;

	private String orderDivisionName9;

	private String inspectRank1;

	private java.math.BigDecimal inspectFrequency1;

	private String inspectRank2;

	private java.math.BigDecimal inspectFrequency2;

	private String inspectRank3;

	private java.math.BigDecimal inspectFrequency3;

	private String inspectRank4;

	private java.math.BigDecimal inspectFrequency4;

	private String inspectRank5;

	private java.math.BigDecimal inspectFrequency5;

	private String inspectRank6;

	private java.math.BigDecimal inspectFrequency6;

	private String inspectRank7;

	private java.math.BigDecimal inspectFrequency7;

	private String inspectRank8;

	private java.math.BigDecimal inspectFrequency8;

	private String inspectRank9;

	private java.math.BigDecimal inspectFrequency9;

	private java.math.BigDecimal nonInspectMonth;

	private String receiveInspreceiptLocation;

	private java.math.BigDecimal pickupInspectSheet;

	private java.math.BigDecimal changespecManagement;

	private java.math.BigDecimal turnMadeManagement;

	private java.math.BigDecimal costUpdateManagement;

	private java.math.BigDecimal traceManagement;

	private java.math.BigDecimal processManagementUse;

	private java.math.BigDecimal disbursementManagement;

	private java.math.BigDecimal monthlyDivideMethod;

	private java.math.BigDecimal firstWeekday;

	private java.math.BigDecimal newTaxRatio;

	private java.sql.Timestamp newTaxApllyDate;

	private java.math.BigDecimal creditUpdateDate;

	private java.math.BigDecimal payableUpdateDate;

	private java.math.BigDecimal inventoryUpdateDate;

	private java.math.BigDecimal stockUpdateDate;

	private java.math.BigDecimal monthlyStockClosingDate;

	private java.math.BigDecimal jseflg1;

	private java.math.BigDecimal jseflg2;

	private java.math.BigDecimal jseflg3;

	private java.math.BigDecimal jseflg4;

	private java.math.BigDecimal jseflg5;

	private java.math.BigDecimal jsekbn1;

	private java.math.BigDecimal jsekbn2;

	private java.math.BigDecimal jsekbn3;

	private java.math.BigDecimal jsekbn4;

	private java.math.BigDecimal jsekbn5;

	private java.math.BigDecimal jsedate1;

	private java.math.BigDecimal jsedate2;

	private java.math.BigDecimal jsedate3;

	private java.math.BigDecimal jsedate4;

	private java.math.BigDecimal jsedate5;

	private java.math.BigDecimal jsetime1;

	private java.math.BigDecimal jsetime2;

	private java.math.BigDecimal jsetime3;

	private java.math.BigDecimal jsetime4;

	private java.math.BigDecimal jsetime5;

	private java.math.BigDecimal jsenum1;

	private java.math.BigDecimal jsenum2;

	private java.math.BigDecimal jsenum3;

	private java.math.BigDecimal jsenum4;

	private java.math.BigDecimal jsenum5;

	private String jsevalue1;

	private String jsevalue2;

	private String jsevalue3;

	private String jsevalue4;

	private String jsevalue5;

	private String companyCd;

	private String centralBranchCd;

	private String calCd;

	private java.math.BigDecimal billOutsideCdPublish;

	private String accountingCd;

	private java.math.BigDecimal creditIssuedDivision;

	private java.math.BigDecimal paymentIssuedDivision;

	private String bankMasterCd1;

	private String accountAbbreviation1;

	private java.math.BigDecimal accountDivision1;

	private String accountNo1;

	private String accountStockhold1;

	private String bankMasterCd2;

	private String accountAbbreviation2;

	private java.math.BigDecimal accountDivision2;

	private String accountNo2;

	private String accountStockhold2;

	private String bankMasterCd3;

	private String accountAbbreviation3;

	private java.math.BigDecimal accountDivision3;

	private String accountNo3;

	private String accountStockhold3;

	private String bankMasterCd4;

	private String accountAbbreviation4;

	private java.math.BigDecimal accountDivision4;

	private String accountNo4;

	private String accountStockhold4;

	private String bankMasterCd;

	private java.math.BigDecimal accountDivision;

	private String accountNo;

	private String transferClientCd;

	private String transferClientName;

	private java.math.BigDecimal passwordValidTerm;

	private java.math.BigDecimal passwordKetaLowerLimit;

	private java.math.BigDecimal passwordKetaUpperLimit;

	private java.math.BigDecimal prime;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String creditBankName1;

	private String creditBankName2;

	private String creditBankName3;

	private String creditBankName4;

	private String paymentBankName;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * homeName1取得.
	 * @return homeName1
	 */
	public String getHomeName1() {
		return this.homeName1;
	}

	/**
	 * homeName1設定.
	 * @param homeName1 homeName1
	 */
	public void setHomeName1(final String homeName1) {
		this.homeName1 = homeName1;
	}

	/**
	 * homeName2取得.
	 * @return homeName2
	 */
	public String getHomeName2() {
		return this.homeName2;
	}

	/**
	 * homeName2設定.
	 * @param homeName2 homeName2
	 */
	public void setHomeName2(final String homeName2) {
		this.homeName2 = homeName2;
	}

	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * address1取得.
	 * @return address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * address1設定.
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2取得.
	 * @return address2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * address2設定.
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3取得.
	 * @return address3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * address3設定.
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * faxNo取得.
	 * @return faxNo
	 */
	public String getFaxNo() {
		return this.faxNo;
	}

	/**
	 * faxNo設定.
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * representRole取得.
	 * @return representRole
	 */
	public String getRepresentRole() {
		return this.representRole;
	}

	/**
	 * representRole設定.
	 * @param representRole representRole
	 */
	public void setRepresentRole(final String representRole) {
		this.representRole = representRole;
	}

	/**
	 * representPerson取得.
	 * @return representPerson
	 */
	public String getRepresentPerson() {
		return this.representPerson;
	}

	/**
	 * representPerson設定.
	 * @param representPerson representPerson
	 */
	public void setRepresentPerson(final String representPerson) {
		this.representPerson = representPerson;
	}

	/**
	 * invoiceNo取得.
	 * @return invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * invoiceNo設定.
	 * @param invoiceNo invoiceNo
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * settlement取得.
	 * @return settlement
	 */
	public java.math.BigDecimal getSettlement() {
		return this.settlement;
	}

	/**
	 * settlement設定.
	 * @param settlement settlement
	 */
	public void setSettlement(final java.math.BigDecimal settlement) {
		this.settlement = settlement;
	}

	/**
	 * closingDay取得.
	 * @return closingDay
	 */
	public java.math.BigDecimal getClosingDay() {
		return this.closingDay;
	}

	/**
	 * closingDay設定.
	 * @param closingDay closingDay
	 */
	public void setClosingDay(final java.math.BigDecimal closingDay) {
		this.closingDay = closingDay;
	}

	/**
	 * managementStartDate取得.
	 * @return managementStartDate
	 */
	public java.sql.Timestamp getManagementStartDate() {
		return this.managementStartDate;
	}

	/**
	 * managementStartDate設定.
	 * @param managementStartDate managementStartDate
	 */
	public void setManagementStartDate(final java.sql.Timestamp managementStartDate) {
		this.managementStartDate = managementStartDate;
	}

	/**
	 * reportCycle取得.
	 * @return reportCycle
	 */
	public java.math.BigDecimal getReportCycle() {
		return this.reportCycle;
	}

	/**
	 * reportCycle設定.
	 * @param reportCycle reportCycle
	 */
	public void setReportCycle(final java.math.BigDecimal reportCycle) {
		this.reportCycle = reportCycle;
	}

	/**
	 * taxDivision取得.
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * taxDivision設定.
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * calcDivision取得.
	 * @return calcDivision
	 */
	public java.math.BigDecimal getCalcDivision() {
		return this.calcDivision;
	}

	/**
	 * calcDivision設定.
	 * @param calcDivision calcDivision
	 */
	public void setCalcDivision(final java.math.BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * taxRoundup取得.
	 * @return taxRoundup
	 */
	public java.math.BigDecimal getTaxRoundup() {
		return this.taxRoundup;
	}

	/**
	 * taxRoundup設定.
	 * @param taxRoundup taxRoundup
	 */
	public void setTaxRoundup(final java.math.BigDecimal taxRoundup) {
		this.taxRoundup = taxRoundup;
	}

	/**
	 * taxRoundupUnit取得.
	 * @return taxRoundupUnit
	 */
	public java.math.BigDecimal getTaxRoundupUnit() {
		return this.taxRoundupUnit;
	}

	/**
	 * taxRoundupUnit設定.
	 * @param taxRoundupUnit taxRoundupUnit
	 */
	public void setTaxRoundupUnit(final java.math.BigDecimal taxRoundupUnit) {
		this.taxRoundupUnit = taxRoundupUnit;
	}

	/**
	 * taxRatio取得.
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * taxRatio設定.
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * stockDiscountRate取得.
	 * @return stockDiscountRate
	 */
	public java.math.BigDecimal getStockDiscountRate() {
		return this.stockDiscountRate;
	}

	/**
	 * stockDiscountRate設定.
	 * @param stockDiscountRate stockDiscountRate
	 */
	public void setStockDiscountRate(final java.math.BigDecimal stockDiscountRate) {
		this.stockDiscountRate = stockDiscountRate;
	}

	/**
	 * purchaseDiscountRate取得.
	 * @return purchaseDiscountRate
	 */
	public java.math.BigDecimal getPurchaseDiscountRate() {
		return this.purchaseDiscountRate;
	}

	/**
	 * purchaseDiscountRate設定.
	 * @param purchaseDiscountRate purchaseDiscountRate
	 */
	public void setPurchaseDiscountRate(final java.math.BigDecimal purchaseDiscountRate) {
		this.purchaseDiscountRate = purchaseDiscountRate;
	}

	/**
	 * roundup取得.
	 * @return roundup
	 */
	public java.math.BigDecimal getRoundup() {
		return this.roundup;
	}

	/**
	 * roundup設定.
	 * @param roundup roundup
	 */
	public void setRoundup(final java.math.BigDecimal roundup) {
		this.roundup = roundup;
	}

	/**
	 * roundupUnit取得.
	 * @return roundupUnit
	 */
	public java.math.BigDecimal getRoundupUnit() {
		return this.roundupUnit;
	}

	/**
	 * roundupUnit設定.
	 * @param roundupUnit roundupUnit
	 */
	public void setRoundupUnit(final java.math.BigDecimal roundupUnit) {
		this.roundupUnit = roundupUnit;
	}

	/**
	 * productAutoCharge取得.
	 * @return productAutoCharge
	 */
	public java.math.BigDecimal getProductAutoCharge() {
		return this.productAutoCharge;
	}

	/**
	 * productAutoCharge設定.
	 * @param productAutoCharge productAutoCharge
	 */
	public void setProductAutoCharge(final java.math.BigDecimal productAutoCharge) {
		this.productAutoCharge = productAutoCharge;
	}

	/**
	 * provideAutoCharge取得.
	 * @return provideAutoCharge
	 */
	public java.math.BigDecimal getProvideAutoCharge() {
		return this.provideAutoCharge;
	}

	/**
	 * provideAutoCharge設定.
	 * @param provideAutoCharge provideAutoCharge
	 */
	public void setProvideAutoCharge(final java.math.BigDecimal provideAutoCharge) {
		this.provideAutoCharge = provideAutoCharge;
	}

	/**
	 * salesRoundup取得.
	 * @return salesRoundup
	 */
	public java.math.BigDecimal getSalesRoundup() {
		return this.salesRoundup;
	}

	/**
	 * salesRoundup設定.
	 * @param salesRoundup salesRoundup
	 */
	public void setSalesRoundup(final java.math.BigDecimal salesRoundup) {
		this.salesRoundup = salesRoundup;
	}

	/**
	 * salesRoundupUnit取得.
	 * @return salesRoundupUnit
	 */
	public java.math.BigDecimal getSalesRoundupUnit() {
		return this.salesRoundupUnit;
	}

	/**
	 * salesRoundupUnit設定.
	 * @param salesRoundupUnit salesRoundupUnit
	 */
	public void setSalesRoundupUnit(final java.math.BigDecimal salesRoundupUnit) {
		this.salesRoundupUnit = salesRoundupUnit;
	}

	/**
	 * purchaseRoundup取得.
	 * @return purchaseRoundup
	 */
	public java.math.BigDecimal getPurchaseRoundup() {
		return this.purchaseRoundup;
	}

	/**
	 * purchaseRoundup設定.
	 * @param purchaseRoundup purchaseRoundup
	 */
	public void setPurchaseRoundup(final java.math.BigDecimal purchaseRoundup) {
		this.purchaseRoundup = purchaseRoundup;
	}

	/**
	 * purchaseRoundupUnit取得.
	 * @return purchaseRoundupUnit
	 */
	public java.math.BigDecimal getPurchaseRoundupUnit() {
		return this.purchaseRoundupUnit;
	}

	/**
	 * purchaseRoundupUnit設定.
	 * @param purchaseRoundupUnit purchaseRoundupUnit
	 */
	public void setPurchaseRoundupUnit(final java.math.BigDecimal purchaseRoundupUnit) {
		this.purchaseRoundupUnit = purchaseRoundupUnit;
	}

	/**
	 * unitpriceRoundup取得.
	 * @return unitpriceRoundup
	 */
	public java.math.BigDecimal getUnitpriceRoundup() {
		return this.unitpriceRoundup;
	}

	/**
	 * unitpriceRoundup設定.
	 * @param unitpriceRoundup unitpriceRoundup
	 */
	public void setUnitpriceRoundup(final java.math.BigDecimal unitpriceRoundup) {
		this.unitpriceRoundup = unitpriceRoundup;
	}

	/**
	 * unitpriceRoundupUnit取得.
	 * @return unitpriceRoundupUnit
	 */
	public java.math.BigDecimal getUnitpriceRoundupUnit() {
		return this.unitpriceRoundupUnit;
	}

	/**
	 * unitpriceRoundupUnit設定.
	 * @param unitpriceRoundupUnit unitpriceRoundupUnit
	 */
	public void setUnitpriceRoundupUnit(final java.math.BigDecimal unitpriceRoundupUnit) {
		this.unitpriceRoundupUnit = unitpriceRoundupUnit;
	}

	/**
	 * blendQtyRoundup取得.
	 * @return blendQtyRoundup
	 */
	public java.math.BigDecimal getBlendQtyRoundup() {
		return this.blendQtyRoundup;
	}

	/**
	 * blendQtyRoundup設定.
	 * @param blendQtyRoundup blendQtyRoundup
	 */
	public void setBlendQtyRoundup(final java.math.BigDecimal blendQtyRoundup) {
		this.blendQtyRoundup = blendQtyRoundup;
	}

	/**
	 * blendQtyRoundupUnit取得.
	 * @return blendQtyRoundupUnit
	 */
	public java.math.BigDecimal getBlendQtyRoundupUnit() {
		return this.blendQtyRoundupUnit;
	}

	/**
	 * blendQtyRoundupUnit設定.
	 * @param blendQtyRoundupUnit blendQtyRoundupUnit
	 */
	public void setBlendQtyRoundupUnit(final java.math.BigDecimal blendQtyRoundupUnit) {
		this.blendQtyRoundupUnit = blendQtyRoundupUnit;
	}

	/**
	 * mixRateRoundup取得.
	 * @return mixRateRoundup
	 */
	public java.math.BigDecimal getMixRateRoundup() {
		return this.mixRateRoundup;
	}

	/**
	 * mixRateRoundup設定.
	 * @param mixRateRoundup mixRateRoundup
	 */
	public void setMixRateRoundup(final java.math.BigDecimal mixRateRoundup) {
		this.mixRateRoundup = mixRateRoundup;
	}

	/**
	 * mixRateRoundupUnit取得.
	 * @return mixRateRoundupUnit
	 */
	public java.math.BigDecimal getMixRateRoundupUnit() {
		return this.mixRateRoundupUnit;
	}

	/**
	 * mixRateRoundupUnit設定.
	 * @param mixRateRoundupUnit mixRateRoundupUnit
	 */
	public void setMixRateRoundupUnit(final java.math.BigDecimal mixRateRoundupUnit) {
		this.mixRateRoundupUnit = mixRateRoundupUnit;
	}

	/**
	 * adjRoundup取得.
	 * @return adjRoundup
	 */
	public java.math.BigDecimal getAdjRoundup() {
		return this.adjRoundup;
	}

	/**
	 * adjRoundup設定.
	 * @param adjRoundup adjRoundup
	 */
	public void setAdjRoundup(final java.math.BigDecimal adjRoundup) {
		this.adjRoundup = adjRoundup;
	}

	/**
	 * adjRoundupUnit取得.
	 * @return adjRoundupUnit
	 */
	public java.math.BigDecimal getAdjRoundupUnit() {
		return this.adjRoundupUnit;
	}

	/**
	 * adjRoundupUnit設定.
	 * @param adjRoundupUnit adjRoundupUnit
	 */
	public void setAdjRoundupUnit(final java.math.BigDecimal adjRoundupUnit) {
		this.adjRoundupUnit = adjRoundupUnit;
	}

	/**
	 * quantityDecimalpoint1取得.
	 * @return quantityDecimalpoint1
	 */
	public java.math.BigDecimal getQuantityDecimalpoint1() {
		return this.quantityDecimalpoint1;
	}

	/**
	 * quantityDecimalpoint1設定.
	 * @param quantityDecimalpoint1 quantityDecimalpoint1
	 */
	public void setQuantityDecimalpoint1(final java.math.BigDecimal quantityDecimalpoint1) {
		this.quantityDecimalpoint1 = quantityDecimalpoint1;
	}

	/**
	 * quantityDecimalpoint2取得.
	 * @return quantityDecimalpoint2
	 */
	public java.math.BigDecimal getQuantityDecimalpoint2() {
		return this.quantityDecimalpoint2;
	}

	/**
	 * quantityDecimalpoint2設定.
	 * @param quantityDecimalpoint2 quantityDecimalpoint2
	 */
	public void setQuantityDecimalpoint2(final java.math.BigDecimal quantityDecimalpoint2) {
		this.quantityDecimalpoint2 = quantityDecimalpoint2;
	}

	/**
	 * costName1取得.
	 * @return costName1
	 */
	public String getCostName1() {
		return this.costName1;
	}

	/**
	 * costName1設定.
	 * @param costName1 costName1
	 */
	public void setCostName1(final String costName1) {
		this.costName1 = costName1;
	}

	/**
	 * costName2取得.
	 * @return costName2
	 */
	public String getCostName2() {
		return this.costName2;
	}

	/**
	 * costName2設定.
	 * @param costName2 costName2
	 */
	public void setCostName2(final String costName2) {
		this.costName2 = costName2;
	}

	/**
	 * costName3取得.
	 * @return costName3
	 */
	public String getCostName3() {
		return this.costName3;
	}

	/**
	 * costName3設定.
	 * @param costName3 costName3
	 */
	public void setCostName3(final String costName3) {
		this.costName3 = costName3;
	}

	/**
	 * costName4取得.
	 * @return costName4
	 */
	public String getCostName4() {
		return this.costName4;
	}

	/**
	 * costName4設定.
	 * @param costName4 costName4
	 */
	public void setCostName4(final String costName4) {
		this.costName4 = costName4;
	}

	/**
	 * costName5取得.
	 * @return costName5
	 */
	public String getCostName5() {
		return this.costName5;
	}

	/**
	 * costName5設定.
	 * @param costName5 costName5
	 */
	public void setCostName5(final String costName5) {
		this.costName5 = costName5;
	}

	/**
	 * costName6取得.
	 * @return costName6
	 */
	public String getCostName6() {
		return this.costName6;
	}

	/**
	 * costName6設定.
	 * @param costName6 costName6
	 */
	public void setCostName6(final String costName6) {
		this.costName6 = costName6;
	}

	/**
	 * costName7取得.
	 * @return costName7
	 */
	public String getCostName7() {
		return this.costName7;
	}

	/**
	 * costName7設定.
	 * @param costName7 costName7
	 */
	public void setCostName7(final String costName7) {
		this.costName7 = costName7;
	}

	/**
	 * workReport取得.
	 * @return workReport
	 */
	public java.math.BigDecimal getWorkReport() {
		return this.workReport;
	}

	/**
	 * workReport設定.
	 * @param workReport workReport
	 */
	public void setWorkReport(final java.math.BigDecimal workReport) {
		this.workReport = workReport;
	}

	/**
	 * productionPlanAutoCreate取得.
	 * @return productionPlanAutoCreate
	 */
	public java.math.BigDecimal getProductionPlanAutoCreate() {
		return this.productionPlanAutoCreate;
	}

	/**
	 * productionPlanAutoCreate設定.
	 * @param productionPlanAutoCreate productionPlanAutoCreate
	 */
	public void setProductionPlanAutoCreate(final java.math.BigDecimal productionPlanAutoCreate) {
		this.productionPlanAutoCreate = productionPlanAutoCreate;
	}

	/**
	 * amountDecimalpoint取得.
	 * @return amountDecimalpoint
	 */
	public java.math.BigDecimal getAmountDecimalpoint() {
		return this.amountDecimalpoint;
	}

	/**
	 * amountDecimalpoint設定.
	 * @param amountDecimalpoint amountDecimalpoint
	 */
	public void setAmountDecimalpoint(final java.math.BigDecimal amountDecimalpoint) {
		this.amountDecimalpoint = amountDecimalpoint;
	}

	/**
	 * proofManagement取得.
	 * @return proofManagement
	 */
	public java.math.BigDecimal getProofManagement() {
		return this.proofManagement;
	}

	/**
	 * proofManagement設定.
	 * @param proofManagement proofManagement
	 */
	public void setProofManagement(final java.math.BigDecimal proofManagement) {
		this.proofManagement = proofManagement;
	}

	/**
	 * manufacturingSubcontractCost取得.
	 * @return manufacturingSubcontractCost
	 */
	public java.math.BigDecimal getManufacturingSubcontractCost() {
		return this.manufacturingSubcontractCost;
	}

	/**
	 * manufacturingSubcontractCost設定.
	 * @param manufacturingSubcontractCost manufacturingSubcontractCost
	 */
	public void setManufacturingSubcontractCost(final java.math.BigDecimal manufacturingSubcontractCost) {
		this.manufacturingSubcontractCost = manufacturingSubcontractCost;
	}

	/**
	 * paymentUpdate取得.
	 * @return paymentUpdate
	 */
	public java.math.BigDecimal getPaymentUpdate() {
		return this.paymentUpdate;
	}

	/**
	 * paymentUpdate設定.
	 * @param paymentUpdate paymentUpdate
	 */
	public void setPaymentUpdate(final java.math.BigDecimal paymentUpdate) {
		this.paymentUpdate = paymentUpdate;
	}

	/**
	 * orderSaveperiod取得.
	 * @return orderSaveperiod
	 */
	public java.math.BigDecimal getOrderSaveperiod() {
		return this.orderSaveperiod;
	}

	/**
	 * orderSaveperiod設定.
	 * @param orderSaveperiod orderSaveperiod
	 */
	public void setOrderSaveperiod(final java.math.BigDecimal orderSaveperiod) {
		this.orderSaveperiod = orderSaveperiod;
	}

	/**
	 * buySubconstractSaveperiod取得.
	 * @return buySubconstractSaveperiod
	 */
	public java.math.BigDecimal getBuySubconstractSaveperiod() {
		return this.buySubconstractSaveperiod;
	}

	/**
	 * buySubconstractSaveperiod設定.
	 * @param buySubconstractSaveperiod buySubconstractSaveperiod
	 */
	public void setBuySubconstractSaveperiod(final java.math.BigDecimal buySubconstractSaveperiod) {
		this.buySubconstractSaveperiod = buySubconstractSaveperiod;
	}

	/**
	 * manufactureSaveperiod取得.
	 * @return manufactureSaveperiod
	 */
	public java.math.BigDecimal getManufactureSaveperiod() {
		return this.manufactureSaveperiod;
	}

	/**
	 * manufactureSaveperiod設定.
	 * @param manufactureSaveperiod manufactureSaveperiod
	 */
	public void setManufactureSaveperiod(final java.math.BigDecimal manufactureSaveperiod) {
		this.manufactureSaveperiod = manufactureSaveperiod;
	}

	/**
	 * workResultSaveperiod取得.
	 * @return workResultSaveperiod
	 */
	public java.math.BigDecimal getWorkResultSaveperiod() {
		return this.workResultSaveperiod;
	}

	/**
	 * workResultSaveperiod設定.
	 * @param workResultSaveperiod workResultSaveperiod
	 */
	public void setWorkResultSaveperiod(final java.math.BigDecimal workResultSaveperiod) {
		this.workResultSaveperiod = workResultSaveperiod;
	}

	/**
	 * salesSaveperiod取得.
	 * @return salesSaveperiod
	 */
	public java.math.BigDecimal getSalesSaveperiod() {
		return this.salesSaveperiod;
	}

	/**
	 * salesSaveperiod設定.
	 * @param salesSaveperiod salesSaveperiod
	 */
	public void setSalesSaveperiod(final java.math.BigDecimal salesSaveperiod) {
		this.salesSaveperiod = salesSaveperiod;
	}

	/**
	 * stockSaveperiod取得.
	 * @return stockSaveperiod
	 */
	public java.math.BigDecimal getStockSaveperiod() {
		return this.stockSaveperiod;
	}

	/**
	 * stockSaveperiod設定.
	 * @param stockSaveperiod stockSaveperiod
	 */
	public void setStockSaveperiod(final java.math.BigDecimal stockSaveperiod) {
		this.stockSaveperiod = stockSaveperiod;
	}

	/**
	 * paymentSaveperiod取得.
	 * @return paymentSaveperiod
	 */
	public java.math.BigDecimal getPaymentSaveperiod() {
		return this.paymentSaveperiod;
	}

	/**
	 * paymentSaveperiod設定.
	 * @param paymentSaveperiod paymentSaveperiod
	 */
	public void setPaymentSaveperiod(final java.math.BigDecimal paymentSaveperiod) {
		this.paymentSaveperiod = paymentSaveperiod;
	}

	/**
	 * badSaveperiod取得.
	 * @return badSaveperiod
	 */
	public java.math.BigDecimal getBadSaveperiod() {
		return this.badSaveperiod;
	}

	/**
	 * badSaveperiod設定.
	 * @param badSaveperiod badSaveperiod
	 */
	public void setBadSaveperiod(final java.math.BigDecimal badSaveperiod) {
		this.badSaveperiod = badSaveperiod;
	}

	/**
	 * monthlySumSaveperiod取得.
	 * @return monthlySumSaveperiod
	 */
	public java.math.BigDecimal getMonthlySumSaveperiod() {
		return this.monthlySumSaveperiod;
	}

	/**
	 * monthlySumSaveperiod設定.
	 * @param monthlySumSaveperiod monthlySumSaveperiod
	 */
	public void setMonthlySumSaveperiod(final java.math.BigDecimal monthlySumSaveperiod) {
		this.monthlySumSaveperiod = monthlySumSaveperiod;
	}

	/**
	 * proofSaveperiod取得.
	 * @return proofSaveperiod
	 */
	public java.math.BigDecimal getProofSaveperiod() {
		return this.proofSaveperiod;
	}

	/**
	 * proofSaveperiod設定.
	 * @param proofSaveperiod proofSaveperiod
	 */
	public void setProofSaveperiod(final java.math.BigDecimal proofSaveperiod) {
		this.proofSaveperiod = proofSaveperiod;
	}

	/**
	 * processManagementSaveperiod取得.
	 * @return processManagementSaveperiod
	 */
	public java.math.BigDecimal getProcessManagementSaveperiod() {
		return this.processManagementSaveperiod;
	}

	/**
	 * processManagementSaveperiod設定.
	 * @param processManagementSaveperiod processManagementSaveperiod
	 */
	public void setProcessManagementSaveperiod(final java.math.BigDecimal processManagementSaveperiod) {
		this.processManagementSaveperiod = processManagementSaveperiod;
	}

	/**
	 * dummySaveperiod1取得.
	 * @return dummySaveperiod1
	 */
	public java.math.BigDecimal getDummySaveperiod1() {
		return this.dummySaveperiod1;
	}

	/**
	 * dummySaveperiod1設定.
	 * @param dummySaveperiod1 dummySaveperiod1
	 */
	public void setDummySaveperiod1(final java.math.BigDecimal dummySaveperiod1) {
		this.dummySaveperiod1 = dummySaveperiod1;
	}

	/**
	 * dummySaveperiod2取得.
	 * @return dummySaveperiod2
	 */
	public java.math.BigDecimal getDummySaveperiod2() {
		return this.dummySaveperiod2;
	}

	/**
	 * dummySaveperiod2設定.
	 * @param dummySaveperiod2 dummySaveperiod2
	 */
	public void setDummySaveperiod2(final java.math.BigDecimal dummySaveperiod2) {
		this.dummySaveperiod2 = dummySaveperiod2;
	}

	/**
	 * dummySaveperiod3取得.
	 * @return dummySaveperiod3
	 */
	public java.math.BigDecimal getDummySaveperiod3() {
		return this.dummySaveperiod3;
	}

	/**
	 * dummySaveperiod3設定.
	 * @param dummySaveperiod3 dummySaveperiod3
	 */
	public void setDummySaveperiod3(final java.math.BigDecimal dummySaveperiod3) {
		this.dummySaveperiod3 = dummySaveperiod3;
	}

	/**
	 * dummySaveperiod4取得.
	 * @return dummySaveperiod4
	 */
	public java.math.BigDecimal getDummySaveperiod4() {
		return this.dummySaveperiod4;
	}

	/**
	 * dummySaveperiod4設定.
	 * @param dummySaveperiod4 dummySaveperiod4
	 */
	public void setDummySaveperiod4(final java.math.BigDecimal dummySaveperiod4) {
		this.dummySaveperiod4 = dummySaveperiod4;
	}

	/**
	 * workerManagement取得.
	 * @return workerManagement
	 */
	public java.math.BigDecimal getWorkerManagement() {
		return this.workerManagement;
	}

	/**
	 * workerManagement設定.
	 * @param workerManagement workerManagement
	 */
	public void setWorkerManagement(final java.math.BigDecimal workerManagement) {
		this.workerManagement = workerManagement;
	}

	/**
	 * workOpen取得.
	 * @return workOpen
	 */
	public java.math.BigDecimal getWorkOpen() {
		return this.workOpen;
	}

	/**
	 * workOpen設定.
	 * @param workOpen workOpen
	 */
	public void setWorkOpen(final java.math.BigDecimal workOpen) {
		this.workOpen = workOpen;
	}

	/**
	 * viewProductPlan取得.
	 * @return viewProductPlan
	 */
	public java.math.BigDecimal getViewProductPlan() {
		return this.viewProductPlan;
	}

	/**
	 * viewProductPlan設定.
	 * @param viewProductPlan viewProductPlan
	 */
	public void setViewProductPlan(final java.math.BigDecimal viewProductPlan) {
		this.viewProductPlan = viewProductPlan;
	}

	/**
	 * orderDivisionName0取得.
	 * @return orderDivisionName0
	 */
	public String getOrderDivisionName0() {
		return this.orderDivisionName0;
	}

	/**
	 * orderDivisionName0設定.
	 * @param orderDivisionName0 orderDivisionName0
	 */
	public void setOrderDivisionName0(final String orderDivisionName0) {
		this.orderDivisionName0 = orderDivisionName0;
	}

	/**
	 * orderDivisionName1取得.
	 * @return orderDivisionName1
	 */
	public String getOrderDivisionName1() {
		return this.orderDivisionName1;
	}

	/**
	 * orderDivisionName1設定.
	 * @param orderDivisionName1 orderDivisionName1
	 */
	public void setOrderDivisionName1(final String orderDivisionName1) {
		this.orderDivisionName1 = orderDivisionName1;
	}

	/**
	 * orderDivisionName2取得.
	 * @return orderDivisionName2
	 */
	public String getOrderDivisionName2() {
		return this.orderDivisionName2;
	}

	/**
	 * orderDivisionName2設定.
	 * @param orderDivisionName2 orderDivisionName2
	 */
	public void setOrderDivisionName2(final String orderDivisionName2) {
		this.orderDivisionName2 = orderDivisionName2;
	}

	/**
	 * orderDivisionName3取得.
	 * @return orderDivisionName3
	 */
	public String getOrderDivisionName3() {
		return this.orderDivisionName3;
	}

	/**
	 * orderDivisionName3設定.
	 * @param orderDivisionName3 orderDivisionName3
	 */
	public void setOrderDivisionName3(final String orderDivisionName3) {
		this.orderDivisionName3 = orderDivisionName3;
	}

	/**
	 * orderDivisionName4取得.
	 * @return orderDivisionName4
	 */
	public String getOrderDivisionName4() {
		return this.orderDivisionName4;
	}

	/**
	 * orderDivisionName4設定.
	 * @param orderDivisionName4 orderDivisionName4
	 */
	public void setOrderDivisionName4(final String orderDivisionName4) {
		this.orderDivisionName4 = orderDivisionName4;
	}

	/**
	 * orderDivisionName5取得.
	 * @return orderDivisionName5
	 */
	public String getOrderDivisionName5() {
		return this.orderDivisionName5;
	}

	/**
	 * orderDivisionName5設定.
	 * @param orderDivisionName5 orderDivisionName5
	 */
	public void setOrderDivisionName5(final String orderDivisionName5) {
		this.orderDivisionName5 = orderDivisionName5;
	}

	/**
	 * orderDivisionName6取得.
	 * @return orderDivisionName6
	 */
	public String getOrderDivisionName6() {
		return this.orderDivisionName6;
	}

	/**
	 * orderDivisionName6設定.
	 * @param orderDivisionName6 orderDivisionName6
	 */
	public void setOrderDivisionName6(final String orderDivisionName6) {
		this.orderDivisionName6 = orderDivisionName6;
	}

	/**
	 * orderDivisionName7取得.
	 * @return orderDivisionName7
	 */
	public String getOrderDivisionName7() {
		return this.orderDivisionName7;
	}

	/**
	 * orderDivisionName7設定.
	 * @param orderDivisionName7 orderDivisionName7
	 */
	public void setOrderDivisionName7(final String orderDivisionName7) {
		this.orderDivisionName7 = orderDivisionName7;
	}

	/**
	 * orderDivisionName8取得.
	 * @return orderDivisionName8
	 */
	public String getOrderDivisionName8() {
		return this.orderDivisionName8;
	}

	/**
	 * orderDivisionName8設定.
	 * @param orderDivisionName8 orderDivisionName8
	 */
	public void setOrderDivisionName8(final String orderDivisionName8) {
		this.orderDivisionName8 = orderDivisionName8;
	}

	/**
	 * orderDivisionName9取得.
	 * @return orderDivisionName9
	 */
	public String getOrderDivisionName9() {
		return this.orderDivisionName9;
	}

	/**
	 * orderDivisionName9設定.
	 * @param orderDivisionName9 orderDivisionName9
	 */
	public void setOrderDivisionName9(final String orderDivisionName9) {
		this.orderDivisionName9 = orderDivisionName9;
	}

	/**
	 * inspectRank1取得.
	 * @return inspectRank1
	 */
	public String getInspectRank1() {
		return this.inspectRank1;
	}

	/**
	 * inspectRank1設定.
	 * @param inspectRank1 inspectRank1
	 */
	public void setInspectRank1(final String inspectRank1) {
		this.inspectRank1 = inspectRank1;
	}

	/**
	 * inspectFrequency1取得.
	 * @return inspectFrequency1
	 */
	public java.math.BigDecimal getInspectFrequency1() {
		return this.inspectFrequency1;
	}

	/**
	 * inspectFrequency1設定.
	 * @param inspectFrequency1 inspectFrequency1
	 */
	public void setInspectFrequency1(final java.math.BigDecimal inspectFrequency1) {
		this.inspectFrequency1 = inspectFrequency1;
	}

	/**
	 * inspectRank2取得.
	 * @return inspectRank2
	 */
	public String getInspectRank2() {
		return this.inspectRank2;
	}

	/**
	 * inspectRank2設定.
	 * @param inspectRank2 inspectRank2
	 */
	public void setInspectRank2(final String inspectRank2) {
		this.inspectRank2 = inspectRank2;
	}

	/**
	 * inspectFrequency2取得.
	 * @return inspectFrequency2
	 */
	public java.math.BigDecimal getInspectFrequency2() {
		return this.inspectFrequency2;
	}

	/**
	 * inspectFrequency2設定.
	 * @param inspectFrequency2 inspectFrequency2
	 */
	public void setInspectFrequency2(final java.math.BigDecimal inspectFrequency2) {
		this.inspectFrequency2 = inspectFrequency2;
	}

	/**
	 * inspectRank3取得.
	 * @return inspectRank3
	 */
	public String getInspectRank3() {
		return this.inspectRank3;
	}

	/**
	 * inspectRank3設定.
	 * @param inspectRank3 inspectRank3
	 */
	public void setInspectRank3(final String inspectRank3) {
		this.inspectRank3 = inspectRank3;
	}

	/**
	 * inspectFrequency3取得.
	 * @return inspectFrequency3
	 */
	public java.math.BigDecimal getInspectFrequency3() {
		return this.inspectFrequency3;
	}

	/**
	 * inspectFrequency3設定.
	 * @param inspectFrequency3 inspectFrequency3
	 */
	public void setInspectFrequency3(final java.math.BigDecimal inspectFrequency3) {
		this.inspectFrequency3 = inspectFrequency3;
	}

	/**
	 * inspectRank4取得.
	 * @return inspectRank4
	 */
	public String getInspectRank4() {
		return this.inspectRank4;
	}

	/**
	 * inspectRank4設定.
	 * @param inspectRank4 inspectRank4
	 */
	public void setInspectRank4(final String inspectRank4) {
		this.inspectRank4 = inspectRank4;
	}

	/**
	 * inspectFrequency4取得.
	 * @return inspectFrequency4
	 */
	public java.math.BigDecimal getInspectFrequency4() {
		return this.inspectFrequency4;
	}

	/**
	 * inspectFrequency4設定.
	 * @param inspectFrequency4 inspectFrequency4
	 */
	public void setInspectFrequency4(final java.math.BigDecimal inspectFrequency4) {
		this.inspectFrequency4 = inspectFrequency4;
	}

	/**
	 * inspectRank5取得.
	 * @return inspectRank5
	 */
	public String getInspectRank5() {
		return this.inspectRank5;
	}

	/**
	 * inspectRank5設定.
	 * @param inspectRank5 inspectRank5
	 */
	public void setInspectRank5(final String inspectRank5) {
		this.inspectRank5 = inspectRank5;
	}

	/**
	 * inspectFrequency5取得.
	 * @return inspectFrequency5
	 */
	public java.math.BigDecimal getInspectFrequency5() {
		return this.inspectFrequency5;
	}

	/**
	 * inspectFrequency5設定.
	 * @param inspectFrequency5 inspectFrequency5
	 */
	public void setInspectFrequency5(final java.math.BigDecimal inspectFrequency5) {
		this.inspectFrequency5 = inspectFrequency5;
	}

	/**
	 * inspectRank6取得.
	 * @return inspectRank6
	 */
	public String getInspectRank6() {
		return this.inspectRank6;
	}

	/**
	 * inspectRank6設定.
	 * @param inspectRank6 inspectRank6
	 */
	public void setInspectRank6(final String inspectRank6) {
		this.inspectRank6 = inspectRank6;
	}

	/**
	 * inspectFrequency6取得.
	 * @return inspectFrequency6
	 */
	public java.math.BigDecimal getInspectFrequency6() {
		return this.inspectFrequency6;
	}

	/**
	 * inspectFrequency6設定.
	 * @param inspectFrequency6 inspectFrequency6
	 */
	public void setInspectFrequency6(final java.math.BigDecimal inspectFrequency6) {
		this.inspectFrequency6 = inspectFrequency6;
	}

	/**
	 * inspectRank7取得.
	 * @return inspectRank7
	 */
	public String getInspectRank7() {
		return this.inspectRank7;
	}

	/**
	 * inspectRank7設定.
	 * @param inspectRank7 inspectRank7
	 */
	public void setInspectRank7(final String inspectRank7) {
		this.inspectRank7 = inspectRank7;
	}

	/**
	 * inspectFrequency7取得.
	 * @return inspectFrequency7
	 */
	public java.math.BigDecimal getInspectFrequency7() {
		return this.inspectFrequency7;
	}

	/**
	 * inspectFrequency7設定.
	 * @param inspectFrequency7 inspectFrequency7
	 */
	public void setInspectFrequency7(final java.math.BigDecimal inspectFrequency7) {
		this.inspectFrequency7 = inspectFrequency7;
	}

	/**
	 * inspectRank8取得.
	 * @return inspectRank8
	 */
	public String getInspectRank8() {
		return this.inspectRank8;
	}

	/**
	 * inspectRank8設定.
	 * @param inspectRank8 inspectRank8
	 */
	public void setInspectRank8(final String inspectRank8) {
		this.inspectRank8 = inspectRank8;
	}

	/**
	 * inspectFrequency8取得.
	 * @return inspectFrequency8
	 */
	public java.math.BigDecimal getInspectFrequency8() {
		return this.inspectFrequency8;
	}

	/**
	 * inspectFrequency8設定.
	 * @param inspectFrequency8 inspectFrequency8
	 */
	public void setInspectFrequency8(final java.math.BigDecimal inspectFrequency8) {
		this.inspectFrequency8 = inspectFrequency8;
	}

	/**
	 * inspectRank9取得.
	 * @return inspectRank9
	 */
	public String getInspectRank9() {
		return this.inspectRank9;
	}

	/**
	 * inspectRank9設定.
	 * @param inspectRank9 inspectRank9
	 */
	public void setInspectRank9(final String inspectRank9) {
		this.inspectRank9 = inspectRank9;
	}

	/**
	 * inspectFrequency9取得.
	 * @return inspectFrequency9
	 */
	public java.math.BigDecimal getInspectFrequency9() {
		return this.inspectFrequency9;
	}

	/**
	 * inspectFrequency9設定.
	 * @param inspectFrequency9 inspectFrequency9
	 */
	public void setInspectFrequency9(final java.math.BigDecimal inspectFrequency9) {
		this.inspectFrequency9 = inspectFrequency9;
	}

	/**
	 * nonInspectMonth取得.
	 * @return nonInspectMonth
	 */
	public java.math.BigDecimal getNonInspectMonth() {
		return this.nonInspectMonth;
	}

	/**
	 * nonInspectMonth設定.
	 * @param nonInspectMonth nonInspectMonth
	 */
	public void setNonInspectMonth(final java.math.BigDecimal nonInspectMonth) {
		this.nonInspectMonth = nonInspectMonth;
	}

	/**
	 * receiveInspreceiptLocation取得.
	 * @return receiveInspreceiptLocation
	 */
	public String getReceiveInspreceiptLocation() {
		return this.receiveInspreceiptLocation;
	}

	/**
	 * receiveInspreceiptLocation設定.
	 * @param receiveInspreceiptLocation receiveInspreceiptLocation
	 */
	public void setReceiveInspreceiptLocation(final String receiveInspreceiptLocation) {
		this.receiveInspreceiptLocation = receiveInspreceiptLocation;
	}

	/**
	 * pickupInspectSheet取得.
	 * @return pickupInspectSheet
	 */
	public java.math.BigDecimal getPickupInspectSheet() {
		return this.pickupInspectSheet;
	}

	/**
	 * pickupInspectSheet設定.
	 * @param pickupInspectSheet pickupInspectSheet
	 */
	public void setPickupInspectSheet(final java.math.BigDecimal pickupInspectSheet) {
		this.pickupInspectSheet = pickupInspectSheet;
	}

	/**
	 * changespecManagement取得.
	 * @return changespecManagement
	 */
	public java.math.BigDecimal getChangespecManagement() {
		return this.changespecManagement;
	}

	/**
	 * changespecManagement設定.
	 * @param changespecManagement changespecManagement
	 */
	public void setChangespecManagement(final java.math.BigDecimal changespecManagement) {
		this.changespecManagement = changespecManagement;
	}

	/**
	 * turnMadeManagement取得.
	 * @return turnMadeManagement
	 */
	public java.math.BigDecimal getTurnMadeManagement() {
		return this.turnMadeManagement;
	}

	/**
	 * turnMadeManagement設定.
	 * @param turnMadeManagement turnMadeManagement
	 */
	public void setTurnMadeManagement(final java.math.BigDecimal turnMadeManagement) {
		this.turnMadeManagement = turnMadeManagement;
	}

	/**
	 * costUpdateManagement取得.
	 * @return costUpdateManagement
	 */
	public java.math.BigDecimal getCostUpdateManagement() {
		return this.costUpdateManagement;
	}

	/**
	 * costUpdateManagement設定.
	 * @param costUpdateManagement costUpdateManagement
	 */
	public void setCostUpdateManagement(final java.math.BigDecimal costUpdateManagement) {
		this.costUpdateManagement = costUpdateManagement;
	}

	/**
	 * traceManagement取得.
	 * @return traceManagement
	 */
	public java.math.BigDecimal getTraceManagement() {
		return this.traceManagement;
	}

	/**
	 * traceManagement設定.
	 * @param traceManagement traceManagement
	 */
	public void setTraceManagement(final java.math.BigDecimal traceManagement) {
		this.traceManagement = traceManagement;
	}

	/**
	 * processManagementUse取得.
	 * @return processManagementUse
	 */
	public java.math.BigDecimal getProcessManagementUse() {
		return this.processManagementUse;
	}

	/**
	 * processManagementUse設定.
	 * @param processManagementUse processManagementUse
	 */
	public void setProcessManagementUse(final java.math.BigDecimal processManagementUse) {
		this.processManagementUse = processManagementUse;
	}

	/**
	 * disbursementManagement取得.
	 * @return disbursementManagement
	 */
	public java.math.BigDecimal getDisbursementManagement() {
		return this.disbursementManagement;
	}

	/**
	 * disbursementManagement設定.
	 * @param disbursementManagement disbursementManagement
	 */
	public void setDisbursementManagement(final java.math.BigDecimal disbursementManagement) {
		this.disbursementManagement = disbursementManagement;
	}

	/**
	 * monthlyDivideMethod取得.
	 * @return monthlyDivideMethod
	 */
	public java.math.BigDecimal getMonthlyDivideMethod() {
		return this.monthlyDivideMethod;
	}

	/**
	 * monthlyDivideMethod設定.
	 * @param monthlyDivideMethod monthlyDivideMethod
	 */
	public void setMonthlyDivideMethod(final java.math.BigDecimal monthlyDivideMethod) {
		this.monthlyDivideMethod = monthlyDivideMethod;
	}

	/**
	 * firstWeekday取得.
	 * @return firstWeekday
	 */
	public java.math.BigDecimal getFirstWeekday() {
		return this.firstWeekday;
	}

	/**
	 * firstWeekday設定.
	 * @param firstWeekday firstWeekday
	 */
	public void setFirstWeekday(final java.math.BigDecimal firstWeekday) {
		this.firstWeekday = firstWeekday;
	}

	/**
	 * newTaxRatio取得.
	 * @return newTaxRatio
	 */
	public java.math.BigDecimal getNewTaxRatio() {
		return this.newTaxRatio;
	}

	/**
	 * newTaxRatio設定.
	 * @param newTaxRatio newTaxRatio
	 */
	public void setNewTaxRatio(final java.math.BigDecimal newTaxRatio) {
		this.newTaxRatio = newTaxRatio;
	}

	/**
	 * newTaxApllyDate取得.
	 * @return newTaxApllyDate
	 */
	public java.sql.Timestamp getNewTaxApllyDate() {
		return this.newTaxApllyDate;
	}

	/**
	 * newTaxApllyDate設定.
	 * @param newTaxApllyDate newTaxApllyDate
	 */
	public void setNewTaxApllyDate(final java.sql.Timestamp newTaxApllyDate) {
		this.newTaxApllyDate = newTaxApllyDate;
	}

	/**
	 * creditUpdateDate取得.
	 * @return creditUpdateDate
	 */
	public java.math.BigDecimal getCreditUpdateDate() {
		return this.creditUpdateDate;
	}

	/**
	 * creditUpdateDate設定.
	 * @param creditUpdateDate creditUpdateDate
	 */
	public void setCreditUpdateDate(final java.math.BigDecimal creditUpdateDate) {
		this.creditUpdateDate = creditUpdateDate;
	}

	/**
	 * payableUpdateDate取得.
	 * @return payableUpdateDate
	 */
	public java.math.BigDecimal getPayableUpdateDate() {
		return this.payableUpdateDate;
	}

	/**
	 * payableUpdateDate設定.
	 * @param payableUpdateDate payableUpdateDate
	 */
	public void setPayableUpdateDate(final java.math.BigDecimal payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * inventoryUpdateDate取得.
	 * @return inventoryUpdateDate
	 */
	public java.math.BigDecimal getInventoryUpdateDate() {
		return this.inventoryUpdateDate;
	}

	/**
	 * inventoryUpdateDate設定.
	 * @param inventoryUpdateDate inventoryUpdateDate
	 */
	public void setInventoryUpdateDate(final java.math.BigDecimal inventoryUpdateDate) {
		this.inventoryUpdateDate = inventoryUpdateDate;
	}

	/**
	 * stockUpdateDate取得.
	 * @return stockUpdateDate
	 */
	public java.math.BigDecimal getStockUpdateDate() {
		return this.stockUpdateDate;
	}

	/**
	 * stockUpdateDate設定.
	 * @param stockUpdateDate stockUpdateDate
	 */
	public void setStockUpdateDate(final java.math.BigDecimal stockUpdateDate) {
		this.stockUpdateDate = stockUpdateDate;
	}

	/**
	 * monthlyStockClosingDate取得.
	 * @return monthlyStockClosingDate
	 */
	public java.math.BigDecimal getMonthlyStockClosingDate() {
		return this.monthlyStockClosingDate;
	}

	/**
	 * monthlyStockClosingDate設定.
	 * @param monthlyStockClosingDate monthlyStockClosingDate
	 */
	public void setMonthlyStockClosingDate(final java.math.BigDecimal monthlyStockClosingDate) {
		this.monthlyStockClosingDate = monthlyStockClosingDate;
	}

	/**
	 * jseflg1取得.
	 * @return jseflg1
	 */
	public java.math.BigDecimal getJseflg1() {
		return this.jseflg1;
	}

	/**
	 * jseflg1設定.
	 * @param jseflg1 jseflg1
	 */
	public void setJseflg1(final java.math.BigDecimal jseflg1) {
		this.jseflg1 = jseflg1;
	}

	/**
	 * jseflg2取得.
	 * @return jseflg2
	 */
	public java.math.BigDecimal getJseflg2() {
		return this.jseflg2;
	}

	/**
	 * jseflg2設定.
	 * @param jseflg2 jseflg2
	 */
	public void setJseflg2(final java.math.BigDecimal jseflg2) {
		this.jseflg2 = jseflg2;
	}

	/**
	 * jseflg3取得.
	 * @return jseflg3
	 */
	public java.math.BigDecimal getJseflg3() {
		return this.jseflg3;
	}

	/**
	 * jseflg3設定.
	 * @param jseflg3 jseflg3
	 */
	public void setJseflg3(final java.math.BigDecimal jseflg3) {
		this.jseflg3 = jseflg3;
	}

	/**
	 * jseflg4取得.
	 * @return jseflg4
	 */
	public java.math.BigDecimal getJseflg4() {
		return this.jseflg4;
	}

	/**
	 * jseflg4設定.
	 * @param jseflg4 jseflg4
	 */
	public void setJseflg4(final java.math.BigDecimal jseflg4) {
		this.jseflg4 = jseflg4;
	}

	/**
	 * jseflg5取得.
	 * @return jseflg5
	 */
	public java.math.BigDecimal getJseflg5() {
		return this.jseflg5;
	}

	/**
	 * jseflg5設定.
	 * @param jseflg5 jseflg5
	 */
	public void setJseflg5(final java.math.BigDecimal jseflg5) {
		this.jseflg5 = jseflg5;
	}

	/**
	 * jsekbn1取得.
	 * @return jsekbn1
	 */
	public java.math.BigDecimal getJsekbn1() {
		return this.jsekbn1;
	}

	/**
	 * jsekbn1設定.
	 * @param jsekbn1 jsekbn1
	 */
	public void setJsekbn1(final java.math.BigDecimal jsekbn1) {
		this.jsekbn1 = jsekbn1;
	}

	/**
	 * jsekbn2取得.
	 * @return jsekbn2
	 */
	public java.math.BigDecimal getJsekbn2() {
		return this.jsekbn2;
	}

	/**
	 * jsekbn2設定.
	 * @param jsekbn2 jsekbn2
	 */
	public void setJsekbn2(final java.math.BigDecimal jsekbn2) {
		this.jsekbn2 = jsekbn2;
	}

	/**
	 * jsekbn3取得.
	 * @return jsekbn3
	 */
	public java.math.BigDecimal getJsekbn3() {
		return this.jsekbn3;
	}

	/**
	 * jsekbn3設定.
	 * @param jsekbn3 jsekbn3
	 */
	public void setJsekbn3(final java.math.BigDecimal jsekbn3) {
		this.jsekbn3 = jsekbn3;
	}

	/**
	 * jsekbn4取得.
	 * @return jsekbn4
	 */
	public java.math.BigDecimal getJsekbn4() {
		return this.jsekbn4;
	}

	/**
	 * jsekbn4設定.
	 * @param jsekbn4 jsekbn4
	 */
	public void setJsekbn4(final java.math.BigDecimal jsekbn4) {
		this.jsekbn4 = jsekbn4;
	}

	/**
	 * jsekbn5取得.
	 * @return jsekbn5
	 */
	public java.math.BigDecimal getJsekbn5() {
		return this.jsekbn5;
	}

	/**
	 * jsekbn5設定.
	 * @param jsekbn5 jsekbn5
	 */
	public void setJsekbn5(final java.math.BigDecimal jsekbn5) {
		this.jsekbn5 = jsekbn5;
	}

	/**
	 * jsedate1取得.
	 * @return jsedate1
	 */
	public java.math.BigDecimal getJsedate1() {
		return this.jsedate1;
	}

	/**
	 * jsedate1設定.
	 * @param jsedate1 jsedate1
	 */
	public void setJsedate1(final java.math.BigDecimal jsedate1) {
		this.jsedate1 = jsedate1;
	}

	/**
	 * jsedate2取得.
	 * @return jsedate2
	 */
	public java.math.BigDecimal getJsedate2() {
		return this.jsedate2;
	}

	/**
	 * jsedate2設定.
	 * @param jsedate2 jsedate2
	 */
	public void setJsedate2(final java.math.BigDecimal jsedate2) {
		this.jsedate2 = jsedate2;
	}

	/**
	 * jsedate3取得.
	 * @return jsedate3
	 */
	public java.math.BigDecimal getJsedate3() {
		return this.jsedate3;
	}

	/**
	 * jsedate3設定.
	 * @param jsedate3 jsedate3
	 */
	public void setJsedate3(final java.math.BigDecimal jsedate3) {
		this.jsedate3 = jsedate3;
	}

	/**
	 * jsedate4取得.
	 * @return jsedate4
	 */
	public java.math.BigDecimal getJsedate4() {
		return this.jsedate4;
	}

	/**
	 * jsedate4設定.
	 * @param jsedate4 jsedate4
	 */
	public void setJsedate4(final java.math.BigDecimal jsedate4) {
		this.jsedate4 = jsedate4;
	}

	/**
	 * jsedate5取得.
	 * @return jsedate5
	 */
	public java.math.BigDecimal getJsedate5() {
		return this.jsedate5;
	}

	/**
	 * jsedate5設定.
	 * @param jsedate5 jsedate5
	 */
	public void setJsedate5(final java.math.BigDecimal jsedate5) {
		this.jsedate5 = jsedate5;
	}

	/**
	 * jsetime1取得.
	 * @return jsetime1
	 */
	public java.math.BigDecimal getJsetime1() {
		return this.jsetime1;
	}

	/**
	 * jsetime1設定.
	 * @param jsetime1 jsetime1
	 */
	public void setJsetime1(final java.math.BigDecimal jsetime1) {
		this.jsetime1 = jsetime1;
	}

	/**
	 * jsetime2取得.
	 * @return jsetime2
	 */
	public java.math.BigDecimal getJsetime2() {
		return this.jsetime2;
	}

	/**
	 * jsetime2設定.
	 * @param jsetime2 jsetime2
	 */
	public void setJsetime2(final java.math.BigDecimal jsetime2) {
		this.jsetime2 = jsetime2;
	}

	/**
	 * jsetime3取得.
	 * @return jsetime3
	 */
	public java.math.BigDecimal getJsetime3() {
		return this.jsetime3;
	}

	/**
	 * jsetime3設定.
	 * @param jsetime3 jsetime3
	 */
	public void setJsetime3(final java.math.BigDecimal jsetime3) {
		this.jsetime3 = jsetime3;
	}

	/**
	 * jsetime4取得.
	 * @return jsetime4
	 */
	public java.math.BigDecimal getJsetime4() {
		return this.jsetime4;
	}

	/**
	 * jsetime4設定.
	 * @param jsetime4 jsetime4
	 */
	public void setJsetime4(final java.math.BigDecimal jsetime4) {
		this.jsetime4 = jsetime4;
	}

	/**
	 * jsetime5取得.
	 * @return jsetime5
	 */
	public java.math.BigDecimal getJsetime5() {
		return this.jsetime5;
	}

	/**
	 * jsetime5設定.
	 * @param jsetime5 jsetime5
	 */
	public void setJsetime5(final java.math.BigDecimal jsetime5) {
		this.jsetime5 = jsetime5;
	}

	/**
	 * jsenum1取得.
	 * @return jsenum1
	 */
	public java.math.BigDecimal getJsenum1() {
		return this.jsenum1;
	}

	/**
	 * jsenum1設定.
	 * @param jsenum1 jsenum1
	 */
	public void setJsenum1(final java.math.BigDecimal jsenum1) {
		this.jsenum1 = jsenum1;
	}

	/**
	 * jsenum2取得.
	 * @return jsenum2
	 */
	public java.math.BigDecimal getJsenum2() {
		return this.jsenum2;
	}

	/**
	 * jsenum2設定.
	 * @param jsenum2 jsenum2
	 */
	public void setJsenum2(final java.math.BigDecimal jsenum2) {
		this.jsenum2 = jsenum2;
	}

	/**
	 * jsenum3取得.
	 * @return jsenum3
	 */
	public java.math.BigDecimal getJsenum3() {
		return this.jsenum3;
	}

	/**
	 * jsenum3設定.
	 * @param jsenum3 jsenum3
	 */
	public void setJsenum3(final java.math.BigDecimal jsenum3) {
		this.jsenum3 = jsenum3;
	}

	/**
	 * jsenum4取得.
	 * @return jsenum4
	 */
	public java.math.BigDecimal getJsenum4() {
		return this.jsenum4;
	}

	/**
	 * jsenum4設定.
	 * @param jsenum4 jsenum4
	 */
	public void setJsenum4(final java.math.BigDecimal jsenum4) {
		this.jsenum4 = jsenum4;
	}

	/**
	 * jsenum5取得.
	 * @return jsenum5
	 */
	public java.math.BigDecimal getJsenum5() {
		return this.jsenum5;
	}

	/**
	 * jsenum5設定.
	 * @param jsenum5 jsenum5
	 */
	public void setJsenum5(final java.math.BigDecimal jsenum5) {
		this.jsenum5 = jsenum5;
	}

	/**
	 * jsevalue1取得.
	 * @return jsevalue1
	 */
	public String getJsevalue1() {
		return this.jsevalue1;
	}

	/**
	 * jsevalue1設定.
	 * @param jsevalue1 jsevalue1
	 */
	public void setJsevalue1(final String jsevalue1) {
		this.jsevalue1 = jsevalue1;
	}

	/**
	 * jsevalue2取得.
	 * @return jsevalue2
	 */
	public String getJsevalue2() {
		return this.jsevalue2;
	}

	/**
	 * jsevalue2設定.
	 * @param jsevalue2 jsevalue2
	 */
	public void setJsevalue2(final String jsevalue2) {
		this.jsevalue2 = jsevalue2;
	}

	/**
	 * jsevalue3取得.
	 * @return jsevalue3
	 */
	public String getJsevalue3() {
		return this.jsevalue3;
	}

	/**
	 * jsevalue3設定.
	 * @param jsevalue3 jsevalue3
	 */
	public void setJsevalue3(final String jsevalue3) {
		this.jsevalue3 = jsevalue3;
	}

	/**
	 * jsevalue4取得.
	 * @return jsevalue4
	 */
	public String getJsevalue4() {
		return this.jsevalue4;
	}

	/**
	 * jsevalue4設定.
	 * @param jsevalue4 jsevalue4
	 */
	public void setJsevalue4(final String jsevalue4) {
		this.jsevalue4 = jsevalue4;
	}

	/**
	 * jsevalue5取得.
	 * @return jsevalue5
	 */
	public String getJsevalue5() {
		return this.jsevalue5;
	}

	/**
	 * jsevalue5設定.
	 * @param jsevalue5 jsevalue5
	 */
	public void setJsevalue5(final String jsevalue5) {
		this.jsevalue5 = jsevalue5;
	}

	/**
	 * companyCd取得.
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return this.companyCd;
	}

	/**
	 * companyCd設定.
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * centralBranchCd取得.
	 * @return centralBranchCd
	 */
	public String getCentralBranchCd() {
		return this.centralBranchCd;
	}

	/**
	 * centralBranchCd設定.
	 * @param centralBranchCd centralBranchCd
	 */
	public void setCentralBranchCd(final String centralBranchCd) {
		this.centralBranchCd = centralBranchCd;
	}

	/**
	 * calCd取得.
	 * @return calCd
	 */
	public String getCalCd() {
		return this.calCd;
	}

	/**
	 * calCd設定.
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * billOutsideCdPublish取得.
	 * @return billOutsideCdPublish
	 */
	public java.math.BigDecimal getBillOutsideCdPublish() {
		return this.billOutsideCdPublish;
	}

	/**
	 * billOutsideCdPublish設定.
	 * @param billOutsideCdPublish billOutsideCdPublish
	 */
	public void setBillOutsideCdPublish(final java.math.BigDecimal billOutsideCdPublish) {
		this.billOutsideCdPublish = billOutsideCdPublish;
	}

	/**
	 * accountingCd取得.
	 * @return accountingCd
	 */
	public String getAccountingCd() {
		return this.accountingCd;
	}

	/**
	 * accountingCd設定.
	 * @param accountingCd accountingCd
	 */
	public void setAccountingCd(final String accountingCd) {
		this.accountingCd = accountingCd;
	}

	/**
	 * creditIssuedDivision取得.
	 * @return creditIssuedDivision
	 */
	public java.math.BigDecimal getCreditIssuedDivision() {
		return this.creditIssuedDivision;
	}

	/**
	 * creditIssuedDivision設定.
	 * @param creditIssuedDivision creditIssuedDivision
	 */
	public void setCreditIssuedDivision(final java.math.BigDecimal creditIssuedDivision) {
		this.creditIssuedDivision = creditIssuedDivision;
	}

	/**
	 * paymentIssuedDivision取得.
	 * @return paymentIssuedDivision
	 */
	public java.math.BigDecimal getPaymentIssuedDivision() {
		return this.paymentIssuedDivision;
	}

	/**
	 * paymentIssuedDivision設定.
	 * @param paymentIssuedDivision paymentIssuedDivision
	 */
	public void setPaymentIssuedDivision(final java.math.BigDecimal paymentIssuedDivision) {
		this.paymentIssuedDivision = paymentIssuedDivision;
	}

	/**
	 * bankMasterCd1取得.
	 * @return bankMasterCd1
	 */
	public String getBankMasterCd1() {
		return this.bankMasterCd1;
	}

	/**
	 * bankMasterCd1設定.
	 * @param bankMasterCd1 bankMasterCd1
	 */
	public void setBankMasterCd1(final String bankMasterCd1) {
		this.bankMasterCd1 = bankMasterCd1;
	}

	/**
	 * accountAbbreviation1取得.
	 * @return accountAbbreviation1
	 */
	public String getAccountAbbreviation1() {
		return this.accountAbbreviation1;
	}

	/**
	 * accountAbbreviation1設定.
	 * @param accountAbbreviation1 accountAbbreviation1
	 */
	public void setAccountAbbreviation1(final String accountAbbreviation1) {
		this.accountAbbreviation1 = accountAbbreviation1;
	}

	/**
	 * accountDivision1取得.
	 * @return accountDivision1
	 */
	public java.math.BigDecimal getAccountDivision1() {
		return this.accountDivision1;
	}

	/**
	 * accountDivision1設定.
	 * @param accountDivision1 accountDivision1
	 */
	public void setAccountDivision1(final java.math.BigDecimal accountDivision1) {
		this.accountDivision1 = accountDivision1;
	}

	/**
	 * accountNo1取得.
	 * @return accountNo1
	 */
	public String getAccountNo1() {
		return this.accountNo1;
	}

	/**
	 * accountNo1設定.
	 * @param accountNo1 accountNo1
	 */
	public void setAccountNo1(final String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	/**
	 * accountStockhold1取得.
	 * @return accountStockhold1
	 */
	public String getAccountStockhold1() {
		return this.accountStockhold1;
	}

	/**
	 * accountStockhold1設定.
	 * @param accountStockhold1 accountStockhold1
	 */
	public void setAccountStockhold1(final String accountStockhold1) {
		this.accountStockhold1 = accountStockhold1;
	}

	/**
	 * bankMasterCd2取得.
	 * @return bankMasterCd2
	 */
	public String getBankMasterCd2() {
		return this.bankMasterCd2;
	}

	/**
	 * bankMasterCd2設定.
	 * @param bankMasterCd2 bankMasterCd2
	 */
	public void setBankMasterCd2(final String bankMasterCd2) {
		this.bankMasterCd2 = bankMasterCd2;
	}

	/**
	 * accountAbbreviation2取得.
	 * @return accountAbbreviation2
	 */
	public String getAccountAbbreviation2() {
		return this.accountAbbreviation2;
	}

	/**
	 * accountAbbreviation2設定.
	 * @param accountAbbreviation2 accountAbbreviation2
	 */
	public void setAccountAbbreviation2(final String accountAbbreviation2) {
		this.accountAbbreviation2 = accountAbbreviation2;
	}

	/**
	 * accountDivision2取得.
	 * @return accountDivision2
	 */
	public java.math.BigDecimal getAccountDivision2() {
		return this.accountDivision2;
	}

	/**
	 * accountDivision2設定.
	 * @param accountDivision2 accountDivision2
	 */
	public void setAccountDivision2(final java.math.BigDecimal accountDivision2) {
		this.accountDivision2 = accountDivision2;
	}

	/**
	 * accountNo2取得.
	 * @return accountNo2
	 */
	public String getAccountNo2() {
		return this.accountNo2;
	}

	/**
	 * accountNo2設定.
	 * @param accountNo2 accountNo2
	 */
	public void setAccountNo2(final String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	/**
	 * accountStockhold2取得.
	 * @return accountStockhold2
	 */
	public String getAccountStockhold2() {
		return this.accountStockhold2;
	}

	/**
	 * accountStockhold2設定.
	 * @param accountStockhold2 accountStockhold2
	 */
	public void setAccountStockhold2(final String accountStockhold2) {
		this.accountStockhold2 = accountStockhold2;
	}

	/**
	 * bankMasterCd3取得.
	 * @return bankMasterCd3
	 */
	public String getBankMasterCd3() {
		return this.bankMasterCd3;
	}

	/**
	 * bankMasterCd3設定.
	 * @param bankMasterCd3 bankMasterCd3
	 */
	public void setBankMasterCd3(final String bankMasterCd3) {
		this.bankMasterCd3 = bankMasterCd3;
	}

	/**
	 * accountAbbreviation3取得.
	 * @return accountAbbreviation3
	 */
	public String getAccountAbbreviation3() {
		return this.accountAbbreviation3;
	}

	/**
	 * accountAbbreviation3設定.
	 * @param accountAbbreviation3 accountAbbreviation3
	 */
	public void setAccountAbbreviation3(final String accountAbbreviation3) {
		this.accountAbbreviation3 = accountAbbreviation3;
	}

	/**
	 * accountDivision3取得.
	 * @return accountDivision3
	 */
	public java.math.BigDecimal getAccountDivision3() {
		return this.accountDivision3;
	}

	/**
	 * accountDivision3設定.
	 * @param accountDivision3 accountDivision3
	 */
	public void setAccountDivision3(final java.math.BigDecimal accountDivision3) {
		this.accountDivision3 = accountDivision3;
	}

	/**
	 * accountNo3取得.
	 * @return accountNo3
	 */
	public String getAccountNo3() {
		return this.accountNo3;
	}

	/**
	 * accountNo3設定.
	 * @param accountNo3 accountNo3
	 */
	public void setAccountNo3(final String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	/**
	 * accountStockhold3取得.
	 * @return accountStockhold3
	 */
	public String getAccountStockhold3() {
		return this.accountStockhold3;
	}

	/**
	 * accountStockhold3設定.
	 * @param accountStockhold3 accountStockhold3
	 */
	public void setAccountStockhold3(final String accountStockhold3) {
		this.accountStockhold3 = accountStockhold3;
	}

	/**
	 * bankMasterCd4取得.
	 * @return bankMasterCd4
	 */
	public String getBankMasterCd4() {
		return this.bankMasterCd4;
	}

	/**
	 * bankMasterCd4設定.
	 * @param bankMasterCd4 bankMasterCd4
	 */
	public void setBankMasterCd4(final String bankMasterCd4) {
		this.bankMasterCd4 = bankMasterCd4;
	}

	/**
	 * accountAbbreviation4取得.
	 * @return accountAbbreviation4
	 */
	public String getAccountAbbreviation4() {
		return this.accountAbbreviation4;
	}

	/**
	 * accountAbbreviation4設定.
	 * @param accountAbbreviation4 accountAbbreviation4
	 */
	public void setAccountAbbreviation4(final String accountAbbreviation4) {
		this.accountAbbreviation4 = accountAbbreviation4;
	}

	/**
	 * accountDivision4取得.
	 * @return accountDivision4
	 */
	public java.math.BigDecimal getAccountDivision4() {
		return this.accountDivision4;
	}

	/**
	 * accountDivision4設定.
	 * @param accountDivision4 accountDivision4
	 */
	public void setAccountDivision4(final java.math.BigDecimal accountDivision4) {
		this.accountDivision4 = accountDivision4;
	}

	/**
	 * accountNo4取得.
	 * @return accountNo4
	 */
	public String getAccountNo4() {
		return this.accountNo4;
	}

	/**
	 * accountNo4設定.
	 * @param accountNo4 accountNo4
	 */
	public void setAccountNo4(final String accountNo4) {
		this.accountNo4 = accountNo4;
	}

	/**
	 * accountStockhold4取得.
	 * @return accountStockhold4
	 */
	public String getAccountStockhold4() {
		return this.accountStockhold4;
	}

	/**
	 * accountStockhold4設定.
	 * @param accountStockhold4 accountStockhold4
	 */
	public void setAccountStockhold4(final String accountStockhold4) {
		this.accountStockhold4 = accountStockhold4;
	}

	/**
	 * bankMasterCd取得.
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * bankMasterCd設定.
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * accountDivision取得.
	 * @return accountDivision
	 */
	public java.math.BigDecimal getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final java.math.BigDecimal accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNo取得.
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * transferClientCd取得.
	 * @return transferClientCd
	 */
	public String getTransferClientCd() {
		return this.transferClientCd;
	}

	/**
	 * transferClientCd設定.
	 * @param transferClientCd transferClientCd
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * transferClientName取得.
	 * @return transferClientName
	 */
	public String getTransferClientName() {
		return this.transferClientName;
	}

	/**
	 * transferClientName設定.
	 * @param transferClientName transferClientName
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
	}

	/**
	 * passwordValidTerm取得.
	 * @return passwordValidTerm
	 */
	public java.math.BigDecimal getPasswordValidTerm() {
		return this.passwordValidTerm;
	}

	/**
	 * passwordValidTerm設定.
	 * @param passwordValidTerm passwordValidTerm
	 */
	public void setPasswordValidTerm(final java.math.BigDecimal passwordValidTerm) {
		this.passwordValidTerm = passwordValidTerm;
	}

	/**
	 * passwordKetaLowerLimit取得.
	 * @return passwordKetaLowerLimit
	 */
	public java.math.BigDecimal getPasswordKetaLowerLimit() {
		return this.passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaLowerLimit設定.
	 * @param passwordKetaLowerLimit passwordKetaLowerLimit
	 */
	public void setPasswordKetaLowerLimit(final java.math.BigDecimal passwordKetaLowerLimit) {
		this.passwordKetaLowerLimit = passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaUpperLimit取得.
	 * @return passwordKetaUpperLimit
	 */
	public java.math.BigDecimal getPasswordKetaUpperLimit() {
		return this.passwordKetaUpperLimit;
	}

	/**
	 * passwordKetaUpperLimit設定.
	 * @param passwordKetaUpperLimit passwordKetaUpperLimit
	 */
	public void setPasswordKetaUpperLimit(final java.math.BigDecimal passwordKetaUpperLimit) {
		this.passwordKetaUpperLimit = passwordKetaUpperLimit;
	}

	/**
	 * prime取得.
	 * @return prime
	 */
	public java.math.BigDecimal getPrime() {
		return this.prime;
	}

	/**
	 * prime設定.
	 * @param prime prime
	 */
	public void setPrime(final java.math.BigDecimal prime) {
		this.prime = prime;
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
	 * creditBankName1取得.
	 * @return creditBankName1
	 */
	public String getCreditBankName1() {
		return this.creditBankName1;
	}

	/**
	 * creditBankName1設定.
	 * @param creditBankName1 creditBankName1
	 */
	public void setCreditBankName1(final String creditBankName1) {
		this.creditBankName1 = creditBankName1;
	}

	/**
	 * creditBankName2取得.
	 * @return creditBankName2
	 */
	public String getCreditBankName2() {
		return this.creditBankName2;
	}

	/**
	 * creditBankName2設定.
	 * @param creditBankName2 creditBankName2
	 */
	public void setCreditBankName2(final String creditBankName2) {
		this.creditBankName2 = creditBankName2;
	}

	/**
	 * creditBankName3取得.
	 * @return creditBankName3
	 */
	public String getCreditBankName3() {
		return this.creditBankName3;
	}

	/**
	 * creditBankName3設定.
	 * @param creditBankName3 creditBankName3
	 */
	public void setCreditBankName3(final String creditBankName3) {
		this.creditBankName3 = creditBankName3;
	}

	/**
	 * creditBankName4取得.
	 * @return creditBankName4
	 */
	public String getCreditBankName4() {
		return this.creditBankName4;
	}

	/**
	 * creditBankName4設定.
	 * @param creditBankName4 creditBankName4
	 */
	public void setCreditBankName4(final String creditBankName4) {
		this.creditBankName4 = creditBankName4;
	}

	/**
	 * paymentBankName取得.
	 * @return paymentBankName
	 */
	public String getPaymentBankName() {
		return this.paymentBankName;
	}

	/**
	 * paymentBankName設定.
	 * @param paymentBankName paymentBankName
	 */
	public void setPaymentBankName(final String paymentBankName) {
		this.paymentBankName = paymentBankName;
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

