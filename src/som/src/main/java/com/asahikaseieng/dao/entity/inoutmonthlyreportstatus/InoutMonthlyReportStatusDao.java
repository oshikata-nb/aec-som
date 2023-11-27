/*
 * Created on Tue Apr 27 09:42:57 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutmonthlyreportstatus;
import java.util.List;

/**
 * InoutMonthlyReportStatusDaoインターフェース.
 * @author kanri-user
 */
public interface InoutMonthlyReportStatusDao {

	/** BEANアノテーション. */
	Class BEAN = InoutMonthlyReportStatus.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean InoutMonthlyReportStatus
	 * @return Insert件数
	 */
	int insert(InoutMonthlyReportStatus bean);

	/**
	 * Update.
	 * @param bean InoutMonthlyReportStatus
	 * @return Update件数
	 */
	int update(InoutMonthlyReportStatus bean);

	/**
	 * Delete.
	 * @param bean InoutMonthlyReportStatus
	 * @return Delete件数
	 */
	int delete(InoutMonthlyReportStatus bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param tantoCd tantoCd
	 * @return InoutMonthlyReportStatus
	 */
	InoutMonthlyReportStatus getEntity(String tantoCd);

	/**
	 * リスト取得.
	 * @return InoutMonthlyReportStatusのリスト
	 */
	List<InoutMonthlyReportStatus> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

