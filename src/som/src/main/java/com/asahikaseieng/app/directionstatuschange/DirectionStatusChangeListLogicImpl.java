/*
 * Created on 2009/05/28
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.directionstatuschange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.jissekiseizohead.JissekiSeizoHead;
import com.asahikaseieng.dao.entity.jissekiseizohead.JissekiSeizoHeadDao;
import com.asahikaseieng.dao.nonentity.comboboxes.directionstatuschange.DirectionStatusChangeLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.directionstatuschange.DirectionStatusChangeLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeList;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeListDao;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangePagerCondition;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeDetailListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeFormulaListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeHeaderListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeInspectionListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeListConditionForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeProcedureListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeProcedureListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造指図ステータス変更 ロジック実装クラス
 * @author tosco
 */
public class DirectionStatusChangeListLogicImpl implements
		DirectionStatusChangeListLogic {

	/** 指図ステータス－指図書発行済 */
	private static final BigDecimal DIRECTION_STATUS_ISSUED = new BigDecimal(2);

	/** 指図ステータス－製造開始 */
	private static final BigDecimal DIRECTION_STATUS_START = new BigDecimal(3);

	/** 指図ステータス－中間品最終検査待 */
	private static final BigDecimal DIRECTION_STATUS_INSPECTION_WAIT = new BigDecimal(
			4);

	/** 指図ステータス－中間品最終検査済 */
	private static final BigDecimal DIRECTION_STATUS_INSPECTED = new BigDecimal(
			5);

	/** 指図ステータス－FA受信 */
	private static final BigDecimal DIRECTION_STATUS_FA_RECEIVE = new BigDecimal(
			6);

	/** 製造指図ステータス変更画面用のDao * */
	private DirectionStatusChangeListDao directionStatusChangeListDao;

	/** 生産ラインコンボボックス用DAO */
	private DirectionStatusChangeLineForComboboxesDao directionStatusChangeLineForComboboxesDao;

	/** 製造指図ヘッダーテーブル用のDao * */
	private DirectionHeaderDao directionHeaderDao;

	/** 計装ＩＦ 製造計画テーブル用Dao */
	private JissekiSeizoHeadDao jissekiSeizoHeadDao;

	private DirectionStatusChangeHeaderListForReportDao directionStatusChangeHeaderListForReportDao;

	private DirectionStatusChangeDetailListForReportDao directionStatusChangeDetailListForReportDao;

	private DirectionStatusChangeProcedureListForReportDao directionStatusChangeProcedureListForReportDao;

	private DirectionStatusChangeFormulaListForReportDao directionStatusChangeFormulaListForReportDao;

	private DirectionStatusChangeInspectionListForReportDao directionStatusChangeInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public DirectionStatusChangeListLogicImpl() {
	}

	/**
	 * 製造指図ステータス変更 検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<MidInspectCompList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<DirectionStatusChangeList> getSearchList(
			final DirectionStatusChangePagerCondition condition)
			throws NoDataException {

		// 一覧検索
		List<DirectionStatusChangeList> list = directionStatusChangeListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeListForReport> 検索結果リスト
	 */
	public List<DirectionStatusChangeHeaderListForReport> getHeaderReportList(
			final DirectionStatusChangeListConditionForReport condition) {
		List<DirectionStatusChangeHeaderListForReport> list = directionStatusChangeHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeDetailListForReport> 検索結果リスト
	 */
	public List<DirectionStatusChangeDetailListForReport> getDetailReportList(
			final DirectionStatusChangeListConditionForReport condition) {
		List<DirectionStatusChangeDetailListForReport> list = directionStatusChangeDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeDetailListForReport> 検索結果リスト
	 */
	public List<DirectionStatusChangeProcedureListForReport> getProcedureReportList(
			final DirectionStatusChangeListConditionForReport condition) {

		List<DirectionStatusChangeProcedureListForReport> list = directionStatusChangeProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeFormulaListForReport> 検索結果リスト
	 */
	public List<DirectionStatusChangeFormulaListForReport> getFormulaReportList(
			final DirectionStatusChangeListConditionForReport condition) {

		List<DirectionStatusChangeFormulaListForReport> list = directionStatusChangeFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeInspectionListForReport> 検索結果リスト
	 */
	public List<DirectionStatusChangeInspectionListForReport> getInspectionReportList(
			final DirectionStatusChangeListConditionForReport condition) {

		List<DirectionStatusChangeInspectionListForReport> list = directionStatusChangeInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<DirectionStatusChangeLineForComboboxes>
	 */
	public List<DirectionStatusChangeLineForComboboxes> getAllLines() {
		return directionStatusChangeLineForComboboxesDao.findAll();
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
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}

		// 生産ライン検索
		List<DirectionStatusChangeLineForComboboxes> lineList = getAllLines();
		for (DirectionStatusChangeLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}
		return res;
	}

	/**
	 * ステータス変更
	 * @param bean データ
	 * @param tantoCd 更新者
	 */
	public void changeStatus(final DirectionStatusChangeList bean,
			final String tantoCd) {
		try {
			DirectionHeader updBean = null;
			BigDecimal directionDivision = bean.getDirectionDivision();
			String directNo = bean.getDirectionNo();

			// 製造指図ヘッダーを検索する
			updBean = directionHeaderDao.getEntity(directionDivision, directNo);
			if (updBean == null) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

			// ステータスを変更
			// 【 2:指図書発行済 】 → 【 4:中間品最終検査待 】
			// 【 3:製造開始 】 → 【 4:中間品最終検査待 】
			// 【 5:中間品最終検査済 】 → 【 6:FA受信 】
			if (DIRECTION_STATUS_ISSUED.equals(updBean.getDirectionStatus())) {
				updBean.setDirectionStatus(DIRECTION_STATUS_INSPECTION_WAIT);
			} else if (DIRECTION_STATUS_START.equals(updBean
					.getDirectionStatus())) {
				updBean.setDirectionStatus(DIRECTION_STATUS_INSPECTION_WAIT);
			} else if (DIRECTION_STATUS_INSPECTED.equals(updBean
					.getDirectionStatus())) {
				updBean.setDirectionStatus(DIRECTION_STATUS_FA_RECEIVE);
			}
			updBean.setUpdateDate(bean.getUpdateDate()); // 更新日付（検索時の更新日付を設定)
			updBean.setUpdatorCd(tantoCd); // 更新者

			// 製造指図ヘッダー更新
			int updateNum = directionHeaderDao.update(updBean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 計装IF済みフラグ変更
	 * @param bean データ
	 * @param tantoCd 更新者
	 * @param value 更新データ
	 */
	public void changeSumiflg(final DirectionStatusChangeList bean,
			final String tantoCd, final String value) {
		try {
			DirectionHeader updBean = null;
			BigDecimal directionDivision = bean.getDirectionDivision();
			String directNo = bean.getDirectionNo();

			// 製造指図ヘッダーを検索する
			updBean = directionHeaderDao.getEntity(directionDivision, directNo);
			if (updBean == null) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

			// ステータスを変更
			// 【 5:中間品最終検査済 】 → 【 6:FA受信 】
			if (DIRECTION_STATUS_INSPECTED.equals(updBean.getDirectionStatus())) {
				JissekiSeizoHead kBean = jissekiSeizoHeadDao
						.getEntity(directNo);
				if (kBean != null) {
					kBean.setSumi(value);
					int ct = jissekiSeizoHeadDao.update(kBean);
					if (ct <= 0) {
						throw new OptimisticLockRuntimeException();
					}
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図ステータス変更画面用のDaoを設定します。
	 * @param directionStatusChangeListDao 製造指図ステータス変更画面用のDao
	 */
	public void setDirectionStatusChangeListDao(
			final DirectionStatusChangeListDao directionStatusChangeListDao) {
		this.directionStatusChangeListDao = directionStatusChangeListDao;

	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param directionStatusChangeLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setDirectionStatusChangeLineForComboboxesDao(
			final DirectionStatusChangeLineForComboboxesDao directionStatusChangeLineForComboboxesDao) {
		this.directionStatusChangeLineForComboboxesDao = directionStatusChangeLineForComboboxesDao;
	}

	/**
	 * 製造指図ヘッダーテーブル用Daoを設定します。
	 * @param directionHeaderDao 製造指図ヘッダーテーブル用Dao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * jissekiSeizoHeadDaoを設定します。
	 * @param jissekiSeizoHeadDao jissekiSeizoHeadDao
	 */
	public void setJissekiSeizoHeadDao(
			final JissekiSeizoHeadDao jissekiSeizoHeadDao) {
		this.jissekiSeizoHeadDao = jissekiSeizoHeadDao;
	}

	/**
	 * directionStatusChangeHeaderListForReportDaoを設定します。
	 * @param directionStatusChangeHeaderListForReportDao
	 *            directionStatusChangeHeaderListForReportDao
	 */
	public void setDirectionStatusChangeHeaderListForReportDao(
			final DirectionStatusChangeHeaderListForReportDao directionStatusChangeHeaderListForReportDao) {
		this.directionStatusChangeHeaderListForReportDao = directionStatusChangeHeaderListForReportDao;
	}

	/**
	 * directionStatusChangeDetailListForReportDaoを設定します。
	 * @param directionStatusChangeDetailListForReportDao
	 *            directionStatusChangeDetailListForReportDao
	 */
	public void setDirectionStatusChangeDetailListForReportDao(
			final DirectionStatusChangeDetailListForReportDao directionStatusChangeDetailListForReportDao) {
		this.directionStatusChangeDetailListForReportDao = directionStatusChangeDetailListForReportDao;
	}

	/**
	 * directionStatusChangeProcedureListForReportDaoを設定します。
	 * @param directionStatusChangeProcedureListForReportDao
	 *            directionStatusChangeProcedureListForReportDao
	 */
	public void setDirectionStatusChangeProcedureListForReportDao(
			final DirectionStatusChangeProcedureListForReportDao directionStatusChangeProcedureListForReportDao) {
		this.directionStatusChangeProcedureListForReportDao = directionStatusChangeProcedureListForReportDao;
	}

	/**
	 * directionStatusChangeFormulaListForReportDaoを設定します。
	 * @param directionStatusChangeFormulaListForReportDao
	 *            directionStatusChangeFormulaListForReportDao
	 */
	public void setDirectionStatusChangeFormulaListForReportDao(
			final DirectionStatusChangeFormulaListForReportDao directionStatusChangeFormulaListForReportDao) {
		this.directionStatusChangeFormulaListForReportDao = directionStatusChangeFormulaListForReportDao;
	}

	/**
	 * directionStatusChangeInspectionListForReportDaoを設定します。
	 * @param directionStatusChangeInspectionListForReportDao
	 *            directionStatusChangeInspectionListForReportDao
	 */
	public void setDirectionStatusChangeInspectionListForReportDao(
			final DirectionStatusChangeInspectionListForReportDao directionStatusChangeInspectionListForReportDao) {
		this.directionStatusChangeInspectionListForReportDao = directionStatusChangeInspectionListForReportDao;
	}
}
