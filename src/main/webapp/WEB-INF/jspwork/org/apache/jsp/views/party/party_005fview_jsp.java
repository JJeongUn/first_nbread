/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.78
 * Generated at: 2023-09-06 13:20:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.party;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.HashMap;
import java.util.ArrayList;
import party.model.vo.Party;
import member.model.vo.Member;
import java.util.ArrayList;
import party.model.vo.Party;
import photo.model.vo.Photo;
import adver.model.vo.Adver;

public final class party_005fview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/views/party/./view_list.jsp", Long.valueOf(1694006397970L));
    _jspx_dependants.put("/views/party/../common/footer.jsp", Long.valueOf(1693893056761L));
    _jspx_dependants.put("/views/party/../common/header.jsp", Long.valueOf(1693993811403L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("adver.model.vo.Adver");
    _jspx_imports_classes.add("member.model.vo.Member");
    _jspx_imports_classes.add("photo.model.vo.Photo");
    _jspx_imports_classes.add("java.util.HashMap");
    _jspx_imports_classes.add("party.model.vo.Party");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
	String pageType = (String) request.getAttribute("type");
    int currentPage = (int) request.getAttribute("currentPage");
    int classfy = (int) request.getAttribute("classfy");
    String keyword = (String) request.getAttribute("keyword");
    String sort = (String) request.getAttribute("sort");
    int listCount = (int) request.getAttribute("listCount");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>party_view</title>\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/jquery-ui.min.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/main.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/party_view.css\"/>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/party_view_click.css\"/>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/comi/resources/js/lib/jquery.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/comi/resources/js/lib/lozad.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/comi/resources/js/util.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        var pageType = ");
      out.print( "\"" + pageType + "\"" );
      out.write(";\r\n");
      out.write("        var listCount = Number(");
      out.print( "\"" + listCount + "\"" );
      out.write(");\r\n");
      out.write("    </script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"/comi/resources/js/party_view.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("//var page = ");
      out.print( "\"" + currentPage + "\"" );
      out.write(";\r\n");
      out.write("var keyword = ");
      out.print( "\"" + ((keyword == null) ? "" : keyword) + "\"" );
      out.write(";\r\n");
      out.write("var sort = ");
      out.print( "\"" + sort + "\"" );
      out.write(";\r\n");
      out.write("console.log('sort : ' + sort);\r\n");
      out.write("var classfy = Number(");
      out.print( "\"" + classfy + "\"" );
      out.write(");\r\n");
      out.write("var limit;\r\n");
      out.write("\r\n");
      out.write("$(function(){\r\n");
      out.write("    $('#selectSort_1 option').each(function(){\r\n");
      out.write("        if($(this).val() == classfy){\r\n");
      out.write("            $(this).prop('selected', true);\r\n");
      out.write("        }    \r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    $('#selectSort_2 option').each(function(){\r\n");
      out.write("        if($(this).val() == sort){\r\n");
      out.write("            $(this).prop('selected', true);\r\n");
      out.write("        }  \r\n");
      out.write("    })\r\n");
      out.write("    \r\n");
      out.write("    $('.search-box-text').on('keyup', function(){\r\n");
      out.write("        if ( event.keyCode == 13 || event.which == 13 ) {\r\n");
      out.write("            search('search', 1, keyword, sort, classfy);\r\n");
      out.write("            console.log('엔터 입력 : ' + keyword);\r\n");
      out.write("        }\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    $('.search-btn').on('click', function(){\r\n");
      out.write("        search('search', 1, keyword, sort_1, sort_2);\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    //정렬 1\r\n");
      out.write("    $('#selectSort_1').on('change', function(){\r\n");
      out.write("        classfy = $(this).val();\r\n");
      out.write("        search('select', 1, keyword, sort, classfy);\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    //정렬 2\r\n");
      out.write("    $('#selectSort_2').on('change', function(){\r\n");
      out.write("        sort = $(this).val();\r\n");
      out.write("        search('select', 1, keyword, sort, classfy);\r\n");
      out.write("    })\r\n");
      out.write("})\r\n");
      out.write("\r\n");
      out.write("function search(type, page, keyword, sort_1, sort_2) {\r\n");
      out.write("    keyword = $('.search-box-text').val();\r\n");
      out.write("    if(type == 'select'){\r\n");
      out.write("        location.href = '/comi/partysall?type=' + ");
      out.print( "\"" + pageType + "\"" );
      out.write(" + '&page=' + page \r\n");
      out.write("                        + '&keyword=' + keyword + '&sort=' + sort + '&classfy=' + classfy;\r\n");
      out.write("    }else{\r\n");
      out.write("        //if(keyword != '') {\r\n");
      out.write("            //검색 시작\r\n");
      out.write("            location.href = '/comi/partysall?type=' + ");
      out.print( "\"" + pageType + "\"" );
      out.write(" + '&page=' + page \r\n");
      out.write("                        + '&keyword=' + keyword + '&sort=' + sort + '&classfy=' + classfy;\r\n");
      out.write("        //}\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Header Section Begin -->\r\n");
      out.write("    <header id=\"header_view\">\r\n");
      out.write("    	");
      out.write('\r');
      out.write('\n');

	String type = (String) request.getAttribute("type");

      out.write('\r');
      out.write('\n');

	//로그인 확인을 위해서 내장된 session 객체를 이용
	Member loginMember = (Member)session.getAttribute("loginMember");

      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>header</title>\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/header.css\"/>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Header Section Begin -->\r\n");
      out.write("    \r\n");
      out.write("    <header class=\"header\">\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <!--메뉴-->\r\n");
      out.write("            <nav class=\"header_menu\">\r\n");
      out.write("                <!--로고-->\r\n");
      out.write("                <div class=\"header_logo left\">\r\n");
      out.write("                    <a href=\"/comi/main.jsp\"><img src=\"/comi/resources/images/logo.png\" alt=\"\"></a>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                <!--메뉴-->\r\n");
      out.write("                <div class=\"header_text center\">\r\n");
      out.write("                    <ul>\r\n");
      out.write("                        <li class=\"");
      out.print( (type != null && type.equals("make")) ? "active" : "" );
      out.write("\"><a href=\"/comi/views/party/party_make.jsp\">공유 해요</a></li>\r\n");
      out.write("                        <li class=\"");
      out.print( (type != null && type.equals("findParty")) ? "active" : "" );
      out.write("\"><a href=\"/comi/partysall?type=findParty\">공유 찾기</a></li>\r\n");
      out.write("                        <li class=\"");
      out.print( (type != null && type.equals("findReview")) ? "active" : "" );
      out.write("\"><a href=\"/comi/partysall?type=findReview\">공유 후기</a></li>\r\n");
      out.write("                        <li class=\"");
      out.print( (type != null && type.equals("qna")) ? "active" : "" );
      out.write("\"><a href=\"/comi/qlist\">Q&A</a></li>\r\n");
      out.write("                        ");
 if (loginMember != null && "Y".equals(loginMember.getMeAdmin())) { 
      out.write("\r\n");
      out.write("                            <li class=\"");
      out.print( (type != null && type.equals("qna")) ? "active" : "" );
      out.write("\"><a href=\"/comi/admin\">Admin Page</a></li>\r\n");
      out.write("                        ");
 } 
      out.write("\r\n");
      out.write("                    </ul>\r\n");
      out.write("                </div>\r\n");
      out.write("				\r\n");
      out.write("                <!--장바구니, 찜-->\r\n");
      out.write("                <div class=\"header_right right\">\r\n");
      out.write("                    <div class=\"header_right_widget\">\r\n");
      out.write("                    	\r\n");
      out.write("                   		<!-- 비로그인 상태 -->\r\n");
      out.write("                    	");
 if(loginMember == null) { 
      out.write("\r\n");
      out.write("                    	 <a><img src=\"/comi/resources/images/launcher.png\" style=\"width: 30px; height: 30px\"></a>\r\n");
      out.write("                        <li><a href=\"/comi/views/member/member_login.html\">로그인</a></li>\r\n");
      out.write("\r\n");
      out.write("                        <!-- 로그인 상태 -->\r\n");
      out.write("                        ");
 } else { 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                        <a href=\"/comi/myinfo?menum=");
      out.print( loginMember.getMeNum() );
      out.write("\">\r\n");
      out.write("                            ");
 if(loginMember.getMePhotoAdd() == null) { 
      out.write("\r\n");
      out.write("                                <img src=\"/comi/resources/images/launcher.png\" style=\"width: 30px; height: 30px\">\r\n");
      out.write("                            ");
 } else { 
      out.write("\r\n");
      out.write("                                <img src=\"/comi/resources/profile_photo_upfiles/");
      out.print( loginMember.getMeNum() );
      out.write('/');
      out.print( loginMember.getMePhotoAdd() );
      out.write("\" style=\"width: 30px; height: 30px\">\r\n");
      out.write("                            ");
 } 
      out.write("\r\n");
      out.write("                        </a>\r\n");
      out.write("                        \r\n");
      out.write("                        <div class=\"myinfo-box\">\r\n");
      out.write("                            <a href=\"/comi/myinfo?menum=");
      out.print( loginMember.getMeNum() );
      out.write('"');
      out.write('>');
      out.print( loginMember.getMeName() );
      out.write("님</a>\r\n");
      out.write("					        <a href=\"/comi/logout\">로그아웃</a> \r\n");
      out.write("                        </div>\r\n");
      out.write("					    ");
 } 
      out.write("\r\n");
      out.write("                        \r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </header>\r\n");
      out.write("    <!-- Header Section End -->\r\n");
      out.write("    \r\n");
      out.write("    <!-- mobile_header 사용안함-->\r\n");
      out.write("    <header class=\"mobile_header\">\r\n");
      out.write("        \r\n");
      out.write("        <nav class=\"mobile_nav\">\r\n");
      out.write("            <div class=\"mobile_nav_top\">\r\n");
      out.write("                <a class=\"home_btn\" href=\"/comi/main.html\">\r\n");
      out.write("                    <img src=\"/comi/resources/images/headerTopIcon_1.png\" />\r\n");
      out.write("                </a>\r\n");
      out.write("            \r\n");
      out.write("                <button class=\"nav_btn\" type=\"button\">\r\n");
      out.write("                    <img src=\"/comi/resources/images/headerTopIcon_2.png\" />\r\n");
      out.write("                </button>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"navbar_box\">\r\n");
      out.write("                <div class=\"nav_item\">\r\n");
      out.write("                    <a class=\"nav_link\" href=\"/comi/views/party/party_make.html\" target=\"_blank\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/headerSubIcon_1.png\" />\r\n");
      out.write("                            <span class=\"nav_link_tex\">공유 해요</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"nav_item\">\r\n");
      out.write("                    <a class=\"nav_link\" href=\"/comi/views/party/party_view.html\" target=\"_blank\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/headerSubIcon_2.png\" />\r\n");
      out.write("                            <span class=\"nav_link_tex\">공유 찾기</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"nav_item\">\r\n");
      out.write("                    <a class=\"nav_link\" href=\"/comi/views/party/party_closed.html\" target=\"_blank\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/headerSubIcon_3.png\" />\r\n");
      out.write("                            <span class=\"nav_link_tex\">공유 후기</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"nav_item\">\r\n");
      out.write("                    <a class=\"nav_link\" href=\"/comi/views/qna/qna.html\" target=\"_blank\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/headerSubIcon_5.png\" />\r\n");
      out.write("                            <span class=\"nav_link_tex\">Q&A</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </header>\r\n");
      out.write("    <!-- mobile_header End -->\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("    </header>\r\n");
      out.write("    <!-- Header Section End -->\r\n");
      out.write("\r\n");
      out.write("    <!-- main -->\r\n");
      out.write("    <main class=\"main_wrapper\">\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"search-container\">\r\n");
      out.write("                <div class=\"search-box\">\r\n");
      out.write("                    <button class=\"search-btn\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/search_btn.png\" class=\"search-image\">\r\n");
      out.write("                    </button>\r\n");
      out.write("                    ");
 if(pageType.equals("findParty")){ 
      out.write("\r\n");
      out.write("                    	<input type=\"text\" placeholder=\"찾고 싶은 공유 모임을 검색해보세요.\" class=\"search-box-text\" value=\"");
      out.print( (keyword == null) ? "" : keyword );
      out.write("\">\r\n");
      out.write("                    ");
 }else{ 
      out.write("\r\n");
      out.write("                    	<input type=\"text\" placeholder=\"예전 공유 모임을 검색해보세요.\" class=\"search-box-text\" value=\"");
      out.print( (keyword == null) ? "" : keyword );
      out.write("\">\r\n");
      out.write("                    ");
 } 
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"search-classify\">\r\n");
      out.write("                \r\n");
      out.write("                    <span class=\"search-classify-title\">분류</span>\r\n");
      out.write("                    <select class=\"search-classify-select\" id=\"selectSort_1\">\r\n");
      out.write("                        <option value=\"-1\">전체</option>\r\n");
      out.write("                        <option value=\"1\">모임</option>\r\n");
      out.write("                        <option value=\"2\">공구</option>\r\n");
      out.write("                        <option value=\"3\"> 렌탈</option>\r\n");
      out.write("                        <option value=\"4\">게임</option>\r\n");
      out.write("                        <option value=\"5\">OTT</option>\r\n");
      out.write("                        <option value=\"6\"> 기타</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("\r\n");
      out.write("                    <select class=\"search-classify-select\" id=\"selectSort_2\">\r\n");
      out.write("                        <!--<option value=\"accuracy\" selected>정확도순</option>-->\r\n");
      out.write("                        <option value=\"current\">최신순</option>\r\n");
      out.write("                        <option value=\"interest\">관심순</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            \r\n");
      out.write("            <div class=\"main_portfolio\" id=\"portf_box\">\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
ArrayList<Party> partyList = (ArrayList<Party>) request.getAttribute("partyList"); 
ArrayList<Photo> photoList = (ArrayList<Photo>) request.getAttribute("photolist");
ArrayList<Adver> advertise = (ArrayList<Adver>) request.getAttribute("advertise");


      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/slick.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/slick-theme.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/party_view.css\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"/comi/resources/js/lib/slick.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/comi/resources/js/util.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	");
 for(Party p : partyList){ 
      out.write('\r');
      out.write('\n');
      out.write('	');
 if(!(p.getPaAct().equals("B"))){
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("	<a class=\"port_box flexBox\"\r\n");
      out.write("		href=\"/comi/partysel?panum=");
      out.print( p.getPaNum() );
      out.write("&act=");
      out.print( p.getPaAct() );
      out.write("\">\r\n");
      out.write("		<div class=\"image featured\">\r\n");
      out.write("			");
 
                Boolean photoBool = false;
                Photo myPh = null;
                for(Photo ph : photoList){
                    if(p.getPhNum() != 0 && p.getPhNum() == ph.getPhotonum()){
                        photoBool = true;
                        myPh = ph;
                        break;
                    }
                } 
            
      out.write("\r\n");
      out.write("\r\n");
      out.write("            ");
  if(photoBool) { 
                    if(myPh != null) { 
      out.write(" <img src=\"/comi/resources/partyfile/");
      out.print( myPh.getPhotonum() );
      out.write('/');
      out.print( myPh.getPhoto1() );
      out.write("\" alt=\"\" />");
 } 
      out.write("\r\n");
      out.write("            ");
  }else{ 
      out.write("\r\n");
      out.write("                    <img src=\"/comi/resources/images/empty.png\" alt=\"\" />\r\n");
      out.write("            ");
  } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("		<div class=\"text_box\">\r\n");
      out.write("			<div class=\"port_box_title\">");
      out.print( p.getPaTitle() );
      out.write("</div>\r\n");
      out.write("			<div class=\"port_box_textbox\">\r\n");
      out.write("				<div class=\"port_box_price\">\r\n");
      out.write("					가격 : <span class=\"price_all\">");
      out.print( p.getPaTotalAmount() );
      out.write("</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"port_box_deposit port_box_text_right\">\r\n");
      out.write("					예치금 : <span class=\"price_deposit\">");
      out.print( p.getPaDeposit() );
      out.write("</span>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"port_box_textbox\">\r\n");
      out.write("				");
if(p.getPaLocation() != null) {
      out.write("\r\n");
      out.write("				<div class=\"port_box_address\">");
      out.print( p.getPaLocation() );
      out.write("</div>\r\n");
      out.write("				");
}else{
      out.write("\r\n");
      out.write("				<div class=\"port_box_address\">모이는 장소가 없어요!</div>\r\n");
      out.write("				");
}
      out.write("\r\n");
      out.write("				<div class=\"port_box_date port_box_text_right\">");
      out.print( p.getPaEnroll() );
      out.write("</div>\r\n");
      out.write("			</div>\r\n");
      out.write("			<div class=\"port_box_textbox\">\r\n");
      out.write("				<div class=\"port_box_text\">\r\n");
      out.write("					인원 모집\r\n");
      out.write("					");
      out.print( p.getPaTotalNum() );
      out.write("명\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"port_box_text port_box_text_right\">\r\n");
      out.write("					인당\r\n");
      out.write("					");
      out.print( p.getPaPerAmount() );
      out.write("원\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("			");
if(p.getPaAct().equals("N")) {
      out.write("\r\n");
      out.write("			<div class=\"party_closed_bottom\">종료된 모임이에요.</div>\r\n");
      out.write("			");
}
      out.write("\r\n");
      out.write("		</div> ");
}
      out.write("\r\n");
      out.write("	</a>\r\n");
      out.write("	");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    ");
 if(advertise != null && advertise.size() > 0) { 
      out.write("\r\n");
      out.write("    ");
 
        ArrayList<String> photoArr = new ArrayList<String>();
    	String pho1 = advertise.get(0).getAdPhoto1();
    	if(pho1 != null) photoArr.add(pho1);
    	String pho2 = advertise.get(0).getAdPhoto2();
    	if(pho2 != null) photoArr.add(pho2);
    	String pho3 = advertise.get(0).getAdPhoto3();
    	if(pho3 != null) photoArr.add(pho3);
    	String pho4 = advertise.get(0).getAdPhoto4();
    	if(pho4 != null) photoArr.add(pho4);
    	String pho5 = advertise.get(0).getAdPhoto5();
    	if(pho5 != null) photoArr.add(pho5);
    	
    
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"port_box flexBox advertise\">\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"image featured\">\r\n");
      out.write("            <!-- 슬라이더 -->\r\n");
      out.write("            <div class=\"slider_view\" id=\"sliderView_1\">\r\n");
      out.write("            	");
 if(photoArr.size() > 0) { 
      out.write("\r\n");
      out.write("	                ");
 for(int i=0; i<photoArr.size(); i++){ 
	                    out.print("advertise : " + advertise);
	                    
      out.write("\r\n");
      out.write("	                    <a href=\"");
      out.print( advertise.get(0).getAdUrl() );
      out.write("\" target=\"_blank\">\r\n");
      out.write("	                        <div class=\"slide_box\">\r\n");
      out.write("	                        	<img src=\"/comi/resources/adverfile/");
      out.print( advertise.get(0).getAdPhNum() );
      out.write('/');
      out.print( photoArr.get(i) );
      out.write("\" alt=\"\" />\r\n");
      out.write("	                        </div>\r\n");
      out.write("	                    </a>\r\n");
      out.write("	                ");
 } 
      out.write("\r\n");
      out.write("	            ");
 }else{ 
      out.write("\r\n");
      out.write("	            		<a href=\"");
      out.print( advertise.get(0).getAdUrl() );
      out.write("\" target=\"_blank\">\r\n");
      out.write("	                        <div class=\"slide_box\">\r\n");
      out.write("	                            <img src=\"/comi/resources/images/empty.png\" alt=\"\" />\r\n");
      out.write("	                        </div>\r\n");
      out.write("	                    </a>\r\n");
      out.write("	           	");
 } 
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        <script type=\"text/javascript\">new Util().slide($('.slider_view'));</script>\r\n");
      out.write("        </div>\r\n");
      out.write("        <a href=\"");
      out.print( advertise.get(0).getAdUrl() );
      out.write("\" target=\"_blank\">\r\n");
      out.write("            <div class=\"text_box\">\r\n");
      out.write("                ");
      out.print( advertise.get(0).getAdTitle() );
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </a>\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
 } 
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </main>\r\n");
      out.write("    \r\n");
      out.write("    <!-- Footer Section Begin -->\r\n");
      out.write("    <footer id=\"footer_view\">\r\n");
      out.write("    	");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>footer</title>\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/footer.css\"/>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Footer Section Begin -->\r\n");
      out.write("    <footer class=\"footer\">\r\n");
      out.write("        <div class=\"container side_margin_zero\">\r\n");
      out.write("            <div class=\"footer_section_box\">\r\n");
      out.write("                <div class=\"footer_section\">\r\n");
      out.write("                    <div class=\"footer_div\">\r\n");
      out.write("                        <img src=\"/comi/resources/images/logo.png\" class=\"footer_logo\">\r\n");
      out.write("                        <div class=\"footer_right\">\r\n");
      out.write("                            <div class=\"footer_right_sub\">\r\n");
      out.write("                                <label class=\"footer_right_sub_label\">서비스</label>\r\n");
      out.write("                                <div class=\"footer_right_subsub\">\r\n");
      out.write("                                    <span class=\"footer_right_sub_text\"><a href=\"/comi/noticesall\">공지사항</a></span>\r\n");
      out.write("                                    <span class=\"footer_right_sub_text\">고객센터</span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"footer_right_sub\">\r\n");
      out.write("                                <label class=\"footer_right_sub_label\">문의</label>\r\n");
      out.write("                                <div class=\"footer_right_subsub\">\r\n");
      out.write("                                    <span class=\"footer_right_sub_text\"><a href=\"/comi/blist\">사업 제휴</a></span>\r\n");
      out.write("                                    <span class=\"footer_right_sub_text\"><a href=\"/comi/adsall\">광고 문의</a></span>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"footer_bottom_1\">\r\n");
      out.write("                        <label class=\"footer_text_2\"><b>(주)십시일반</b></label>\r\n");
      out.write("                        <div class=\"\">\r\n");
      out.write("                            <p class=\"footer_text_2\">\r\n");
      out.write("                                <span>사업자 등록번호 : 없음</span> |<span> 팀장 : 김정선 </span>| 팀원 : 전성훤, 이재민, 나정운, 서진우 | <br>서울특별시 서초구 서초대로77길 41, 4층 (서초동, 대동Ⅱ)\r\n");
      out.write("                            </p>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"footer_bottom_2\">\r\n");
      out.write("                        <span class=\"footer_text_1\">서비스 이용약관</span>\r\n");
      out.write("                        <span class=\"footer_text_1\">개인정보 처리방침</span>\r\n");
      out.write("                        <span class=\"footer_text_1 footer_copyright_text\">Copyright &copy; <script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class=\"fa fa-heart\" aria-hidden=\"true\"></i> by <a href=\"#\" target=\"_blank\" class=\"team_text\">Comi</a></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </footer>\r\n");
      out.write("    <!-- Footer Section End -->\r\n");
      out.write("\r\n");
      out.write("    <!-- 상담 -->\r\n");
      out.write("    <div>\r\n");
      out.write("        <div class=\"launcher_character\">\r\n");
      out.write("            <button id=\"launcher_btn\" type=\"button\" class=\"\">\r\n");
      out.write("                <span class=\"text_hidden\">상담 버튼</span>\r\n");
      out.write("                <img alt=\"\" src=\"/comi/resources/images/launcher.png\" width=\"64\" height=\"64\" class=\"character_img\">\r\n");
      out.write("                <div class=\"hidden_message\">\r\n");
      out.write("                    <span class=\"\">0개의 안읽은 메시지</span>\r\n");
      out.write("                </div>\r\n");
      out.write("            </button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- 상담 End -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("    </footer>\r\n");
      out.write("    <!-- Footer Section End -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}