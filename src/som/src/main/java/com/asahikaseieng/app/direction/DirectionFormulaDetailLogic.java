/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図－フォーミュラ詳細 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionFormulaDetailLogic {

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 製造指図工程テーブルより取得する
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return String[] 工程コード、工程名称
	 */
	String[] getOperationName(final String directionNo, final BigDecimal stepNo);

	/**
	 * 製造指図－フォーミュラ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @param lineNo   LINE_NO
	 * @return DirectionDirectionFormulaList 詳細データ
	 * @throws NoDataException データが存在しない例外
	 */
	DirectionDirectionFormulaList getByPrimaryKey(final String directionNo
			, final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param bean 製造指図－フォーミュラBean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final DirectionDirectionFormulaList bean);

	/**
	 * 製造指図－フォーミュラ更新処理を行う.
	 * @param bean 製造指図－フォーミュラBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception 例外
	 */
	void update(final DirectionDirectionFormulaList bean,
			final DirectionDirectionHeaderList header) throws Exception;

}
