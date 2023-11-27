/*
 * Created on 2009/02/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.pkgdirection;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図ヘッダー ロジッククラス interface. 
 * @author tosco
 */
public interface PkgDirectionHeaderLogic {

	/**
	 * 検索処理
	 * @param frm 包装指図ヘッダー Form
	 * @return DirectionHeader
	 * @throws NoDataException データが存在しない例外
	 */
	DirectionHeader getEntity(final PkgDirectionHeaderForm frm) throws NoDataException;

	/**
	 * 更新処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 */
	void update(final PkgDirectionHeaderForm frm, final String tantoCd);

	/**
	 * 登録処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	void insert(final PkgDirectionHeaderForm frm, final String tantoCd)
		throws PkgDirectionLogicException, IllegalAccessException, InvocationTargetException;

	/**
	 * 削除処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	void delete(final PkgDirectionHeaderForm frm, final String tantoCd)
		throws PkgDirectionLogicException;

	/**
	 * 包装ラインのチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	ActionMessage checkPackageLine(final PkgDirectionHeaderForm frm);

	/**
	 * 品目コードのチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	ActionMessage checkItemCd(final PkgDirectionHeaderForm frm);

	/**
	 * 基本処方のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessages エラーメッセージ
	 */
	ActionMessages checkRecipe(final PkgDirectionHeaderForm frm);

	/**
	 * 生産予定数量のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @param request リクエスト
	 * @return ActionMessage エラーメッセージ
	 */
	ActionMessage checkPlanedQty(final PkgDirectionHeaderForm frm, final HttpServletRequest request);

	/**
	 * 製造指図番号のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	ActionMessages checkDirectionNo(final PkgDirectionHeaderForm frm);

}
