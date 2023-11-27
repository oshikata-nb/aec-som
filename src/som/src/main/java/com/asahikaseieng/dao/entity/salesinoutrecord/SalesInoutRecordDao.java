/*
 * Created on Tue May 19 19:18:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesinoutrecord;

/**
 * SalesInoutRecordDaoインターフェース.
 * @author kanri-user
 */
public interface SalesInoutRecordDao {

	/** BEANアノテーション. */
	Class BEAN = SalesInoutRecord.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SalesInoutRecord
	 * @return Insert件数
	 */
	int insert(SalesInoutRecord bean);

	/**
	 * Update.
	 * @param bean SalesInoutRecord
	 * @return Update件数
	 */
	int update(SalesInoutRecord bean);

	/**
	 * Delete.
	 * @param bean SalesInoutRecord
	 * @return Delete件数
	 */
	int delete(SalesInoutRecord bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SALES_NO,SALES_ROW_NO";

	/**
	 * エンティティ取得.
	 * @param salesNo salesNo
	 * @param salesRowNo salesRowNo
	 * @return SalesInoutRecord
	 */
	SalesInoutRecord getEntity(String salesNo, java.math.BigDecimal salesRowNo);
}
