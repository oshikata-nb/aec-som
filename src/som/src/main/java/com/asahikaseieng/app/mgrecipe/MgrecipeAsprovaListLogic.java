/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方 Asprovaタブ ロジック interface.
 * @author tosco
 */
public interface MgrecipeAsprovaListLogic {

	/**
	 * 用途が製造の場合の初期表示画面項目設定
	 * @param frm 配合タブForm
	 */
	void makeInitDispProduction(final MgrecipeAsprovaListForm frm);

	/**
	 * 用途が包装の場合の初期表示画面項目設定
	 * @param frm 配合タブForm
	 */
	void makeInitDispPacking(final MgrecipeAsprovaListForm frm);

	/**
	 * Asprovaタブ－一覧検索処理
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param operationGroupCd 工程グループコード
	 * @param productionLine 生産ラインコード
	 * @return List<RecipeFormulaList> データ
	 * @throws NoDataException 例外
	 */
	List<RecipeAsprovaList> getSearchList(final BigDecimal recipeId,
			final String resouceGroupCd, final String operationGroupCd,
			final String productionLine) throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 設備マスタにデータがない場合はエラーとする。
	 * @param searchAsprList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(List<RecipeAsprovaList> searchAsprList);

	/**
	 * 処方Asprova登録処理を行う.
	 * @param frm AsprovaタブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(MgrecipeAsprovaListForm frm, String tantoCd) throws Exception;

	/**
	 * 設備グループコード取得
	 * @param recipeId レシピインデックス
	 * @param operationGroupCd 工程グループコード
	 * @return RecipeAsprovaList
	 */
	RecipeAsprovaList getResouceGroupCd(final BigDecimal recipeId,
			final String operationGroupCd);
}
