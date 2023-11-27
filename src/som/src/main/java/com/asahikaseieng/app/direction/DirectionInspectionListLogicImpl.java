/*
 * Created on 2009/01/14
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
 * 製造指図 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class DirectionInspectionListLogicImpl implements DirectionInspectionListLogic {

	/** 製造指図検査Dao */
	private DirectionDirectionInspectionListDao directionDirectionInspectionListDao;

	/** 製造指図プロシージャDao */
	private DirectionDirectionProcedureListDao directionDirectionProcedureListDao;

	/** 製造指図検索 ロジッククラス */
	private DirectionCommonsLogic directionCommonsLogic;

	/**
	 * コンストラクタ.製造指図検査タブ
	 */
	public DirectionInspectionListLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を
	 * 製造指図工程テーブルより取得する
	 * @param form 製造指図 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final DirectionInspectionListForm form) {
		String[] res = {null, null};
		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			DirectionDirectionProcedureList bean = null;
			bean = directionDirectionProcedureListDao.getOperationName(decDirectionNo, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 製造指図検査情報の検索を行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return List<DirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<DirectionDirectionInspectionList> getSearchList(
		final DirectionInspectionListForm form)throws NoDataException {
		List<DirectionDirectionInspectionList> list = null;
		String decDirectionNo = null;
		BigDecimal decStepNo = null;

		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			decDirectionNo = form.getDirectionNo();
			decStepNo = new BigDecimal(form.getSelectKeyStepNo());
		}
		list = directionDirectionInspectionListDao.getSearchList(decDirectionNo, decStepNo);

		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final DirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = DirectionUtil.addError(errors, "errors.direction.no.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = DirectionUtil.addError(errors, "errors.direction.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 行削除時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final DirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = DirectionUtil.addError(errors, "errors.direction.no.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = DirectionUtil.addError(errors, "errors.direction.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 製造指図検査情報の登録時のチェックを行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final DirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = DirectionUtil.addError(errors, "errors.mgrecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errors = DirectionUtil.addError(errors, "errors.mgrecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 製造指図検査情報の登録処理を行う
	 * @param form 製造指図 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @param header 製造指図ヘッダ
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final DirectionInspectionListForm form,
			final DirectionDirectionHeaderList header, final String tantoCd) throws Exception {

		try {
			//製造指図ヘッダ更新
			directionCommonsLogic.update(header);

			// 最終LINE_NOを取得する
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			DirectionDirectionInspectionList lineBean
				= directionDirectionInspectionListDao.getLastLineNo(decDirectionNo, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (DirectionDirectionInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号、STEP_NOで、製造指図検査テーブルより一括削除
			directionDirectionInspectionListDao.deleteByDirectionNoStepNo(decDirectionNo, decStepNo);

			for (DirectionDirectionInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 製造指図検査テーブルに登録
				int insertNum = directionDirectionInspectionListDao.insert(bean);
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

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図検査Daoを設定する
	 * @param directionDirectionInspectionListDao 製造指図検査Dao
	 */
	public void setDirectionDirectionInspectionListDao(
			final DirectionDirectionInspectionListDao directionDirectionInspectionListDao) {
		this.directionDirectionInspectionListDao = directionDirectionInspectionListDao;
	}

	/**
	 * 製造指図プロシージャDaoを設定する
	 * @param directionDirectionProcedureListDao 製造指図プロシージャDao
	 */
	public void setDirectionDirectionProcedureListDao(
			final DirectionDirectionProcedureListDao directionDirectionProcedureListDao) {
		this.directionDirectionProcedureListDao = directionDirectionProcedureListDao;

	}

	/**
	 * 製造指図-共通ロジッククラスを設定します。
	 * @param directionCommonsLogic 製造指図-共通ロジッククラス
	 */
	public void setDirectionCommonsLogic(final DirectionCommonsLogic directionCommonsLogic) {
		this.directionCommonsLogic = directionCommonsLogic;
	}
}
