package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"es\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>Software | Login</title>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/main.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <header>\r\n");
      out.write("        <a href=\"index.jsp\"><img class=\"brandlogo\" src=\"img/logo_ufps.png\"/></a>\r\n");
      out.write("        <nav>\r\n");
      out.write("            <li><a href=\"index.jsp\">Inicio</a></li>\r\n");
      out.write("            <li><a href=\"login.jsp\">Iniciar Sesion</a></li>\r\n");
      out.write("            <li><img src=\"img/logo_ingsistemas.png\"/></li>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("    </header>\r\n");
      out.write("    <main> \r\n");
      out.write("        <section class=\"login-usuarios\">  \r\n");
      out.write("            <form class=\"formulario-login\" action=\"ControlUsuario?q=reg\" method=\"POST\">\r\n");
      out.write("                <div class=\"title-form\">\r\n");
      out.write("                    <h1>Inicio de Sesion</h1>\r\n");
      out.write("                </div> \r\n");
      out.write("                <label for=\"doc\">Documento</label>\r\n");
      out.write("                <input type=\"text\" name=\"doc\" id=\"doc\"/>\r\n");
      out.write("                <label for=\"cod\">Codigo</label>\r\n");
      out.write("                <input type=\"text\" name=\"cod\" id=\"cod\"/>\r\n");
      out.write("                <label for=\"pw\">Contraseña</label>\r\n");
      out.write("                <input type=\"password\" name=\"pw\" id=\"pw\"/>\r\n");
      out.write("                <a href=\"recuperar_clave.jsp\" >¿Has olvidado tu contraseña?</a>\r\n");
      out.write("                <button type=\"submit\" >Iniciar Sesion</button>\r\n");
      out.write("            </form>\r\n");
      out.write("        </section>\r\n");
      out.write("\r\n");
      out.write("    </main>\r\n");
      out.write("    <footer>\r\n");
      out.write("        &copy; 2021 | Analisis y Diseño de Sistemas de Información\r\n");
      out.write("    </footer>\r\n");
      out.write("</body>\r\n");
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
