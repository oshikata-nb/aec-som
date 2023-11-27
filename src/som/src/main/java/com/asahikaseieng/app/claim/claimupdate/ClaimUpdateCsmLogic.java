/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClUpdateCallDto;

/**
 * 請求更新処理 ロジッククラス interface. (カスタマイズ)
 * @author tosco
 */
public interface ClaimUpdateCsmLogic {

	/**
	 * 請求ヘッダー検索処理
	 * 
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<Update> 請求ヘッダーデータ
	 */
	List<ClaimUpdate> getSearchList(final String organizationCd,
			final String venderCd, final Date cleditDate);

	/**
	 * 請求PROCEDURE DTOセット
	 * @param frm 請求更新Form
	 * @return ProClUpdateCallDto 請求更新処理用Dto
	 */
	ProClUpdateCallDto setProcedureDto(final ClaimUpdateCsmForm frm);

	/**
	 * 請求更新
	 * @param proDto 請求更新処理用Dto
	 * @return ProClUpdateCallDto 処理結果（0:成功 99:エラー）
	 * 
	 */
	ProClUpdateCallDto callProcedure(final ProClUpdateCallDto proDto);

	/**
	 * 部署マスタ取得
	 * @param organizationCd 部署コード
	 * @return Organization 部署情報
	 */
	Organization getOrganization(final String organizationCd);

	/**
	 * エラーログ出力処理
	 * @param errorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final BigDecimal errorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;

	/**
	 * パラメータ情報取得
	 * @param procCd プロシージャ名
	 * @return ProcParam 検索結果データ
	 */
	ProcParam getProcParam(final String procCd);

	/**
	 * ログ情報取得
	 * @param procCd プロシージャ名
	 * @param inputorCd 登録者名
	 * @return List<ProcedureLog> 検索結果データ
	 */
	List<ProcedureLog> getProcLog(final String procCd, final String inputorCd);

	/**
	 * パラメータ情報登録
	 * @param procCd プロシージャ名
	 * @param frm 登録データ
	 */
	void insertProcParam(final String procCd, final ClaimUpdateCsmForm frm);
}
