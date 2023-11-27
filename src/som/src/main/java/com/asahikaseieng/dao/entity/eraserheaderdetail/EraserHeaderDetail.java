/*
 * Created on Thu Jan 22 13:17:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheaderdetail;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * EraserHeaderDetailクラス.
 * @author t0011036
 */
public class EraserHeaderDetail extends EraserHeaderDetailBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public EraserHeaderDetail() {
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
