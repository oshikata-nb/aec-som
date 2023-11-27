/*
 * Created on 2009/03/23
 s *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原処方－仕上タブ ロジック実装クラス
 * @author tosco
 */
public class GrecipeFinishListLogicImpl implements GrecipeFinishListLogic {

	/** 処方フォーミュラDao */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private GrecipeItemQueueDetailDao grecipeItemQueueDetailDao;

	/**
	 * コンストラクタ.原処方検索
	 */
	public GrecipeFinishListLogicImpl() {
	}

	/**
	 * 仕上タブ－一覧検索処理
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeFormulaList> データ
	 * @throws NoDataException 例外
	 */
	public List<GrecipeRecipeFormulaList> getSearchList(
			final BigDecimal recipeId) throws NoDataException {

		checkParams(recipeId);

		List<GrecipeRecipeFormulaList> list = grecipeRecipeFormulaListDao
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
			final List<GrecipeRecipeFormulaList> searchList) {
		ActionMessages errors = new ActionMessages();

		if (searchList == null) {
			throw new IllegalArgumentException("IllegalArgumentException : "
					+ "Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchList.size(); i++) {
			GrecipeRecipeFormulaList bean = searchList.get(i);

			// 品目マスタを検索
			GrecipeItemQueueDetail opeBean = grecipeItemQueueDetailDao
					.getEntity(bean.getItemCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = GrecipeUtil.addError(errors,
					"errors.grecipe.no.item.queue.row", i + 1);
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
	public void regist(final GrecipeFinishListForm frm, final String tantoCd)
			throws Exception {
		int insertNum;

		try {

			// 最終LINE_NO、SEQ を再設定
			BigDecimal lastLineNo = frm.getSearchFinishList().get(0)
					.getLineNo();
			BigDecimal lastSeq = BigDecimal.ONE;
			for (GrecipeRecipeFormulaList bean : frm.getSearchFinishList()) {
				// LINE_NO 更新
				bean.setLineNo(lastLineNo);
				lastLineNo = lastLineNo.add(BigDecimal.ONE);
				// SEQ 更新
				bean.setSeq(lastSeq);
				lastSeq = lastSeq.add(BigDecimal.ONE);
			}

			// レシピインデックス、LINE_TYPEで一括削除
			grecipeRecipeFormulaListDao.deleteFinishListByRecipeId(frm
					.getRecipeId());

			// 登録処理
			for (GrecipeRecipeFormulaList bean : frm.getSearchFinishList()) {
				// 移し変え
				bean.setStepNo(new BigDecimal(bean.getStrStepNo())); // STEP_NO
				bean.setLineType(new BigDecimal(bean.getStrLineType())); // LINE_TYPE
				// 登録情報
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = grecipeRecipeFormulaListDao.insert(bean);
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
	 * 処方フォーミュラDaoを設定します。
	 * @param grecipeRecipeFormulaListDao 処方フォーミュラDao
	 */
	public void setGrecipeRecipeFormulaListDao(
			final GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao) {
		this.grecipeRecipeFormulaListDao = grecipeRecipeFormulaListDao;
	}

	/**
	 * grecipeItemQueueDetailDaoを設定します。
	 * @param grecipeItemQueueDetailDao grecipeItemQueueDetailDao
	 */
	public void setGrecipeItemQueueDetailDao(
			final GrecipeItemQueueDetailDao grecipeItemQueueDetailDao) {
		this.grecipeItemQueueDetailDao = grecipeItemQueueDetailDao;
	}
}
