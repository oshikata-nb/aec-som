/*
 * Created on 2009/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;

/**
 * 受払履歴を更新するかの判断を行う。
 * @author a7710658
 */
public class StockinoutForUpdateChecker {

	/**
	 * コンストラクタ
	 */
	public StockinoutForUpdateChecker() {
	}

	/**
	 * コンストラクタ
	 * @param bean .
	 */
	public StockinoutForUpdateChecker(final DirectionFormula bean) {
		this.setRecord(bean);
	}

	/**
	 * コンストラクタ
	 * @param bean .
	 */
	public StockinoutForUpdateChecker(final RdirectionDirectionFormulaList bean) {
		this.setRecord(bean);
	}

	/** 量 */
	private java.math.BigDecimal qty;

	/** ロケーション */
	private String locationCd;

	/** ロット */
	private String lotNo;

	/**
	 * 更新が必要（差異あり）を判定する。
	 * @param bean .
	 * @return true:差異あり
	 */
	public boolean notEqual(final StockinoutForUpdateChecker bean) {
		if (bean != null) {
			if (notEqual(this.qty, bean.getQty())) {
				return true;
			}
			if (notEqual(this.locationCd, bean.getLocationCd())) {
				return true;
			}
			if (notEqual(this.lotNo, bean.getLotNo())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 差異あり）を判定する。
	 * @param a .
	 * @param b .
	 * @return true:差異あり
	 */
	private boolean notEqual(final BigDecimal a, final BigDecimal b) {
		if (a != null && b != null) {
			if (a.compareTo(b) == 0) {
				return false;
			}
		} else if (a == null && b == null) {
			return false;
		}
		return true;
	}

	/**
	 * 差異あり）を判定する。
	 * @param a .
	 * @param b .
	 * @return true:差異あり
	 */
	private boolean notEqual(final String a, final String b) {
		if (a != null && b != null) {
			if (a.equals(b)) {
				return false;
			}
		} else if (a == null && b == null) {
			return false;
		}
		return true;
	}

	/**
	 * 包装実績－フォーミュラデータ格納クラスの比較用データセット
	 * @param bean 包装実績－フォーミュラデータ格納クラス.
	 */
	public void setRecord(final PkgRdirectionDirectionFormulaList bean) {
		this.qty = bean.getStockpdQty();
		this.locationCd = bean.getLocationCd();
		this.lotNo = bean.getLotNo();

	}

	/**
	 * 包装実績－ロット検索画面データ格納クラス.の比較用データセット
	 * @param bean 包装実績－ロット検索画面データ格納クラス.
	 */
	public void setRecord(final PkgRdirectionLotInventorySearchList bean) {
		this.qty = bean.getStockpdQty();
		this.locationCd = bean.getLocationCd();
		this.lotNo = bean.getLotNo();
	}

	/**
	 * 製造指図フォーミュラ_表示用データ格納クラス.の比較用データセット
	 * @param bean 製造指図フォーミュラ_表示用データ格納クラス.
	 */
	public void setRecord(final RdirectionDirectionFormulaList bean) {
		this.qty = bean.getStockpdQty();
		this.locationCd = bean.getLocationCd();
		this.lotNo = bean.getLotNo();
	}

	/**
	 * 製造指図フォーミュラデータ格納クラス.の比較用データセット
	 * @param bean 製造指図フォーミュラデータ格納クラス.
	 */
	public void setRecord(final DirectionFormula bean) {
		this.qty = bean.getStockpdQty();
		this.locationCd = bean.getLocationCd();
		this.lotNo = bean.getLotNo();
	}

	/* -------------------- setter -------------------- */

	/**
	 * locationCdを取得します。
	 * @return locationCd
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * locationCdを設定します。
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * lotNoを取得します。
	 * @return lotNo
	 */
	public String getLotNo() {
		return lotNo;
	}

	/**
	 * lotNoを設定します。
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * qtyを取得します。
	 * @return qty
	 */
	public java.math.BigDecimal getQty() {
		return qty;
	}

	/**
	 * qtyを設定します。
	 * @param qty qty
	 */
	public void setQty(final java.math.BigDecimal qty) {
		this.qty = qty;
	}
}
