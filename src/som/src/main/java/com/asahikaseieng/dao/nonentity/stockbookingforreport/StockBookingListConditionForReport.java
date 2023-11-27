/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.sql.Timestamp;

/**
 * 検査待ち在庫計上一覧 複数ページ制御クラス.
 * 
 * @author t1344224
 */
public class StockBookingListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：生産ライン */
	private String srhProductionLine;

	/** 検索入力：包装実績日付(From) */
	private Timestamp srhResultSdateFrom;

	/** 検索入力：包装実績日付(To) */
	private Timestamp srhResultSdateTo;

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
	 * @return srhResultSdateFrom
	 */
	public Timestamp getSrhResultSdateFrom() {
		return srhResultSdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(From)を設定します。
	 * @param srhResultSdateFrom 検索入力：包装実績日付(From)
	 */
	public void setSrhResultSdateFrom(final Timestamp srhResultSdateFrom) {
		this.srhResultSdateFrom = srhResultSdateFrom;
	}

	/**
	 * 検索入力：包装実績日付(To)を取得します。
	 * @return srhResultSdateTo
	 */
	public Timestamp getSrhResultSdateTo() {
		return srhResultSdateTo;
	}

	/**
	 * 検索入力：包装実績日付(To)を設定します。
	 * @param srhResultSdateTo 検索入力：包装実績日付(To)
	 */
	public void setSrhResultSdateTo(final Timestamp srhResultSdateTo) {
		this.srhResultSdateTo = srhResultSdateTo;
	}
}
