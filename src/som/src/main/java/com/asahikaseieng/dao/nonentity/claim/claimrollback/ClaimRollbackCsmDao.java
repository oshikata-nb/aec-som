/*
 * Created on 2008/10/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.claimrollback;

import java.sql.Date;
import java.util.List;


/**
 * 請求更新ロールバック用Daoクラス(カスタマイズ)
 * 
 * @author tosco
 */
public interface ClaimRollbackCsmDao extends ClaimRollbackDao {

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,venderCd,cleditDate";

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 検索データ
	 */
	List<ClaimRollback> getSearchEraserList(final String organizationCd, final String venderCd, final Date cleditDate);

}
