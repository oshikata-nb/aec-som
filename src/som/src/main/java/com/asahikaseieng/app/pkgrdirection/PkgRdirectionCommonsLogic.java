/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－共通ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionCommonsLogic {

	/**
	 * 生産ラインを全件取得する
	 * @return List<PkgdirectionLineForComboboxes>
	 */
	List<PkgRdirectionLineForComboboxes> getAllLines();

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
			final PkgRdirectionLineForComboboxesDao pkgdirectionLineForComboboxesDao);

	/**
	 * 工程順序リストを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionProcedureSetpNoForComboboxes>
	 */
	List<PkgRdirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(
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
	PkgRdirectionDirectionHeaderDetail getEntity
		(final String directionDivision, final String pkgDirectionNo) throws NoDataException;

	/**
	 * 製造指図ヘッダーを包装実績入力済に更新する
	 * @param status 更新前の指図ステータス
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param headerUpdateDate 更新前の製造指図ヘッダー更新日付（排他用）
	 * @param tantoCd 更新者
	 */
	void updateInputResultHeader(final String status, final BigDecimal directionDivision,
		final String directionNo, final Timestamp headerUpdateDate, final String tantoCd);
}
