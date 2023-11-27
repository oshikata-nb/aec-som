-- 製造指図プロシージャ一時取込
DROP TABLE DIRECTION_PROCEDURE_TEMP CASCADE CONSTRAINTS
/

CREATE TABLE DIRECTION_PROCEDURE_TEMP (
  TEMP_NO NVARCHAR2(20) NOT NULL
  , ROW_NUMBER NUMBER NOT NULL
  , DIRECTION_DIVISION NUMBER NOT NULL
  , DIRECTION_NO NVARCHAR2(20) NOT NULL
  , STEP_NO NUMBER NOT NULL
  , CONDITION NVARCHAR2(100)
  , REMARK NVARCHAR2(256)
  , NOTES NVARCHAR2(256)
  , RESULT_SDATE DATE
  , RESULT_EDATE DATE
  , CONDITION_TEMP NUMBER
  , CONDITION_TIME NUMBER
  , STIR_SPEED1 NUMBER
  , STIR_SPEED2 NUMBER
  , WATER_WEIGHT NUMBER
  , MAIN_STREAM NUMBER
  , PH NUMBER
  , RESULT_CONDITION_TEMP NUMBER
  , RESULT_STIR_SPEED NUMBER
  , RESULT_PH NUMBER
  , UPDATE_DATE DATE
  , UPDATOR_CD NVARCHAR2(10)
  , CONSTRAINT DIRECTION_PROCEDURE_TEMP_PKC PRIMARY KEY (TEMP_NO, ROW_NUMBER)
) 
/
COMMENT ON TABLE DIRECTION_PROCEDURE_TEMP IS '製造指図プロシージャ一時取込'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.TEMP_NO IS '一時取込番号'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.ROW_NUMBER IS '行番号'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.DIRECTION_DIVISION IS '指図区分|1:バッチ指図,2:充填・包装指図,3:詰替・貼替指図'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.DIRECTION_NO IS '指図番号'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.STEP_NO IS 'ステップNO.'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.CONDITION IS '工程条件:フリー入力'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.REMARK IS '備考'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.NOTES IS '注釈'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.RESULT_SDATE IS '開始実績日時'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.RESULT_EDATE IS '終了実績日時'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.CONDITION_TEMP IS '温度'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.CONDITION_TIME IS '時間'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.STIR_SPEED1 IS '攪拌速度1'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.STIR_SPEED2 IS '攪拌速度2'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.WATER_WEIGHT IS '洗浄水絶対重量'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.MAIN_STREAM IS '本流/予備溶解|0:本流,1:予備溶解①,2:予備溶解②,3:予備溶解③,4:予備溶解④,5:予備溶解⑤,6:予備溶解⑥,8:準備'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.PH IS 'ｐＨ'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.RESULT_CONDITION_TEMP IS '実績温度'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.RESULT_STIR_SPEED IS '実績攪拌速度'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.RESULT_PH IS '実績ｐＨ'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.UPDATE_DATE IS '更新日時'
/
COMMENT ON COLUMN DIRECTION_PROCEDURE_TEMP.UPDATOR_CD IS '更新者ID'
/
