/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.classification;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.classification.Classification;
import com.asahikaseieng.dao.entity.classification.ClassificationDao;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 分類マスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class ClassificationDetailLogicImpl implements ClassificationDetailLogic {

	private ClassificationDetailDao classificationDetailDao;

	private ClassificationDao classificationDao;

	/**
	 * コンストラクタ.分類マスタ詳細ロジック
	 */
	public ClassificationDetailLogicImpl() {
	}

	/**
	 * 分類マスタ検索
	 * 
	 * @param dataType ﾃﾞｰﾀ種別
	 * @param dataTotalDivision データ集計区分
	 * @param categoryDivision 分類コード
	 * @return BankDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public Classification getEntity(final BigDecimal dataType,
			final BigDecimal dataTotalDivision, final String categoryDivision)
			throws NoDataException {
		checkParams(dataType, categoryDivision);

		Classification bean = classificationDao.getEntity(categoryDivision,
			dataTotalDivision, dataType);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void update(final Classification bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			int updateNum = classificationDao.update(bean);

			if (updateNum != 1) {
				/* 更新エラー */
				throw new NoDataException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	public void insert(final Classification bean) {
		int updateNum;

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			updateNum = classificationDao.insert(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 * 
	 */
	public void delete(final Classification bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			int deleteNum = classificationDao.delete(bean);

			if (deleteNum != 1) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー */
			throw new NoDataException();
		}
	}

	/**
	 * パラメータチェック.
	 * @param cd 検索条件:LOCATION
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final BigDecimal cd1, final String cd3)
			throws IllegalArgumentException {

		if (cd1 == null || cd3 == null) {
			throw new IllegalArgumentException(
					"ClassificationDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/**
	 * 分類マスタ検索
	 * 
	 * @param dataType ﾃﾞｰﾀ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail 詳細データ
	 * @throws NoDataException NoDataException
	 */
	public ClassificationDetail getDetailEntity(final BigDecimal dataType,
			final String categoryDivision) throws NoDataException {
		ClassificationDetail bean = classificationDetailDao.getEntity(dataType,
			categoryDivision, null);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * classificationDetailDaoを設定します。
	 * @param classificationDetailDao classificationDetailDao
	 */
	public void setClassificationDetailDao(
			final ClassificationDetailDao classificationDetailDao) {
		this.classificationDetailDao = classificationDetailDao;

	}

	/**
	 * classificationDaoを設定します。
	 * @param classificationDao classificationDao
	 */
	public void setClassificationDao(final ClassificationDao classificationDao) {
		this.classificationDao = classificationDao;
	}
}
