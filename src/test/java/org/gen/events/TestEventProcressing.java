package org.gen.events;

import org.gen.cast.CastMember;
import org.gen.cast.ListCastMembers;
import org.gen.random.Dice;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.UUID;

/**
 * Created by Daniel on 05/06/2017.
 */
public class TestEventProcressing {

    public static void main(String[] args) {





        ListCastMembers listCastMembers = new ListCastMembers();
        EventProcessor eventProcessor = new EventProcessor();



        CastMember castMember = listCastMembers.getRandomCastMember();

        List<CastMember> cast = listCastMembers.getCastMemberList();


        for (CastMember npc: cast) {
            UUID uuid = npc.getUuid();
            int rand = (int) Dice.roll("1d100");

            if(rand>=01&&rand<=70) {
                //meet friend
                int numberOfFriends = listCastMembers.numberOfFriends(uuid);
                if(numberOfFriends == 0) {
                    // Pick a random npc
                    UUID newFriend = listCastMembers.getRandomCastMemberExceptSelf(uuid).getUuid();


                    MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(),newFriend.toString(), LocalDate.now());
                    eventProcessor.addEvent(meetFriendEvent);
                }
                else {
                    //Pick friend to meet
                    UUID friend = listCastMembers.pickRandomFriendMeeting(uuid);
                    MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(),friend.toString(), LocalDate.now());

                    eventProcessor.addEvent(meetFriendEvent);
                    // MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid, , new Date());
                }
            }

            if(rand>=71&&rand<=89) {
                //meet friend of friend
                int numberFriendOfFriends = listCastMembers.numberOfFriendsOfFriends(uuid);
                if( numberFriendOfFriends == 0) {

                }
                else {
                    UUID friend = listCastMembers.getRandomCastFriendOfFriends(uuid).getUuid();
                    MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(),friend.toString(), LocalDate.now());

                    eventProcessor.addEvent(meetFriendEvent);
                }


            }

            if(rand>=90&&rand<=99) {
                //meet work mate

            }

            if(rand>=100&&rand<=100) {
                //meet friend
                UUID newFriend = listCastMembers.getRandomCastMemberExceptSelf(uuid).getUuid();
                MeetFriendEvent meetFriendEvent = new MeetFriendEvent(uuid.toString(),newFriend.toString(), LocalDate.now());
                eventProcessor.addEvent(meetFriendEvent);
            }
        }

      //  List<CastMember> castList = listCastMembers.getCastMemberFriendsList(castMember.getUuid());



        Date date = new Date();
     //   MeetFriendEvent meetFriendEvent = new MeetFriendEvent("f4878894-3fc4-4906-92ee-c273e69b38f2", "81065eff-599b-480b-b482-22bbe30006d9", date);

        UUID friend = listCastMembers.pickRandomFriendMeeting(UUID.fromString("e9d1d2e5-b46e-4efc-b2dc-68ed07a6c95c"));

        System.out.println(friend.toString());
      //  eventProcessor.addEvent(meetFriendEvent);

    }
}
