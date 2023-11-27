/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

/**
 * 包装指図－製造指図明細データ格納クラス.
 * 
 * @author tosco
 */
public class PkgDirectionDirectionDetailList extends PkgDirectionDirectionDetailListBase {

	private static final long serialVersionUID = 1L;

	/** チェックフラグ */
	private boolean checkFlg;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionDetailList() {
		super();
	}

	/**
	 * チェックフラグ取得.
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグ設定.
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}
}
