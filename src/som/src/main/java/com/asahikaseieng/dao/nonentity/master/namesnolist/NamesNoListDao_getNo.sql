/*
 * Created on 2009/01/28
 *
 * $copyright$
 *
*/

/**
 * 各発番番号取得用SQL
 *
 * @author tosco
 *
 * entityName=NamesNoList
 * packageName=namesnolist
 * methodName=getNo
 *
 */

SELECT	
/*IF (( nameCd != null ) && ( nameCd != "" ))*/
	/*IF ( nameCd == "HT" )*/
		NO_KEY_ORDER_NO.nextval					-- 発注番号
	/*END*/
	/*IF ( nameCd == "SI" )*/
		NO_KEY_SI_SLIP_NO.nextval				-- 仕入番号
	/*END*/
	/*IF ( nameCd == "JT" )*/
		NO_KEY_SUPPLIER_ORDER_NO.nextval		-- 受注番号
	/*END*/
	/*IF ( nameCd == "SK" )*/
		NO_KEY_SHIPPING_ORDER_SLIP_NO.nextval	-- 出荷番号
	/*END*/
	/*IF ( nameCd == "UR" )*/
		NO_KEY_UR_SLIP_NO.nextval				-- 売上番号
	/*END*/
	/*IF ( nameCd == "MI" )*/
		NO_KEY_MI_ESTIMATE_NO.nextval			-- 見積番号
	/*END*/
	/*IF ( nameCd == "SLSK" )*/
		NO_KEY_SLIPSHIPPING_NO.nextval			-- 出荷伝票番号
	/*END*/
	/*IF ( nameCd == "SLUR" )*/
		NO_KEY_SLIPSALES_NO.nextval				-- 売上伝票番号
	/*END*/
	/*IF ( nameCd == "FO" )*/
		SEQ_FRST_ORDER_NO.nextval					-- 取込番号
	/*END*/
	/*IF ( nameCd == "CD" )*/
		NO_KEY_ORDER_IMPORT_CHECKED.nextval		-- 受注取込一覧画面でファイル送信対象保存時に連番を発番
	/*END*/
	/*IF ( nameCd == "OI" )*/
		NO_KEY_TEMP_NO.nextval					-- 一時取込番号
	/*END*/
	/*IF ( nameCd == "LOG" )*/
		SEQ_PROC_LOG_NO.nextval					-- ログ番号
	/*END*/
	/*IF ( nameCd == "CF" )*/
		SEQ_CARRY_FARE.nextval					-- 運賃計算用シーケンス番号
	/*END*/
	/*IF ( nameCd == "SAES" )*/
		NO_KEY_SALESTERMS_AND_ESTIMATE.nextval				-- 販売条件・見積単価コピー・削除
	/*END*/
		
/*END*/
/*IF (( nameCd == null ) || ( nameCd == "" ))*/
	NO_KEY_ORDER_SHEET_NO.nextval				-- 発注書NO
/*END*/
FROM	DUAL
