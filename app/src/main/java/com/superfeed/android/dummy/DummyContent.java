package com.superfeed.android.dummy;

import com.superfeed.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<Integer, DummyItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
        addItem(new DummyItem(0, "Grains", "Bread, pasta, etc.", R.drawable.img_bread));
        addItem(new DummyItem(1, "Chicken", "Chicken and other poultry.", R.drawable.img_chicken));
        addItem(new DummyItem(2, "Meat", "Beef and lamb meat.", R.drawable.img_meat));
        addItem(new DummyItem(3, "Fish", "Fish and seafood products.", R.drawable.img_fish));
        addItem(new DummyItem(4, "Dairy", "Dairy products: milk, cheese, labneh, etc.", R.drawable.img_dairy));
        addItem(new DummyItem(5, "Fruits", "Fruits.", R.drawable.img_fruits));
        addItem(new DummyItem(6, "Vegetables", "Vegetables and legumes.", R.drawable.img_vegetables));
        addItem(new DummyItem(7, "Spices", "All kinds of spices.", R.drawable.img_spices));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem(position, "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final int id;
        public final String content;
        public final String details;
        public final int drawableId;

        public DummyItem(int id, String content, String details, int drawableId) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.drawableId = drawableId;
        }

        @Override
        public String toString() {
            return content;
        }

        public int getDrawable() {
            return drawableId;
        }
    }
}
