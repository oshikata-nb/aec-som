/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.payment;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.payment.PaymentVenderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.payment.PaymentVenderForAutoCompleteDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetStockReductionCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 取引先マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public class PaymentVenderForAutoCompleteLogicImpl implements
		PaymentVenderForAutoCompleteLogic {
	/** 取引先マスタキュー操作DAO */
	private PaymentVenderForAutoCompleteDao paymentVenderForAutoCompleteDao;

	private ProcedureCallDao procedureCallDao;

	/**
	 * コンストラクタ
	 */
	public PaymentVenderForAutoCompleteLogicImpl() {
	}

	/**
	 * 支払先入力－取引先マスタのオートコンプリート用データの取得
	 * @param organizationCd 部署コード
	 * @param venderCd 取引先コードまたは取引先名称
	 * @return List<PaymentVenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PaymentVenderForAutoComplete> getSearchList(
			final String organizationCd, final String venderCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(venderCd);
		List<PaymentVenderForAutoComplete> list = paymentVenderForAutoCompleteDao
				.getSearchList(organizationCd, val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * サイト情報取得
	 * @param venderCd 仕入先コード
	 * @param paymentScheduledAmount 支払予定額
	 * @return サイト情報
	 */
	public ProGetStockReductionCallDto getStockReduction(final String venderCd,
			final BigDecimal paymentScheduledAmount) {
		ProGetStockReductionCallDto dto = new ProGetStockReductionCallDto();

		dto.setPStrVenderCd(venderCd);
		dto.setPNumPayableAmount(paymentScheduledAmount);

		try {
			procedureCallDao.proGetStockReduction(dto);

			if (dto.getPStrErrorReturnCd() != null) {
				throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
		}

		return dto;
	}

	// setter---------------------------------------------------------------
	/**
	 * 取引先マスタ操作DAOを設定します。
	 * @param paymentVenderForAutoCompleteDao 支払入力－取引先マスタ操作DAO
	 */
	public void setVenderForAutoCompleteDao(
			final PaymentVenderForAutoCompleteDao paymentVenderForAutoCompleteDao) {
		this.paymentVenderForAutoCompleteDao = paymentVenderForAutoCompleteDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * paymentVenderForAutoCompleteDaoを設定します。
	 * @param paymentVenderForAutoCompleteDao paymentVenderForAutoCompleteDao
	 */
	public void setPaymentVenderForAutoCompleteDao(
			final PaymentVenderForAutoCompleteDao paymentVenderForAutoCompleteDao) {
		this.paymentVenderForAutoCompleteDao = paymentVenderForAutoCompleteDao;
	}
}
