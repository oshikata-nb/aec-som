/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.Stockinout;
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.entity.salesinoutrecord.SalesInoutRecord;
import com.asahikaseieng.dao.entity.salesinoutrecord.SalesInoutRecordDao;
import com.asahikaseieng.dao.nonentity.comboboxes.master.taxmaster.TaxMasterListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepEntity;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepEntityDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordList;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordListDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderList;
import com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepVenderListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 売上詳細(預り品) ロジック実装クラス
 * @author tosco
 */
public class SalesDetailKeepLogicImpl extends AbstractSalesDetailLogic
		implements SalesDetailKeepLogic {

	/** 売上詳細(預り品)画面用出荷指図ヘッダテーブル用Dao */
	private SalesDetailKeepEntityDao salesDetailKeepEntityDao;

	/** 売上詳細(預り品)画面_得意先取得用用Dao */
	private SalesDetailKeepVenderListDao salesDetailKeepVenderListDao;

	/** 売上詳細(預り品)画面_売上受払履歴取得用Dao */
	private SalesDetailKeepSalesInoutRecordListDao salesDetailKeepSalesInoutRecordListDao;

	/** 売上受払履歴用Dao */
	private SalesInoutRecordDao salesInoutRecordDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** ロケーションマスタ用Dao */
	private LocationDao locationDao;

	
	/**
	 * コンストラクタ.出荷指図
	 */
	public SalesDetailKeepLogicImpl() {
	}

	/**
	 * 検索処理を行なう.売上番号
	 * @param salesNo 売上番号
	 * @return ShippingDetail
	 * @throws NoDataException データが存在しない例外
	 */
	public SalesDetailKeepEntity getEntity(final String salesNo)
			throws NoDataException {
		checkParams(salesNo);

		// 売上トランザクションデータ取得
		SalesDetailKeepEntity bean = salesDetailKeepEntityDao
				.getEntity(salesNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 売上区分名称を取得する
	 * @param categoryDivision 売上区分
	 * @return String
	 * @throws NoDataException データが存在しない例外
	 */
	public String getCategoryName(final String categoryDivision)
			throws NoDataException {

		// 売上区分より売上区分名称を取得
		String name = salesDetailKeepEntityDao
				.getCategoryName(categoryDivision);
		if (name == null) {
			throw new NoDataException();
		}

		return name;
	}

	/**
	 * 品目情報を取得する
	 * @param itemCd 品目コード
	 * @return SalesDetailStandardEntity
	 * @throws NoDataException データが存在しない例外
	 */
	public SalesDetailKeepEntity getSalesByItem(final String itemCd)
			throws NoDataException {

		// 品目情報を取得
		SalesDetailKeepEntity bean = salesDetailKeepEntityDao
				.getSalesByItem(itemCd);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 納入先コード、品目コードより帳合コードを取得する
	 * @param deliveryCd 納入先コード
	 * @param itemCd 品目コード
	 * @return String 帳合コード
	 * @throws NoDataException データが存在しない例外
	 */
	public String getBalanceCdByDeliveryCdItemCd(final String deliveryCd,
			final String itemCd) throws NoDataException {
		String balanceCd = salesDetailKeepEntityDao
				.getBalanceCdByDeliveryCdItemCd(deliveryCd, itemCd);
		if (balanceCd == null) {
			throw new NoDataException();
		}
		return balanceCd;
	}

	/**
	 * 帳合コードより得意先一覧を取得する
	 * @param balanceCd 帳合
	 * @return List<SalesDetailKeepVenderList>
	 * @throws NoDataException データが存在しない例外
	 */
	public List<SalesDetailKeepVenderList> getVenderList(final String balanceCd)
			throws NoDataException {
		List<SalesDetailKeepVenderList> list = salesDetailKeepVenderListDao
				.getVenderList(balanceCd);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 売上受払履歴より出庫ロケーション一覧を取得する
	 * @param frm 売上詳細(預り品)画面FORM
	 * @return List<SalesDetailKeepLocationList> 検索結果一覧
	 * @throws NoDataException データが存在しない例外
	 */
	public List<SalesDetailKeepSalesInoutRecordList> getLocationList(
			final SalesDetailKeepForm frm) throws NoDataException {
		String salesNo = null;
		if (Integer.parseInt(frm.getCategoryDivision()) > 0) {
			// 売上区分が取消ではない場合、自身の売上番号を設定
			salesNo = frm.getSalesNo();
		} else {
			// 売上区分が取消の場合、取消元の売上番号を設定
			salesNo = frm.getCancelSalesNo();
		}

		// 出庫ロケーション取得
		List<SalesDetailKeepSalesInoutRecordList> list = salesDetailKeepSalesInoutRecordListDao
				.getList(salesNo, frm.getItemCd());
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 数値のフォーマット
		String unitDivision = frm.getUnitDivision();
		String venderCd = frm.getVenderCd();
		CheckDigitUtilsLogic checker = super.getChecker();
		for (SalesDetailKeepSalesInoutRecordList bean : list) {
			// 入庫先のロケーションコードを編集
			bean.setInLocationCd(editInLocationCd(bean.getLocationCd(), frm
					.getHousingLocationCd()));
			// 数量
			bean.setStrQty(checker.format(unitDivision,
				SalesConst.VENDER_DIVISION_TS, venderCd, bean.getQty()));
			// 手持在庫
			bean.setStrInventoryQty(checker
					.format(unitDivision, SalesConst.VENDER_DIVISION_TS,
						venderCd, bean.getInventoryQty()));
			// 検査待
			bean.setStrInspectionQty(checker.format(unitDivision,
				SalesConst.VENDER_DIVISION_TS, venderCd, bean
						.getInspectionQty()));
			// 前回値をセット
			bean.setPrevQty(bean.getQty());
		}

		return list;
	}

	/**
	 * 入庫先ロケーションコードを編集する
	 * @param outLocationCd 出庫ロケーションコード
	 * @param inLocationStr 入庫ロケーション(ex.BV)
	 * @param loginInfo ログイン情報
	 */
	private String editInLocationCd(final String outLocationCd,
			final String inLocation) {
		String inLocationCd = null;

		String inCd = inLocation.substring(inLocation.length() - 1);
		String cutLocationCd = outLocationCd.substring(0, outLocationCd
				.length() - 1);
		inLocationCd = cutLocationCd + inCd;
		return inLocationCd;
	}

	/**
	 * 売上トランザクションデータの登録処理を行う
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void insert(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {

		// 売上トランザクションを登録
		String salesNo = super.insert(frm, loginInfo);

		Timestamp inoutDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrSalesDate());
		String errMsg = "errors.sales.stock.update";
		// 在庫更新処理
		Stockinout stock = new Stockinout(zaiCtrlDao);

		// 出庫ロケーション情報を売上受払履歴に登録
		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		BigDecimal salesRowNo = BigDecimal.ONE;
		for (SalesDetailKeepSalesInoutRecordList bean : list) {
			SalesInoutRecord insBean = new SalesInoutRecord();
			insBean.setSalesNo(salesNo);
			insBean.setSalesRowNo(salesRowNo);
			insBean.setLocationCd(bean.getLocationCd());
			// 入庫先のロケーションコードを編集
			bean.setInLocationCd(editInLocationCd(bean.getLocationCd(), frm
					.getHousingLocationCd()));
			insBean.setLotNo(bean.getLotNo());
			insBean.setQty(bean.getQty());
			insBean.setInputorCd(loginInfo.getTantoCd());
			insBean.setInputDate(inoutDate);
			insBean.setUpdatorCd(loginInfo.getTantoCd());
			insBean.setUpdateDate(insBean.getInputDate());
			try {
				salesInoutRecordDao.insert(insBean);
				try {
					// 在庫更新－預かり品売上計上
					if (!stock.salesWithoutDelivery(frm.getItemCd(), bean
							.getQty(), inoutDate, bean.getLocationCd(), bean
							.getInLocationCd(), bean.getLotNo(), insBean
							.getSalesNo(), frm.getRemark(), frm
							.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}

				salesRowNo = salesRowNo.add(BigDecimal.ONE);
			} catch (NotSingleRowUpdatedRuntimeException e) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			} catch (SQLRuntimeException e) {
				// 一意制約エラー
				throw new DuplicateRuntimeException(0);
			}
		}
	}

	/**
	 * 売上トランザクションデータの更新処理を行う
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {
		// 売上トランザクション更新
		super.update(frm, loginInfo);

		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		List<SalesDetailKeepSalesInoutRecordList> delList = frm
				.getDeleteLocationList();
		String errMsg = "errors.sales.stock.update";
		// 在庫更新処理
		Stockinout stock = new Stockinout(zaiCtrlDao);

		try {
			Timestamp inoutDate = AecDateUtils.getTimestampYmdFormat(frm
					.getStrBeforeSalesDate());
			
			// 出庫ロケーション情報を売上受払履歴から削除する
			for (SalesDetailKeepSalesInoutRecordList bean : delList) {
				// 新規追加の削除は対象外
				if (StringUtils.isEmpty(bean.getSalesNo())) {
					continue;
				}
				try {
					// 在庫更新－預かり品売上計上取消
					if (!stock.deSalesWithoutDelivery(frm.getItemCd(), bean
							.getPrevQty(), inoutDate, bean.getLocationCd(),
						bean.getInLocationCd(), bean.getLotNo(), bean
								.getSalesNo(), frm.getRemark(), frm
								.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				SalesInoutRecord delBean = new SalesInoutRecord();
				IgnoreCaseBeanUtils.copyProperties(delBean, bean);
				int delNum = salesInoutRecordDao.delete(delBean);
				if (delNum == 0) {
					// 対象データ無し
					throw new NoDataException();
				}
			}

			// 出庫ロケーション情報を売上受払履歴から削除する
			for (SalesDetailKeepSalesInoutRecordList bean : list) {
				// 新規追加は対象外
				if (StringUtils.isEmpty(bean.getSalesNo())) {
					continue;
				}
				try {
					// 在庫更新－預かり品売上計上取消
					if (!stock.deSalesWithoutDelivery(frm.getItemCd(), bean
							.getPrevQty(), inoutDate, bean.getLocationCd(),
						bean.getInLocationCd(), bean.getLotNo(), bean
								.getSalesNo(), frm.getRemark(), frm
								.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				SalesInoutRecord delBean = new SalesInoutRecord();
				IgnoreCaseBeanUtils.copyProperties(delBean, bean);
				int delNum = salesInoutRecordDao.delete(delBean);
				if (delNum == 0) {
					// 対象データ無し
					throw new NoDataException();
				}
			}
			// 出庫ロケーション情報を売上受払履歴へ登録する
			inoutDate = AecDateUtils.getTimestampYmdFormat(frm
					.getStrSalesDate());
			BigDecimal salesRowNo = BigDecimal.ONE;
			for (SalesDetailKeepSalesInoutRecordList bean : list) {
				SalesInoutRecord insBean = new SalesInoutRecord();
				IgnoreCaseBeanUtils.copyProperties(insBean, bean);
				insBean.setSalesNo(frm.getSalesNo());
				insBean.setSalesRowNo(salesRowNo);
				// 入庫先ロケーションを設定
				bean.setInLocationCd(editInLocationCd(bean.getLocationCd(), frm
						.getHousingLocationCd()));
				if (insBean.getInputorCd() == null) {
					insBean.setInputorCd(loginInfo.getTantoCd());
					insBean.setInputDate(inoutDate);
					insBean.setUpdatorCd(loginInfo.getTantoCd());
					insBean.setUpdateDate(insBean.getInputDate());
				} else {
					insBean.setUpdatorCd(loginInfo.getTantoCd());
					insBean.setUpdateDate(inoutDate);
				}
				int insNum = salesInoutRecordDao.insert(insBean);
				if (insNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
				try {
					// 在庫更新－預かり品売上計上
					if (!stock.salesWithoutDelivery(frm.getItemCd(), bean
							.getQty(), inoutDate, bean.getLocationCd(), bean
							.getInLocationCd(), bean.getLotNo(), insBean
							.getSalesNo(), frm.getRemark(), frm
							.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				salesRowNo = salesRowNo.add(BigDecimal.ONE);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 削除処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {

		// 売上トランザクション削除
		super.delete(frm, loginInfo);

		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		List<SalesDetailKeepSalesInoutRecordList> delList = frm
				.getDeleteLocationList();
		String errMsg = "errors.sales.stock.update";
		// 在庫更新処理
		Stockinout stock = new Stockinout(zaiCtrlDao);

		// 売上区分が取消ではない場合
		if (Integer.parseInt(frm.getCategoryDivision()) > 0) {
			Timestamp inoutDate = AecDateUtils.getTimestampYmdFormat(frm
					.getStrBeforeSalesDate());

			// 出庫ロケーション情報を売上受払履歴から削除する
			for (SalesDetailKeepSalesInoutRecordList bean : delList) {
				// 新規追加の削除は対象外
				if (StringUtils.isEmpty(bean.getSalesNo())) {
					continue;
				}
				try {
					// 在庫更新－預かり品売上計上取消
					if (!stock.deSalesWithoutDelivery(frm.getItemCd(), bean
							.getPrevQty(), inoutDate, bean.getLocationCd(),
						bean.getInLocationCd(), bean.getLotNo(), bean
								.getSalesNo(), frm.getRemark(), frm
								.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				SalesInoutRecord delBean = new SalesInoutRecord();
				IgnoreCaseBeanUtils.copyProperties(delBean, bean);
				int delNum = salesInoutRecordDao.delete(delBean);
				if (delNum == 0) {
					// 対象データ無し
					throw new NoDataException();
				}
			}
			// 出庫ロケーション情報を売上受払履歴から削除する
			for (SalesDetailKeepSalesInoutRecordList bean : list) {
				// 新規追加は対象外
				if (StringUtils.isEmpty(bean.getSalesNo())) {
					continue;
				}
				try {
					// 在庫更新－預かり品売上計上取消
					if (!stock.deSalesWithoutDelivery(frm.getItemCd(), bean
							.getPrevQty(), inoutDate, bean.getLocationCd(),
						bean.getInLocationCd(), bean.getLotNo(), bean
								.getSalesNo(), frm.getRemark(), frm
								.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				SalesInoutRecord delBean = new SalesInoutRecord();
				IgnoreCaseBeanUtils.copyProperties(delBean, bean);
				int delNum = salesInoutRecordDao.delete(delBean);
				if (delNum == 0) {
					// 対象データ無し
					throw new NoDataException();
				}
			}

			// 売上区分が取消の場合
		} else {
			Timestamp inoutDate = AecDateUtils.getTimestampYmdFormat(frm
					.getStrSalesDate());
			// 在庫更新のみ行う
			for (SalesDetailKeepSalesInoutRecordList bean : list) {
				try {
					// 在庫更新－預かり品売上計上
					if (!stock.salesWithoutDelivery(frm.getItemCd(), bean
							.getQty(), inoutDate, bean.getLocationCd(), bean
							.getInLocationCd(), bean.getLotNo(), bean
							.getSalesNo(), frm.getRemark(), frm
							.getRyDescription(), loginInfo.getTantoCd())) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsg, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					SalesLogicException ex = new SalesLogicException(errMsg, "");
					ex.setModuleCd("Stockinout");
					ex.setInsideErrCd(bean.getSalesNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
			}
		}
	}

	/**
	 * 売上取消処理を行う.
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void cancel(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {
		// 売上取消処理を行う
		super.cancel(frm, loginInfo);

		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		Timestamp inoutDate = AecDateUtils.getTimestampYmdFormat(frm
				.getStrBeforeSalesDate());
		String errMsg = "errors.sales.stock.update";
		// 在庫更新処理
		Stockinout stock = new Stockinout(zaiCtrlDao);

		// 在庫更新のみ行う
		for (SalesDetailKeepSalesInoutRecordList bean : list) {
			try {
				// 在庫更新－預かり品売上計上取消
				if (!stock.deSalesWithoutDelivery(frm.getItemCd(), bean
						.getQty(), inoutDate, bean.getLocationCd(), bean
						.getInLocationCd(), bean.getLotNo(), bean.getSalesNo(),
					frm.getRemark(), frm.getRyDescription(), loginInfo
							.getTantoCd())) {
					ShippingLogicException ex = new ShippingLogicException(
							errMsg, "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				SalesLogicException ex = new SalesLogicException(errMsg, "");
				ex.setModuleCd("Stockinout");
				ex.setInsideErrCd(bean.getSalesNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
		}
	}

	/**
	 * 売上の仮単価フラグの更新処理を行う 
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param loginInfo ログイン情報
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void tmpUnitflagUpdate(final SalesDetailKeepForm frm, final LoginInfo loginInfo)
			throws NoDataException, Exception {
		
		super.tmpUnitflagUpdate(frm, loginInfo);
	}
	
	/**
	 * 入庫ロケーションの存在をチェックする
	 * @param frm 売上詳細(預り品)画面FORM
	 * @param errors ActionMessage
	 */
	public void checkInLocation(final SalesDetailKeepForm frm,
			final ActionMessages errors) {
		ActionMessage errMsg = null;
		int rowNo = 1;

		List<SalesDetailKeepSalesInoutRecordList> list = frm.getLocationList();
		String outLocation = null;
		for (SalesDetailKeepSalesInoutRecordList bean : list) {
			outLocation = editInLocationCd(bean.getLocationCd(), frm
					.getHousingLocationCd());
			if (locationDao.getEntity(outLocation) == null) {
				errMsg = new ActionMessage("errors.sales.in.location.row",
						String.valueOf(rowNo));
				errors.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			}
			rowNo++;
		}
	}

	/**
	 * パラメータチェック
	 * @param param パラメータ
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}
	


	/* -------------------- setter -------------------- */

	/**
	 * 売上詳細(預り品)画面用売上トランザクションテーブル用Daoを設定する
	 * @param salesDetailKeepEntityDao 売上詳細(預り品)画面用売上トランザクションテーブル用Dao
	 */
	public void setSalesDetailKeepEntityDao(
			final SalesDetailKeepEntityDao salesDetailKeepEntityDao) {
		this.salesDetailKeepEntityDao = salesDetailKeepEntityDao;
	}

	/**
	 * 売上詳細(預り品)画面_得意先取得用Daoを設定する
	 * @param salesDetailKeepVenderListDao 売上詳細(預り品)画面_得意先取得用Dao
	 */
	public void setSalesDetailKeepVenderListDao(
			final SalesDetailKeepVenderListDao salesDetailKeepVenderListDao) {
		this.salesDetailKeepVenderListDao = salesDetailKeepVenderListDao;
	}

	/**
	 * 売上詳細(預り品)画面_売上受払取得用Daoを設定する
	 * @param salesDetailKeepSalesInoutRecordListDao 売上詳細(預り品)画面_売上受払取得用Dao
	 */
	public void setSalesDetailKeepSalesInoutRecordListDao(
			final SalesDetailKeepSalesInoutRecordListDao salesDetailKeepSalesInoutRecordListDao) {
		this.salesDetailKeepSalesInoutRecordListDao = salesDetailKeepSalesInoutRecordListDao;
	}

	/**
	 * 売上受払履歴用Daoを設定する
	 * @param salesInoutRecordDao 売上受払履歴用Dao
	 */
	public void setSalesInoutRecordDao(
			final SalesInoutRecordDao salesInoutRecordDao) {
		this.salesInoutRecordDao = salesInoutRecordDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * ロケーションマスタ用Daoを設定します。
	 * @param locationDao ロケーションマスタ用Dao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

}
