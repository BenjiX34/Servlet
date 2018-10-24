/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;
import simplejdbc.States;

/**
 *
 * @author benjamin
 */
public class ShowClientsInStateWithForm extends HttpServlet {
    private DAO myDAO;
    private DataSource myDataSource;
    private List<CustomerEntity> customersInState;
    private List<String> listOfStates;
    private States states;
    private String actualState;
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        myDataSource = DataSourceFactory.getDataSource();
        myDAO = new DAO(myDataSource);
        states = new States(myDataSource);        
        
        try{
            listOfStates = states.listOfStates();
        }catch(SQLException | DAOException e ){
            System.exit(-1);
        }
        
        actualState = request.getParameter("states");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<title>Servlet ShowClientsInState</title>");            
                out.println("</head>");
                out.println("<body>");
                    out.println("<form action=\"ShowClientsInStateWithForm\" method=\"get\">");
                        out.println("<select name=\"states\">");
                        for(String st: listOfStates){
                            out.println("<option id="+st+" value="+st+">"+st+"</option>");
                        }
                        out.println("</select>");
                        out.println("<button type=\"submit\">Valider</button>");
                    out.println("</form>");
                    if(!actualState.isEmpty()){
                        try{
                            customersInState = myDAO.customersInState(actualState);
                            out.println("<table border=1>");
                            out.println("<tr>");
                                out.println("<th>ID</th>");
                                out.println("<th>Name</th>");
                                out.println("<th>Address</th>");
                            out.println("</tr>");
                            for(CustomerEntity customer: customersInState){
                                out.println("<tr>");
                                    out.println("<td>"+customer.getCustomerId()+"</td>");
                                    out.println("<td>"+customer.getName()+"</td>");
                                    out.println("<td>"+customer.getAddressLine1()+"</td>");
                                out.println("</tr>");
                            }
                            out.println("</table>");
                        }catch(DAOException e){
                            System.exit(-1);
                        }
                        
                    }
                out.println("</body>");
            out.println("</html>");      
        }
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

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
