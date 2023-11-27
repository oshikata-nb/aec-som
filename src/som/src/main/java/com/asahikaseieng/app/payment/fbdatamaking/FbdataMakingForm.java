/*
 * Created on 2009/06/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.fbdatamaking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingData;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingHeader;
import com.asahikaseieng.dao.nonentity.payment.fbdatamaking.FbdataMakingTrailer;
import com.asahikaseieng.struts.AbstractForm;

/**
 * ＦＢデータ作成 Formクラス
 * @author tosco
 */
public class FbdataMakingForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	//
	// インスタンスフィールド
	//
	/** 変更フラグ */
	private String dirtyFlg;

	/** 銀行マスタコード */
	private String bankMasterCd;

	/** 銀行マスタ名称 */
	private String bankMasterName;

	/** 支払日付 */
	private String paymentDate;

	// ヘッダー
	/** ファイル作成日時(スラッシュ編集) */
	private String strDlDate;

	/** 依頼人コード */
	private String transferClientCd;

	/** 依頼人名 */
	private String transferClientName;

	/** 振込指定日(月日の4桁（MMDD）) */
	private String trasferDate;

	/** 仕向金融機関番号 */
	private String bankCd;

	/** 仕向金融機関名 */
	private String bankKanaName;

	/** 仕向支店番号 */
	private String branchCd;

	/** 仕向支店名 */
	private String branchKanaName;

	/** 預金種目（依頼人）1:普通 2:当座 4:貯蓄 */
	private String accountDivision;

	/** 口座番号（依頼人） */
	private String accountNo;

	/** ＦＢヘッダー(保持用) */
	private FbdataMakingHeader fbHeader;

	// データ
	/** ＦＢデータリスト(表示用) */
	private List<FbdataMakingData> fbDataMakingList;

	// トレーラー
	/** 合計件数 */
	private String transferTotalNumber;

	/** 合計金額 */
	private String transferTotalAmount;

	/** ＦＢトレーラー(保持用) */
	private FbdataMakingTrailer fbTrailer;

	/** 小数点桁数(仕入金額) */
	private String decimalPoint;

	/** 端数区分(仕入金額) */
	private String round;

	/** ダウンロードフラグ */
	private String fbDownloadFlag;

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);
		clearCheck();

		if ("init".equals(getOp())) {
			clear();
		}

