/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－工程一覧画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionProcedureListLogic {

	/**
	 * 包装実績－製造指図プロシージャ検索処理を行う.
	 * @param frm 包装実績－工程一覧画面 Form
	 * @return List<PkgRdirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgRdirectionDirectionProcedureList> getSearchList(final PkgRdirectionProcedureListForm frm)
		throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造指図フォーミュラ、製造指図検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<PkgRdirectionDirectionProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param frm 包装実績－工程一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgRdirectionProcedureListForm frm);

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 包装実績－工程一覧画面 Form
	 * @param tantoCd 担当者コード
	 */
	void regist(final PkgRdirectionProcedureListForm frm, final String tantoCd);

}
