/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;


/**
 * 製造指図－仕上タブ ロジック interface.
 * @author tosco
 */
public interface DirectionFinishListLogic {

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	List<DirectionDirectionFormulaList> getSearchList(final String directionNo) throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<DirectionDirectionFormulaList> searchList);

	/**
	 * 製造指図－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final DirectionFinishListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception;

}
