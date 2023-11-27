/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemchangehistory;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * DeliverydateConditionクラス.
 * @author jbd
 */
public class ItemchangehistoryCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemchangehistoryCondition() {
	}

	private java.math.BigDecimal menuId;

	private String itemCd;

	private String updatorCd;

	private java.sql.Timestamp updateDateFrom;

	private java.sql.Timestamp updateDateTo;

	private java.math.BigDecimal dispMaxRowNum;

	private String tantoNm;

	private String reason;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	/**
	 * dispMaxRowNumを取得します。
	 * @return dispMaxRowNum
	 */
	public java.math.BigDecimal getDispMaxRowNum() {
		return dispMaxRowNum;
	}

	/**
	 * dispMaxRowNumを設定します。
	 * @param dispMaxRowNum dispMaxRowNum
	 */
	public void setDispMaxRowNum(final java.math.BigDecimal dispMaxRowNum) {
		this.dispMaxRowNum = dispMaxRowNum;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * menuIdを取得します。
	 * @return menuId
	 */
	public java.math.BigDecimal getMenuId() {
		return menuId;
	}

	/**
	 * menuIdを設定します。
	 * @param menuId menuId
	 */
	public void setMenuId(final java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * reasonを取得します。
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * reasonを設定します。
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * updateDateFromを取得します。
	 * @return updateDateFrom
	 */
	public java.sql.Timestamp getUpdateDateFrom() {
		return updateDateFrom;
	}

	/**
	 * updateDateFromを設定します。
	 * @param updateDateFrom updateDateFrom
	 */
	public void setUpdateDateFrom(final java.sql.Timestamp updateDateFrom) {
		this.updateDateFrom = updateDateFrom;
	}

	/**
	 * updateDateToを取得します。
	 * @return updateDateTo
	 */
	public java.sql.Timestamp getUpdateDateTo() {
		return updateDateTo;
	}

	/**
	 * updateDateToを設定します。
	 * @param updateDateTo updateDateTo
	 */
	public void setUpdateDateTo(final java.sql.Timestamp updateDateTo) {
		this.updateDateTo = updateDateTo;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

}
