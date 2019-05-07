DROP TABLE IF EXISTS FoodMenu;

CREATE TABLE FoodMenu
(
 id INT UNSIGNED NOT NULL AUTO_INCREMENT,
 Name VARCHAR(125) NOT NULL,
 Description VARCHAR(300),
 FoodType VARCHAR(64) ,
 price float(10) Not null,
 
  PRIMARY KEY (id)
);


insert into FoodMenu(Name, Description, FoodType, price)
VALUES ('Marcona almond','Roasted in olive oil with sea salt','Starter', 6),
('Mixed Olives', 'Kalamata and Mt. Athos olives, pickled garlic, red peppers, & caper berries', 'Starter', 5),
('Pancetta crostin', 'Ground pancetta, house made pickle, shallot gel, on crusty bread (three)','Starter',8),
('Boquerones','Marinated white anchovies, pickled onions, sea salt crackers', 'Starter', 8),
('Sardines with sweet Piquillo peppers',null, 'Tinned Seafood', 10),
('Ortiz line caught White Tuna in olive oil', null, 'Tinned Seafood', 13),
('Mussels in Escabeche (oil, vinegar)', null, 'Tinned Seafood', 11),
('Ekone smoked oysters', null, 'Tinned Seafood', 11),
('Ekone Octopus', null, 'Tinned Seafood', 12),
('Spicy tasso ham','Gruyère, caramelized onions, & hot honey on rosemary ciabatta','Sandwiches and Salads', 11),
('Roasted turkey','Dill havarti, argula, red onion, herb mayonnaise on rosemary ciabatta','Sandwiches and Salads',11),
('Smoked trout','Hot-smoked trout, microgreens, pickled onion, herb mayo','Sandwiches and Salads',14),
('Grilled cheese', 'Gouda, havarti, choice of roasted tomatoes or ‘nduja (spicy proscuitto spread) +2', 'Sandwiches and Salads',11),
('Goat cheese', 'Roasted red peppers, arugula, on rosemary ciabatta', 'Sandwiches and Salads',11),
('Chocolate sampler', 'Selection of artisan chocolates and caramels (four)', 'Desserts', 7),
('Cheese board', 'Three cheeses', 'Desserts', 17),
('Charcuterie board', 'Three meats', 'Desserts',18),
('Cheese & charcuterie', 'six meats and cheeses', 'Desserts',28),
('Pimento Cheese', 'Made in house. Roasted pimento, chive, two cheddars', 'Featured Cheeses', 8),
('El Trigal Manchego', 'Raw sheep milk. Firm, buttery, nutty', 'Featured Cheeses', 8),
('Barbers Vintage Reserve Cheddar', 'Cow milk. Firm, rich, sharp', 'Featured Cheeses', 8),
('Beamster classic 18 month Gouda', 'Cow milk. Hard, robust, tangy', 'Featured Cheeses', 8),
('Caseificio dell’Alta Langa Cravanzina', 'Cow & sheep milk. Creamy, subtle grassy tang', 'Featured Cheeses', 8),
('Vermont Creamery Cremont', 'Cow & goat milk. Creamy, herbal, salty with a delicate rind', 'Featured Cheeses', 8),
('Bucheron', 'Goat milk. Subtle citrus flavor with many textures in one', 'Featured Cheeses', 8),
('Chällerhocker', 'Cow milk. Semisoft, silky texture, ripe, fresh', 'Featured Cheeses', 8),
('Meadow Creek Farms Appalachian', 'Cow milk. Semisoft, grassy, earthy, funky', 'Featured Cheeses', 8),
('Duck liver mousse','Smooth mousse with cognac', 'Featured Meats', 10),
('Duck Rillettes','Shredded duck and spices', 'Featured Meats', 10),
('Prosciutto Americano','Smooth, leaan, tender', 'Featured Meats', 10),
('Speck Alto Adige','Dry-cured smoked ham', 'Featured Meats', 10),
('Finocchiona Salami','Slightly sweet all-pork with fennel, dry cured', 'Featured Meats', 10),
('Milano Italian Salami','Mild, tart, peppery flavor', 'Featured Meats', 10),
('Varzi Italian Salami','Coarse bite, notes of nutmeg and sea salt', 'Featured Meats', 10),
('Nduja','Spicy rich prosciutto spread', 'Featured Meats', 10),
('Bresaola','Salt-cured, air-dried beef. Peppery, sweet, aromatic', 'Featured Meats', 10);


