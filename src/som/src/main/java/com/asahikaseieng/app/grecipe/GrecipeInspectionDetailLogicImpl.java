/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 原処方検索 ロジック実装クラス
 * @author tosco
 */
public class GrecipeInspectionDetailLogicImpl implements GrecipeInspectionDetailLogic {

	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;
	/** 処方検査Dao */
	private GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao;
	/** NamesDao */
	private NamesDao namesDao;

	/**
	 * コンストラクタ.原処方検索
	 */
	public GrecipeInspectionDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public GrecipeRecipeHeaderList getHeader(final BigDecimal recipeId,
			final BigDecimal stepNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		GrecipeRecipeHeaderList header = grecipeRecipeHeaderListDao.getInspectionHeader(
			recipeId, new BigDecimal(GrecipeRecipeHeaderListDao.RECIPE_TYPE_GENERAL), stepNo);
		if (header == null) {
			//データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 処方検査-検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return GrecipeRecipeInspectionList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public GrecipeRecipeInspectionList getEntity(final BigDecimal recipeId,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);
		checkParams(lineNo);

		GrecipeRecipeInspectionList bean = grecipeRecipeInspectionListDao.getEntity(recipeId, stepNo, lineNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param bean 処方検査Bean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final GrecipeRecipeInspectionList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(bean.getInspectionCd(), "STDV");

		if (nameBean == null) {
			// データが存在しない場合
			errors = GrecipeUtil.addError(errors, "errors.grecipe.no.names");
		}

		return errors;
	}

	/**
	 * 処方検査更新処理を行う.
	 * @param bean 処方検査Bean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final GrecipeRecipeInspectionList bean) throws Exception {
		try {
			// 更新処理
			grecipeRecipeInspectionListDao.update(bean);
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
	 * @param grecipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setGrecipeRecipeHeaderListDao(final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
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
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}
}
