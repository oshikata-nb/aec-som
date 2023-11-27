/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原処方－配合タブ ロジック実装クラス
 * @author tosco
 */
public class GrecipeFormulaListLogicImpl implements GrecipeFormulaListLogic {

	/** 処方フォーミュラDao */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private GrecipeItemQueueDetailDao grecipeItemQueueDetailDao;

	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderList;

	/**
	 * コンストラクタ.原処方検索
	 */
	public GrecipeFormulaListLogicImpl() {
	}

	/**
	 * 配合タブ－初期表示用.
	 * 
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return GrecipeRecipeFormulaList データ
	 */
	public GrecipeRecipeFormulaList getSumQty(final BigDecimal recipeId,
			final BigDecimal procStepNo) {

		checkParams(recipeId);
		checkParams(procStepNo);

		GrecipeRecipeFormulaList record = grecipeRecipeFormulaListDao
				.getSumQty(recipeId, procStepNo);

		return record;
	}

	/**
	 * 配合タブ－一覧検索処理
	 * 
	 * @param recipeId レシピインデックス
	 * @param procStepNo 工程順序
	 * @return List<GrecipeRecipeFormulaList> データ
	 * @throws NoDataException 例外
	 */
	public List<GrecipeRecipeFormulaList> getSearchList(
			final BigDecimal recipeId, final BigDecimal procStepNo)
			throws NoDataException {

		checkParams(recipeId);
		checkParams(procStepNo);

		List<GrecipeRecipeFormulaList> list = grecipeRecipeFormulaListDao
				.getSearchList(recipeId, procStepNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 行削除、行追加時のチェックを行う
	 * @param form 原処方 配合一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddDelList(final GrecipeFormulaListForm form) {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getSeqCombo().size() == 1) {
			errors = GrecipeUtil
					.addError(errors, "errors.grecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getSearchFormList() == null) {
				errors = GrecipeUtil.addError(errors,
					"errors.grecipe.no.search");
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
			final List<GrecipeRecipeFormulaList> searchFormList) {
		ActionMessages errors = new ActionMessages();

		if (searchFormList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 品目マスタ存在チェック
		for (int i = 0; i < searchFormList.size(); i++) {
			GrecipeRecipeFormulaList bean = searchFormList.get(i);

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
	 * @param frm 配合タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final GrecipeFormulaListForm frm, final String tantoCd)
			throws Exception {

		try {

			// 削除対象配合を物理削除する
			if (frm.getDelFormList() != null) {
				for (GrecipeRecipeFormulaList bean : frm.getDelFormList()) {
					// 削除
					if (StringUtils.isNotEmpty(bean.getStrSeq())) {
						// 実データのみ削除（追加したデータにはサブステップがnull)
						int delNum = grecipeRecipeFormulaListDao.delete(bean);
						if (delNum == 0) {
							// 削除対象データ無し->エラー
							throw new NoDataException();
						}
					}
				}
			}

			Map<String, List<GrecipeRecipeFormulaList>> stepNoBeanMap = Collections
					.synchronizedMap(new HashMap<String, List<GrecipeRecipeFormulaList>>());

			// STEP_NOとSTEP_NOごとのリストのマップを作成する
			for (GrecipeRecipeFormulaList bean : frm.getSearchFormList()) {
				List<GrecipeRecipeFormulaList> tempList = stepNoBeanMap
						.get(bean.getStrStepNo());
				if (tempList == null) {
					// リストを作成
					tempList = new ArrayList<GrecipeRecipeFormulaList>();
					// STEP_NOごとにマッピングする
					stepNoBeanMap.put(bean.getStrStepNo(), tempList);
				}
				// STEP_NOごとにマッピングする
				tempList.add(bean);
			}

			// STEP_NOとSTEP_NOごとに更新処理を行う
			for (Map.Entry<String, List<GrecipeRecipeFormulaList>> stepNoBeanList : stepNoBeanMap
					.entrySet()) {

				// キー（STEP_NO） を取得
				String stepNo = stepNoBeanList.getKey();
				// 値（List<GrecipeRecipeFormulaList>）を取得（同一工程順序に属する配当の配列)
				List<GrecipeRecipeFormulaList> beanList = stepNoBeanList
						.getValue();

				checkParams(new BigDecimal(frm.getRecipeId()));
				checkParams(new BigDecimal(stepNo));

				// 最終LINE_NO を取得
				GrecipeRecipeFormulaList lineBean = grecipeRecipeFormulaListDao
						.getLastLineNo(new BigDecimal(frm.getRecipeId()),
							new BigDecimal(stepNo));
				BigDecimal lastLineNo = lineBean.getLastLineNo();

				// 最終SEQ を取得
				GrecipeRecipeFormulaList seqBean = grecipeRecipeFormulaListDao
						.getLastSeq(new BigDecimal(frm.getRecipeId()),
							new BigDecimal(stepNo));
				BigDecimal lastSeqNo = seqBean.getLastSeq();

				for (int i = 0; i < stepNoBeanList.getValue().size(); i++) {
					GrecipeRecipeFormulaList bean = stepNoBeanList.getValue()
							.get(i);
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
					if (StringUtils.isEmpty(bean.getStrSeq())) {
						// 追加行
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
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 更新・登録処理
	 * @param tantoCd 担当者コード
	 * @param beanList 更新対象配合配列
	 */
	private void insertBean(final String tantoCd,
			final List<GrecipeRecipeFormulaList> beanList) {

		// 更新処理
		for (GrecipeRecipeFormulaList bean : beanList) {
			bean.setUpdatorCd(tantoCd); // 更新者コード

			if (StringUtils.isEmpty(bean.getStrSeq())) {
				// 追加行
				bean.setStepNo(new BigDecimal(bean.getStrStepNo()));
				bean.setLineType(new BigDecimal(-1));
				bean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時
				bean.setInputorCd(tantoCd); // 登録者コード
				bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時

				// 登録処理
				int insertNum = grecipeRecipeFormulaListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			} else {
				// 既存データ
				// 更新処理
				int updateNum = grecipeRecipeFormulaListDao.update(bean);
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
	private void updateHeader(final GrecipeFormulaListForm frm)
			throws NoDataException {
		// レシピIDに属する配合リスト全件取得（前処理で更新したデータは配合の一部かもしれないので、全件取得しなおす)
		List<GrecipeRecipeFormulaList> searchList = grecipeRecipeFormulaListDao
				.getSearchList(new BigDecimal(frm.getRecipeId()),
					BigDecimal.ZERO);

		// 表示時ヘッダ情報取得
		GrecipeRecipeHeaderList headBean = frm.getHeaderBean();
		if (headBean == null) {
			throw new IllegalArgumentException("bean == null");
		}
		// 収率
		BigDecimal processLoss = null;
		if (SelectRecipeUse.RECIPE_USE_PRODUCTION.equals(frm.getRecipeUse())) {
			// 製造時
			// ヘッダ更新用の収率を再計算
			processLoss = GrecipeUtil.calcProcessLoss(searchList, headBean
					.getStdQty(), 4, RoundingMode.HALF_UP);
		} else {
			// 包装時
			processLoss = GrecipeConst.PACKAGE_PROCESS_LOSS;
		}
		// 収率を再計算
		headBean.setProcessLoss(processLoss);

		// 処方ヘッダーに更新
		int updateNum = grecipeRecipeHeaderList.update(headBean);
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
	 * @param grecipeRecipeFormulaListDao 処方フォーミュラDao
	 */
	public void setGrecipeRecipeFormulaListDao(
			final GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao) {
		this.grecipeRecipeFormulaListDao = grecipeRecipeFormulaListDao;
	}

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param grecipeRecipeHeaderList 処方ヘッダー操作DAO
	 */
	public void setGrecipeRecipeHeaderList(
			final GrecipeRecipeHeaderListDao grecipeRecipeHeaderList) {
		this.grecipeRecipeHeaderList = grecipeRecipeHeaderList;
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
