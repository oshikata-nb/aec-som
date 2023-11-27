/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.pkgdirection.PkgDirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－包装ラインのAuto Complete用ロジック
 * @author tosco
 */
public interface PkgDirectionRecipeResouceForAutoCompleteLogic {
	/**
	 * 包装ライン一覧を取得
	 * @param productionLine 生産ライン
	 * @param packageLine 包装ライン
	 * @return List<PkgDirectionRecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionRecipeResouceForAutoComplete> getSearchList(
		String productionLine, String packageLine) throws NoDataException;
}
