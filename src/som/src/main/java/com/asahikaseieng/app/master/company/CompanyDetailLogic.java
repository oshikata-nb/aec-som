/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import com.asahikaseieng.dao.entity.master.company.Company;
import com.asahikaseieng.dao.entity.master.numberchkdisit.NumberChkdisit;
import com.asahikaseieng.dao.nonentity.master.bankdetail.BankDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 自社詳細ロジック interface.
 * @author t0011036
 */
public interface CompanyDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param companyCd 自社コード
	 * @throws NoDataException NoDataException
	 * @return Company
	 */
	Company getEntity(final String companyCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param companyCd 自社コード
	 * @return CompanyDetail
	 */
	CompanyDetail getDetailEntity(final String companyCd);

	/**
	 * 検索処理を行う.
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return NumberChkdisit
	 */
	NumberChkdisit getNumberEntity(final String unitDivision,
			final String venderDivision, final String venderCd);

	/**
	 * 検索処理を行う.
	 * @param bankMasterCd 銀行マスタコード
	 * @return BankDetail
	 */
	BankDetail getBankEntity(final String bankMasterCd);

	/**
	 * 登録処理を行う.
	 * @param bean 追加対象データ
	 * @param beanTax 追加対象データ(消費税)
	 * @param beanRoundup 追加対象データ(端数)
	 * @param beanSales 追加対象データ(売上金額)
	 * @param beanPurchase 追加対象データ(仕入金額)
	 * @param beanSalesUnitprice 追加対象データ(売上単価)
	 * @param beanPurchaseUnitprice 追加対象データ(仕入単価)
	 * @param beanUnitprice 追加対象データ(単価)
	 * @param beanBlendQty 追加対象データ(配合量)
	 * @param beanMixRate 追加対象データ(配合率)
	 * @param beanAdj 追加対象データ(配合調整)
	 */
	void regist(final Company bean, final NumberChkdisit beanTax,
			final NumberChkdisit beanRoundup, final NumberChkdisit beanSales,
			final NumberChkdisit beanPurchase,
			final NumberChkdisit beanSalesUnitprice,
			final NumberChkdisit beanPurchaseUnitprice,
			final NumberChkdisit beanUnitprice,
			final NumberChkdisit beanBlendQty,
			final NumberChkdisit beanMixRate, final NumberChkdisit beanAdj);
}
