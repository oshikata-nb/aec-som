/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipsalesforreport;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 売上伝票出力画面 複数ページ制御クラス.
 * 
 * @author t1344224
 */
public class SlipSalesListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipSalesListConditionForReport() {
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

	/** 検索入力：売上伝票NO */
	private String srhSalesSlipNo;

	/** 検索入力：出荷予定日 */
	private String srhScheduledShippingDate;

	/** 検索入力：納入予定日 */
	private String srhDeliveryExpectedDate;

	/** 検索入力：注文番号 */
	private String srhCustomerOrderNo;

	/** 検索入力：運送店コード */
	private String srhCarryCd;

	/** 検索入力：ﾛｹｰｼｮﾝコード */
	private String srhLocationCd;

	/** 検索入力：納入先コード */
	private String srhDeliveryCd;

	/** 検索入力：売上伝票発行済 */
	private BigDecimal srhSlipPublishComp;

	// 2015/9/7 自動FAX対応
	/** 検索入力：伝票発行日FROM */
	private String srhSlipDateFrom;

	/** 検索入力：伝票発行日TO */
	private String srhSlipDateTo;

	/** 検索入力：売上伝票送信済 */
	private Boolean srhSlipSendComp;
	/** 検索入力：仕入伝票発行区分 */
	private java.math.BigDecimal srhFaxOutput;
	/* 売上FAX送信区分 */
	private BigDecimal srhSalesFaxOutput;
	/* 売上メール送信区分 */
	private BigDecimal srhSalesMailOutput;

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
	 * 運送店コードを取得します。
	 * @return srhCarryCd
	 */
	public String getSrhCarryCd() {
		return srhCarryCd;
	}

	/**
	 * 運送店コードを設定します。
	 * @param srhCarryCd 運送店コード
	 */
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * 注文番号を取得します。
	 * @return srhCustomerOrderNo
	 */
	public String getSrhCustomerOrderNo() {
		return srhCustomerOrderNo;
	}

	/**
	 * 注文番号を設定します。
	 * @param srhCustomerOrderNo 注文番号
	 */
	public void setSrhCustomerOrderNo(final String srhCustomerOrderNo) {
		this.srhCustomerOrderNo = AecTextUtils.likeFilter(srhCustomerOrderNo);
	}

	/**
	 * 納入先コードを取得します。
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return srhDeliveryCd;
	}

	/**
	 * 納入先コードを設定します。
	 * @param srhDeliveryCd 納入先コード
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}

	/**
	 * 納入予定日を取得します。
	 * @return srhDeliveryExpectedDate
	 */
	public String getSrhDeliveryExpectedDate() {
		return srhDeliveryExpectedDate;
	}

	/**
	 * 納入予定日を設定します。
	 * @param srhDeliveryExpectedDate 納入予定日
	 */
	public void setSrhDeliveryExpectedDate(final String srhDeliveryExpectedDate) {
		this.srhDeliveryExpectedDate = srhDeliveryExpectedDate;
	}

	/**
	 * ロケーションコードを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * ロケーションコードを設定します。
	 * @param srhLocationCd ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * 売上伝票NOを取得します。
	 * @return srhSalesSlipNo
	 */
	public String getSrhSalesSlipNo() {
		return srhSalesSlipNo;
	}

	/**
	 * 売上伝票NOを設定します。
	 * @param srhSalesSlipNo 売上伝票NO
	 */
	public void setSrhSalesSlipNo(final String srhSalesSlipNo) {
		this.srhSalesSlipNo = AecTextUtils.likeFilter(srhSalesSlipNo);
	}

	/**
	 * 出荷予定日を取得します。
	 * @return srhScheduledShippingDate
	 */
	public String getSrhScheduledShippingDate() {
		return srhScheduledShippingDate;
	}

	/**
	 * 出荷予定日を設定します。
	 * @param srhScheduledShippingDate 出荷予定日
	 */
	public void setSrhScheduledShippingDate(
			final String srhScheduledShippingDate) {
		this.srhScheduledShippingDate = srhScheduledShippingDate;
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
	public void setSrhSlipPublishComp(final BigDecimal srhSlipPublishComp) {
		this.srhSlipPublishComp = srhSlipPublishComp;
	}

	/**
	 * srhSlipDateFromを取得します。
	 * @return srhSlipDateFrom
	 */
	public String getSrhSlipDateFrom() {
		return srhSlipDateFrom;
	}

	/**
	 * srhSlipDateFromを設定します。
	 * @param srhSlipDateFrom srhSlipDateFrom
	 */
	public void setSrhSlipDateFrom(final String srhSlipDateFrom) {
		this.srhSlipDateFrom = srhSlipDateFrom;
	}

	/**
	 * srhSlipDateToを取得します。
	 * @return srhSlipDateTo
	 */
	public String getSrhSlipDateTo() {
		return srhSlipDateTo;
	}

	/**
	 * srhSlipDateToを設定します。
	 * @param srhSlipDateTo srhSlipDateTo
	 */
	public void setSrhSlipDateTo(final String srhSlipDateTo) {
		this.srhSlipDateTo = srhSlipDateTo;
	}

	/**
	 * srhSlipSendCompを取得します。
	 * @return srhSlipSendComp
	 */
	public Boolean getSrhSlipSendComp() {
		return srhSlipSendComp;
	}

	/**
	 * srhSlipSendCompを設定します。
	 * @param srhSlipSendComp srhSlipSendComp
	 */
	public void setSrhSlipSendComp(final Boolean srhSlipSendComp) {
		this.srhSlipSendComp = srhSlipSendComp;
	}

	/**
	 * srhFaxOutputを取得します。
	 * @return srhFaxOutput
	 */
	public java.math.BigDecimal getSrhFaxOutput() {
		return srhFaxOutput;
	}

	/**
	 * srhFaxOutputを設定します。
	 * @param srhFaxOutput srhFaxOutput
	 */
	public void setSrhFaxOutput(java.math.BigDecimal srhFaxOutput) {
		this.srhFaxOutput = srhFaxOutput;
	}

	/**
	 * srhSalesFaxOutputを取得します。
	 * @return srhSalesFaxOutput
	 */
	public BigDecimal getSrhSalesFaxOutput() {
		return srhSalesFaxOutput;
	}

	/**
	 * srhSalesFaxOutputを設定します。
	 * @param srhSalesFaxOutput srhSalesFaxOutput
	 */
	public void setSrhSalesFaxOutput(BigDecimal srhSalesFaxOutput) {
		this.srhSalesFaxOutput = srhSalesFaxOutput;
	}

	/**
	 * srhSalesMailOutputを取得します。
	 * @return srhSalesMailOutput
	 */
	public BigDecimal getSrhSalesMailOutput() {
		return srhSalesMailOutput;
	}

	/**
	 * srhSalesMailOutputを設定します。
	 * @param srhSalesMailOutput srhSalesMailOutput
	 */
	public void setSrhSalesMailOutput(BigDecimal srhSalesMailOutput) {
		this.srhSalesMailOutput = srhSalesMailOutput;
	}
}
