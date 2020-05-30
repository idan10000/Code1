package il.ac.tau.cs.software1.date;

public interface Date {

    public static int getDaysInMonth(int month) {
        switch (month) {
            case 1:
            case 7:
            case 3:
            case 5:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }


    public String toString();

    public int getDay();

    public int getMonth();

    public int getYear();

    public void addDays(int days);

    public default int differenceInDays(Date other) {
        int days = 0;
        days += other.getDay() - getDay();
        int months = other.getMonth() - getMonth();
        if(months != 0) {
            int initialMonth;
            if (months > 0)
                initialMonth = other.getMonth();
            else
                initialMonth = getMonth();
            for (int i = 0; i < Math.abs(months); i++) {
                days += getDaysInMonth(initialMonth++);
            }
        }
        days += (other.getYear() - getYear()) * 365;
        return days;
    }

    public default boolean isBetweenDates(Date date1, Date date2) {
        int diff1 = differenceInDays(date1);
        int diff2 = differenceInDays(date2);
        return (diff1 >= 0 && diff2 <= 0 || diff1 <= 0 && diff2 >= 0);
    }
}
