/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績検索 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionCommonsLogic {
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return RdirectionDirectionHeaderList
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	RdirectionDirectionHeaderList findByDirectionNo(String directionNo) throws NoDataException;
	/**
	 * 指図ヘッダ更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	void updateDirectionHeader(RdirectionDirectionHeaderList bean);

	/**
	 * 工程順序リストを取得する
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedureSetpNoForComboboxes>
	 */
	List<RdirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(final String directionNo);
	/**
	 * 工程順序コンボボックス作成
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createProcedureSetpNoCombobox(final String directionNo,  final boolean zero);

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

}
