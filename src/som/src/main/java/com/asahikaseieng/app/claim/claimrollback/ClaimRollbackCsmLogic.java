/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.claimrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.nonentity.claim.claimrollback.ClaimRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClRollbackCallDto;

/**
 * 請求更新ロールバック処理 ロジッククラス interface. (カスタマイズ)
 * @author tosco
 */
public interface ClaimRollbackCsmLogic {

	/**
	 * 請求ヘッダー(ロールバック対象)検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 請求ヘッダーデータ
	 */
	List<ClaimRollback> getSearchRbList(final String organizationCd, final String venderCd, final Date cleditDate);

	/**
	 * 請求ヘッダー(未来締めデータ)検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 請求ヘッダーデータ
	 */
	List<ClaimRollback> getSearchList(final String organizationCd, final String venderCd, final Date cleditDate);

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<ClaimRollback> 検索データ
	 */
	List<ClaimRollback> getSearchEraserList(final String organizationCd, final String venderCd, final Date cleditDate);

	/**
	 * 請求PROCEDURE DTOセット
	 * @param frm 請求ロールバックForm
	 * @return ProArRollbackCallDto 請求ロールバック処理用Dto
	 */
	ProClRollbackCallDto setProcedureDto(final ClaimRollbackCsmForm frm);

	/**
	 * 請求ロールバック
	 * @param proDto 請求ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProClRollbackCallDto proDto);

	/**
	 * 部署マスタ取得
	 * @param organizationCd 部署コード
	 * @return Organization 部署情報
	 */
	Organization getOrganization(final String organizationCd);
}
