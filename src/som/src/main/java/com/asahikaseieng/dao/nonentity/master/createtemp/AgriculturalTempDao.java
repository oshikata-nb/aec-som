/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;


/**
 * 残留農薬Temp（原処方情報からの取得）Daoインターフェース.
 * @author hongyo
 */
public interface AgriculturalTempDao {

	/** BEANアノテーション. */
	Class BEAN = AgriculturalTemp.class;

	/**
	 * エンティティ取得.
	 * @return AgriculturalTemp
	 */
	AgriculturalTemp getEntity();

}
