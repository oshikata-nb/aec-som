/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.midinspectcomp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.direction.DirectionConst;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.tankkaijyo.TankKaijyo;
import com.asahikaseieng.dao.entity.tankkaijyo.TankKaijyoDao;
import com.asahikaseieng.dao.nonentity.comboboxes.midinspectcomp.MidInspectCompLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.midinspectcomp.MidInspectCompLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompList;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompListDao;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompDetailListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompFormulaListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompHeaderListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompInspectionListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompListConditionForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompProcedureListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompProcedureListForReportDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 中間品検査完了入力 ロジック実装クラス
 * @author tosco
 */
public class MidInspectCompListLogicImpl implements MidInspectCompListLogic {

	/** 中間品検査完了入力画面用のDao * */
	private MidInspectCompListDao midInspectCompListDao;

	/** 生産ラインコンボボックス用DAO */
	private MidInspectCompLineForComboboxesDao midInspectCompLineForComboboxesDao;

	/** 製造指図ヘッダーテーブル用のDao * */
	private DirectionHeaderDao directionHeaderDao;

	/** タンク解除テーブル用NODao */
	private TankKaijyoDao tankKaijyoDao;

	/** 指図ステータス 中間品最終検査済 */
	private static final String INSPECTED = "5";

	/** 検査判定フラグ 合格 */
	private static final String PASSING = "1";

	/** タンク解除テーブル 調合タンクNo桁数 */
	private static final int CHOGOTANKNO = 8;

	/** タンク解除テーブル 上位処理済桁数 */
	private static final int SUMI = 1;

	/** タンク解除テーブル 解除／再設定桁数 */
	private static final int INTERLOCK = 1;

	/** 解除/再設定 解除 */
	private static final String CANCELLATION = "2";

	private MidInspectCompHeaderListForReportDao midInspectCompHeaderListForReportDao;

	private MidInspectCompDetailListForReportDao midInspectCompDetailListForReportDao;

	private MidInspectCompProcedureListForReportDao midInspectCompProcedureListForReportDao;

	private MidInspectCompFormulaListForReportDao midInspectCompFormulaListForReportDao;

	private MidInspectCompInspectionListForReportDao midInspectCompInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public MidInspectCompListLogicImpl() {
	}

