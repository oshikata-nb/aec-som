/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.entity.master.carry.Carry;
import com.asahikaseieng.dao.entity.master.carry.CarryDao;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntityDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherListDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図詳細 ロジック実装クラス
 * @author tosco
 */
public class ShippingDetailOtherLogicImpl extends AbstractShippingDetailLogic
		implements ShippingDetailOtherLogic {

	/** 出荷指図詳細画面用出荷指図ヘッダテーブル用Dao */
	private ShippingDetailOtherEntityDao shippingDetailOtherEntityDao;

	/** 出荷指図詳細画面用出荷指図詳細テーブル用Dao */
	private ShippingDetailOtherListDao shippingDetailOtherListDao;

	/** 納入先マスタ用Dao */
	private DeliveryDao deliveryDao;

	private CarryDao carryDao;

	/**
	 * コンストラクタ.出荷指図
	 */
	public ShippingDetailOtherLogicImpl() {
	}

	/**
	 * 検索処理を行なう.出荷指図
	 * @param shippingNo 出荷番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public ShippingDetailOtherEntity getEntity(final String shippingNo)
			throws NoDataException {
		checkParams(shippingNo);

		// 出荷指図ヘッダデータ取得
		ShippingDetailOtherEntity bean = shippingDetailOtherEntityDao
				.getEntity(shippingNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 出荷指図詳細データを取得する
	 * @param shippingNo 出荷番号
	 * @param unitDivision 単位区分
	 * @return List<ShippingDetailOtherList> 出荷指図詳細データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ShippingDetailOtherList> getDetailList(final String shippingNo,
			final String unitDivision) throws NoDataException {
		checkParams(shippingNo);

		// 出荷指図詳細データ取得
		List<ShippingDetailOtherList> list = shippingDetailOtherListDao
				.getEntity(shippingNo);
		// 数値のフォーマット
		super.formatDetailList(list, unitDivision, null, null);

		return list;
	}

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return ShippingShippingDetailEntity
	 * @throws NoDataException データが存在しない例外
	 */
	public ShippingDetailOtherEntity getItem(final String itemCd)
			throws NoDataException {
		checkParams(itemCd);

		// 品目情報取得
		ShippingDetailOtherEntity bean = shippingDetailOtherEntityDao
				.getItem(itemCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 出荷指図データの登録処理を行う
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final ShippingDetailOtherForm frm, final String tantoCd)
			throws NoDataException, Exception {
		// 品目数分INSERT
		for (ShippingDetailOtherItemBean itemBean : frm.getItemList()) {
			// 品名コードを更新用Formにコピー
			frm.setItemCd(itemBean.getItemCd());
			super.insert(frm, itemBean.getDetailList(), tantoCd);
		}
	}

	/**
	 * 出荷指図データの更新処理を行う
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final ShippingDetailOtherForm frm, final String tantoCd)
			throws NoDataException, Exception {
		// 更新時の品目は１件なので、１件目情報を更新用のフォームにコピーする
		// ※花王・その他画面の場合、itemListの中にのみ品目、詳細情報を持っているため
		ShippingDetailOtherItemBean itemBean = frm.getItemList().get(0);
		frm.setItemCd(itemBean.getItemCd());

		super.update(frm, itemBean.getDetailList(), tantoCd);
	}

	/**
	 * 削除処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final ShippingDetailOtherForm frm, final String tantoCd)
			throws NoDataException, Exception {
		super.delete(frm, tantoCd);
	}

	/**
	 * 納入先マスタ存在チェック用
	 * @param locationCd 納入先コード
	 * @return チェック結果 true:存在する false:存在しない
	 */
	public boolean isExistsDelivery(final String locationCd) {

		// 引数の納入先コードより納入先マスタの検索を行う
		Delivery bean = deliveryDao.getEntity(locationCd);
		if (bean == null) {
			return false;
		}

		return true;
	}

	/**
	 * 運送会社コードマスタチェックを行う.<br>
	 * 運送会社マスタにデータがない場合はエラーとする。
	 * @param carryCd 運送会社コード
	 * @param errors ActionMessage
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkCarry(final String carryCd,
			final ActionMessages errors) {
		Carry carryBean = carryDao.getEntity(carryCd);
		if (carryBean == null || carryCd == null) {
			// データが存在しない場合
			errors.add("", new ActionMessage("errors.shipping.carry.not.exist"));
		}

		return errors;
	}

	/**
	 * パラメータチェック
	 * @param param パラメータ
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 出荷指図詳細画面用出荷指図ヘッダテーブル用Daoを設定する
	 * @param shippingDetailOtherEntityDao 出荷指図詳細画面用出荷指図ヘッダテーブル用Dao
	 */
	public void setShippingShippingDetailOtherEntityDao(
			final ShippingDetailOtherEntityDao shippingDetailOtherEntityDao) {
		this.shippingDetailOtherEntityDao = shippingDetailOtherEntityDao;
	}

	/**
	 * 出荷指図詳細画面用出荷指図詳細テーブル用Daoを設定する
	 * @param shippingDetailOtherListDao 出荷指図詳細画面用出荷指図詳細テーブル用Dao
	 */
	public void setShippingShippingDetailOtherListDao(
			final ShippingDetailOtherListDao shippingDetailOtherListDao) {
		this.shippingDetailOtherListDao = shippingDetailOtherListDao;
	}

	/**
	 * 納入先マスタ用Daoを設定する
	 * @param deliveryDao 納入先マスタ用Dao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	/**
	 * 運送会社マスタdaoを設定します。
	 * @param carryDao エラーログ出力用dao
	 */
	public void setCarryDao(final CarryDao carryDao) {
		this.carryDao = carryDao;
	}
}
