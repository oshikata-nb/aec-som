/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 部署詳細ロジック interface.
 * @author t0011036
 */
public interface OrganizationDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @throws NoDataException NoDataException
	 * @return Organization
	 */
	Organization getEntity(final String organizationCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getDetailEntity(final String organizationCd);

	/**
	 * 検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return LoginDetail
	 */
	LoginDetail getLoginEntity(final String tantoCd) throws NoDataException;

	/**
	 * 部署レベル計算
	 * @param organizationCd 部署コード
	 * @param parentOrganizationCd 親部署コード
	 * @throws NoDataException NoDataException
	 * @return 部署レベル
	 */
	int calcOrganizationLevel(final String organizationCd,
			final String parentOrganizationCd) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Organization bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Organization bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Organization bean) throws NoDataException;
}
