package com.asahikaseieng.dao.nonentity.master.accountslistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 科目検索条件
 * @author a1020630
 */
public class AccountsListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public AccountsListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhAccountsCd; /* 科目コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhAccountsCdを取得します。
	 * @return srhAccountsCd
	 */
	public String getSrhAccountsCd() {
		return srhAccountsCd;
	}

	/**
	 * srhAccountsCdを設定します。
	 * @param srhAccountsCd srhAccountsCd
	 */
	public void setSrhAccountsCd(final String srhAccountsCd) {
		this.srhAccountsCd = AecTextUtils.likeFilter(srhAccountsCd);
	}
}
