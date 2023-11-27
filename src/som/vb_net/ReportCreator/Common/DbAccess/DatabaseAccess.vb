Option Explicit On
Option Strict On

Imports log4net

''' <summary>
''' DB�����N���X
''' </summary>
''' <remarks></remarks>
Public Class DatabaseAccess

#Region "�萔"
    Private Const DB_TYPE_ORACLE As String = "ORACLE"
    Private Const DB_TYPE_SQLSERVER As String = "SQLSERVER"
#End Region

#Region "�ϐ�"
    ''' <summary>
    ''' DB�}�l�[�W��
    ''' </summary>
    ''' <remarks></remarks>
    Private _dbManager As AbstractDbManager = Nothing
    ''' <summary>
    ''' ���K�[
    ''' </summary>
    ''' <remarks></remarks>
    Private _log As ILog = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType)
#End Region

#Region "�\����"
    ''' <summary>
    ''' DB�ڑ����
    ''' </summary>
    ''' <remarks></remarks>
    Public Structure DataBaseAccessInfo
        Public DbType As String
        Public DataSource As String
        Public InitialCatalog As String
        Public UserID As String
        Public PassWord As String

        Sub SetDataBaseAccessInfo(ByVal dbType As String, ByVal initialCatalog As String, ByVal userID As String _
                                  , ByVal passWord As String, ByVal dataSource As String)

            Me.DbType = dbType
            Me.InitialCatalog = initialCatalog
            Me.UserID = userID
            Me.PassWord = passWord
            Me.DataSource = dataSource

        End Sub
    End Structure
#End Region

#Region "�v���p�e�B"
    ''' <summary>
    ''' LastSQL
    ''' </summary>
    ''' <value></value>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Property LastSQL() As String
        Get
            If Not _dbManager Is Nothing Then
                Return _dbManager.LastSQL
            Else
                Return String.Empty
            End If

        End Get
        Set(value As String)
            If Not _dbManager Is Nothing Then
                _dbManager.LastSQL = value
            Else
                '�������Ȃ�
            End If
        End Set
    End Property
#End Region

#Region "�R���X�g���N�^"
    ''' <summary>
    ''' �R���X�g���N�^
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub New()

    End Sub
#End Region

#Region "�f�[�^�x�[�X�ڑ�"
    ''' <summary>
    ''' �f�[�^�x�[�X�ڑ�
    ''' </summary>
    ''' <param name="accessInfo"></param>
    ''' <remarks></remarks>
    Public Sub DBConnect(ByVal accessInfo As DataBaseAccessInfo)

        Try
            Select Case accessInfo.DbType.ToUpper
                Case DB_TYPE_ORACLE
                    'TODO:Oracle�g�p���̓R�����g����
                    '_dbManager = New OracleManager(accessInfo.DataSource _
                    '                            , accessInfo.UserID _
                    '                            , accessInfo.PassWord)
                Case DB_TYPE_SQLSERVER
                    _dbManager = New SqlserverManager(accessInfo.DataSource _
                                                     , accessInfo.InitialCatalog _
                                                     , accessInfo.UserID _
                                                     , accessInfo.PassWord)
                Case Else
                    'TODO
            End Select

        Catch ex As Exception
            _log.Error("DB�ڑ����s�BDB_TYPE=" & accessInfo.DbType & "; USER ID=" & accessInfo.UserID & "; PASSWORD=" & accessInfo.PassWord & "; DATA SOURCE=" & accessInfo.DataSource & ";")
            Throw ex
        End Try
    End Sub
#End Region

#Region "�f�[�^�x�[�X�ؒf"
    ''' <summary>
    ''' �f�[�^�x�[�X�ؒf
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub DBDisconnect()

        If Not _dbManager Is Nothing Then
            _dbManager.Dispose()
        End If

    End Sub
#End Region

#Region "�g�����U�N�V�����J�n"
    ''' <summary>
    ''' �g�����U�N�V�����J�n
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub BeginTran()

        If Not _dbManager Is Nothing Then
            _dbManager.BeginTran()
        End If

    End Sub
#End Region

#Region "�R�~�b�g"
    ''' <summary>
    ''' �R�~�b�g
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub Commit()

        If Not _dbManager Is Nothing Then
            _dbManager.Commit()
        End If

    End Sub
#End Region

#Region "���[���o�b�N"
    ''' <summary>
    ''' ���[���o�b�N
    ''' </summary>
    ''' <remarks></remarks>
    Public Sub RollBack()

        If Not _dbManager Is Nothing Then
            _dbManager.RollBack()
        End If

    End Sub
#End Region

#Region "SQL���s�iSELECT�j"
    ''' <summary>
    ''' SQL���s�iSELECT�j
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetDataSet(ByVal wSql As String) As DataSet
        Dim ds As New DataSet

        If Not _dbManager Is Nothing Then
            ds = _dbManager.GetDataSet(wSql)
        End If

        Return ds

    End Function

    ''' <summary>
    ''' SQL���s�iSELECT�j
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <param name="tableName"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function GetDataSet(ByVal wSql As String, ByVal tableName As String) As DataSet
        Dim ds As New DataSet

        If Not _dbManager Is Nothing Then
            ds = _dbManager.GetDataSet(wSql, tableName)
        End If

        Return ds

    End Function
#End Region

#Region "SQL���s�iSELECT�ȊO�j"
    ''' <summary>
    ''' SQL���s�iSELECT�ȊO�j
    ''' </summary>
    ''' <param name="wSql"></param>
    ''' <returns></returns>
    ''' <remarks></remarks>
    Public Function ExecuteSQL(ByVal wSql As String) As Integer
        Dim RecordCount As Integer

        If Not _dbManager Is Nothing Then
            RecordCount = _dbManager.ExecuteSQL(wSql)
        End If

        Return RecordCount

    End Function
#End Region

#Region "SQL�֐��ϊ�"
    ''' <summary>
    ''' ����������擾SQL������
    ''' </summary>
    ''' <param name="str">���������񒊏o�Ώ�</param>
    ''' <param name="startPos">���������񒊏o�J�n�ʒu</param>
    ''' <param name="length">����������̒���</param>
    ''' <returns>����������擾SQL</returns>
    ''' <remarks></remarks>
    Public Function MakeSQLSubString(str As String, startPos As Integer, length As Integer) As String
        Return _dbManager.MakeSQLSubString(str, startPos, length)
    End Function

    ''' <summary>
    ''' NULL�u�������񐶐�
    ''' </summary>
    ''' <param name="str">NULL����Ώە�����</param>
    ''' <param name="replaceStr">NULL�u��������</param>
    ''' <returns>NULL�u���֐�������(SQL)</returns>
    ''' <remarks></remarks>
    Public Function MakeSQLConvertNULL(ByVal str As String, ByVal replaceStr As String) As String
        Return _dbManager.MakeSQLConvertNULL(str, replaceStr)
    End Function

    ''' <summary>
    ''' ��������t�֐��擾SQL������
    ''' </summary>
    ''' <param name="dateTime">�ϊ����������t</param>
    ''' <returns>�ϊ������������</returns>
    ''' <remarks></remarks>
    Public Function ConvertSQLDateTime(ByVal dateTime As Date) As String
        Return _dbManager.ConvertSQLDateTime(dateTime)
    End Function

    ''' <summary>
    ''' Dual��擾
    ''' </summary>
    ''' <returns>Dual��</returns>
    ''' <remarks></remarks>
    Public Function GetDualSQL() As String
        Return _dbManager.GetDualSQL()
    End Function
#End Region

End Class
