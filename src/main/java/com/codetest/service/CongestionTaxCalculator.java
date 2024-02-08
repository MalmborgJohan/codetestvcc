package com.codetest.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CongestionTaxCalculator {

    private static final List<String> tollFreeVehicles = new ArrayList<>();

    static {
        tollFreeVehicles.add("Motorcycle");
        tollFreeVehicles.add("Tractor");
        tollFreeVehicles.add("Emergency");
        tollFreeVehicles.add("Diplomat");
        tollFreeVehicles.add("Foreign");
        tollFreeVehicles.add("Military");

    }

    public int getTax(String vehicle, Date[] dates) {

        Date intervalStart = dates[0];
        int totalFee = 0;

        for (Date date : dates) {
            int nextFee = GetTollFee(date, vehicle);
            System.out.println("NextFee: " + nextFee);
            int tempFee = GetTollFee(intervalStart, vehicle);
            System.out.println("TempFee: " + tempFee);

            long diffInMillies = date.getTime() - intervalStart.getTime();
            long minutes = diffInMillies / 1000 / 60;
            System.out.println("diffInMillies: " + diffInMillies);
            System.out.println("minutes: " + minutes);
            if (minutes <= 60) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                totalFee += nextFee;
            }
            System.out.println("Total Fee: " + totalFee);
        }                
      
        if (totalFee > 60) totalFee = 60;
        return totalFee;
    }

    private boolean IsTollFreeVehicle(String vehicle) {
        if (vehicle == null) return false;
        System.out.println("VehicleType: " + vehicle);
        return tollFreeVehicles.contains(vehicle);
    }

    public int GetTollFee(Date date, String vehicle)
    {
        if (IsTollFreeDate(date) || IsTollFreeVehicle(vehicle)) return 0;

        int hour = date.getHours();
        int minute = date.getMinutes();
        if (hour == 6 && minute >= 0 && minute <= 29) return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59) return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59) return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59) return 8;
        else if (hour == 15 && minute >= 0 && minute <= 29) return 13;
        else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59) return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29) return 8;
        else return 0;
    }

    private Boolean IsTollFreeDate(Date date)
    {
        int year = date.getYear() + 1900;
        int month = date.getMonth() + 1;
        int day = date.getDay() + 1;
        int dayOfMonth = date.getDate();


        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) return true;

        if (year == 2013) {
            if ((month == 1 && dayOfMonth == 1) ||
                    (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29)) ||
                    (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30)) ||
                    (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
                    (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) ||
                    (month == 7) ||
                    (month == 11 && dayOfMonth == 1) ||
                    (month == 12 && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31))) {

                System.out.println("Date is toll free");
                return true;
            }
        }
        return false;
    }
}
