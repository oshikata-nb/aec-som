/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimporttemp;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderImportHeadDaoインターフェース.
 * @author 
 */
public interface OrderImportTempDao {

	/** BEANアノテーション. */
	Class<OrderImportTemp> BEAN = OrderImportTemp.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderImportTemp
	 * @return Insert件数
	 */
	int insert(OrderImportTemp bean);

	/**
	 * Update.
	 * @param bean OrderImportTemp
	 * @return Update件数
	 */
	int update(OrderImportTemp bean);

	/**
	 * Delete.
	 * @param bean OrderImportTemp
	 * @return Delete件数
	 */
	int delete(OrderImportTemp bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "VENDER_GROUP_CD,CELL_ROW_NUMBER,CELL_COLUMN_NUMBER";

	/**
	 * エンティティ取得.
	 * @param venderGroupCd String
	 * @param cellRowNumber BigDecimal
	 * @param cellColumnNumber BigDecimal
	 * @return OrderImportTemp
	 */
	OrderImportTemp getEntity(String venderGroupCd, BigDecimal cellRowNumber, BigDecimal cellColumnNumber);
	
	/** ARGSアノテーション getList(). */
	String getList_ARGS = "TEMP_CD, CELL_ROW_NUMBER";

	/**
	 * 行リスト取得.
	 * @param venderGroupCd String
	 * @param cellRowNumber BigDecimal
	 * @return OrderImportTemp
	 */
	List<OrderImportTemp> getRowList(String tempCd, BigDecimal cellRowNumber);
	
	

}
