/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.sales;

import java.math.BigDecimal;
import java.util.List;

/**
 * 売上－売上区分Daoインターフェース.
 * 
 * @author tosco
 */
public interface SalesCategoryDivisionForComboboxesDao {

	/** BEANアノテーション */
	Class<SalesCategoryDivisionForComboboxes> BEAN = com.asahikaseieng.dao.nonentity.comboboxes.sales.SalesCategoryDivisionForComboboxes.class;

	/**
	 * 売上－売上区分一覧取得
	 * @param arDivision arDivision
	 * @return List<SalesCategoryDivisionForComboboxes> 検索結果リスト
	 */
	List<SalesCategoryDivisionForComboboxes> getCategoryDivisionList(
			final BigDecimal arDivision);

}
