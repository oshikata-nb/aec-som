/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeAndNumberDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;

/**
 * 在庫・受払ソースを操作するクラス(Shipping用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでprocedureCallDao(PLSQL実行DAO) を設定すること。<BR>
 * ＤＢデータを使って、出荷指示に関する在庫・受払ソース操作（入力、取消）を行う。<BR>
 * 処理コードPLngOpno=1(出荷指図）受払区分PLngInoutdivision=4(出荷払い出し)で処理を行います。
 * @author a7710658
 */
public class StockinoutForShipping {
	/** 在庫更新PROCEDURE DAO宣言 */
	private ZaiCtrlDao zaiCtrlDao;

	/** Log */
	private static Log log = LogFactory.getLog(StockinoutForShipping.class);

	/**
	 * コンストラクタ
	 * @param zaiCtrlDao PLSQL実行DAO
	 */
	public StockinoutForShipping(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	/**
	 * 出荷指図入力（一詳細）<BR>
	 * 出荷引当を行う。引当残ASSIGN_QTY+。<BR>
	 * 対応する受注データが有れば、その受払リソースを取り消す。<BR>
	 * 処理すべき出荷指図がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param slipRowNo 出荷指図行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryShipping(final String slipNo,
			final BigDecimal slipRowNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setStrCode(slipNo);
		dto.setLngRowNo(slipRowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryShipping(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForShipping.entryShipping(" + slipNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryShipping()");
			throw e;
		}
		return true;
	}

	/**
	 * 出荷指図入力（全）<BR>
	 * 出荷引当を行う。引当残ASSIGN_QTY+。<BR>
	 * 対応する受注データが有れば、その受払リソースを取り消す。<BR>
	 * 処理すべき出荷指図がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryShipping(final String slipNo, final String loginUser) {
		return entryShipping(slipNo, null, loginUser);
	}

	/**
	 * 出荷指図取消（一詳細）<BR>
	 * 出荷引当を取り消す。引当残ASSIGN_QTY-。<BR>
	 * 対応する受注データが有れば、その受払リソースを作成する。<BR>
	 * 処理すべき出荷指図がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param slipRowNo 出荷指図行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelShipping(final String slipNo,
			final BigDecimal slipRowNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setStrCode(slipNo);
		dto.setLngRowNo(slipRowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryShipping(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForShipping.cancelShipping(" + slipNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("cancelShipping()");
			throw e;
		}
		return true;
	}

	/**
	 * 出荷指図取消（全）<BR>
	 * 出荷引当を行う。引当残ASSIGN_QTY+。<BR>
	 * 対応する受注データが有れば、その受払リソース作成する。<BR>
	 * 処理すべき出荷指図がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelShipping(final String slipNo, final String loginUser) {
		return cancelShipping(slipNo, null, loginUser);
	}

	/**
	 * 出荷指図完了（一詳細）<BR>
	 * ＤＢの出荷指図を基に受払ソースを無効にする。。
	 * @param slipNo 出荷指図番号
	 * @param slipRowNo 出荷指図行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completeShipping(final String slipNo,
			final BigDecimal slipRowNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_COMPLETE_FLG);
		dto.setStrCode(slipNo);
		dto.setLngRowNo(slipRowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryShipping(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForShipping.completeShipping(" + slipNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("completeShipping()");
			throw e;
		}
		return true;
	}

	/**
	 * 出荷指図完了（全）<BR>
	 * ＤＢの出荷指図を基に受払ソースを無効にする。。
	 * @param slipNo 出荷指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completeShipping(final String slipNo, final String loginUser) {
		return completeShipping(slipNo, null, loginUser);
	}

	/**
	 * 出荷実績入力（全)<BR>
	 * 出荷実績による在庫量INVENTORY_QTY-、引当残ASSIGN_QTY-、を行う。<BR>
	 * 処理すべき出荷実績がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryResult(final String slipNo, final String loginUser) {
		return entryResult(slipNo, null, loginUser);
	}

	/**
	 * 出荷実績入力（一詳細)<BR>
	 * 出荷実績による在庫量INVENTORY_QTY-、引当残ASSIGN_QTY-、を行う。<BR>
	 * 処理すべき出荷実績がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param slipRowNo 出荷指図行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryResult(final String slipNo, final BigDecimal slipRowNo,
			final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_RESULT_FLG);
		dto.setStrCode(slipNo);
		dto.setLngRowNo(slipRowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryShipping(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForShipping.entryResult(" + slipNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryResult()");
			throw e;
		}
		return true;
	}

	/**
	 * 出荷実績取消（全)<BR>
	 * 出荷実績による在庫量INVENTORY_QTY+、引当残ASSIGN_QTY+、を行う。<BR>
	 * 処理すべき出荷実績がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelResult(final String slipNo, final String loginUser) {
		return cancelResult(slipNo, null, loginUser);
	}

	/**
	 * 出荷実績取消（一詳細)<BR>
	 * 出荷実績による在庫量INVENTORY_QTY+、引当残ASSIGN_QTY+、を行う。<BR>
	 * 処理すべき出荷実績がＤＢに存在すること。
	 * @param slipNo 出荷指図番号
	 * @param slipRowNo 出荷指図行番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelResult(final String slipNo,
			final BigDecimal slipRowNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_RESULT_FLG);
		dto.setStrCode(slipNo);
		dto.setLngRowNo(slipRowNo);
		dto.setStrLoginUser(loginUser);
		dto.setOutPara("");
		this.zaiCtrlDao.entryShipping(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForShipping.cancelResult(" + slipNo + ","
					+ loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("cancelResult()");
			throw e;
		}
		return true;
	}
}
