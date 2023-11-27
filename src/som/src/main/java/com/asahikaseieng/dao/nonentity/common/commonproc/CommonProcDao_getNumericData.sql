/*
 * 数値項目取得用(ここは引数の区分毎に取得する値を変えています。新規に追加する場合は区分を重複せずまた区分を変えないようにお願いします）
 *
 * entityName=commonProc
 * packageName=commonProc
 * methodName=getNumericData
 *
 */
 /*IF(division==1)*/
-- 区分:1 

/*END*/

/*IF(division==10)*/
-- 区分1(yy:mm)を時間を数値に変えた値に変更
SELECT FUN_CHANGE_TIME_HHMM_TO_NUM(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/) FROM DUAL
/*END*/
 /*IF(division==1000)*/
-- 区分:1 製造記録・包装記録出力前実績日チェック　0:正常　1:製造終了実績日が未来　2:カレンダーマスタにデータなし　3:カレンダーマスタ休日、製造日付が前月1日～当月当日以外　4:カレンダーマスタ平日、製造日付が当日1日～当月当日以外　-1:その他
SELECT FNC_CHECK_OUT_RDIRECTION(/*PARA01*/,/*PARA02*/,/*PARA03*/) FROM DUAL
/*END*/
