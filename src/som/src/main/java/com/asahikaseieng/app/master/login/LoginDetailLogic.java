/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.tantorole.TantoRole;
import com.asahikaseieng.dao.nonentity.master.loginallrolelist.LoginAllRoleList;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.loginrolelist.LoginRoleList;
import com.asahikaseieng.dao.nonentity.master.tantorolealldelete.TantoRoleAllDelete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 担当者マスタ詳細 ロジッククラス interface.
 * @author t0011036
 */
public interface LoginDetailLogic {
	/**
	 * 全ロール一覧取得.
	 * @return List<LoginAllRoleList>
	 */
	List<LoginAllRoleList> getLoginAllRoleList();

	/**
	 * 担当者ロール一覧取得.
	 * @param tantoCd 担当者コード
	 * @return List<LoginRoleList>
	 */
	List<LoginRoleList> getLoginRoleList(final String tantoCd);

	/**
	 * 検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return Login
	 */
	Login getEntity(final String tantoCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getDetailEntity(final String tantoCd);

	/**
	 * 担当者登録処理を行う.
	 * @param frm 更新対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void regist(final LoginDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void updateLogin(final Login bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insertLogin(final Login bean);

	/**
	 * 削除処理を行う.
	 * @param frm 削除データ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void delete(final LoginDetailForm frm, final String tantoCd)
			throws NoDataException, IllegalAccessException,
			InvocationTargetException;

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void deleteLogin(final Login bean) throws NoDataException;

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insertTantoRole(final TantoRole bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 */
	void deleteTantoRole(final TantoRoleAllDelete bean);
}
