/*
 * Created on 2009/02/17
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

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 基本処方－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeFinishListLogicImpl implements MgrecipeFinishListLogic {

	/** 処方フォーミュラDao */
	private RecipeFormulaListDao recipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList> データ
	 * @throws NoDataException 例外
	 */
	public List<RecipeFormulaList> getSearchList(final BigDecimal recipeId)
			throws NoDataException {

		checkParams(recipeId);

		List<RecipeFormulaList> list = recipeFormulaListDao
				.getSearchFinishList(recipeId);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(
			final List<RecipeFormulaList> searchList) {
		ActionMessages errors = new ActionMessages();

		if (searchList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchList.size(); i++) {
			RecipeFormulaList bean = searchList.get(i);

			// 品目マスタを検索
			MgrecipeItemQueueDetail opeBean = mgrecipeItemQueueDetailDao
					.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.item.queue.row", i + 1);
			}
		}
		return errors;
	}

	/**
	 * 処方フォーミュラ登録処理を行う.
	 * @param frm 仕上タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final MgrecipeFinishListForm frm, final String tantoCd)
			throws Exception {
		int insertNum;

		try {

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0)
					.getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (RecipeFormulaList bean : frm.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// レシピインデックス、LINE_TYPEで一括削除
			recipeFormulaListDao.deleteFinishListByRecipeId(frm.getRecipeId());

			// 登録処理
			for (RecipeFormulaList bean : frm.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = recipeFormulaListDao.insert(bean);
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
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param recipeFormulaListDao 処方フォーミュラDao
	 */
	public void setRecipeFormulaListDao(
			final RecipeFormulaListDao recipeFormulaListDao) {
		this.recipeFormulaListDao = recipeFormulaListDao;
	}

	/**
	 * mgrecipeItemQueueDetailDaoを設定します。
	 * @param mgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao
	 */
	public void setMgrecipeItemQueueDetailDao(
			final MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao) {
		this.mgrecipeItemQueueDetailDao = mgrecipeItemQueueDetailDao;
	}
}
