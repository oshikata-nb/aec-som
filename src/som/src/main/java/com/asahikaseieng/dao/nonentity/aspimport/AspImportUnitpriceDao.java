/*
 * Created on Thu Jan 22 18:21:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.math.BigDecimal;

/**
 * AspImportUnitpriceDaoインターフェース.
 * @author 
 */
public interface AspImportUnitpriceDao {

	/** BEANアノテーション. */
	Class BEAN = AspImportUnitprice.class;

	/** ARGSアノテーション getUnitprice */
	String getUnitprice_ARGS = "itemCd,venderCd,orderQuantity";

	/**
	 *検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return AspImportUnitprice
	 */
	AspImportUnitprice getUnitprice(final String itemCd, final String venderCd,
			final BigDecimal orderQuantity);
}
