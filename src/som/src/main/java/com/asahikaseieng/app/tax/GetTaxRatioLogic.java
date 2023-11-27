/*
 * Created on 2014/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.tax;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;

/**
 * 消費税率取得 ロジッククラス interface.
 * @author t1344224
 */
public interface GetTaxRatioLogic {

	/**
	 * 帳合コードと品目コードから有効な有効単価を取得
	 *
	 * @param date 日付
	 * @return VaridUnitprice 有効単価
	 */
	String getTaxRatio(String date);

	/**
	 * 税コードを取得
	 * 軽減税率対応20190823
	 *
	 * @param
	 * @param
	 * @param
	 * @param
	 * @param
	 *
	 * @return TaxCd 税コード
	 */
	public String getTaxCd(final String date,final String itemCd,final String venderDivision
			,final String venderCd,final String category,final String taxRatio, final String taxFreeFlg);

	/**
	 * 税コードから消費税率を取得
	 * 軽減税率対応20190823
	 *
	 * @param taxCd　税コード
	 * @return TaxRatio　消費税率
	 */
	BigDecimal getTaxRatioFromTaxCd(final String taxCd);

	/**
	 * 消費税用コンボボックスを作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createTaxRatioCombobox();

	/**
	 * 消費税区分を取得
	 * @param itemCd itemCd
	 * @param division 区分 0:売上 1:仕入
	 * @return List<ComboBoxItems>
	 */
	String getTaxDivisionFromItem(String itemCd, String division);

	/**
	 * 日付と消費税率から有効な単価であるか確認する
	 * @param date date
	 * @param taxRatio taxRatio
	 * @return List<ComboBoxItems>
	 */
	boolean isValidTax(final String date, final String taxRatio);
}
