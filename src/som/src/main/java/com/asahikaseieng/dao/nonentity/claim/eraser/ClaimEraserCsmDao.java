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
 * 消込入力詳細画面(下段)用Daoインターフェース.(カスタマイズ)
 * @author tosco
 */
public interface ClaimEraserCsmDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsm.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEraserCsmData(). */
	String getEraserCsmData_ARGS = "organizationCd, organizationName, venderCd, tantoCd"
			+ ", eraserCompleteDateFrom, eraserCompleteDateTo, kbn, approvalStatus, claimNo";

	/**
	 * 消込入力詳細(下段) 消込トラン(カスタマイズ)データ検索処理.
	 * @param organizationCd 部署コード
	 * @param organizationName 部署名称
	 * @param venderCd 請求先コード
	 * @param tantoCd 担当者コード
	 * @param eraserCompleteDateFrom 消込完了日付FROM
	 * @param eraserCompleteDateTo 消込完了日付TO
	 * @param kbn 区分
	 * @param approvalStatus 承認ステータス
	 * @param claimNo 請求番号
	 * @return List<EraserCsm> 消込入力詳細(下段) 消込トラン(カスタマイズ)データ
	 */
	List<ClaimEraserCsm> getEraserCsmData(String organizationCd,
			String organizationName, String venderCd, String tantoCd,
			Date eraserCompleteDateFrom, Date eraserCompleteDateTo, String kbn,
			BigDecimal approvalStatus, final String claimNo);

	/**
	 * 消込入力詳細 消込トラン(Csm)更新処理
	 * 
	 * @param bean 消込トラン(Csm)用Bean
	 * @return 更新件数
	 */
	int update(ClaimEraserCsm bean);

}
