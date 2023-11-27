/*
 * Created on 2009/03/05
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.unitprice;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.balance.Balance;
import com.asahikaseieng.dao.entity.master.balance.BalanceDao;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitprice;
import com.asahikaseieng.dao.nonentity.master.varidunitprice.VaridUnitpriceDao;

/**
 * 有効単価取得クラス ロジック実装クラス
 * @author t1344224
 */
public class GetVaridUnitpriceLogicImpl implements GetValidUnitpriceLogic {

	private VaridUnitpriceDao varidUnitpriceDao;

	private BalanceDao balanceDao;

	/** 見積り単価承認済み */
	protected static final BigDecimal ESTIMATE_APPROVAL = new BigDecimal(3);

	/**
	 * コンストラクタ
	 */
	public GetVaridUnitpriceLogicImpl() {
	}

	/**
	 * 帳合コードと品目コードから有効な単価を取得
	 * @param balanceCd 帳合コード
	 * @param itemCd 品目
	 * @param date 日付
	 * @return VaridUnitprice 有効単価
	 */
	public VaridUnitprice getValidUnitprice(final String balanceCd,
			final String itemCd, final String date) {

		String tmpBalanceCd = balanceCd;

		VaridUnitprice retBean = varidUnitpriceDao.getUnitPrice(tmpBalanceCd,
			itemCd, date, ESTIMATE_APPROVAL);

		// 帳合コードがNullの場合単価検索処理無し(帳合コードがNULLの場合品目マスタの標準販売単価を返す）
		if (tmpBalanceCd == null) {

			if (retBean != null) {
				retBean.setTmpUnitpriceFlg("0");

				// 品目がなかった場合0を返す
				if (retBean.getStandardUnitPrice() == null) {
					retBean.setStandardUnitPrice(new BigDecimal(0));
					retBean.setMatss(new BigDecimal(0));
					retBean.setSpecialDiscount(new BigDecimal(0));
					retBean.setStandardAmount(new BigDecimal(0));
					retBean.setStandardDiscount(new BigDecimal(0));
					retBean.setUnitprice(new BigDecimal(0));
				}
			}
			return retBean;
		}

		while (true) {

			retBean = varidUnitpriceDao.getUnitPrice(tmpBalanceCd, itemCd,
				date, new BigDecimal(3));

			// 検索結果がある場合(正単価)
			if (retBean != null && retBean.getEstimateNo() != null) {

				retBean.setTmpUnitpriceFlg("0");
				return retBean;

				// 検索結果がない場合
			} else {
				retBean = varidUnitpriceDao.getUnitPrice(tmpBalanceCd, itemCd,
					null, null);

				// 検索結果がある場合（正単価無し）
				if (retBean != null && retBean.getEstimateNo() != null) {
					retBean.setTmpUnitpriceFlg("1");
					// (帳合コードがNULLではない場合品目マスタの標準販売単価を返さない）
					retBean.setStandardUnitPrice(new BigDecimal(0));
					return retBean;

					// 検索結果がない場合
				} else {

					// 上位帳合コードを取得
					Balance balanceBean = balanceDao.getEntity(tmpBalanceCd);
					tmpBalanceCd = balanceBean.getUpperBalanceCd();

					// 上位帳合コードがない場合処理終了 有る場合は処理継続
					if (tmpBalanceCd == null) {

						// 検索結果がない場合
						if (retBean == null) {
							retBean = new VaridUnitprice();
						}

						retBean.setTmpUnitpriceFlg("1");
						// (帳合コードがNULLではない場合品目マスタの標準販売単価を返さない）
						retBean.setStandardUnitPrice(new BigDecimal(0));
						return retBean;
					}

				}

			}

		}
	}

	/**
	 * varidUnitpriceDaoを設定します。
	 * @param varidUnitpriceDao varidUnitpriceDao
	 */
	public void setVaridUnitpriceDao(final VaridUnitpriceDao varidUnitpriceDao) {
		this.varidUnitpriceDao = varidUnitpriceDao;
	}

	/**
	 * balanceDaoを設定します。
	 * @param balanceDao balanceDao
	 */
	public void setBalanceDao(final BalanceDao balanceDao) {
		this.balanceDao = balanceDao;
	}
}
