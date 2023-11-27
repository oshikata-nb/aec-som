/*
 * Created on 2009/01/29
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
 * 基本処方 処方フォーミュラ検索 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeFormulaListLogic {

	/**
	 * 
	 * 配合量の合計値取得処理を行う.
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return String 配合量の合計
	 */
	RecipeFormulaList getSumQty(final BigDecimal recipeId, final BigDecimal procStepNo);

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return List<RecipeFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<RecipeFormulaList> getSearchList(
		final BigDecimal recipeId, final BigDecimal procStepNo) throws NoDataException;

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 基本処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final MgrecipeFormulaListForm form);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<RecipeFormulaList> searchFormList);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final MgrecipeFormulaListForm frm, final String tantoCd) throws Exception;

}
