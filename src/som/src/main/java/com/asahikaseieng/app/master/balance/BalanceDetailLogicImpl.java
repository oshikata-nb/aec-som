/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.balance;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetail;
import com.asahikaseieng.dao.nonentity.master.balancedetail.BalanceDetailDao;
import com.asahikaseieng.dao.nonentity.master.balancedetaillist.BalanceDetailList;
import com.asahikaseieng.dao.nonentity.master.balancedetaillist.BalanceDetailListDao;
import com.asahikaseieng.dao.nonentity.master.balancelowerdetail.BalanceLowerDetail;
import com.asahikaseieng.dao.nonentity.master.balancelowerdetail.BalanceLowerDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 帳合詳細ロジック 実装クラス.
 * @author t0011036
 */
public class BalanceDetailLogicImpl implements BalanceDetailLogic {

	private BalanceDao balanceDao;

	private BalanceDetailDao balanceDetailDao;

	private BalanceLowerDetailDao balanceLowerDetailDao;

	private BalanceDetailListDao balanceDetailListDao;

	private VenderDetailDao venderDetailDao;

	/**
	 * コンストラクタ.
	 */
	public BalanceDetailLogicImpl() {
	}

	/**
	 * 帳合検索（登録用）
	 * @param balanceCd 帳合コード
	 * @return Balance
	 * @throws NoDataException NoDataException
	 */
	public Balance getEntity(final String balanceCd) throws NoDataException {
		if (StringUtils.isEmpty(balanceCd)) {
			throw new IllegalArgumentException("balanceCd is empty");
		}

		Balance bean = balanceDao.getEntity(balanceCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 帳合検索（詳細用）
	 * @param balanceCd 帳合コード
	 * @return BalanceDetail
	 */
	public BalanceDetail getDetailEntity(final String balanceCd) {
		BalanceDetail bean = balanceDetailDao.getEntity(balanceCd);
		return bean;
	}

	/**
	 * 帳合検索（下位帳合用）
	 * @param upperBalanceCd 上位帳合コード
	 * @return BalanceLowerDetail
	 */
	public BalanceLowerDetail getLowerEntity(final String upperBalanceCd) {
		BalanceLowerDetail bean = balanceLowerDetailDao
				.getEntity(upperBalanceCd);
		return bean;
	}

	/**
	 * 帳合詳細一覧検索
	 * @param balanceCd 帳合コード
	 * @return List<BalanceDetailList>
	 */
	public List<BalanceDetailList> getDetailList(final String balanceCd) {
		List<BalanceDetailList> bean = balanceDetailListDao.getList(balanceCd);
		return bean;
	}

	/**
	 * 得意先検索
	 * @param venderDivision 取引先区分
	 * @param venderCd 得意先コード
	 * @return VenderDetail
	 */
	public VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd) {
		VenderDetail bean = venderDetailDao.getEntity(venderDivision, venderCd);
		return bean;
	}

	/**
	 * 帳合レベル計算
	 * @param balanceCd 帳合コード
	 * @param upperBalanceCd 上位帳合コード
	 * @return 帳合レベル
	 * @throws NoDataException NoDataException
	 */
	public int calcBalanceLevel(final String balanceCd,
			final String upperBalanceCd) throws NoDataException {
		int shopLevel = 1;

		/* 帳合検索 */
		BalanceDetail bean = getDetailEntity(upperBalanceCd);

		if (bean == null) {
			return shopLevel;
		}

		shopLevel++;

		while (!StringUtils.isEmpty(bean.getUpperBalanceCd())) {
			/* 循環参照チェック */
			if (bean.getUpperBalanceCd().equals(balanceCd)) {
				shopLevel = -1;
				break;
			}

			/* 帳合検索 */
			bean = getDetailEntity(bean.getUpperBalanceCd());

			if (bean == null) {
				return shopLevel;
			}

			shopLevel++;
		}

		return shopLevel;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Balance bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			balanceDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Balance bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			balanceDao.insert(bean);
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
	public void delete(final Balance bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			balanceDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 先付受注ヘッダ存在チェック用
	 * @param balanceCd 帳合コード
	 * @return ある・無し値
	 */
	public int getCountFrstOrderHead(final String balanceCd) {

		// 引数の帳合コードを使用し、先付受注ヘッダの検索を行い
		// 検索結果件数を取得する
		int count = balanceDetailDao.getCountFrstOrderHead(balanceCd);
 
		return count;
	}
	
	/* -------------------- setter -------------------- */

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}

	/**
	 * balanceDetailDaoを設定します。
	 * @param balanceDetailDao balanceDetailDao
	 */
	public void setBalanceDetailDao(final BalanceDetailDao balanceDetailDao) {
		this.balanceDetailDao = balanceDetailDao;
	}

	/**
	 * balanceDetailListDaoを設定します。
	 * @param balanceDetailListDao balanceDetailListDao
	 */
	public void setBalanceDetailListDao(
			final BalanceDetailListDao balanceDetailListDao) {
		this.balanceDetailListDao = balanceDetailListDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * balanceLowerDetailDaoを設定します。
	 * @param balanceLowerDetailDao balanceLowerDetailDao
	 */
	public void setBalanceLowerDetailDao(
			final BalanceLowerDetailDao balanceLowerDetailDao) {
		this.balanceLowerDetailDao = balanceLowerDetailDao;
	}
		
}
