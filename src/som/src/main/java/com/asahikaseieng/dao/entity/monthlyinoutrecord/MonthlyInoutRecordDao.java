/*
 * Created on Thu Jan 22 20:07:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.monthlyinoutrecord;
import java.util.List;

/**
 * MonthlyInoutRecordDaoインターフェース.
 * @author t0011036
 */
public interface MonthlyInoutRecordDao {

	/** BEANアノテーション. */
	Class BEAN = MonthlyInoutRecord.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MonthlyInoutRecord
	 * @return Insert件数
	 */
	int insert(MonthlyInoutRecord bean);

	/**
	 * Update.
	 * @param bean MonthlyInoutRecord
	 * @return Update件数
	 */
	int update(MonthlyInoutRecord bean);

	/**
	 * Delete.
	 * @param bean MonthlyInoutRecord
	 * @return Delete件数
	 */
	int delete(MonthlyInoutRecord bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,LOCATION_CD,LOT_NO,TARGET_MONTH";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param locationCd locationCd
	 * @param lotNo lotNo
	 * @param targetMonth targetMonth
	 * @return MonthlyInoutRecord
	 */
	MonthlyInoutRecord getEntity(String itemCd, String locationCd, String lotNo, java.math.BigDecimal targetMonth);

	/**
	 * リスト取得.
	 * @return MonthlyInoutRecordのリスト
	 */
	List<MonthlyInoutRecord> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

