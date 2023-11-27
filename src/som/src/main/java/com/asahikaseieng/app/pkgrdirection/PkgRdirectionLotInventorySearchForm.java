/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 包装実績－ロット検索画面 Formクラス.
 * @author tosco
 */
public final class PkgRdirectionLotInventorySearchForm extends
		AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/** ページの明細行数 */
	private static final int PAGE_ROW;

	/** 最大データ数 */
	private static final int DATA_ROW;

	/** 変更フラグ */
	private String dirtyFlg;

	/** 指図区分 * */
	private String directionDivision;

	/** 包装指図番号 * */
	private String directionNo;

	/** ステップNO. */
	private String stepNo;

	/** 行NO. */
	private String lineNo;

	/** 検索品目コード */
	private String srhItemCd;

	/** 品目名称 */
	private String srhItemName;

	/** ロット番号 */
	private String srhLotNo;

	/** 使用数 */
	private String useQty;

	/** total数 */
	private String totalQty;

	/** 行番号 */
	private String line;

	/** 小数点桁数 */
	private String decimalPoint;

	/** 端数区分 */
	private String roundDivision;

	/** 検索結果リスト */
	private List<PkgRdirectionLotInventorySearchList> searchList;

	/** 登録対象リスト */
	private List<PkgRdirectionLotInventorySearchList> registList;

	static {
		PAGE_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("linage.search.pkgdirection.lot.search"));
		DATA_ROW = Integer.parseInt(Constants.RB_SYSTEM_PROPERTIES
				.getString("threshold.search.pkgdirection.lot.search"));
	}

	/**
	 * コンストラクタ.
	 */
	public PkgRdirectionLotInventorySearchForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return PkgRdirectionLotInventorySearchListPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
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
	@Override
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if ("regist".equals(getOp())) {
			// 登録時チェック処理
			validateRegist(errors, request);
		}
		return errors;
	}

	/**
	 * 初期化
	 */
	public void clear() {
		setDirtyFlg(null);
		setDirectionDivision(null);
		setDirectionNo(null);
		setStepNo(null);
		setLineNo(null);
		setSrhItemCd(null);
		setSrhItemName(null);
		setSrhLotNo(null);
		setUseQty(null);
		setTotalQty(null);
		setLine(null);
		setDecimalPoint(null);
		setRoundDivision(null);
		setSearchList(new ArrayList<PkgRdirectionLotInventorySearchList>());
		setRegistList(new ArrayList<PkgRdirectionLotInventorySearchList>());
	}

	/**
	 * 登録時チェック処理.
	 * @param request HttpServletRequest
	 * @param ActionErrors 検証エラー内容
	 */
	private void validateRegist(final ActionErrors errors,
			final HttpServletRequest request) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		PkgRdirectionLotInventorySearchList addBean = null;
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		String unitDivHaigo = PkgRdirectionConst.UNIT_DIVISION_HAIGO;
		String unitDivAdjust = PkgRdirectionConst.UNIT_DIVISION_ADJUST;
		ActionMessage errMsg = null;
		this.getRegistList().clear();

		for (int i = 0; i < searchList.size(); i++) {
			addBean = null;
			PkgRdirectionLotInventorySearchList bean = searchList.get(i);

			// 使用数量(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrUseQty())) {
				errMsg = check.checkDigitMessage(unitDivHaigo, null, null, bean
						.getStrUseQty(), rb
						.getString("item.pkgrdirection.lot.search.use.qty"),
					i + 1);
				if (errMsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				} else {
					addBean = bean;
				}
			}
			// ｻﾝﾌﾟﾙ(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrSampleQty())) {
				errMsg = check.checkDigitMessage(unitDivHaigo, null, null, bean
						.getStrSampleQty(), rb
						.getString("item.pkgrdirection.lot.search.sample.qty"),
					i + 1);
				if (errMsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				} else {
					addBean = bean;
				}
			}
			// ロス(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrLossQty())) {
				errMsg = check.checkDigitMessage(unitDivHaigo, null, null, bean
						.getStrLossQty(), rb
						.getString("item.pkgrdirection.lot.search.loss.qty"),
					i + 1);
				if (errMsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				} else {
					addBean = bean;
				}
			}
			// 不良(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrDefectQty())) {
				errMsg = check.checkDigitMessage(unitDivHaigo, null, null, bean
						.getStrDefectQty(), rb
						.getString("item.pkgrdirection.lot.search.defect.qty"),
					i + 1);
				if (errMsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				} else {
					addBean = bean;
				}
			}
			// 調整数量(数値桁数チェック)
			if (!StringUtils.isEmpty(bean.getStrAdjustQty())) {
				errMsg = check.checkDigitMessage(unitDivAdjust, null, null,
					bean.getStrAdjustQty(),
					rb.getString("item.pkgrdirection.lot.search.adjust.qty"),
					i + 1);
				if (errMsg != null) {
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				} else {
					addBean = bean;
				}
			}
			// 登録対象リストに追加する
			if (addBean != null) {
				this.getRegistList().add(addBean);
				BigDecimal useSumQty = AecNumberUtils.convertBigDecimal(addBean
						.getStrUseSumQty());
				// 横計マイナスはエラー
				if (BigDecimal.ZERO.compareTo(useSumQty) > 0) {
					errMsg = new ActionMessage(
							"errors.pkgrdirection.minus.use.sum.qty.row",
							String.valueOf(i + 1));
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				}
				// 使用量>在庫量はエラー
				useSumQty = useSumQty.subtract(addBean.getStockpdQty()); // 在庫引落量は減算
				if (useSumQty.compareTo(addBean.getInventoryQty()) > 0) {
					errMsg = new ActionMessage(
							"errors.pkgrdirection.over.use.sum.qty.row", String
									.valueOf(i + 1));
					errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				}
			}
		}

		// 入力なし
		if (this.getRegistList().isEmpty()) {
			errMsg = new ActionMessage("errors.pkgrdirection.not.input.use.qty");
			errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
		}
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
	 * 指図区分取得.
	 * @return String 指図区分
	 */
	public String getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * 指図区分設定.
	 * @param directionDivision 指図区分
	 */
	public void setDirectionDivision(final String directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * 包装指図番号取得.
	 * @return String 包装指図番号
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * 包装指図番号設定.
	 * @param directionNo 包装指図番号
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * ステップNO.取得
	 * @return String ステップNO.
	 */
	public String getStepNo() {
		return this.stepNo;
	}

	/**
	 * ステップNO.設定
	 * @param stepNo ステップNO.
	 */
	public void setStepNo(final String stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * 行NO.取得
	 * @return String 行NO.
	 */
	public String getLineNo() {
		return this.lineNo;
	}

	/**
	 * 行NO.設定
	 * @param lineNo 行NO.
	 */
	public void setLineNo(final String lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * 検索品目コード取得.
	 * @return String 検索品目コード
	 */
	public String getSrhItemCd() {
		return srhItemCd;
	}

	/**
	 * 検索品目コード設定.
	 * @param srhItemCd 検索品目コード
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = srhItemCd;
	}

	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getSrhItemName() {
		return srhItemName;
	}

	/**
	 * 品目名称設定.
	 * @param srhItemName 品目名称
	 */
	public void setSrhItemName(final String srhItemName) {
		this.srhItemName = srhItemName;
	}

	/**
	 * ロット番号取得.
	 * @return String ロット番号
	 */
	public String getSrhLotNo() {
		return srhLotNo;
	}

	/**
	 * ロット番号設定.
	 * @param srhLotNo ロット番号
	 */
	public void setSrhLotNo(final String srhLotNo) {
		this.srhLotNo = srhLotNo;
	}

	/**
	 * 使用数取得.
	 * @return String 使用数
	 */
	public String getUseQty() {
		return useQty;
	}

	/**
	 * 使用数設定.
	 * @param useQty 使用数
	 */
	public void setUseQty(final String useQty) {
		this.useQty = useQty;
	}

	/**
	 * total数取得.
	 * @return String total数
	 */
	public String getTotalQty() {
		return totalQty;
	}

	/**
	 * total数設定.
	 * @param totalQty total数
	 */
	public void setTotalQty(final String totalQty) {
		this.totalQty = totalQty;
	}

	/**
	 * 行番号取得
	 * @return String 行番号
	 */
	public String getLine() {
		return line;
	}

	/**
	 * 行番号設定
	 * @param line 行番号
	 */
	public void setLine(final String line) {
		this.line = line;
	}

	/**
	 * 小数点桁数を取得します。
	 * @return decimalPoint 小数点桁数
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数を設定します。
	 * @param decimalPoint 小数点桁数
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分を取得します。
	 * @return roundDivision
	 */
	public String getRoundDivision() {
		return roundDivision;
	}

	/**
	 * 端数区分を設定します。
	 * @param roundDivision 端数区分
	 */
	public void setRoundDivision(final String roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * 検索結果リストを取得します。
	 * @return searchList 検索結果リスト
	 */
	public List<PkgRdirectionLotInventorySearchList> getSearchList() {
		return searchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param searchList 検索結果リスト
	 */
	public void setSearchList(
			final List<PkgRdirectionLotInventorySearchList> searchList) {
		this.searchList = searchList;
	}

	/**
	 * 登録対象リスト取得
	 * @return searchList 登録対象リスト
	 */
	public List<PkgRdirectionLotInventorySearchList> getRegistList() {
		return registList;
	}

	/**
	 * 登録対象リスト設定
	 * @param registList 登録対象リスト
	 */
	public void setRegistList(
			final List<PkgRdirectionLotInventorySearchList> registList) {
		this.registList = registList;
	}
}
