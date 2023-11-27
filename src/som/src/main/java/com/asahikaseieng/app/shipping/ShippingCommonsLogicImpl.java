/*
 * Created on 2009/02/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.shipping.ShippingCarryForComboboxesDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingGetDeliveryAddress;
import com.asahikaseieng.dao.nonentity.shipping.ShippingGetDeliveryAddressDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷指図 共通ロジック実装クラス
 * @author tosco
 */
public class ShippingCommonsLogicImpl implements ShippingCommonsLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 出荷指図ー運送会社コンボボックス用DAO */
	private ShippingCarryForComboboxesDao shippingCarryForComboboxesDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 納入先アドレス取得 */
	private ShippingGetDeliveryAddressDao shippingGetDeliveryAddressDao;

	/**
	 * コンストラクタ
	 */
	public ShippingCommonsLogicImpl() {
	}

	/**
	 * 運送会社コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 運送会社検索
		List<ShippingCarryForComboboxes> lineList = getCarryList();
		for (ShippingCarryForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCarryCd());
			// 運送会社名称は運送会社名称１_運送会社名称２とする
			StringBuffer nameBuf = new StringBuffer("");
			nameBuf.append(bean.getCarryName1());
			if (bean.getCarryName2() != null
					&& !bean.getCarryName2().equals("")) {
				nameBuf.append("_").append(bean.getCarryName2());
			}
			item.setLabales(nameBuf.toString());
			res.add(item);
		}
		return res;
	}

	/**
	 * 運送会社一覧取得
	 * @return List<ShippingCarryForComboboxes>
	 */
	public List<ShippingCarryForComboboxes> getCarryList() {
		return shippingCarryForComboboxesDao.getCarryList();
	}

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryAllCombobox() {
		// 運送会社マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = createCarryCombobox();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 納入先ｺｰﾄﾞから納入先住所を取得する
	 * @param deliveryCd 納入先ｺｰﾄﾞ
	 * @return 納入先住所
	 */
	public String getDeliveryAddress(final String deliveryCd) {

		ShippingGetDeliveryAddress bean = shippingGetDeliveryAddressDao
				.getDeliveryAddress(deliveryCd);

		if (bean == null) {
			return "";
		} else {
			return bean.getDeliveryAddress();
		}

	}

	/* -------------------- setter -------------------- */

	/**
	 * 出荷指図-運送会社コンボボックス用DAOを設定します。
	 * @param shippingCarryForComboboxesDao 出荷指図-運送会社コンボボックス用DAO
	 */
	public void setCarryForComboboxesDao(
			final ShippingCarryForComboboxesDao shippingCarryForComboboxesDao) {
		this.shippingCarryForComboboxesDao = shippingCarryForComboboxesDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * shippingGetDeliveryAddressDaoを設定する
	 * @param shippingGetDeliveryAddressDao shippingGetDeliveryAddressDao
	 */
	public void setShippingGetDeliveryAddressDao(
			final ShippingGetDeliveryAddressDao shippingGetDeliveryAddressDao) {
		this.shippingGetDeliveryAddressDao = shippingGetDeliveryAddressDao;
	}

}
