/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.belongrole.BelongRole;
import com.asahikaseieng.dao.nonentity.master.belongroledetail.BelongRoleDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.dao.nonentity.master.roledetail.RoleDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属・ロール組合せ詳細ロジック interface.
 * @author t0011036
 */
public interface BelongRoleDetailLogic {
	/**
	 * 部署検索処理を行う.
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getOrganizationEntity(final String organizationCd);

	/**
	 * 役職検索処理を行う.
	 * @param postId 役職コード
	 * @return PostDetail
	 */
	PostDetail getPostEntity(final String postId);

	/**
	 * ロール検索処理を行う.
	 * @param roleId ロールID
	 * @return RoleDetail
	 */
	RoleDetail getRoleEntity(final String roleId);

	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return BelongRole
	 */
	BelongRole getEntity(final String organizationCd, final BigDecimal postId);

	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return BelongRoleDetail
	 */
	BelongRoleDetail getDetailEntity(final String organizationCd,
			final String postId);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final BelongRole bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final BelongRole bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final BelongRole bean) throws NoDataException;
}
