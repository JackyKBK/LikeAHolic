package org.champsoft.likeaholic.dataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User getByUserId(String userId);
}