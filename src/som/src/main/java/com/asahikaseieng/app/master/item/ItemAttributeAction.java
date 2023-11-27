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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
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
import com.asahikaseieng.dao.nonentity.master.linedetail.LineDetail;
import com.asahikaseieng.dao.nonentity.master.productattributequeuedetail.ProductAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.PurchaseAttributeQueueDetail;
import com.asahikaseieng.dao.nonentity.master.venderdetail.VenderDetail;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目在庫・単価 Actionクラス.
 * @author t0011036
 */
public final class ItemAttributeAction extends AbstractAction {

	private ItemAttributeLogic itemAttributeLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemAttributeAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_ATTRIBUTE);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemAttributeLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 初期検索 */
		CommonAttributeQueueDetail beanCommon = itemAttributeLogic
				.getCommonDetailEntity(frm.getItemCd(), frm.getVersion());

		BigDecimal status = new BigDecimal("0");

		if (beanCommon != null) {
			/* 数値文字列に変換 */
			beanCommon.setStrExpireMonths(checker.format(frm
					.getUnitDivisionOther(), beanCommon.getExpireMonths()));
			beanCommon.setStrContractMonths(checker.format(frm
					.getUnitDivisionOther(), beanCommon.getContractMonths()));

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, beanCommon);
		}

		ProductAttributeQueueDetail beanProduct = itemAttributeLogic
				.getProductDetailEntity(frm.getItemCd(), frm.getVersion());

		if (beanProduct != null) {
			/* 数値文字列に変換 */
			beanProduct.setStrOrderPattern(checker.format(frm
					.getUnitDivisionOther(), beanProduct.getOrderPattern()));
			beanProduct.setStrInspectionDays(checker.format(frm
					.getUnitDivisionOther(), beanProduct.getInspectionDays()));
			beanProduct.setStrProductOrderPoint(checker
					.format(frm.getUnitDivisionOther(), beanProduct
							.getProductOrderPoint()));

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, beanProduct);

			/* 全てのステータスは同じ */
			if (!frm.getProductDivision().equals(new BigDecimal("0"))) {
				status = frm.getProductStatus();
			}
		}

		ArticleAttributeQueueDetail beanArticle = itemAttributeLogic
				.getArticleDetailEntity(frm.getItemCd(), frm.getVersion());

		if (beanArticle != null) {
			/* 数値文字列に変換 */
			beanArticle.setStrPaletteProducts(checker.format(frm
					.getUnitDivisionOther(), beanArticle.getPaletteProducts()));
			beanArticle.setStrSellingPrice(checker.format(frm
					.getUnitDivisionUrtanka(), beanArticle.getSellingPrice()));
			beanArticle.setStrSafetyLeadTime(checker.format(frm
					.getUnitDivisionOther(), beanArticle.getSafetyLeadTime()));

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, beanArticle);

			/* 全てのステータスは同じ */
			if (!frm.getArticleDivision().equals(new BigDecimal("0"))) {
				status = frm.getArticleStatus();
			}
		}

		PurchaseAttributeQueueDetail beanPurchase = itemAttributeLogic
				.getPurchaseDetailEntity(frm.getItemCd(), frm.getVersion());

		if (beanPurchase != null) {
			/* 数値文字列に変換 */
			beanPurchase.setStrPurchaseLeadTime(checker
					.format(frm.getUnitDivisionOther(), beanPurchase
							.getPurchaseLeadTime()));
			beanPurchase
					.setStrPurchasePrice(checker.format(frm
							.getUnitDivisionSitanka(), beanPurchase
							.getPurchasePrice()));
			beanPurchase.setStrPurchaseOrderPoint(checker.format(frm
					.getUnitDivisionOther(), beanPurchase
					.getPurchaseOrderPoint()));
			beanPurchase.setStrOrderQty(checker.format(frm
					.getUnitOfStockControl(), beanPurchase.getOrderQty()));

			/* BeanをFormにコピーする */
			IgnoreCaseBeanUtils.copyProperties(frm, beanPurchase);

			/* 全てのステータスは同じ */
			if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))) {
				status = frm.getPurchaseStatus();
			}
		}

		frm.setStatus(status);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		if (!frm.getProductDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getProductionLine())) {
			/* 生産工場№チェック */
			LineDetail beanLine = itemAttributeLogic.getLineEntity(frm
					.getProductionLine());

			if (beanLine == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.production.line");
				return mapping.findForward("success");
			}
		}

		if (!frm.getProductDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getProductSectionCd())) {
			/* 原価部門チェック */
			BumonDetail beanBumon = itemAttributeLogic.getBumonEntity(frm
					.getProductSectionCd());

			if (beanBumon == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.product.section.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getArticleDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getArticleSectionCd())) {
			/* 売上会計部門チェック */
			BumonDetail beanBumon = itemAttributeLogic.getBumonEntity(frm
					.getArticleSectionCd());

			if (beanBumon == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.article.section.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getArticleDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getArticleAccountsCd())) {
			/* 売上勘定科目チェック */
			AccountsDetail beanAccounts = itemAttributeLogic
					.getAccountsEntity(frm.getArticleAccountsCd());

			if (beanAccounts == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.article.accounts.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getArticleDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getFinancialClassCd())) {
			/* 財務分類チェック */
			FinancialClassDetail beanFinancialClass = itemAttributeLogic
					.getFinancialClassEntity(frm.getFinancialClassCd());

			if (beanFinancialClass == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.financial.class.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getVenderCd())) {
			/* 基準仕入先チェック */
			VenderDetail beanVender = itemAttributeLogic.getVenderEntity("SI",
				frm.getVenderCd());

			if (beanVender == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.vender.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getDeliveryCd())) {
			/* 納入先チェック */
			DeliveryDetail beanDelivery = itemAttributeLogic
					.getDeliveryEntity(frm.getDeliveryCd());

			if (beanDelivery == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.delivery.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getPurchaseSectionCd())) {
			/* 仕入会計部門チェック */
			BumonDetail beanBumon = itemAttributeLogic.getBumonEntity(frm
					.getPurchaseSectionCd());

			if (beanBumon == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.purchase.section.cd");
				return mapping.findForward("success");
			}
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))
				&& !StringUtils.isEmpty(frm.getPurchaseAccountsCd())) {
			/* 仕入勘定科目チェック */
			AccountsDetail beanAccounts = itemAttributeLogic
					.getAccountsEntity(frm.getPurchaseAccountsCd());

			if (beanAccounts == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.purchase.accounts.cd");
				return mapping.findForward("success");
			}
		}

		/* 数値文字列を数値に変換 */
		frm.setExpireMonths(AecNumberUtils.convertBigDecimal(frm
				.getStrExpireMonths()));
		frm.setContractMonths(AecNumberUtils.convertBigDecimal(frm
				.getStrContractMonths()));

		/* 製造品 */
		if (!frm.getProductDivision().equals(new BigDecimal("0"))) {
			frm.setOrderPattern(AecNumberUtils.convertBigDecimal(frm
					.getStrOrderPattern()));
			frm.setInspectionDays(AecNumberUtils.convertBigDecimal(frm
					.getStrInspectionDays()));
			frm.setProductOrderPoint(AecNumberUtils.convertBigDecimal(frm
					.getStrProductOrderPoint()));
		}

		/* 販売品 */
		if (!frm.getArticleDivision().equals(new BigDecimal("0"))) {
			frm.setSellingPrice(AecNumberUtils.convertBigDecimal(frm
					.getStrSellingPrice()));
			frm.setPaletteProducts(AecNumberUtils.convertBigDecimal(frm
					.getStrPaletteProducts()));
			frm.setSafetyLeadTime(AecNumberUtils
					.convertNullToZero(AecNumberUtils.convertBigDecimal(frm
							.getStrSafetyLeadTime())));
		}

		/* 購入品 */
		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))) {
			frm.setPurchaseLeadTime(AecNumberUtils.convertBigDecimal(frm
					.getStrPurchaseLeadTime()));
			frm.setPurchasePrice(AecNumberUtils.convertBigDecimal(frm
					.getStrPurchasePrice()));
			frm.setPurchaseOrderPoint(AecNumberUtils.convertBigDecimal(frm
					.getStrPurchaseOrderPoint()));
			frm.setOrderQty(AecNumberUtils.convertBigDecimal(frm
					.getStrOrderQty()));
		}

		CommonAttributeQueue beanCommon = null;
		ProductAttributeQueue beanProduct = null;
		ArticleAttributeQueue beanArticle = null;
		PurchaseAttributeQueue beanPurchase = null;

		/* 登録データをセット */
		if (frm.getCommonUpdateDate() == null) {
			beanCommon = insertCommonAttributeQueue(frm, getLoginInfo(request)
					.getTantoCd());
		} else {
			beanCommon = updateCommonAttributeQueue(frm, getLoginInfo(request)
					.getTantoCd());
		}

		if (!frm.getProductDivision().equals(new BigDecimal("0"))) {
			if (frm.getProductUpdateDate() == null) {
				beanProduct = insertProductAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			} else {
				beanProduct = updateProductAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			}

			beanProduct.setStatus(new BigDecimal("1")); /* 入力中 */
		}

		if (!frm.getArticleDivision().equals(new BigDecimal("0"))) {
			if (frm.getArticleUpdateDate() == null) {
				beanArticle = insertArticleAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			} else {
				beanArticle = updateArticleAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			}

			beanArticle.setStatus(new BigDecimal("1")); /* 入力中 */
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))) {
			if (frm.getPurchaseUpdateDate() == null) {
				beanPurchase = insertPurchaseAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			} else {
				beanPurchase = updatePurchaseAttributeQueue(frm, getLoginInfo(
					request).getTantoCd());
			}

			beanPurchase.setStatus(new BigDecimal("1")); /* 入力中 */
		}

		/* 登録処理を実行 */
		itemAttributeLogic.regist(frm.getProductDivision(), frm
				.getArticleDivision(), frm.getPurchaseDivision(), beanCommon,
			beanProduct, beanArticle, beanPurchase, frm.getReason());

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemAttributeLogic.getHeaderEntity(
			beanCommon.getItemCd(), beanCommon.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		// BigDecimal status = new BigDecimal("0");
		//
		// /* 全てのステータスは同じ */
		// if (!frm.getProductDivision().equals(new BigDecimal("0"))) {
		// status = frm.getProductStatus();
		// } else if (!frm.getArticleDivision().equals(new BigDecimal("0"))) {
		// status = frm.getArticleStatus();
		// } else if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))) {
		// status = frm.getPurchaseStatus();
		// }

		frm.setStatus(new BigDecimal("1")); /* 入力中 */
		frm.setCommonUpdateDate(AecDateUtils.getCurrentTimestamp());

		if (!frm.getProductDivision().equals(new BigDecimal("0"))) {
			frm.setProductUpdateDate(AecDateUtils.getCurrentTimestamp());
		}

		if (!frm.getArticleDivision().equals(new BigDecimal("0"))) {
			frm.setArticleUpdateDate(AecDateUtils.getCurrentTimestamp());
		}

		if (!frm.getPurchaseDivision().equals(new BigDecimal("0"))) {
			frm.setPurchaseUpdateDate(AecDateUtils.getCurrentTimestamp());
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 追加処理用のCommonAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return CommonAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private CommonAttributeQueue insertCommonAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		CommonAttributeQueue newBean = new CommonAttributeQueue();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用のCommonAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return CommonAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private CommonAttributeQueue updateCommonAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		CommonAttributeQueue newBean = itemAttributeLogic.getCommonEntity(frm
				.getItemCd(), frm.getVersion());

		/* 退避用 */
		Timestamp inputDate = newBean.getInputDate(); /* 登録日時 */

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(inputDate);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のProductAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ProductAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ProductAttributeQueue insertProductAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		ProductAttributeQueue newBean = new ProductAttributeQueue();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 未使用項目に初期値をセット */
		newBean.setDailyProduction(new BigDecimal("0"));
		newBean.setServerDivision(new BigDecimal("0"));
		newBean.setProductionCycle(new BigDecimal("0"));
		newBean.setEnphasisDivision(new BigDecimal("0"));
		newBean.setCockDivision(new BigDecimal("0"));
		newBean.setPlanFlg(new BigDecimal("0"));
		newBean.setSerialnoFlg(new BigDecimal("0"));
		newBean.setProductionLeadTime(new BigDecimal("0"));
		newBean.setSafetyLeadTime(new BigDecimal("0"));

		/* コピーしきれなかった分は手で */
		newBean.setOrderPoint(frm.getProductOrderPoint());
		newBean.setSectionCd(frm.getProductSectionCd());
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用のProductAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ProductAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ProductAttributeQueue updateProductAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		ProductAttributeQueue newBean = itemAttributeLogic.getProductEntity(frm
				.getItemCd(), frm.getVersion());

		/* 退避用 */
		Timestamp inputDate = newBean.getInputDate(); /* 登録日時 */

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setOrderPoint(frm.getProductOrderPoint());
		newBean.setSectionCd(frm.getProductSectionCd());
		newBean.setInputDate(inputDate);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のArticleAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ArticleAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ArticleAttributeQueue insertArticleAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		ArticleAttributeQueue newBean = new ArticleAttributeQueue();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 未使用項目に初期値をセット */
		newBean.setTaxRatio(new BigDecimal("0"));
		newBean.setDeliveryTime(new BigDecimal("0"));
		newBean.setCertificationAttach(new BigDecimal("0"));
		newBean.setDeliveryLeadTime(new BigDecimal("0"));

		/* コピーしきれなかった分は手で */
		newBean.setPriceCalcDivision(frm.getArticlePriceCalcDivision());
		newBean.setSectionCd(frm.getArticleSectionCd());
		newBean.setAccountsCd(frm.getArticleAccountsCd());
		newBean.setTaxDivision(frm.getArticleTaxDivision());
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用のArticleAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ArticleAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ArticleAttributeQueue updateArticleAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		ArticleAttributeQueue newBean = itemAttributeLogic.getArticleEntity(frm
				.getItemCd(), frm.getVersion());

		/* 退避用 */
		Timestamp inputDate = newBean.getInputDate(); /* 登録日時 */

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setPriceCalcDivision(frm.getArticlePriceCalcDivision());
		newBean.setSectionCd(frm.getArticleSectionCd());
		newBean.setAccountsCd(frm.getArticleAccountsCd());
		newBean.setTaxDivision(frm.getArticleTaxDivision());
		newBean.setInputDate(inputDate);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 追加処理用のPurchaseAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return PurchaseAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private PurchaseAttributeQueue insertPurchaseAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		PurchaseAttributeQueue newBean = new PurchaseAttributeQueue();

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* 未使用項目に初期値をセット */
		newBean.setSafetyLeadTime(new BigDecimal("0"));
		newBean.setTaxRatio(new BigDecimal("0"));
		newBean.setExtraRatio(new BigDecimal("0"));
		newBean.setServerDivision(new BigDecimal("0"));
		newBean.setCertificationAttach(new BigDecimal("0"));
		newBean.setCockDivision(new BigDecimal("0"));

		/* コピーしきれなかった分は手で */
		newBean.setTaxDivision(frm.getPurchaseTaxDivision());
		newBean.setOrderPoint(frm.getPurchaseOrderPoint());
		newBean.setPriceCalcDivision(frm.getPurchasePriceCalcDivision());
		newBean.setSectionCd(frm.getPurchaseSectionCd());
		newBean.setAccountsCd(frm.getPurchaseAccountsCd());
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 更新処理用のPurchaseAttributeQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return PurchaseAttributeQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private PurchaseAttributeQueue updatePurchaseAttributeQueue(
			final ItemAttributeForm frm, final String tantoCd)
			throws IllegalAccessException, InvocationTargetException {
		PurchaseAttributeQueue newBean = itemAttributeLogic.getPurchaseEntity(
			frm.getItemCd(), frm.getVersion());

		/* 退避用 */
		Timestamp inputDate = newBean.getInputDate(); /* 登録日時 */

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		/* コピーしきれなかった分は手で */
		newBean.setTaxDivision(frm.getPurchaseTaxDivision());
		newBean.setOrderPoint(frm.getPurchaseOrderPoint());
		newBean.setPriceCalcDivision(frm.getPurchasePriceCalcDivision());
		newBean.setSectionCd(frm.getPurchaseSectionCd());
		newBean.setAccountsCd(frm.getPurchaseAccountsCd());
		newBean.setInputDate(inputDate);
		newBean.setUpdatorCd(tantoCd);

		return newBean;
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 承認依頼.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalRequest(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalRequest.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		/* 共通情報検索 */
		CommonAttributeQueue beanCommon = itemAttributeLogic.getCommonEntity(
			frm.getItemCd(), frm.getVersion());

		/* 製造品検索 */
		ProductAttributeQueue beanProduct = itemAttributeLogic
				.getProductEntity(frm.getItemCd(), frm.getVersion());

		/* 販売品品検索 */
		ArticleAttributeQueue beanArticle = itemAttributeLogic
				.getArticleEntity(frm.getItemCd(), frm.getVersion());

		/* 購入品検索 */
		PurchaseAttributeQueue beanPurchase = itemAttributeLogic
				.getPurchaseEntity(frm.getItemCd(), frm.getVersion());

		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 承認依頼中 */
		if (beanProduct != null) {
			beanProduct.setStatus(new BigDecimal("2"));
			beanProduct.setUpdatorCd(tantoCd);
		}

		if (beanArticle != null) {
			beanArticle.setStatus(new BigDecimal("2"));
			beanArticle.setUpdatorCd(tantoCd);
		}

		if (beanPurchase != null) {
			beanPurchase.setStatus(new BigDecimal("2"));
			beanPurchase.setUpdatorCd(tantoCd);
		}

		/* 登録処理 */
		itemAttributeLogic.updateStatus(frm.getProductDivision(), frm
				.getArticleDivision(), frm.getPurchaseDivision(), beanProduct,
			beanArticle, beanPurchase);

		frm.setExpireMonths(beanCommon.getExpireMonths());
		frm.setContractMonths(beanCommon.getContractMonths());
		frm.setOrderInfo(beanCommon.getOrderInfo());
		frm.setRemarks(beanCommon.getRemarks());
		frm.setApplicationLaw(beanCommon.getApplicationLaw());

		if (beanProduct != null) {
			frm.setOrderPattern(beanProduct.getOrderPattern());
			frm.setProductionLine(beanProduct.getProductionLine());
			frm.setInspectionDays(beanProduct.getInspectionDays());
			frm.setProductOrderPoint(beanProduct.getOrderPoint());
			frm.setProductSectionCd(beanProduct.getSectionCd());
		}

		if (beanArticle != null) {
			frm.setArticlePriceCalcDivision(beanArticle.getPriceCalcDivision());
			frm.setSellingPrice(beanArticle.getSellingPrice());
			frm.setPaletteProducts(beanArticle.getPaletteProducts());
			frm.setJanCd(beanArticle.getJanCd());
			frm.setItfCd(beanArticle.getItfCd());
			frm.setSafetyLeadTime(beanArticle.getSafetyLeadTime());
			frm.setArticleSectionCd(beanArticle.getSectionCd());
			frm.setArticleAccountsCd(beanArticle.getAccountsCd());
			frm.setArticleTaxDivision(beanArticle.getTaxDivision());
			frm.setFinancialClassCd(beanArticle.getFinancialClassCd());
			frm.setKeepDivision(beanArticle.getKeepDivision());
		}

		if (beanPurchase != null) {
			frm.setMaterialMakerName(beanPurchase.getMaterialMakerName());
			frm.setVenderCd(beanPurchase.getVenderCd());
			frm.setDeliveryCd(beanPurchase.getDeliveryCd());
			frm.setPurchaseLeadTime(beanPurchase.getPurchaseLeadTime());
			frm.setPurchasePrice(beanPurchase.getPurchasePrice());
			frm.setPurchaseTaxDivision(beanPurchase.getTaxDivision());
			frm.setPurchaseTrigger(beanPurchase.getPurchaseTrigger());
			frm.setMultiSupplierDivision(beanPurchase
					.getMultiSupplierDivision());
			frm.setPurchaseOrderPoint(beanPurchase.getOrderPoint());
			frm.setOrderQty(beanPurchase.getOrderQty());
			frm.setInspectionType(beanPurchase.getInspectionType());
			frm.setPurchasePriceCalcDivision(beanPurchase
					.getPriceCalcDivision());
			frm.setSuppliedGoodsDivision(new BigDecimal(beanPurchase
					.getSuppliedGoodsDivision()));
			frm.setLeaseDrumFlag(beanPurchase.getLeaseDrumFlag());
			frm.setLorryDivision(beanPurchase.getLorryDivision());
			frm.setPurchaseSectionCd(beanPurchase.getSectionCd());
			frm.setPurchaseAccountsCd(beanPurchase.getAccountsCd());
		}

		frm.setStatus(new BigDecimal("2"));

		if (beanProduct != null) {
			frm.setProductStatus(new BigDecimal("2"));
		}

		if (beanArticle != null) {
			frm.setArticleStatus(new BigDecimal("2"));
		}

		if (beanPurchase != null) {
			frm.setPurchaseStatus(new BigDecimal("2"));
		}

		frm.setHeadAttributeStatusName("承認依頼中");

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列に変換 */
		frm.setStrExpireMonths(checker.format(frm.getUnitDivisionOther(),
			beanCommon.getExpireMonths()));
		frm.setStrContractMonths(checker.format(frm.getUnitDivisionOther(),
			beanCommon.getContractMonths()));

		if (beanProduct != null) {
			/* 数値文字列に変換 */
			frm.setStrOrderPattern(checker.format(frm.getUnitDivisionOther(),
				beanProduct.getOrderPattern()));
			frm.setStrInspectionDays(checker.format(frm.getUnitDivisionOther(),
				beanProduct.getInspectionDays()));
			frm.setStrProductOrderPoint(checker.format(frm
					.getUnitDivisionOther(), beanProduct.getOrderPoint()));
		}

		if (beanArticle != null) {
			/* 数値文字列に変換 */
			frm.setStrPaletteProducts(checker.format(
				frm.getUnitDivisionOther(), beanArticle.getPaletteProducts()));
			frm.setStrSellingPrice(checker.format(frm.getUnitDivisionUrtanka(),
				beanArticle.getSellingPrice()));
			frm.setStrSafetyLeadTime(checker.format(frm.getUnitDivisionOther(),
				beanArticle.getSafetyLeadTime()));
		}

		if (beanPurchase != null) {
			/* 数値文字列に変換 */
			frm.setStrPurchaseLeadTime(checker
					.format(frm.getUnitDivisionOther(), beanPurchase
							.getPurchaseLeadTime()));
			frm.setStrPurchasePrice(checker.format(
				frm.getUnitDivisionSitanka(), beanPurchase.getPurchasePrice()));
			frm.setStrPurchaseOrderPoint(checker.format(frm
					.getUnitDivisionOther(), beanPurchase.getOrderPoint()));
			frm.setStrOrderQty(checker.format(frm.getUnitOfStockControl(),
				beanPurchase.getOrderQty()));
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approval(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approval.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		/* 製造品検索 */
		ProductAttributeQueue beanProduct = itemAttributeLogic
				.getProductEntity(frm.getItemCd(), frm.getVersion());

		/* 販売品品検索 */
		ArticleAttributeQueue beanArticle = itemAttributeLogic
				.getArticleEntity(frm.getItemCd(), frm.getVersion());

		/* 購入品検索 */
		PurchaseAttributeQueue beanPurchase = itemAttributeLogic
				.getPurchaseEntity(frm.getItemCd(), frm.getVersion());

		String itemCd = null;
		BigDecimal version = null;
		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 承認 */
		if (beanProduct != null) {
			beanProduct.setStatus(new BigDecimal("3"));
			beanProduct.setUpdatorCd(tantoCd);
			itemCd = beanProduct.getItemCd();
			version = beanProduct.getVersion();
		}

		if (beanArticle != null) {
			beanArticle.setStatus(new BigDecimal("3"));
			beanArticle.setUpdatorCd(tantoCd);
			itemCd = beanArticle.getItemCd();
			version = beanArticle.getVersion();
		}

		if (beanPurchase != null) {
			beanPurchase.setStatus(new BigDecimal("3"));
			beanPurchase.setUpdatorCd(tantoCd);
			itemCd = beanPurchase.getItemCd();
			version = beanPurchase.getVersion();
		}

		/* 登録処理 */
		itemAttributeLogic.approvalUpdate(frm.getProductDivision(), frm
				.getArticleDivision(), frm.getPurchaseDivision(), beanProduct,
			beanArticle, beanPurchase);

		frm.setStatus(new BigDecimal("3"));

		if (beanProduct != null) {
			frm.setProductStatus(new BigDecimal("3"));
		}

		if (beanArticle != null) {
			frm.setArticleStatus(new BigDecimal("3"));
		}

		if (beanPurchase != null) {
			frm.setPurchaseStatus(new BigDecimal("3"));
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemAttributeLogic.getHeaderEntity(itemCd,
			version);

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		frm.setHeadAttributeStatusName("承認済み");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 否認.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward negation(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("negation.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		/* 製造品検索 */
		ProductAttributeQueue beanProduct = itemAttributeLogic
				.getProductEntity(frm.getItemCd(), frm.getVersion());

		/* 販売品品検索 */
		ArticleAttributeQueue beanArticle = itemAttributeLogic
				.getArticleEntity(frm.getItemCd(), frm.getVersion());

		/* 購入品検索 */
		PurchaseAttributeQueue beanPurchase = itemAttributeLogic
				.getPurchaseEntity(frm.getItemCd(), frm.getVersion());

		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 否認 */
		if (beanProduct != null) {
			beanProduct.setStatus(new BigDecimal("1"));
			beanProduct.setUpdatorCd(tantoCd);
		}

		if (beanArticle != null) {
			beanArticle.setStatus(new BigDecimal("1"));
			beanArticle.setUpdatorCd(tantoCd);
		}

		if (beanPurchase != null) {
			beanPurchase.setStatus(new BigDecimal("1"));
			beanPurchase.setUpdatorCd(tantoCd);
		}

		/* 登録処理 */
		itemAttributeLogic.approvalCancelUpdate(frm.getItemCd(), frm
				.getVersion(), frm.getHeadActiveDate(), frm
				.getProductDivision(), frm.getArticleDivision(), frm
				.getPurchaseDivision(), beanProduct, beanArticle, beanPurchase,
			Boolean.FALSE);

		frm.setStatus(new BigDecimal("1"));

		if (beanProduct != null) {
			frm.setProductStatus(new BigDecimal("1"));
		}

		if (beanArticle != null) {
			frm.setArticleStatus(new BigDecimal("1"));
		}

		if (beanPurchase != null) {
			frm.setPurchaseStatus(new BigDecimal("1"));
		}

		frm.setHeadAttributeStatusName("入力中");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 承認取消.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward approvalCancel(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("approvalCancel.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		/* 製造品検索 */
		ProductAttributeQueue beanProduct = itemAttributeLogic
				.getProductEntity(frm.getItemCd(), frm.getVersion());

		/* 販売品品検索 */
		ArticleAttributeQueue beanArticle = itemAttributeLogic
				.getArticleEntity(frm.getItemCd(), frm.getVersion());

		/* 購入品検索 */
		PurchaseAttributeQueue beanPurchase = itemAttributeLogic
				.getPurchaseEntity(frm.getItemCd(), frm.getVersion());

		String itemCd = null;
		BigDecimal version = null;
		String tantoCd = getLoginInfo(request).getTantoCd();

		/* 承認取消 */
		if (beanProduct != null) {
			beanProduct.setStatus(new BigDecimal("1"));
			beanProduct.setUpdatorCd(tantoCd);
			itemCd = beanProduct.getItemCd();
			version = beanProduct.getVersion();
		}

		if (beanArticle != null) {
			beanArticle.setStatus(new BigDecimal("1"));
			beanArticle.setUpdatorCd(tantoCd);
			itemCd = beanArticle.getItemCd();
			version = beanArticle.getVersion();
		}

		if (beanPurchase != null) {
			beanPurchase.setStatus(new BigDecimal("1"));
			beanPurchase.setUpdatorCd(tantoCd);
			itemCd = beanPurchase.getItemCd();
			version = beanPurchase.getVersion();
		}

		Boolean activate = Boolean.FALSE;

		/* 有効品目検索 */
		ItemDetail beanItem = itemAttributeLogic.getItemDetailEntity(itemCd,
			version);

		if (beanItem != null) {
			activate = Boolean.TRUE;
		}

		/* 登録処理 */
		itemAttributeLogic.approvalCancelUpdate(frm.getItemCd(), frm
				.getVersion(), frm.getHeadActiveDate(), frm
				.getProductDivision(), frm.getArticleDivision(), frm
				.getPurchaseDivision(), beanProduct, beanArticle, beanPurchase,
			activate);

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemAttributeLogic.getHeaderEntity(itemCd,
			version);

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		frm.setStatus(new BigDecimal("1"));

		if (beanProduct != null) {
			frm.setProductStatus(new BigDecimal("1"));
		}

		if (beanArticle != null) {
			frm.setArticleStatus(new BigDecimal("1"));
		}

		if (beanPurchase != null) {
			frm.setPurchaseStatus(new BigDecimal("1"));
		}

		frm.setHeadAttributeStatusName("入力中");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 標準販売単価更新
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward updatePrice(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("updatePrice.");
		}

		ItemAttributeForm frm = (ItemAttributeForm) form;

		BigDecimal price = AecNumberUtils.convertBigDecimal(frm
				.getStrSellingPrice());

		frm.setInputDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrInputDate()));

		/* 標準販売単価更新処理 */
		itemAttributeLogic.updatePrice(frm.getItemCd(), price, frm
				.getInputDate(), getLoginInfo(request).getTantoCd());

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemAttributeLogicを設定します。
	 * @param itemAttributeLogic itemAttributeLogic
	 */
	public void setItemAttributeLogic(
			final ItemAttributeLogic itemAttributeLogic) {
		this.itemAttributeLogic = itemAttributeLogic;
	}
}
