/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.itemqueue;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 品目マスタキュー検索(ポップアップ)　検索条件
 * @author tosco
 */
public class ItemQueueSearchListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ItemQueueSearchListPagerCondition() {
	}

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/**
	 * 品目コードを取得します
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = AecTextUtils.likeFilter(itemCd);
	}

	/**
	 * 品目名称を取得します
	 * @return String 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します#
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = AecTextUtils.likeFilter(itemName);
	}

	/**
	 * 他社コード1を取得します。
	 * @return String 他社コード1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード1を設定します。
	 * @param otherCompanyCd1 他社コード1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = AecTextUtils.likeFilter(otherCompanyCd1);
	}

}
