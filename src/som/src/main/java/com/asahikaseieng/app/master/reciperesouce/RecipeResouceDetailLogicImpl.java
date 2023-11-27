/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.recipepegresouce.RecipePegResouce;
import com.asahikaseieng.dao.entity.master.recipepegresouce.RecipePegResouceDao;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouce;
import com.asahikaseieng.dao.entity.master.reciperesouce.RecipeResouceDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.line.LineListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.recipepegresoucedelete.RecipePegResouceDeleteDao;
import com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailList;
import com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist.RecipePegResouceDetailListDao;
import com.asahikaseieng.dao.nonentity.master.reciperesoucedetail.RecipeResouceDetail;
import com.asahikaseieng.dao.nonentity.master.reciperesoucedetail.RecipeResouceDetailDao;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetail;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegroupdetail.RecipeResouceGroupDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 設備詳細ロジック 実装クラス.
 * @author t0011036
 */
public class RecipeResouceDetailLogicImpl implements RecipeResouceDetailLogic {

	private RecipeResouceDao recipeResouceDao;

	private RecipeResouceDetailDao recipeResouceDetailDao;

	private RecipeResouceGroupDetailDao recipeResouceGroupDetailDao;

	private LineListForComboboxesDao lineListForComboboxesDao;

	private RecipePegResouceDetailListDao recipePegResouceDetailListDao;

	private RecipePegResouceDao recipePegResouceDao;

	private RecipePegResouceDeleteDao recipePegResouceDeleteDao;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceDetailLogicImpl() {
	}

