/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.78
 * Generated at: 2023-08-25 07:22:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.party;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.simple.JSONObject;
import member.model.vo.Member;
import party.model.vo.Party;

public final class party_005fview_005fclick_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("org.json.simple.JSONObject");
    _jspx_imports_classes.add("member.model.vo.Member");
    _jspx_imports_classes.add("party.model.vo.Party");
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
 
	Party party = (Party) request.getAttribute("party");
	Member member = (Member) request.getAttribute("member");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("	<meta charset=\"UTF-8\">\r\n");
      out.write("	<title>party_view</title>\r\n");
      out.write("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/slick.min.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/slick-theme.min.css\">\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/lib/jquery-ui.min.css\"/>\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/main.css\"/>\r\n");
      out.write("	<link rel=\"stylesheet\" type=\"text/css\" href=\"/comi/resources/css/party_view_click.css\"/>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/lib/jquery.min.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/lib/slick.min.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/lib/axios.min.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/common.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/clickView.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/makeParty.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/setParty.js\"></script>\r\n");
      out.write("	<script type=\"text/javascript\" src=\"/comi/resources/js/party_view_click.js\"></script>\r\n");
      out.write("	\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <!-- Header Section Begin -->\r\n");
      out.write("    <header id=\"header_view\">    \r\n");
      out.write("    </header>\r\n");
      out.write("    <!-- Header Section End -->\r\n");
      out.write("\r\n");
      out.write("	<!-- main -->\r\n");
      out.write("	<main class=\"main_wrapper\">\r\n");
      out.write("		<div class=\"container side_margin_zero\">\r\n");
      out.write("\r\n");
      out.write("			<!-- 슬라이더 -->\r\n");
      out.write("			<div class=\"main_slider\">\r\n");
      out.write("				<div id=\"slider_main\">\r\n");
      out.write("					\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("\r\n");
      out.write("		<div class=\"container\">\r\n");
      out.write("			<!-- 프로필 -->\r\n");
      out.write("			<section id=\"article-profile\">\r\n");
      out.write("				<div class=\"article-profile-box\">\r\n");
      out.write("					<a class=\"article-profile-link\" href=\"#\">\r\n");
      out.write("						<div class=\"space-between\">\r\n");
      out.write("							<div class=\"display-align-items-center\">\r\n");
      out.write("								<div id=\"article-profile-image-box\">\r\n");
      out.write("									<img src=\"\" />\r\n");
      out.write("								</div>\r\n");
      out.write("								<div id=\"article-profile-left\">\r\n");
      out.write("									<div id=\"article-nickname\">");
      out.print(  );
      out.write("</div>\r\n");
      out.write("									<div id=\"article-region-name\"></div>\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("							<div class=\"article-profile-right\">\r\n");
      out.write("								<div class=\"temperature-wrap\">\r\n");
      out.write("									<span>신뢰도</span>\r\n");
      out.write("									<span class=\"text-color text-color-03\" id=\"text-color-id\"></span>\r\n");
      out.write("								</div>\r\n");
      out.write("								<div class=\"meters\">\r\n");
      out.write("									<div class=\"bar bar-color-03\" id=\"bar-color-id\"></div>\r\n");
      out.write("								</div>\r\n");
      out.write("								<div class=\"temperature-face\">\r\n");
      out.write("									<img id=\"temperature-face-id\" src=\"/comi/resources/images/launcher.png\" />\r\n");
      out.write("								</div>\r\n");
      out.write("							</div>\r\n");
      out.write("						</div>\r\n");
      out.write("					</a>\r\n");
      out.write("				</div>\r\n");
      out.write("			</section>\r\n");
      out.write("			<!-- 프로필 end-->\r\n");
      out.write("			\r\n");
      out.write("			<!-- 게시글 -->\r\n");
      out.write("			<section id=\"article-description\">\r\n");
      out.write("				<h1 id=\"article-title\"></h1>\r\n");
      out.write("				<p id=\"article-category\">\r\n");
      out.write("				  모임 ∙\r\n");
      out.write("				  <span id=\"article-befordate\">\r\n");
      out.write("					\r\n");
      out.write("				  </span>\r\n");
      out.write("				</p>\r\n");
      out.write("				<p id=\"article-location\">\r\n");
      out.write("\r\n");
      out.write("				</p>\r\n");
      out.write("				<div id=\"article-price-box\">\r\n");
      out.write("					\r\n");
      out.write("					<div id=\"article-deposit\"></div>\r\n");
      out.write("					<div id=\"article-bar-box\">\r\n");
      out.write("						<span id=\"article-price-bar\"></span>\r\n");
      out.write("						<span id=\"article-deposit-bar\"></span>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div id=\"article-price\"></div>\r\n");
      out.write("\r\n");
      out.write("					<div class=\"article-people-box\">\r\n");
      out.write("						<div id=\"article-people\"></div>\r\n");
      out.write("						<div id=\"article-people-price\"></div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div id=\"article-detail\">\r\n");
      out.write("					<p>\r\n");
      out.write("						\r\n");
      out.write("					</p>\r\n");
      out.write("				</div>\r\n");
      out.write("				<p id=\"article-counts\">\r\n");
      out.write("					\r\n");
      out.write("				</p>\r\n");
      out.write("			</section>\r\n");
      out.write("			<!-- 게시글 end-->\r\n");
      out.write("\r\n");
      out.write("			<!-- 채팅 리뷰-->\r\n");
      out.write("			<section id=\"chating\">\r\n");
      out.write("				<!--채팅 헤드-->\r\n");
      out.write("				<div class=\"chating-head\">\r\n");
      out.write("					<span class=\"chating-head-title\">엔브레드톡</span>\r\n");
      out.write("				</div>\r\n");
      out.write("				<!--채팅 헤드 end-->\r\n");
      out.write("				\r\n");
      out.write("				<!--채팅 바디-->\r\n");
      out.write("				<div class=\"chating-body\">\r\n");
      out.write("					<div class=\"chating-body-box\" id=\"chating_box\">\r\n");
      out.write("\r\n");
      out.write("						\r\n");
      out.write("\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				<!--채팅 바디end-->\r\n");
      out.write("\r\n");
      out.write("				<!--채팅 입력-->\r\n");
      out.write("				<div class=\"chating-input\" id=\"chating-input-talk\">\r\n");
      out.write("					<div class=\"chating-input-area\">\r\n");
      out.write("						<textarea placeholder=\"엔브레드톡에 참여해보세요\" class=\"chating-input-area-write\"></textarea> \r\n");
      out.write("						<button type=\"button\" class=\"chating-post-stickerbtn\">\r\n");
      out.write("							<img class=\"chating-post-sendbtn-image\" src=\"/comi/resources/images/stickerbtn.png\">\r\n");
      out.write("						</button>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"chating-post-area\">\r\n");
      out.write("						<button type=\"button\" class=\"chating-post-sendbtn\">\r\n");
      out.write("							<img class=\"chating-post-sendbtn-image\" src=\"/comi/resources/images/sendbtn.png\">\r\n");
      out.write("						</button>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<!--채팅 입력 end-->\r\n");
      out.write("			</section>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("			<!--참여 버튼 버튼을 클릭해야 채팅을 할 수 있음 - 참여하지 않으면 채팅기록만 볼수 있음-->\r\n");
      out.write("			<section class=\"chating-allow\">\r\n");
      out.write("				<button class=\"chating-allow-btn\">참여</button>\r\n");
      out.write("			</section>\r\n");
      out.write("			<!--참여 버튼 end-->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("			<!-- 공유 모임 더 보기 -->\r\n");
      out.write("			<section class=\"article-party-share\">\r\n");
      out.write("				<div class=\"article-party-share-box\">\r\n");
      out.write("					<div class=\"article-party-share-title\">인기공유모임</div>\r\n");
      out.write("					<a id=\"article-party-share-re\" href=\"/comi/partysall?type=findParty\">더보기</a>\r\n");
      out.write("				</div>\r\n");
      out.write("\r\n");
      out.write("				<div class=\"main_portfolio\" id=\"portf_box\">\r\n");
      out.write("					\r\n");
      out.write("				</div>\r\n");
      out.write("			</section>\r\n");
      out.write("			<!-- 공유 모임 더 보기 end-->\r\n");
      out.write("\r\n");
      out.write("		</div>\r\n");
      out.write("	</main>\r\n");
      out.write("	\r\n");
      out.write("\r\n");
      out.write("	<!-- Footer Section Begin -->\r\n");
      out.write("	<footer id=\"footer_view\">	\r\n");
      out.write("	</footer>\r\n");
      out.write("	<!-- Footer Section End -->\r\n");
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
