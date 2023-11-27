package com.asahikaseieng.dao.nonentity.buyinginout;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 受払検索(ポップアップ)検索条件
 * @author t1344224
 */
public class BuyingInoutSearchListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BuyingInoutSearchListPagerCondition() {
	}

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：仕入先コード */
	private String srhVenderCd;

	/**
	 * srhItemCd取得.
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCd設定.
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 仕入先取得.
	 * @return String 仕入先
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

}
