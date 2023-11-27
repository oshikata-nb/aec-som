/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouce;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucedetail.RecipeResouceDetail;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備詳細ロジック interface.
 * @author t0011036
 */
public interface RecipeResouceDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param resouceCd 設備コード
	 * @throws NoDataException NoDataException
	 * @return RecipeResouce
	 */
	RecipeResouce getEntity(final String resouceCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param resouceCd 設備コード
	 * @return RecipeResouceDetail
	 */
	RecipeResouceDetail getDetailEntity(final String resouceCd);

	/**
	 * 設備グループ検索処理を行う.
	 * @param resouceGroupCd 設備グループコード
	 * @return RecipeResouceGroupDetail
	 */
	RecipeResouceGroupDetail getRecipeResouceGroupEntity(
			final String resouceGroupCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final RecipeResouce bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final RecipeResouce bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final RecipeResouce bean) throws NoDataException;

	/**
	 * 生産ライン取得
	 * @return List<LineListForComboboxes>
	 */
	List<LineListForComboboxes> getLineList();

	/**
	 * 前工程設備一覧検索
	 * @param resouceCd 設備コード
	 * @return List<RecipePegResouceDetailList>
	 */
	List<RecipePegResouceDetailList> getList(final String resouceCd);

	/**
	 * 登録処理を行う.
	 * @param frm 更新対象データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void regist(final RecipeResouceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 削除処理
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void deleteList(final RecipeResouce bean) throws NoDataException;
}
