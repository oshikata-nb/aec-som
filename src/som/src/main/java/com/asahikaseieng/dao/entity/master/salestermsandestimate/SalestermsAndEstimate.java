package com.asahikaseieng.dao.entity.master.salestermsandestimate;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * SalesTermsクラス.
 * @author kanri-user
 */
public class SalestermsAndEstimate extends SalestermsAndEstimateBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimate() {
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
