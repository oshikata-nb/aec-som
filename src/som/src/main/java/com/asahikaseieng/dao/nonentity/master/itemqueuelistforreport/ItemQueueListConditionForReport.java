package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ##ItemのPagerConditionクラス：検索画面の検索条件##
 * @author a1020630
 */
public class ItemQueueListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public ItemQueueListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhItemCd;

	private String srhParentItemCd;

	private String srhOtherCompanyCd1;

	private java.math.BigDecimal srhStatus;

	private java.math.BigDecimal srhDetailStatus;

	private String strSrhActiveDateFrom;

	private String strSrhActiveDateTo;

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhItemCdを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * srhItemCdを設定します。
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

	/**
	 * srhParentItemCdを取得します。
	 * @return srhParentItemCd
	 */
	public String getSrhParentItemCd() {
		return srhParentItemCd;
	}

	/**
	 * srhParentItemCdを設定します。
	 * @param srhParentItemCd srhParentItemCd
	 */
	public void setSrhParentItemCd(final String srhParentItemCd) {
		this.srhParentItemCd = AecTextUtils.likeFilter(srhParentItemCd);
	}

	/**
	 * srhStatusを取得します。
	 * @return srhStatus
	 */
	public java.math.BigDecimal getSrhStatus() {
		return srhStatus;
	}

	/**
	 * srhStatusを設定します。
	 * @param srhStatus srhStatus
	 */
	public void setSrhStatus(final java.math.BigDecimal srhStatus) {
		this.srhStatus = srhStatus;
	}

	/**
	 * strSrhActiveDateFromを取得します。
	 * @return strSrhActiveDateFrom
	 */
	public String getStrSrhActiveDateFrom() {
		return strSrhActiveDateFrom;
	}

	/**
	 * strSrhActiveDateFromを設定します。
	 * @param strSrhActiveDateFrom strSrhActiveDateFrom
	 */
	public void setStrSrhActiveDateFrom(final String strSrhActiveDateFrom) {
		this.strSrhActiveDateFrom = strSrhActiveDateFrom;
	}

	/**
	 * strSrhActiveDateToを取得します。
	 * @return strSrhActiveDateTo
	 */
	public String getStrSrhActiveDateTo() {
		return strSrhActiveDateTo;
	}

	/**
	 * strSrhActiveDateToを設定します。
	 * @param strSrhActiveDateTo strSrhActiveDateTo
	 */
	public void setStrSrhActiveDateTo(final String strSrhActiveDateTo) {
		this.strSrhActiveDateTo = strSrhActiveDateTo;
	}

	/**
	 * srhDetailStatusを取得します。
	 * @return srhDetailStatus
	 */
	public java.math.BigDecimal getSrhDetailStatus() {
		return srhDetailStatus;
	}

	/**
	 * srhDetailStatusを設定します。
	 * @param srhDetailStatus srhDetailStatus
	 */
	public void setSrhDetailStatus(final java.math.BigDecimal srhDetailStatus) {
		this.srhDetailStatus = srhDetailStatus;
	}
}
