/*
 * Created on 2009/08/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.carryshippingforreport;

/**
 * 運送店別出荷指図画面 一覧複数ページ制御クラス.
 * 
 * @author t1344224O
 */
public class CarryShippingListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryShippingListConditionForReport() {
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
