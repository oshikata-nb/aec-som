/*
 * Created on 2008/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.credit.arupdate.ArUpdate;
import com.asahikaseieng.dao.nonentity.procedurecall.ProArUpdateCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売掛更新処理 ロジッククラス interface.
 * @author tosco
 */
public interface ArUpdateLogic {

	/**
	 * 売掛ヘッダーからMAX(売掛締め日)の翌年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ArUpdate 売掛ヘッダーデータ(売掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ArUpdate getSearch(final String organizationCd) throws NoDataException;

	/**
	 * 売掛ヘッダー検索処理
	 * 
	 * 
	 * @param organizationCd 部署コード
	 * @param cleditDate 売掛締め日
	 * @return List<ArUpdate> 売掛ヘッダーデータ
	 */
	List<ArUpdate> getSearchList(final String organizationCd,
			final Date cleditDate);

	/**
	 * 売掛PROCEDURE DTOセット
	 * @param frm 売掛更新Form
	 * @return ProArUpdateCallDto 売掛更新処理用Dto
	 */
	ProArUpdateCallDto setProcedureDto(final ArUpdateForm frm);

	/**
	 * 売掛更新
	 * @param proDto 売掛更新処理用Dto
	 * @return BigDecimal 処理結果
	 * 
	 */
	ProArUpdateCallDto callProcedure(final ProArUpdateCallDto proDto);

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
	void insertProcParam(final String procCd, final ArUpdateForm frm);
}
