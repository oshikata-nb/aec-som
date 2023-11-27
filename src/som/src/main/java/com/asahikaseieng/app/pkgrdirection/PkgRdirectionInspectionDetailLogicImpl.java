/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspection;
import com.asahikaseieng.dao.entity.directioninspection.DirectionInspectionDao;
import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 包装実績検索 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionInspectionDetailLogicImpl implements
		PkgRdirectionInspectionDetailLogic {

	/** 包装実績－製造指図検査Dao */
	private PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao;

	/** NamesDao */
	private NamesDao namesDao;

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/** 製造指図検査Dao */
	private DirectionInspectionDao directionInspectionDao;

	/**
	 * コンストラクタ.包装実績検索
	 */
	public PkgRdirectionInspectionDetailLogicImpl() {
	}

	/**
	 * 検査詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－検査詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setInspectionDetailForm(final HttpServletRequest request,
			final PkgRdirectionInspectionDetailForm frm) throws NoDataException {
		PkgRdirectionDirectionHeaderDetail header = null;
		PkgRdirectionDirectionInspectionList bean = null;

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());
		BigDecimal lineNo = new BigDecimal(frm.getLineNo());

		// 製造指図ヘッダーを検索
		header = pkgRdirectionCommonsLogic.getEntity(
			frm.getDirectionDivision(), directionNo);
		if (header == null) {
			throw new NoDataException();
		}
		setHeaderInfo(frm, header);

		// 製造指図検査検索
		bean = pkgRdirectionDirectionInspectionListDao.getInspecrionDetail(
			directionDivision, directionNo, stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}
		setDetailInfo(request, frm, bean);
	}

	/**
	 * 製造指図ヘッダーの情報をFormに設定
	 * @param form 画面のForm
	 * @param bean 共通ヘッダー情報
	 */
	private void setHeaderInfo(final AbstractPkgRdirectionForm frm,
			final PkgRdirectionDirectionHeaderDetail bean) {
		frm.setRecipeCd(bean.getRecipeCd()); // レシピコード
		frm.setRecipeVersion(bean.getRecipeVersion()); // レシピバージョン
		frm.setRecipeName(bean.getRecipeName()); // 処方名称
		frm.setItemCd(bean.getItemCd()); // 品目コード
		frm.setItemName(bean.getItemName()); // 分目名称
		frm.setRecipeUse(SelectRecipeUse.getName(frm.getRecipeUse())); // 用途名称
		frm.setHeaderUpdateDate(bean.getUpdateDate());
		frm.setDirectionStatus(getBigDecimalString(bean.getDirectionStatus()));
	}

	/**
	 * 製造指図検査の情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装実績－検査詳細画面 Form
	 * @param bean 共通ヘッダー情報
	 * @return AbstractPkgRdirectionForm
	 */
	private void setDetailInfo(final HttpServletRequest request,
			final PkgRdirectionInspectionDetailForm form,
			final PkgRdirectionDirectionInspectionList inspectionBean) {
		form.setOperationCd(inspectionBean.getOperationCd()); // 工程コード
		form.setOperationName(inspectionBean.getOperationName()); // 工程名称
		form.setInspectionCd(inspectionBean.getInspectionCd()); // 検査コード
		form.setInspectionName(inspectionBean.getInspectionName()); // 検査名称
		form.setStrInspectionCondition(inspectionBean.getCondition()); // 条件
		form.setStrInspectionDivision(inspectionBean.getDivision()); // 区分
		form.setValue1(inspectionBean.getValue1()); // 値1
		form.setValue2(inspectionBean.getValue2()); // 値2
		form.setResultValue1(inspectionBean.getResultValue1()); // 値1
		form.setValueType(inspectionBean.getValueType()); // 入力種類|1:数値,2:文字列
		form.setRemark(inspectionBean.getRemark()); // 備考
		form.setNotes(inspectionBean.getNotes()); // 注釈
		form.setInspectionUpdateDate(inspectionBean.getUpdateDate()); // 製造指図検査更新時刻
		// ラジオボタンにデフォルト値セット
		if (StringUtils.isEmpty(inspectionBean.getValueType())) {
			form.setValueType("1");
		}
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 各種名称マスタにデータがない場合はエラーとする。
	 * @param frm 包装実績検査Bean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(
			final PkgRdirectionInspectionDetailForm frm) {
		ActionMessages errMsgs = new ActionMessages();

		if (frm == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}
		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(frm.getInspectionCd(), "STDV");

		if (nameBean == null) {
			// データが存在しない場合
			errMsgs = addError(errMsgs, "errors.pkgrdirection.no.names");
		}
		return errMsgs;
	}

	/**
	 * 包装実績検査-更新処理を行う.
	 * @param frm 包装実績検査-詳細画面Form
	 * @param tantoCd 更新者コード
	 */
	public void update(final PkgRdirectionInspectionDetailForm frm,
			final String tantoCd) {
		try {
			String directionStatue = frm.getDirectionStatus();
			BigDecimal directionDivision = new BigDecimal(frm
					.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());
			BigDecimal lineNo = new BigDecimal(frm.getLineNo());

			// 製造指図ヘッダーを包装実績入力済に更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(directionStatue,
				directionDivision, frm.getDirectionNo(), frm
						.getHeaderUpdateDate(), tantoCd);

			// 製造指図検査を検索
			DirectionInspection updBean = directionInspectionDao.getEntity(
				directionDivision, directionNo, lineNo, stepNo);
			if (updBean == null) {
				throw new OptimisticLockRuntimeException();
			}
			// 画面の入力内容を反映
			setDirectionInspection(frm, updBean);

			// 更新前の更新日付を設定（排他用）
			updBean.setUpdateDate(frm.getInspectionUpdateDate());

			// 更新処理
			int updNum = directionInspectionDao.update(updBean);
			if (updNum != 1) {
				throw new OptimisticLockRuntimeException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 画面の内容をDirectionInspectionに設定
	 * @param frm 包装実績－検査詳細画面 Form
	 * @param bean 製造検査Bean
	 */
	private void setDirectionInspection(
			final PkgRdirectionInspectionDetailForm frm,
			final DirectionInspection bean) {

		bean.setInspectionCd(frm.getInspectionCd()); // 検査コード
		bean.setCondition(frm.getStrInspectionCondition()); // 条件
		bean.setDivision(frm.getStrInspectionDivision()); // 区分
		bean.setValue1(frm.getValue1()); // 値1
		bean.setValue2(frm.getValue2()); // 値2
		bean.setResultValue1(frm.getResultValue1()); // 値1
		bean.setValueType(frm.getValueType()); // 入力種類|1:数値,2:文字列
		bean.setRemark(frm.getRemark()); // 備考
		bean.setNotes(frm.getNotes()); // 注釈
	}

	/**
	 * パラメータチェック
	 * @param frm 包装実績－検査詳細画面 Form
	 */
	private void checkParams(final PkgRdirectionInspectionDetailForm frm) {

		if (frm.getDirectionDivision() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : directionDivision is empty.パラメータチェック.getEntity");
		}
		if (frm.getDirectionNo() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : directionNo is empty.パラメータチェック.getEntity");
		}
		if (frm.getStepNo() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : stepNo is empty.パラメータチェック.getEntity");
		}
	}

	/**
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	public static ActionMessages addError(final ActionMessages errors,
			final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/**
	 * BigDecimalの文字列表現を取得する BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	protected String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績－製造指図検査Daoを設定します。
	 * @param pkgRdirectionDirectionInspectionListDao 包装実績－製造指図検査Dao
	 */
	public void setPkgRdirectionDirectionInspectionListDao(
			final PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao) {
		this.pkgRdirectionDirectionInspectionListDao = pkgRdirectionDirectionInspectionListDao;
	}

	/**
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}

	/**
	 * 製造指図検査Dao設定
	 * @param directionInspectionDao 製造指図検査Dao
	 */
	public void setDirectionInspectionDao(
			final DirectionInspectionDao directionInspectionDao) {
		this.directionInspectionDao = directionInspectionDao;
	}

}
