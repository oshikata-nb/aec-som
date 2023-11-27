/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.direction;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 製造指図ヘッダーメンテ 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class DirectionDirectionHeaderListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionDirectionHeaderListPagerCondition() {
	}

	/** 指図番号 */
	private String directionNo;

	/** 生産工場 */
	private String productionLine;

	/** 指図ステータス */
	private String directionStatus;

	/** 主要製品コード */
	private String itemCd;

	/** 開始予定日(From) */
	private String planedSdateDateFrom;

	/** 終了予定日(From) */
	private String planedEdateDateFrom;

	/** 開始予定日(To) */
	private String planedSdateDateTo;

	/** 終了予定日(To) */
	private String planedEdateDateTo;

	/** 他社コード１ */
	private String otherCompanyCd1;

	/** APSオーダーコード */
	private String aspOrderNo;

	/** 調合タンクNo */
	private String compoundTankNo;

	// メソッド--------------------------------------------------
	/**
	 * 検索入力：生産工場.
	 * @return String 生産工場
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * 検索入力：生産工場.
	 * @param productionLine 生産工場
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * 検索入力：他社コード１を取得します。
	 * @return 検索入力：他社コード１
	 */
	public String getOtherCompanyCd1() {
		return AecTextUtils.likeFilter(otherCompanyCd1);
	}

	/**
	 * 検索入力：他社コード１を設定します。
	 * @param otherCompanyCd1 検索入力：他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 指図番号を取得します。
	 * @return 指図番号
	 */
	public String getDirectionNo() {
		return AecTextUtils.likeFilter(directionNo);
	}

	/**
	 * 指図番号を設定します。
	 * @param directionNo 指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 指図ステータスを取得します。
	 * @return 指図ステータス
	 */
	public String getDirectionStatus() {
		return directionStatus;
	}

	/**
	 * 指図ステータスを設定します。
	 * @param directionStatus 指図ステータス
	 */
	public void setDirectionStatus(final String directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * 主要製品コードを取得します。
	 * @return 主要製品コード
	 */
	public String getItemCd() {
		return AecTextUtils.likeFilter(itemCd);
	}

	/**
	 * 主要製品コードを設定します。
	 * @param itemCd 主要製品コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 開始予定日(From)を取得します。
	 * @return 開始予定日(From)
	 */
	public String getPlanedSdateDateFrom() {
		return planedSdateDateFrom;
	}

	/**
	 * 開始予定日(From)を設定します。
	 * @param planedSdateDateFrom 開始予定日(From)
	 */
	public void setPlanedSdateDateFrom(final String planedSdateDateFrom) {
		this.planedSdateDateFrom = planedSdateDateFrom;
	}

	/**
	 * 終了予定日(From)を取得します。
	 * @return 終了予定日(From)
	 */
	public String getPlanedEdateDateFrom() {
		return planedEdateDateFrom;
	}

	/**
	 * 終了予定日(From)を設定します。
	 * @param planedEdateDateFrom 終了予定日(From)
	 */
	public void setPlanedEdateDateFrom(final String planedEdateDateFrom) {
		this.planedEdateDateFrom = planedEdateDateFrom;
	}

	/**
	 * 開始予定日(to)を取得します。
	 * @return 開始予定日(to)
	 */
	public String getPlanedSdateDateTo() {
		return planedSdateDateTo;
	}

	/**
	 * 開始予定日(to)を設定します。
	 * @param planedSdateDateTo 開始予定日(to)
	 */
	public void setPlanedSdateDateTo(final String planedSdateDateTo) {
		this.planedSdateDateTo = planedSdateDateTo;
	}

	/**
	 * 終了予定日(to)を取得します。
	 * @return 終了予定日(to)
	 */
	public String getPlanedEdateDateTo() {
		return planedEdateDateTo;
	}

	/**
	 * 終了予定日(to)を設定します。
	 * @param planedEdateDateTo 終了予定日(to)
	 */
	public void setPlanedEdateDateTo(final String planedEdateDateTo) {
		this.planedEdateDateTo = planedEdateDateTo;
	}

	/**
	 * ASPオーダーコードを取得します。
	 * @return ASPオーダーコード
	 */
	public String getAspOrderNo() {
		return AecTextUtils.likeFilter(aspOrderNo);
	}

	/**
	 * ASPオーダーコードを設定します。
	 * @param aspOrderNo ASPオーダーコード
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * compoundTankNoを取得します。
	 * @return compoundTankNo
	 */
	public String getCompoundTankNo() {
		return AecTextUtils.likeFilter(compoundTankNo);
	}

	/**
	 * compoundTankNoを設定します。
	 * @param compoundTankNo compoundTankNo
	 */
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}
}
