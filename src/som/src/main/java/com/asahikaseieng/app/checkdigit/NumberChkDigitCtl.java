/*
 * Created on 2021/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

// チェックディジットの管理クラス
public class NumberChkDigitCtl {

	private List<NumberChkDigit> numberChkDigitList = new ArrayList<NumberChkDigit>();
	
	/**
	 * 数値チェックディジットを取得する
	 * @param checkDigitUtil
	 * @param unitDivision
	 * @param venderDivision
	 * @param venderCd
	 * @return
	 */
	public NumberChkDisitDetail getCheckDigitDetail( CheckDigitUtilsLogic checkDigitUtil, String unitDivision, String venderDivision, String venderCd){
		NumberChkDisitDetail ret = null;
		
		// 過去に取得したことのあるチェックディジットを取得
		for ( NumberChkDigit numberChkDigit  : this.numberChkDigitList) {
			if( ( ( numberChkDigit.getUnitDivision() == null && unitDivision == null ) || ( numberChkDigit.getUnitDivision() != null && numberChkDigit.getUnitDivision().equals(unitDivision) ) )
					&& ( ( numberChkDigit.getVenderDivision() == null && venderDivision == null ) || ( numberChkDigit.getVenderDivision() != null && numberChkDigit.getVenderDivision().equals(venderDivision) ) )
					&& ( ( numberChkDigit.getVenderCd() == null && venderCd == null ) || ( numberChkDigit.getVenderDivision() != null && numberChkDigit.getVenderCd().equals(venderCd) ) )){
				ret = numberChkDigit.getCheckDetail();
			}
		}
		
		// 対象が見つからなかった時、新規作成してリストに追加
		if( ret == null ){
			ret = checkDigitUtil.getCheckDigit(unitDivision, venderDivision, venderCd);
			NumberChkDigit numberChkDigit = new NumberChkDigit(unitDivision , venderDivision , venderCd , ret);
			this.numberChkDigitList.add(numberChkDigit);
		}
		
		return ret;
	}
	
	/**
	 * 
	 * 取得した履歴の消去
	 */
	public void clear(){
		this.numberChkDigitList.clear();
	}
	
}
