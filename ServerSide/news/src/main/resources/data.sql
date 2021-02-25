DROP TABLE IF EXISTS newsfeed;

CREATE TABLE newsfeed (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL
);

INSERT INTO newsfeed (name, url) VALUES
  ('Медуза', 'https://meduza.io/rss/all'),
  ('Sports.ru', 'https://www.sports.ru/rss/all_news.xml'),
  ('Лента', 'https://lenta.ru/rss'),
  ('РБК', 'http://static.feed.rbc.ru/rbc/logical/footer/news.rss'),
  ('Коммерсантъ', 'https://www.kommersant.ru/RSS/main.xml'),
  ('Российская газета', 'https://rg.ru/xml/index.xml'),
  ('Ведомости', 'https://www.vedomosti.ru/rss/news'),
  ('Газета.ру', 'https://www.gazeta.ru/export/rss/first.xml'),
  ('Интерфакс', 'https://www.interfax.ru/rss.asp'),
  ('CNN', 'http://rss.cnn.com/rss/edition.rss'),
  ('Al Jazeera', 'https://www.aljazeera.com/xml/rss/all.xml'),
  ('Russia Today', 'https://russian.rt.com/rss'),
  ('The New York Times', 'https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml'),
  ('BBC', 'http://feeds.bbci.co.uk/news/rss.xml'),
  ('Sky Sports', 'https://www.skysports.com/rss/12040');
