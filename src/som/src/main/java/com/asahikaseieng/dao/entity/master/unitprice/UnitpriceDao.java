/*
 * Created on Thu Jan 22 18:21:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.unitprice;

/**
 * UnitpriceDaoインターフェース.
 * @author kanri-user
 */
public interface UnitpriceDao {

	/** BEANアノテーション. */
	Class BEAN = Unitprice.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Unitprice
	 * @return Insert件数
	 */
	int insert(Unitprice bean);

	/**
	 * Update.
	 * @param bean Unitprice
	 * @return Update件数
	 */
	int update(Unitprice bean);

	/**
	 * Delete.
	 * @param bean Unitprice
	 * @return Delete件数
	 */
	int delete(Unitprice bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "VENDER_DIVISION,VENDER_CD,ITEM_CD,VERSION,CONSECUTIVE_NO";

	/**
	 * エンティティ取得.
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @param itemCd itemCd
	 * @param version version
	 * @param consecutiveNo consecutiveNo
	 * @return Unitprice
	 */
	Unitprice getEntity(String venderDivision, String venderCd, String itemCd,
			java.math.BigDecimal version, java.math.BigDecimal consecutiveNo);
}
