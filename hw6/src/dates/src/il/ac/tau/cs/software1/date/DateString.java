package il.ac.tau.cs.software1.date;

public class DateString implements Date {
	private String date;
	public DateString(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return date;
	}

	@Override
	public int getDay() {
		return Integer.parseInt(date.split("/")[0]);
	}

	@Override
	public int getMonth() {
		return Integer.parseInt(date.split("/")[1]);
	}

	@Override
	public int getYear() {
		return Integer.parseInt(date.split("/")[2]);
	}

	@Override
	public void addDays(int days) {
		String[] parts = date.split("/");
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);
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
