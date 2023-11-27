/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontract;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseSubcontractDao;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetail;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailDao;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailList;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 入荷入力詳細 ロジック実装クラス
 * @author tosco
 */
public class PurchaseDeliverySumDetailLogicImpl implements
		PurchaseDeliverySumDetailLogic {

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 納期回答まとめ入力画面ヘッダ用Dao */
	private PurchaseDeliverySumDetailDao purchaseDeliverySumDetailDao;

	/** 納期回答まとめ入力画面明細用Dao */
	private PurchaseDeliverySumDetailListDao purchaseDeliverySumDetailListDao;

	/** 購買外注オーダ更新用Dao */
	private PurchaseSubcontractDao purchaseSubcontractDao;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetailLogicImpl() {
	}

	/**
	 * 入力された発注書NOに該当するまとめ入力対象データの件数を取得する.
	 * @param orderSheetNo 発注書NO
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForExist(final String orderSheetNo) {
		ActionMessages errors = new ActionMessages();

		// 発注書No未入力の場合
		if (StringUtils.isEmpty(orderSheetNo)) {
			ResourceBundle rb = ResourceBundle
					.getBundle(Constants.APPLICATION_PROPERTIES);
			errors = addError(errors, "errors.required", rb
					.getString("item.purchasedelivery.str.order.sheet.no"));
			return errors;
		}

		// 対象データ存在チェック
		PurchaseDeliverySumDetail bean = purchaseDeliverySumDetailDao
				.getCount(orderSheetNo);

		if (bean == null) {
			// データが存在しない場合
			errors = addError(errors, "errors.nodata");
		} else if (bean.getCount().equals(BigDecimal.ZERO)) {
			// すべて入荷・受入済の場合
			errors = addError(errors,
				"errors.purchasedelivery.all.arrived.accepted");
		}

		return errors;
	}

	/**
	 * 選択された発注書NOに該当するデータの各件数を取得する.
	 * @param orderSheetNo 発注書NO
	 * @return PurchaseDeliverySumDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	public PurchaseDeliverySumDetail getHeader(final String orderSheetNo)
			throws NoDataException {

		PurchaseDeliverySumDetail bean = purchaseDeliverySumDetailDao
				.getHeader(orderSheetNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 選択された発注書NOに該当するデータの納入先を取得する.
	 * @param orderSheetNo 発注書NO
	 * @return String 納入先
	 */
	public String getLocation(final String orderSheetNo) {
		return purchaseDeliverySumDetailDao.getLocation(orderSheetNo);
	}

	/**
	 * 選択された発注書NOに該当するデータを取得する.（明細部）
	 * @param orderSheetNo 発注書NO
	 * @param check 数値項目用表示ロジッククラス
	 * @return List<PurchaseDeliverySumDetailList> 明細部データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PurchaseDeliverySumDetailList> getEntity(
			final String orderSheetNo, final CheckDigitUtilsLogic check)
			throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;

		List<PurchaseDeliverySumDetailList> list = purchaseDeliverySumDetailListDao
				.getEntity(orderSheetNo);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (PurchaseDeliverySumDetailList bean : list) {
			// 数量
			strOrderQuantity = check.format(bean.getUnitDiv(), VENDER_DIV_SI,
				bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 重量
			strOrderConvertQuantity = check.format(UNIT_DIVISION_KG,
				VENDER_DIV_SI, bean.getVenderCd(), bean
						.getOrderConvertQuantity());
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);

		}

		return list;
	}

	/**
	 * 購買外注オーダーテーブル更新処理を行う.
	 * @param list 納期回答まとめ入力画面明細リスト
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final List<PurchaseDeliverySumDetailList> list,
			final String tantoCd) throws NoDataException, Exception {

		try {

			for (PurchaseDeliverySumDetailList bean : list) {

				// 更新対象データ検索
				PurchaseSubcontract psubBean = purchaseSubcontractDao
						.getEntity(bean.getPurchaseNo());
				if (psubBean == null) {
					throw new NoDataException();
				}

				// 排他制御が無かった為追加
				psubBean.setUpdateDate(bean.getUpdateDate());

				PurchaseSubcontract updBean = new PurchaseSubcontract();
				// 値を更新用Beanにコピー
				IgnoreCaseBeanUtils.copyProperties(updBean, psubBean);

				// 納品希望日
				String sugDelDate = bean.getStrSuggestedDeliverlimitDate()
						+ " ";
				String tmptime = bean.getStrSuggestedDeliverlimitDateTime();
				if (StringUtils.isEmpty(tmptime)) {
					tmptime = "00:00";
				}
				sugDelDate = sugDelDate + tmptime;
				updBean
						.setSuggestedDeliverlimitDate(AecDateUtils
								.getTimestampYmdHmFormat(sugDelDate,
									"yyyy/MM/dd HH:mm"));

				updBean.setSiOrderNo(bean.getSiOrderNo()); // 仕入先受注番号
				updBean.setStatus(bean.getStatus()); // 購買ステータス

				updBean.setUpdatorCd(tantoCd); // 更新者

				// 更新処理
				purchaseSubcontractDao.update(updBean);

			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	private ActionMessages addError(final ActionMessages errors,
			final String key, final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答まとめ入力画面ヘッダ用Daoを設定します。
	 * @param purchaseDeliverySumDetailDao 納期回答まとめ入力画面ヘッダ用Dao
	 */
	public void setPurchaseDeliverySumDetailDao(
			final PurchaseDeliverySumDetailDao purchaseDeliverySumDetailDao) {
		this.purchaseDeliverySumDetailDao = purchaseDeliverySumDetailDao;
	}

	/**
	 * 納期回答まとめ入力画面明細用Daoを設定します。
	 * @param purchaseDeliverySumDetailListDao 納期回答まとめ入力画面明細用Dao
	 */
	public void setPurchaseDeliverySumDetailListDao(
			final PurchaseDeliverySumDetailListDao purchaseDeliverySumDetailListDao) {
		this.purchaseDeliverySumDetailListDao = purchaseDeliverySumDetailListDao;
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
