/*
 * Created on 2008/08/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.payment.paymentrollback;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.asahikaseieng.dao.nonentity.payment.paymentrollback.PaymentRollback;
import com.asahikaseieng.dao.nonentity.payment.paymentrollback.PaymentRollbackDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProPaymentRollbackCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;

/**
 * 支払更新ロールバック処理 ロジック実装クラス
 * @author tosco
 */
public class PaymentRollbackLogicImpl implements PaymentRollbackLogic {

	private PaymentRollbackDao paymentRollbackDao;

	/** 支払ロールバックPROCEDURE DAO宣言 */
	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ.支払更新ロールバック
	 */
	public PaymentRollbackLogicImpl() {
	}

	/**
	 * 支払ヘッダー(ロールバック対象)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param payableDate 支払締め日
	 * @return List<UpdateRollback> 支払ヘッダーデータ
	 */
	public List<PaymentRollback> getSearchRbList(final String organizationCd,
			final String venderCd, final Date payableDate) {
		checkParams(organizationCd);

		List<PaymentRollback> list = paymentRollbackDao.getSearchRbList(
			organizationCd, venderCd, payableDate);

		return list;
	}

	/**
	 * 支払ヘッダー(未来締めデータ)検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 支払先コード
	 * @param payableDate 支払締め日
	 * @return List<UpdateRollback> 支払ヘッダーデータ
	 */
	public List<PaymentRollback> getSearchList(final String organizationCd,
			final String venderCd, final Date payableDate) {
		checkParams(organizationCd);

		List<PaymentRollback> list = paymentRollbackDao.getSearchList(
			organizationCd, venderCd, payableDate);

		return list;
	}

	/**
	 * 消込データ検索処理
	 * 
	 * @param organizationCd 部署コード
	 * @param venderCd 請求先コード
	 * @param payableDate 支払締め日
	 * @return List<PaymentRollback> 検索データ
	 */
	public List<PaymentRollback> getSearchEraserList(
			final String organizationCd, final String venderCd,
			final Date payableDate) {
		checkParams(organizationCd);

		List<PaymentRollback> list = paymentRollbackDao.getSearchEraserList(
			organizationCd, venderCd, payableDate);

		return list;
	}

	/**
	 * 支払更新ロールバックPROCEDURE DTOセット
	 * @param frm 支払更新ロールバックForm
	 * @return UpdateRollbackForm 支払更新ロールバック処理用Dto
	 */
	public ProPaymentRollbackCallDto setProcedureDto(
			final PaymentRollbackForm frm) {
		ProPaymentRollbackCallDto dto = new ProPaymentRollbackCallDto();

		// 部署コード

		dto.setPStrOrganizationCd(frm.getOrganizationCd());
		if (frm.getOrganizationCd().equals("")) {
			dto.setPStrOrganizationCd(null);
		}
		// 支払先コード

		dto.setPStrCustomerCd(frm.getVenderCd());
		if (frm.getVenderCd().equals("")) {
			dto.setPStrCustomerCd(null);
		}
		// 支払締め日
		Date payableDate = frm.getPayableDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dto.setPStrPayableDate(sdf.format(payableDate));
		// 登録者ＩＤ
		dto.setPStrInputorCd(frm.getTantoCd());

		return dto;
	}

	/**
	 * 支払更新ロールバック
	 * @param proDto 支払更新ロールバック処理用Dto
	 * @return BigDecimal 処理結果（0:成功 99:エラー）
	 * 
	 */
	public BigDecimal callProcedure(final ProPaymentRollbackCallDto proDto) {
		// 支払更新ロールバック処理
		procedureCallDao.proPaymentRollback(proDto);

		return proDto.getPNumRet();
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearch");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * PaymentRollbackDaoを設定します。
	 * 
	 * @param paymentRollbackDao PaymentRollbackDao
	 */
	public void setPaymentRollbackDao(
			final PaymentRollbackDao paymentRollbackDao) {
		this.paymentRollbackDao = paymentRollbackDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * 
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

}
