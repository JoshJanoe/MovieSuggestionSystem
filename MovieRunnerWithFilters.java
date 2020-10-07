
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    private ArrayList<String> movies;
    private ThirdRatings tr;
    private int movieSize;
    private int raterSize;
    
    public MovieRunnerWithFilters () {
        movies = MovieDatabase.filterBy(new TrueFilter());
        tr = new ThirdRatings();
        movieSize = movies.size();
        raterSize = tr.getRaterSize();
        System.out.println("Total Movies: "+movieSize+"\nTotal Raters: "+raterSize);
    }
    
    public ArrayList<Rating> averageRatings (int minRatings) {
        ArrayList<Rating> avgRatings = tr.getAverageRatingsWithMap(minRatings);
        System.out.println(avgRatings.size()+" movies met the minimum criteria");
        Collections.sort(avgRatings);
        return avgRatings;
    }
    
    public ArrayList<Rating> filteredAverageRatings (int minRatings, Filter f) {
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minRatings,f);
        Collections.sort(avgRatings);
        System.out.println(avgRatings.size()+" movies met the minimum criteria");
        return avgRatings;
    }
    
    public void printAverageRatings () {
        int minRatings = 1; 
        ArrayList<Rating> avgRatings = averageRatings(minRatings);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            if (rating > 0.0) System.out.println("\t"+rating+" \t "+title);
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByYear () {
        int minRatings = 1;
        Filter filter = new YearAfterFilter(2000);
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,filter);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            //String title = sr.getTitle(currID);
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            int year = MovieDatabase.getYear(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+year+" \t "+title);
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByGenre () {
        int minRatings = 1;
        String genre = "Crime";
        Filter filter = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,filter);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            //String title = sr.getTitle(currID);
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            int year = MovieDatabase.getYear(currID);
            String genres = MovieDatabase.getGenres(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+year+"\t"+title+"\t("+genres+")");
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByMinutes () {
        int minRatings = 1;
        int minTime = 110;
        int maxTime = 170;
        Filter filter = new MinutesFilter(minTime,maxTime);
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,filter);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            int runtime = MovieDatabase.getMinutes(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+runtime+" minutes\t"+title);
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByDirector () {
        int minRatings = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        Filter filter = new DirectorsFilter(directors);
        
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,filter);
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            String director = MovieDatabase.getDirector(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+title+"\t"+director);
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByYearAndGenre () {
        int minRatings = 1;
        int year = 1980;
        String genre = "Romance";
        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters multiFilter = new AllFilters();
        multiFilter.addFilter(yearFilter);
        multiFilter.addFilter(genreFilter);
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,multiFilter);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            String genre2 = MovieDatabase.getGenres(currID);
            int year2 = MovieDatabase.getYear(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+title+"\t"+year2+"\t"+genre2);
        }
        System.out.println("Done ============\n");
    }
    
    public void printAverageRatingsByDirectorAndMinutes () {
        int minRatings = 1;
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        int minTime = 30;
        int maxTime = 170;
        Filter directorFilter = new DirectorsFilter(directors);
        Filter timeFilter = new MinutesFilter(minTime,maxTime);
        AllFilters multiFilter = new AllFilters();
        multiFilter.addFilter(directorFilter);
        multiFilter.addFilter(timeFilter);
        ArrayList<Rating> avgRatings = filteredAverageRatings(minRatings,multiFilter);
        
        for (Rating r : avgRatings) {
            String currID = r.getItem();
            String title = MovieDatabase.getTitle(currID);
            double rating = r.getValue();
            String director = MovieDatabase.getDirector(currID);
            int minutes = MovieDatabase.getMinutes(currID);
            if (rating > 0.0) System.out.println("\t"+rating+"\t"+title+"\t"+minutes+" minutes\t"+director);
        }
        System.out.println("Done ============\n");
    }
}
