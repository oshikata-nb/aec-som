/*
 * Created on Tue Apr 28 09:04:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transnote;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TransNoteクラス.
 * @author t0011036
 */
public class TransNote extends TransNoteBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TransNote() {
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
