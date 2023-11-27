/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingapprovalforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 仕入承認画面複数ページ制御クラス.
 * 
 * @author t1344224
 */
public class BuyingApprovalListConditionForReport {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：発注番号 */
	private String srhBuySubcontractOrderNo;

	/** 検索入力：担当部署コード */
	private String srhChargeOrganizationCd;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：発注担当者コード */
	private String srhTantoCd;

	/** 検索入力：仕入先コード|VENDER_CD(SI) */
	private String srhVenderCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：オーダー区分 */
	private String srhOrderDivision;

	/** 検索入力：納入ロケーションコード */
	private String srhLocationCd;

	/** 検索入力：発注書NO */
	private String srhOrderSheetNo;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：仕入日付(From) */
	private String srhStockingDateFrom;

	/** 検索入力：仕入日付(To) */
	private String srhStockingDateTo;

	/** 検索入力：仕入番号 */
	private String srhSlipNo;

	/** 検索入力：受入日付(From) */
	private String srhAcceptDateFrom;

	/** 検索入力：受入日付(To) */
	private String srhAcceptDateTo;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：取消チェックボックス */
	private boolean srhCancelCheck;

	//
	// 検索入力.section
	//

	/**
	 * 検索入力.発注番号取得
	 * @return srhBuySubcontractOrderNo
	 */
	public String getSrhBuySubcontractOrderNo() {
		return this.srhBuySubcontractOrderNo;
	}

	/**
	 * 検索入力.発注番号設定
	 * @param srhBuySubcontractOrderNo 検索入力.発注番号
	 */
	public void setSrhBuySubcontractOrderNo(
			final String srhBuySubcontractOrderNo) {
		this.srhBuySubcontractOrderNo = AecTextUtils
				.likeFilter(srhBuySubcontractOrderNo);
	}

	/**
	 * 検索入力.担当部署コード取得
	 * @return srhChargeOrganizationCd
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署コード設定
	 * @param srhChargeOrganizationCd 検索入力.担当部署コード
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		this.srhChargeOrganizationCd = AecTextUtils
				.likeFilter(srhChargeOrganizationCd);
	}

	/**
	 * 検索入力.部署コード取得
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * 検索入力.部署コード設定
	 * @param srhOrganizationCd 検索入力.部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 検索入力.発注担当者コード取得
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力.発注担当者コード設定
	 * @param srhTantoCd 検索入力.発注担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 検索入力.仕入先コード取得
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.仕入先コード設定
	 * @param srhVenderCd 検索入力.仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.品目コード取得
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定
	 * @param srhItemCd 検索入力.品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.オーダー区分取得
	 * @return srhOrderDivision
	 */
	public String getSrhOrderDivision() {
		return this.srhOrderDivision;
	}

	/**
	 * 検索入力.オーダー区分設定
	 * @param srhOrderDivision 検索入力.オーダー区分
	 */
	public void setSrhOrderDivision(final String srhOrderDivision) {
		this.srhOrderDivision = srhOrderDivision;
	}

	/**
	 * 検索入力.納入ロケーションコード取得
	 * @return srhLocationCd
	 */
	public String getSrhLocationCd() {
		return this.srhLocationCd;
	}

	/**
	 * 検索入力.納入ロケーションコード設定
	 * @param srhLocationCd 検索入力.納入ロケーションコード
	 */
	public void setSrhLocationCd(final String srhLocationCd) {
		this.srhLocationCd = AecTextUtils.likeFilter(srhLocationCd);
	}

	/**
	 * 検索入力.発注書NO取得
	 * @return srhOrderSheetNo
	 */
	public String getSrhOrderSheetNo() {
		return this.srhOrderSheetNo;
	}

	/**
	 * 検索入力.発注書NO設定
	 * @param srhOrderSheetNo 検索入力.発注書NO
	 */
	public void setSrhOrderSheetNo(final String srhOrderSheetNo) {
		this.srhOrderSheetNo = AecTextUtils.likeFilter(srhOrderSheetNo);
	}

	/**
	 * 検索入力.仕入区分取得
	 * @return srhCategoryDivision
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力.仕入区分(取消)取得
	 * @return srhCancelCategoryDivision
	 */
	public String getSrhCancelCategoryDivision() {
		return "-" + this.srhCategoryDivision;
	}

	/**
	 * 検索入力.仕入区分設定
	 * @param srhCategoryDivision 検索入力.仕入区分
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 検索入力：仕入日付(From)取得
	 * @return String 仕入日付(From)
	 */
	public String getSrhStockingDateFrom() {
		return this.srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日付(From)設定.
	 * @param srhStockingDateFrom 仕入日付(From)
	 */
	public void setSrhStockingDateFrom(final String srhStockingDateFrom) {
		this.srhStockingDateFrom = srhStockingDateFrom;
	}

	/**
	 * 検索入力：仕入日付(To)取得.
	 * @return String 仕入日付(To)
	 */
	public String getSrhStockingDateTo() {
		return this.srhStockingDateTo;
	}

	/**
	 * 検索入力：仕入日付(To)設定.
	 * @param srhStockingDateTo 仕入日付(To)
	 */
	public void setSrhStockingDateTo(final String srhStockingDateTo) {
		this.srhStockingDateTo = srhStockingDateTo;
	}

	/**
	 * 検索入力.仕入番号取得
	 * @return srhSlipNo
	 */
	public String getSrhSlipNo() {
		return this.srhSlipNo;
	}

	/**
	 * 検索入力.仕入番号設定
	 * @param srhSlipNo 検索入力.仕入番号
	 */
	public void setSrhSlipNo(final String srhSlipNo) {
		this.srhSlipNo = AecTextUtils.likeFilter(srhSlipNo);
	}

	/**
	 * 検索入力：受入日付(From)取得.
	 * @return String 受入日付(From)
	 */
	public String getSrhAcceptDateFrom() {
		return this.srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付(From)設定.
	 * @param srhAcceptDateFrom 受入日付(From)
	 */
	public void setSrhAcceptDateFrom(final String srhAcceptDateFrom) {
		this.srhAcceptDateFrom = srhAcceptDateFrom;
	}

	/**
	 * 検索入力：受入日付(To)取得.
	 * @return String 受入日付(To)
	 */
	public String getSrhAcceptDateTo() {
		return this.srhAcceptDateTo;
	}

	/**
	 * 検索入力：受入日付(To)設定.
	 * @param srhAcceptDateTo 受入日付(To)
	 */
	public void setSrhAcceptDateTo(final String srhAcceptDateTo) {
		this.srhAcceptDateTo = srhAcceptDateTo;
	}

	/**
	 * 検索入力：他社コード１
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * 取消チェックボックス取得
	 * @return srhCancelCheck
	 */
	public boolean isSrhCancelCheck() {
		return srhCancelCheck;
	}

	/**
	 * 取消チェックボックス設定
	 * @param srhCancelCheck 取消チェックボックス
	 */
	public void setSrhCancelCheck(final boolean srhCancelCheck) {
		this.srhCancelCheck = srhCancelCheck;
	}

}
