/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.nonentity.payment.paymentrollback.PaymentRollback;
import com.asahikaseieng.dao.nonentity.procedurecall.ProPaymentRollbackCallDto;

/**
 * 支払更新ロールバック処理 ロジッククラス interface. 
 * @author tosco
 */
public interface PaymentRollbackLogic {

	/**
	 * 支払ヘッダー(ロールバック対象)検索処理	 * 
	 * @param organizationCd  部署コード	 * @param venderCd   支払先コード	 * @param payableDate 支払締め日
	 * @return List<UpdateRollback> 支払ヘッダーデータ
	 */
	List<PaymentRollback> getSearchRbList(final String organizationCd, final String venderCd, final Date payableDate);

	/**
	 * 支払ヘッダー(未来締めデータ)検索処理	 * 
	 * @param organizationCd  部署コード	 * @param venderCd   支払先コード	 * @param payableDate 支払締め日
	 * @return List<UpdateRollback> 支払ヘッダーデータ
	 */
	List<PaymentRollback> getSearchList(final String organizationCd, final String venderCd, final Date payableDate);

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd  部署コード
	 * @param venderCd   請求先コード
	 * @param payableDate 支払締め日
	 * @return List<PaymentRollback> 検索データ
	 */
	List<PaymentRollback> getSearchEraserList(final String organizationCd,
					final String venderCd, final Date payableDate);

	/**
	 * 支払PROCEDURE DTOセット
	 * @param frm 支払ロールバックForm
	 * @return ProArRollbackCallDto 支払ロールバック処理用Dto
	 */
	ProPaymentRollbackCallDto setProcedureDto(final PaymentRollbackForm frm);

	/**
	 * 支払ロールバック
	 * @param proDto 支払ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 */
	BigDecimal callProcedure(final ProPaymentRollbackCallDto proDto);

}
