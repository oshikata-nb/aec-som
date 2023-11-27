/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceGroupDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceGroupDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方 Asprova詳細 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeAsprovaDetailLogicImpl implements MgrecipeAsprovaDetailLogic {

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;
	/** 処方ASPROVA Dao */
	private RecipeAsprovaListDao recipeAsprovaListDao;
	/** 設備グループDao */
	private MgrecipeResouceGroupDetailDao mgrecipeResouceGroupDetailDao;
	/** 設備Dao */
	private MgrecipeResouceDetailDao mgrecipeResouceDetailDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeAsprovaDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @return RecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public RecipeHeaderList getHeader(final BigDecimal recipeId) throws NoDataException {
		checkParams(recipeId);

		RecipeHeaderList header = recipeHeaderListDao.getAsprovaHeader(
			recipeId, new BigDecimal(RecipeHeaderListDao.RECIPE_TYPE_MASTER));
		if (header == null) {
			//データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 処方ASPROVA検索（主キーで検索）処理を行う.
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param oerationGroupCd 工程グループコード
	 * @param resouceCd 設備コード
	 * @return RecipeAsprovaList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeAsprovaList findByPrimaryKey(
		final BigDecimal recipeId, final String resouceGroupCd
		, final String oerationGroupCd, final String resouceCd) throws NoDataException {
		checkParams(recipeId);

		RecipeAsprovaList bean =
			recipeAsprovaListDao.findByPrimaryKey(recipeId, resouceGroupCd, oerationGroupCd, resouceCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　設備グループマスタと、設備マスタにデータがない場合はエラーとする。
	 * @param bean 処方ASPROVA Bean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final RecipeAsprovaList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 設備グループマスタを検索
		MgrecipeResouceGroupDetail resouceGroupBean
			= mgrecipeResouceGroupDetailDao.getEntity(bean.getResouceGroupCd());

		if (resouceGroupBean == null) {
			// データが存在しない場合
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.resouce.group");
		}

		// 設備マスタを検索
		MgrecipeResouceDetail resouceBean = mgrecipeResouceDetailDao.getEntity(bean.getResouceCd());

		if (resouceBean == null) {
			// データが存在しない場合
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.resouce");
		}

		return errors;
	}

	/**
	 * 処方ASPROVA更新処理を行う.
	 * @param bean 処方ASPROVA Bean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RecipeAsprovaList bean) throws Exception {
		try {
			// 更新処理
			recipeAsprovaListDao.update(bean);
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
	 * 処方ヘッダー操作DAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
	}
	/**
	 * 処方ASPROVA Daoを設定します。
	 * @param recipeAsprovaListDao 処方ASPROVA Dao
	 */
	public void setRecipeAsprovaListDao(
			final RecipeAsprovaListDao recipeAsprovaListDao) {
		this.recipeAsprovaListDao = recipeAsprovaListDao;
	}
	/**
	 * 設備グループDaoを設定します。
	 * @param mgrecipeResouceGroupDetailDao 設備グループDao
	 */
	public void setMgrecipeResouceGroupDetailDao(
			final MgrecipeResouceGroupDetailDao mgrecipeResouceGroupDetailDao) {
		this.mgrecipeResouceGroupDetailDao = mgrecipeResouceGroupDetailDao;
	}
	/**
	 * 設備Daoを設定します。
	 * @param mgrecipeResouceDetailDao 設備Dao
	 */
	public void setMgrecipeResouceDetailDao(final MgrecipeResouceDetailDao mgrecipeResouceDetailDao) {
		this.mgrecipeResouceDetailDao = mgrecipeResouceDetailDao;
	}

}
