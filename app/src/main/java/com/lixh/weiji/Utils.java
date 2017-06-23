package com.lixh.weiji;

import java.text.DecimalFormat;

/**
 * Created by LIXH on 2017/6/21.
 * email lixhVip9@163.com
 * des
 */

public class Utils {
    static DecimalFormat df = new DecimalFormat("#");

    static double parseValue(double result_value) {
        return Double.parseDouble(df.format(result_value));
    }

    public enum V1 {
        INSTANCE;

        /**
         * 按币数充值
         *
         * @param s
         * @param a
         * @param b
         * @return 利润 v
         */
        public double profits(double s, double a, double b) {
            return parseValue(getZ(s, a) - getQ(s, b));
        }

        /**
         * 一个回扣的公式1
         *
         * @param s
         * @param e
         * @param b
         * @return 利润  v
         */
        public double profits_kickback(double s, double e, double b) {
//        double c = getZ(s, a) - getW(s, e);
//        return getZ(s, a) - getQ(s, b)-c;
            return parseValue(getW(s, e) - getQ(s, b));
        }

        /**
         * 上面扣加客户的点公式1
         * s÷a=z   s÷e=客户实际转款数w
         * c=z-w   s÷b=给上面的钱q s÷b1=实际给上面的钱g  p=q-g 利润v= z-q-c-p
         *
         * @param s
         * @param e
         * @param b
         * @return 利润 v
         */
        public double profits_kickback_add_user(double s, double e, double b, double b1) {
//            double c = getZ(s, a) - getW(s, e);
//            double p = getG(s, b1) - getQ(s, b);
//            return getZ(s, a) - getQ(s, b) - c - p;
            return parseValue(getW(s, e) - getG(s, b1));
        }

        /**
         * @param s
         * @param b1
         * @return 实际给上面的钱g
         */
        public double getG(double s, double b1) {
            return parseValue(s / b1);
        }

        /**
         * @param s
         * @param e
         * @return 客户实际转款数w
         */
        public double getW(double s, double e) {
            return parseValue(s / e);
        }

        /**
         * @param s
         * @param a
         * @return 客户转款数z
         */
        public double getZ(double s, double a) {
            return parseValue(s / a);
        }

        /**
         * @param s
         * @param b
         * @return 给上面的钱q
         */
        public double getQ(double s, double b) {
            return parseValue(s / b);
        }
    }


    /**
     * 按人民币充值
     */
    public enum V2 {
        INSTANCE;

        /**
         * @param y
         * @param a
         * @return 客户充值币数s
         */
        public double getS(double y, double a) {
            return parseValue(y * a);
        }

        /**
         * 按人民币充值
         * y*a=客户充值币数s
         * s÷b=给上面的钱q
         * 利润V=y-q
         *
         * @param y
         * @param a
         * @param b
         * @return 利润 v
         */
        public double profits(double y, double a, double b) {
            return parseValue(y - getQ(getS(y, a), b));
        }

        /**
         * 一个回扣的公式2
         * y*a=s   c=y-s÷n   y÷b=给上面的钱q
         * 利润V=y-q-c
         *
         * @param y
         * @param a
         * @param n
         * @return 利润  v
         */
        public double profits_kickback(double y, double a, double n, double b) {
            double c = y - parseValue(getS(y, a) / n);
            return parseValue(y - getQ(getS(y, a), b) - c);
        }

        /**
         * 上面扣加客户的点公式2
         * y*a=s   c=y-s÷n   y÷b=给上面的钱q
         * s÷b1=实际给上面的钱g  p=q-g 利润V=y-c-q-p
         *
         * @param y
         * @param a
         * @param n
         * @param b1
         * @return 利润 v
         */
        public double profits_kickback_add_user(double y, double a, double n, double b, double b1) {
            double c = y - getS(y, a) / n;
            double g = getG(getS(y, a), b1);
            double p = g - getQ(y, b);
            return parseValue(y - c - getQ(y, b) - p);
        }

        /**
         * @param s
         * @param b1
         * @return 实际给上面的钱g
         */
        public double getG(double s, double b1) {
            return parseValue(s / b1);
        }

        /**
         * @param y
         * @param b
         * @return 给上面的钱q
         */
        public double getQ(double y, double b) {
            return parseValue(y / b);
        }
    }

    public enum T {
        INSTANCE;

        public double getT1(double t, double b) {
            return parseValue(t / b);
        }

        /**
         * T2=  t/b1-T1
         *
         * @param t
         * @param b1
         * @param b
         * @return
         */
        public double getT2(double t, double b, double b1) {
            return parseValue(t / b1) - getT1(t, b);
        }

        public double getT3(double t, double a) {
            return parseValue(t / a);
        }

        /**
         * T2=  T3-t/n
         *
         * @param t
         * @param a
         * @param n
         * @return
         */
        public double getT4(double t, double a, double n) {

            return getT3(t, a) - parseValue(t / n);
        }

        /**
         *  L1=t÷b1-t÷n
         * @param t
         * @param b1
         * @param n
         * @return
         */
        public double getL1(double t, double b1, double n){
            return parseValue(t / b1) - parseValue(t / n);
        }
    }
}
