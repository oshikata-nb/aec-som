/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績－仕上タブ ロジック interface.
 * @author tosco
 */
public interface RdirectionFinishListLogic {

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	List<RdirectionDirectionFormulaList> getSearchList(final String directionNo)
			throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(
			final List<RdirectionDirectionFormulaList> searchList);

	/**
	 * 製造実績－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final RdirectionFinishListForm frm,
			final RdirectionDirectionHeaderList header, final String tantoCd)
			throws Exception;

	/**
	 * 更新時に開始、終了の実績日時が入っているかチェックする 開始、終了の実績日時がNullの場合エラー
	 * @param directionNo 製造指図No
	 * @param directionDivision 製造指図区分
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForResultDate(final String directionDivision,
			final String directionNo);
}
