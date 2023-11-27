/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図-検査詳細 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionInspectionDetailLogic {

	/**
	 * ヘッダー部のデータを製造指図ヘッダーから取得する。
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return DirectionDirectionHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	DirectionDirectionHeaderList getInspectionHeader(
			final String directionNo, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 製造指図検査-検索処理を行う。
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @param lineNo	LINE_NO
	 * @return DirectionDirectionInspectionList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	DirectionDirectionInspectionList getEntity(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う。
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 製造指図検査Bean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final DirectionDirectionInspectionList bean);

	/**
	 * 製造指図検査-更新処理を行う。
	 * @param bean 製造指図検査Bean
	 * @param header 製造指図ヘッダ
	 * @throws Exception 例外
	 */
	void update(final DirectionDirectionInspectionList bean,
			final DirectionDirectionHeaderList header) throws Exception;

}
