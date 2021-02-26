DROP TABLE IF EXISTS newsfeed;

CREATE TABLE newsfeed (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  language VARCHAR(250) NOT NULL
);

INSERT INTO newsfeed (name, url, language) VALUES
  ('Медуза', 'https://meduza.io/rss/all','Russian'),
  ('Лента', 'https://lenta.ru/rss','Russian'),
  ('РБК', 'http://static.feed.rbc.ru/rbc/logical/footer/news.rss','Russian'),
  ('Коммерсантъ', 'https://www.kommersant.ru/RSS/main.xml','Russian'),
  ('Российская газета', 'https://rg.ru/xml/index.xml','Russian'),
  ('Ведомости', 'https://www.vedomosti.ru/rss/news','Russian'),
  ('Газета.ру', 'https://www.gazeta.ru/export/rss/first.xml','Russian'),
  ('Интерфакс', 'https://www.interfax.ru/rss.asp','Russian'),
  ('CNN', 'http://rss.cnn.com/rss/edition.rss','English'),
  ('Al Jazeera', 'https://www.aljazeera.com/xml/rss/all.xml','English'),
  ('Russia Today', 'https://russian.rt.com/rss','Russian'),
  ('The New York Times', 'https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml','English'),
  ('BBC', 'http://feeds.bbci.co.uk/news/rss.xml','English'),
  ('Sky Sports', 'https://www.skysports.com/rss/12040','English'),
  ('Известия', 'https://iz.ru/xml/rss/all.xml','Russian'),
  ('Forbes', 'https://www.forbes.ru/newrss.xml','Russian'),
  ('GQ','https://www.gq.ru/feed/all-content/rss','Russian'),
  ('Дождь','https://tvrain.ru/export/rss/all.xml','Russian'),
  ('Esquire', 'https://esquire.ru/out/feedburner.xml','Russian'),
  ('Daily Mail', 'https://www.dailymail.co.uk/articles.rss','English'),
  ('The Wall Street Journal','https://feeds.a.dj.com/rss/RSSWorldNews.xml','English');
