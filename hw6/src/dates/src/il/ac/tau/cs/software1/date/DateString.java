package dates.src.il.ac.tau.cs.software1.date;

public class DateString implements Date {
	private int day, month, year;
	private String date;
	public DateString(String date) {
		this.date = date;
		String[] parts = date.split("/");
		day = Integer.parseInt(parts[0]);
		month = Integer.parseInt(parts[1]);
		year = Integer.parseInt(parts[2]);
	}
	
	@Override
	public String toString() {
		return date;
	}

	@Override
	public int getDay() {
		return day;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public void addDays(int days) {
		day += days;
		while(day > Date.getDaysInMonth(month)){
			day -= Date.getDaysInMonth(month++);
			if(month > 12){
				year++;
				month = 1;
			}
		}
		while (day <= 0){
			day += Date.getDaysInMonth(month--);
			if(month == 0) {
				year--;
				if(year == 0){
					date = "1/1/1";
					day = 1;
					month = 1;
					year = 1;
					return;
				}
				month = 12;
			}
		}
		date = day + "/" + month + "/" + year;
	}

}
