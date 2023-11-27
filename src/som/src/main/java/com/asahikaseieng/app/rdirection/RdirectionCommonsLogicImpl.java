/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.InoutRecordUpdate;
import com.asahikaseieng.app.common.stockinout.StockinoutForDirection;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.inoutrecord.InoutRecordDao;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造実績 共通ロジック実装クラス
 * @author tosco
 */
public class RdirectionCommonsLogicImpl implements RdirectionCommonsLogic {
	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 指図ヘッダ操作DAO */
	private RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao;

	/** 製造実績－工程順序コンボボックス用Dao */
	private RdirectionProcedureSetpNoForComboboxesDao rdirectionProcedureSetpNoForComboboxesDao;

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 受払履歴Dao */
	private InoutRecordDao inoutRecordDao;

	/**
	 * コンストラクタ
	 */
	public RdirectionCommonsLogicImpl() {
	}

	/**
	 * 指図番号で検索
	 * @param directionNo 指図番号
	 * @return RdirectionDirectionHeaderList
	 * @throws NoDataException 検索結果が存在しなかった場合発生
	 */
	public RdirectionDirectionHeaderList findByDirectionNo(
			final String directionNo) throws NoDataException {
		RdirectionDirectionHeaderList header = rdirectionDirectionHeaderListDao
				.findByDirectionNo(directionNo);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 指図ヘッダ更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void updateDirectionHeader(final RdirectionDirectionHeaderList bean) {
		try {
			// 指図ヘッダーに更新
			int updateNum = rdirectionDirectionHeaderListDao.update(bean);
			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
			// 受払履歴の時刻を変更する
			InoutRecordUpdate inout = new InoutRecordUpdate(inoutRecordDao,
					bean.getUpdatorCd());
			// 製造入庫
			inout.updateInoutRecord4Pro(bean.getDirectionNo(), bean
					.getResultEdate());
			// 製造出庫
			inout.updateInoutRecord4Mat(bean.getDirectionNo(), bean
					.getResultSdate());

			// ステータス更新完了の場合
			if (RdirectionConst.DIRECTION_STATUS_COMPLETED.equals(bean
					.getDirectionStatus())) {
				// 在庫更新処理
				StockinoutForDirection stock = new StockinoutForDirection(
						zaiCtrlDao);
				String errMsg = "errors.rdirection.stock.update";
				try {
					/* 在庫更新－包装・製造指図完了 */
					if (!stock.completeDirection(bean.getDirectionDivision(),
						bean.getDirectionNo(), bean.getUpdatorCd())) {
						throw new LogicExceptionEx(errMsg);
					}
				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 工程順序リストを取得する
	 * @param directionNo 指図番号
	 * @return List<RdirectionProcedureSetpNoForComboboxes>
	 */
	public List<RdirectionProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final String directionNo) {
		return rdirectionProcedureSetpNoForComboboxesDao
				.findByDirectionNo(directionNo);
	}

	/**
	 * 工程順序コンボボックス作成
	 * @param directionNo 指図番号
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createProcedureSetpNoCombobox(
			final String directionNo, final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("0");
			res.add(item);
		}
		if (directionNo != null) {

			// 工程順序検索
			List<RdirectionProcedureSetpNoForComboboxes> list = getProcedureSetpNoList(directionNo);
			if (list != null) {
				for (RdirectionProcedureSetpNoForComboboxes bean : list) {
					ComboBoxItems item = new ComboBoxItems();
					item.setValues(bean.getStepNo().toString());
					item.setLabales(bean.getSeq().toString());
					res.add(item);
				}
			}
		}
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

	// setter------------------------------------------
	/**
	 * 指図ヘッダ操作DAOを設定します。
	 * @param rdirectionDirectionHeaderListDao 指図ヘッダ操作DAO
	 */
	public void setRdirectionDirectionHeaderListDao(
			final RdirectionDirectionHeaderListDao rdirectionDirectionHeaderListDao) {
		this.rdirectionDirectionHeaderListDao = rdirectionDirectionHeaderListDao;
	}

	/**
	 * 製造実績－工程順序コンボボックス用Daoを設定します。
	 * @param rdirectionProcedureSetpNoForComboboxesDao 製造実績－工程順序コンボボックス用Dao
	 */
	public void setRdirectionProcedureSetpNoForComboboxesDao(
			final RdirectionProcedureSetpNoForComboboxesDao rdirectionProcedureSetpNoForComboboxesDao) {
		this.rdirectionProcedureSetpNoForComboboxesDao = rdirectionProcedureSetpNoForComboboxesDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * inoutRecordDaoを設定します。
	 * @param inoutRecordDao inoutRecordDao
	 */
	public void setInoutRecordDao(final InoutRecordDao inoutRecordDao) {
		this.inoutRecordDao = inoutRecordDao;
	}
}
