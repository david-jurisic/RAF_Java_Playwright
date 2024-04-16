package ExtendedTypes;

import org.raf3k.shared.DebugLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class HelpersAction {
    public static String randomDigits(int length) {
        try {
            Random rnd = new Random();
            char[] RandomNumber = new char[length];
            RandomNumber[0] = (char) (rnd.nextInt(9) + '1');
            for (int i = 1; i < RandomNumber.length; i++) {
                RandomNumber[i] = (char) (rnd.nextInt(10) + '0');
            }
            return String.valueOf(RandomNumber);
        } catch (Exception ex) {
            DebugLog.add(ex.getMessage(), 1);
            return "";
        }
    }

    public static String nextYear() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 1); //here n is no.of year you want to increase
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            return (format1.format(cal.getTime()));
        } catch (Exception ex) {
            DebugLog.add(ex.getMessage(), 1);
            return "";
        }
    }

    public static String generateOib() {
        StringBuilder oib = new StringBuilder();
        int[] oibDigits = new int[11];
        int remainder = 10;
        for (int i = 0; i < 10; ++i) {
            oibDigits[i] = Integer.parseInt(randomDigits(1));
            oib.append(oibDigits[i]);
            remainder += oibDigits[i];
            remainder %= 10;
            if (remainder == 0)
                remainder = 10;
            remainder *= 2;
            remainder %= 11;
        }
        int controlNumber = 11 - remainder;
        if (controlNumber == 10)
            controlNumber = 0;
        oibDigits[10] = controlNumber;
        oib.append(controlNumber);
        return oib.toString();
    }
}