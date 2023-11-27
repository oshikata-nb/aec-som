/*
 * Created on Wed Apr 14 10:59:16 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tempinoutmonthlyreport;
import java.util.List;

/**
 * TempInoutMonthlyReportDaoインターフェース.
 * @author kanri-user
 */
public interface TempInoutMonthlyReportDao {

	/** BEANアノテーション. */
	Class BEAN = TempInoutMonthlyReport.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TempInoutMonthlyReport
	 * @return Insert件数
	 */
	int insert(TempInoutMonthlyReport bean);

	/**
	 * Update.
	 * @param bean TempInoutMonthlyReport
	 * @return Update件数
	 */
	int update(TempInoutMonthlyReport bean);

	/**
	 * Delete.
	 * @param bean TempInoutMonthlyReport
	 * @return Delete件数
	 */
	int delete(TempInoutMonthlyReport bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROW_NO";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @return TempInoutMonthlyReport
	 */
	TempInoutMonthlyReport getEntity(java.math.BigDecimal rowNo);

	/**
	 * リスト取得.
	 * @return TempInoutMonthlyReportのリスト
	 */
	List<TempInoutMonthlyReport> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

