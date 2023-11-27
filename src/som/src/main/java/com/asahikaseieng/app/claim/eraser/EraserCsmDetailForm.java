/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsm;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserDetail;
import com.asahikaseieng.struts.AbstractForm;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 消込入力詳細 Formクラス(カスタマイズ)
 * @author tosco
 */
public final class EraserCsmDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 消込日付 */
	private Timestamp eraserDate;

	private String strEraserDate;

	/** 請求先 */
	private String venderCd;

	/** 請求先名称 */
	private String venderName1;

	/** 請求先略称 */
	private String venderShortedName;

	/** 担当者コード */
	private String tantoCd;

	/** 消込完了日付FROM(文字列) */
	private String strEraserCompleteDateFrom;

	/** 消込完了日付TO(文字列) */
	private String strEraserCompleteDateTo;

	/** 区分 */
	private String kbn;

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

	/** 消込(Csm)データリスト */
	private List<ClaimEraserCsm> eraserCsmList;

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

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	/** 請求番号 */
	private String claimNo;

	/**
	 * コンストラクタ.消込入力詳細
	 */
	public EraserCsmDetailForm() {
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
		if ("update".equals(getOp())) {
			for (int i = 0; i < eraserCsmList.size(); i++) {
				ClaimEraserCsm bean = eraserCsmList.get(i);
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

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
			// 消込残合計、入金消込残合計の値チェック
			chkBalance(errors);
			// 符号チェック
			chkSignum(errors);
			// 再計算値設定
			recalculate();
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 部署コード
		setOrganizationCd(null);
		// 部署名称
		setOrganizationName(null);
		// 消込日付
		setEraserDate(AecDateUtils.getCurrentTimestamp());
		setStrEraserDate(AecDateUtils.dateFormat(AecDateUtils
				.getCurrentTimestamp(), "yyyy/MM/dd"));
		// 請求先
		setVenderCd(null);
		// 請求先名
		setVenderName1(null);
		// 請求先略称
		setVenderShortedName(null);
		// 担当者コード
		setTantoCd(null);
		// 消込可能額
		setEraserCapaAmount(null);
		// 消込額
		setEraserAmount(null);
		// 消込残合計
		setEraserBalanceAmount(null);
		// 入金ﾄﾗﾝ.入金金額合計
		setSumCreditAmount(null);
		// 入金ﾄﾗﾝ.消込額合計
		setSumEraserAmount(null);
		// 入金ﾄﾗﾝ.入金消込残合計
		setSumCreditEraserAmount(null);
		// 入金データリスト
		setCreditList(new ArrayList<ClaimEraserDetail>());
		// 消込(Csm)データリスト
		setEraserCsmList(new ArrayList<ClaimEraserCsm>());
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
		// 承認ステータス
		setApprovalStatus(BigDecimal.ZERO);
		// 請求番号
		setClaimNo(null);
	}

	//	
	// インスタンスメソッド
	//		

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
	 * 消込完了日付FROM(文字列)取得.
	 * @return String 消込完了日付FROM(文字列)
	 */
	public String getStrEraserCompleteDateFrom() {
		return strEraserCompleteDateFrom;
	}

	/**
	 * 消込完了日付FROM(文字列)設定.
	 * @param strEraserCompleteDateFrom 消込完了日付FROM(文字列)
	 */
	public void setStrEraserCompleteDateFrom(
			final String strEraserCompleteDateFrom) {
		this.strEraserCompleteDateFrom = strEraserCompleteDateFrom;
	}

	/**
	 * 消込完了日付TO(文字列)取得.
	 * @return String 消込完了日付TO(文字列)
	 */
	public String getStrEraserCompleteDateTo() {
		return strEraserCompleteDateTo;
	}

	/**
	 * 消込完了日付TO(文字列)設定.
	 * @param strEraserCompleteDateTo 消込完了日付TO(文字列)
	 */
	public void setStrEraserCompleteDateTo(final String strEraserCompleteDateTo) {
		this.strEraserCompleteDateTo = strEraserCompleteDateTo;
	}

	/**
	 * 区分取得.
	 * @return String 区分
	 */
	public String getKbn() {
		return kbn;
	}

	/**
	 * 区分設定.
	 * @param kbn 区分
	 */
	public void setKbn(final String kbn) {
		this.kbn = kbn;
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
	 * 
	 */
	public void setEraserBalanceAmount(final String eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計取得.
	 * @return String 入金ﾄﾗﾝ.入金金額合計
	 * 
	 */
	public String getSumCreditAmount() {
		return sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金金額合計設定.
	 * @param sumCreditAmount 入金ﾄﾗﾝ.入金金額合計
	 * 
	 */
	public void setSumCreditAmount(final String sumCreditAmount) {
		this.sumCreditAmount = sumCreditAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計取得.
	 * @return String 入金ﾄﾗﾝ.消込額合計
	 * 
	 */
	public String getSumEraserAmount() {
		return sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計設定.
	 * @param sumEraserAmount 入金ﾄﾗﾝ.消込額合計
	 * 
	 */
	public void setSumEraserAmount(final String sumEraserAmount) {
		this.sumEraserAmount = sumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計取得.
	 * @return String 入金ﾄﾗﾝ.入金消込残合計
	 * 
	 */
	public String getSumCreditEraserAmount() {
		return sumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計設定.
	 * @param sumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計
	 * 
	 */
	public void setSumCreditEraserAmount(final String sumCreditEraserAmount) {
		this.sumCreditEraserAmount = sumCreditEraserAmount;
	}

	/**
	 * 入金データリスト取得.
	 * @return List<EraserDetail> 入金データリスト
	 * 
	 */
	public List<ClaimEraserDetail> getCreditList() {
		return creditList;
	}

	/**
	 * 入金データリスト設定.
	 * @param creditList 入金データリスト
	 * 
	 */
	public void setCreditList(final List<ClaimEraserDetail> creditList) {
		this.creditList = creditList;
	}

	/**
	 * 消込(Csm)データリスト取得.
	 * @return List<EraserCsm> 消込(Csm)データリスト
	 * 
	 */
	public List<ClaimEraserCsm> getEraserCsmList() {
		return eraserCsmList;
	}

	/**
	 * 消込(Csm)データリスト設定.
	 * @param eraserCsmList 消込(Csm)データリスト
	 * 
	 */
	public void setEraserCsmList(final List<ClaimEraserCsm> eraserCsmList) {
		this.eraserCsmList = eraserCsmList;
	}

	/**
	 * 新規用切替フラグを取得します。
	 * 
	 * @return int 新規用切替フラグ
	 */
	public int getInsertFlg() {
		return insertFlg;
	}

	/**
	 * 新規用切替フラグを設定します。
	 * 
	 * @param insertFlg 新規用切替フラグ
	 */
	public void setInsertFlg(final int insertFlg) {
		this.insertFlg = insertFlg;
	}

	/**
	 * 変更フラグを取得します。
	 * 
	 * @return String 変更フラグ
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * 変更フラグを設定します。
	 * 
	 * @param dirtyFlg 変更フラグ
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 指定した請求番号の消込額を除いた消込合計を取得します。
	 * 
	 * @return String 指定した請求番号の消込額を除いた消込合計
	 * 
	 */
	public String getBaseSumEraserAmount() {
		return baseSumEraserAmount;
	}

	/**
	 * 指定した請求番号の消込額を除いた消込合計を設定します。
	 * 
	 * @param baseSumEraserAmount 指定した請求番号の消込額を除いた消込合計
	 * 
	 */
	public void setBaseSumEraserAmount(final String baseSumEraserAmount) {
		this.baseSumEraserAmount = baseSumEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(再計算値)を取得します。
	 * 
	 * @return String 請求ﾍｯﾀﾞｰ.消込額(再計算値)
	 */
	public String getNewEraserAmount() {
		return newEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込額(再計算値)を設定します。
	 * 
	 * @param newEraserAmount 請求ﾍｯﾀﾞｰ.消込額(再計算値)
	 */
	public void setNewEraserAmount(final String newEraserAmount) {
		this.newEraserAmount = newEraserAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込残(再計算値)を取得します。
	 * 
	 * @return String 請求ﾍｯﾀﾞｰ.消込残(再計算値)
	 */
	public String getNewEraserBalanceAmount() {
		return newEraserBalanceAmount;
	}

	/**
	 * 請求ﾍｯﾀﾞｰ.消込残(再計算値)を設定します。
	 * 
	 * @param newEraserBalanceAmount 請求ﾍｯﾀﾞｰ.消込残(再計算値)
	 */
	public void setNewEraserBalanceAmount(final String newEraserBalanceAmount) {
		this.newEraserBalanceAmount = newEraserBalanceAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(再計算値)を取得します。
	 * 
	 * @return String 入金ﾄﾗﾝ.消込額合計(再計算値)
	 */
	public String getNewSumEraserAmount() {
		return newSumEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.消込額合計(再計算値)を設定します。
	 * 
	 * @param newSumEraserAmount 入金ﾄﾗﾝ.消込額合計(再計算値)
	 */
	public void setNewSumEraserAmount(final String newSumEraserAmount) {
		this.newSumEraserAmount = newSumEraserAmount;
	}

	/**
	 * 入入金ﾄﾗﾝ.金消込残合計(再計算値)を取得します。
	 * 
	 * @return String 入金ﾄﾗﾝ.入金消込残合計(再計算値)
	 */
	public String getNewSumCreditEraserAmount() {
		return newSumCreditEraserAmount;
	}

	/**
	 * 入金ﾄﾗﾝ.入金消込残合計(再計算値)を設定します。
	 * 
	 * @param newSumCreditEraserAmount 入金ﾄﾗﾝ.入金消込残合計(再計算値)
	 */
	public void setNewSumCreditEraserAmount(
			final String newSumCreditEraserAmount) {
		this.newSumCreditEraserAmount = newSumCreditEraserAmount;
	}

	/**
	 * 消込残合計、入金消込残合計の値チェック 消込残合計!=０ かつ 請求合計と正負逆転の場合、エラー 入金消込残合計!=０ かつ
	 * 入金合計と正負逆転の場合、エラー
	 * 
	 * @param request HttpServletRequest
	 * @param errors エラー内容
	 */
	private void chkBalance(final ActionErrors errors) {
		// 入金ﾄﾗﾝ.入金金額合計
		BigDecimal sumCreditAmount = AecNumberUtils.convertBigDecimal(this
				.getSumCreditAmount());
		// 入金ﾄﾗﾝ.入金消込残合計(再計算値)
		BigDecimal sumCreditEraserAmount = AecNumberUtils
				.convertBigDecimal(this.getNewSumCreditEraserAmount());

		// 入金消込残合計(再計算値)!=０ かつ 入金合計と正負逆転 の場合
		if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) != 0) {

			// 入金合計>=０(正)
			if (sumCreditAmount.compareTo(new BigDecimal(0)) >= 0) {
				// 入金消込残合計＜０(負)
				if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) == -1) {
					errors.add(this.sumCreditEraserAmount, new ActionMessage(
							"errors.eraser.eraseramount"));
					return;
				}
				// 入金合計＜０(負)
			} else {
				// 入金消込残合計＞０(正)
				if (sumCreditEraserAmount.compareTo(new BigDecimal(0)) == 1) {
					errors.add(this.sumCreditEraserAmount, new ActionMessage(
							"errors.eraser.eraseramount"));
					return;
				}
			}
		}

	}

	/**
	 * 
	 * 明細の請求金額と消込金額の符号をチェックする 符号が同じ場合：エラーなし、符号が違う場合：エラー
	 * @param errors エラー内容
	 */
	private void chkSignum(final ActionErrors errors) {
		for (int i = 0; i < this.eraserCsmList.size(); i++) {
			ClaimEraserCsm bean = this.eraserCsmList.get(i);
			BigDecimal eraserObjAmount = AecNumberUtils.convertBigDecimal(bean
					.getStrEraserObjectAmount());
			BigDecimal eraserAmount = AecNumberUtils.convertBigDecimal(bean
					.getStrEraserAmount());

			/* null ---> zero */
			eraserObjAmount = AecNumberUtils.convertNullToZero(eraserObjAmount); /* 消込残 */
			eraserAmount = AecNumberUtils.convertNullToZero(eraserAmount); /* 消込額 */

			if (eraserAmount.compareTo(new BigDecimal(0)) == 0) {
				continue;
			}

			// 符号チェック
			if (eraserObjAmount.signum() >= 0 && eraserAmount.signum() < 0) {
				// 請求金額(+),消込金額(-)の場合
				errors.add(this.sumCreditEraserAmount, new ActionMessage(
						"errors.eraser.signum.row", "請求金額", "消込金額", i + 1));
			}
			if (eraserObjAmount.signum() < 0 && eraserAmount.signum() >= 0) {
				// 請求金額(-),消込金額(+)の場合
				errors.add(this.sumCreditEraserAmount, new ActionMessage(
						"errors.eraser.signum.row", "請求金額", "消込金額", i + 1));
			}
		}
	}

	/**
	 * 
	 * 再計算値を再設定する。 請求ﾍｯﾀﾞｰ.消込額、請求ﾍｯﾀﾞｰ.消込残、入金ﾄﾗﾝ.消込額合計、入金ﾄﾗﾝ.入金消込残合計
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

	/**
	 * 消込日付(文字列)をDate型に変換した値取得
	 * 
	 * @param srhEraserCompleteDate 消込完了日付(String型)
	 * @return Date 消込完了日付(Date型)
	 */
	private Date getEraserCompleteDate(final String srhEraserCompleteDate) {
		if (StringUtils.isEmpty(srhEraserCompleteDate)) {
			return null;
		}
		// スラッシュ除去
		String[] ymd = srhEraserCompleteDate.split("/");
		int year = Integer.parseInt(ymd[0]);
		int month = Integer.parseInt(ymd[1]);
		int day = Integer.parseInt(ymd[2]);

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);

		Date creditDate = new Date(cal.getTimeInMillis());

		return creditDate;
	}

	/**
	 * 消込完了日付FROM取得
	 * @return Date 消込完了日付FROM
	 */
	public Date getEraserCompleteDateFrom() {
		return getEraserCompleteDate(this.getStrEraserCompleteDateFrom());
	}

	/**
	 * 消込完了日付TO取得
	 * @return Date 消込完了日付TO
	 */
	public Date getEraserCompleteDateTo() {
		return getEraserCompleteDate(this.getStrEraserCompleteDateTo());
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * eraserDateを取得します。
	 * @return eraserDate
	 */
	public Timestamp getEraserDate() {
		return eraserDate;
	}

	/**
	 * eraserDateを設定します。
	 * @param eraserDate eraserDate
	 */
	public void setEraserDate(final Timestamp eraserDate) {
		this.eraserDate = eraserDate;
	}

	/**
	 * strEraserDateを取得します。
	 * @return strEraserDate
	 */
	public String getStrEraserDate() {
		return strEraserDate;
	}

	/**
	 * strEraserDateを設定します。
	 * @param strEraserDate strEraserDate
	 */
	public void setStrEraserDate(final String strEraserDate) {
		this.strEraserDate = strEraserDate;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * claimNoを取得します。
	 * @return claimNo
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * claimNoを設定します。
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}
}
