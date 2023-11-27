/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 製造実績　工程タブ  ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionProcedureListLogic {

	/**
	 * 製造実績プロシージャ検索処理を行う.
	 * @param directionNo 実績番号
	 * @return List<RdirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RdirectionDirectionProcedureList> getSearchList(final String directionNo) throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造実績フォーミュラ、製造実績検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<RdirectionDirectionProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<RdirectionDirectionProcedureList> searchProcList);

	/**
	 * 製造実績プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final RdirectionProcedureListForm frm, final String tantoCd) throws Exception;

}
