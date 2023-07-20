package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        String sql = "create table if not exists japons (id serial primary key ," +
                "first_name varchar,last_name varchar,age int)";
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Successfully saved table japon ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table japons";
        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted table japons");
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into japons (first_name ,last_name,age)values (?,?,?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
                System.out.println("Successfully saved " + name + " to table users");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from japons where id =?";
        try(Connection connection=Util.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "select *from japons";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                ));
            }
        } catch (SQLException s) {
            System.out.println(s.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "delete from  japons;";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}