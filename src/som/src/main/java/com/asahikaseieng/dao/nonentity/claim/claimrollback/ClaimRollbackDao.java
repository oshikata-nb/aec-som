/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.claimrollback;

import java.sql.Date;
import java.util.List;


/**
 * 請求更新ロールバック用Daoクラス
 * 
 * @author tosco
 */
public interface ClaimRollbackDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollback.class;

	/** ARGSアノテーション getSearch */
	String getCreditDate_ARGS = "venderCd";

	/** ARGSアノテーション getSearchRbList */
	String getSearchRbList_ARGS = "organizationCd,venderCd,cleditDate";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,venderCd,cleditDate";

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,venderCd,cleditDate";

	/**
	 * MAX(請求締め日)取得メソッド
	 * @param venderCd  請求先コード
	 * @return ClaimRollback 請求ヘッダーデータ(MAX(請求締め日)
	 */
	ClaimRollback getCreditDate(final String venderCd);

	/**
	 * 請求ヘッダー(ロールバック対象)検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> ロールバック対象の請求ヘッダーデータ
	 */
	List<ClaimRollback> getSearchRbList(final String organizationCd, final String venderCd, final Date cleditDate);

	/**
	 * 請求ヘッダー(未来締めデータ)検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 請求ヘッダーデータ
	 */
	List<ClaimRollback> getSearchList(final String organizationCd, final String venderCd, final Date cleditDate);

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 検索データ
	 */
	List<ClaimRollback> getSearchEraserList(final String organizationCd, final String venderCd, final Date cleditDate);

}
