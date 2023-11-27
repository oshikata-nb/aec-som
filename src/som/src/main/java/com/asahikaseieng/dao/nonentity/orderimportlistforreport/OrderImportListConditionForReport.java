/*
 * Created on 2020/11/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OrderListPagerConditionクラス.
 * @author t1344224
 */
public class OrderImportListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportListConditionForReport() {
	}

	//
	// 受注取込検索入力用
	//	/* 検索条件.客先注文番号 */
	private String srhCtmOrderNo;
	/* 検索条件.得意先グループコード */
	private String srhVenderGroupCd;
	/* 検索条件.取込日From*/
	private String srhImportDateFrom;
	/* 検索条件.取込日To*/
	private String srhImportDateTo;
	/* 検索条件.受注番号From */
	private String srhOrderNoFrom;
	/* 検索条件.受注番号To */
	private String srhOrderNoTo;
	/* 検索条件.受注区分 */
	private BigDecimal srhOrderDivision;
	/* 検索条件.受注ステータス */
	private BigDecimal srhOrderStatus;
	/* 検索条件.受注日From */
	private String srhOrderDateFrom;
	/* 検索条件.受注日To */
	private String srhOrderDateTo;
	/* 検索条件.納入先コード */
	private String srhDeliveryCd;
	/* 検索条件.納入先名称 */
	private String srhDeliveryName;
	/* 検索条件.納入先住所 */
	private String srhAddress;
	/* 検索条件.客先返信 */
	private BigDecimal srhSlipPublishComp;
	/* 検索条件.出荷予定日From */
	private String srhScheduledShippingDateFrom;
	/* 検索条件.出荷予定日To */
	private String srhScheduledShippingDateTo;
	/* 検索条件.得意先コード */
	private String srhVenderCd;
	/* 検索条件.得意先名称 */
	private String srhVenderName;
	/* 検索条件.担当部署コード */
	private String srhOrganizationCd;
	/* 検索条件.担当部署名称 */
	private String srhOrganizationName;
	/* 検索条件.仮単価 */
	private String srhTmpUnitpriceFlg;
	/* 検索条件.営業担当者コード */
	private String srhSalesTantoCd;
	/* 検索条件.営業担当者名称 */
	private String srhSalesTantoName;
	/* 検索条件.入力担当者コード */
	private String srhInputTantoCd;
	/* 検索条件.入力担当者名称 */
	private String srhInputTantoName;
	/* 検索条件.出荷BC */
	private String srhCarryBc;
	/* 検索条件品目コード */
	private String srhItemCd;
	/* 検索条件品目名 */
	private String srhItemName;
	/* 検索条件他社コード1 */
	private String srhOtherCompanyCd1;
	/* 検索条件.納入先電話番号 */
	private String srhDeliveryTelNo;
	/* 検索条件.希望納期From*/
	private String srhSuggestedDeliverlimitFrom;
	/* 検索条件.希望納期To*/
	private String srhSuggestedDeliverlimitTo;
	/* 検索条件.運送会社コード */
	private String srhCarryCd;
	/* 検索条件.エラー状態*/
	private String srhErrorStatus;
	/* 検索条件.データ作成区分*/
	private BigDecimal srhInputDivision;
	/* 検索条件.納品予定日From*/
	private String srhDeliveryExpectedDateFrom;
	/* 検索条件.納品予定日To*/
	private String srhDeliveryExpectedDateTo;
	/* 検索条件.取込番号From*/
	private String srhTempNoFrom;
	/* 検索条件.取込番号To*/
	private String srhTempNoTo;
	/* 検索条件.受注グループ番号From*/
	private String srhFrstOrderNoFrom;
	/* 検索条件.受注グループ番号To*/
	private String srhFrstOrderNoTo;
	/* 検索条件.入力チェック */
	private String srhOrderInputCheck;
	/* 検索条件.納期連絡 */
	private String srhDelDateSend;
	/* 検索条件.削除、キャンセル */
	private String srhDeleteCancel;
	// 得意先リスト
	private ArrayList<String> srhVenderList;

	/**
	 * srhCtmOrderNoを取得します。
	 * @return srhCtmOrderNo
	 */
	public String getSrhCtmOrderNo() {
		return srhCtmOrderNo;
	}
	/**
	 * srhCtmOrderNoを設定します。
	 * @param srhCtmOrderNo srhCtmOrderNo
	 */
	public void setSrhCtmOrderNo(String srhCtmOrderNo) {
		this.srhCtmOrderNo = srhCtmOrderNo;
	}
	/**
	 * srhVenderGroupCdを取得します。
	 * @return srhVenderGroupCd
	 */
	public String getSrhVenderGroupCd() {
		return srhVenderGroupCd;
	}
	/**
	 * srhVenderGroupCdを設定します。
	 * @param srhVenderGroupCd srhVenderGroupCd
	 */
	public void setSrhVenderGroupCd(String srhVenderGroupCd) {
		this.srhVenderGroupCd = srhVenderGroupCd;
	}
	/**
	 * srhImportDateFromを取得します。
	 * @return srhImportDateFrom
	 */
	public String getSrhImportDateFrom() {
		return srhImportDateFrom;
	}
	/**
	 * srhImportDateFromを設定します。
	 * @param srhImportDateFrom srhImportDateFrom
	 */
	public void setSrhImportDateFrom(String srhImportDateFrom) {
		this.srhImportDateFrom = srhImportDateFrom;
	}
	/**
	 * srhImportDateToを取得します。
	 * @return srhImportDateTo
	 */
	public String getSrhImportDateTo() {
		return srhImportDateTo;
	}
	/**
	 * srhImportDateToを設定します。
	 * @param srhImportDateTo srhImportDateTo
	 */
	public void setSrhImportDateTo(String srhImportDateTo) {
		this.srhImportDateTo = srhImportDateTo;
	}
	/**
	 * srhOrderNoFromを取得します。
	 * @return srhOrderNoFrom
	 */
	public String getSrhOrderNoFrom() {
		return srhOrderNoFrom;
	}
	/**
	 * srhOrderNoFromを設定します。
	 * @param srhOrderNoFrom srhOrderNoFrom
	 */
	public void setSrhOrderNoFrom(String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}
	/**
	 * srhOrderNoToを取得します。
	 * @return srhOrderNoTo
	 */
	public String getSrhOrderNoTo() {
		return srhOrderNoTo;
	}
	/**
	 * srhOrderNoToを設定します。
	 * @param srhOrderNoTo srhOrderNoTo
	 */
	public void setSrhOrderNoTo(String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}
	/**
	 * srhOrderDivisionを取得します。
	 * @return srhOrderDivision
	 */
	public BigDecimal getSrhOrderDivision() {
		return srhOrderDivision;
	}
	/**
	 * srhOrderDivisionを設定します。
	 * @param srhOrderDivision srhOrderDivision
	 */
	public void setSrhOrderDivision(BigDecimal srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}
	/**
	 * srhOrderStatusを取得します。
	 * @return srhOrderStatus
	 */
	public BigDecimal getSrhOrderStatus() {
		return srhOrderStatus;
	}
	/**
	 * srhOrderStatusを設定します。
	 * @param srhOrderStatus srhOrderStatus
	 */
	public void setSrhOrderStatus(BigDecimal srhOrderStatus) {
		this.srhOrderStatus = srhOrderStatus;
	}
	/**
	 * srhOrderDateFromを取得します。
	 * @return srhOrderDateFrom
	 */
	public String getSrhOrderDateFrom() {
		return srhOrderDateFrom;
	}
	/**
	 * srhOrderDateFromを設定します。
	 * @param srhOrderDateFrom srhOrderDateFrom
	 */
	public void setSrhOrderDateFrom(String srhOrderDateFrom) {
		this.srhOrderDateFrom = srhOrderDateFrom;
	}
	/**
	 * srhOrderDateToを取得します。
	 * @return srhOrderDateTo
	 */
	public String getSrhOrderDateTo() {
		return srhOrderDateTo;
	}
	/**
	 * srhOrderDateToを設定します。
	 * @param srhOrderDateTo srhOrderDateTo
	 */
	public void setSrhOrderDateTo(String srhOrderDateTo) {
		this.srhOrderDateTo = srhOrderDateTo;
	}
	/**
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return AecTextUtils.likeFilter(srhDeliveryCd);
	}
	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(String srhDeliveryCd) {
		this.srhDeliveryCd =  srhDeliveryCd;
	}
	/**
	 * srhDeliveryNameを取得します。
	 * @return srhDeliveryName
	 */
	public String getSrhDeliveryName() {
		return srhDeliveryName;
	}
	/**
	 * srhDeliveryNameを設定します。
	 * @param srhDeliveryName srhDeliveryName
	 */
	public void setSrhDeliveryName(String srhDeliveryName) {
		this.srhDeliveryName = srhDeliveryName;
	}
	/**
	 * srhAddressを取得します。
	 * @return srhAddress
	 */
	public String getSrhAddress() {
		return srhAddress;
	}
	/**
	 * srhAddressを設定します。
	 * @param srhAddress srhAddress
	 */
	public void setSrhAddress(String srhAddress) {
		this.srhAddress = srhAddress;
	}
	/**
	 * srhSlipPublishCompを取得します。
	 * @return srhSlipPublishComp
	 */
	public BigDecimal getSrhSlipPublishComp() {
		return srhSlipPublishComp;
	}
	/**
	 * srhSlipPublishCompを設定します。
	 * @param srhSlipPublishComp srhSlipPublishComp
	 */
	public void setSrhSlipPublishComp(BigDecimal srhSlipPublishComp) {
		this.srhSlipPublishComp = srhSlipPublishComp;
	}
	/**
	 * srhScheduledShippingDateFromを取得します。
	 * @return srhScheduledShippingDateFrom
	 */
	public String getSrhScheduledShippingDateFrom() {
		return srhScheduledShippingDateFrom;
	}
	/**
	 * srhScheduledShippingDateFromを設定します。
	 * @param srhScheduledShippingDateFrom srhScheduledShippingDateFrom
	 */
	public void setSrhScheduledShippingDateFrom(String srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}
	/**
	 * srhScheduledShippingDateToを取得します。
	 * @return srhScheduledShippingDateTo
	 */
	public String getSrhScheduledShippingDateTo() {
		return srhScheduledShippingDateTo;
	}
	/**
	 * srhScheduledShippingDateToを設定します。
	 * @param srhScheduledShippingDateTo srhScheduledShippingDateTo
	 */
	public void setSrhScheduledShippingDateTo(String srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}
	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return AecTextUtils.likeFilter(srhVenderCd);
	}
	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(String srhVenderCd) {
		this.srhVenderCd =  srhVenderCd;
	}
	/**
	 * srhVenderNameを取得します。
	 * @return srhVenderName
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}
	/**
	 * srhVenderNameを設定します。
	 * @param srhVenderName srhVenderName
	 */
	public void setSrhVenderName(String srhVenderName) {
		this.srhVenderName = srhVenderName;
	}
	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}
	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(String srhOrganizationCd) {
		this.srhOrganizationCd = srhOrganizationCd;
	}
	/**
	 * srhOrganizationNameを取得します。
	 * @return srhOrganizationName
	 */
	public String getSrhOrganizationName() {
		return srhOrganizationName;
	}
	/**
	 * srhOrganizationNameを設定します。
	 * @param srhOrganizationName srhOrganizationName
	 */
	public void setSrhOrganizationName(String srhOrganizationName) {
		this.srhOrganizationName = srhOrganizationName;
	}
	/**
	 * srhTmpUnitpriceFlgを取得します。
	 * @return srhTmpUnitpriceFlg
	 */
	public String getSrhTmpUnitpriceFlg() {
		return srhTmpUnitpriceFlg;
	}
	/**
	 * srhTmpUnitpriceFlgを設定します。
	 * @param srhTmpUnitpriceFlg srhTmpUnitpriceFlg
	 */
	public void setSrhTmpUnitpriceFlg(String srhTmpUnitpriceFlg) {
		this.srhTmpUnitpriceFlg = srhTmpUnitpriceFlg;
	}
	/**
	 * srhSalesTantoCdを取得します。
	 * @return srhSalesTantoCd
	 */
	public String getSrhSalesTantoCd() {
		return srhSalesTantoCd;
	}
	/**
	 * srhSalesTantoCdを設定します。
	 * @param srhSalesTantoCd srhSalesTantoCd
	 */
	public void setSrhSalesTantoCd(String srhSalesTantoCd) {
		this.srhSalesTantoCd = srhSalesTantoCd;
	}
	/**
	 * srhSalesTantoNameを取得します。
	 * @return srhSalesTantoName
	 */
	public String getSrhSalesTantoName() {
		return srhSalesTantoName;
	}
	/**
	 * srhSalesTantoNameを設定します。
	 * @param srhSalesTantoName srhSalesTantoName
	 */
	public void setSrhSalesTantoName(String srhSalesTantoName) {
		this.srhSalesTantoName = srhSalesTantoName;
	}
	/**
	 * srhInputTantoCdを取得します。
	 * @return srhInputTantoCd
	 */
	public String getSrhInputTantoCd() {
		return srhInputTantoCd;
	}
	/**
	 * srhInputTantoCdを設定します。
	 * @param srhInputTantoCd srhInputTantoCd
	 */
	public void setSrhInputTantoCd(String srhInputTantoCd) {
		this.srhInputTantoCd = srhInputTantoCd;
	}
	/**
	 * srhInputTantoNameを取得します。
	 * @return srhInputTantoName
	 */
	public String getSrhInputTantoName() {
		return srhInputTantoName;
	}
	/**
	 * srhInputTantoNameを設定します。
	 * @param srhInputTantoName srhInputTantoName
	 */
	public void setSrhInputTantoName(String srhInputTantoName) {
		this.srhInputTantoName = srhInputTantoName;
	}
	/**
	 * srhCarryBcを取得します。
	 * @return srhCarryBc
	 */
	public String getSrhCarryBc() {
		return srhCarryBc;
	}
	/**
	 * srhCarryBcを設定します。
	 * @param srhCarryBc srhCarryBc
	 */
	public void setSrhCarryBc(String srhCarryBc) {
		this.srhCarryBc = srhCarryBc;
	}
	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}
	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}
	/**
	 * srhItemNameを取得します。
	 * @return srhItemName
	 */
	public String getSrhItemName() {
		return srhItemName;
	}
	/**
	 * srhItemNameを設定します。
	 * @param srhItemName srhItemName
	 */
	public void setSrhItemName(String srhItemName) {
		this.srhItemName = srhItemName;
	}
	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}
	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}
	/**
	 * srhDeliveryTelNoを取得します。
	 * @return srhDeliveryTelNo
	 */
	public String getSrhDeliveryTelNo() {
		return srhDeliveryTelNo;
	}
	/**
	 * srhDeliveryTelNoを設定します。
	 * @param srhDeliveryTelNo srhDeliveryTelNo
	 */
	public void setSrhDeliveryTelNo(String srhDeliveryTelNo) {
		this.srhDeliveryTelNo = srhDeliveryTelNo;
	}
	/**
	 * srhSuggestedDeliverlimitFromを取得します。
	 * @return srhSuggestedDeliverlimitFrom
	 */
	public String getSrhSuggestedDeliverlimitFrom() {
		return srhSuggestedDeliverlimitFrom;
	}
	/**
	 * srhSuggestedDeliverlimitFromを設定します。
	 * @param srhSuggestedDeliverlimitFrom srhSuggestedDeliverlimitFrom
	 */
	public void setSrhSuggestedDeliverlimitFrom(String srhSuggestedDeliverlimitFrom) {
		this.srhSuggestedDeliverlimitFrom = srhSuggestedDeliverlimitFrom;
	}
	/**
	 * srhSuggestedDeliverlimitToを取得します。
	 * @return srhSuggestedDeliverlimitTo
	 */
	public String getSrhSuggestedDeliverlimitTo() {
		return srhSuggestedDeliverlimitTo;
	}
	/**
	 * srhSuggestedDeliverlimitToを設定します。
	 * @param srhSuggestedDeliverlimitTo srhSuggestedDeliverlimitTo
	 */
	public void setSrhSuggestedDeliverlimitTo(String srhSuggestedDeliverlimitTo) {
		this.srhSuggestedDeliverlimitTo = srhSuggestedDeliverlimitTo;
	}
	/**
	 * srhCarryCdを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}
	/**
	 * srhCarryCdを設定します。
	 * @param srhCarryCd srhCarryCd
	 */
	public void setSrhCarryCd(String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}
	/**
	 * srhErrorStatusを取得します。
	 * @return srhErrorStatus
	 */
	public String getSrhErrorStatus() {
		return srhErrorStatus;
	}
	/**
	 * srhErrorStatusを設定します。
	 * @param srhErrorStatus srhErrorStatus
	 */
	public void setSrhErrorStatus(String srhErrorStatus) {
		this.srhErrorStatus = srhErrorStatus;
	}
	/**
	 * srhInputDivisionを取得します。
	 * @return srhInputDivision
	 */
	public BigDecimal getSrhInputDivision() {
		return srhInputDivision;
	}
	/**
	 * srhInputDivisionを設定します。
	 * @param srhInputDivision srhInputDivision
	 */
	public void setSrhInputDivision(BigDecimal srhInputDivision) {
		this.srhInputDivision = srhInputDivision;
	}
	/**
	 * srhDeliveryExpectedDateFromを取得します。
	 * @return srhDeliveryExpectedDateFrom
	 */
	public String getSrhDeliveryExpectedDateFrom() {
		return srhDeliveryExpectedDateFrom;
	}
	/**
	 * srhDeliveryExpectedDateFromを設定します。
	 * @param srhDeliveryExpectedDateFrom srhDeliveryExpectedDateFrom
	 */
	public void setSrhDeliveryExpectedDateFrom(String srhDeliveryExpectedDateFrom) {
		this.srhDeliveryExpectedDateFrom = srhDeliveryExpectedDateFrom;
	}
	/**
	 * srhDeliveryExpectedDateToを取得します。
	 * @return srhDeliveryExpectedDateTo
	 */
	public String getSrhDeliveryExpectedDateTo() {
		return srhDeliveryExpectedDateTo;
	}
	/**
	 * srhDeliveryExpectedDateToを設定します。
	 * @param srhDeliveryExpectedDateTo srhDeliveryExpectedDateTo
	 */
	public void setSrhDeliveryExpectedDateTo(String srhDeliveryExpectedDateTo) {
		this.srhDeliveryExpectedDateTo = srhDeliveryExpectedDateTo;
	}
	/**
	 * srhTempNoFromを取得します。
	 * @return srhTempNoFrom
	 */
	public String getSrhTempNoFrom() {
		return srhTempNoFrom;
	}
	/**
	 * srhTempNoFromを設定します。
	 * @param srhTempNoFrom srhTempNoFrom
	 */
	public void setSrhTempNoFrom(String srhTempNoFrom) {
		this.srhTempNoFrom = srhTempNoFrom;
	}
	/**
	 * srhTempNoToを取得します。
	 * @return srhTempNoTo
	 */
	public String getSrhTempNoTo() {
		return srhTempNoTo;
	}
	/**
	 * srhTempNoToを設定します。
	 * @param srhTempNoTo srhTempNoTo
	 */
	public void setSrhTempNoTo(String srhTempNoTo) {
		this.srhTempNoTo = srhTempNoTo;
	}
	/**
	 * srhFrstOrderNoFromを取得します。
	 * @return srhFrstOrderNoFrom
	 */
	public String getSrhFrstOrderNoFrom() {
		return srhFrstOrderNoFrom;
	}
	/**
	 * srhFrstOrderNoFromを設定します。
	 * @param srhFrstOrderNoFrom srhFrstOrderNoFrom
	 */
	public void setSrhFrstOrderNoFrom(String srhFrstOrderNoFrom) {
		this.srhFrstOrderNoFrom = srhFrstOrderNoFrom;
	}
	/**
	 * srhFrstOrderNoToを取得します。
	 * @return srhFrstOrderNoTo
	 */
	public String getSrhFrstOrderNoTo() {
		return srhFrstOrderNoTo;
	}
	/**
	 * srhFrstOrderNoToを設定します。
	 * @param srhFrstOrderNoTo srhFrstOrderNoTo
	 */
	public void setSrhFrstOrderNoTo(String srhFrstOrderNoTo) {
		this.srhFrstOrderNoTo = srhFrstOrderNoTo;
	}
	/**
	 * srhOrderInputCheckを取得します。
	 * @return srhOrderInputCheck
	 */
	public String getSrhOrderInputCheck() {
		return srhOrderInputCheck;
	}
	/**
	 * srhOrderInputCheckを設定します。
	 * @param srhOrderInputCheck srhOrderInputCheck
	 */
	public void setSrhOrderInputCheck(String srhOrderInputCheck) {
		this.srhOrderInputCheck = srhOrderInputCheck;
	}
	/**
	 * srhDelDateSendを取得します。
	 * @return srhDelDateSend
	 */
	public String getSrhDelDateSend() {
		return srhDelDateSend;
	}
	/**
	 * srhDelDateSendを設定します。
	 * @param srhDelDateSend srhDelDateSend
	 */
	public void setSrhDelDateSend(String srhDelDateSend) {
		this.srhDelDateSend = srhDelDateSend;
	}
	/**
	 * srhDeleteCancelを取得します。
	 * @return srhDeleteCancel
	 */
	public String getSrhDeleteCancel() {
		return srhDeleteCancel;
	}
	/**
	 * srhDeleteCancelを設定します。
	 * @param srhDeleteCancel srhDeleteCancel
	 */
	public void setSrhDeleteCancel(String srhDeleteCancel) {
		this.srhDeleteCancel = srhDeleteCancel;
	}
	/**
	 * srhVenderListを取得します。
	 * @return srhVenderList
	 */
	public ArrayList<String> getSrhVenderList() {
		return srhVenderList;
	}
	/**
	 * srhVenderListを設定します。
	 * @param srhVenderList srhVenderList
	 */
	public void setSrhVenderList(ArrayList<String> srhVenderList) {
		this.srhVenderList = srhVenderList;
	}	

}
