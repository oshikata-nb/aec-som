/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.saleslistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 売上トランザクションメンテ 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class SalesListReportCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesListReportCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：売上番号 */
	private String srhSalesNo;

	/** 検索入力：売上区分（分類コード） */
	private String srhCategoryDivision;

	/** 検索入力：取消フラグ */
	private boolean srhCancelFlg;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：売上日付FROM */
	private String srhSalesDateFrom;

	/** 検索入力：売上日付TO */
	private String srhSalesDateTo;

	/** 検索入力：勘定年月 */
	private String srhAccountYears;
	
	/** 検索入力：勘定年月 */
	private String srhAccountYearsTo;

	/** 検索入力：仮単価FLG */
	private boolean srhTmpUnitpriceFlg;

	/** 検索入力：預り品フラグ */
	private boolean srhKeepFlag;

	/** 検索入力：月次更新済みフラグ */
	private boolean srhMonthlyUpdateFlag;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：他社コード1 */
	private String srhOtherCompanyCd1;

	/** 検索入力：納品先名称 */
	private String srhDeliveryCd;

	/** 検索入力：受注客先注文番号 */
	private String srhCustomerOrderNo;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.売上番号取得
	 * @return srhSalesNo
	 */
	public String getSrhSalesNo() {
		return this.srhSalesNo;
	}

	/**
	 * 検索入力.売上番号設定 *
	 * @param srhSalesNo organizationId
	 */
	public void setSrhSalesNo(final String srhSalesNo) {
		this.srhSalesNo = AecTextUtils.likeFilter(srhSalesNo);
	}

	/**
	 * 検索入力.売上区分（分類コード）取得
	 * @return srhCategoryDivision
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力.売上区分（分類コード）設定 *
	 * @param srhCategoryDivision organizationId
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 検索入力.取消フラグ取得
	 * @return srhCancelSalesNo
	 */
	public boolean getSrhCancelFlg() {
		return this.srhCancelFlg;
	}

	/**
	 * 検索入力.取消フラグ設定 *
	 * @param srhCancelFlg organizationId
	 */
	public void setSrhCancelFlg(final boolean srhCancelFlg) {
		this.srhCancelFlg = srhCancelFlg;
	}

	/**
	 * 検索入力.受注番号FROM取得
	 * @return srhOrderNoFrom
	 */
	public String getSrhOrderNoFrom() {
		return this.srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号FROM設定 *
	 * @param srhOrderNoFrom organizationId
	 */
	public void setSrhOrderNoFrom(final String srhOrderNoFrom) {
		this.srhOrderNoFrom = srhOrderNoFrom;
	}

	/**
	 * 検索入力.受注番号TO取得
	 * @return srhOrderNoTo
	 */
	public String getSrhOrderNoTo() {
		return this.srhOrderNoTo;
	}

	/**
	 * 検索入力.受注番号TO設定 *
	 * @param srhOrderNoTo organizationId
	 */
	public void setSrhOrderNoTo(final String srhOrderNoTo) {
		this.srhOrderNoTo = srhOrderNoTo;
	}

	/**
	 * 検索入力.売上日付FROM取得
	 * @return srhSalesDateFrom
	 */
	public String getSrhSalesDateFrom() {
		return this.srhSalesDateFrom;
	}

	/**
	 * 検索入力.売上日付FROM設定 *
	 * @param srhSalesDateFrom organizationId
	 */
	public void setSrhSalesDateFrom(final String srhSalesDateFrom) {
		this.srhSalesDateFrom = srhSalesDateFrom;
	}

	/**
	 * 検索入力.売上日付TO取得
	 * @return srhSalesDateTo
	 */
	public String getSrhSalesDateTo() {
		return this.srhSalesDateTo;
	}

	/**
	 * 検索入力.売上日付TO設定 *
	 * @param srhSalesDateTo organizationId
	 */
	public void setSrhSalesDateTo(final String srhSalesDateTo) {
		this.srhSalesDateTo = srhSalesDateTo;
	}

	/**
	 * 検索入力.勘定年月取得
	 * @return srhAccountYears
	 */
	public String getSrhAccountYears() {
		return this.srhAccountYears;
	}
	

	/**
	 * 検索入力.勘定年月設定 *
	 * @param srhAccountYears organizationId
	 */
	public void setSrhAccountYears(final String srhAccountYears) {
		this.srhAccountYears = srhAccountYears;
	}

	/**
	 * 検索入力.勘定年月取得0406
	 * @return srhAccountYearsTo
	 */
	public String getSrhAccountYearsTo() {
		return this.srhAccountYearsTo;
	}	/**
	 * 検索入力.勘定年月設定 *0406
	 * @param srhAccountYearsTo organizationId
	 */
	public void setSrhAccountYearsTo(final String srhAccountYearsTo) {
		this.srhAccountYearsTo = srhAccountYearsTo;
	}
	/**
	 * 検索入力.仮単価FLG取得
	 * @return srhTmpUnitpriceFlg
	 */
	public boolean getSrhTmpUnitpriceFlg() {
		return this.srhTmpUnitpriceFlg;
	}

	/**
	 * 検索入力.仮単価FLG設定 *
	 * @param srhTmpUnitpriceFlg organizationId
	 */
	public void setSrhTmpUnitpriceFlg(final boolean srhTmpUnitpriceFlg) {
		this.srhTmpUnitpriceFlg = srhTmpUnitpriceFlg;
	}

	/**
	 * 検索入力.預り品フラグ取得
	 * @return srhKeepFlag
	 */
	public boolean getSrhKeepFlag() {
		return this.srhKeepFlag;
	}

	/**
	 * 検索入力.預り品フラグ設定 *
	 * @param srhKeepFlag organizationId
	 */
	public void setSrhKeepFlag(final boolean srhKeepFlag) {
		this.srhKeepFlag = srhKeepFlag;
	}

	/**
	 * 検索入力：月次更新済みフラグ取得.
	 * @return boolean 月次更新済みフラグ
	 */
	public boolean getSrhMonthlyUpdateFlag() {
		return this.srhMonthlyUpdateFlag;
	}

	/**
	 * 検索入力：月次更新済みフラグ設定.
	 * @param srhMonthlyUpdateFlag 月次更新済みフラグ
	 */
	public void setSrhMonthlyUpdateFlag(final boolean srhMonthlyUpdateFlag) {
		this.srhMonthlyUpdateFlag = srhMonthlyUpdateFlag;
	}

	/**
	 * 検索入力.得意先コード取得
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.得意先コード設定 *
	 * @param srhVenderCd organizationId
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return srhOrganizationCd
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定 *
	 * @param srhChargeOrganizationCd organizationId
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = AecTextUtils
				.likeFilter(srhChargeOrganizationCd);
	}

	/**
	 * 検索入力.品目コード取得
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定 *
	 * @param srhItemCd organizationId
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.他社コード1取得
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード1設定 *
	 * @param srhOtherCompanyCd1 organizationId
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * srhCustomerOrderNoを取得します。
	 * @return srhCustomerOrderNo
	 */
	public String getSrhCustomerOrderNo() {
		return srhCustomerOrderNo;
	}

	/**
	 * srhCustomerOrderNoを設定します。
	 * @param srhCustomerOrderNo srhCustomerOrderNo
	 */
	public void setSrhCustomerOrderNo(final String srhCustomerOrderNo) {
		this.srhCustomerOrderNo = srhCustomerOrderNo;
	}

	/**
	 * srhDeliveryCdを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * srhDeliveryCdを設定します。
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}
}
