/*
 * Created on Wed Feb 04 16:09:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseizodetail;

/**
 * JissekiSeizoDetailDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiSeizoDetailDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiSeizoDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiSeizoDetail
	 * @return Insert件数
	 */
	int insert(JissekiSeizoDetail bean);

	/**
	 * Update.
	 * @param bean JissekiSeizoDetail
	 * @return Update件数
	 */
	int update(JissekiSeizoDetail bean);

	/**
	 * Delete.
	 * @param bean JissekiSeizoDetail
	 * @return Delete件数
	 */
	int delete(JissekiSeizoDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEIZOSASHIZUNO,STEP,SUBSTEP";

	/**
	 * エンティティ取得.
	 * @param seizosashizuno seizosashizuno
	 * @param step step
	 * @param substep substep
	 * @return JissekiSeizoDetail
	 */
	JissekiSeizoDetail getEntity(String seizosashizuno,
			java.math.BigDecimal step, java.math.BigDecimal substep);
}
