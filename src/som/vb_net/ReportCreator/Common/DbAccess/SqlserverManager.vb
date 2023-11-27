Option Explicit On
Option Strict On

Imports System.Data.SqlClient
Imports System.Text
Imports log4net

''' <summary>
''' SqlServer処理クラス
''' </summary>
''' <remarks></remarks>
Public Class SqlserverManager
    Inherits AbstractDbManager

#Region "変数"
    ''' <summary>
    ''' ロガー
    ''' </summary>
    ''' <remarks></remarks>
    Private _log As ILog = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)
#End Region

#Region "コンストラクタ"
    ''' <summary>
    ''' コンストラクタ
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub New(ByVal dataSource As String, ByVal initialCatalog As String, ByVal user As String, ByVal pass As String)
        Try

            ' ＤＢコネクション設定
            Dim connectionString As String = String.Format("Data Source={0};Initial Catalog={1};User ID={2};Password={3};" _
                                                           , dataSource, initialCatalog, user, pass)
            _dbCommand = New SqlCommand()

            If _dbConnection Is Nothing Then
                _dbConnection = New SqlConnection(connectionString)
            Else
                If _dbConnection.ConnectionString.Equals(connectionString) = False Then
                    _dbConnection.Close()
                End If
            End If

            ' ＤＢ接続
            Me.Open()

        Catch ex As Exception
            _log.Error(ex)
            Throw ex
        End Try

    End Sub
#End Region

#Region "データベース接続"
    ''' <summary>
    ''' SqlServer接続
    ''' </summary>
    ''' <remarks></remarks>
    Protected Overrides Sub Open()
        Try
            ' DB接続
            If _dbConnection.State <> ConnectionState.Open Then
                _dbConnection.Open()
            End If

        Catch ex As Exception
            _log.Error(ex)

            If _dbConnection IsNot Nothing Then
                _dbConnection.Dispose()
            End If

            Throw ex
        End Try
    End Sub
#End Region

#Region "トランザクション開始"
    ''' <summary>
    ''' トランザクション開始
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub BeginTran()

        If Not _dbConnection Is Nothing Then
            _dbTransaction = _dbConnection.BeginTransaction
        End If

    End Sub
#End Region

#Region "コミット"
    ''' <summary>
    ''' コミット
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub Commit()

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Commit()
        End If

        _dbTransaction = Nothing

    End Sub
#End Region

#Region "ロールバック"
    ''' <summary>
    ''' ロールバック
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub RollBack()

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Rollback()
        End If

        _dbTransaction = Nothing

    End Sub
#End Region

#Region "Dispose"
    ''' <summary>
    ''' 終了処理
    ''' </summary>
    ''' <remarks></remarks>
    Public Overrides Sub Dispose()

        If Not _dbConnection Is Nothing Then
            _dbConnection.Close()
            _dbConnection.Dispose()
            _dbConnection = Nothing
        End If

        If Not _dbCommand Is Nothing Then
            _dbCommand.Dispose()
            _dbCommand = Nothing
        End If

        If Not _dbDataAdapter Is Nothing Then
            CType(_dbDataAdapter, SqlDataAdapter).Dispose()
            _dbDataAdapter = Nothing
        End If

        If Not _dbTransaction Is Nothing Then
            _dbTransaction.Dispose()
            _dbTransaction = Nothing
        End If

        GC.SuppressFinalize(Me)
    End Sub
#End Region

#Region "SQL実行（SELECT）"
    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDataSet(ByVal wSql As String) As DataSet
        Try

            Dim ds As New DataSet

            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbDataAdapter = New SqlDataAdapter(wSql, CType(_dbConnection, SqlConnection))
                _dbDataAdapter.Fill(ds)
                LastSQL = ""
            End If

            Return ds

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            Throw ex
        End Try
    End Function

    ''' <summary>
    ''' SQL実行（SELECT）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDataSet(ByVal wSql As String, ByVal tableName As String) As DataSet
        Try

            Dim ds As New DataSet

            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbDataAdapter = New SqlDataAdapter(wSql, CType(_dbConnection, SqlConnection))
                CType(_dbDataAdapter, SqlDataAdapter).Fill(ds, tableName)
                LastSQL = ""
            End If

            Return ds

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            Throw ex
        End Try

    End Function
