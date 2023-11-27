/*
 * Created on 2008/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.asahikaseieng.dao.entity.master.vender.Vender;
import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.master.namesdetail.NamesDetail;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPayment;
import com.asahikaseieng.dao.nonentity.payment.payment.AltPaymentVender;
import com.asahikaseieng.dao.nonentity.procedurecall.ProGetStockReductionCallDto;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * PaymentCsmDetailLogicクラス．支払入力（詳細)支払分類変更イベントロジック(カスタマイズ)
 * @author tosco
 */
public interface PaymentCsmDetailLogic {
	/**
	 * 検索処理を行う.支払入力検索処理
	 * @param venderCd 取引先コード
	 * @param venderDivision 取引先分類
	 * @return Vender
	 * @throws NoDataException 検索結果が存在しない場合発生
	 */
	Vender getVenderEntity(String venderCd, String venderDivision)
			throws NoDataException;

	/**
	 * 支払テーブルにデータを挿入する。
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws InvocationTargetException InvocationTargetException
	 */
	String insert(List<AltPayment> list) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 支払番号で支払トランザクションテーブルからデータを取得
	 * @param paymentNo 支払番号
	 * @return 支払明細データ
	 * @throws NoDataException データが存在しない場合発生
	 */
	List<AltPayment> findBySlipNo(final String paymentNo)
			throws NoDataException;

	/**
	 * 支払テーブルにデータを更新する。（実際はDeleteInsert）
	 * @param list 更新明細データ
	 * @return エラーメッセージ（正常時null)
	 */
	String update(final List<AltPayment> list);

	/**
	 * 支払テーブルから支払番号に合致するデータを全て削除する
	 * @param slipNo 支払番号
	 * @return 削除件数
	 */
	int delete(final String slipNo);

	/**
	 * 支払テーブルから支払番号に合致するデータを全て削除する
	 * @param payment 削除データ
	 * @return 削除件数
	 */
	int delete(final AltPayment payment);

	/**
	 * 支払先AutoComplete用一覧取得
	 * @param venderDivision 取引先区分==TS:得意先 SI:仕入先
	 * @param venderCd 取引先コード
	 * @return 支払先AutoComplete用一覧
	 * @throws NoDataException データが存在しない場合
	 */
	// List<AltPaymentVender> getVenderList(final String venderDivision,
	// final String venderCd) throws NoDataException;
	/**
	 * 支払先取得
	 * @param venderDivision 取引先区分==TS:得意先 SI:仕入先
	 * @param venderCd 取引先コード
	 * @return 支払先データ
	 * @throws NoDataException データが存在しない場合
	 */
	AltPaymentVender getVender(final String venderDivision,
			final String venderCd) throws NoDataException;

	/**
	 * 名称マスタ検索
	 * @return NamesDetail
	 */
	NamesDetail getNamesEntity();

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final PaymentCsmDetailForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;

	/**
	 * DateオブジェクトをTimestampに変換する
	 * @param d Dateオブジェクト
	 * @return Timestamp
	 */
	Timestamp getDateTimestamp(final Date d);

	/**
	 * 支払予定取得取得
	 * @param venderCd 仕入先コード
	 * @param payableAmount 支払残高合計
	 * @return 支払予定
	 */
	List<AltPayment> getPaymentHeaderList(final String venderCd,
			final BigDecimal payableAmount);

	/**
	 * サイト情報取得
	 * @param venderCd 仕入先コード
	 * @param paymentScheduledAmount 支払予定額
	 * @return サイト情報
	 */
	ProGetStockReductionCallDto getStockReduction(final String venderCd,
			final BigDecimal paymentScheduledAmount);

	/**
	 * 分類マスタ検索
	 * @param dataType データ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail
	 */
	ClassificationDetail getClassificationEntity(BigDecimal dataType,
			String categoryDivision);

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);

	/**
	 * 支払済金額取得
	 * @param supplierCd 支払先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	AltPayment getPaidEntity(String supplierCd, String strPaymentDate);

	/**
	 * 支払残高取得
	 * @param supplierCd 支払先コード
	 * @param strPaymentDate 支払日付
	 * @return AltPayment
	 */
	AltPayment getBalanceEntity(String supplierCd, String strPaymentDate);

	/**
	 * 支払登録チェック
	 * @param paymentDate 支払日付
	 * @param supplierCd 支払先コード
	 * @param organizationCd 部署コード
	 * @return List<AltPayment>
	 */
	List<AltPayment> checkEntity(Timestamp paymentDate, String supplierCd,
			String organizationCd);

	/**
	 * 支払予定 相殺検索
	 * @param strOffsetDate 相殺日
	 * @param venderCd 支払先コード
	 * @return AltPayment
	 */
	AltPayment getOffsetAmount(String strOffsetDate, String venderCd);

	/**
	 * 支払額合計検索
	 * @param supplierCd 支払先コード
	 * @return AltPayment
	 */
	AltPayment getTotalPaymentAmount(String supplierCd);
}
