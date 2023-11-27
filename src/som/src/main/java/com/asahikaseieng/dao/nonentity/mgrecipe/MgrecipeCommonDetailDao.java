/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * テンプレート保存先マスタ共通用Daoインターフェース.
 *
 * @author tosco
 */
public interface MgrecipeCommonDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeCommonDetail> BEAN = MgrecipeCommonDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション findByCommonCd(). */
	String findByCommonCd_ARGS = "commonCd";
	/**
	 * テンプレート区分に紐づくデータをすべて取得
	 * @param commonCd テンプレート区分
	 * @return MgrecipeCommonDetail
	 */
	MgrecipeCommonDetail findByCommonCd(String commonCd);

}
