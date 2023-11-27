/*
 * 相殺グループマスタメンテ　詳細画面表示用SQL
 *
 * entityName=OffsetGroupDetail
 * packageName=offsetgroupdetail
 * methodName=getEntity
 *
 */
SELECT 	OFF.OFFSET_GROUP_CD
,		OFF.OFFSET_GROUP_NAME
,		OFF.VENDER_DIVISION
,		OFF.VENDER_CD
,		NVL(VND.VENDER_NAME1, '名称がありません') VENDER_NAME1
,		OFF.UPDATE_DATE
FROM 	OFFSET_GROUP OFF
		LEFT OUTER JOIN
		VENDER VND
		ON 		OFF.VENDER_CD = VND.VENDER_CD

WHERE	OFFSET_GROUP_CD IS NOT NULL

/*IF (( offsetGroupCd != null ) && ( offsetGroupCd != "" ))*/
	AND	OFF.OFFSET_GROUP_CD = /*offsetGroupCd*/'a'
/*END*/

ORDER BY OFF.OFFSET_GROUP_CD
,		 OFF.VENDER_DIVISION
,        OFF.VENDER_CD


