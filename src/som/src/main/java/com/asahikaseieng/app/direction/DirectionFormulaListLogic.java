/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図－フォーミュラ検索 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionFormulaListLogic {

	/**
	 * 配合量の合計値取得処理を行う.
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return String 配合量の合計
	 */
	DirectionDirectionFormulaList getSumQty(final String directionNo, final BigDecimal procStepNo);

	/**
	 * 一覧検索処理を行う.
	 * @param directionNo 指図番号
	 * @param procStepNo 工程順序
	 * @return List<DirectionDirectionFormulaList> 一覧
	 * @throws NoDataException 例外
	 */
	List<DirectionDirectionFormulaList> getSearchList(
		final String directionNo, final BigDecimal procStepNo) throws NoDataException;

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 基本処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForAddDelList(final DirectionFormulaListForm form);

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForRegist(final List<DirectionDirectionFormulaList> searchFormList);

	/**
	 * 製造指図－フォーミュラ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	void regist(final DirectionFormulaListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception;

}
