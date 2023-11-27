/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.util.List;

/**
 * 
 * OffsetTranDataDao．グループ間相殺トランザクション
 * @author tosco
 */
public interface OffsetTranDataDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offset.OffsetTranData.class;

	//
	// インスタンスメソッド
	//
	/**
	 * Insert.
	 * @param bean Unitprice
	 * @return Insert件数
	 */
	int insert(OffsetTranData bean);

	/**
	 * update.
	 * @param bean Unitprice
	 * @return update件数
	 */
	int update(OffsetTranData bean);

	/**
	 * delete.
	 * @param bean Unitprice
	 * @return delete件数
	 */
	int delete(OffsetTranData bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_NO,VENDER_DIVISION,VENDER_CD";

	/**
	 * エンティティ取得.
	 * @param offsetNo 相殺番号
	 * @param venderDivision 取引先区分
	 * @param venderCd 請求先／支払先コード
	 * @return OffsetTranData グループ間相殺トランザクション
	 */
	OffsetTranData getEntity(String offsetNo, String venderDivision,
			String venderCd);

	/** ARGSアノテーション getDetailData(). */
	String getDetailData_ARGS = "offsetNo";

	/**
	 * 相殺トランザクションリスト取得.
	 * @param offsetNo 相殺番号
	 * @return List<OffsetTranData> グループ間相殺トランザクションリスト
	 */
	List<OffsetTranData> getDetailData(String offsetNo);

	/** ARGSアノテーション getVenderOrganization(). */
	String getVenderOrganization_ARGS = "venderDivision, venderCd";

	/**
	 * 取引先部署取得
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return OffsetTranData
	 */
	OffsetTranData getVenderOrganization(String venderDivision, String venderCd);
}
