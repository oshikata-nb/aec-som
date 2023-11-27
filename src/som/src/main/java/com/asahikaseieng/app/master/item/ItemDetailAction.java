/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.CommonLogic;
import com.asahikaseieng.dao.entity.master.itemqueue.ItemQueue;
import com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory.ItemCategoryListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.master.names.NamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 品目基本 Actionクラス.
 * @author t0011036
 */
public final class ItemDetailAction extends AbstractAction {

	private ItemDetailLogic itemDetailLogic;
	
	private CommonLogic commonLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemDetailAction() {
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

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* 運用管理区分セット */
		setUnitOfOperationManagementCombobox(frm);

		/* 在庫管理区分セット */
		setUnitOfStockControlCombobox(frm);

		/* 端数管理区分セット */
		setUnitOfFractionManagementCombobox(frm);

		/* 品目分類セット */
		setItemCategoryCombobox(frm);
		
		//20190819 軽減税率対応 
		/* 売上課税区分セット */
		setSalesTaxCategoryCombobox(frm);
		
		/* 仕入課税区分セット */
		setPurchaseTaxCategoryCombobox(frm);
		//20190819 end

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemDetailLogic.getHeaderEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* 初期検索 */
		ItemQueueDetail beanDetail = itemDetailLogic.getDetailEntity(frm
				.getItemCd(), frm.getVersion());

		/* 日付文字列に変換 */
		beanDetail.setStrActiveDate(AecDateUtils.dateFormat(beanDetail
				.getActiveDate(), "yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列に変換 */
		beanDetail.setStrNumberOfInsertions(checker.format(frm
				.getUnitDivision(), beanDetail.getNumberOfInsertions()));
		beanDetail.setStrAllUpWeight(checker.format(frm.getUnitDivision(),
			beanDetail.getAllUpWeight()));
		beanDetail.setStrKgOfFractionManagement(checker.format(frm
				.getUnitDivision(), beanDetail.getKgOfFractionManagement()));
		beanDetail.setStrKgConversionCoefficient(checker.format(frm
				.getUnitDivision(), beanDetail.getKgConversionCoefficient()));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);
		IgnoreCaseBeanUtils.copyProperties(frm, beanDetail);
		
		//種別によって課税区分の表示を切り替える
		//0:製品の場合は売上のみ、1:原料　2:包材の場合は仕入のみ、3:中間品の場合は表示しない、、左記以外はどちらも表示する
		String taxCategoryFlg = commonLogic.getTaxCategoryFlg(beanDetail.getTypeDivision().toString());
		frm.setTaxCategoryFlg(taxCategoryFlg);

		return mapping.findForward("success");
	}

	/**
	 * 運用管理単位リスト取得
	 * @param frm 画面データ
	 */
	public void setUnitOfOperationManagementCombobox(final ItemDetailForm frm) {
		/* 単位マスタの区分データを取得 */
		List<NamesListForComboboxes> list = itemDetailLogic.getUnitList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setUnitOfOperationManagementLabels(labels);
		frm.setUnitOfOperationManagementValues(values);
	}

	/**
	 * 在庫管理単位リスト取得
	 * @param frm 画面データ
	 */
	public void setUnitOfStockControlCombobox(final ItemDetailForm frm) {
		/* 単位マスタの区分データを取得 */
		List<NamesListForComboboxes> list = itemDetailLogic.getUnitList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setUnitOfStockControlLabels(labels);
		frm.setUnitOfStockControlValues(values);
	}

	/**
	 * 端数管理単位リスト取得
	 * @param frm 画面データ
	 */
	public void setUnitOfFractionManagementCombobox(final ItemDetailForm frm) {
		/* 単位マスタの区分データを取得 */
		List<NamesListForComboboxes> list = itemDetailLogic.getUnitList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setUnitOfFractionManagementLabels(labels);
		frm.setUnitOfFractionManagementValues(values);
	}

	/**
	 * 品目分類リスト取得
	 * @param frm 画面データ
	 */
	public void setItemCategoryCombobox(final ItemDetailForm frm) {
		/* 品目分類マスタの分類データを取得 */
		List<ItemCategoryListForComboboxes> list = itemDetailLogic
				.getItemCategoryList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getItemCategoryName();
			values[i] = list.get(i).getItemCategory();
		}

		frm.setItemCategoryLabels(labels);
		frm.setItemCategoryValues(values);
	}
	
