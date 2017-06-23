package com.lixh.weiji;

import java.io.Serializable;

/**
 * Created by LIXH on 2017/6/21.
 * email lixhVip9@163.com
 * des
 */

public class Bean implements Serializable{
    //随时变动
    double s = 0;//币数  s
    double y = 0;//人民币 y
    double a = 0;//客户返点 a
    double c = 0;//渠道回扣  c
    double e = 0;//渠道点数 e
    double n = 0;//客户实际返点n
    double b = 0;//上面返点b
    double b1 = 0;//上面实际点数b1
    //结果
//    double z = 0;//客户转款数z
//    double w = 0;//客户实际转款数w
//    double q = 0;//给上面的钱q
//    double p = 0;//上面的回扣 p
//    double g = 0;//实际给上面的钱g
//    double v = 0;//利润v


    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "a=" + a +
                ", s=" + s +
                ", y=" + y +
                ", c=" + c +
                ", e=" + e +
                ", n=" + n +
                ", b=" + b +
                ", b1=" + b1 +
                '}';
    }
}
