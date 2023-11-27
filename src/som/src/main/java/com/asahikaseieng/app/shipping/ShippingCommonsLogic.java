/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxes;

/**
 * 出荷指図検索 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingCommonsLogic {

	/**
	 * 運送会社一覧取得
	 * @return List<ShippingCarryForComboboxes>
	 */
	List<ShippingCarryForComboboxes> getCarryList();

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCarryCombobox();

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCarryAllCombobox();

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

	/**
	 * 納入先ｺｰﾄﾞから納入先住所を取得する
	 * @param deliveryCd 納入先ｺｰﾄﾞ
	 * @return 納入先住所
	 */
	String getDeliveryAddress(final String deliveryCd);


}
