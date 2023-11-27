/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.organization;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.organization.OrganizationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.organization.OrganizationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 部署マスタのAuto Complete用ロジック
 * @author tosco
 */
public class OrganizationForAutoCompleteLogicImpl implements
		OrganizationForAutoCompleteLogic {
	/** 部署マスタ操作DAO */
	private OrganizationForAutoCompleteDao organizationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public OrganizationForAutoCompleteLogicImpl() {
	}

	// 部署コードまたは、部署名称で検索--------------------------------
	/**
	 * 検索画面用部署マスタのオートコンプリート用データの取得
	 * @param itemCd 部署コードまたは部署名称
	 * @return List<OrganizationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<OrganizationForAutoComplete> getSearchList(final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(itemCd);
		List<OrganizationForAutoComplete> list = organizationForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 部署マスタ操作DAOを設定します。
	 * @param organizationForAutoCompleteDao 部署マスタ操作DAO
	 */
	public void setOrganizationForAutoCompleteDao(
			final OrganizationForAutoCompleteDao organizationForAutoCompleteDao) {
		this.organizationForAutoCompleteDao = organizationForAutoCompleteDao;
	}

}
