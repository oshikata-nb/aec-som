/*
 * Created on 2009/02/02
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetail;
import com.asahikaseieng.dao.entity.orderdetail.OrderDetailDao;
import com.asahikaseieng.dao.entity.orderhead.OrderHead;
import com.asahikaseieng.dao.entity.orderhead.OrderHeadDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntityDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 出荷指図詳細 ロジック実装クラス
 * @author tosco
 */
public class ShippingDetailCompanyLogicImpl extends AbstractShippingDetailLogic
		implements ShippingDetailCompanyLogic {

	/** 出荷指図詳細画面用出荷指図ヘッダテーブル用Dao */
	private ShippingDetailCompanyEntityDao shippingDetailCompanyEntityDao;

	/** 出荷指図詳細画面用出荷指図詳細テーブル用Dao */
	private ShippingDetailCompanyListDao shippingDetailCompanyListDao;

	/** 受注ヘッダテーブル用Dao */
	private OrderHeadDao orderHeadDao;

	/** 受注詳細テーブル用Dao */
	private OrderDetailDao orderDetailDao;

	/** 上位ロケーションを探索する回数の最大値（無限ループ回避のため） */
	protected static final BigDecimal LOCATIN_LOOP_MAX = BigDecimal.TEN;

	private LocationDao locationDao;

	/**
	 * コンストラクタ.出荷指図
	 */
	public ShippingDetailCompanyLogicImpl() {
	}

	/**
	 * 検索処理を行なう.出荷指図
	 * @param shippingNo 出荷番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public ShippingDetailCompanyEntity getEntity(final String shippingNo)
			throws NoDataException {
		checkParams(shippingNo);

		// 出荷指図ヘッダデータ取得
		ShippingDetailCompanyEntity bean = shippingDetailCompanyEntityDao
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
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<ShippingDetailCompanyList> 出荷指図詳細データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ShippingDetailCompanyList> getDetailList(
			final String shippingNo, final String unitDivision,
			final String venderDivision, final String venderCd)
			throws NoDataException {
		checkParams(shippingNo);

		// 出荷指図詳細データ取得
		List<ShippingDetailCompanyList> list = shippingDetailCompanyListDao
				.getEntity(shippingNo);
		// 数値のフォーマット
		super.formatDetailList(list, unitDivision, venderDivision, venderCd);

		return list;
	}

	/**
	 * 上位ロケーションを取得する
	 * @param locationCd ロケーションコード
	 * @return String 最上位ロケーション
	 * @throws NoDataException データ無し例外
	 */
	public String getUpperLocation(final String locationCd)
			throws NoDataException {

		String search = null;

		search = locationCd;
		for (int i = 0; i < LOCATIN_LOOP_MAX.intValue(); i++) {
			Location upper = locationDao.getEntity(search);
			if (upper.getLocationLevel().equals(BigDecimal.ONE)) {
				return upper.getLocationCd();
			} else {
				search = upper.getUpperLocationCd();
			}
		}
		return null;
	}

	/**
	 * 出荷指図データの登録処理を行う
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception {

		// 出荷指図を更新
		String shippingNo = super.insert(frm, frm.getDetailList(), tantoCd);

		if (shippingNo != null) {
			// 受注詳細を更新
			updateOrderDetail(frm.getOrderNo(), frm.getOrderRowNo(),
				shippingNo, frm.getStrUpdateDateOrderDetail(), tantoCd);
			// 受注ヘッダを更新（ヘッダで排他処理を行うため最後に更新する）
			updateOrderHead(frm.getOrderNo(), frm.getStrUpdateDateOrderHead(),
				tantoCd);
		}
	}

	/**
	 * 出荷指図データの更新処理を行う
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception {
		// 出荷指図情報更新
		super.update(frm, frm.getDetailList(), tantoCd);

		// 受注が変更されている場合
		if (!frm.getOrderNo().equals(frm.getOrderNoPrev())
				|| !frm.getOrderRowNo().equals(frm.getOrderRowNoPrev())) {
			// 前回の受注番号の受注詳細の出荷番号をクリアする
			updateOrderDetail(frm.getOrderNoPrev(), frm.getOrderRowNoPrev(),
				null, frm.getStrUpdateDateOrderDetailPrev(), tantoCd);

			// 異なる受注番号に変更する場合は、前回の受注番号の受注ヘッダを更新する
			if (!frm.getOrderNo().equals(frm.getOrderNoPrev())) {
				updateOrderHead(frm.getOrderNoPrev(), frm
						.getStrUpdateDateOrderHeadPrev(), tantoCd);
			}

			// 今回の状態で受注詳細を更新
			updateOrderDetail(frm.getOrderNo(), frm.getOrderRowNo(), frm
					.getShippingNo(), frm.getStrUpdateDateOrderDetail(),
				tantoCd);
			// 今回の状態で受注ヘッダを更新（ヘッダで排他処理を行うため最後に更新する）
			updateOrderHead(frm.getOrderNo(), frm.getStrUpdateDateOrderHead(),
				tantoCd);
		}
	}

	/**
	 * 削除処理を行う.
	 * @param frm 出荷指図詳細（花王・その他）画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final ShippingDetailCompanyForm frm, final String tantoCd)
			throws NoDataException, Exception {
		// 出荷指図情報削除
		super.delete(frm, tantoCd);

		// 前回の受注番号の受注詳細の出荷番号をクリアする
		updateOrderDetail(frm.getOrderNoPrev(), frm.getOrderRowNoPrev(), null,
			frm.getStrUpdateDateOrderDetailPrev(), tantoCd);
		// 前回の受注番号の受注ヘッダを更新（ヘッダで排他処理を行うため最後に更新する）
		updateOrderHead(frm.getOrderNoPrev(), frm
				.getStrUpdateDateOrderHeadPrev(), tantoCd);
	}

	/**
	 * 受注ヘッダテーブルの更新処理を行う
	 * @param orderNo 受注番号
	 * @param updateDateHead 受注ヘッダ更新日時
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	private void updateOrderHead(final String orderNo,
			final String updateDateHead, final String tantoCd)
			throws NoDataException, Exception {

		try {
			// 受注ヘッダを更新(受注ヘッダで排他を行うので最後に更新する)
			OrderHead head = orderHeadDao.getEntity(orderNo);
			if (head == null) {
				throw new NoDataException();
			}
			head.setUpdateDate(Timestamp.valueOf(updateDateHead));
			head.setUpdatorCd(tantoCd);
			orderHeadDao.update(head);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 受注詳細テーブルの更新処理を行う
	 * @param orderNo 受注番号
	 * @param orderRowNo 行番号(受注)
	 * @param shippingNo 出荷番号
	 * @param updateDateDetail 受注詳細更新日時
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	private void updateOrderDetail(final String orderNo,
			final String orderRowNo, final String shippingNo,
			final String updateDateDetail, final String tantoCd)
			throws NoDataException, Exception {

		try {
			// 受注詳細を更新
			OrderDetail detail = orderDetailDao.getEntity(orderNo,
				new BigDecimal(orderRowNo));
			if (detail == null) {
				throw new NoDataException();
			}
			detail.setUpdateDate(Timestamp.valueOf(updateDateDetail));
			detail.setShippingNo(shippingNo);
			orderDetailDao.update(detail);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
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
	 * @param shippingDetailCompanyEntityDao 出荷指図詳細画面用出荷指図ヘッダテーブル用Dao
	 */
	public void setShippingShippingDetailCompanyEntityDao(
			final ShippingDetailCompanyEntityDao shippingDetailCompanyEntityDao) {
		this.shippingDetailCompanyEntityDao = shippingDetailCompanyEntityDao;
	}

	/**
	 * 出荷指図詳細画面用出荷指図詳細テーブル用Daoを設定する
	 * @param shippingDetailCompanyListDao 出荷指図詳細画面用出荷指図詳細テーブル用Dao
	 */
	public void setShippingShippingDetailCompanyListDao(
			final ShippingDetailCompanyListDao shippingDetailCompanyListDao) {
		this.shippingDetailCompanyListDao = shippingDetailCompanyListDao;
	}

	/**
	 * 受注ヘッダテーブル用Daoを設定する
	 * @param orderHeadDao 受注ヘッダテーブル用Dao
	 */
	public void setOrderHeadDao(final OrderHeadDao orderHeadDao) {
		this.orderHeadDao = orderHeadDao;
	}

	/**
	 * 受注詳細テーブル用Daoを設定する
	 * @param orderDetailDao 受注詳細テーブル用Dao
	 */
	public void setOrderDetailDDao(final OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	/**
	 * locationDaoを設定する
	 * @param locationDao ロケーションDAO
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

}
