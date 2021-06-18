/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.edu.co.negocio;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import ufps.edu.co.dto.Actividad;
import ufps.edu.co.dto.ConferencistaActividad;
import ufps.edu.co.dto.ConvenioActividad;
import ufps.edu.co.util.Conexion;

/**
 *
 * @author dunke
 */
public class InformePDF {
    private List<Actividad> acts;
    private String path;
    private BaseFont bf;
    private Document doc;
    
    public InformePDF(String path, List<Actividad> acts) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        EntityManagerFactory em = Conexion.getConexion().getBd();
        
        this.acts = acts;
        this.path = path;
        this.path = new File(this.path).getParentFile().getParentFile().getAbsolutePath();
        this.bf = BaseFont.createFont(this.path + "\\fonts\\Roboto-Medium.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        
        TableHeader th = new TableHeader(this.getTablaEnca());
        
        this.doc = new Document(PageSize.A4, 36, 36, 20 + th.getTableHeight(), 36);
        
        PdfWriter.getInstance(this.doc, new FileOutputStream(this.path + "\\temp\\InformeActividades.pdf")).setPageEvent(th);
    }
    
    public void createPDF() throws DocumentException, BadElementException, IOException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        this.doc.open();
        
        this.createBlank();
        this.doc.add(this.getParapgraph("Informe de actividades", 10, Paragraph.ALIGN_CENTER));
        this.createBlank();
        this.createBlank();
        
