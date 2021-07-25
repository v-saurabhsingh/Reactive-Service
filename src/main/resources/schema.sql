CREATE TABLE IF NOT EXISTS posts (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  content VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  version INT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS comments (
  id INT NOT NULL AUTO_INCREMENT,
  content VARCHAR(255) NOT NULL,
  post_id INT NOT NULL,
  created_at TIMESTAMP ,
  updated_at TIMESTAMP,
  version INT,
  PRIMARY KEY (id),
  FOREIGN KEY(post_id)
    REFERENCES posts(id)
);