/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.changehistory.ChangeHistory;
import com.asahikaseieng.dao.entity.master.changehistory.ChangeHistoryDao;
import com.asahikaseieng.dao.entity.master.commonattributequeue.CommonAttributeQueue;
import com.asahikaseieng.dao.entity.master.commonattributequeue.CommonAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueue;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueueDao;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueue;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetailDao;
import com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail.ArticleAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail.ArticleAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetailDao;
import com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail.CommonAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail.CommonAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetailDao;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActive;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActiveDao;
import com.asahikaseieng.dao.nonentity.master.itemupdate.ItemUpdateDao;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetailDao;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetailDao;
import com.asahikaseieng.dao.nonentity.master.productattributequeuedetail.ProductAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.productattributequeuedetail.ProductAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.UpdateItemPriceCallDto;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 品目在庫・単価ロジック 実装クラス.
 * @author t0011036
 */
public class ItemAttributeLogicImpl implements ItemAttributeLogic {

	private ItemQueueDao itemQueueDao;

	private ItemDetailDao itemDetailDao;

	private CommonAttributeQueueDao commonAttributeQueueDao;

	private ArticleAttributeQueueDao articleAttributeQueueDao;

	private ProductAttributeQueueDao productAttributeQueueDao;

	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;

	private ChangeHistoryDao changeHistoryDao;

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private CommonAttributeQueueDetailDao commonAttributeQueueDetailDao;

	private ProductAttributeQueueDetailDao productAttributeQueueDetailDao;

	private ArticleAttributeQueueDetailDao articleAttributeQueueDetailDao;

	private PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao;

	private LineDetailDao lineDetailDao;

	private OrganizationDetailDao organizationDetailDao;

	private BumonDetailDao bumonDetailDao;

	private AccountsDetailDao accountsDetailDao;

	private FinancialClassDetailDao financialClassDetailDao;

	private VenderDetailDao venderDetailDao;

	private DeliveryDetailDao deliveryDetailDao;

	private ItemDao itemDao;

	private ItemQueueLastActiveDao itemQueueLastActiveDao;

	private ProcedureCallDao procedureCallDao;

	private ItemUpdateDao itemUpdateDao;

	/**
	 * コンストラクタ.
	 */
	public ItemAttributeLogicImpl() {
	}

	/**
	 * 生産工場№検索
	 * @param productionLine 生産ラインコード
	 * @return LineDetail
	 */
	public LineDetail getLineEntity(final String productionLine) {
		LineDetail bean = lineDetailDao.getEntity(productionLine);
		return bean;
	}

	/**
	 * 原価部門検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	public OrganizationDetail getOrganizationEntity(final String organizationCd) {
		OrganizationDetail bean = organizationDetailDao
				.getEntity(organizationCd);
		return bean;
	}

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	public BumonDetail getBumonEntity(final String sectionCd) {
		BumonDetail bean = bumonDetailDao.getEntity(sectionCd);
		return bean;
	}

	/**
	 * 勘定科目検索
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	public AccountsDetail getAccountsEntity(final String accountsCd) {
		AccountsDetail bean = accountsDetailDao.getEntity(accountsCd);
		return bean;
	}

	/**
	 * 財務分類検索
	 * @param financialClassCd 財務分類コード
	 * @return FinancialClassDetail
	 */
	public FinancialClassDetail getFinancialClassEntity(
			final String financialClassCd) {
		FinancialClassDetail bean = financialClassDetailDao
				.getEntity(financialClassCd);
		return bean;
	}

	/**
	 * 基準仕入先検索
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	public VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd) {
		VenderDetail bean = venderDetailDao.getEntity(venderDivision, venderCd);
		return bean;
	}

	/**
	 * 納入先検索
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	public DeliveryDetail getDeliveryEntity(final String deliveryCd) {
		DeliveryDetail bean = deliveryDetailDao.getEntity(deliveryCd);
		return bean;
	}

	/**
	 * 品目検索（有効品目確認用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemDetail
	 */
	public ItemDetail getItemDetailEntity(final String itemCd,
			final BigDecimal version) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 共通情報検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return CommonAttributeQueue
	 */
	public CommonAttributeQueue getCommonEntity(final String itemCd,
			final BigDecimal version) {
		CommonAttributeQueue bean = commonAttributeQueueDao.getEntity(itemCd,
			version);
		return bean;
	}

