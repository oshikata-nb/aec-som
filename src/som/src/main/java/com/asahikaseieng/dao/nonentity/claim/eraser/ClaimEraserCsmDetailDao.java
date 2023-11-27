/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * 消込入力詳細画面用Daoインターフェース.(カスタマイズ)
 * @author tosco
 */
public interface ClaimEraserCsmDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getDetailCreditData(). */
	String getDetailCreditData_ARGS = "organizationCd, sectionName, venderCd, tantoCd"
			+ ", eraserCompleteDateFrom, eraserCompleteDateTo, kbn, approvalStatus, claimNo";

	/**
	 * 消込入力詳細データ検索処理.
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserDetail> 消込入力詳細データ
	 */
	List<ClaimEraserDetail> getDetailCreditData(String organizationCd,
			String organizationName, String venderCd, String tantoCd,
			Date eraserCompleteDateFrom, Date eraserCompleteDateTo, String kbn,
			BigDecimal approvalStatus, String claimNo);

	/** ARGSアノテーション getCreditData(). */
	String getCreditData_ARGS = "organizationCd, venderCd, eraserAmount";

	/**
	 * 消込途中または消込済の入金データ取得処理.
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param eraserAmount 前回消込金額(差分)
	 * @return List<ClaimEraserDetail> 入金データ
	 */
	List<ClaimEraserDetail> getCreditData(String organizationCd,
			String venderCd, BigDecimal eraserAmount);

	/** ARGSアノテーション getOffsetGroupData(). */
	String getOffsetGroupData_ARGS = "organizationCd, venderDivision, venderCd, eraserAmount";

	/**
	 * 消込途中または消込済の相殺データ取得処理.
	 * @param organizationCd 部署コード
	 * @param venderDivision 取引先区分
	 * @param venderCd 請求先コード
	 * @param eraserAmount 前回消込金額(差分)
	 * @return List<ClaimEraserDetail> 相殺データ
	 */
	List<ClaimEraserDetail> getOffsetGroupData(String organizationCd,
			String venderDivision, String venderCd, BigDecimal eraserAmount);

	/**
	 * 消込入力詳細 グループ間相殺トランザクション更新処理 （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateOffsetGroup(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 支払トランザクション更新処理 （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updatePayment(ClaimEraserDetail bean);

	/**
	 * 消込入力詳細 入金トランザクション更新処理(相殺データ) （消込完了フラグ、消込完了日）
	 * 
	 * @param bean 消込入力用Bean
	 * @return 更新件数
	 */
	int updateCreditOffset(ClaimEraserDetail bean);

}
