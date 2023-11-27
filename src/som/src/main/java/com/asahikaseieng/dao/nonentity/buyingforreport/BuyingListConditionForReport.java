/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 仕入一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class BuyingListConditionForReport {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：担当部署 */
	private String srhChargeOrganizationCd;

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：仕入日(from) */
	private String srhStockingDateFrom;

	/** 検索入力：仕入日(to) */
	private String srhStockingDateTo;

	/** 検索入力：仕入番号 */
	private String srhSlipNo;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：取消チェックボックス */
	private boolean srhCancelCheck;

	/** 検索入力：仕入ステータス */
	private String srhStockingStatus;

	/** 検索入力：月次更新済チェックボックス */
	private boolean srhMonthlyCheck;

	/** 仕入れ伝票発行済チェックボックス */
	private boolean srhSlipIssueDivision;

	//
	// 検索入力
	//

	/**
	 * 検索入力.担当部署取得
	 * @return srhChargeOrganizationCd
	 */
	public String getSrhChargeOrganizationCd() {
		return this.srhChargeOrganizationCd;
	}

	/**
	 * 検索入力.担当部署設定 *
	 * @param srhChargeOrganizationCd 担当部署
	 */
	public void setSrhChargeOrganizationCd(final String srhChargeOrganizationCd) {
		// like検索用に%を付けてコピーする
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
	 * 検索入力.部署コード設定 *
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		// like検索用に%を付けてコピーする
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 検索入力.担当者コード取得
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return this.srhTantoCd;
	}

	/**
	 * 検索入力.担当者コード設定 *
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		// like検索用に%を付けてコピーする
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
	 * 検索入力.仕入先コード設定 *
	 * @param srhVenderCd 仕入先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		// like検索用に%を付けてコピーする
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
	 * 検索入力.品目コード設定 *
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		// like検索用に%を付けてコピーする
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * 検索入力.分類コード取得
	 * @return srhCategoryDivision
	 */
	public String getSrhCategoryDivision() {
		return this.srhCategoryDivision;
	}

	/**
	 * 検索入力.分類コード(取消)取得
	 * @return srhCancelCategoryDivision
	 */
	public String getSrhCancelCategoryDivision() {
		return "-" + this.srhCategoryDivision;
	}

	/**
	 * 検索入力.分類コード設定 *
	 * @param srhCategoryDivision 仕入区分
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = srhCategoryDivision;
	}

	/**
	 * 検索入力.仕入日(from)取得
	 * @return srhStockingDateFrom
	 */
	public String getSrhStockingDateFrom() {
		return this.srhStockingDateFrom;
	}

	/**
	 * 検索入力.仕入日(from)設定 *
	 * @param srhStockingDateFrom 仕入日(from)
	 */
	public void setSrhStockingDateFrom(final String srhStockingDateFrom) {
		this.srhStockingDateFrom = srhStockingDateFrom;
	}

	/**
	 * 検索入力.仕入日(to)取得
	 * @return srhStockingDateTo
	 */
	public String getSrhStockingDateTo() {
		return this.srhStockingDateTo;
	}

	/**
	 * 検索入力.仕入日(to)設定 *
	 * @param srhStockingDateTo 仕入日(to)
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
	 * 検索入力.仕入番号設定 *
	 * @param srhSlipNo 仕入番号
	 */
	public void setSrhSlipNo(final String srhSlipNo) {
		// like検索用に%を付けてコピーする
		this.srhSlipNo = AecTextUtils.likeFilter(srhSlipNo);
	}

	/**
	 * 検索入力.他社コード1 *
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力.他社コード1 *
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		// like検索用に%を付けてコピーする
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

	/**
	 * 検索入力.仕入ステータス取得
	 * @return srhStockingStatus
	 */
	public String getSrhStockingStatus() {
		return this.srhStockingStatus;
	}

	/**
	 * 検索入力.仕入ステータス設定 *
	 * @param srhStockingStatus 仕入ステータス
	 */
	public void setSrhStockingStatus(final String srhStockingStatus) {
		this.srhStockingStatus = srhStockingStatus;
	}

	/**
	 * 月次更新済チェックボックス取得
	 * @return srhMonthlyCheck
	 */
	public boolean isSrhMonthlyCheck() {
		return srhMonthlyCheck;
	}

	/**
	 * 月次更新済チェックボックス設定
	 * @param srhMonthlyCheck 月次更新済チェックボックス
	 */
	public void setSrhMonthlyCheck(final boolean srhMonthlyCheck) {
		this.srhMonthlyCheck = srhMonthlyCheck;
	}

	/**
	 * srhSlipIssueDivision取得
	 * @return srhSlipIssueDivision
	 */
	public boolean isSrhSlipIssueDivision() {
		return srhSlipIssueDivision;
	}

	/**
	 * srhSlipIssueDivision設定
	 * @param srhSlipIssueDivision srhSlipIssueDivision
	 */
	public void setSrhSlipIssueDivision(final boolean srhSlipIssueDivision) {
		this.srhSlipIssueDivision = srhSlipIssueDivision;
	}
}
