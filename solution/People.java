package solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

	public ArrayList<String> getIndirectFriends() {
		// If this people don't have direct friends, then we say he doesn't have indirect neither.
		if (null == friends || friends.isEmpty()) {
			return null;
		}
		// Use a set to save all his direct friend's friends, then remove direct friends
		Set<People> friendsSet = new HashSet<People>();
		for(People dirFriend : friends) {
			for(People indirFriend: dirFriend.friends)
				friendsSet.add(indirFriend);
		}
		// Make sure friendSet don't contain direct friends
		for(People p : friends){
			if(friendsSet.contains(p)){
				friendsSet.remove(p);
			}
		}
		if(friendsSet.contains(this))
			friendsSet.remove(this);
		
		// Check whether friendSet is empty
		if(null == friendsSet || friendsSet.isEmpty()){
			return null;
		}
		
		// return indirect friend's name
		ArrayList<String> indirectFriends = new ArrayList<String>();
		for (People p : friendsSet) {
			indirectFriends.add(p.name);
		}
		return indirectFriends;
	}
}
