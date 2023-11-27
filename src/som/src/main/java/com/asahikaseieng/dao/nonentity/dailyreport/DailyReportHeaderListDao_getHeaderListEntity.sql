/*
 * 作業日報ヘッダー一覧用SQL
 *
 * entityName=DailyReportHeaderList
 * packageName=dailyReportHeaderList
 * methodName=getDailyReportHeaderList
 *
 */
SELECT
	 DRH.PRODUCTION_LINE		-- 0
	,TO_CHAR(DRH.PRODUCTION_DATE,'YYYY/MM/DD') AS PRODUCTION_DATE	-- 1:製造日
	,DRH.TANTO_DIVISION			-- 2
	,DRH.TANTO_CD				-- 3
	,DRH.SEQ					-- 4
	,DRH.INSIDE_TOTAL			-- 5
	,DRH.OUTSIDE_TOTAL			-- 6
	,DRH.EMPLOY_TIME			-- 7
	,DRH.INDIRECT_TIME			-- 8
	
	,DRH.INSIDE_TOTAL_HHMM			-- 5 2021/3/29 作業日報改修により追加
	,DRH.OUTSIDE_TOTAL_HHMM			-- 6 2021/3/29 作業日報改修により追加
	,DRH.EMPLOY_TIME_HHMM			-- 7 2021/3/29 作業日報改修により追加
	,DRH.INDIRECT_TIME_HHMM			-- 8 2021/3/29 作業日報改修により追加
	,TANTO.TANTO_NM				-- 9 担当者名
	,'0' AS DELETE_FLG			-- 10 削除フラグ
FROM DAILY_REPORT_HEADER DRH
	INNER JOIN (
/*IF (condition.srhTantoDiv != null) && (condition.srhTantoDiv == "1")*/
			SELECT	-- 担当者
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
WHERE DRH.TANTO_CD IS NOT NULL
AND	TO_CHAR(DRH.PRODUCTION_DATE,'YYYY/MM/DD') = /*condition.srhDate*/
AND	DRH.PRODUCTION_LINE = /*condition.srhLine*/
AND	DRH.TANTO_DIVISION = /*condition.srhTantoDiv*/
ORDER BY DRH.TANTO_DIVISION, DRH.SEQ, DRH.TANTO_CD
