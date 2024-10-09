package com.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.objects.GameObject;

public class Inventory {

	private int capacity;
	private Map<String, List<GameObject>> items; // Map to store items based on their type

	public Inventory(int capacity) {
		this.capacity = capacity;
		this.items = new HashMap<>();
	}

	public boolean addItem(GameObject item) {
		String itemType = item.getClass().getSimpleName();

		// Check if inventory has reached capacity
		if (getTotalItemsCount() >= capacity) {
			System.out.println("Inventory is full!");
			return false;
		}

		// Add item to the appropriate list
		items.computeIfAbsent(itemType, k -> new ArrayList<>()).add(item);
		return true;
	}

	public boolean removeItem(GameObject item) {
		String itemType = item.getClass().getSimpleName();
		List<GameObject> itemList = items.get(itemType);

		if (itemList != null && itemList.remove(item)) {
			if (itemList.isEmpty()) {
				items.remove(itemType);
			}
			return true;
		}
		return false;
	}

	public int getTotalItemsCount() {
		return items.values().stream().mapToInt(List::size).sum();
	}

	public boolean containsItem(GameObject item) {
		String itemType = item.getClass().getSimpleName();
		List<GameObject> itemList = items.get(itemType);

		return itemList != null && itemList.contains(item);
	}

	// Count how many items of a certain type are in the inventory
	public int getItemCountByType(String itemType) {
		List<GameObject> itemList = items.get(itemType);
		return itemList != null ? itemList.size() : 0;
	}

	// Get a list of all items in the inventory
	public List<GameObject> getAllItems() {
		List<GameObject> allItems = new ArrayList<>();
		for (List<GameObject> itemList : items.values()) {
			allItems.addAll(itemList);
		}
		return allItems;
	}

	// Display the inventory (for example purposes)
	public void displayInventory() {
		System.out.println("Inventory:");
		for (Map.Entry<String, List<GameObject>> entry : items.entrySet()) {
			String itemType = entry.getKey();
			int count = entry.getValue().size();
			System.out.println(itemType + ": " + count);
		}
	}
}
