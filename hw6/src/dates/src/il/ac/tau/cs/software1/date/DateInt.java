package il.ac.tau.cs.software1.date;

public class DateInt implements Date {
	private int date;
	public DateInt(int date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return getDay() + "/" + getMonth() + "/" + getYear();
	}
	
	@Override
	public int getDay() {
		int days = (date % 365);
		if(days == 0)
			return 1;
		for (int i = 1; i <= 12; i++) {
			days -= Date.getDaysInMonth(i);
			if(days < 0) {
				days += Date.getDaysInMonth(i);
			}
		}
		return days + 1;
	}

	@Override
	public int getMonth() {
		int days = (date % 365);
		for (int i = 1; i <= 12; i++) {
			days -= Date.getDaysInMonth(i);
			if(days < 0)
				return i;
		}
		return 1;
	}

	@Override
	public int getYear() {
		return (date / 365) + 1;
	}

	@Override
	public void addDays(int days) {
		date += days;
		if(date <= 0)
			date = 0;
	}

}
