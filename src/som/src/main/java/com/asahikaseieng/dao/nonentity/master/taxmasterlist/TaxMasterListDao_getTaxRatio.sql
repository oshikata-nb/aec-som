/*
 * ロケーション一覧用SQL
 *
 * entityName=TaxMasterList
 * packageName=taxMasterlist
 * methodName=getTaxRatio
 *
 */

SELECT TAX_RATIO
FROM TAX_MASTER
WHERE TAX_CD IS NOT NULL
/*IF (taxCd != null) && (taxCd != "")*/
AND TAX_CD = /*taxCd*/'53' 
/*END*/

