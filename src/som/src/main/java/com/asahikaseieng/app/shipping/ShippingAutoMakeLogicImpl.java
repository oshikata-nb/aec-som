/*
 * Created on 2009/02/25
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueue;
import com.asahikaseieng.dao.entity.master.articleattributequeue.ArticleAttributeQueueDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProAutoMakeShippingOrderCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingAutoMake;
import com.asahikaseieng.dao.nonentity.shipping.ShippingAutoMakeDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 出荷指図指図自動作成画面 ロジック実装クラス
 * @author tosco
 */
public class ShippingAutoMakeLogicImpl implements ShippingAutoMakeLogic {

	private ProcedureCallDao procedureCallDao; // プロシージャコール用dao

	private ErrorLogDao errorLogDao; // エラーログ出力用

	private ItemDao itemDao;

	private ArticleAttributeQueueDao articleAttributeQueueDao;

	/** エラーログ出力用モジュールコード */
	protected static final String SHIPPING_AUTO_MAKE_MODULE_CD = "PRO_AUTO_MAKE_SHIPPING_ORDER";

	/** 出荷指図自動作成 正常終了 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_NOMAL_END = new BigDecimal(
			0);

	/** 出荷指図自動作成 在庫異常 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_STOCK_ERROR = new BigDecimal(
			-1);

	/** 出荷指図自動作成 在庫プロシージャエラー */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_STOCK_PRO_ERROR = new BigDecimal(
			-2);

	/** 出荷指図自動作成 異常 */
	protected static final BigDecimal SHIPPING_AUTO_MAKE_ERROR = new BigDecimal(
			-9);

	private ShippingAutoMakeDao shippingAutoMakeDao;

	/**
	 * コンストラクタ.出荷指図
	 */
	public ShippingAutoMakeLogicImpl() {

	}

	/**
	 * 出荷指図自動作成対象となるデータを返す
	 * @param frm 出荷指図自動作成画面
	 * @return List<ShippingAutoMake> 処理対象
	 * @throws NoDataException 例外
	 */
	public List<ShippingAutoMake> getShippingAutoMakeList(
			final ShippingAutoMakeForm frm) throws NoDataException {

		List<ShippingAutoMake> list = shippingAutoMakeDao
				.getShippingAutoMakeList(frm.getScheduledShippingDateFrom(),
					frm.getScheduledShippingDateTo());
		return list;
	}

