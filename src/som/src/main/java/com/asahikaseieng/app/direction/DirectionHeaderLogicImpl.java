/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouce;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouceDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipePegResouceList;
import com.asahikaseieng.dao.nonentity.direction.DirectionRecipePegResouceListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造指図ヘッダ画面ロジッククラス
 * @author tosco
 */
public class DirectionHeaderLogicImpl implements DirectionHeaderLogic {

	/** 前後設備紐付けマスタDAO */
	private DirectionRecipePegResouceListDao directionRecipePegResouceListDao;
	/** 指図ヘッダ用DAO */
	private DirectionDirectionHeaderListDao directionDirectionHeaderListDao;
	/** 指図プロシージャ用DAO */
	private DirectionDirectionProcedureListDao directionDirectionProcedureListDao;
	/** 指図フォーミュラ用DAO */
	private DirectionDirectionFormulaListDao directionDirectionFormulaListDao;
	/** 指図検査用DAO */
	private DirectionDirectionInspectionListDao directionDirectionInspectionListDao;
	/** 設備マスタ用DAO */
	private RecipeResouceDao recipeResouceDao;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ
	 */
	public DirectionHeaderLogicImpl() {
	}

	/**
	 * ホールドタンクの存在チェック
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public String checkExistsHoldTank(final String compoundTankNo, final String holdTankNo)
	throws NoDataException {
		DirectionRecipePegResouceList bean =
			directionRecipePegResouceListDao.getResourceCd(compoundTankNo, holdTankNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean.getResouceName();
	}
	/**
	 * 指図ヘッダ削除処理を行う
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final DirectionDirectionHeaderList bean) throws NoDataException {
		String errMsg = "errors.direction.stock.update";
		try {
			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			//指図ステータス=指図書発行済みの場合
			if (DirectionConst.DIRECTION_STATUS_ISSUED.compareTo(bean.getDirectionStatus()) == 0) {
				/* 在庫更新－包装・製造指図確定取消 */
				if (!stock.deFixDirection
					(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
				/* 在庫更新－配合指図確定取消 */
				if (!stock.deFixFormula
					(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			}
			/* 在庫更新－包装・製造指図取消 */
			if (!stock.cancelDirection
				(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
				throw new LogicExceptionEx(errMsg);
			}
			/* 在庫更新－配合指図取消 */
			if (!stock.cancelFormula
				(bean.getDirectionDivision(), bean.getDirectionNo(), bean.getInputorCd())) {
				throw new LogicExceptionEx(errMsg);
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(errMsg);
		}
		try {
			//指図ヘッダを削除
			int deleteNum = directionDirectionHeaderListDao.delete(bean);

			if (deleteNum != 1) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//排他エラー
			throw new OptimisticLockRuntimeException();
		}
		String directionNo = bean.getDirectionNo();
		//プロシージャ削除
		directionDirectionProcedureListDao.deleteByDirectionNo(directionNo);
		//フォーミュラ削除
		directionDirectionFormulaListDao.deleteByDirectionNo(directionNo);
		//検査削除
		directionDirectionInspectionListDao.deleteByDirectionNo(directionNo);
	}
	/**
	 * ホールドタンク名称を取得する
	 * @param holdTankNo ホールドタンクNO
	 * @return ホールドタンク名称
	 */
	public String getHoldTankName(final String holdTankNo) {
		String res = null;
		if (StringUtils.isNotEmpty(holdTankNo)) {
			RecipeResouce bean = recipeResouceDao.getEntity(holdTankNo);
			if (bean != null) {
				res = bean.getResouceName();
			}
		}
		return res;
	}
	//setter--------------------------------------------------------------------------------
	/**
	 * 前後設備紐付けマスタDAOを設定します。
	 * @param directionRecipePegResouceListDao 前後設備紐付けマスタDAO
	 */
	public void setDirectionRecipePegResouceListDao(
			final DirectionRecipePegResouceListDao directionRecipePegResouceListDao) {
		this.directionRecipePegResouceListDao = directionRecipePegResouceListDao;
	}

	/**
	 * 指図ヘッダ用DAOを設定します。
	 * @param directionDirectionHeaderListDao 指図ヘッダ用DAO
	 */
	public void setDirectionDirectionHeaderListDao(
			final DirectionDirectionHeaderListDao directionDirectionHeaderListDao) {
		this.directionDirectionHeaderListDao = directionDirectionHeaderListDao;
	}

	/**
	 * 指図プロシージャ用DAOを設定します。
	 * @param directionDirectionProcedureListDao 指図プロシージャ用DAO
	 */
	public void setDirectionDirectionProcedureListDao(
			final DirectionDirectionProcedureListDao directionDirectionProcedureListDao) {
		this.directionDirectionProcedureListDao = directionDirectionProcedureListDao;
	}

	/**
	 * 指図フォーミュラ用DAOを設定します。
	 * @param directionDirectionFormulaListDao 指図フォーミュラ用DAO
	 */
	public void setDirectionDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionDirectionFormulaListDao) {
		this.directionDirectionFormulaListDao = directionDirectionFormulaListDao;
	}

	/**
	 * 指図検査用DAOを設定します。
	 * @param directionDirectionInspectionListDao 指図検査用DAO
	 */
	public void setDirectionDirectionInspectionListDao(
			final DirectionDirectionInspectionListDao directionDirectionInspectionListDao) {
		this.directionDirectionInspectionListDao = directionDirectionInspectionListDao;
	}

	/**
	 * 設備マスタ用DAOを設定します。
	 * @param recipeResouceDao 設備マスタ用DAO
	 */
	public void setRecipeResouceDao(final RecipeResouceDao recipeResouceDao) {
		this.recipeResouceDao = recipeResouceDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
