package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"es\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0\">\n");
      out.write("    <title>SISRECA | UFPS</title>\n");
      out.write("    <link href=\"css/ufps.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/main.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    \n");
      out.write("  <div class=\"ufps-navbar\" id=\"menu\">\n");
      out.write("    <div class=\"ufps-container-fluid\">\n");
      out.write("        <div class=\"ufps-navbar-brand\">\n");
      out.write("            <div class=\"ufps-btn-menu\" onclick=\"toggleMenu('menu')\">\n");
      out.write("                <div class=\"ufps-btn-menu-bar\"></div>\n");
      out.write("                <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("           </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"ufps-navbar-right\">\n");
      out.write("            <a href=\"\" class=\"ufps-navbar-btn\">Inicio</a>\n");
      out.write("            <a href=\"login.jsp\" class=\"ufps-navbar-btn\">Inicio Sesion</a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"ufps-navbar-left\">\n");
      out.write("            <div class=\"ufps-navbar-corporate\">\n");
      out.write("                <img src=\"img/logo_ufps_inverted.png\" alt=\"Logo UFPS\">\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("    <div class=\"ufps-container \">\n");
      out.write("      <h1 class=\"ufps-text-center\">ACTIVIDADES</h1>\n");
      out.write("      <div class=\"ufps-row\" >\n");
      out.write("        <div class=\"ufps-col-mobile-12 ufps-col-netbook-6 \" >\n");
      out.write("          <img class=\"ufps-img-responsive\" src=\"img/actividad1.jpg\"/>\n");
      out.write("        </div >\n");
      out.write("        <div class=\"ufps-col-mobile-12 ufps-col-netbook-6 ufps-actividades\" >\n");
      out.write("          <form>\n");
      out.write("            <label>Actividad</label>\n");
      out.write("            <input type=\"text\" placeholder=\"Texto\" class=\"ufps-input-line\" disabled>\n");
      out.write("            <label>Fecha</label>\n");
      out.write("            <input type=\"text\" placeholder=\"Texto\" class=\"ufps-input-line\" disabled>\n");
      out.write("            <label>Hora</label>\n");
      out.write("            <input type=\"text\" placeholder=\"Texto\" class=\"ufps-input-line\" disabled>\n");
      out.write("            <label>Lugar</label>\n");
      out.write("            <input type=\"text\" placeholder=\"Texto\" class=\"ufps-input-line\" disabled>\n");
      out.write("            <label>Organizador</label>\n");
      out.write("            <input type=\"text\" placeholder=\"Texto\" class=\"ufps-input-line\" disabled>\n");
      out.write("        </form>  \n");
      out.write("        </div >\n");
      out.write("      </div >\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"ufps-footer\">\n");
      out.write("    <h3>Universidad Francisco de Paula Santander</h3>\n");
      out.write("    <p>Programa Ingeniería de Sistemas</p>\n");
      out.write("    <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>\n");
      out.write("</div>   \n");
      out.write("\n");
      out.write("    <script src=\"js/ufps.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
