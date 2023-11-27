/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.delivery.DeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface DeliveryForAutoCompleteLogic {
	//納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先ココードまたは納入先コ名称
	 * @return List<DeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<DeliveryForAutoComplete> getSearchList(String deliveryCd) throws NoDataException;
	
	//fromの区分を条件に設定した上で、納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 納入先マスタのオートコンプリート用データの取得

	 * @param deliveryCd 納入先ココードまたは納入先コ名称
	 * @param　deliveryDivision 区分
	 * @return List<DeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合

	 */
	List<DeliveryForAutoComplete> getDeliverySearchList(String deliveryCd,String deliveryDivision) throws NoDataException;

}
