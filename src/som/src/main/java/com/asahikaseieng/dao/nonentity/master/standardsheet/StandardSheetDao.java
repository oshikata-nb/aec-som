/*
 * Created on 2008/11/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.standardsheet;

import java.util.List;

/**
 * 規格書マスタDaoクラス
 * @author tosco
 */
public interface StandardSheetDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.standardsheet.StandardSheet.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 規格書一覧取得メソッド
	 * @param condition condition
	 * @return StandardSheet
	 */
	List<StandardSheet> getList(final StandardSheetPagerCondition condition);

	/**
	 * 挿入
	 * @param bean StandardSheet
	 * @return Insert件数
	 */
	int insert(StandardSheet bean);

	/**
	 * 更新
	 * @param bean StandardSheet
	 * @return Update件数
	 */
	int update(StandardSheet bean);

	/**
	 * 削除
	 * @param bean StandardSheet
	 * @return Delete件数
	 */
	int delete(StandardSheet bean);

	/** ARGSアノテーション find(). */
	String find_ARGS = "venderCd,deliveryCd,itemCd";

	/**
	 * エンティティ取得.
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return Remark
	 */
	StandardSheet find(final String venderCd, final String deliveryCd,
			final String itemCd);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "STD_SHEET_CD";

	/**
	 * エンティティ取得.
	 * @param stdSheetCd 規格書コード
	 * @return Carry
	 */
	StandardSheet getEntity(String stdSheetCd);

	/** ARGSアノテーション getDetail(). */
	String getDetail_ARGS = "stdSheetCd";

	/**
	 * エンティティ取得.
	 * @param stdSheetCd 規格書コード
	 * @return Carry
	 */
	StandardSheet getDetail(String stdSheetCd);

}
