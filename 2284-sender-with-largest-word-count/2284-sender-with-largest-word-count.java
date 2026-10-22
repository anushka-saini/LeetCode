

class Solution {
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < messages.length; i++) {
            int words = messages[i].split(" ").length;
            count.put(senders[i], count.getOrDefault(senders[i], 0) + words);
        }

        String ans = "";
        int max = 0;

        for (String sender : count.keySet()) {
            if (count.get(sender) > max ||
                (count.get(sender) == max && sender.compareTo(ans) > 0)) {
                max = count.get(sender);
                ans = sender;
            }
        }

        return ans;
    }
}
