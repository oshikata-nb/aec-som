/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 原処方 検査タブ ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeInspectionListLogic {

	/**
	 * レシピインデックス、STEP_NOに該当する工程コード、工程名称を
	 * 処方工程テーブルより取得する
	 * @param form 原処方 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final GrecipeInspectionListForm form);

	/**
	 * 処方検査情報の検索処理を行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return List<GrecipeRecipeInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<GrecipeRecipeInspectionList> getSearchList(final GrecipeInspectionListForm form) throws NoDataException;

	/**
	 * 行追加時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddList(final GrecipeInspectionListForm form);

	/**
	 * 行削除時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final GrecipeInspectionListForm form);

	/**
	 * 処方検査情報の登録時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final GrecipeInspectionListForm form);

	/**
	 * 処方検査情報の登録処理を行う.
	 * @param form 原処方 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final GrecipeInspectionListForm form, final String tantoCd) throws Exception;

}
