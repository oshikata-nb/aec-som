/*
 * Created on 2010/01/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasesubcontract;

import java.math.BigDecimal;

/**
 * 購買ステータス|1:展開済 2:発注書発行済 3:納期調整中 4:納期確定 5:入荷登録済 6:受入登録済 7:発注書未発行
 * @author a7710658
 */
public final class PurchaseStatus {
	private PurchaseStatus() {
	}

	/** 購買ステータス 0:すべて */
	public static final BigDecimal PURCHASE_STATUS_ALL = new BigDecimal(0);

	/** 購買ステータス 0:すべて */
	public static final String PURCHASE_STATUS_ALL_NAME = "すべて";

	/** 購買ステータス 1:展開済 */
	public static final BigDecimal ASPROVA = new BigDecimal(1);

	/** 購買ステータス 1:展開済 */
	public static final String ASPROVA_NAME = "展開済";

	/** 購買ステータス 2:発注書発行済 */
	public static final BigDecimal ISSUED = new BigDecimal(2);

	/** 購買ステータス 2:発注書発行済 */
	public static final String ISSUED_NAME = "発注書発行済";

	/** 購買ステータス 3:納期調整中 */
	public static final BigDecimal TYOSEI = new BigDecimal(3);

	/** 購買ステータス 3:納期調整中 */
	public static final String TYOSEI_NAME = "納期調整中";

	/** 購買ステータス 4:納期確定 */
	public static final BigDecimal FIXED = new BigDecimal(4);

	/** 購買ステータス 4:納期確定 */
	public static final String FIXED_NAME = "納期確定";

	/** 購買ステータス 5:入荷登録済 */
	public static final BigDecimal ARRIVALED = new BigDecimal(5);

	/** 購買ステータス 5:入荷登録済 */
	public static final String ARRIVALED_NAME = "入荷登録済";

	/** 購買ステータス 6:受入登録済 */
	public static final BigDecimal ACCEPTED = new BigDecimal(6);

	/** 購買ステータス 6:受入登録済 */
	public static final String ACCEPTED_NAME = "受入登録済";

	/** 購買ステータス 7:発注書未発行 */
	public static final BigDecimal NOT_ISSUE = new BigDecimal(7);

	/** 購買ステータス 7:発注書未発行 */
	public static final String NOT_ISSUE_NAME = "発注書未発行";

	/** 発注画面の検索区分 1:通常検索 */
	public static final String SEARCH_TYPE_NOMAL = "1";

	/** 発注画面の検索区分 2:アラーム検索 */
	public static final String SEARCH_TYPE_ALARM = "2";

	/**
	 * 未入荷であることを確認
	 * @param status 購買ステータス
	 * @return true:未入荷 false:入荷済み
	 */
	public static boolean isMinyuka(final BigDecimal status) {
		if (status == null) {
			return true;
		}
		if (ARRIVALED.compareTo(status) > 0) {
			return true;
		}
		if (NOT_ISSUE.compareTo(status) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 購買ステータスの名称を返す
	 * @param status 購買ステータスNo
	 * @return 購買ステータス名称 該当なしはNULLを返す。
	 */
	public static String getName(final BigDecimal status) {
		if (status == null) {
			return null;
		}
		if (PURCHASE_STATUS_ALL.compareTo(status) == 0) {
			return PURCHASE_STATUS_ALL_NAME;
		}
		if (ISSUED.compareTo(status) == 0) {
			return ISSUED_NAME;
		}
		if (TYOSEI.compareTo(status) == 0) {
			return TYOSEI_NAME;
		}
		if (FIXED.compareTo(status) == 0) {
			return FIXED_NAME;
		}
		if (ACCEPTED.compareTo(status) == 0) {
			return ACCEPTED_NAME;
		}
		if (ARRIVALED.compareTo(status) == 0) {
			return ARRIVALED_NAME;
		}
		if (ASPROVA.compareTo(status) == 0) {
			return ASPROVA_NAME;
		}
		if (NOT_ISSUE.compareTo(status) == 0) {
			return NOT_ISSUE_NAME;
		}
		return null;
	}

	/**
	 * ステータスが変更可能かを返す
	 * @param status 購買ステータスNo
	 * @return 変更ならtrue 発行済み、調整中、確定
	 */
	public static boolean isAbleChange(final BigDecimal status) {
		if (status == null) {
			return false;
		}
		if (ISSUED.compareTo(status) == 0) {
			return true;
		}
		if (TYOSEI.compareTo(status) == 0) {
			return true;
		}
		if (FIXED.compareTo(status) == 0) {
			return true;
		}
		return false;
	}
}
