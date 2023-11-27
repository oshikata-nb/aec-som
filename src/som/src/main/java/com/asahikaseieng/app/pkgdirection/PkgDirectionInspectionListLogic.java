/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図 検査タブ ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionInspectionListLogic {

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 包装指図工程テーブルより取得する
	 * @param form 包装指図-検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final PkgDirectionInspectionListForm form);

	/**
	 * 包装指図検査情報の検索処理を行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return List<PkgDirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgDirectionDirectionInspectionList> getSearchList(
		final PkgDirectionInspectionListForm form) throws NoDataException;

	/**
	 * 行追加時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddList(final PkgDirectionInspectionListForm form);

	/**
	 * 行削除時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final PkgDirectionInspectionListForm form);

	/**
	 * 包装指図検査情報の登録時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgDirectionInspectionListForm form);

	/**
	 * 包装指図検査情報の登録処理を行う.
	 * @param form 包装指図-検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final PkgDirectionInspectionListForm form, final String tantoCd) throws Exception;

}
