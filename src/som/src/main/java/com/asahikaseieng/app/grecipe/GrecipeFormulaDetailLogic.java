/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 原処方 処方フォーミュラ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeFormulaDetailLogic {

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	GrecipeRecipeHeaderList getHeader(final BigDecimal recipeId, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 処方フォーミュラ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @param lineNo   LINE_NO
	 * @return MgrecipeList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeFormulaList getEntity(final BigDecimal recipeId, final BigDecimal stepNo, final BigDecimal lineNo)
	throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param bean 処方フォーミュラBean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final GrecipeRecipeFormulaList bean);

	/**
	 * 処方フォーミュラ更新処理を行う.
	 * @param bean 処方フォーミュラBean
	 * @param form 配合詳細情報
	 * @throws Exception 例外
	 */
	void update(final GrecipeRecipeFormulaList bean, final GrecipeFormulaDetailForm form) throws Exception;

}
