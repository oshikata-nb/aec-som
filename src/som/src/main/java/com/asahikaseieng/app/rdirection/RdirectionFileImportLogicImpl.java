/*
 * Created on 2022/08/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionFormulaTemp;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionFormulaTempDao;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionHeaderTemp;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionHeaderTempDao;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionProcedureTemp;
import com.asahikaseieng.dao.entity.directionfileimport.DirectionProcedureTempDao;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormula;
import com.asahikaseieng.dao.entity.directionformula.DirectionFormulaDao;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeaderDao;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedure;
import com.asahikaseieng.dao.entity.directionprocedure.DirectionProcedureDao;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.master.common.CommonDao;
import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.entity.master.item.ItemDao;
import com.asahikaseieng.dao.entity.master.location.Location;
import com.asahikaseieng.dao.entity.master.location.LocationDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionFormulaLotInventoryListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventoryList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventoryListDao;
import com.asahikaseieng.dao.nonentity.rdirection.fileimport.ProRDirectionFileImportDto;
import com.asahikaseieng.dao.nonentity.rdirection.fileimport.ProRdirectionFileImportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * 製造実績取込詳細ロジック 実装クラス.
 * @author 
 */
public class RdirectionFileImportLogicImpl implements RdirectionFileImportLogic {
	
	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** CommonDao */
	private CommonDao commonDao;
	/** LoginDetailDao */
	private LoginDetailDao loginDetailDao;
	/** ロケーションマスタ検索用Dao * */
	private LocationDao locationDao;

	/** ﾛｯﾄ番号を元にﾛｯﾄ在庫のﾛｹｰｼｮﾝを取得用Dao * */
	private RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao;

	/** ロット在庫用Dao */
	private RdirectionLotInventoryListDao rdirectionLotInventoryListDao;

	/** 品目マスタ検索Dao */
	private ItemDao itemDao;

	private GetNumberLogic getNumberLogic;
	
	private DirectionHeaderDao directionHeaderDao;
	private DirectionProcedureDao directionProcedureDao;
	private DirectionFormulaDao directionFormulaDao;

	private ProRdirectionFileImportDao proRdirectionFileImportDao;
	
	/** 製造指図ヘッダー一時取込Dao */
	private DirectionHeaderTempDao directionHeaderTempDao;
	/** 製造指図プロシージャ一時取込Dao */
	private DirectionProcedureTempDao directionProcedureTempDao;
	/** 製造指図フォーミュラ一時取込Dao */
	private DirectionFormulaTempDao directionFormulaTempDao;

	/** エラーログ出力用Dao */
	private ErrorLogDao errorLogDao;

	/** 製造ヘッダシート名 */
	private static final String RDIRECTION_HEADER_SHEET = "製造ヘッダ";
	/** 製造工程シート名 */
	private static final String RDIRECTION_PROCEDURE_SHEET = "製造工程";
	/** 製造配合・仕上げシート名 */
	private static final String RDIRECTION_FORMULA_SHEET = "製造配合・仕上";
	
	/** 製造ヘッダシート名 */
	private static final String RDIRECTION_HEADER_SHEET_EN = "direction_header";
	/** 製造工程シート名 */
	private static final String RDIRECTION_PROCEDURE_SHEET_EN = "direction_procedure";
	/** 製造配合・仕上げシート名 */
	private static final String RDIRECTION_FORMULA_SHEET_EN = "direction_formula";
	
	
	/** シート取込開始行番号 */
	private static final Integer RDIRECTION_DATA_S_ROW = 1;
	
	/** 指図区分 */
	private static final short HEADER_DIRE_DIV_COLUMN = 0;
	/** 指図番号 */
	private static final short HEADER_DIRE_NO_COLUMN = 1;
	/** ロット番号 */
	private static final short HEADER_LOTNO_COLUMN = 31;
	/** 開始実績日時 */
	private static final short HEADER_RESULT_SDATE_COLUMN = 34;
	/** 終了実績日時 */
	private static final short HEADER_RESULT_EDATE_COLUMN = 35;
	/** 滅菌作業開始日時 */
	private static final short HEADER_STERIT_SDATE_COLUMN = 36;
	/** 滅菌作業終了日時 */
	private static final short HEADER_STERIT_EDATE_COLUMN = 37;
	/** 備考 */
	private static final short HEADER_REMARK_COLUMN = 59;
	/** 注釈 */
	private static final short HEADER_NOTES_COLUMN = 60;
	/** 製造担当者 */
	private static final short HEADER_SEIZO_TANTO_COLUMN = 62;
	/** 調合タンク洗浄担当者 */
	private static final short HEADER_SENJO_TANTO_COLUMN = 64;
	/** 調合タンク滅菌作業担当者 */
	private static final short HEADER_MEKKIN_TANTO_COLUMN = 66;

	/** 指図区分　入力パターン */
	public static final String INPUT_DIRE_DIV_PATTERN = "[123]";
	
	/** 指図ステータス　パターン */
	public static final String DIRECTION_STATUS_PATTERN = "[67]";

	/** ロット番号最大文字数 */
	private static final Integer LOTNO_MAX_LENGTH = 20;
	/** 備考最大文字数 */
	private static final Integer REMARK_MAX_LENGTH = 256;
	/** 注釈最大文字数 */
	private static final Integer NOTES_MAX_LENGTH = 256;
	
	/** 製造担当者  */
	private static final Integer SEIZOTANTO_MAX_LENGTH = 3;
	/** 調合タンク洗浄担当者 */
	private static final Integer SENJOTANTO_MAX_LENGTH = 3;
	/** 調合タンク滅菌作業担当者 */
	private static final Integer MEKKINTANTO_MAX_LENGTH = 3;
	
	/** 指図区分 */
	private static final short PROCEDURE_DIRE_DIV_COLUMN = 0;
	/** 指図番号 */
	private static final short PROCEDURE_DIRE_NO_COLUMN = 1;
	/** ステップNO */
	private static final short PROCEDURE_STEP_NO_COLUMN = 2;
	/** 工程条件 */
	private static final short PROCEDURE_CONDITION_COLUMN = 6;
	/** 備考 */
	private static final short PROCEDURE_REMARK_COLUMN = 7;
	/** 注釈 */
	private static final short PROCEDURE_NOTES_COLUMN = 8;
	/** 開始実績日時 */
	private static final short PROCEDURE_RESULT_SDATE_COLUMN = 12;
	/** 終了実績日時 */
	private static final short PROCEDURE_RESULT_EDATE_COLUMN = 13;
	/** 温度 */
	private static final short PROCEDURE_CONDITION_TEMP_COLUMN = 14;
	/** 時間 */
	private static final short PROCEDURE_CONDITION_TIME_COLUMN = 15;
	/** 攪拌速度1 */
	private static final short PROCEDURE_STIR_SPEED1_COLUMN = 16;
	/** 攪拌速度2 */
	private static final short PROCEDURE_STIR_SPEED2_COLUMN = 17;
	/** 洗浄水絶対重量 */
	private static final short PROCEDURE_WATER_WEIGHT_COLUMN = 18;
	/** 本流/予備溶解 */
	private static final short PROCEDURE_MAIN_STREAM_COLUMN = 19;
	/** pH */
	private static final short PROCEDURE_PH_COLUMN = 20;
	/** 実績温度 */
	private static final short PROCEDURE_RESULT_TEMP_COLUMN = 21;
	/** 実績攪拌速度 */
	private static final short PROCEDURE_RESULT_RTIR_SPEED_COLUMN = 22;
	/** 実績pH */
	private static final short PROCEDURE_RESULT_PH_COLUMN = 23;

