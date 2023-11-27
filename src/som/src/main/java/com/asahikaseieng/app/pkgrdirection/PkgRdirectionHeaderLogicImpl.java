/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.pkgrdirection;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.InoutRecordUpdate;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.app.pkgdirection.PkgDirectionConst;
import com.asahikaseieng.dao.entity.directiondetail.DirectionDetail;
import com.asahikaseieng.dao.entity.directiondetail.DirectionDetailDao;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspection;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspectionDao;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecordDao;
import com.asahikaseieng.dao.entity.lotinventory.LotInventory;
import com.asahikaseieng.dao.entity.lotinventory.LotInventoryDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 包装実績－包装指図ヘッダー ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionHeaderLogicImpl implements PkgRdirectionHeaderLogic {

	/** 製造指図ヘッダDao */
	private DirectionHeaderDao directionHeaderDao;

	/** 製造指図プロシージャDao */
	private DirectionProcedureDao directionProcedureDao;

	/** 製造指図フォーミュラDao */
	private DirectionFormulaDao directionFormulaDao;

	/** 製造指図検査Dao */
	private DirectionInspectionDao directionInspectionDao;

	/** 製造指図明細Dao */
	private DirectionDetailDao directionDetailDao;

	/** 品目マスタDao */
	private ItemDao itemDao;

	/** 包装実績－製造指図プロシージャDao */
	private PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao;

	/** 包装実績－製造指図フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao;

	/** 包装実績－製造指図検査Dao */
	private PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao;

	/** 包装実績－製造指図明細Dao */
	private PkgRdirectionDirectionDetailListDao pkgRdirectionDirectionDetailListDao;

	/** 数値桁数チェックロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 受払履歴Dao */
	private InoutRecordDao inoutRecordDao;

	private LotInventoryDao lotInventoryDao;

	/**
	 * コンストラクタ.包装指図ヘッダー
	 */
	public PkgRdirectionHeaderLogicImpl() {
	}

	/**
	 * ヘッダー情報画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績ヘッダー Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setPkgRdirectionHeaderForm(final HttpServletRequest request,
			final PkgRdirectionHeaderForm frm) throws NoDataException {
		checkParams(frm);

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String pkgDirectionNo = frm.getDirectionNo();

		// 製造指図ヘッダー検索
		DirectionHeader bean = directionHeaderDao.getEntity(directionDivision,
			pkgDirectionNo);
		if (bean == null) {
			throw new NoDataException();
		}
		if (bean.getResultSdate() != null) {
			frm.setResultSDay(AecDateUtils.dateFormat(bean.getResultSdate(),
				"yyyy/MM/dd"));
			frm.setResultSTime(AecDateUtils.dateFormat(bean.getResultSdate(),
				"HH:mm"));
		}
		if (bean.getResultEdate() != null) {
			frm.setResultEDay(AecDateUtils.dateFormat(bean.getResultEdate(),
				"yyyy/MM/dd"));
			frm.setResultETime(AecDateUtils.dateFormat(bean.getResultEdate(),
				"HH:mm"));
		}
		frm.setRemark(bean.getRemark());
		frm.setNotes(bean.getNotes());
		frm.setUpdateDate(bean.getUpdateDate());

		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null);

		// 小数点桁数
		if (checkDetail.getSmallnumLength() != null) {
			frm.setQtyDecimalPoint(checkDetail.getSmallnumLength().toString());
		}
		// 端数区分
		if (checkDetail.getRoundDivision() != null) {
			frm.setQtyRoundDivision(checkDetail.getRoundDivision().toString());
		}
	}

	/**
	 * 検索処理
	 * @param frm 包装実績ヘッダー Form
	 * @return DirectionHeader
	 * @throws NoDataException データが存在しない例外
	 */
	public DirectionHeader getEntity(final PkgRdirectionHeaderForm frm)
			throws NoDataException {
		checkParams(frm);

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String pkgDirectionNo = frm.getDirectionNo();

		// 製造指図ヘッダー検索
		DirectionHeader bean = directionHeaderDao.getEntity(directionDivision,
			pkgDirectionNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/**
	 * 更新処理を行う
	 * @param frm 包装実績ヘッダー Form
	 * @param tantoCd 更新者
	 */
	public void update(final PkgRdirectionHeaderForm frm, final String tantoCd) {

		if (frm == null) {
			throw new IllegalArgumentException(
					"PkgRdirectionHeaderForm == null");
		}
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String pkgDirectionNo = frm.getDirectionNo();
		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.pkgrdirection.stock.update";

		try {

			// 製造指図ヘッダー検索
			DirectionHeader bean = directionHeaderDao.getEntity(
				directionDivision, pkgDirectionNo);
			if (bean == null) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

			// 検索時の更新日付を設定
			bean.setUpdateDate(frm.getUpdateDate());

			// 2：指図書発行済 or 3:包装実績入力 or 4：包装記録済の場合は、3:包装実績入力にステータスを更新
			BigDecimal directionStatus = null;
			if (StringUtils.isNotEmpty(frm.getDirectionStatus())) {
				directionStatus = new BigDecimal(frm.getDirectionStatus());
			}
			if ((PkgRdirectionConst.DIRECTION_STATUS_ISSUE
					.equals(directionStatus))
					|| (PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS
							.equals(directionStatus))
					|| (PkgRdirectionConst.DIRECTION_STATUS_OUTPUT_DOC
							.equals(directionStatus))) {
				bean
						.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS);
			}
			// 完了指定の場合は、ステータスを完了に更新する
			BigDecimal inputDirectionStatus = null;
			if (StringUtils.isNotEmpty(frm.getInputDirectionStatus())) {
				inputDirectionStatus = new BigDecimal(frm
						.getInputDirectionStatus());
			}
			if (PkgRdirectionConst.DIRECTION_STATUS_COMPLETE
					.equals(inputDirectionStatus)) {
				if (PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_INPUT
						.equals(directionStatus)) {
					bean
							.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_COMPLETE);
				} else if (PkgRdirectionConst.DIRECTION_STATUS_INSPECTION_WAIT
						.equals(directionStatus)) {
					PkgRdirectionDirectionFormulaList finshBean = pkgRdirectionDirectionFormulaListDao
							.getFinishResultQty(directionDivision,
								pkgDirectionNo);
					if ((finshBean == null)
							|| (finshBean.getResultQty() == null)
							|| (finshBean.getResultQty()
									.equals(BigDecimal.ZERO))) {
						bean
								.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_COMPLETE);
					} else {
						errMsg = "errors.pkgrdirection.status.complete";
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				} else {
					PkgRdirectionDirectionFormulaList finshBean = pkgRdirectionDirectionFormulaListDao
							.getFinishQty(directionDivision, pkgDirectionNo);
					if ((finshBean == null)
							|| (finshBean.getResultQty() == null)
							|| (finshBean.getResultQty()
									.equals(BigDecimal.ZERO))) {
						bean
								.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_COMPLETE);
					} else {
						errMsg = "errors.pkgrdirection.status.complete";
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}

				}

			}

			// 包装開始実績日時を設定
			String strDate = frm.getResultSDay() + " " + frm.getResultSTime()
					+ ":00";
			bean.setResultSdate(AecDateUtils.getTimestampYmdHmFormat(strDate,
				"yyyy/MM/dd HH:mm:ss"));
			// 包装終了実績日時を設定
			strDate = frm.getResultEDay() + " " + frm.getResultETime() + ":59";
			bean.setResultEdate(AecDateUtils.getTimestampYmdHmFormat(strDate,
				"yyyy/MM/dd HH:mm:ss"));
			bean.setLotNo(frm.getLotNo()); // ロット番号
			bean.setNotes(frm.getNotes()); // 注釈
			bean.setRemark(frm.getRemark()); // 備考
			bean.setUpdatorCd(tantoCd); // 更新者

			// 更新処理
			int updateNum = directionHeaderDao.update(bean);
			if (updateNum != 1) {

				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

			// ﾌｫｰﾐｭﾗデータ取得
			DirectionFormula formula = directionFormulaDao.getEntity(bean
					.getDirectionDivision(), bean.getDirectionNo(),
				PkgRdirectionConst.LINE_NO_FINISH_START_NO, new BigDecimal(1));

			// 配合データがある場合
			if (formula != null) {
				LotInventory updateLotBean = lotInventoryDao.getEntity(formula
						.getItemCd(), formula.getLocationCd(), bean
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
			List<PkgRdirectionDirectionFormulaList> finFormList = pkgRdirectionDirectionFormulaListDao
					.getSearchFinishList(directionDivision, pkgDirectionNo);
			PkgRdirectionDirectionFormulaList finFormBean = finFormList.get(0);
			// 製品ロットNO
			finFormBean.setLotNo(bean.getLotNo());
			// 仕上げデータ更新
			updateNum = pkgRdirectionDirectionFormulaListDao
					.update(finFormBean);
			if (updateNum == 0) {
				// 更新対象無しエラー
				throw new OptimisticLockRuntimeException();
			}
			// 受払履歴の時刻を変更する
			InoutRecordUpdate inout = new InoutRecordUpdate(inoutRecordDao,
					bean.getUpdatorCd());
			inout.updateInoutRecord4Mat(bean.getDirectionNo(), bean
					.getResultSdate());
			inout.updateInoutRecord4Pro(bean.getDirectionNo(), bean
					.getResultEdate());

			// 完了の場合
			if (PkgRdirectionConst.DIRECTION_STATUS_COMPLETE.equals(bean
					.getDirectionStatus())) {
				try {
					/* 在庫更新－包装・製造指図完了 */
					if (!stock.completeDirection(directionDivision,
						pkgDirectionNo, tantoCd)) {
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {

			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 分納処理時のチェックを行う.<br>
	 * @param frm 包装実績ヘッダー Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkFordivide(final PkgRdirectionHeaderForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 製造指図ヘッダー検索
		DirectionHeader bean = directionHeaderDao.getEntity(directionDivision,
			directionNo);
		if (bean == null) {

			// 分納元データなし
			throw new OptimisticLockRuntimeException();
		}

		// 分納可能なステータスでない場合
		if ((!bean.getDirectionStatus().equals(
			PkgRdirectionConst.DIRECTION_STATUS_ISSUE))
				&& (!bean.getDirectionStatus().equals(
					PkgRdirectionConst.DIRECTION_STATUS_INPUT_RESULTS))) {
			errMsg = new ActionMessage("errors.pkgrdirection.not.divide.status");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
		}
		return errMsgs;
	}

	/**
	 * 分納処理を行う
	 * @param frm 包装実績ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws LogicException 発番失敗時の例外
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 * @return 分納先包装指図番号
	 */
	public String divide(final PkgRdirectionHeaderForm frm, final String tantoCd)
			throws LogicException, IllegalAccessException,
			InvocationTargetException {

		if (frm == null) {
			throw new IllegalArgumentException(
					"PkgRdirectionHeaderForm == null");
		}

		try {
			// 発番処理を行う
			String pkgDirectionNo = getNumberLogic
					.getPkgDirectionNo(AecDateUtils.getCurrentTimestamp());
			if (StringUtils.isEmpty(pkgDirectionNo)) {
				throw new LogicException();
			}

			// 製造指図ヘッダー登録
			DirectionHeader headerBean = insertDirectionHeader(frm,
				pkgDirectionNo, tantoCd);

			// 製造指図プロシージャ登録
			insertDirectionProcedure(frm, headerBean);

			// 製造指図フォーミュラ登録
			insertDirectionFormula(frm, headerBean);

			// 製造指図検査登録
			insertDirectionInspection(frm, headerBean);

			// 製造指図明細登録
			insertDirectionDetail(frm, headerBean);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			String errMsg = "errors.pkgrdirection.stock.update";
			BigDecimal directionDivision = new BigDecimal(frm
					.getDirectionDivision());

			try {
				/* 在庫更新－包装・製造指図入力 */
				if (!stock.entryDirection(directionDivision, pkgDirectionNo,
					tantoCd)) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
				/* 在庫更新－配合指図入力 */
				if (!stock.entryFormula(directionDivision, pkgDirectionNo,
					tantoCd)) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				LogicExceptionEx ex = new LogicExceptionEx(errMsg);
				throw ex;
			}
			update(frm, tantoCd);

			return pkgDirectionNo;

		} catch (NotSingleRowUpdatedRuntimeException e) {

			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 製造指図ヘッダー登録（分納処理①）
	 * @param frm 包装実績ヘッダー Form
	 * @param pkgDirectionNo 発番した包装指図番号
	 * @param tantoCd 更新者
	 */
	private DirectionHeader insertDirectionHeader(
			final PkgRdirectionHeaderForm frm, final String pkgDirectionNo,
			final String tantoCd) {
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 製造指図ヘッダー検索
		DirectionHeader bean = directionHeaderDao.getEntity(directionDivision,
			directionNo);
		if (bean == null) {

			// 更新データなし
			throw new OptimisticLockRuntimeException();
		}
		Timestamp updDate = AecDateUtils.getCurrentTimestamp();
		DirectionHeader newBean = new DirectionHeader();

		newBean.setDirectionDivision(bean.getDirectionDivision());
		newBean.setDirectionNo(pkgDirectionNo);
		newBean.setDirectionDate(updDate);
		newBean
				.setDirectionStatus(PkgRdirectionConst.DIRECTION_STATUS_UN_CONFIRMED);
		newBean.setRecipeId(bean.getRecipeId());
		newBean.setRecipeCd(bean.getRecipeCd());
		newBean.setRecipeVersion(bean.getRecipeVersion());
		newBean.setProductionLine(bean.getProductionLine());
		newBean.setPackageLine(bean.getPackageLine());
		newBean.setItemCd(bean.getItemCd());
		newBean.setPlanedQty(AecNumberUtils.convertBigDecimal(frm
				.getNextPlanedQty()));
		newBean.setNextPlanedQty(newBean.getPlanedQty());
		newBean.setDivideFlag(PkgRdirectionConst.DIVIDE_ON);
		newBean.setLotNo(null);
		// 包装開始実績日時を設定
		String strDate = frm.getNextSDay() + " " + frm.getNextSTime() + ":00";
		newBean.setPlanedSdate(AecDateUtils.getTimestampYmdHmFormat(strDate,
			"yyyy/MM/dd HH:mm:ss"));
		// 包装終了実績日時を設定
		strDate = frm.getNextEDay() + " " + frm.getNextETime() + ":59";
		newBean.setPlanedEdate(AecDateUtils.getTimestampYmdHmFormat(strDate,
			"yyyy/MM/dd HH:mm:ss"));
		newBean.setStampFlag(BigDecimal.ZERO);
		newBean.setProductLabelFlag(BigDecimal.ZERO);
		newBean.setProductRecordFlag(BigDecimal.ZERO);
		newBean.setStockdissLabelFlag(BigDecimal.ZERO);
		newBean.setRemark(bean.getRemark());
		newBean.setNotes(bean.getNotes());
		newBean.setInputDate(updDate);
		newBean.setInputorCd(tantoCd);
		newBean.setUpdateDate(updDate);
		newBean.setUpdatorCd(tantoCd);

		// 包装指図－製造指図ヘッダーを登録
		int insertNum = directionHeaderDao.insert(newBean);
		if (insertNum != 1) {

			// 重複エラー
			throw new DuplicateRuntimeException(0);
		}
		return newBean;
	}

	/**
	 * 製造指図プロシージャ登録（分納処理②）
	 * @param frm 包装実績ヘッダー Form
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertDirectionProcedure(final PkgRdirectionHeaderForm frm,
			final DirectionHeader headerBean) throws IllegalAccessException,
			InvocationTargetException {
		List<PkgRdirectionDirectionProcedureList> list = null;

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 分納元の製造指図プロシージャを検索
		list = pkgRdirectionDirectionProcedureListDao.getDivideOriginList(
			directionDivision, directionNo);
		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgRdirectionDirectionProcedureList bean = list.get(i);
				DirectionProcedure newBean = new DirectionProcedure();

				// 分納元の製造指図プロシージャからコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// ヘッダーの項目を設定
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

				// 初期化対象項目
				newBean.setResultSdate(null);
				newBean.setResultEdate(null);
				newBean.setResultConditionTemp(null);
				newBean.setResultStirSpeed(null);
				newBean.setResultPh(null);
				// 包装開始実績日時を設定
				String strDate = frm.getNextSDay() + " " + frm.getNextSTime()
						+ ":00";
				newBean.setStartDate(AecDateUtils.getTimestampYmdHmFormat(
					strDate, "yyyy/MM/dd HH:mm:ss"));
				// 包装終了実績日時を設定
				strDate = frm.getNextEDay() + " " + frm.getNextETime() + ":59";
				newBean.setEndDate(AecDateUtils.getTimestampYmdHmFormat(
					strDate, "yyyy/MM/dd HH:mm:ss"));

				// 製造指図プロシージャに登録
				int insertNum = directionProcedureDao.insert(newBean);
				if (insertNum != 1) {

					// 重複エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 製造指図フォーミュラ登録（分納処理③）
	 * @param frm 包装実績ヘッダー Form
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertDirectionFormula(final PkgRdirectionHeaderForm frm,
			final DirectionHeader headerBean) throws IllegalAccessException,
			InvocationTargetException {
		List<PkgRdirectionDirectionFormulaList> list = null;
		BigDecimal multiple = null;
		String unitDiv = PkgRdirectionConst.UNIT_DIVISION_HAIGO;
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal roundDivision = new BigDecimal(
				CheckDigitUtilsLogic.ROUND_DOWN_UP); // 切上げ

		// 数値桁数マスタの設定を取得
		NumberChkDisitDetail check = checkDigitUtilsLogic.getCheckDigit(
			unitDiv, null, null);

		// 丸めモードを取得
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(check
				.getRoundDivision().intValue());

		// 少数点以下桁数を取得
		// int decPos = check.getSmallnumLength().intValue();

		// 分納元の生産予定数量を取得する
		BigDecimal planedQty = AecNumberUtils.convertBigDecimal(frm
				.getPlanedQty());

		// 次回生産予定数量÷分納元の生産予定数量で比率を算出
		multiple = headerBean.getPlanedQty().divide(planedQty,
			Constants.SYOSU_KETA, round);

		// 製造指図フォーミュラを検索
		list = pkgRdirectionDirectionFormulaListDao.getDivideOriginList(
			directionDivision, directionNo);
		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgRdirectionDirectionFormulaList bean = list.get(i);
				DirectionFormula newBean = new DirectionFormula();

				// 分納元の製造指図フォーミュラからコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				if (PkgRdirectionConst.LINE_TYPE_COMBINE.equals(newBean
						.getLineType())) {
					// 配合の場合

					BigDecimal qty = BigDecimal.ZERO;
					if (bean.getQty() != null
							&& bean.getQty().compareTo(BigDecimal.ZERO) != 0) {
						// 比率を掛ける
						qty = bean.getQty().multiply(multiple);

						// 切り上げ
						newBean.setQty(checkDigitUtilsLogic.round(unitDiv,
							null, null, qty, roundDivision));
					} else {
						newBean.setQty(qty);
					}

				} else {
					// 仕上の場合

					// 仕上の開始番号の場合の処理
					if (PkgRdirectionConst.LINE_NO_FINISH_START_NO
							.equals(newBean.getLineNo())) {

						// 次回生産予定数量を設定
						newBean.setQty(headerBean.getPlanedQty());
					} else {
						BigDecimal qty = BigDecimal.ZERO;

						if (bean.getQty() != null
								&& bean.getQty().compareTo(BigDecimal.ZERO) != 0) {
							// 比率を掛ける
							qty = bean.getQty().multiply(multiple);

							// 切り上げ
							newBean.setQty(checkDigitUtilsLogic.round(unitDiv,
								null, null, qty, roundDivision));
						} else {
							newBean.setQty(qty);
						}
					}

					// 充填予定数量を算出する
					BigDecimal fillQty = BigDecimal.ZERO;
					// 入数を取得する
					Item itemBean = itemDao.getEntity(bean.getItemCd());
					BigDecimal numberOfInsertions = BigDecimal.ONE;
					if (itemBean.getNumberOfInsertions() != null) {
						numberOfInsertions = itemBean.getNumberOfInsertions();
					}
					if (newBean.getQty().compareTo(BigDecimal.ZERO) != 0) {
						// 充填予定数量 = 生産予定数量×品目マスタ.入数
						fillQty = newBean.getQty().multiply(numberOfInsertions);
						// 切り上げ
						newBean.setFillQty(checkDigitUtilsLogic.round(itemBean
								.getUnitOfOperationManagement(), null, null,
							fillQty, roundDivision));
					} else {
						newBean.setFillQty(fillQty);
					}
				}

				// ヘッダーの項目を設定
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

				// 初期化対象項目
				newBean.setStockpdQty(null);
				newBean.setResultQty(null);
				newBean.setSampleQty(null);
				newBean.setLossQty(null);
				newBean.setDefectQty(null);
				newBean.setAdjustQty(null);
				newBean.setFillResultQty(null);
				newBean.setLocationCd(null);
				newBean.setNextLocationCd(null);
				newBean.setNextAfterLocationCd(null);
				newBean.setManufacturerLotNo(null);
				newBean.setLotNo(null);

				// 製造指図フォーミュラに登録
				int insertNum = directionFormulaDao.insert(newBean);
				if (insertNum != 1) {
					// 重複エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 製造指図検査登録
	 * @param frm 包装実績ヘッダー Form
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertDirectionInspection(final PkgRdirectionHeaderForm frm,
			final DirectionHeader headerBean) throws IllegalAccessException,
			InvocationTargetException {
		List<PkgRdirectionDirectionInspectionList> list = null;

		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();

		// 包装指図検査を検索
		list = pkgRdirectionDirectionInspectionListDao.getDivideOriginList(
			directionDivision, directionNo);

		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgRdirectionDirectionInspectionList bean = list.get(i);
				DirectionInspection newBean = new DirectionInspection();

				// 分納元の製造指図検査からコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// ヘッダーの項目を設定
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

				// 初期化対象項目
				newBean.setResultValue1(null);
				newBean.setResultValue2(null);

				// 製造指図プロシージャに登録
				int insertNum = directionInspectionDao.insert(newBean);
				if (insertNum != 1) {

					// 重複エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 製造指図明細登録
	 * @param frm 包装実績ヘッダー Form
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertDirectionDetail(final PkgRdirectionHeaderForm frm,
			final DirectionHeader headerBean) throws IllegalAccessException,
			InvocationTargetException {
		List<PkgRdirectionDirectionDetailList> list = null;

		String directionNo = frm.getDirectionNo();

		// 包装指図明細を検索
		list = pkgRdirectionDirectionDetailListDao
				.getDivideOriginList(directionNo);

		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgRdirectionDirectionDetailList bean = list.get(i);
				DirectionDetail newBean = new DirectionDetail();

				// 分納元の製造指図検査からコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// ヘッダーの項目を設定
				newBean.setPkgDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

				// 製造指図プロシージャに登録
				int insertNum = directionDetailDao.insert(newBean);
				if (insertNum != 1) {

					// 重複エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * パラメータチェック
	 * @param frm 包装実績ヘッダー Form
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final PkgRdirectionHeaderForm frm)
			throws IllegalArgumentException {

		if (StringUtils.isEmpty(frm.getDirectionDivision())) {
			throw new IllegalArgumentException(
					"PkgRdirectionHeaderLogic IllegalArgumentException : Paramater"
							+ " is empty checkParams(directionDivision).");
		}
		if (StringUtils.isEmpty(frm.getDirectionNo())) {
			throw new IllegalArgumentException(
					"PkgRdirectionHeaderLogic IllegalArgumentException : Paramater"
							+ " is empty checkParams(directionNo).");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図ヘッダーDao設定.
	 * @param directionHeaderDao 製造指図ヘッダーDao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 製造指図プロシージャDao設定.
	 * @param directionProcedureDao 製造指図プロシージャDao
	 */
	public void setDirectionProcedureDao(
			final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * 製造指図フォーミュラDao設定.
	 * @param directionFormulaDao 製造指図フォーミュラDao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * 製造指図検査Dao設定.
	 * @param directionInspectionDao 製造指図検査Dao
	 */
	public void setDirectionInspectionDao(
			final DirectionInspectionDao directionInspectionDao) {
		this.directionInspectionDao = directionInspectionDao;
	}

	/**
	 * 製造指図明細Dao設定.
	 * @param directionDetailDao 製造指図明細Dao
	 */
	public void setDirectionDetailDao(
			final DirectionDetailDao directionDetailDao) {
		this.directionDetailDao = directionDetailDao;
	}

	/**
	 * 品目マスタDao設定.
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 包装実績－製造指図プロシージャDao設定.
	 * @param pkgRdirectionDirectionProcedureListDao 包装実績－製造指図プロシージャDao
	 */
	public void setPkgRdirectionDirectionProcedureListDao(
			final PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao) {
		this.pkgRdirectionDirectionProcedureListDao = pkgRdirectionDirectionProcedureListDao;
	}

	/**
	 * 包装実績－製造指図フォーミュラDao設定.
	 * @param pkgRdirectionDirectionFormulaListDao 包装実績－製造指図フォーミュラDao
	 */
	public void setPkgRdirectionDirectionFormulaListDao(
			final PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao) {
		this.pkgRdirectionDirectionFormulaListDao = pkgRdirectionDirectionFormulaListDao;
	}

	/**
	 * 包装実績－製造指図検査Dao設定.
	 * @param pkgRdirectionDirectionInspectionListDao 包装実績－製造指図検査Dao
	 */
	public void setPkgRdirectionDirectionInspectionListDao(
			final PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao) {
		this.pkgRdirectionDirectionInspectionListDao = pkgRdirectionDirectionInspectionListDao;
	}

	/**
	 * 包装実績－製造指図明細Dao設定.
	 * @param pkgRdirectionDirectionDetailListDao 包装実績－製造指図明細Dao
	 */
	public void setPkgRdirectionDirectionDetailListDao(
			final PkgRdirectionDirectionDetailListDao pkgRdirectionDirectionDetailListDao) {
		this.pkgRdirectionDirectionDetailListDao = pkgRdirectionDirectionDetailListDao;
	}

	/**
	 * 数値桁数チェックロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェックロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 発番処理用ロジッククラス設定.
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * inoutRecordDaoを設定します。
	 * @param inoutRecordDao inoutRecordDao
	 */
	public void setInoutRecordDao(final InoutRecordDao inoutRecordDao) {
		this.inoutRecordDao = inoutRecordDao;
	}

	/**
	 * lotInventoryDaoを設定します。
	 * @param lotInventoryDao lotInventoryDao
	 */
	public void setLotInvenrtoryDao(final LotInventoryDao lotInventoryDao) {
		this.lotInventoryDao = lotInventoryDao;
	}

}
