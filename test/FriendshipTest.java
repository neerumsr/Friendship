package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import solution.FriendsAdmin;

/**
 * @author fan
 *
 */
public class FriendshipTest {

	/**
	 * Test get direct friends
	 */
	@Test
	public void testGetDirectFriends() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Aaron", "Bella");
		admin.makeFriend("Bella", "Cindy");
		admin.makeFriend("Bella", "David");
		admin.makeFriend("David", "Elizabeth");
		admin.makeFriend("Cindy", "Frank");
		ArrayList<String> directFriends = admin.getDirectFriends("David");
		assertTrue(directFriends.contains("Bella"));
		assertTrue(directFriends.contains("Elizabeth"));		
	}
	
	/**
	 * Empty string value for name
	 */
	@Test
	public void testEmptyName()	{
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("", "Aaron");
		assertNull(admin.getDirectFriends("Aaron"));
	}

	/**
	 * Normal string value for name
	 */
	@Test
	public void testNormalName() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Bella", "Aaron");
		assertTrue(admin.getDirectFriends("Aaron").contains("Bella"));
	}
	
	/**
	 * Same names
	 */
	@Test 
	public void testSameName() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Aaron", "Aaron");
		assertNull(admin.getDirectFriends("Aaron"));
	}
	
	/**
	 * Normal unmake Friend
	 */
	@Test
	public void testUnmakeFriend() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Bella", "Aaron");
		admin.unmakeFriend("Bella", "Aaron");
		assertNull(admin.getDirectFriends("Aaron"));
		assertNull(admin.getDirectFriends("Bella"));
	}
	
	/**
	 * Unmake not exist Friend
	 */
	@Test
	public void testUnmakeNotExistFriend() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Bella", "Aaron");
		admin.unmakeFriend("Bella", "Baron");
		assertNotNull(admin.getDirectFriends("Aaron"));
		assertNotNull(admin.getDirectFriends("Bella"));
	}
	
	/**
	 * Test get indirect friends in a network
	 */
	@Test
	public void testGetIndirectFriends() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Aaron", "Bella");
		admin.makeFriend("Bella", "Cindy");
		admin.makeFriend("Bella", "David");
		admin.makeFriend("David", "Elizabeth");
		admin.makeFriend("Cindy", "Frank");
		
		ArrayList<String> indirectFriends = admin.getIndirectFriends("David");

		assertTrue(indirectFriends.contains("Aaron"));
		assertTrue(indirectFriends.contains("Cindy"));	
		assertTrue(indirectFriends.contains("Frank"));
		assertFalse(indirectFriends.contains("Bella"));
		assertFalse(indirectFriends.contains("David"));
		assertFalse(indirectFriends.contains("Elizabeth"));
	}
	
	/**
	 * Test get indirect friends along a single chain
	 */
	@Test
	public void testGetIndirectFriendsChain() {
		FriendsAdmin admin = new FriendsAdmin();
		admin.makeFriend("Aaron", "Bella");
		admin.makeFriend("Bella", "Cindy");
		admin.makeFriend("Cindy", "David");
		admin.makeFriend("David", "Elizabeth");
		admin.makeFriend("Elizabeth", "Frank");
		
		ArrayList<String> indirectFriends = admin.getIndirectFriends("Aaron");
		
		assertTrue(indirectFriends.contains("David"));
		assertTrue(indirectFriends.contains("Cindy"));	
		assertTrue(indirectFriends.contains("Frank"));
		assertTrue(indirectFriends.contains("Elizabeth"));
		assertFalse(indirectFriends.contains("Bella"));
		assertFalse(indirectFriends.contains("Aaron"));
	}
}
