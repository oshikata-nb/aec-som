/*
 * 買掛ロールバック処理 グループ間相殺データ(締め未処理)存在チェック用SQL
 *
 * entityName=ArRollback
 * packageName=credit.arrollback
 * methodName=getSearchOffsetList
 *
 */
SELECT ORGANIZATION_CD
FROM OFFSET_GROUP_DATA
WHERE ORGANIZATION_CD IS NOT NULL

/*IF (( organizationCd != null ) && ( organizationCd != "" ))*/
AND	ORGANIZATION_CD = /*organizationCd*/'%'
/*END*/

AND VENDER_DIVISION = 'SI'
AND PAYABLE_UPDATE_DIVISION = 0	
--20210407 改修　No1202開始	
AND APPROVALDATE IS NULL			
AND OFFSET_DATE BETWEEN  /*offsetFromDate*/'20210201' AND /*offsetToDate*/'20210228'
--AND PAYABLE_UPDATE_DATE = creditDate '2021/04/07'	
--20210407 改修　No1202終了	