	/** 工程条件 */
	private static final Integer CONDITION_MAX_LENGTH = 100;
	/** 備考最大文字数 */
	private static final Integer PROCEDURE_REMARK_MAX_LENGTH = 256;
	/** 注釈最大文字数 */
	private static final Integer PROCEDURE_NOTES_MAX_LENGTH = 256;
	
	/** 区分 RECIPE1 */
	public static final String UNIT_DIV_RECIPE1 = "RECIPE1";

	/** 区分 RECIPE2 */
	public static final String UNIT_DIV_RECIPE2 = "RECIPE2";

	/** 区分 RECIPE5 */
	public static final String UNIT_DIV_RECIPE5 = "RECIPE5";

	/** 区分 その他 */
	public static final String UNIT_DIV_SONOTA = "SONOTA";

	/** 区分 配合 */
	public static final String UNIT_DIV_HAIGO = "HAIGO";

	/** 区分 配合ADJ */
	public static final String UNIT_DIV_HAIGO_ADJ = "HAIGO_ADJ";

	/** 区分 原価 */
	public static final String UNIT_DIV_GENKA = "GENKA";

	/** 区分  */
	public static final String UNIT_DIVISION_DIVISION = "unitDivision";

	/** 本流/予備溶解　入力パターン */
	public static final String INPUT_MAIN_STREAM_PATTERN = "[01234568]";
	

	/** 指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図 */ 
	private static final short FORMULA_DIRECTION_DIVISION_COLUMN = 0;
	/** 指図番号 */ 
	private static final short FORMULA_DIRECTION_NO_COLUMN = 1;
	/** ステップNO. */ 
	private static final short FORMULA_STEP_NO_COLUMN = 2;
	/** 行NO. */ 
	private static final short FORMULA_LINE_NO_COLUMN = 3;
	/** 投入順 */ 
	private static final short FORMULA_SEQ_COLUMN = 4;
	/** 品目タイプ|-1:原材料,1:製品,2:中間品,3:回収品,4:副生品,5:廃棄物 */ 
	private static final short FORMULA_LINE_TYPE_COLUMN = 5;
	/** 品目コード */ 
	private static final short FORMULA_ITEM_CD_COLUMN = 6;
	/** 投入方法|0:自動,1:手動 */ 
	private static final short FORMULA_TONYU_COLUMN = 9;
	/** データ読み取り|0:自動,1:手動 */ 
	private static final short FORMULA_DATAREAD_COLUMN = 10;
	/** 投入速度 */ 
	private static final short FORMULA_TONYUSOKUDO_COLUMN = 11;
	/** 実績数量 */ 
	private static final short FORMULA_RESULT_QTY_COLUMN = 14;
	/** ロス数量 */ 
	private static final short FORMULA_LOSS_QTY_COLUMN = 16;
	/** 調整数量 */ 
	private static final short FORMULA_ADJUST_QTY_COLUMN = 18;
	/** 条件 */ 
	private static final short FORMULA_STEP_CONDITION_COLUMN = 20;
	/** 注釈 */ 
	private static final short FORMULA_NOTES_COLUMN = 21;
	/** 入荷ロット番号 */ 
	private static final short FORMULA_LOT_NO_COLUMN = 28;
	/** 備考 */ 
	private static final short FORMULA_REMARK_COLUMN = 32;
	
	/** 条件 */
	private static final Integer STEP_CONDITION_MAX_LENGTH = 100;
	/** 備考最大文字数 */
	private static final Integer FORMULA_REMARK_MAX_LENGTH = 200;
	/** 注釈最大文字数 */
	private static final Integer FORMULA_NOTES_MAX_LENGTH = 256;
	
	/** 品目タイプ　入力パターン */
	public static final String INPUT_LINETYPE_PATTERN = "^-[1]|[12345]";
	/** 投入方法　入力パターン */
	public static final String INPUT_TONYU_PATTERN = "[01]";
	/** データ読み取り　入力パターン */
	public static final String INPUT_DATAREAD_PATTERN = "[01]";

	
	
	/**
	 * コンストラクタ.
	 */
	public RdirectionFileImportLogicImpl() {
	}
	
	/**
	 * TMPファイルパス取得.
	 */
	public String getTmpFilePath(){
		
		String filePath = commonDao.getEntity("RDIRECTION_IMPORT").getCommonValue();
		
		return filePath;
	}
	
	/**
	 * 担当者検索.
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	public LoginDetail getLoginEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * ロケーション検索.
	 * ##ここにメソッドの説明を書いてください##
	 * @param locationCd ロケーションコード
	 * @return Location
	 */
	public Location getLocationEntity(final String locationCd) {
		Location bean = locationDao.getEntity(locationCd);
		return bean;
	}
	
