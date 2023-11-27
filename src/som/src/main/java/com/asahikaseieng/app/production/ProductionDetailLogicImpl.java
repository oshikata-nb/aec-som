/*
 * Created on 2009/04/08
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.production;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.aspproduction.AspProduction;
import com.asahikaseieng.dao.entity.aspproduction.AspProductionDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.production.ProductionDetail;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailDao;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailList;
import com.asahikaseieng.dao.nonentity.production.ProductionDetailListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 生産計画入力　明細画面 ロジック実装クラス
 * @author tosco
 */
public class ProductionDetailLogicImpl implements ProductionDetailLogic {

	/** 生産計画入力　明細画面ヘッダ用Dao */
	private ProductionDetailDao productionDetailDao;

	/** 生産計画入力　明細画面明細用Dao */
	private ProductionDetailListDao productionDetailListDao;

	/** ASP_PRODUCTIONテーブル用Dao */
	private AspProductionDao aspProductionDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;


	/**
	 * コンストラクタ.
	 */
	public ProductionDetailLogicImpl() {
	}

	/**
	 * 選択された生産計画に該当するデータを取得する.（ヘッダ部）
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return ProductionDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	public ProductionDetail getHeader(final String itemCd, final String orderLet)
		throws NoDataException {

		ProductionDetail bean
			= productionDetailDao.getHeader(itemCd, orderLet);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 選択された生産計画の件数を取得する
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return int 件数
	 * @throws NoDataException 対象データなしエラー
	 */
	public int getCountList(final String itemCd, final String orderLet)
		throws NoDataException {

		int num = productionDetailListDao.getCountList(itemCd, orderLet);

		return num;
	}

	/**
	 * 入力された品目データを取得する.
	 * @param itemCd 品目コード
	 * @return ProductionDetail ヘッダデータ
	 * @throws NoDataException 対象データなしエラー
	 */
	public ProductionDetail getItemEntity(final String itemCd)
		throws NoDataException {

		ProductionDetail bean
			= productionDetailDao.getItemEntity(itemCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}


	/**
	 * 選択された生産計画に該当するデータを取得する.（明細部）
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @param headBean ヘッダ部データ
	 * @return List<ProductionDetailList> 明細部データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ProductionDetailList> getEntity(final String itemCd
								, final String orderLet
								, final ProductionDetail headBean)
									throws NoDataException {

		List<ProductionDetailList> list = productionDetailListDao.getEntity(itemCd, orderLet);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		for (ProductionDetailList bean : list) {
			//ヘッダの情報をコピー
			bean.setUnitOfOperationManagement(headBean.getUnitOfOperationManagement()); //運用管理単位
			bean.setUnit(headBean.getUnit()); //単位

			if (bean.getOrderAcceptQty() != null) {
				bean.setStrOrderAcceptQty(
					checkDigitUtilsLogic.format(bean.getUnitOfOperationManagement()
										, bean.getOrderAcceptQty()));
			}
			if (bean.getOrderExpectQty() != null) {
				bean.setStrOrderExpectQty(
					checkDigitUtilsLogic.format(bean.getUnitOfOperationManagement()
										, bean.getOrderExpectQty()));
			}
			if (bean.getSumOrderQty() != null) {
				bean.setStrSumOrderQty(
					checkDigitUtilsLogic.format(bean.getUnitOfOperationManagement()
										, bean.getSumOrderQty()));
			}

			//小数点桁数、端数区分取得、セット
			NumberChkDisitDetail checkDetail
				= checkDigitUtilsLogic.getCheckDigit(bean.getUnitOfOperationManagement(), " ", " ");

			if (checkDetail.getSmallnumLength() != null) {
				bean.setQuantityDecimalPoint(checkDetail
					.getSmallnumLength().toString());	// 小数点桁数
			}
			if (checkDetail.getRoundDivision() != null) {
				bean.setQuantityRound(checkDetail
					.getRoundDivision().toString());	// 端数区分
			}
		}
		return list;
	}

	/**
	 * 更新、登録処理を行う
	 * @param newBean 更新、登録対象ビーン
	 * @param frm Form
	 * @param loginUserId 更新者ID
	 * @throws NoDataException データ無し例外
	 */
	public void update(final List<AspProduction> newBean
			, final ProductionDetailForm frm
			, final String loginUserId) throws NoDataException {

		try {
			for (AspProduction bean : newBean) {
				//オーダーコードがNullではない＝更新処理
				if (bean.getOrderCd() != null) {
					bean.setUpdatorCd(loginUserId);

					aspProductionDao.update(bean);

				//オーダーコードがNull　＝　新規登録処理
				} else {
					//CD 作成
					String strOrderLet = AecDateUtils.dateFormat(bean.getOrderLet(), "yyyyMMdd");
					String orderCd = "O_" + frm.getItemCd() + "_" + strOrderLet;
					//CDをセット
					bean.setOrderCd(orderCd);
					//品目コード
					bean.setItemCd(frm.getItemCd());

					//システム日時、ログインユーザーの取得
					Timestamp now = AecDateUtils.getCurrentTimestamp();
					//登録者セット
					bean.setInputDate(now);
					bean.setInputorCd(loginUserId);
					//更新者セット
					bean.setUpdateDate(now);
					bean.setUpdatorCd(loginUserId);

					aspProductionDao.insert(bean);
				}
			}

		//更新時に、すでに更新されていた場合
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//更新エラー　OptimisticLockRuntimeExceptionをthrowする
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			//登録エラー　DuplicateRuntimeExceptionをthrowする
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 生産計画入力　明細画面ヘッダ用Daoを設定します。
	 * @param productionDetailDao 生産計画入力　明細画面ヘッダ用Dao
	 */
	public void setProductionDetailDao(
			final ProductionDetailDao productionDetailDao) {
		this.productionDetailDao = productionDetailDao;
	}

	/**
	 * 生産計画入力　明細画面明細用Daoを設定します。
	 * @param productionDetailListDao 生産計画入力　明細画面明細用Dao
	 */
	public void setProductionDetailListDao(
			final ProductionDetailListDao productionDetailListDao) {
		this.productionDetailListDao = productionDetailListDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * ASP_PRODUCTIONテーブル用Daoを設定します。
	 * @param aspProductionDao ASP_PRODUCTIONテーブル用Dao
	 */
	public void setAspProductionDao(final AspProductionDao aspProductionDao) {
		this.aspProductionDao = aspProductionDao;
	}
}
