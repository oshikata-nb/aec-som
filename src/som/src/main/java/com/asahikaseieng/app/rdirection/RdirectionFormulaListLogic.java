/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績－フォーミュラ検索 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionFormulaListLogic {

	/**
	 * 一覧検索処理を行う.
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return List<RdirectionDirectionFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<RdirectionDirectionFormulaList> getSearchList(
			final String directionNo, final BigDecimal procStepNo)
			throws NoDataException;

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 基本処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final RdirectionFormulaListForm form);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(
			final List<RdirectionDirectionFormulaList> searchFormList);

	/**
	 * 製造実績－フォーミュラ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final RdirectionFormulaListForm frm,
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

	/**
	 * 確定済みの配合で戻し場所に在庫があるかチェック
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return ActionMessages エラー内容
	 */
	boolean checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo);
}
