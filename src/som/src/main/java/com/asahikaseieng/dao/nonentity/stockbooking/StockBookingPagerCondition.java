/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 検査待ち在庫計上一覧　複数ページ制御クラス.
 *
 * @author tosco
 */
public class StockBookingPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingPagerCondition() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：包装実績日付(From) */
	private Timestamp srhResultEdateFrom;

	/** 検索入力：包装実績日付(To) */
	private Timestamp srhResultEdateTo;

	//
	// 検索入力
	//

	/**
	 * 検索入力：生産ライン取得.
	 * @return String 生産ライン
	 */
	public String getSrhProductionLine() {
		return this.srhProductionLine;
	}

	/**
	 * 検索入力：生産ライン設定.
	 * @param srhProductionLine 生産ライン
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：包装実績日付(From)を取得します。
	 * @return srhResultEdateFrom
	 */
	public Timestamp getSrhResultEdateFrom() {
		return srhResultEdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(From)を設定します。
	 * @param srhResultEdateFrom 検索入力：包装実績日付(From)
	 */
	public void setSrhResultEdateFrom(final Timestamp srhResultEdateFrom) {
		this.srhResultEdateFrom = srhResultEdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(To)を取得します。
	 * @return srhResultSdateTo
	 */
	public Timestamp getSrhResultEdateTo() {
		return srhResultEdateTo;
	}

	/**
	 * 検索入力：包装実績日付(To)を設定します。
	 * @param srhResultSdateTo 検索入力：包装実績日付(To)
	 */
	public void setSrhResultEdateTo(final Timestamp srhResultSdateTo) {
		this.srhResultEdateTo = srhResultSdateTo;
	}
}
