package il.ac.tau.cs.software1.date;

public class DateArray implements Date {

	int[] date;
	public DateArray(int[] date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return date[2] + "/" + date[1] + "/" + date[0];
	}

	@Override
	public int getDay() {
		return date[2];
	}

	@Override
	public int getMonth() {
		return date[1];
	}

	@Override
	public int getYear() {
		return date[0];
	}

	@Override
	public void addDays(int days) {
		date[2] += days;
		while(date[2] > Date.getDaysInMonth(date[1])){
			date[2] -= Date.getDaysInMonth(date[1]++);
			if(date[1] > 12){
				date[0]++;
				date[1] = 1;
			}
		}
		while (date[2] <= 0){
			date[2] += Date.getDaysInMonth(date[1]--);
			if(date[1] == 0) {
				date[0]--;
				if(date[0] == 0){
					date[2] = 1;
					date[1] = 1;
					date[0] = 1;
					return;
				}
				date[1] = 12;
			}
		}
	}
	
}
