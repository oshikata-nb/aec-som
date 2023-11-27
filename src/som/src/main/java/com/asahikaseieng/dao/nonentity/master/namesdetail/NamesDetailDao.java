/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.namesdetail;

/**
 * NamesDetailDaoクラス
 * @author t0011036
 */
public interface NamesDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "nameDivision, nameCd";

	/**
	 * NamesDetailメソッド
	 *
	 * @param nameDivision nameDivision
	 * @param nameCd nameCd
	 * @return NamesDetail
	 */
	NamesDetail getEntity(final String nameDivision, final String nameCd);


	/** ARGSアノテーション getTaxFreeRatio(). */
	String getTaxFreeRatio_ARGS = "name01";

	/**
	 * 免税措置対象割合一覧取得処理.
	 *
	 * @param name01
	 *            name01
	 * @return Namesのリスト
	 */
	NamesDetail getTaxFreeRatio(String name01);
}
