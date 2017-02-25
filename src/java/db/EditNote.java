/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Models.Note;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Smeki
 */
public class EditNote {
    
    private Connection getConnection() throws NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context ctx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) ctx.lookup("jdbc/mynotes");
        System.out.println("CALLED - GET CONNECTION");
        return ds.getConnection();
    }
    
    public List<Note> getNotes() throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;       
        List<Note> notes = new ArrayList();
        
        System.out.println("CALLED - GET ALL NOTES");

        try {
            String query = "SELECT * FROM notes";            
            connection = getConnection();            
            stmt = connection.prepareStatement(query);           
            rs = stmt.executeQuery();

            while (rs.next()) {
                notes.add(new Note(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("createType"), rs.getString("author")));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(rs != null) {rs.close();}
            if(stmt != null) {stmt.close();}
            if(connection != null) {connection.close();}
        }
        return notes;
    }
    
    public Note getNote(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        Note note = null;

        try {
            String query = "SELECT * FROM notes WHERE id = ?";            
            connection = getConnection();            
            stmt = connection.prepareStatement(query); 
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                note = new Note(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getString("createType"), rs.getString("author"));
            }
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(rs != null) {rs.close();}
            if(stmt != null){stmt.close();}
            if(connection != null){connection.close();}
        }
        return note;
    }
    
    public void setNote(int id, String title, String content, String createType, String author) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE notes SET title = ?, content = ?, createType = ?, author = ? WHERE id = ?";            
            connection = getConnection();            
            stmt = connection.prepareStatement(query); 
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setString(3, createType);
            stmt.setString(4, author);
            stmt.setInt(5, id);
            stmt.executeUpdate();            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(stmt != null){stmt.close();}
            if(connection != null){connection.close();}
        }
    }
    
    public void addNote(String title, String content, String createType, String author) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            String query = "INSERT INTO notes (title, content, createType, author) VALUES (?, ?, ?, ?)";            
            connection = getConnection();            
            stmt = connection.prepareStatement(query); 
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setString(3, createType);
            stmt.setString(4, author);
            stmt.executeUpdate();          
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(stmt != null){stmt.close();}
            if(connection != null){connection.close();}
        }
    }
    
    public void removeNote(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement stmt = null;
        
    try {
        String query = "DELETE FROM notes WHERE id = ?";            
        connection = getConnection();            
        stmt = connection.prepareStatement(query); 
        stmt.setInt(1, id);
        stmt.executeUpdate();            
    } catch (NamingException | SQLException ex) {
        Logger.getLogger(EditNote.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if(stmt != null){stmt.close();}
        if(connection != null){connection.close();}
    }
    }
}
