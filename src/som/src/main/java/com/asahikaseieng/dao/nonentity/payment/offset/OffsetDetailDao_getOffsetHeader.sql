/*
 * 相殺一覧用SQL
 *
 * entityName=OffsetList
 * packageName=payment.offset
 * methodName=getOffsetHeader
 *
 */
SELECT OGH.OFFSET_NO                  --相殺番号
,      OGH.ORGANIZATION_CD            --部署コード
,      ORG.ORGANIZATION_NAME          --部署名称
,      OGH.OFFSET_GROUP_CD            --相殺グループコード
,      OSG.OFFSET_GROUP_NAME          --相殺グループ名称
,      OGH.OFFSET_DATE                --相殺締め日
,      OGH.SUMMARY_CD                 --摘要コード
,      OGH.SUMMARY                    --摘要名
,      OGH.OFFSET_AMOUNT              --相殺金額
,      OGH.APPROVAL_STATUS            --承認ステータス
FROM   OFFSET_GROUP_HEADER OGH
	   LEFT JOIN ORGANIZATION ORG
	   ON OGH.ORGANIZATION_CD = ORG.ORGANIZATION_CD
	   LEFT JOIN (SELECT DISTINCT OFFSET_GROUP_CD
	              ,               OFFSET_GROUP_NAME
	              FROM OFFSET_GROUP
	             ) OSG
	   ON OGH.OFFSET_GROUP_CD = OSG.OFFSET_GROUP_CD
WHERE  OGH.OFFSET_NO = /*offsetNo*/'%'
