import java.util.Scanner;

public class BookingSystem {
    public static final int MAX_APPTS = 20;

    public static void main(String[] args) {
        Appointment[] schedule = new Appointment[MAX_APPTS];
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < MAX_APPTS; i++) {
            System.out.println("Enter data for appointment " + (i + 1) + ":");

            System.out.print("Enter patient name: ");
            String patientName = input.nextLine();

            System.out.print("Enter appointment date (YYYY-MM-DD): ");
            String apptDate = input.nextLine();
            while (!isValidDate(apptDate)) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format:");
                apptDate = input.nextLine();
            }

            System.out.print("Enter appointment time (HH:MM): ");
            String apptTime = input.nextLine();
            while (!isValidTime(apptTime)) {
                System.out.println("Invalid time format. Please enter time in HH:MM format:");
                apptTime = input.nextLine();
            }

            System.out.print("Enter dentist name: ");
            String dentistName = input.nextLine();

            Appointment appt = new Appointment(patientName, apptDate, apptTime, dentistName);
            schedule[i] = appt;

            System.out.println("Appointment " + (i + 1) + " booked successfully.");
        }

        System.out.println("All appointment details:");
        for (int i = 0; i < MAX_APPTS; i++) {
            if (schedule[i] != null) {
                System.out.println("Appointment " + (i + 1) + ":");
                System.out.println(schedule[i].toString());
            }
        }

        System.out.println("Enter a date to search for appointments (YYYY-MM-DD):");
        String date = input.nextLine();

        int count = 0;
        for (int i = 0; i < MAX_APPTS; i++) {
            if (schedule[i] != null && schedule[i].getApptDate().equals(date)) {
                count++;
                System.out.println("Appointment " + (i + 1) + ":");
                System.out.println(schedule[i].toString());
            }
        }
        System.out.println("There are " + count + " appointments scheduled for " + date + ".");

        System.out.println("Enter the number of the appointment you want to cancel (1 to " + MAX_APPTS + "):");
        int num = input.nextInt();
        if (num >= 1 && num <= MAX_APPTS && schedule[num - 1] != null) {
            schedule[num - 1] = null;
            System.out.println("Appointment " + num + " cancelled successfully.");
        } else {
            System.out.println("Invalid appointment number or appointment already cancelled.");
        }

        input.close();
    }

    public static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean isValidTime(String time) {
        return time.matches("\\d{2}:\\d{2}");
    }
}
