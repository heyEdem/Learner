package com.edem.admin.utility;

import com.edem.admin.dao.InstructorDao;
import com.edem.admin.dao.RoleDao;
import com.edem.admin.dao.StudentDao;
import com.edem.admin.dao.UserDao;
import com.edem.admin.entity.Instructor;
import com.edem.admin.entity.Role;
import com.edem.admin.entity.Student;
import com.edem.admin.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class OperationUtility {
    public static void usersOperations(UserDao userDao){
        createUsers(userDao);
        updateUsers(userDao);
        updateUsers(userDao);
        fetchUsers(userDao);
    }

    public static void rolesOperations(RoleDao roleDao){
        createRoles(roleDao);
        updateRoles(roleDao);
        deleteRole(roleDao);
        fetchRoles(roleDao);
    }

    public static void assignRolesToUsers(RoleDao roleDao,UserDao userDao){
        Role role = roleDao.findByName("Admin");
        if(role==null) throw new EntityNotFoundException("role not found");

        List<User> users = userDao.findAll();
        users.forEach(user -> {
            user.assignRoleToUser(role);
        });
    }

    public static void instructorOperations(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao){
        createInstructors(userDao,instructorDao,roleDao);
        updateInstructor(instructorDao);
        removeInstructor(instructorDao);
        fetchInstructor(instructorDao);
    }

   public static void studentOperations(UserDao userDao, StudentDao studentDao,RoleDao roleDao){
        createStudents(userDao,studentDao,roleDao);
        updateStudent(studentDao);
        removeStudent(studentDao);
        fetchStudents(studentDao);
   }

   private static void createStudents(UserDao userDao, StudentDao studentDao, RoleDao roleDao){
        Role role = roleDao.findByName("Student");
        if (role == null)throw new EntityNotFoundException("Role Not Found");

        User user1 = new User("stdUser1@gmail.com","user1");
        userDao.save(user1);
        user1.assignRoleToUser(role);

        Student student1 = new Student("Student1FN","Student1LN","master",user1);
        studentDao.save(student1);

       User user2 = new User("stdUser2@gmail.com","user2");
       userDao.save(user2);
       user2.assignRoleToUser(role);

       Student student2 = new Student("Student2FN","Student2LN","Phd",user2);
       studentDao.save(student2);
   }

   private static void updateStudent(StudentDao studentDao){
        Student student = studentDao.findById(2L).orElseThrow(()->new EntityNotFoundException("Student not found"));
        student.setFirstName("Jay");
        student.setLastName("Bahd");
        studentDao.save(student);
   }

   private static void removeStudent( StudentDao studentDao){
        studentDao.deleteById(1L);
   }

   private static void fetchStudents(StudentDao studentDao){
        studentDao.findAll().forEach(student-> System.out.print(student.toString()));
   }

    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
        Role role = roleDao.findByName("Instructor");
        if (role == null) throw new EntityNotFoundException("Role not found");

        User user1 = new User("user1@gmail.com","user1");
        userDao.save(user1);

        user1.assignRoleToUser(role);

        Instructor instructor1 = new Instructor("instructor1","Instructor1","advanced instructor",user1);
        instructorDao.save(instructor1);

        User user2 = new User("user2@gmail.com","user2");
        userDao.save(user2);

        user2.assignRoleToUser(role);

        Instructor instructor2 = new Instructor("instructor2","Instructor2","good instructor",user2);
        instructorDao.save(instructor2);
    }

    private static void updateInstructor(InstructorDao instructorDao){
        Instructor instructor = instructorDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Instructor not found"));
        instructor.setSummary("CertifiedInstructor");
        instructorDao.save(instructor);

    }

    private static void removeInstructor(InstructorDao instructorDao){
        instructorDao.deleteById(2L);
    }

    private static void fetchInstructor(InstructorDao instructorDao){
        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
    }

    private static void fetchRoles(RoleDao roleDao){
        roleDao.findAll().forEach(role-> System.out.println(role.toString()));
    }



    public static void createRoles(RoleDao roleDao){
        Role role1 = new Role("Admin");
        roleDao.save(role1);
        Role role2 = new Role("Instructor");
        roleDao.save(role2);
        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    private static void updateRoles(RoleDao roleDao){
        Role role = roleDao.findById(1L).orElseThrow(()-> new EntityNotFoundException("Role Not Found Exception"));
        role.setName("supervisor");
        roleDao.save(role);
    }

    private static void deleteRole(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role Not Found Exception"));
        roleDao.delete(role);
    }

    public static void createUsers(UserDao userDao){
        User user1 = new User ("user1@gmail.com","pass1");
        userDao.save(user1);
        User user2 = new User ("user2@gmail.com","pass2");
        userDao.save(user2);
        User user3 = new User ("user3@gmail.com","pass3");
        userDao.save(user3);
        User user4 = new User ("user4@gmail.com","pass4");
        userDao.save(user4);
    }

    private static void updateUsers(UserDao userDao){
        User user = userDao.findById(2L).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setEmail("userUpdate@gmail.com");
        userDao.save(user);
    }

    private static void deleteUser(UserDao userDao){
        User user = userDao.findById(3L).orElseThrow(()->new EntityNotFoundException("User not found"));
        userDao.delete(user);
    }


    private static void fetchUsers(UserDao userDao){
        userDao.findAll().forEach(user -> System.out.println(user.toString()));
    }
}
