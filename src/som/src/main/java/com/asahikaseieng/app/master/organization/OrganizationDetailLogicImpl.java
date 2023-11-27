/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 部署詳細ロジック 実装クラス.
 * @author t0011036
 */
public class OrganizationDetailLogicImpl implements OrganizationDetailLogic {

	private OrganizationDao organizationDao;

	private OrganizationDetailDao organizationDetailDao;

	private LoginDetailDao loginDetailDao;

	/**
	 * コンストラクタ.
	 */
	public OrganizationDetailLogicImpl() {
	}

	/**
	 * 部署検索（登録用）
	 * @param organizationCd 部署コード
	 * @return Organization
	 * @throws NoDataException NoDataException
	 */
	public Organization getEntity(final String organizationCd)
			throws NoDataException {
		if (StringUtils.isEmpty(organizationCd)) {
			throw new IllegalArgumentException("organizationCd is empty");
		}

		Organization bean = organizationDao.getEntity(organizationCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 部署検索（詳細用）
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getDetailEntity(final String organizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(organizationCd);
		return bean;
	}

	/**
	 * 担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	public LoginDetail getLoginEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 部署レベル計算
	 * @param organizationCd 部署コード
	 * @param parentOrganizationCd 親部署コード
	 * @return 部署レベル
	 * @throws NoDataException NoDataException
	 */
	public int calcOrganizationLevel(final String organizationCd,
			final String parentOrganizationCd) throws NoDataException {
		int organizationLevel = 1;

		/* 部署検索 */
		OrganizationDetail bean = getDetailEntity(parentOrganizationCd);

		if (bean == null) {
			return organizationLevel;
		}

		organizationLevel++;

		while (!StringUtils.isEmpty(bean.getParentOrganizationCd())) {
			/* 循環参照チェック */
			if (bean.getParentOrganizationCd().equals(organizationCd)) {
				organizationLevel = -1;
				break;
			}

			/* 部署検索 */
			bean = getDetailEntity(bean.getParentOrganizationCd());

			if (bean == null) {
				return organizationLevel;
			}

			organizationLevel++;
		}

		return organizationLevel;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Organization bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			organizationDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Organization bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			organizationDao.insert(bean);
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
	public void delete(final Organization bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			organizationDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationDaoを設定します。
	 * @param organizationDao organizationDao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * organizationDetailDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}
}
