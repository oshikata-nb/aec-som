/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisitDao;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 名称詳細ロジック 実装クラス.
 * @author t0011036
 */
public class NamesDetailLogicImpl implements NamesDetailLogic {

	private NamesDao namesDao;

	private NamesDetailDao namesDetailDao;

	private NumberChkdisitDao numberChkdisitDao;

	/**
	 * コンストラクタ.
	 */
	public NamesDetailLogicImpl() {
	}

	/**
	 * 名称検索（登録用）
	 * @param nameDivision 名称区分
	 * @param nameCd 名称コード
	 * @return Names
	 * @throws NoDataException NoDataException
	 */
	public Names getEntity(final String nameDivision, final String nameCd)
			throws NoDataException {
		if (StringUtils.isEmpty(nameDivision)) {
			throw new IllegalArgumentException("nameDivision is empty");
		}

		if (StringUtils.isEmpty(nameCd)) {
			throw new IllegalArgumentException("nameCd is empty");
		}

		Names bean = namesDao.getEntity(nameCd, nameDivision);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 数値チェック検索（登録用）
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return NumberChkdisit
	 */
	public NumberChkdisit getNumberEntity(final String unitDivision,
			final String venderDivision, final String venderCd) {
		if (StringUtils.isEmpty(unitDivision)) {
			throw new IllegalArgumentException("unitDivision is empty");
		}

		if (StringUtils.isEmpty(venderDivision)) {
			throw new IllegalArgumentException("venderDivision is empty");
		}

		if (StringUtils.isEmpty(venderCd)) {
			throw new IllegalArgumentException("venderCd is empty");
		}

		NumberChkdisit bean = numberChkdisitDao.getEntity(unitDivision,
			venderDivision, venderCd);

		return bean;
	}

	/**
	 * 名称検索（詳細用）
	 * @param nameDivision 名称区分
	 * @param nameCd 名称コード
	 * @return NamesDetail
	 */
	public NamesDetail getDetailEntity(final String nameDivision,
			final String nameCd) {
		NamesDetail bean = namesDetailDao.getEntity(nameDivision, nameCd);
		return bean;
	}

	/**
	 * 登録処理
	 * @param bean 登録データ
	 * @param beanNumber 登録データ(数値チェック)
	 */
	public void regist(final Names bean, final NumberChkdisit beanNumber) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		if (bean.getUpdateDate() == null) {
			/* 追加処理 */
			insert(bean);
		} else {
			/* 更新処理 */
			update(bean);
		}

		if (beanNumber != null) {
			if (beanNumber.getUpdateDate() == null) {
				/* 追加処理 */
				insertNumber(beanNumber);
			} else {
				/* 更新処理 */
				updateNumber(beanNumber);
			}
		}
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Names bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			namesDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新登録(数値チェック)
	 * @param bean 登録データ
	 */
	public void updateNumber(final NumberChkdisit bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			numberChkdisitDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Names bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			namesDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 追加登録(数値チェック)
	 * @param bean 登録データ
	 */
	public void insertNumber(final NumberChkdisit bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			numberChkdisitDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @param beanNumber 削除データ(数値チェック)
	 * @throws NoDataException NoDataException
	 */
	public void delete(final Names bean, final NumberChkdisit beanNumber)
			throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			namesDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		if (beanNumber != null) {
			try {
				/* 削除処理 */
				numberChkdisitDao.delete(beanNumber);
			} catch (SQLRuntimeException e) {
				/* データなしエラー */
				throw new NoDataException();
			}
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * namesDaoを設定します。
	 * @param namesDao namesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}

	/**
	 * namesDetailDaoを設定します。
	 * @param namesDetailDao namesDetailDao
	 */
	public void setNamesDetailDao(final NamesDetailDao namesDetailDao) {
		this.namesDetailDao = namesDetailDao;
	}

	/**
	 * numberChkdisitDaoを設定します。
	 * @param numberChkdisitDao numberChkdisitDao
	 */
	public void setNumberChkdisitDao(final NumberChkdisitDao numberChkdisitDao) {
		this.numberChkdisitDao = numberChkdisitDao;
	}
}
