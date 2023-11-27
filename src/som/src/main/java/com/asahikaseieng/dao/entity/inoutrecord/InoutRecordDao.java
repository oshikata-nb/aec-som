/*
 * Created on Thu Jan 22 15:02:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutrecord;

import java.math.BigDecimal;
import java.util.List;

/**
 * InoutRecordDaoインターフェース.
 * @author t0011036
 */
public interface InoutRecordDao {

	/** BEANアノテーション. */
	Class BEAN = InoutRecord.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean InoutRecord
	 * @return Insert件数
	 */
	int insert(InoutRecord bean);

	/**
	 * Update.
	 * @param bean InoutRecord
	 * @return Update件数
	 */
	int update(InoutRecord bean);

	/**
	 * Delete.
	 * @param bean InoutRecord
	 * @return Delete件数
	 */
	int delete(InoutRecord bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "INOUT_NO";

	/**
	 * エンティティ取得.
	 * @param inoutNo inoutNo
	 * @return InoutRecord
	 */
	InoutRecord getEntity(final String inoutNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "INOUT_DIVISION,ODER_NO";

	/**
	 * リスト取得.
	 * @param inoutDivision inoutDivision
	 * @param orderNo orderNo.
	 * @return List<InoutRecord> .
	 */
	List<InoutRecord> getList(final BigDecimal inoutDivision,
			final String orderNo);

}
