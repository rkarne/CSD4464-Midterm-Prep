/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midtermprep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0587637
 */
@Named
@ApplicationScoped
public class Guestbook {
    private  List<GuestbookPost> guestbookPosts;
    private GuestbookPost gs;

    public GuestbookPost getGs() {
        return gs;
    }

    public Guestbook() {
        gs = new GuestbookPost(-1, "", "");
        refresh();
    }

    public List<GuestbookPost> getGuestbookPosts() {
        return guestbookPosts;
    }
    
    private void refresh() {
        try {
            guestbookPosts = new ArrayList<>();
            guestbookPosts.clear();
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM guestbook");
            while (rs.next()) {
                GuestbookPost gbp = new GuestbookPost(
                        rs.getInt("id"),
                        rs.getString("author"),
                        rs.getString("message")
                );
                guestbookPosts.add(gbp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Guestbook.class.getName()).log(Level.SEVERE, null, ex);
            guestbookPosts.clear();
        }
    }
    
    public String addPost(){
         gs = new GuestbookPost(-1, "", "");
        return "addPost";
    }
    
    public String saveMessage(){
         try (Connection conn = Database.getConnection()) {
            if (gs.getId() >= 0) {
                String sql = "UPDATE guestbook SET author = ?, message = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, gs.getAuthor());
                pstmt.setString(2, gs.getMessage());
                pstmt.setInt(3, gs.getId());
                pstmt.executeUpdate();
            } else {
                String sql = "INSERT INTO guestbook ( author, message) VALUES (?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                //pstmt.setInt(1, user.getId());
                pstmt.setString(1, gs.getAuthor());
                pstmt.setString(2, gs.getMessage());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Guestbook.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
        return "displaybook";
    }
    
    public String view(GuestbookPost book){
        gs = book;
        return "displaybook";
    }
    
    public String editdetails(){
        return "addPost";
    }
            
    public String delete(){
        try (Connection conn = Database.getConnection()) {
            if (gs.getId() >= 0) {
                String sql = "DELETE FROM guestbook WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, gs.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Guestbook.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();  
        return "index";
    }
}
