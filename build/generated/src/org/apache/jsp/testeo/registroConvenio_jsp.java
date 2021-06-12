package org.apache.jsp.testeo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;

public final class registroConvenio_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>JSP Page</title>\r\n");
      out.write("        <script src=\"../js/JQuery.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1>Opciones para convenio</h1>\r\n");
      out.write("        <h2>Registrar</h2>\r\n");
      out.write("        <form action=\"../ControlConvenio?q=reg\" method=\"POST\">\r\n");
      out.write("            <label>Numero</label>\r\n");
      out.write("            <input type=\"text\" name=\"num\"><br>\r\n");
      out.write("            <label>Fecha</label>\r\n");
      out.write("            <input type=\"Date\" name=\"fe_in\"><br\r\n");
      out.write("            <label>Vigencia</label>\r\n");
      out.write("            <input type=\"Date\" name=\"fe_out\"><br\r\n");
      out.write("            <label>Descripción</label>\r\n");
      out.write("            <textarea name=\"descr\"></textarea><br>\r\n");
      out.write("            <label>Razón del convenio</label>\r\n");
      out.write("            <textarea name=\"razon\"></textarea><br>\r\n");
      out.write("            \r\n");
      out.write("            <input type=\"checkbox\" id=\"ins_exis\" name=\"ins_exis\" onchange=\"exist_ins()\">Institucion registrada<br>\r\n");
      out.write("            \r\n");
      out.write("            <div id=\"exist\" hidden>\r\n");
      out.write("                <label>Institución</label>\r\n");
      out.write("                <select id=\"ins_exi\" name=\"ins_exi\">\r\n");
      out.write("                </select>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"non_exist\">\r\n");
      out.write("                <label>Institución</label>\r\n");
      out.write("                <input type=\"text\" id=\"ins_non\" name=\"ins_non\">\r\n");
      out.write("                <label>Pais</label>\r\n");
      out.write("                <select id=\"pai\" name=\"pai\">\r\n");
      out.write("                </select>\r\n");
      out.write("            </div>\r\n");
      out.write("            <label>Tipo de convenio</label>\r\n");
      out.write("            <select id=\"tp_con\" name=\"tp_con\">\r\n");
      out.write("            </select>\r\n");
      out.write("            \r\n");
      out.write("            <input type=\"submit\">\r\n");
      out.write("        </form>\r\n");
      out.write("        \r\n");
      out.write("        <script>\r\n");
      out.write("            $(document).ready(function (){\r\n");
      out.write("                load('../ControlInstitucion?q=list', '#ins_exi');\r\n");
      out.write("                load('../ControlPaises?q=list', '#pai');\r\n");
      out.write("                load('../ControlConvenio?q=list_type', '#tp_con');\r\n");
      out.write("            });\r\n");
      out.write("            \r\n");
      out.write("            function load(control, compnt){\r\n");
      out.write("                $.post(control, {}, function(response){\r\n");
      out.write("                    $(compnt).html(response);\r\n");
      out.write("                });\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            function exist_ins(){\r\n");
      out.write("                if($('#ins_exis').is(':checked')){\r\n");
      out.write("                    $('#non_exist').hide();\r\n");
      out.write("                    $('#exist').show();\r\n");
      out.write("                }else{\r\n");
      out.write("                    $('#non_exist').show();\r\n");
      out.write("                    $('#exist').hide();\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        </script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
