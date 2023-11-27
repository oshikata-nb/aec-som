/*
 * Created on 2009/02/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.pkgdirection;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.app.mgrecipe.MgrecipeConst;
import com.asahikaseieng.app.numbering.GetNumberLogic;
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
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouce;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouceDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeHeaderDetailDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionRecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.AecStringUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 包装指図－包装指図ヘッダー ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionHeaderLogicImpl implements PkgDirectionHeaderLogic {

	/** 品目マスタDao */
	private ItemDao itemDao;

	/** 設備マスタDao */
	private RecipeResouceDao recipeResouceDao;

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

	/** 包装指図－処方ヘッダーDao */
	private PkgDirectionRecipeHeaderDetailDao pkgDirectionRecipeHeaderDetailDao;

	/** 包装指図－処方プロシージャDao */
	private PkgDirectionRecipeProcedureListDao pkgDirectionRecipeProcedureListDao;

	/** 包装指図－処方フォーミュラDao */
	private PkgDirectionRecipeFormulaListDao pkgDirectionRecipeFormulaListDao;

	/** 包装指図－処方検査Dao */
	private PkgDirectionRecipeInspectionListDao pkgDirectionRecipeInspectionListDao;

	/** 包装指図－製造指図プロシージャDao */
	private PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao;

	/** 包装指図－製造指図フォーミュラDao */
	private PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao;

	/** 包装指図－処方ASPROVADao */
	private PkgDirectionRecipeAsprovaListDao pkgDirectionRecipeAsprovaListDao;

	/** 包装指図－製造指図検査Dao */
	private PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao;

	/** 包装指図－製造指図明細Dao */
	private PkgDirectionDirectionDetailListDao pkgDirectionDirectionDetailListDao;

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェックロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.包装指図ヘッダー
	 */
	public PkgDirectionHeaderLogicImpl() {
	}

	/**
	 * 検索処理
	 * @param frm 包装指図ヘッダー Form
	 * @return DirectionHeader
	 * @throws NoDataException データが存在しない場合
	 */
	public DirectionHeader getEntity(final PkgDirectionHeaderForm frm)
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
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 */
	public void update(final PkgDirectionHeaderForm frm, final String tantoCd) {
		if (frm == null) {
			throw new IllegalArgumentException("PkgDirectionHeaderForm == null");
		}
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String pkgDirectionNo = frm.getDirectionNo();

		try {

			// 製造指図ヘッダー検索
			DirectionHeader bean = directionHeaderDao.getEntity(
				directionDivision, pkgDirectionNo);
			if (bean == null) {
				// 排他エラー
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
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
					/* 在庫更新－配合指図確定取消 */
					if (!stock.deFixFormula(bean.getDirectionDivision(), bean
							.getDirectionNo(), tantoCd)) {
						LogicExceptionEx ex = new LogicExceptionEx(errMsg);
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					LogicExceptionEx ex = new LogicExceptionEx(errMsg);
					throw ex;
				}
			}

			// 検索時の更新日付を設定
			bean.setUpdateDate(frm.getHeaderUpdateDate());

			// 未確定に更新
			bean
					.setDirectionStatus(PkgDirectionConst.DIRECTION_STATUS_UN_CONFIRMED);

			bean.setNotes(frm.getNotes()); // 注釈
			bean.setRemark(frm.getRemark()); // 備考
			bean.setUpdatorCd(tantoCd); // 更新者

			// 更新処理
			int updateNum = directionHeaderDao.update(bean);
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
	 * 登録処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void insert(final PkgDirectionHeaderForm frm, final String tantoCd)
			throws PkgDirectionLogicException, IllegalAccessException,
			InvocationTargetException {
		String strFormat = "yyyy/MM/dd HH:mm";
		Timestamp planedDate = null;

		if (frm == null) {
			throw new IllegalArgumentException("PkgDirectionHeaderForm == null");
		}

		DirectionHeader newBean = new DirectionHeader();
		try {
			String pkgDirectionNo = getNumberLogic.getPkgDirectionNo(frm
					.getHeaderUpdateDate());
			if (StringUtils.isEmpty(pkgDirectionNo)) {
				throw new PkgDirectionLogicException("errors.numbering", "");
			}
			newBean.setDirectionDivision(new BigDecimal(frm
					.getDirectionDivision()));
			newBean.setDirectionNo(pkgDirectionNo);
			newBean.setDirectionDate(frm.getHeaderUpdateDate());
			newBean.setDirectionStatus(BigDecimal.ONE);
			if (StringUtils.isNotEmpty(frm.getRecipeId())) {
				newBean.setRecipeId(new BigDecimal(frm.getRecipeId()));
			}
			newBean.setRecipeCd(frm.getRecipeCd());
			newBean.setRecipeVersion(frm.getRecipeVersion());
			newBean.setProductionLine(frm.getProductionLine());
			newBean.setPackageLine(frm.getPackageLine());
			newBean.setItemCd(frm.getItemCd());
			newBean.setPlanedQty(AecNumberUtils.convertBigDecimal(frm
					.getPlanedQty()));
			newBean.setLotNo(frm.getLotNo());
			if (!StringUtils.isEmpty(frm.getPlanedSDay())) {
				String strPlanedSdate = frm.getPlanedSDay();
				if (!StringUtils.isEmpty(frm.getPlanedSTime())) {
					strPlanedSdate = strPlanedSdate + " "
							+ frm.getPlanedSTime();
				} else {
					strPlanedSdate = strPlanedSdate + " "
							+ PkgDirectionConst.STR_MIN_TIME;
				}
				planedDate = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedSdate, strFormat);
				newBean.setPlanedSdate(planedDate);
			}
			if (!StringUtils.isEmpty(frm.getPlanedEDay())) {
				String strPlanedEdate = frm.getPlanedEDay();
				if (!StringUtils.isEmpty(frm.getPlanedETime())) {
					strPlanedEdate = strPlanedEdate + " "
							+ frm.getPlanedETime();
				} else {
					strPlanedEdate = strPlanedEdate + " "
							+ PkgDirectionConst.STR_MAX_TIME;
				}
				planedDate = AecDateUtils.getTimestampYmdHmFormat(
					strPlanedEdate, strFormat);
				newBean.setPlanedEdate(planedDate);
			}
			newBean.setStampFlag(BigDecimal.ZERO);
			newBean.setProductLabelFlag(BigDecimal.ZERO);
			newBean.setProductRecordFlag(BigDecimal.ZERO);
			newBean.setStockdissLabelFlag(BigDecimal.ZERO);
			newBean.setRemark(frm.getRemark());
			newBean.setNotes(frm.getNotes());
			newBean.setInputDate(frm.getHeaderUpdateDate());
			newBean.setInputorCd(tantoCd);
			newBean.setUpdateDate(frm.getHeaderUpdateDate());
			newBean.setUpdatorCd(tantoCd);

			// 包装指図－製造指図ヘッダーを登録
			int insertNum = directionHeaderDao.insert(newBean);
			if (insertNum != 1) {

				// 重複エラー
				throw new DuplicateRuntimeException(0);
			}

			// 包装指図－製造指図プロシージャを登録
			insertPkgDirectionProcedure(newBean);

			// 包装指図－製造指図フォーミュラを登録
			insertPkgDirectionFormula(frm, newBean);

			// 包装指図－製造指図検査を登録
			insertPkgDirectionInspection(newBean);

			// 包装指図－製造指図明細を登録
			insertPkgDirectionDetail(newBean, frm.getDirectionDetailList());

			frm.setDirectionNo(pkgDirectionNo);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(
					zaiCtrlDao);
			String errMsg = "errors.pkgdirection.stock.update";
			try {
				/* 在庫更新－包装・製造指図入力 */
				if (!stock.entryDirection(newBean.getDirectionDivision(),
					newBean.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
				/* 在庫更新－配合指図入力 */
				if (!stock.entryFormula(newBean.getDirectionDivision(), newBean
						.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}
		} catch (SQLRuntimeException e) {

			// 重複エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 日付・時刻文字列からTimestampを取得する
	 * @param day 日付文字列
	 * @param time 時刻文字列
	 * @return Timestamp
	 */
	private Timestamp getTimestamp(final String day, final String time) {
		String date = day + " " + time;
		Timestamp stamp = AecDateUtils.getTimestampYmdHmFormat(date,
			"yyyy/MM/dd HH:mm");
		return stamp;
	}

	/**
	 * 削除処理を行う
	 * @param frm 包装指図ヘッダー Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	public void delete(final PkgDirectionHeaderForm frm, final String tantoCd)
			throws PkgDirectionLogicException {

		if (frm == null) {
			throw new IllegalArgumentException("PkgDirectionHeaderForm == null");
		}
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String pkgDirectionNo = frm.getDirectionNo();

		// 在庫更新処理
		StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
		String errMsg = "errors.pkgdirection.stock.update";

		try {

			// 製造指図ヘッダー検索
			DirectionHeader bean = directionHeaderDao.getEntity(
				directionDivision, pkgDirectionNo);
			if (bean == null) {

				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
			// 指図書発行済みの場合
			if (PkgDirectionConst.DIRECTION_STATUS_ISSUE.equals(bean
					.getDirectionStatus())) {
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
			try {
				/* 在庫更新－包装・製造指図取消 */
				if (!stock.cancelDirection(bean.getDirectionDivision(), bean
						.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
				/* 在庫更新－配合指図取消 */
				if (!stock.cancelFormula(bean.getDirectionDivision(), bean
						.getDirectionNo(), tantoCd)) {
					throw new PkgDirectionLogicException(errMsg, "");
				}
			} catch (LogicExceptionEx e) {
				throw new PkgDirectionLogicException(errMsg, "");
			}

			// 検索時の更新日付を設定
			bean.setUpdateDate(frm.getHeaderUpdateDate());

			// 製造指図ヘッダーを削除
			int deleteNum = directionHeaderDao.delete(bean);
			if (deleteNum != 1) {

				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}

			// 製造指図プロシージャを削除
			pkgDirectionDirectionProcedureListDao.deleteByDirectionNo(bean
					.getDirectionDivision(), bean.getDirectionNo());

			// 製造指図フォーミュラを削除
			pkgDirectionDirectionFormulaListDao.deleteByDirectionNo(bean
					.getDirectionDivision(), bean.getDirectionNo());

			// 製造指図検査を削除
			pkgDirectionDirectionInspectionListDao.deleteByDirectionNo(bean
					.getDirectionDivision(), bean.getDirectionNo());

			// 製造指図検査を削除
			pkgDirectionDirectionDetailListDao.deleteByPkgDirectionNo(bean
					.getDirectionNo());

		} catch (NotSingleRowUpdatedRuntimeException e) {

			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param frm 包装指図ヘッダー Form
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final PkgDirectionHeaderForm frm)
			throws IllegalArgumentException {

		if (StringUtils.isEmpty(frm.getDirectionDivision())) {
			throw new IllegalArgumentException(
					"PkgDirectionHeaderLogic IllegalArgumentException : Paramater"
							+ " is empty checkParams(directionDivision).");
		}
		if (StringUtils.isEmpty(frm.getDirectionNo())) {
			throw new IllegalArgumentException(
					"PkgDirectionHeaderLogic IllegalArgumentException : Paramater"
							+ " is empty checkParams(directionNo).");
		}
	}

	/**
	 * 包装ラインのチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	public ActionMessage checkPackageLine(final PkgDirectionHeaderForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessage errMsg = null;

		if (!StringUtils.isEmpty(frm.getPackageLine())) {

			// 詰替・貼替指図の場合
			if (PkgDirectionConst.DIRECTION_DIVISION_REPACK.toString().equals(
				frm.getDirectionDivision())) {

				// 設備マスタ検索
				RecipeResouce bean = recipeResouceDao.getEntity(frm
						.getPackageLine());
				if (bean == null) {
					errMsg = new ActionMessage("errors.nodata.master", rb
							.getString("item.pkgdirection.package.line"));
				} else {
					// 生産ラインが相違の場合はエラー
					if (!frm.getProductionLine().equals(
						bean.getProductionLine())) {
						errMsg = new ActionMessage("errors.nodata.master", rb
								.getString("item.pkgdirection.package.line"));
					}
				}
			}
		}
		return errMsg;
	}

	/**
	 * 品目コードのチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	public ActionMessage checkItemCd(final PkgDirectionHeaderForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessage errMsg = null;

		// 初期化
		frm.setItemName(null);
		frm.setStyleOfPacking(null);
		frm.setOtherCompanyCd1(null);

		// 品目マスタを検索
		Item bean = itemDao.getEntity(frm.getItemCd());
		if (bean == null) {
			errMsg = new ActionMessage("errors.nodata.master", rb
					.getString("item.pkgdirection.item.cd"));
			return errMsg;
		}

		// 品目が製造品でない場合はエラー
		if (frm.getDirectionDivision().equals("3")) {

			if (!bean.getTypeDivision().equals(new BigDecimal(5))) {
				if (!bean.getTypeDivision().equals(new BigDecimal(6))) {
					if (!bean.getTypeDivision().equals(new BigDecimal(7))) {
						if (bean.getProductDivision().equals(
							PkgDirectionConst.ITEM_PRODUCT_DIVISION_NONE)) {
							errMsg = new ActionMessage(
									"errors.pkgdirection.item.no.product");
							return errMsg;
						}
					}
				}
			}
		} else {
			if (bean.getProductDivision().equals(
				PkgDirectionConst.ITEM_PRODUCT_DIVISION_NONE)) {
				errMsg = new ActionMessage(
						"errors.pkgdirection.item.no.product");
				return errMsg;
			}

		}
		frm.setUnitOfOperationManagement(bean.getUnitOfOperationManagement());
		frm.setItemName(bean.getItemName());
		frm.setStyleOfPacking(bean.getStyleOfPacking());
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1());
		if (bean.getNumberOfInsertions() == null) {
			frm.setNumberOfInsertions(BigDecimal.ONE);
		} else {
			frm.setNumberOfInsertions(bean.getNumberOfInsertions());
		}
		return errMsg;
	}

	/**
	 * 基本処方のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessages エラーメッセージ
	 */
	public ActionMessages checkRecipe(final PkgDirectionHeaderForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		// 初期化
		frm.setRecipeId(null);
		frm.setRecipeVersion(null);
		frm.setRecipeCd(null);
		frm.setMinQty(null);
		frm.setMaxQty(null);
		frm.setStdQty(null);
		frm.setUnitQty(null);

		// 基本処方コード入力なし
		if (StringUtils.isEmpty(frm.getRecipeCdVersion())) {
			return null;
		}

		// 入力された基本処方コード＋バージョンを分離する
		String recipeCdVersion = frm.getRecipeCdVersion();
		String[] strArray = StringUtils.split(recipeCdVersion,
			PkgDirectionConst.RECIPE_CD_VERSION_SEPARATOR);
		String recipeCd = null;
		String strVersion = null;

		if (strArray.length != 2) {
			// 入力フォーマット不正
			errMsg = new ActionMessage("errors.nodata.master", rb
					.getString("item.pkgdirection.recipe.cd"));
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}
		recipeCd = strArray[0];
		strVersion = strArray[1];

		if (!AecStringUtils.isNumeric(strVersion)) {
			// バージョンの数値エラー
			errMsg = new ActionMessage("errors.number", rb
					.getString("item.pkgdirection.recipe.cd.version"));
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}
		BigDecimal version = new BigDecimal(strVersion);

		// 処方ヘッダー検索
		PkgDirectionRecipeHeaderDetail bean = pkgDirectionRecipeHeaderDetailDao
				.getEntity(recipeCd, version, null);
		if (bean == null) {
			// 基本処方なし
			errMsg = new ActionMessage("errors.nodata.master", rb
					.getString("item.pkgdirection.recipe.cd"));
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		} else {
			// 品目コードが不一致
			if (!frm.getItemCd().equals(bean.getProduct())) {
				errMsg = new ActionMessage(
						"errors.pkgdirection.difference.recipec.item", rb
								.getString("item.pkgdirection.item.cd"));
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}

			// 生産ラインが不一致
			if (!frm.getProductionLine().equals(bean.getProductionLine())) {
				errMsg = new ActionMessage(
						"errors.pkgdirection.difference.recipec.item", rb
								.getString("item.pkgdirection.production.line"));
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}
			// 承認のチェック
			BigDecimal status = new BigDecimal(
					MgrecipeConst.APPROVAL_STATUS_APPROVAL);
			if (bean.getApprovalStatus().compareTo(status) != 0) {
				errMsg = new ActionMessage(
						"errors.direction.no.recipe.approval");
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}
			// ステータスのチェック
			String key = "errors.direction.no.recipe.status";
			String object = null;
			switch (bean.getRecipeStatus().intValue()) {
			case 1:
				object = "ラボ使用";
				break;
			case 2:
			case 3:
				break;
			case 4:
				object = "廃棄";
				break;
			case 5:
				object = "改訂中";
				break;
			case 6:
				object = "保留中";
				break;
			default:
				object = "不正";
				break;

			}
			if (StringUtils.isNotEmpty(object)) {
				errMsg = new ActionMessage(key, object);
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}
			// 有効期限のチェック
			Timestamp sdate = getTimestamp(frm.getPlanedSDay(), frm
					.getPlanedSTime());
			if (sdate == null || sdate.compareTo(bean.getStartDate()) < 0
					|| sdate.compareTo(bean.getEndDate()) > 0) {
				errMsg = new ActionMessage("errors.direction.no.recipe.priod");
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}
			frm.setRecipeId(bean.getRecipeId().toString());
			frm.setRecipeVersion(bean.getRecipeVersion());
			frm.setRecipeCd(bean.getRecipeCd());
			frm.setMinQty(bean.getMinQty());
			frm.setMaxQty(bean.getMaxQty());
			frm.setStdQty(bean.getStdQty());
			frm.setUnitQty(bean.getUnitQty());
			frm.setRemark(bean.getRecipeMemo());
			frm.setNotes(bean.getRecipeDescription());
			// 充填・包装指図の場合
			if (PkgDirectionConst.DIRECTION_DIVISION_PACK.toString().equals(
				frm.getDirectionDivision())) {

				// 包装ラインが入力ありの場合
				if (!StringUtils.isEmpty(frm.getPackageLine())) {
					List<PkgDirectionRecipeAsprovaList> list;
					frm.setPackageLineName(null);

					// 処方Asprovaより設備を検索
					list = pkgDirectionRecipeAsprovaListDao
							.getResouceList(new BigDecimal(frm.getRecipeId()),
								frm.getPackageLine());

					if ((list == null) || (list.isEmpty())) {
						// 該当なしの場合エラー
						errMsg = new ActionMessage(
								"errors.pkgdirection.difference.recipec.item",
								rb.getString("item.pkgdirection.package.line"));
						errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					} else {
						PkgDirectionRecipeAsprovaList resouceBean = list.get(0);
						frm.setPackageLineName(resouceBean.getResouceName());
					}
				}
			}
		}
		if (errMsgs.isEmpty()) {
			errMsgs = null;
		}
		return errMsgs;
	}

	/**
	 * 生産予定数量のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @param request リクエスト
	 * @return ActionMessage エラーメッセージ
	 */
	public ActionMessage checkPlanedQty(final PkgDirectionHeaderForm frm,
			final HttpServletRequest request) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		ActionMessage errMsg = null;

		// 数値桁数チェック
		if (frm.getUnitOfOperationManagement() != null) {
			errMsg = check.checkDigitMessage(
				PkgDirectionConst.UNIT_DIVISION_HAIGO, null, null, frm
						.getPlanedQty(), rb
						.getString("item.pkgdirection.planed.qty"));
		}

		// 基本処方コードが指定された場合のみチェックする
		if (errMsg == null && frm.getRecipeId() != null) {
			BigDecimal planedQty = AecNumberUtils.convertBigDecimal(frm
					.getPlanedQty());

			if (frm.getMinQty().compareTo(planedQty) > 0) {
				// 最低生産量>生産予定数量
				errMsg = new ActionMessage("errors.pkgdirection.min.qty.less",
						rb.getString("item.pkgdirection.planed.qty"));
			} else if (planedQty.compareTo(frm.getMaxQty()) > 0) {
				// 生産予定数量>最高生産量
				errMsg = new ActionMessage("errors.pkgdirection.max.qty.over",
						rb.getString("item.pkgdirection.planed.qty"));
			} else if (BigDecimal.ZERO.compareTo(frm.getUnitQty()) != 0) {
				if (planedQty.remainder(frm.getUnitQty()).compareTo(
					BigDecimal.ZERO) != 0) {
					// 単位生産量の倍数でない
					errMsg = new ActionMessage(
							"errors.pkgdirection.unit.qty.no.multiple", rb
									.getString("item.pkgdirection.planed.qty"));
				}
			}
		}
		return errMsg;
	}

	/**
	 * 製造指図番号のチェック
	 * @param frm 包装指図ヘッダー Form
	 * @return ActionMessage エラーメッセージ
	 */
	public ActionMessages checkDirectionNo(final PkgDirectionHeaderForm frm) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		ActionMessages errMsgs = null;
		ActionMessage errMsg = null;
		List<PkgDirectionDirectionDetailList> list;
		PkgDirectionDirectionDetailList bean = null;
		HashMap<String, String> directionMap = new HashMap<String, String>();

		list = frm.getDirectionDetailList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				bean = list.get(i);
				if (StringUtils.isEmpty(bean.getDirectionNo())) {
					errMsg = new ActionMessage(
							"errors.pkgdirection.required.row", String
									.valueOf(i + 1),
							rb.getString("item.pkgdirection.direction.no"));
				} else {

					// 製造指図番号を検索
					if (directionHeaderDao.getEntity(BigDecimal.ONE, bean
							.getDirectionNo()) == null) {
						errMsg = new ActionMessage(
								"errors.pkgdirection.nodata.row", String
										.valueOf(i + 1),
								rb.getString("item.pkgdirection.direction.no"));

					}
					if (directionMap.get(bean.getDirectionNo()) != null) {
						errMsg = new ActionMessage(
								"errors.pkgdirection.duplication.row", String
										.valueOf(i + 1),
								rb.getString("item.pkgdirection.direction.no"));
					} else {
						directionMap.put(bean.getDirectionNo(), bean
								.getDirectionNo());
					}
				}
				if (errMsg != null) {
					if (errMsgs == null) {
						errMsgs = new ActionMessages();
					}
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					errMsg = null;
				}
			}
		}
		return errMsgs;
	}

	/**
	 * 包装指図－製造指図プロシージャ登録
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertPkgDirectionProcedure(final DirectionHeader headerBean)
			throws IllegalAccessException, InvocationTargetException {
		List<PkgDirectionRecipeProcedureList> list = null;

		// 処方プロシージャを検索
		list = pkgDirectionRecipeProcedureListDao.getList(headerBean
				.getRecipeId());
		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgDirectionRecipeProcedureList bean = list.get(i);
				DirectionProcedure newBean = new DirectionProcedure();

				// 基本処方のプロシージャから包装指図のプロシージャをコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// ヘッダーの項目を設定
				newBean.setDirectionDivision(headerBean.getDirectionDivision());
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setStartDate(headerBean.getPlanedSdate());
				newBean.setEndDate(headerBean.getPlanedEdate());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

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
	 * 包装指図－製造指図フォーミュラ登録
	 * @param PkgDirectionHeaderForm 包装指図ヘッダー Form
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertPkgDirectionFormula(final PkgDirectionHeaderForm frm,
			final DirectionHeader headerBean) throws IllegalAccessException,
			InvocationTargetException {
		List<PkgDirectionRecipeFormulaList> list = null;
		BigDecimal multiple = null;
		boolean setFinishFlg = false;
		String unitDiv = PkgDirectionConst.UNIT_DIVISION_HAIGO;

		// 数値桁数マスタの設定を取得
		NumberChkDisitDetail check = checkDigitUtilsLogic.getCheckDigit(
			unitDiv, null, null);
		// 丸めモードを取得
		RoundingMode round = checkDigitUtilsLogic.getRoundingMode(check
				.getRoundDivision().intValue());
		// 少数点以下桁数を取得
		// int decPos = check.getSmallnumLength().intValue();

		// 処方フォーミュラを検索
		list = pkgDirectionRecipeFormulaListDao.getList(headerBean
				.getRecipeId());
		if ((list != null) && (!list.isEmpty())) {
			// 比率 = 生産予定数量÷標準生産量
			multiple = headerBean.getPlanedQty().divide(frm.getStdQty(),
				Constants.SYOSU_KETA, round);

			for (int i = 0; i < list.size(); i++) {
				PkgDirectionRecipeFormulaList bean = list.get(i);
				DirectionFormula newBean = new DirectionFormula();

				// 基本処方のフォーミュラから包装指図のフォーミュラをコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				if (PkgDirectionConst.LINE_TYPE_COMBINE.equals(newBean
						.getLineType())) {
					// 配合の場合

					// 比率を掛ける
					BigDecimal qty = bean.getQty().multiply(multiple);
					// 丸め処理
					newBean.setQty(checkDigitUtilsLogic.round(unitDiv, null,
						null, qty));

				} else {
					// 仕上の場合

					BigDecimal fillQty = null;

					// 仕上の開始番号の場合の処理
					if (PkgDirectionConst.LINE_NO_FINISH_START_NO
							.equals(newBean.getLineNo())) {

						// 生産予定数量を仕上に設定
						newBean.setQty(headerBean.getPlanedQty());
						newBean
								.setLineType(PkgDirectionConst.LINE_TYPE_PRODUCT);
						newBean.setItemCd(headerBean.getItemCd());
						newBean.setLotNo(headerBean.getLotNo());

						// 充填予定数量 = 数量×品目マスタ.入数
						fillQty = newBean.getQty().multiply(
							frm.getNumberOfInsertions());
						// 丸め処理
						fillQty = checkDigitUtilsLogic.round(frm
								.getUnitOfOperationManagement(), null, null,
							fillQty);
						setFinishFlg = true;
					} else {
						// 比率を掛ける
						BigDecimal qty = newBean.getQty().multiply(multiple);
						// 丸め処理
						newBean.setQty(checkDigitUtilsLogic.round(unitDiv,
							null, null, qty));
						// 充填予定数量 = 数量×品目マスタ.入数
						fillQty = newBean.getQty().multiply(
							bean.getNumberOfInsertions());
						// 丸め処理
						fillQty = checkDigitUtilsLogic.round(bean
								.getUnitDivision(), null, null, fillQty);
					}
					newBean.setFillQty(fillQty);
				}

				// ヘッダーの項目を設定
				newBean.setDirectionDivision(headerBean.getDirectionDivision());
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

				// 製造指図プロシージャに登録
				int insertNum = directionFormulaDao.insert(newBean);
				if (insertNum != 1) {
					// 重複エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}

		// 仕上が登録されていない場合、新規レコードを作成する
		if (!setFinishFlg) {
			DirectionFormula newBean = new DirectionFormula();
			newBean.setDirectionDivision(headerBean.getDirectionDivision());
			newBean.setDirectionNo(headerBean.getDirectionNo());
			newBean.setStepNo(BigDecimal.ONE);
			newBean.setLineNo(PkgDirectionConst.LINE_NO_FINISH_START_NO);
			newBean.setLineType(PkgDirectionConst.LINE_TYPE_PRODUCT);
			newBean.setSeq(BigDecimal.ONE);
			newBean.setQty(headerBean.getPlanedQty());
			newBean.setLineType(PkgDirectionConst.LINE_TYPE_PRODUCT);
			newBean.setItemCd(headerBean.getItemCd());
			newBean.setLotNo(headerBean.getLotNo());
			newBean.setInputDate(headerBean.getInputDate());
			newBean.setInputorCd(headerBean.getInputorCd());
			newBean.setUpdateDate(headerBean.getUpdateDate());
			newBean.setUpdatorCd(headerBean.getUpdatorCd());

			// 充填予定数量 = 生産予定数量×品目マスタ.入数
			BigDecimal fillQty = newBean.getQty().multiply(
				frm.getNumberOfInsertions());
			// 丸め処理
			newBean.setFillQty(checkDigitUtilsLogic.round(unitDiv, null, null,
				fillQty));

			// 製造指図プロシージャに登録
			int insertNum = directionFormulaDao.insert(newBean);
			if (insertNum != 1) {
				// 重複エラー
				throw new DuplicateRuntimeException(0);
			}
		}
	}

	/**
	 * 包装指図－製造指図検査登録
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertPkgDirectionInspection(final DirectionHeader headerBean)
			throws IllegalAccessException, InvocationTargetException {
		List<PkgDirectionRecipeInspectionList> list = null;

		// 処方検査を検索
		list = pkgDirectionRecipeInspectionListDao.getList(headerBean
				.getRecipeId());

		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgDirectionRecipeInspectionList bean = list.get(i);
				DirectionInspection newBean = new DirectionInspection();

				// 基本処方の検査から包装指図の検査をコピーして作成
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);

				// ヘッダーの項目を設定
				newBean.setDirectionDivision(headerBean.getDirectionDivision());
				newBean.setDirectionNo(headerBean.getDirectionNo());
				newBean.setInputDate(headerBean.getInputDate());
				newBean.setInputorCd(headerBean.getInputorCd());
				newBean.setUpdateDate(headerBean.getUpdateDate());
				newBean.setUpdatorCd(headerBean.getUpdatorCd());

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
	 * 包装指図－製造指図明細登録
	 * @param headerBean 包装指図ヘッダー
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	private void insertPkgDirectionDetail(final DirectionHeader headerBean,
			final List<PkgDirectionDirectionDetailList> list) {

		if ((list != null) && (!list.isEmpty())) {
			for (int i = 0; i < list.size(); i++) {
				PkgDirectionDirectionDetailList bean = list.get(i);
				DirectionDetail newBean = new DirectionDetail();
				newBean.setPkgDirectionNo(headerBean.getDirectionNo());
				newBean.setRowNo(bean.getRowNo());
				newBean.setDirectionNo(bean.getDirectionNo());
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

	/* -------------------- setter -------------------- */

	/**
	 * 品目マスタDao設定.
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 設備マスタDao設定.
	 * @param recipeResouceDao 設備マスタDao
	 */
	public void setRecipeResouceDao(final RecipeResouceDao recipeResouceDao) {
		this.recipeResouceDao = recipeResouceDao;
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
	 * 包装指図－処方ヘッダーDao設定.
	 * @param pkgDirectionRecipeHeaderDetailDao 包装指図－処方ヘッダーDao
	 */
	public void setPkgDirectionRecipeHeaderDetailDao(
			final PkgDirectionRecipeHeaderDetailDao pkgDirectionRecipeHeaderDetailDao) {
		this.pkgDirectionRecipeHeaderDetailDao = pkgDirectionRecipeHeaderDetailDao;
	}

	/**
	 * 包装指図－処方プロシージャDao設定.
	 * @param pkgDirectionRecipeProcedureListDao 包装指図－処方プロシージャDao
	 */
	public void setPkgDirectionRecipeProcedureListDao(
			final PkgDirectionRecipeProcedureListDao pkgDirectionRecipeProcedureListDao) {
		this.pkgDirectionRecipeProcedureListDao = pkgDirectionRecipeProcedureListDao;
	}

	/**
	 * 包装指図－処方フォーミュラDao設定.
	 * @param pkgDirectionRecipeFormulaListDao 包装指図－処方フォーミュラDao
	 */
	public void setPkgDirectionRecipeFormulaListDao(
			final PkgDirectionRecipeFormulaListDao pkgDirectionRecipeFormulaListDao) {
		this.pkgDirectionRecipeFormulaListDao = pkgDirectionRecipeFormulaListDao;
	}

	/**
	 * 包装指図－処方検査Dao設定.
	 * @param pkgDirectionRecipeInspectionListDao 包装指図－処方検査Dao
	 */
	public void setPkgDirectionRecipeInspectionListDao(
			final PkgDirectionRecipeInspectionListDao pkgDirectionRecipeInspectionListDao) {
		this.pkgDirectionRecipeInspectionListDao = pkgDirectionRecipeInspectionListDao;
	}

	/**
	 * 包装指図－処方ASPROVADao設定.
	 * @param pkgDirectionRecipeAsprovaListDao 包装指図－処方ASPROVADao
	 */
	public void setPkgDirectionRecipeAsprovaListDao(
			final PkgDirectionRecipeAsprovaListDao pkgDirectionRecipeAsprovaListDao) {
		this.pkgDirectionRecipeAsprovaListDao = pkgDirectionRecipeAsprovaListDao;
	}

	/**
	 * 包装指図－製造指図プロシージャDao設定.
	 * @param pkgDirectionDirectionProcedureListDao 包装指図－製造指図プロシージャDao
	 */
	public void setPkgDirectionDirectionProcedureListDao(
			final PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao) {
		this.pkgDirectionDirectionProcedureListDao = pkgDirectionDirectionProcedureListDao;
	}

	/**
	 * 包装指図－製造指図フォーミュラDao設定.
	 * @param pkgDirectionDirectionFormulaListDao 包装指図－製造指図フォーミュラDao
	 */
	public void setPkgDirectionDirectionFormulaListDao(
			final PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao) {
		this.pkgDirectionDirectionFormulaListDao = pkgDirectionDirectionFormulaListDao;
	}

	/**
	 * 包装指図－製造指図検査Dao設定.
	 * @param pkgDirectionDirectionInspectionListDao 包装指図－製造指図検査Dao
	 */
	public void setPkgDirectionDirectionInspectionListDao(
			final PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao) {
		this.pkgDirectionDirectionInspectionListDao = pkgDirectionDirectionInspectionListDao;
	}

	/**
	 * 包装指図－製造指図明細Dao設定.
	 * @param pkgDirectionDirectionDetailListDao 包装指図－製造指図明細Dao
	 */
	public void setPkgDirectionDirectionDetailListDao(
			final PkgDirectionDirectionDetailListDao pkgDirectionDirectionDetailListDao) {
		this.pkgDirectionDirectionDetailListDao = pkgDirectionDirectionDetailListDao;
	}

	/**
	 * 発番処理用ロジッククラス設定.
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
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
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
