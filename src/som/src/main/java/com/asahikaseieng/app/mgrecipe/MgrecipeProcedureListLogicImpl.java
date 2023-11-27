/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeProcedureListLogicImpl implements MgrecipeProcedureListLogic {

	/** 処方プロシージャDao */
	private RecipeProcedureListDao recipeProcedureListDao;

	/** 処方フォーミュラDao */
	private RecipeFormulaListDao recipeFormulaListDao;

	/** 処方検査Dao */
	private RecipeInspectionListDao recipeInspectionListDao;

	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeProcedureListLogicImpl() {
	}

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @return List<MgrecipeList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RecipeProcedureList> getSearchList(final BigDecimal recipeId) throws NoDataException {
		checkParams(recipeId);

		List<RecipeProcedureList> list = recipeProcedureListDao.getSearchList(recipeId);
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
	public ActionMessages checkForDelList(final List<RecipeProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			RecipeProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal recipeId = bean.getRecipeId();
				BigDecimal stepNo = bean.getStepNo();

				// 工程に紐づく処方フォーミュラを検索
				List<RecipeFormulaList> formulaList
					= recipeFormulaListDao.findByRecipeIdStepNo(recipeId, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.mgrecipe.proc.existform.row"
										, i + 1);
				}

				// 工程に紐づく処方検査を検索
				List<RecipeInspectionList> inspectionList
					= recipeInspectionListDao.findByRecipeIdStepNo(recipeId, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.mgrecipe.proc.existins.row"
										, i + 1);
				}

				// 工程に紐づく処方フォーミュラを検索
				List<RecipeFormulaList> finishList
					= recipeFormulaListDao.findFinishByRecipeIdStepNo(recipeId, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上が存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.mgrecipe.proc.existfini.row"
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
	public ActionMessages checkForRegist(final List<RecipeProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			RecipeProcedureList bean = searchProcList.get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = MgrecipeUtil.addError(errors
									, "errors.mgrecipe.no.operation.row"
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
	public void regist(final MgrecipeProcedureListForm frm, final String tantoCd) throws Exception {
		int insertNum;

		try {

			// 最終STEP_NO を取得
			RecipeProcedureList stepBean
				= recipeProcedureListDao.getLastStepNo(new BigDecimal(frm.getRecipeId()));
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (RecipeProcedureList bean : frm.getSearchProcList()) {
				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// レシピインデックスで一括削除
			recipeProcedureListDao.deleteByRecipeId(frm.getRecipeId());

			// 登録処理
			for (RecipeProcedureList bean : frm.getSearchProcList()) {
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = recipeProcedureListDao.insert(bean);
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
	 * @param recipeProcedureListDao 処方プロシージャDao
	 */
	public void setRecipeProcedureListDao(final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;
	}

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param recipeFormulaListDao 処方フォーミュラDao
	 */
	public void setRecipeFormulaListDao(final RecipeFormulaListDao recipeFormulaListDao) {
		this.recipeFormulaListDao = recipeFormulaListDao;
	}

	/**
	 * 処方検査Daoを設定します。
	 * @param recipeInspectionListDao 処方検査Dao
	 */
	public void setRecipeInspectionListDao(
			final RecipeInspectionListDao recipeInspectionListDao) {
		this.recipeInspectionListDao = recipeInspectionListDao;
	}

	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
