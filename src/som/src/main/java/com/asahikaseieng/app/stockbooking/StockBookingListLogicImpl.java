/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.stockbooking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.pkgdirection.PkgDirectionConst;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.comboboxes.stockbooking.StockBookingLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.stockbooking.StockBookingLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.procedurecall.ProIfPakageStoockBookingCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingJissekiSeihinList;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingJissekiSeihinListDao;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingList;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingListDao;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingPagerCondition;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingDetailListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingFormulaListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingFormulaListForReportDao;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingHeaderListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingInspectionListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingInspectionListForReportDao;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingListConditionForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingProcedureListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingProcedureListForReportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 検査待ち在庫計上一覧 ロジック実装クラス
 * @author tosco
 */
public class StockBookingListLogicImpl implements StockBookingListLogic {

	/** エラーログ出力用モジュールコード */
	public static final String PRO_IF_PAKAGE_STOOCK_BOOKING = "PRO_IF_PAKAGE_STOOCK_BOOKING";

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** ロケーション単位の仕上実績LINE_NOの開始番号 */
	private static final int FORMULA_RESULT_LINE_NO = 10001;

	/** 検査待ち在庫計上一覧画面用のDao * */
	private StockBookingListDao stockBookingListDao;

	/** 生産ラインコンボボックス用DAO */
	private StockBookingLineForComboboxesDao stockBookingLineForComboboxesDao;

	/** 指図ヘッダーテーブル用のDao * */
	private DirectionHeaderDao directionHeaderDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 指図ステータス '5'検査待ち在庫計上 */
	private static final int STOCK_BOOKING = 5;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 製造指図フォーミュラ用Dao * */
	private DirectionFormulaDao directionFormulaDao;

	/** プロシージャコール用Dao */
	private ProcedureCallDao procedureCallDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 品目マスタ用Dao */
	private ItemDao itemDao;

	/** 製品入出庫実績取得用Dao */
	private StockBookingJissekiSeihinListDao stockBookingJissekiSeihinListDao;

	private StockBookingHeaderListForReportDao stockBookingHeaderListForReportDao;

	private StockBookingDetailListForReportDao stockBookingDetailListForReportDao;

	private StockBookingProcedureListForReportDao stockBookingProcedureListForReportDao;

	private StockBookingFormulaListForReportDao stockBookingFormulaListForReportDao;

