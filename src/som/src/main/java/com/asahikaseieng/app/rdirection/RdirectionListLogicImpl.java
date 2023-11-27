/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultDetailListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultFormulaListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultHeaderListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultInspectionListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultListConditionForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultProcedureListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultProcedureListForReportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 製造実績検索 ロジック実装クラス
 * @author tosco
 */
public class RdirectionListLogicImpl implements RdirectionListLogic {

	/** 生産ライン操作用DAO */
	private RdirectionLineForComboboxesDao rdirectionLineForComboboxesDao;

	/** 製造指図ヘッダ操作用DAO */
	private RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	private DirectionResultHeaderListForReportDao directionResultHeaderListForReportDao;

	private DirectionResultDetailListForReportDao directionResultDetailListForReportDao;

	private DirectionResultProcedureListForReportDao directionResultProcedureListForReportDao;

	private DirectionResultFormulaListForReportDao directionResultFormulaListForReportDao;

	private DirectionResultInspectionListForReportDao directionResultInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public RdirectionListLogicImpl() {
	}

	/**
	 * 検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RdirectionDirectionHeaderList> getSearchList(
			final RdirectionDirectionHeaderListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<RdirectionDirectionHeaderList> list = rdirectionDirectionHeaderListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultListForReport> 検索結果リスト
	 */
	public List<DirectionResultHeaderListForReport> getHeaderReportList(
			final DirectionResultListConditionForReport condition) {
		List<DirectionResultHeaderListForReport> list = directionResultHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultDetailListForReport> 検索結果リスト
	 */
	public List<DirectionResultDetailListForReport> getDetailReportList(
			final DirectionResultListConditionForReport condition) {
		List<DirectionResultDetailListForReport> list = directionResultDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultDetailListForReport> 検索結果リスト
	 */
	public List<DirectionResultProcedureListForReport> getProcedureReportList(
			final DirectionResultListConditionForReport condition) {

		List<DirectionResultProcedureListForReport> list = directionResultProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultFormulaListForReport> 検索結果リスト
	 */
	public List<DirectionResultFormulaListForReport> getFormulaReportList(
			final DirectionResultListConditionForReport condition) {

		List<DirectionResultFormulaListForReport> list = directionResultFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultInspectionListForReport> 検索結果リスト
	 */
	public List<DirectionResultInspectionListForReport> getInspectionReportList(
			final DirectionResultListConditionForReport condition) {

		List<DirectionResultInspectionListForReport> list = directionResultInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * directionResultHeaderListForReportDaoを設定します。
	 * @param directionResultHeaderListForReportDao
	 *            directionResultHeaderListForReportDao
	 */
	public void setDirectionResultListForReportDao(
			final DirectionResultHeaderListForReportDao directionResultHeaderListForReportDao) {
		this.directionResultHeaderListForReportDao = directionResultHeaderListForReportDao;
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<RdirectionLineForComboboxes>
	 */
	public List<RdirectionLineForComboboxes> getAllLines() {
		return rdirectionLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 生産ライン検索
		List<RdirectionLineForComboboxes> lineList = getAllLines();
		for (RdirectionLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}

		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(RdirectionConst.COMBO_ALL_VALUE);
		allItem.setLabales(RdirectionConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 製造記録発行
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return TreeMap<String, String> 発行対象製造指図番号
	 * @throws RdirectionLogicException エラー発生時
	 */
	public TreeMap<String, String> issuance(
			final List<RdirectionDirectionHeaderList> list, final String tantoCd)
			throws RdirectionLogicException {
		TreeMap<String, String> tMap = new TreeMap<String, String>();

		boolean isSelected = false;
		Timestamp now = AecDateUtils.getCurrentTimestamp();
		for (RdirectionDirectionHeaderList bean : list) {
			if (bean.isSelectedCheck()) {
				// 製造記録発行対象データ
				isSelected = true;
				// 指図ステータス
				if (!RdirectionConst.DIRECTION_STATUS_COMPLETED.equals(bean
						.getDirectionStatus())) {
					// ステータス≠完了以外、記録を発行する。
					updateHeader(bean, tantoCd, now);
				}
				// 出力対象の製造指図番号をマップに保存
				tMap.put(bean.getDirectionNo(), bean.getDirectionNo());
			}
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			RdirectionLogicException ex = new RdirectionLogicException();
			ex.setKey("errors.rdirection.selected.checkbox");
			throw ex;
		}
		return tMap;
	}

	/**
	 * 製造指図ヘッダ更新に指図ステータス=7（製造記録出力済み）にして、更新する。
	 * @param bean 製造指図ヘッダデータ
	 * @param tantoCd 担当者コード
	 * @param now 現在日時
	 */
	private void updateHeader(final RdirectionDirectionHeaderList bean,
			final String tantoCd, final Timestamp now) {
		RdirectionDirectionHeaderList updateBean = new RdirectionDirectionHeaderList();
		try {
			IgnoreCaseBeanUtils.copyProperties(updateBean, bean);
		} catch (IllegalAccessException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		}
		updateBean
				.setDirectionStatus(RdirectionConst.DIRECTION_STATUS_OUTPUTED); // 指図ステータス=7（製造記録出力済み）
		updateBean
				.setProductRecordFlag(RdirectionConst.PRODUCT_RECORD_FLAG_ISSUED); // 製造記録発行フラグ-発行済み
		updateBean.setProductRecordDate(now); // 製造記録発行日
		updateBean.setProductRecordTantoCd(tantoCd); // 製造記録発行者
		updateBean.setUpdatorCd(tantoCd); // 更新者
		// 製造指図ヘッダ更新
		updateDirectionHeader(updateBean);
	}

	/**
	 * 製造指図ヘッダの更新を行う。
	 * @param bean データ
	 */
	public void updateDirectionHeader(final RdirectionDirectionHeaderList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}
		try {
			// ヘッダを更新
			int updateNum = rdirectionDirectionHeaderListDao.update(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 一括完了
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws RdirectionLogicException エラー発生時
	 */
	public boolean complete(final List<RdirectionDirectionHeaderList> list,
			final String tantoCd) throws RdirectionLogicException {
		boolean res = false;

		boolean isSelected = false;
		int index = 1;
		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.rdirection.stock.update.direction.no";
		for (RdirectionDirectionHeaderList bean : list) {
			if (bean.isSelectedCheck()) {
				// 完了対象データ
				isSelected = true;
				// 指図ステータス
				if (RdirectionConst.DIRECTION_STATUS_OUTPUTED.equals(bean
						.getDirectionStatus())) {
					// ステータス=製造記録出力済のみ、完了にする。
					updateCompleteHeader(bean, tantoCd);

					try {
						/* 在庫更新－包装・製造指図完了 */
						if (!stock.completeDirection(bean
								.getDirectionDivision(), bean.getDirectionNo(),
							tantoCd)) {
							RdirectionLogicException ex = new RdirectionLogicException(
									errMsg, bean.getDirectionNo());
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						RdirectionLogicException ex = new RdirectionLogicException(
								errMsg, bean.getDirectionNo());
						throw ex;
					}
				} else {
					RdirectionLogicException ex = new RdirectionLogicException(
							"errors.rdirection.complete.status", String
									.valueOf(index));
					throw ex;
				}
			}
			index++;
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			RdirectionLogicException ex = new RdirectionLogicException();
			ex.setKey("errors.rdirection.selected.checkbox");
			throw ex;
		} else {
			// 処理成功メッセージ
			res = true;
		}
		return res;
	}

	/**
	 * 製造指図ヘッダ更新に指図ステータス=8（完了）にして、更新する。
	 * @param bean 製造指図ヘッダデータ
	 * @param tantoCd 担当者コード
	 */
	private void updateCompleteHeader(final RdirectionDirectionHeaderList bean,
			final String tantoCd) {
		RdirectionDirectionHeaderList updateBean = new RdirectionDirectionHeaderList();
		try {
			IgnoreCaseBeanUtils.copyProperties(updateBean, bean);
		} catch (IllegalAccessException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// 同一クラスだから例外は発生しない
			e.printStackTrace();
		}
		updateBean
				.setDirectionStatus(RdirectionConst.DIRECTION_STATUS_COMPLETED); // 指図ステータス=8（完了）
		updateBean.setUpdatorCd(tantoCd); // 更新者
		// 製造指図ヘッダ更新
		updateDirectionHeader(updateBean);
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(
			final RdirectionDirectionHeaderListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産ライン操作用DAOを設定します。
	 * @param rdirectionLineForComboboxesDao 生産ライン操作用DAO
	 */
	public void setRdirectionLineForComboboxesDao(
			final RdirectionLineForComboboxesDao rdirectionLineForComboboxesDao) {
		this.rdirectionLineForComboboxesDao = rdirectionLineForComboboxesDao;
	}

	/**
	 * 製造指図ヘッダ操作用DAOを設定します。
	 * @param rdirectionDirectionHeaderListDao 製造指図ヘッダ操作用DAO
	 */
	public void setRdirectionDirectionHeaderListDao(
			final RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao) {
		this.rdirectionDirectionHeaderListDao = rdirectionDirectionHeaderListDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * directionResultDetailListForReportDaoを設定します。
	 * @param directionResultDetailListForReportDao
	 *            directionResultDetailListForReportDao
	 */
	public void setDirectionResultDetailListForReportDao(
			final DirectionResultDetailListForReportDao directionResultDetailListForReportDao) {
		this.directionResultDetailListForReportDao = directionResultDetailListForReportDao;
	}

	/**
	 * directionResultProcedureListForReportDaoを設定します。
	 * @param directionResultProcedureListForReportDao
	 *            directionResultProcedureListForReportDao
	 */
	public void setDirectionResultProcedureListForReportDao(
			final DirectionResultProcedureListForReportDao directionResultProcedureListForReportDao) {
		this.directionResultProcedureListForReportDao = directionResultProcedureListForReportDao;
	}

	/**
	 * directionResultFormulaListForReportDaoを設定します。
	 * @param directionResultFormulaListForReportDao
	 *            directionResultFormulaListForReportDao
	 */
	public void setDirectionResultFormulaListForReportDao(
			final DirectionResultFormulaListForReportDao directionResultFormulaListForReportDao) {
		this.directionResultFormulaListForReportDao = directionResultFormulaListForReportDao;
	}

	/**
	 * directionResultInspectionListForReportDaoを設定します。
	 * @param directionResultInspectionListForReportDao
	 *            directionResultInspectionListForReportDao
	 */
	public void setDirectionResultInspectionListForReportDao(
			final DirectionResultInspectionListForReportDao directionResultInspectionListForReportDao) {
		this.directionResultInspectionListForReportDao = directionResultInspectionListForReportDao;
	}
}
