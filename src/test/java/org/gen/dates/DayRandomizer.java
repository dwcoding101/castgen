package org.gen.dates;

import org.gen.cast.CastMember;
import org.gen.cast.CastMemberImpl;
import org.gen.cast.ListCastMembers;
import org.gen.events.EventProcessor;
import org.gen.events.MeetFriendEvent;
import org.gen.random.Dice;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Daniel on 12/06/2017.
 */
public class DayRandomizer {

    public static void main(String[] args) {

        ListCastMembers listCastMembers = new ListCastMembers();
        EventProcessor eventProcessor = new EventProcessor();

        //   CastMember castMember = listCastMembers.getRandomCastMember();

        LocalDate start = LocalDate.of(2003, 07, 1);
        LocalDate currentDate = start;
        int numberOfEvents = 0;
        for (int week = 0; week <= 1; week++) {
            currentDate = currentDate.plusDays(1);
            numberOfEvents = (int) Dice.roll("1D20");

            for (int number = 0; number <= numberOfEvents; number++) {

                UUID uuid = listCastMembers.getRandomCastMember().getUuid();
                CastMember npc = new CastMemberImpl().find(uuid.toString());
                int maxNumberOfFriends = npc.getStat("Fel") / 6;
                int rand = (int) Dice.roll("1D100");
                CastMember npc1 = new CastMemberImpl().find(uuid.toString());
                if (rand >= 1 && rand <= 50) {
                    //meet friend
                    int numberOfFriends = listCastMembers.numberOfFriends(uuid);
                    if (numberOfFriends == 0) {
                        // Pick a random npc
                        UUID newFriend = listCastMembers.getRandomCastMemberExceptSelf(uuid).getUuid();

                        CastMember npc2 = new CastMemberImpl().find(newFriend.toString());

                        int strength1 = randomFriendStrength(uuid);
                        int strength2 = randomFriendStrength(npc2.getUuid());

                        MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(), newFriend.toString(),strength1,strength2,currentDate);
                        eventProcessor.addEvent(meetFriendEvent);
                    } else {
                        //Pick friend to meet
                        UUID friend = listCastMembers.pickRandomFriendMeeting(uuid);
                        int strength1 = randomFriendStrength(uuid);
                        int strength2 = randomFriendStrength(friend);

                        MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(), friend.toString(),strength1,strength2,currentDate);
                        eventProcessor.addEvent(meetFriendEvent);
                        // MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid, , new Date());
                    }


                    if (rand >= 51 && rand <= 98) {

                        numberOfFriends = listCastMembers.numberOfFriends(uuid);

                        if (numberOfFriends <= maxNumberOfFriends) {
                            //meet friend of friend
                            int numberFriendOfFriends = listCastMembers.numberOfFriendsOfFriends(uuid);
                            if (numberFriendOfFriends == 0) {

                            } else {
                                UUID friend = listCastMembers.getRandomCastFriendOfFriends(uuid).getUuid();
                                int strength1 = randomFriendStrength(uuid);
                                int strength2 = randomFriendStrength(friend);

                                MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(), friend.toString(), strength1, strength2, currentDate);
                                eventProcessor.addEvent(meetFriendEvent);
                            }
                        }
                    }

//                    if (rand >= 50 && rand <= 59) {
//                        //meet work mate
//
//                    }

                    if (rand >= 99 && rand <= 100) {
                        //meet new npc
                        if (numberOfFriends <= maxNumberOfFriends) {
                            UUID newFriend = listCastMembers.getRandomCastMemberExceptFriendsAndSelf(uuid).getUuid();

                            int strength1 = randomFriendStrength(uuid);
                            int strength2 = randomFriendStrength(newFriend);

                            MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(), newFriend.toString(), strength1, strength2, currentDate);
                            eventProcessor.addEvent(meetFriendEvent);
                        }
                    }
                }
                System.out.println("Date:= " + currentDate + " Events:= " + numberOfEvents);
            }
        }
    }

   static public int randomFriendStrength(UUID uuid) {
        int strength = 0;

        CastMember npc1 = new CastMemberImpl().find(uuid.toString());
        if (npc1.testStat("Fel","1D100")) {
            strength = npc1.getStat("Fel");
            strength = (strength) /10 *2;
        }
        else {
            strength = npc1.getStat("Fel");
            strength = -(strength /20);
        }

       return strength;
    }
}




