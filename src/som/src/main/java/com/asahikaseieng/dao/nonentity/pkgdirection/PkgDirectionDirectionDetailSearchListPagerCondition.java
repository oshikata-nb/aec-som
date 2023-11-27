/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.math.BigDecimal;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 包装指図－製造指図明細ポップアップ画面一覧複数ページ制御クラス
 * @author tosco
 */
public class PkgDirectionDirectionDetailSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionDirectionDetailSearchListPagerCondition() {
	}

	/** 指図区分 */
	private BigDecimal directionDivision;

	/** 包装指図番号 */
	private String pkgDirectionNo;

	/**
	 * 指図区分取得.
	 * @return String 指図区分
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
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getPkgDirectionNo() {
		return pkgDirectionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param pkgDirectionNo 包装指図番号
	 */
	public void setPkgDirectionNo(final String pkgDirectionNo) {
		this.pkgDirectionNo = pkgDirectionNo;
	}
}