//		if ("insert".equals(getOp()) || "delete".equals(getOp())) {
//			clearCheck();
//		}
	}

	/**
	 * コンストラクタ.ＦＢデータ作成
	 */
	public FbdataMakingForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {

		// 変更フラグ
		setDirtyFlg(null);
		// 銀行マスタコード
		setBankMasterCd(null);
		// 銀行マスタ名称
		setBankMasterName(null);
		// 支払日付
		setPaymentDate(null);

		//-- ヘッダー
		// ファイル作成日時(スラッシュ編集)
		setStrDlDate(null);
		// 依頼人コード
		setTransferClientCd(null);
		// 依頼人名
		setTransferClientName(null);
		// 振込指定日(月日の4桁（MMDD）)
		setTrasferDate(null);
		// 仕向金融機関番号
		setBankCd(null);
		// 仕向金融機関名
		setBankKanaName(null);
		// 仕向支店番号
		setBranchCd(null);
		// 仕向支店名
		setBranchKanaName(null);
		// 預金種目（依頼人）1:普通 2:当座 4:貯蓄設定
		setAccountDivision(null);
		// 口座番号（依頼人）
		setAccountNo(null);
		// ＦＢヘッダー(更新用)
		setFbHeader(null);

		//-- データ
		// ＦＢデータリスト(表示用)
		setFbDataMakingList(null);

		//-- トレーラー
		// 合計件数
		setTransferTotalNumber(null);
		// 合計金額
		setTransferTotalAmount(null);
		// ＦＢトレーラー(更新用)
		setFbTrailer(null);

		// 小数点桁数
		setDecimalPoint(null);
		// 端数区分
		setRound(null);

		// FBダウンロードフラグ
		setFbDownloadFlag(null);

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * 銀行マスタコードを取得します。
	 * @return String 銀行マスタコード
	 */
	public String getBankMasterCd() {
		return bankMasterCd;
	}

	/**
	 * 銀行マスタコードを設定します。
	 * @param bankMasterCd 銀行マスタコード
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * 銀行マスタ名称を取得します。
	 * @return String 銀行マスタ名称
	 */
	public String getBankMasterName() {
		return bankMasterName;
	}

	/**
	 * 銀行マスタ名称を設定します。
	 * @param bankMasterName 銀行マスタ名称
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * 支払日付を取得します。
	 * @return String 支払日付
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 支払日付を設定します。
	 * @param paymentDate 支払日付
	 */
	public void setPaymentDate(final String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * ファイル作成日時(スラッシュ編集)を取得します。
	 * @return String ファイル作成日時(スラッシュ編集)
	 */
	public String getStrDlDate() {
		return strDlDate;
	}

	/**
	 * ファイル作成日時(スラッシュ編集)を設定します。
	 * @param strDlDate ファイル作成日時(スラッシュ編集)
	 */
	public void setStrDlDate(final String strDlDate) {
		this.strDlDate = strDlDate;
	}

	/**
	 * 依頼人コード取得.
	 * @return String 依頼人コード
	 */
	public String getTransferClientCd() {
		return this.transferClientCd;
	}

	/**
	 * 依頼人コード設定.
	 * @param transferClientCd 依頼人コード
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * 依頼人名取得.
	 * @return String 依頼人名
	 */
	public String getTransferClientName() {
		return this.transferClientName;
	}

	/**
	 * 依頼人名設定.
	 * @param transferClientName 依頼人名
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
	}

	/**
	 * 振込指定日(月日の4桁（MMDD）)取得.
	 * @return String 振込指定日(月日の4桁（MMDD）)
	 */
	public String getTrasferDate() {
		return this.trasferDate;
	}

	/**
	 * 振込指定日(月日の4桁（MMDD）)設定.
	 * @param trasferDate 振込指定日(月日の4桁（MMDD）)
	 */
	public void setTrasferDate(final String trasferDate) {
		this.trasferDate = trasferDate;
	}

	/**
	 * 仕向金融機関番号取得.
	 * @return String 仕向金融機関番号
	 */
	public String getBankCd() {
		return this.bankCd;
	}

	/**
	 * 仕向金融機関番号設定.
	 * @param bankCd 仕向金融機関番号
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * 仕向金融機関名取得.
	 * @return String 仕向金融機関名
	 */
	public String getBankKanaName() {
		return this.bankKanaName;
	}

	/**
	 * 仕向金融機関名設定.
	 * @param bankKanaName 仕向金融機関名
	 */
	public void setBankKanaName(final String bankKanaName) {
		this.bankKanaName = bankKanaName;
	}

	/**
	 * 仕向支店番号取得.
	 * @return String 仕向支店番号
	 */
	public String getBranchCd() {
		return this.branchCd;
	}

	/**
	 * 仕向支店番号設定.
	 * @param branchCd 仕向支店番号
	 */
	public void setBranchCd(final String branchCd) {
		this.branchCd = branchCd;
	}

	/**
	 * 仕向支店名取得.
	 * @return String 仕向支店名
	 */
	public String getBranchKanaName() {
		return this.branchKanaName;
	}

	/**
	 * 仕向支店名設定.
	 * @param branchKanaName 仕向支店名
	 */
	public void setBranchKanaName(final String branchKanaName) {
		this.branchKanaName = branchKanaName;
	}

	/**
	 * 預金種目（依頼人）1:普通 2:当座 4:貯蓄取得.
	 * @return String 預金種目（依頼人）1:普通 2:当座 4:貯蓄
	 */
	public String getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * 預金種目（依頼人）1:普通 2:当座 4:貯蓄設定.
	 * @param accountDivision 預金種目（依頼人）1:普通 2:当座 4:貯蓄
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * 口座番号（依頼人）取得.
	 * @return String 口座番号（依頼人）
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * 口座番号（依頼人）設定.
	 * @param accountNo 口座番号（依頼人）
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * ＦＢヘッダー(保持用)を取得します。
	 * @return FbdataMakingHeader ＦＢヘッダー(保持用)
	 */
	public FbdataMakingHeader getFbHeader() {
		return fbHeader;
	}

	/**
	 * ＦＢヘッダー(保持用)を設定します。
	 * @param fbHeader ＦＢヘッダー(保持用)
	 */
	public void setFbHeader(final FbdataMakingHeader fbHeader) {
		this.fbHeader = fbHeader;
	}

	/**
	 * ＦＢデータリスト(表示用)を取得します。
	 * @return List<FbdataMakingData> ＦＢデータリスト(表示用)
	 */
	public List<FbdataMakingData> getFbDataMakingList() {
		return fbDataMakingList;
	}

	/**
	 * ＦＢデータリスト(表示用)を設定します。
	 * @param fbDataMakingList ＦＢデータリスト(表示用)
	 */
	public void setFbDataMakingList(final List<FbdataMakingData> fbDataMakingList) {
		this.fbDataMakingList = fbDataMakingList;
	}

	/**
	 * 合計件数取得.
	 * @return String 合計件数
	 */
	public String getTransferTotalNumber() {
		return this.transferTotalNumber;
	}

	/**
	 * 合計件数設定.
	 * @param transferTotalNumber 合計件数
	 */
	public void setTransferTotalNumber(final String transferTotalNumber) {
		this.transferTotalNumber = transferTotalNumber;
	}

	/**
	 * 合計金額取得.
	 * @return String 合計金額
	 */
	public String getTransferTotalAmount() {
		return this.transferTotalAmount;
	}

	/**
	 * 合計金額設定.
	 * @param transferTotalAmount 合計金額
	 */
	public void setTransferTotalAmount(final String transferTotalAmount) {
		this.transferTotalAmount = transferTotalAmount;
	}

	/**
	 * ＦＢトレーラー(保持用)を取得します。
	 * @return FbdataMakingTrailer ＦＢトレーラー(保持用)
	 */
	public FbdataMakingTrailer getFbTrailer() {
		return fbTrailer;
	}

	/**
	 * ＦＢトレーラー(保持用)を設定します。
	 * @param fbTrailer ＦＢトレーラー(保持用)
	 */
	public void setFbTrailer(final FbdataMakingTrailer fbTrailer) {
		this.fbTrailer = fbTrailer;
	}

	/**
	 * 小数点桁数(仕入金額)取得
	 * @return String 小数点桁数(仕入金額)
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * 小数点桁数(仕入金額)設定
	 * @param decimalPoint 小数点桁数(仕入金額)
	 */
	public void setDecimalPoint(final String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * 端数区分(仕入金額)取得
	 * @return String 端数区分(仕入金額)
	 */
	public String getRound() {
		return round;
	}

	/**
	 * 端数区分(仕入金額)設定
	 * @param round 端数区分(仕入金額)
	 */
	public void setRound(final String round) {
		this.round = round;
	}

	/**
	 * 明細行数を取得する。
	 * @return 明細行数
	 */
	public int getDetailCount() {
		int count = 0;
		if (fbDataMakingList != null) {
			count = fbDataMakingList.size();
		}
		return count;
	}

	/**
	 * チェックボックス用クリア処理.
	 */
	private void clearCheck() {
		if (getFbDataMakingList() != null) {
			for (FbdataMakingData bean : getFbDataMakingList()) {
				bean.setCheckFlg(Boolean.FALSE);
			}
		}
	}

	/**
	 * FBデータダウンロードフラグ取得
	 * @return String FBデータダウンロードフラグ
	 */
	public String getFbDownloadFlag() {
		return fbDownloadFlag;
	}

	/**
	 * FBデータダウンロードフラグ設定
	 * @param fbDownloadFlag FBデータダウンロードフラグ
	 */
	public void setFbDownloadFlag(final String fbDownloadFlag) {
		this.fbDownloadFlag = fbDownloadFlag;
	}

}