	/**
	 * 設備検索（登録用）
	 * @param resouceCd 設備コード
	 * @return RecipeResouce
	 * @throws NoDataException NoDataException
	 */
	public RecipeResouce getEntity(final String resouceCd)
			throws NoDataException {
		if (StringUtils.isEmpty(resouceCd)) {
			throw new IllegalArgumentException("resouceCd is empty");
		}

		RecipeResouce bean = recipeResouceDao.getEntity(resouceCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 設備検索（詳細用）
	 * @param resouceCd 設備コード
	 * @return RecipeResouceDetail
	 */
	public RecipeResouceDetail getDetailEntity(final String resouceCd) {
		RecipeResouceDetail bean = recipeResouceDetailDao.getEntity(resouceCd);
		return bean;
	}

	/**
	 * 設備グループ検索
	 * @param resouceGroupCd 設備グループコード
	 * @return RecipeResouceGroupDetail
	 */
	public RecipeResouceGroupDetail getRecipeResouceGroupEntity(
			final String resouceGroupCd) {
		RecipeResouceGroupDetail bean = recipeResouceGroupDetailDao
				.getEntity(resouceGroupCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final RecipeResouce bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			recipeResouceDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final RecipeResouce bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			recipeResouceDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final RecipeResouce bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			recipeResouceDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/**
	 * 生産ラインリスト取得
	 * @return List<LineListForComboboxes>
	 */
	public List<LineListForComboboxes> getLineList() {
		List<LineListForComboboxes> list = lineListForComboboxesDao
				.getListForComboboxes();
		return list;
	}

	/**
	 * 前工程設備一覧検索
	 * @param resouceCd 設備コード
	 * @return List<RecipePegResouceDetailList>
	 */
	public List<RecipePegResouceDetailList> getList(final String resouceCd) {
		List<RecipePegResouceDetailList> bean = recipePegResouceDetailListDao
				.getList(resouceCd);
		return bean;
	}

	/**
	 * 登録処理
	 * @param frm 登録データ
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	public void regist(final RecipeResouceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipePegResouce bean = new RecipePegResouce();

		if (frm.getUpdateDate() == null) {
			/* 追加処理を実行 */
			insert(insertRecipeResouce(frm, tantoCd));
		} else {
			/* 更新処理を実行 */
			update(updateRecipeResouce(frm, tantoCd));
		}

		try {
			/* 一括削除処理 */
			deleteRecipePegResouceList(frm.getResouceCd());

			for (int i = 0; i < frm.getSearchRecipePegResouceDetailList()
					.size(); i++) {
				/* 値を更新用Beanにコピる */
				IgnoreCaseBeanUtils.copyProperties(bean, frm
						.getSearchRecipePegResouceDetailList().get(i));

				/* コピーしきれなかった分は手で */
				bean.setResouceCd(frm.getResouceCd());
				bean.setSeq(new BigDecimal(i + 1));
				bean.setInputDate(bean.getCurrentTimestamp());
				bean.setInputorCd(tantoCd);
				bean.setUpdatorCd(tantoCd);

				/* 追加処理 */
				recipePegResouceDao.insert(bean);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 更新処理用のRecipeResouceを作成する.
	 * @param frm 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouce
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouce updateRecipeResouce(
			final RecipeResouceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouce newBean = new RecipeResouce();

		try {
			newBean = getEntity(frm.getResouceCd());
		} catch (NoDataException e) {
			return null;
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setCostMachine(AecNumberUtils.convertBigDecimal(frm
				.getStrCostMachine()));
		newBean.setCost(AecNumberUtils.convertBigDecimal(frm.getStrCost()));
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のRecipeResouceを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return RecipeResouce
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private RecipeResouce insertRecipeResouce(
			final RecipeResouceDetailForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		RecipeResouce newBean = new RecipeResouce();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setCostMachine(AecNumberUtils.convertBigDecimal(frm
				.getStrCostMachine()));
		newBean.setCost(AecNumberUtils.convertBigDecimal(frm.getStrCost()));
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 前工程設備一括削除
	 * @param resouceCd 設備コード
	 * @return 削除件数
	 */
	public int deleteRecipePegResouceList(final String resouceCd) {
		return recipePegResouceDeleteDao.deleteList(resouceCd);
	}

	/**
	 * 削除処理
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	public void deleteList(final RecipeResouce bean) throws NoDataException {
		try {
			/* 削除処理を実行 */
			delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}

		/* 前工程設備一括削除 */
		deleteRecipePegResouceList(bean.getResouceCd());
	}

	/* -------------------- setter -------------------- */

	/**
	 * recipeResouceDaoを設定します。
	 * @param recipeResouceDao recipeResouceDao
	 */
	public void setRecipeResouceDao(final RecipeResouceDao recipeResouceDao) {
		this.recipeResouceDao = recipeResouceDao;
	}

	/**
	 * recipeResouceDetailDaoを設定します。
	 * @param recipeResouceDetailDao recipeResouceDetailDao
	 */
	public void setRecipeResouceDetailDao(
			final RecipeResouceDetailDao recipeResouceDetailDao) {
		this.recipeResouceDetailDao = recipeResouceDetailDao;
	}

	/**
	 * recipeResouceGroupDetailDaoを設定します。
	 * @param recipeResouceGroupDetailDao recipeResouceGroupDetailDao
	 */
	public void setRecipeResouceGroupDetailDao(
			final RecipeResouceGroupDetailDao recipeResouceGroupDetailDao) {
		this.recipeResouceGroupDetailDao = recipeResouceGroupDetailDao;
	}

	/**
	 * lineListForComboboxesDaoを設定します。
	 * @param lineListForComboboxesDao lineListForComboboxesDao
	 */
	public void setLineListForComboboxesDao(
			final LineListForComboboxesDao lineListForComboboxesDao) {
		this.lineListForComboboxesDao = lineListForComboboxesDao;
	}

	/**
	 * recipePegResouceDetailListDaoを設定します。
	 * @param recipePegResouceDetailListDao recipePegResouceDetailListDao
	 */
	public void setRecipePegResouceDetailListDao(
			final RecipePegResouceDetailListDao recipePegResouceDetailListDao) {
		this.recipePegResouceDetailListDao = recipePegResouceDetailListDao;
	}

	/**
	 * recipePegResouceDaoを設定します。
	 * @param recipePegResouceDao recipePegResouceDao
	 */
	public void setRecipePegResouceDao(
			final RecipePegResouceDao recipePegResouceDao) {
		this.recipePegResouceDao = recipePegResouceDao;
	}

	/**
	 * recipePegResouceDeleteDaoを設定します。
	 * @param recipePegResouceDeleteDao recipePegResouceDeleteDao
	 */
	public void setRecipePegResouceDeleteDao(
			final RecipePegResouceDeleteDao recipePegResouceDeleteDao) {
		this.recipePegResouceDeleteDao = recipePegResouceDeleteDao;
	}
}
