/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Models.Note;
import db.EditNote;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Smeki
 */
@WebServlet(name = "Controller", urlPatterns = {"/notes", "/edit", "/add", "/delete", "/save"})
public class Controller extends HttpServlet {

    //public List<Note> notes = new ArrayList();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    protected void helloWorld(){
        System.out.println("This fsjfdsgfksgdfhsdbfsdf useless function");
        int howMuch = 58;
        for(int i = 0; i < howMuch; i++){
            System.out.println("I am crgbvcbcxying :D");
        }
        
        
        for(int i = 0; i < howMuch; i++){
            System.out.println("I am crfvsdfbfgbdfying :D");
        }
        
        System.out.println("FINAL MODIFICATION!!");
        System.out.println("FINAL MODIFICATION!!");
        System.out.println("FINAL MODIFICATION!!");
        System.out.println("FINAL MODIFICATION!!");
        System.out.println("FINAL MODIFICATION!!");
        System.out.println("FINAL MODIFICATION!!");
    }
    


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
       					 throws ServletException, IOException {
    String addr = request.getServletPath();
    EditNote en = new EditNote();
    
    if(addr.equals("/notes")) {
        try {
                List<Note> notes = en.getNotes();
                request.setAttribute("notes", notes);
                request.getRequestDispatcher("/WEB-INF/view/notes.jsp").forward(request, response);    
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else if(addr.equals("/edit")){
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Note note = en.getNote(id);
                request.setAttribute("note", note);
                request.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        					throws ServletException, IOException {
    String addr = request.getServletPath();
    request.setCharacterEncoding("UTF-8");
    EditNote en = new EditNote();
    
    if(addr.equals("/add")) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String createType = request.getParameter("createType");
        String author = request.getParameter("author");
        
        System.out.println("CALLED");
        
        if(!title.isEmpty() && !content.isEmpty() && !author.isEmpty()){
            try {
                    en.addNote(title, content, createType, author);
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                redirect(request, response, "");
        }
        else {
            redirect(request, response, "/?warning=True");
        }  
    }
    else if(addr.equals("/save")){
        int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String createType = request.getParameter("createType");
            String author = request.getParameter("author");
            
            if(!title.isEmpty() && !content.isEmpty() && !author.isEmpty()){
                try {
                    en.setNote(id, title, content, createType, author);
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                redirect(request, response, "");
            }
            else {
                redirect(request, response, "/edit?id=" + id + "&warning=True");
            }
    }
    else if(addr.equals("/delete")){
        int id = Integer.parseInt(request.getParameter("id"));
            try {
                en.removeNote(id);
            } catch (SQLException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            redirect(request, response, "");
    }
    else {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}

private void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
    response.setStatus(HttpServletResponse.SC_SEE_OTHER);
    response.setHeader("Location", request.getContextPath() + url);
}

}
