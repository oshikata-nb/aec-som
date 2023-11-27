/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;


/**
 * 製造実績　工程タブ ロジック実装クラス
 * @author tosco
 */
public class RdirectionProcedureListLogicImpl implements RdirectionProcedureListLogic {

	/** 製造指図プロシージャDao */
	private RdirectionDirectionProcedureListDao rdirectionProcedureDao;
	/** 製造指図フォーミュラDao */
	private RdirectionDirectionFormulaListDao rdirectionFormulaDao;
	/** 製造指図検査Dao */
	private RdirectionDirectionInspectionListDao rdirectionInspectionDao;
	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ.基本製造実績検索
	 */
	public RdirectionProcedureListLogicImpl() {
	}

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @return List<RdirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RdirectionDirectionProcedureList> getSearchList(final String directionNo) throws NoDataException {
		checkParams(directionNo);

		List<RdirectionDirectionProcedureList> list = rdirectionProcedureDao.getSearchList(directionNo);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造指図フォーミュラ、製造指図検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final List<RdirectionDirectionProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			RdirectionDirectionProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directionNo = bean.getDirectionNo();
				BigDecimal stepNo = bean.getStepNo();

				//TODO メッセージキーの変更
				// 工程に紐づく製造指図フォーミュラを検索
				List<RdirectionDirectionFormulaList> formulaList
					= rdirectionFormulaDao.getList(directionNo, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errors = RdirectionUtil.addError(errors
										, "errors.rdirection.proc.existform.row"
										, i + 1);
				}

				// 工程に紐づく製造指図検査を検索
				List<RdirectionDirectionInspectionList> inspectionList
					= rdirectionInspectionDao.getList(directionDivision, directionNo, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errors = RdirectionUtil.addError(errors
										, "errors.rdirection.proc.existins.row"
										, i + 1);
				}

				//工程に紐づく製造指図フォーミュラ（仕上げ）を検索
				List<RdirectionDirectionFormulaList> finishList
					= rdirectionFormulaDao.getFinishList(directionNo, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上げが存在する場合
					errors = RdirectionUtil.addError(errors
										, "errors.rdirection.proc.existfini.row"
										, i + 1);
				}

			}

		}

		return errors;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final List<RdirectionDirectionProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			RdirectionDirectionProcedureList bean = searchProcList.get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = RdirectionUtil.addError(errors
									, "errors.rdirection.no.operation.row"
									, i + 1);
			}

		}
		return errors;
	}

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final RdirectionProcedureListForm frm, final String tantoCd) throws Exception {
		int insertNum;

		try {

			// 最終STEP_NO を取得
			RdirectionDirectionProcedureList stepBean
				= rdirectionProcedureDao.getLastStepNo(frm.getDirectionNo());
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (RdirectionDirectionProcedureList bean : frm.getSearchProcList()) {
				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号で一括削除
			rdirectionProcedureDao.deleteByDirectionNo(frm.getDirectionNo());

			// 登録処理
			for (RdirectionDirectionProcedureList bean : frm.getSearchProcList()) {
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}

				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = rdirectionProcedureDao.insert(bean);
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
	private void checkParams(final String recipeId) {

		if (recipeId == null || recipeId.equals("")) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図プロシージャDao を設定します。
	 * @param rdirectionProcedureDao 製造指図プロシージャDao 
	 */
	public void setRdirectionProcedureDao(
			final RdirectionDirectionProcedureListDao rdirectionProcedureDao) {
		this.rdirectionProcedureDao = rdirectionProcedureDao;
	}

	/**
	 * 製造指図フォーミュラDaoを設定します。
	 * @param rdirectionFormulaDao ** 製造指図フォーミュラDao
	 */
	public void setRdirectionFormulaDao(
			final RdirectionDirectionFormulaListDao rdirectionFormulaDao) {
		this.rdirectionFormulaDao = rdirectionFormulaDao;
	}

	/**
	 * 製造指図検査Daoを設定します。
	 * @param rdirectionInspectionDao 製造指図検査Dao
	 */
	public void setRdirectionInspectionDao(
			final RdirectionDirectionInspectionListDao rdirectionInspectionDao) {
		this.rdirectionInspectionDao = rdirectionInspectionDao;
	}

	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
