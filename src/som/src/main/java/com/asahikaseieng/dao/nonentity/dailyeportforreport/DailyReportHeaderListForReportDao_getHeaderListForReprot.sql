/*
 * ��Ɠ��w�b�_�[�ꗗ�pSQL
 *
 * entityName=DailyReportHeaderListForReport
 * packageName=dailyeportforreport
 * methodName=getHeaderListForReprot
 *
 */
SELECT
	DRH.PRODUCTION_LINE
,	LINE.PRODUCTION_LINE_NAME
,	DRH.PRODUCTION_DATE
,	DRH.TANTO_DIVISION
,	DRH.TANTO_CD
,	TANTO.TANTO_NM
,	DRH.SEQ

,	DRH.INSIDE_TOTAL
,	DRH.OUTSIDE_TOTAL
,	DRH.EMPLOY_TIME
,	DRH.INDIRECT_TIME

,	DRH.INSIDE_TOTAL_HHMM
,	DRH.OUTSIDE_TOTAL_HHMM
,	DRH.EMPLOY_TIME_HHMM
,	DRH.INDIRECT_TIME_HHMM

,	DRH.INPUT_DATE
,	DRH.INPUTOR_CD
,	INPUTOR.TANTO_NM AS INPUTOR_NAME

,	DRH.UPDATE_DATE
,	DRH.UPDATOR_CD
,	UPDATOR.TANTO_NM AS UPDATOR_NAME

FROM DAILY_REPORT_HEADER DRH
	INNER JOIN (
/*IF (condition.srhTantoDiv != null) && (condition.srhTantoDiv == "1")*/
			SELECT	-- �S����
				 TANTO_CD
				,TANTO_NM
			 FROM LOGIN
/*END*/
/*IF (condition.srhTantoDiv != null) && (condition.srhTantoDiv == "2")*/
			SELECT	-- 協力会社
				 ORGANIZATION_CD AS TANTO_CD
				,ORGANIZATION_NAME AS TANTO_NM
			FROM ORGANIZATION
/*END*/
			) TANTO ON DRH.TANTO_CD = TANTO.TANTO_CD
	INNER JOIN (LOGIN) INPUTOR ON DRH.INPUTOR_CD = INPUTOR.TANTO_CD
	INNER JOIN (LOGIN) UPDATOR ON DRH.UPDATOR_CD = UPDATOR.TANTO_CD
	INNER JOIN (LINE) LINE ON DRH.PRODUCTION_LINE = LINE.PRODUCTION_LINE

WHERE DRH.TANTO_CD IS NOT NULL
AND	TO_CHAR(DRH.PRODUCTION_DATE,'YYYY/MM/DD') = /*condition.srhDate*/'2009/04/14'
AND	DRH.PRODUCTION_LINE = /*condition.srhLine*/'KL'
AND	DRH.TANTO_DIVISION = /*condition.srhTantoDiv*/'1'
ORDER BY DRH.TANTO_DIVISION, DRH.SEQ, DRH.TANTO_CD

