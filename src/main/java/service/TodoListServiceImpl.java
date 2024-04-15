package service;

import entity.Todolist;
import repository.TodoListRepository;

public class TodoListServiceImpl implements TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public void showTodoList() {
        Todolist[] task = todoListRepository.getAll();

        System.out.println("TODOLIST");
        for (Todolist todolist : task) {
            System.out.println(todolist.getId() + ". " + todolist.getTodo());
        }

        //====================================================

//        for (int index = 0; index < task.length; index++) {
//            Todolist newTask = task[index];
//            Integer nomor = index + 1;
//
//            if (newTask != null) {
//                System.out.println(nomor + ". " + newTask.getTodo());
//            }
//        }

    }

    @Override
    public void addTodoList(String todo) {
        Todolist todolist = new Todolist(todo);
        todoListRepository.add(todolist);
        System.out.println("Sukses menambahkan TODO: " + todo);
    }

    @Override
    public void removeTodoList(Integer number) {
        boolean success = todoListRepository.remove(number);
        if (success) {
            System.out.println("Sukses menghapus TODO: " + number);
        } else {
            System.out.println("Gagal menghapus TODO: " + number);
        }
    }
}
