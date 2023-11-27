/*
 * 受入入力 入荷ロケーション存在チェック用SQL
 *
 * entityName=AcceptDetailList
 * packageName=accept
 * methodName=getCountAvailableLocation
 *
 */

SELECT COUNT(LOCATION.LOCATION_CD)
FROM LOCATION
WHERE LOCATION_CD IS NOT NULL
AND LOCATION.AVAILABLE_FLG = 1
/*IF (locationCd != null) && (locationCd != "")*/
AND LOCATION.LOCATION_CD = /*locationCd*/
/*END*/
