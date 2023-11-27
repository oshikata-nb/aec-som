/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－工程詳細画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionProcedureDetailLogic {

	/**
	 * 工程詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装指図－工程詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setProcedureDetailForm(final HttpServletRequest request, final PkgDirectionProcedureDetailForm frm)
		throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param frm 包装指図－工程詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final PkgDirectionProcedureDetailForm frm);

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param frm 包装指図－工程詳細画面 Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	void update(final PkgDirectionProcedureDetailForm frm, final String tantoCd)
		throws PkgDirectionLogicException;
}