	//20190819 軽減税率対応 
	/**
	 * 売上課税区分リスト取得
	 * @param frm 画面データ
	 */
	public void setSalesTaxCategoryCombobox(final ItemDetailForm frm) {
		/* 売上課税区分マスタの分類データを取得 */
		List<NamesListForComboboxes> list = itemDetailLogic.getTaxCategoryList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setSalesTaxCategoryLabels(labels);
		frm.setSalesTaxCategoryValues(values);
	}
	
	/**
	 * 仕入課税区分リスト取得
	 * @param frm 画面データ
	 */
	public void setPurchaseTaxCategoryCombobox(final ItemDetailForm frm) {
		/* 品目分類マスタの分類データを取得 */
		List<NamesListForComboboxes> list = itemDetailLogic.getTaxCategoryList();

		String[] values;
		String[] labels;

		labels = new String[list.size()];
		values = new String[list.size()];

		/* コンボボックスアイテム設定処理 */
		for (int i = 0; i < list.size(); i++) {
			labels[i] = list.get(i).getName01();
			values[i] = list.get(i).getNameCd();
		}

		frm.setPurchaseTaxCategoryLabels(labels);
		frm.setPurchaseTaxCategoryValues(values);
	}
	//20190819 end

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

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 品目名称 全角チェック */
		if (!checkZenkaku(frm.getItemName())) {
			/* エラーメッセージ */
			saveError(request, "errors.item.item.name.byte.length");
			return mapping.findForward("success");
		}

		/* 日付文字列を日付に変換 */
		frm.setActiveDate(AecDateUtils.getTimestampYmdFormat(frm
				.getStrActiveDate()));

