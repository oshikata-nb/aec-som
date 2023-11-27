/*
 * Created on 2013/12/09
 * 取引先マスタの入金情報が入力済みかチェックする 20181109 Add
 * $copyright$
 *
 */
package com.asahikaseieng.app.common;

import java.math.BigDecimal;


/**
 * ロジッククラス interface. 共通ロジック
 */
public interface CommonLogic {
	/**
	 * 表示する課税区分を判定
	 * @param typeDivision　種別
	 * @return SALES:売上課税区分　PURCHASE:仕入課税区分　BOTH:両方
	 */
	public String getTaxCategoryFlg(String typeDivision);

	/**
	 * 消費税コードから消費税率を取得する
	 * @param taxCd 消費税コード
	 * @return　消費税率
	 */
	public String getTaxRatio(String taxCd,String ex1,String ex2,String ex3,String ex4);

	/**
	 *
	 * 売上・仕入のデータから消費税コードを取得する。
	 * @param salesDate
	 * @param itemCd
	 * @param venderDivision
	 * @param venderCd
	 * @param category
	 * @param taxRatio
	 * @param ex1
	 * @param ex2
	 * @param ex3
	 * @param taxFreeFlg
	 * @return
	 */
	public String getTaxCd(String salesDate,String itemCd,String venderDivision,String venderCd,String category,BigDecimal taxRatio ,String ex1,String ex2,String ex3,String taxFreeFlg);

	/**
	 *
	 * 品目と取引先から消費税区分を取得する
	 * @param itemCd
	 * @param venderDivision
	 * @param venderCd
	 * @param ex3
	 * @param ex4
	 * @return
	 */
	public String getTaxDivision(String itemCd,String venderDivision,String venderCd,String ex3,String ex4);

}