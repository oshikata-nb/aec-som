package com.asahikaseieng.dao.nonentity.salesinout;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 受払検索(ポップアップ)検索条件
 * @author t1344224
 */
public class SalesInoutSearchListPagerCondition extends
		DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public SalesInoutSearchListPagerCondition() {
	}

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：得意先コード */
	private String srhVenderCd;

	/** 検索入力：売上日 */
	private String srhSalesDate;

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
	 * srhVenderCd取得.
	 * @return srhVenderCd srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCd設定.
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = srhVenderCd;
	}

	/**
	 * srhSalesDate取得.
	 * @return srhSalesDate
	 */
	public String getSrhSalesDate() {
		return srhSalesDate;
	}

	/**
	 * srhSalesDate設定.
	 * @param srhSalesDate srhSalesDate
	 */
	public void setSrhSalesDate(final String srhSalesDate) {
		this.srhSalesDate = srhSalesDate;
	}
}
