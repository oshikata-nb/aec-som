/*
 * Created on Thu Jan 22 18:21:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailunitprice;

import java.math.BigDecimal;

/**
 * UnitpriceDaoインターフェース.
 * @author kanri-user
 */
public interface OrderDetailUnitpriceDao {

	/** BEANアノテーション. */
	Class BEAN = OrderDetailUnitprice.class;

	/** ARGSアノテーション getOrderQuantityRelatedData */
	String getOrderQuantityRelatedData_ARGS = "itemCd,venderCd,orderQuantity";
	/**
	 *検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return OrderDetailUnitprice
	 */
	OrderDetailUnitprice getOrderQuantityRelatedData(final String itemCd, final String venderCd,
			final BigDecimal orderQuantity);
}
