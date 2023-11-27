/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.pkgdirection;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection.PkgDirectionRecipeHeaderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection.PkgDirectionRecipeHeaderForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装指図－基本処方ヘッダ情報AutoComplete用ロジック
 * @author tosco
 */
public class PkgDirectionRecipeHeaderForAutoCompleteLogicImpl implements
		PkgDirectionRecipeHeaderForAutoCompleteLogic {

	/** 包装指図－基本処方ヘッダ情報操作DAO */
	private PkgDirectionRecipeHeaderForAutoCompleteDao pkgDirectionRecipeHeaderForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteLogicImpl() {
	}

	/**
	 * 包装指図－基本処方ヘッダ情報のオートコンプリート用データの取得
	 * 
	 * @param recipeCdVersion レシピコード+レシピバージョンまたは基本処方名称
	 * @param itemCd 品目コード
	 * @param line 生産ライン
	 * @param otherCompanyCd1 他社コード１
	 * @return List<PkgDirectionRecipeHeaderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<PkgDirectionRecipeHeaderForAutoComplete> getSearchList(
			final String recipeCdVersion, final String itemCd,
			final String line, final String otherCompanyCd1)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(recipeCdVersion);
		List<PkgDirectionRecipeHeaderForAutoComplete> list = pkgDirectionRecipeHeaderForAutoCompleteDao
				.getSearchList(val, itemCd, line, otherCompanyCd1,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 包装指図－基本処方ヘッダ情報操作DAOを設定します。
	 * 
	 * @param pkgDirectionRecipeHeaderForAutoCompleteDao 包装指図－基本処方ヘッダ情報操作DAO
	 */
	public void setPkgDirectionRecipeHeaderForAutoCompleteDao(
			final PkgDirectionRecipeHeaderForAutoCompleteDao pkgDirectionRecipeHeaderForAutoCompleteDao) {
		this.pkgDirectionRecipeHeaderForAutoCompleteDao = pkgDirectionRecipeHeaderForAutoCompleteDao;
	}

}
