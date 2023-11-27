/*
 * Created on 2014/03/05
 *
 * $copyright$
 *
*/

/**
* ログ情報を確認済みに更新する
*
* @author atts
*
* entityName=ProcedureLog
* packageName=common.procedurelog
* methodName=updateProcLog
*
*
*/

UPDATE TEMP_BATCH_MSG
SET    CHECK_FLG   = 1 -- 1:確認済み
      ,UPDATE_DATE = /*bean.updateDate*/'2015/01/01'
      ,UPDATOR_CD  = /*bean.updatorCd*/'aec'
WHERE  PROC_CD = /*bean.procCd*/'%'
AND    PROC_DATE = /*bean.procDate*/'2015/01/01'
AND    PROC_SEQ = /*bean.procSeq*/1
