/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.aprollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProApRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 買掛ロールバック処理 ロジッククラス interface. 
 * @author tosco
 */
public interface ApRollbackLogic {

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の年月を取得	 * 
	 * @param organizationCd   部署コード	 * @return ApRollback 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ApRollback getSearch(final String organizationCd)	throws NoDataException;

	/**
	 * 買掛ヘッダー(ロールバック対象)検索処理	 * 
	 * @param organizationCd  部署コード	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 買掛ヘッダーデータ
	 */
	List<ApRollback> getSearchRbList(final String organizationCd, final Date payableDate);

	/**
	 * 買掛ヘッダー(未来締めデータ)検索処理	 * 
	 * @param organizationCd  部署コード	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 買掛ヘッダーデータ
	 */
	List<ApRollback> getSearchList(final String organizationCd, final Date payableDate);

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @param payableDate 買掛締め日
	 * @return List<ApRollback> 検索データ
	 */
	List<ApRollback> getSearchEraserList(final String organizationCd, final Date payableDate);

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @return List<ApRollback> 検索データ
	 */
	List<ApRollback> getSearchOffsetList(final String organizationCd,final Date offsetFromDate,final Date offsetToDate);

	/**
	 * 買掛PROCEDURE DTOセット
	 * @param frm 買掛ロールバックForm
	 * @return ProArRollbackCallDto 買掛ロールバック処理用Dto
	 */
	ProApRollbackCallDto setProcedureDto(final ApRollbackForm frm);

	/**
	 * 買掛ロールバック
	 * @param proDto 買掛ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProApRollbackCallDto proDto);

}
