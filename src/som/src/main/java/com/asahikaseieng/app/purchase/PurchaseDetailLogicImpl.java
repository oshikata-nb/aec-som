/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchase;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForPurchase;
import com.asahikaseieng.dao.entity.master.delivery.Delivery;
import com.asahikaseieng.dao.entity.master.delivery.DeliveryDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.entity.master.organization.Organization;
import com.asahikaseieng.dao.entity.master.organization.OrganizationDao;
import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.entity.master.vender.VenderDao;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.master.belongdetail.BelongDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseDetail;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseDetailDao;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseRemarkList;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 発注詳細 ロジック実装クラス
 * @author tosco
 */
public class PurchaseDetailLogicImpl implements PurchaseDetailLogic {

	/** 発注書発行フラグ 0:未発行 */
	public static final BigDecimal ORDER_SHEET_FLG = new BigDecimal(0);

	/** 分納区分 0:無し */
	public static final BigDecimal REPLY_CONTENT_DIVISION = new BigDecimal(0);

	/** ラベル発行フラグ 0:未発行 */
	public static final BigDecimal LABEL_FLG = new BigDecimal(0);

	/** 発注詳細画面用Dao */
	private PurchaseDetailDao purchaseDetailDao;

	/** 購買オーダーテーブル更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 備考マスタ検索用Dao * */
	private PurchaseRemarkListDao purchaseRemarkListDao;

	/** 品目マスタ検索用Dao * */
	private ItemDao itemDao;

	/** 仕入先マスタ検索用Dao * */
	private VenderDao venderDao;

	/** 組織マスタ検索用Dao * */
	private OrganizationDao organizationDao;

	/** 納入先マスタ検索用Dao * */
	private DeliveryDao deliveryDao;

	/** ロケーションマスタ検索用Dao * */
	private LocationDao locationDao;

	/** ログインマスタ検索用Dao * */
	private LoginDao loginDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	private BelongDetailDao belongDetailDao;

	/**
	 * コンストラクタ
	 */
	public PurchaseDetailLogicImpl() {
	}

	/**
	 * 検索処理を行なう.
	 * @param purchaseNo 購買NO
	 * @param tantoCd ログインユーザー
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public PurchaseDetail getEntity(final String purchaseNo,
			final String tantoCd) throws NoDataException {

		checkParams(purchaseNo);

		PurchaseDetail bean = purchaseDetailDao.getEntity(purchaseNo, tantoCd);

		if (bean == null) {
			throw new NoDataException();
		}

		// 発注数量
		bean.setStrOrderQuantity(checker.format(bean
				.getUnitOfOperationManagement(), "SI", bean.getVenderCd(), bean
				.getOrderQuantity()));

		// 重量
		bean.setStrOrderConvertQuantity(checker.format("1", "SI", bean
				.getVenderCd(), bean.getOrderConvertQuantity()));

		// 発注単価
		bean.setStrOrderUnitprice(checker.format("SITANKA", "SI", bean
				.getVenderCd(), bean.getOrderUnitprice()));

		// 発注金額
		bean.setStrSupplierOrdAmount(checker.format("SIKINGAKU", "SI", bean
				.getVenderCd(), bean.getSupplierOrdAmount()));

		return bean;
	}

	/**
	 * 更新処理を行う
	 * @param bean 更新対象ビーン
	 * @param orderLocation 発注まとめ場所
	 * @throws NoDataException データ無し例外
	 */
	public void update(final PurchaseSubcontract bean,
			final String orderLocation) throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		// *******************************************
		// 更新前にデータを取得し、
		// 品目、仕入先、納入先が変更されている場合
		// 購買ステータス、発注書NO、発注書発行フラグ、発注書発行日、発注書発行者 をクリアする
		// *******************************************
		PurchaseSubcontract getBean = purchaseSubcontractDao.getEntity(bean
				.getPurchaseNo());
		if (getBean == null) {
			throw new NoDataException();
		}
		// 品目、仕入先、納入先のどれか１つでも変更されている場合
		if (!(bean.getItemCd().equals(getBean.getItemCd()))
				|| !(bean.getVenderCd().equals(getBean.getVenderCd()))
				|| (!(bean.getLocationCd().equals(getBean.getLocationCd())) && StringUtils
						.isEmpty(orderLocation))
				|| !(AecDateUtils.dateFormat(bean.getOrderDate(), "yyyy/MM/dd")
						.equals(AecDateUtils.dateFormat(getBean.getOrderDate(),
							"yyyy/MM/dd")))) {
			// 購買ステータス|7:未発行
			bean.setStatus(PurchaseStatus.NOT_ISSUE);
			// 発注書NO=null
			bean.setOrderSheetNo(null);
			// 発注書発行フラグ|0:未発行
			bean.setOrderSheeFlag(ORDER_SHEET_FLG);
			// 発注書発行日=null
			bean.setOrderSheeDate(null);
			// 発注書発行者 =null
			bean.setOrderSheelTantoCd(null);
		}
		// 購買ステータス|7:未発行
		bean.setStatus(PurchaseStatus.NOT_ISSUE);

