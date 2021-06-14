package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.dao.TipoConferencistaJpaController;
import ufps.edu.co.dto.Tipo;
import ufps.edu.co.dto.TipoConferencista;
import java.util.List;
import ufps.edu.co.dto.TipoDocente;
import ufps.edu.co.dto.SolicitudRegistro;

public final class registrarDocConf_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <script src=\"js/JQuery.js\"></script>\n");
      out.write("        <link href=\"css/ufps.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\" integrity=\"sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p\" crossorigin=\"anonymous\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/main.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"ufps-navbar\" id=\"menu\">\n");
      out.write("            <div class=\"ufps-container-fluid\">\n");
      out.write("                <div class=\"ufps-navbar-brand\">\n");
      out.write("                    <div class=\"ufps-btn-menu\" onclick=\"toggleMenu('menu')\">\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"></div>\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ufps-navbar-right\">\n");
      out.write("                    <a href=\"index.html\" class=\"ufps-navbar-btn\">Inicio</a>\n");
      out.write("                    <a href=\"login.html\" class=\"ufps-navbar-btn\">Inicio Sesion</a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ufps-navbar-left\">\n");
      out.write("                    <div class=\"ufps-navbar-corporate\">\n");
      out.write("                        <img src=\"img/logo_ufps_inverted.png\" alt=\"Logo UFPS\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"ufps-container-fluid\">\n");
      out.write("                    <div class=\"ufps-section-form\">\n");
      out.write("                        <div class=\"ufps-title-section\">\n");
      out.write("                            Registrar Docente\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"ufps-body-section ufps-padding-5\">\n");
      out.write("\n");
      out.write("                            ");

                                //SolicitudRegistro sr = ((SolicitudRegistro)request.getSession().getAttribute("sol"));
                                SolicitudRegistro sr = new SolicitudRegistro();
                                sr.setTypeUs(new Rol(1, "admin"));
                                request.getSession().setAttribute("types", new TipoConferencistaJpaController(Conexion.getConexion().getBd()).findTipoConferencistaEntities());
                                if (sr == null) {
                                    //  response.sendRedirect("index.jsp");
                                }
                            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                            <form action=\"ControlUsuario?q=reg");
      out.print(sr.getTypeUs().getRol());
      out.write("\" method=\"POST\" enctype=\"multipart/form-data\">\n");
      out.write("                                <div class=\"label\"><label class=\"ufps-title-input\" for=\"name\">Nombre</label></div>\n");
      out.write("                                <input type=\"text\" id=\"nam\" name=\"nam\" class=\"ufps-input-line\" required>\n");
      out.write("                                <div class=\"label\"><label class=\"ufps-title-input\" for=\"ape\">Apellidos</label></div>\n");
      out.write("                                <input type=\"text\"  id=\"ape\" name=\"ape\" class=\"ufps-input-line\" required>\n");
      out.write("                                <div class=\"label\"><label class=\"ufps-title-input\" for=\"doc\">Documento</label></div>\n");
      out.write("                                <input type=\"text\"   id=\"doc\" name=\"doc\" class=\"ufps-input-line\" required>\n");
      out.write("                                ");
if (sr.getTypeUs().getId() == 2) {
      out.write("\n");
      out.write("                                <div class=\"label\"><label class=\"ufps-title-input\" for=\"cod\">Codigo</label></div>\n");
      out.write("                                <input type=\"text\"  id=\"cod\" name=\"cod\" class=\"ufps-input-line\" required>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("\n");
      out.write("                                <div class=\"label\"><label class=\"ufps-title-input\">Tipo</label></div>\n");
      out.write("                                <select class=\"ufps-input-line\" id=\"types\" name=\"types\">\n");
      out.write("                                    ");

                                        List<Tipo> types = ((List<Tipo>) request.getSession().getAttribute("types"));
                                        for (Tipo tp : types) {
                                    
      out.write("\n");
      out.write("                                    <option value=\"");
      out.print(tp.getId());
      out.write('"');
      out.write('>');
      out.print(tp.getTipo());
      out.write("</option>\n");
      out.write("                                    ");

                                        }
                                    
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                                ");
if (sr.getTypeUs().getId() == 2) {
      out.write("\n");
      out.write("                                <label class=\"ufps-title-input\" for=\"pw\">Contraseña</label>\n");
      out.write("                                <input type=\"password\" class=\"ufps-input-line\" id=\"pw\" name=\"pw\" ><br>\n");
      out.write("                                <label class=\"ufps-title-input\" for=\"image\">Imagen de perfil</label>\n");
      out.write("                                <input type=\"file\" class=\"ufps-input-line\" name=\"image\" id=\"image\">\n");
      out.write("                                ");
} else {
      out.write("\n");
      out.write("                                <label class=\"ufps-title-input\">Afiliación</label>\n");
      out.write("                                <select class=\"ufps-input-line\" id=\"ins_exi\" name=\"ins_exi\">\n");
      out.write("                                </select>\n");
      out.write("                                <label class=\"ufps-title-input\">País de origen</label>\n");
      out.write("                                <select class=\"ufps-input-line\" id=\"pai\" name=\"pai\">\n");
      out.write("                                </select>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </form>\n");
      out.write("                            <div class=\"label\"><label class=\"ufps-title-input\">Estado</label></div>\n");
      out.write("                            <input type=\"checkbox\"  name=\"nombre\" id=\"nombreData\" class=\"ufps-input-line\" required>\n");
      out.write("                            <input type=\"submit\"  class=\"ufps-btn ufps-width-100 ufps-margin-top-10\" value=\"Registrar\">\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div >\n");
      out.write("            </div >\n");
      out.write("        </div>\n");
      out.write("                            \n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                load('ControlInstitucion?q=list', '#ins_exi');\n");
      out.write("                load('ControlPaises?q=list', '#pai');\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            function load(control, compnt) {\n");
      out.write("                $.post(control, {}, function (response) {\n");
      out.write("                    $(compnt).html(response);\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        <div class=\"ufps-footer\">\n");
      out.write("            <h3>Universidad Francisco de Paula Santander</h3>\n");
      out.write("            <p>Programa Ingeniería de Sistemas</p>\n");
      out.write("            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>\n");
      out.write("        </div> \n");
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
