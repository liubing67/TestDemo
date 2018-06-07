package com.abing.testdemo;

/**
 * 静态内部类方式
 * 思考懒汉式和饿汉式优缺点
 * 饿汉式优点：天生线程安全，执行效率高 缺点：如果我不使用该对象的时候，比较占内存、没有延迟加载
 * 懒汉式优点：占内存小、具有延迟加载功能。缺点：线程不安全，需要加锁，加锁后执行效率变低了、阻塞、等待。
 *
 * 静态内部类方式结合饿汉式和懒汉式优点，具备延迟加载、天生线程安全、真正需要才会去创建对象。
 */
public class UserEntity {

    public UserEntity(){
        System.out.println("UserEntity对象已经被创建了....");
    }
    //内部类在什么时候会初始化，需要外部类调用内部类才会初始化。
    public static class SingletonClassInstance{
        public static final UserEntity userEntity=new UserEntity();
    }
    public static UserEntity getInstance(){
        return SingletonClassInstance.userEntity;
    }
    public static void main(String[] args){
        UserEntity u1=UserEntity.getInstance();
        UserEntity u2=UserEntity.getInstance();
        System.out.println(u1==u2);
    }
}













//    private String userName;
//    public UserEntity(){
//        System.out.println("对象初始化。。。。");
//    }
//    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        //一、初始化操作无参构造函数
////        UserEntity userEntity=new UserEntity();
////        userEntity.userName="阿冰的小屋";
////        System.out.println(userEntity.userName);
//        //二、1.使用Java的反射机制创建对象 类的完整路径
////        Class<?> forName=Class.forName("com.abing.testdemo.UserEntity");
////        // 2.使用反射机制创建对象
////        UserEntity userEntity1= (UserEntity) forName.newInstance();
////        userEntity1.userName="反射创建对象。。。阿冰的小屋";
////        System.out.println(userEntity1.userName);
//
////        ABList<String> aa=new ABArrayList();
////        aa.add();
//
//        int[] a={1,2,3,4,5,6};
//        int[] b={7,8,9,10,11,12};
//        System.arraycopy(a,3,b,1,3);
//        for (int i=0;i<b.length;i++){
//            System.out.println(b[i]);
//        }
//
//    }
//}
