/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.stockinout;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeAnd3NumberDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.StrCodeAndNumberDto;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.exception.ZaikoCtrlExceptionEx;

/**
 * 在庫・受払ソースを操作するクラス(Direction用) <BR>
 * **LogicImplから呼ぶこと。<BR>
 * コンストラクタでZaiCtrlDao(PLSQL実行DAO)を設定すること。<BR>
 * ＤＢデータを使って、包装・製造指図、指図確定、包装・製造実績、検査待在庫計上、製品検査完了<BR>
 * に関する在庫・受払ソース操作（入力、取消）を行う。
 * @author a7710658
 */
public class StockinoutForDirection {
	/** 在庫更新PROCEDURE DAO宣言 */
	private ZaiCtrlDao zaiCtrlDao;

	/** Log */
	private static Log log = LogFactory.getLog(StockinoutForDirection.class);

	/**
	 * コンストラクタ
	 * @param zaiCtrlDao PLSQL実行Dao
	 */
	public StockinoutForDirection(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}

	// /**
	// * directionDivision := 1:バッチ指図
	// */
	// private static final BigDecimal DIRECTION_DIVISION_BATCH = new
	// BigDecimal(1);
	//
	// /**
	// * directionDivision :=2:充填・包装指図
	// */
	// private static final BigDecimal DIRECTION_DIVISION_PACCKAGE = new
	// BigDecimal(
	// 2);
	//
	// /**
	// * directionDivision :=3:詰替・貼替指図
	// */
	// private static final BigDecimal DIRECTION_DIVISION_REPACK = new
	// BigDecimal(
	// 3);
	//
	// /**
	// * directionDivision :=4:外注
	// */
	// private static final BigDecimal DIRECTION_DIVISION_OUTSOURCE = new
	// BigDecimal(
	// 4);

	// TODO 外注はここで区別するのか、確認する。:

