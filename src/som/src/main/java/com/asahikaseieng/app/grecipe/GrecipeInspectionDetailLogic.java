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
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 原処方 処方検査詳細 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeInspectionDetailLogic {

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	GrecipeRecipeHeaderList getHeader(final BigDecimal recipeId,
			final BigDecimal stepNo) throws NoDataException;

	/**
	 * 処方検査検索処理を行う。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @param lineNo	LINE_NO
	 * @return GrecipeRecipeInspectionList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	GrecipeRecipeInspectionList getEntity(final BigDecimal recipeId,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う。
	 * 　各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 処方検査Bean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final GrecipeRecipeInspectionList bean);

	/**
	 * 処方検査更新処理を行う。
	 * @param bean 処方	検査Bean
	 * @throws Exception 例外
	 */
	void update(final GrecipeRecipeInspectionList bean) throws Exception;

}
