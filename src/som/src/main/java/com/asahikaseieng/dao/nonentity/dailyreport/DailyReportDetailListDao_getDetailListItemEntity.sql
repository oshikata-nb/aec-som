/*
 * 作業日報明細一覧用SQL
 *
 * entityName=DailyReportDetailList
 * packageName=dailyreport
 * methodName=searchImpl
 *
 */
SELECT
     DRCH.DIRECTION_DIVISION		-- 0:指図区分
    ,DRCH.DIRECTION_NO				-- 1:指図番号
    ,DRCH.PRODUCTION_LINE			-- 2:生産ライン
    ,ITEM_QUEUE.ITEM_NAME			-- 3:品目名称
    ,DR.TANTO_DIVISION				-- 4:担当区分
    ,DR.SUB_TOTAL					-- 5:指図毎の合計
    ,DRCH.AREA_FLG					-- 6:'1' 範囲外データ '2' 範囲内データ
	,FUN_CHANGE_TIME_NUM_TO_HHMM(DR.SUB_TOTAL,NULL,NULL,NULL) AS SUB_TOTAL_HHMM

FROM (
	SELECT DIRECTION_HEADER.*, '2' as AREA_FLG
	FROM DIRECTION_HEADER
	WHERE
	   (/*condition.srhDate*/'2009/05/05' between TO_CHAR(DIRECTION_HEADER.RESULT_SDATE,'YYYY/MM/DD') and TO_CHAR(DIRECTION_HEADER.RESULT_EDATE,'YYYY/MM/DD'))
	AND DIRECTION_HEADER.PRODUCTION_LINE = /*condition.srhLine*/'KD'

	UNION 
	SELECT DRCH.*, '1' AS AREA_FLG
	FROM DIRECTION_HEADER DRCH

		INNER JOIN ( SELECT
	                 DRH.PRODUCTION_LINE
	                ,DRH.PRODUCTION_DATE
	                ,DR.DIRECTION_NO
					FROM DAILY_REPORT_HEADER DRH
					LEFT JOIN DAILY_REPORT DR ON DRH.PRODUCTION_LINE=DR.PRODUCTION_LINE AND DRH.PRODUCTION_DATE=DR.PRODUCTION_DATE
	                                         AND DRH.TANTO_DIVISION=DR.TANTO_DIVISION AND DRH.TANTO_CD=DR.TANTO_CD
					WHERE TO_CHAR(DRH.PRODUCTION_DATE,'YYYY/MM/DD') = /*condition.srhDate*/'2009/05/05'
					AND	DRH.TANTO_DIVISION = /*condition.srhTantoDiv*/'1'
	              ) DR ON DRCH.PRODUCTION_LINE = DR.PRODUCTION_LINE AND DRCH.DIRECTION_NO = DR.DIRECTION_NO
	WHERE DRCH.PRODUCTION_LINE = /*condition.srhLine*/'KD'
	AND (Not TO_CHAR(DR.PRODUCTION_DATE,'YYYY/MM/DD') between TO_CHAR(DRCH.RESULT_SDATE,'YYYY/MM/DD') and TO_CHAR(DRCH.RESULT_EDATE,'YYYY/MM/DD'))
     ) DRCH
     
    INNER JOIN VALID_ITEM_QUEUE ITEM_QUEUE ON DRCH.ITEM_CD = ITEM_QUEUE.ITEM_CD
    LEFT JOIN (SELECT
                 DAILY_REPORT_HEADER.PRODUCTION_LINE
                ,DAILY_REPORT_HEADER.PRODUCTION_DATE
                ,DAILY_REPORT_HEADER.TANTO_DIVISION
                ,DAILY_REPORT.DIRECTION_NO
                ,SUM(DAILY_REPORT.JOB_TIME) as SUB_TOTAL
                FROM DAILY_REPORT_HEADER
                INNER JOIN DAILY_REPORT ON DAILY_REPORT_HEADER.PRODUCTION_LINE=DAILY_REPORT.PRODUCTION_LINE
                                       AND DAILY_REPORT_HEADER.PRODUCTION_DATE=DAILY_REPORT.PRODUCTION_DATE
                                       AND DAILY_REPORT_HEADER.TANTO_DIVISION=DAILY_REPORT.TANTO_DIVISION
                                       AND DAILY_REPORT_HEADER.TANTO_CD=DAILY_REPORT.TANTO_CD
                INNER JOIN (
/*IF (condition.srhTantoDiv != null)*/
    /*IF (condition.srhTantoDiv == "2")*/
                            SELECT    -- 担当者
                             TANTO_CD
                            ,TANTO_NM
                            FROM LOGIN
	/*END*/
	/*IF (condition.srhTantoDiv == "1")*/
                            SELECT    -- 協力会社
                             ORGANIZATION_CD AS TANTO_CD
                            ,ORGANIZATION_NAME AS TANTO_NM
                            FROM ORGANIZATION
    /*END*/
/*END*/
                           ) TANTO ON DAILY_REPORT_HEADER.TANTO_CD = TANTO.TANTO_CD
                WHERE
                     TO_CHAR(DAILY_REPORT_HEADER.PRODUCTION_DATE,'YYYY/MM/DD') = /*condition.srhDate*/'2009/05/05'
                 AND DAILY_REPORT_HEADER.PRODUCTION_LINE = /*condition.srhLine*/'KD'
/*IF (condition.srhTantoDiv != null)*/
	/*IF (condition.srhTantoDiv == "2")*/
                 AND DAILY_REPORT_HEADER.TANTO_DIVISION = 1
	/*END*/
	/*IF (condition.srhTantoDiv == "1")*/
                 AND DAILY_REPORT_HEADER.TANTO_DIVISION = 2
	/*END*/
/*END*/
                GROUP BY
                 DAILY_REPORT_HEADER.PRODUCTION_LINE
                ,DAILY_REPORT_HEADER.PRODUCTION_DATE
                ,DAILY_REPORT_HEADER.TANTO_DIVISION
                ,DAILY_REPORT.DIRECTION_NO
              ) DR ON DRCH.PRODUCTION_LINE = DR.PRODUCTION_LINE AND DRCH.DIRECTION_NO = DR.DIRECTION_NO
ORDER BY
 DRCH.DIRECTION_DIVISION
,DRCH.DIRECTION_NO
