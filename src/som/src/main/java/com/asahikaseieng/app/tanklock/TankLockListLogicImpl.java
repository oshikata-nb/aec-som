/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.tanklock;

import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.direction.DirectionConst;
import com.asahikaseieng.dao.entity.tankkaijyo.TankKaijyo;
import com.asahikaseieng.dao.entity.tankkaijyo.TankKaijyoDao;
import com.asahikaseieng.dao.nonentity.comboboxes.tanklock.TankLockLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.tanklock.TankLockLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockList;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockListDao;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockPagerCondition;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockDetailListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockFormulaListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockHeaderListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockInspectionListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockListConditionForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockProcedureListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockProcedureListForReportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 調合タンク底弁インターロック解除/再設定 ロジック実装クラス
 * @author tosco
 */
public class TankLockListLogicImpl implements TankLockListLogic {

	/** 調合タンク底弁インターロック解除/再設定画面用のDao * */
	private TankLockListDao tankLockListDao;

	/** 生産ラインコンボボックス用DAO */
	private TankLockLineForComboboxesDao tankLockLineForComboboxesDao;

	/** タンク解除テーブル用の Dao */
	private TankKaijyoDao tankKaijyoDao;

	/** タンク解除テーブル 調合タンクNo桁数 */
	private static final int CHOGOTANKNO = 8;

	/** タンク解除テーブル 上位処理済桁数 */
	private static final int SUMI = 1;

	/** タンク解除テーブル 解除／再設定桁数 */
	private static final int INTERLOCK = 1;

	private TankLockHeaderListForReportDao tankLockHeaderListForReportDao;

	private TankLockDetailListForReportDao tankLockDetailListForReportDao;

	private TankLockProcedureListForReportDao tankLockProcedureListForReportDao;

	private TankLockFormulaListForReportDao tankLockFormulaListForReportDao;

