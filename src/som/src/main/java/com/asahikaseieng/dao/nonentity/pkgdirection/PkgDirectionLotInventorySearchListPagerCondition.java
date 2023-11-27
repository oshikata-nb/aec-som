/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 包装指図－在庫確認ポップアップ画面一覧複数ページ制御クラス
 * @author tosco
 */
public class PkgDirectionLotInventorySearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionLotInventorySearchListPagerCondition() {
	}

	/** 品目コード */
	private String itemCd;

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
}
