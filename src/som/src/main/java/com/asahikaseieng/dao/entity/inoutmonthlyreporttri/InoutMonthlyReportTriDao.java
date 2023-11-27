/*
 * Created on Tue Apr 27 09:43:26 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutmonthlyreporttri;
import java.util.List;

/**
 * InoutMonthlyReportTriDaoインターフェース.
 * @author kanri-user
 */
public interface InoutMonthlyReportTriDao {

	/** BEANアノテーション. */
	Class BEAN = InoutMonthlyReportTri.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean InoutMonthlyReportTri
	 * @return Insert件数
	 */
	int insert(InoutMonthlyReportTri bean);

	/**
	 * Update.
	 * @param bean InoutMonthlyReportTri
	 * @return Update件数
	 */
	int update(InoutMonthlyReportTri bean);

	/**
	 * Delete.
	 * @param bean InoutMonthlyReportTri
	 * @return Delete件数
	 */
	int delete(InoutMonthlyReportTri bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param tantoCd tantoCd
	 * @return InoutMonthlyReportTri
	 */
	InoutMonthlyReportTri getEntity(String tantoCd);

	/**
	 * リスト取得.
	 * @return InoutMonthlyReportTriのリスト
	 */
	List<InoutMonthlyReportTri> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

