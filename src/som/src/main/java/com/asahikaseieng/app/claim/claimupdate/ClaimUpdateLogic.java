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

import com.asahikaseieng.dao.nonentity.claim.claimupdate.ClaimUpdate;
import com.asahikaseieng.dao.nonentity.procedurecall.ProClUpdateCallDto;

/**
 * 請求更新処理 ロジッククラス interface. 
 * @author tosco
 */
public interface ClaimUpdateLogic {

	/**
	 * 請求ヘッダー検索処理
	 * 
	 * @param sectionCd  部門コード
	 * @param venderCd   請求先コード
	 * @param cleditDate 請求締め日
	 * @return List<Update> 請求ヘッダーデータ
	 */
	List<ClaimUpdate> getSearchList(final String sectionCd, final String venderCd, final Date cleditDate);

	/**
	 * 請求PROCEDURE DTOセット
	 * @param frm 請求更新Form
	 * @return ProClUpdateCallDto 請求更新処理用Dto
	 */
	ProClUpdateCallDto setProcedureDto(final ClaimUpdateForm frm);

	/**
	 * 請求更新
	 * @param proDto 請求更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProClUpdateCallDto proDto);

}
