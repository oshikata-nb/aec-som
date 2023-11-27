/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgrdirection.PkgRdirectionProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetailDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 包装実績－共通ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionCommonsLogicImpl implements PkgRdirectionCommonsLogic {

	/** 生産ラインコンボボックス用Dao */
	private PkgRdirectionLineForComboboxesDao pkgRdirectionLineForComboboxesDao;

	/** 包装実績－製造指図ヘッダー情報用Dao */
	private PkgRdirectionDirectionHeaderDetailDao pkgRdirectionHeaderDetailDao;

	/** 製造指図－工程順序コンボボックス用Dao */
	private PkgRdirectionProcedureSetpNoForComboboxesDao pkgRdirectionProcedureSetpNoForComboboxesDao;

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionCommonsLogicImpl() {
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<PkgdirectionLineForComboboxes>
	 */
	public List<PkgRdirectionLineForComboboxes> getAllLines() {
		return pkgRdirectionLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox(final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(PkgRdirectionConst.COMBO_ALL_VALUE.toString());
			item.setLabales(PkgRdirectionConst.COMBO_ALL_LABEL);
			res.add(item);
		}

		// 生産ライン検索
		List<PkgRdirectionLineForComboboxes> lineList = getAllLines();
		for (PkgRdirectionLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}
		return res;
	}

	/**
	 * 工程順序リストを取得する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @return List<PkgRdirectionProcedureSetpNoForComboboxes>
	 */
	public List<PkgRdirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final BigDecimal directionDivision, final String directionNo) {
		return pkgRdirectionProcedureSetpNoForComboboxesDao.findByDirectionNo(
			directionDivision, directionNo);
	}

	/**
	 * 工程順序コンボボックス作成
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createProcedureSetpNoCombobox(
			final BigDecimal directionDivision, final String directionNo,
			final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("0");
			res.add(item);
		}
		if (directionNo != null) {

			// 工程順序検索
			List<PkgRdirectionProcedureSetpNoForComboboxes> list = getProcedureSetpNoList(
				directionDivision, directionNo);
			if (list != null) {
				for (PkgRdirectionProcedureSetpNoForComboboxes bean : list) {
					ComboBoxItems item = new ComboBoxItems();
					item.setValues(bean.getStepNo().toString());
					item.setLabales(bean.getSeq().toString());
					res.add(item);
				}
			}
		}
		return res;
	}

	/**
	 * 包装指図番号で検索
	 * @param directionDivision 指図区分
	 * @param pkgDirectionNo 包装指図番号
	 * @return DirectionHeaderDetail
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	public PkgRdirectionDirectionHeaderDetail getEntity(
			final String directionDivision, final String pkgDirectionNo)
			throws NoDataException {
		PkgRdirectionDirectionHeaderDetail header = null;
		BigDecimal decDirectionDivision = new BigDecimal(directionDivision);
		header = pkgRdirectionHeaderDetailDao.getEntity(decDirectionDivision,
			pkgDirectionNo);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 製造指図ヘッダーを包装実績入力済に更新する
	 * @param status 更新前の指図ステータス
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param headerUpdateDate 更新前の製造指図ヘッダー更新日付（排他用）
	 * @param tantoCd 更新者
	 */
	public void updateInputResultHeader(final String status,
			final BigDecimal directionDivision, final String directionNo,
			final Timestamp headerUpdateDate, final String tantoCd) {

		try {

			// 製造指図ヘッダー検索
			DirectionHeader bean = directionHeaderDao.getEntity(
				directionDivision, directionNo);
			if (bean == null) {

				// 更新データなし
				throw new OptimisticLockRuntimeException();
			}

			// 検索時の更新日付を設定
			bean.setUpdateDate(headerUpdateDate);

			// 更新者を設定
			bean.setUpdatorCd(tantoCd);

			BigDecimal directionStatus = null;
			if (status != null) {
				directionStatus = new BigDecimal(status);
			}
			// if
			// ((PkgRdirectionConst.DIRECTION_STATUS_ISSUE.equals(directionStatus))
			// ||
			// (PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS.equals(directionStatus))
			// ||
			// (PkgRdirectionConst.DIRECTION_STATUS_OUTPUT_DOC.equals(directionStatus)))
			// {
			// 3:包装実績入力済 or 4：包装記録済の場合は、3:包装実績入力済にステータスを更新するよう変更。
			// 包装実績でヘッダー情報登録時で更新した場合のみ、2：指図書発行済→3:包装実績入力済になる
			if ((PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS
					.equals(directionStatus))
					|| (PkgRdirectionConst.DIRECTION_STATUS_OUTPUT_DOC
							.equals(directionStatus))) {
				bean
						.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS);
			}

			// 更新処理
			int updateNum = directionHeaderDao.update(bean);
			if (updateNum != 1) {

				// 更新データなし
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {

			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産ラインコンボボックス用Dao設定.
	 * @param pkgdirectionLineForComboboxesDao 生産ラインコンボボックス用Dao
	 */
	public void setMgrecipeLineForComboboxesDao(
			final PkgRdirectionLineForComboboxesDao pkgdirectionLineForComboboxesDao) {
		this.pkgRdirectionLineForComboboxesDao = pkgdirectionLineForComboboxesDao;
	}

	/**
	 * 包装実績－製造指図ヘッダー情報用Dao設定.
	 * @param pkgRdirectionHeaderDetailDao 包装指図－製造指図ヘッダー情報用Dao
	 */
	public void setPkgRdirectionHeaderDetailDao(
			final PkgRdirectionDirectionHeaderDetailDao pkgRdirectionHeaderDetailDao) {
		this.pkgRdirectionHeaderDetailDao = pkgRdirectionHeaderDetailDao;
	}

	/**
	 * 製造指図－工程順序コンボボックス用Dao設定
	 * @param pkgRdirectionProcedureSetpNoForComboboxesDao 製造指図－工程順序コンボボックス用Dao
	 */
	public void setPkgRdirectionProcedureSetpNoForComboboxesDao(
			final PkgRdirectionProcedureSetpNoForComboboxesDao pkgRdirectionProcedureSetpNoForComboboxesDao) {
		this.pkgRdirectionProcedureSetpNoForComboboxesDao = pkgRdirectionProcedureSetpNoForComboboxesDao;
	}

	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

}