	private TankLockInspectionListForReportDao tankLockInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public TankLockListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<TankLockList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<TankLockList> getSearchList(
			final TankLockPagerCondition condition) throws NoDataException {
		// パラメータチェック
		checkParams(condition);
		// 一覧検索
		List<TankLockList> list = tankLockListDao.getSearchList(condition);

		// 検索結果があるか？
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockListForReport> 検索結果リスト
	 */
	public List<TankLockHeaderListForReport> getHeaderReportList(
			final TankLockListConditionForReport condition) {
		List<TankLockHeaderListForReport> list = tankLockHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockDetailListForReport> 検索結果リスト
	 */
	public List<TankLockDetailListForReport> getDetailReportList(
			final TankLockListConditionForReport condition) {
		List<TankLockDetailListForReport> list = tankLockDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockDetailListForReport> 検索結果リスト
	 */
	public List<TankLockProcedureListForReport> getProcedureReportList(
			final TankLockListConditionForReport condition) {

		List<TankLockProcedureListForReport> list = tankLockProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockFormulaListForReport> 検索結果リスト
	 */
	public List<TankLockFormulaListForReport> getFormulaReportList(
			final TankLockListConditionForReport condition) {

		List<TankLockFormulaListForReport> list = tankLockFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockInspectionListForReport> 検索結果リスト
	 */
	public List<TankLockInspectionListForReport> getInspectionReportList(
			final TankLockListConditionForReport condition) {

		List<TankLockInspectionListForReport> list = tankLockInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 調合タンク底弁インターロック解除/再設定の登録処理を行う
	 * @param searchList 検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	public void update(final List<TankLockList> searchList,
			final String loginUserId) throws Exception {

		// 検索結果分ループ
		for (TankLockList bean : searchList) {
			// チェックボックスにチェックが入っていたら
			if (bean.isTankLockCheckBox()) {
				// 調合タンクNOをキーにタンク解除テーブルを検索
				TankKaijyo tankKaijyo = tankKaijyoDao.getEntity(AecStringUtils
						.editSpecifiedSize(bean.getCompoundTankNo(),
							CHOGOTANKNO));

				if (tankKaijyo != null) { // 検索結果が取得が出来る場合
					// 調合タンクNoをセット
					tankKaijyo.setChogotankno(AecStringUtils.editSpecifiedSize(
						bean.getCompoundTankNo(), CHOGOTANKNO));

					// 上位処理済をセット (0:固定)
					tankKaijyo.setSumi(AecStringUtils.editSpecifiedSize("0",
						SUMI));

					// 解除をセット
					tankKaijyo.setInterlock(AecStringUtils.editSpecifiedSize(
						bean.getInterlock(), INTERLOCK));

					try {
						// 調合タンクNoをキーにUPDATEを行う
						tankKaijyoDao.update(tankKaijyo);
						// 更新時に、すでに更新されていた場合
					} catch (NotSingleRowUpdatedRuntimeException e) {
						throw new OptimisticLockRuntimeException();
					}

				} else { // 検索結果が取得できない場合
					// 更新用Bean作成
					tankKaijyo = new TankKaijyo();

					// 調合タンクNoをセット
					tankKaijyo.setChogotankno(AecStringUtils.editSpecifiedSize(
						bean.getCompoundTankNo(), CHOGOTANKNO));

					// 上位処理済をセット (0:固定)
					tankKaijyo.setSumi(AecStringUtils.editSpecifiedSize("0",
						SUMI));

					// 解除をセット
					tankKaijyo.setInterlock(AecStringUtils.editSpecifiedSize(
						bean.getInterlock(), INTERLOCK));

					try {

						// 調合タンクNoをキーにINSERTを行う
						tankKaijyoDao.insert(tankKaijyo);

					} catch (SQLRuntimeException e) {
						throw new DuplicateRuntimeException(0);
					}

				}
			}
		}
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<TankLockLineForComboboxes>
	 */
	public List<TankLockLineForComboboxes> getAllLines() {
		return tankLockLineForComboboxesDao.findAll();
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
			item.setValues(DirectionConst.COMBO_ALL_VALUE);
			item.setLabales(DirectionConst.COMBO_ALL_LABEL);
			res.add(item);
		}

		// 生産ライン検索
		List<TankLockLineForComboboxes> lineList = getAllLines();
		for (TankLockLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}
		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final TankLockPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 調合タンク底弁インターロック解除/再設定画面用のDaoを設定します。
	 * @param tankLockListDao 調合タンク底弁インターロック解除/再設定画面用のDao
	 */
	public void setTankLockListDao(final TankLockListDao tankLockListDao) {
		this.tankLockListDao = tankLockListDao;

	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param tankLockLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setTankLockLineForComboboxesDao(
			final TankLockLineForComboboxesDao tankLockLineForComboboxesDao) {
		this.tankLockLineForComboboxesDao = tankLockLineForComboboxesDao;
	}

	/**
	 * タンク解除テーブル用Daoを設定します。
	 * @param tankKaijyoDao タンク解除テーブル用Dao
	 */
	public void setTankKaijyoDao(final TankKaijyoDao tankKaijyoDao) {
		this.tankKaijyoDao = tankKaijyoDao;
	}

	/**
	 * tankLockHeaderListForReportDaoを設定します。
	 * @param tankLockHeaderListForReportDao tankLockHeaderListForReportDao
	 */
	public void setTankLockListForReportDao(
			final TankLockHeaderListForReportDao tankLockHeaderListForReportDao) {
		this.tankLockHeaderListForReportDao = tankLockHeaderListForReportDao;
	}

	/**
	 * tankLockDetailListForReportDaoを設定します。
	 * @param tankLockDetailListForReportDao tankLockDetailListForReportDao
	 */
	public void setTankLockDetailListForReportDao(
			final TankLockDetailListForReportDao tankLockDetailListForReportDao) {
		this.tankLockDetailListForReportDao = tankLockDetailListForReportDao;
	}

	/**
	 * tankLockProcedureListForReportDaoを設定します。
	 * @param tankLockProcedureListForReportDao
	 *            tankLockProcedureListForReportDao
	 */
	public void setTankLockProcedureListForReportDao(
			final TankLockProcedureListForReportDao tankLockProcedureListForReportDao) {
		this.tankLockProcedureListForReportDao = tankLockProcedureListForReportDao;
	}

	/**
	 * tankLockFormulaListForReportDaoを設定します。
	 * @param tankLockFormulaListForReportDao tankLockFormulaListForReportDao
	 */
	public void setTankLockFormulaListForReportDao(
			final TankLockFormulaListForReportDao tankLockFormulaListForReportDao) {
		this.tankLockFormulaListForReportDao = tankLockFormulaListForReportDao;
	}

	/**
	 * tankLockInspectionListForReportDaoを設定します。
	 * @param tankLockInspectionListForReportDao
	 *            tankLockInspectionListForReportDao
	 */
	public void setTankLockInspectionListForReportDao(
			final TankLockInspectionListForReportDao tankLockInspectionListForReportDao) {
		this.tankLockInspectionListForReportDao = tankLockInspectionListForReportDao;
	}
}
