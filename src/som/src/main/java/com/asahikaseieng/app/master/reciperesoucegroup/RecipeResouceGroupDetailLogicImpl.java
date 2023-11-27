/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.reciperesoucegroup.RecipeResouceGroup;
import com.asahikaseieng.dao.entity.master.reciperesoucegroup.RecipeResouceGroupDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 設備グループ詳細ロジック 実装クラス.
 * @author t0011036
 */
public class RecipeResouceGroupDetailLogicImpl implements
		RecipeResouceGroupDetailLogic {

	private RecipeResouceGroupDao recipeResouceGroupDao;

	private RecipeResouceGroupDetailDao recipeResouceGroupDetailDao;

	private OperationGroupListForComboboxesDao operationGroupListForComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupDetailLogicImpl() {
	}

	/**
	 * 設備グループ検索（登録用）
	 * @param resouceGroupCd 設備グループコード
	 * @return RecipeResouceGroup
	 * @throws NoDataException NoDataException
	 */
	public RecipeResouceGroup getEntity(final String resouceGroupCd)
			throws NoDataException {
		if (StringUtils.isEmpty(resouceGroupCd)) {
			throw new IllegalArgumentException("resouceGroupCd is empty");
		}

		RecipeResouceGroup bean = recipeResouceGroupDao
				.getEntity(resouceGroupCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 設備グループ検索（詳細用）
	 * @param resouceGroupCd 設備グループコード
	 * @return RecipeResouceGroupDetail
	 */
	public RecipeResouceGroupDetail getDetailEntity(final String resouceGroupCd) {
		RecipeResouceGroupDetail bean = recipeResouceGroupDetailDao
				.getEntity(resouceGroupCd);
		return bean;
	}

	/**
	 * 工程グループリスト取得
	 * @return List<OperationGroupListForComboboxes>
	 */
	public List<OperationGroupListForComboboxes> getOperationGroupList() {
		List<OperationGroupListForComboboxes> list = operationGroupListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final RecipeResouceGroup bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			recipeResouceGroupDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final RecipeResouceGroup bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			recipeResouceGroupDao.insert(bean);
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
	public void delete(final RecipeResouceGroup bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			recipeResouceGroupDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * operationGroupListForComboboxesDaoを設定します。
	 * @param operationGroupListForComboboxesDao
	 *            operationGroupListForComboboxesDao
	 */
	public void setOperationGroupListForComboboxesDao(
			final OperationGroupListForComboboxesDao operationGroupListForComboboxesDao) {
		this.operationGroupListForComboboxesDao = operationGroupListForComboboxesDao;
	}

	/**
	 * recipeResouceGroupDaoを設定します。
	 * @param recipeResouceGroupDao recipeResouceGroupDao
	 */
	public void setRecipeResouceGroupDao(
			final RecipeResouceGroupDao recipeResouceGroupDao) {
		this.recipeResouceGroupDao = recipeResouceGroupDao;
	}

	/**
	 * recipeResouceGroupDetailDaoを設定します。
	 * @param recipeResouceGroupDetailDao recipeResouceGroupDetailDao
	 */
	public void setRecipeResouceGroupDetailDao(
			final RecipeResouceGroupDetailDao recipeResouceGroupDetailDao) {
		this.recipeResouceGroupDetailDao = recipeResouceGroupDetailDao;
	}
}
