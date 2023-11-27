/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.claimupdate;

import java.sql.Date;
import java.util.List;


/**
 * 請求更新用Daoクラス
 * 
 * @author tosco
 */
public interface ClaimUpdateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate.class;

	/** ARGSアノテーション getCreditDate */
	String getCreditDate_ARGS = "venderCd";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,venderCd,cleditDate";

	/**
	 * MAX(請求締め日)の翌月取得メソッド
	 * @param venderCd 請求先コード
	 * @return ClaimUpdate 請求ヘッダーデータ(MAX(請求締め日の翌月)
	 */
	ClaimUpdate getCreditDate(final String venderCd);

	/**
	 * 請求ヘッダー検索メソッド
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimUpdate> 請求ヘッダーデータ
	 */
	List<ClaimUpdate> getSearchList(final String organizationCd, final String venderCd, final Date cleditDate);

}
