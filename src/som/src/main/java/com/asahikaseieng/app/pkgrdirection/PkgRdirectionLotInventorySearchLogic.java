/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.lang.reflect.InvocationTargetException;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－ロット検索画面ロジック interface.
 * @author tosco
 */
public interface PkgRdirectionLotInventorySearchLogic {

	/**
	 * ロット一覧編集処理
	 * @param frm 包装実績－ロット検索画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setList(final PkgRdirectionLotInventorySearchForm frm)
			throws NoDataException;

	/**
	 * 製造指図フォーミュラ登録処理
	 * @param frm 包装実績－ロット検索画面 Form
	 * @param tantoCd 登録者
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void regist(final PkgRdirectionLotInventorySearchForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 更新時に開始、終了の実績日時が入っているかチェックする 開始、終了の実績日時がNullの場合エラー
	 * @param directionNo 製造指図No
	 * @param directionDivision 製造指図区分
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForResultDate(final String directionDivision,
			final String directionNo);
}
