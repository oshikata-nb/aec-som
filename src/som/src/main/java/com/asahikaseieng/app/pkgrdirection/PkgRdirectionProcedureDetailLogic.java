/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;


import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－工程詳細画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionProcedureDetailLogic {

	/**
	 * 工程詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－工程詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setProcedureDetailForm(final HttpServletRequest request, final PkgRdirectionProcedureDetailForm frm)
		throws NoDataException;

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param frm 包装実績－工程詳細画面 Form
	 * @param tantoCd 更新者
	 */
	void update(final PkgRdirectionProcedureDetailForm frm, final String tantoCd);
}
