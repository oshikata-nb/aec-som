/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.component.Component;
import com.asahikaseieng.dao.entity.master.component.ComponentDao;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetail;
import com.asahikaseieng.dao.nonentity.master.componentdetail.ComponentDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 成分詳細ロジック 実装クラス.
 * @author t0011036
 */
public class ComponentDetailLogicImpl implements ComponentDetailLogic {

	private ComponentDao componentDao;

	private ComponentDetailDao componentDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ComponentDetailLogicImpl() {
	}

	/**
	 * 成分検索（登録用）
	 * @param componentCd 成分コード
	 * @return Component
	 * @throws NoDataException NoDataException
	 */
	public Component getEntity(final String componentCd) throws NoDataException {
		if (StringUtils.isEmpty(componentCd)) {
			throw new IllegalArgumentException("componentCd is empty");
		}

		Component bean = componentDao.getEntity(componentCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 成分検索（詳細用）
	 * @param componentCd 成分コード
	 * @return ComponentDetail
	 */
	public ComponentDetail getDetailEntity(final String componentCd) {
		ComponentDetail bean = componentDetailDao.getEntity(componentCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Component bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			componentDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Component bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			componentDao.insert(bean);
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
	public void delete(final Component bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			componentDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * componentDaoを設定します。
	 * @param componentDao componentDao
	 */
	public void setComponentDao(final ComponentDao componentDao) {
		this.componentDao = componentDao;
	}

	/**
	 * componentDetailDaoを設定します。
	 * @param componentDetailDao componentDetailDao
	 */
	public void setComponentDetailDao(
			final ComponentDetailDao componentDetailDao) {
		this.componentDetailDao = componentDetailDao;
	}
}
