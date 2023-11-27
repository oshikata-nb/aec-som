/*
 * Created on 2007/11/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.util.List;

/**
 * 開発依頼詳細DAOクラス
 * @author FPC
 */
public interface DevelopDao {

	/** BEANアノテーション. */
	Class BEAN = Develop.class;

	/** ARGSアノテーション getDevelopHeader */
	String getDevelopHeader_ARGS = "developmentRequestNo";

	/**
	 * DevelopHeaderメソッド
	 *
     * @param developmentRequestNo	developmentRequestNo
	 * @return DevelopHeader
	 */
	Develop getDevelopHeader(final String developmentRequestNo);

	/** ARGSアノテーション getDevelopDetail */
	String getDevelopDetail_ARGS = "developmentRequestNo";

	/**
	 * DevelopDetailメソッド
	 *
     * @param developmentRequestNo	developmentRequestNo
	 * @return DevelopDetail
	 */
	Develop getDevelopDetail(final String developmentRequestNo);

	/** ARGSアノテーション getDevelopContent */
	String getDevelopContent_ARGS = "developmentRequestNo";

	/**
	 * DevelopContentメソッド
	 *
     * @param developmentRequestNo	developmentRequestNo
	 * @return DevelopContent
	 */
	Develop getDevelopContent(final String developmentRequestNo);

	/** ARGSアノテーション getDevelopContent */
	String getDevelopmentRequest_ARGS = "developmentRequestNo";

	/**
	 * getDevelopmentRequestメソッド
	 *
     * @param developmentRequestNo	developmentRequestNo
	 * @return getDevelopmentRequest
	 */
	Develop getDevelopmentRequest(final String developmentRequestNo);

	/** ARGSアノテーション getCountVender */
	String getCountVender_ARGS = "venderCd";

	/**
	 * 依頼元コードのマスターチェック
	 * @param venderCd 依頼元コード
	 * @return int 0,1
	 */
	int getCountVender(final String venderCd);

	/** ARGSアノテーション getCountUser */
	String getCountUser_ARGS = "tantoCd";

	/**
	 * 担当コードのマスターチェック
	 * @param tantoCd 担当コード
	 * @return int 0,1
	 */
	int getCountUser(final String tantoCd);

	/**
	 * getMaxDeveloprequestNo
	 * @return BigDecimal BigDecimal
	 */
	java.math.BigDecimal getMaxDeveloprequestNo();


	/** ARGSアノテーション getListForItemRelation */
	String getListForItemRelation_ARGS = "itemCd";

	/**
	 * getListForItemRelationメソッド
	 * @param itemCd itemCd
	 * @return List<Develop>
	 */
	List<Develop> getListForItemRelation(final String itemCd);

}
