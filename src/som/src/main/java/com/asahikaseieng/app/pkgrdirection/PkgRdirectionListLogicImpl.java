/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.lotinventory.LotInventory;
import com.asahikaseieng.dao.entity.lotinventory.LotInventoryDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionDetailListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionFormulaListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionHeaderListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionInspectionListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionListConditionForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionProcedureListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionProcedureListForReportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績－検索画面 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionListLogicImpl implements PkgRdirectionListLogic {

	/** 包装実績－検索画面Dao */
	private PkgRdirectionListDao pkgRdirectionListDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 製造指図ヘッダーDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao rdirectionFormulaDao;

	private PkgRdirectionHeaderListForReportDao pkgRdirectionHeaderListForReportDao;

	private PkgRdirectionDetailListForReportDao pkgRdirectionDetailListForReportDao;

	private PkgRdirectionProcedureListForReportDao pkgRdirectionProcedureListForReportDao;

	private PkgRdirectionFormulaListForReportDao pkgRdirectionFormulaListForReportDao;

	private PkgRdirectionInspectionListForReportDao pkgRdirectionInspectionListForReportDao;

	private LotInventoryDao lotInventoryDao;

	private DirectionFormulaDao directionFormulaDao;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionListLogicImpl() {
	}

	/**
	 * 包装実績検索一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PkgRdirectionList> 検索結果リスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<PkgRdirectionList> getSearchList(
			final PkgRdirectionListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<PkgRdirectionList> list = pkgRdirectionListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PkgRdirectionList bean : list) {
			// 数量
			String strPlanedQty = checkDigitUtilsLogic.format(
				PkgRdirectionConst.UNIT_DIVISION_HAIGO, bean.getPlanedQty());
			bean.setStrPlanedQty(strPlanedQty);
		}

		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionListForReport> 検索結果リスト
	 */
	public List<PkgRdirectionHeaderListForReport> getHeaderReportList(
			final PkgRdirectionListConditionForReport condition) {
		List<PkgRdirectionHeaderListForReport> list = pkgRdirectionHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDetailListForReport> 検索結果リスト
	 */
	public List<PkgRdirectionDetailListForReport> getDetailReportList(
			final PkgRdirectionListConditionForReport condition) {
		List<PkgRdirectionDetailListForReport> list = pkgRdirectionDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDetailListForReport> 検索結果リスト
	 */
	public List<PkgRdirectionProcedureListForReport> getProcedureReportList(
			final PkgRdirectionListConditionForReport condition) {

		List<PkgRdirectionProcedureListForReport> list = pkgRdirectionProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionFormulaListForReport> 検索結果リスト
	 */
	public List<PkgRdirectionFormulaListForReport> getFormulaReportList(
			final PkgRdirectionListConditionForReport condition) {

		List<PkgRdirectionFormulaListForReport> list = pkgRdirectionFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionInspectionListForReport> 検索結果リスト
	 */
	public List<PkgRdirectionInspectionListForReport> getInspectionReportList(
			final PkgRdirectionListConditionForReport condition) {

		List<PkgRdirectionInspectionListForReport> list = pkgRdirectionInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final PkgRdirectionListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 指図ステータスのチェック(包装記録の発行可能チェック）
	 * @param searchList 一覧データ
	 * @return ActionMessage エラーメッセージ
	 */
	public ActionMessages checkDirectionStatus(
			final List<PkgRdirectionList> searchList) {
		ActionMessages errMsgs = null;
		ActionMessage errMsg = null;
		PkgRdirectionList bean = null;

		if (searchList != null) {
			for (int i = 0; i < searchList.size(); i++) {
				bean = searchList.get(i);
				if (!bean.isCheckFlg()) {
					// チェック無
					continue;
				}

				// 指図ステータスが、未確定、指図書発行済の場合は、エラー（包装記録発行不可）
				if (PkgRdirectionConst.DIRECTION_STATUS_UN_CONFIRMED
						.equals(bean.getDirectionStatus())
						|| PkgRdirectionConst.DIRECTION_STATUS_ISSUE
								.equals(bean.getDirectionStatus())) {
					errMsg = new ActionMessage(
							"errors.pkgrdirection.not.issue.row", String
									.valueOf(i + 1));
				}
				if (errMsg != null) {
					if (errMsgs == null) {
						errMsgs = new ActionMessages();
					}
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				}
			}
		}
		return errMsgs;
	}

	/**
	 * 包装記録発行処理（製造指図ヘッダー更新）
	 * @param searchList 一覧データ
	 * @return TreeMap<String, String> 発行対象包装指図番号
	 * @param tantoCd 更新者
	 */
	public TreeMap<String, String> issueProductRecord(
			final List<PkgRdirectionList> searchList, final String tantoCd) {

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}
		TreeMap<String, String> tMap = new TreeMap<String, String>();

		try {
			for (PkgRdirectionList bean : searchList) {
				if (!bean.isCheckFlg()) {
					// チェック無
					continue;
				}
				DirectionHeader updBean = null;
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directNo = bean.getDirectionNo();

				// 製造指図ヘッダーを検索する
				updBean = directionHeaderDao.getEntity(directionDivision,
					directNo);
				if (updBean == null) {
					throw new OptimisticLockRuntimeException();
				}

				// 包装実績入力済の場合は、包装記録出力済にステータスを更新する
				if (PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS
						.equals(bean.getDirectionStatus())) {
					updBean
							.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_OUTPUT_DOC);
				}

				updBean.setProductRecordFlag(BigDecimal.ONE); // 包装記録発行フラグ(1:発行)
				updBean
						.setProductRecordDate(AecDateUtils
								.getCurrentTimestamp()); // 包装記録発行日
				updBean.setProductRecordTantoCd(tantoCd); // 包装記録発行者
				updBean.setLotNo(bean.getLotNo()); // ロット番号
				updBean.setUpdateDate(bean.getUpdateDate()); // 更新日付（検索時の更新日付を設定)
				updBean.setUpdatorCd(tantoCd); // 更新者

				// 製造指図ヘッダー更新
				int updateNum = directionHeaderDao.update(updBean);
				if (updateNum != 1) {
					// 更新対象無しエラー
					throw new OptimisticLockRuntimeException();
				}

				// ﾌｫｰﾐｭﾗデータ取得
				DirectionFormula formula = directionFormulaDao.getEntity(bean
						.getDirectionDivision(), bean.getDirectionNo(),
					PkgRdirectionConst.LINE_NO_FINISH_START_NO, new BigDecimal(
							1));

				// 配合データがある場合
				if (formula != null) {
					LotInventory updateLotBean = lotInventoryDao.getEntity(
						formula.getItemCd(), formula.getLocationCd(), bean
								.getDirectionNo());

					// ロット在庫がある場合
					if (updateLotBean != null) {

						// ロット在庫テーブルと画面のロット在庫が異なる場合のみロット在庫テーブル更新
						if (!bean.getLotNo().equals(
							updateLotBean.getAliasLotNo())) {
							updateLotBean.setUpdatorCd(tantoCd);
							updateLotBean.setAliasLotNo(bean.getLotNo());
							lotInventoryDao.update(updateLotBean);
						}
					}
				}
				// 仕上げデータ取得
				List<PkgRdirectionDirectionFormulaList> finFormList = rdirectionFormulaDao
						.getSearchFinishList(directionDivision, directNo);
				PkgRdirectionDirectionFormulaList finFormBean = finFormList
						.get(0);
				// 製品ロットNO
				finFormBean.setLotNo(bean.getLotNo());

				// 仕上げデータ更新
				updateNum = rdirectionFormulaDao.update(finFormBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new OptimisticLockRuntimeException();
				}
				// 出力対象の包装指図番号をマップに保存
				tMap.put(bean.getDirectionNo(), bean.getDirectionNo());
			}
			return tMap;

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * ラベル発行処理（製造指図ヘッダー更新）
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 */
	public void issueLabel(final List<PkgRdirectionList> searchList,
			final String tantoCd) {

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			for (PkgRdirectionList bean : searchList) {
				if (!bean.isCheckFlg()) {
					// チェック無
					continue;
				}
				DirectionHeader updBean = null;
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directNo = bean.getDirectionNo();

				// 製造指図ヘッダーを検索する
				updBean = directionHeaderDao.getEntity(directionDivision,
					directNo);
				if (updBean == null) {
					throw new OptimisticLockRuntimeException();
				}
				updBean.setProductLabelFlag(BigDecimal.ONE); // 製品ラベル発行フラグ(1:発行)
				updBean.setProductLabelDate(AecDateUtils.getCurrentTimestamp()); // 製品ラベル発行日
				updBean.setProductLabelTantoCd(tantoCd); // 製品ラベル発行者
				updBean.setUpdateDate(bean.getUpdateDate()); // 更新日付（検索時の更新日付を設定)
				updBean.setUpdatorCd(tantoCd); // 更新者

				// 製造指図ヘッダー更新
				int updateNum = directionHeaderDao.update(updBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new OptimisticLockRuntimeException();
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 一括完了
	 * @param list List<PkgRdirectionList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws PkgRdirectionLogicException エラー発生時
	 */
	public boolean complete(final List<PkgRdirectionList> list,
			final String tantoCd) throws PkgRdirectionLogicException {
		boolean res = false;

		boolean isSelected = false;
		int index = 1;
		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.rdirection.stock.update.direction.no";
		for (PkgRdirectionList bean : list) {
			if (bean.isCheckFlg()) {
				// 完了対象データ
				isSelected = true;
				// 指図ステータス
				if (PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT
						.equals(bean.getDirectionStatus())) {
					// ステータス=製造記録出力済のみ、完了にする。
					updateCompleteHeader(bean, tantoCd);

					try {
						/* 在庫更新－包装・製造指図完了 */
						if (!stock.completeDirection(bean
								.getDirectionDivision(), bean.getDirectionNo(),
							tantoCd)) {
							PkgRdirectionLogicException ex = new PkgRdirectionLogicException(
									errMsg, bean.getDirectionNo());
							throw ex;
						}
					} catch (LogicExceptionEx e) {
						PkgRdirectionLogicException ex = new PkgRdirectionLogicException(
								errMsg, bean.getDirectionNo());
						throw ex;
					}
				} else {
					PkgRdirectionLogicException ex = new PkgRdirectionLogicException(
							"errors.pkgrdirection.complete.status", String
									.valueOf(index));
					throw ex;
				}
			}
			index++;
		}
		if (!isSelected) {
			// 一つも選択されていない場合
			PkgRdirectionLogicException ex = new PkgRdirectionLogicException();
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
	private void updateCompleteHeader(final PkgRdirectionList bean,
			final String tantoCd) {
		DirectionHeader updateBean = directionHeaderDao.getEntity(bean
				.getDirectionDivision(), bean.getDirectionNo());
		updateBean
				.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_COMPLETE); // 指図ステータス=7（完了）
		updateBean.setUpdatorCd(tantoCd); // 更新者
		// 製造指図ヘッダ更新
		directionHeaderDao.update(updateBean);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－検索画面Dao設定.
	 * @param pkgRdirectionListDao 包装実績－検索画面Dao
	 */
	public void setPkgRdirectionListDao(
			final PkgRdirectionListDao pkgRdirectionListDao) {
		this.pkgRdirectionListDao = pkgRdirectionListDao;

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
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * フォーミュラDaoを設定します。
	 * @param rdirectionFormulaDao フォーミュラDao
	 */
	public void setRdirectionFormulaDao(
			final PkgRdirectionDirectionFormulaListDao rdirectionFormulaDao) {
		this.rdirectionFormulaDao = rdirectionFormulaDao;
	}

	/**
	 * pkgRdirectionHeaderListForReportDaoを設定します。
	 * @param pkgRdirectionHeaderListForReportDao
	 *            pkgRdirectionHeaderListForReportDao
	 */
	public void setPkgRdirectionHeaderListForReportDao(
			final PkgRdirectionHeaderListForReportDao pkgRdirectionHeaderListForReportDao) {
		this.pkgRdirectionHeaderListForReportDao = pkgRdirectionHeaderListForReportDao;
	}

	/**
	 * pkgRdirectionDetailListForReportDaoを設定します。
	 * @param pkgRdirectionDetailListForReportDao
	 *            pkgRdirectionDetailListForReportDao
	 */
	public void setPkgRdirectionDetailListForReportDao(
			final PkgRdirectionDetailListForReportDao pkgRdirectionDetailListForReportDao) {
		this.pkgRdirectionDetailListForReportDao = pkgRdirectionDetailListForReportDao;
	}

	/**
	 * pkgRdirectionProcedureListForReportDaoを設定します。
	 * @param pkgRdirectionProcedureListForReportDao
	 *            pkgRdirectionProcedureListForReportDao
	 */
	public void setPkgRdirectionProcedureListForReportDao(
			final PkgRdirectionProcedureListForReportDao pkgRdirectionProcedureListForReportDao) {
		this.pkgRdirectionProcedureListForReportDao = pkgRdirectionProcedureListForReportDao;
	}

	/**
	 * pkgRdirectionFormulaListForReportDaoを設定します。
	 * @param pkgRdirectionFormulaListForReportDao
	 *            pkgRdirectionFormulaListForReportDao
	 */
	public void setPkgRdirectionFormulaListForReportDao(
			final PkgRdirectionFormulaListForReportDao pkgRdirectionFormulaListForReportDao) {
		this.pkgRdirectionFormulaListForReportDao = pkgRdirectionFormulaListForReportDao;
	}

	/**
	 * pkgRdirectionInspectionListForReportDaoを設定します。
	 * @param pkgRdirectionInspectionListForReportDao
	 *            pkgRdirectionInspectionListForReportDao
	 */
	public void setPkgRdirectionInspectionListForReportDao(
			final PkgRdirectionInspectionListForReportDao pkgRdirectionInspectionListForReportDao) {
		this.pkgRdirectionInspectionListForReportDao = pkgRdirectionInspectionListForReportDao;
	}

	/**
	 * zaiCtrlDaoを設定します。
	 * @param zaiCtrlDao zaiCtrlDao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * lotInventoryDaoを設定します。
	 * @param lotInventoryDao lotInventoryDao
	 */
	public void setLotInvenrtoryDao(final LotInventoryDao lotInventoryDao) {
		this.lotInventoryDao = lotInventoryDao;
	}

	/**
	 * directionFormulaDaoを設定します。
	 * @param directionFormulaDao directionFormulaDao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

}
