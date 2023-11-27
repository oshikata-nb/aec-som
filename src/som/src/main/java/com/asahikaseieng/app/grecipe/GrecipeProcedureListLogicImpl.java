/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原処方-工程  ロジック実装クラス
 * @author tosco
 */
public class GrecipeProcedureListLogicImpl implements GrecipeProcedureListLogic {

	/** 処方プロシージャDao */
	private GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao;

	/** 処方フォーミュラDao */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;

	/** 処方検査Dao */
	private GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao;

	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ.原処方検索
	 */
	public GrecipeProcedureListLogicImpl() {
	}

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<GrecipeRecipeProcedureList> getSearchList(final BigDecimal recipeId) throws NoDataException {
		checkParams(recipeId);

		List<GrecipeRecipeProcedureList> list = grecipeRecipeProcedureListDao.getSearchList(recipeId);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　処方フォーミュラ、処方検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final List<GrecipeRecipeProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			GrecipeRecipeProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal recipeId = bean.getRecipeId();
				BigDecimal stepNo = bean.getStepNo();

				// 工程に紐づく処方フォーミュラを検索
				List<GrecipeRecipeFormulaList> formulaList
					= grecipeRecipeFormulaListDao.findByRecipeIdStepNo(recipeId, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errors = GrecipeUtil.addError(errors
										, "errors.grecipe.proc.existform.row"
										, i + 1);
				}

				// 工程に紐づく処方検査を検索
				List<GrecipeRecipeInspectionList> inspectionList
					= grecipeRecipeInspectionListDao.findByRecipeIdStepNo(recipeId, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errors = GrecipeUtil.addError(errors
										, "errors.grecipe.proc.existins.row"
										, i + 1);
				}

				// 工程に紐づく処方フォーミュラを検索(仕上げ)
				List<GrecipeRecipeFormulaList> finishList
					= grecipeRecipeFormulaListDao.findFinishByRecipeIdStepNo(recipeId, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上が存在する場合
					errors = GrecipeUtil.addError(errors
										, "errors.grecipe.proc.existfini.row"
										, i + 1);
				}
			}

		}

		return errors;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final List<GrecipeRecipeProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			GrecipeRecipeProcedureList bean = searchProcList.get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = GrecipeUtil.addError(errors
									, "errors.grecipe.no.operation.row"
									, i + 1);
			}

		}
		return errors;
	}

	/**
	 * 処方プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final GrecipeProcedureListForm frm, final String tantoCd) throws Exception {
		int insertNum;

		try {

			// 最終STEP_NO を取得
			GrecipeRecipeProcedureList stepBean
				= grecipeRecipeProcedureListDao.getLastStepNo(new BigDecimal(frm.getRecipeId()));
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (GrecipeRecipeProcedureList bean : frm.getSearchProcList()) {
				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// レシピインデックスで一括削除
			grecipeRecipeProcedureListDao.deleteByRecipeId(frm.getRecipeId());

			// 登録処理
			for (GrecipeRecipeProcedureList bean : frm.getSearchProcList()) {
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = grecipeRecipeProcedureListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final BigDecimal recipeId) {

		if (recipeId == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方プロシージャDaoを設定します。
	 * @param grecipeRecipeProcedureListDao 処方プロシージャDao
	 */
	public void setGrecipeRecipeProcedureListDao(
			final GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao) {
		this.grecipeRecipeProcedureListDao = grecipeRecipeProcedureListDao;
	}

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param grecipeRecipeFormulaListDao 処方フォーミュラDao
	 */
	public void setGrecipeRecipeFormulaListDao(final GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao) {
		this.grecipeRecipeFormulaListDao = grecipeRecipeFormulaListDao;
	}

	/**
	 * 処方検査Daoを設定します。
	 * @param grecipeRecipeInspectionListDao 処方検査Dao
	 */
	public void setGrecipeRecipeInspectionListDao(
			final GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao) {
		this.grecipeRecipeInspectionListDao = grecipeRecipeInspectionListDao;
	}

	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
