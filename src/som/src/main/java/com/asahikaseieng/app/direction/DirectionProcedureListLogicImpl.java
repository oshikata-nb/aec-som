/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.mgrecipe.MgrecipeUtil;
import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.direction.DirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 基本製造指図検索 ロジック実装クラス
 * @author tosco
 */
public class DirectionProcedureListLogicImpl implements DirectionProcedureListLogic {

	/** 製造指図プロシージャDao */
	private DirectionDirectionProcedureListDao directionProcedureDao;
	/** 製造指図フォーミュラDao */
	private DirectionDirectionFormulaListDao directionFormulaDao;
	/** 製造指図検査Dao */
	private DirectionDirectionInspectionListDao directionInspectionDao;
	/** 工程マスタDao */
	private OperationDao operationDao;
	/** 製造指図-共通ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.基本製造指図検索
	 */
	public DirectionProcedureListLogicImpl() {
	}

	/**
	 * 製造指図プロシージャ検索処理を行う.
	 * @param directionNo 指図番号
	 * @return List<DirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<DirectionDirectionProcedureList> getSearchList(final String directionNo) throws NoDataException {
		checkParams(directionNo);

		List<DirectionDirectionProcedureList> list = directionProcedureDao.getSearchList(directionNo);
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
	public ActionMessages checkForDelList(final List<DirectionDirectionProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			DirectionDirectionProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directionNo = bean.getDirectionNo();
				BigDecimal stepNo = bean.getStepNo();

				// 工程に紐づく製造指図フォーミュラを検索
				List<DirectionDirectionFormulaList> formulaList
					= directionFormulaDao.getList(directionNo, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.direction.proc.existform.row"
										, i + 1);
				}

				// 工程に紐づく製造指図検査を検索
				List<DirectionDirectionInspectionList> inspectionList
					= directionInspectionDao.getList(directionDivision, directionNo, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.direction.proc.existins.row"
										, i + 1);
				}

				// 工程に紐づく製造指図フォーミュラ（仕上げ）を検索
				List<DirectionDirectionFormulaList> finishList
					= directionFormulaDao.getFinishList(directionNo, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上げが存在する場合
					errors = MgrecipeUtil.addError(errors
										, "errors.direction.proc.existfini.row"
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
	public ActionMessages checkForRegist(final List<DirectionDirectionProcedureList> searchProcList) {
		ActionMessages errors = new ActionMessages();

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			DirectionDirectionProcedureList bean = searchProcList.get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errors = MgrecipeUtil.addError(errors
									, "errors.mgrecipe.no.operation.row"
									, i + 1);
			}

		}
		return errors;
	}

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 工程タブForm
	 * @param header 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final DirectionProcedureListForm frm,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception {
		int insertNum;

		try {
			//処方ヘッダ更新
			directionCommonsLogic.update(header);

			// 最終STEP_NO を取得
			DirectionDirectionProcedureList stepBean
				= directionProcedureDao.getLastStepNo(frm.getDirectionNo());
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (DirectionDirectionProcedureList bean : frm.getSearchProcList()) {
				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号で一括削除
			directionProcedureDao.deleteByDirectionNo(frm.getDirectionNo());

			// 登録処理
			for (DirectionDirectionProcedureList bean : frm.getSearchProcList()) {
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setStartDate(frm.getPlanedSdate()); // 開始日時
				bean.setEndDate(frm.getPlanedEdate()); // 終了日時
				bean.setUpdatorCd(tantoCd); // 更新者コード
				insertNum = directionProcedureDao.insert(bean);
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
	 * @param directionProcedureDao 製造指図プロシージャDao 
	 */
	public void setDirectionProcedureDao(
			final DirectionDirectionProcedureListDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * 製造指図フォーミュラDaoを設定します。
	 * @param directionFormulaDao ** 製造指図フォーミュラDao
	 */
	public void setDirectionFormulaDao(
			final DirectionDirectionFormulaListDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * 製造指図検査Daoを設定します。
	 * @param directionInspectionDao 製造指図検査Dao
	 */
	public void setDirectionInspectionDao(
			final DirectionDirectionInspectionListDao directionInspectionDao) {
		this.directionInspectionDao = directionInspectionDao;
	}

	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}

}
