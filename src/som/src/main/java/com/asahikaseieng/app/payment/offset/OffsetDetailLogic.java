/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.master.classification.ClassificationListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.classificationdetail.ClassificationDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDeposit;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetDetail;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetPayable;
import com.asahikaseieng.dao.nonentity.payment.offset.OffsetTranData;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * OffsetDetailLogicクラス．グループ間相殺入力
 * @author tosco
 */
public interface OffsetDetailLogic {

	/**
	 * 更新処理を行う.
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データ無し例外
	 */
	void update(final OffsetDetail hedBean,
			final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param categoryDivision 相殺ヘッダービーン
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データ無し例外
	 */
	void insert(final String categoryDivision, final OffsetDetail hedBean,
			final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException;

	/**
	 * 削除処理を行う.
	 * @param hedBean 相殺ヘッダービーン
	 * @param payableList 買掛リスト
	 * @param depositList 売掛リスト
	 * @throws NoDataException データ無し例外
	 */
	void delete(final OffsetDetail hedBean,
			final List<OffsetPayable> payableList,
			final List<OffsetDeposit> depositList) throws NoDataException;

	/**
	 * 相殺ヘッダーの検索処理を行う.グループ間相殺
	 * @param offsetNo offsetNo
	 * @return List<OffsetDetail> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	OffsetDetail getOffsetHeader(final String offsetNo) throws NoDataException;

	/**
	 * 相殺トランザクションリストの検索処理を行う.
	 * @param offsetNo 相殺番号
	 * @return List<OffsetTranData> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<OffsetTranData> getDetailData(final String offsetNo)
			throws NoDataException;

	/**
	 * 売掛リスト検索処理を行う.検索ボタン押下時（新規）
	 * @param organizationCd 部署コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param creditFlg 売上科目区分
	 * @return List<OffsetDeposit> 売掛リスト
	 * @throws NoDataException NoDataException
	 */
	List<OffsetDeposit> getDepositNew(final String organizationCd,
			final String offsetGroupCd, final BigDecimal creditFlg)
			throws NoDataException;

	/**
	 * 買掛リスト検索処理を行う.検索ボタン押下時（新規）
	 * @param organizationCd 部署コード
	 * @param offsetGroupCd 相殺グループコード
	 * @param payableFlg 仕入科目区分
	 * @return List<OffsetPayable> 買掛リスト
	 * @throws NoDataException NoDataException
	 */
	List<OffsetPayable> getPayableNew(final String organizationCd,
			final String offsetGroupCd, final BigDecimal payableFlg)
			throws NoDataException;

	/**
	 * 売掛リスト検索処理を行う.リンク遷移時（変更）
	 * @param sectionCd 部門コード
	 * @param venderCd 請求先コード
	 * @param depositDate 売掛締め日
	 * @param creditFlg creditFlg
	 * @return OffsetDeposit 売掛リスト
	 * @throws NoDataException NoDataException
	 */
	OffsetDeposit getDeposit(final String sectionCd, final String venderCd,
			final Date depositDate, final BigDecimal creditFlg)
			throws NoDataException;

	/**
	 * 買掛リスト検索処理を行う.リンク遷移時（変更）
	 * @param sectionCd 部門コード
	 * @param venderCd 支払先コード
	 * @param payableDate 買掛締め日
	 * @param payableFlg payableFlg
	 * @return OffsetPayable 買掛リスト
	 * @throws NoDataException NoDataException
	 */
	OffsetPayable getPayable(final String sectionCd, final String venderCd,
			final Date payableDate, final BigDecimal payableFlg)
			throws NoDataException;

	/**
	 * ステータス更新
	 * @param frm 更新対象データ
	 * @param status ステータス
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void statusUpdate(final OffsetDetailForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;

	/**
	 * 相殺番号取得
	 * 
	 * @param updatorCd 更新者ID
	 * @return String 相殺番号
	 * @throws NoDataException データ無し例外
	 */
	String callFunction(final String updatorCd) throws NoDataException;

	/**
	 * 分類取得
	 * @param dataType サイトデータ種別
	 * @return List<ClassificationListForComboboxes>
	 */
	List<ClassificationListForComboboxes> getClassificationList(
			final BigDecimal dataType);

	/**
	 * 分類マスタ検索
	 * @param dataType データ種別
	 * @param categoryDivision 分類コード
	 * @return ClassificationDetail
	 */
	ClassificationDetail getClassificationEntity(BigDecimal dataType,
			String categoryDivision);
}