DROP TABLE IF EXISTS Suggestions;

CREATE TABLE Suggestions
(
    suggestion VARCHAR(1024) NOT NULL,
    PRIMARY KEY (suggestion)
);

DROP TABLE IF EXISTS TableBooking;

CREATE TABLE TableBooking
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    email VARCHAR(128) NOT NULL,
    booking_date DATE NOT NULL, 
    booking_time DATE NOT NULL,
    number_people INT(32) NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS Subscribers;

CREATE TABLE Subscribers
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    email VARCHAR(128) NOT NULL,      
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS WineCollection;

CREATE TABLE WineCollection
(
 id INT UNSIGNED NOT NULL AUTO_INCREMENT,
 WineName VARCHAR(64) NOT NULL,
 YearofManufacture VARCHAR(4),
 Country VARCHAR(32),
 Description VARCHAR(512),
 Glass_price float(10) ,
 Bottle_price float(10) Not null,
 Wine_type VARCHAR(10) not null,
 isAvailable TINYINT(1) default 1,
 PRIMARY KEY (id)
);


INSERT into WineCollection(WineName,YearofManufacture,Country,Description,Glass_price,Bottle_price,Wine_type) values
('Enjingi Graševina', '2015', 'Croatia', 'Do not adjust your set—you read the name of this wine right. Graševina is a grape that comes across like a mix of white flowers and fresh-cut grass. Lovely spicy floral character mixes with bracing acidity to make this a food wine for Riesling and Sauvignon Blanc lovers alike!', 10,40, 'White' ),
('Horizon de Bichot Chardonna', '2016', 'France ', 'Napa style meets French tradition in this delicious and drinkable Chardonnay, made by Burgundy producer Albert Bichot. Creamy apple, pastry and confectionary notes in the aroma blend with toasty oak-driven flavors of fig, butterscotch of toffee.', 11,44, 'White' ),
('Domaine Aubron Muscadet', '2016', 'France ', 'When it comes to food-friendly white wines, you can’t do much better than Muscadet. Fresh aromas of peach and lemon with a minerally, citrusy character on the palate. Crisp acidity makes this more than a match for seafood—try it with the smoked oysters!', 9,36, 'White' ),
('Freschetto Table White Blend', null, 'Italy', 'Pinot Grigio and Chardonnay blend. Clean, crisp and easily quaffable white.', 4.5,18, 'White' ),
('Cotes de Gascogne White Blend', '2016', 'France', 'Colombard and Ugni Blanc blend. Citrusy, tropical on the nose, creamy bright on the palate. Easy drinking', 5.5,22, 'White' ),
('North by Northwest Chardonnay', '2015', 'USA', 'Flavors of melon and apple melt together in this medium bodied Chard which then rounding out into a rich, smooth, light oaky/buttery finish. Delish.', 6.5,26, 'White' ),
('Planalto Vinho Branco Reserva', '2017', 'Portugal', 'Great Portuguese white blend grown in the northern Douro region, this clean and refreshing wine has intense and complex aromas of tropical and stone fruits, with hints of wild shrubs. Well-integrated acidity makes for a vibrant palate of citrus and white flowers.', 7,28, 'White' ),
('Ribafreixo Barrancoa', '2017', 'Portugal', 'Youthful and irreverent is the way the winemaker describes this fun wine. Crisp citrus and apple in the aroma, with a vibrant palate of stone fruit and melon. Excellent with smoked fish, seafood, ham and turkey!', 8,32, 'White' ),
('Gabriella Pinot Grigio', '2017', 'Italy', 'This ain''t your grandma''s Pinot Grigio...this one actually has flavors! Vivacious, crisp apple/citrus fruit, creamy mouthfeel combined with a lively personality on the palate', 7,28, 'White' ),
('Schwarzbock Grüner', '2016', 'Austria', 'Grüner Veltliner is a dry white wine that grows almost only in Austria. With flavors of green pepper and lime, it is an exotic alternative to Sauvignon Blanc. This one presents a distinct aroma of apple, stone fruit, lentil, and citrus characteristics', 7,34, 'White' ),
('Château de Parenchère Bordeaux Blanc', '2017', 'France', 'Sauvignon Blanc core; Super Dry, Light, Floral, Zesty! Stainless steel fermented & finished, intense white flower/citrus fruit aromas.', 8.5,34, 'White' ),
('Fritz Zimmer Piesporter Michelsberg Riesling Kabinett', '2017', 'Germany', 'Michelsberg is actually the term for Riesling grown between Trittenheim and Minheim in the Middle Mosel region. Ripe apple and citrus in the aroma, with more apple and tropical fruit on the palate. An excellent introduction to the archetypal Rieslings of the Mosel Valley!', 8,32, 'White' ),
('Santa Julia Tardio Late Harvest Torrontes', '2016', 'Argentina', 'Sweet delicious dessert in a glass! Intense flavors of honey, pears, apricots, and peaches. 4 oz. pour', 7,28, 'White' ),
('Solitaire', null, 'Greece', 'Greek moscato-style wine. Light & Delicate & Fruity & Delicious! What a sweet deal for 20 bones!', 6,20, 'White' ),
('Poquito Moscato', null, 'Spain', 'Check out this adorable new product! Semi-sweet, semi-sparkling, full-on moscato wine served in its own little 375ml split bottle...& served with a straw so as not to smear lipstick! Hints of ripe peach, pear, apricot, flowers! “Wine soda pop” FTW', null,10, 'White' ),
('Zardetto Frizzante Prosecco',null, 'Italy','Semi-fizzy fun for your palate! Light & Delicate with cheerful fruit flavors of green and golden apple, white peach and citrus.', 7.5,30, 'Sparkling'),
('Santa Julia Organic Brut Rosé', null,'Argentina', 'BACK BY POPULAR DEMAND!!! This bubbly rosé was such a blast, we had to bring it back! A dry, brisk and refreshing sparkler made from Pinot Noir grapes, so add in hints of strawberry & red berries, on a toasty creamy finish.', 8.5, 34, 'Sparkling'),
('SEX Sparkling Rosé Wine',null, 'USA','Pink hue, red fruit aromas, soft style, delicious sexiness in a bottle: A provocative blend of Pinot Noir, Chardonnay and Muscat grapes that are hand picked and carefully whole-cluster pressed. Finishes dry with a hint of a sweet kiss.', null,30, 'Sparkling'),
('Zenato Bardolino Chiaretto', '2017', 'Italy','Chiaretto is the name of the traditional rosé wine made in the Bardolino region of northeast Italy on Lake Garda. Pretty aromas of strawberries and white flowers complement a zippy acidity and cranberry and juicy cherry flavors. Delicious!', 8,34, 'Rosé'),
('OZV Rosé', null, 'USA', 'An expressive dry, pink wine with floral aromas and flavors of salted watermelon, strawberry and peach…creating the perception of a touch of sweetness on the finish.', 5.5,22, 'Rosé'),
('Vina Skaramuca Plavac Mali', null, 'Croatia', 'You may not know Plavac Mali…but you know its child, Zinfandel. That’s right, this is the parent of the famous California grape. Cherry and licorice in the aroma, with black fruit, stone, and drying tannins on the palate. Lighter than Zin, but somehow just as powerful!', 12, 48, 'Red'),
('Vigilance Cimarron', '2015', 'USA', 'Classic Rhone blend, but from Lake County California. This wine has enticing aromas of black cherry, vanilla, dried herbs, tobacco on the nose; this is a big wine! The wine tastes smooth, juicy and delicious with rich and vibrant fruit flavors.', 9.5,38, 'Red'),
('Peachy Canyon Zinfandel ', '2015','USA', 'Rich and fruit-forward, this wine begins with a subtle smoky nose full of cedar, cigar box and candied red cherry….then burst on the palate with bright strawberry and over-ripe cherry fruit flavors, with hints of mocha and leather mixed in for a full-bodied wine.', 9.5,38, 'Red'),
('Mas Olivier', '2015', 'France', 'Syrah, Carignan, Grenache blend. Soft aromas of red/black berries, plum, subtle spice and pepper, and hints of coffee. Medium to full bodied wine with solid tannins and fruit and a kiss of caramelized oak on the finish.', 6,24,'Red'),
('Monte Alegre Tempranillo', null, 'Spain', 'Tempranillo, the classic king grape of Spain. Medium Bodied, hints of raspberry red cherry and licorice.', 6,24,'Red'),
('La Vite Montepulciano d''Abruzzo',null, 'Italy','This cherry/herbal/floral and lively, juicy red wine comes from the rugged Abruzzi hills above the Adriatic coast of central Italy...an easy perfect pairing built for any spaghetti or pizza dinner...and a great charcuterie board pairing too.',6,24,'Red'),
('Time Waits For No One','2017', 'Spain', 'Don’t let the skull and crossbones logo scare you…too much. Jumilla is the region of southeastern Spain known for Monastrell, a powerful and high-octane red grape. With rich aromas of plum liqueur and chocolate and a palate of mouthfilling tannins and cherry coridal, this wine won’t wait for you!', 9,36,'Red'),
('Clayhouse Adobe Red', '2013', 'USA', 'This big, bold red is everything you’d want from a Paso Robles offering. Predominantly Zinfandel, Petite Sirah, and Syrah, it offers up a powerful aroma of blackberry, bramble, vanilla and black cherry. Jammy yet soft on the palate, with rich and rounded tannins.', 9,36,'Red'),
('Le Fraghe Bardolino', '2017','Italy','Organic wine! From the shores of Lake Garda! Fruit forward with cherry notes, light on the palate, refreshing, easy drinking. Great introduction to organic & natural wines',9,36,'Red'),
('Lambrusco Grasparossa di Castelvetro',null, 'Italy', 'Wow. Love the Lambrusco: a fizzy red wine! Opens with intense floral, violet & red berry scents. The palate delivers the fruit, tangy acidity, and even a hint of sea salt. Brilliant charcuterie pairing.',10,40,'Red'),
('Tait The Border Crossing Shiraz', '2014', 'Australia', 'Crikey! Strapping Shiraz alert! This is a full-on opulent, voluptuous and a powerhouse of black fruits and chocolate box notes. Fullbodied with lovely richness and plenty of velvety tannins to frame, it finishes with great length. Don’t build a wall for this wine…let it cross the border of your palate!', 13,52, 'Red'),
('Brandborg Benchlands Pinot', '2016', 'USA', 'If you like Oregonian Pinot, this bomb is for you.  The aromas leaping from the glass are a heady combination of dark skinned cherries, blueberries and raspberry, with a pleasing element of toast and spice that complements the fruit. Add in a charge of warmed oak spice and supple tannins and you have a great little Noir. BTW, where or what the heck is an Umpqua?', 13,52,'Red'),
('Brancatelli Valle Dl Stelle','2015','Italy', ' A great 75% Cabernet Sauvignon and 25% Cabernet Franc blend from Tuscany! Rich raspberry and cherry, medium to full body, bright fine tannins and a fresh and silky finish.', 13,52, 'Red'),
('Allessandro Veglio Barolo', '2014','Italy', 'Delicious red ripe fruit and tobacco on the nose. Round, structured earthly flavor with good persistence and smooth tannins. Final taste has a light balsamic note.', 18,54, 'Red'),
('Mollydooker', '2017', 'Australia', ' This extracted Shiraz is unabashedly in-your-face. The aroma is full of spiced plums, blackberry jam and black cherry. Flavors of licorice, vanilla and dark berries combined with hints of coffee and chocolate on the finish. Rich, tannic, and full-bodied, it’s a wine you won’t forget anytime soon.',22,66,'Red');



DROP TABLE IF EXISTS User;

CREATE TABLE User
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    address1 VARCHAR(128) NOT NULL,
    address2 VARCHAR(128),
    city VARCHAR(64) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,      
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS UserPhoto;

CREATE TABLE UserPhoto
(
       id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
       extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
       user_id INT UNSIGNED,
       FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);
