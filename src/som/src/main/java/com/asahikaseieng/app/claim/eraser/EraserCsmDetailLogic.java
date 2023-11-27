/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.asahikaseieng.dao.entity.erasercsm.EraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力詳細 ロジッククラス interface.(カスタマイズ)
 * @author tosco
 */
public interface EraserCsmDetailLogic {

	/**
	 * 消込・入金データ検索処理を行う.消込入力詳細(上・中段)
	 * 
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserDetail> 消込入力詳細(上・中段)データ
	 * @throws NoDataException NoDataException
	 */
	List<ClaimEraserDetail> getDetailData(final String organizationCd,
			final String organizationName, final String venderCd,
			final String tantoCd, final Date eraserCompleteDateFrom,
			final Date eraserCompleteDateTo, final String kbn,
			final BigDecimal approvalStatus, final String claimNo)
			throws NoDataException;

	/**
	 * 消込トラン(カスタマイズ)明細データ検索処理を行う.消込入力詳細(下段)
	 * 
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserCsm> 消込入力詳細(下段) 請求明細データ
	 * @throws NoDataException NoDataException
	 */
	List<ClaimEraserCsm> getEraserCsmData(final String organizationCd,
			final String organizationName, final String venderCd,
			final String tantoCd, final Date eraserCompleteDateFrom,
			final Date eraserCompleteDateTo, final String kbn,
			final BigDecimal approvalStatus, final String claimNo)
			throws NoDataException;

	/**
	 * 更新処理を行う.
	 * @param eraserBean 消込トラン(Csm)データ
	 * @param detailList 消込トラン(Csm)・入金データリスト(上段・中段)
	 * @param eraserCsmList 消込トラン(Csm)データリスト(下段)
	 * @throws NoDataException データ無し例外
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws SQLException 入金データ無し例外
	 */
	void update(final ClaimEraserCsm eraserBean,
			final List<ClaimEraserDetail> detailList,
			final List<ClaimEraserCsm> eraserCsmList) throws NoDataException,
			IllegalAccessException, InvocationTargetException, SQLException;

	/**
	 * 検索処理を行う.
	 * @param dataType dataType
	 * @param slipNo slipNo
	 * @throws NoDataException NoDataException
	 * @return EraserCsm
	 */
	EraserCsm getEntity(final BigDecimal dataType, final String slipNo)
			throws NoDataException;

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final EraserCsmDetailForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;
}
