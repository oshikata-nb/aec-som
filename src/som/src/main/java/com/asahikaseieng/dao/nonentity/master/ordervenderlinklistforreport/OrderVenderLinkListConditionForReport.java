/*
 * Created on 2020/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.ordervenderlinklistforreport;

import com.asahikaseieng.utils.AecTextUtils;
import java.math.BigDecimal;

/**
 * OrderVenderLinkListConditionForReportクラス.
 * @author 
 */
public class OrderVenderLinkListConditionForReport {

	/**
	 * コンストラクタ.
	 */
	public OrderVenderLinkListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：得意先グループコード */
	private String srhVenderGroupCd;

	/** 検索入力：納入先設定 */
	private BigDecimal srhDeliveryConf;

	/** 検索入力：品目設定 */
	private BigDecimal srhItemConf;

	//
	// 検索入力
	//

	/**
	 * 検索入力：取引先グループ取得.
	 * @return srhVenderGroupCd
	 */
	public String getSrhVenderGroupCd() {
		return this.srhVenderGroupCd;
	}

	/**
	 * 検索入力：取引先グループ設定.
	 * @param srhVenderGroupCd srhVenderGroupCd
	 */
	public void setSrhVenderGroupCd(final String srhVenderGroupCd) {
		this.srhVenderGroupCd = AecTextUtils.likeFilter(srhVenderGroupCd);
	}

	/**
	 * 検索入力：納入先設定取得.
	 * @return srhDeliveryConf
	 */
	public BigDecimal getSrhDeliveryConf() {
		return this.srhDeliveryConf;
	}

	/**
	 * 検索入力：納入先設定設定.
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
}
