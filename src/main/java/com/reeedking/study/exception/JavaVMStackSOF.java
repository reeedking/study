package com.reeedking.study.exception;

public class JavaVMStackSOF {

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        javaVMStackSOF.test();
    }

    public void test() {
        test();
    }
}
