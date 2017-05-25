package org.gen.file;

import org.apache.commons.lang3.text.WordUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 19/05/2017.
 */
public class SortFile {
    public static void main(String args[]) {
        //this will close the resources automatically
        //even if an exception rises

        Map<String,FileCareer> carrers = new HashMap<>();
        String careerName="";
        StringBuilder stats = new StringBuilder();
        StringBuilder skills = new StringBuilder();
        StringBuilder talents = new StringBuilder();
        StringBuilder trappings = new StringBuilder();
        StringBuilder careerEntries = new StringBuilder();
        StringBuilder careerExists = new StringBuilder();
        String line;
        BufferedReader br;
        try {
            InputStream fis = new FileInputStream("C:\\Users\\Daniel\\Documents\\Roleplay\\Plot Gen\\Cypher Queries\\Creation\\Basic careers");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            br = new BufferedReader(isr);

            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                // Pick out careers
                final String regex = "^—\\s(.+)\\s—";
                final String string = "— baIlIff —";

                final Pattern pattern = Pattern.compile(regex);
                final Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    if(!matcher.group(0).contains("Advance")) {
                        if(matcher.group(0).chars().filter(num -> num == '—').count() == 2) {
                      //      System.out.println("Full match: " + matcher.group(0));
                            for (int i = 1; i <= matcher.groupCount(); i++) {
                               careerName = WordUtils.capitalize(matcher.group(i).toLowerCase(),' ','-');
                            }

                            FileCareer fileCareer = new FileCareer();
                            fileCareer.setName(careerName);
                            carrers.put(careerName,fileCareer);
                        }

                    }
                }

//                final Pattern patternStats = Pattern.compile("([+\\d%—]+)\\s?", Pattern.CASE_INSENSITIVE);
//                final Matcher matcherStats = patternStats.matcher(line);
//
//                while (matcherStats.find()) {
//                    System.out.println("Full match: " + matcherStats.group(0));
//                    for (int i = 1; i <= matcherStats.groupCount(); i++) {
//                        stats = stats + matcherStats.group(i)+",";
//                    }
//                }
//

            }

            fis = new FileInputStream("C:\\Users\\Daniel\\Documents\\Roleplay\\Plot Gen\\Cypher Queries\\Creation\\Basic careers");
            isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            br = new BufferedReader(isr);

            careerName ="";

            boolean skillslogic = false;
            boolean talentlogic = false;
            boolean trappingslogic = false;
            boolean careerEntrieslogic = false;
            boolean careerExistlogic = false;

            int catchStats = 0;

            while ((line = br.readLine()) != null) {

                final Pattern pattern = Pattern.compile("—\\s(.+)\\sAdvance Scheme\\s—");
                final Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    if(matcher.group(0).chars().filter(num -> num == '—').count() == 2) {
                        //      System.out.println("Full match: " + matcher.group(0));
                        for (int i = 1; i <= matcher.groupCount(); i++) {
                            if(!careerName.equals("")) {
                                carrers.get(careerName).setStats(stats.toString());
                                carrers.get(careerName).setSkills(skills.toString());
                                carrers.get(careerName).setTalents(talents.toString());
                                carrers.get(careerName).setTrappings(trappings.toString());
                                carrers.get(careerName).setCareerEntries(careerEntries.toString());
                                carrers.get(careerName).setCareerExits(careerExists.toString());


                            }
                            careerName = WordUtils.capitalize(matcher.group(i).toLowerCase(),' ','-');
                            stats = new StringBuilder();
                            skills = new StringBuilder();
                            talents = new StringBuilder();
                            trappings = new StringBuilder();
                            careerEntries = new StringBuilder();
                            careerExists = new StringBuilder();
                        }
                    }
                }

                if(line.equals("Main Profile")) {
                    catchStats = 16;
                }

                final Pattern patternStats = Pattern.compile("([+\\d%—]+)\\s?", Pattern.CASE_INSENSITIVE);
                final Matcher matcherStats = patternStats.matcher(line);
              //  System.out.println("Line: " + line);
                while (matcherStats.find()) {
                 //   System.out.println("Full match: " + matcherStats.group(0));
                    if(catchStats > 0) {
                        for (int i = 1; i <= matcherStats.groupCount(); i++) {
                            stats.append(matcherStats.group(i)).append(",");
                        }
                        catchStats--;
                    }
                }

                if(line.startsWith("—")||line.matches("^\\d+")||line.startsWith("Note")){
                    careerExistlogic = false;
                }

                if(line.startsWith("Career Exits:")){
                    skillslogic = false;
                    talentlogic = false;
                    trappingslogic = false;
                    careerEntrieslogic = false;
                    careerExistlogic = true;

                    careerExists = new StringBuilder(line.substring(14));
                } else {
                    if(careerExistlogic){
                        careerExists.append(" ").append(line);
                    }
                }

                if(line.startsWith("Career Entries:")){
                    skillslogic = false;
                    talentlogic = false;
                    trappingslogic = false;
                    careerEntrieslogic = true;
                    careerExistlogic = false;

                    careerEntries = new StringBuilder(line.substring(16));
                } else {
                    if(careerEntrieslogic){
                        careerEntries.append(" ").append(line);
                    }
                }

                if(line.startsWith("Trappings:")) {
                    skillslogic = false;
                    careerEntrieslogic = false;
                    careerExistlogic = false;
                    talentlogic = false;
                    trappingslogic = true;
                    trappings = new StringBuilder(line.substring(11));
                } else {
                    if(trappingslogic){
                        trappings.append(" ").append(line);
                    }
                }


                if(line.startsWith("Talents:")) {

                    trappingslogic = false;
                    careerEntrieslogic = false;
                    careerExistlogic = false;
                    skillslogic = false;
                    talentlogic = true;
                    talents = new StringBuilder(line.substring(9));
                } else {
                    if(talentlogic) {
                        talents.append(" ").append(line);
                    }
                }

                if(line.startsWith("Skills:")) {

                    talentlogic = false;
                    trappingslogic = false;
                    careerEntrieslogic = false;
                    careerExistlogic = false;
                    skillslogic = true;
                    skills = new StringBuilder(line.substring(8));
                } else {
                    if(skillslogic) {
                        skills.append(" ").append(line);
                    }
                }



            }

            if(!careerName.equals("")) {
                carrers.get(careerName).setStats(stats.toString());
                carrers.get(careerName).setSkills(skills.toString());
                carrers.get(careerName).setTalents(talents.toString());
                carrers.get(careerName).setTrappings(trappings.toString());
                carrers.get(careerName).setCareerEntries(careerEntries.toString());
                carrers.get(careerName).setCareerExits(careerExists.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        carrers.forEach((string,career)->{
            System.out.println("career: " + career.getName());
            System.out.println("stats: " + career.getStats());
            System.out.println("skills: " + career.getSkills());
            System.out.println("talents: " + career.getTalents());
            System.out.println("trappings: " + career.getTrappings());
            System.out.println("career entries: " + career.getCareerEntries());
            System.out.println("career exites: " + career.getCareerExits());

        });
    }
}