	/**
	 * 中間品検査完了入力 検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<MidInspectCompList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<MidInspectCompList> getSearchList(
			final MidInspectCompPagerCondition condition)
			throws NoDataException {
		// パラメータチェック
		checkParams(condition);
		// 一覧検索
		List<MidInspectCompList> list = midInspectCompListDao
				.getSearchList(condition);
		// 検索結果があるか？
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果分ループ
		for (MidInspectCompList bean : list) {
			// ステータスが中間品最終検査済 の場合はデフォルトチェック
			if (bean.getDirectionStatus().equals(new BigDecimal(INSPECTED))) {
				bean.setMidInspectCompCheckBox(Boolean.TRUE);
			}
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompListForReport> 検索結果リスト
	 */
	public List<MidInspectCompHeaderListForReport> getHeaderReportList(
			final MidInspectCompListConditionForReport condition) {
		List<MidInspectCompHeaderListForReport> list = midInspectCompHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompDetailListForReport> 検索結果リスト
	 */
	public List<MidInspectCompDetailListForReport> getDetailReportList(
			final MidInspectCompListConditionForReport condition) {
		List<MidInspectCompDetailListForReport> list = midInspectCompDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompDetailListForReport> 検索結果リスト
	 */
	public List<MidInspectCompProcedureListForReport> getProcedureReportList(
			final MidInspectCompListConditionForReport condition) {

		List<MidInspectCompProcedureListForReport> list = midInspectCompProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompFormulaListForReport> 検索結果リスト
	 */
	public List<MidInspectCompFormulaListForReport> getFormulaReportList(
			final MidInspectCompListConditionForReport condition) {

		List<MidInspectCompFormulaListForReport> list = midInspectCompFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompInspectionListForReport> 検索結果リスト
	 */
	public List<MidInspectCompInspectionListForReport> getInspectionReportList(
			final MidInspectCompListConditionForReport condition) {

		List<MidInspectCompInspectionListForReport> list = midInspectCompInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 中間品検査完了入力 登録処理
	 * @param bean 中間品検査完了一覧データ
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	public void update(final MidInspectCompList bean, final String loginUserId)
			throws Exception {

		// システム日時取得
		Timestamp now = AecDateUtils.getCurrentTimestamp();

		// 更新対象データを作成する
		DirectionHeader newBean = new DirectionHeader();
		// 更新対象データに検索取得時のデータのコピーを行う
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		// 計装IFテーブルに送信しない場合は、ステータスを6:FA受信済みに更新
		if (DirectionConst.ORDER_PUBLISH_FLG_OFF.equals(bean
				.getOrderPublishFlg())) {
			newBean
					.setDirectionStatus(DirectionConst.DIRECTION_STATUS_FA_RECEIVE);
		} else {
			// 指図ステータスを設定
			newBean.setDirectionStatus(new BigDecimal(INSPECTED)); // 中間品検査済みを設定
		}
		newBean.setCertificationFlag(bean.getCertificationFlag());
		// 合格だったら
		if (bean.getCertificationFlag().equals(new BigDecimal(PASSING))) {
			// 合格したシステム日時を設定
			newBean.setCertificationDate(now);
		}

		// 更新者(ログインユーザー)セット
		newBean.setUpdatorCd(loginUserId);
		try {
			// 製造指図ヘッダー更新
			int updateNum = directionHeaderDao.update(newBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.midinspectcomp.nodata.deleted.direction.no";
				throw new MidInspectCompLogicException(errMsg, bean
						.getDirectionNo());
			}

			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			String errMsg = "errors.midinspectcomp.optimisticlock.data.direction.no";
			throw new MidInspectCompLogicException(errMsg, bean
					.getDirectionNo());
		}
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. : タンクインターロック解除
	 * @param bean 中間品検査完了一覧データ
	 * @param tantoCd 更新者
	 * @throws MidInspectCompLogicException 更新エラー
	 */
	public void updateIfTable(final MidInspectCompList bean,
			final String tantoCd) throws MidInspectCompLogicException {
		String errMsg = "errors.midinspectcomp.update.if.table.direction.no";

		try {
			// 更新用Bean作成
			TankKaijyo tankKaijyo = new TankKaijyo();

			// 調合タンクNoをセット
			tankKaijyo.setChogotankno(AecStringUtils.editSpecifiedSize(bean
					.getCompoundTankNo(), CHOGOTANKNO));

			// 上位処理済をセット (0:固定)
			tankKaijyo.setSumi(AecStringUtils.editSpecifiedSize("0", SUMI));

			// 解除をセット
			tankKaijyo.setInterlock(AecStringUtils.editSpecifiedSize(
				CANCELLATION, INTERLOCK));

			// タンクインターロック解除テーブル更新
			if (tankKaijyoDao.getEntity(tankKaijyo.getChogotankno()) != null) {
				tankKaijyoDao.update(tankKaijyo);
			} else {
				tankKaijyoDao.insert(tankKaijyo);
			}
		} catch (SQLRuntimeException e) {
			throw new MidInspectCompLogicException(errMsg, bean
					.getDirectionNo());
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new MidInspectCompLogicException(errMsg, bean
					.getDirectionNo());
		}
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<MidInspectCompLineForComboboxes>
	 */
	public List<MidInspectCompLineForComboboxes> getAllLines() {
		return midInspectCompLineForComboboxesDao.findAll();
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
		List<MidInspectCompLineForComboboxes> lineList = getAllLines();
		for (MidInspectCompLineForComboboxes bean : lineList) {
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
	private void checkParams(final MidInspectCompPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 中間品検査完了入力画面用のDaoを設定します。
	 * @param midInspectCompListDao 中間品検査完了入力画面用のDao
	 */
	public void setMidInspectCompListDao(
			final MidInspectCompListDao midInspectCompListDao) {
		this.midInspectCompListDao = midInspectCompListDao;

	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param midInspectCompLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setMidInspectCompLineForComboboxesDao(
			final MidInspectCompLineForComboboxesDao midInspectCompLineForComboboxesDao) {
		this.midInspectCompLineForComboboxesDao = midInspectCompLineForComboboxesDao;
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
	 * タンク解除テーブル用Daoを設定します。
	 * @param tankKaijyoDao タンク解除テーブル用Dao
	 */
	public void setTankKaijyoDao(final TankKaijyoDao tankKaijyoDao) {
		this.tankKaijyoDao = tankKaijyoDao;
	}

	/**
	 * midInspectCompHeaderListForReportDaoを設定します。
	 * @param midInspectCompHeaderListForReportDao
	 *            midInspectCompHeaderListForReportDao
	 */
	public void setMidInspectCompListForReportDao(
			final MidInspectCompHeaderListForReportDao midInspectCompHeaderListForReportDao) {
		this.midInspectCompHeaderListForReportDao = midInspectCompHeaderListForReportDao;
	}

	/**
	 * midInspectCompDetailListForReportDaoを設定します。
	 * @param midInspectCompDetailListForReportDao
	 *            midInspectCompDetailListForReportDao
	 */
	public void setMidInspectCompDetailListForReportDao(
			final MidInspectCompDetailListForReportDao midInspectCompDetailListForReportDao) {
		this.midInspectCompDetailListForReportDao = midInspectCompDetailListForReportDao;
	}

	/**
	 * midInspectCompProcedureListForReportDaoを設定します。
	 * @param midInspectCompProcedureListForReportDao
	 *            midInspectCompProcedureListForReportDao
	 */
	public void setMidInspectCompProcedureListForReportDao(
			final MidInspectCompProcedureListForReportDao midInspectCompProcedureListForReportDao) {
		this.midInspectCompProcedureListForReportDao = midInspectCompProcedureListForReportDao;
	}

	/**
	 * midInspectCompFormulaListForReportDaoを設定します。
	 * @param midInspectCompFormulaListForReportDao
	 *            midInspectCompFormulaListForReportDao
	 */
	public void setMidInspectCompFormulaListForReportDao(
			final MidInspectCompFormulaListForReportDao midInspectCompFormulaListForReportDao) {
		this.midInspectCompFormulaListForReportDao = midInspectCompFormulaListForReportDao;
	}

	/**
	 * midInspectCompInspectionListForReportDaoを設定します。
	 * @param midInspectCompInspectionListForReportDao
	 *            midInspectCompInspectionListForReportDao
	 */
	public void setMidInspectCompInspectionListForReportDao(
			final MidInspectCompInspectionListForReportDao midInspectCompInspectionListForReportDao) {
		this.midInspectCompInspectionListForReportDao = midInspectCompInspectionListForReportDao;
	}

}