#End Region

#Region "SQL実行（SELECT以外）"
    ''' <summary>
    ''' SQL実行（SELECT以外）
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function ExecuteSQL(ByVal wSql As String) As Integer
        Try

            Dim RecordCount As Integer

            If Not _dbConnection Is Nothing Then
                LastSQL = wSql
                _dbCommand = New SqlCommand(wSql, CType(_dbConnection, SqlConnection))
                _dbCommand.Transaction = _dbTransaction
                RecordCount = _dbCommand.ExecuteNonQuery()
                LastSQL = ""
            End If

            Return RecordCount

        Catch ex As Exception
            _log.Error(ex)
            _log.Error(LastSQL)
            Throw ex
        End Try

    End Function
#End Region

#Region "SQL関数置換"

    ''' <summary>
    ''' 部分文字列取得SQL文生成
    ''' </summary>
    ''' <param name="str">部分文字列抽出対象</param>
    ''' <param name="startPos">部分文字列抽出開始位置</param>
    ''' <param name="length">部分文字列の長さ</param>
    ''' <returns>部分文字列取得SQL</returns>
    ''' <remarks></remarks>
    Public Overrides Function MakeSQLSubString(str As String, startPos As Integer, length As Integer) As String
        Return "substring( " & str & ", " & startPos & ", " & length & ")"
    End Function

    ''' <summary>
    ''' NULL置換文字列生成
    ''' </summary>
    ''' <param name="str">NULL判定対象文字列</param>
    ''' <param name="replaceStr">NULL置換文字列</param>
    ''' <returns>NULL置換関数文字列(SQL)</returns>
    ''' <remarks></remarks>
    Public Overrides Function MakeSQLConvertNULL(ByVal str As String, ByVal replaceStr As String) As String
        Return "ISNULL(" & str & " ,'" & replaceStr & "')"
    End Function

    ''' <summary>
    ''' 文字列選択置換SQL文生成
    ''' </summary>
    ''' <param name="str">置換対象文字列リスト</param>
    ''' <param name="replaceStr">置換文字列リスト</param>
    ''' <param name="elseStr">例外文字列</param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function MakeSQLReplace(ByVal str As String, ByVal pattern As String(), ByVal replaceStr As String(), ByVal elseStr As String) As String
        Dim strSQL As New StringBuilder

        strSQL.Append("CASE '" & str & "' ")

        For position As Integer = 0 To pattern.Length
            strSQL.Append(" WHEN '" & pattern(position) & "' , '" & replaceStr(position) & "' , ")
        Next

        strSQL.Append(" ELSE '" & elseStr & "' " & " THEN '" & elseStr & "' ")

        Return strSQL.ToString

    End Function

    ''' <summary>
    ''' 文字列日付関数取得SQL文生成
    ''' </summary>
    ''' <param name="dateTime"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Overrides Function ConvertSQLDateTime(ByVal dateTime As Date) As String
        ' SQL生成用
        Dim strSQL As New StringBuilder

        strSQL.Append(" CONVERT(DateTime, '")
        strSQL.Append(dateTime.ToString("yyyy-MM-dd HH:mm:ss"))
        strSQL.Append("', 120) ")

        Return strSQL.ToString

    End Function

    ''' <summary>
    ''' Dual句SQL文取得
    ''' </summary>
    ''' <returns>Dual句</returns>
    ''' <remarks></remarks>
    Public Overrides Function GetDualSQL() As String

        Return String.Empty

    End Function

#End Region

End Class
