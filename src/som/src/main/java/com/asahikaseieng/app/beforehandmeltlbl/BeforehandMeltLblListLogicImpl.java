/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.beforehandmeltlbl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.direction.DirectionConst;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.labelyobiyokai.LabelYobiyokai;
import com.asahikaseieng.dao.entity.labelyobiyokai.LabelYobiyokaiDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblList;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblListDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblPagerCondition;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblDetailListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblFormulaListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblInspectionListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListConditionForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblListForReportDao;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblProcedureListForReport;
import com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport.BeforehandMeltLblProcedureListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.beforehandmeltlbl.BeforehandMeltLblLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.beforehandmeltlbl.BeforehandMeltLblLineForComboboxesDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 予備溶解ラベル発行画面 ロジック実装クラス
 * @author tosco
 */
public class BeforehandMeltLblListLogicImpl implements
		BeforehandMeltLblListLogic {

	/** 予備溶解ラベル作成画面用のDao * */
	private BeforehandMeltLblListDao beforehandMeltLblListDao;

	/** 製造指図プロシージャ用のDao * */
	private BeforehandMeltLblDirectionProcedureListDao beforehandMeltLblDirectionProcedureListDao;

	/** 生産ラインコンボボックス用DAO */
	private BeforehandMeltLblLineForComboboxesDao beforehandMeltLblLineForComboboxesDao;

	/** 製造指図ヘッダーテーブル用のDao * */
	private DirectionHeaderDao directionHeaderDao;

	/** 予備溶解ラベルテーブル用のDao * */
	private LabelYobiyokaiDao labelYobiyokaiDao;

	/** 発番処理 ロジッククラス interface */
	private GetNumberLogic getNumberLogic;

	/** ラベル発行済み */
	private static final String ISSUE = "1";

	/** ラベル予備溶解テーブル 予備溶解バーコード桁数 */
	private static final int YOBIYOKAIBCR = 11;

	/** ラベル予備溶解テーブル 製造指図番号桁数 */
	private static final int SEIZOSASHIZUNO = 11;

	/** ラベル予備溶解テーブル 予備溶解番号桁数 */
	private static final int YOBIYOKAINO = 4;

	private BeforehandMeltLblListForReportDao beforehandMeltLblListForReportDao;

	private BeforehandMeltLblDetailListForReportDao beforehandMeltLblDetailListForReportDao;

	private BeforehandMeltLblProcedureListForReportDao beforehandMeltLblProcedureListForReportDao;

	private BeforehandMeltLblFormulaListForReportDao beforehandMeltLblFormulaListForReportDao;

	private BeforehandMeltLblInspectionListForReportDao beforehandMeltLblInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BeforehandMeltLblListLogicImpl() {
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblListForReport> 検索結果リスト
	 */
	public List<BeforehandMeltLblListForReport> getHeaderReportList(
			final BeforehandMeltLblListConditionForReport condition) {
		List<BeforehandMeltLblListForReport> list = beforehandMeltLblListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblDetailListForReport> 検索結果リスト
	 */
	public List<BeforehandMeltLblDetailListForReport> getDetailReportList(
			final BeforehandMeltLblListConditionForReport condition) {
		List<BeforehandMeltLblDetailListForReport> list = beforehandMeltLblDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblDetailListForReport> 検索結果リスト
	 */
	public List<BeforehandMeltLblProcedureListForReport> getProcedureReportList(
			final BeforehandMeltLblListConditionForReport condition) {

		List<BeforehandMeltLblProcedureListForReport> list = beforehandMeltLblProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblFormulaListForReport> 検索結果リスト
	 */
	public List<BeforehandMeltLblFormulaListForReport> getFormulaReportList(
			final BeforehandMeltLblListConditionForReport condition) {

		List<BeforehandMeltLblFormulaListForReport> list = beforehandMeltLblFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblInspectionListForReport> 検索結果リスト
	 */
	public List<BeforehandMeltLblInspectionListForReport> getInspectionReportList(
			final BeforehandMeltLblListConditionForReport condition) {

		List<BeforehandMeltLblInspectionListForReport> list = beforehandMeltLblInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 予備溶解ラベル発行画面一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<BeforehandMeltLblList> getSearchList(
			final BeforehandMeltLblPagerCondition condition)
			throws NoDataException {
		// パラメータチェック
		checkParams(condition);
		// 一覧検索
		List<BeforehandMeltLblList> list = beforehandMeltLblListDao
				.getSearchList(condition);
		// 検索結果があるか？
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 予備溶解ラベル発行処理
	 * @param bean 予備溶解ラベル発行一覧データ
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 * @return List<LabelYobiyokai> 登録用予備溶解ラベルリスト
	 */
	public List<LabelYobiyokai> insertLabelYobiyokai(
			final BeforehandMeltLblList bean, final String loginUserId)
			throws Exception {
		List<LabelYobiyokai> lblList = new ArrayList<LabelYobiyokai>();

		// システム日時取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		// ここから製造指図ヘッダーテーブル更新処理
		// 更新対象データを作成する
		DirectionHeader newBean = new DirectionHeader();
		// 更新対象データに検索取得時のデータのコピーを行う
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		// 予備溶解ラベル発行フラグを発行にセット
		newBean.setStockdissLabelFlag(new BigDecimal(ISSUE));
		// 予備溶解ラベル発行日設定
		newBean.setStockdissLabelDate(now);
		// 予備溶解ラベル発行者設定
		newBean.setStockdissLabelTantoCd(loginUserId);

		// 更新者(ログインユーザー)セット
		newBean.setUpdatorCd(loginUserId);
		try {
			// 製造指図ヘッダー更新
			int updateNum = directionHeaderDao.update(newBean);

			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.beforehandmeltlbl.nodata.deleted.direction.no";
				throw new BeforehandMeltLblLogicException(errMsg, bean
						.getDirectionNo());
			}

			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			String errMsg = "errors.beforehandmeltlbl.optimisticlock.data.direction.no";
			throw new BeforehandMeltLblLogicException(errMsg, bean
					.getDirectionNo());
		}

		// 予備溶解ラベルが発行済みの場合は、登録を行わない
		if (bean.getStockdissLabelFlag().equals(new BigDecimal(ISSUE))) {
			return lblList;
		}

		// 予備溶解ラベル発行対象一覧を検索
		List<BeforehandMeltLblDirectionProcedureList> directionProcedureList = beforehandMeltLblDirectionProcedureListDao
				.getDirectionProcedureList(bean.getDirectionDivision()
						.toString(), bean.getDirectionNo());

		// 製造指図プロシージャ一覧の検索結果分ループ
		for (BeforehandMeltLblDirectionProcedureList dPBean : directionProcedureList) {
			// 登録対象データの作成
			LabelYobiyokai labelYobiyokai = new LabelYobiyokai();

			// 予備溶解バーコードを発番
			String yobiYokaiBcr = getNumberLogic.getYobiYokaiBcr(now);
			if (yobiYokaiBcr == null) {
				// 発番に失敗した場合
				String errMsg = "errors.beforehandmeltlbl.numbering.direction.no";
				throw new BeforehandMeltLblLogicException(errMsg, bean
						.getDirectionNo());
			}
			// 予備溶解バーコードをセット
			labelYobiyokai.setYobiyokaibcr(AecStringUtils.editSpecifiedSize(
				yobiYokaiBcr, YOBIYOKAIBCR));

			// 指図番号セット
			labelYobiyokai.setSeizosashizuno(AecStringUtils.editSpecifiedSize(
				dPBean.getDirectionNo(), SEIZOSASHIZUNO));
			// 本流/予備溶解セット
			if (dPBean.getMainStream() == null) {
				dPBean.setMainStream(BigDecimal.ZERO);
			}
			labelYobiyokai.setYobiyokaino(AecStringUtils.editSpecifiedSize(
				dPBean.getMainStream().toString(), YOBIYOKAINO));

			// 予備溶解ラベルをリストに追加
			lblList.add(labelYobiyokai);
		}
		return lblList;
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 予備溶解ラベル
	 * @param list 登録予備溶解ラベルリスト
	 * @param directionNo 製造指図番号
	 * @throws BeforehandMeltLblLogicException 更新エラー
	 */
	public void insertIfTable(final List<LabelYobiyokai> list,
			final String directionNo) throws BeforehandMeltLblLogicException {

		try {
			for (LabelYobiyokai bean : list) {
				// 予備溶解ラベルテーブル登録
				labelYobiyokaiDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			String errMsg = "errors.beforehandmeltlbl.update.if.table.direction.no";
			throw new BeforehandMeltLblLogicException(errMsg, directionNo);
		}
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<BeforehandMeltLblLineForComboboxes>
	 */
	public List<BeforehandMeltLblLineForComboboxes> getAllLines() {
		return beforehandMeltLblLineForComboboxesDao.findAll();
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
		List<BeforehandMeltLblLineForComboboxes> lineList = getAllLines();
		for (BeforehandMeltLblLineForComboboxes bean : lineList) {
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
	private void checkParams(final BeforehandMeltLblPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 予備溶解ラベル作成画面用のDaoを設定します。
	 * @param beforehandMeltLblListDao 予備溶解ラベル作成画面用のDao
	 */
	public void setBeforehandMeltLblListDao(
			final BeforehandMeltLblListDao beforehandMeltLblListDao) {
		this.beforehandMeltLblListDao = beforehandMeltLblListDao;

	}

	/**
	 * 製造指図プロシージャ用Daoを設定します。
	 * @param beforehandMeltLblDirectionProcedureListDao 製造指図プロシージャ用Dao
	 */
	public void setBeforehandMeltLblDirectionProcedureListDao(
			final BeforehandMeltLblDirectionProcedureListDao beforehandMeltLblDirectionProcedureListDao) {
		this.beforehandMeltLblDirectionProcedureListDao = beforehandMeltLblDirectionProcedureListDao;
	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param beforehandMeltLblLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setBeforehandMeltLblLineForComboboxesDao(
			final BeforehandMeltLblLineForComboboxesDao beforehandMeltLblLineForComboboxesDao) {
		this.beforehandMeltLblLineForComboboxesDao = beforehandMeltLblLineForComboboxesDao;
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
	 * 製造指図プロシージャ用Daoを設定します。
	 * @param labelYobiyokaiDao 製造指図プロシージャ用Dao
	 */
	public void setLabelYobiyokaiDao(final LabelYobiyokaiDao labelYobiyokaiDao) {
		this.labelYobiyokaiDao = labelYobiyokaiDao;
	}

	/**
	 * 発番処理 ロジッククラスを設定します。
	 * @param getNumberLogic 発番処理ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * beforehandMeltLblListForReportDaoを設定します。
	 * @param beforehandMeltLblListForReportDao
	 *            beforehandMeltLblListForReportDao
	 */
	public void setBeforehandMeltLblListForReportDao(
			final BeforehandMeltLblListForReportDao beforehandMeltLblListForReportDao) {
		this.beforehandMeltLblListForReportDao = beforehandMeltLblListForReportDao;
	}

	/**
	 * beforehandMeltLblDetailListForReportDaoを設定します。
	 * @param beforehandMeltLblDetailListForReportDao
	 *            beforehandMeltLblDetailListForReportDao
	 */
	public void setBeforehandMeltLblDetailListForReportDao(
			final BeforehandMeltLblDetailListForReportDao beforehandMeltLblDetailListForReportDao) {
		this.beforehandMeltLblDetailListForReportDao = beforehandMeltLblDetailListForReportDao;
	}

	/**
	 * beforehandMeltLblProcedureListForReportDaoを設定します。
	 * @param beforehandMeltLblProcedureListForReportDao
	 *            beforehandMeltLblProcedureListForReportDao
	 */
	public void setBeforehandMeltLblProcedureListForReportDao(
			final BeforehandMeltLblProcedureListForReportDao beforehandMeltLblProcedureListForReportDao) {
		this.beforehandMeltLblProcedureListForReportDao = beforehandMeltLblProcedureListForReportDao;
	}

	/**
	 * beforehandMeltLblFormulaListForReportDaoを設定します。
	 * @param beforehandMeltLblFormulaListForReportDao
	 *            beforehandMeltLblFormulaListForReportDao
	 */
	public void setBeforehandMeltLblFormulaListForReportDao(
			final BeforehandMeltLblFormulaListForReportDao beforehandMeltLblFormulaListForReportDao) {
		this.beforehandMeltLblFormulaListForReportDao = beforehandMeltLblFormulaListForReportDao;
	}

	/**
	 * beforehandMeltLblInspectionListForReportDaoを設定します。
	 * @param beforehandMeltLblInspectionListForReportDao
	 *            beforehandMeltLblInspectionListForReportDao
	 */
	public void setBeforehandMeltLblInspectionListForReportDao(
			final BeforehandMeltLblInspectionListForReportDao beforehandMeltLblInspectionListForReportDao) {
		this.beforehandMeltLblInspectionListForReportDao = beforehandMeltLblInspectionListForReportDao;
	}
}
