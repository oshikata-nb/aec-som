/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.changehistory.ChangeHistory;
import com.asahikaseieng.dao.entity.master.changehistory.ChangeHistoryDao;
import com.asahikaseieng.dao.entity.master.commonattributequeue.CommonAttributeQueue;
import com.asahikaseieng.dao.entity.master.commonattributequeue.CommonAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueue;
import com.asahikaseieng.dao.entity.master.componentinformationqueue.ComponentInformationQueueDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueue;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueueDao;
import com.asahikaseieng.dao.entity.master.itemremark.ItemRemark;
import com.asahikaseieng.dao.entity.master.itemremark.ItemRemarkDao;
import com.asahikaseieng.dao.entity.master.label.Label;
import com.asahikaseieng.dao.entity.master.label.LabelDao;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueue;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueueDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory.ItemCategoryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory.ItemCategoryListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.articleattributequeuedelete.ArticleAttributeQueueDeleteDao;
import com.asahikaseieng.dao.nonentity.master.commonattributequeuedelete.CommonAttributeQueueDeleteDao;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist.ComponentInformationQueueDeleteListDao;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueList;
import com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist.ComponentInformationQueueListDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuedelete.ItemQueueDeleteDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeaderDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActive;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActiveDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd.ItemQueueNewItemCd;
import com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd.ItemQueueNewItemCdDao;
import com.asahikaseieng.dao.nonentity.master.itemremarkdelete.ItemRemarkDeleteDao;
import com.asahikaseieng.dao.nonentity.master.itemupdate.ItemUpdateDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.dao.nonentity.master.productattributequeuedelete.ProductAttributeQueueDeleteDao;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete.PurchaseAttributeQueueDeleteDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 品目基本ロジック 実装クラス.
 * @author t0011036
 */
public class ItemDetailLogicImpl implements ItemDetailLogic {

	private ItemQueueDao itemQueueDao;

	private ItemQueueDeleteDao itemQueueDeleteDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;

	private CommonAttributeQueueDao commonAttributeQueueDao;

	private ArticleAttributeQueueDao articleAttributeQueueDao;

	private ProductAttributeQueueDao productAttributeQueueDao;

	private PurchaseAttributeQueueDao purchaseAttributeQueueDao;

	private ComponentInformationQueueListDao componentInformationQueueListDao;

	private ComponentInformationQueueDeleteListDao componentInformationQueueDeleteListDao;

	private CommonAttributeQueueDeleteDao commonAttributeQueueDeleteDao;

	private ProductAttributeQueueDeleteDao productAttributeQueueDeleteDao;

	private ArticleAttributeQueueDeleteDao articleAttributeQueueDeleteDao;

	private PurchaseAttributeQueueDeleteDao purchaseAttributeQueueDeleteDao;

	private ItemRemarkDeleteDao itemRemarkDeleteDao;

	private ComponentInformationQueueDao componentInformationQueueDao;

	private ItemRemarkDao itemRemarkDao;

	private ChangeHistoryDao changeHistoryDao;

	private ItemQueueDetailDao itemQueueDetailDao;

	private ItemDetailDao itemDetailDao;

	private ItemQueueHeaderDao itemQueueHeaderDao;

	private LocationDetailDao locationDetailDao;

	private NamesListForComboboxesDao namesListForComboboxesDao;

	private ItemCategoryListForComboboxesDao itemCategoryListForComboboxesDao;

	private LabelDao labelDao;

	private ItemDao itemDao;

	private ItemQueueLastActiveDao itemQueueLastActiveDao;

	private ItemUpdateDao itemUpdateDao;

	private ItemQueueNewItemCdDao itemQueueNewItemCdDao;

	private static final int ITEM_CD_LEN = 6;

