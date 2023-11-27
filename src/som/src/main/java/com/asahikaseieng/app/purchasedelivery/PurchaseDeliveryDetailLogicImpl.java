/*
 * Created on 2009/02/20
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryDetail;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryDetailDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 受入入力詳細 ロジック実装クラス
 * @author tosco
 */
public class PurchaseDeliveryDetailLogicImpl implements
		PurchaseDeliveryDetailLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 納期回答入力画面用Dao */
	private PurchaseDeliveryDetailDao purchaseDeliveryDetailDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryDetailLogicImpl() {
	}

	/**
	 * 購買オーダーテーブル検索処理を行う.
	 * @param purchaseNo 購買NO
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<PurchaseDeliveryDetail> 納期回答入力データ
	 * @throws NoDataException データが存在しない例外
	 */
	public PurchaseDeliveryDetail getEntity(final String purchaseNo,
			final CheckDigitUtilsLogic check) throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;

		PurchaseDeliveryDetail bean = purchaseDeliveryDetailDao
				.getEntity(purchaseNo);

		if (bean == null) {
			throw new NoDataException();
		}

		// 数値フォーマット
		// 数量
		strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI, bean
				.getVenderCd(), bean.getOrderQuantity());
		bean.setStrOrderQuantity(strOrderQuantity);

		// 重量
		strOrderConvertQuantity = check.format(UNIT_DIVISION_KG, VENDER_DIV_SI,
			bean.getVenderCd(), bean.getOrderConvertQuantity());
		bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

		return bean;
	}

	/**
	 * 購買外注データを購買NO(KEY)で全項目取得する.
	 * @param purchaseNo 購買NO
	 * @return PurchaseSubcontract 購買外注データBean
	 * @throws NoDataException 対象データ無しエラー
	 */
	public PurchaseSubcontract getEntity(final String purchaseNo)
			throws NoDataException {
		PurchaseSubcontract bean = purchaseSubcontractDao.getEntity(purchaseNo);
		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/**
	 * 購買オーダーテーブル更新処理を行う.
	 * 
	 * @param frm 納期回答入力画面FORM
	 * @param tantoCd ログイン者コード
	 * @param bean 更新前検索データBean
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final PurchaseDeliveryDetailForm frm,
			final String tantoCd, final PurchaseSubcontract bean)
			throws NoDataException, Exception {
		try {
			PurchaseSubcontract updBean = new PurchaseSubcontract();

			// 値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(updBean, bean);

			// 更新項目セット
			setUpdInfo(updBean, frm, tantoCd);

			// 購買外注オーダ更新処理
			purchaseSubcontractDao.update(updBean);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 受入入力画面の共通項目(ロット分割以外)の値設定
	 * @param updBean 購買外注オーダ更新Bean
	 * @param frm 納期回答入力画面FORM
	 * @param tantoCd 担当者コード
	 */
	private void setUpdInfo(final PurchaseSubcontract updBean,
			final PurchaseDeliveryDetailForm frm, final String tantoCd) {

		updBean.setStatus(AecNumberUtils.convertBigDecimal(frm.getStatus())); // 購買ステータス
		updBean.setSiOrderNo(frm.getSiOrderNo()); // 仕入先受注番号
		// 納品希望日
		String sugDelDate = frm.getStrSuggestedDeliverlimitDate() + " "
				+ frm.getStrSuggestedDeliverlimitDateTime();
		updBean.setSuggestedDeliverlimitDate(AecDateUtils
				.getTimestampYmdHmFormat(sugDelDate, "yyyy/MM/dd HH:mm"));

		if (frm.isReplyContentsDivision()) {
			updBean.setReplyContentsDivision(BigDecimal.ONE); // 分納区分(1:有り)
		} else {
			updBean.setReplyContentsDivision(BigDecimal.ZERO); // 分納区分(0:無し)
		}

		updBean.setOrderSheetRemark(frm.getOrderSheetRemark()); // 発注書備考
		updBean.setRemark(frm.getRemark()); // 備考
		updBean.setOrderSheetRemark2(frm.getOrderSheetRemark()); // 発注書備考(入荷以降)
		updBean.setRemark2(frm.getRemark()); // 備考(入荷以降)
		updBean.setUpdatorCd(tantoCd); // 更新者
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答入力画面用Daoを設定します。
	 * @param purchaseDeliveryDetailDao 納期回答入力画面用Dao
	 */
	public void setPurchaseDeliveryDetailDao(
			final PurchaseDeliveryDetailDao purchaseDeliveryDetailDao) {
		this.purchaseDeliveryDetailDao = purchaseDeliveryDetailDao;
	}

	/**
	 * 購買外注オーダ更新用Daoを設定します。
	 * @param purchaseSubcontractDao 購買外注オーダ更新用Dao
	 */
	public void setPurchaseSubcontractDao(
			final PurchaseSubcontractDao purchaseSubcontractDao) {
		this.purchaseSubcontractDao = purchaseSubcontractDao;
	}

}
