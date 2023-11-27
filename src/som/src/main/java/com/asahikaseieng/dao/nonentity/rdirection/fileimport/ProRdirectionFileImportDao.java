/*
 * Created on 2022/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection.fileimport;

/**
 * ProcedureCallクラス.DAO
 * @author 
 */
public interface ProRdirectionFileImportDao {
	/**
	 * 製造実績ファイルインポート
	 * @param dto dto
	 */
	void rdirectionFileImport(ProRDirectionFileImportDto dto);

	/** rdirectionFileImport_PROCEDURE_CALL */
	String rdirectionFileImport_PROCEDURE_CALL = "PRO_RDIRECTION_FILE_IMPORT";

}
