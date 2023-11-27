/*
 * Created on 2021/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

public class NumberChkDigit {
	private String unitDivision;
	private String venderDivision;
	private String venderCd;
	private NumberChkDisitDetail checkDetail;
	
	/**
	 * コンストラクタ
	 * @param unitDivision
	 * @param venderDivision
	 * @param venderCd
	 * @param checkDetail
	 */
	public NumberChkDigit( String unitDivision , String venderDivision , String venderCd , NumberChkDisitDetail checkDetail){
		this.unitDivision = unitDivision;
		this.venderDivision = venderDivision;
		this.venderCd = venderCd;
		this.checkDetail = checkDetail;
		
	}
	
	/**
	 * unitDivisionを取得します。
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return unitDivision;
	}
	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}
	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}
	/**
	 * checkDetailを取得します。
	 * @return checkDetail
	 */
	public NumberChkDisitDetail getCheckDetail() {
		return checkDetail;
	}
	
	
}
