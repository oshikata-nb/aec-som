/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図詳細（花王・その他）画面 ロジッククラス interface.
 * @author tosco
 */
public interface ShippingDetailCompanyLogic {

	/**
	 * 出荷指図ヘッダデータを取得する
	 * @param shippingNo 出荷番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	ShippingDetailCompanyEntity getEntity(final String shippingNo)
			throws NoDataException;

	/**
	 * 出荷指図詳細データを取得する
	 * @param shippingNo 出荷番号
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<ShippingDetailCompanyList>
	 * @throws NoDataException データが存在しない例外
	 */
	List<ShippingDetailCompanyList> getDetailList(final String shippingNo,
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException;

	/**
	 * 上位ロケーションを取得する
	 * @param locationCd ロケーションコード
	 * @return String 上位ロケーション
	 * @throws NoDataException データ無し例外
	 */
	String getUpperLocation(final String locationCd) throws NoDataException;

	/**
	 * 出荷指図データの登録処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void insert(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * 出荷指図データの更新処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void update(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * 削除処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	void delete(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception;

}
