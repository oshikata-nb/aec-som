/*
 * Created on 2009/02/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHoso;
import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHosoDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.pkgdirection.PkgDirectionProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetailDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionJissekiSeihinList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionJissekiSeihinListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 包装指図－共通ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionCommonsLogicImpl implements PkgDirectionCommonsLogic {

	/** 生産ラインコンボボックス用Dao */
	private PkgDirectionLineForComboboxesDao pkgDirectionLineForComboboxesDao;

	/** 包装指図－製造指図ヘッダー情報用Dao */
	private PkgDirectionDirectionHeaderDetailDao pkgDirectionHeaderDetailDao;

	/** 包装指図－製品入出庫実績Dao */
	private PkgDirectionJissekiSeihinListDao pkgDirectionJissekiSeihinListDao;

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 包装計画Dao */
	private KeikakuHosoDao keikakuHosoDao;

	/** 製造指図－工程順序コンボボックス用Dao */
	private PkgDirectionProcedureSetpNoForComboboxesDao pkgDirectionProcedureSetpNoForComboboxesDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionCommonsLogicImpl() {
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<PkgdirectionLineForComboboxes>
	 */
	public List<PkgDirectionLineForComboboxes> getAllLines() {
		return pkgDirectionLineForComboboxesDao.findAll();
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
			item.setValues(PkgDirectionConst.COMBO_ALL_VALUE.toString());
			item.setLabales(PkgDirectionConst.COMBO_ALL_LABEL);
			res.add(item);
		}

		// 生産ライン検索
		List<PkgDirectionLineForComboboxes> lineList = getAllLines();
		for (PkgDirectionLineForComboboxes bean : lineList) {
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
	 * @return List<DirectionProcedureSetpNoForComboboxes>
	 */
	public List<PkgDirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final BigDecimal directionDivision, final String directionNo) {
		return pkgDirectionProcedureSetpNoForComboboxesDao.findByDirectionNo(
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
			List<PkgDirectionProcedureSetpNoForComboboxes> list = getProcedureSetpNoList(
				directionDivision, directionNo);
			if (list != null) {
				for (PkgDirectionProcedureSetpNoForComboboxes bean : list) {
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
	public PkgDirectionDirectionHeaderDetail getEntity(
			final String directionDivision, final String pkgDirectionNo)
			throws NoDataException {
		PkgDirectionDirectionHeaderDetail header = null;
		BigDecimal decDirectionDivision = new BigDecimal(directionDivision);
		header = pkgDirectionHeaderDetailDao.getEntity(decDirectionDivision,
			pkgDirectionNo);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 製品入出庫実績のレコード件数を取得する
	 * @param pkgDirectionNo 包装指図
	 * @param itemCd 品目コード
	 * @return int 件数
	 */
	public int getJissekiSeihinCount(final String pkgDirectionNo,
			final String itemCd) {
		String hososashizuNo = null;
		String seihinCode = null;
		List<PkgDirectionJissekiSeihinList> list;

		hososashizuNo = AecStringUtils.editSpecifiedSize(pkgDirectionNo, 11);
		seihinCode = AecStringUtils.editSpecifiedSize(itemCd, 8);

		// 製品入出庫実績を検索
		list = pkgDirectionJissekiSeihinListDao.getList(hososashizuNo,
			seihinCode);

		return list.size();
	}

	/**
	 * 製造指図ヘッダーを未確定に更新する
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param headerUpdateDate 更新前の製造指図ヘッダー更新日付（排他用）
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	public void updateUnconfirmedHeader(final BigDecimal directionDivision,
			final String directionNo, final Timestamp headerUpdateDate,
			final String tantoCd) throws PkgDirectionLogicException {

		try {

			// 製造指図ヘッダー検索
			DirectionHeader bean = directionHeaderDao.getEntity(
				directionDivision, directionNo);
			if (bean == null) {

				// 更新データなし
				throw new OptimisticLockRuntimeException();
			}

			// 指図書発行済みの場合
			if (PkgDirectionConst.DIRECTION_STATUS_ISSUE.equals(bean
					.getDirectionStatus())) {
				// 在庫更新処理
				StockinoutForDirection stock = new StockinoutForDirection(
						zaiCtrlDao);
				String errMsg = "errors.pkgdirection.stock.update";
				try {
					/* 在庫更新－包装・製造指図確定取消 */
					if (!stock.deFixDirection(bean.getDirectionDivision(), bean
							.getDirectionNo(), tantoCd)) {
						throw new PkgDirectionLogicException(errMsg, "");
					}
					/* 在庫更新－配合指図確定取消 */
					if (!stock.deFixFormula(bean.getDirectionDivision(), bean
							.getDirectionNo(), tantoCd)) {
						throw new PkgDirectionLogicException(errMsg, "");
					}
				} catch (LogicExceptionEx e) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			}

			// 検索時の更新日付を設定
			bean.setUpdateDate(headerUpdateDate);

			// 未確定を設定
			bean
					.setDirectionStatus(PkgDirectionConst.DIRECTION_STATUS_UN_CONFIRMED);

			// 更新者を設定
			bean.setUpdatorCd(tantoCd);

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

	/**
	 * 包装計画削除
	 * @param directionNo 包装指図番号
	 * @throws PkgDirectionLogicException 削除エラー
	 */
	public void deleteHosoKeikaku(final String directionNo)
			throws PkgDirectionLogicException {
		String errMsg = "errors.pkgdirection.update.if.table";
		try {
			String hososashizuNo = AecStringUtils.editSpecifiedSize(
				directionNo, 11);
			KeikakuHoso hoso = keikakuHosoDao.getEntity(hososashizuNo);
			if (hoso != null) {
				keikakuHosoDao.delete(hoso);
			}
		} catch (SQLRuntimeException e) {
			throw new PkgDirectionLogicException(errMsg, "");
		} catch (NotSingleRowUpdatedRuntimeException e) {
			throw new PkgDirectionLogicException(errMsg, "");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産ラインコンボボックス用Dao設定.
	 * @param pkgdirectionLineForComboboxesDao 生産ラインコンボボックス用Dao
	 */
	public void setMgrecipeLineForComboboxesDao(
			final PkgDirectionLineForComboboxesDao pkgdirectionLineForComboboxesDao) {
		this.pkgDirectionLineForComboboxesDao = pkgdirectionLineForComboboxesDao;
	}

	/**
	 * 包装指図－製造指図ヘッダー情報用Dao設定.
	 * @param pkgDirectionHeaderDetailDao 包装指図－製造指図ヘッダー情報用Dao
	 */
	public void setPkgDirectionHeaderDetailDao(
			final PkgDirectionDirectionHeaderDetailDao pkgDirectionHeaderDetailDao) {
		this.pkgDirectionHeaderDetailDao = pkgDirectionHeaderDetailDao;
	}

	/**
	 * 包装指図－製品入出庫実績Dao設定.
	 * @param pkgDirectionJissekiSeihinListDao 包装指図－製品入出庫実績Dao
	 */
	public void setPkgDirectionJissekiSeihinListDao(
			final PkgDirectionJissekiSeihinListDao pkgDirectionJissekiSeihinListDao) {
		this.pkgDirectionJissekiSeihinListDao = pkgDirectionJissekiSeihinListDao;
	}

	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 包装計画Dao設定.
	 * @param keikakuHosoDao 包装計画Dao
	 */
	public void setKeikakuHosoDao(final KeikakuHosoDao keikakuHosoDao) {
		this.keikakuHosoDao = keikakuHosoDao;
	}

	/**
	 * 製造指図－工程順序コンボボックス用Dao設定
	 * @param pkgDirectionProcedureSetpNoForComboboxesDao 製造指図－工程順序コンボボックス用Dao
	 */
	public void setPkgDirectionProcedureSetpNoForComboboxesDao(
			final PkgDirectionProcedureSetpNoForComboboxesDao pkgDirectionProcedureSetpNoForComboboxesDao) {
		this.pkgDirectionProcedureSetpNoForComboboxesDao = pkgDirectionProcedureSetpNoForComboboxesDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
