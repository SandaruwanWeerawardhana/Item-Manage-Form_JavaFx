package Controller;

import Model.Item;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.*;

public class ItemController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> tlbCode;

    @FXML
    private TableColumn<?, ?> tlbDes;

    @FXML
    private TableView<Item> tlbItem;

    @FXML
    private TableColumn<?, ?> tlbPrice;

    @FXML
    private TableColumn<?, ?> tlbQTY;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHAnd;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnDearch(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    List<Item> ItemList = new ArrayList<>();

    public void btnAdd(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?)");

            stm.setString(1, txtCode.getText());
            stm.setString(2, txtDes.getText());
            stm.setDouble(3, Double.parseDouble(txtPrice.getText()));
            stm.setInt(4, Integer.parseInt(txtQtyOnHAnd.getText()));

            if (stm.executeUpdate() > 0) {
                new Alert(AlertType.CONFIRMATION, "Item Added!!").show();
            }

            txtCode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHAnd.clear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ItemList.add(new Item(
                txtCode.getText(),
                txtDes.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQtyOnHAnd.getText()))
        );
        loadTable();
    }

    public void btnDelete(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Item WHERE code = ?");
            stm.setString(1, txtCode.getText());

            if (stm.executeUpdate() > 0) {
                new Alert(AlertType.CONFIRMATION, "Item deleted!").show();

            }
            txtCode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHAnd.clear();

        } catch (SQLException e) {

        }
        loadTable();
    }

    public void btnUpdate(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("UPDATE Item set description=?, unitPrice=?, qtyOnHand=? where code=?");

            stm.setObject(1, txtDes.getText());
            stm.setObject(2, txtPrice.getText());
            stm.setObject(3, txtQtyOnHAnd.getText());
            stm.setObject(4, txtCode.getText());

            if (stm.executeUpdate() > 0) {
                new Alert(AlertType.CONFIRMATION, "Item updated!").show();
            }
            txtCode.clear();
            txtDes.clear();
            txtPrice.clear();
            txtQtyOnHAnd.clear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTable();
    }

    public void btnSearch(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM item WHERE code=?");
            stm.setString(1, txtCode.getText());
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
                txtDes.setText(item.getDescription());
                txtPrice.setText(item.getUnitPrice() + "");
                txtQtyOnHAnd.setText(item.getQty() + "");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<Item> ItemObList = FXCollections.observableArrayList();

    public void loadTable() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from item");

            System.out.println(resultSet);
            while (resultSet.next()) {
                ItemObList.add(new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));

                ItemList.forEach(item -> {
                    ItemObList.add(item);
                });

                tlbItem.setItems(ItemObList);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tlbCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tlbDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        tlbPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tlbQTY.setCellValueFactory(new PropertyValueFactory<>("Qty"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    public void txtCodeAction(javafx.event.ActionEvent actionEvent) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM item WHERE code=?");
            stm.setString(1, txtCode.getText());
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                Item item = new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
                txtDes.setText(item.getDescription());
                txtPrice.setText(item.getUnitPrice() + "");
                txtQtyOnHAnd.setText(item.getQty() + "");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
























