package view;

import service.TodoListService;
import util.InputUtil;

public class TodoListView {
    private TodoListService todoListService;

    public TodoListView(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    public void showTodoList() {
        while (true) {
            todoListService.showTodoList();

            System.out.println("Menu");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("x. Keluar");

            String input = InputUtil.input("Pilih");

            if (input.equals("1")) {
                addTodoList();
            } else if (input.equals("2")) {
                removeTodoList();
            } else if (input.equals("x")) {
                break;
            } else {
                System.out.println("Pilihan salah, coba lagi");
            }
        }
    }

    public void addTodoList() {

        System.out.println("MENAMBAH TASK");

        String addTask = InputUtil.input("Task (x Untuk Batal)");

        if (addTask.equals("x")) {
            //batal
            showTodoList();
        } else {
            todoListService.addTodoList(addTask);
        }

    }

    public void removeTodoList() {

        System.out.println("MENGHAPUS TASK");
        String number = InputUtil.input("Nomor yang Dihapus (x Untuk Batal)");

        if (number.equals("x")) {
            //batal
            showTodoList();
        } else {
            todoListService.removeTodoList(Integer.valueOf(number));
        }

    }
}