	private StockBookingInspectionListForReportDao stockBookingInspectionListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public StockBookingListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<StockBookingList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<StockBookingList> getSearchList(
			final StockBookingPagerCondition condition) throws NoDataException {
		String strSumSuuryou = null;
		String strResultQty = null;
		String strSubtractionQty = null;

		// パラメータチェック
		checkParams(condition);
		// 一覧検索
		List<StockBookingList> list = stockBookingListDao
				.getSearchList(condition);

		// 検索結果があるか
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (StockBookingList bean : list) {
			// 差を計算
			BigDecimal sumSuuryou = bean.getSumSuuryou(); // 物流入庫実績
			BigDecimal resultQty = bean.getResultQty(); // 実績生産量
			bean.setSubtractionQty(subtractQty(sumSuuryou, resultQty)); // 差を計算
			// 差が0であればチェックボックスにチェックを入れる
			if (bean.getSubtractionQty() != null) {
				if (bean.getSubtractionQty().compareTo(new BigDecimal(0)) == 0) {
					bean.setStockBookingCheckBox(Boolean.TRUE);
				}
			}

			// 物流入庫実績
			strSumSuuryou = checkDigitUtilsLogic.format(bean
					.getUnitOfOperationManagement(), bean.getSumSuuryou());
			bean.setStrSumSuuryou(strSumSuuryou);

			// 実績生産量
			strResultQty = checkDigitUtilsLogic.format(bean
					.getUnitOfOperationManagement(), bean.getResultQty());
			bean.setStrResultQty(strResultQty);

			// 物流-生産(差分)
			strSubtractionQty = checkDigitUtilsLogic.format(bean
					.getUnitOfOperationManagement(), bean.getSubtractionQty());
			bean.setStrSubtractionQty(strSubtractionQty);

			// 小数点桁数、端数区分取得、セット
			NumberChkDisitDetail checkDetail = checkDigitUtilsLogic
					.getCheckDigit(bean.getUnitOfOperationManagement(), " ",
						" ");

			if (checkDetail.getSmallnumLength() != null) {
				bean.setQuantityDecimalPoint(checkDetail.getSmallnumLength()
						.toString()); // 小数点桁数
			}
			if (checkDetail.getRoundDivision() != null) {
				bean
						.setQuantityRound(checkDetail.getRoundDivision()
								.toString()); // 端数区分
			}
		}
		return list;
	}

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingListForReport> 検索結果リスト
	 */
	public List<StockBookingHeaderListForReport> getHeaderReportList(
			final StockBookingListConditionForReport condition) {
		List<StockBookingHeaderListForReport> list = stockBookingHeaderListForReportDao
				.getHeaderReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingDetailListForReport> 検索結果リスト
	 */
	public List<StockBookingDetailListForReport> getDetailReportList(
			final StockBookingListConditionForReport condition) {
		List<StockBookingDetailListForReport> list = stockBookingDetailListForReportDao
				.getDetailReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingDetailListForReport> 検索結果リスト
	 */
	public List<StockBookingProcedureListForReport> getProcedureReportList(
			final StockBookingListConditionForReport condition) {

		List<StockBookingProcedureListForReport> list = stockBookingProcedureListForReportDao
				.getProcedureReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingFormulaListForReport> 検索結果リスト
	 */
	public List<StockBookingFormulaListForReport> getFormulaReportList(
			final StockBookingListConditionForReport condition) {

		List<StockBookingFormulaListForReport> list = stockBookingFormulaListForReportDao
				.getFormulaReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingInspectionListForReport> 検索結果リスト
	 */
	public List<StockBookingInspectionListForReport> getInspectionReportList(
			final StockBookingListConditionForReport condition) {

		List<StockBookingInspectionListForReport> list = stockBookingInspectionListForReportDao
				.getInspectionReportList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 検査待ち在庫計上一覧の登録処理を行う
	 * @param bean 検査待ち在庫計上一覧データ
	 * @param loginUserId ログインユーザ
	 * @return DirectionHeader ヘッダー更新情報
	 * @throws Exception 例外
	 */
	public DirectionHeader update(final StockBookingList bean,
			final String loginUserId) throws Exception {

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.stockbooking.stock.update.direction.no";
		StockBookingLogicException ex = null;
		BigDecimal lineNo = new BigDecimal(1001);
		BigDecimal stepNo = new BigDecimal(1);
		DirectionFormula finishBean;

		// 更新対象データを作成する
		DirectionHeader newBean = new DirectionHeader();
		// 更新対象データに検索取得時のデータのコピーを行う
		IgnoreCaseBeanUtils.copyProperties(newBean, bean);

		// 指図ステータスに'5':検査待ち在庫計上をセット
		newBean.setDirectionStatus(new BigDecimal(STOCK_BOOKING));

		// 物流入庫実績をセット
		newBean.setPdwResult(bean.getSumSuuryou());

		// 更新者(ログインユーザー)セット
		newBean.setUpdatorCd(loginUserId);

		try {
			// 製造指図ヘッダー更新
			int updateNum = directionHeaderDao.update(newBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				ex = new StockBookingLogicException(
						"errors.stockbooking.nodata.deleted.direction.no",
						newBean.getDirectionNo());
				throw ex;
			}

			// 仕上のレコードを取得し、ロケーション単位の実績数量を登録する
			finishBean = directionFormulaDao.getEntity(newBean
					.getDirectionDivision(), newBean.getDirectionNo(), lineNo,
				stepNo);

			// ロケーション単位の実績数量を登録する
			String resultLocationCd = insertResultLocationQty(finishBean,
				newBean.getDirectionNo(), bean.getResultQty(), loginUserId);

			// 仕上げレコードには、実績ロケーションの先頭を更新する
			// 実績なしの場合は、品目の基準保管場所を設定
			finishBean.setLocationCd(resultLocationCd);
			finishBean.setUpdatorCd(loginUserId);
			updateNum = directionFormulaDao.update(finishBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				ex = new StockBookingLogicException(
						"errors.stockbooking.nodata.deleted.direction.no",
						newBean.getDirectionNo());
				throw ex;
			}

			try {
				/* 在庫更新－包装指図検査待ち移行 */
				if (!stock.inspectionDirection(newBean.getDirectionDivision(),
					newBean.getDirectionNo(), loginUserId)) {
					ex = new StockBookingLogicException(errMsg, newBean
							.getDirectionNo());
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				ex = new StockBookingLogicException(errMsg, newBean
						.getDirectionNo());
				ex.setModuleCd("StockinoutForDirection");
				ex.setInsideErrCd(newBean.getDirectionNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
			return newBean;

			// 更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			errMsg = "errors.stockbooking.optimisticlock.data.direction.no";
			ex = new StockBookingLogicException(errMsg, newBean
					.getDirectionNo());
			throw ex;
			// 更新エラー
		} catch (SQLRuntimeException e) {
			errMsg = "errors.stockbooking.update.direction.no";
			ex = new StockBookingLogicException(errMsg, newBean
					.getDirectionNo());
			throw ex;
		}
	}

	/**
	 * ロケーション単位の実績数量を登録する
	 * @param finishBean 仕上
	 * @param directionNo 包装指図番号
	 * @param resultQty 実績生産量
	 * @param loginUserId ログインユーザ
	 * @return String 実績ロケーションコード（複数ある場合は先頭)
	 * @throws Exception 例外
	 */
	private String insertResultLocationQty(final DirectionFormula finishBean,
			final String directionNo, final BigDecimal resultQty,
			final String loginUserId) throws Exception {
		List<StockBookingJissekiSeihinList> list = null;
		StockBookingLogicException ex = null;
		String resultLocationCd = null;
		BigDecimal sumResultQty = new BigDecimal(0);

		list = stockBookingJissekiSeihinListDao.getResultList(directionNo);
		int insNum = 0;
		String errMsg = "errors.stockbooking.update.direction.no";
		BigDecimal lineNo = new BigDecimal(FORMULA_RESULT_LINE_NO);
		DirectionFormula insBase = new DirectionFormula();
		IgnoreCaseBeanUtils.copyProperties(insBase, finishBean);
		insBase.setLineNo(lineNo);
		insBase.setQty(BigDecimal.ZERO);
		insBase.setInputDate(AecDateUtils.getCurrentTimestamp());
		insBase.setInputorCd(loginUserId);
		insBase.setUpdateDate(insBase.getInputDate());
		insBase.setUpdatorCd(loginUserId);
		insBase.setFillQty(null);

		// 実績をロケーション単位にフォーミュラに登録
		if (list != null && list.size() > 0) {
			List<DirectionFormula> insList = new ArrayList<DirectionFormula>();
			for (StockBookingJissekiSeihinList bean : list) {
				DirectionFormula insBean = new DirectionFormula();
				IgnoreCaseBeanUtils.copyProperties(insBean, insBase);
				insBean.setLocationCd(bean.getLocation());
				insBean.setResultQty(bean.getSumSuuryou());
				insBean.setStockpdQty(bean.getSumSuuryou());
				insList.add(insBean);
				sumResultQty = sumResultQty.add(bean.getSumSuuryou());
				insBase.setLineNo(insBase.getLineNo().add(BigDecimal.ONE));
			}
			resultLocationCd = list.get(0).getLocation();

			// 実績と実績生産量の差を調整する
			BigDecimal subtractQty = sumResultQty.subtract(resultQty);
			if (subtractQty.compareTo(BigDecimal.ZERO) != 0) {
				if (subtractQty.compareTo(BigDecimal.ZERO) > 0) {
					for (DirectionFormula bean : insList) {
						if (bean.getStockpdQty().compareTo(subtractQty) > 0) {
							bean.setStockpdQty(bean.getStockpdQty().subtract(
								subtractQty));
							break;
						} else if (bean.getStockpdQty().compareTo(subtractQty) == 0) {
							bean.setStockpdQty(BigDecimal.ZERO);
							break;
						} else {
							subtractQty = subtractQty.subtract(bean
									.getStockpdQty());
							bean.setStockpdQty(BigDecimal.ZERO);
						}
					}
				} else {
					subtractQty = resultQty.subtract(sumResultQty);
					BigDecimal addStockpdQty = insList.get(0).getStockpdQty()
							.add(subtractQty);
					insList.get(0).setStockpdQty(addStockpdQty);
				}
			}
			// 実績を登録
			for (DirectionFormula bean : insList) {
				// StockpdQty => ResultQtyにする
				bean.setResultQty(bean.getStockpdQty());
				insNum = directionFormulaDao.insert(bean);
				if (insNum != 1) {
					ex = new StockBookingLogicException(errMsg, directionNo);
					throw ex;
				}
			}

			// 実績なしの場合は、1レコード登録
		} else {
			DirectionFormula insBean = new DirectionFormula();
			IgnoreCaseBeanUtils.copyProperties(insBean, insBase);
			// StockpdQty = ResultQtyにする
			insBean.setResultQty(insBean.getStockpdQty());

			// 実績なしの場合は、ロケーションに品目の基準保管場所を設定
			Item itemBean = itemDao.getEntity(finishBean.getItemCd());
			if (itemBean != null) {
				insBean.setLocationCd(itemBean.getDefaultLocation());
			}
			insNum = directionFormulaDao.insert(insBean);
			if (insNum != 1) {
				ex = new StockBookingLogicException(errMsg, directionNo);
				throw ex;
			}
			resultLocationCd = insBean.getLocationCd();
		}
		return resultLocationCd;
	}

	/**
	 * PRO_IF_PAKAGE_STOOCK_BOOKINGを実行する
	 * @param bean 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws StockBookingLogicException プロシージャ実行時エラー
	 */
	public void callProIf(final DirectionHeader bean, final String tantoCd)
			throws StockBookingLogicException {
		String errKey = "errors.stockbooking.update.if.table.detail";
		String errDetailKey = null;
		ProIfPakageStoockBookingCallDto dto = new ProIfPakageStoockBookingCallDto();
		dto.setPStrLotNo(bean.getDirectionNo());
		dto.setPNumQty(bean.getPdwResult());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.proIfPakageStoockBooking(dto);
			// 異常終了の場合
			int intRet = dto.getPNumRet().intValue();
			if (intRet != 0) {
				switch (intRet) {
				case -1:
					errDetailKey = "errors.stockbooking.pro.if.direction.no";
					break;
				case -2:
					errDetailKey = "errors.stockbooking.pro.if.result.qty";
					break;
				case -4:
					errDetailKey = "errors.stockbooking.pro.if.stock.update";
					break;
				default:
					errDetailKey = "errors.stockbooking.pro.if";
					break;
				}
				StockBookingLogicException ex = new StockBookingLogicException(
						errKey, bean.getDirectionNo(), errDetailKey);
				ex.setModuleCd(PRO_IF_PAKAGE_STOOCK_BOOKING);
				ex.setInsideErrCd(dto.getPStrErrorCd());
				ex.setInsideErrMsg(dto.getPStrErrorMsg());
				throw ex;
			}
		} catch (LogicExceptionEx e) {
			StockBookingLogicException ex = new StockBookingLogicException(
					errKey, bean.getDirectionNo(), "errors.stockbooking.pro.if");
			ex.setModuleCd(PRO_IF_PAKAGE_STOOCK_BOOKING);
			ex.setInsideErrCd(dto.getPStrErrorCd());
			ex.setInsideErrMsg(dto.getPStrErrorMsg());
			throw ex;
		}
	}

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {
		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 物流入庫実績と実績生産量の差を計算<br> = 物流入庫実績 - 実績生産量<br>
	 * 実績生産量がnullの場合はnullを返す<br>
	 * 実績生産量がnullでない場合で、物流入庫実績がnullの場合は0として計算を行う<br>
	 * @param sumSuuryou 物流入庫実績
	 * @param resultQty 実績生産量
	 * @return BigDecimal 差(物流入庫実績 - 実績生産量)
	 */
	private BigDecimal subtractQty(final BigDecimal sumSuuryou,
			final BigDecimal resultQty) {
		BigDecimal subtractionQty = null;

		if (resultQty != null) {
			if (sumSuuryou != null) {
				subtractionQty = sumSuuryou.subtract(resultQty);
			} else {
				subtractionQty = new BigDecimal("0").subtract(resultQty);
			}
		}
		return subtractionQty;
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<StockBookingLineForComboboxes>
	 */
	public List<StockBookingLineForComboboxes> getAllLines() {
		return stockBookingLineForComboboxesDao.findAll();
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
		List<StockBookingLineForComboboxes> lineList = getAllLines();
		for (StockBookingLineForComboboxes bean : lineList) {
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
	private void checkParams(final StockBookingPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 検査待ち在庫計上一覧画面用のDaoを設定します。
	 * @param stockBookingListDao 検査待ち在庫計上一覧画面用のDao
	 */
	public void setStockBookingListDao(
			final StockBookingListDao stockBookingListDao) {
		this.stockBookingListDao = stockBookingListDao;

	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param stockBookingLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setStockBookingLineForComboboxesDao(
			final StockBookingLineForComboboxesDao stockBookingLineForComboboxesDao) {
		this.stockBookingLineForComboboxesDao = stockBookingLineForComboboxesDao;
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
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 製造指図フォーミュラ用Daoを設定します。
	 * @param directionFormulaDao 製造指図フォーミュラ用Dao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * プロシージャコール用daoを設定します。
	 * @param procedureCallDao プロシージャコール用dao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 製品入出庫実績取得用daoを設定します。
	 * @param stockBookingJissekiSeihinListDao 製品入出庫実績取得用dao
	 */
	public void setStockBookingJissekiSeihinListDao(
			final StockBookingJissekiSeihinListDao stockBookingJissekiSeihinListDao) {
		this.stockBookingJissekiSeihinListDao = stockBookingJissekiSeihinListDao;
	}

	/**
	 * 品目マスタ用Daoを設定します。
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * stockBookingHeaderListForReportDaoを設定します。
	 * @param stockBookingHeaderListForReportDao
	 *            stockBookingHeaderListForReportDao
	 */
	public void setStockBookingHeaderListForReportDao(
			final StockBookingHeaderListForReportDao stockBookingHeaderListForReportDao) {
		this.stockBookingHeaderListForReportDao = stockBookingHeaderListForReportDao;
	}

	/**
	 * stockBookingDetailListForReportDaoを設定します。
	 * @param stockBookingDetailListForReportDao
	 *            stockBookingDetailListForReportDao
	 */
	public void setStockBookingDetailListForReportDao(
			final StockBookingDetailListForReportDao stockBookingDetailListForReportDao) {
		this.stockBookingDetailListForReportDao = stockBookingDetailListForReportDao;
	}

	/**
	 * stockBookingProcedureListForReportDaoを設定します。
	 * @param stockBookingProcedureListForReportDao
	 *            stockBookingProcedureListForReportDao
	 */
	public void setStockBookingProcedureListForReportDao(
			final StockBookingProcedureListForReportDao stockBookingProcedureListForReportDao) {
		this.stockBookingProcedureListForReportDao = stockBookingProcedureListForReportDao;
	}

	/**
	 * stockBookingFormulaListForReportDaoを設定します。
	 * @param stockBookingFormulaListForReportDao
	 *            stockBookingFormulaListForReportDao
	 */
	public void setStockBookingFormulaListForReportDao(
			final StockBookingFormulaListForReportDao stockBookingFormulaListForReportDao) {
		this.stockBookingFormulaListForReportDao = stockBookingFormulaListForReportDao;
	}

	/**
	 * stockBookingInspectionListForReportDaoを設定します。
	 * @param stockBookingInspectionListForReportDao
	 *            stockBookingInspectionListForReportDao
	 */
	public void setStockBookingInspectionListForReportDao(
			final StockBookingInspectionListForReportDao stockBookingInspectionListForReportDao) {
		this.stockBookingInspectionListForReportDao = stockBookingInspectionListForReportDao;
	}
}
