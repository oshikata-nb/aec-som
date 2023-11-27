/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－仕上タブ ロジック interface.
 * @author tosco
 */
public interface PkgRdirectionFinishListLogic {

	/**
	 * 
	 * 一覧検索処理を行う.
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionDirectionFormulaList> 検索結果一覧
	 * @throws NoDataException 例外
	 */
	List<PkgRdirectionDirectionFormulaList> getSearchList(
			final BigDecimal directionDivision, final String directionNo)
			throws NoDataException;

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param frm 包装実績－仕上一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final PkgRdirectionFinishListForm frm);

	/**
	 * 包装実績－フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final PkgRdirectionFinishListForm frm, final String tantoCd)
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
