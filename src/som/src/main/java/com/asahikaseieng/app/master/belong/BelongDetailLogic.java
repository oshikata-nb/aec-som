/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.belong.Belong;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属詳細ロジック interface.
 * @author t0011036
 */
public interface BelongDetailLogic {
	/**
	 * 部署検索処理を行う.
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getOrganizationEntity(final String organizationCd);

	/**
	 * 担当者検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getLoginEntity(final String tantoCd);

	/**
	 * 役職検索処理を行う.
	 * @param postId 役職コード
	 * @return PostDetail
	 */
	PostDetail getPostEntity(final String postId);

	/**
	 * 所属マスタから担当者の主所属の件数を取得する。
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return int 主所属件数
	 */
	int getCountMainBelong(final String tantoCd) throws NoDataException;

	/**
	 * 所属マスタから担当者の兼務所属の件数を取得する。
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return int 兼務所属件数
	 */
	int getCountKenmuBelong(final String tantoCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @param postId 役職コード
	 * @return Belong
	 */
	Belong getEntity(final String organizationCd, final String tantoCd,
			final BigDecimal postId);

	/**
	 * 検索処理を行う.
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @param postId 役職コード
	 * @param belongKbn 所属区分
	 * @return BelongDetail
	 */
	BelongDetail getDetailEntity(final String organizationCd,
			final String tantoCd, final String postId,
			final BigDecimal belongKbn);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final BelongDetail bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final BelongDetail bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final BelongDetail bean) throws NoDataException;
}
