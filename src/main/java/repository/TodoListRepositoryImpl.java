package repository;

import entity.Todolist;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepositoryImpl implements TodoListRepository {

//    public Todolist[] data = new Todolist[10];

    private DataSource dataSource;

    public TodoListRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Todolist[] getAll() {
        String sql = "SELECT id, todo FROM todolist";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Todolist> list = new ArrayList<>();
            while (resultSet.next()) {
                Todolist todolist = new Todolist();

                todolist.setId(resultSet.getInt("id"));
                todolist.setTodo(resultSet.getString("todo"));

                list.add(todolist);
            }

            return list.toArray(new Todolist[]{});

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

//    public boolean isFull() {
//        //cek task penuh atau tidak
//        boolean isFull = true;
//        for (int index = 0; index < data.length; index++) {
//            if (data[index] == null) {
//                //task masih ada yang kosong
//                isFull = false;
//                break;
//            }
//        }
//        return isFull;
//    }

//    public void resizeIfFull() {
//        //jika penuh, akan resize ukuran array 2x lipat
//        if (isFull()) {
//            Todolist[] temp = data;
//            data = new Todolist[data.length * 2];
//
//            for (int index = 0; index < temp.length; index++) {
//                data[index] = temp[index];
//            }
//        }
//    }

    @Override
    public void add(Todolist todolist) {
//        resizeIfFull();
//
//        // menambahkan data ke posisi array null
//        for (int index = 0; index < data.length; index++) {
//            if (data[index] == null) {
//                data[index] = todolist;
//                break;
//            }
//        }
        //=========================================================

        String sql = "INSERT INTO todolist(todo) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, todolist.getTodo());
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private boolean isExists(Integer number) {
        String sql = "SELECT id FROM todolist WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, number);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean remove(Integer number) {

//        if ((number - 1) >= data.length) {
//            return false;
//        } else if (data[number - 1] == null) {
//            return false;
//        } else {
//            for (int index = (number - 1); index < data.length; index++) {
//                if (index == (data.length - 1)) {
//                    data[index] = null;
//                } else {
//                    data[index] = data[index + 1];
//                }
//            }
//            return true;
//        }

        //=========================================================

        if (isExists(number)) {
            String sql = "DELETE FROM todolist WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, number);
                statement.executeUpdate();

                return true;

            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }

        } else {
            return false;
        }

    }
}
