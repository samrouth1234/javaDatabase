import model.Topic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static Jdbclmpl jdb;
    private static Scanner scanner;
        public static void main(String[] args) {
            jdb=new Jdbclmpl();
            scanner=new Scanner(System.in);
            int option;
            Jdbclmpl jdb=new Jdbclmpl();
            List<Topic> topics=new ArrayList<>();
            Topic topic=new Topic();

           do {
               System.out.println("======================================================================");
                System.out.println("1.UPDATE OPERATION ");
                System.out.println("2.DELETE BY ID OPERATION");
                System.out.println("3.SELECT ID OPERATION");
                System.out.println("4.SELECT BY NAME OPERATION");
                System.out.println("5.EXIT");
                System.out.println("======================================================================");
                System.out.print("==> Chooses option 1->5 :");
                option= scanner.nextInt();
                switch (option) {
                   case 1 -> {
                       System.out.println("===============UPDATE OPERATION===============");
                       System.out.print("Enter id :");
                       topic.setId(scanner.nextInt());
                       System.out.print("Enter name :");
                       topic.setName(scanner.nextLine());
                       scanner.nextLine();
                       System.out.print("Enter Description :");
                       topic.setDescription(scanner.nextLine());
                       topic.setStatus(true);
                       try (Connection conn = jdb.dataSource().getConnection()) {
                           String sql = "UPDATE toys SET name=?, description=?,status=? WHERE id="+ topic.getId();
                           PreparedStatement statement = conn.prepareStatement(sql);
                           statement.setString(1, topic.getName());
                           statement.setString(2, topic.getDescription());
                           statement.setBoolean(3, topic.getStatus());
                           statement.executeUpdate(sql);
                           System.out.println("Updated Successfully!!");
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                       scanner.nextInt();
                   }
                   case 2 -> {
                       System.out.println("===============Delete By ID OPERATION===============");
                       System.out.print("Enter id :");
                       topic.setId(scanner.nextInt());
                       try (Connection connection = jdb.dataSource().getConnection()) {
                           String sql = "DELETE FROM toys WHERE id="+topic.getId();
                           PreparedStatement statements = connection.prepareStatement(sql);
                           System.out.println("Updated Successfully!!");
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   }
                   case 3 -> {
                       System.out.println("===============SELECT By ID OPERATION===============");
                       System.out.print("Enter id :");
                       topic.setId(scanner.nextInt());
                       try (Connection connections = jdb.dataSource().getConnection()) {
                           String sql = "SELECT * FROM toys WHERE id="+ topic.getId();
                           PreparedStatement statement = connections.prepareStatement(sql);
                           ResultSet resultSet =  statement.executeQuery();
                           while (resultSet.next()){
                               Integer id=resultSet.getInt("id");
                               String name=resultSet.getString("name");
                               String description=resultSet.getString("description");
                               Boolean status=resultSet.getBoolean("status");
                               topics.add(new Topic(id,name,description,status));
                           }
                           topics.forEach(System.out::println);
                           } catch (SQLException e) {
                           e.printStackTrace();
                        }
                   }
                   case 4 -> {
                       System.out.println("===============SELECT By NAME OPERATION===============");
                       System.out.print("Enter name :");
                       topic.setName(scanner.nextLine());
                       try (Connection connecs = jdb.dataSource().getConnection()) {
                           String sql = "SELECT * FROM toys WHERE name ILIKE '%"+ topic.getName()+"%'";
                           PreparedStatement statements = connecs.prepareStatement(sql);
                           ResultSet resultSet =  statements.executeQuery();
                           while (resultSet.next()){
                               Integer id = resultSet.getInt("id");
                               String name = resultSet.getString("name");
                               String description = resultSet.getString("description");
                               boolean status = resultSet.getBoolean("status");
                               topics.add(new Topic(id,name,description,status));
                           }
                           } catch (SQLException e) {
                           e.printStackTrace();
                       }
                       scanner.nextInt();
                   }
                    default -> System.out.println("Chose try again ..!");
               }
            }while (option!=5);
        }
}
