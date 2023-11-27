/*
 * Created on 2008/11/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.inspsheet;

import java.util.List;


/**
 * 検査成績書マスタDaoクラス
 * @author tosco
 */
public interface InspSheetDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.inspsheet.InspSheet.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 検査成績書一覧取得メソッド
	 * @param condition condition
	 * @return InspSheet
	 */
	List<InspSheet> getList(final InspSheetPagerCondition condition);

	/**
	 * 挿入
	 * @param bean InspSheet
	 * @return Insert件数
	 */
	int insert(InspSheet bean);

	/**
	 * 更新
	 * @param bean InspSheet
	 * @return Update件数
	 */
	int update(InspSheet bean);

	/**
	 * 削除
	 * @param bean InspSheet
	 * @return Delete件数
	 */
	int delete(InspSheet bean);

	/** ARGSアノテーション find(). */
	String find_ARGS = "venderCd,deliveryCd,itemCd";

	/**
	 * エンティティ取得.
	 * @param venderCd venderCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return Remark
	 */
	InspSheet find(final String venderCd, final String deliveryCd, final String itemCd);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "INSP_SHEET_CD";

	/**
	 * エンティティ取得.
	 * @param inspSheetCd 検査成績書コード
	 * @return Carry
	 */
	InspSheet getEntity(String inspSheetCd);

	/** ARGSアノテーション getDetail(). */
	String getDetail_ARGS = "inspSheetCd";
	/**
	 * エンティティ取得.
	 * @param inspSheetCd 検査成績書コード
	 * @return Carry
	 */
	InspSheet getDetail(String inspSheetCd);

}
