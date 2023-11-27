/*
 * 文字列項目取得用(ここは引数の区分毎に取得する値を変えています。新規に追加する場合は区分を重複せずまた区分を変えないようにお願いします）
 *
 * entityName=commonProc
 * packageName=commonProc
 * methodName=getStringData
 *
 */
 /*IF(division==1)*/
-- 区分:1 表示する課税区分を取得する SALES:売上 PURCHASE:仕入　BOTH:両方　NONE:無し
SELECT FUN_GET_TAX_CATEGORY(/*PARA01*/) FROM DUAL
/*END*/

 /*IF(division==2)*/
-- 区分:消費税コードから消費税率を取得する
SELECT FUN_GET_TAX_RATIO(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/,/*PARA05*/) FROM DUAL
/*END*/

 /*IF(division==3)*/
-- 区分:売上と仕入のデータから消費税コードを取得する
SELECT FUN_GET_TAX_CD_NEW(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/,/*PARA05*/,/*PARA06*/,/*PARA07*/,/*PARA08*/,/*PARA09*/,/*PARA10*/) FROM DUAL
/*END*/

 /*IF(division==4)*/
-- 区分:品目と取引先から消費税区分を取得する
SELECT FUN_GET_TAX_DIVISION(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/,/*PARA05*/) FROM DUAL
/*END*/

/*IF(division==5)*/
-- 区分5: シーケンス番号から運賃を取得する
SELECT FUN_GET_NEXT_CARRY_FARE(/*PARA01*/) FROM DUAL
/*END*/

/*IF(division==10)*/
-- 区分10:引数１と引数２を時刻で加算する ※引数３から５は拡張用
SELECT FUN_CALC_DATE_TIME_ADD(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/,/*PARA05*/) FROM DUAL
/*END*/
/*IF(division==11)*/
-- 区分11:引数１と引数２を時刻で減算する ※引数３から５は拡張用
SELECT FUN_CALC_DATE_TIME_SUB(/*PARA01*/,/*PARA02*/,/*PARA03*/,/*PARA04*/,/*PARA05*/) FROM DUAL
/*END*/





