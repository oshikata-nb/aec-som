/*
 * Created on Fri Jan 23 13:53:49 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.label;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Labelクラス.
 * @author t0011036
 */
public class Label extends LabelBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Label() {
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
