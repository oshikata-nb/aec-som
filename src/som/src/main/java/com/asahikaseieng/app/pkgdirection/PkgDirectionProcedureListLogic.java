/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－工程一覧画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionProcedureListLogic {

	/**
	 * 包装指図－製造指図プロシージャ検索処理を行う.
	 * @param frm 包装指図－工程一覧画面 Form
	 * @return List<PkgDirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgDirectionDirectionProcedureList> getSearchList(final PkgDirectionProcedureListForm frm)
		throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造指図フォーミュラ、製造指図検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<PkgDirectionDirectionProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param frm 包装指図－工程一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgDirectionProcedureListForm frm);

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 包装指図－工程一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final PkgDirectionProcedureListForm frm, final String tantoCd) throws Exception;

}
