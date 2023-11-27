/*
 * Created on 2008/10/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arrollback;

import java.sql.Date;
import java.util.List;


/**
 * 売掛ロールバック用Daoクラス(カスタマイズ)
 * 
 * @author tosco
 */
public interface ArRollbackCsmDao extends ArRollbackDao {

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,cleditDate";

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd    部署コード
	 * @param cleditDate   売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	List<ArRollback> getSearchEraserList(final String organizationCd, final Date cleditDate);

}
