/*
 * Created on Wed Feb 04 09:58:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.organization;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Organizationクラス.
 * @author kanri-user
 */
public class Organization extends OrganizationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Organization() {
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
