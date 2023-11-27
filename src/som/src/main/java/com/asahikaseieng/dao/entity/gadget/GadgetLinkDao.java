/*
 * Created on 2008/6/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.gadget;

import java.util.List;

/**
 * ガジェットに表示するリンクデータ取得Daoクラス.
 * @author tosco
 */
public interface GadgetLinkDao {

	/** BEANアノテーション. */
	Class BEAN = GadgetLink.class;

	/** ARGSアノテーション getGadgetLinkList */
	String getGadgetLinkList_SQL = "/*$sqlCd*/;";

	/** getGadgetLinkList_ARGS */
	String getGadgetLinkList_ARGS = "sqlCd";

	//
	// インスタンスメソッド
	//

	/**
	 * ガジェットに表示するデータリスト取得.
	 * @param sqlCd SQL文
	 * @return List<GadgetLink> データリスト
	 */
	List<GadgetLink> getGadgetLinkList(final String sqlCd);

}
