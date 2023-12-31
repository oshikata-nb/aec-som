/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/

/**
 * 日毎発番管理情報取得SQL
 *
 * @author tosco
 *
 * entityName=DailyNumberList
 * packageName=numbering
 * methodName=getDailyNumber
 *
 */
SELECT	DAILY_NUMBER.KEY
,		DAILY_NUMBER.DATEKEY
,		DAILY_NUMBER.FIXED_CHAR
,		DAILY_NUMBER.FIXED_CHAR_NM
,		DAILY_NUMBER.MIN_CONSECUTIVE_NO
,		DAILY_NUMBER.MAX_CONSECUTIVE_NO
,		DAILY_NUMBER.CURRENT_CONSECUTIVE_NO
,		DAILY_NUMBER.INPUT_DATE
,		DAILY_NUMBER.INPUTOR_CD
,		DAILY_NUMBER.UPDATE_DATE
,		DAILY_NUMBER.UPDATOR_CD
FROM	DAILY_NUMBER

WHERE DAILY_NUMBER.KEY = /*key*/'DIRECTIONNO' 
  AND (DAILY_NUMBER.DATEKEY = /*datekey*/'20000101' OR 
       DAILY_NUMBER.DATEKEY = /*initDatekey*/'20090220')
ORDER BY DAILY_NUMBER.DATEKEY DESC
FOR UPDATE NOWAIT
