package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fruits")
public class FruitsController {
    @GetMapping("/all")
    public ResponseEntity<List<Fruit>> all() {

        Object[][] rawFruitsData = {
                {1, "Apple", "🍎", "Red", true, 45, 2.30, "Apples are one of the most popular fruits worldwide and come in thousands of varieties..."},
                {2, "Lemon", "🍋", "Yellow", false, 12, 1.50, "Lemons are highly acidic citrus fruits known for their bright yellow zest..."},
                {3, "Blueberry", "🫐", "Blue", true, 150, 4.20, "These tiny, indigo-colored berries are celebrated as a nutrient-dense superfood..."},
                {4, "Avocado", "🥑", "Green", false, 8, 3.10, "Often referred to as a 'superfood,' avocados are unique for their high healthy fat content..."},
                {5, "Mango", "🥭", "Orange", true, 22, 2.80, "Known as the 'king of fruits' in many cultures, mangos are juicy, tropical stone fruits..."},
                {6, "Banana", "🍌", "Yellow", true, 85, 1.20, "Bananas are a convenient, naturally wrapped snack known for being high in potassium..."},
                {7, "Strawberry", "🍓", "Red", true, 60, 3.50, "Strawberries are beloved for their bright red color and iconic heart shape..."},
                {8, "Grapes", "🍇", "Purple", true, 40, 2.70, "Grapes grow in clusters and have been cultivated for thousands of years..."},
                {9, "Watermelon", "🍉", "Green", true, 15, 5.60, "This massive summer fruit is composed of about 92% water, making it incredibly hydrating..."},
                {10, "Orange", "🍊", "Orange", true, 55, 2.00, "Oranges are famous for their high Vitamin C content and vibrant, citrusy aroma..."},
                {11, "Pineapple", "🍍", "Yellow", true, 18, 3.80, "The pineapple is a tropical fruit with a tough, spiky exterior and a sweet, tangy yellow interior..."},
                {12, "Peach", "🍑", "Pink", true, 30, 2.90, "Peaches are soft, fuzzy stone fruits that are synonymous with summertime..."},
                {13, "Cherry", "🍒", "Red", true, 100, 4.50, "Cherries are small, round drupes that range from bright red to nearly black in color..."},
                {14, "Kiwi", "🥝", "Brown", true, 42, 3.20, "Kiwifruit has a unique brown, fuzzy skin that hides a vibrant green interior..."},
                {15, "Pear", "🍐", "Green", true, 25, 2.40, "Pears have a distinct bell shape and a texture that can be either crisp or buttery..."},
                {16, "Coconut", "🥥", "Brown", true, 10, 3.50, "Coconuts are versatile fruits that provide water, milk, oil, and tasty meat..."},
                {17, "Pomegranate", "🍎", "Red", true, 20, 3.80, "The pomegranate is known for its leathery skin and hundreds of juicy red seeds called arils..."},
                {18, "Melon", "🍈", "Green", true, 12, 4.00, "Honeydew melons are characterized by their smooth, pale green skin and sweet, succulent flesh..."},
                {19, "Tangerine", "🍊", "Orange", true, 48, 2.50, "Tangerines are a type of mandarin orange that are smaller and easier to peel than standard oranges..."},
                {20, "Lime", "🍋‍🟩", "Green", false, 35, 1.80, "Limes are small, green citrus fruits that are essential for their high acidity and zesty flavor..."},
                {21, "Grapefruit", "🍊", "Pink", false, 14, 2.60, "Grapefruits are large citrus fruits known for their unique combination of bitter, sour, and sweet notes..."},
                {22, "Raspberry", "🍓", "Red", true, 90, 4.10, "Raspberries are delicate, hollow berries that come in a variety of colors..."},
                {23, "Papaya", "🥭", "Orange", true, 11, 3.20, "Papayas are tropical fruits with vibrant orange flesh and a cluster of black seeds in the center..."},
                {24, "Dragon Fruit", "🌵", "Pink", true, 9, 5.00, "Also known as pitaya, this fruit is famous for its stunning pink skin and speckled white or red interior..."},
                {25, "Apricot", "🍑", "Orange", true, 33, 3.00, "Apricots are small, golden-orange stone fruits with a smooth, velvety skin..."},
                {26, "Plum", "🟣", "Purple", true, 28, 2.70, "Plums are juicy drupes that come in a wide range of colors from yellow to deep purple..."},
                {27, "Fig", "🍷", "Purple", true, 16, 4.30, "Figs are unique, teardrop-shaped fruits with a honey-like sweetness and a chewy texture..."},
                {28, "Blackberry", "🫐", "Black", true, 75, 3.90, "Blackberries are aggregate fruits that grow on thorny bushes and turn a deep, glossy black..."},
                {29, "Cantaloupe", "🍈", "Orange", true, 13, 4.20, "Cantaloupes are a variety of muskmelon with a netted rind and a sweet, orange interior..."},
                {30, "Guava", "🍐", "Green", true, 19, 3.60, "Guavas are tropical fruits with a fragrance so strong it can fill an entire room..."},
                {31, "Passion Fruit", "🟣", "Purple", false, 24, 3.90, "Passion fruits have a wrinkled purple or yellow rind that protects a mass of jelly-like seeds..."},
                {32, "Lychee", "🔴", "Red", true, 50, 4.00, "Lychees are small, round fruits from Southeast Asia with a rough, red outer shell..."},
                {33, "Persimmon", "🍅", "Orange", true, 17, 3.50, "Persimmons look somewhat like orange tomatoes and have a unique, honey-like flavor..."},
                {34, "Cranberry", "🍒", "Red", false, 120, 2.80, "Cranberries are small, hard red berries that are very tart and rarely eaten raw..."},
                {35, "Date", "🫘", "Brown", true, 200, 5.20, "Dates are the fruit of the date palm and are often referred to as 'nature's candy'..."},
                {36, "Jackfruit", "🌳", "Green", true, 5, 6.00, "Jackfruit is the largest tree-borne fruit in the world, sometimes weighing up to 80 pounds..."},
                {37, "Starfruit", "⭐", "Yellow", true, 14, 3.70, "Also known as carambola, this fruit gets its name from the star shape it forms when sliced crosswise..."},
                {38, "Durian", "🦔", "Green", true, 3, 6.50, "Durian is famous for its large size, spiky exterior, and an incredibly strong odor..."},
                {39, "Clementine", "🍊", "Orange", true, 65, 2.90, "Clementines are a hybrid between a mandarin and a sweet orange, known for being seedless..."},
                {40, "Mulberry", "🍇", "Black", true, 40, 3.80, "Mulberries look similar to long blackberries and grow on trees rather than bushes..."},
                {41, "Kumquat", "🟠", "Orange", false, 22, 2.20, "Kumquats are tiny citrus fruits that are unique because the peel is sweet while the juice is very sour..."},
                {42, "Rhubarb", "🪴", "Red", false, 18, 1.90, "While botanically a vegetable, rhubarb is legally considered a fruit in the US..."},
                {43, "Gooseberry", "🟢", "Green", false, 30, 2.40, "Gooseberries are small, translucent berries with a striped skin that can be quite tart..."},
                {44, "Plantain", "🍌", "Green", false, 25, 1.80, "Plantains are members of the banana family but are starchier and lower in sugar..."},
                {45, "Elderberry", "🫐", "Purple", false, 80, 4.10, "Elderberries are small, dark purple berries that grow in clusters on elder trees..."},
                {46, "Quince", "🍏", "Yellow", false, 12, 3.20, "A quince looks like a lumpy cross between an apple and a pear and is quite hard when raw..."},
                {47, "Rambutan", "🔴", "Red", true, 45, 4.50, "The rambutan is easily recognized by its bright red skin covered in soft, hair-like spines..."},
                {48, "Longan", "⚪", "Brown", true, 38, 3.90, "Longan is a tropical fruit related to the lychee, often called 'dragon's eye'..."},
                {49, "Olive", "🫒", "Green", false, 300, 2.00, "Olives are small drupes that are technically fruits, though they are treated as savory items..."},
                {50, "Tomato", "🍅", "Red", true, 110, 1.70, "The tomato is a botanical fruit that is used almost exclusively as a vegetable in cooking..."}
        };

        var fruits = Arrays.stream(rawFruitsData)
                .map(this::mapToFruit)
                .toList();

        return ResponseEntity.ok(fruits);
    }

    private Fruit mapToFruit(Object[] row) {
        return new Fruit(
                (int) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (boolean) row[4],
                (int) row[5],
                (double) row[6],
                (String) row[7]
        );
    }
}