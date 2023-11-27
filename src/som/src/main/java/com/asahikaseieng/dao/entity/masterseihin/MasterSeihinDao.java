/*
 * Created on Tue May 12 16:14:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterseihin;
import java.util.List;

/**
 * MasterSeihinDaoインターフェース.
 * @author kanri-user
 */
public interface MasterSeihinDao {

	/** BEANアノテーション. */
	Class BEAN = MasterSeihin.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterSeihin
	 * @return Insert件数
	 */
	int insert(MasterSeihin bean);

	/**
	 * Update.
	 * @param bean MasterSeihin
	 * @return Update件数
	 */
	int update(MasterSeihin bean);

	/**
	 * Delete.
	 * @param bean MasterSeihin
	 * @return Delete件数
	 */
	int delete(MasterSeihin bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEIHINCODE";

	/**
	 * エンティティ取得.
	 * @param seihincode seihincode
	 * @return MasterSeihin
	 */
	MasterSeihin getEntity(String seihincode);

	/**
	 * リスト取得.
	 * @return MasterSeihinのリスト
	 */
	List<MasterSeihin> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