		try {
			if (bean.getOrderConvertQuantity() != null) {
				/* 小数点丸め処理 */
				BigDecimal result = checker.round("1", "SI",
					bean.getVenderCd(), bean.getOrderConvertQuantity());

				bean.setOrderConvertQuantity(result);
			}
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			String errMsg = "errors.purchase.stock.update";
			try {
				/* 在庫更新－発注入力取消 */
				if (!stock.canselPurchase(bean.getPurchaseNo(), bean
						.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			/* 更新処理 */
			int updateNum = purchaseSubcontractDao.update(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new NoDataException();
			}

			try {
				/* 在庫更新－発注入力 */
				if (!stock.entryPurchase(bean.getPurchaseNo(), bean
						.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 登録処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void insert(final PurchaseSubcontract bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		if (bean.getOrderConvertQuantity() != null) {
			/* 小数点丸め処理 */
			BigDecimal result = checker.round("1", "SI", bean.getVenderCd(),
				bean.getOrderConvertQuantity());

			bean.setOrderConvertQuantity(result);
		}
		bean.setStatus(PurchaseStatus.NOT_ISSUE); // ステータス(7)
		bean.setOrderSheeFlag(ORDER_SHEET_FLG); // 発注書発行フラグ(0)
		bean.setReplyContentsDivision(REPLY_CONTENT_DIVISION); // 分納区分(0)
		bean.setLabelFlag(LABEL_FLG); // ラベル発行フラグ(0)
		bean.setRowNo(BigDecimal.ONE);
		bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
		bean.setUpdateDate(AecDateUtils.getCurrentTimestamp()); // 更新日時

		try {
			/* 追加処理 */
			int updateNum = purchaseSubcontractDao.insert(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

			// 自動発番した購買NOを取得する
			PurchaseDetail detailBean = purchaseDetailDao.getPurchaseNo(bean
					.getBuySubcontractOrderNo());
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			String errMsg = "errors.purchase.stock.update";
			try {
				/* 在庫更新－発注入力 */
				if (!stock.entryPurchase(detailBean.getPurchaseNo(), bean
						.getInputorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 削除処理を行う
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final PurchaseSubcontract bean) throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			String errMsg = "errors.purchase.stock.update";
			try {
				/* 在庫更新－発注入力取消 */
				if (!stock.canselPurchase(bean.getPurchaseNo(), bean
						.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			/* 削除処理 */
			int deleteNum = purchaseSubcontractDao.delete(bean);

			if (deleteNum != 1) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 品目マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 */
	public Item getItem(final String itemCd) {

		checkParams(itemCd);

		Item bean = itemDao.getEntity(itemCd);
		return bean;
	}

	/**
	 * 取引先マスタ存在チェック用
	 * @param venderCd 仕入先コード
	 * @return 取得件数
	 */
	public int getCountVender(final String venderCd) {

		// 引数の仕入先コードを使用し、取引先マスタの検索を行う
		checkParams(venderCd);
		Vender bean = venderDao.getEntity(venderCd, "SI");
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 組織マスタ存在チェック用
	 * @param organizationCd 部署コード
	 * @return ある・無し値
	 */
	public int getCountOrganization(final String organizationCd) {

		// 引数の部署コードを使用し、組織マスタの検索を行う
		checkParams(organizationCd);
		Organization bean = organizationDao.getEntity(organizationCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 納入先マスタ存在チェック用
	 * @param deliveryCd 納入先コード
	 * @return ある・無し値
	 */
	public int getCountDelivery(final String deliveryCd) {

		// 引数の納入先コードを使用し、組織マスタの検索を行い
		checkParams(deliveryCd);
		Delivery bean = deliveryDao.getEntity(deliveryCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * ロケーションマスタ存在チェック用
	 * @param locationCd 納入先コード
	 * @return ある・無し値
	 */
	public Location getCountLocation(final String locationCd) {

		// 引数のロケーションコードを使用し、組織マスタの検索を行い
		checkParams(locationCd);
		Location bean = locationDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * ログインユーザ定義マスタ存在チェック用
	 * @param tantoCd 担当者コード
	 * @return ある・無し値
	 */
	public int getCountLogin(final String tantoCd) {

		// 引数のロケーションコードを使用し、組織マスタの検索を行い
		checkParams(tantoCd);
		Login bean = loginDao.getEntity(tantoCd);
		if (bean == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 仕入先別単価マスタ存在チェック用
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return ある・無し値
	 */
	public int getCountUnitprice(final String itemCd, final String venderCd) {

		// 引数の品目コード、仕入先コードを使用し、仕入先別単価マスタの検索を行い
		// 検索結果件数を取得する
		int count = purchaseDetailDao.getCountUnitprice(itemCd, venderCd);

		return count;
	}

	/**
	 * パラメータチェック.Stringバージョン
	 * @param cd 検索条件:LOCATION
	 * @throws IllegalArgumentException
	 */
	private void checkParams(final String cd) throws IllegalArgumentException {

		if (StringUtils.isEmpty(cd)) {
			throw new IllegalArgumentException(
					"BelongDetailLogic IllegalArgumentException : Paramater is empty checkParams(cd).");
		}
	}

	/**
	 * 備考マスタ検索処理
	 * 
	 * @param venderCd 検索条件
	 * @param deliveryCd 検索条件
	 * @param itemCd 検索条件
	 * @return List<PurchaseRemarkList>備考マスタ 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PurchaseRemarkList> getRemarkList(final String venderCd,
			final String deliveryCd, final String itemCd)
			throws NoDataException {

		List<PurchaseRemarkList> list = purchaseRemarkListDao.getRemarkList(
			venderCd, deliveryCd, itemCd);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 購買ステータスの更新処理を行う
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無し例外
	 */
	public void updateStatus(final PurchaseSubcontract bean)
			throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		checkParams(bean.getPurchaseNo());

		bean.setOrderSheeFlag(ORDER_SHEET_FLG); // 発注書発行フラグ(0)
		bean.setReplyContentsDivision(REPLY_CONTENT_DIVISION); // 分納区分(0)
		bean.setLabelFlag(LABEL_FLG); // ラベル発行フラグ(0)

		try {
			StockinoutForPurchase stock = new StockinoutForPurchase(zaiCtrlDao);
			String errMsg = "errors.purchase.stock.update";
			try {
				/* 在庫更新－発注確定取消 */
				if (!stock.defixPurchase(bean.getPurchaseNo(), bean
						.getUpdatorCd())) {
					throw new LogicExceptionEx(errMsg);
				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

			/* 更新処理 null値以外の項目を更新 */
			int updateNum = purchaseSubcontractDao.updateUnlessNull(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public PurchaseDetail getItemRelatedData(final String itemCd)
			throws NoDataException {

		PurchaseDetail bean = purchaseDetailDao.getItemRelatedData(itemCd);

		return bean;
	}

	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public PurchaseDetail getVenderRelatedData(final String itemCd,
			final String venderCd, final BigDecimal orderQuantity)
			throws NoDataException {

		PurchaseDetail bean = purchaseDetailDao.getVenderRelatedData(itemCd,
			venderCd, orderQuantity);

		return bean;
	}

	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public PurchaseDetail getOrderQuantityRelatedData(final String itemCd,
			final String venderCd, final BigDecimal orderQuantity)
			throws NoDataException {

		PurchaseDetail bean = purchaseDetailDao.getOrderQuantityRelatedData(
			itemCd, venderCd, orderQuantity);

		if (bean != null) {
			// 発注単価
			bean.setStrOrderUnitprice(checker.format("SITANKA", "SI", bean
					.getVenderCd(), bean.getOrderUnitprice()));
		}

		return bean;
	}

	/**
	 * 所属コード存在チェック用
	 * @param organizationCd 部署コード
	 * @param tantoCd 担当者コード
	 * @return 登録件数
	 */
	public int getCountBelong(final String organizationCd, final String tantoCd) {
		return belongDetailDao.getCountBelong(organizationCd, tantoCd);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注詳細画面用Daoを設定します。
	 * @param purchaseDetailDao 発注詳細画面用Dao
	 */
	public void setPurchaseDetailDao(final PurchaseDetailDao purchaseDetailDao) {
		this.purchaseDetailDao = purchaseDetailDao;
	}

	/**
	 * 購買オーダーテーブル更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買オーダーテーブル更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 備考取得用Daoを設定します。
	 * @param purchaseRemarkListDao 備考取得用Dao
	 */
	public void setPurchaseRemarkListDao(
			final PurchaseRemarkListDao purchaseRemarkListDao) {
		this.purchaseRemarkListDao = purchaseRemarkListDao;
	}

	/**
	 * 品目用Daoを設定します。
	 * @param itemDao 品目取得用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 仕入先用Daoを設定します。
	 * @param venderDao 仕入先取得用Dao
	 */
	public void setVenderDao(final VenderDao venderDao) {
		this.venderDao = venderDao;
	}

	/**
	 * 部署取得用Daoを設定します。
	 * @param organizationDao 部署取得用Dao
	 */
	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	/**
	 * 納入先取得用Daoを設定します。
	 * @param deliveryDao 納入先取得用Dao
	 */
	public void setDeliveryDao(final DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	/**
	 * ロケーション取得用Daoを設定します。
	 * @param locationDao ロケーション取得用Dao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * 担当者取得用Daoを設定します。
	 * @param loginDao 担当者取得用Dao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * belongDaoを設定します。
	 * @param belongDetailDao belongDetailDao
	 */
	public void setBelongDetailDao(final BelongDetailDao belongDetailDao) {
		this.belongDetailDao = belongDetailDao;
	}
}
