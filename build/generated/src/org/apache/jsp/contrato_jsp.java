package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contrato_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta charset=\"ISO-8859-1\">\n");
      out.write("        <meta name=\"viewport\"\n");
      out.write("              content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("        <link rel=\"stylesheet\"\n");
      out.write("              href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n");
      out.write("        <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n");
      out.write("        <title>Contrato</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <div class=\"ufps-navbar\" id=\"menu\">\n");
      out.write("         <div class=\"ufps-container-fluid\">\n");
      out.write("             <div class=\"ufps-navbar-brand\">\n");
      out.write("                 <div class=\"ufps-btn-menu\" onclick=\"toggleMenu('menu')\">\n");
      out.write("                     <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                     <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                     <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                </div>\n");
      out.write("                Navbar\n");
      out.write("             </div>\n");
      out.write("             <div class=\"ufps-navbar-left\">\n");
      out.write("                 <a href=\"\" class=\"ufps-navbar-btn\">Boton 1 </a>\n");
      out.write("                 <a href=\"\" class=\"ufps-navbar-btn\">Boton 2 </a>\n");
      out.write("             </div>\n");
      out.write("             <div class=\"ufps-navbar-right\">\n");
      out.write("                 <div class=\"ufps-navbar-corporate\">\n");
      out.write("                     <img src=\"img/logo_ingsistemas.png\" alt=\"Logo ingeniería de sistemas\">\n");
      out.write("                     <img src=\"img/logo_ufps_inverted.png\" alt=\"Logo UFPS\">\n");
      out.write("                 </div>\n");
      out.write("             </div>\n");
      out.write("         </div>\n");
      out.write(" </div>\n");
      out.write("         \n");
      out.write("         \n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
