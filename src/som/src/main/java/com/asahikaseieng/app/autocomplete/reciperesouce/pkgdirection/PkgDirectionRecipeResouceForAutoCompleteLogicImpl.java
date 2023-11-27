/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.pkgdirection;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.pkgdirection.PkgDirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.pkgdirection.PkgDirectionRecipeResouceForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装指図－包装ラインオートコンプリート用ロジック
 * @author tosco
 */
public class PkgDirectionRecipeResouceForAutoCompleteLogicImpl implements
		PkgDirectionRecipeResouceForAutoCompleteLogic {

	/** 包装ラインオートコンプリート用DAO */
	private PkgDirectionRecipeResouceForAutoCompleteDao pkgDirectionRecipeResouceForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeResouceForAutoCompleteLogicImpl() {
	}

	/**
	 * 包装ライン一覧を取得
	 * @param productionLine 生産ライン
	 * @param packageLine 包装ライン
	 * @return List<PkgDirectionRecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionRecipeResouceForAutoComplete> getSearchList(
			final String productionLine, final String packageLine)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(packageLine);
		List<PkgDirectionRecipeResouceForAutoComplete> list = pkgDirectionRecipeResouceForAutoCompleteDao
				.getSearchList(productionLine, val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 包装ラインオートコンプリート用DAOを設定します。
	 * @param pkgDirectionRecipeResouceForAutoCompleteDao 包装ラインオートコンプリート用DAO
	 */
	public void setDirectionRecipeResouceForAutoCompleteDao(
			final PkgDirectionRecipeResouceForAutoCompleteDao pkgDirectionRecipeResouceForAutoCompleteDao) {
		this.pkgDirectionRecipeResouceForAutoCompleteDao = pkgDirectionRecipeResouceForAutoCompleteDao;
	}

}
