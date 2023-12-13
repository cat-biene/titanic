package titanik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TrainAppl {

    public static void main(String[] args) {

        // 1. Calculate the total cost of travel.
        // 2. Calculate the average fare for travel classes 1, 2, 3.
        // 3. Calculate the total number of surviving and dead passengers.
        // 4 Count the total number of surviving and deceased men, women and children (under 18 years of age).

        // read data train.csv file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/titanik/train.csv"))) {
            String str = bufferedReader.readLine();
            String[] cells = str.split(",");
            printCells(cells);

            double totalCoast = 0;
            double totalFareClass1 = 0;
            double totalFareClass2 = 0;
            double totalFareClass3 = 0;
            int counterClass1 = 0;
            int counterClass2 = 0;
            int counterClass3 = 0;
            double averageFareClass1 = 0;
            double averageFareClass2 = 0;
            double averageFareClass3 = 0;
            int totalSurviving = 0;
            int totalDead = 0;
            int countSurvivingMen = 0;
            int countDeceasedMen = 0;
            int countSurvivingWomen = 0;
            int countDeceasedWomen = 0;
            int countSurvivingChildren = 0;
            int countDeceasedChildren = 0;

            str = bufferedReader.readLine();
            while (str != null) {
                cells = str.split(",");

                // Calculate the total cost of travel
                totalCoast += Double.parseDouble(cells[10]);

                // Calculate the average fare for travel classes 1, 2, 3
                int pClass = Integer.parseInt(cells[2]);

                switch (pClass) {
                    case 1:
                        counterClass1++;
                        totalFareClass1 += Double.parseDouble(cells[10]);
                        break;
                    case 2:
                        counterClass2++;
                        totalFareClass2 += Double.parseDouble(cells[10]);
                        break;
                    case 3:
                        counterClass3++;
                        totalFareClass3 += Double.parseDouble(cells[10]);
                        break;
                }

                averageFareClass1 = counterClass1 > 0 ? totalFareClass1 / counterClass1 : 0;
                averageFareClass2 = counterClass2 > 0 ? totalFareClass2 / counterClass2 : 0;
                averageFareClass3 = counterClass3 > 0 ? totalFareClass3 / counterClass3 : 0;

                // Calculate the total number of surviving and dead passengers
                int survived = Integer.parseInt(cells[1]);
                if(survived == 1) {
                    totalSurviving++;
                } else {
                    totalDead++;
                }

                // Count the total number of surviving and deceased men, women and children (under 18 years of age)
                String sex = cells[5];
                String ageString = cells[6];
                double age = 0;
                if (!ageString.isEmpty()) {
                    age = Double.parseDouble(ageString);
                }
                if(sex.equalsIgnoreCase("male") && age >= 18 && survived == 1) {
                    countSurvivingMen++;
                } else if(sex.equalsIgnoreCase("male") && age >= 18 && survived == 0) {
                    countDeceasedMen++;
                }
                if(sex.equalsIgnoreCase("female") && age >= 18 && survived == 1) {
                    countSurvivingWomen++;
                } else if(sex.equalsIgnoreCase("female") && age >= 18 && survived == 0){
                    countDeceasedWomen++;
                }
                if(age < 18 && survived == 1) {
                    countSurvivingChildren++;
                } else if(age < 18 && survived == 0){
                    countDeceasedChildren++;
                }
                printCells(cells);
                str = bufferedReader.readLine();
            }
            System.out.println("Total coast of travel: " + totalCoast);
            System.out.println("Average fare for class 1: " + averageFareClass1);
            System.out.println("Average fare for class 2: " + averageFareClass2);
            System.out.println("Average fare for class 3: " + averageFareClass3);
            System.out.println("Total surviving passengers: " + totalSurviving);
            System.out.println("Total deceased passengers: " + totalDead);
            System.out.println("Total surviving men: " + countSurvivingMen);
            System.out.println("Total surviving women: " + countSurvivingWomen);
            System.out.println("Total surviving children (under 18 years of age): " + countSurvivingChildren);
            System.out.println("Total deceased men: " + countDeceasedMen);
            System.out.println("Total deceased women: " + countDeceasedWomen);
            System.out.println("Total deceased children (under 18 years of age): " + countDeceasedChildren);

        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void printCells(String[] cells) {
        for (String cell : cells) {
            System.out.print(cell + "\t");
        }
        System.out.println();
    }
}
