/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureListDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 製造指図－フォーミュラ検索 ロジック実装クラス
 * @author tosco
 */
public class DirectionFormulaDetailLogicImpl implements DirectionFormulaDetailLogic {

	/** 製造指図－プロシージャDao */
	private DirectionDirectionProcedureListDao directionProcedureDao;
	/** 製造指図－フォーミュラDao */
	private DirectionDirectionFormulaListDao directionFormulaListDao;
	/** 品目マスタ検索Dao */
	private ItemDao itemDao;
	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;
	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * コンストラクタ.製造指図－フォーミュラ検索
	 */
	public DirectionFormulaDetailLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を<br>
	 * 製造指図工程テーブルより取得する
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final String directionNo, final BigDecimal stepNo) {
		String[] res = {null, null};
			DirectionDirectionProcedureList bean =
				directionProcedureDao.getOperationName(directionNo, stepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		return res;
	}

	/**
	 * 製造指図－フォーミュラ検索処理を行う.
	 * @param directionNo 指図番号
	 * @param stepNo   STEP_NO
	 * @param lineNo   LINE_NO
	 * @return DirectionDirectionFormulaList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public DirectionDirectionFormulaList getByPrimaryKey(final String directionNo
			, final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException {
//		checkParams(directionNo);
		checkParams(stepNo);
		checkParams(lineNo);

		DirectionDirectionFormulaList bean =
			directionFormulaListDao.getByPrimaryKey(directionNo, stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　品目マスタにデータがない場合はエラーとする。
	 * @param bean 製造指図－フォーミュラBean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final DirectionDirectionFormulaList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 品目マスタを検索
		Item opeBean = itemDao.getEntity(bean.getItemCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = DirectionUtil.addError(errors, "errors.direction.no.item");
		}

		return errors;
	}

	/**
	 * 製造指図－フォーミュラ更新処理を行う.
	 * @param bean 製造指図－フォーミュラBean
	 * @param header 製造指図ヘッダー
	 * @throws Exception データが存在しない例外
	 */
	public void update(final DirectionDirectionFormulaList bean,
			final DirectionDirectionHeaderList header) throws Exception {
		try {
			//処方ヘッダ更新
			directionCommonsLogic.update(header);

			// 在庫更新処理
			StockinoutForDirection stock = new StockinoutForDirection(zaiCtrlDao);
			String errMsg = "errors.direction.stock.update";
			try {
				/* 在庫更新－配合指図取消 */
				if (!stock.cancelFormula(bean.getStepNo(), bean.getLineNo(),
					bean.getDirectionDivision(), bean.getDirectionNo(), bean.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			// 更新処理
			directionFormulaListDao.update(bean);

			try {
				/* 在庫更新－配合指図入力 */
				if (!stock.entryFormula(bean.getStepNo(), bean.getLineNo(),
					bean.getDirectionDivision(), bean.getDirectionNo(), bean.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final BigDecimal recipeId) {

		if (recipeId == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図－プロシージャDaoを設定します。
	 * @param directionProcedureDao 製造指図－プロシージャDao
	 */
	public void setDirectionProcedureDao(
			final DirectionDirectionProcedureListDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}
	/**
	 * 製造指図－フォーミュラDaoを設定します。
	 * @param directionFormulaListDao 製造指図－フォーミュラDao
	 */
	public void setDirectionFormulaListDao(
			final DirectionDirectionFormulaListDao directionFormulaListDao) {
		this.directionFormulaListDao = directionFormulaListDao;
	}
	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
