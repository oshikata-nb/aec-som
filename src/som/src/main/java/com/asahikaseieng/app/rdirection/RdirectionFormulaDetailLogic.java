/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図－フォーミュラ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionFormulaDetailLogic {

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 製造指図工程テーブルより取得する
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final String directionNo, final BigDecimal stepNo);

	/**
	 * 製造指図－フォーミュラ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return DirectionDirectionFormulaList 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	RdirectionDirectionFormulaList getByPrimaryKey(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo)
			throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param bean 製造実績－フォーミュラBean
	 * @param initBean 初期検索データ
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final RdirectionDirectionFormulaList bean,
			final RdirectionDirectionFormulaList initBean);

	/**
	 * 製造指図－フォーミュラ更新処理を行う.
	 * @param bean 製造指図－フォーミュラBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception 例外
	 */
	void update(final RdirectionDirectionFormulaList bean,
			final RdirectionDirectionHeaderList header) throws Exception;

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
	ActionMessages checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo);

}
