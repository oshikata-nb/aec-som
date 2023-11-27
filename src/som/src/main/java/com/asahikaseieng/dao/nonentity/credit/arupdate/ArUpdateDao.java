/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arupdate;

import java.sql.Date;
import java.util.List;


/**
 * 売掛更新用Daoクラス
 * 
 * @author tosco
 */
public interface ArUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arupdate.ArUpdate.class;

	/** ARGSアノテーション getSearch */
	String getSearch_ARGS = "organizationCd";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,cleditDate";

	/**
	 * MAX(売掛締め日)の翌月取得メソッド
	 * @param organizationCd 部署コード
	 * @return ArUpdate 売掛ヘッダーデータ(MAX(売掛締め日の翌年月)
	 */
	ArUpdate getSearch(final String organizationCd);

	/**
	 * 売掛ヘッダー検索メソッド
	 * @param organizationCd    部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArUpdate> 売掛ヘッダーデータ
	 */
	List<ArUpdate> getSearchList(final String organizationCd, final Date cleditDate);

}
