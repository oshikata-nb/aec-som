/*
 * Created on Mon Jan 19 17:47:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.company;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Companyクラス.
 * @author t0011036
 */
public class Company extends CompanyBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Company() {
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
