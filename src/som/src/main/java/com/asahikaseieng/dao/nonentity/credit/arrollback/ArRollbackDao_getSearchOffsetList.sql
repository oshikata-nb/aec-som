/*
 * 売掛ロールバック処理 グループ間相殺データ(締め未処理)存在チェック用SQL
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

AND VENDER_DIVISION = 'TS'
AND DEPOSIT_UPDATE_DIVISION = 0	--売掛更新フラグ(0:未処理)
--20210407 改修　No1202開始					
AND APPROVALDATE IS NULL --承認日(NULL:未入力)					
AND OFFSET_DATE BETWEEN  /*offsetFromDate*/'20210201' AND /*offsetToDate*/'20210228' --RB範囲内で未処理のデータのチェック					
--AND DELIVERY_UPDATE_DATE = cleditDate'2009/04/30'
--20210407 改修　No1202終了					


