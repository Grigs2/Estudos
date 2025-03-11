package DTO;

//import DAO.ClienteDAO;
//import DAO.ConexaoMysql;
import javax.swing.*;
//import java.awt.*;

public class App {
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> {
             JanelaPrincipal frame = new JanelaPrincipal();
             frame.setVisible(true);
         }
         );
    } 
}

