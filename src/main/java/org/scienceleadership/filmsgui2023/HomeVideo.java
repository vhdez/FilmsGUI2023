package org.scienceleadership.filmsgui2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class HomeVideo extends Film {
    // Fields
    private String releaseDate;
    private int allSales;
    private int vhsSales;
    private int dvdSales;
    private int bluraySales;

    // Constructors
    HomeVideo(int rank, String title, int releaseYear, long gross, String releaseDate, int allSales, int vhsSales, int dvdSales, int bluraySales) {
        super(rank, title, releaseYear, gross);
        this.releaseDate = releaseDate;
        this.allSales = allSales;
        this.vhsSales = vhsSales;
        this.dvdSales = dvdSales;
        this.bluraySales = bluraySales;
    }

    // Setters/Getters

    String getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    int getAllSales() {
        return allSales;
    }

    void setAllSales(int allSales) {
        this.allSales = allSales;
    }

    int getVhsSales() {
        return vhsSales;
    }

    void setVhsSales(int vhsSales) {
        this.vhsSales = vhsSales;
    }

    int getDvdSales() {
        return dvdSales;
    }

    void setDvdSales(int dvdSales) {
        this.dvdSales = dvdSales;
    }

    int getBluraySales() {
        return bluraySales;
    }

    void setBluraySales(int bluraySales) {
        this.bluraySales = bluraySales;
    }

    // Methods
    public String toString() {
        String description = "HomeVideo rank #" + getRank();
        description = description + " is " + this.getTitle();
        description = description + ".  It was released on " + getReleaseDate();
        description = description + ", and grossed $" + getGross();
        description = description + ".  It had " + getAllSales();
        description = description + " total sales (vhs: " + getVhsSales();
        description = description + " dvd: " + getDvdSales();
        description = description + " blu-ray: " + getBluraySales()+ ").";
        return description;
    }

    static void readAllData() {
        Scanner sc = null;
        try {
            File file = new File("TopHomeVideoData"); // java.io.File
            sc = new Scanner(file);     // java.util.Scanner
            String line;
            int rank = 1;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\t");

                String name = lineScanner.next();

                String date = lineScanner.next();
                int commaLocation = date.lastIndexOf(",");
                int yearLocation = commaLocation + 2;
                // extract year from date by removing text before comma + 2
                String year = date.substring(yearLocation);
                int yearNumber = Integer.parseInt(year);

                // allSales needs commas and [ bracket removed from it
                String allSales = lineScanner.next();
                // remove commas from allSales
                String allSalesClean = allSales.replace(",","");
                // remove [] from end of allSales
                int firstBracketLocation = allSalesClean.indexOf("[");
                if (firstBracketLocation >= 0) {
                    allSalesClean = allSalesClean.substring(0, firstBracketLocation);
                }
                // remove any spaces (whitespace) from allSales
                allSalesClean = allSalesClean.trim();
                int allSalesNumber = Integer.parseInt(allSalesClean);

                String vhsSales = lineScanner.next();
                // remove commas from vhsSales
                String vhsSalesClean = vhsSales.replace(",","");
                // remove [] from end of vhsSales
                firstBracketLocation = vhsSalesClean.indexOf("[");
                if (firstBracketLocation >= 0) {
                    vhsSalesClean = vhsSalesClean.substring(0, firstBracketLocation);
                }
                // detect if vhsSales is "-" which means it was never released on VHS
                int vhsSalesNumber = 0;
                if (!vhsSalesClean.equalsIgnoreCase("—")) {
                    vhsSalesNumber = Integer.parseInt(vhsSalesClean);
                }

                String dvdSales = lineScanner.next();
                // remove commas from dvdSales
                String dvdSalesClean = dvdSales.replace(",","");
                // remove [] from end of dvdSales
                firstBracketLocation = dvdSalesClean.indexOf("[");
                if (firstBracketLocation >= 0) {
                    dvdSalesClean = dvdSalesClean.substring(0, firstBracketLocation);
                }
                // remove any spaces (whitespace) from dvdSales
                dvdSalesClean = dvdSalesClean.trim();
                int dvdSalesNumber = Integer.parseInt(dvdSalesClean);

                String bluraySales = lineScanner.next();
                // remove commas from bluraySales
                String bluraySalesClean = bluraySales.replace(",","");
                // remove [] from end of bluraySales
                firstBracketLocation = bluraySalesClean.indexOf("[");
                if (firstBracketLocation >= 0) {
                    bluraySalesClean = bluraySalesClean.substring(0, firstBracketLocation);
                }
                // detect if bluraySales is "-" which means it was never released on VHS
                int blueraySalesNumber = 0;
                if (!bluraySalesClean.equalsIgnoreCase("—")) {
                    blueraySalesNumber = Integer.parseInt(bluraySalesClean);
                }

                String revenue = lineScanner.next();
                // remove commas from revenue
                String revenueClean = revenue.replace(",","");
                // remove $ sign from revenue
                revenueClean = revenueClean.replace("$","");
                // remove [] from end of revenue
                firstBracketLocation = revenueClean.indexOf("[");
                if (firstBracketLocation >= 0) {
                    revenueClean = revenueClean.substring(0, firstBracketLocation);
                }
                int revenueNumber = Integer.parseInt(revenueClean);

                new HomeVideo(rank, name,yearNumber,revenueNumber,date,allSalesNumber,vhsSalesNumber,dvdSalesNumber,blueraySalesNumber);
                rank = rank + 1;
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (sc != null) sc.close();
        }
    }
}
