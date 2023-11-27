/*
 * Created on Tue Apr 21 10:54:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspImportAspUseinstructionクラス.
 * @author
 */
public class AspImportAspUseinstruction extends AspImportAspUseinstructionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspImportAspUseinstruction() {
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
