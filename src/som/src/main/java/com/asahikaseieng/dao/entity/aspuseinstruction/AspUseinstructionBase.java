/*
 * Created on Tue Apr 21 10:54:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspuseinstruction;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AspUseinstructionBaseクラス.
 * @author kanri-user
 */
public class AspUseinstructionBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AspUseinstructionBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ASP_USEINSTRUCTION";



	/** COLUMNアノテーション orderCd. */
	public static final String orderCd_COLUMN = "ORDER_CD";

	/** COLUMNアノテーション operationCd. */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション resouceCd. */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション work. */
	public static final String work_COLUMN = "WORK";

	/** COLUMNアノテーション timeArray. */
	public static final String timeArray_COLUMN = "TIME_ARRAY";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String orderCd;

	private String operationCd;

	private String resouceCd;

	private String work;

	private String timeArray;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * orderCd取得.
	 * @return orderCd
	 */
	public String getOrderCd() {
		return this.orderCd;
	}

	/**
	 * orderCd設定.
	 * @param orderCd orderCd
	 */
	public void setOrderCd(final String orderCd) {
		this.orderCd = orderCd;
	}

	/**
	 * operationCd取得.
	 * @return operationCd
	 */
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * operationCd設定.
	 * @param operationCd operationCd
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * resouceCd取得.
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * resouceCd設定.
	 * @param resouceCd resouceCd
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * work取得.
	 * @return work
	 */
	public String getWork() {
		return this.work;
	}

	/**
	 * work設定.
	 * @param work work
	 */
	public void setWork(final String work) {
		this.work = work;
	}

	/**
	 * timeArray取得.
	 * @return timeArray
	 */
	public String getTimeArray() {
		return this.timeArray;
	}

	/**
	 * timeArray設定.
	 * @param timeArray timeArray
	 */
	public void setTimeArray(final String timeArray) {
		this.timeArray = timeArray;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
