/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentupdate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.procparam.ProcParam;
import com.asahikaseieng.dao.nonentity.common.procedurelog.ProcedureLog;
import com.asahikaseieng.dao.nonentity.payment.paymentupdate.PaymentUpdate;
import com.asahikaseieng.dao.nonentity.procedurecall.ProPaymentUpdateCallDto;

/**
 * 支払更新処理 ロジッククラス interface.
 * @author tosco
 */
public interface PaymentUpdateLogic {

	/**
	 * 支払ヘッダー検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param paymentDate 支払締め日
	 * @return List<PaymentUpdate> 支払ヘッダーデータ
	 */
	List<PaymentUpdate> getSearchList(final String organizationCd,
			final String venderCd, final Date paymentDate);

	/**
	 * 支払PROCEDURE DTOセット
	 * @param frm 支払更新Form
	 * @param companyCd 会社コード
	 * @return ProClUpdateCallDto 支払更新処理用Dto
	 */
	ProPaymentUpdateCallDto setProcedureDto(final PaymentUpdateForm frm,
			final String companyCd);

	/**
	 * 支払更新
	 * @param proDto 支払更新処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 * 
	 */
	BigDecimal callProcedure(final ProPaymentUpdateCallDto proDto);

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
	 * @param companyCd 自社コード
	 */
	void insertProcParam(final String procCd, final PaymentUpdateForm frm,
			final String companyCd);
}
