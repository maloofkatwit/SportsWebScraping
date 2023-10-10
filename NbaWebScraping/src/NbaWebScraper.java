import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A web scraper for NBA team statistics using Jsoup.
 */
public class NbaWebScraper {

    /**
     * Scrape NBA team statistics from a webpage.
     *
     * @return An array of TeamStats objects containing team statistics.
     */
    public static TeamStats[] Scrape() {
        final String url = "https://www.espn.com/nba/stats/team";
        TeamStats[] rosterstats = new TeamStats[30];

        try {
            final Document doc = Jsoup.connect(url).get();
            int k = 0;

            for (Element row : doc.select("tbody.Table__TBODY tr")) {
                if (row.select("td:nth-of-type(3)").text().equals("")) {
                    continue; // Skip rows with empty data
                } else {
                    String name = extractTeamName(row); // Extract team name
                    double[] stats = extractStats(row); // Extract team stats

                    rosterstats[k] = new TeamStats(name, stats);
                    k++;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rosterstats;
    }

    /**
     * Extract the team name from the row's HTML code.
     *
     * @param row The HTML Element representing the table row.
     * @return The team name.
     */
    private static String extractTeamName(Element row) {
        Element anchorElement = row.select("a.AnchorLink").first();
        if (anchorElement != null) {
            return anchorElement.text();
        }
        return null;
    }

    /**
     * Extract team statistics from the row's HTML code.
     *
     * @param row The HTML Element representing the table row.
     * @return An array of team statistics.
     */
    private static double[] extractStats(Element row) {
        double[] stats = new double[19];
        for (int i = 1; i <= 19; i++) {
            String nthtype = "td:nth-of-type(" + i + ")";
            stats[i - 1] = Double.parseDouble(row.select(nthtype).text());
        }
        return stats;
    }

    /**
     * Main method to execute the web scraping and display the results.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        TeamStats[] teams = Scrape();
        
        // Define the file path where you want to save the results
        String filePath = "output.txt"; // Update with your desired file path

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
            
            if (teams[0] != null) {
                // Print the table header
                writer.printf("%-20s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s%n",
                    "Team", "GP", "PTS", "FGM", "FGA", "FG%", "3PM", "3PA", "3P%", "FTM", "FTA", "FT%", "OR", "DR", "REB", "AST", "STL", "BLK", "TO", "PF");
                for (TeamStats team : teams) {
                    writer.printf("%-20s %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f%n",
                        team.getName(), team.getGP(), team.getPTS(), team.getFGM(), team.getFGA(), team.getFGP(), team.getTPM(), team.getTPA(),
                        team.getTPP(), team.getFTM(), team.getFTA(), team.getFTP(), team.getOR(), team.getDR(), team.getREB(),
                        team.getAST(), team.getSTL(), team.getBLK(), team.getTO(), team.getPF());
                }
            }
            
            writer.close();
            System.out.println("Results have been written to the file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}