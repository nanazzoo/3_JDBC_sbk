package am.parameter.model.dao;

import static am.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import am.parameter.vo.Parameter;


public class ParameterDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	public ParameterDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("parameter-query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 파라미터 개수 조회 DAO
	 * @param conn
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int countParameter(Connection conn, Parameter parameter) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("countParameter");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	/** 최근 물성치 조회 DAO
	 * @param conn
	 * @param parameter
	 * @return parameterList
	 * @throws Exception
	 */
	public Parameter selectCParameter(Connection conn, Parameter parameter) throws Exception {
		Parameter cParameter = null;
		
		try {
			String sql = prop.getProperty("selectCParameter");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cParameter = new Parameter();
				
				cParameter.setTankNo(rs.getInt(1));
				cParameter.setParameterNo(rs.getInt(2));
				cParameter.setRegDate(rs.getString(3));
				cParameter.setParamTemp(rs.getDouble(4));
				cParameter.setParamNo3(rs.getDouble(5));
				cParameter.setParamPo4(rs.getDouble(6));
				cParameter.setParamKh(rs.getDouble(7));
				cParameter.setParamCa(rs.getDouble(8));
				cParameter.setParamMg(rs.getDouble(9));
							
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return cParameter;
	}
	
	
	
	/** 직전 물성치 조회 DAO
	 * @param conn
	 * @param parameter
	 * @return parameterList
	 * @throws Exception
	 */
	public Parameter selectPParameter(Connection conn, Parameter parameter) throws Exception {
		Parameter pParameter = null;
		
		try {
			String sql = prop.getProperty("selectPParameter");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pParameter = new Parameter();
				
				pParameter.setTankNo(rs.getInt(1));
				pParameter.setParameterNo(rs.getInt(2));
				pParameter.setRegDate(rs.getString(3));
				pParameter.setParamTemp(rs.getDouble(4));
				pParameter.setParamNo3(rs.getDouble(5));
				pParameter.setParamPo4(rs.getDouble(6));
				pParameter.setParamKh(rs.getDouble(7));
				pParameter.setParamCa(rs.getDouble(8));
				pParameter.setParamMg(rs.getDouble(9));
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return pParameter;
	}


	/** 물성치 기록 DAO
	 * @param conn
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int insertParameter(Connection conn, Parameter parameter) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertParameter");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			
			pstmt.setDouble(3, parameter.getParamTemp());
			pstmt.setDouble(4, parameter.getParamNo3());
			pstmt.setDouble(5, parameter.getParamPo4());
			pstmt.setDouble(6, parameter.getParamKh());
			pstmt.setDouble(7, parameter.getParamCa());
			pstmt.setDouble(8, parameter.getParamMg());
			
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 물성치 삭제 DAO
	 * @param conn
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int deleteParameter(Connection conn, Parameter parameter) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteParameter");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			pstmt.setInt(3, parameter.getParameterNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 전체 물성치 삭제 DAO
	 * @param conn
	 * @param parameter
	 * @return result
	 * @throws Exception
	 */
	public int deleteAllParameter(Connection conn, Parameter parameter) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteAllParameter");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parameter.getMemberNo());
			pstmt.setInt(2, parameter.getTankNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
}
