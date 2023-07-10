//import org.apache.poi.sl.usermodel.PaintStyle.SolidPaint;
package practice;

import java.util.Scanner;

public class Demo{
    public static void main(String[] args) {
        //1.创建键盘录入对象
       Scanner sc = new Scanner(System.in);
        //2.分别录入存款金额和年份
        System.out.println("请输入存款金额：");
        double money = sc.nextInt();
        System.out.println("请输入存款年份：");
        int year = sc.nextInt();
        //对利息进行判断
        if(money >= 1000){
            if(year == 1){
            money = money + (money*0.0225*year);

        }else if(year == 2){
            money = money + (money*0.027*year);

        }else if (year == 3 || year == 4){
            money = money + (money*0.0325*year);

        }else {
            money = money + (money*0.036*year);
        }
    }
    System.out.println(money);

        }
        
}