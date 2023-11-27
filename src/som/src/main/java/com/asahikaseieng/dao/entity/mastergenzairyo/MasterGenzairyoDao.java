/*
 * Created on Wed Feb 04 16:11:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastergenzairyo;

/**
 * MasterGenzairyoDaoインターフェース.
 * @author kanri-user
 */
public interface MasterGenzairyoDao {

	/** BEANアノテーション. */
	Class BEAN = MasterGenzairyo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterGenzairyo
	 * @return Insert件数
	 */
	int insert(MasterGenzairyo bean);

	/**
	 * Update.
	 * @param bean MasterGenzairyo
	 * @return Update件数
	 */
	int update(MasterGenzairyo bean);

	/**
	 * Delete.
	 * @param bean MasterGenzairyo
	 * @return Delete件数
	 */
	int delete(MasterGenzairyo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "GENZAICODE";

	/**
	 * エンティティ取得.
	 * @param genzaicode genzaicode
	 * @return MasterGenzairyo
	 */
	MasterGenzairyo getEntity(String genzaicode);
}
