package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        while(true){
            System.out.println("1->createUsersTAble" +
                    "\n2->dropUsersTable" +
                    "\n3->saveUser" +
                    "\n4->removeUserById" +
                    "\n5->getAllUsers" +
                    "\n6->cleanUserTable");
            int n=new Scanner(System.in).nextInt();
            switch (n){
                case 1->{
                    System.out.println("createUsersTable()");
                    userService.createUsersTable();
                }
                case 2->{
                    System.out.println("dropUsersTable()");
                    userService.dropUsersTable();
                }
                case 3->{
                    System.out.println("saveUser");
                    userService.saveUser("Isa","Akunbaev",(byte)52);
                }
                case 4->{
                    System.out.println("removeUserById");
                    userService.removeUserById(1L);
                }
                case 5->{
                    System.out.println("getAllUsers");
                    userService.getAllUsers().forEach(System.out::println);
                }
                case 6->{
                    System.out.println("cleanUserTable");
                    userService.cleanUsersTable();
                }
            }
        }
    }
}
