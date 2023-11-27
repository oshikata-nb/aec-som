/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;


/**
 * 消防法Temp（原処方情報からの取得）Daoインターフェース.
 * @author hongyo
 */
public interface SyobohoTempDao {

	/** BEANアノテーション. */
	Class BEAN = SyobohoTemp.class;


	/**
	 * エンティティ取得.
	 * @return SyobohoTemp
	 */
	SyobohoTemp getEntity();

}
