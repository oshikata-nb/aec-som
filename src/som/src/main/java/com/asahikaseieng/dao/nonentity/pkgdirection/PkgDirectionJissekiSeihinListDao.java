/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.util.List;

/**
 * 包装指図－製品入出庫実績Daoインターフェース.
 *
 * @author tosco 
 */
public interface PkgDirectionJissekiSeihinListDao {

	/** BEANアノテーション. */
	Class BEAN = PkgDirectionJissekiSeihinList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "pkgDirectionNo,itemCd";

	/**
	 * 包装指図番号、品目コードに紐づくデータをすべて取得.
	 * @param pkgDirection 包装指図番号
	 * @param itemCd 品目コード
	 * @return List<PkgDirectionJissekiSeihinList>
	 * 
	 */
	List<PkgDirectionJissekiSeihinList> getList(String pkgDirection, String itemCd);
}
