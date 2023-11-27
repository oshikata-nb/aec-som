/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.production;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailList;
import com.asahikaseieng.struts.AbstractForm;

/**
 * 製造計画入力 明細画面 Formクラス.
 * @author tosco
 */
public final class ProductionDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/** 新規用切り替えフラグ */
	private int insertFlg;

	/** 変更フラグ */
	private String dirtyFlg;

	//
	// インスタンスフィールド
	//
	/** 検索用引数：品目コード */
	private String srhItemCd;

	/** 検索用引数：生産計画年月 */
	private String srhOrderLet;

	/** 荷主 */
	private String strShipperDivision;

	/** 社内製造品/外注品区分 */
	private String strTypeDivision;

	/** 工場名 */
	private String productionLineName;

	/** 品目コード */
	private String itemCd;

	/** 品目名称 */
	private String itemName;

	/** 他社コード1 */
	private String otherCompanyCd1;

	/** 生産計画年月 */
	private String strOrderLet;

	/** 見込み数量合計 */
	private String sumExpectQty;

	/** 受注数量合計 */
	private String sumAcceptQty;

	/** 見込み数量合計 + 受注数量合計 */
	private String sumAllOrderQty;

	/** 荷姿 */
	private String styleOfPacking;

	/** リスト */
	private List<ProductionDetailList> detailList;

	/**
	 * コンストラクタ.詳細
	 */
	public ProductionDetailForm() {
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
		}
		if ("initNew".equals(getOp())) {
			clear();
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

		if ("init".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setDetailList(new ArrayList<ProductionDetailList>());
		}
		if ("initNew".equals(getOp())) {
			/* イレギュラーだけど、ここでリストをクリア */
			setDetailList(new ArrayList<ProductionDetailList>());
		}

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			validateUpdateList(request, errors);
		}
		if ("initNewInputed".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		setStrShipperDivision(null);
		setStrTypeDivision(null);
		setProductionLineName(null);
		setItemCd(null);
		setItemName(null);
		setStyleOfPacking(null);
		setOtherCompanyCd1(null);
		setStrOrderLet(null);

		setSumAcceptQty(null);
		setSumExpectQty(null);
		setSumAllOrderQty(null);

		setDetailList(new ArrayList<ProductionDetailList>());
	}

	/**
	 * 更新処理の入力チェック
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void validateUpdateList(final HttpServletRequest request,
			final ActionErrors errors) {

		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		List<ProductionDetailList> list = this.getDetailList();
		int index = 1;

		// 数値チェック
		for (ProductionDetailList bean : list) {
			CheckDigitUtilsLogic check = CheckDigitUtil
					.getCheckDigitUtils(request);

			// カンマを取り除く
			String value = StringUtils.replace(bean.getStrOrderExpectQty(),
				",", "");

			// 数値チェックを行う
			CheckDigitResult result = check.checkDigit(bean
					.getUnitOfOperationManagement(), value);
			// チェックの結果取得
			int code = result.getCode();

			if (code != CheckDigitUtilsLogic.SUCCESS) {
				// チェック結果がチェックOKではない場合
				NumberChkDisitDetail detail = result.getDetail();
				switch (code) {
				case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT: // 数値ではない場合
					errors.add("", new ActionMessage("errors.digit.number.row",
							rb.getString("item.production.order.expect.qty"),
							Integer.toString(index)));
					break;

				case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH: // 最大値を超えた場合
					errors.add("", new ActionMessage(
							"errors.digit.maxlength.row",
							rb.getString("item.production.order.expect.qty"),
							detail.getMaxLength().toString(), Integer
									.toString(index)));
					break;

				case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH: // 整数部桁数エラー
					errors.add("", new ActionMessage(
							"errors.digit.integer.row",
							rb.getString("item.production.order.expect.qty"),
							detail.getIntegerLength().toString(), Integer
									.toString(index)));
					break;

				case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH: // 小数部桁数エラー
					errors.add("", new ActionMessage(
							"errors.digit.decimal.row",
							rb.getString("item.production.order.expect.qty"),
							detail.getSmallnumLength().toString(), Integer
									.toString(index)));
					break;

				case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE: // 範囲チェックエラー
					errors.add("", new ActionMessage("errors.digit.rang.row",
							rb.getString("item.production.order.expect.qty"),
							detail.getLowerLimit().toString(), detail
									.getUpperLimit().toString(), Integer
									.toString(index)));
					break;

				default:
					break;
				}

			}
			index++;
		}
	}

	//
	// インスタンスメソッド
	//
	/**
	 * 新規用切替フラグを取得します。
	 * @return int 新規用切替フラグ
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 新規用切替フラグを設定します。
	 * @param insertFlg 新規用切替フラグ
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 変更フラグを取得します。
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 品目コード取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * 品目コードを設定します。
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 品目名称を取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称を設定します。
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * 生産計画年月を取得します。(String)
	 * @return strOrderLet
	 */
	public String getStrOrderLet() {
		return strOrderLet;
	}

	/**
	 * 生産計画年月を設定します。(String)
	 * @param strOrderLet 生産計画年月(String)
	 */
	public void setStrOrderLet(final String strOrderLet) {
		this.strOrderLet = strOrderLet;
	}

	/**
	 * 他社コード１を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * 他社コード１を設定します。
	 * @param otherCompanyCd1 他社コード１
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * 工場名を取得します。
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return productionLineName;
	}

	/**
	 * 工場名を設定します。
	 * @param productionLineName 工場名
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * 荷主を取得します。(String)
	 * @return strShipperDivision
	 */
	public String getStrShipperDivision() {
		return strShipperDivision;
	}

	/**
	 * 荷主を設定します。(String)
	 * @param strShipperDivision 荷主(String)
	 */
	public void setStrShipperDivision(final String strShipperDivision) {
		this.strShipperDivision = strShipperDivision;
	}

	/**
	 * 社内製造品/外注品区分を取得します。(String)
	 * @return strTypeDivision
	 */
	public String getStrTypeDivision() {
		return strTypeDivision;
	}

	/**
	 * 社内製造品/外注品区分を設定します。(String)
	 * @param strTypeDivision 社内製造品/外注品区分(String)
	 */
	public void setStrTypeDivision(final String strTypeDivision) {
		this.strTypeDivision = strTypeDivision;
	}

	/**
	 * 明細部リストを取得します。
	 * @return String 明細部リスト
	 */
	public List<ProductionDetailList> getDetailList() {
		return detailList;
	}

	/**
	 * 明細部リストを設定します。
	 * @param detailList 明細部リスト
	 */
	public void setDetailList(final List<ProductionDetailList> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (detailList != null) {
			count = detailList.size();
		}
		return count;
	}

	/**
	 * 検索用引数：品目コードを取得します。
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索用引数：品目コードを設定します。
	 * @param srhItemCd 検索用引数：品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 検索用引数：生産計画年月を取得します。
	 * @return srhOrderLet
	 */
	public String getSrhOrderLet() {
		return srhOrderLet;
	}

	/**
	 * 検索用引数：生産計画年月を設定します。
	 * @param srhOrderLet 検索用引数：生産計画年月
	 */
	public void setSrhOrderLet(final String srhOrderLet) {
		this.srhOrderLet = srhOrderLet;
	}

	/**
	 * 受注数量合計を取得します。
	 * @return sumAcceptQty
	 */
	public String getSumAcceptQty() {
		return sumAcceptQty;
	}

	/**
	 * 受注数量合計を設定します。
	 * @param sumAcceptQty 受注数量合計
	 */
	public void setSumAcceptQty(final String sumAcceptQty) {
		this.sumAcceptQty = sumAcceptQty;
	}

	/**
	 * 見込み数量合計 + 受注数量合計を取得します。
	 * @return sumAllOrderQty
	 */
	public String getSumAllOrderQty() {
		return sumAllOrderQty;
	}

	/**
	 * 見込み数量合計 + 受注数量合計を設定します。
	 * @param sumAllOrderQty 見込み数量合計 + 受注数量合計
	 */
	public void setSumAllOrderQty(final String sumAllOrderQty) {
		this.sumAllOrderQty = sumAllOrderQty;
	}

	/**
	 * 見込み数量合計を取得します。
	 * @return sumExpectQty
	 */
	public String getSumExpectQty() {
		return sumExpectQty;
	}

	/**
	 * 見込み数量合計を設定します。
	 * @param sumExpectQty 見込み数量合計
	 */
	public void setSumExpectQty(final String sumExpectQty) {
		this.sumExpectQty = sumExpectQty;
	}

	/**
	 * リスト件数を取得します。
	 * @return detailListLength
	 */
	public int getDetailListLength() {
		return detailList.size();
	}
}
