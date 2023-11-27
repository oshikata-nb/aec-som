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

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 原処方-工程  ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeProcedureListLogic {

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<GrecipeRecipeProcedureList> getSearchList(final BigDecimal recipeId) throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　処方フォーミュラ、処方検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<GrecipeRecipeProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<GrecipeRecipeProcedureList> searchProcList);

	/**
	 * 処方プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final GrecipeProcedureListForm frm, final String tantoCd) throws Exception;

}
