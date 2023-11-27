/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.exception.NoDataException;



/**
 * 原処方 仕上タブ ロジック interface.
 * @author tosco
 */
public interface GrecipeFinishListLogic {

	/**
	 *
	 * 一覧検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeGrecipeRecipeFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<GrecipeRecipeFormulaList> getSearchList(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<GrecipeRecipeFormulaList> searchList);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final GrecipeFinishListForm frm, final String tantoCd) throws Exception;

}
