package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷帳票検索画面 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class SlipShippingListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipShippingListConditionForReport() {
	}

	//
	// 検索入力用.section
	//
	/** 検索入力：上位ロケーション */
	private String srhLocationCd;

	/** 検索入力：運送店 */
	private String srhCarryCd;

	/** 検索入力：出荷予定日FROM */
	private Timestamp srhScheduledShippingDateFrom;

	/** 検索入力：出荷予定日To */
	private Timestamp srhScheduledShippingDateTo;

	/** 検索入力：受注番号FROM */
	private String srhOrderNoFrom;

	/** 検索入力：受注番号TO */
	private String srhOrderNoTo;

	/** 検索入力：出荷番号 */
	private String srhShippingNo;

	/** 検索入力：ステータス */
	private String srhShippingStatus;

	/** 検索入力：出荷伝票発行済 */
	private BigDecimal srhSlipPublishComp;

	/** 検索入力：出荷指図書発行済 */
	private BigDecimal srhSlipShippingOrderComp;

	/** 検索入力：出荷予定表発行済 */
	private BigDecimal srhSlipShippingScheduleComp;

	/** 検索入力：荷札発行済 */
	private BigDecimal srhSlipShippingTagComp;

	/** 検索入力：出荷依頼書発行済 */
	private BigDecimal srhSlipShippingRequestComp;
	
	/** 検索入力：運賃表発行済 */
	private BigDecimal srhSlipShippingFareComp;
	
	/** 検索入力：納品伝票発行済 */
	private BigDecimal srhShippingSlipDeliveryComp;

	/** 検索入力：新荷札発行済 */
	private BigDecimal srhShippingSlipNewShippingTagComp;

	/** 検索入力：新郵政発行済 */
	private BigDecimal srhShippingSlipPostalComp;

	/** 検索入力：荷札種別 */
	private String srhLabelPublish;

	/** 検索入力：荷札伝票番号 */
	private String srhShippingSlipNo;

	/** 検索入力：出荷バーコード */
	private String srhCarryBarcode;

	//
	// 検索入力.section
	//

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
	public void setSrhCarryCd(final String srhCarryCd) {
		this.srhCarryCd = srhCarryCd;
	}

	/**
	 * srhLabelPublishを取得します。
	 * @return srhLabelPublish
	 */
	public String getSrhLabelPublish() {
		return srhLabelPublish;
	}

	/**
	 * srhLabelPublishを設定します。
	 * @param srhLabelPublish srhLabelPublish
	 */
	public void setSrhLabelPublish(final String srhLabelPublish) {
		this.srhLabelPublish = srhLabelPublish;
	}

	/**
	 * srhLocationCdを取得します。
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return srhLocationCd;
	}

	/**
	 * srhLocationCdを設定します。
	 * @param srhLocationCd srhLocationCd
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = srhLocationCd;
	}

	/**
	 * srhScheduledShippingDateFromを取得します。
	 * @return srhScheduledShippingDateFrom
	 */
	public Timestamp getSrhScheduledShippingDateFrom() {
		return srhScheduledShippingDateFrom;
	}

	/**
	 * srhScheduledShippingDateFromを設定します。
	 * @param srhScheduledShippingDateFrom srhScheduledShippingDateFrom
	 */
	public void setSrhScheduledShippingDateFrom(
			final Timestamp srhScheduledShippingDateFrom) {
		this.srhScheduledShippingDateFrom = srhScheduledShippingDateFrom;
	}

	/**
	 * srhScheduledShippingDateToを取得します。
	 * @return srhScheduledShippingDateTo
	 */
	public Timestamp getSrhScheduledShippingDateTo() {
		return srhScheduledShippingDateTo;
	}

	/**
	 * srhScheduledShippingDateToを設定します。
	 * @param srhScheduledShippingDateTo srhScheduledShippingDateTo
	 */
	public void setSrhScheduledShippingDateTo(
			final Timestamp srhScheduledShippingDateTo) {
		this.srhScheduledShippingDateTo = srhScheduledShippingDateTo;
	}

	/**
	 * srhShippingNoを取得します。
	 * @return srhShippingNo
	 */
	public String getSrhShippingNo() {
		return srhShippingNo;
	}

	/**
	 * srhShippingNoを設定します。
	 * @param srhShippingNo srhShippingNo
	 */
	public void setSrhShippingNo(final String srhShippingNo) {
		this.srhShippingNo = AecTextUtils.likeFilter(srhShippingNo);
	}

	/**
	 * srhShippingSlipNoを取得します。
	 * @return srhShippingSlipNo
	 */
	public String getSrhShippingSlipNo() {
		return srhShippingSlipNo;
	}

	/**
	 * srhShippingSlipNoを設定します。
	 * @param srhShippingSlipNo srhShippingSlipNo
	 */
	public void setSrhShippingSlipNo(final String srhShippingSlipNo) {
		this.srhShippingSlipNo = AecTextUtils.likeFilter(srhShippingSlipNo);
	}

	/**
	 * srhShippingStatusを取得します。
	 * @return srhShippingStatus
	 */
	public String getSrhShippingStatus() {
		return srhShippingStatus;
	}

	/**
	 * srhShippingStatusを設定します。
	 * @param srhShippingStatus srhShippingStatus
	 */
	public void setSrhShippingStatus(final String srhShippingStatus) {
		this.srhShippingStatus = srhShippingStatus;
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
	 * srhSlipShippingOrderCompを取得します。
	 * @return srhSlipShippingOrderComp
	 */
	public BigDecimal getSrhSlipShippingOrderComp() {
		return srhSlipShippingOrderComp;
	}

	/**
	 * srhSlipShippingOrderCompを設定します。
	 * @param srhSlipShippingOrderComp srhSlipShippingOrderComp
	 */
	public void setSrhSlipShippingOrderComp(
			final BigDecimal srhSlipShippingOrderComp) {
		this.srhSlipShippingOrderComp = srhSlipShippingOrderComp;
	}

	/**
	 * srhSlipShippingRequestCompを取得します。
	 * @return srhSlipShippingRequestComp
	 */
	public BigDecimal getSrhSlipShippingRequestComp() {
		return srhSlipShippingRequestComp;
	}

	/**
	 * srhSlipShippingRequestCompを設定します。
	 * @param srhSlipShippingRequestComp srhSlipShippingRequestComp
	 */
	public void setSrhSlipShippingRequestComp(
			final BigDecimal srhSlipShippingRequestComp) {
		this.srhSlipShippingRequestComp = srhSlipShippingRequestComp;
	}
	
	/**
	 * srhSlipShippingRequestCompを取得します。
	 * @return srhSlipShippingFareComp
	 */
	public BigDecimal getSrhSlipShippingFareComp() {
		return srhSlipShippingFareComp;
	}

	/**
	 * srhSlipShippingFareCompを設定します。
	 * @param srhSlipShippingFareComp srhSlipShippingFareComp
	 */
	public void setSrhSlipShippingFareComp(
			final BigDecimal srhSlipShippingFareComp) {
		this.srhSlipShippingFareComp = srhSlipShippingFareComp;
	}

	/**
	 * srhSlipShippingScheduleCompを取得します。
	 * @return srhSlipShippingScheduleComp
	 */
	public BigDecimal getSrhSlipShippingScheduleComp() {
		return srhSlipShippingScheduleComp;
	}

	/**
	 * srhSlipShippingScheduleCompを設定します。
	 * @param srhSlipShippingScheduleComp srhSlipShippingScheduleComp
	 */
	public void setSrhSlipShippingScheduleComp(
			final BigDecimal srhSlipShippingScheduleComp) {
		this.srhSlipShippingScheduleComp = srhSlipShippingScheduleComp;
	}

	/**
	 * srhSlipShippingTagCompを取得します。
	 * @return srhSlipShippingTagComp
	 */
	public BigDecimal getSrhSlipShippingTagComp() {
		return srhSlipShippingTagComp;
	}

	/**
	 * srhSlipShippingTagCompを設定します。
	 * @param srhSlipShippingTagComp srhSlipShippingTagComp
	 */
	public void setSrhSlipShippingTagComp(
			final BigDecimal srhSlipShippingTagComp) {
		this.srhSlipShippingTagComp = srhSlipShippingTagComp;
	}

	/**
	 * srhShippingSlipDeliveryCompを取得します。
	 * @return srhShippingSlipDeliveryComp
	 */
	public BigDecimal getSrhShippingSlipDeliveryComp() {
		return srhShippingSlipDeliveryComp;
	}

	/**
	 * srhShippingSlipDeliveryCompを設定します。
	 * @param srhShippingSlipDeliveryComp srhShippingSlipDeliveryComp
	 */
	public void setSrhShippingSlipDeliveryComp(
			BigDecimal srhShippingSlipDeliveryComp) {
		this.srhShippingSlipDeliveryComp = srhShippingSlipDeliveryComp;
	}

	/**
	 * srhShippingSlipNewShippingTagCompを取得します。
	 * @return srhShippingSlipNewShippingTagComp
	 */
	public BigDecimal getSrhShippingSlipNewShippingTagComp() {
		return srhShippingSlipNewShippingTagComp;
	}

	/**
	 * srhShippingSlipNewShippingTagCompを設定します。
	 * @param srhShippingSlipNewShippingTagComp srhShippingSlipNewShippingTagComp
	 */
	public void setSrhShippingSlipNewShippingTagComp(
			BigDecimal srhShippingSlipNewShippingTagComp) {
		this.srhShippingSlipNewShippingTagComp = srhShippingSlipNewShippingTagComp;
	}

	/**
	 * srhShippingSlipPostalCompを取得します。
	 * @return srhShippingSlipPostalComp
	 */
	public BigDecimal getSrhShippingSlipPostalComp() {
		return srhShippingSlipPostalComp;
	}

	/**
	 * srhShippingSlipPostalCompを設定します。
	 * @param srhShippingSlipPostalComp srhShippingSlipPostalComp
	 */
	public void setSrhShippingSlipPostalComp(BigDecimal srhShippingSlipPostalComp) {
		this.srhShippingSlipPostalComp = srhShippingSlipPostalComp;
	}

	/**
	 * srhCarryBarcodeを取得します。
	 * @return srhCarryBarcode
	 */
	public String getSrhCarryBarcode() {
		return srhCarryBarcode;
	}

	/**
	 * srhCarryBarcodeを設定します。
	 * @param srhCarryBarcode srhCarryBarcode
	 */
	public void setSrhCarryBarcode(String srhCarryBarcode) {
		this.srhCarryBarcode = srhCarryBarcode;
	}
	
}
