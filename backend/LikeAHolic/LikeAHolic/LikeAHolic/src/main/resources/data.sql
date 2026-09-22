INSERT INTO USER_TABLE(user_id, name, email, password) VALUES
                                                (  '1', 'alice.johnson@example.com', 'email1', 'pass2'),
                                                ( '2', 'bob.smith@example.com', 'email2', 'pass3'),
                                                (  '3', 'charlie.brown@example.com', 'email3', 'pass3');



INSERT INTO POST_TABLE (post_id, title, content, user_id, IMAGE_URL) VALUES
                                                                         ('1', 'Gaming Blog', 'Started a new game today. It is really interesting!', 1, 'gaming.png'),
                                                                         ('2', 'Photography', 'Captured some amazing photos today in the city.', 2, 'city.png'),
                                                                         ('3', 'Food Adventures', 'I tried a new restaurant today, highly recommend it!', 3, 'food.png');
INSERT INTO LIKES_TABLE (user_id, post_id, created_at, updated_at)
VALUES
    (1, 1, '2024-11-10 09:15:00', '2024-11-10 09:15:09');
