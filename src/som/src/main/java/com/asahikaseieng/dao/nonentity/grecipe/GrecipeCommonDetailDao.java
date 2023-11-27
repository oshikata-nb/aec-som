/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

/**
 * テンプレート保存先マスタ共通用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeCommonDetailDao {

	/** BEANアノテーション */
	Class<GrecipeCommonDetail> BEAN = GrecipeCommonDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション findByCommonCd(). */
	String findByCommonCd_ARGS = "commonCd";
	/**
	 * テンプレート区分に紐づくデータをすべて取得
	 * @param commonCd テンプレート区分
	 * @return GrecipeCommonDetail
	 */
	GrecipeCommonDetail findByCommonCd(String commonCd);

}
