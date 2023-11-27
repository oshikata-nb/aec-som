/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図－配合詳細画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionFormulaDetailLogic {

	/**
	 * 配合詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装指図－配合詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setFormulaDetailForm(final HttpServletRequest request, final PkgDirectionFormulaDetailForm frm)
		throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * @param frm 包装指図－配合詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final PkgDirectionFormulaDetailForm frm);

	/**
	 * 製造指図フォーミュラ更新処理を行う.
	 * @param frm 包装指図－配合詳細画面 Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	void update(final PkgDirectionFormulaDetailForm frm, final String tantoCd)
		throws PkgDirectionLogicException;

}
