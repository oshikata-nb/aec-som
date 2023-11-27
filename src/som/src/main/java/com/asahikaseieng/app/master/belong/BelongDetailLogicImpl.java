/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.belong.Belong;
import com.asahikaseieng.dao.entity.master.belong.BelongDao;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetail;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetailDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 所属詳細ロジック 実装クラス.
 * @author t0011036
 */
public class BelongDetailLogicImpl implements BelongDetailLogic {

	private BelongDao belongDao;

	private BelongDetailDao belongDetailDao;

	private OrganizationDetailDao organizationDetailDao;

	private LoginDetailDao loginDetailDao;

	private PostDetailDao postDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BelongDetailLogicImpl() {
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
	 * 担当者検索
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	public LoginDetail getLoginEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
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
	 * 所属マスタから担当者の主所属件数を取得する。
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return int 主所属件数
	 */
	public int getCountMainBelong(final String tantoCd) throws NoDataException {
		int count = belongDetailDao.getCountMainBelong(tantoCd);
		return count;
	}

	/**
	 * 所属マスタから担当者の兼務所属の件数を取得する。
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return int 兼務所属件数
	 */
	public int getCountKenmuBelong(final String tantoCd) throws NoDataException {
		int count = belongDetailDao.getCountKenmuBelong(tantoCd);
		return count;
	}

	/**
	 * 所属検索（登録用）
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @param postId 役職コード
	 * @return Belong
	 */
	public Belong getEntity(final String organizationCd, final String tantoCd,
			final BigDecimal postId) {
		if (StringUtils.isEmpty(organizationCd)) {
			throw new IllegalArgumentException("organizationCd is empty");
		}

		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}

		Belong bean = belongDao.getEntity(organizationCd, tantoCd, postId);

		return bean;
	}

	/**
	 * 所属検索（詳細用）
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @param postId 役職コード
	 * @param belongKbn 所属区分
	 * @return BelongDetail
	 */
	public BelongDetail getDetailEntity(final String organizationCd,
			final String tantoCd, final String postId,
			final BigDecimal belongKbn) {
		BelongDetail bean = belongDetailDao.getEntity(organizationCd, tantoCd,
			postId, belongKbn);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final BelongDetail bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			belongDetailDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final BelongDetail bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			belongDetailDao.insert(bean);
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
	public void delete(final BelongDetail bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			belongDetailDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * belongDaoを設定します。
	 * @param belongDao belongDao
	 */
	public void setBelongDao(final BelongDao belongDao) {
		this.belongDao = belongDao;
	}

	/**
	 * belongDetailDaoを設定します。
	 * @param belongDetailDao belongDetailDao
	 */
	public void setBelongDetailDao(final BelongDetailDao belongDetailDao) {
		this.belongDetailDao = belongDetailDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
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
	 * postDetailDaoを設定します。
	 * @param postDetailDao postDetailDao
	 */
	public void setPostDetailDao(final PostDetailDao postDetailDao) {
		this.postDetailDao = postDetailDao;
	}
}