		if (frm.getInsertFlg().equals("versionup")) {
			/* 今日を含むより過去の日付は入力できない */
			if (0 <= AecDateUtils.getTimestampYmdFormat(
				AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(),
					"yyyy/MM/dd")).compareTo(frm.getActiveDate())) {
				/* エラーメッセージ */
				saveError(request, "errors.item.less.active.date");
				return mapping.findForward("success");
			}
		} else {
			/* 今日より過去の日付は入力できない */
			if (0 < AecDateUtils.getTimestampYmdFormat(
				AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(),
					"yyyy/MM/dd")).compareTo(frm.getActiveDate())) {
				/* エラーメッセージ */
				saveError(request, "errors.item.less.equal.active.date");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getParentItemCd())) {
			/* 親品目コードチェック */
			ItemQueueLastVersion beanItem = itemDetailLogic.getLastVersion(frm
					.getParentItemCd());

			if (beanItem == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.parent.item.cd");
				return mapping.findForward("success");
			}
		}

		if (!StringUtils.isEmpty(frm.getDefaultLocation())) {
			/* 基準保管場所チェック */
			LocationDetail beanDefaultLocation = itemDetailLogic
					.getLocationEntity(frm.getDefaultLocation());

			if (beanDefaultLocation == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.default.location");
				return mapping.findForward("success");
			} else if (frm.getTypeDivision().equals(new BigDecimal("3"))) {
				/* 中間品の場合は在庫可能ロケ以外は選択できない */
				if (beanDefaultLocation.getAvailableFlg().equals(
					BigDecimal.ZERO)) {
					/* エラーメッセージ */
					saveError(request,
						"errors.nodata.item.default.location.available");
					return mapping.findForward("success");
				}
			}
		}

		if (!StringUtils.isEmpty(frm.getOrderLocation())) {
			/* 発注まとめ場所チェック */
			LocationDetail beanOrderLocation = itemDetailLogic
					.getLocationEntity(frm.getOrderLocation());

			if (beanOrderLocation == null) {
				/* エラーメッセージ */
				saveError(request, "errors.nodata.item.order.location");
				return mapping.findForward("success");
			}
		}

		/* 数値文字列を数値に変換 */
		frm.setNumberOfInsertions(AecNumberUtils.convertBigDecimal(frm
				.getStrNumberOfInsertions()));
		frm.setAllUpWeight(AecNumberUtils.convertBigDecimal(frm
				.getStrAllUpWeight()));
		frm.setKgOfFractionManagement(AecNumberUtils.convertBigDecimal(frm
				.getStrKgOfFractionManagement()));
		frm.setKgConversionCoefficient(AecNumberUtils.convertBigDecimal(frm
				.getStrKgConversionCoefficient()));

		/* 登録データをセット */
		ItemQueue bean = insertItemQueue(frm, getLoginInfo(request)
				.getTantoCd());

		if (StringUtils.isEmpty(frm.getInsertFlg())
				&& StringUtils.isEmpty(frm.getCopyFlg())) {
			if (frm.getVersion().equals(bean.getVersion())) {
				/* 更新処理を実行 */
				itemDetailLogic.update(bean, frm.getReason());
			} else {
				/* 追加処理を実行 */
				itemDetailLogic.insert(bean, frm.getReason(), frm
						.getInsertFlg(), frm.getCopyFlg(), frm.getCopyItemCd(),
					frm.getCopyVersion());
			}
		} else if (frm.getInsertFlg().equals("true")
				|| frm.getCopyFlg().equals("true")
				|| frm.getInsertFlg().equals("versionup")) {
			/* 追加処理を実行 */
			itemDetailLogic.insert(bean, frm.getReason(), frm.getInsertFlg(),
				frm.getCopyFlg(), frm.getCopyItemCd(), frm.getCopyVersion());
		} else if (frm.getVersion().equals(bean.getVersion())) {
			/* 更新処理を実行 */
			itemDetailLogic.update(bean, frm.getReason());
		} else {
			/* 追加処理を実行 */
			itemDetailLogic.insert(bean, frm.getReason(), frm.getInsertFlg(),
				frm.getCopyFlg(), frm.getCopyItemCd(), frm.getCopyVersion());
		}

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemDetailLogic.getHeaderEntity(bean
				.getItemCd(), bean.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		frm.setItemCd(bean.getItemCd());
		frm.setVersion(bean.getVersion());
		frm.setStatus(bean.getStatus());
		frm.setUpdateDate(bean.getUpdateDate());
		frm.setInsertFlg(null);
		frm.setCopyFlg(null);
		
		//20190819 軽減税率対応
		/* 売上課税区分セット */
		setSalesTaxCategoryCombobox(frm);
		
		/* 仕入課税区分セット */
		setPurchaseTaxCategoryCombobox(frm);

		//種別によって課税区分の表示を切り替える
		//0:製品の場合は売上のみ、1:原料　2:包材の場合は仕入のみ、3:中間品の場合は表示しない、、左記以外はどちらも表示する
		String taxCategoryFlg = commonLogic.getTaxCategoryFlg(bean.getTypeDivision().toString());
		frm.setTaxCategoryFlg(taxCategoryFlg);
		//20190819 end

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/**
	 * 全角チェック
	 * @param str チェック元文字列
	 * @return true:全角 false:半角含む
	 */
	private boolean checkZenkaku(final String str) {
		byte[] bytes;
		bytes = str.getBytes();

		/* length の2倍を取得 */
		int lentime = str.length() * 2;

		StringBuffer sb = new StringBuffer(str);

		for (int i = 0; i < str.length(); i++) {
			if ('\n' == sb.charAt(i)) {
				lentime -= 2;
			}
		}

		/* length の2倍と引数のバイト数が一緒であれば全て全角 */
		if (lentime == bytes.length) {
			return true;
		}

		return false;
	}

	/**
	 * 削除処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 削除処理を実行 */
		itemDetailLogic.delete(frm.getItemCd(), frm.getVersion());

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 追加処理用のItemQueueを作成する.
	 * @param bean 画面データ
	 * @param tantoCd 担当者コード
	 * @return ItemQueue
	 * @throws InvocationTargetException InvocationTargetException
	 * @throws IllegalAccessException IllegalAccessException
	 */
	private ItemQueue insertItemQueue(final ItemDetailForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {
		ItemQueue newBean = new ItemQueue();
		BigDecimal version = frm.getVersion();

		if (StringUtils.isEmpty(frm.getInsertFlg())
				|| frm.getInsertFlg().equals("versionup")) {
			try {
				newBean = itemDetailLogic.getEntity(frm.getItemCd(), frm
						.getVersion());

				/* 有効日が変更された場合はバージョンを上げる */
				if (!newBean.getActiveDate().equals(frm.getActiveDate())) {
					/* 最新バージョン取得 */
					ItemQueueLastVersion beanLast = itemDetailLogic
							.getLastVersion(frm.getItemCd());

					if (beanLast == null) {
						version = new BigDecimal("1");
					} else {
						version = beanLast.getVersion()
								.add(new BigDecimal("1"));
					}
				}
			} catch (NoDataException e) {
				return null;
			}
		}

		/* 値を更新用Beanにコピる */
		IgnoreCaseBeanUtils.copyProperties(newBean, frm);

		if (frm.getInsertFlg().equals("true")) {
			/* 最新品目コード取得 */
			String itemCd = itemDetailLogic.getNewItemCd();

			newBean.setItemCd(newBean.getShipperDivision().toString()
					+ newBean.getTypeDivision() + itemCd);
			newBean.setVersion(new BigDecimal("1"));
			newBean.setStatus(new BigDecimal("1")); /* 入力中 */
		} else if (frm.getInsertFlg().equals("versionup")) {
			/* 最新バージョン取得 */
			ItemQueueLastVersion beanLast = itemDetailLogic.getLastVersion(frm
					.getCopyItemCd());

			if (beanLast == null) {
				version = new BigDecimal("1");
			} else {
				version = beanLast.getVersion().add(new BigDecimal("1"));
			}

			newBean.setItemCd(frm.getCopyItemCd());
			newBean.setVersion(version);
			newBean.setStatus(new BigDecimal("1")); /* 入力中 */
		} else {
			newBean.setVersion(version);
		}

		/* 未使用項目に初期値をセット */
		newBean.setItemType(new BigDecimal("0"));
		newBean.setPhantomDivision(new BigDecimal("0"));
		newBean.setBulkDivision(new BigDecimal("0"));
		newBean.setCostDivision(new BigDecimal("0"));
		newBean.setNewFlg(new BigDecimal("0"));

		if (newBean.getNumberOfInsertions() == null) {
			newBean.setNumberOfInsertions(new BigDecimal("0"));
		}

		if (newBean.getAllUpWeight() == null) {
			newBean.setAllUpWeight(new BigDecimal("0"));
		}

		if (newBean.getKgConversionCoefficient() == null) {
			newBean.setKgConversionCoefficient(new BigDecimal("0"));
		}

		switch (newBean.getTypeDivision().intValue()) {
		case 0: /* 製品 */
			newBean.setArticleDivision(new BigDecimal("1")); /* 商品 */
			newBean.setProductDivision(new BigDecimal("1")); /* 製品 */
			newBean.setPurchaseDivision(new BigDecimal("0")); /* 該当なし */
			break;
		case 1: /* 原料 */
			newBean.setArticleDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("1")); /* 原料 */
			break;
		case 2: /* 包材 */
			newBean.setArticleDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("2")); /* 材料 */
			break;
		case 3: /* 中間品 */
			newBean.setArticleDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setProductDivision(new BigDecimal("2")); /* 中間品 */
			newBean.setPurchaseDivision(new BigDecimal("0")); /* 該当なし */
			break;
		case 4: /* 仕入直送品 */
			newBean.setArticleDivision(new BigDecimal("2")); /* 仕入商品 */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("6")); /* 製品 */
			break;
		case 5: /* 仕入在庫品 */
			newBean.setArticleDivision(new BigDecimal("2")); /* 仕入商品 */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("6")); /* 製品 */
			break;
		case 6: /* 外注品（直送） */
			newBean.setArticleDivision(new BigDecimal("1")); /* 商品 */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("6")); /* 製品 */
			break;
		case 7: /* 外注品（非直送） */
			newBean.setArticleDivision(new BigDecimal("1")); /* 商品 */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("6")); /* 製品 */
			break;
		case 9: /* その他 */
			newBean.setArticleDivision(new BigDecimal("1")); /* 商品 */
			newBean.setProductDivision(new BigDecimal("1")); /* 原料 */
			newBean.setPurchaseDivision(new BigDecimal("1")); /* 製品 */
			break;
		default:
			newBean.setArticleDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setProductDivision(new BigDecimal("0")); /* 該当なし */
			newBean.setPurchaseDivision(new BigDecimal("0")); /* 該当なし */
			break;
		}

		/* 研究用 */
		if (newBean.getResearchItem().equals(new BigDecimal("2"))) {
			newBean.setStatus(new BigDecimal("4"));
		} else if (newBean.getStatus().equals(new BigDecimal("4"))) {
			newBean.setStatus(new BigDecimal("1"));
		}

		/* コピーしきれなかった分は手で */
		newBean.setInputDate(newBean.getCurrentTimestamp());
		newBean.setInputorCd(tantoCd);
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
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setInsertFlg("true");
		frm.setVersion(new BigDecimal("1"));

		/* 運用管理区分セット */
		setUnitOfOperationManagementCombobox(frm);

		/* 在庫管理区分セット */
		setUnitOfStockControlCombobox(frm);

		/* 端数管理区分セット */
		setUnitOfFractionManagementCombobox(frm);

		/* 品目分類セット */
		setItemCategoryCombobox(frm);

		return mapping.findForward("success");
	}

	/**
	 * コピー処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward copy(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("copy.");
		}

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 権限取得 */
		getControlAuthority(request, frm, Constants.MENU_ID_ITEM,
			Constants.TAB_ID_ITEM_DETAIL);

		if (!frm.getViewAuthority()) {
			/* エラーメッセージ */
			saveError(request, "errors.not.view.authority");
			return mapping.findForward("back");
		}

		/* ここを通る場合は新規処理 */
		frm.setInsertFlg("true");

		frm.setCopyItemCd(frm.getItemCd());
		frm.setCopyVersion(frm.getVersion());
		frm.setItemCd(null);
		frm.setHeadActivate(null);
		frm.setHeadDetailStatusName(null);
		frm.setHeadAttributeStatusName(null);
		frm.setUpdateDate(null);
		frm.setStatus(null);
		frm.setCopyFlg("true");

		return mapping.findForward("success");
	}

	/**
	 * バージョンアップ
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward versionup(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("versionup.");
		}

		ItemDetailForm frm = (ItemDetailForm) form;

		/* ここを通る場合は新規処理 */
		frm.setInsertFlg("versionup");

		frm.setCopyItemCd(frm.getItemCd());
		frm.setCopyVersion(frm.getVersion());
		frm.setHeadActivate(null);
		frm.setHeadDetailStatusName(null);
		frm.setHeadAttributeStatusName(null);
		frm.setUpdateDate(null);
		frm.setStatus(null);
		frm.setCopyFlg("true");

		return mapping.findForward("success");
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

		ItemDetailForm frm = (ItemDetailForm) form;

		ItemQueue bean = itemDetailLogic.getEntity(frm.getItemCd(), frm
				.getVersion());

		/* 承認依頼中 */
		bean.setStatus(new BigDecimal("2"));
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());

		/* 登録処理 */
		itemDetailLogic.itemQueueUpdate(bean);

		frm.setItemName(bean.getItemName());
		frm.setItemSubName(bean.getItemSubName());
		frm.setResearchItem(bean.getResearchItem());
		frm.setActiveDate(bean.getActiveDate());
		frm.setShipperDivision(bean.getShipperDivision());
		frm.setTypeDivision(bean.getTypeDivision());
		//20190819 軽減税率対応 
		frm.setSalesTaxCategory(bean.getSalesTaxCategory());
		frm.setPurchaseTaxCategory(bean.getPurchaseTaxCategory());
		//20190819 軽減税率対応 end
		//20210121 品目運賃区分追加
		frm.setItemDivision(bean.getItemDivision());
		frm.setOtherCompanyCd1(bean.getOtherCompanyCd1());
		frm.setStyleOfPacking(bean.getStyleOfPacking());
		frm.setNumberOfInsertions(bean.getNumberOfInsertions());
		frm.setAllUpWeight(bean.getAllUpWeight());
		frm.setUnitOfOperationManagement(bean.getUnitOfOperationManagement());
		frm.setUnitOfStockControl(bean.getUnitOfStockControl());
		frm.setKgOfFractionManagement(bean.getKgOfFractionManagement());
		frm.setUnitOfFractionManagement(bean.getUnitOfFractionManagement());
		frm.setKgConversionCoefficient(bean.getKgConversionCoefficient());
		frm.setItemCategory(bean.getItemCategory());
		frm.setParentItemCd(bean.getParentItemCd());
		frm.setSpotDivision(bean.getSpotDivision());
		frm.setStockDivision(bean.getStockDivision());
		frm.setDefaultLocation(bean.getDefaultLocation());
		frm.setOrderLocation(bean.getOrderLocation());
		frm.setLotDivision(bean.getLotDivision());
		frm.setWaterDivision(bean.getWaterDivision());
		frm.setStatus(new BigDecimal("2"));
		frm.setHeadDetailStatusName("承認依頼中");

		/* 日付文字列に変換 */
		frm.setStrActiveDate(AecDateUtils.dateFormat(bean.getActiveDate(),
			"yyyy/MM/dd"));

		/* 数値桁数チェック部品呼び出し */
		CheckDigitUtilsLogic checker = CheckDigitUtil
				.getCheckDigitUtils(request);

		/* 数値文字列に変換 */
		frm.setStrNumberOfInsertions(checker.format(frm.getUnitDivision(), frm
				.getNumberOfInsertions()));
		frm.setStrAllUpWeight(checker.format(frm.getUnitDivision(), frm
				.getAllUpWeight()));
		frm.setStrKgOfFractionManagement(checker.format(frm.getUnitDivision(),
			frm.getKgOfFractionManagement()));
		frm.setStrKgConversionCoefficient(checker.format(frm.getUnitDivision(),
			frm.getKgConversionCoefficient()));

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

		ItemDetailForm frm = (ItemDetailForm) form;

		ItemQueue bean = itemDetailLogic.getEntity(frm.getItemCd(), frm
				.getVersion());

		/* 承認 */
		bean.setStatus(new BigDecimal("3"));
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());

		/* 承認登録処理 */
		itemDetailLogic.approvalUpdate(bean);

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemDetailLogic.getHeaderEntity(bean
				.getItemCd(), bean.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		frm.setStatus(new BigDecimal("3"));
		frm.setHeadDetailStatusName("承認済み");

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

		ItemDetailForm frm = (ItemDetailForm) form;

		ItemQueue bean = itemDetailLogic.getEntity(frm.getItemCd(), frm
				.getVersion());

		/* 否認 */
		bean.setStatus(new BigDecimal("1"));
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());

		/* 否認処理 */
		itemDetailLogic.approvalCancelUpdate(bean, Boolean.FALSE);

		frm.setStatus(new BigDecimal("1"));
		frm.setHeadDetailStatusName("入力中");

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

		ItemDetailForm frm = (ItemDetailForm) form;

		/* 品目検索 */
		ItemQueue bean = itemDetailLogic.getEntity(frm.getItemCd(), frm
				.getVersion());

		/* 承認取消 */
		bean.setStatus(new BigDecimal("1"));
		bean.setUpdatorCd(getLoginInfo(request).getTantoCd());

		Boolean activate = Boolean.FALSE;

		/* 有効品目検索 */
		ItemDetail beanItem = itemDetailLogic.getItemDetailEntity(frm
				.getItemCd(), frm.getVersion());

		if (beanItem != null) {
			activate = Boolean.TRUE;
		}

		/* 承認取消処理 */
		itemDetailLogic.approvalCancelUpdate(bean, activate);

		/* ヘッダー検索 */
		ItemQueueHeader beanHeader = itemDetailLogic.getHeaderEntity(bean
				.getItemCd(), bean.getVersion());

		/* 日付文字列に変換 */
		beanHeader.setStrHeadActiveDate(AecDateUtils.dateFormat(beanHeader
				.getHeadActiveDate(), "yyyy/MM/dd"));

		/* BeanをFormにコピーする */
		IgnoreCaseBeanUtils.copyProperties(frm, beanHeader);

		frm.setStatus(new BigDecimal("1"));
		frm.setHeadDetailStatusName("入力中");

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemDetailLogicを設定します。
	 * @param itemDetailLogic itemDetailLogic
	 */
	public void setItemDetailLogic(final ItemDetailLogic itemDetailLogic) {
		this.itemDetailLogic = itemDetailLogic;
	}
	
	/**
	 * @param commonLogic セットする commonLogic
	 */
	public void setCommonLogic(CommonLogic commonLogic) {
		this.commonLogic = commonLogic;
	}
}