        for(Actividad act: this.acts){
            this.doc.add(this.getParapgraph("Nombre de la actividad: "+act.getNombre(), 10, Paragraph.ALIGN_LEFT));
            Image i = Image.getInstance(act.getImagen());
            i.scaleAbsoluteHeight(100);
            i.scaleAbsoluteWidth(100);
            i.setAlignment(1);
            this.doc.add(i);
            this.doc.add(this.getParapgraph("Fecha inicio: "+sd.format(act.getFechaInicio()), 10, Paragraph.ALIGN_LEFT));
            this.doc.add(this.getParapgraph("Fecha fin: "+sd.format(act.getFechaInicio()), 10, Paragraph.ALIGN_LEFT));
            this.doc.add(this.getParapgraph("Tipo de actividad: "+act.getTipoActividadId().getTipo(), 10, Paragraph.ALIGN_LEFT));
            this.doc.add(this.getParapgraph("Tipo movilidad: "+act.getTipoMovilidadId().getTipo(), 10, Paragraph.ALIGN_LEFT));
            this.doc.add(this.getParapgraph("Tematica:\n"+act.getTematica(), 10, Paragraph.ALIGN_JUSTIFIED));
            this.createBlank();
            this.doc.add(this.getParapgraph("Descripción:\n"+act.getDescripcion(), 10, Paragraph.ALIGN_JUSTIFIED));
            this.createBlank();
            this.doc.add(this.getParapgraph("Lugar:\n"+act.getLugar(), 10, Paragraph.ALIGN_LEFT));
            this.createBlank();
            this.doc.add(this.getParapgraph("Conferencistas:\n", 10, Paragraph.ALIGN_LEFT));
            PdfPTable p = new PdfPTable(4);
            p.setWidthPercentage(100);
            p.addCell(this.getParapgraph("Nombre", 11, Paragraph.ALIGN_CENTER));
            p.addCell(this.getParapgraph("Afiliación", 11, Paragraph.ALIGN_CENTER));
            p.addCell(this.getParapgraph("País", 11, Paragraph.ALIGN_CENTER));
            p.addCell(this.getParapgraph("Tipo", 11, Paragraph.ALIGN_CENTER));
            for(ConferencistaActividad ca: act.getConferencistaActividadList()){
                p.addCell(this.getParapgraph(ca.getUsuarioDni().getNombre()+" "+ca.getUsuarioDni().getApellido(), 10, Paragraph.ALIGN_LEFT));
                if(ca.getUsuarioDni().getIdRol().getId() == 2){
                    p.addCell(this.getParapgraph("UNIVERSIDAD FRANCISCO DE PAULA SANTANDER", 10, Paragraph.ALIGN_LEFT));
                    p.addCell(this.getParapgraph("", 10, Paragraph.ALIGN_LEFT));
                }else{
                    p.addCell(this.getParapgraph(ca.getUsuarioDni().getConferencista().getInstitucionId().getNombre(), 10, Paragraph.ALIGN_LEFT));
                    p.addCell(this.getParapgraph(ca.getUsuarioDni().getConferencista().getPaisOrigen().getNombre(), 10, Paragraph.ALIGN_LEFT));
                }
                p.addCell(this.getParapgraph(ca.getUsuarioDni().getIdRol().getRol(), 10, Paragraph.ALIGN_LEFT));
            }
            this.createBlank();
            this.doc.add(p);
            
            PdfPTable p2 = new PdfPTable(4);
            p2.setWidthPercentage(100);
            p2.addCell(this.getParapgraph("Numero", 11, Paragraph.ALIGN_CENTER));
            p2.addCell(this.getParapgraph("Institución", 11, Paragraph.ALIGN_CENTER));
            p2.addCell(this.getParapgraph("Vigencia", 11, Paragraph.ALIGN_CENTER));
            p2.addCell(this.getParapgraph("Tipo", 11, Paragraph.ALIGN_CENTER));
            for(ConvenioActividad ca: act.getConvenioActividadList()){
                p2.addCell(this.getParapgraph(""+ca.getConvenioId().getNumero(), 10, Paragraph.ALIGN_LEFT));
                p2.addCell(this.getParapgraph(ca.getConvenioId().getEmpresa().getNombre(), 10, Paragraph.ALIGN_LEFT));
                p2.addCell(this.getParapgraph(sd.format(ca.getConvenioId().getVigencia()), 10, Paragraph.ALIGN_LEFT));
                p2.addCell(this.getParapgraph(ca.getConvenioId().getTipoConvenio().getTipo(), 10, Paragraph.ALIGN_LEFT));
            }
            this.createBlank();
            this.doc.add(p2);
            
            this.doc.add(this.getParapgraph("Cantidad involucrados:\n", 10, Paragraph.ALIGN_LEFT));
            this.createBlank();
            PdfPTable p3 = new PdfPTable(5);
            p3.addCell(this.getParapgraph("Cantidad Docentes Catedra", 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph("Cantidad Docentes Tiempo Completo", 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph("Cantidad Docentes Catedra Tiempo Completo", 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph("Cantidad Estudiantes", 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph("Total", 11, Paragraph.ALIGN_CENTER));
            boolean as = act.getInvolucradosActividad()==null;
            p3.addCell(this.getParapgraph(as ? "0" : ""+act.getInvolucradosActividad().getCantDocC(), 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph(as ? "0" : ""+act.getInvolucradosActividad().getCantDocTC(), 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph(as ? "0" : ""+act.getInvolucradosActividad().getCantDocCTP(), 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph(as ? "0" : ""+act.getInvolucradosActividad().getCantEst(), 11, Paragraph.ALIGN_CENTER));
            p3.addCell(this.getParapgraph(as ? "0" : ""+(act.getInvolucradosActividad().getCantDocC()+act.getInvolucradosActividad().getCantEst()+act.getInvolucradosActividad().getCantDocCTP()+act.getInvolucradosActividad().getCantDocTC()), 11, Paragraph.ALIGN_CENTER));
        }
        
        this.doc.close();
    }
    
    public PdfPTable getTablaEnca() throws DocumentException, BadElementException, IOException {
        PdfPTable tab = new PdfPTable(3);
        tab.setWidths(new int[]{1, 4, 1});
        
        tab.getDefaultCell().setRowspan(3);
        tab.addCell(Image.getInstance(this.path+"\\imgs\\logoufps.png"));

        tab.getDefaultCell().setRowspan(3);
        PdfPCell c = new PdfPCell(this.getParapgraph("UNIVERSIDAD FRANCISCO DE PAULA SANTANDER FACULTAD DE INGENIERIA PROGRAMA INGENIERIA DE SISTEMAS", 11, Paragraph.ALIGN_CENTER));
        c.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        c.setVerticalAlignment(Paragraph.ALIGN_MIDDLE);
        c.setFixedHeight(85);
        tab.addCell(c);
        
        tab.getDefaultCell().setRowspan(3);
        tab.addCell(Image.getInstance(this.path+"\\imgs\\logo_ingsis.jpg"));
        tab.getDefaultCell().setRowspan(1);
        return tab;
    }
    
    
    
    public Paragraph getParapgraph(String e, int tam, int orien) {
        Paragraph p = new Paragraph(e, new Font(this.bf, tam));
        p.setAlignment(orien);
        return p;
    }
    
    public void createBlank() throws DocumentException{
        this.doc.add(getParapgraph("\n", 9, Paragraph.ALIGN_CENTER));
    }
}
