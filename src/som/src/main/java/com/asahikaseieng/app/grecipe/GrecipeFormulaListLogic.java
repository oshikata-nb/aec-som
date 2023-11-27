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
 * 原処方 処方フォーミュラ検索 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeFormulaListLogic {

	/**
	 *
	 * 配合量の合計値取得処理を行う.
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return String 配合量の合計
	 */
	GrecipeRecipeFormulaList getSumQty(final BigDecimal recipeId, final BigDecimal procStepNo);

	/**
	 *
	 * 一覧検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return List<GrecipeRecipeFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<GrecipeRecipeFormulaList> getSearchList(
		final BigDecimal recipeId, final BigDecimal procStepNo) throws NoDataException;

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 原処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final GrecipeFormulaListForm form);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<GrecipeRecipeFormulaList> searchFormList);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final GrecipeFormulaListForm frm, final String tantoCd) throws Exception;

}
