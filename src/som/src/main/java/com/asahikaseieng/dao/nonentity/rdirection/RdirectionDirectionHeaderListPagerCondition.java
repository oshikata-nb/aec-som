/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 製造指図ヘッダーメンテ 一覧複数ページ制御クラス.
 * 
 * @author tosco
 */
public class RdirectionDirectionHeaderListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionDirectionHeaderListPagerCondition() {
	}

	/** 指図番号 */
	private String directionNo;

	/** 生産工場 */
	private String productionLine;

	/** 指図ステータス */
	private String directionStatus;

	/** 主要製品コード */
	private String itemCd;

	/** 製造開始実績日時(From) */
	private String resultSdateDateFrom;

	/** 製造終了実績日時(From) */
	private String resultEdateDateFrom;

	/** 製造開始実績日時(To) */
	private String resultSdateDateTo;

	/** 製造終了実績日時(To) */
	private String resultEdateDateTo;

	/** 他社コード１ */
	private String otherCompanyCd1;

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

	/**
	 * 製造開始実績日時(From)を取得します。
	 * @return 製造開始実績日時(From)
	 */
	public String getResultSdateDateFrom() {
		return resultSdateDateFrom;
	}

	/**
	 * 製造開始実績日時(From)を設定します。
	 * @param resultSdateDateFrom 製造開始実績日時(From)
	 */
	public void setResultSdateDateFrom(final String resultSdateDateFrom) {
		this.resultSdateDateFrom = resultSdateDateFrom;
	}

	/**
	 * 製造終了実績日時(From)を取得します。
	 * @return 製造終了実績日時(From)
	 */
	public String getResultEdateDateFrom() {
		return resultEdateDateFrom;
	}

	/**
	 * 製造終了実績日時(From)を設定します。
	 * @param resultEdateDateFrom 製造終了実績日時(From)
	 */
	public void setResultEdateDateFrom(final String resultEdateDateFrom) {
		this.resultEdateDateFrom = resultEdateDateFrom;
	}

	/**
	 * 製造開始実績日時(to)を取得します。
	 * @return 製造開始実績日時(to)
	 */
	public String getResultSdateDateTo() {
		return resultSdateDateTo;
	}

	/**
	 * 製造開始実績日時(to)を設定します。
	 * @param resultSdateDateTo 製造開始実績日時(to)
	 */
	public void setResultSdateDateTo(final String resultSdateDateTo) {
		this.resultSdateDateTo = resultSdateDateTo;
	}

	/**
	 * 製造終了実績日時(to)を取得します。
	 * @return 製造終了実績日時(to)
	 */
	public String getResultEdateDateTo() {
		return resultEdateDateTo;
	}

	/**
	 * 製造終了実績日時(to)を設定します。
	 * @param resultEdateDateTo 製造終了実績日時(to)
	 */
	public void setResultEdateDateTo(final String resultEdateDateTo) {
		this.resultEdateDateTo = resultEdateDateTo;
	}

}
