/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.sales;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.sales.SalesItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.sales.SalesItemForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 売上用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public class SalesItemForAutoCompleteLogicImpl implements
		SalesItemForAutoCompleteLogic {
	/** 品目マスタ操作DAO */
	private SalesItemForAutoCompleteDao salesItemForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public SalesItemForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名称
	 * @param deliveryCd 納入先コード
	 * @return List<SalesItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<SalesItemForAutoComplete> getDetailList(final String itemCd,
			final String deliveryCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<SalesItemForAutoComplete> list = salesItemForAutoCompleteDao
				.getSearchList(val, deliveryCd,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param deliveryCd 納入先コード
	 * @return List<SalesItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<SalesItemForAutoComplete> getDetailOtherCompany1List(
			final String otherCompany1, final String deliveryCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<SalesItemForAutoComplete> list = salesItemForAutoCompleteDao
				.getOtherCompany1SearchList(val, deliveryCd,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 品目マスタ操作DAOを設定します。
	 * 
	 * @param salesItemForAutoCompleteDao 品目マスタ操作DAO
	 */
	public void setSalesItemForAutoCompleteDao(
			final SalesItemForAutoCompleteDao salesItemForAutoCompleteDao) {
		this.salesItemForAutoCompleteDao = salesItemForAutoCompleteDao;
	}

}
