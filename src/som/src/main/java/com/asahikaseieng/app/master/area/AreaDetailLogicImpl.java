/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.area.Area;
import com.asahikaseieng.dao.entity.master.area.AreaDao;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetail;
import com.asahikaseieng.dao.nonentity.master.areadetail.AreaDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 地区詳細ロジック 実装クラス.
 * @author t0011036
 */
public class AreaDetailLogicImpl implements AreaDetailLogic {

	private AreaDao areaDao;

	private AreaDetailDao areaDetailDao;

	/**
	 * コンストラクタ.
	 */
	public AreaDetailLogicImpl() {
	}

	/**
	 * 地区検索（登録用）
	 * @param areaCd 地区コード
	 * @return Area
	 * @throws NoDataException NoDataException
	 */
	public Area getEntity(final String areaCd) throws NoDataException {
		if (StringUtils.isEmpty(areaCd)) {
			throw new IllegalArgumentException("areaCd is empty");
		}

		Area bean = areaDao.getEntity(areaCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 地区検索（詳細用）
	 * @param areaCd 地区コード
	 * @return AreaDetail
	 */
	public AreaDetail getDetailEntity(final String areaCd) {
		AreaDetail bean = areaDetailDao.getEntity(areaCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Area bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			areaDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Area bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			areaDao.insert(bean);
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
	public void delete(final Area bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			areaDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * areaDaoを設定します。
	 * @param areaDao areaDao
	 */
	public void setAreaDao(final AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	/**
	 * areaDetailDaoを設定します。
	 * @param areaDetailDao areaDetailDao
	 */
	public void setAreaDetailDao(final AreaDetailDao areaDetailDao) {
		this.areaDetailDao = areaDetailDao;
	}
}
