/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productionplan;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.production.ProductionList;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 生産計画入力一覧 Formクラス.
 * @author tosco
 * 
 */
public final class ProductionPlanForm extends AbstractSearchForm {

	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** 変更フラグ */
	private String dirtyFlg;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	static {
		// SYSTEM_PROPERTIESの取得
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		// 明細行数をSYSTEM_PROPERTIESから取得
		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		// 最大データ数をSYSTEM_PROPERTIESから取得
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// 検索用.
	//
	/** 検索入力：荷主 */
	private String srhShipperDivision;

	/** 検索入力：社内製造品/外注品区分 */
	private String srhTypeDivision;

	/** 検索入力：工場名 */
	private String srhProductionLine;

	/** 検索入力：品目コード */
	private String srhItemCd;

	/** 検索入力：品目名称 */
	private String srhItemName;

	/** 検索入力：他社コード１ */
	private String srhOtherCompanyCd1;

	/** 検索入力：生産計画年月 */
	private String srhOrderLet;

	/** 生産工場コンボボックス */
	private List<ComboBoxItems> productionLineCombo;

	/** EXCELダウンロードフラグ */
	private boolean excelDownloadFlg;

	//
	// インスタンスフィールド.
	//

	/** 検索結果格納用リスト */
	private List<ProductionList> searchList;

	/**
	 * コンストラクタ
	 */
	public ProductionPlanForm() {
	}

	/**
	 * ページの明細行数取得
	 * @return int ページの明細行数
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * 最大データ数取得
	 * @return int 最大データ数
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * PagerConditionClass取得
	 * @return BuyingPagerCondition BuyingPagerCondition
	 */
	protected Class getPagerConditionClass() {
		return ProductionPagerCondition.class;
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
		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);
	}

	/**
	 * validate
	 * 
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return ActionErrors
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("report".equals(getOp())) {
			// Validatorによる入力チェック
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * 初期化.
	 */
	public void clear() {
		// 仕入一覧検索結果リストのクリア
		setSearchList(new ArrayList<ProductionList>());

		// 検索入力：品目コード
		setSrhItemCd(null);
		// 検索入力：品目名称
		setSrhItemName(null);
		// 検索入力：他社コード１
		setSrhOtherCompanyCd1(null);
		// 検索入力：荷主
		setSrhShipperDivision(null);
		// 検索入力：社内製造品/外注品区分
		setSrhTypeDivision(null);
		// 検索入力：工場名
		setSrhProductionLine(null);
		// 検索入力：生産計画年月
		setSrhOrderLet(null);

		// 生産ラインコンボ
		setProductionLineCombo(null);
		// Excelダウンロードフラグ
		setExcelDownloadFlg(false);
	}

	/**
	 * 仕入一覧検索結果を取得します。
	 * @return searchList 仕入一覧検索結果
	 */
	public List<ProductionList> getSearchList() {
		return searchList;
	}

	/**
	 * 仕入一覧検索結果を設定します。
	 * 
	 * @param searchList 仕入一覧検索結果
	 */
	public void setSearchList(final List<ProductionList> searchList) {
		this.searchList = searchList;
	}

	//
	// 検索入力.
	//

	/**
	 * 検索入力：品目コード取得.
	 * @return String 品目コード
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力：品目コード設定.
	 * @param srhItemCd 品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
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
	 * 検索入力：品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 検索入力：品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * 検索入力：他社コード１
	 * @return String 他社コード１
	 */
	public String getSrhOtherCompanyCd1() {
		return this.srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：他社コード１.
	 * @param srhOtherCompanyCd1 他社コード１
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = srhOtherCompanyCd1;
	}

	/**
	 * 検索入力：生産計画年月取得
	 * @return srhOrderLet
	 */
	public String getSrhOrderLet() {
		return srhOrderLet;
	}

	/**
	 * 検索入力：生産計画年月設定
	 * @param srhOrderLet 生産計画年月
	 */
	public void setSrhOrderLet(final String srhOrderLet) {
		this.srhOrderLet = srhOrderLet;
	}

	/**
	 * 検索入力：工場名取得
	 * @return srhProductionLine
	 */
	public String getSrhProductionLine() {
		return srhProductionLine;
	}

	/**
	 * 検索入力：工場名設定
	 * @param srhProductionLine 工場名
	 */
	public void setSrhProductionLine(final String srhProductionLine) {
		this.srhProductionLine = srhProductionLine;
	}

	/**
	 * 検索入力：荷主取得
	 * @return srhShipperDivision
	 */
	public String getSrhShipperDivision() {
		return srhShipperDivision;
	}

	/**
	 * 検索入力：荷主設定
	 * @param srhShipperDivision 荷主
	 */
	public void setSrhShipperDivision(final String srhShipperDivision) {
		this.srhShipperDivision = srhShipperDivision;
	}

	/**
	 * 検索入力：社内製造品/外注品区分取得
	 * @return srhTypeDivision
	 */
	public String getSrhTypeDivision() {
		return srhTypeDivision;
	}

	/**
	 * 検索入力：社内製造品/外注品区分設定
	 * @param srhTypeDivision 社内製造品/外注品区分
	 */
	public void setSrhTypeDivision(final String srhTypeDivision) {
		this.srhTypeDivision = srhTypeDivision;
	}

	/**
	 * 生産工場コンボボックスを取得します。
	 * @return 生産工場コンボボックス
	 */
	public List<ComboBoxItems> getProductionLineCombo() {
		return productionLineCombo;
	}

	/**
	 * 生産工場コンボボックスを設定します。
	 * @param productionLineCombo 生産工場コンボボックス
	 */
	public void setProductionLineCombo(
			final List<ComboBoxItems> productionLineCombo) {
		this.productionLineCombo = productionLineCombo;
	}

	/**
	 * excelDownloadFlgを取得します。
	 * @return excelDownloadFlg
	 */
	public boolean isExcelDownloadFlg() {
		return excelDownloadFlg;
	}

	/**
	 * excelDownloadFlgを設定します。
	 * @param excelDownloadFlg excelDownloadFlg
	 */
	public void setExcelDownloadFlg(final boolean excelDownloadFlg) {
		this.excelDownloadFlg = excelDownloadFlg;
	}
}