	/**
	 * コンストラクタ.
	 */
	public ItemDetailLogicImpl() {
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
	 * 品目検索（詳細用）
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueDetail
	 */
	public ItemQueueDetail getDetailEntity(final String itemCd,
			final BigDecimal version) {
		ItemQueueDetail bean = itemQueueDetailDao.getEntity(itemCd, version);
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
	 * 品目検索（バージョン用）
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getLastVersion(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
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
	 * ロケーション検索（詳細用）
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	public LocationDetail getLocationEntity(final String locationCd) {
		LocationDetail bean = locationDetailDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * 製造品扱い属性検索
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
	 * 販売品扱い属性検索
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
	 * 購入品扱い属性検索
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
	 * 最新有効品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastActive
	 */
	public ItemQueueLastActive getLastActiveItemQueueEntity(final String itemCd) {
		ItemQueueLastActive bean = itemQueueLastActiveDao.getEntity(itemCd);
		return bean;
	}

	/**
	 * 単位リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getUnitList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao
				.getListForComboboxes("UNIT");
		return list;
	}

	/**
	 * 品目分類リスト取得
	 * @return List<ItemCategoryListForComboboxes>
	 */
	public List<ItemCategoryListForComboboxes> getItemCategoryList() {
		List<ItemCategoryListForComboboxes> list = itemCategoryListForComboboxesDao
				.getListForComboboxes();
		return list;
	}
	
	//20190819 軽減税率対応 
	/**
	 * 課税区分リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<NamesListForComboboxes> getTaxCategoryList() {
		List<NamesListForComboboxes> list = namesListForComboboxesDao.getListForComboboxes("TAXD");
		return list;
	}
	//20190819 軽減税率対応  end

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void itemQueueUpdate(final ItemQueue bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 削除処理 */
		itemQueueDeleteDao.delete(bean.getItemCd(), bean.getVersion());

		try {
			/* 追加処理 */
			itemQueueDao.insert(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 承認登録
	 * @param bean 登録データ
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	public void approvalUpdate(final ItemQueue bean)
			throws IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			itemQueueDao.update(bean);

			BigDecimal productStatus = new BigDecimal("1");
			BigDecimal articleStatus = new BigDecimal("1");
			BigDecimal purchaseStatus = new BigDecimal("1");

			/* 有効品目検索 */
			ItemQueueLastActive beanItemQueue = getLastActiveItemQueueEntity(bean
					.getItemCd());

			/* 有効品目がない場合はITEMから削除する */
			if (beanItemQueue == null) {
				/* 品目検索 */
				Item beanItem = itemDao.getEntity(bean.getItemCd());

				if (beanItem != null) {
					/* 削除処理 */
					itemDao.delete(beanItem);
				}
			} else {
				if (!beanItemQueue.getProductDivision().equals(
					new BigDecimal("0"))) {
					ProductAttributeQueue beanProduct = getProductEntity(bean
							.getItemCd(), bean.getVersion());

					if (beanProduct != null) {
						productStatus = beanProduct.getStatus();
					}
				} else {
					productStatus = new BigDecimal("3");
				}

				if (!beanItemQueue.getArticleDivision().equals(
					new BigDecimal("0"))) {
					ArticleAttributeQueue beanArticle = getArticleEntity(bean
							.getItemCd(), bean.getVersion());

					if (beanArticle != null) {
						articleStatus = beanArticle.getStatus();
					}
				} else {
					articleStatus = new BigDecimal("3");
				}

				if (!beanItemQueue.getPurchaseDivision().equals(
					new BigDecimal("0"))) {
					PurchaseAttributeQueue beanPurchase = getPurchaseEntity(
						bean.getItemCd(), bean.getVersion());

					if (beanPurchase != null) {
						purchaseStatus = beanPurchase.getStatus();
					}
				} else {
					purchaseStatus = new BigDecimal("3");
				}

				/* 基本＆在庫・単価が承認された場合ITEM作成する */
				if (productStatus.equals(new BigDecimal("3"))
						&& articleStatus.equals(new BigDecimal("3"))
						&& purchaseStatus.equals(new BigDecimal("3"))) {
					/* 削除品目検索 */
					Item beanDeleteItem = itemDao.getEntity(beanItemQueue
							.getItemCd());

					if (beanDeleteItem != null) {
						/* 削除処理 */
						itemDao.delete(beanDeleteItem);
					}

					/* 更新処理 */
					itemUpdateDao.update(beanItemQueue.getItemCd(),
						beanItemQueue.getVersion());
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 否認・承認取消処理を行う.
	 * @param bean 登録データ
	 * @param activate true:有効品目
	 */
	public void approvalCancelUpdate(final ItemQueue bean,
			final Boolean activate) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			itemQueueDao.update(bean);

			/* 品目検索 */
			Item beanItem = itemDao.getEntity(bean.getItemCd());

			if (beanItem != null) {
				/* 編集中の品目が有効品目の場合は有効品目を削除する */
				if (beanItem.getVersion().compareTo(bean.getVersion()) == 0) {
					/* 削除処理 */
					itemDao.delete(beanItem);
				}
			}

			/* 最新有効品目検索 */
			ItemQueueLastActive beanItemQueue = getLastActiveItemQueueEntity(bean
					.getItemCd());

			if (beanItemQueue != null) {
				/* 編集中の品目よりも新しい有効開始日の有効品目があった場合 */
				if (bean.getActiveDate().compareTo(
					beanItemQueue.getActiveDate()) < 0) {
					/* 削除品目検索 */
					Item beanDeleteItem = itemDao.getEntity(beanItemQueue
							.getItemCd());

					if (beanDeleteItem != null) {
						/* 削除処理 */
						itemDao.delete(beanDeleteItem);
					}

					/* 最新有効品目追加 */
					itemUpdateDao.update(beanItemQueue.getItemCd(),
						beanItemQueue.getVersion());
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 * @param reason 更新理由
	 * @param insertFlg 新規フラグ
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void insert(final ItemQueue bean, final String reason,
			final String insertFlg, final String copyFlg,
			final String copyItemCd, final BigDecimal copyVersion) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 品目追加登録 */
		itemQueueInsert(bean);

		BigDecimal prevVersion = bean.getVersion()
				.subtract(new BigDecimal("1"));

		/* 新規登録時の対応 */
		if (prevVersion.equals(new BigDecimal("0"))) {
			prevVersion = new BigDecimal("1");
		}

		/* 共通追加登録 */
		commonAttributeInsert(bean, prevVersion, copyFlg, copyItemCd,
			copyVersion);

		/* 販売品追加登録 */
		articleAttributeInsert(bean, prevVersion, insertFlg, copyFlg,
			copyItemCd, copyVersion);

		/* 製造品追加登録 */
		productAttributeInsert(bean, prevVersion, insertFlg, copyFlg,
			copyItemCd, copyVersion);

		/* 購入品追加登録 */
		purchaseAttributeInsert(bean, prevVersion, insertFlg, copyFlg,
			copyItemCd, copyVersion);

		/* 成分情報追加登録 */
		componentInformationInsert(bean, prevVersion, copyFlg, copyItemCd,
			copyVersion);

		/* 技術追加登録 */
		if (!StringUtils.isEmpty(copyFlg)) {
			if (copyFlg.equals("true")) {
				labelInsert(bean, copyItemCd);
			}
		}

		/* その他追加登録 */
		remarkInsert(bean, copyFlg, prevVersion, copyItemCd, copyVersion);

		/* 更新履歴追加登録 */
		historyInsert(bean, reason);
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 * @param reason 更新理由
	 */
	public void update(final ItemQueue bean, final String reason) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		/* 品目更新登録 */
		itemQueueUpdate(bean);

		/* 更新履歴追加登録 */
		historyInsert(bean, reason);
	}

	/**
	 * 品目追加登録
	 * @param bean 登録データ
	 */
	public void itemQueueInsert(final ItemQueue bean) {
		try {
			/* 追加処理 */
			itemQueueDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 共通追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void commonAttributeInsert(final ItemQueue bean,
			final BigDecimal prevVersion, final String copyFlg,
			final String copyItemCd, final BigDecimal copyVersion) {
		try {
			CommonAttributeQueue beanCommon = new CommonAttributeQueue();

			/* 共通検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				beanCommon = commonAttributeQueueDao.getEntity(
					bean.getItemCd(), prevVersion);
			} else {
				beanCommon = commonAttributeQueueDao.getEntity(copyItemCd,
					copyVersion);

				if (beanCommon == null) {
					beanCommon = new CommonAttributeQueue();

					beanCommon.setExpireMonths(new BigDecimal("0"));
					beanCommon.setContractMonths(new BigDecimal("0"));
				}
			}

			if (beanCommon != null) {
				beanCommon.setItemCd(bean.getItemCd());
				beanCommon.setVersion(bean.getVersion());
				beanCommon.setInputDate(beanCommon.getCurrentTimestamp());
				beanCommon.setInputorCd(bean.getUpdatorCd());
				beanCommon.setUpdatorCd(bean.getUpdatorCd());

				/* 追加処理 */
				commonAttributeQueueDao.insert(beanCommon);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 販売品追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param insertFlg 新規フラグ
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void articleAttributeInsert(final ItemQueue bean,
			final BigDecimal prevVersion, final String insertFlg,
			final String copyFlg, final String copyItemCd,
			final BigDecimal copyVersion) {
		try {
			ArticleAttributeQueue beanArticle = new ArticleAttributeQueue();

			/* 販売品検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				beanArticle = articleAttributeQueueDao.getEntity(bean
						.getItemCd(), prevVersion);
			} else {
				beanArticle = articleAttributeQueueDao.getEntity(copyItemCd,
					copyVersion);

				/* 販売品判定 */
				if (checkTypeArticle(bean.getTypeDivision())) {
					/* 販売品なのに販売品レコードがない場合は新規作成 */
					if (beanArticle == null) {
						beanArticle = new ArticleAttributeQueue();

						beanArticle.setTaxDivision(new BigDecimal("1"));
						beanArticle.setPriceCalcDivision(new BigDecimal("1"));
						beanArticle.setPaletteProducts(new BigDecimal("0"));
						beanArticle.setKeepDivision(new BigDecimal("1"));
					}
				} else {
					beanArticle = null;
				}
			}

			if (beanArticle != null) {
				beanArticle.setItemCd(bean.getItemCd());
				beanArticle.setVersion(bean.getVersion());
				beanArticle.setInputDate(beanArticle.getCurrentTimestamp());
				beanArticle.setInputorCd(bean.getUpdatorCd());
				beanArticle.setUpdatorCd(bean.getUpdatorCd());

				if (insertFlg.equals("versionup")) {
					beanArticle.setStatus(new BigDecimal("1"));
				} else {
					/* 研究用 */
					if (bean.getResearchItem().equals(new BigDecimal("2"))) {
						beanArticle.setStatus(new BigDecimal("4"));
					} else {
						beanArticle.setStatus(new BigDecimal("1"));
					}
				}

				/* 追加処理 */
				articleAttributeQueueDao.insert(beanArticle);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 製造品追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param insertFlg 新規フラグ
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void productAttributeInsert(final ItemQueue bean,
			final BigDecimal prevVersion, final String insertFlg,
			final String copyFlg, final String copyItemCd,
			final BigDecimal copyVersion) {
		try {
			ProductAttributeQueue beanProduct = new ProductAttributeQueue();

			/* 製造品検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				beanProduct = productAttributeQueueDao.getEntity(bean
						.getItemCd(), prevVersion);
			} else {
				beanProduct = productAttributeQueueDao.getEntity(copyItemCd,
					copyVersion);

				/* 製造品判定 */
				if (checkTypeProduct(bean.getTypeDivision())) {
					/* 製造品なのに製造品レコードがない場合は新規作成 */
					if (beanProduct == null) {
						beanProduct = new ProductAttributeQueue();

						beanProduct.setDailyProduction(new BigDecimal("0"));
						beanProduct.setServerDivision(new BigDecimal("0"));
						beanProduct.setProductionCycle(new BigDecimal("0"));
						beanProduct.setEnphasisDivision(new BigDecimal("0"));
						beanProduct.setCockDivision(new BigDecimal("0"));
						beanProduct.setPlanFlg(new BigDecimal("0"));
						beanProduct.setSerialnoFlg(new BigDecimal("0"));
						beanProduct.setProductionLeadTime(new BigDecimal("0"));
						beanProduct.setSafetyLeadTime(new BigDecimal("0"));
						beanProduct.setOrderPattern(new BigDecimal("0"));
						beanProduct.setInspectionDays(new BigDecimal("0"));
						beanProduct.setOrderPoint(new BigDecimal("0"));
					}
				} else {
					beanProduct = null;
				}
			}

			if (beanProduct != null) {
				beanProduct.setItemCd(bean.getItemCd());
				beanProduct.setVersion(bean.getVersion());
				beanProduct.setInputDate(beanProduct.getCurrentTimestamp());
				beanProduct.setInputorCd(bean.getUpdatorCd());
				beanProduct.setUpdatorCd(bean.getUpdatorCd());

				if (insertFlg.equals("versionup")) {
					beanProduct.setStatus(new BigDecimal("1"));
				} else {
					/* 研究用 */
					if (bean.getResearchItem().equals(new BigDecimal("2"))) {
						beanProduct.setStatus(new BigDecimal("4"));
					} else {
						beanProduct.setStatus(new BigDecimal("1"));
					}
				}

				/* 追加処理 */
				productAttributeQueueDao.insert(beanProduct);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 購入品追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param insertFlg 新規フラグ
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void purchaseAttributeInsert(final ItemQueue bean,
			final BigDecimal prevVersion, final String insertFlg,
			final String copyFlg, final String copyItemCd,
			final BigDecimal copyVersion) {
		try {
			PurchaseAttributeQueue beanPurchase = new PurchaseAttributeQueue();

			/* 購入品検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				beanPurchase = purchaseAttributeQueueDao.getEntity(bean
						.getItemCd(), prevVersion);
			} else {
				beanPurchase = purchaseAttributeQueueDao.getEntity(copyItemCd,
					copyVersion);

				/* 購入品判定 */
				if (checkTypePurchase(bean.getTypeDivision())) {
					/* 購入品なのに購入品レコードがない場合は新規作成 */
					if (beanPurchase == null) {
						beanPurchase = new PurchaseAttributeQueue();

						beanPurchase.setPriceCalcDivision(new BigDecimal("1"));
						beanPurchase.setTaxDivision(new BigDecimal("1"));
						beanPurchase.setPurchaseTrigger(new BigDecimal("1"));
						beanPurchase.setMultiSupplierDivision(new BigDecimal(
								"1"));
						beanPurchase.setInspectionType(new BigDecimal("1"));
						beanPurchase.setSuppliedGoodsDivision("1");
						beanPurchase.setLeaseDrumFlag(new BigDecimal("1"));
						beanPurchase.setOrderPoint(new BigDecimal("0"));
						beanPurchase.setOrderQty(new BigDecimal("0"));
						beanPurchase.setLorryDivision(new BigDecimal("1"));
					}
				} else {
					beanPurchase = null;
				}
			}

			if (beanPurchase != null) {
				beanPurchase.setItemCd(bean.getItemCd());
				beanPurchase.setVersion(bean.getVersion());
				beanPurchase.setInputDate(beanPurchase.getCurrentTimestamp());
				beanPurchase.setInputorCd(bean.getUpdatorCd());
				beanPurchase.setUpdatorCd(bean.getUpdatorCd());

				if (insertFlg.equals("versionup")) {
					beanPurchase.setStatus(new BigDecimal("1"));
				} else {
					/* 研究用 */
					if (bean.getResearchItem().equals(new BigDecimal("2"))) {
						beanPurchase.setStatus(new BigDecimal("4"));
					} else {
						beanPurchase.setStatus(new BigDecimal("1"));
					}
				}

				/* 追加処理 */
				purchaseAttributeQueueDao.insert(beanPurchase);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 成分情報追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void componentInformationInsert(final ItemQueue bean,
			final BigDecimal prevVersion, final String copyFlg,
			final String copyItemCd, final BigDecimal copyVersion) {
		ComponentInformationQueue beanComponent = new ComponentInformationQueue();

		try {
			List<ComponentInformationQueueList> listComponent = new ArrayList<ComponentInformationQueueList>();

			/* 成分情報検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				listComponent = componentInformationQueueListDao.getList(bean
						.getItemCd(), prevVersion);
			} else {
				listComponent = componentInformationQueueListDao.getList(
					copyItemCd, copyVersion);
			}

			for (int i = 0; i < listComponent.size(); i++) {
				beanComponent.setItemCd(bean.getItemCd());
				beanComponent.setVersion(bean.getVersion());
				beanComponent.setIndicateOrder(listComponent.get(i)
						.getIndicateOrder());
				beanComponent.setComponentCd(listComponent.get(i)
						.getComponentCd());
				beanComponent.setCalcValue(listComponent.get(i).getCalcValue());
				beanComponent.setIndicateValue(listComponent.get(i)
						.getIndicateValue());
				beanComponent.setInputDate(beanComponent.getCurrentTimestamp());
				beanComponent.setInputorCd(bean.getUpdatorCd());
				beanComponent.setUpdatorCd(bean.getUpdatorCd());

				/* 追加処理 */
				componentInformationQueueDao.insert(beanComponent);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 技術追加登録
	 * @param bean 登録データ
	 * @param copyItemCd コピー元品目コード
	 */
	public void labelInsert(final ItemQueue bean, final String copyItemCd) {
		try {
			/* リンク検索 */
			Label beanLink = labelDao.getEntity("ITEM_LINK", copyItemCd);

			if (beanLink != null) {
				if (!beanLink.getLabelCd().equals(copyItemCd)) {
					beanLink.setLabelCd(bean.getItemCd());
					beanLink.setInputDate(beanLink.getCurrentTimestamp());
					beanLink.setInputorCd(bean.getUpdatorCd());
					beanLink.setUpdatorCd(bean.getUpdatorCd());

					/* 追加処理 */
					labelDao.insert(beanLink);
				}
			}

			/* 技術検索 */
			Label beanTech = labelDao.getEntity("ITEM_TECH", copyItemCd);

			if (beanTech != null) {
				if (!beanTech.getLabelCd().equals(copyItemCd)) {
					beanTech.setLabelCd(bean.getItemCd());
					beanTech.setInputDate(beanLink.getCurrentTimestamp());
					beanTech.setInputorCd(bean.getUpdatorCd());
					beanTech.setUpdatorCd(bean.getUpdatorCd());

					/* 追加処理 */
					labelDao.insert(beanTech);
				}
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * その他追加登録
	 * @param bean 登録データ
	 * @param prevVersion コピー前バージョン
	 * @param copyFlg コピーフラグ
	 * @param copyItemCd コピー元品目コード
	 * @param copyVersion コピー元バージョン
	 */
	public void remarkInsert(final ItemQueue bean, final String copyFlg,
			final BigDecimal prevVersion, final String copyItemCd,
			final BigDecimal copyVersion) {
		try {
			ItemRemark beanRemark = new ItemRemark();

			/* その他検索 */
			if (StringUtils.isEmpty(copyFlg)) {
				beanRemark = itemRemarkDao.getEntity(bean.getItemCd(),
					prevVersion);
			} else {
				beanRemark = itemRemarkDao.getEntity(copyItemCd, copyVersion);
			}

			if (beanRemark != null) {
				beanRemark.setItemCd(bean.getItemCd());
				beanRemark.setVersion(bean.getVersion());
				beanRemark.setInputDate(beanRemark.getCurrentTimestamp());
				beanRemark.setInputorCd(bean.getUpdatorCd());
				beanRemark.setUpdatorCd(bean.getUpdatorCd());

				/* 追加処理 */
				itemRemarkDao.insert(beanRemark);
			}
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 更新履歴追加登録
	 * @param bean 登録データ
	 * @param reason 更新理由
	 */
	public void historyInsert(final ItemQueue bean, final String reason) {
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
	 * 削除
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @throws NoDataException NoDataException
	 */
	public void delete(final String itemCd, final BigDecimal version)
			throws NoDataException {
		/* 削除処理 */
		int cnt = itemQueueDeleteDao.delete(itemCd, version);

		if (cnt == 0) {
			/* データなしエラー */
			throw new NoDataException();
		}

		/* 共通検索 */
		CommonAttributeQueue beanCommon = commonAttributeQueueDao.getEntity(
			itemCd, version);

		if (beanCommon != null) {
			/* 削除処理 */
			commonAttributeQueueDeleteDao.delete(itemCd, version);
		}

		/* 販売品検索 */
		ArticleAttributeQueue beanArticle = articleAttributeQueueDao.getEntity(
			itemCd, version);

		if (beanArticle != null) {
			/* 削除処理 */
			articleAttributeQueueDeleteDao.delete(itemCd, version);
		}

		/* 製造品検索 */
		ProductAttributeQueue beanProduct = productAttributeQueueDao.getEntity(
			itemCd, version);

		if (beanProduct != null) {
			/* 削除処理 */
			productAttributeQueueDeleteDao.delete(itemCd, version);
		}

		/* 購入品検索 */
		PurchaseAttributeQueue beanPurchase = purchaseAttributeQueueDao
				.getEntity(itemCd, version);

		if (beanPurchase != null) {
			/* 削除処理 */
			purchaseAttributeQueueDeleteDao.delete(itemCd, version);
		}

		/* 成分情報検索 */
		List<ComponentInformationQueueList> listComponent = componentInformationQueueListDao
				.getList(itemCd, version);

		if (0 < listComponent.size()) {
			/* 削除処理 */
			componentInformationQueueDeleteListDao.deleteList(itemCd, version);
		}

		/* 技術検索 */
		Label beanLink = labelDao.getEntity("ITEM_LINK", itemCd);

		if (beanLink != null) {
			/* 削除処理 */
			labelDao.delete(beanLink);
		}

		Label beanTech = labelDao.getEntity("ITEM_TECH", itemCd);

		if (beanTech != null) {
			/* 削除処理 */
			labelDao.delete(beanTech);
		}

		/* その他検索 */
		ItemRemark beanRemark = itemRemarkDao.getEntity(itemCd, version);

		if (beanRemark != null) {
			/* 削除処理 */
			itemRemarkDeleteDao.delete(itemCd, version);
		}
	}

	/**
	 * 販売品判定
	 * @param typeDivision 種別
	 * @return true:販売品である
	 */
	public boolean checkTypeArticle(final BigDecimal typeDivision) {
		boolean ret = false;

		switch (typeDivision.intValue()) {
		case 0: /* 製品 */
			ret = true; /* 商品 */
			break;
		case 1: /* 原料 */
			ret = false; /* 該当なし */
			break;
		case 2: /* 包材 */
			ret = false; /* 該当なし */
			break;
		case 3: /* 中間品 */
			ret = false; /* 該当なし */
			break;
		case 4: /* 仕入直送品 */
			ret = true; /* 仕入商品 */
			break;
		case 5: /* 仕入在庫品 */
			ret = true; /* 仕入商品 */
			break;
		case 6: /* 外注品（直送） */
			ret = true; /* 商品 */
			break;
		case 7: /* 外注品（非直送） */
			ret = true; /* 商品 */
			break;
		case 9: /* その他 */
			ret = true; /* 商品 */
			break;
		default:
			ret = false; /* 該当なし */
			break;
		}

		return ret;
	}

	/**
	 * 製造品判定
	 * @param typeDivision 種別
	 * @return true:製造品である
	 */
	public boolean checkTypeProduct(final BigDecimal typeDivision) {
		boolean ret = false;

		switch (typeDivision.intValue()) {
		case 0: /* 製品 */
			ret = true; /* 製品 */
			break;
		case 1: /* 原料 */
			ret = false; /* 該当なし */
			break;
		case 2: /* 包材 */
			ret = false; /* 該当なし */
			break;
		case 3: /* 中間品 */
			ret = true; /* 中間品 */
			break;
		case 4: /* 仕入直送品 */
			ret = false; /* 該当なし */
			break;
		case 5: /* 仕入在庫品 */
			ret = false; /* 該当なし */
			break;
		case 6: /* 外注品（直送） */
			ret = false; /* 該当なし */
			break;
		case 7: /* 外注品（非直送） */
			ret = false; /* 該当なし */
			break;
		case 9: /* その他 */
			ret = true; /* 原料 */
			break;
		default:
			ret = false; /* 該当なし */
			break;
		}

		return ret;
	}

	/**
	 * 購入品判定
	 * @param typeDivision 種別
	 * @return true:購入品である
	 */
	public boolean checkTypePurchase(final BigDecimal typeDivision) {
		boolean ret = false;

		switch (typeDivision.intValue()) {
		case 0: /* 製品 */
			ret = false; /* 該当なし */
			break;
		case 1: /* 原料 */
			ret = true; /* 原料 */
			break;
		case 2: /* 包材 */
			ret = true; /* 材料 */
			break;
		case 3: /* 中間品 */
			ret = false; /* 該当なし */
			break;
		case 4: /* 仕入直送品 */
			ret = true; /* 製品 */
			break;
		case 5: /* 仕入在庫品 */
			ret = true; /* 製品 */
			break;
		case 6: /* 外注品（直送） */
			ret = true; /* 製品 */
			break;
		case 7: /* 外注品（非直送） */
			ret = true; /* 製品 */
			break;
		case 9: /* その他 */
			ret = true; /* 製品 */
			break;
		default:
			ret = false; /* 該当なし */
			break;
		}

		return ret;
	}

	/**
	 * 最新品目コード取得
	 * @return 最新品目コード
	 */
	public String getNewItemCd() {
		ItemQueueNewItemCd bean = itemQueueNewItemCdDao.getNewItemCd();

		if (bean == null) {
			return null;
		}

		/* 1 ---> 000001 みたいな変換 */
		String itemCd = "";
		int len = bean.getNextval().toString().length();

		for (int i = 0; i < ITEM_CD_LEN; i++) {
			itemCd += "0";
		}

		itemCd += bean.getNextval().toString();
		itemCd = itemCd.substring(len, len + ITEM_CD_LEN);

		return itemCd;
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
	 * itemQueueDetailDaoを設定します。
	 * @param itemQueueDetailDao itemQueueDetailDao
	 */
	public void setItemQueueDetailDao(
			final ItemQueueDetailDao itemQueueDetailDao) {
		this.itemQueueDetailDao = itemQueueDetailDao;
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
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao locationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}

	/**
	 * namesListForComboboxesDaoを設定します。
	 * @param namesListForComboboxesDao namesListForComboboxesDao
	 */
	public void setNamesListForComboboxesDao(
			final NamesListForComboboxesDao namesListForComboboxesDao) {
		this.namesListForComboboxesDao = namesListForComboboxesDao;
	}

	/**
	 * itemCategoryListForComboboxesDaoを設定します。
	 * @param itemCategoryListForComboboxesDao itemCategoryListForComboboxesDao
	 */
	public void setItemCategoryListForComboboxesDao(
			final ItemCategoryListForComboboxesDao itemCategoryListForComboboxesDao) {
		this.itemCategoryListForComboboxesDao = itemCategoryListForComboboxesDao;
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
	 * componentInformationQueueListDaoを設定します。
	 * @param componentInformationQueueListDao componentInformationQueueListDao
	 */
	public void setComponentInformationQueueListDao(
			final ComponentInformationQueueListDao componentInformationQueueListDao) {
		this.componentInformationQueueListDao = componentInformationQueueListDao;
	}

	/**
	 * componentInformationQueueDaoを設定します。
	 * @param componentInformationQueueDao componentInformationQueueDao
	 */
	public void setComponentInformationQueueDao(
			final ComponentInformationQueueDao componentInformationQueueDao) {
		this.componentInformationQueueDao = componentInformationQueueDao;
	}

	/**
	 * itemRemarkDaoを設定します。
	 * @param itemRemarkDao itemRemarkDao
	 */
	public void setItemRemarkDao(final ItemRemarkDao itemRemarkDao) {
		this.itemRemarkDao = itemRemarkDao;
	}

	/**
	 * changeHistoryDaoを設定します。
	 * @param changeHistoryDao changeHistoryDao
	 */
	public void setChangeHistoryDao(final ChangeHistoryDao changeHistoryDao) {
		this.changeHistoryDao = changeHistoryDao;
	}

	/**
	 * itemQueueDeleteDaoを設定します。
	 * @param itemQueueDeleteDao itemQueueDeleteDao
	 */
	public void setItemQueueDeleteDao(
			final ItemQueueDeleteDao itemQueueDeleteDao) {
		this.itemQueueDeleteDao = itemQueueDeleteDao;
	}

	/**
	 * itemQueueLastVersionDaoを設定します。
	 * @param itemQueueLastVersionDao itemQueueLastVersionDao
	 */
	public void setItemQueueLastVersionDao(
			final ItemQueueLastVersionDao itemQueueLastVersionDao) {
		this.itemQueueLastVersionDao = itemQueueLastVersionDao;
	}

	/**
	 * articleAttributeQueueDeleteDaoを設定します。
	 * @param articleAttributeQueueDeleteDao articleAttributeQueueDeleteDao
	 */
	public void setArticleAttributeQueueDeleteDao(
			final ArticleAttributeQueueDeleteDao articleAttributeQueueDeleteDao) {
		this.articleAttributeQueueDeleteDao = articleAttributeQueueDeleteDao;
	}

	/**
	 * commonAttributeQueueDeleteDaoを設定します。
	 * @param commonAttributeQueueDeleteDao commonAttributeQueueDeleteDao
	 */
	public void setCommonAttributeQueueDeleteDao(
			final CommonAttributeQueueDeleteDao commonAttributeQueueDeleteDao) {
		this.commonAttributeQueueDeleteDao = commonAttributeQueueDeleteDao;
	}

	/**
	 * componentInformationQueueDeleteListDaoを設定します。
	 * @param componentInformationQueueDeleteListDao
	 *            componentInformationQueueDeleteListDao
	 */
	public void setComponentInformationQueueDeleteListDao(
			final ComponentInformationQueueDeleteListDao componentInformationQueueDeleteListDao) {
		this.componentInformationQueueDeleteListDao = componentInformationQueueDeleteListDao;
	}

	/**
	 * itemRemarkDeleteDaoを設定します。
	 * @param itemRemarkDeleteDao itemRemarkDeleteDao
	 */
	public void setItemRemarkDeleteDao(
			final ItemRemarkDeleteDao itemRemarkDeleteDao) {
		this.itemRemarkDeleteDao = itemRemarkDeleteDao;
	}

	/**
	 * productAttributeQueueDeleteDaoを設定します。
	 * @param productAttributeQueueDeleteDao productAttributeQueueDeleteDao
	 */
	public void setProductAttributeQueueDeleteDao(
			final ProductAttributeQueueDeleteDao productAttributeQueueDeleteDao) {
		this.productAttributeQueueDeleteDao = productAttributeQueueDeleteDao;
	}

	/**
	 * purchaseAttributeQueueDeleteDaoを設定します。
	 * @param purchaseAttributeQueueDeleteDao purchaseAttributeQueueDeleteDao
	 */
	public void setPurchaseAttributeQueueDeleteDao(
			final PurchaseAttributeQueueDeleteDao purchaseAttributeQueueDeleteDao) {
		this.purchaseAttributeQueueDeleteDao = purchaseAttributeQueueDeleteDao;
	}

	/**
	 * labelDaoを設定します。
	 * @param labelDao labelDao
	 */
	public void setLabelDao(final LabelDao labelDao) {
		this.labelDao = labelDao;
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
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}

	/**
	 * itemUpdateDaoを設定します。
	 * @param itemUpdateDao itemUpdateDao
	 */
	public void setItemUpdateDao(final ItemUpdateDao itemUpdateDao) {
		this.itemUpdateDao = itemUpdateDao;
	}

	/**
	 * itemQueueNewItemCdDaoを設定します。
	 * @param itemQueueNewItemCdDao itemQueueNewItemCdDao
	 */
	public void setItemQueueNewItemCdDao(
			final ItemQueueNewItemCdDao itemQueueNewItemCdDao) {
		this.itemQueueNewItemCdDao = itemQueueNewItemCdDao;
	}
}
