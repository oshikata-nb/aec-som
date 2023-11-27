/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionHeaderDetail;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 包装実績－工程詳細画面 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionProcedureDetailLogicImpl implements
		PkgRdirectionProcedureDetailLogic {

	/** 包装実績－製造指図プロシージャDao */
	private PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao;

	/** 製造指図プロシージャDao */
	private DirectionProcedureDao directionProcedureDao;

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/**
	 * コンストラクタ
	 */
	public PkgRdirectionProcedureDetailLogicImpl() {
	}

	/**
	 * 工程詳細画面にデータを設定する
	 * @param request リクエスト
	 * @param frm 包装実績－工程詳細画面 Form
	 * @throws NoDataException データが存在しない例外
	 */
	public void setProcedureDetailForm(final HttpServletRequest request,
			final PkgRdirectionProcedureDetailForm frm) throws NoDataException {
		PkgRdirectionDirectionHeaderDetail header = null;
		PkgRdirectionDirectionProcedureList bean = null;

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm
				.getDirectionDivision());
		String directionNo = frm.getDirectionNo();
		BigDecimal stepNo = new BigDecimal(frm.getStepNo());

		// 製造指図ヘッダーを検索
		header = pkgRdirectionCommonsLogic.getEntity(
			frm.getDirectionDivision(), directionNo);
		if (header == null) {
			throw new NoDataException();
		}
		setHeaderInfo(frm, header);

		// 製造指図プロシージャ検索
		bean = pkgRdirectionDirectionProcedureListDao.getEntity(
			directionDivision, directionNo, stepNo);
		if (bean == null) {
			throw new NoDataException();
		}
		setDetailInfo(request, frm, bean);
	}

	/**
	 * 製造指図ヘッダーの情報をFormに設定
	 * @param form 包装実績-共通抽象 Form
	 * @param bean 包装実績－製造指図ヘッダー情報データ
	 */
	private void setHeaderInfo(final AbstractPkgRdirectionForm frm,
			final PkgRdirectionDirectionHeaderDetail bean) {
		frm.setDirectionDivision(bean.getDirectionDivision().toString());
		frm.setDirectionNo(bean.getDirectionNo());
		frm.setItemCd(bean.getItemCd());
		frm.setItemName(bean.getItemName());
		frm.setHeaderUpdateDate(bean.getUpdateDate());
		frm.setDirectionStatus(getBigDecimalString(bean.getDirectionStatus()));
	}

	/**
	 * 製造指図プロシージャの情報をFormに設定
	 * @param request リクエスト
	 * @param frm 包装実績－工程詳細画面 Form
	 * @param bean 包装実績－製造指図プロシージャデータ
	 */
	private void setDetailInfo(final HttpServletRequest request,
			final PkgRdirectionProcedureDetailForm frm,
			final PkgRdirectionDirectionProcedureList bean) {

		// 工程順序
		frm.setStepNo(bean.getStrStepNo());
		// Seq
		frm.setSeq(bean.getSeq().toString());
		// 工程コード
		frm.setOperationCd(bean.getOperationCd());
		// 工程名称
		frm.setOperationName(bean.getOperationName());
		// 工程開始実績日
		frm.setStrResultSDay(bean.getStrResultSDay());
		// 工程開始実績時
		frm.setStrResultSTime(bean.getStrResultSTime());
		// 工程終了実績日
		frm.setStrResultEDay(bean.getStrResultEDay());
		// 工程終了実績時
		frm.setStrResultETime(bean.getStrResultETime());
		// 製造指図プロシージャ更新日時
		frm.setProcedureUpdateDate(bean.getUpdateDate());

		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);
		NumberChkDisitDetail checkDetail = checker.getCheckDigit(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, null, null);
		// 小数点桁数
		if (checkDetail.getSmallnumLength() != null) {
			frm.setDecimalPointRecipe5(checkDetail.getSmallnumLength()
					.toString());
		}
		// 端数区分
		if (checkDetail.getRoundDivision() != null) {
			frm.setRoundDivisionRecipe5(checkDetail.getRoundDivision()
					.toString());
		}

		// 正味質量
		frm.setStrNet(checker.format(PkgRdirectionConst.UNIT_DIV_RECIPE5, bean
				.getNet()));
		// 量目管理幅最小
		frm.setStrWeightMin(checker.format(PkgRdirectionConst.UNIT_DIV_RECIPE5,
			bean.getWeightMin()));
		// 量目管理幅最大
		frm.setStrWeightMax(checker.format(PkgRdirectionConst.UNIT_DIV_RECIPE5,
			bean.getWeightMax()));
		// 濾過用フィルター
		frm.setStrFilter(checker.format(PkgRdirectionConst.UNIT_DIV_RECIPE5,
			bean.getFilter()));
		// 濾過用メッシュ
		frm.setStrMesh(checker.format(PkgRdirectionConst.UNIT_DIV_RECIPE5, bean
				.getMesh()));
		// オートチェッカー最小
		frm.setStrAutoCheckerMin(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerMin()));
		// オートチェッカー最大
		frm.setStrAutoCheckerMax(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerMax()));
		// グロスチェッカー最小
		frm.setStrGrossCheckerMin(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerMin()));
		// グロスチェッカー最大
		frm.setStrGrossCheckerMax(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerMax()));
		// オートチェッカー中心
		frm.setStrAutoCheckerAve(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getAutoCheckerAve()));
		// グロスチェッカー中心
		frm.setStrGrossCheckerAve(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getGrossCheckerAve()));
		// 開きトルク最小
		frm.setStrOpeningTorqueMin(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getOpeningTorqueMin()));
		// 開きトルク最大
		frm.setStrOpeningTorqueMax(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getOpeningTorqueMax()));
		// 閉めトルク最小
		frm.setStrClosingTorqueMin(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getClosingTorqueMin()));
		// 閉めトルク最大
		frm.setStrClosingTorqueMax(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getClosingTorqueMax()));
		// トルク圧
		frm.setStrTorquePressure(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getTorquePressure()));
		// エアー圧
		frm.setStrAirPressure(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getAirPressure()));
		// 巻締め時間
		frm.setStrVcloseTime(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getVcloseTime()));
		// ホットエアー設定温度
		frm.setStrHotAirPresetTemp(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getHotAirPresetTemp()));
		// ホットエアー吹き出し圧力
		frm.setStrHotAirPressure(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getHotAirPressure()));
		// 第一ヒートシール設定温度
		frm.setStrFirstHeatSeal(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getFirstHeatSeal()));
		// 第二ヒートシール設定温度
		frm.setStrSecondHeatSeal(checker.format(
			PkgRdirectionConst.UNIT_DIV_RECIPE5, bean.getSecondHeatSeal()));
		// 条件
		frm.setCondition(bean.getCondition());
		// 備考
		frm.setRemark(bean.getRemark());
		// 注釈
		frm.setNotes(bean.getNotes());
	}

	/**
	 * 製造指図プロシージャ更新処理を行う.
	 * @param frm 包装実績－工程詳細画面 Form
	 * @param tantoCd 更新者
	 */
	public void update(final PkgRdirectionProcedureDetailForm frm,
			final String tantoCd) {
		try {

			BigDecimal directionDivision = new BigDecimal(frm
					.getDirectionDivision());
			String directionNo = frm.getDirectionNo();
			BigDecimal stepNo = new BigDecimal(frm.getStepNo());
			String directionStatus = frm.getDirectionStatus();

			// 製造指図ヘッダーを更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(directionStatus,
				directionDivision, frm.getDirectionNo(), frm
						.getHeaderUpdateDate(), tantoCd);

			// 製造指図プロシージャを検索
			DirectionProcedure updBean = directionProcedureDao.getEntity(
				directionDivision, directionNo, stepNo);
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
	 * @param frm 包装実績－工程詳細画面 Form
	 * @param bean 製造指図プロシージャBean
	 */
	private void setDirectionProcedure(
			final PkgRdirectionProcedureDetailForm frm,
			final DirectionProcedure bean) {

		// 日付と時間を結合し、Timestamp型に変換しセット
		// 工程開始実績日時
		String strDay = frm.getStrResultSDay();
		String strTime = frm.getStrResultSTime();
		bean.setResultSdate(getTimestampYmdHmsFormat(strDay, strTime, ""));
		// 工程終了実績日時
		strDay = frm.getStrResultEDay();
		strTime = frm.getStrResultETime();
		bean.setResultEdate(getTimestampYmdHmsFormat(strDay, strTime, ""));

		// 正味質量
		bean.setNet(AecNumberUtils.convertBigDecimal(frm.getStrNet()));
		// 量目管理幅最小
		bean.setWeightMin(AecNumberUtils.convertBigDecimal(frm
				.getStrWeightMin()));
		// 量目管理幅最大
		bean.setWeightMax(AecNumberUtils.convertBigDecimal(frm
				.getStrWeightMax()));
		// 濾過用フィルター
		bean.setFilter(AecNumberUtils.convertBigDecimal(frm.getStrFilter()));
		// 濾過用メッシュ
		bean.setMesh(AecNumberUtils.convertBigDecimal(frm.getStrMesh()));
		// オートチェッカー最小
		bean.setAutoCheckerMin(AecNumberUtils.convertBigDecimal(frm
				.getStrAutoCheckerMin()));
		// オートチェッカー最大
		bean.setAutoCheckerMax(AecNumberUtils.convertBigDecimal(frm
				.getStrAutoCheckerMax()));
		// グロスチェッカー最小
		bean.setGrossCheckerMin(AecNumberUtils.convertBigDecimal(frm
				.getStrGrossCheckerMin()));
		// グロスチェッカー最大
		bean.setGrossCheckerMax(AecNumberUtils.convertBigDecimal(frm
				.getStrGrossCheckerMax()));
		// オートチェッカー中心
		bean.setAutoCheckerAve(AecNumberUtils.convertBigDecimal(frm
				.getStrAutoCheckerAve()));
		// グロスチェッカー中心
		bean.setGrossCheckerAve(AecNumberUtils.convertBigDecimal(frm
				.getStrGrossCheckerAve()));
		// 開きトルク最小
		bean.setOpeningTorqueMin(AecNumberUtils.convertBigDecimal(frm
				.getStrOpeningTorqueMin()));
		// 開きトルク最大
		bean.setOpeningTorqueMax(AecNumberUtils.convertBigDecimal(frm
				.getStrOpeningTorqueMax()));
		// 閉めトルク最小
		bean.setClosingTorqueMin(AecNumberUtils.convertBigDecimal(frm
				.getStrClosingTorqueMin()));
		// 閉めトルク最大
		bean.setClosingTorqueMax(AecNumberUtils.convertBigDecimal(frm
				.getStrClosingTorqueMax()));
		// トルク圧
		bean.setTorquePressure(AecNumberUtils.convertBigDecimal(frm
				.getStrTorquePressure()));
		// エアー圧
		bean.setAirPressure(AecNumberUtils.convertBigDecimal(frm
				.getStrAirPressure()));
		// 巻締め時間
		bean.setVcloseTime(AecNumberUtils.convertBigDecimal(frm
				.getStrVcloseTime()));
		// ホットエアー設定温度
		bean.setHotAirPresetTemp(AecNumberUtils.convertBigDecimal(frm
				.getStrHotAirPresetTemp()));
		// ホットエアー吹き出し圧力
		bean.setHotAirPressure(AecNumberUtils.convertBigDecimal(frm
				.getStrHotAirPressure()));
		// 第一ヒートシール設定温度
		bean.setFirstHeatSeal(AecNumberUtils.convertBigDecimal(frm
				.getStrFirstHeatSeal()));
		// 第二ヒートシール設定温度
		bean.setSecondHeatSeal(AecNumberUtils.convertBigDecimal(frm
				.getStrSecondHeatSeal()));
		// 条件
		bean.setCondition(frm.getCondition());
		// 備考
		bean.setRemark(frm.getRemark());
		// 注釈
		bean.setNotes(frm.getNotes());
	}

	/**
	 * 日付文字列、時間文字列を結合してTimestamp型で返却する
	 * @param strDay 日付(yyyy/MM/dd)
	 * @param strTime 時分(HH:mm)
	 * @param strDefTime デフォルト時間(HH:mm:ss)
	 * @return Timestamp 文字列結合後のTimestamp
	 */
	private Timestamp getTimestampYmdHmsFormat(final String strDay,
			final String strTime, final String strDefTime) {
		Timestamp timestamp = null;
		String strFormat = "yyyy/MM/dd HH:mm:ss";
		if (StringUtils.isNotEmpty(strDay)) {
			String strDate = strDay;
			if (StringUtils.isNotEmpty(strTime)) {
				String[] strHms = strDefTime.split(":", 3);
				strDate = strDate + " " + strTime;
				if (strHms != null && strHms.length == 3) {
					strDate = strDate + ":" + strHms[2];
				} else {
					strFormat = "yyyy/MM/dd HH:mm";
				}
			} else {
				strDate = strDate + " " + strDefTime;
			}
			timestamp = AecDateUtils
					.getTimestampYmdHmFormat(strDate, strFormat);
		}
		return timestamp;
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

	/**
	 * パラメータチェック
	 * @param frm 包装実績－工程詳細画面 Form
	 */
	private void checkParams(final PkgRdirectionProcedureDetailForm frm) {

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
	 * 包装実績－製造指図プロシージャDao設定
	 * @param pkgRdirectionDirectionProcedureListDao 包装実績－製造指図プロシージャDao
	 */
	public void setPkgRdirectionDirectionProcedureListDao(
			final PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao) {
		this.pkgRdirectionDirectionProcedureListDao = pkgRdirectionDirectionProcedureListDao;
	}

	/**
	 * 製造指図プロシージャDao設定
	 * @param directionProcedureDao 製造指図プロシージャDao
	 */
	public void setDirectionProcedureDao(
			final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}
}
