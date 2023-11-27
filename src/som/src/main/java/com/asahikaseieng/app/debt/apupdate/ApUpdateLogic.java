/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.debt.apupdate.ApUpdate;
import com.asahikaseieng.dao.nonentity.procedurecall.ProApUpdateCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 買掛更新処理 ロジッククラス interface.
 * @author tosco
 */
public interface ApUpdateLogic {

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の翌年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ApUpdate 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ApUpdate getSearch(final String organizationCd) throws NoDataException;

	/**
	 * 買掛ヘッダー検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 買掛締め日
	 * @return List<ApUpdate> 買掛ヘッダーデータ
	 */
	List<ApUpdate> getSearchList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 買掛PROCEDURE DTOセット
	 * @param frm 買掛更新Form
	 * @return ProArUpdateCallDto 買掛更新処理用Dto
	 */
	ProApUpdateCallDto setProcedureDto(final ApUpdateForm frm);

	/**
	 * 買掛更新
	 * @param proDto 買掛更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 * 
	 */
	BigDecimal callProcedure(final ProApUpdateCallDto proDto);

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
	void insertProcParam(final String procCd, final ApUpdateForm frm);
}
