/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.belongrole.BelongRole;
import com.asahikaseieng.dao.entity.master.belongrole.BelongRoleDao;
import com.asahikaseieng.dao.nonentity.master.belongroledetail.BelongRoleDetail;
import com.asahikaseieng.dao.nonentity.master.belongroledetail.BelongRoleDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetailDao;
import com.asahikaseieng.dao.nonentity.master.roledetail.RoleDetail;
import com.asahikaseieng.dao.nonentity.master.roledetail.RoleDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 所属・ロール組合せ詳細ロジック 実装クラス.
 * @author t0011036
 */
public class BelongRoleDetailLogicImpl implements BelongRoleDetailLogic {

	private BelongRoleDao belongRoleDao;

	private BelongRoleDetailDao belongRoleDetailDao;

	private OrganizationDetailDao organizationDetailDao;

	private PostDetailDao postDetailDao;

	private RoleDetailDao roleDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleDetailLogicImpl() {
	}

	/**
	 * 部署検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getOrganizationEntity(final String organizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(organizationCd);
		return bean;
	}

	/**
	 * 役職検索
	 * @param postId 役職コード
	 * @return PostDetail
	 */
	public PostDetail getPostEntity(final String postId) {
		PostDetail bean = postDetailDao.getEntity(postId);
		return bean;
	}

	/**
	 * ロール検索
	 * @param roleId ロールID
	 * @return RoleDetail
	 */
	public RoleDetail getRoleEntity(final String roleId) {
		RoleDetail bean = roleDetailDao.getEntity(roleId);
		return bean;
	}

	/**
	 * 所属・ロール組合せ検索（登録用）
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return BelongRole
	 */
	public BelongRole getEntity(final String organizationCd,
			final BigDecimal postId) {
		if (StringUtils.isEmpty(organizationCd)) {
			throw new IllegalArgumentException("organizationCd is empty");
		}

		BelongRole bean = belongRoleDao.getEntity(organizationCd, postId);

		return bean;
	}

	/**
	 * 所属・ロール組合せ検索（詳細用）
	 * @param organizationCd 部署コード
	 * @param postId 役職コード
	 * @return BelongRoleDetail
	 */
	public BelongRoleDetail getDetailEntity(final String organizationCd,
			final String postId) {
		BelongRoleDetail bean = belongRoleDetailDao.getEntity(organizationCd,
			postId);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final BelongRole bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			belongRoleDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final BelongRole bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			belongRoleDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final BelongRole bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			belongRoleDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDetailDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * postDetailDaoを設定します。
	 * @param postDetailDao postDetailDao
	 */
	public void setPostDetailDao(final PostDetailDao postDetailDao) {
		this.postDetailDao = postDetailDao;
	}

	/**
	 * belongRoleDaoを設定します。
	 * @param belongRoleDao belongRoleDao
	 */
	public void setBelongRoleDao(final BelongRoleDao belongRoleDao) {
		this.belongRoleDao = belongRoleDao;
	}

	/**
	 * belongRoleDetailDaoを設定します。
	 * @param belongRoleDetailDao belongRoleDetailDao
	 */
	public void setBelongRoleDetailDao(
			final BelongRoleDetailDao belongRoleDetailDao) {
		this.belongRoleDetailDao = belongRoleDetailDao;
	}

	/**
	 * roleDetailDaoを設定します。
	 * @param roleDetailDao roleDetailDao
	 */
	public void setRoleDetailDao(final RoleDetailDao roleDetailDao) {
		this.roleDetailDao = roleDetailDao;
	}
}
