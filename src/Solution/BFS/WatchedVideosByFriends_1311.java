package Solution.BFS;

import java.util.*;

/**
 * There are n people, each person has a unique id between 0 and n-1.
 * Given the arrays watchedVideos and friends.
 * watchedVideos[i] and friends[i] is the list of watched videos and the list of friends for the person with id = i.
 * Level 1 of videos are all watched videos by your friends, level 2 are all watched videos by the friends of friends.
 * In general, the level k of videos are all watched videos by people with the shortest path equal to k with you.
 * Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing).
 * For videos with the same frequency order them alphabetically from least to greatest.
 *
 * @author BorisMirage
 * Time: 2020/05/21 15:21
 * Created with IntelliJ IDEA
 */

public class WatchedVideosByFriends_1311 {
    /**
     * BFS to find all the kth friends.
     * Then find all the movies that these friends has watched.
     * Sort the order based on frequency in kth friends (not the total occurrence).
     *
     * @param watchedVideos videos each friend watched
     * @param friends       friends of each person
     * @param id            start person
     * @param level         kth friend
     * @return the list of videos ordered by their frequencies (increasing)
     */
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        HashMap<String, Integer> frequency = new HashMap<>();

        boolean[] visited = new boolean[friends.length];
        visited[id] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(id);

        while (level-- > 0) {       // BFS to find kth friend
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int current = q.poll();
                for (int next : friends[current]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }

        for (Integer n : q) {       // build frequency based on kth friends
            for (String v : watchedVideos.get(n)) {
                frequency.put(v, frequency.getOrDefault(v, 0) + 1);
            }
        }

        List<String> out = new LinkedList<>(frequency.keySet());

        out.sort((o1, o2) -> {
            if (frequency.get(o1).equals(frequency.get(o2))) {
                return o1.compareTo(o2);
            } else {
                return frequency.get(o1) - frequency.get(o2);
            }
        });

        return out;
    }

    /**
     * A more general approach. Idea is similar to the previous approach, but the implementation is more complex.
     * Build a bidirectional graph first, then finish the BFS based on graph.
     *
     * @param watchedVideos videos each friend watched
     * @param friends       friends of each person
     * @param id            start person
     * @param level         kth friend
     * @return the list of videos ordered by their frequencies (increasing)
     */
    public List<String> watchedVideosByFriendsGraph(List<List<String>> watchedVideos, int[][] friends, int id, int level) {

        HashMap<Integer, HashSet<Integer>> friendship = new HashMap<>();

        for (int i = 0; i < friends.length; i++) {      // build friendship graph
            for (int j = 0; j < friends[i].length; j++) {
                addFriend(i, friends[i][j], friendship);
                addFriend(friends[i][j], i, friendship);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int size = q.size();
        q.add(id);
        HashSet<Integer> people = new HashSet<>();
        people.add(id);

        while (level >= 0) {        // BFS to find kth friend

            for (int i = 0; i < size; i++) {
                int current = q.poll();
                HashSet<Integer> next = friendship.get(current);
                for (Integer n : next) {
                    if (people.add(n)) {
                        q.add(n);
                    }
                }
            }
            level--;
            size = q.size();
        }

        HashMap<String, Integer> frequency = new HashMap<>();       // build frequency based on kth friends

        for (int i : q) {
            for (String v : watchedVideos.get(i)) {
                frequency.put(v, frequency.getOrDefault(v, 0) + 1);
            }
        }

        List<String> out = new LinkedList<>(frequency.keySet());

        out.sort((o1, o2) -> {
            if (frequency.get(o1).equals(frequency.get(o2))) {
                return o1.compareTo(o2);
            }

            return frequency.get(o1) - frequency.get(o2);
        });

        return out;
    }

    /**
     * Add a new unidirectional friendship (from a to b) to the graph.
     *
     * @param a first person
     * @param b seconding person
     * @param m friendship graph
     */
    private void addFriend(int a, int b, HashMap<Integer, HashSet<Integer>> m) {

        if (!m.containsKey(a)) {
            m.put(a, new HashSet<>());
        }
        m.get(a).add(b);
    }
}
