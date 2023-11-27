/*
 * Created on 2008/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.check;



/**
 * PasswordCheckDaoクラス
 * @author tosco
 */
public interface PasswordCheckDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.check.PasswordCheck.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "tantoCd";

	/**
	 * 自社マスタ検索メソッド
	 * 
	 * @return PasswordCheck
	 */
	PasswordCheck getEntity();

	/**
	 * ログインユーザ定義マスタ更新処理.
	 * @param tantoCd 担当者コード
	 * @return 更新件数
	 */
	int updateLogin(String tantoCd);

}
