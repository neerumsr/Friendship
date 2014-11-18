package solution;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FriendsAdmin {
	public ArrayList<People> peopleList;

	public FriendsAdmin() {
		peopleList = new ArrayList<People>();
	}

	public People findByName(String name) {
		if (null == name || name.isEmpty()) {
			return null;
		}
		if (!peopleList.isEmpty()) {
			for (People p : peopleList) {
				if (p.name.equals(name)) {
					return p;
				}
			}
		}
		People p1 = new People(name);
		peopleList.add(p1);
		return p1;
	}

	public void makeFriend(String name1, String name2) {
		if(name1.equals(name2)) return;
		People p1 = this.findByName(name1);
		People p2 = this.findByName(name2);
		if (null != p1 && null != p2) {
			p1.addFriend(p2);
			p2.addFriend(p1);
		}
	}

	public void unmakeFriend(String name1, String name2) {
		People p1 = this.findByName(name1);
		People p2 = this.findByName(name2);
		if (null != p1 && null != p2) {
			p1.removeFriend(p2);
			p2.removeFriend(p1);
		}
	}

	public ArrayList<String> getDirectFriends(String name) {
		People p = this.findByName(name);
		return p.getDirectFriends();
	}

	public ArrayList<String> getIndirectFriends(String name) {
		People p = this.findByName(name);
		Set<String> friendsSet = new HashSet<String>();
		for(People people : peopleList){
			for(People friend : people.friends){
				friendsSet.add(friend.name);
			}
		}
		friendsSet.removeAll(p.getDirectFriends());
		if(friendsSet.contains(name)) friendsSet.remove(name);
		ArrayList<String> indirfriends = new ArrayList<String>(friendsSet);
		return indirfriends;
	}
}
