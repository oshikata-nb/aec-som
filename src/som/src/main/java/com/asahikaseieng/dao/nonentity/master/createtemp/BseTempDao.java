/*
 * Created on 2008/09/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.createtemp;


/**
 * BSETemp（原処方情報からの取得）Daoインターフェース.
 * @author hongyo
 */
public interface BseTempDao {

	/** BEANアノテーション. */
	Class BEAN = BseTemp.class;

	/**
	 * エンティティ取得.
	 * @return BseTemp
	 */
	BseTemp getEntity();

}