	/**
	 * 包装・製造指図入力 <BR>
	 * ＤＢの包装・製造指図を基に受払ソースを作成する。<BR>
	 * 処理コードPLngOpno=包装103,製造105 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara()
					+ ":StockinoutForPurchase.entryDirection(" + directionNo
					+ "," + loginUser + ")";
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図取消 <BR>
	 * ＤＢの包装・製造指図を基に受払ソースを取り消す。<BR>
	 * 処理コードPLngOpno=包装103,製造105 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("cancelDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図確定 <BR>
	 * ＤＢの包装・製造指図を基に受払ソースを取り消し、<BR>
	 * 在庫更新（引当）を行う。BACKORDER_QTY+ <BR>
	 * 処理コードPLngOpno=包装103,製造105および3 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean fixDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_KAKUTEI_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("fixDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図確定取消 <BR>
	 * ＤＢの包装・製造指図を基に受払ソースを作成し、<BR>
	 * 在庫更新（引当）を取り消す。BACKORDER_QTY- <BR>
	 * 処理コードPLngOpno=包装103,製造105および3 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deFixDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_KAKUTEI_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deFixDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図仕上入力 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を行う。 <BR>
	 * 処理コードPLngOpno=包装41 発注残BACKORDER_QTY- 仕上り在庫+,<BR>
	 * 処理コードPLngOpno製造42 在庫数量INVENTORY_QTY+ 発注残BACKORDER_QTY- <BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean finishDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_FINISH_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("finishDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図仕上取消 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を取り消す。 <BR>
	 * 処理コードPLngOpno=包装41 発注残BACKORDER_QTY+ 仕上り在庫-,<BR>
	 * 処理コードPLngOpno製造42 在庫数量INVENTORY_QTY- 発注残BACKORDER_QTY+ <BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deFinishDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_FINISH_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deFinishDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図検査待ち移行 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を行う。 <BR>
	 * 処理コードPLngOpno=包装5 仕上り在庫-,検査待在庫INSPECTION_QTY+<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean inspectionDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_INSPECTION_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("inspectionDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図検査待ち移行取消 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を取り消す。 <BR>
	 * 処理コードPLngOpno=包装5 仕上り在庫+,検査待在庫INSPECTION_QTY-<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deInspectionDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_INSPECTION_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultDirection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deInspectionDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図製品完成 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を行う。 <BR>
	 * 処理コードPLngOpno=包装51 検査待在庫INSPECTION_QTY-,在庫数量INVENTORY_QTY+<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean gradeDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_GRADE_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultGrade(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("gradeDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図製品完成取消 <BR>
	 * ＤＢの包装・製造指図を基に在庫更新（引当）を取り消す。 <BR>
	 * 処理コードPLngOpno=包装51 検査待在庫INSPECTION_QTY+,在庫数量INVENTORY_QTY-<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deGradeDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_GRADE_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultGrade(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deGradeDirection()");
			throw e;
		}
		return true;
	}

	/**
	 * 包装・製造指図完了 <BR>
	 * ＤＢの包装・製造指図を元に受払ソースを無効にする。 <BR>
	 * 処理コードPLngOpno=包装51 検査待在庫INSPECTION_QTY+,在庫数量INVENTORY_QTY-<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean completeDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAndNumberDto dto = new StrCodeAndNumberDto();
		dto.setLngFlg(StockinoutConstant.BD_COMPLETE_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultDirection(dto);
		if (!dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("completeDirection(head)");
			throw e;
		}
		StrCodeAnd3NumberDto dtof = new StrCodeAnd3NumberDto();
		dtof.setLngFlg(StockinoutConstant.BD_COMPLETE_FLG);
		dtof.setLngRowNo(directionDivision);
		dtof.setStrCode(directionNo);
		dtof.setLngStepNo(null);
		dtof.setLngLineNo(null);
		dtof.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultFormula(dtof);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("completeDirection(formula)");
			throw e;
		}
		return true;

	}

	/**
	 * 包装・製造指図完了取消 <BR>
	 * ＤＢの包装・製造指図を元に受払ソースを有効にする。 <BR>
	 * 処理コードPLngOpno=包装51 検査待在庫INSPECTION_QTY+,在庫数量INVENTORY_QTY-<BR>
	 * 受払区分=製造受入1
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deCompleteDirection(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return false;
	}

	/**
	 * 配合指図入力（指図全て） <BR>
	 * ＤＢの全配合指図を基に受払ソースを作成する。<BR>
	 * 処理コードPLngOpno=製造106 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return entryFormula(null, null, directionDivision, directionNo,
			loginUser);
	}

	/**
	 * 配合指図入力（一投入） <BR>
	 * ＤＢの配合指図を基に受払ソースを作成する。<BR>
	 * 処理コードPLngOpno=製造106 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean entryFormula(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("entryFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 配合指図取消（指図全て） <BR>
	 * ＤＢの全配合指図を基に受払ソースを取り消す。<BR>
	 * 処理コードPLngOpno=製造106 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return cancelFormula(null, null, directionDivision, directionNo,
			loginUser);
	}

	/**
	 * 配合指図取消（一投入） <BR>
	 * ＤＢの配合指図を基に受払ソースを取り消す。<BR>
	 * 処理コードPLngOpno=製造106 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean cancelFormula(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("cancelFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 配合指図確定（指図全て） <BR>
	 * ＤＢの全配合指図を基に受払ソースを取り消し、<BR>
	 * 在庫更新（引当）を行う。引当残ASSIGN_QTY+。<BR>
	 * 処理コードPLngOpno=4 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean fixFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return fixFormula(null, null, directionDivision, directionNo, loginUser);
	}

	/**
	 * 配合指図確定（一投入） <BR>
	 * ＤＢの配合指図を基に受払ソースを取り消し、<BR>
	 * 在庫更新（引当）を行う。引当残ASSIGN_QTY+。<BR>
	 * 処理コードPLngOpno=4 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean fixFormula(final BigDecimal stepNo, final BigDecimal lineNo,
			final BigDecimal directionDivision, final String directionNo,
			final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_KAKUTEI_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("fixFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 配合指図確定取消（指図全て） <BR>
	 * ＤＢの全配合指図を基に受払ソースを作成し、<BR>
	 * 在庫更新（引当）を取り消す。引当残ASSIGN_QTY-。<BR>
	 * 処理コードPLngOpno=4 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deFixFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return deFixFormula(null, null, directionDivision, directionNo,
			loginUser);
	}

	/**
	 * 配合指図確定取消（一投入） <BR>
	 * ＤＢの配合指図を基に受払ソースを作成し、<BR>
	 * 在庫更新（引当）を取り消す。引当残ASSIGN_QTY-。<BR>
	 * 処理コードPLngOpno=4 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deFixFormula(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_KAKUTEI_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.entryFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deFixFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 配合実績入力（指図全て） <BR>
	 * ＤＢの全配合指図を基に在庫更新（引当）を行う。<BR>
	 * 引当残ASSIGN_QTY-。在庫数量INVENTORY_QTY-<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean resultFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return resultFormula(null, null, directionDivision, directionNo,
			loginUser);
	}

	/**
	 * 配合実績入力（一投入） <BR>
	 * ＤＢの配合指図を基に在庫更新（引当）を行う。<BR>
	 * 引当残ASSIGN_QTY-。在庫数量INVENTORY_QTY-<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean resultFormula(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("resultFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 配合実績取消（指図全て） <BR>
	 * ＤＢの全配合指図を基に在庫更新（引当）を取り消す。<BR>
	 * 引当残ASSIGN_QTY+。在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deResultFormula(final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		return deResultFormula(null, null, directionDivision, directionNo,
			loginUser);
	}

	/**
	 * 配合実績取消（一投入） <BR>
	 * ＤＢの配合指図を基に在庫更新（引当）を取り消す。<BR>
	 * 引当残ASSIGN_QTY+。在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param directionDivision 指図区分
	 * @param directionNo 指図番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deResultFormula(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal directionDivision,
			final String directionNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setLngRowNo(directionDivision);
		dto.setStrCode(directionNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultFormula(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deResultFormula()");
			throw e;
		}
		return true;
	}

	/**
	 * 外注投入実績入力（全投入） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を行う。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean resultInjection(final String orderNo, final String loginUser) {
		return resultInjection(null, null, null, orderNo, loginUser);
	}

	/**
	 * 外注投入実績入力（一レシピ） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を行う。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param recipeId レシピＩＤ
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean resultInjection(final BigDecimal recipeId,
			final String orderNo, final String loginUser) {
		return resultInjection(null, null, recipeId, orderNo, loginUser);
	}

	/**
	 * 外注投入実績入力（一投入） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を行う。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param recipeId レシピＩＤ
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean resultInjection(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal recipeId,
			final String orderNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_NYURYOKU_FLG);
		dto.setLngRowNo(recipeId);
		dto.setStrCode(orderNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultInjection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("resultInjection()");
			throw e;
		}
		return true;
	}

	/**
	 * 外注投入実績取消（全投入） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を取り消す。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deResultInjection(final String orderNo,
			final String loginUser) {
		return deResultInjection(null, null, null, orderNo, loginUser);
	}

	/**
	 * 外注投入実績取消（1レシピ） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を取り消す。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param recipeId レシピＩＤ
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deResultInjection(final BigDecimal recipeId,
			final String orderNo, final String loginUser) {
		return deResultInjection(null, null, recipeId, orderNo, loginUser);
	}

	/**
	 * 外注投入実績取消（一投入） <BR>
	 * ＤＢの購買外注原材料投入実績を基に在庫更新（引当）を取り消す。<BR>
	 * 在庫数量INVENTORY_QTY+<BR>
	 * 処理コードPLngOpno=6 受払区分=製造払出2
	 * @param stepNo ステップ番号
	 * @param lineNo ライン番号
	 * @param recipeId レシピＩＤ
	 * @param orderNo 発注番号
	 * @param loginUser 更新者
	 * @return 正常:true エラー:false
	 */
	public boolean deResultInjection(final BigDecimal stepNo,
			final BigDecimal lineNo, final BigDecimal recipeId,
			final String orderNo, final String loginUser) {
		StrCodeAnd3NumberDto dto = new StrCodeAnd3NumberDto();
		dto.setLngFlg(StockinoutConstant.BD_CANCEL_NYURYOKU_FLG);
		dto.setLngRowNo(recipeId);
		dto.setStrCode(orderNo);
		dto.setLngStepNo(stepNo);
		dto.setLngLineNo(lineNo);
		dto.setStrLoginUser(loginUser);

		this.zaiCtrlDao.resultInjection(dto);
		if (dto.getOutPara() == null
				|| !dto.getOutPara().equals(StockinoutConstant.STR_COMPLETE)) {
			String errMess = dto.getOutPara();
			log.error(errMess);
			ZaikoCtrlExceptionEx e = new ZaikoCtrlExceptionEx(dto.getOutPara());
			e.setUser(loginUser);
			e.setInsideErrMsg(errMess);
			e.setModuleCd("deResultInjection()");
			throw e;
		}
		return true;
	}

}
