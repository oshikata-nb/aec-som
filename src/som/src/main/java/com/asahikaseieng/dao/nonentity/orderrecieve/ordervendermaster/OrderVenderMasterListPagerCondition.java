/*
 * Created on 2020/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderrecieve.ordervendermaster;

import java.math.BigDecimal;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * OrderVenderLinkPagerConditionクラス.
 * @author 
 */
public class OrderVenderMasterListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderVenderMasterListPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：得意先グループ */
	private String srhVenderGroupCd;

	/** 検索入力：納入先設定 */
	private BigDecimal srhDeliveryConf;

	/** 検索入力：品目設定 */
	private BigDecimal srhItemConf;
	
	/** 検索入力:帳合コード*/
	private String srhBalanceCd;
	//
	// 検索入力
	//

	/**
	 * 検索入力：得意先グループ取得.
	 * @return srhVenderGroupCd
	 */
	public String getSrhVenderGroupCd() {
		return this.srhVenderGroupCd;
	}

	/**
	 * 検索入力：取引先区分設定.
	 * @param srhVenderGroupCd srhVenderGroupCd
	 */
	public void setSrhVenderGroupCd(final String srhVenderGroupCd) {
		this.srhVenderGroupCd = srhVenderGroupCd;
	}

	/**
	 * srhDeliveryConfを取得します。
	 * @return srhDeliveryConf
	 */
	public BigDecimal getSrhDeliveryConf() {
		return srhDeliveryConf;
	}

	/**
	 * srhDeliveryConfを設定します。
	 * @param srhDeliveryConf srhDeliveryConf
	 */
	public void setSrhDeliveryConf(final BigDecimal srhDeliveryConf) {
		this.srhDeliveryConf = srhDeliveryConf;
	}
	
	/**
	 * srhItemConfを取得します。
	 * @return srhItemConf
	 */
	public BigDecimal getSrhItemConf() {
		return srhItemConf;
	}

	/**
	 * srhItemConfを設定します。
	 * @param srhItemConf srhItemConf
	 */
	public void setSrhItemConf(final BigDecimal srhItemConf) {
		this.srhItemConf = srhItemConf;
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
	public void setSrhBalanceCd(String srhBalanceCd) {
		this.srhBalanceCd = srhBalanceCd;
	}
	
}
