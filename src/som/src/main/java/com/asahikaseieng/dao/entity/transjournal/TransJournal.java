/*
 * Created on Wed Apr 29 11:49:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transjournal;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TransJournalクラス.
 * @author t0011036
 */
public class TransJournal extends TransJournalBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TransJournal() {
		super();
	}

	/**
	 * 更新日時を返す
	 * @return Timestamp
	 */
	public Timestamp getCurrentTimestamp() {
		return AecDateUtils.getCurrentTimestamp();
	}
}
