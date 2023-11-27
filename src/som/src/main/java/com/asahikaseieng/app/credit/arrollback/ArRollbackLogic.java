/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arrollback.ArRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArRollbackCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売掛ロールバック処理 ロジッククラス interface.
 * @author tosco
 */
public interface ArRollbackLogic {

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の年月を取得
	 * 
	 * @param sectionCd 部門コード
	 * @return ArRollback 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ArRollback getSearch(final String sectionCd) throws NoDataException;

	/**
	 * 売掛ヘッダー(ロールバック対象)検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchRbList(final String sectionCd,
			final Date cleditDate);

	/**
	 * 売掛ヘッダー(未来締めデータ)検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 売掛ヘッダーデータ
	 */
	List<ArRollback> getSearchList(final String sectionCd, final Date cleditDate);

	/**
	 * 消込データ検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	List<ArRollback> getSearchEraserList(final String sectionCd,
			final Date cleditDate);

	/**
	 * グループ間相殺データ(締め未処理)検索処理
	 * 
	 * @param sectionCd 部門コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArRollback> 検索データ
	 */
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　開始
	//List<ArRollback> getSearchOffsetList(final String sectionCd,final Date cleditDate);
	List<ArRollback> getSearchOffsetList(final String sectionCd,final Date offsetFromDate,final Date offsetToDate);
	//20210409　S.FUJIMAKI 変更 買掛売掛ﾛﾙﾊ修正　終了

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛ロールバックForm
	 * @return ProArRollbackCallDto 売掛ロールバック処理用Dto
	 */
	ProArRollbackCallDto setProcedureDto(final ArRollbackForm frm);

	/**
	 * 売掛ロールバック
	 * @param proDto 売掛ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProArRollbackCallDto proDto);

}
