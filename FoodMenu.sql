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

