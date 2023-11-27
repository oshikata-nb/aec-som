/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図 配合一覧画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionFormulaListLogic {

	/**
	 * 配合一覧検索処理
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return List<PkgDirectionDirectionFormulaList> データ
	 * @throws NoDataException データなし
	 */
	List<PkgDirectionDirectionFormulaList> getSearchList(final PkgDirectionFormulaListForm frm)
		throws NoDataException;

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final PkgDirectionFormulaListForm frm);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param frm 包装指図－配合一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgDirectionFormulaListForm frm);

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 包装指図－配合一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final PkgDirectionFormulaListForm frm, final String tantoCd) throws Exception;

}
