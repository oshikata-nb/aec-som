/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 製造実績－ロット検索ポップアップ画面一覧複数ページ制御クラス
 * @author tosco
 */
public class RdirectionLotInventorySearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public RdirectionLotInventorySearchListPagerCondition() {
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
