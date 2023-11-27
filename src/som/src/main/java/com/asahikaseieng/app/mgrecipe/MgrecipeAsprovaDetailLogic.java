/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方 処方ASPROVA 詳細 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeAsprovaDetailLogic {

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @return RecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	RecipeHeaderList getHeader(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 処方ASPROVA 検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param oerationGroupCd 工程グループコード
	 * @param resouceCd 設備コード
	 * @return MgrecipeList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	RecipeAsprovaList findByPrimaryKey(
	final BigDecimal recipeId, final String resouceGroupCd, final String oerationGroupCd, final String resouceCd)
	throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param bean 処方ASPROVA Bean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final RecipeAsprovaList bean);

	/**
	 * 処方ASPROVA 更新処理を行う.
	 * @param bean 処方ASPROVA Bean
	 * @throws Exception 例外
	 */
	void update(final RecipeAsprovaList bean) throws Exception;

}
