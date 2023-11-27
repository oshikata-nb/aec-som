/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe;

import java.util.List;

/**
 * 設備グループコンボボックス作成用Daoインターフェース.
 *
 * @author tosco
 */
public interface RecipeResouceGroupForComboboxesDao {

	/** BEANアノテーション */
	Class<RecipeResouceGroupForComboboxes> BEAN = RecipeResouceGroupForComboboxes.class;

	/** ARGSアノテーション findByOperationGroupCd */
	String findByOperationGroupCd_ARGS = "operationGroupCd";
	/**
	 * 工程グループコードを条件にした一覧を取得.
	 * @param operationGroupCd 工程グループコード
	 * @return Mgrecipeline
	 */
	List<RecipeResouceGroupForComboboxes> findByOperationGroupCd(String operationGroupCd);

}
