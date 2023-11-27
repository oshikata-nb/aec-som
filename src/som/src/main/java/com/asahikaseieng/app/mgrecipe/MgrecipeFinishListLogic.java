/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 基本処方 仕上タブ ロジック interface.
 * @author tosco
 */
public interface MgrecipeFinishListLogic {

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<RecipeFormulaList> getSearchList(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<RecipeFormulaList> searchList);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final MgrecipeFinishListForm frm, final String tantoCd) throws Exception;

}
