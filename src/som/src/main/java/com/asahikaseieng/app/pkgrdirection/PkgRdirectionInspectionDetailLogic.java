/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績-検査詳細 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionInspectionDetailLogic {

	/**
	 * 検査詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－検査詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	void setInspectionDetailForm(final HttpServletRequest request, final PkgRdirectionInspectionDetailForm frm)
		throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う。
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param form 包装実績-検査詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final PkgRdirectionInspectionDetailForm  form);

	/**
	 * 包装実績-検査更新処理を行う。
	 * @param frm 包装実績-検査詳細画面 Form
	 * @param tantoCd 更新者
	 */
	void update(final PkgRdirectionInspectionDetailForm frm, final String tantoCd);

}
