/*
 * Created on 2013/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.commonproc;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * CommonUnitpriceBeanクラス.
 * 
 * @author ATTS
 */
public class CommonProcBase {

	/**
	 * コンストラクタ.
	 */
	public CommonProcBase() {

	}
	/** COLUMNアノテーション 数値項目用の戻り値 */
	public static final String retDedcimal_COLUMN = "RET_DECIMAL";
	
	/** COLUMNアノテーション 文字列項目用の戻り値 */
	public static final String retString_COLUMN = "RET_STRING";
	
	/** COLUMNアノテーション 日付項目用の戻り値 */
	public static final String retDate_COLUMN = "RET_DATE";
	
	BigDecimal retDedcimal;		// 数値項目

	String retString;			// 文字列項目
	
	Timestamp retDate;			// 日付項目

	/**
	 * @return retDedcimal
	 */
	public BigDecimal getRetDedcimal() {
		return retDedcimal;
	}

	/**
	 * @param retDedcimal セットする retDedcimal
	 */
	public void setRetDedcimal(BigDecimal retDedcimal) {
		this.retDedcimal = retDedcimal;
	}

	/**
	 * @return retString
	 */
	public String getRetString() {
		return retString;
	}

	/**
	 * @param retString セットする retString
	 */
	public void setRetString(String retString) {
		this.retString = retString;
	}

	/**
	 * @return retDate
	 */
	public Timestamp getRetDate() {
		return retDate;
	}

	/**
	 * @param retDate セットする retDate
	 */
	public void setRetDate(Timestamp retDate) {
		this.retDate = retDate;
	}

	//
	// 定数
	//
	
}
