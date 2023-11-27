CREATE OR REPLACE PROCEDURE      PRO_PLAN_LOG(
	daDate in date, nDiv in number,  vFunc in varchar2, vLog in varchar2)
	IS pragma autonomous_transaction;
	
BEGIN
	insert into plan_log 
	(
		PLAN_DATE,
		LOG_DIV,
		LOG_STRING,
		LOG_FUNC
	) values (
		daDate,
		nDiv,
		vLog,
		vFunc
	);
	commit;
END;
/
