/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 原処方-工程詳細 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeProcedureDetailLogic {

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	GrecipeRecipeHeaderList getHeader(final BigDecimal recipeId, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeProcedureList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeProcedureList getEntity(final BigDecimal recipeId, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param bean 処方プロシージャBean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final GrecipeRecipeProcedureList bean);

	/**
	 * 処方プロシージャ更新処理を行う.
	 * @param bean 処方プロシージャBean
	 * @throws Exception 例外
	 */
	void update(final GrecipeRecipeProcedureList bean) throws Exception;

}
