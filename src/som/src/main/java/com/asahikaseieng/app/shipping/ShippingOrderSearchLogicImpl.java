/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchListDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class ShippingOrderSearchLogicImpl implements ShippingOrderSearchLogic {

	/** 受注検索(ポップアップ)Dao */
	private ShippingOrderSearchListDao shippingOrderSearchDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ShippingOrderSearchLogicImpl() {
	}

	/**
	 * 受注検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ShippingOrderSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<ShippingOrderSearchList> getList(final ShippingOrderSearchListPagerCondition condition)
		throws NoDataException {

		checkParams(condition);

		List<ShippingOrderSearchList> list = shippingOrderSearchDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		NumberChkDisitDetail detail = null;
		//数値フォーマット
		for (ShippingOrderSearchList bean : list) {
			String unitDivision = bean.getUnitDivision();
			String venderDivision = ShippingConst.VENDER_DIVISION_TS;
			String venderCd = bean.getVenderCd();
			//増付数
			bean.setStrOrderQty(checker.format(unitDivision, venderDivision,
				venderCd, bean.getOrderQty()));
			//受注量
			bean.setStrMatss(checker.format(unitDivision, venderDivision, venderCd,
				bean.getMatss()));
			//運賃
			bean.setStrCarryFare(checker.format(ShippingConst.UNIT_DIVISION_UNTIN, venderDivision, venderCd,
				bean.getCarryFare()));

			detail = checker.getCheckDigit(unitDivision, venderDivision, venderCd);
			//小数点桁数
			if (detail.getSmallnumLength() != null) {
				bean.setDecimalPoint(detail.getSmallnumLength().toString());
			}
			//端数区分
			if (detail.getRoundDivision() != null) {
				bean.setRound(detail.getRoundDivision().toString());
			}
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ShippingOrderSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 受注検索(ポップアップ)Daoを設定します。
	 * @param shippingOrderSearchDao 受注検索(ポップアップ)Dao
	 */
	public void setShippingOrderSearchDao(final ShippingOrderSearchListDao shippingOrderSearchDao) {
		this.shippingOrderSearchDao = shippingOrderSearchDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}
}
