/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.arrival;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 入荷入力 詳細画面_表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ArrivalDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArrivalDetailListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "PURCHASE_SUBCONTRACT";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderDivideNo */
	public static final String orderDivideNo_COLUMN = "ORDER_DIVIDE_NO";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

	/** COLUMNアノテーション materialDivision. */
	public static final String materialDivision_COLUMN = "MATERIAL_DIVISION";

	/** COLUMNアノテーション siOrderNo */
	public static final String siOrderNo_COLUMN = "SI_ORDER_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション chargeOrganizationCd */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";

	/** COLUMNアノテーション unitpriceDefineunit */
	public static final String unitpriceDefineunit_COLUMN = "UNITPRICE_DEFINEUNIT";

	/** COLUMNアノテーション supplierOrdAmount */
	public static final String supplierOrdAmount_COLUMN = "SUPPLIER_ORD_AMOUNT";

	/** COLUMNアノテーション suggestedDeliverlimitDate */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション orderSheetRemark */
	public static final String orderSheetRemark_COLUMN = "ORDER_SHEET_REMARK";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション orderSheeFlag */
	public static final String orderSheeFlag_COLUMN = "ORDER_SHEE_FLAG";

	/** COLUMNアノテーション orderSheeDate */
	public static final String orderSheeDate_COLUMN = "ORDER_SHEE_DATE";

	/** COLUMNアノテーション orderSheelTantoCd */
	public static final String orderSheelTantoCd_COLUMN = "ORDER_SHEEL_TANTO_CD";

	/** COLUMNアノテーション goodHousingSum */
	public static final String goodHousingSum_COLUMN = "GOOD_HOUSING_SUM";

	/** COLUMNアノテーション replyContentsDivision */
	public static final String replyContentsDivision_COLUMN = "REPLY_CONTENTS_DIVISION";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション nextDeliverlimitDate */
	public static final String nextDeliverlimitDate_COLUMN = "NEXT_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション dataType. */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision. */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション stockingDate */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション accountYears. */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション cancelSlipNo. */
	public static final String cancelSlipNo_COLUMN = "CANCEL_SLIP_NO";

	/** COLUMNアノテーション cancelRowNo. */
	public static final String cancelRowNo_COLUMN = "CANCEL_ROW_NO";

	/** COLUMNアノテーション supplierLotno */
	public static final String supplierLotno_COLUMN = "SUPPLIER_LOTNO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション stockLocationCd */
	public static final String stockLocationCd_COLUMN = "STOCK_LOCATION_CD";

	/** COLUMNアノテーション housingLocationCd */
	public static final String housingLocationCd_COLUMN = "HOUSING_LOCATION_CD";

	/** COLUMNアノテーション arrivalQuantity */
	public static final String arrivalQuantity_COLUMN = "ARRIVAL_QUANTITY";

	/** COLUMNアノテーション stockQuantity */
	public static final String stockQuantity_COLUMN = "STOCK_QUANTITY";

	/** COLUMNアノテーション acceptQuantity */
	public static final String acceptQuantity_COLUMN = "ACCEPT_QUANTITY";

	/** COLUMNアノテーション acceptConvertQuantity */
	public static final String acceptConvertQuantity_COLUMN = "ACCEPT_CONVERT_QUANTITY";

	/** COLUMNアノテーション increaseQuantity */
	public static final String increaseQuantity_COLUMN = "INCREASE_QUANTITY";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション fareAmount */
	public static final String fareAmount_COLUMN = "FARE_AMOUNT";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション orderSheetRemark2 */
	public static final String orderSheetRemark2_COLUMN = "ORDER_SHEET_REMARK2";

	/** COLUMNアノテーション remark2 */
	public static final String remark2_COLUMN = "REMARK2";

	/** COLUMNアノテーション notes2 */
	public static final String notes2_COLUMN = "NOTES2";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション payeeCd */
	public static final String payeeCd_COLUMN = "PAYEE_CD";

	/** COLUMNアノテーション accountDebitSectionCd */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountDreditSectionCd */
	public static final String accountDreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitSubTitleCd */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditSubTitleCd */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** COLUMNアノテーション payableTargetDivision. */
	public static final String payableTargetDivision_COLUMN = "PAYABLE_TARGET_DIVISION";

	/** COLUMNアノテーション paymentTargetDivision. */
	public static final String paymentTargetDivision_COLUMN = "PAYMENT_TARGET_DIVISION";

	/** COLUMNアノテーション payableUpdateDivision. */
	public static final String payableUpdateDivision_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** COLUMNアノテーション payableNo. */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション paymentUpdateDivision. */
	public static final String paymentUpdateDivision_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** COLUMNアノテーション paymentNo. */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション summaryCd. */
	public static final String summaryCd_COLUMN = "SUMMARY_CD";

	/** COLUMNアノテーション summary. */
	public static final String summary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション paymentUpdateDate */
	public static final String paymentUpdateDate_COLUMN = "PAYMENT_UPDATE_DATE";

	/** COLUMNアノテーション payableUpdateDate */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** COLUMNアノテーション transmissionDate */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション labelFlag */
	public static final String labelFlag_COLUMN = "LABEL_FLAG";

	/** COLUMNアノテーション labelDate */
	public static final String labelDate_COLUMN = "LABEL_DATE";

	/** COLUMNアノテーション labelTantoCd */
	public static final String labelTantoCd_COLUMN = "LABEL_TANTO_CD";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション inspectMethod */
	public static final String inspectMethod_COLUMN = "INSPECT_METHOD";

	/** COLUMNアノテーション mortgageDetailFlg */
	public static final String mortgageDetailFlg_COLUMN = "MORTGAGE_DETAIL_FLG";

	/** COLUMNアノテーション inspreceiptWaitQuantity */
	public static final String inspreceiptWaitQuantity_COLUMN = "INSPRECEIPT_WAIT_QUANTITY";

	/** COLUMNアノテーション badQuantity */
	public static final String badQuantity_COLUMN = "BAD_QUANTITY";

	/** COLUMNアノテーション costEntry */
	public static final String costEntry_COLUMN = "COST_ENTRY";

	/** COLUMNアノテーション advPurNoticeDecideDivision */
	public static final String advPurNoticeDecideDivision_COLUMN = "ADV_PUR_NOTICE_DECIDE_DIVISION";

	/** COLUMNアノテーション orderMortgagedQuantity */
	public static final String orderMortgagedQuantity_COLUMN = "ORDER_MORTGAGED_QUANTITY";

	/** COLUMNアノテーション sumHousingConvertQuant */
	public static final String sumHousingConvertQuant_COLUMN = "SUM_HOUSING_CONVERT_QUANT";

	/** COLUMNアノテーション inspectWaitConvertQuantity */
	public static final String inspectWaitConvertQuantity_COLUMN = "INSPECT_WAIT_CONVERT_QUANTITY";

	/** COLUMNアノテーション extractionsQuantity */
	public static final String extractionsQuantity_COLUMN = "EXTRACTIONS_QUANTITY";

	/** COLUMNアノテーション defectiveQuantity */
	public static final String defectiveQuantity_COLUMN = "DEFECTIVE_QUANTITY";

	/** COLUMNアノテーション checkTantoCd */
	public static final String checkTantoCd_COLUMN = "CHECK_TANTO_CD";

	/** COLUMNアノテーション laboratoryMethod */
	public static final String laboratoryMethod_COLUMN = "LABORATORY_METHOD";

	/** COLUMNアノテーション provisionDivision */
	public static final String provisionDivision_COLUMN = "PROVISION_DIVISION";

	/** COLUMNアノテーション checkDate */
	public static final String checkDate_COLUMN = "CHECK_DATE";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション checkQuantity */
	public static final String checkQuantity_COLUMN = "CHECK_QUANTITY";

	/** COLUMNアノテーション slipIssueDivision */
	public static final String slipIssueDivision_COLUMN = "SLIP_ISSUE_DIVISION";

	/** COLUMNアノテーション slipIssueDate */
	public static final String slipIssueDate_COLUMN = "SLIP_ISSUE_DATE";

	/** COLUMNアノテーション approvalStatus. */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby. */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate. */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注番号分納枝番 */
	private String orderDivideNo;

	/** 受注番号 */
	private String orderNo;

	/** 行番号(受注) */
	private String orderRowNo;

	/** アスプローバオーダーコード */
	private String aspOrderNo;

	/** 外注原材料区分 */
	private BigDecimal materialDivision;

	/** 仕入先受注番 */
	private String siOrderNo;

	/** 発注日 */
	private Timestamp orderDate;

	/** 担当部署コード */
	private String chargeOrganizationCd;

	/** 部署コード */
	private String organizationCd;

	/** 発注担当者コード */
	private String tantoCd;

	/** オーダー区分 */
	private BigDecimal orderDivision;

	/** 仕入先コード */
	private String venderCd;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 発注単価 */
	private BigDecimal orderUnitprice;

	/** 単価決定単位区分|0..個単位の単価　1..KGあたりの単価 */
	private BigDecimal unitpriceDefineunit;

	/** 発注金額 */
	private BigDecimal supplierOrdAmount;

	/** 納品希望日 */
	private Timestamp suggestedDeliverlimitDate;

	/** 発注書備考 */
	private String orderSheetRemark;

	/** 備考 */
	private String remark;

	/** 注釈 */
	private String notes;

	/** 納入ロケーションコード */
	private String locationCd;

	/** 購買ステータス */
	private BigDecimal status;

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注書発行フラグ */
	private BigDecimal orderSheeFlag;

	/** 発注書発行日 */
	private Timestamp orderSheeDate;

	/** 発注書発行者 */
	private String orderSheelTantoCd;

	/** 生産出来高 */
	private BigDecimal goodHousingSum;

	/** 分納区分|0:無し 1:有り */
	private BigDecimal replyContentsDivision;

	/** 完納区分 */
	private BigDecimal deliveryComp;

	/** 次回納品希望日 */
	private Timestamp nextDeliverlimitDate;

	/** ﾃﾞｰﾀ種別 */
	private BigDecimal dataType;

	/** ﾃﾞｰﾀ集計区分 */
	private BigDecimal dataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 仕入日付 */
	private Timestamp stockingDate;

	/** 勘定年月 */
	private String accountYears;

	/** 仕入番号 */
	private String slipNo;

	/** 行番号 */
	private BigDecimal rowNo;

	/** 仕入-取消　仕入番号 */
	private String cancelSlipNo;

	/** 仕入-取消　行番号 */
	private BigDecimal cancelRowNo;

	/** メーカーロット番号 */
	private String supplierLotno;

	/** 入荷ロット番号 */
	private String lotNo;

	/** 入庫ロケーションコード(I/Fから登録) */
	private String stockLocationCd;

	/** 入庫ロケーションコード */
	private String housingLocationCd;

	/** 入荷予定数量 */
	private BigDecimal arrivalQuantity;

	/** 入庫実績数(I/Fから登録) */
	private BigDecimal stockQuantity;

	/** 受入数量 */
	private BigDecimal acceptQuantity;

	/** 受入重量 */
	private BigDecimal acceptConvertQuantity;

	/** 増付数量 */
	private BigDecimal increaseQuantity;

	/** 仕入数量 */
	private BigDecimal stockingQuantity;

	/** 仕入単価 */
	private BigDecimal housingUnitprice;

	/** 運賃 */
	private BigDecimal fareAmount;

	/** 仕入金額 */
	private BigDecimal stockingAmount;

	/** 受入日付 */
	private Timestamp acceptDate;

	/** 発注書備考（入荷以降） */
	private String orderSheetRemark2;

	/** 備考（入荷以降） */
	private String remark2;

	/** 注釈（入荷以降） */
	private String notes2;

	/** 仕入ステータス */
	private BigDecimal status2;

	/** 消費税課税区分 */
	private BigDecimal taxDivision;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 支払先コード */
	private String payeeCd;

	/** 会計部門借方コード */
	private String accountDebitSectionCd;

	/** 会計部門貸方コード */
	private String accountDreditSectionCd;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 借方補助科目コード */
	private String debitSubTitleCd;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 貸方補助科目コード */
	private String creditSubTitleCd;

	/** 買掛対象 */
	private BigDecimal payableTargetDivision;

	/** 支払対象 */
	private BigDecimal paymentTargetDivision;

	/** 買掛更新フラグ */
	private BigDecimal payableUpdateDivision;

	/** 買掛番号 */
	private String payableNo;

	/** 支払更新フラグ */
	private BigDecimal paymentUpdateDivision;

	/** 支払番号 */
	private String paymentNo;

	/** 摘要コード */
	private String summaryCd;

	/** 摘要名 */
	private String summary;

	/** 支払締め日 */
	private Timestamp paymentUpdateDate;

	/** 買掛締め日 */
	private Timestamp payableUpdateDate;

	/** データ転送日時 */
	private Timestamp transmissionDate;

	/** ラベル発行フラグ|0:未発行,1:発行 */
	private BigDecimal labelFlag;

	/** ラベル発行日 */
	private Timestamp labelDate;

	/** ラベル発行者 */
	private String labelTantoCd;

	/** 仮単価FLG|0:通常 1:仮単価 */
	private BigDecimal tmpUnitpriceFlg;

	/** 検査方法|0:無検査 1:抜取検査 2:全品検査 */
	private BigDecimal inspectMethod;

	/** 引当明細FLG|0:なし 1:引当あり */
	private BigDecimal mortgageDetailFlg;

	/** 検収待ち数 */
	private BigDecimal inspreceiptWaitQuantity;

	/** 不良数 */
	private BigDecimal badQuantity;

	/** 原価項目 */
	private BigDecimal costEntry;

	/** 内示・確定区分|0:内示 1:確定 */
	private BigDecimal advPurNoticeDecideDivision;

	/** オーダー引当済数 */
	private BigDecimal orderMortgagedQuantity;

	/** 累計入庫数換算量 */
	private BigDecimal sumHousingConvertQuant;

	/** 検査待数換算量 */
	private BigDecimal inspectWaitConvertQuantity;

	/** 検収抜取数 */
	private BigDecimal extractionsQuantity;

	/** 不良数 */
	private BigDecimal defectiveQuantity;

	/** 検収担当者コード */
	private String checkTantoCd;

	/** 検査方法|0:無検査 1:抜取検査 2:全品検査 */
	private BigDecimal laboratoryMethod;

	/** 支給区分|0:無償 1:有償 */
	private BigDecimal provisionDivision;

	/** 検収日付 */
	private Timestamp checkDate;

	/** 消費税率 */
	private BigDecimal taxRatio;

	/** 検収数 */
	private BigDecimal checkQuantity;

	/** 伝票発行済み区分|0..未発行 1..発行済 */
	private BigDecimal slipIssueDivision;

	/** 伝票発行日 */
	private Timestamp slipIssueDate;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	/** 承認者 */
	private String approvedby;

	/** 承認日 */
	private Timestamp approvaldate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者 */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者 */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	*/
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	*/
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得
	 * @return String 発注番号
	*/
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	*/
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注番号分納枝番取得
	 * @return String 発注番号分納枝番
	*/
	public String getOrderDivideNo() {
		return this.orderDivideNo;
	}

	/**
	 * 発注番号分納枝番設定
	 * @param orderDivideNo 発注番号分納枝番
	*/
	public void setOrderDivideNo(final String orderDivideNo) {
		this.orderDivideNo = orderDivideNo;
	}

	/**
	 * 受注番号取得
	 * @return String 受注番号
	*/
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	*/
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 行番号(受注)取得
	 * @return String 行番号(受注)
	 */
	public String getOrderRowNo() {
		return orderRowNo;
	}

	/**
	 * 行番号(受注)設定
	 * @param orderRowNo 行番号(受注)
	 */
	public void setOrderRowNo(final String orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * アスプローバオーダーコード取得
	 * @return String アスプローバオーダーコード
	 */
	public String getAspOrderNo() {
		return aspOrderNo;
	}

	/**
	 * アスプローバオーダーコード設定
	 * @param aspOrderNo アスプローバオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * 外注原材料区分取得.
	 * @return BigDecimal 外注原材料区分
	 */
	public BigDecimal getMaterialDivision() {
		return this.materialDivision;
	}

	/**
	 * 外注原材料区分設定.
	 * @param materialDivision 外注原材料区分
	 */
	public void setMaterialDivision(final BigDecimal materialDivision) {
		this.materialDivision = materialDivision;
	}

	/**
	 * 仕入先受注番取得
	 * @return String 仕入先受注番
	*/
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定
	 * @param siOrderNo 仕入先受注番
	*/
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
	}

	/**
	 * 発注日取得
	 * @return Timestamp 発注日
	*/
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * 発注日設定
	 * @param orderDate 発注日
	*/
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 担当部署コード取得
	 * @return String 担当部署コード
	*/
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * 担当部署コード設定
	 * @param chargeOrganizationCd 担当部署コード
	*/
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 部署コード取得
	 * @return String 部署コード
	*/
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * 部署コード設定
	 * @param organizationCd 部署コード
	*/
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 発注担当者コード取得
	 * @return String 発注担当者コード
	*/
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * 発注担当者コード設定
	 * @param tantoCd 発注担当者コード
	*/
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * オーダー区分取得
	 * @return BigDecimal オーダー区分
	*/
	public BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * オーダー区分設定
	 * @param orderDivision オーダー区分
	*/
	public void setOrderDivision(final BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * 仕入先コード取得
	 * @return String 仕入先コード
	*/
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定
	 * @param venderCd 仕入先コード
	*/
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	*/
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	*/
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称取得
	 * @return String 品目名称
	*/
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	*/
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 発注数量取得
	 * @return BigDecimal 発注数量
	*/
	public BigDecimal getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定
	 * @param orderQuantity 発注数量
	*/
	public void setOrderQuantity(final BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 重量取得
	 * @return BigDecimal 重量
	*/
	public BigDecimal getOrderConvertQuantity() {
		return this.orderConvertQuantity;
	}

	/**
	 * 重量設定
	 * @param orderConvertQuantity 重量
	*/
	public void setOrderConvertQuantity(final BigDecimal orderConvertQuantity) {
		this.orderConvertQuantity = orderConvertQuantity;
	}

	/**
	 * 発注単価取得
	 * @return BigDecimal 発注単価
	*/
	public BigDecimal getOrderUnitprice() {
		return this.orderUnitprice;
	}

	/**
	 * 発注単価設定
	 * @param orderUnitprice 発注単価
	*/
	public void setOrderUnitprice(final BigDecimal orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}

	/**
	 * 単価決定単位区分|0..個単位の単価　1..KGあたりの単価取得
	 * @return BigDecimal 単価決定単位区分|0..個単位の単価　1..KGあたりの単価
	*/
	public BigDecimal getUnitpriceDefineunit() {
		return this.unitpriceDefineunit;
	}

	/**
	 * 単価決定単位区分|0..個単位の単価　1..KGあたりの単価設定
	 * @param unitpriceDefineunit 単価決定単位区分|0..個単位の単価　1..KGあたりの単価
	*/
	public void setUnitpriceDefineunit(final BigDecimal unitpriceDefineunit) {
		this.unitpriceDefineunit = unitpriceDefineunit;
	}

	/**
	 * 発注金額取得
	 * @return BigDecimal 発注金額
	*/
	public BigDecimal getSupplierOrdAmount() {
		return this.supplierOrdAmount;
	}

	/**
	 * 発注金額設定
	 * @param supplierOrdAmount 発注金額
	*/
	public void setSupplierOrdAmount(final BigDecimal supplierOrdAmount) {
		this.supplierOrdAmount = supplierOrdAmount;
	}

	/**
	 * 納品希望日取得
	 * @return Timestamp 納品希望日
	*/
	public Timestamp getSuggestedDeliverlimitDate() {
		return this.suggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定
	 * @param suggestedDeliverlimitDate 納品希望日
	*/
	public void setSuggestedDeliverlimitDate(final Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

	/**
	 * 発注書備考取得
	 * @return String 発注書備考
	*/
	public String getOrderSheetRemark() {
		return this.orderSheetRemark;
	}

	/**
	 * 発注書備考設定
	 * @param orderSheetRemark 発注書備考
	*/
	public void setOrderSheetRemark(final String orderSheetRemark) {
		this.orderSheetRemark = orderSheetRemark;
	}

	/**
	 * 備考取得
	 * @return String 備考
	*/
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 備考設定
	 * @param remark 備考
	*/
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 注釈取得
	 * @return String 注釈
	*/
	public String getNotes() {
		return this.notes;
	}

	/**
	 * 注釈設定
	 * @param notes 注釈
	*/
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * 納入ロケーションコード取得
	 * @return String 納入ロケーションコード
	*/
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * 納入ロケーションコード設定
	 * @param locationCd 納入ロケーションコード
	*/
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * 購買ステータス取得
	 * @return BigDecimal 購買ステータス
	*/
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定
	 * @param status 購買ステータス
	*/
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * 発注書NO取得
	 * @return String 発注書NO
	*/
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定
	 * @param orderSheetNo 発注書NO
	*/
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 発注書発行フラグ取得
	 * @return BigDecimal 発注書発行フラグ
	*/
	public BigDecimal getOrderSheeFlag() {
		return this.orderSheeFlag;
	}

	/**
	 * 発注書発行フラグ設定
	 * @param orderSheeFlag 発注書発行フラグ
	*/
	public void setOrderSheeFlag(final BigDecimal orderSheeFlag) {
		this.orderSheeFlag = orderSheeFlag;
	}

	/**
	 * 発注書発行日取得
	 * @return Timestamp 発注書発行日
	*/
	public Timestamp getOrderSheeDate() {
		return this.orderSheeDate;
	}

	/**
	 * 発注書発行日設定
	 * @param orderSheeDate 発注書発行日
	*/
	public void setOrderSheeDate(final Timestamp orderSheeDate) {
		this.orderSheeDate = orderSheeDate;
	}

	/**
	 * 発注書発行者取得
	 * @return String 発注書発行者
	*/
	public String getOrderSheelTantoCd() {
		return this.orderSheelTantoCd;
	}

	/**
	 * 発注書発行者設定
	 * @param orderSheelTantoCd 発注書発行者
	*/
	public void setOrderSheelTantoCd(final String orderSheelTantoCd) {
		this.orderSheelTantoCd = orderSheelTantoCd;
	}

	/**
	 * 生産出来高取得
	 * @return BigDecimal 生産出来高
	*/
	public BigDecimal getGoodHousingSum() {
		return this.goodHousingSum;
	}

	/**
	 * 生産出来高設定
	 * @param goodHousingSum 生産出来高
	*/
	public void setGoodHousingSum(final BigDecimal goodHousingSum) {
		this.goodHousingSum = goodHousingSum;
	}

	/**
	 * 分納区分|0:無し 1:有り取得
	 * @return BigDecimal 分納区分|0:無し 1:有り
	*/
	public BigDecimal getReplyContentsDivision() {
		return this.replyContentsDivision;
	}

	/**
	 * 分納区分|0:無し 1:有り設定
	 * @param replyContentsDivision 分納区分|0:無し 1:有り
	*/
	public void setReplyContentsDivision(final BigDecimal replyContentsDivision) {
		this.replyContentsDivision = replyContentsDivision;
	}

	/**
	 * 完納区分取得
	 * @return BigDecimal 完納区分
	*/
	public BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * 完納区分設定
	 * @param deliveryComp 完納区分
	*/
	public void setDeliveryComp(final BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * 次回納品希望日取得
	 * @return Timestamp 次回納品希望日
	*/
	public Timestamp getNextDeliverlimitDate() {
		return this.nextDeliverlimitDate;
	}

	/**
	 * 次回納品希望日設定
	 * @param nextDeliverlimitDate 次回納品希望日
	*/
	public void setNextDeliverlimitDate(final Timestamp nextDeliverlimitDate) {
		this.nextDeliverlimitDate = nextDeliverlimitDate;
	}

	/**
	 * ﾃﾞｰﾀ種別取得.
	 * @return BigDecimal ﾃﾞｰﾀ種別
	 */
	public BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * ﾃﾞｰﾀ種別設定.
	 * @param dataType ﾃﾞｰﾀ種別
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * ﾃﾞｰﾀ集計区分取得.
	 * @return dataTotalDivision ﾃﾞｰﾀ集計区分
	 */
	public BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * ﾃﾞｰﾀ集計区分設定.
	 * @param dataTotalDivision ﾃﾞｰﾀ集計区分
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * 分類コード取得.
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定.
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 仕入日付取得
	 * @return Timestamp 仕入日付
	*/
	public Timestamp getStockingDate() {
		return this.stockingDate;
	}

	/**
	 * 仕入日付設定
	 * @param stockingDate 仕入日付
	*/
	public void setStockingDate(final Timestamp stockingDate) {
		this.stockingDate = stockingDate;
	}

	/**
	 * 勘定年月取得.
	 * @return String 勘定年月
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * 勘定年月設定.
	 * @param accountYears 勘定年月
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

	/**
	 * 仕入番号取得
	 * @return String 仕入番号
	*/
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * 仕入番号設定
	 * @param slipNo 仕入番号
	*/
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 行番号取得
	 * @return BigDecimal 行番号
	*/
	public BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * 行番号設定
	 * @param rowNo 行番号
	*/
	public void setRowNo(final BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 仕入-取消　仕入番号取得.
	 * @return String 仕入-取消　仕入番号
	 */
	public String getCancelSlipNo() {
		return this.cancelSlipNo;
	}

	/**
	 * 仕入-取消　仕入番号設定.
	 * @param cancelSlipNo 仕入-取消　仕入番号
	 */
	public void setCancelSlipNo(final String cancelSlipNo) {
		this.cancelSlipNo = cancelSlipNo;
	}

	/**
	 * 仕入-取消　行番号取得.
	 * @return BigDecimal 仕入-取消　行番号
	 */
	public BigDecimal getCancelRowNo() {
		return this.cancelRowNo;
	}

	/**
	 * 仕入-取消　行番号設定.
	 * @param cancelRowNo 仕入-取消　行番号
	 */
	public void setCancelRowNo(final BigDecimal cancelRowNo) {
		this.cancelRowNo = cancelRowNo;
	}

	/**
	 * メーカーロット番号取得
	 * @return String メーカーロット番号
	*/
	public String getSupplierLotno() {
		return this.supplierLotno;
	}

	/**
	 * メーカーロット番号設定
	 * @param supplierLotno メーカーロット番号
	*/
	public void setSupplierLotno(final String supplierLotno) {
		this.supplierLotno = supplierLotno;
	}

	/**
	 * 入荷ロット番号取得
	 * @return String 入荷ロット番号
	*/
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * 入荷ロット番号設定
	 * @param lotNo 入荷ロット番号
	*/
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 入庫ロケーションコード(I/Fから登録)取得
	 * @return String 入庫ロケーションコード(I/Fから登録)
	*/
	public String getStockLocationCd() {
		return this.stockLocationCd;
	}

	/**
	 * 入庫ロケーションコード(I/Fから登録)設定
	 * @param stockLocationCd 入庫ロケーションコード(I/Fから登録)
	*/
	public void setStockLocationCd(final String stockLocationCd) {
		this.stockLocationCd = stockLocationCd;
	}

	/**
	 * 入庫ロケーションコード取得
	 * @return String 入庫ロケーションコード
	*/
	public String getHousingLocationCd() {
		return this.housingLocationCd;
	}

	/**
	 * 入庫ロケーションコード設定
	 * @param housingLocationCd 入庫ロケーションコード
	*/
	public void setHousingLocationCd(final String housingLocationCd) {
		this.housingLocationCd = housingLocationCd;
	}

	/**
	 * 入荷予定数量取得
	 * @return BigDecimal 入荷予定数量
	*/
	public BigDecimal getArrivalQuantity() {
		return this.arrivalQuantity;
	}

	/**
	 * 入荷予定数量設定
	 * @param arrivalQuantity 入荷予定数量
	*/
	public void setArrivalQuantity(final BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	/**
	 * 入庫実績数(I/Fから登録)取得
	 * @return BigDecimal 入庫実績数(I/Fから登録)
	*/
	public BigDecimal getStockQuantity() {
		return this.stockQuantity;
	}

	/**
	 * 入庫実績数(I/Fから登録)設定
	 * @param stockQuantity 入庫実績数(I/Fから登録)
	*/
	public void setStockQuantity(final BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * 受入数量取得
	 * @return BigDecimal 受入数量
	*/
	public BigDecimal getAcceptQuantity() {
		return this.acceptQuantity;
	}

	/**
	 * 受入数量設定
	 * @param acceptQuantity 受入数量
	*/
	public void setAcceptQuantity(final BigDecimal acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	/**
	 * 受入重量取得
	 * @return BigDecimal 受入重量
	*/
	public BigDecimal getAcceptConvertQuantity() {
		return this.acceptConvertQuantity;
	}

	/**
	 * 受入重量設定
	 * @param acceptConvertQuantity 受入重量
	*/
	public void setAcceptConvertQuantity(final BigDecimal acceptConvertQuantity) {
		this.acceptConvertQuantity = acceptConvertQuantity;
	}

	/**
	 * 増付数量取得
	 * @return BigDecimal 増付数量
	*/
	public BigDecimal getIncreaseQuantity() {
		return this.increaseQuantity;
	}

	/**
	 * 増付数量設定
	 * @param increaseQuantity 増付数量
	*/
	public void setIncreaseQuantity(final BigDecimal increaseQuantity) {
		this.increaseQuantity = increaseQuantity;
	}

	/**
	 * 仕入数量取得
	 * @return BigDecimal 仕入数量
	*/
	public BigDecimal getStockingQuantity() {
		return this.stockingQuantity;
	}

	/**
	 * 仕入数量設定
	 * @param stockingQuantity 仕入数量
	*/
	public void setStockingQuantity(final BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
	}

	/**
	 * 仕入単価取得
	 * @return BigDecimal 仕入単価
	*/
	public BigDecimal getHousingUnitprice() {
		return this.housingUnitprice;
	}

	/**
	 * 仕入単価設定
	 * @param housingUnitprice 仕入単価
	*/
	public void setHousingUnitprice(final BigDecimal housingUnitprice) {
		this.housingUnitprice = housingUnitprice;
	}

	/**
	 * 運賃取得
	 * @return BigDecimal 運賃
	*/
	public BigDecimal getFareAmount() {
		return this.fareAmount;
	}

	/**
	 * 運賃設定
	 * @param fareAmount 運賃
	*/
	public void setFareAmount(final BigDecimal fareAmount) {
		this.fareAmount = fareAmount;
	}

	/**
	 * 仕入金額取得
	 * @return BigDecimal 仕入金額
	*/
	public BigDecimal getStockingAmount() {
		return this.stockingAmount;
	}

	/**
	 * 仕入金額設定
	 * @param stockingAmount 仕入金額
	*/
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * 受入日付取得
	 * @return Timestamp 受入日付
	*/
	public Timestamp getAcceptDate() {
		return this.acceptDate;
	}

	/**
	 * 受入日付設定
	 * @param acceptDate 受入日付
	*/
	public void setAcceptDate(final Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	/**
	 * 発注書備考（入荷以降）取得
	 * @return String 発注書備考（入荷以降）
	*/
	public String getOrderSheetRemark2() {
		return this.orderSheetRemark2;
	}

	/**
	 * 発注書備考（入荷以降）設定
	 * @param orderSheetRemark2 発注書備考（入荷以降）
	*/
	public void setOrderSheetRemark2(final String orderSheetRemark2) {
		this.orderSheetRemark2 = orderSheetRemark2;
	}

	/**
	 * 備考（入荷以降）取得
	 * @return String 備考（入荷以降）
	*/
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * 備考（入荷以降）設定
	 * @param remark2 備考（入荷以降）
	*/
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * 注釈（入荷以降）取得
	 * @return String 注釈（入荷以降）
	*/
	public String getNotes2() {
		return this.notes2;
	}

	/**
	 * 注釈（入荷以降）設定
	 * @param notes2 注釈（入荷以降）
	*/
	public void setNotes2(final String notes2) {
		this.notes2 = notes2;
	}

	/**
	 * 仕入ステータス取得
	 * @return BigDecimal 仕入ステータス
	*/
	public BigDecimal getStatus2() {
		return this.status2;
	}

	/**
	 * 仕入ステータス設定
	 * @param status2 仕入ステータス
	*/
	public void setStatus2(final BigDecimal status2) {
		this.status2 = status2;
	}

	/**
	 * 消費税課税区分取得
	 * @return BigDecimal 消費税課税区分
	*/
	public BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * 消費税課税区分設定
	 * @param taxDivision 消費税課税区分
	*/
	public void setTaxDivision(final BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * 消費税額取得
	 * @return BigDecimal 消費税額
	*/
	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * 消費税額設定
	 * @param taxAmount 消費税額
	*/
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * 支払先コード取得
	 * @return String 支払先コード
	*/
	public String getPayeeCd() {
		return this.payeeCd;
	}

	/**
	 * 支払先コード設定
	 * @param payeeCd 支払先コード
	*/
	public void setPayeeCd(final String payeeCd) {
		this.payeeCd = payeeCd;
	}

	/**
	 * 会計部門借方コード取得
	 * @return String 会計部門借方コード
	*/
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * 会計部門借方コード設定
	 * @param accountDebitSectionCd 会計部門借方コード
	*/
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門貸方コード取得
	 * @return String 会計部門貸方コード
	*/
	public String getAccountCreditSectionCd() {
		return this.accountDreditSectionCd;
	}

	/**
	 * 会計部門貸方コード設定
	 * @param accountDreditSectionCd 会計部門貸方コード
	*/
	public void setAccountCreditSectionCd(final String accountDreditSectionCd) {
		this.accountDreditSectionCd = accountDreditSectionCd;
	}

	/**
	 * 借方科目コード取得
	 * @return String 借方科目コード
	*/
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * 借方科目コード設定
	 * @param debitTitleCd 借方科目コード
	*/
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 借方補助科目コード取得
	 * @return String 借方補助科目コード
	*/
	public String getDebitSubTitleCd() {
		return this.debitSubTitleCd;
	}

	/**
	 * 借方補助科目コード設定
	 * @param debitSubTitleCd 借方補助科目コード
	*/
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
	}

	/**
	 * 貸方科目コード取得
	 * @return String 貸方科目コード
	*/
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * 貸方科目コード設定
	 * @param creditTitleCd 貸方科目コード
	*/
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 貸方補助科目コード取得
	 * @return String 貸方補助科目コード
	*/
	public String getCreditSubTitleCd() {
		return this.creditSubTitleCd;
	}

	/**
	 * 貸方補助科目コード設定
	 * @param creditSubTitleCd 貸方補助科目コード
	*/
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * 買掛対象取得.
	 * @return BigDecimal 買掛対象
	 */
	public BigDecimal getPayableTargetDivision() {
		return this.payableTargetDivision;
	}

	/**
	 * 買掛対象設定.
	 * @param payableTargetDivision 買掛対象
	 */
	public void setPayableTargetDivision(final BigDecimal payableTargetDivision) {
		this.payableTargetDivision = payableTargetDivision;
	}

	/**
	 * 支払対象取得.
	 * @return BigDecimal 支払対象
	 */
	public BigDecimal getPaymentTargetDivision() {
		return this.paymentTargetDivision;
	}

	/**
	 * 支払対象設定.
	 * @param paymentTargetDivision 支払対象
	 */
	public void setPaymentTargetDivision(final BigDecimal paymentTargetDivision) {
		this.paymentTargetDivision = paymentTargetDivision;
	}

	/**
	 * 買掛更新フラグ取得.
	 * @return BigDecimal 買掛更新フラグ
	 */
	public BigDecimal getPayableUpdateDivision() {
		return this.payableUpdateDivision;
	}

	/**
	 * 買掛更新フラグ設定.
	 * @param payableUpdateDivision 買掛更新フラグ
	 */
	public void setPayableUpdateDivision(final BigDecimal payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * 買掛番号取得.
	 * @return String 買掛番号
	 */
	public String getPayableNo() {
		return this.payableNo;
	}

	/**
	 * 買掛番号設定.
	 * @param payableNo 買掛番号
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * 支払更新フラグ取得.
	 * @return BigDecimal 支払更新フラグ
	 */
	public BigDecimal getPaymentUpdateDivision() {
		return this.paymentUpdateDivision;
	}

	/**
	 * 支払更新フラグ設定.
	 * @param paymentUpdateDivision 支払更新フラグ
	 */
	public void setPaymentUpdateDivision(final BigDecimal paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

	/**
	 * 支払番号取得.
	 * @return String 支払番号
	 */
	public String getPaymentNo() {
		return this.paymentNo;
	}

	/**
	 * 支払番号設定.
	 * @param paymentNo 支払番号
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * 摘要コード取得.
	 * @return summaryCd 摘要コード
	 */
	public String getSummaryCd() {
		return this.summaryCd;
	}

	/**
	 * 摘要コード設定.
	 * @param summaryCd 摘要コード
	 */
	public void setSummaryCd(final String summaryCd) {
		this.summaryCd = summaryCd;
	}

	/**
	 * 摘要名取得.
	 * @return String 摘要名
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * 摘要名設定.
	 * @param summary 摘要名
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * 支払締め日取得
	 * @return Timestamp 支払締め日
	*/
	public Timestamp getPaymentUpdateDate() {
		return this.paymentUpdateDate;
	}

	/**
	 * 支払締め日設定
	 * @param paymentUpdateDate 支払締め日
	*/
	public void setPaymentUpdateDate(final Timestamp paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * 買掛締め日取得
	 * @return Timestamp 買掛締め日
	*/
	public Timestamp getPayableUpdateDate() {
		return this.payableUpdateDate;
	}

	/**
	 * 買掛締め日設定
	 * @param payableUpdateDate 買掛締め日
	*/
	public void setPayableUpdateDate(final Timestamp payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * データ転送日時取得
	 * @return Timestamp データ転送日時
	*/
	public Timestamp getTransmissionDate() {
		return this.transmissionDate;
	}

	/**
	 * データ転送日時設定
	 * @param transmissionDate データ転送日時
	*/
	public void setTransmissionDate(final Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	/**
	 * ラベル発行フラグ|0:未発行,1:発行取得
	 * @return BigDecimal ラベル発行フラグ|0:未発行,1:発行
	*/
	public BigDecimal getLabelFlag() {
		return this.labelFlag;
	}

	/**
	 * ラベル発行フラグ|0:未発行,1:発行設定
	 * @param labelFlag ラベル発行フラグ|0:未発行,1:発行
	*/
	public void setLabelFlag(final BigDecimal labelFlag) {
		this.labelFlag = labelFlag;
	}

	/**
	 * ラベル発行日取得
	 * @return Timestamp ラベル発行日
	*/
	public Timestamp getLabelDate() {
		return this.labelDate;
	}

	/**
	 * ラベル発行日設定
	 * @param labelDate ラベル発行日
	*/
	public void setLabelDate(final Timestamp labelDate) {
		this.labelDate = labelDate;
	}

	/**
	 * ラベル発行者取得
	 * @return String ラベル発行者
	*/
	public String getLabelTantoCd() {
		return this.labelTantoCd;
	}

	/**
	 * ラベル発行者設定
	 * @param labelTantoCd ラベル発行者
	*/
	public void setLabelTantoCd(final String labelTantoCd) {
		this.labelTantoCd = labelTantoCd;
	}

	/**
	 * 仮単価FLG|0:通常 1:仮単価取得
	 * @return BigDecimal 仮単価FLG|0:通常 1:仮単価
	*/
	public BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * 仮単価FLG|0:通常 1:仮単価設定
	 * @param tmpUnitpriceFlg 仮単価FLG|0:通常 1:仮単価
	*/
	public void setTmpUnitpriceFlg(final BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * 検査方法|0:無検査 1:抜取検査 2:全品検査取得
	 * @return BigDecimal 検査方法|0:無検査 1:抜取検査 2:全品検査
	*/
	public BigDecimal getInspectMethod() {
		return this.inspectMethod;
	}

	/**
	 * 検査方法|0:無検査 1:抜取検査 2:全品検査設定
	 * @param inspectMethod 検査方法|0:無検査 1:抜取検査 2:全品検査
	*/
	public void setInspectMethod(final BigDecimal inspectMethod) {
		this.inspectMethod = inspectMethod;
	}

	/**
	 * 引当明細FLG|0:なし
1:引当あり取得
	 * @return BigDecimal 引当明細FLG|0:なし
1:引当あり
	*/
	public BigDecimal getMortgageDetailFlg() {
		return this.mortgageDetailFlg;
	}

	/**
	 * 引当明細FLG|0:なし
1:引当あり設定
	 * @param mortgageDetailFlg 引当明細FLG|0:なし
1:引当あり
	*/
	public void setMortgageDetailFlg(final BigDecimal mortgageDetailFlg) {
		this.mortgageDetailFlg = mortgageDetailFlg;
	}

	/**
	 * 検収待ち数取得
	 * @return BigDecimal 検収待ち数
	*/
	public BigDecimal getInspreceiptWaitQuantity() {
		return this.inspreceiptWaitQuantity;
	}

	/**
	 * 検収待ち数設定
	 * @param inspreceiptWaitQuantity 検収待ち数
	*/
	public void setInspreceiptWaitQuantity(final BigDecimal inspreceiptWaitQuantity) {
		this.inspreceiptWaitQuantity = inspreceiptWaitQuantity;
	}

	/**
	 * 不良数取得
	 * @return BigDecimal 不良数
	*/
	public BigDecimal getBadQuantity() {
		return this.badQuantity;
	}

	/**
	 * 不良数設定
	 * @param badQuantity 不良数
	*/
	public void setBadQuantity(final BigDecimal badQuantity) {
		this.badQuantity = badQuantity;
	}

	/**
	 * 原価項目取得
	 * @return BigDecimal 原価項目
	*/
	public BigDecimal getCostEntry() {
		return this.costEntry;
	}

	/**
	 * 原価項目設定
	 * @param costEntry 原価項目
	*/
	public void setCostEntry(final BigDecimal costEntry) {
		this.costEntry = costEntry;
	}

	/**
	 * 内示・確定区分|0:内示 1:確定取得
	 * @return BigDecimal 内示・確定区分|0:内示 1:確定
	*/
	public BigDecimal getAdvPurNoticeDecideDivision() {
		return this.advPurNoticeDecideDivision;
	}

	/**
	 * 内示・確定区分|0:内示 1:確定設定
	 * @param advPurNoticeDecideDivision 内示・確定区分|0:内示 1:確定
	*/
	public void setAdvPurNoticeDecideDivision(final BigDecimal advPurNoticeDecideDivision) {
		this.advPurNoticeDecideDivision = advPurNoticeDecideDivision;
	}

	/**
	 * オーダー引当済数取得
	 * @return BigDecimal オーダー引当済数
	*/
	public BigDecimal getOrderMortgagedQuantity() {
		return this.orderMortgagedQuantity;
	}

	/**
	 * オーダー引当済数設定
	 * @param orderMortgagedQuantity オーダー引当済数
	*/
	public void setOrderMortgagedQuantity(final BigDecimal orderMortgagedQuantity) {
		this.orderMortgagedQuantity = orderMortgagedQuantity;
	}

	/**
	 * 累計入庫数換算量取得
	 * @return BigDecimal 累計入庫数換算量
	*/
	public BigDecimal getSumHousingConvertQuant() {
		return this.sumHousingConvertQuant;
	}

	/**
	 * 累計入庫数換算量設定
	 * @param sumHousingConvertQuant 累計入庫数換算量
	*/
	public void setSumHousingConvertQuant(final BigDecimal sumHousingConvertQuant) {
		this.sumHousingConvertQuant = sumHousingConvertQuant;
	}

	/**
	 * 検査待数換算量取得
	 * @return BigDecimal 検査待数換算量
	*/
	public BigDecimal getInspectWaitConvertQuantity() {
		return this.inspectWaitConvertQuantity;
	}

	/**
	 * 検査待数換算量設定
	 * @param inspectWaitConvertQuantity 検査待数換算量
	*/
	public void setInspectWaitConvertQuantity(final BigDecimal inspectWaitConvertQuantity) {
		this.inspectWaitConvertQuantity = inspectWaitConvertQuantity;
	}

	/**
	 * 検収抜取数取得
	 * @return BigDecimal 検収抜取数
	*/
	public BigDecimal getExtractionsQuantity() {
		return this.extractionsQuantity;
	}

	/**
	 * 検収抜取数設定
	 * @param extractionsQuantity 検収抜取数
	*/
	public void setExtractionsQuantity(final BigDecimal extractionsQuantity) {
		this.extractionsQuantity = extractionsQuantity;
	}

	/**
	 * 不良数取得
	 * @return BigDecimal 不良数
	*/
	public BigDecimal getDefectiveQuantity() {
		return this.defectiveQuantity;
	}

	/**
	 * 不良数設定
	 * @param defectiveQuantity 不良数
	*/
	public void setDefectiveQuantity(final BigDecimal defectiveQuantity) {
		this.defectiveQuantity = defectiveQuantity;
	}

	/**
	 * 検収担当者コード取得
	 * @return String 検収担当者コード
	*/
	public String getCheckTantoCd() {
		return this.checkTantoCd;
	}

	/**
	 * 検収担当者コード設定
	 * @param checkTantoCd 検収担当者コード
	*/
	public void setCheckTantoCd(final String checkTantoCd) {
		this.checkTantoCd = checkTantoCd;
	}

	/**
	 * 検査方法|0:無検査 1:抜取検査 2:全品検査取得
	 * @return BigDecimal 検査方法|0:無検査 1:抜取検査 2:全品検査
	*/
	public BigDecimal getLaboratoryMethod() {
		return this.laboratoryMethod;
	}

	/**
	 * 検査方法|0:無検査 1:抜取検査 2:全品検査設定
	 * @param laboratoryMethod 検査方法|0:無検査 1:抜取検査 2:全品検査
	*/
	public void setLaboratoryMethod(final BigDecimal laboratoryMethod) {
		this.laboratoryMethod = laboratoryMethod;
	}

	/**
	 * 支給区分|0:無償 1:有償取得
	 * @return BigDecimal 支給区分|0:無償 1:有償
	*/
	public BigDecimal getProvisionDivision() {
		return this.provisionDivision;
	}

	/**
	 * 支給区分|0:無償 1:有償設定
	 * @param provisionDivision 支給区分|0:無償 1:有償
	*/
	public void setProvisionDivision(final BigDecimal provisionDivision) {
		this.provisionDivision = provisionDivision;
	}

	/**
	 * 検収日付取得
	 * @return Timestamp 検収日付
	*/
	public Timestamp getCheckDate() {
		return this.checkDate;
	}

	/**
	 * 検収日付設定
	 * @param checkDate 検収日付
	*/
	public void setCheckDate(final Timestamp checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * 消費税率取得
	 * @return BigDecimal 消費税率
	*/
	public BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * 消費税率設定
	 * @param taxRatio 消費税率
	*/
	public void setTaxRatio(final BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * 検収数取得
	 * @return BigDecimal 検収数
	*/
	public BigDecimal getCheckQuantity() {
		return this.checkQuantity;
	}

	/**
	 * 検収数設定
	 * @param checkQuantity 検収数
	*/
	public void setCheckQuantity(final BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	/**
	 * 伝票発行済み区分|0..未発行 1..発行済取得
	 * @return BigDecimal 伝票発行済み区分|0..未発行 1..発行済
	*/
	public BigDecimal getSlipIssueDivision() {
		return this.slipIssueDivision;
	}

	/**
	 * 伝票発行済み区分|0..未発行 1..発行済設定
	 * @param slipIssueDivision 伝票発行済み区分|0..未発行 1..発行済
	*/
	public void setSlipIssueDivision(final BigDecimal slipIssueDivision) {
		this.slipIssueDivision = slipIssueDivision;
	}

	/**
	 * 伝票発行日取得
	 * @return Timestamp 伝票発行日
	*/
	public Timestamp getSlipIssueDate() {
		return this.slipIssueDate;
	}

	/**
	 * 伝票発行日設定
	 * @param slipIssueDate 伝票発行日
	*/
	public void setSlipIssueDate(final Timestamp slipIssueDate) {
		this.slipIssueDate = slipIssueDate;
	}

	/**
	 * 承認ステータス取得.
	 * @return String 承認ステータス
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * 承認ステータス設定.
	 * @param approvalStatus 承認ステータス
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 承認者取得.
	 * @return String 承認者
	 */
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * 承認者設定.
	 * @param approvedby 承認者
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * 承認日取得.
	 * @return Timestamp 承認日
	 */
	public Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * 承認日設定.
	 * @param approvaldate 承認日
	 */
	public void setApprovaldate(final Timestamp approvaldate) {
		this.approvaldate = approvaldate;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	*/
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	*/
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者取得
	 * @return String 登録者
	*/
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者設定
	 * @param inputorCd 登録者
	*/
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者取得
	 * @return String 更新者
	*/
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者設定
	 * @param updatorCd 更新者
	*/
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
