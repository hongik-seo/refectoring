package com.nanam.controller.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanam.domain.memberDB.AgreeVO;
import com.nanam.domain.memberDB.MemberVO;

import com.nanam.service.MemberService;
import org.apache.axis.session.Session;
import org.json.simple.JSONObject;

@WebServlet(urlPatterns = { "/member/agreeFront", "/member/alreadyIdFind","/member/agreeAndJoin", "/member/login","/member/logOutFront","/member/idFindBeforeClickBtn",
		"/member/user-find", "/member/findResult", "/member/findPopup", "/member/myPageAdjust",
		"/member/joinFront", "/member/idFindFront", "/member/loginFront", "/member/pwdFindFront" ,"/member/myPageFront","/member/leaveOutFront","/member/myPageResult",})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String result;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
		String path = req.getRequestURI();
		//System.out.println(path);
		MemberService memberService = new MemberService();

		if (path.equals("/member/agreeFront")) {

			result = "/member/agreeAndJoin.jsp";

		}else if(path.equals("/member/alreadyIdFind")){

			String alreadyId = req.getParameter("alreadyId");

			MemberVO member=memberService.alreadyExistMember(alreadyId);

			String selectAlreadyId = member.getUserId();

			PrintWriter out=resp.getWriter();

			String canUse="";

			if(alreadyId.equals(selectAlreadyId)) {

				canUse = "notUsable";
				out.write(canUse);
				out.flush();
				out.close();
			}else if(selectAlreadyId==null) {

				canUse="usable";
				out.write(canUse);
				out.flush();
				out.close();

			}

		}else if (path.equals("/member/idFindFront")) {

			result = "/member/idFind.jsp";

		} else if (path.equals("/member/loginFront")) {

			result = "/member/login.jsp";

		} else  if (path.equals("/member/pwdFindFront")) {

			result = "/member/pwdFind.jsp";

		} else if(path.equals("/member/findResult")) {

			result = "/member/findResult.jsp";


		}else if(path.equals("/member/myPageFront")) {

			result="/member/myPage.jsp";

		}else if(path.equals("/member/myPageMain")) {


		}else if(path.equals("/member/leaveOutFront")) {

			result="/member/leaveOut.jsp";

		}else if(path.equals("/member/myPageResult")) {

			result="/member/myPageResult.jsp";
		}

		req.getRequestDispatcher(result).forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

		String path = req.getRequestURI();
		HttpSession session = req.getSession();


		if (path.equals("/member/agreeAndJoin")) {

			String agreeYnStr = req.getParameter("agreeList");
			String joinStr = req.getParameter("joinList");

			MemberService memberService = new MemberService();

			String success = memberService.agreeAndJoinReturn(agreeYnStr,joinStr);

			PrintWriter out = resp.getWriter();
			out.print(success);
			out.flush();
			out.close();

		}else if (path.equals("/member/login")) {

			String loginInfo = req.getParameter("loginInfo");

			MemberService memberService = new MemberService();

			JSONObject loginJSON =memberService.longinAndOut(loginInfo);

			String logInAndOutFlag ="";

			if(loginJSON.get("USER_ID")!=null && loginJSON.get("USER_ID")!="") {

				session.setAttribute("id",loginJSON.get("ID"));
				session.setAttribute("login_id",loginJSON.get("USER_ID"));
				session.setAttribute("login_pwd",loginJSON.get("USER_PWD"));
				session.setAttribute("login_name",loginJSON.get("USER_NAME"));
				session.setAttribute("login_phone",loginJSON.get("USER_PHONE"));
				session.setAttribute("login_address",loginJSON.get("ADDRESS"));
				session.setAttribute("login_detail_address",loginJSON.get("DETAIL_ADDRESS"));
				session.setAttribute("login_zipcode",loginJSON.get("ZIPCODE"));
				session.setAttribute("login_email",loginJSON.get("USER_EMAIL"));
				session.setAttribute("login_point",loginJSON.get("TOTAL_POINT"));

				logInAndOutFlag=memberService.isLogin(session);
			}

			if("success".equals(logInAndOutFlag)) {

				PrintWriter out = resp.getWriter();
				out.print(loginJSON);
				out.flush();
				out.close();
			}

		}else if (path.equals("/member/logOutFront")) {

			String logoutInfo = req.getParameter("logoutInfo");

			String logInAndOutFlag= "";
			MemberService memberService = new MemberService();

			JSONObject logoutJSON =memberService.longinAndOut(logoutInfo);

			if(
					logoutJSON.get("USER_ID").equals((String)session.getAttribute("login_id"))
					&&logoutJSON.get("USER_PWD").equals((String)session.getAttribute("login_pwd"))
			){

				session.removeAttribute("login_id");
				session.removeAttribute("login_pwd");

				logInAndOutFlag=memberService.isLogin(session);
			}
			if("success".equals(logInAndOutFlag)) {
				PrintWriter out = resp.getWriter();
				out.print(logoutJSON);
				out.flush();
				out.close();
			}

		}else if (path.equals("/member/user-find")) {

			String find_info = req.getParameter("find_info");

			//System.out.println(find_info);
			MemberService memberService = new MemberService();
			JSONObject  findJSON =memberService.userIdOrPwdFindSelect(find_info);

			//System.out.println(findJSON);
			PrintWriter out = resp.getWriter();
			out.print(findJSON);
			out.flush();
			out.close();

		}else if(path.equals("/member/myPageAdjust")){

			String modifyData = req.getParameter("modifyData");

			//System.out.println(modifyData);
			MemberService memberService = new MemberService();

			Map<String, String> map = new HashMap<>();

			JSONObject updateJson=memberService.myPageAdjustUpdate(modifyData);

			String logInAndOutFlag ="";

			if(updateJson.get("USER_ID")!=null && updateJson.get("USER_ID")!="") {

				session.setAttribute("id", updateJson.get("ID"));
				session.setAttribute("login_id", updateJson.get("USER_ID"));
				session.setAttribute("login_pwd", updateJson.get("USER_PWD"));
				session.setAttribute("login_name", updateJson.get("USER_NAME"));
				session.setAttribute("login_phone", updateJson.get("USER_PHONE"));
				session.setAttribute("login_address", updateJson.get("ADDRESS"));
				session.setAttribute("login_detail_address", updateJson.get("DETAIL_ADDRESS"));
				session.setAttribute("login_zipcode", updateJson.get("ZIPCODE"));
				session.setAttribute("login_email", updateJson.get("USER_EMAIL"));

				logInAndOutFlag = memberService.isLogin(session);
			}

			if("success".equals(logInAndOutFlag)) {

				PrintWriter out = resp.getWriter();
				out.print(updateJson);
				out.flush();
				out.close();
			}

		}
	}
}
