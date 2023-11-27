/*
 * Created on Wed Feb 04 16:08:26 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekichogobc;

/**
 * JissekiChogoBcDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiChogoBcDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiChogoBc.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiChogoBc
	 * @return Insert件数
	 */
	int insert(JissekiChogoBc bean);

	/**
	 * Update.
	 * @param bean JissekiChogoBc
	 * @return Update件数
	 */
	int update(JissekiChogoBc bean);

	/**
	 * Delete.
	 * @param bean JissekiChogoBc
	 * @return Delete件数
	 */
	int delete(JissekiChogoBc bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "LOCATION,MODE_FLG,NYUKALOT,STEP,SUBSTEP,TONYUNO";

	/**
	 * エンティティ取得.
	 * @param location location
	 * @param modeFlg modeFlg
	 * @param nyukalot nyukalot
	 * @param step step
	 * @param substep substep
	 * @param tonyuno tonyuno
	 * @return JissekiChogoBc
	 */
	JissekiChogoBc getEntity(String location, String modeFlg, String nyukalot,
			java.math.BigDecimal step, java.math.BigDecimal substep,
			java.math.BigDecimal tonyuno);
}
