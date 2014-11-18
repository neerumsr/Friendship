package solution;

import java.util.ArrayList;

public class People {
	public String name;
	public ArrayList<People> friends;

	public People(String name) {
		this.name = name;
		this.friends = new ArrayList<People>();
	}

	public boolean addFriend(People p1) {
		if (!friends.contains(p1)) {
			friends.add(p1);
			return true;
		}
		return false;
	}

	public boolean removeFriend(People p1) {
		if (friends.contains(p1)) {
			friends.remove(p1);
			return true;
		}
		return false;
	}

	public ArrayList<String> getDirectFriends() {
		ArrayList<String> directFriends = new ArrayList<String>();
		if (null == friends || friends.isEmpty()) {
			return null;
		}
		for (People p : friends) {
			directFriends.add(p.name);
		}
		return directFriends;
	}
}
