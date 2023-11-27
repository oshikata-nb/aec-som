/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－配合詳細画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionFormulaDetailLogic {

	/**
	 * 配合詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－配合詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setFormulaDetailForm(final HttpServletRequest request, final PkgRdirectionFormulaDetailForm frm)
		throws NoDataException;

	/**
	 * 製造指図フォーミュラ更新処理を行う.
	 * @param frm 包装実績－配合詳細画面 Form
	 * @param tantoCd 更新者
	 */
	void update(final PkgRdirectionFormulaDetailForm frm, final String tantoCd);

}
