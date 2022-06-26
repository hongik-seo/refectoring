package com.nanam.domain.memberDB;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.nanam.util.DBManager;
public class MemberDAO{

	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}

	public long makeId() throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;

		//System.out.println("makeID()");
		Set<Long> set = new HashSet<>();

		long id = (long)(Math.random()*10000)+1;

		String sql = "SELECT ID FROM BRIDGE  WHERE ID != ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, id);

		resultSet = pstmt.executeQuery();


		long reMakeId=  (long) (Math.random() * 10000) + 1;;

		while (resultSet.next()) {

			long resultId = resultSet.getLong("ID");
			set.add(resultId);
		}

		Iterator<Long> itar = set.iterator();

		while (itar.hasNext()) {

			long reId = itar.next();


			if(reMakeId == reId ) {
				reMakeId = (long) (Math.random() * 10000) + 1;

				//System.out.println("reMakeId:::::::::::::::::" + reMakeId);
			}

			reMakeId=reMakeId;
		}

		String sql2 = "insert into BRIDGE ( ID ) VALUES (?)";

		pstmt2 = conn.prepareStatement(sql2);

		pstmt2.setLong(1, reMakeId);

		pstmt2.executeUpdate();

		return reMakeId;

	}


	public void insertAccess (AgreeVO agreeVO) throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		conn = DBManager.getConnection();

		//System.out.println("insertAccess(AgreeVO agreeVO)");

		String sql = "INSERT INTO AGREE ( AGREE_YN_USER_CONDITION , AGREE_YN_PRIVATE , AGREE_YN_ALL , ID ) VALUES (?, ? , ? , ? )";
		pstmt = conn.prepareStatement(sql);


		pstmt.setString(1, agreeVO.getAgreeYnList().get(0));
		pstmt.setString(2, agreeVO.getAgreeYnList().get(1));
		pstmt.setString(3, agreeVO.getAgreeYnList().get(2));
		pstmt.setLong(4, agreeVO.getId());

		pstmt.executeUpdate();

	}

	public void insertAccess (MemberVO member) throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet resultSet = null;
		conn = DBManager.getConnection();

		//System.out.println("insertAccess(MemberVO member)");

		String sql = "INSERT INTO MEMBER ( USER_NAME, USER_ID , USER_PWD , USER_PWD_CONFIRM , USER_EMAIL , USER_PHONE , ZIPCODE , ADDRESS , DETAIL_ADDRESS, USE_YN_OF_SERVICE ,ID) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
		pstmt = conn.prepareStatement(sql);

		String sql2 = "INSERT INTO MEMBER_HISTORY ( USER_NAME, USER_ID , USER_PWD , USER_PWD_CONFIRM , USER_EMAIL , USER_PHONE , ZIPCODE , ADDRESS , DETAIL_ADDRESS, USE_YN_OF_SERVICE ,ID) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

		pstmt2 = conn.prepareStatement(sql2);

		pstmt.setString(1, member.getUserName());
		pstmt.setString(2, member.getUserId());
		pstmt.setString(3, member.getUserPwd());
		pstmt.setString(4, member.getUserPwdConfirm());
		pstmt.setString(5, member.getUserEmail());
		pstmt.setString(6, member.getUserPhone());
		pstmt.setString(7, member.getZipcode());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getDetailAddress());
		pstmt.setString(10, "Y");
		pstmt.setLong(11, member.getId());


		pstmt.executeUpdate();

		pstmt2.setString(1, member.getUserName());
		pstmt2.setString(2, member.getUserId());
		pstmt2.setString(3, member.getUserPwd());
		pstmt2.setString(4, member.getUserPwdConfirm());
		pstmt2.setString(5, member.getUserEmail());
		pstmt2.setString(6, member.getUserPhone());
		pstmt2.setString(7, member.getZipcode());
		pstmt2.setString(8, member.getAddress());
		pstmt2.setString(9, member.getDetailAddress());
		pstmt2.setString(10, "Y");
		pstmt2.setLong(11, member.getId());
		pstmt2.executeUpdate();

	}

	public MemberVO alreadyExistIDAccess (String alreadyId) throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		conn = DBManager.getConnection();

		String sql = "SELECT USER_ID FROM MEMBER WHERE USER_ID = ? ";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, alreadyId);

		resultSet = pstmt.executeQuery();

		MemberVO memberVO = new MemberVO();

		while (resultSet.next()) {

			memberVO.setUserId(resultSet.getString("USER_ID"));

		}
		return memberVO;
	}

	public Map<String,Object> selectAccess(Map<String,Object> map,Map<String,String> slqMap ) throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		conn = DBManager.getConnection();

		MemberVO memberVO=null;

		Map<String,Object> returnMap  = new HashMap<>();

		String where = slqMap.get("sql");

		String sql = "SELECT M.ID, M.USER_NAME, M.USER_ID , M.USER_PWD , M.USER_EMAIL , M.USER_PHONE , M.ZIPCODE, M.ADDRESS, M.DETAIL_ADDRESS , P.TOTAL_POINT FROM MEMBER M  " +slqMap.get("sql");


		pstmt = conn.prepareStatement(sql);

		//System.out.println("DAO SQL::::::"+sql);

		ArrayList<Object> valueList = new ArrayList<>();

		Iterator<Map.Entry<String, Object>> itar = map.entrySet().iterator();

		while (itar.hasNext()){

			Object values=itar.next().getValue();

			valueList.add(values);
		}

		ArrayList<PreparedStatement> pstmtList = new ArrayList<>();

		for(int i=0; i<valueList.size(); i++) {

			pstmt.setString((i+1), (String) valueList.get(i));

			//System.out.println(i);
			//System.out.println("valuses::::::::::"+valueList.get(i));

			pstmtList.add(pstmt);
		}

		resultSet = pstmtList.get(0).executeQuery();


		while (resultSet.next()){

			Long id=resultSet.getLong("ID");
			String user_name=resultSet.getString("USER_NAME");
			String user_id=resultSet.getString("USER_ID");
			String user_pwd = resultSet.getString("USER_PWD");
			String user_email = resultSet.getString("USER_EMAIL");
			String user_phone = resultSet.getString("USER_PHONE");
			String zipcode = resultSet.getString("ZIPCODE");
			String address = resultSet.getString("ADDRESS");
			String detail_address = resultSet.getString("DETAIL_ADDRESS");
			long point = resultSet.getLong("TOTAL_POINT");


			returnMap.put("ID",id);
			returnMap.put("USER_NAME",user_name);
			returnMap.put("USER_ID",user_id);
			returnMap.put("USER_PWD",user_pwd);
			returnMap.put("USER_EMAIL",user_email);
			returnMap.put("USER_PHONE",user_phone);
			returnMap.put("ZIPCODE",zipcode);
			returnMap.put("ADDRESS",address);
			returnMap.put("DETAIL_ADDRESS",detail_address);
			returnMap.put("TOTAL_POINT",point);
		}

		//System.out.println(returnMap);
		return returnMap;
	}


	public void insertAccess(Map<String,String> map) throws SQLException {

		Connection conn = DBManager.getConnection();
		Statement stmt = null;
		ResultSet resultSet = null;

		conn = DBManager.getConnection();

		//System.out.println("insertAccess(MAP)");

		String insert = map.get("insert");

		String values = map.get("values");

		String sql = insert + values;

		stmt= conn.createStatement();

		//System.out.println(sql);

		stmt.executeUpdate(sql);

	}

	public String updateAccess(Map<String,Object> map) throws SQLException {

		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;

		PreparedStatement pstmt2 = null;

		PreparedStatement pstmt3 = null;

		ResultSet resultSet = null;

		conn = DBManager.getConnection();

		//System.out.println("update Access(MAP)");

		//System.out.println("updateMAP::::::::::::"+map);

		String updateSql  =  "UPDATE MEMBER SET USER_ID =? , USER_NAME = ? , USER_PWD = ? , USER_PWD_CONFIRM =? , USER_EMAIL =? , USER_PHONE =? , ZIPCODE =? , ADDRESS =? , DETAIL_ADDRESS =?, MODDATE =? WHERE ID = ? AND USER_ID = ? "    ;

		pstmt=conn.prepareStatement(updateSql);


		//System.out.println(updateSql);

		pstmt.setString(1,map.get("USER_ID").toString());
		pstmt.setString(2,map.get("USER_NAME").toString());
		pstmt.setString(3,map.get("USER_PWD").toString());
		pstmt.setString(4,map.get("USER_PWD_CONFIRM").toString());
		pstmt.setString(5,map.get("USER_EMAIL").toString());
		pstmt.setString(6,map.get("USER_PHONE").toString());
		pstmt.setString(7,map.get("ZIPCODE").toString());
		pstmt.setString(8,map.get("ADDRESS").toString());
		pstmt.setString(9,map.get("DETAIL_ADDRESS").toString());
		pstmt.setDate(10, Date.valueOf(map.get("MODDATE").toString()));
		pstmt.setLong(11, Long.parseLong(map.get("ID").toString()));
		pstmt.setString(12, map.get("USER_ID").toString());

		//System.out.println(pstmt);

		pstmt.executeQuery();

		String question = "";

		for(int i=0; i<map.size(); i++){

			question += " ?  ,";

		}

		String valuesMemberHistorySql = "  VALUES  ( "+question+" ? )";

		String insertMemberHistorySql = "INSERT INTO MEMBER_HISTORY (USER_ID,  USER_NAME  , USER_PWD , USER_PWD_CONFIRM , USER_EMAIL , USER_PHONE , ZIPCODE , ADDRESS , DETAIL_ADDRESS,  MODDATE ,ID , USE_YN_OF_SERVICE )" ;

		String insertMemberHistory = insertMemberHistorySql + valuesMemberHistorySql;

		//System.out.println(insertMemberHistory);


		pstmt2= conn.prepareStatement(insertMemberHistory);

		pstmt2.setString(1,map.get("USER_ID").toString());
		pstmt2.setString(2,map.get("USER_NAME").toString());
		pstmt2.setString(3,map.get("USER_PWD").toString());
		pstmt2.setString(4,map.get("USER_PWD_CONFIRM").toString());
		pstmt2.setString(5,map.get("USER_EMAIL").toString());
		pstmt2.setString(6,map.get("USER_PHONE").toString());
		pstmt2.setString(7,map.get("ZIPCODE").toString());
		pstmt2.setString(8,map.get("ADDRESS").toString());
		pstmt2.setString(9,map.get("DETAIL_ADDRESS").toString());
		pstmt2.setDate(10, Date.valueOf(map.get("MODDATE").toString()));
		pstmt2.setLong(11, Long.valueOf(map.get("ID").toString()));
		pstmt2.setString(12,"Y");

		pstmt2.executeUpdate();

		//System.out.println("완료");

		return "success";

	}
}


