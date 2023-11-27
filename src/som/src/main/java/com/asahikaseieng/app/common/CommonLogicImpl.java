/*
 * Created on 2013/12/09
 * 取引先マスタの入金情報が入力済みかチェックする 20181109 Add
 * $copyright$
 *
 */
package com.asahikaseieng.app.common;

import java.math.BigDecimal;

import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;

/**
 * 共通 ロジック実装クラス
 * @author atts
 */
public class CommonLogicImpl implements CommonLogic {

	private CommonProcDao commonProcDao;

	/**
	 * 表示する課税区分を判定
	 * @param typeDivision 種別
	 * @return　表示判定結果
	 */
	public String getTaxCategoryFlg(String typeDivision){
		// ファンクション　FUN_GET_TAX_CATEGORYを呼び引数を返す
		return commonProcDao.getStringData("1", typeDivision, null, null, null, null, null, null, null, null, null);
	}

	/**
	 * 消費税区分から消費税率を取得する
	 * @param taxCd 消費税コード
	 * @return　消費税率
	 */
	public String getTaxRatio(String taxCd,String ex1,String ex2,String ex3,String ex4){
		// ファンクション　FUN_GET_TAX_CATEGORYを呼び引数を返す
		return commonProcDao.getStringData("2", taxCd, ex1, ex2, ex3, ex4, null, null, null, null, null);
	}

	/**
	 * 売上・仕入のデータから消費税コードを取得する。
	 * @param taxCd 消費税コード
	 * @return　消費税率
	 */
	public String getTaxCd(String salesDate,String itemCd,String venderDivision,String venderCd,String category,BigDecimal taxRatio ,String ex1,String ex2,String ex3,String taxFreeFlg){

		// ファンクション　FUN_GET_TAX_CATEGORYを呼び引数を返す
		return commonProcDao.getStringData("3", salesDate, itemCd, venderDivision,venderCd,category,null,null,null,null,taxFreeFlg);
	}

	/**
	 * 売上・仕入のデータから消費税区分を取得する。
	 * @param taxCd 消費税コード
	 * @return　消費税率
	 */
	public String getTaxDivision(String itemCd,String venderDivision,String venderCd,String ex3,String ex4){

		// ファンクション　FUN_GET_TAX_DIVISIONを呼び引数を返す
		return commonProcDao.getStringData("4", itemCd,venderDivision,venderCd,null,null,null,null,null,null,null);
	}

	/* -------------------- setter -------------------- */

	/**
	 * @param commonProcDao セットする commonProcDao
	 */
	public void setCommonProcDao(CommonProcDao commonProcDao) {
		this.commonProcDao = commonProcDao;
	}

}
