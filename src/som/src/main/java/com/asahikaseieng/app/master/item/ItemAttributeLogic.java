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

import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.commonattributequeue.CommonAttributeQueue;
import com.asahikaseieng.dao.entity.master.productattributequeue.ProductAttributeQueue;
import com.asahikaseieng.dao.entity.master.purchaseattributequeue.PurchaseAttributeQueue;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.articleattributequeuedetail.ArticleAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.bumondetail.BumonDetail;
import com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail.CommonAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.deliverydetail.DeliveryDetail;
import com.asahikaseieng.dao.nonentity.master.financialclassdetail.FinancialClassDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastactive.ItemQueueLastActive;
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail;
import com.asahikaseieng.dao.nonentity.master.organizationdetail.OrganizationDetail;
import com.asahikaseieng.dao.nonentity.master.productattributequeuedetail.ProductAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;

/**
 * 品目在庫・単価ロジック interface.
 * @author t0011036
 */
public interface ItemAttributeLogic {
	/**
	 * 生産工場№検索
	 * @param productionLine 生産ラインコード
	 * @return LineDetail
	 */
	LineDetail getLineEntity(final String productionLine);

	/**
	 * 原価部門検索
	 * @param organizationCd 部署コード
	 * @return OrganizationDetail
	 */
	OrganizationDetail getOrganizationEntity(final String organizationCd);

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return BumonDetail
	 */
	BumonDetail getBumonEntity(final String sectionCd);

	/**
	 * 勘定科目検索
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	AccountsDetail getAccountsEntity(final String accountsCd);

	/**
	 * 財務分類検索
	 * @param financialClassCd 財務分類コード
	 * @return FinancialClassDetail
	 */
	FinancialClassDetail getFinancialClassEntity(final String financialClassCd);

	/**
	 * 基準仕入先検索
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return VenderDetail
	 */
	VenderDetail getVenderEntity(final String venderDivision,
			final String venderCd);

	/**
	 * 納入先検索
	 * @param deliveryCd 納入先コード
	 * @return DeliveryDetail
	 */
	DeliveryDetail getDeliveryEntity(final String deliveryCd);

	/**
	 * 共通情報検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return CommonAttributeQueue
	 */
	CommonAttributeQueue getCommonEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 製造品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ProductAttributeQueue
	 */
	ProductAttributeQueue getProductEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 販売品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ArticleAttributeQueue
	 */
	ArticleAttributeQueue getArticleEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 購入品扱い属性検索(登録用)
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueue
	 */
	PurchaseAttributeQueue getPurchaseEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 共通情報検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return CommonAttributeQueueDetail
	 */
	CommonAttributeQueueDetail getCommonDetailEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 製造品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ProductAttributeQueueDetail
	 */
	ProductAttributeQueueDetail getProductDetailEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 販売品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ArticleAttributeQueueDetail
	 */
	ArticleAttributeQueueDetail getArticleDetailEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 購入品扱い属性検索
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return PurchaseAttributeQueueDetail
	 */
	PurchaseAttributeQueueDetail getPurchaseDetailEntity(final String itemCd,
			final java.math.BigDecimal version);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getHeaderEntity(final String itemCd,
			final BigDecimal version);

	/**
	 * ステータス更新処理を行う.
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 */
	void updateStatus(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase);

	/**
	 * 承認登録処理を行う.
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	void approvalUpdate(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase)
			throws IllegalAccessException, InvocationTargetException;

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
	void approvalCancelUpdate(final String itemCd, final BigDecimal version,
			final Timestamp activeDate, final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase, final Boolean activate);

	/**
	 * 登録処理を行う.
	 * @param productDivision 製造品区分
	 * @param articleDivision 販売品区分
	 * @param purchaseDivision 購入品区分
	 * @param beanCommon 追加対象データ(共通情報)
	 * @param beanProduct 追加対象データ(製造品)
	 * @param beanArticle 追加対象データ(販売品)
	 * @param beanPurchase 追加対象データ(購入品)
	 * @param reason 更新理由
	 */
	void regist(final BigDecimal productDivision,
			final BigDecimal articleDivision,
			final BigDecimal purchaseDivision,
			final CommonAttributeQueue beanCommon,
			final ProductAttributeQueue beanProduct,
			final ArticleAttributeQueue beanArticle,
			final PurchaseAttributeQueue beanPurchase, final String reason);

	/**
	 * 有効品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastActive
	 */
	ItemQueueLastActive getLastActiveItemQueueEntity(final String itemCd);

	/**
	 * 標準販売単価更新
	 * @param itemCd 品目コード
	 * @param price 標準販売単価
	 * @param inputDate 見積変更開始基準日
	 * @param tantoCd 担当者コード
	 */
	void updatePrice(final String itemCd, final BigDecimal price,
			final Timestamp inputDate, final String tantoCd);

	/**
	 * 検索処理を行う.
	 * @param itemCd 品目コード
	 * @param version バージョン
	 * @return ItemDetail
	 */
	ItemDetail getItemDetailEntity(final String itemCd, final BigDecimal version);
}
