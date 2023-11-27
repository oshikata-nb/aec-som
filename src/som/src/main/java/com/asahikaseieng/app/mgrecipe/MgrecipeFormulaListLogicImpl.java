/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 基本処方－配合タブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeFormulaListLogicImpl implements MgrecipeFormulaListLogic {

	/** 処方フォーミュラDao */
	private RecipeFormulaListDao recipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao;

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeFormulaListLogicImpl() {
	}

	/**
	 * 配合タブ－初期表示用.
	 * 
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return RecipeFormulaList データ
	 */
	public RecipeFormulaList getSumQty(final BigDecimal recipeId,
			final BigDecimal procStepNo) {

		checkParams(recipeId);
		checkParams(procStepNo);

		RecipeFormulaList record = recipeFormulaListDao.getSumQty(recipeId,
			procStepNo);

		return record;
	}

	/**
	 * 配合タブ－一覧検索処理
	 * 
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return List<RecipeFormulaList> データ
	 * @throws NoDataException 例外
	 */
	public List<RecipeFormulaList> getSearchList(final BigDecimal recipeId,
			final BigDecimal procStepNo) throws NoDataException {

		checkParams(recipeId);
		checkParams(procStepNo);

		List<RecipeFormulaList> list = recipeFormulaListDao.getSearchList(
			recipeId, procStepNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 基本処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddDelList(final MgrecipeFormulaListForm form) {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getSeqCombo().size() == 1) {
			errors = MgrecipeUtil.addError(errors,
				"errors.mgrecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getSearchFormList() == null) {
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param searchFormList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(
			final List<RecipeFormulaList> searchFormList) {
		ActionMessages errors = new ActionMessages();

		if (searchFormList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchFormList.size(); i++) {
			RecipeFormulaList bean = searchFormList.get(i);

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
	 * @param frm 配合タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final MgrecipeFormulaListForm frm, final String tantoCd)
			throws Exception {

		try {

			// 削除処理
			if (frm.getDelFormList() != null) {
				for (RecipeFormulaList bean : frm.getDelFormList()) {
					// 削除
					if (bean.getStrSeq() != null
							&& !bean.getStrSeq().equals("")) {
						int delNum = recipeFormulaListDao.delete(bean);
						if (delNum == 0) {
							// 対象データ無し
							throw new NoDataException();
						}
					}
				}
			}

			Map<String, List<RecipeFormulaList>> stepNoBeanMap = Collections
					.synchronizedMap(new HashMap<String, List<RecipeFormulaList>>());

			// STEP_NOとSTEP_NOごとのリストのマップを作成する
			for (RecipeFormulaList bean : frm.getSearchFormList()) {

				List<RecipeFormulaList> tempList = stepNoBeanMap.get(bean
						.getStrStepNo());

				if (tempList == null) {

					// リストを作成
					List<RecipeFormulaList> stepNoBeanList = new ArrayList<RecipeFormulaList>();
					stepNoBeanList.add(bean);
					// STEP_NOごとにマッピングする
					stepNoBeanMap.put(bean.getStrStepNo(), stepNoBeanList);

				} else {

					// STEP_NOごとにマッピングする
					tempList.add(bean);
					stepNoBeanMap.put(bean.getStrStepNo(), tempList);
				}

			}

			// STEP_NOとSTEP_NOごとに更新処理を行う
			for (Map.Entry<String, List<RecipeFormulaList>> stepNoBeanList : stepNoBeanMap
					.entrySet()) {

				// キー（STEP_NO） を取得
				String stepNo = stepNoBeanList.getKey();
				// 値（List<RecipeFormulaList>）を取得
				List<RecipeFormulaList> beanList = stepNoBeanList.getValue();

				checkParams(new BigDecimal(frm.getRecipeId()));
				checkParams(new BigDecimal(stepNo));

				// 最終LINE_NO を取得
				RecipeFormulaList lineBean = recipeFormulaListDao
						.getLastLineNo(new BigDecimal(frm.getRecipeId()),
							new BigDecimal(stepNo));
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				RecipeFormulaList seqBean = recipeFormulaListDao.getLastSeq(
					new BigDecimal(frm.getRecipeId()), new BigDecimal(stepNo));
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < stepNoBeanList.getValue().size(); i++) {

					RecipeFormulaList bean = stepNoBeanList.getValue().get(i);

					// ｻﾌﾞｽﾃｯﾌﾟ（表示順）の振り直し
					if ((!frm.getTempProcStepNo().equals("0"))
							&& (!frm.getTempProcStepNo().equals(
								bean.getStrStepNo()))) {
						bean.setSeq(lastSeqNo);
						lastSeqNo = lastSeqNo.add(BigDecimal.ONE);
					} else {
						bean.setSeq(new BigDecimal(i + 1));
					}

					// STEP_NO, LINE_NO, LINE_TYPE 更新
					if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {
						bean.setLineNo(lastLineNo);
						lastLineNo = lastLineNo.add(BigDecimal.ONE);
					}
				}

				// 更新・登録処理
				insertBean(tantoCd, beanList);
			}

			// 処方ヘッダを更新
			updateHeader(frm);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 更新・登録処理
	 * @param tantoCd
	 * @param beanList
	 */
	private void insertBean(final String tantoCd,
			final List<RecipeFormulaList> beanList) {

		// 更新処理
		for (RecipeFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			if (bean.getStrSeq() == null || bean.getStrSeq().equals("")) {

				// 登録情報
				bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
				bean.setLineType(new BigDecimal(-1));
				bean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時

				// 登録処理
				int insertNum = recipeFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			} else {

				// 更新処理
				int updateNum = recipeFormulaListDao.update(bean);
				if (updateNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}
		}
	}

	/**
	 * 処方ヘッダを更新
	 * @param frm 配合タグ
	 * @throws NoDataException
	 */
	private void updateHeader(final MgrecipeFormulaListForm frm)
			throws NoDataException {
		// 配合リスト取得
		List<RecipeFormulaList> searchList = recipeFormulaListDao
				.getSearchList(new BigDecimal(frm.getRecipeId()),
					BigDecimal.ZERO);

		// 表示時ヘッダ情報取得
		RecipeHeaderList headBean = frm.getHeaderBean();
		// ヘッダ更新用の収率を再計算
		BigDecimal processLoss = MgrecipeUtil.calcProcessLoss(searchList,
			headBean.getStdQty(), 4, RoundingMode.HALF_UP);
		// 収率を再計算
		headBean.setProcessLoss(processLoss);

		if (headBean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		// 処方ヘッダーに更新
		int updateNum = recipeHeaderListDao.update(headBean);
		if (updateNum != 1) {
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
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
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
	 * 処方ヘッダー操作DAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(
			final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
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
