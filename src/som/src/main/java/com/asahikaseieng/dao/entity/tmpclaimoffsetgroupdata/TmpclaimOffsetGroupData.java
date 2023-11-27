/*
 * Created on Thu Jan 22 17:22:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpclaimoffsetgroupdata;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TmpclaimOffsetGroupDataクラス.
 * @author kanri-user
 */
public class TmpclaimOffsetGroupData extends TmpclaimOffsetGroupDataBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TmpclaimOffsetGroupData() {
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
