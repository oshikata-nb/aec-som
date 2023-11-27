/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－共通ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionCommonsLogic {

	/**
	 * 生産ラインを全件取得する
	 * @return List<PkgdirectionLineForComboboxes>
	 */
	List<PkgDirectionLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param pkgdirectionLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	void setMgrecipeLineForComboboxesDao(
			final PkgDirectionLineForComboboxesDao pkgdirectionLineForComboboxesDao);

	/**
	 * 工程順序リストを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<DirectionProcedureSetpNoForComboboxes>
	 */
	List<PkgDirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(
		final BigDecimal directionDivision, final String directionNo);

	/**
	 * 工程順序コンボボックス作成
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createProcedureSetpNoCombobox(
		final BigDecimal directionDivision, final String directionNo,  final boolean zero);

	/**
	 * 包装指図番号で検索
	 * @param directionDivision 指図区分
	 * @param pkgDirectionNo 包装指図番号
	 * @return DirectionHeaderDetail
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	PkgDirectionDirectionHeaderDetail getEntity
		(final String directionDivision, final String pkgDirectionNo) throws NoDataException;

	/**
	 * 製品入出庫実績のレコード件数を取得する
	 * @param pkgDirectionNo 包装指図
	 * @param itemCd 品目コード
	 * @return int 件数
	 */
	int getJissekiSeihinCount(final String pkgDirectionNo, final String itemCd);

	/**
	 * 製造指図ヘッダーを未確定に更新する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param headerUpdateDate 更新前の製造指図ヘッダー更新日付（排他用）
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	void updateUnconfirmedHeader(final BigDecimal directionDivision, final String directionNo,
			final Timestamp headerUpdateDate, final String tantoCd)
		throws PkgDirectionLogicException;

	/**
	 * 包装計画削除
	 * @param directionNo 包装指図番号
	 * @throws PkgDirectionLogicException 削除エラー
	 */
	void deleteHosoKeikaku(final String directionNo) throws PkgDirectionLogicException;
}
