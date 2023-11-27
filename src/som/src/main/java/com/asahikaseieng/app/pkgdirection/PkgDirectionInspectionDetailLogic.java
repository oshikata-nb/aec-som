/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図-検査詳細 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionInspectionDetailLogic {

	/**
	 * 検査詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装指図－検査詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setInspectionDetailForm(final HttpServletRequest request, final PkgDirectionInspectionDetailForm frm)
		throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う。
	 * 　各種名称マスタにデータがない場合はエラーとする。
	 * @param form 包装検査-詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final PkgDirectionInspectionDetailForm  form);

	/**
	 * 包装指図検査-更新処理を行う.
	 * @param frm 包装指図検査-詳細画面Form
	 * @param tantoCd 更新者コード
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	void update(final PkgDirectionInspectionDetailForm frm, final String tantoCd)
		throws PkgDirectionLogicException;

}
