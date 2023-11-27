/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;


/**
 * 基本処方－その他タブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeOtherLogicImpl implements MgrecipeOtherLogic {

	/** 処方その他_Dao */
	private RecipeRemarkListDao recipeRemarkListDao;

	/**
	 * コンストラクタ.基本処方-その他
	 */
	public MgrecipeOtherLogicImpl() {
	}

	/**
	 * その他タブ－初期表示用.
	 *
	 * @param recipeId レシピインデックス
	 * @return Recipe データ
	 */
	public RecipeRemarkList getByRecipeId(final BigDecimal recipeId) {

		checkParams(recipeId);

		RecipeRemarkList list = recipeRemarkListDao.getByRecipeId(recipeId);

		return list;
	}

	/**
	 * 処方その他登録処理を行う.
	 * @param bean その他タブ
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final RecipeRemarkList bean) throws Exception {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			int updateNum = recipeRemarkListDao.update(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
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
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方その他_Daoを設定します。
	 * @param recipeRemarkListDao 処方その他_Dao
	 */
	public void setRecipeRemarkListDao(
			final RecipeRemarkListDao recipeRemarkListDao) {
		this.recipeRemarkListDao = recipeRemarkListDao;
	}

}
