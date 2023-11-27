/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesCategoryDivisionForComboboxes;

/**
 * 売上共通 ロジッククラス interface.
 * @author tosco
 */
public interface SalesCommonsLogic {

	/**
	 * 売上区分一覧取得
	 * @param arDivision arDivision
	 * @return List<SalesCategoryDivisionForComboboxes>
	 */
	List<SalesCategoryDivisionForComboboxes> getCategoryDivisionList(
			final BigDecimal arDivision);

	/**
	 * 売上区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCategoryDivisionCombobox();

	/**
	 * 売上区分コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCategoryDivisionAllCombobox();

	/**
	 * 売上区分コンボボックス作成、仕訳反転関連項目取得
	 * @param frm 売上詳細画面フォーム
	 */
	void createCategoryDivisionComboboxRelated(final SalesDetailStandardForm frm);

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

	/**
	 * 得意先コードから請求先コードを取得
	 * @param venderCd 得意先コード
	 * @return ArDivision ArDivision
	 */
	BigDecimal getArDivision(final String venderCd);

}
