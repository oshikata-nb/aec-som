/*
 * Created on 2009/02/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventory;
import com.asahikaseieng.dao.entity.iteminventory.ItemInventoryDao;
import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHoso;
import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHosoDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderFormulaListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderHeaderListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderInspectionListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderProcedureListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderProcedureListForReportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecStringUtils;

/**
 * 包装指図－検索画面 ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionListLogicImpl implements PkgDirectionListLogic {

	/** 指図ステータス 1:未確定 */
	private static final BigDecimal DIRECTION_STATUS_UNDECIDED = new BigDecimal(
			1);

	/** 指図ステータス 2:指図書発行済 */
	private static final BigDecimal DIRECTION_STATUS_ISSUED = new BigDecimal(2);

	/** 製品入出庫実績の製品ロットNOサイズ */
	private static final int SEIHIN_LOT_LENGTH = 20;

	/** 包装指図－検索画面Dao */
	private PkgDirectionListDao pkgDirectionListDao;

	/** 製造指図ヘッダーDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 計画包装Dao */
	private KeikakuHosoDao keikakuHosoDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 包装指図－フォーミュラDao */
	private PkgDirectionDirectionFormulaListDao pkgDirectionFormulaDao;

	/** 包装指図－製造指図明細Dao */
	private PkgDirectionDirectionDetailListDao pkgDirectionDirectionDetailListDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	private ItemDao itemDao;

	private ItemInventoryDao itemInventoryDao;

	private PkgDirectionOrderHeaderListForReportDao pkgDirectionOrderHeaderListForReportDao;

	private PkgDirectionOrderDetailListForReportDao pkgDirectionOrderDetailListForReportDao;

	private PkgDirectionOrderProcedureListForReportDao pkgDirectionOrderProcedureListForReportDao;

	private PkgDirectionOrderFormulaListForReportDao pkgDirectionOrderFormulaListForReportDao;

	private PkgDirectionOrderInspectionListForReportDao pkgDirectionOrderInspectionListForReportDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public PkgDirectionListLogicImpl() {
	}

	/**
	 * 包装指図検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgDirectionList> getSearchList(
			final PkgDirectionListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<PkgDirectionList> list = pkgDirectionListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PkgDirectionList bean : list) {
			// 数量
			String strPlanedQty = checkDigitUtilsLogic.format(
				PkgDirectionConst.UNIT_DIVISION_HAIGO, bean.getPlanedQty());
			bean.setStrPlanedQty(strPlanedQty);
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderListForReport> 検索結果リスト
	 */
	public List<PkgDirectionOrderHeaderListForReport> getHeaderReportList(
			final PkgDirectionOrderListConditionForReport condition) {
		List<PkgDirectionOrderHeaderListForReport> list = pkgDirectionOrderHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderDetailListForReport> 検索結果リスト
	 */
	public List<PkgDirectionOrderDetailListForReport> getDetailReportList(
			final PkgDirectionOrderListConditionForReport condition) {
		List<PkgDirectionOrderDetailListForReport> list = pkgDirectionOrderDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderDetailListForReport> 検索結果リスト
	 */
	public List<PkgDirectionOrderProcedureListForReport> getProcedureReportList(
			final PkgDirectionOrderListConditionForReport condition) {

		List<PkgDirectionOrderProcedureListForReport> list = pkgDirectionOrderProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderFormulaListForReport> 検索結果リスト
	 */
	public List<PkgDirectionOrderFormulaListForReport> getFormulaReportList(
			final PkgDirectionOrderListConditionForReport condition) {

		List<PkgDirectionOrderFormulaListForReport> list = pkgDirectionOrderFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderInspectionListForReport> 検索結果リスト
	 */
	public List<PkgDirectionOrderInspectionListForReport> getInspectionReportList(
			final PkgDirectionOrderListConditionForReport condition) {

		List<PkgDirectionOrderInspectionListForReport> list = pkgDirectionOrderInspectionListForReportDao
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
	private void checkParams(final PkgDirectionListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 包装指図書発行処理
	 * @param bean 一覧データ
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException エラー発生時
	 * @return KeikakuHoso 包装計画
	 */
	public KeikakuHoso issueDirection(final PkgDirectionList bean,
			final String tantoCd) throws PkgDirectionLogicException {
		KeikakuHoso insBean = null;
		String directNo = null;

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errStockMsg = "errors.pkgdirection.stock.update.direction.no";
		try {
			DirectionHeader updBean = null;
			BigDecimal directionDivision = bean.getDirectionDivision();
			directNo = bean.getDirectionNo();

			// 製造指図ヘッダーを検索する
			updBean = directionHeaderDao.getEntity(directionDivision, directNo);
			if (updBean == null) {
				String errMsg = "errors.pkgdirection.nodata.deleted.direction.no";
				throw new PkgDirectionLogicException(errMsg, directNo);
			}
			// 指図ステータス(2:指図書発行済)
			updBean.setDirectionStatus(DIRECTION_STATUS_ISSUED);
			// ロット番号
			updBean.setLotNo(bean.getLotNo());
			// 指図書発行フラグ(1:発行)
			updBean.setStampFlag(BigDecimal.ONE);
			// 指図書発行日
			updBean.setStampDate(AecDateUtils.getCurrentTimestamp());
			// 指図書発行者
			updBean.setStampTantoCd(tantoCd);
			// 更新日付（検索時の更新日付を設定)
			updBean.setUpdateDate(bean.getUpdateDate());
			// 更新者
			updBean.setUpdatorCd(tantoCd);

			// 製造指図ヘッダー更新
			int updateNum = directionHeaderDao.update(updBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.pkgdirection.nodata.deleted.direction.no";
				throw new PkgDirectionLogicException(errMsg, directNo);
			}

			// 仕上げデータ取得
			List<PkgDirectionDirectionFormulaList> finFormList = pkgDirectionFormulaDao
					.getSearchFinishList(directionDivision, directNo);
			PkgDirectionDirectionFormulaList finFormBean = finFormList.get(0);
			// 製品ロットNO
			finFormBean.setLotNo(bean.getLotNo());

			// 仕上げデータ更新
			updateNum = pkgDirectionFormulaDao.update(finFormBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				String errMsg = "errors.pkgdirection.nodata.deleted.direction.no";
				throw new PkgDirectionLogicException(errMsg, directNo);
			}

			// 指図ステータスが未確定の場合、計画包装データを登録する
			if (bean.getDirectionStatus().equals(DIRECTION_STATUS_UNDECIDED)) {
				try {
					/* 在庫更新－包装・製造指図確定 */
					if (!stock.fixDirection(bean.getDirectionDivision(),
						directNo, tantoCd)) {
						PkgDirectionLogicException ex = new PkgDirectionLogicException(
								errStockMsg, directNo);
						throw ex;
					}
					/* 在庫更新－配合指図確定 */
					if (!stock.fixFormula(bean.getDirectionDivision(),
						directNo, tantoCd)) {
						PkgDirectionLogicException ex = new PkgDirectionLogicException(
								errStockMsg, directNo);
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					PkgDirectionLogicException ex = new PkgDirectionLogicException(
							errStockMsg, directNo);
					throw ex;
				}

				// 計画包装を検索
				insBean = keikakuHosoDao.getEntity(directNo);
				if (insBean == null) {
					// 製造指図詳細を検索
					List<PkgDirectionDirectionDetailList> detailList;
					detailList = pkgDirectionDirectionDetailListDao
							.getList(directNo);

					insBean = new KeikakuHoso();
					// 包装日
					insBean.setHosobi(bean.getPlanedSdate().toString());
					// 工場コード
					insBean.setKojocode(AecStringUtils.editSpecifiedSize(bean
							.getProductionLine(), 2));
					// 包装指図番号
					insBean.setHososashizuno(AecStringUtils.editSpecifiedSize(
						directNo, 11));
					// 製品ロットNO
					insBean.setSeihinlotno(AecStringUtils.editSpecifiedSize(
						bean.getLotNo(), SEIHIN_LOT_LENGTH));
					if (detailList != null && detailList.size() > 0) {
						// 製造指図番号(先頭)
						insBean.setSeizosashizuno(AecStringUtils
								.editSpecifiedSize(detailList.get(0)
										.getDirectionNo(), 11));
					} else {
						// 製造指図番号(スペース詰)
						insBean.setSeizosashizuno(AecStringUtils
								.editSpecifiedSize(null, 11));
					}
					// 製品コード
					insBean.setSeihincode(AecStringUtils.editSpecifiedSize(bean
							.getItemCd(), 8));
					// IOコード(他社コード１)
					insBean.setIocode(AecStringUtils.editSpecifiedSize(bean
							.getOtherCompanyCd1(), 8));

					// 荷主が1:花王の時は、"2"以外は"1"
					String jishaFlag = "1";
					if (bean.getShipperDivision().equals(BigDecimal.ONE)) {
						jishaFlag = "2";
					}
					insBean.setJishaflag(jishaFlag); // 自社/花王

					// 計画数 オーバーフローの場合は、後ろから4文字までとする
					String strPlanedQty = bean.getPlanedQty().toString();
					if (strPlanedQty.length() > 4) {
						insBean.setKeikakusu(strPlanedQty
								.substring(strPlanedQty.length() - 4));
					} else {
						insBean.setKeikakusu(bean.getPlanedQty().toString());
					}
					insBean.setNyukosu("0"); // 入庫数(0固定)
					insBean.setHosojokyo("0"); // 包装状況(0:デフォルト)

				} else {
					insBean = null;
				}
			}
			return insBean;

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			String errMsg = "errors.pkgdirection.optimisticlock.data.direction.no";
			throw new PkgDirectionLogicException(errMsg, directNo);
		}
	}

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 包装計画
	 * @param bean 登録包装計画
	 * @throws PkgDirectionLogicException 更新エラー
	 */
	public void insertIfTable(final KeikakuHoso bean)
			throws PkgDirectionLogicException {

		try {
			// 包装計画新規登録
			if (keikakuHosoDao.getEntity(bean.getHososashizuno()) == null) {
				keikakuHosoDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			String directNo = StringUtils.trim(bean.getHososashizuno());
			String errMsg = "errors.pkgdirection.update.if.table.direction.no";
			throw new PkgDirectionLogicException(errMsg, directNo);
		}
	}

	/**
	 * ラベル発行処理（製造指図ヘッダー更新）
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 */
	public void issueLabel(final List<PkgDirectionList> searchList,
			final String tantoCd) {

		if (searchList == null || searchList.size() == 0) {
			throw new IllegalArgumentException("list == null");
		}

		try {
			for (PkgDirectionList bean : searchList) {
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
	 * 指図発行時に手持ち在庫をチェックして不足なら警告を出す。
	 * @param directionNo 製造指図番号
	 * @return 在庫不足量警告メッセージ
	 * @param div 製造指図番号
	 * @throws PkgDirectionLogicException 例外
	 */
	public List<String> checkInventoryFormula(final String directionNo,
			final BigDecimal div) throws PkgDirectionLogicException {
		List<String> eList = new ArrayList<String>();
		List<PkgDirectionDirectionFormulaList> list = pkgDirectionFormulaDao
				.getList(div, directionNo, BigDecimal.ZERO);
		for (PkgDirectionDirectionFormulaList formBean : list) {
			BigDecimal fQty = checkInventory(formBean.getItemCd(), formBean
					.getQty());
			if (fQty.compareTo(BigDecimal.ZERO) > 0) {
				String mes = directionNo + ":品目(" + formBean.getItemCd()
						+ ")の手持ち在庫が(" + fQty.toString() + ")不足です。";
				eList.add(mes);
			}
		}

		return eList;
	}

	/**
	 * 手持ち在庫の不足量を返す
	 * @param itemCd 品目コード
	 * @param qty 必要量
	 * @return 不足量（正） 、余裕量（負）
	 */
	private BigDecimal checkInventory(final String itemCd, final BigDecimal qty) {
		if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO;
		}
		Item item = itemDao.getEntity(itemCd);
		if (item == null
				|| item.getStockDivision().compareTo(new BigDecimal(3)) == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal rQty = qty;
		ItemInventory inv = itemInventoryDao.getEntity(itemCd);
		if (inv != null && inv.getInspectionQty() != null) {
			rQty = qty.subtract(inv.getInventoryQty());
		}
		return rQty;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－検索画面Dao設定.
	 * @param pkgDirectionListDao 包装指図－検索画面Dao
	 */
	public void setPkgDirectionListDao(
			final PkgDirectionListDao pkgDirectionListDao) {
		this.pkgDirectionListDao = pkgDirectionListDao;

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
	 * 計画包装Dao設定.
	 * @param keikakuHosoDao 計画包装Dao
	 */
	public void setKeikakuHosoDao(final KeikakuHosoDao keikakuHosoDao) {
		this.keikakuHosoDao = keikakuHosoDao;

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
	 * 包装指図－フォーミュラDaoを設定します。
	 * @param pkgDirectionFormulaDao 包装指図－フォーミュラDao
	 */
	public void setPkgDirectionFormulaDao(
			final PkgDirectionDirectionFormulaListDao pkgDirectionFormulaDao) {
		this.pkgDirectionFormulaDao = pkgDirectionFormulaDao;
	}

	/**
	 * 包装指図－製造指図明細Daoを設定します。
	 * @param pkgDirectionDirectionDetailListDao 包装指図－製造指図明細Dao
	 */
	public void setPkgDirectionDirectionDetailListDao(
			final PkgDirectionDirectionDetailListDao pkgDirectionDirectionDetailListDao) {
		this.pkgDirectionDirectionDetailListDao = pkgDirectionDirectionDetailListDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * itemInventoryDaoを設定します。
	 * @param itemInventoryDao itemInventoryDao
	 */
	public void setItemInventoryDao(final ItemInventoryDao itemInventoryDao) {
		this.itemInventoryDao = itemInventoryDao;
	}

	/**
	 * pkgDirectionOrderHeaderListForReportDaoを設定します。
	 * @param pkgDirectionOrderHeaderListForReportDao
	 *            pkgDirectionOrderHeaderListForReportDao
	 */
	public void setPkgDirectionOrderHeaderListForReportDao(
			final PkgDirectionOrderHeaderListForReportDao pkgDirectionOrderHeaderListForReportDao) {
		this.pkgDirectionOrderHeaderListForReportDao = pkgDirectionOrderHeaderListForReportDao;
	}

	/**
	 * pkgDirectionOrderDetailListForReportDaoを設定します。
	 * @param pkgDirectionOrderDetailListForReportDao
	 *            pkgDirectionOrderDetailListForReportDao
	 */
	public void setPkgDirectionOrderDetailListForReportDao(
			final PkgDirectionOrderDetailListForReportDao pkgDirectionOrderDetailListForReportDao) {
		this.pkgDirectionOrderDetailListForReportDao = pkgDirectionOrderDetailListForReportDao;
	}

	/**
	 * pkgDirectionOrderProcedureListForReportDaoを設定します。
	 * @param pkgDirectionOrderProcedureListForReportDao
	 *            pkgDirectionOrderProcedureListForReportDao
	 */
	public void setPkgDirectionOrderProcedureListForReportDao(
			final PkgDirectionOrderProcedureListForReportDao pkgDirectionOrderProcedureListForReportDao) {
		this.pkgDirectionOrderProcedureListForReportDao = pkgDirectionOrderProcedureListForReportDao;
	}

	/**
	 * pkgDirectionOrderFormulaListForReportDaoを設定します。
	 * @param pkgDirectionOrderFormulaListForReportDao
	 *            pkgDirectionOrderFormulaListForReportDao
	 */
	public void setPkgDirectionOrderFormulaListForReportDao(
			final PkgDirectionOrderFormulaListForReportDao pkgDirectionOrderFormulaListForReportDao) {
		this.pkgDirectionOrderFormulaListForReportDao = pkgDirectionOrderFormulaListForReportDao;
	}

	/**
	 * pkgDirectionOrderInspectionListForReportDaoを設定します。
	 * @param pkgDirectionOrderInspectionListForReportDao
	 *            pkgDirectionOrderInspectionListForReportDao
	 */
	public void setPkgDirectionOrderInspectionListForReportDao(
			final PkgDirectionOrderInspectionListForReportDao pkgDirectionOrderInspectionListForReportDao) {
		this.pkgDirectionOrderInspectionListForReportDao = pkgDirectionOrderInspectionListForReportDao;
	}

}
