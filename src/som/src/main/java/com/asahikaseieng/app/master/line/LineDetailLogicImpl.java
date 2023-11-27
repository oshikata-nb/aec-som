/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.line;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.line.Line;
import com.asahikaseieng.dao.entity.master.line.LineDao;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 生産ライン詳細ロジック 実装クラス.
 * @author t0011036
 */
public class LineDetailLogicImpl implements LineDetailLogic {

	private LineDao lineDao;

	private LineDetailDao lineDetailDao;

	/**
	 * コンストラクタ.
	 */
	public LineDetailLogicImpl() {
	}

	/**
	 * 生産ライン検索（登録用）
	 * @param productionLine 生産ラインコード
	 * @return Line
	 * @throws NoDataException NoDataException
	 */
	public Line getEntity(final String productionLine) throws NoDataException {
		if (StringUtils.isEmpty(productionLine)) {
			throw new IllegalArgumentException("productionLine is empty");
		}

		Line bean = lineDao.getEntity(productionLine);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 生産ライン検索（詳細用）
	 * @param productionLine 生産ラインコード
	 * @return LineDetail
	 */
	public LineDetail getDetailEntity(final String productionLine) {
		LineDetail bean = lineDetailDao.getEntity(productionLine);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Line bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			lineDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Line bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			lineDao.insert(bean);
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
	public void delete(final Line bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			lineDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * lineDaoを設定します。
	 * @param lineDao lineDao
	 */
	public void setLineDao(final LineDao lineDao) {
		this.lineDao = lineDao;
	}

	/**
	 * lineDetailDaoを設定します。
	 * @param lineDetailDao lineDetailDao
	 */
	public void setLineDetailDao(final LineDetailDao lineDetailDao) {
		this.lineDetailDao = lineDetailDao;
	}
}
