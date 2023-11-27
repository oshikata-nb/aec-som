/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本製造指図 製造指図プロシージャ検索 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionProcedureListLogic {

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<DirectionDirectionProcedureList> getSearchList(final String directionNo) throws NoDataException;

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造指図フォーミュラ、製造指図検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForDelList(final List<DirectionDirectionProcedureList> searchProcList);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<DirectionDirectionProcedureList> searchProcList);

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void regist(final DirectionProcedureListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception;

}
