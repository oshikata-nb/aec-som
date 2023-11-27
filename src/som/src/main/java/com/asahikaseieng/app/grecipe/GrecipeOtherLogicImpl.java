/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;


/**
 * 原処方－その他タブ ロジック実装クラス
 * @author tosco
 */
public class GrecipeOtherLogicImpl implements GrecipeOtherLogic {

	/** 処方その他_Dao */
	private GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao;

	/**
	 * コンストラクタ.原処方-その他
	 */
	public GrecipeOtherLogicImpl() {
	}

	/**
	 * その他タブ－初期表示用.
	 *
	 * @param recipeId レシピインデックス
	 * @return Recipe データ
	 */
	public GrecipeRecipeRemarkList getEntity(final String recipeId) {

		checkParams(recipeId);

		List<GrecipeRecipeRemarkList> list = grecipeRecipeRemarkListDao.findByRecipeId(recipeId);

		if (list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	/**
	 * 処方その他登録処理を行う.
	 * @param bean その他タブ
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final GrecipeRecipeRemarkList bean) throws Exception {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			int updateNum = grecipeRecipeRemarkListDao.update(bean);

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
	private void checkParams(final String recipeId) {

		if (recipeId == null || recipeId.equals("")) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方その他_Daoを設定します。
	 * @param grecipeRecipeRemarkListDao 処方その他_Dao
	 */
	public void setGrecipeRecipeRemarkListDao(
			final GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao) {
		this.grecipeRecipeRemarkListDao = grecipeRecipeRemarkListDao;
	}

}
