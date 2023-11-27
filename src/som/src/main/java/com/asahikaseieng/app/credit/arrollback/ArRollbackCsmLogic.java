/*
 * Created on 2008/10/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売掛ロールバック処理 ロジッククラス interface. (カスタマイズ)
 * @author tosco
 */
public interface ArRollbackCsmLogic {

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ArRollback 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ArRollback getSearch(final String organizationCd) throws NoDataException;

	/**
	 * 売掛ヘッダー(ロールバック対象)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchRbList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 売掛ヘッダー(未来締めデータ)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	List<ArRollback> getSearchEraserList(final String organizationCd,
			final Date cleditDate);

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	//List<ArRollback> getSearchOffsetList(final String organizationCd,final Date cleditDate);
	List<ArRollback> getSearchOffsetList(final String organizationCd,final Date offsetFromDate,final Date offsetToDate);
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛ロールバックForm
	 * @return ProArRollbackCallDto 売掛ロールバック処理用Dto
	 */
	ProArRollbackCallDto setProcedureDto(final ArRollbackCsmForm frm);

	/**
	 * 売掛ロールバック
	 * @param proDto 売掛ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProArRollbackCallDto proDto);

	/**
	 * 部署マスタ取得
	 * @param organizationCd 部署コード
	 * @return Organization 部署情報
	 */
	Organization getOrganization(final String organizationCd);
}