	/**
	 * EXCELからデータを読み込む.
	 * @param srhFilePath Excelファイルパス
	 * @param tantoCd 担当者ID
	 * @param request HttpServletRequest
	 * @return Actionメッセージ
	 */
	public ActionMessages getDataFromUploadFile(final String srhFilePath, final String tantoCd,final HttpServletRequest request) throws IOException,Exception{

		ActionMessages errMsg = new ActionMessages();
		
		try {
			POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(srhFilePath));
			HSSFWorkbook wb = new HSSFWorkbook(filein);
	
			// =================================================================================================================
			// シート取得
			// =================================================================================================================
			// 製造ヘッダシートを指定
			HSSFSheet headerSheet = wb.getSheet(RDIRECTION_HEADER_SHEET);
			
			if ( headerSheet == null ){
				headerSheet = wb.getSheet(RDIRECTION_HEADER_SHEET_EN);
			}
			// 製造ヘッダシートを指定
			HSSFSheet procedureSheet = wb.getSheet(RDIRECTION_PROCEDURE_SHEET);
			if ( procedureSheet == null ){
				procedureSheet = wb.getSheet(RDIRECTION_PROCEDURE_SHEET_EN);
			}

			// 製造ヘッダシートを指定
			HSSFSheet formulaSheet = wb.getSheet(RDIRECTION_FORMULA_SHEET);
			if ( formulaSheet == null ){
				formulaSheet = wb.getSheet(RDIRECTION_FORMULA_SHEET_EN);
			}

			
			// シートの存在チェック
			if (headerSheet == null || procedureSheet == null || formulaSheet == null) {
				// 製造ヘッダシート、又は製造工程シート、又は製造配合・仕上シートのいずれかが存在しません。取込ファイルの内容を確認してください。
				errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.sheets"));
				return errMsg;
			}
			
			List<DirectionHeaderTemp> headerTempList = new ArrayList<DirectionHeaderTemp>();
			List<DirectionProcedureTemp> procedureTempList = new ArrayList<DirectionProcedureTemp>();
			List<DirectionFormulaTemp> formulaTempList = new ArrayList<DirectionFormulaTemp>();
			
			// =================================================================================================================
			// ヘッダシート処理
			// =================================================================================================================
			//データ開始行に行番号をセット
			int rowNo = RDIRECTION_DATA_S_ROW;
			ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
			for(;;rowNo++){
				
				HSSFRow row = headerSheet.getRow(rowNo);
				if (row == null) {
					// 終了行
					break;
				}
				// 各セル情報を取得
				// 指図区分
				String directionDiv = getString(row.getCell(HEADER_DIRE_DIV_COLUMN));
				// 指図番号
				String directionNo = getString(row.getCell(HEADER_DIRE_NO_COLUMN));
				if ( directionNo == null || directionNo.trim().length() == 0 ){
					break;
				}
				// ロット番号
				String lotNo = getString(row.getCell(HEADER_LOTNO_COLUMN));
				// 開始実績日時
				Date resultSDate = getDate(row.getCell(HEADER_RESULT_SDATE_COLUMN), errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.result.sdate"));
				// 終了実績日時
				Date resultEDate = getDate(row.getCell(HEADER_RESULT_EDATE_COLUMN), errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.result.edate"));
				// 滅菌作業開始日時
				Date steritSDate = getDate(row.getCell(HEADER_STERIT_SDATE_COLUMN), errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.sterit.sdate"), false);
				// 滅菌作業終了日時
				Date steritEDate = getDate(row.getCell(HEADER_STERIT_EDATE_COLUMN), errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.sterit.edate"), false);
				// 備考
				String remark = getString(row.getCell(HEADER_REMARK_COLUMN));
				// 注釈
				String notes = getString(row.getCell(HEADER_NOTES_COLUMN));
				// 製造担当者
				String seizoTanto = getString(row.getCell(HEADER_SEIZO_TANTO_COLUMN));
				// 調合タンク洗浄担当者
				String senjoTanto = getString(row.getCell(HEADER_SENJO_TANTO_COLUMN));
				// 調合タンク滅菌作業担当者
				String mekkinTanto = getString(row.getCell(HEADER_MEKKIN_TANTO_COLUMN));
	
				// ----- チェック -----
				checkPattern(directionDiv, INPUT_DIRE_DIV_PATTERN, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.rdirection.division"));
				if(errMsg.isEmpty()) {
					// 存在チェック
					DirectionHeader header = directionHeaderDao.getEntity(new BigDecimal(directionDiv), directionNo);
					if (header == null) {
						// ヘッダ存在なし
						errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.header", RDIRECTION_HEADER_SHEET, rowNo + 1));
					} else {
						// ステータスチェック(6,7以外は異常)
						String status = RdirectionUtil.getBigDecimalString(header.getDirectionStatus());
						if(status == null || !status.matches(DIRECTION_STATUS_PATTERN)) {
							errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
								"errors.rdirection.fileimport.header.invalid", RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.direction.status")));
						}
					}
				}
				
				checkLength(lotNo, LOTNO_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.lot.no"));
				checkLength(remark, REMARK_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.header.remark"));
				checkLength(notes, NOTES_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.notes"));
				
				if (!StringUtils.isEmpty(seizoTanto)) {
					/* 製造担当者コードチェック */
					LoginDetail beanLogin = getLoginEntity(seizoTanto);
					if (beanLogin == null) {
						// {0}シート{1}行目 {2}は存在していません。
						errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.none", RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.seizo.tanto")));
					}
					checkLength(seizoTanto, SEIZOTANTO_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.seizo.tanto"));
				}
	
				if (!StringUtils.isEmpty(senjoTanto)) {
					/* 調合タンク洗浄担当者コードチェック */
					LoginDetail beanLogin = getLoginEntity(senjoTanto);
					if (beanLogin == null) {
						// {0}シート{1}行目 {2}は存在していません。
						errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.none", RDIRECTION_HEADER_SHEET, rowNo + 1,	rb.getString("item.rdirection.senjo.tanto")));
					}
					checkLength(senjoTanto, SENJOTANTO_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.senjo.tanto"));
				}
				
				if (!StringUtils.isEmpty(mekkinTanto)) {
					/* 調合タンク滅菌作業担当者コードチェック */
					LoginDetail beanLogin = getLoginEntity(mekkinTanto);
					if (beanLogin == null) {
						// {0}シート{1}行目 {2}は存在していません。
						errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.none", RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.mekkin.tanto")));
					}
					checkLength(mekkinTanto, MEKKINTANTO_MAX_LENGTH, errMsg, RDIRECTION_HEADER_SHEET, rowNo + 1, rb.getString("item.rdirection.mekkin.tanto"));
				}
				
				if (errMsg.isEmpty()) {
					DirectionHeaderTemp headTemp = new DirectionHeaderTemp();
					headTemp.setRowNumber(new BigDecimal(rowNo+1));
					headTemp.setDirectionDivision(new BigDecimal(directionDiv));
					headTemp.setDirectionNo(directionNo);
					headTemp.setLotNo(lotNo);
					headTemp.setResultSdate(new Timestamp(resultSDate.getTime()));
					headTemp.setResultEdate(new Timestamp(resultEDate.getTime()));
					if (steritSDate != null) {
						headTemp.setSteritSdate(new Timestamp(steritSDate.getTime()));
					}
					if (steritEDate != null) {
						headTemp.setSteritEdate(new Timestamp(steritEDate.getTime()));
					}
					headTemp.setRemark(remark);
					headTemp.setNotes(notes);
					headTemp.setSeizotantocode(seizoTanto);
					headTemp.setSenjotantocode(senjoTanto);
					headTemp.setMekkintantocode(mekkinTanto);
					headTemp.setUpdatorCd(tantoCd);
					
					headerTempList.add(headTemp);
				}
			}
			
			// =================================================================================================================
			// 製造工程シート処理
			// =================================================================================================================
			//データ開始行に行番号をセット
			rowNo = RDIRECTION_DATA_S_ROW;
			for(;;rowNo++){
				
				HSSFRow row = procedureSheet.getRow(rowNo);
				if (row == null) {
					// 終了行
					break;
				}
				// 各セル情報を取得
				// 指図区分
				String directionDiv = getString(row.getCell(PROCEDURE_DIRE_DIV_COLUMN));
				// 指図番号
				String directionNo = getString(row.getCell(PROCEDURE_DIRE_NO_COLUMN));
				if ( directionNo == null || directionNo.trim().length() == 0 ){
					break;
				}
				// ステップNO.
				String stepNo = getString(row.getCell(PROCEDURE_STEP_NO_COLUMN));
				// 工程条件:フリー入力
				String condition = getString(row.getCell(PROCEDURE_CONDITION_COLUMN));
				// 備考
				String remark = getString(row.getCell(PROCEDURE_REMARK_COLUMN));
				// 注釈
				String notes = getString(row.getCell(PROCEDURE_NOTES_COLUMN));
				// 開始実績日時
				Date resultSdate = getDate(row.getCell(PROCEDURE_RESULT_SDATE_COLUMN), errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.proc.result.sdate"));
				// 終了実績日時
				Date resultEdate = getDate(row.getCell(PROCEDURE_RESULT_EDATE_COLUMN), errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.proc.result.edate"));
				// 温度
				String conditionTemp = getNumericValue(row.getCell(PROCEDURE_CONDITION_TEMP_COLUMN));
				// 時間
				String conditionTime = getNumericValue(row.getCell(PROCEDURE_CONDITION_TIME_COLUMN));
				// 攪拌速度1
				String stirSpeed1 = getNumericValue(row.getCell(PROCEDURE_STIR_SPEED1_COLUMN));
				// 攪拌速度2
				String stirSpeed2 = getNumericValue(row.getCell(PROCEDURE_STIR_SPEED2_COLUMN));
				// 洗浄水絶対重量
				String waterWeght = getNumericValue(row.getCell(PROCEDURE_WATER_WEIGHT_COLUMN));
				// 本流/予備溶解
				String mainStream = getNumericValue(row.getCell(PROCEDURE_MAIN_STREAM_COLUMN));
				// ｐＨ
				String pH = getNumericValue(row.getCell(PROCEDURE_PH_COLUMN));
				// 実績温度
				String resultTemp = getNumericValue(row.getCell(PROCEDURE_RESULT_TEMP_COLUMN));
				// 実績攪拌速度
				String resultStirSpeed = getNumericValue(row.getCell(PROCEDURE_RESULT_RTIR_SPEED_COLUMN));
				// 実績ｐＨ
				String resultPh = getNumericValue(row.getCell(PROCEDURE_RESULT_PH_COLUMN));
	
				// ----- チェック -----
				checkPattern(directionDiv, INPUT_DIRE_DIV_PATTERN, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.rdirection.division"));
				checkDigitValue(stepNo, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.stepno"));
				if (errMsg.isEmpty()) {
					// 存在チェック
					DirectionProcedure procedure = directionProcedureDao.getEntity(new BigDecimal(directionDiv), directionNo, new BigDecimal(stepNo));
					if (procedure == null) {
						errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.procedure", RDIRECTION_PROCEDURE_SHEET, rowNo + 1));
					}
				}
	
				checkLength(condition, CONDITION_MAX_LENGTH, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.condition"));
				checkLength(remark, PROCEDURE_REMARK_MAX_LENGTH, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.remark"));
				checkLength(notes, PROCEDURE_NOTES_MAX_LENGTH, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.notes"));
	
				//　温度チェック
				checkDigit(request, UNIT_DIV_RECIPE2, conditionTemp, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.condition.temp"));
				//　時間チェック
				checkDigit(request, UNIT_DIV_RECIPE1, conditionTime, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.condition.time"));
				//　攪拌速度1チェック
				checkDigit(request, UNIT_DIV_RECIPE1, stirSpeed1, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.stir.speed1"));
				//　攪拌速度2チェック
				checkDigit(request, UNIT_DIV_RECIPE1, stirSpeed2, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.stir.speed2"));
				//　洗浄水絶対重量チェック
				checkDigit(request, UNIT_DIV_SONOTA, waterWeght, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.water.weight"));
				//　ｐＨチェック
				checkDigit(request, UNIT_DIV_RECIPE2, pH, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.str.ph"));
				//　実績温度チェック
				checkDigit(request, UNIT_DIV_RECIPE2, resultTemp, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.result.condition.temp"));
				//　実績攪拌速度チェック
				checkDigit(request, UNIT_DIV_RECIPE1, resultStirSpeed, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.result.stir.speed"));
				//　実績pHチェック
				checkDigit(request, UNIT_DIV_RECIPE2, resultPh, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.result.ph"));
	
				// 本流/予備溶解
				checkPattern(mainStream, INPUT_MAIN_STREAM_PATTERN, errMsg, RDIRECTION_PROCEDURE_SHEET, rowNo + 1, rb.getString("item.rdirection.main.stream"));
	
				if(errMsg.isEmpty()){
					DirectionProcedureTemp proTemp = new DirectionProcedureTemp();
					proTemp.setRowNumber(new BigDecimal(rowNo+1));
					proTemp.setDirectionDivision(AecNumberUtils.convertBigDecimal(directionDiv));
					proTemp.setDirectionNo(directionNo);
					proTemp.setStepNo(AecNumberUtils.convertBigDecimal(stepNo));
					proTemp.setCondition(condition);
					proTemp.setRemark(remark);
					proTemp.setNotes(notes);
					proTemp.setResultSdate(new Timestamp(resultSdate.getTime()));
					proTemp.setResultEdate(new Timestamp(resultEdate.getTime()));
					proTemp.setConditionTemp(AecNumberUtils.convertBigDecimal(conditionTemp));
					proTemp.setConditionTime(AecNumberUtils.convertBigDecimal(conditionTime));
					proTemp.setStirSpeed1(AecNumberUtils.convertBigDecimal(stirSpeed1));
					proTemp.setStirSpeed2(AecNumberUtils.convertBigDecimal(stirSpeed2));
					proTemp.setWaterWeight(AecNumberUtils.convertBigDecimal(waterWeght));
					proTemp.setMainStream(AecNumberUtils.convertBigDecimal(mainStream));
					proTemp.setPh(AecNumberUtils.convertBigDecimal(pH));
					proTemp.setResultConditionTemp(AecNumberUtils.convertBigDecimal(resultTemp));
					proTemp.setResultStirSpeed(AecNumberUtils.convertBigDecimal(resultStirSpeed));
					proTemp.setResultPh(AecNumberUtils.convertBigDecimal(resultPh));
					proTemp.setUpdatorCd(tantoCd);
					
					procedureTempList.add(proTemp);
				}
			}
			
			// =================================================================================================================
			// 製造配合・シート処理
			// =================================================================================================================
			rowNo = RDIRECTION_DATA_S_ROW;
			for(;;rowNo++){
				
				HSSFRow row = formulaSheet.getRow(rowNo);
				if (row == null) {
					// 終了行
					break;
				}
				// 各セル情報を取得
				// 指図区分
				String directionDiv = getString(row.getCell(FORMULA_DIRECTION_DIVISION_COLUMN));
				// 指図番号
				String directionNo = getString(row.getCell(FORMULA_DIRECTION_NO_COLUMN));
				if ( directionNo == null || directionNo.trim().length() == 0 ){
					break;
				}
				// ステップNO.
				String stepNo = getString(row.getCell(FORMULA_STEP_NO_COLUMN));
				// 行NO.
				String lineNo = getString(row.getCell(FORMULA_LINE_NO_COLUMN));
				if ( lineNo.trim().length() == 0 ){
					lineNo = "-9999";
				}
				// 投入順
				String seq = getString(row.getCell(FORMULA_SEQ_COLUMN));
				// 品目タイプ
				String lineType = getString(row.getCell(FORMULA_LINE_TYPE_COLUMN));
				// 品目コード
				String itemCd = getString(row.getCell(FORMULA_ITEM_CD_COLUMN));
				// 投入方法
				String tonyu = getString(row.getCell(FORMULA_TONYU_COLUMN));
				// データ読み取り
				String dataRead = getString(row.getCell(FORMULA_DATAREAD_COLUMN));
				// 投入速度
				String tonyuSokudo = getNumericValue(row.getCell(FORMULA_TONYUSOKUDO_COLUMN));
				// 実績数量
				String resultQty = getNumericValue(row.getCell(FORMULA_RESULT_QTY_COLUMN));
				// ロス数量
				String lossQty = getNumericValue(row.getCell(FORMULA_LOSS_QTY_COLUMN));
				// 調整数量
				String adjustQty = getNumericValue(row.getCell(FORMULA_ADJUST_QTY_COLUMN));
				// 条件
				String stepCondition = getString(row.getCell(FORMULA_STEP_CONDITION_COLUMN));
				// 注釈
				String notes = getString(row.getCell(FORMULA_NOTES_COLUMN));
				// 入荷ロット番号
				String lotNo = getString(row.getCell(FORMULA_LOT_NO_COLUMN));
				// 備考
				String remark = getString(row.getCell(FORMULA_REMARK_COLUMN));
	
			
				// チェック
				checkPattern(directionDiv, INPUT_DIRE_DIV_PATTERN, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.rdirection.division"));
				checkDigitValue(stepNo, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.stepno"));
				checkDigitValue(lineNo, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.lineno"));
				
				// 品目コード
				if (StringUtils.isEmpty(itemCd)) {
					errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.required", RDIRECTION_FORMULA_SHEET, rowNo+1, rb.getString("item.rdirection.item.cd")));
				}
				// 投入速度
				checkDigit(request, UNIT_DIV_HAIGO, tonyuSokudo, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.tonyu.sokudo"));
				// 実績数量
				if (StringUtils.isEmpty(resultQty)) {
					errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.required", RDIRECTION_FORMULA_SHEET, rowNo+1, rb.getString("item.rdirection.formula.result.qty")));
				} else {
					checkDigit(request, UNIT_DIV_HAIGO, resultQty, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.formula.result.qty"));
				}
				// ロス数量
				checkDigit(request, UNIT_DIV_HAIGO, lossQty, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.formula.loss.qty"));
				// 仕上品の場合、ロス数量入力はエラー
				if (RdirectionConst.LINE_NO_FINISH_START_NO.compareTo(AecNumberUtils.convertBigDecimal(lineNo)) <= 0 && !StringUtils.isEmpty(lossQty) ) {
					errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.finish.qty", RDIRECTION_FORMULA_SHEET, rowNo+1, rb.getString("item.rdirection.formula.loss.qty")));
				}
				// 調整数量
				checkDigit(request, UNIT_DIV_HAIGO_ADJ, adjustQty, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.formula.adjust.qty"));
				// 仕上品の場合、調整数量入力はエラー
				if (RdirectionConst.LINE_NO_FINISH_START_NO.compareTo(AecNumberUtils.convertBigDecimal(lineNo)) <= 0 && !StringUtils.isEmpty(adjustQty) ) {
					errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.finish.qty", RDIRECTION_FORMULA_SHEET, rowNo+1, rb.getString("item.rdirection.formula.adjust.qty")));
				}
	
				// 条件
				checkLength(stepCondition, STEP_CONDITION_MAX_LENGTH, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.step.condition"));
				// 注釈
				checkLength(notes, FORMULA_NOTES_MAX_LENGTH, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.notes"));
				// 備考
				checkLength(remark, FORMULA_REMARK_MAX_LENGTH, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.remark"));
				
				// 投入方法
				checkPattern(tonyu, INPUT_TONYU_PATTERN, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.tonyu"));
				// データ読取
				checkPattern(dataRead, INPUT_DATAREAD_PATTERN, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.data.read"));
				
				// グレー項目のチェック
				checkDigitValue(seq, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.seq"));
				checkPattern(lineType, INPUT_LINETYPE_PATTERN, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1, rb.getString("item.rdirection.linetype"));
	
				// DBの値を取得
				if (errMsg.isEmpty()) {
					DirectionFormula beforeBean = directionFormulaDao.getEntity(AecNumberUtils.convertBigDecimal(directionDiv), directionNo, AecNumberUtils.convertBigDecimal(lineNo), AecNumberUtils.convertBigDecimal(stepNo));
					if (beforeBean != null) {
						// 画面の変更前の実績値と異なる場合ロケチェックを行う
						if (beforeBean.getResultQty() != null
								&& !beforeBean.getResultQty().equals(
									AecNumberUtils.convertBigDecimal(resultQty))) {
							
							// 製造配合とロット在庫のロケが不一致の場合エラーを画面に表示
							if (!checkForFormulaResultDate(directionNo, AecNumberUtils.convertBigDecimal(stepNo),AecNumberUtils.convertBigDecimal(lineNo))) {
								errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.location.disagreement", RDIRECTION_FORMULA_SHEET, rowNo + 1));
								continue;
							}
						}
					} else {
						// 製造指図フォーミュラが存在しない場合、該当するステップNoの製造工程が存在しない場合はエラーとする
						// 存在チェック
						DirectionProcedure procedure = directionProcedureDao.getEntity(new BigDecimal(directionDiv), directionNo, new BigDecimal(stepNo));
						if (procedure == null) {
							errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.formula.none", RDIRECTION_FORMULA_SHEET, rowNo + 1));
						}
					}
					
				}
				
				// 設定
				if (errMsg.isEmpty()) {
					DirectionFormulaTemp formula = new DirectionFormulaTemp();
					formula.setRowNumber(new BigDecimal(rowNo+1));
					formula.setDirectionDivision(AecNumberUtils.convertBigDecimal(directionDiv));
					formula.setDirectionNo(directionNo);
					formula.setStepNo(AecNumberUtils.convertBigDecimal(stepNo));
					formula.setLineNo(AecNumberUtils.convertBigDecimal(lineNo));
					// 現在値がある場合は、設定
					DirectionFormula initFormula = directionFormulaDao.getEntity(formula.getDirectionDivision(), formula.getDirectionNo(), formula.getLineNo(), formula.getStepNo());
					copyFormula(formula, initFormula);
					
					formula.setSeq(AecNumberUtils.convertBigDecimal(seq));
					formula.setLineType(AecNumberUtils.convertBigDecimal(lineType));
					formula.setItemCd(itemCd);
					formula.setTonyu(AecNumberUtils.convertBigDecimal(tonyu));
					formula.setDataread(AecNumberUtils.convertBigDecimal(dataRead));
					formula.setTonyusokudo(AecNumberUtils.convertBigDecimal(tonyuSokudo));
					formula.setResultQty(AecNumberUtils.convertBigDecimal(resultQty));
					formula.setLossQty(AecNumberUtils.convertBigDecimal(lossQty));
					formula.setAdjustQty(AecNumberUtils.convertBigDecimal(adjustQty));
					formula.setStockpdQty(getStockpdQty(formula));
					formula.setStepCondition(stepCondition);
					formula.setNotes(notes);
					formula.setLotNo(lotNo);
					formula.setRemark(remark);
					formula.setUpdatorCd(tantoCd);
	
					// 品目マスタ
					checkForUpdate(initFormula,formula, errMsg, RDIRECTION_FORMULA_SHEET, rowNo + 1);
	
					if (errMsg.isEmpty()){
						formulaTempList.add(formula);
					}
				}
			}
			
			
			// Excel読み込みにおいて、異常がなかった場合
			if (errMsg.isEmpty()) {
				// 一時取込番号の取得
				String tempNo = "";
				try{
					//一時取込番号と取込番号をシーケンスから取得
					tempNo = getNumberLogic.getTempNo();
				} catch(Exception e){
					//エラーメッセージを追加
					errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nodata.sequence"));
					return errMsg;
				}
				
				try{
					// 製造指図一時取込テーブルの作成
					for (DirectionHeaderTemp directionHeaderTemp : headerTempList) {
						directionHeaderTemp.setTempNo(tempNo);
						directionHeaderTempDao.insert(directionHeaderTemp);
					}
					for (DirectionProcedureTemp directionProcedureTemp : procedureTempList) {
						directionProcedureTemp.setTempNo(tempNo);
						directionProcedureTempDao.insert(directionProcedureTemp);
					}
					for (DirectionFormulaTemp directionFormulaTemp : formulaTempList) {
						directionFormulaTemp.setTempNo(tempNo);
						directionFormulaTempDao.insert(directionFormulaTemp);
					}
				}catch(Exception e) {
					RdirectionFileImportLogicException ex = new RdirectionFileImportLogicException("errors.rdirection.fileimport.insert", "");
					String insideMsg = getCauseMsg(e);
					ex.setInsideErrMsg(insideMsg);
					throw ex;
				}
				
				// プロシージャの実行
				rdirectionFileImport(tempNo, tantoCd, errMsg);
				
			}
		}catch(RdirectionFileImportLogicException re) {
			throw re;
		}catch(Exception e) {
			RdirectionFileImportLogicException ex = new RdirectionFileImportLogicException("errors.rdirection.fileimport.insert", "");
			String insideMsg = getCauseMsg(e);
			ex.setInsideErrMsg(insideMsg);
			throw ex;
		}
		return errMsg;
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
	 * HSSFRichTextStringの返却(NULL/空文字判定)
	 * @return
	 */
	private String getString(HSSFCell hssfCell) {
		String ret = "";
		try {
			if(null != hssfCell && null != hssfCell.getRichStringCellValue() && hssfCell.getRichStringCellValue().getString().compareTo("")!=0){
				ret = hssfCell.getRichStringCellValue().getString();
			}
		} catch(Exception e) {
			try {
				if(null != hssfCell){
					ret = String.valueOf((long)hssfCell.getNumericCellValue());
				}
			} catch(Exception ex) {
				return ret;
			}
		}
		return ret;
	}
	
	/**
	 * HSSFNumericCellValueの返却.
	 * @param hssfCell HSSFCell
	 * @return 値
	 */
	private String getNumericValue(HSSFCell hssfCell) {
		String ret = "";
		try {
			ret = String.valueOf(hssfCell.getNumericCellValue());
			//不要な0削除
			Double d = new Double(ret);
			if (d == d.longValue()) {
				return String.format("%d", d.longValue());
			} else {
				return BigDecimal.valueOf(d).toPlainString();
			}
		}catch(Exception ex){
			ret = getString(hssfCell);
		}
		return ret;
	}
	
	/**
	 * DateCellValueの返却(NULL/空文字判定).
	 * @param hssfCell cell
	 * @param actionMsg メッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名称
	 * @return 日時
	 */
	private Date getDate(HSSFCell hssfCell, ActionMessages actionMsg, String sheetNm, int row, String itemNm) {
		Date ret = null;
		try {
			if(null == hssfCell || 0 == hssfCell.getNumericCellValue()){
				// 必須エラー {0}シート{1}行目 {2}を入力して下さい。
				actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.required", sheetNm, row, itemNm));
			} else if (null != hssfCell && null != hssfCell.getDateCellValue()) {
				ret = hssfCell.getDateCellValue();
			}
		} catch(Exception e) {
			//　{0}シート{1}行目 {2}には正しい日時を入力して下さい。
			actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"errors.rdirection.fileimport.datetime", sheetNm, row, itemNm));
		}
		return ret;
	}
	
	/**
	 * DateCellValueの返却.
	 * @param hssfCell cell
	 * @param actionMsg メッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名称
	 * @param isRequired 必須チェック
	 * @return 日時
	 */
	private Date getDate(HSSFCell hssfCell, ActionMessages actionMsg, String sheetNm, int row, String itemNm, boolean isRequired) {
		Date ret = null;
		if (isRequired) {
			return getDate(hssfCell,actionMsg,sheetNm,row,itemNm);
		} else {
			if(null == hssfCell || 0 == hssfCell.getNumericCellValue()){
				//　入力無し
				return ret;
			}			
		}
		return getDate(hssfCell,actionMsg,sheetNm,row,itemNm);
	}
	
	/**
	 * 文字数チェック処理.
	 * @param value 値
	 * @param maxlen 最大長
	 * @param actionMsg Actionメッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名
	 */
	private void checkLength(String value, int maxlen, ActionMessages actionMsg, String sheetNm, int row, String itemNm) {
		if (!StringUtils.isEmpty(value)) {
			if (value.length() > maxlen) {
				// {0}シート{1}行目 {2}は{3}桁以下で入力して下さい。
				actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.length", sheetNm, row, itemNm, maxlen));
			}
		}
	}

	/**
	 * 
	 * 数値チェック処理.
	 * @param request HttpServletRequest
	 * @param unitDiv 区分
	 * @param value 値
	 * @param actionMsg Actionメッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名
	 */
	private void checkDigit(HttpServletRequest request, String unitDiv, String value, ActionMessages actionMsg, String sheetNm, int row, String itemNm) {
		
		//数値桁数チェック部品呼び出し
		CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
		//チェック
		CheckDigitResult result = check.checkDigit(unitDiv, null, null, value);
		NumberChkDisitDetail detail = result.getDetail();
		if (result.getCode() != CheckDigitUtilsLogic.SUCCESS) {
			//入力エラー時
			int errorCode = result.getCode();
			String keta = null;
			switch (errorCode) {
				case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
					// 数値文字列エラー
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.digit.number", sheetNm, row, itemNm));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
					// 最大文字列長エラー
					keta = detail.getMaxLength().toString();
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.digit.maxlength", sheetNm, row, itemNm, keta));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
					// 整数部桁数エラー
					keta = detail.getIntegerLength().toString();
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.digit.integer", sheetNm, row, itemNm, keta));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
					// 小数部桁数エラー
					keta = detail.getSmallnumLength().toString();
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.digit.decimal", sheetNm, row, itemNm, keta));
					break;
				case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
					// 範囲エラー
					String lower = detail.getLowerLimit().toString();
					String upper = detail.getUpperLimit().toString();
					//　４つまでしか可変引数を渡せないため、ここで行数と項目名を結合
					String rowItem = row + "行目" + itemNm;
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.rdirection.fileimport.digit.rang", sheetNm, rowItem, lower, upper));
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 
	 * 数字チェック.
	 * @param value 値
	 * @param actionMsg Actionメッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名
	 */
	private void checkDigitValue(String value, ActionMessages actionMsg, String sheetNm, int row, String itemNm) {
		try {
			new BigDecimal(value);
		}catch(NumberFormatException e) {
			actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"errors.rdirection.fileimport.number", sheetNm, row, itemNm));
		}
	}
	
	/**
	 * 
	 * 入力パターンチェック処理.
	 * @param value 値
	 * @param pattern 入力パターン
	 * @param actionMsg Actionメッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 * @param itemNm 項目名
	 */
	private void checkPattern(String value, String pattern, ActionMessages actionMsg, String sheetNm, int row, String itemNm) {
		
		if(StringUtils.isEmpty(value)){
			return;
		}
		//　入力パターンと一致しているか確認
		if (!value.matches(pattern)) {
			// エラー
			actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"errors.rdirection.fileimport.invalid", sheetNm, row, itemNm));
		}
	}
	
	/**
	 * 在庫引落量を算出.
	 * @param formula DirectionFormulaTemp
	 * @return 在庫引落量
	 */
	private BigDecimal getStockpdQty(DirectionFormulaTemp formula) {
		// 在庫引落量を設定
		BigDecimal stockpdQty = new BigDecimal(formula.getResultQty().toPlainString());
		if (formula.getLossQty() != null) {
			stockpdQty = stockpdQty.add(formula.getLossQty());
		}
		if (formula.getAdjustQty() != null) {
			stockpdQty = stockpdQty.add(formula.getAdjustQty());
		}
		return stockpdQty;
	}
	
	/**
	 * 実績を変更時、製造配合とロット在庫のロケーションが異なる場合エラーを表示
	 * @param directionNo 指図番号
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return ActionMessages エラー内容
	 */
	public boolean checkForFormulaResultDate(final String directionNo,
			final BigDecimal stepNo, final BigDecimal lineNo) {
		boolean ret = true;

		// ライン番号とステップ番号が無い場合はチェックを行わない
		if (lineNo == null || stepNo == null) {
			return ret;
		}
		// 仕上品の場合(行Noが1001以上)は、ロットチェックを実施しない
		if (RdirectionConst.LINE_NO_FINISH_START_NO.compareTo(lineNo) <= 0) {
			return ret;
		}
		
		DirectionFormula opeBean = directionFormulaDao.getEntity(
			RdirectionConst.DIRECTION_DIVISION, directionNo, lineNo, stepNo);

		// ﾛｯﾄとﾛｹがある場合戻しあり
		if (opeBean.getLotNo() != null && opeBean.getLocationCd() != null) {

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索
			List<RdirectionFormulaLotInventoryList> locationBean = rdirectionFormulaLotInventoryListDao
					.getLotList(opeBean.getLotNo());

			// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果がある場合
			if (!locationBean.isEmpty()) {

				// ﾛｯﾄ番号でﾛｯﾄ在庫を検索し検索結果が１件の場合
				if (locationBean.size() == 1) {

					// 現在の配合のﾛｹとﾛｯﾄ在庫のﾛｹが異なる場合エラー
					if (!opeBean.getLocationCd().equals(
						locationBean.get(0).getLocationCd())) {

						ret = false;

					}
				}
			}
		}
		return ret;
	}

	/**
	 * 製造配合コピー処理.
	 * @param temp Excel読み込みフォーミュラ
	 * @param initFormula 製造指図フォーミュラDB値
	 */
	private void copyFormula(DirectionFormulaTemp temp, DirectionFormula initFormula) {
		if (initFormula != null) {
			temp.setLocationCd(initFormula.getLocationCd());
			temp.setNextLocationCd(initFormula.getNextLocationCd());
			temp.setNextAfterLocationCd(initFormula.getNextAfterLocationCd());
			temp.setManufacturerLotNo(initFormula.getManufacturerLotNo());
		}
	}
	
	/**
	 * 更新時のマスタチェックを行う.
	 * @param initFormula 製造指図フォーミュラDB値
	 * @param bean 更新Bean
	 * @param actionMsg Actionメッセージ
	 * @param sheetNm シート名
	 * @param row 行数
	 */
	private void checkForUpdate(DirectionFormula initFormula, DirectionFormulaTemp bean, ActionMessages actionMsg, String sheetNm, int row) {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		boolean flgLotDivision = true;

		// 品目マスタを検索
		Item opeBean = itemDao.getEntity(bean.getItemCd());
		if (opeBean == null) {
			// データが存在しない場合
			actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.item", sheetNm, row));
			return;
		}

		// 仕上品の場合(行Noが1001以上)は、ロットチェックを実施しない
		if (RdirectionConst.LINE_NO_FINISH_START_NO.compareTo(bean.getLineNo()) <= 0) {
			// ロット番号も無視（現在値）
			bean.setLotNo(initFormula.getLotNo());
			return ;
		}
		
		// 在庫更新対象の場合
		if (!RdirectionConst.STOCK_DIVISION_NOT_UPDATE_INVENTORY.equals(opeBean.getStockDivision().toString())) {
			// ロット管理する場合
			if (RdirectionConst.LOT_DIVISION_ON.equals(opeBean.getLotDivision().toString())) {
				if (StringUtils.isEmpty(bean.getLotNo())) {
					actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.required", sheetNm, row, rb.getString("item.rdirection.formula.lot.no")));
				} else {
					// 在庫引当を一度もしていない場合、または、ロット番号を変更した場合にロット在庫が存在するか確認する
					if (initFormula == null || initFormula.getStockpdQty() == null
							|| !bean.getLotNo().equals(initFormula.getLotNo())) {
						List<RdirectionLotInventoryList> list = null;
						list = rdirectionLotInventoryListDao.getLocationList(bean.getItemCd(), bean.getLotNo());
						if (list == null || list.isEmpty()) {
							actionMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.none", sheetNm, row, rb.getString("item.rdirection.formula.lot.no")));
						} else {
							bean.setLocationCd(list.get(0).getLocationCd());
							bean.setManufacturerLotNo(list.get(0).getAliasLotNo());
						}
					}
				}
			} else {
				// ロット管理なし
				flgLotDivision = false;
			}
		} else {
			// ロット管理なし
			flgLotDivision = false;
		}
		if(!flgLotDivision) {
			// ロット管理なし
			if (initFormula != null) {
				// ロット管理しなときは、ロット番号を変更しないので現在値を保持
				bean.setLotNo(initFormula.getLotNo());
			}
		}
	}

	/**
	 * 製造実績ファイル取込プロシージャ実行.
	 * @param tempNo 一時取込番号
	 * @param tantoCd 担当者ID
	 * @param errMsg ActionMessages
	 * @throws RdirectionFileImportLogicException 
	 */
	private void rdirectionFileImport(String tempNo, String tantoCd, ActionMessages errMsg) throws RdirectionFileImportLogicException {
		
		// プロシージャを呼び出すためのデータをDtoにセット
		ProRDirectionFileImportDto dto = new ProRDirectionFileImportDto();
		dto.setpTempNo(tempNo);
		dto.setpTantoCd(tantoCd);
		
		//　製造実績ファイル取込機能プロシージャ呼び出し
		try{
			
			proRdirectionFileImportDao.rdirectionFileImport(dto);
			
		} catch(Exception e){
			e.printStackTrace();
			RdirectionFileImportLogicException ex = new RdirectionFileImportLogicException("errors.rdirection.fileimport.error", "");
			ex.setInsideErrMsg(e.getMessage());
			throw ex;
		}
		if (dto.getPNumRet().compareTo(BigDecimal.ZERO) != 0) {
			// 異常
			String code = StringUtils.defaultString(dto.getPStrErrorCd());
			String msg = StringUtils.defaultString(dto.getPStrErrorMsg());
			errMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.rdirection.fileimport.error.proc", code, msg));
		}
	}

	/**
	 * 原因例外取得.
	 * @param th 例外
	 * @return 原因メッセージ
	 */
	private String getCauseMsg(Throwable th) {
		Throwable cause = th;
		while(true) {
			Throwable throwable = cause.getCause();
			if (throwable == null) {
				break;
			} else {
				cause = throwable;
			}
		}
		String msg = th.getMessage() + ":"+ cause.getMessage();
		return msg;
	}
	
	/* -------------------- setter -------------------- */

	/**
	 * commonDaoを設定します。
	 * @param commonDao commonDao
	 */
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}

	/**
	 * ロケーション取得用Daoを設定します。
	 * @param locationDao ロケーション取得用Dao
	 */
	public void setLocationDao(final LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	/**
	 * rdirectionFormulaLotInventoryListDaoを設定します。
	 * @param rdirectionFormulaLotInventoryListDao
	 *            rdirectionFormulaLotInventoryListDao
	 */
	public void setRdirectionFormulaLotInventoryListDao(
			final RdirectionFormulaLotInventoryListDao rdirectionFormulaLotInventoryListDao) {
		this.rdirectionFormulaLotInventoryListDao = rdirectionFormulaLotInventoryListDao;
	}

	/**
	 * ロット在庫用Daoを設定します。
	 * @param rdirectionLotInventoryListDao ロット在庫用Dao
	 */
	public void setRdirectionLotInventoryListDao(
			final RdirectionLotInventoryListDao rdirectionLotInventoryListDao) {
		this.rdirectionLotInventoryListDao = rdirectionLotInventoryListDao;
	}

	/**
	 * 品目マスタDaoを設定します。
	 * @param itemDao 品目マスタDao
	 */
	public void setItemDao(final ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	/**
	 * getNumberLogicを設定します。
	 * @param getNumberLogic getNumberLogic
	 */
	public void setGetNumberLogic(GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 製造ヘッダ用Daoを設定します。
	 * @param directionHeaderDao 製造ヘッダ用Dao
	 */
	public void setDirectionHeaderDao(
			final DirectionHeaderDao directionHeaderDao) {
		this.directionHeaderDao = directionHeaderDao;
	}

	/**
	 * 製造プロシージャ用Daoを設定します。
	 * @param directionProcedureDao 製造プロシージャ用Dao
	 */
	public void setDirectionProcedureDao(
			final DirectionProcedureDao directionProcedureDao) {
		this.directionProcedureDao = directionProcedureDao;
	}

	/**
	 * 製造配合用Daoを設定します。
	 * @param directionFormulaDao 製造配合用Dao
	 */
	public void setDirectionFormulaDao(
			final DirectionFormulaDao directionFormulaDao) {
		this.directionFormulaDao = directionFormulaDao;
	}

	/**
	 * 製造指図ヘッダ一時取込Daoを設定します。
	 * @param directionHeaderDao 製造指図ヘッダ一時取込Dao
	 */
	public void setDirectionHeaderTempDao(
			final DirectionHeaderTempDao directionHeaderTempDao) {
		this.directionHeaderTempDao = directionHeaderTempDao;
	}

	/**
	 * 製造指図プロシージャ一時取込Daoを設定します。
	 * @param directionProcedureDao 製造指図プロシージャ一時取込Dao
	 */
	public void setDirectionProcedureTempDao(
			final DirectionProcedureTempDao directionProcedureTempDao) {
		this.directionProcedureTempDao = directionProcedureTempDao;
	}

	/**
	 * 製造指図フォーミュラ一時取込Daoを設定します。
	 * @param directionFormulaDao 製造指図フォーミュラ一時取込Dao
	 */
	public void setDirectionFormulaTempDao(
			final DirectionFormulaTempDao directionFormulaTempDao) {
		this.directionFormulaTempDao = directionFormulaTempDao;
	}

	/**
	 * 製造実績ファイル取込Procedure実行DAOを設定します。
	 * @param proRdirectionFileImportDao 製造実績ファイル取込Procedure実行DAO
	 */
	public void setProRdirectionFileImportDao(
			final ProRdirectionFileImportDao proRdirectionFileImportDao) {
		this.proRdirectionFileImportDao = proRdirectionFileImportDao;
	}

	/**
	 * エラーログ出力用daoを設定します。
	 * @param errorLogDao エラーログ出力用dao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}
}
