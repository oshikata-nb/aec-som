/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績 検査タブ ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionInspectionListLogic {

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 製造指図工程テーブルより取得する
	 * @param form 包装実績 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final PkgRdirectionInspectionListForm form);

	/**
	 * 包装実績-検査情報の検索処理を行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return List<PkgRdirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgRdirectionDirectionInspectionList> getSearchList(
		final PkgRdirectionInspectionListForm form) throws NoDataException;

	/**
	 * 行追加時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddList(final PkgRdirectionInspectionListForm form);

	/**
	 * 行削除時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final PkgRdirectionInspectionListForm form);

	/**
	 * 包装実績-検査情報の登録時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgRdirectionInspectionListForm form);

	/**
	 * 包装実績-検査情報の登録処理を行う.
	 * @param form 包装実績 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final PkgRdirectionInspectionListForm form, final String tantoCd) throws Exception;

}
