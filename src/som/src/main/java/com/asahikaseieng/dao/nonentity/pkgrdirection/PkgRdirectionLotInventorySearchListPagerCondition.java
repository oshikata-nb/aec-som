/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.math.BigDecimal;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 包装実績－ロット検索画面一覧複数ページ制御クラス
 * @author tosco
 */
public class PkgRdirectionLotInventorySearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionLotInventorySearchListPagerCondition() {
	}

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 指図番号 */
	private String directionNo;

	/** ステップNO. */
	private BigDecimal stepNo;

	/** 行NO. */
	private BigDecimal lineNo;

	/** 品目コード */
	private String itemCd;

	/** ロット番号 */
	private String lotNo;

	/**
	 * 品目コード取得.
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コード設定.
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * ロット番号設定.
	 * @param lotNo ロット番号
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * 指図区分取得.
	 * @return BigDecimal 指図区分
	 */
	public BigDecimal getDirectionDivision() {
		return directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 指図番号取得.
	 * @return String 指図番号
	 */
	public String getDirectionNo() {
		return directionNo;
	}

	/**
	 * 指図番号設定.
	 * @param directionNo 指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * 行NO.取得.
	 * @return BigDecimal 行NO.
	 */
	public BigDecimal getLineNo() {
		return lineNo;
	}

	/**
	 * 行NO.設定.
	 * @param lineNo 行NO.
	 */
	public void setLineNo(final BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * ステップNO.取得.
	 * @return BigDecimal ステップNO.
	 */
	public BigDecimal getStepNo() {
		return stepNo;
	}

	/**
	 * ステップNO.設定.
	 * @param stepNo ステップNO.
	 */
	public void setStepNo(final BigDecimal stepNo) {
		this.stepNo = stepNo;
	}
}
