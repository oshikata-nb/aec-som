/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aprollback;

import java.sql.Date;
import java.util.List;


/**
 * 買掛ロールバック用Daoクラス
 * 
 * @author tosco
 */
public interface ApRollbackDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback.class;

	/** ARGSアノテーション getSearch */
	String getSearch_ARGS = "organizationCd";

	/** ARGSアノテーション getSearchRbList */
	String getSearchRbList_ARGS = "organizationCd,payableDate";

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "organizationCd,payableDate";

	/** ARGSアノテーション getSearchEraserList */
	String getSearchEraserList_ARGS = "organizationCd,payableDate";
	
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	/** ARGSアノテーション getSearchOffsetList */
	String getSearchOffsetList_ARGS = "organizationCd,offsetFromDate,offsetToDate";
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

	/**
	 * MAX(買掛締め日)の年月取得メソッド
	 * @param organizationCd   部署コード	 * @return ApRollback 買掛ヘッダーデータ(MAX(買掛締め日の年月)
	 */
	ApRollback getSearch(final String organizationCd);

	/**
	 * 買掛ヘッダー(ロールバック対象)検索メソッド
	 * @param organizationCd    部署コード	 * @param payableDate   買掛締め日
	 * @return List<ApRollback> ロールバック対象の買掛ヘッダーデータ
	 */
	List<ApRollback> getSearchRbList(final String organizationCd, final Date payableDate);

	/**
	 * 買掛ヘッダー(未来締めデータ)検索メソッド
	 * @param organizationCd    部署コード	 * @param payableDate   買掛締め日
	 * @return List<ApRollback> 買掛ヘッダーデータ
	 */
	List<ApRollback> getSearchList(final String organizationCd, final Date payableDate);

	/**
	 * 消込データ検索メソッド
	 * @param organizationCd    部署コード
	 * @param payableDate   買掛締め日
	 * @return List<ApRollback> 検索データ
	 */
	List<ApRollback> getSearchEraserList(final String organizationCd, final Date payableDate);

	/**
	 * グループ間相殺データ(締め未処理)検索メソッド
	 * @param organizationCd    部署コード
	 * @return List<ApRollback> 検索データ
	 * 買掛更新処理に合わせて修正
	 */
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	//List<ApRollback> getSearchOffsetList(final String organizationCd);
	List<ApRollback> getSearchOffsetList(final String organizationCd,final Date offsetFromDate,final Date offsetToDate);
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了
}
