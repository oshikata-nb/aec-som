/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 包装指図－工程詳細画面 ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionProcedureDetailLogicImpl implements PkgDirectionProcedureDetailLogic {

	/** 包装指図－製造指図プロシージャDao */
	private PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao;

	/** 製造指図プロシージャDao */
	private DirectionProcedureDao directionProcedureDao;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionProcedureDetailLogicImpl() {
	}

	/**
	 * 工程詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装指図－工程詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setProcedureDetailForm(final HttpServletRequest request, final PkgDirectionProcedureDetailForm frm)
		throws NoDataException {
		PkgDirectionDirectionHeaderDetail header = null;
		PkgDirectionDirectionProcedureList bean = null;

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());

		// 製造指図ヘッダーを検索
		header = pkgDirectionCommonsLogic.getEntity(frm.getDirectionDivision(), directionNo);
		if (header == null) {
			throw new NoDataException();
		}
		setHeaderInfo(frm, header);

		// 製造指図プロシージャ検索
		bean = pkgDirectionDirectionProcedureListDao.getEntity(directionDivision, directionNo, stepNo);
		if (bean == null) {
			throw new NoDataException();
		}
		setDetailInfo(request, frm, bean);
	}

	/**
	 * 製造指図ヘッダーの情報をFormに設定
	 * @param form 包装指図-共通抽象 Form
	 * @param bean 包装指図－製造指図ヘッダー情報データ
	 */
	private void setHeaderInfo(final AbstractPkgDirectionForm frm, final PkgDirectionDirectionHeaderDetail bean) {
		frm.setDirectionDivision(bean.getDirectionDivision().toString());
		frm.setDirectionNo(bean.getDirectionNo());
		frm.setItemCd(bean.getItemCd());
		frm.setItemName(bean.getItemName());
		frm.setHeaderUpdateDate(bean.getUpdateDate());
		frm.setJissekiFlag(bean.getJissekiFlag());
	}

	/**
	 * 製造指図プロシージャの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装指図－工程詳細画面 Form
	 * @param bean 包装指図－製造指図プロシージャデータ
	 */
	private void setDetailInfo(final HttpServletRequest request, final PkgDirectionProcedureDetailForm frm,
		final PkgDirectionDirectionProcedureList bean) {

		// 工程順序
		frm.setStepNo(bean.getStrStepNo());
		// Seq
		frm.setSeq(bean.getSeq().toString());
		// 工程コード
		frm.setOperationCd(bean.getOperationCd());
		// 工程名称
		frm.setOperationName(bean.getOperationName());
		// 開始時刻
		frm.setStrStartDate(bean.getStrStartDate());
		// 終了時刻
		frm.setStrEndDate(bean.getStrEndDate());
		// 製造指図プロシージャ更新日時
		frm.setProcedureUpdateDate(bean.getUpdateDate());

		CheckDigitUtilsLogic checker = CheckDigitUtil.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail
			= checker.getCheckDigit(PkgDirectionConst.UNIT_DIV_RECIPE5, null, null);
		// 小数点桁数
		if (checkDetail.getSmallnumLength() != null) {
			frm.setDecimalPointRecipe5(checkDetail.getSmallnumLength().toString());
		}
		// 端数区分
		if (checkDetail.getRoundDivision() != null) {
			frm.setRoundDivisionRecipe5(checkDetail.getRoundDivision().toString());
		}

		// 正味質量
		frm.setStrNet(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getNet()));
		// 量目管理幅最小
		frm.setStrWeightMin(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getWeightMin()));
		// 量目管理幅最大
		frm.setStrWeightMax(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getWeightMax()));
		// 濾過用フィルター
		frm.setStrFilter(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getFilter()));
		// 濾過用メッシュ
		frm.setStrMesh(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getMesh()));
		// オートチェッカー最小
		frm.setStrAutoCheckerMin(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerMin()));
	 	// オートチェッカー最大
		frm.setStrAutoCheckerMax(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerMax()));
	 	// グロスチェッカー最小
		frm.setStrGrossCheckerMin(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerMin()));
	 	// グロスチェッカー最大
		frm.setStrGrossCheckerMax(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerMax()));
	 	// オートチェッカー中心
		frm.setStrAutoCheckerAve(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerAve()));
	 	// グロスチェッカー中心
		frm.setStrGrossCheckerAve(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerAve()));
		// 開きトルク最小
		frm.setStrOpeningTorqueMin(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getOpeningTorqueMin()));
		// 開きトルク最大
		frm.setStrOpeningTorqueMax(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getOpeningTorqueMax()));
		// 閉めトルク最小
		frm.setStrClosingTorqueMin(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getClosingTorqueMin()));
		// 閉めトルク最大
		frm.setStrClosingTorqueMax(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getClosingTorqueMax()));
		// トルク圧
		frm.setStrTorquePressure(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getTorquePressure()));
		// エアー圧
		frm.setStrAirPressure(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getAirPressure()));
		// 巻締め時間
		frm.setStrVcloseTime(checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getVcloseTime()));
		// ホットエアー設定温度
		frm.setStrHotAirPresetTemp(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getHotAirPresetTemp()));
		// ホットエアー吹き出し圧力
		frm.setStrHotAirPressure(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getHotAirPressure()));
		// 第一ヒートシール設定温度
		frm.setStrFirstHeatSeal(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getFirstHeatSeal()));
		// 第二ヒートシール設定温度
		frm.setStrSecondHeatSeal(
			checker.format(PkgDirectionConst.UNIT_DIV_RECIPE5, bean.getSecondHeatSeal()));
		// 条件
		frm.setCondition(bean.getCondition());
		// 備考
		frm.setRemark(bean.getRemark());
		// 注釈
		frm.setNotes(bean.getNotes());
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * @param frm 包装指図－工程詳細画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final PkgDirectionProcedureDetailForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}
		return errMsgs;
	}

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param frm 包装指図－工程詳細画面 Form
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException 包装指図－ロジック処理例外
	 */
	public void update(final PkgDirectionProcedureDetailForm frm, final String tantoCd)
		throws PkgDirectionLogicException {
		try {

			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());

			// 製造指図ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, frm.getDirectionNo(),
				frm.getHeaderUpdateDate(), tantoCd);

			// 製造指図プロシージャを検索
			DirectionProcedure updBean = directionProcedureDao.getEntity
				(directionDivision, directionNo, stepNo);
			if (updBean == null) {
				throw new OptimisticLockRuntimeException();
			}

			// 画面の入力内容を反映
			setDirectionProcedure(frm, updBean);

			// 更新前の更新日付を設定（排他用）
			updBean.setUpdateDate(frm.getProcedureUpdateDate());

			// 更新者を設定
			updBean.setUpdatorCd(tantoCd);

			// 更新処理
			int updNum = directionProcedureDao.update(updBean);
			if (updNum != 1) {
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 画面の内容をDirectionProcedureに設定
	 * @param frm 包装指図－工程詳細画面 Form
	 * @param bean 製造指図プロシージャBean
	 */
	private void setDirectionProcedure(final PkgDirectionProcedureDetailForm frm, final DirectionProcedure bean) {

		// 正味質量
		bean.setNet(AecNumberUtils.convertBigDecimal(frm.getStrNet()));
		// 量目管理幅最小
		bean.setWeightMin(AecNumberUtils.convertBigDecimal(frm.getStrWeightMin()));
		// 量目管理幅最大
		bean.setWeightMax(AecNumberUtils.convertBigDecimal(frm.getStrWeightMax()));
		// 濾過用フィルター
		bean.setFilter(AecNumberUtils.convertBigDecimal(frm.getStrFilter()));
		// 濾過用メッシュ
		bean.setMesh(AecNumberUtils.convertBigDecimal(frm.getStrMesh()));
		// オートチェッカー最小
		bean.setAutoCheckerMin(
			AecNumberUtils.convertBigDecimal(frm.getStrAutoCheckerMin()));
		// オートチェッカー最大
		bean.setAutoCheckerMax(
			AecNumberUtils.convertBigDecimal(frm.getStrAutoCheckerMax()));
		// グロスチェッカー最小
		bean.setGrossCheckerMin(
			AecNumberUtils.convertBigDecimal(frm.getStrGrossCheckerMin()));
		// グロスチェッカー最大
		bean.setGrossCheckerMax(
			AecNumberUtils.convertBigDecimal(frm.getStrGrossCheckerMax()));
		// オートチェッカー中心
		bean.setAutoCheckerAve(
			AecNumberUtils.convertBigDecimal(frm.getStrAutoCheckerAve()));
		// グロスチェッカー中心
		bean.setGrossCheckerAve(
			AecNumberUtils.convertBigDecimal(frm.getStrGrossCheckerAve()));
		// 開きトルク最小
		bean.setOpeningTorqueMin(
			AecNumberUtils.convertBigDecimal(frm.getStrOpeningTorqueMin()));
		// 開きトルク最大
		bean.setOpeningTorqueMax(
			AecNumberUtils.convertBigDecimal(frm.getStrOpeningTorqueMax()));
		// 閉めトルク最小
		bean.setClosingTorqueMin(
			AecNumberUtils.convertBigDecimal(frm.getStrClosingTorqueMin()));
		// 閉めトルク最大
		bean.setClosingTorqueMax(
			AecNumberUtils.convertBigDecimal(frm.getStrClosingTorqueMax()));
		// トルク圧
		bean.setTorquePressure(AecNumberUtils.convertBigDecimal(frm.getStrTorquePressure()));
		// エアー圧
		bean.setAirPressure(AecNumberUtils.convertBigDecimal(frm.getStrAirPressure()));
		// 巻締め時間
		bean.setVcloseTime(AecNumberUtils.convertBigDecimal(frm.getStrVcloseTime()));
		// ホットエアー設定温度
		bean.setHotAirPresetTemp(
			AecNumberUtils.convertBigDecimal(frm.getStrHotAirPresetTemp()));
		// ホットエアー吹き出し圧力
		bean.setHotAirPressure(
			AecNumberUtils.convertBigDecimal(frm.getStrHotAirPressure()));
		// 第一ヒートシール設定温度
		bean.setFirstHeatSeal(
			AecNumberUtils.convertBigDecimal(frm.getStrFirstHeatSeal()));
		// 第二ヒートシール設定温度
		bean.setSecondHeatSeal(
			AecNumberUtils.convertBigDecimal(frm.getStrSecondHeatSeal()));
		// 条件
		bean.setCondition(frm.getCondition());
		// 備考
		bean.setRemark(frm.getRemark());
		// 注釈
		bean.setNotes(frm.getNotes());
	}

	/**
	 * パラメータチェック
	 * @param frm 包装指図－工程詳細画面 Form
	 */
	private void checkParams(final PkgDirectionProcedureDetailForm frm) {

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

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－製造指図プロシージャDao設定
	 * @param pkgDirectionDirectionProcedureListDao 包装指図－製造指図プロシージャDao
	 */
	public void setPkgDirectionDirectionProcedureListDao
		(final PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao) {
		this.pkgDirectionDirectionProcedureListDao = pkgDirectionDirectionProcedureListDao;
	}

	/**
	 * 製造指図プロシージャDao設定
	 * @param directionProcedureDao 製造指図プロシージャDao
	 */
	public void setDirectionProcedureDao(final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * 包装指図共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 包装指図共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}
}
