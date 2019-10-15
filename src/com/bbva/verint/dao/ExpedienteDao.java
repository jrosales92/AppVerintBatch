package com.bbva.verint.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bbva.verint.bean.VerintBean;
import com.bbva.verint.manager.SequenceManager;

import java.sql.*;

import static java.lang.String.format;

/**
 * @author tahis
 */
public class ExpedienteDao {

    private static Logger log = LogManager.getLogger(ExpedienteDao.class);

    /**
     * @param conn
     * @param exp
     * @param st
     * @return
     * @throws SQLException
     */
    public VerintBean validaExpediente(Connection conn, VerintBean exp, Statement st) throws SQLException {
        System.out.println("Inicia idExpediente");
        PreparedStatement ps = null;
        ResultSet rs = null;
        DocumentoDao doc = new DocumentoDao();
        String query = "SELECT CD_EXPEDIENTE FROM GORAPR.TATN002_EXPEDIENTE WHERE CD_APLICACION = ? AND CD_CUENTA = ?  AND CD_CLIENTE = ? ";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, exp.getCdAplicacion());
            ps.setString(2, exp.getContactIdVerint());
            ps.setString(3, exp.getContractId());
            rs = ps.executeQuery();
            if (rs.next()) {
                exp.setIdGabinete(rs.getInt("CD_EXPEDIENTE"));
            } else {
                secuenceExpediente(conn, exp, st);
            }
            System.out.println("Expediente " + exp.getIdGabinete());
            exp = doc.idDocumento(conn, exp, st);
            st.executeBatch();
        } catch (SQLException e) {
            log.error(e);
            conn.rollback();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return exp;
    }

    /**
     * @param conn
     * @param exp
     * @param st
     * @return
     * @throws SQLException
     */
    public int secuenceExpediente(Connection conn, VerintBean exp, Statement st) throws SQLException {
    	System.out.println("Inicia secuenceExpediente");
        int idExpediente;
        idExpediente = nextValExpediente(conn, exp.getCdAplicacion());
        exp.setIdGabinete(idExpediente);
        createExpediente(conn, exp, st);
        return idExpediente;
    }

    /**
     * @param conn
     * @param cd_aplicacion
     * @return
     * @throws SQLException
     */
    private static synchronized int nextValExpediente(Connection conn, int cd_aplicacion) throws SQLException {
    	System.out.println("Inicia nextValExpediente");
        String titulo_aplicacion = "GORAPR.TATN002_EXPEDIENTE";
        return SequenceManager.getInstance().nextValue(conn, format(titulo_aplicacion), "cd_expediente", cd_aplicacion);
    }

    /**
     * @param conn
     * @param exp
     * @param st
     * @return
     * @throws SQLException
     */
    public int createExpediente(Connection conn, VerintBean exp, Statement st) throws SQLException {
    	System.out.println("Inicia createExpediente");
        int insert = 0;
        String query = "INSERT INTO GORAPR.TATN002_EXPEDIENTE (CD_EXPEDIENTE,CD_APLICACION,CD_CLIENTE,CD_CUENTA,CD_CR) VALUES "
                + " ( " + exp.getIdGabinete() + " , " + exp.getCdAplicacion() + " ,'" + exp.getCustomerId() + "' , '" + exp.getContactIdVerint() + "', '" + exp.getCr() + "')";
        System.out.println(query);
        st.addBatch(query);
        return insert;
    }
}