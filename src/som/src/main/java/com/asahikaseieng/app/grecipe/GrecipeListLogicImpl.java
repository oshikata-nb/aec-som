/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeListPagerCondition;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 原処方-一覧検索 ロジック実装クラス
 * @author tosco
 */
public class GrecipeListLogicImpl implements GrecipeListLogic {
	/** 処方ヘッダDA0 */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;

	/**
	 * コンストラクタ.原処方-一覧検索
	 */
	public GrecipeListLogicImpl() {
	}

	/**
	 * 原処方-一覧検索処理
	 *
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<GrecipeListBean> getSearchList(
		final GrecipeListPagerCondition condition) throws NoDataException {
		checkParams(condition);
		List<GrecipeRecipeHeaderList> list = grecipeRecipeHeaderListDao.getSearchList(condition);

		List<GrecipeListBean> res = null;
		if (list.isEmpty()) {
			throw new NoDataException();
		} else {
			res = new ArrayList<GrecipeListBean>();
			for (GrecipeRecipeHeaderList head : list) {
				GrecipeListBean bean = new GrecipeListBean();
				try {
					//画面表示用beanに検索結果の値をコピー
					IgnoreCaseBeanUtils.copyProperties(bean, head);
					res.add(bean);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final GrecipeListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダーDAOを設定します。
	 * @param grecipeRecipeHeaderListDao 処方ヘッダーDAO
	 */
	public void setGrecipeRecipeHeaderListDao(final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
	}

}
