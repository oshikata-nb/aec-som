/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.dao.entity.master.reciperesoucegroup.RecipeResouceGroup;
import com.asahikaseieng.dao.nonentity.comboboxes.master.operationgroup.OperationGroupListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備グループ詳細ロジック interface.
 * @author t0011036
 */
public interface RecipeResouceGroupDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param resouceGroupCd 設備グループコード
	 * @throws NoDataException NoDataException
	 * @return RecipeResouceGroup
	 */
	RecipeResouceGroup getEntity(final String resouceGroupCd)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param resouceGroupCd 設備グループコード
	 * @return RecipeResouceGroupDetail
	 */
	RecipeResouceGroupDetail getDetailEntity(final String resouceGroupCd);

	/**
	 * 工程グループ取得
	 * @return List<OperationGroupListForComboboxes>
	 */
	List<OperationGroupListForComboboxes> getOperationGroupList();

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final RecipeResouceGroup bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final RecipeResouceGroup bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final RecipeResouceGroup bean) throws NoDataException;
}