	/**
	 * 出荷指図データの登録処理を行う.
	 * @param auto 受注
	 * @param tantoCd ログイン者コード
	 * @param scheduledShippingDateFrom 開始日
	 * @param scheduledShippingDateTo 終了日
	 * @return boolean boolean
	 * @throws ShippingLogicException 例外
	 */
	public boolean shippingAutoMake(final ShippingAutoMake auto,
			final String scheduledShippingDateFrom,
			final String scheduledShippingDateTo, final String tantoCd)
			throws ShippingLogicException {

		String errKey = "errors.shipping.auto.make";
		String errDetailKey = null;
		ProAutoMakeShippingOrderCallDto dto = new ProAutoMakeShippingOrderCallDto();

		dto.setPStrShippingDateFrom(scheduledShippingDateFrom);
		dto.setPStrShippingDateTo(scheduledShippingDateTo);
		dto.setPStrOrderNo(auto.getOrderNo());
		dto.setPNumOrderRowNo(auto.getRowNo());
		dto.setPStrTantoCd(tantoCd);
		String errMsg = "受注番号:" + auto.getOrderNo() + " 受注行番号:"
				+ auto.getRowNo().toString() + " 品目コード:" + auto.getItemCd()
				+ " 出荷指図自動作成に失敗しました。";

		try {

			// 品目マスタの存在チェック
			Item itemBean = itemDao.getEntity(auto.getItemCd());
			if (itemBean == null) {
				errMsg = "受注番号:" + auto.getOrderNo() + " 受注行番号:"
						+ auto.getRowNo().toString() + " 品目コード:"
						+ auto.getItemCd() + " 品目コードが品目マスタに存在しません。";
				ShippingLogicException ex = new ShippingLogicException(errKey,
						errDetailKey);
				ex.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
				ex.setInsideErrCd(dto.getPStrErrorCd());
				ex.setInsideErrMsg(errMsg);
				throw ex;

			}

			// 品目マスタ(品目－販売品扱い属性キュー)の存在チェック
			ArticleAttributeQueue artBean = articleAttributeQueueDao.getEntity(
				itemBean.getItemCd(), itemBean.getVersion());
			if (artBean == null) {
				errMsg = "受注番号:" + auto.getOrderNo() + " 受注行番号:"
						+ auto.getRowNo().toString() + " 品目コード:"
						+ auto.getItemCd()
						+ " 品目コードが品目マスタ(品目－販売品扱い属性キュー)に存在しません。";
				ShippingLogicException ex = new ShippingLogicException(errKey,
						errDetailKey);
				ex.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
				ex.setInsideErrCd(dto.getPStrErrorCd());
				ex.setInsideErrMsg(errMsg);
				throw ex;

			}

			// 基準保管場所か預り品区分がNULLの場合
			if (itemBean.getDefaultLocation() == null
					|| artBean.getKeepDivision() == null) {
				errMsg = "受注番号:" + auto.getOrderNo() + " 受注行番号:"
						+ auto.getRowNo().toString() + " 品目コード:"
						+ auto.getItemCd() + " 基準保管場所、または預かり品区分がNULLです。";
				ShippingLogicException ex = new ShippingLogicException(errKey,
						errDetailKey);
				ex.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
				ex.setInsideErrCd(dto.getPStrErrorCd());
				ex.setInsideErrMsg(errMsg);
				throw ex;

			}

			// 品目マスタ.基準保管場所が[G]始まりでない場合 かつ 預かり品区分が[1:通常品]の場合出荷指図自動作成の対象データ(2020/03/18 花王品の品目を対象外に追加)
			if (!itemBean.getDefaultLocation().substring(0, 1).equals("G")
					&& artBean.getKeepDivision().compareTo(BigDecimal.ONE) == 0
					&& !auto.getItemCd().substring(0, 2).equals("10")) {	// 品目コード10は花王品であり対象外

				// 出荷指図自動作成プロシージャ実行
				procedureCallDao.funAutoMakeShippingOrder(dto);

				int intRet = dto.getPNumRet().intValue();
				if (intRet != 0) {
					errDetailKey = dto.getPStrErrorMsg();
					ShippingLogicException ex = new ShippingLogicException(
							errKey, errDetailKey);
					ex.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
					ex.setInsideErrCd(dto.getPStrErrorCd());
					ex.setInsideErrMsg(dto.getPStrErrorMsg());
					throw ex;
				}
				return true;

			} else {
				return false;

			}

		} catch (LogicExceptionEx e) {
			ShippingLogicException ex = new ShippingLogicException(errKey,
					errMsg);
			ex.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
			ex.setInsideErrCd(dto.getPStrErrorCd());
			ex.setInsideErrMsg(dto.getPStrErrorMsg());
			throw ex;
		}
	}

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(SHIPPING_AUTO_MAKE_MODULE_CD);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		log.setSqlStr(strErrorMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}

	}

	/* -------------------- setter -------------------- */
	/**
	 * プロシージャコール用daoを設定する
	 * @param procedureCallDao プロシージャコール用dao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * エラーログ出力用daoを設定する
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 出荷自動作成
	 * @param shippingAutoMakeDao 出荷自動作成
	 */
	public void setShippingAutoMakeDao(
			final ShippingAutoMakeDao shippingAutoMakeDao) {
		this.shippingAutoMakeDao = shippingAutoMakeDao;
	}

	/**
	 * 品目マスタ用Dao
	 * @param itemDao 品目マスタ用Dao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * 品目マスタ(品目－販売品扱い属性キュー)用Dao
	 * @param articleAttributeQueueDao 品目マスタ用(品目－販売品扱い属性キュー)Dao
	 */
	public void setArticleAttributeQueueDao(
			final ArticleAttributeQueueDao articleAttributeQueueDao) {
		this.articleAttributeQueueDao = articleAttributeQueueDao;
	}

}
