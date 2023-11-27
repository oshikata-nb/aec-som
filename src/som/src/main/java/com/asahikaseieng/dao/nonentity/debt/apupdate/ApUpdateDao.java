/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apupdate;

import java.sql.Date;
import java.util.List;


/**
 * 買掛更新用Daoクラス
 * 
 * @author tosco
 */
public interface ApUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apupdate.ApUpdate.class;

	/** ARGSアノテーション getSearch */
	String getSearch_ARGS = "organizationCd";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,payableDate";

	/**
	 * MAX(買掛締め日)の翌月取得メソッド
	 * @param organizationCd 部署コード
	 * @return ApUpdate 買掛ヘッダーデータ(MAX(買掛締め日の翌年月)
	 */
	ApUpdate getSearch(final String organizationCd);

	/**
	 * 買掛ヘッダー検索メソッド
	 * @param organizationCd    部署コード
	 * @param payableDate 買掛締め日
	 * @return List<ApUpdate> 買掛ヘッダーデータ
	 */
	List<ApUpdate> getSearchList(final String organizationCd, final Date payableDate);

}
