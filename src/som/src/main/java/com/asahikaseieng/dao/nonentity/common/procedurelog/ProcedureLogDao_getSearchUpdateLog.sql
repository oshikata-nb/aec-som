/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
*/

/**
* 更新対象のログ情報を取得する
*
* @author atts
*
* entityName=ProcedureLog
* packageName=common.procedurelog
* methodName=getSearchUpdateLog
*
*
*/
SELECT B.PROC_CD, B.PROC_DATE, B.PROC_SEQ
FROM   (SELECT PROC_CD
              ,PROC_DATE
              ,PROC_SEQ
              ,CHECK_FLG
              ,INPUTOR_CD
              ,MAX(PROC_SEQ) OVER(PARTITION BY PROC_CD) AS MAXPROCSEQ
        FROM   (SELECT PROC_CD
                      ,PROC_DATE
                      ,PROC_SEQ
                      ,CHECK_FLG
                      ,INPUTOR_CD
                      ,MAX(PROC_DATE) OVER(PARTITION BY PROC_CD) AS MAXDATE
                FROM   TEMP_BATCH_MSG
                WHERE  PROC_CD = /*srhProcCd*/'%') A
        WHERE  A.PROC_DATE = A.MAXDATE) B
WHERE  B.PROC_SEQ = B.MAXPROCSEQ AND　B.CHECK_FLG = 0 -- 0:未確認
 AND　B.INPUTOR_CD = /*srhInputorCd*/'%'
GROUP  BY B.PROC_CD, B.PROC_DATE, B.PROC_SEQ
