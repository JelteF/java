import java.util.*;

public class Assignment8{
    public static void main(String[] args){
        int day=0, month=0, year=0;
        int startYear=0, endYear=0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter birthday: ");
        String birthday = sc.next();
        System.out.print("Enter interval: ");
        String interval = sc.next();
        if(!checkInput(birthday, interval)){
            System.out.println("This is not the correct input format. The "
                    + "correct one looks like this:\n"
                    + "dd-mm-yyyy for the birthday "
                    + "and yyyy-yyyy for the interval");
            return;
        }
        day=(birthday.charAt(0)-'0')*10 + birthday.charAt(1)-'0';
        month=(birthday.charAt(3)-'0')*10 + birthday.charAt(4)-'0';
        year= (birthday.charAt(6)-'0')*1000 + (birthday.charAt(7)-'0')*100 +
                (birthday.charAt(8)-'0')*10 + birthday.charAt(9)-'0';
        startYear= (interval.charAt(0)-'0')*1000 + (interval.charAt(1)-'0')*100+
                (interval.charAt(2)-'0')*10 + interval.charAt(3)-'0';
        endYear= (interval.charAt(5)-'0')*1000 + (interval.charAt(6)-'0')*100 +
                (interval.charAt(7)-'0')*10 + interval.charAt(8)-'0';
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
        System.out.println(startYear);
        System.out.println(endYear);
        if(!exists(day, month, year)){
            System.out.println("This date does not exist.");
            return;
        }
        System.out.println("These are the years that this date will be on a "
                + "sunday:");
        for(int i = startYear; i<endYear; i++){
            if(month == 2 && day==29 && !schrikkel(i))
                continue;
            if(weekday(day, month, i)==0)
                System.out.print(i + " ");

        }
    }

    static boolean checkInput(String birthday, String interval){
        if(birthday.length()!=10 || interval.length()!=9)
            return false;
        for(int i=0; i<10; i++){
            if(i!=2 && i!=5){
                if (birthday.charAt(i)<'0' || birthday.charAt(i)>'9')
                    return false;
            }
            else
                if(birthday.charAt(i)!='-')
                    return false;
        }
        for(int i=0; i<9; i++){
            if(i!=4){
                if (interval.charAt(i)<'0' || interval.charAt(i)>'9')
                    return false;
            }
            else
                if(interval.charAt(i)!='-')
                    return false;
        }
        return true;
    }

    static boolean schrikkel(int year){
        if(year%4 != 0)
            return false;
        else if(year%100 != 0)
            return true;
        else if(year%400 != 0)
            return false;
        return true;
    }

    static boolean exists(int day, int month, int year){
        if(day<1 || month<1 || year<1 || day>31 || month>12)
            return false;
        if((month==4 || month==6 || month==9 || month==11) && day>30)
            return false;
        if(month==2 && day>29)
            return false;
        if(month==2 && !schrikkel(year) && day>28)
            return false;
        return true;
    }

    static int weekday(int day, int month, int year){
        int days=0;
        if(year*10000 + month*100 + day < 20120101){
            for (int i=2011; i>year; i--){
                if (schrikkel(i))
                    days+=366;
                else
                    days+=365;
            }
            for (int i=12; i>month; i--){
                if(i==2 && schrikkel(year))
                    days+=29;
                else if(i==2)
                    days+=28;
                else if(i==4 || i==6 || i==9 || i==11)
                    days+=30;
                else
                    days+=31;
            }
            if(month==2 && schrikkel(year))
                days+=30-day;
            else if(month==2)
                days+=29-day;
            else if(month==4 || month==6 || month==9 || month==11)
                days+=31-day;
            else
                days+=32-day;

            if(days%7==0)
                return 0;
            else
                return 7-days%7;

        }
        else if(year*10000 + month*100 + day > 20120101){
            for (int i=2012; i<year; i++){
                if (schrikkel(i))
                    days+=366;
                else
                    days+=365;
            }
            for (int i=1; i<month; i++){
                if(i==2 && schrikkel(year))
                    days+=29;
                else if(i==2)
                    days+=28;
                else if(i==4 || i==6 || i==9 || i==11)
                    days+=30;
                else
                    days+=31;
            }
            days+=day-1;
            return days%7;
        }
        return 0;
    }
}