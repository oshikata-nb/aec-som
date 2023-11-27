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

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方 処方プロシージャ検索 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeProcedureListLogic {

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<MgrecipeList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RecipeProcedureList> getSearchList(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　処方フォーミュラ、処方検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<RecipeProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<RecipeProcedureList> searchProcList);

	/**
	 * 処方プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final MgrecipeProcedureListForm frm, final String tantoCd) throws Exception;

}
