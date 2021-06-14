package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import ufps.edu.co.dto.Rol;
import ufps.edu.co.dao.RolJpaController;
import ufps.edu.co.dto.Conferencista;
import java.util.List;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.dao.UsuarioJpaController;
import ufps.edu.co.dto.Usuario;

public final class registroDocConf_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <div class=\"ufps-navbar ufps-navbar-delete_margin\" id=\"menu\">\n");
      out.write("            <div class=\"ufps-container-fluid\">\n");
      out.write("                <div class=\"ufps-navbar-brand\">\n");
      out.write("                    <div class=\"ufps-btn-menu\" onclick=\"toggleMenu('menu')\">\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"></div>\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                        <div class=\"ufps-btn-menu-bar\"> </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ufps-navbar-right\">\n");
      out.write("                    <a href=\"dashboard.jsp\" class=\"ufps-navbar-btn\">Inicio</a>\n");
      out.write("                    <a href=\"misActividades.jsp\" class=\"ufps-navbar-btn\">Mis Actividades</a>\n");
      out.write("                    <a onclick=\"openDropdown('dropdown4')\"  class=\"ufps-navbar-btn ufps-dropdown-btn\">Hecttor Parra <img class=\"ufps-perfil-redonde\" src=\"img/user.jpg\"/></a>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ufps-dropdown\" id=\"dropdown4\">\n");
      out.write("                    <div class=\"ufps-dropdown-content\">\n");
      out.write("                        <a href=\"#\">Opción 1</a>\n");
      out.write("                        <a href=\"#\">Opción 2</a>\n");
      out.write("                        <a href=\"#\">Opción 3</a>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"ufps-navbar-left\">\n");
      out.write("                    <div class=\"ufps-navbar-corporate\">\n");
      out.write("                        <img  src=\"img/logo_ufps_inverted.png\" alt=\"Logo UFPS\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"ufps-row\" >\n");
      out.write("            <div class=\"ufps-col-mobile-12 ufps-margin-top-10 ufps-col-netbook-9\" >\n");
      out.write("                <div class=\"ufps-section-form\">\n");
      out.write("                    <div class=\"ufps-title-section\">\n");
      out.write("                        Tabla de Docentes y/o Conferencistas Registrados\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"ufps-body-section-80 ufps-padding-5\">\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        ");

                            List<Rol> roles = ((List<Rol>) request.getSession().getAttribute("rjpa"));
                            List<Usuario> users = ((List<Usuario>) request.getSession().getAttribute("ujpa"));;
                            Usuario user = ((Usuario) request.getSession().getAttribute("user"));
                            if (user == null || !user.getIdRol().getRol().equalsIgnoreCase("admin")) {
                                response.sendRedirect("login.jsp");
                            }
                        
      out.write("\n");
      out.write("                        <form action=\"ControlUsuario?q=reg_sol\" method=\"POST\">\n");
      out.write("                            <div class=\"ufps-tooltip ufps-input-line\">\n");
      out.write("                                <span class=\"ufps-tooltip-content-bottom\">Ingresar el email del usuario a registrar</span>\n");
      out.write("                                <input type=\"email\" placeholder=\"some_email@mail.co\" id=\"mail_us\" name=\"mail_us\" class=\"ufps-input-line\">\n");
      out.write("                            </div>\n");
      out.write("                            <select id=\"docOrConf\" name=\"docOrConf\" onchange=\"change_table()\" class=\"ufps-dropdown-btn\">\n");
      out.write("                                ");

                                    for (Rol r : roles) {
                                        if (r.getId() != 1) {
                                
      out.write("\n");
      out.write("                                <option value=");
      out.print(r.getId());
      out.write('>');
      out.print(r.getRol());
      out.write("</option>\n");
      out.write("                                ");

                                        }
                                    }
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                            <input type=\"submit\" class=\"ufps-btn\">\n");
      out.write("                        </form>\n");
      out.write("\n");
      out.write("                            <table id=\"tDoc\" border=\"1\" style=\"width: 100%\" class=\"ufps-margin-top-20  ufps-table ufps-table-responsive ufps-table-inserted ufps-text-center\">\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Nombre</th>\n");
      out.write("                                <th>Codigo</th>\n");
      out.write("                                <th>Estado</th>\n");
      out.write("                                <th>Ver actividades</th>\n");
      out.write("                            </tr>\n");
      out.write("                            ");

                                for (Usuario u : users) {
                                    if (u.getDocente() != null) {
                            
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(u.getNombre() + " " + u.getApellido());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(u.getDocente().getCodigo());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(u.getDocente().getActivo());
      out.write("</td>\n");
      out.write("                                <td><a href=\"ControlActividad?q=list&id_user=");
      out.print(u.getDni());
      out.write("\">Ver actividades</a></td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");

                                    }
                                }
                            
      out.write("\n");
      out.write("                        </table>\n");
      out.write("                        <table id=\"tConf\" border=\"1\" hidden border=\"1\" style=\"width: 100%\" class=\"ufps-margin-top-20  ufps-table ufps-table-responsive ufps-table-inserted ufps-text-center\">\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Nombre</th>\n");
      out.write("                                <th>Afiliación</th>\n");
      out.write("                                <th>Pais</th>\n");
      out.write("                                <th>Tipo</th>\n");
      out.write("                                <th>Ver actividades</th>\n");
      out.write("                            </tr>\n");
      out.write("                            ");

                                for (Usuario u : users) {
                                    Conferencista c = u.getConferencista();
                                    if (c != null) {
                            
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(u.getNombre());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getInstitucionId().getNombre());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getPaisOrigen().getNombre());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getTipoConferencistaId().getTipo());
      out.write("</td>\n");
      out.write("                                <td><a href=\"ControlActividad?q=list&id_user=");
      out.print(u.getDni());
      out.write("\">Ver actividades</a></td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");

                                    }
                                }
                            
      out.write("\n");
      out.write("                        </table>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                change_table();\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            function change_table() {\n");
      out.write("                if ($('#docOrConf').val() == 3) {\n");
      out.write("                    $('#tDoc').hide();\n");
      out.write("                    $('#tConf').show();\n");
      out.write("                } else {\n");
      out.write("                    $('#tDoc').show();\n");
      out.write("                    $('#tConf').hide();\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("        <div class=\"ufps-footer \">\n");
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