	/**
	 * 製造品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ProductAttributeQueue
	 */
	public ProductAttributeQueue getProductEntity(final String itemCd,
			final BigDecimal version) {
		ProductAttributeQueue bean = productAttributeQueueDao.getEntity(itemCd,
			version);
		return bean;
	}

	/**
	 * 販売品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ArticleAttributeQueue
	 */
	public ArticleAttributeQueue getArticleEntity(final String itemCd,
			final BigDecimal version) {
		ArticleAttributeQueue bean = articleAttributeQueueDao.getEntity(itemCd,
			version);
		return bean;
	}

	/**
	 * 購入品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueue
	 */
	public PurchaseAttributeQueue getPurchaseEntity(final String itemCd,
			final BigDecimal version) {
		PurchaseAttributeQueue bean = purchaseAttributeQueueDao.getEntity(
			itemCd, version);
		return bean;
	}

	/**
	 * 共通情報検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return CommonAttributeQueueDetail
	 */
	public CommonAttributeQueueDetail getCommonDetailEntity(
			final String itemCd, final BigDecimal version) {
		CommonAttributeQueueDetail bean = commonAttributeQueueDetailDao
				.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 製造品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ProductAttributeQueueDetail
	 */
	public ProductAttributeQueueDetail getProductDetailEntity(
			final String itemCd, final BigDecimal version) {
		ProductAttributeQueueDetail bean = productAttributeQueueDetailDao
				.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 販売品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ArticleAttributeQueueDetail
	 */
	public ArticleAttributeQueueDetail getArticleDetailEntity(
			final String itemCd, final BigDecimal version) {
		ArticleAttributeQueueDetail bean = articleAttributeQueueDetailDao
				.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 購入品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueueDetail
	 */
	public PurchaseAttributeQueueDetail getPurchaseDetailEntity(
			final String itemCd, final BigDecimal version) {
		PurchaseAttributeQueueDetail bean = purchaseAttributeQueueDetailDao
				.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 品目検索（ヘッダー用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	public ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version) {
		ItemQueueHeader bean = itemQueueHeaderDao.getEntity(itemCd, version);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final ItemQueue bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			itemQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * ステータス更新登録
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 */
	public void updateStatus(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase) {
		/* 販売品登録 */
		if (!articleDivision.equals(new BigDecimal("0"))) {
			articleAttributeUpdate(beanArticle);
		}

		/* 製造品登録 */
		if (!productDivision.equals(new BigDecimal("0"))) {
			productAttributeUpdate(beanProduct);
		}

		/* 購入品登録 */
		if (!purchaseDivision.equals(new BigDecimal("0"))) {
			purchaseAttributeUpdate(beanPurchase);
		}
	}

	/**
	 * 承認登録
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void approvalUpdate(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase)
			throws IllegalAccessException, InvocationTargetException {
		String itemCd = null;

		/* 販売品登録 */
		if (!articleDivision.equals(new BigDecimal("0"))) {
			articleAttributeUpdate(beanArticle);
			itemCd = beanArticle.getItemCd();
		}

		/* 製造品登録 */
		if (!productDivision.equals(new BigDecimal("0"))) {
			productAttributeUpdate(beanProduct);
			itemCd = beanProduct.getItemCd();
		}

		/* 購入品登録 */
		if (!purchaseDivision.equals(new BigDecimal("0"))) {
			purchaseAttributeUpdate(beanPurchase);
			itemCd = beanPurchase.getItemCd();
		}

		/* 有効品目検索 */
		ItemQueueLastActive beanItemQueue = getLastActiveItemQueueEntity(itemCd);

		/* 有効品目がない場合はITEMから削除する */
		if (beanItemQueue == null) {
			/* 品目検索 */
			Item beanItem = itemDao.getEntity(itemCd);

			if (beanItem != null) {
				/* 削除処理 */
				itemDao.delete(beanItem);
			}
		} else {
			/* 基本＆在庫・単価が承認された場合ITEM作成する */
			if (beanItemQueue.getStatus().equals(new BigDecimal("3"))) {
				/* 品目検索 */
				Item beanDeleteItem = itemDao.getEntity(beanItemQueue
						.getItemCd());

				if (beanDeleteItem != null) {
					/* 削除処理 */
					itemDao.delete(beanDeleteItem);
				}

				/* 更新処理 */
				itemUpdateDao.update(beanItemQueue.getItemCd(), beanItemQueue
						.getVersion());
			}
		}
	}

	/**
	 * 否認・承認取消処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @param activeDate 有効開始日
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 * @param activate true:有効品目
	 */
	public void approvalCancelUpdate(final String itemCd,
			final BigDecimal version, final Timestamp activeDate,
			final BigDecimal productDivision, final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase, final Boolean activate) {

		/* 販売品登録 */
		if (!articleDivision.equals(new BigDecimal("0"))) {
			articleAttributeUpdate(beanArticle);
		}

		/* 製造品登録 */
		if (!productDivision.equals(new BigDecimal("0"))) {
			productAttributeUpdate(beanProduct);
		}

		/* 購入品登録 */
		if (!purchaseDivision.equals(new BigDecimal("0"))) {
			purchaseAttributeUpdate(beanPurchase);
		}

		/* 品目検索 */
		Item beanItem = itemDao.getEntity(itemCd);

		if (beanItem != null) {
			/* 編集中の品目が有効品目の場合は有効品目を削除する */
			if (beanItem.getVersion().compareTo(version) == 0) {
				/* 削除処理 */
				itemDao.delete(beanItem);
			}
		}

		/* 最新有効品目検索 */
		ItemQueueLastActive beanItemQueue = getLastActiveItemQueueEntity(itemCd);

		if (beanItemQueue != null) {
			/* 編集中の品目よりも新しい有効開始日の有効品目があった場合 */
			if (activeDate.compareTo(beanItemQueue.getActiveDate()) < 0) {
				/* 最新有効品目検索 */
				Item beanDeleteItem = itemDao.getEntity(beanItemQueue
						.getItemCd());

				if (beanDeleteItem != null) {
					/* 最新有効品目を削除 */
					itemDao.delete(beanDeleteItem);
				}

				/* 最新有効品目追加 */
				itemUpdateDao.update(beanItemQueue.getItemCd(), beanItemQueue
						.getVersion());
			}
		}
	}

	/**
	 * 登録処理を行う
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanCommon 追加対象データ(共通情報)
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 * @param reason 更新理由
	 */
	public void regist(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final CommonAttributeQueue beanCommon,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase, final String reason) {
		if (beanCommon == null) {
			throw new IllegalArgumentException("beanCommon == null");
		}

		/* 共通追加登録 */
		if (beanCommon.getUpdateDate() == null) {
			commonAttributeInsert(beanCommon);
		} else {
			commonAttributeUpdate(beanCommon);
		}

		/* 販売品登録 */
		if (!articleDivision.equals(new BigDecimal("0"))) {
			if (beanArticle.getUpdateDate() == null) {
				articleAttributeInsert(beanArticle);
			} else {
				articleAttributeUpdate(beanArticle);
			}
		}

		/* 製造品登録 */
		if (!productDivision.equals(new BigDecimal("0"))) {
			if (beanProduct.getUpdateDate() == null) {
				productAttributeInsert(beanProduct);
			} else {
				productAttributeUpdate(beanProduct);
			}
		}

		/* 購入品登録 */
		if (!purchaseDivision.equals(new BigDecimal("0"))) {
			if (beanPurchase.getUpdateDate() == null) {
				purchaseAttributeInsert(beanPurchase);
			} else {
				purchaseAttributeUpdate(beanPurchase);
			}
		}

		/* 更新履歴追加登録 */
		historyInsert(beanCommon, reason);
	}

	/**
	 * 共通追加登録
	 * @param bean 登録データ
	 */
	public void commonAttributeInsert(final CommonAttributeQueue bean) {
		try {
			/* 追加処理 */
			commonAttributeQueueDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 共通更新登録
	 * @param bean 登録データ
	 */
	public void commonAttributeUpdate(final CommonAttributeQueue bean) {
		try {
			/* 更新処理 */
			commonAttributeQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 販売品追加登録
	 * @param bean 登録データ
	 */
	public void articleAttributeInsert(final ArticleAttributeQueue bean) {
		try {
			/* 追加処理 */
			articleAttributeQueueDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 販売品更新登録
	 * @param bean 登録データ
	 */
	public void articleAttributeUpdate(final ArticleAttributeQueue bean) {
		try {
			/* 更新処理 */
			articleAttributeQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 製造品追加登録
	 * @param bean 登録データ
	 */
	public void productAttributeInsert(final ProductAttributeQueue bean) {
		try {
			/* 追加処理 */
			productAttributeQueueDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 製造品更新登録
	 * @param bean 登録データ
	 */
	public void productAttributeUpdate(final ProductAttributeQueue bean) {
		try {
			/* 更新処理 */
			productAttributeQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 購入品追加登録
	 * @param bean 登録データ
	 */
	public void purchaseAttributeInsert(final PurchaseAttributeQueue bean) {
		try {
			/* 追加処理 */
			purchaseAttributeQueueDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 購入品更新登録
	 * @param bean 登録データ
	 */
	public void purchaseAttributeUpdate(final PurchaseAttributeQueue bean) {
		try {
			/* 更新処理 */
			purchaseAttributeQueueDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 更新履歴追加登録
	 * @param bean 登録データ
	 * @param reason 更新理由
	 */
	public void historyInsert(final CommonAttributeQueue bean,
			final String reason) {
		try {
			ChangeHistory beanHistory = new ChangeHistory();

			beanHistory.setMenuId(new BigDecimal("60"));
			beanHistory.setItemCd(bean.getItemCd());
			beanHistory.setReason(reason);
			beanHistory.setInputDate(beanHistory.getCurrentTimestamp());
			beanHistory.setInputorCd(bean.getUpdatorCd());
			beanHistory.setUpdatorCd(bean.getUpdatorCd());

			/* 追加処理 */
			changeHistoryDao.insert(beanHistory);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 品目検索（登録用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueue
	 * @throws NoDataException NoDataException
	 */
	public ItemQueue getEntity(final String itemCd, final BigDecimal version)
			throws NoDataException {
		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		ItemQueue bean = itemQueueDao.getEntity(itemCd, version);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 有効品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastActive
	 */
	public ItemQueueLastActive getLastActiveItemQueueEntity(final String itemCd) {
		if (StringUtils.isEmpty(itemCd)) {
			throw new IllegalArgumentException("itemCd is empty");
		}

		ItemQueueLastActive bean = itemQueueLastActiveDao.getEntity(itemCd);

		return bean;
	}

	/**
	 * 標準販売単価更新
	 * @param itemCd 品目コード
	 * @param price 標準販売単価
	 * @param inputDate 見積変更開始基準日
	 * @param tantoCd 担当者コード
	 */
	public void updatePrice(final String itemCd, final BigDecimal price,
			final Timestamp inputDate, final String tantoCd) {
		UpdateItemPriceCallDto dto = new UpdateItemPriceCallDto();

		dto.setPDatStandardDate(inputDate);
		dto.setPStrItemCd(itemCd);
		dto.setPStrPrice(price.toString());
		dto.setPStrTantoCd(tantoCd);

		try {
			procedureCallDao.updateItemPrice(dto);

			if (dto.getPStrErrorReturnCd() != null) {
				throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
			}
		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(dto.getPStrErrorReturnMsg());
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemQueueDaoを設定します。
	 * @param itemQueueDao itemQueueDao
	 */
	public void setItemQueueDao(final ItemQueueDao itemQueueDao) {
		this.itemQueueDao = itemQueueDao;
	}

	/**
	 * itemQueueHeaderDaoを設定します。
	 * @param itemQueueHeaderDao itemQueueHeaderDao
	 */
	public void setItemQueueHeaderDao(
			final ItemQueueHeaderDao itemQueueHeaderDao) {
		this.itemQueueHeaderDao = itemQueueHeaderDao;
	}

	/**
	 * articleAttributeQueueDaoを設定します。
	 * @param articleAttributeQueueDao articleAttributeQueueDao
	 */
	public void setArticleAttributeQueueDao(
			final ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

	/**
	 * commonAttributeQueueDaoを設定します。
	 * @param commonAttributeQueueDao commonAttributeQueueDao
	 */
	public void setCommonAttributeQueueDao(
			final CommonAttributeQueueDao commonAttributeQueueDao) {
		this.commonAttributeQueueDao = commonAttributeQueueDao;
	}

	/**
	 * productAttributeQueueDaoを設定します。
	 * @param productAttributeQueueDao productAttributeQueueDao
	 */
	public void setProductAttributeQueueDao(
			final ProductAttributeQueueDao productAttributeQueueDao) {
		this.productAttributeQueueDao = productAttributeQueueDao;
	}

	/**
	 * purchaseAttributeQueueDaoを設定します。
	 * @param purchaseAttributeQueueDao purchaseAttributeQueueDao
	 */
	public void setPurchaseAttributeQueueDao(
			final PurchaseAttributeQueueDao purchaseAttributeQueueDao) {
		this.purchaseAttributeQueueDao = purchaseAttributeQueueDao;
	}

	/**
	 * changeHistoryDaoを設定します。
	 * @param changeHistoryDao changeHistoryDao
	 */
	public void setChangeHistoryDao(final ChangeHistoryDao changeHistoryDao) {
		this.changeHistoryDao = changeHistoryDao;
	}

	/**
	 * commonAttributeQueueDetailDaoを設定します。
	 * @param commonAttributeQueueDetailDao commonAttributeQueueDetailDao
	 */
	public void setCommonAttributeQueueDetailDao(
			final CommonAttributeQueueDetailDao commonAttributeQueueDetailDao) {
		this.commonAttributeQueueDetailDao = commonAttributeQueueDetailDao;
	}

	/**
	 * articleAttributeQueueDetailDaoを設定します。
	 * @param articleAttributeQueueDetailDao articleAttributeQueueDetailDao
	 */
	public void setArticleAttributeQueueDetailDao(
			final ArticleAttributeQueueDetailDao articleAttributeQueueDetailDao) {
		this.articleAttributeQueueDetailDao = articleAttributeQueueDetailDao;
	}

	/**
	 * productAttributeQueueDetailDaoを設定します。
	 * @param productAttributeQueueDetailDao productAttributeQueueDetailDao
	 */
	public void setProductAttributeQueueDetailDao(
			final ProductAttributeQueueDetailDao productAttributeQueueDetailDao) {
		this.productAttributeQueueDetailDao = productAttributeQueueDetailDao;
	}

	/**
	 * purchaseAttributeQueueDetailDaoを設定します。
	 * @param purchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao
	 */
	public void setPurchaseAttributeQueueDetailDao(
			final PurchaseAttributeQueueDetailDao purchaseAttributeQueueDetailDao) {
		this.purchaseAttributeQueueDetailDao = purchaseAttributeQueueDetailDao;
	}

	/**
	 * lineDetailDaoを設定します。
	 * @param lineDetailDao lineDetailDao
	 */
	public void setLineDetailDao(final LineDetailDao lineDetailDao) {
		this.lineDetailDao = lineDetailDao;
	}

	/**
	 * organizationDetailDaoを設定します。
	 * @param organizationDetailDao organizationDetailDao
	 */
	public void setOrganizationDetailDao(
			final OrganizationDetailDao organizationDetailDao) {
		this.organizationDetailDao = organizationDetailDao;
	}

	/**
	 * bumonDetailDaoを設定します。
	 * @param bumonDetailDao bumonDetailDao
	 */
	public void setBumonDetailDao(final BumonDetailDao bumonDetailDao) {
		this.bumonDetailDao = bumonDetailDao;
	}

	/**
	 * accountsDetailDaoを設定します。
	 * @param accountsDetailDao accountsDetailDao
	 */
	public void setAccountsDetailDao(final AccountsDetailDao accountsDetailDao) {
		this.accountsDetailDao = accountsDetailDao;
	}

	/**
	 * financialClassDetailDaoを設定します。
	 * @param financialClassDetailDao financialClassDetailDao
	 */
	public void setFinancialClassDetailDao(
			final FinancialClassDetailDao financialClassDetailDao) {
		this.financialClassDetailDao = financialClassDetailDao;
	}

	/**
	 * venderDetailDaoを設定します。
	 * @param venderDetailDao venderDetailDao
	 */
	public void setVenderDetailDao(final VenderDetailDao venderDetailDao) {
		this.venderDetailDao = venderDetailDao;
	}

	/**
	 * deliveryDetailDaoを設定します。
	 * @param deliveryDetailDao deliveryDetailDao
	 */
	public void setDeliveryDetailDao(final DeliveryDetailDao deliveryDetailDao) {
		this.deliveryDetailDao = deliveryDetailDao;
	}

	/**
	 * itemDaoを設定します。
	 * @param itemDao itemDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * itemQueueLastActiveDaoを設定します。
	 * @param itemQueueLastActiveDao itemQueueLastActiveDao
	 */
	public void setItemQueueLastActiveDao(
			final ItemQueueLastActiveDao itemQueueLastActiveDao) {
		this.itemQueueLastActiveDao = itemQueueLastActiveDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * itemUpdateDaoを設定します。
	 * @param itemUpdateDao itemUpdateDao
	 */
	public void setItemUpdateDao(final ItemUpdateDao itemUpdateDao) {
		this.itemUpdateDao = itemUpdateDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}
}
