/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図詳細（花王・その他）画面 ロジッククラス interface. 
 * @author tosco
 */
public interface ShippingDetailOtherLogic {

	/**
	 * 出荷指図ヘッダデータを取得する
	 * @param shippingNo 出荷番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	ShippingDetailOtherEntity getEntity(final String shippingNo)
		throws NoDataException;

	/**
	 * 出荷指図詳細データを取得する
	 * @param shippingNo 出荷番号
	 * @param unitDivision 単位区分
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	List<ShippingDetailOtherList> getDetailList(final String shippingNo, final String unitDivision)
		throws NoDataException;

	/**
	 * 出荷指図データの登録処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final ShippingDetailOtherForm frm, final String tantoCd)
		throws NoDataException, Exception;

	/**
	 * 出荷指図データの更新処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final ShippingDetailOtherForm frm, final String tantoCd)
		throws NoDataException, Exception;

	/**
	 * 削除処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final ShippingDetailOtherForm frm, final String tantoCd)
	throws NoDataException, Exception;

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return ShippingShippingDetailEntity
	 * @throws NoDataException データが存在しない例外
	 */
	ShippingDetailOtherEntity getItem(final String itemCd)
		throws NoDataException;

	/**
	 * 納入先マスタ存在チェック
	 * @param locationCd 納入先コード
	 * @return チェック結果 true:存在する false:存在しない
	 */
	boolean isExistsDelivery(final String locationCd);

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	ActionMessages checkCarry(final String carryCd, final ActionMessages errors);
}
