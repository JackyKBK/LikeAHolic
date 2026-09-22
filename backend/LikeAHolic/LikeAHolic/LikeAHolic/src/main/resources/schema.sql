CREATE TABLE IF NOT EXISTS USER_TABLE (
                                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                          USER_ID VARCHAR(255) NOT NULL UNIQUE,
                                          name VARCHAR(255),
                                          email VARCHAR(255),
                                        password VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS POST_TABLE (
                                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                          title VARCHAR(255),
                                          post_id VARCHAR(50) NOT NULL UNIQUE,
                                          content VARCHAR(255),
                                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                          user_id BIGINT,
                                          IMAGE_URL VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES USER_TABLE(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS LIKES_TABLE (
                                           id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                           user_id BIGINT,
                                           post_id BIGINT,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           FOREIGN KEY (user_id) REFERENCES USER_TABLE(id),
                                           FOREIGN KEY (post_id) REFERENCES POST_TABLE(id),
    CONSTRAINT unique_like UNIQUE (user_id, post_id)
    );