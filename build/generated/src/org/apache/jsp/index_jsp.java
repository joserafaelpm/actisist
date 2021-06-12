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
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Software | Login</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/main.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <a href=\"index.html\"><img class=\"brandlogo\" src=\"img/logo_ufps.png\"/></a>\n");
      out.write("        <nav>\n");
      out.write("            <li><a href=\"\">Inicio</a></li>\n");
      out.write("            <li><a href=\"login.html\">Iniciar Sesion</a></li>\n");
      out.write("            <li><img src=\"img/logo_ingsistemas.png\"/></li>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("    </header>\n");
      out.write("    <main> \n");
      out.write("        <section class=\"login-usuarios\">  \n");
      out.write("            <form action=\"nueva_clave.html\" class=\"formulario-login\">\n");
      out.write("                <div class=\"title-form mb-25\">\n");
      out.write("                    <h1>Recupero Contraseña</h1>\n");
      out.write("                </div> \n");
      out.write("                <label>Nueva contraseña</label>\n");
      out.write("                <input type=\"password\" />\n");
      out.write("                <label>Confirmar contraseña</label>\n");
      out.write("                <input type=\"password\" class=\"mb-25\" />\n");
      out.write("                <button type=\"submit\" >Enviar</button>\n");
      out.write("            </form>\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("    </main>\n");
      out.write("    <footer>\n");
      out.write("        &copy; 2021 | Analisis y Diseño de Sistemas de Información\n");
      out.write("    </footer>\n");
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
