/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.direction.DirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図検索 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionCommonsLogic {
	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return DirectionDirectionHeaderList
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	DirectionDirectionHeaderList findByDirectionNo(String directionNo) throws NoDataException;
	/**
	 * 製造指図フォーミュラを更新する
	 * （nullなフィールド以外の値を更新する。)
	 * @param bean DirectionDirectionFormulaList
	 */
	void updateFormulaUnlessNull(DirectionDirectionFormulaList bean);

	/**
	 * 工程順序リストを取得する
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedureSetpNoForComboboxes>
	 */
	List<DirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(final String directionNo);
	/**
	 * 工程順序コンボボックス作成
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createProcedureSetpNoCombobox(final String directionNo,  final boolean zero);
	/**
	 * 指図ヘッダ更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	void update(DirectionDirectionHeaderList bean);

	/**
	 * 製造計画・指示削除
	 * @param directioNo 製造指図番号
	 * @throws DirectionLogicException 削除エラー
	 */
	void deleteSeizo(final String directioNo) throws DirectionLogicException;

	/**
	 * 製造計画の製造状況をチェックする.<br>
	 * 　製造状況が0:未処理以外はエラーとする。
	 * @param directionNo 製造指図番号
	 * @return String エラーメッセージキー
	 */
	String checkSeizoKeikaku(final String directionNo);

}
