/*
 * Created on 2009/06/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.payment;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.vender.payment.PaymentVenderForAutoComplete;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetStockReductionCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 支払入力－取引先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface PaymentVenderForAutoCompleteLogic {

	/**
	 * 検索画面用取引先マスタのオートコンプリート用データの取得
	 * @param organizationCd 部署コード
	 * @param venderCd 取引先コードまたは取引先名称
	 * @return List<PaymentVenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PaymentVenderForAutoComplete> getSearchList(
			final String organizationCd, final String venderCd)
			throws NoDataException;

	/**
	 * サイト情報取得
	 * @param venderCd 仕入先コード
	 * @param paymentScheduledAmount 支払予定額
	 * @return サイト情報
	 */
	ProGetStockReductionCallDto getStockReduction(final String venderCd,
			final BigDecimal paymentScheduledAmount);
}
