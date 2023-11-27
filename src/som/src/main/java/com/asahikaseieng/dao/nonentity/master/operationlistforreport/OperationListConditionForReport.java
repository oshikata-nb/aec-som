package com.asahikaseieng.dao.nonentity.master.operationlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 工程検索条件
 * @author a1020630
 */
public class OperationListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public OperationListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhOperationCd; /* 工程コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhOperationCdを取得します。
	 * @return srhOperationCd
	 */
	public String getSrhOperationCd() {
		return srhOperationCd;
	}

	/**
	 * srhOperationCdを設定します。
	 * @param srhOperationCd srhOperationCd
	 */
	public void setSrhOperationCd(final String srhOperationCd) {
		this.srhOperationCd = AecTextUtils.likeFilter(srhOperationCd);
	}
}
