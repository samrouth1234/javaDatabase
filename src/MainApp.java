import model.Topic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MainApp {
    private static Jdbclmpl jdb;
    private static Scanner scanner;
        public static void main(String[] args) {
            jdb = new Jdbclmpl();
            scanner = new Scanner(System.in);
            Topic topic=new Topic();
            System.out.print("Enter id :");
            topic.setId(scanner.nextInt());
            System.out.print("Enter name :");
            scanner.nextLine();
            topic.setName(scanner.nextLine());
            System.out.print("Enter Description :");
            topic.setDescription(scanner.nextLine());
            topic.setStatus(true);
            updateTopic(topic);
            insertTopic(topic);
            deleteById(topic);
            selectByName(topic.getName());
            selectDatabase();
        }
        private static void selectDatabase(){
            try (Connection con=jdb.dataSource().getConnection()){
                String sql="SELECT * FROM topic";
                PreparedStatement statement=con.prepareStatement(sql);
                ResultSet resultSet=statement.executeQuery();
                List<Topic>topics=new ArrayList<>();
                while (resultSet.next()){
                    Integer id=resultSet.getInt("id");
                    String name=resultSet.getString("name");
                    String description=resultSet.getString("description");
                    Boolean status=resultSet.getBoolean("status");
                    topics.add(new Topic(id,name,description,status));
                }
                topics.forEach(System.out::println);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    private static void insertTopic(Topic topic) {
        try (Connection con = jdb.dataSource().getConnection()) {
            String insertSql = "INSERT INTO topic(name,description,status)" + "VALUES(?,?,?)";
            PreparedStatement statement = con.prepareStatement(insertSql);
            statement.setString(1, topic.getName());
            statement.setString(2, topic.getDescription());
            statement.setBoolean(3, topic.getStatus());
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateTopic(Topic topic){
        try (Connection con = jdb.dataSource().getConnection()) {
            String updateDatabase = "UPDATE topic SET name=?,description=?,status=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(updateDatabase);
            statement.setString(1, topic.getName());
            statement.setString(2, topic.getDescription());
            statement.setBoolean(3, topic.getStatus());
            statement.setInt(4, topic.getId());
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deleteById(Topic topic){
        try (Connection con = jdb.dataSource().getConnection()) {
            String deleteDatabase = "DELETE FROM topic WHERE id=?";
            PreparedStatement statement = con.prepareStatement(deleteDatabase);
            statement.setInt(1, topic.getId());
            int count = statement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void selectById(Integer id){
        try (Connection con = jdb.dataSource().getConnection()) {
            String selectID = "SELECT FROM topic WHERE id=?";
            PreparedStatement statement = con.prepareStatement(selectID);
            statement.setInt(1,id);
            ResultSet count = statement.executeQuery();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void selectByName(String name){
        try (Connection con = jdb.dataSource().getConnection()) {
            String selectName = "SELECT FROM topic WHERE name LIKE ?";
            PreparedStatement statement = con.prepareStatement(selectName);
            statement.setString(1,name);
            ResultSet count = statement.executeQuery();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
