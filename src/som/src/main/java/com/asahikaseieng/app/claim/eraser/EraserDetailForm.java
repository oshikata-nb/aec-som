/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserSales;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecNumberUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * 消込入力詳細 Formクラス
 * @author tosco
 */
public final class EraserDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;


	//
	// インスタンスフィールド	//

	/** 消込番号 */
	private String eraserNo;

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 請求先 */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 担当者コード */
	private String tantoCd;

	/** 担当者名称 */
	private String tantoNm;

	/** 消込可能額 */
	private String eraserCapaAmount;

	/** 消込額 */
	private String eraserAmount;

	/** 消込残合計 */
	private String eraserBalanceAmount;

	/** 入金ﾄﾗﾝ.入金金額合計 */
	private String sumCreditAmount;

	/** 入金ﾄﾗﾝ.消込額合計 */
	private String sumEraserAmount;

	/** 入金ﾄﾗﾝ.入金消込残合計 */
	private String sumCreditEraserAmount;

	/** 入金データリスト */
	private List<ClaimEraserDetail> creditList;

	/** 売上データリスト */
	private List<ClaimEraserSales> salesList;

	/** 新規用切替フラグ */
	private int insertFlg;

	/** 変更フラグ */
	private String dirtyFlg;

	/** 指定した請求番号の消込額を除いた消込合計 */
	private String baseSumEraserAmount;

	/** 明細選択状態による再計算値 */
	/** 請求ﾍｯﾀﾞｰ.消込額(再計算値) */
	private String newEraserAmount;

	/** 請求ﾍｯﾀﾞｰ.消込残(再計算値) */
	private String newEraserBalanceAmount;

	/** 入金ﾄﾗﾝ.消込額合計(再計算値) */
	private String newSumEraserAmount;

	/** 入金ﾄﾗﾝ.入金消込残合計(再計算値) */
	private String newSumCreditEraserAmount;

	/**
	 * コンストラクタ.消込入力詳細
	 */
	public EraserDetailForm() {
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
		if ("insert".equals(getOp()) || "update".equals(getOp()) || "delete".equals(getOp())) {
			for (int i = 0; i < salesList.size(); i++) {
				ClaimEraserSales bean = salesList.get(i);
				if ("2".equals(bean.getCheckKbn())) {
					bean.setCheckFlg(true);
				} else {
					bean.setCheckFlg(false);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("insert".equals(getOp()) || "update".equals(getOp()) || "delete".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 消込残合計、入金消込残合計の値チェック
			chkBalance(errors);
			// 再計算値設定
			recalculate();
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 消込番号
		setEraserNo(null);
		// 部門コード
		setSectionCd(null);
		// 部門名称
		setSectionName(null);
		// 請求先
		setVenderCd(null);
		// 請求先名
		setVenderName(null);
		// 担当者コード
		setTantoCd(null);
		// 担当者名
		setTantoNm(null);
		// 消込可能額		setEraserCapaAmount(null);
		// 消込額		setEraserAmount(null);
		// 消込残合計		setEraserBalanceAmount(null);
		// 入金ﾄﾗﾝ.入金金額合計		setSumCreditAmount(null);
		// 入金ﾄﾗﾝ.消込額合計		setSumEraserAmount(null);
		// 入金ﾄﾗﾝ.入金消込残合計		setSumCreditEraserAmount(null);
		// 入金データリスト		setCreditList(new ArrayList<ClaimEraserDetail>());
		// 売上データリスト		setSalesList(new ArrayList<ClaimEraserSales>());
		// 新規更新切替フラグ
		setInsertFlg(0);
		// 変更フラグ
		setDirtyFlg(null);
		// 請求ﾍｯﾀﾞｰ.消込額(再計算値)
		setNewEraserAmount(null);
		// 請求ﾍｯﾀﾞｰ.消込残(再計算値)
		setNewEraserBalanceAmount(null);
		// 入金ﾄﾗﾝ.消込額合計(再計算値)
		setNewSumEraserAmount(null);
		// 入金ﾄﾗﾝ.入金消込残合計(再計算値)
		setNewSumCreditEraserAmount(null);
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 消込番号取得.
	 * @return String 消込番号
	 */
	public String getEraserNo() {
		return eraserNo;
	}

	/**
	 * 消込番号設定.
	 * @param eraserNo 消込番号
	 */
	public void setEraserNo(final String eraserNo) {
		this.eraserNo = eraserNo;
	}

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称取得.
	 * @return String 部門名称
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称設定.
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 請求先取得.
	 * @return String 請求先
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先設定.
	 * @param venderCd 請求先
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求先名取得.
	 * @return String 請求先名
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名設定.
	 * @param venderName 請求先名
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 担当者コード取得.
	 * @return String 担当者コード
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * 担当者コード設定.
	 * @param tantoCd 担当者コード
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * 担当者名称取得.
	 * @return String 担当者名称
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * 担当者名称設定.
	 * @param tantoNm 担当者名称
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * 消込可能額取得.
	 * @return String 消込可能額
	 */
	public String getEraserCapaAmount() {
		return eraserCapaAmount;
	}

	/**
	 * 消込可能額設定.
	 * @param eraserCapaAmount 消込可能額
	 */
	public void setEraserCapaAmount(final String eraserCapaAmount) {
		this.eraserCapaAmount = eraserCapaAmount;
	}

	/**
	 * 消込額取得.
	 * @return String 消込額
	 */
	public String getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * 消込額設定.
	 * @param eraserAmount 消込額
	 */
	public void setEraserAmount(final String eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * 消込残合計取得.
	 * @return String 消込残合計
	 */
	public String getEraserBalanceAmount() {
		return eraserBalanceAmount;
	}

	/**
	 * 消込残合計設定.
	 * @param eraserBalanceAmount 消込残合計
	 */
	public void setEraserBalanceAmount(final String eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計取得.
	 * @return String 入金ﾄﾗﾝ.入金金額合計
	 */
	public String getSumCreditAmount() {
		return sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計設定.
	 * @param sumCreditAmount 入金ﾄﾗﾝ.入金金額合計
	 */
	public void setSumCreditAmount(final String sumCreditAmount) {
		this.sumCreditAmount = sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計取得.
	 * @return String 入金ﾄﾗﾝ.消込額合計
	 */
	public String getSumEraserAmount() {
		return sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計設定.
	 * @param sumEraserAmount 入金ﾄﾗﾝ.消込額合計
	 */
	public void setSumEraserAmount(final String sumEraserAmount) {
		this.sumEraserAmount = sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計取得.
	 * @return String 入金ﾄﾗﾝ.入金消込残合計
	 */
	public String getSumCreditEraserAmount() {
		return sumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計設定.
	 * @param sumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計
	 */
	public void setSumCreditEraserAmount(final String sumCreditEraserAmount) {
		this.sumCreditEraserAmount = sumCreditEraserAmount;
	}

	/**
	 * 入金データリスト取得.
	 * @return List<EraserDetail> 入金データリスト
	 */
	public List<ClaimEraserDetail> getCreditList() {
		return creditList;
	}

	/**
	 * 入金データリスト設定.
	 * @param creditList 入金データリスト
	 */
	public void setCreditList(final List<ClaimEraserDetail> creditList) {
		this.creditList = creditList;
	}

	/**
	 * 売上データリスト取得.
	 * @return List<EraserSales> 売上データリスト
	 */
	public List<ClaimEraserSales> getSalesList() {
		return salesList;
	}

	/**
	 * 売上データリスト設定.
	 * @param salesList 売上データリスト
	 */
	public void setSalesList(final List<ClaimEraserSales> salesList) {
		this.salesList = salesList;
	}

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
	 * 指定した請求番号の消込額を除いた消込合計を取得します。
	 * @return String 指定した請求番号の消込額を除いた消込合計
	 */
	public String getBaseSumEraserAmount() {
		return baseSumEraserAmount;
	}

	/**
	 * 指定した請求番号の消込額を除いた消込合計を設定します。
	 * @param baseSumEraserAmount 指定した請求番号の消込額を除いた消込合計
	 */
	public void setBaseSumEraserAmount(final String baseSumEraserAmount) {
		this.baseSumEraserAmount = baseSumEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(再計算値)を取得します。
	 * @return String 請求ﾍｯﾀﾞｰ.消込額(再計算値)
	 */
	public String getNewEraserAmount() {
		return newEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(再計算値)を設定します。
	 * @param newEraserAmount 請求ﾍｯﾀﾞｰ.消込額(再計算値)
	 */
	public void setNewEraserAmount(final String newEraserAmount) {
		this.newEraserAmount = newEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込残(再計算値)を取得します。
	 * @return String 請求ﾍｯﾀﾞｰ.消込残(再計算値)
	 */
	public String getNewEraserBalanceAmount() {
		return newEraserBalanceAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込残(再計算値)を設定します。
	 * @param newEraserBalanceAmount 請求ﾍｯﾀﾞｰ.消込残(再計算値)
	 */
	public void setNewEraserBalanceAmount(final String newEraserBalanceAmount) {
		this.newEraserBalanceAmount = newEraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(再計算値)を取得します。
	 * @return String 入金ﾄﾗﾝ.消込額合計(再計算値)
	 */
	public String getNewSumEraserAmount() {
		return newSumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(再計算値)を設定します。
	 * @param newSumEraserAmount 入金ﾄﾗﾝ.消込額合計(再計算値)
	 */
	public void setNewSumEraserAmount(final String newSumEraserAmount) {
		this.newSumEraserAmount = newSumEraserAmount;
	}

	/**
	 * 入入金ﾄﾗﾝ.金消込残合計(再計算値)を取得します。
	 * @return String 入金ﾄﾗﾝ.入金消込残合計(再計算値)
	 */
	public String getNewSumCreditEraserAmount() {
		return newSumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計(再計算値)を設定します。
	 * @param newSumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計(再計算値)
	 */
	public void setNewSumCreditEraserAmount(final String newSumCreditEraserAmount) {
		this.newSumCreditEraserAmount = newSumCreditEraserAmount;
	}

	/**
	 * 消込残合計、入金消込残合計の値チェック
	 * 　消込残合計!=０ かつ 請求合計と正負逆転の場合、エラー
	 * 　入金消込残合計!=０ かつ 入金合計と正負逆転の場合、エラー
	 * 
	 * @param request HttpServletRequest
	 * @param errors  エラー内容
	 */
	private void chkBalance(final ActionErrors errors) {
		// 消込可能額
		BigDecimal eraserCapaAmount = AecNumberUtils.convertBigDecimal(this.getEraserCapaAmount());
		// 入金ﾄﾗﾝ.入金金額合計
		BigDecimal sumCreditAmount = AecNumberUtils.convertBigDecimal(this.getSumCreditAmount());
		// 請求ﾍｯﾀﾞｰ.消込残(再計算値)
		BigDecimal eraserBalanceAmount = AecNumberUtils.convertBigDecimal(this.getNewEraserBalanceAmount());
		// 入金ﾄﾗﾝ.入金消込残合計(再計算値)
		BigDecimal sumCreditEraserAmount = AecNumberUtils.convertBigDecimal(this.getNewSumCreditEraserAmount());
		// 入金ﾄﾗﾝ.消込額合計(再計算値)
		BigDecimal sumEraserAmount = AecNumberUtils.convertBigDecimal(this.getNewSumEraserAmount());

		// 消込残(再計算値)!=０ かつ 請求合計と正負逆転 の場合
		if (eraserBalanceAmount.compareTo(new BigDecimal(0)) != 0) {

			// 消込可能額>=０(正)
			if (eraserCapaAmount.compareTo(new BigDecimal(0)) >= 0) {
				// 消込残合計＜０(負)
				if (eraserBalanceAmount.compareTo(new BigDecimal(0)) == -1) {
			        errors.add(this.eraserBalanceAmount, new ActionMessage("errors.eraser.eraseramount"));
			        return;
				}
			// 消込可能額＜０(負)
			} else {
				// 消込残合計＞０(正)
				if (eraserBalanceAmount.compareTo(new BigDecimal(0)) == 1) {
			        errors.add(this.eraserBalanceAmount, new ActionMessage("errors.eraser.eraseramount"));
			        return;
				}
			}
		}

		// 入金消込残合計(再計算値)!=０ かつ 入金合計と正負逆転 の場合
		if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) != 0) {

			// 入金合計>=０(正)
			if (sumCreditAmount.compareTo(new BigDecimal(0)) >= 0) {
				// 入金消込残合計＜０(負)
				if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) == -1) {
			        errors.add(this.sumCreditEraserAmount, new ActionMessage("errors.eraser.eraseramount"));
			        return;
				}
			// 入金合計＜０(負)
			} else {
				// 入金消込残合計＞０(正)
				if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) == 1) {
			        errors.add(this.sumCreditEraserAmount, new ActionMessage("errors.eraser.eraseramount"));
			        return;
				}
			}
		}

		// 入金合計と消込額が正負逆転の場合
		// 入金合計>=０(正)
		if (sumCreditAmount.compareTo(new BigDecimal(0)) >= 0) {
			// 消込額合計＜０(負)
			if (sumEraserAmount.compareTo(new BigDecimal(0)) == -1) {
		        errors.add(this.sumEraserAmount, new ActionMessage("errors.eraser.eraseramount"));
		        return;
			}
		// 入金合計＜０(負)
		} else {
			// 消込額合計＞０(正)
			if (sumEraserAmount.compareTo(new BigDecimal(0)) == 1) {
		        errors.add(this.sumEraserAmount, new ActionMessage("errors.eraser.eraseramount"));
		        return;
			}
		}
	}

	/**
	 * 
	 * 再計算値を再設定する。
	 * 請求ﾍｯﾀﾞｰ.消込額、請求ﾍｯﾀﾞｰ.消込残、入金ﾄﾗﾝ.消込額合計、入金ﾄﾗﾝ.入金消込残合計
	 */
	private void recalculate() {
		// 請求ﾍｯﾀﾞｰ.消込額
		this.setEraserAmount(this.getNewEraserAmount());
		// 請求ﾍｯﾀﾞｰ.消込残
		this.setEraserBalanceAmount(this.getNewEraserBalanceAmount());
		// 入金ﾄﾗﾝ.消込額合計
		this.setSumEraserAmount(this.getNewSumEraserAmount());
		// 入金ﾄﾗﾝ.入金消込残合計
		this.setSumCreditEraserAmount(this.getNewSumCreditEraserAmount());
	}

}
