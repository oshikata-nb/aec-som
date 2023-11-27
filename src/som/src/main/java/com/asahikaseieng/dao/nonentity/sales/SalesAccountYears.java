/*
 * Created on 2009/07/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * SalesAccountYearsクラス.
 * @author kanri-user
 */
public class SalesAccountYears extends SalesAccountYearsBase implements
		PropertyTransferCallbacker {
	private static final long serialVersionUID = 1L;

	/** 勘定年月(String) */
	private String strAccountYears;

	/**
	 * コンストラクタ.
	 */
	public SalesAccountYears() {
		super();
	}

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		// 勘定年月
		if (getAccountYears() != null) {
			setStrAccountYears(AecDateUtils.dateFormat(AecDateUtils
					.getTimestampYmdHmFormat(getAccountYears().toString(),
						"yyyyMM"), "yyyy/MM"));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * strAccountYearsを取得します。
	 * @return strAccountYears
	 */
	public String getStrAccountYears() {
		return strAccountYears;
	}

	/**
	 * strAccountYearsを設定します。
	 * @param strAccountYears strAccountYears
	 */
	public void setStrAccountYears(final String strAccountYears) {
		this.strAccountYears = strAccountYears;
	}
}
