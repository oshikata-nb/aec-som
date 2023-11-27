package com.asahikaseieng.dao.nonentity.master.balancelistforreport;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 帳合検索条件
 * @author t0011036
 */
public class BalanceListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BalanceListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private BigDecimal srhBalanceType; /* 区分 */

	private String srhVenderCd; /* 得意先コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhBalanceTypeを取得します。
	 * @return srhBalanceType
	 */
	public BigDecimal getSrhBalanceType() {
		return srhBalanceType;
	}

	/**
	 * srhBalanceTypeを設定します。
	 * @param srhBalanceType srhBalanceType
	 */
	public void setSrhBalanceType(final BigDecimal srhBalanceType) {
		this.srhBalanceType = srhBalanceType;
	}

	/**
	 * srhVenderCdを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * srhVenderCdを設定します。
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}
}
