/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntity;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList;

/**
 * 出荷指図詳細 Formクラス.
 * @author tosco
 */
public final class ShippingDetailOtherForm extends AbstractShippingDetailForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド

	/** 出荷指図ヘッダ検索結果 */
	private ShippingDetailOtherEntity shippingBean;

	/** 品目リスト */
	private List<ShippingDetailOtherItemBean> itemList;

	/** 品目インデックス */
	private int itemIndex;

	/** 運送会社コード */
	private String tempCarryCd;

	/**
	 * コンストラクタ.出荷指図詳細（花王・その他）
	 */
	public ShippingDetailOtherForm() {
	}

	/**
	 * Beanの全てのプロパティをデフォルトの状態にリセット
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
//		} else if ("initNew".equals(getOp())) {
//			clear();
		} else if ("addItemRow".equals(getOp())) {
			clearCheck();
		} else if ("delItemRow".equals(getOp())) {
			clearCheck();
		} else if ("addRow".equals(getOp())) {
			clearCheck();
		} else if ("delRow".equals(getOp())) {
			clearCheck();
		} else if ("insert".equals(getOp())) {
			clearCheck();
		} else if ("update".equals(getOp())) {
			clearCheck();
		}

	}

	/**
	 * 入力データの検証
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors 検証エラー内容
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 品目、明細のチェック
			this.validateItemForInsert(errors, request);
		} else if ("addRow".equals(getOp())) {
			// 品目、明細のチェック
			errors = new ActionErrors();
			this.validateAddRow(errors, request);
		} else if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 品目、明細のチェック
			validateItemForUpdate(errors, request);
		} else if ("initNew".equals(getOp())) {
			// イレギュラーだけどココでクリア
			clear();
		}

		return errors;
	}

	/**
	 * 入力データ（品目リスト）の検証
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateItemForInsert(final ActionErrors errors,
			final HttpServletRequest request) {

		List<ShippingDetailOtherItemBean> itemList = getItemList();
		int itemLineNo = 0;
		// 品目数分チェック
		for (ShippingDetailOtherItemBean bean : itemList) {
			itemLineNo++;

			// 品目コード
			validateItemCd(bean.getItemCd(), itemLineNo, errors);
			// 明細リスト
			List<ShippingDetailOtherList> detailList = bean.getDetailList();

			if (detailList == null || detailList.size() == 0) {
				// 明細が1件も存在しない場合、エラーとする
				errors.add("", new ActionMessage(
						"errors.shipping.required.detail.row",
						getErrorDetailInfo(bean.getItemCd())));
			} else {
				// 明細数分チェック
				int detailLineNo = 0;
				for (ShippingDetailOtherList detailBean : detailList) {
					detailLineNo++;
					String message = getErrorDetailInfo(bean.getItemCd(),
						detailLineNo);
					// ロケーションコード
					validateLocationCd(detailBean.getLocationCd(), message,
						errors);
					// 指図量
					super.validateShippingInstruction(detailBean, bean
							.getUnitDivision(), null, null, message, request,
						errors);
				}
			}
		}
	}

	/**
	 * 入力データ（品目リスト）の検証（更新時）
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateItemForUpdate(final ActionErrors errors,
			final HttpServletRequest request) {

		// 更新時１件のみなので最初の１件のみをチェックする
		ShippingDetailOtherItemBean bean = getItemList().get(0);

		// 品目コード
		validateItemCd(bean.getItemCd(), errors);
		// 明細リスト
		List<ShippingDetailOtherList> detailList = bean.getDetailList();

		if (detailList == null || detailList.size() == 0) {
			// 明細が1件も存在しない場合、エラーとする
			errors.add("", new ActionMessage(
					"errors.shipping.required.detail.row", ""));
		} else {
			// 明細数分チェック
			int detailLineNo = 0;
			for (ShippingDetailOtherList detailBean : detailList) {
				detailLineNo++;
				// ロケーションコード
				super.validateLocationCd(detailBean.getLocationCd(),
					detailLineNo, errors);
				// 指図量
				super.validateShippingInstruction(detailBean, bean
						.getUnitDivision(), null, null, getErrorDetailInfo(
					null, detailLineNo), request, errors);
			}
		}
	}

	/**
	 * ロケーションの検証(品目及び行番号指定)
	 * @param locationCd ロケーションコード
	 * @param message 置換文字列
	 * @param errors 検証エラー内容
	 */
	private void validateLocationCd(final String locationCd,
			final String message, final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(locationCd)) {
			errors.add("", new ActionMessage(
					"errors.shipping.required.select.row", rb
							.getString("item.shipping.location"), message));
		}
	}

	/**
	 * エラーメッセージ詳細情報取得（行番号あり）
	 * @param itemCd 品目コード
	 * @param lineNo 行番号
	 */
	private String getErrorDetailInfo(final String itemCd, final int lineNo) {
		StringBuffer msgBuf = new StringBuffer();
		if (!StringUtils.isEmpty(itemCd)) {
			msgBuf.append(getErrorDetailInfo(itemCd));
		}
		msgBuf.append(lineNo).append("行目");
		return msgBuf.toString();
	}

	/**
	 * エラーメッセージ詳細情報取得
	 * @param itemCd 品目コード
	 */
	private String getErrorDetailInfo(final String itemCd) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);
		StringBuffer msgBuf = new StringBuffer("");
		msgBuf.append(rb.getString("item.shipping.item.cd")).append("[")
				.append(itemCd).append("] ");
		return msgBuf.toString();
	}

	/**
	 * 行追加時入力データの検証
	 * 
	 * @param errors 検証エラー内容
	 * @param request HttpServletRequest
	 */
	private void validateAddRow(final ActionErrors errors,
			final HttpServletRequest request) {
		int itemIndex = getItemIndex();
		ShippingDetailOtherItemBean bean = getItemList().get(itemIndex);
		// 品目コード
		this.validateItemCd(bean.getItemCd(), errors);
	}

	/**
	 * クリア処理.
	 */
	protected void clear() {
		super.clear();

		/** 品目リストのクリア */
		setItemList(new ArrayList<ShippingDetailOtherItemBean>());

		/** 品目インデックス */
		setItemIndex(-1);
	}

	//
	// インスタンスメソッド
	//

	/**
	 * 出荷指図新規：itemListを取得します。
	 * @return itemList
	 */
	public List<ShippingDetailOtherItemBean> getItemList() {
		return itemList;
	}

	/**
	 * 出荷指図新規： itemListを設定します。
	 * 
	 * @param itemList itemList
	 */
	public void setItemList(final List<ShippingDetailOtherItemBean> itemList) {
		this.itemList = itemList;
	}

	/**
	 * 出荷指図ヘッダ検索結果取得.
	 * @return String 出荷指図ヘッダ検索結果
	 */
	public ShippingDetailOtherEntity getShippingBean() {
		return this.shippingBean;
	}

	/**
	 * 出荷指図ヘッダ検索結果設定.
	 * @param shippingBean 出荷指図ヘッダ検索結果
	 */
	public void setShippingBean(final ShippingDetailOtherEntity shippingBean) {
		this.shippingBean = shippingBean;
	}

	/**
	 * 品目インデックスを取得します。
	 * @return int 品目インデックス
	 */
	public int getItemIndex() {
		return itemIndex;
	}

	/**
	 * 品目インデックスを設定します。
	 * @param itemIndex 品目インデックス
	 */
	public void setItemIndex(final int itemIndex) {
		this.itemIndex = itemIndex;
	}

	/**
	 * 運送会社を仮保持を取得する。
	 * @return 運送会社を仮保持
	 */
	public String getTempCarryCd() {
		return tempCarryCd;
	}

	/**
	 * 運送会社を仮保持の検証
	 * @param tempCarryCd 運送会社を仮保持
	 */
	public void setTempCarryCd(final String tempCarryCd) {
		this.tempCarryCd = tempCarryCd;
	}

	/**
	 * 品目数を取得する。
	 * @return 品目数
	 */
	public int getItemCount() {
		int count = 0;
		if (itemList != null) {
			count = itemList.size();
		}
		return count;
	}

	/**
	 * 品目コードの検証
	 * @param itemCd 品目コード
	 * @param errors 検証エラー内容
	 */
	private void validateItemCd(final String itemCd, final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(itemCd)) {
			errors.add("", new ActionMessage("errors.required", rb
					.getString("item.shipping.item.cd")));
		}
	}

	/**
	 * 品目コードの検証(品目の行番号指定)
	 * @param itemCd 品目コード
	 * @param lineNo 品目の行番号
	 * @param errors 検証エラー内容
	 */
	private void validateItemCd(final String itemCd, final int lineNo,
			final ActionErrors errors) {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.APPLICATION_PROPERTIES);

		// 必須チェック
		if (StringUtils.isEmpty(itemCd)) {
			errors.add("", new ActionMessage("errors.required.row", rb
					.getString("item.shipping.item.cd"), Integer
					.toString(lineNo)));
		}
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getItemList() != null) {
			for (ShippingDetailOtherItemBean bean : getItemList()) {
				bean.setCheckFlg(Boolean.FALSE);
				if (bean.getDetailList() != null) {
					for (ShippingDetailOtherList detail : bean.getDetailList()) {
						detail.setCheckFlg(Boolean.FALSE);
					}
				}
			}
		}
	}
}
