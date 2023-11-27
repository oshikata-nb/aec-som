/*
 * Created on Wed Feb 04 16:10:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sijiseizo;

/**
 * SijiSeizoDaoインターフェース.
 * @author kanri-user
 */
public interface SijiSeizoDao {

	/** BEANアノテーション. */
	Class BEAN = SijiSeizo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SijiSeizo
	 * @return Insert件数
	 */
	int insert(SijiSeizo bean);

	/**
	 * Update.
	 * @param bean SijiSeizo
	 * @return Update件数
	 */
	int update(SijiSeizo bean);

	/**
	 * Delete.
	 * @param bean SijiSeizo
	 * @return Delete件数
	 */
	int delete(SijiSeizo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEIZOSASHIZUNO,STEP,SUBSTEP";

	/**
	 * エンティティ取得.
	 * @param seizosashizuno seizosashizuno
	 * @param step step
	 * @param substep substep
	 * @return SijiSeizo
	 */
	SijiSeizo getEntity(String seizosashizuno, java.math.BigDecimal step,
			java.math.BigDecimal substep);
}
