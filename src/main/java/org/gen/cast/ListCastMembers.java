package org.gen.cast;

import com.google.common.collect.Lists;
import org.gen.factory.Neo4jSessionFactory;
import org.gen.random.Dice;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.util.*;

/**
 * Created by Daniel on 06/06/2017.
 */
public class ListCastMembers {

    private static final String CYPHER_GET_CAST_LIST_EXCEPT_SELF = "MATCH (a:NPC) WHERE NOT a.uuid='$uuid' RETURN a AS NPC";
    private static final String CYPHER_GET_CASTMEMBER_FRIENDS_LIST = "MATCH (a:NPC)-->(b:RELATIONS)-[c:RELATIONSHIP]->(d:RELATIONSHIP_NODE)<-[e:RELATIONSHIP]-(f:RELATIONS)<--(g:NPC) WHERE a.uuid='$uuid' AND NOT g.uuid='$uuid' return g as NPC";
    private static final String CYPHER_GET_CASTMEMBERS_EXCEPT_FRIEND_AND_SELF = "MATCH (a:NPC)-->(b:RELATIONS)-[c:RELATIONSHIP]->(d:RELATIONSHIP_NODE{current:true})<-[e:RELATIONSHIP]-(f:RELATIONS)<--(g:NPC) WHERE a.uuid='$uuid' AND NOT g.uuid='$uuid' AND d.current =true WITH COLLECT( g.uuid) as group MATCH (h:NPC) WITH h,group WHERE NOT h.uuid IN group WITH h WHERE NOT h.uuid='$uuid' RETURN h AS NPC";
    private static final String CYPHER_GET_CASTMEMBER_FRIENDS_OF_FRIENDS_LIST = "MATCH p=(a:NPC)-[:RELATIONS]->(:RELATIONS)-[:RELATIONSHIP]->(b:RELATIONSHIP_NODE)<-[:RELATIONSHIP]-(:RELATIONS)-[:RELATIONSHIP]->(c:RELATIONSHIP_NODE)<-[:RELATIONSHIP]-(:RELATIONS)<-[:RELATIONS]-(d:NPC) WHERE a.uuid = '$uuid' AND b.current = true AND c.current = true RETURN d as NPC";
    private static final String CYPHER_PICK_RANDOM_FRIEND_MEETING_TABLE = "MATCH (a:NPC)-->(b:RELATIONS)-[c:RELATIONSHIP]->(d:RELATIONSHIP_NODE)<-[e:RELATIONSHIP]-(f:RELATIONS)<--(g:NPC) WHERE a.uuid='$uuid' AND NOT g.uuid='$uuid' AND d.current = true WITH d.date as date, d.meanStrength as mean, d.current as current, g.uuid as uuid RETURN date, mean,current, uuid";

    public List<CastMember> getCastMemberList(){
        CastMemberImpl castMemberImpl = new CastMemberImpl();
        Iterable<CastMember> castMembers = castMemberImpl.findAll(1);
        List<CastMember> castMemberList = Lists.newArrayList(castMembers);
        return castMemberList;
    }

    public List<CastMember> getCastMemberListExceptSelf(UUID uuid){
        Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();


        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid.toString());
        Iterable<CastMember> iterablefof = session.query(CastMember.class,CYPHER_GET_CAST_LIST_EXCEPT_SELF,params);
        List<CastMember> friendsOfFriends = Lists.newArrayList(iterablefof);
        return friendsOfFriends;
    }

    public int numberOfFriends(UUID uuid){
        List<CastMember> cast = getCastMemberFriendsList(uuid);
        return cast.size();
    }

    public int numberOfFriendsOfFriends(UUID uuid){
        List<CastMember> cast = getCastMemberFriendsOfFriendsList(uuid);
        return cast.size();
    }

    public CastMember getRandomCastMember(){
        List<CastMember> castMembers = getCastMemberList();
        int size = castMembers.size();
        int rand = (int) Dice.roll("1D"+(size-1));
        CastMember castMember = castMembers.get(rand);
        return castMember;
    }

    public CastMember getRandomCastMemberExceptSelf(UUID uuid){
        List<CastMember> castMembers = getCastMemberListExceptSelf(uuid);
        int size = castMembers.size();
        int rand = (int) Dice.roll("1D"+(size-1));
        CastMember castMember = castMembers.get(rand);
        return castMember;
    }

    public CastMember getRandomCastMemberExceptFriendsAndSelf(UUID uuid){
        List<CastMember> castMembers = getCastMemberExceptFriendsAndSelf(uuid);
        int size = castMembers.size();
        int rand = (int) Dice.roll("1D"+(size-1));
        CastMember castMember = castMembers.get(rand);
        return castMember;
    }

    public CastMember getRandomCastFriendOfFriends(UUID uuid){
        List<CastMember> castMembers =getCastMemberFriendsOfFriendsList(uuid);
        int size = castMembers.size();
        int rand = 0;
        if (size == 1){
            rand =0;
        }
        else {
            rand = (int) Dice.roll("1D"+(size-1));
        }

        CastMember castMember = castMembers.get(rand);
        return castMember;
    }

    public List<CastMember> getCastMemberFriendsList(UUID uuid){
       Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid.toString());
       Iterable<CastMember> iterablefof = session.query(CastMember.class,CYPHER_GET_CASTMEMBER_FRIENDS_LIST, params);
       List<CastMember> friends = Lists.newArrayList(iterablefof);
       return friends;
    }

    public List<CastMember> getCastMemberExceptFriendsAndSelf(UUID uuid){
        Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid.toString());

        Iterable<CastMember> iterableFOF = session.query(CastMember.class,CYPHER_GET_CASTMEMBERS_EXCEPT_FRIEND_AND_SELF, params);

        List<CastMember> friendsOfFriends = Lists.newArrayList(iterableFOF);

        return friendsOfFriends;
    }


    public List<CastMember> getCastMemberFriendsOfFriendsList(UUID uuid){
        Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid.toString());

        Iterable<CastMember> iterableFOF = session.query(CastMember.class,CYPHER_GET_CASTMEMBER_FRIENDS_OF_FRIENDS_LIST, params);

        List<CastMember> friendsOfFriends = Lists.newArrayList(iterableFOF);

        return friendsOfFriends;
    }

    public UUID pickRandomFriendMeeting(UUID uuid) {

        Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid.toString());

        Result results = session.query(CYPHER_PICK_RANDOM_FRIEND_MEETING_TABLE,params);

        //Pick friend to based on friendship rating
        //work out what the chance is out of
        long totalMean = 0;
        for (Map<String,Object> result :results) {
            long meanStrength = (long) result.get("mean");
            totalMean = totalMean + meanStrength;
        }

        long rand = (long)Dice.roll("1D"+totalMean);

        String friendUuid = "";
        long count =0;
        for (Map<String,Object> result :results) {

            long meanStrength = (long) result.get("mean");

            if(count<=rand&&rand<=count+meanStrength){
                friendUuid = result.get("uuid").toString();
            }
            count = count + meanStrength;
        }

       // List<CastMember> friendsOfFriends = Lists.newArrayList(iterablefof);
        System.out.println("uuid:= "+ friendUuid);
        return UUID.fromString(friendUuid);
    }

}
