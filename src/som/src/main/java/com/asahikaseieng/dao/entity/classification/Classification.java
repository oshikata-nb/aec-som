/*
 * Created on Mon Jan 19 16:58:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.classification;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Classificationクラス.
 * @author t0011036
 */
public class Classification extends ClassificationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Classification() {
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
