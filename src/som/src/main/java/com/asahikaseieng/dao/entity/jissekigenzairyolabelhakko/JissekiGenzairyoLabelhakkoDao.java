/*
 * Created on Wed Feb 04 16:08:52 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyolabelhakko;

/**
 * JissekiGenzairyoLabelhakkoDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiGenzairyoLabelhakkoDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiGenzairyoLabelhakko.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiGenzairyoLabelhakko
	 * @return Insert件数
	 */
	int insert(JissekiGenzairyoLabelhakko bean);

	/**
	 * Update.
	 * @param bean JissekiGenzairyoLabelhakko
	 * @return Update件数
	 */
	int update(JissekiGenzairyoLabelhakko bean);

	/**
	 * Delete.
	 * @param bean JissekiGenzairyoLabelhakko
	 * @return Delete件数
	 */
	int delete(JissekiGenzairyoLabelhakko bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NYUKALOT,RENBAN";

	/**
	 * エンティティ取得.
	 * @param nyukalot nyukalot
	 * @param renban renban
	 * @return JissekiGenzairyoLabelhakko
	 */
	JissekiGenzairyoLabelhakko getEntity(String nyukalot,
			java.math.BigDecimal renban);
}
