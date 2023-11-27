/*
 * Created on Mon Jan 19 18:36:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.component;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Componentクラス.
 * @author t0011036
 */
public class Component extends ComponentBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Component() {
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
