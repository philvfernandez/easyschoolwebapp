package com.easybytes.easyschool.repository;

import com.easybytes.easyschool.model.Contact;
import com.easybytes.easyschool.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact) {
        String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
        sql = sql.toLowerCase();
        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(),
                contact.getEmail(),contact.getSubject(),contact.getMessage(),
                contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }


    /* PVF: This method is a little different from what was given in the course.
    This way is a little easier to understand.
     */
    public List<Contact> findMsgsWithStatus(String status) {
        String sql = "SELECT * FROM CONTACT_MSG where STATUS = ?";
        sql = sql.toLowerCase();

        List<Contact> contacts = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Contact.class),status);

        return contacts;

    }

    public int updateMsgStatus(int contactId, String status, String updatedBy) {
        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
        sql = sql.toLowerCase();
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, status);
                preparedStatement.setString(2, updatedBy);
                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                preparedStatement.setInt(4, contactId);
            }
        });
    }
}
