/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
*/

/**
* ログ情報を取得する
*
* @author atts
*
* entityName=ProcedureLog
* packageName=common.procedurelog
* methodName=getSearchList
*
*
*/
SELECT FLG, MSG, TO_CHAR(INPUT_DATE, 'YYYY/MM/DD HH24:MI:SS') AS INPUT_DATE
FROM   (SELECT PROC_CD
              ,PROC_DATE
              ,PROC_SEQ
              ,SEQ
              ,FLG
              ,MSG
              ,CHECK_FLG
              ,INPUT_DATE
              ,INPUTOR_CD
              ,MAX(PROC_SEQ) OVER(PARTITION BY PROC_CD) AS MAXPROCSEQ
        FROM   (SELECT PROC_CD
                      ,PROC_DATE
                      ,PROC_SEQ
                      ,SEQ
                      ,FLG
                      ,MSG
                      ,CHECK_FLG
                      ,INPUT_DATE
                      ,INPUTOR_CD
                      ,MAX(PROC_DATE) OVER(PARTITION BY PROC_CD) AS MAXDATE
                FROM   TEMP_BATCH_MSG
                WHERE  PROC_CD = /*srhProcCd*/'%') A
        WHERE  A.PROC_DATE = A.MAXDATE) B
WHERE  B.PROC_SEQ = B.MAXPROCSEQ AND　B.CHECK_FLG = 0 -- 0:未確認
 AND　B.INPUTOR_CD = /*srhInputorCd*/'%'
ORDER  BY SEQ
