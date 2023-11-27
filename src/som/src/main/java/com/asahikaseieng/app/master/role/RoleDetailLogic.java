/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.master.role.Role;
import com.asahikaseieng.dao.nonentity.master.roledetaillist.RoleDetailList;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロール詳細ロジック interface.
 * @author t0011036
 */
public interface RoleDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param roleId ロールID
	 * @return Role
	 */
	Role getEntity(final BigDecimal roleId);

	/**
	 * 検索処理を行う.
	 * @param roleId ロールID
	 * @return RoleDetail
	 */
	List<RoleDetailList> getList(final String roleId);

	/**
	 * 登録処理を行う.
	 * @param frm 登録データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	void update(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException, NoDataException;

	/**
	 * 追加処理を行う.
	 * @param frm 登録データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void insert(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 削除処理を行う.
	 * @param frm 削除データ
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws NoDataException NoDataException
	 */
	void delete(final RoleDetailForm frm) throws IllegalAccessException,
			InvocationTargetException, NoDataException;
}
