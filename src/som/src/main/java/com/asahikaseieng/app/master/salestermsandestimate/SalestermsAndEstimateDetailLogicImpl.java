
/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.salestermsandestimate.SalestermsAndEstimate;
import com.asahikaseieng.dao.entity.master.salestermsandestimate.SalestermsAndEstimateDao;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersion;
import com.asahikaseieng.dao.nonentity.master.itemqueuelastversion.ItemQueueLastVersionDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetail;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail.SalestermsAndEstimateDetailDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheckDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProConSalestermsAndEstimateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 販売条件・見積単価 コピー作成・削除詳細ロジック 実装クラス.
 * @author t0011036
 */
public class SalestermsAndEstimateDetailLogicImpl implements SalestermsAndEstimateDetailLogic {

	private SalestermsAndEstimateDao salestermsAndEstimateDao;
	
	private ItemDetailDao itemDetailDao;

	private ItemQueueLastVersionDao itemQueueLastVersionDao;
	
	private SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao;
	
	private SalestermsAndEstimateDetailDao salestermsAndEstimateDetailDao;
	
	private SalestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao;
	
	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;
	
	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao;
	
	/** 販売条件・見積単価コピー・削除確定or確定取消処理 異常終了 */
	protected static final BigDecimal MAKE_SALESTERMSANDESTIMATE_RECORD_ERROR = new BigDecimal(
			-9);
	
	/** エラーログ出力用モジュールコード */
	protected static final String CON_SALESTERMSANDESTIMATE_MODULE_CD = "PRO_CON_SALESTERMSANDESTIMATE";


	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateDetailLogicImpl() {
	}
	
	/**
	 * 検索処理を行う（詳細画面遷移用）.
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 * @throws NoDataException NoDataException
	 */
	public SalestermsAndEstimate getEntity(final String pkNo) throws NoDataException {
		if (StringUtils.isEmpty(pkNo)) {
			throw new IllegalArgumentException("pkNo is empty");
		}

		SalestermsAndEstimate bean = salestermsAndEstimateDao.getEntity(pkNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	public ItemDetail getItemEntity(final String itemCd) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, null);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return ItemQueueLastVersion
	 */
	public ItemQueueLastVersion getItemQueueEntity(final String itemCd) {
		ItemQueueLastVersion bean = itemQueueLastVersionDao
				.getLastVersion(itemCd);
		return bean;
	}
	
	/**
	 * 販売条件マスタの該当品目有無確認用
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	public SalestermsAndEstimateItemCheck getSalestermsItemCount(final String itemCd) {
		SalestermsAndEstimateItemCheck bean = salestermsAndEstimateItemCheckDao
				.getSalestermsItemCount(itemCd);
		return bean;
	}
	
	/**
	 * 見積ファイルの該当品目有無確認用
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	public SalestermsAndEstimateItemCheck getEstimateItemCount(final String itemCd) {
		SalestermsAndEstimateItemCheck bean = salestermsAndEstimateItemCheckDao
				.getEstimateItemCount(itemCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final SalestermsAndEstimate bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			salestermsAndEstimateDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 登録登録
	 * @param bean 登録データ
	 */
	public void insert(final SalestermsAndEstimate bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 登録処理 */
			salestermsAndEstimateDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除登録
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final SalestermsAndEstimate bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			salestermsAndEstimateDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}
	
	/**
	 * 詳細検索
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimateDetail
	 */
	public SalestermsAndEstimateDetail getDetailEntity(final String pkNo) {
		SalestermsAndEstimateDetail bean = salestermsAndEstimateDetailDao.getEntity(pkNo);
		return bean;
	}

	/**
	 * 販売条件・見積単価コピー・削除確定or確定取消処理を行う(詳細画面の「確定」or「確定取消」ボタン押下時).
	 * @param frm 販売条件・見積単価コピー・削除詳細画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	public void conSalestermsAndEstimate(final SalestermsAndEstimateDetailForm frm,
			final String tantoCd) {

		ProConSalestermsAndEstimateCallDto dto = new ProConSalestermsAndEstimateCallDto();

		dto.setPStrTantoCd(tantoCd);
		dto.setpPkNo(frm.getPkNo());
		// true：確定処理、false：確定取消処理
		dto.setpConfirmFlg(frm.getConfirmFlg());
		
		// プロシージャ実行
		procedureCallDao.proConSalestermsAndEstimate(dto);

		// エラーメッセージを設定
		frm.setErrorCd(dto.getPStrErrorCd());
		frm.setErrorMsg(dto.getPStrErrorMsg());
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

		log.setModuleCd(CON_SALESTERMSANDESTIMATE_MODULE_CD);
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
	
	/**
	 * 処理区分リスト取得
	 * @return List<NamesListForComboboxes>
	 */
	public List<SalestermsandestimateNamesListForComboboxes> getProcessDivisionList() {
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsandestimateNamesListForComboboxesDao
				.getProcessDivision("PRDI");
		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemQueueLastVersionDaoを設定します。
	 * @param itemQueueLastVersionDao itemQueueLastVersionDao
	 */
	public void setItemQueueLastVersionDao(
			final ItemQueueLastVersionDao itemQueueLastVersionDao) {
		this.itemQueueLastVersionDao = itemQueueLastVersionDao;
	}

	/**
	 * salestermsAndEstimateDetailDaoを設定します。
	 * @param salestermsAndEstimateDetailDao salestermsAndEstimateDetailDao
	 */
	public void setSalestermsAndEstimateDetailDao(
			final SalestermsAndEstimateDetailDao salestermsAndEstimateDetailDao) {
		this.salestermsAndEstimateDetailDao = salestermsAndEstimateDetailDao;
	}

	/**
	 * salestermsAndEstimateDaoを設定します。
	 * @param salestermsAndEstimateDao salestermsAndEstimateDao
	 */
	public void setSalestermsAndEstimateDao(
			final SalestermsAndEstimateDao salestermsAndEstimateDao) {
		this.salestermsAndEstimateDao = salestermsAndEstimateDao;
	}
	
	/**
	 * プロシージャコール用のDaoを設定します。
	 * @param procedureCallDao プロシージャコール用のDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}
	
	/**
	 * エラーログ出力用のDaoを設定します。
	 * @param errorLogDao エラーログ出力用
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * salestermsandestimateNamesListForComboboxesDaoを設定します。
	 * @param salestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao
	 */
	public void setSalestermsandestimateNamesListForComboboxesDao(
			final SalestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao) {
		this.salestermsandestimateNamesListForComboboxesDao = salestermsandestimateNamesListForComboboxesDao;
	}

	/**
	 * salestermsAndEstimateItemCheckDaoを設定します。
	 * @param salestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao
	 */
	public void setSalestermsAndEstimateItemCheckDao(
			final SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao) {
		this.salestermsAndEstimateItemCheckDao = salestermsAndEstimateItemCheckDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}
}