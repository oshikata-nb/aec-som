/*
 * Created on Mon Feb 02 11:37:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.label;

/**
 * LabelDaoインターフェース.
 * @author t0011036
 */
public interface LabelDao {

	/** BEANアノテーション. */
	Class BEAN = Label.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Label
	 * @return Insert件数
	 */
	int insert(Label bean);

	/**
	 * Update.
	 * @param bean Label
	 * @return Update件数
	 */
	int update(Label bean);

	/**
	 * Delete.
	 * @param bean Label
	 * @return Delete件数
	 */
	int delete(Label bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COMMON_CD,LABEL_CD";

	/**
	 * エンティティ取得.
	 * @param commonCd commonCd
	 * @param labelCd labelCd
	 * @return Label
	 */
	Label getEntity(String commonCd, String labelCd);
}
