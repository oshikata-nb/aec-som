/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図-検査タブ ロジッククラス interface.
 * @author tosco
 */
public interface DirectionInspectionListLogic {

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を
	 * 製造指図工程テーブルより取得する
	 * @param form 製造指図-検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final DirectionInspectionListForm form);

	/**
	 * 製造指図検査情報の検索処理を行う
	 * @param form 製造指図-検査一覧タブ Form
	 * @return List<DirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<DirectionDirectionInspectionList> getSearchList(
		final DirectionInspectionListForm form) throws NoDataException;

	/**
	 * 行追加時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddList(final DirectionInspectionListForm form);

	/**
	 * 行削除時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final DirectionInspectionListForm form);

	/**
	 * 製造指図検査情報の登録時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final DirectionInspectionListForm form);

	/**
	 * 製造指図検査情報の登録処理を行う.
	 * @param form 製造指図 検査一覧タブ Form
	 * @param header 製造指図ヘッダ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final DirectionInspectionListForm form,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception;

}
