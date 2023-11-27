/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * チェックデジット結果取得用Condition
 *
 * @author Kiguchi
 */
public class CarryCheckDigitCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	//
	// 入力用.section
	//
	/** 入力：基本バーコード*/
	private String base_str;

	/** チェックデジット開始桁 */
	private BigDecimal bcCheckdigitStr;

	/** チェックデジット終了桁 */
	private BigDecimal bcCheckdigitEnd;

	
	/**
	 * コンストラクタ.
	 */
	public CarryCheckDigitCondition() {
	}

	/**
	 * base_strを取得します。
	 * @return base_str
	 */
	public String getBase_str() {
		return base_str;
	}

	/**
	 * base_strを設定します。
	 * @param base_str base_str
	 */
	public void setBase_str(String base_str) {
		this.base_str = base_str;
	}

	/**
	 * bcCheckdigitStrを取得します。
	 * @return bcCheckdigitStr
	 */
	public BigDecimal getBcCheckdigitStr() {
		return bcCheckdigitStr;
	}

	/**
	 * bcCheckdigitStrを設定します。
	 * @param bcCheckdigitStr bcCheckdigitStr
	 */
	public void setBcCheckdigitStr(BigDecimal bcCheckdigitStr) {
		this.bcCheckdigitStr = bcCheckdigitStr;
	}

	/**
	 * bcCheckdigitEndを取得します。
	 * @return bcCheckdigitEnd
	 */
	public BigDecimal getBcCheckdigitEnd() {
		return bcCheckdigitEnd;
	}

	/**
	 * bcCheckdigitEndを設定します。
	 * @param bcCheckdigitEnd bcCheckdigitEnd
	 */
	public void setBcCheckdigitEnd(BigDecimal bcCheckdigitEnd) {
		this.bcCheckdigitEnd = bcCheckdigitEnd;
	}




}
