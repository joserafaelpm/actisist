package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class verActividades_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <link href=\"css/ufps.min.css\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\" integrity=\"sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p\" crossorigin=\"anonymous\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/jquery.transfer.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"icon_font/css/icon_font.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/main.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
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
      out.write("                    <a href=\"index.jsp\" class=\"ufps-navbar-btn\">Inicio</a>\n");
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
      out.write("        <div class=\"ufps-container-fluid \">\n");
      out.write("\n");
      out.write("            <div class=\"ufps-row ufps-margin-top-10\" >\n");
      out.write("                <div class=\"ufps-col-mobile-12 ufps-col-netbook-3\" > \n");
      out.write("                    <div class=\"ufps-card\">\n");
      out.write("                        <div class=\"ufps-card-caption ufps-padding-30\">\n");
      out.write("                            <div class=\"ufps-div-title\">\n");
      out.write("                                <label>Buscar</label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"label\"><label class=\"ufps-title-input\">Nombre</label></div>\n");
      out.write("                            <input type=\"text\"  name=\"nombre\" id=\"nombreData\" class=\"ufps-input-line\" required>\n");
      out.write("                            <div class=\"label\"><label class=\"ufps-title-input\">Fecha</label></div>\n");
      out.write("                            <input type=\"date\"  name=\"fecha\" id=\"fechaData\"  value=\"2017-06-01\" class=\"ufps-input-line\" required>\n");
      out.write("                            <div class=\"label\"><label class=\"ufps-title-input\">Hora</label></div>\n");
      out.write("                            <input type=\"text\"  name=\"hora\" id=\"horaData\" class=\"ufps-input-line\" required>\n");
      out.write("                            <div class=\"label\"><label class=\"ufps-title-input\">Lugar</label></div>\n");
      out.write("                            <input type=\"text\"  name=\"lugar\" id=\"lugarData\" class=\"ufps-input-line\" required>\n");
      out.write("                             <div class=\"label\"><label class=\"ufps-title-input\">Docente</label></div>\n");
      out.write("                            <input type=\"text\"  name=\"docente\" id=\"docenteData\" class=\"ufps-input-line\" required>\n");
      out.write("                            <a href=\"registrarActividad.jsp\"  class=\"ufps-tx-center ufps-btn ufps-width-100 ufps-margin-top-10\">Registrar Actividad</a>\n");
      out.write("                            <a href=\"#\" class=\"ufps-btn ufps-width-100 ufps-margin-top-10 ufps-tx-center\">Generar Informe</a>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>   \n");
      out.write("                </div >\n");
      out.write("                <div class=\"ufps-col-mobile-12 ufps-col-netbook-9\" >\n");
      out.write("                    <div class=\"ufps-card\">\n");
      out.write("                        <div class=\"ufps-card-caption ufps-padding-5\">\n");
      out.write("                            <div class=\"ufps-table-responsive\">\n");
      out.write("                                <table id=\"example\" class=\"display\" style=\"width:100%\">\n");
      out.write("                                    <thead>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <th></th>\n");
      out.write("                                            <th>Nombre</th>\n");
      out.write("                                            <th>Fecha</th>\n");
      out.write("                                            <th>Hora</th>\n");
      out.write("                                            <th>Lugar</th>\n");
      out.write("                                            <th>Docente Encargado</th>\n");
      out.write("                                            <th>Acciones</th>\n");
      out.write("                                        </tr>\n");
      out.write("                                    </thead>\n");
      out.write("                                    <tbody>\n");
      out.write("                                        <tr>\n");
      out.write("                                            <td style=\"text-align: center;\"><input type=\"checkbox\"></td>\n");
      out.write("                                            <td style=\"text-align: center;\">Activida 1</td>\n");
      out.write("                                            <td style=\"text-align: center;\">2021-06-02</td>\n");
      out.write("                                            <td style=\"text-align: center;\">09:00 a.m.</td>\n");
      out.write("                                            <td style=\"text-align: center;\">Cúcuta</td>\n");
      out.write("                                            <td style=\"text-align: center;\">Hector Parra</td>\n");
      out.write("                                            <td style=\"text-align: center;\"><a href=\"#\"><i class=\"fa fa-edit\"></i></a><a href=\"#\"><i class=\"fa fa-times\"></i></a></td>\n");
      out.write("\n");
      out.write("                                        </tr>    \n");
      out.write("                                    </tbody>\n");
      out.write("                                </table>\n");
      out.write("                            </div>  \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div >\n");
      out.write("            </div >\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"ufps-footer\">\n");
      out.write("            <h3>Universidad Francisco de Paula Santander</h3>\n");
      out.write("            <p>Programa Ingeniería de Sistemas</p>\n");
      out.write("            <p>&copy; 2021 | Analisis y Diseño de Sistemas de Información</p>\n");
      out.write("        </div>  \n");
      out.write("        <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\" integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\" crossorigin=\"anonymous\"></script>\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js\"></script>\n");
      out.write("        <script src=\"js/dataTables.min.js\"></script>\n");
      out.write("        <script src=\"js/ufps.min.js\"></script>\n");
      out.write("        <script src=\"js/main.js\"></script>\n");
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
