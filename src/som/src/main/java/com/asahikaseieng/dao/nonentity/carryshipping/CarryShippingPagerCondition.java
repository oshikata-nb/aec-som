/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.carryshipping;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 運送店別出荷指図画面　一覧複数ページ制御クラス.
 *
 * @author tosco
 */
public class CarryShippingPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryShippingPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：出荷予定日 */
	private String srhShippingInstructDate;

	//
	// 検索入力
	//

	/**
	 * 検索入力：出荷予定日取得.
	 * @return String 出荷予定日
	 */
	public String getSrhShippingInstructDate() {
		return this.srhShippingInstructDate;
	}

	/**
	 * 検索入力：出荷予定日設定.
	 * @param srhShippingInstructDate 出荷予定日
	 */
	public void setSrhShippingInstructDate(final String srhShippingInstructDate) {
		this.srhShippingInstructDate = srhShippingInstructDate;
	}

}
