/*
 * グループ間相殺入力一覧画面用SQL
 *
 * entityName=OffsetList
 * packageName=payment.offset
 * methodName=getSearchList
 *
 */
SELECT OGH.OFFSET_NO                  --相殺番号
,      OGH.ORGANIZATION_CD            --部署コード
,      ORG.ORGANIZATION_NAME          --部署名称
,      OGH.OFFSET_GROUP_CD            --相殺グループコード
,      OSG.OFFSET_GROUP_NAME          --相殺グループ名称
,      TO_CHAR(OGH.OFFSET_DATE,'YYYY/MM/DD') AS OFFSET_DATE
                                      --相殺締め日
,      OGH.OFFSET_AMOUNT              --相殺金額
,      OGH.APPROVAL_STATUS            --承認フラグ
,      OGD.CATEGORY_DIVISION          --分類コード
,      OGD.CATEGORY_NAME              --分類名称
FROM   OFFSET_GROUP_HEADER OGH
       LEFT JOIN BELONG
       ON OGH.INPUTOR_CD = BELONG.TANTO_CD
       AND NVL(BELONG.BELONG_KBN, 1) = 1 --主所属
	   LEFT JOIN ORGANIZATION ORG
	   ON BELONG.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	   LEFT JOIN LOGIN LOGIN
	   ON OGH.INPUTOR_CD = LOGIN.TANTO_CD
	   LEFT JOIN (SELECT DISTINCT OFFSET_GROUP_CD
	              ,               OFFSET_GROUP_NAME
	              FROM OFFSET_GROUP
	             ) OSG
	   ON OGH.OFFSET_GROUP_CD = OSG.OFFSET_GROUP_CD
	   LEFT JOIN (SELECT DISTINCT OFF.OFFSET_NO
	              ,               OFF.CATEGORY_DIVISION
	              ,               CLA.CATEGORY_NAME
	              FROM OFFSET_GROUP_DATA OFF
				  LEFT JOIN CLASSIFICATION CLA
				  ON CLA.DATA_TYPE = '5'
				  AND OFF.CATEGORY_DIVISION = TO_CHAR(CLA.CATEGORY_DIVISION)
	             ) OGD
	   ON OGH.OFFSET_NO = OGD.OFFSET_NO
WHERE  OGH.OFFSET_NO IS NOT NULL

/*IF (( condition.srhOrganizationCd != null ) && ( condition.srhOrganizationCd != "" ))*/
	AND	(BELONG.ORGANIZATION_CD LIKE /*condition.srhOrganizationCd*/'%' OR ORG.ORGANIZATION_NAME LIKE /*condition.srhOrganizationCd*/'%')
/*END*/

/*IF (( condition.srhTantoCd != null ) && ( condition.srhTantoCd != "" ))*/
	AND	(OGH.INPUTOR_CD LIKE /*condition.srhTantoCd*/'%' OR LOGIN.TANTO_NM LIKE /*condition.srhTantoCd*/'%')
/*END*/

/*IF (( condition.srhOffsetGrp != null ) && ( condition.srhOffsetGrp != "" ))*/
	AND	OGH.OFFSET_GROUP_CD = /*condition.srhOffsetGrp*/'%'
/*END*/

/*IF (( condition.srhOffsetDateFrom != null ) && ( condition.srhOffsetDateFrom != "" ))*/
	AND	OGH.OFFSET_DATE >= /*condition.srhOffsetDateFrom*/'2009/07/01'
/*END*/

/*IF (( condition.srhOffsetDateTo != null ) && ( condition.srhOffsetDateTo != "" ))*/
	AND	OGH.OFFSET_DATE <= /*condition.srhOffsetDateTo*/'2009/07/31'
/*END*/

/*IF (( condition.srhCassification != null ) && ( condition.srhCassification != "" )) */
	AND	OGD.CATEGORY_DIVISION = /*condition.srhCassification*/'%'
/*END*/

/*IF condition.srhOutputDivision != "0" */
	AND	OGH.APPROVAL_STATUS = /*condition.srhOutputDivision*/1
/*END*/

ORDER BY OGH.OFFSET_NO
,        OGH.OFFSET_GROUP_CD
,        OGH.OFFSET_DATE
