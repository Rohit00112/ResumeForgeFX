package com.snapgramfx.resumeforgefx.dao.impl;

import com.snapgramfx.resumeforgefx.dao.ResumeDAO;
import com.snapgramfx.resumeforgefx.model.Resume;
import com.snapgramfx.resumeforgefx.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ResumeDAO interface.
 */
public class ResumeDAOImpl implements ResumeDAO {

    @Override
    public Resume save(Resume resume) {
        String sql = "INSERT INTO resumes (user_id, title, summary) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, resume.getUserId());
            pstmt.setString(2, resume.getTitle());
            pstmt.setString(3, resume.getSummary());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating resume failed, no rows affected.");
            }
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                resume.setId(rs.getLong(1));
            } else {
                throw new SQLException("Creating resume failed, no ID obtained.");
            }
            
            return resume;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    @Override
    public Resume findById(Long id) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractResumeFromResultSet(rs);
            }
            
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    @Override
    public List<Resume> findByUserId(Long userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Resume> resumes = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Resume resume = extractResumeFromResultSet(rs);
                resumes.add(resume);
            }
            
            return resumes;
        } catch (SQLException e) {
            e.printStackTrace();
            return resumes;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    @Override
    public List<Resume> findAll() {
        String sql = "SELECT * FROM resumes";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Resume> resumes = new ArrayList<>();
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Resume resume = extractResumeFromResultSet(rs);
                resumes.add(resume);
            }
            
            return resumes;
        } catch (SQLException e) {
            e.printStackTrace();
            return resumes;
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    @Override
    public Resume update(Resume resume) {
        String sql = "UPDATE resumes SET title = ?, summary = ?, last_modified_date = CURRENT_TIMESTAMP WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, resume.getTitle());
            pstmt.setString(2, resume.getSummary());
            pstmt.setLong(3, resume.getId());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Updating resume failed, no rows affected.");
            }
            
            return resume;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM resumes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(conn, pstmt, null);
        }
    }
    
    /**
     * Extract a Resume object from a ResultSet.
     * 
     * @param rs the ResultSet containing resume data
     * @return a Resume object
     * @throws SQLException if a database access error occurs
     */
    private Resume extractResumeFromResultSet(ResultSet rs) throws SQLException {
        Resume resume = new Resume();
        resume.setId(rs.getLong("id"));
        resume.setUserId(rs.getLong("user_id"));
        resume.setTitle(rs.getString("title"));
        resume.setSummary(rs.getString("summary"));
        resume.setCreatedDate(rs.getTimestamp("created_date"));
        resume.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        
        // Note: This doesn't load the related entities (education, experience, skills, contact info)
        // Those would need to be loaded separately if needed
        
        return resume;
    }
    
    /**
     * Close database resources.
     * 
     * @param conn the Connection object to close
     * @param pstmt the PreparedStatement object to close
     * @param rs the ResultSet object to close
     */
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        DatabaseUtil.closeConnection(conn);
    }
}
