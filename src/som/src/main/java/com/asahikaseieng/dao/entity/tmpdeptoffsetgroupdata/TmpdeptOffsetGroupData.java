/*
 * Created on Thu Jan 22 17:16:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpdeptoffsetgroupdata;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TmpdeptOffsetGroupDataクラス.
 * @author kanri-user
 */
public class TmpdeptOffsetGroupData extends TmpdeptOffsetGroupDataBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TmpdeptOffsetGroupData() {
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
