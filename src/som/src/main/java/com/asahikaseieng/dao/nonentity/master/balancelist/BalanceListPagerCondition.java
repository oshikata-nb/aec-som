package com.asahikaseieng.dao.nonentity.master.balancelist;

import java.math.BigDecimal;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 帳合検索条件
 * @author t0011036
 */
public class BalanceListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BalanceListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private BigDecimal srhBalanceType; /* 区分 */

	private String srhVenderCd; /* 得意先コード */

	private String srhBalanceCd; /* 帳合コード */

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

	/**
	 * srhBalanceCdを取得します。
	 * @return srhBalanceCd
	 */
	public String getSrhBalanceCd() {
		return srhBalanceCd;
	}

	/**
	 * srhBalanceCdを設定します。
	 * @param srhBalanceCd srhBalanceCd
	 */
	public void setSrhBalanceCd(final String srhBalanceCd) {
		this.srhBalanceCd = AecTextUtils.likeFilter(srhBalanceCd);
	}
}
