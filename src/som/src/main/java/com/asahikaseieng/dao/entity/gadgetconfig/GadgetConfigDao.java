/*
 * Created on Mon Apr 09 13:53:49 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadgetconfig;

import java.util.List;

/**
 * GadgetConfigDaoインターフェース.
 * @author jbd
 */
public interface GadgetConfigDao {

	/** BEANアノテーション. */
	Class BEAN = GadgetConfig.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean GadgetConfig
	 * @return Insert件数
	 */
	int insert(GadgetConfig bean);

	//
	// 追加メソッドはこの下に記述して下さい
	//

	/** ARGSアノテーション getList(). */
	String getListByTantoCd_ARGS = "TANTO_CD";

	/** QUERYアノテーション getList(). */
	String getListByTantoCd_QUERY = "order by LANE_NO,VERTICAL_ORDER";

	/**
	 * リスト取得.
	 * @param tantoCd tantoCd
	 * @return GadgetConfigのリスト
	 */
	List<GadgetConfig> getListByTantoCd(final String tantoCd);

	/** ARGSアノテーション getList(). */
	String deleteByTantoCd_ARGS = "tantoCd";

	/**
	 * 指定した担当者の情報を削除する
	 * @param tantoCd String
	 */
	void deleteByTantoCd(final String tantoCd);
}
