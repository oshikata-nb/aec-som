/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arrollback;

import java.sql.Date;
import java.util.List;

/**
 * 売掛ロールバック用Daoクラス
 * 
 * @author tosco
 */
public interface ArRollbackDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback.class;

	/** ARGSアノテーション getSearch */
	String getSearch_ARGS = "organizationCd";

	/** ARGSアノテーション getSearchRbList */
	String getSearchRbList_ARGS = "organizationCd,cleditDate";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,cleditDate";

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,cleditDate";
	
	/** ARGSアノテーション getSearchEraserList */
	String getSearchOffsetList_ARGS = "organizationCd,offsetFromDate,offsetToDate";

	/**
	 * MAX(売掛締め日)の年月取得メソッド
	 * @param organizationCd 部署コード
	 * @return ArRollback 売掛ヘッダーデータ(MAX(売掛締め日の年月)
	 */
	ArRollback getSearch(final String organizationCd);

	/**
	 * 売掛ヘッダー(ロールバック対象)検索メソッド
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> ロールバック対象の売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchRbList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 売掛ヘッダー(未来締めデータ)検索メソッド
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	List<ArRollback> getSearchEraserList(final String organizationCd,
			final Date cleditDate);

	/**
	 * グループ間相殺データ(締め未処理)検索メソッド
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	//List<ArRollback> getSearchOffsetList(final String organizationCd,final Date cleditDate);
	List<ArRollback> getSearchOffsetList(final String organizationCd,final Date offsetFromDate,final Date offsetToDate);
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

}
