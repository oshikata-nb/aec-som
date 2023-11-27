/*
 * Created on Wed Feb 04 09:37:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * EraserHeaderクラス.
 * @author kanri-user
 */
public class EraserHeader extends EraserHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public EraserHeader() {
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
