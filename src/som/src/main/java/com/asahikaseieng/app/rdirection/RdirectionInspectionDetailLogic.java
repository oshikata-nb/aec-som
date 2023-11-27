/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績-検査詳細 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionInspectionDetailLogic {

	/**
	 * ヘッダー部のデータを製造実績ヘッダーから取得する。
	 * @param directionNo 指図番号
	 * @param stepNo ステップNO
	 * @return RdirectionDirectionHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	RdirectionDirectionHeaderList getInspectionHeader(
			final String directionNo, final BigDecimal stepNo) throws NoDataException;

	/**
	 * 製造実績検査-検索処理を行う。
	 * @param directionNo 指図番号
	 * @param stepNo   ステップNO
	 * @param lineNo	ラインNO
	 * @return RdirectionDirectionInspectionList データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	RdirectionDirectionInspectionList getEntity(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException;

	/**
	 * 更新時のマスタチェックを行う。
	 * 　各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 製造実績検査Bean
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkForUpdate(final RdirectionDirectionInspectionList bean);

	/**
	 * 製造実績検査-更新処理を行う。
	 * @param bean 製造実績検査Bean
	 * @throws Exception 例外
	 */
	void update(final RdirectionDirectionInspectionList bean) throws Exception;

}
